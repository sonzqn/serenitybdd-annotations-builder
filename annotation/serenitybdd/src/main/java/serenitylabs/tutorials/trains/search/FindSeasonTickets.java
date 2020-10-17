package serenitylabs.tutorials.trains.search;

import com.serenity.annotation.processor.GetBuilder;
import com.serenity.annotation.processor.StepData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static com.serenity.annotation.processor.StepOrder.*;

@GetBuilder
public class FindSeasonTickets extends FindSeasonTicketsBuilder implements Task {
    @StepData(value = "ofDuration", order = First)
    SeasonTicketDuration duration;
    @StepData("from")
    String origin;
    @StepData(value = "to", order = Last)
    String destination;

    @Override
    @Step("{0} looks for a one-way ticket from #origin to #destination #departureDay")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectItinerary.from(origin).to(destination),
                Click.on(JourneyDetails.SEASON_TICKET_TYPE.of(duration.name())),
                Click.on(JourneyDetails.BUY_TICKETS_BUTTON)
        );
    }
}
