package serenitylabs.tutorials.trains.search;

import java.time.LocalDate;

public enum DepartureDay {
    today(0), tomorrow(1);

    private int daysFromToday;

    DepartureDay(int daysFromToday) {
        this.daysFromToday = daysFromToday;
    }

    public int daysFromToday() {
        return daysFromToday;
    }

    public DepartureDay plus(int daysAfter) {
        this.daysFromToday = this.daysFromToday + daysAfter;
        return this;
    }

    public String getDepartureDay() {
        return String.valueOf(
                LocalDate.now().plusDays(daysFromToday).getDayOfMonth()
        );
    }
}