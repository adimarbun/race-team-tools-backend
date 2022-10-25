package com.raceteam.racetools.controller;

import com.raceteam.racetools.dto.GeneralResponse;
import com.raceteam.racetools.service.IRaceStatusService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaceStatusController {
    private final IRaceStatusService raceStatusService;
    public RaceStatusController(IRaceStatusService raceStatusService){
        this.raceStatusService = raceStatusService;
    }

    @PutMapping("start")
    private GeneralResponse startRace(){
        return GeneralResponse.response(this.raceStatusService.startRace());
    }

    @PutMapping("stop")
    private GeneralResponse stopRace(){
        return GeneralResponse.response(this.raceStatusService.stopRace());
    }
}
