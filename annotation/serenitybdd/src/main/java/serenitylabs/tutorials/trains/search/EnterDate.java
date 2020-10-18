package serenitylabs.tutorials.trains.search;

import com.serenity.annotation.processor.StepData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static com.serenity.annotation.processor.StepOrder.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

class EnterDate extends EnterDateBuilder implements Interaction {
    @StepData(value = "of", order = First)
    DepartureDay departureDay;
    @StepData("into")
    Target dateField;

    @Override
    @Step("{0} selects day #departureDay")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                //Click.on(dateField),
                WaitUntil.the(DatePicker.DATE_SELECTION_CLOSE, isVisible()),
                Click.on(DatePicker.DAY.of(departureDay.getDepartureDay()))
                //Click.on(DatePicker.DATE_SELECTION_CLOSE)
        );
    }
}
