package serenitylabs.tutorials.trains.journeys;

import net.serenitybdd.screenplay.targets.Target;

public class JourneyList {
    static Target CHEAPEST_PRICE = Target.the("cheapest price")
            .locatedBy("//li[contains(@class,'cheapest')]//span[contains(@class,'price')]//sup/..");
    public static Target CHEAPEST_PRICE_TITLE = Target.the("cheapest price title")
            .locatedBy("//li[contains(@class,'cheapest')]//span[contains(@class,'name')]//span");
    static Target ORIGIN = Target.the("origin")
            .locatedBy("//input[@placeholder='Where from?']");
    static Target DESTINATION = Target.the("destination")
            .locatedBy("//input[@placeholder='Where to?']");
}
