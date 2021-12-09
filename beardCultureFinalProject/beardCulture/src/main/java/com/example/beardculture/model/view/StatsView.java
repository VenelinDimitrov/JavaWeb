package com.example.beardculture.model.view;

public class StatsView {

    private final int authorizedRequests;
    private final int anonymousRequests;

    public StatsView(int authorizedRequests, int anonymousRequests) {
        this.authorizedRequests = authorizedRequests;
        this.anonymousRequests = anonymousRequests;
    }

    public int getAuthorizedRequests() {
        return authorizedRequests;
    }

    public int getAnonymousRequests() {
        return anonymousRequests;
    }
}
