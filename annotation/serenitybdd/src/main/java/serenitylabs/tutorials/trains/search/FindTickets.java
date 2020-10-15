package serenitylabs.tutorials.trains.search;

public class FindTickets {

    public static FindOneWayTicketsEx.Builder forAOneWayTrip() {
        return new FindOneWayTicketsEx.Builder();
    }

    public static FindReturnTicketsEx.Builder forAReturnTrip() {
        return new FindReturnTicketsEx.Builder();
    }

    public static FindSeasonTicketsEx.Builder forASeasonTicket() {
        return new FindSeasonTicketsEx.Builder();
    }

}
