package serenitylabs.tutorials.trains.search;

import com.serenity.annotation.processor.StepData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static com.serenity.annotation.processor.StepOrder.First;

public class SelectItinerary extends SelectItineraryBuilder implements Task {
    @StepData(value = "from", order = First)
    String origin;
    @StepData("to")
    String destination;

    @Override
    @Step("{0} selects an itinerary from #origin to #destination")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectTheStation.called(origin).in(JourneyDetails.ORIGIN),
                SelectTheStation.called(destination).in(JourneyDetails.DESTINATION)
        );
    }
}
