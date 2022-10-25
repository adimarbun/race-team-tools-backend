package com.raceteam.racetools.service;

import com.raceteam.racetools.dto.RaceHistoryModel;
import com.raceteam.racetools.entity.RaceHistory;
import java.util.List;

public interface IRaceHistoryService {
    List<RaceHistory> GetHistory();
    RaceHistory CreateRaceHistory(RaceHistory raceHistory);
    void GenerateDataHistoryRace();
    void UpdateRaceDashBoard();
    List<RaceHistoryModel> GetHistoryLastFiveMinutes(int offset,int pageSize);
}
