package serenitylabs.tutorials.trains.search;

import com.serenity.annotation.processor.GetBuilder;
import com.serenity.annotation.processor.StepData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static com.serenity.annotation.processor.StepOrder.*;

@GetBuilder
public class FindOneWayTickets implements Task {
    @StepData(value = "from", order = First)
    String origin;
    @StepData("to")
    String destination;
    @StepData(value = "leaving", order = Last)
    DepartureDay departureDay;

    @Override
    @Step("{0} looks for a one-way ticket from #origin to #destination #departureDay")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectItineraryEx.from(origin).to(destination),
                EnterDateEx.of(departureDay).into(JourneyDetails.LEAVING_DATE),
                Click.on(JourneyDetails.BUY_TICKETS_BUTTON)
        );
    }
}
