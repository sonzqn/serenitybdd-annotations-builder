package serenitylabs.tutorials.trains.search;

import net.serenitybdd.screenplay.targets.Target;

class DatePicker {
    static Target DAY = Target.the("departure day")
                              .locatedBy("//a[contains(@aria-label,'{0}')]");//sample input date data '30 October 2020'
    static final Target DATE_SELECTION_CLOSE = Target.the("date selection close button")
                                                    .locatedBy("//a[@id='closeCalendar']");

}

