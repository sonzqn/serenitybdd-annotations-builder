package serenitylabs.tutorials.trains.features;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/buy_tickets/buy_individual_tickets.feature")
public class BuyIndividualTickets {}
