package serenitylabs.tutorials.trains.features.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matcher;
import serenitylabs.tutorials.trains.journeys.TheAvailableJourneys;
import serenitylabs.tutorials.trains.navigation.Navigate;
import serenitylabs.tutorials.trains.search.DepartureDay;
import serenitylabs.tutorials.trains.search.FindTickets;
import serenitylabs.tutorials.trains.search.SeasonTicketDuration;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;
import static serenitylabs.tutorials.trains.navigation.Section.BuySeasonTickets;
import static serenitylabs.tutorials.trains.navigation.Section.BuyTickets;
import static serenitylabs.tutorials.trains.seasontickets.SeasonTicketOptions.seeEachSeasonTicketOptionIn;

public class BuyTicketsStepDefinitions {

    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^that (.*) has decided to check available tickets$")
    public void decided_to_travel_by_train(String personaName) throws Throwable {
        theActorCalled(personaName).attemptsTo(
                Navigate.to(BuyTickets)
        );
    }

    @Given("^that (.*) has decided to check season tickets$")
    public void decided_to_buy_a_season_ticket(String personaName) throws Throwable {
        theActorCalled(personaName).attemptsTo(
                Navigate.to(BuySeasonTickets)
        );
    }

    @When("^s?he looks at a trip from (.*) to (.*) leaving (.*)$")
    public void looks_at_a_trip(String origin,
                                String destination,
                                DepartureDay departureDay) throws Throwable {

        theActorInTheSpotlight().attemptsTo(
                FindTickets
                        .forAOneWayTrip()
                        .from(origin)
                        .to(destination)
                        .leaving(departureDay)
        );
    }

    @When("^s?he looks at a return trip from (.*) to (.*) leaving (.*) and returning in (.*) days$")
    public void looks_at_a_return_trip(String origin,
                                       String destination,
                                       DepartureDay departureDay,
                                       int returningAfterDayCount) throws Throwable {

        theActorInTheSpotlight().attemptsTo(
                FindTickets
                        .forAReturnTrip()
                        .from(origin)
                        .to(destination)
                        .leaving(departureDay)
                        .andReturningAfter(returningAfterDayCount)
        );
    }

    @When("^s?he looks at a (.*) season ticket from (.*) to (.*)")
    public void looks_at_a_season_ticket(SeasonTicketDuration seasonTicketDuration,
                                         String origin,
                                         String destination) throws Throwable {
        theActorInTheSpotlight().attemptsTo(
                FindTickets
                        .forASeasonTicket()
                        .ofDuration(seasonTicketDuration)
                        .from(origin)
                        .to(destination)
        );
    }

    @Then("^s?he should be shown the (.*) ticket price from (.*) to (.*)")
    public void she_should_be_shown_the_cheapest_ticket_price(String ticketType,
                                                              String expectedOrigin,
                                                              String expectedDestination) throws Throwable {
        theActorInTheSpotlight().should(
                seeThat("Promo title", TheAvailableJourneys.ticketType(), equalToIgnoringCase(ticketType)),
                seeThat("Cheapest price", TheAvailableJourneys.lowestPrice(), isPresent()),
                seeThat("Origin station", TheAvailableJourneys.origin(), containsString(expectedOrigin)),
                seeThat("Destination station", TheAvailableJourneys.destination(), containsString(expectedDestination))
        );
    }

    private Matcher<String> isPresent() {
        return not(isEmptyString());
    }

    @Then("^s?he should see the following season ticket options:$")
    public void shouldSeeTheFollowingSeasonTicketOptions(List<String> seasonTicketOptions) throws Throwable {
        theActorInTheSpotlight().should(
                seeEachSeasonTicketOptionIn(seasonTicketOptions)
        );
    }
}
