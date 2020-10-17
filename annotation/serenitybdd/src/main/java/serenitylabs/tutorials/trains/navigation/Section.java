package serenitylabs.tutorials.trains.navigation;

public enum Section {
    BuyTickets("https://www.cheapoair.com/"),

    BuySeasonTickets("https://www.cheapoair.com/"),

    LiveTravelUpdates("https://www.cheapoair.com/");

    private final String url;

    Section(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }
    }
