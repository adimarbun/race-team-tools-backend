package com.raceteam.racetools.service;

import com.raceteam.racetools.dto.RaceHistoryModel;
import com.raceteam.racetools.entity.RaceHistory;
import java.util.concurrent.ExecutionException;

public interface IFireStoreService {
    String saveHistory(RaceHistoryModel raceHistory) throws ExecutionException, InterruptedException;
}
