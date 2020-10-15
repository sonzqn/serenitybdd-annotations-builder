package serenitylabs.tutorials.trains.search;

import com.serenity.annotation.processor.StepData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static com.serenity.annotation.processor.StepOrder.First;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

class SelectTheStation implements Interaction {
    @StepData(value = "called", order = First)
    String stationName;
    @StepData( "in")
    Target stationDropdown;

    @Override
    @Step("{0} selects station #stationName")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(stationName).into(stationDropdown),
                WaitUntil.the(JourneyDetails.DROPDOWN_ENTRY.of(stationName), isVisible()),
                Click.on(JourneyDetails.DROPDOWN_ENTRY.of(stationName))
        );
    }
}
