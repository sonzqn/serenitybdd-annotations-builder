package serenitylabs.tutorials.trains.search;

public class FindTickets {

    public static FindOneWayTickets.Builder forAOneWayTrip() {
        return new FindOneWayTickets.Builder();
    }

    public static FindReturnTickets.Builder forAReturnTrip() {
        return new FindReturnTicketsBuilder.Builder();
    }

    public static FindSeasonTickets.Builder forASeasonTicket() {
        return new FindSeasonTickets.Builder();
    }

}
