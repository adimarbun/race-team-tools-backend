package com.raceteam.racetools.component;

import com.github.javafaker.Faker;
import com.raceteam.racetools.entity.RaceHistory;
import com.raceteam.racetools.service.IFireStoreService;
import com.raceteam.racetools.service.IRaceHistoryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final IRaceHistoryService _raceHistoryService;

    public Scheduler(IRaceHistoryService raceHistoryService){
        _raceHistoryService = raceHistoryService;
    }

    /*
        The scheduler below to simulate car race send data every 1 ms and save to database
    */
    @Scheduled(fixedDelay = 1000,initialDelay = 1000)
    public void schedule(){
        _raceHistoryService.GenerateDataHistoryRace();
    }

    /*
        The scheduler below to update dashboard every 10 ms , get last data from race history
        and update to firestore to show for dashboard
    */
    @Scheduled(fixedDelay = 1000,initialDelay = 1000)
    public void scheduleUpdateDashboard() {

        _raceHistoryService.UpdateRaceDashBoard();
    }
}
