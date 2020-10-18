package com.serenity.annotation.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import com.google.auto.service.AutoService;

@SupportedAnnotationTypes("com.serenity.annotation.processor.StepData")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class StepDataProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {

            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            Map<String, List<Element>> annotatedMethods = annotatedElements.stream().collect(Collectors.groupingBy(element -> ((TypeElement) element.getEnclosingElement()).getQualifiedName().toString()));

            if (annotatedMethods.isEmpty()) {
                continue;
            }
            annotatedMethods.forEach((key, setters) -> {
                boolean isGetBuilder = setters.get(0).getEnclosingElement().getAnnotation(GetBuilder.class)!=null;
                String className = ((TypeElement) setters.get(0).getEnclosingElement()).getQualifiedName().toString();
                String classInterfaceName = ((TypeElement) setters.get(0).getEnclosingElement()).getInterfaces().get(0).toString();

                setters = setters.stream().sorted(Comparator.comparing(setter -> setter.getAnnotation(StepData.class).order())).collect(Collectors.toList());;

                List<List<String>> listSetter = new ArrayList<>();
                for (Element setter : setters) {
                    listSetter.add(Arrays.asList(
                            setter.getAnnotation(StepData.class).value(),
                            setter.asType().toString(),
                            setter.getSimpleName().toString()
                    ));
                }

                try {
                    writeBuilderFile(className, classInterfaceName, isGetBuilder, listSetter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return true;
    }

    private void writeBuilderFile(String className, String classInterfaceName, boolean isGetBuilder, List<List<String>> listSetter) throws IOException {

        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }

        String simpleClassName = className.substring(lastDot + 1);
        String simpleBuilderClassName = "BuilderImp";
        String superClassName = className + "Builder";
        String extendSimpleClassName = "_" + simpleClassName;

        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(superClassName);
        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

            List<String> methodList = new ArrayList<>();
            List<String> typeList = new ArrayList<>();
            List<String> paramList = new ArrayList<>();
            List<String> typeParamList = new ArrayList<>();
            for (List<String> setter : listSetter) {
                methodList.add(setter.get(0));
                typeList.add(setter.get(1));
                paramList.add(setter.get(2));
                typeParamList.add(setter.get(1) + " " + setter.get(2));
            }
            String params = String.join(", ", paramList);
            String typeParams = String.join(", ", typeParamList);

            if (packageName != null) {
                out.println("package " + packageName + ";");
                out.println();
            }

            out.println("import net.serenitybdd.screenplay.Tasks;");
            out.println();
            out.println("class " + simpleClassName + "Builder {");
            out.println("    public static final class Builder extends " + extendSimpleClassName + ".BuilderImp {}");
            if(!isGetBuilder) {
                out.println("    protected static final Builder " + methodList.get(0) + "(" + typeList.get(0) + " " + paramList.get(0) + ") {");
                out.println("        return (Builder)(new Builder()." + methodList.get(0) + "(" + paramList.get(0) + "));");
                out.println("    }");
            }

            out.println("    private static class " + extendSimpleClassName + " extends " + simpleClassName + " {");
            out.println("        protected " + extendSimpleClassName + "(" + typeParams + ") {");
            for (List<String> setter : listSetter) {
                out.print("            this." + setter.get(2));
                out.print(" = ");
                out.print(setter.get(2));
                out.println(";");
            }
            out.println("        }");

            out.println("        public static class " + simpleBuilderClassName + " {");
            out.println("            protected final " + classInterfaceName + " nextStep() {");
            out.println("                return Tasks.instrumented(" + extendSimpleClassName + ".class, " + params + ");");
            out.println("            }");

            for(int i=0; i < methodList.size(); i++) {
                out.println("            private " + typeList.get(i) + " " + paramList.get(i) + ";");
                String returnType = simpleBuilderClassName;
                String returnCode = "this";
                String methodName = " " + methodList.get(i);
                if(i==methodList.size()-1) {
                    returnType = classInterfaceName;
                    returnCode = "nextStep()";
                }
                out.println("            public final " + returnType + methodName + "(" + typeList.get(i) + " " + paramList.get(i) + ") {");
                out.println("                this." + paramList.get(i) + " = " + paramList.get(i) + ";");
                if(!methodName.isEmpty())
                out.println("                return " + returnCode + ";");
                out.println("            }");
            }
            out.println("        }");
            out.println("    }");
            out.println("}");
        }
    }

}
