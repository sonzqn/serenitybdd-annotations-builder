package serenitylabs.tutorials.trains.search;

import com.serenity.annotation.processor.GetBuilder;
import com.serenity.annotation.processor.StepData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static com.serenity.annotation.processor.StepOrder.*;

@GetBuilder
public class FindReturnTickets implements Task {
    @StepData(value = "from", order = First)
    String origin;
    @StepData("to")
    String destination;
    @StepData("leaving")
    DepartureDay departureDay;
    @StepData(value = "andReturningAfter", order = Last)
    int returningAfterDayCount;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectItineraryEx.from(origin).to(destination),

                EnterDateEx.of(departureDay).into(JourneyDetails.LEAVING_DATE),

                Click.on(JourneyDetails.RETURN_TRIP),
                EnterDateEx.of(departureDay.plus(returningAfterDayCount))
                        .into(JourneyDetails.RETURN_DATE),

                Click.on(JourneyDetails.BUY_TICKETS_BUTTON)
        );
    }
}
