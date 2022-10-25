package com.raceteam.racetools.controller;

import com.raceteam.racetools.dto.GeneralResponse;
import com.raceteam.racetools.entity.RaceHistory;
import com.raceteam.racetools.service.IRaceHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("history")
public class RaceHistoryController {
    private final IRaceHistoryService raceHistoryService;
    public RaceHistoryController(IRaceHistoryService raceHistoryService){
        this.raceHistoryService = raceHistoryService;
    }

    @GetMapping
    public List<RaceHistory> findAll(){
        return raceHistoryService.GetHistory();
    }

    @PostMapping
    public RaceHistory createRaceHistory(@RequestBody RaceHistory raceHistory){
        return  raceHistoryService.CreateRaceHistory(raceHistory);
    }

    @GetMapping("public")
    public GeneralResponse getRaceHistoryToPublic(){
        return GeneralResponse.response(raceHistoryService.GetHistoryLastFiveMinutes(0,10));
    }
}
