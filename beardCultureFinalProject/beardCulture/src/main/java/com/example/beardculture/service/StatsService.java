package com.example.beardculture.service;

import com.example.beardculture.model.view.StatsView;

public interface StatsService {

    void onRequest();
    StatsView getStats();
}
