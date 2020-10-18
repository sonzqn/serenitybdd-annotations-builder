package serenitylabs.tutorials.trains.search;

import net.serenitybdd.screenplay.targets.Target;

class JourneyDetails {
    static final Target ONEWAY_BUTTON = Target.the("one way button")
            .locatedBy("//label[@for='onewayTrip']");

    static final Target ORIGIN = Target.the("origin field")
            .locatedBy("//input[@aria-describedby='from0autoSuggestLabel']");

    static final Target DROPDOWN_ENTRY = Target.the("dropdown entry '{0}")
            .locatedBy("//span[contains(string(),'{0}')]");

    static final Target DESTINATION = Target.the("destination field")
            .locatedBy("//input[@aria-describedby='to0autoSuggestLabel']");

    static final Target BUY_TICKETS_BUTTON = Target.the("buy tickets button")
            .locatedBy("//input[@id='searchNow']");

    static final Target LEAVING_DATE = Target.the("leaving date")
            .locatedBy("(//input[@aria-label='Choose a departure date. expanded']");

    static final Target SEASON_TICKET_TYPE = Target.the("{0} season ticket trip")
            .locatedBy("//label[.='{0}']");

    static final Target RETURN_TRIP = Target.the("return trip")
            .locatedBy("//label[.='Return']");

    static final Target RETURN_DATE = Target.the("return date")
            .locatedBy("(//*[contains(@class,'date-time__display-primary')])[2]");
}