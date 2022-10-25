package com.raceteam.racetools.service.serviceImpl;

import com.raceteam.racetools.entity.RaceStatus;
import com.raceteam.racetools.repository.RaceHistoryRepository;
import com.raceteam.racetools.repository.RaceStatusRepository;
import com.raceteam.racetools.service.IRaceStatusService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RaceStatusService implements IRaceStatusService {

    private  final RaceStatusRepository raceStatusRepository;
    private final RaceHistoryRepository raceHistoryRepository;

    public RaceStatusService(RaceStatusRepository raceStatusRepository,RaceHistoryRepository raceHistoryRepository){
        this.raceStatusRepository = raceStatusRepository;
        this.raceHistoryRepository = raceHistoryRepository;
    }
    @Override
    public boolean startRace() {
        this.raceHistoryRepository.deleteAll();
        Optional<RaceStatus> raceStatusData = this.raceStatusRepository.findById(1);
        RaceStatus raceStatus = new RaceStatus();
        if(raceStatusData.isPresent()){
            raceStatus = raceStatusData.get();
            raceStatus.setStartRaceAt(LocalDateTime.now());
            raceStatus.setRaceRun(true);
            raceStatusRepository.save(raceStatus);
        }else{
            raceStatus.setId(1);
            raceStatus.setStartRaceAt(LocalDateTime.now());
            this.raceStatusRepository.save(raceStatus);
        }
        return true;
    }

    @Override
    public boolean stopRace() {
        Optional<RaceStatus> raceStatusData = this.raceStatusRepository.findById(1);
        RaceStatus raceStatus = new RaceStatus();
        if(raceStatusData.isPresent()){
            raceStatus = raceStatusData.get();
            raceStatus.setRaceRun(false);
            raceStatusRepository.save(raceStatus);
        }
        return true;
    }
}
