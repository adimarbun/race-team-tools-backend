package com.raceteam.racetools.service.serviceImpl;

import com.github.javafaker.Faker;
import com.raceteam.racetools.dto.RaceHistoryModel;
import com.raceteam.racetools.entity.RaceHistory;
import com.raceteam.racetools.repository.RaceHistoryRepository;
import com.raceteam.racetools.service.IFireStoreService;
import com.raceteam.racetools.service.IRaceHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceHistoryService implements IRaceHistoryService {

    private final RaceHistoryRepository raceHistoryRepository;
    private final IFireStoreService _fireStoreService;
    private final Logger logger = LoggerFactory.getLogger(RaceHistoryService.class);

    public RaceHistoryService(RaceHistoryRepository raceHistoryRepository,IFireStoreService fireStoreService) {
        this.raceHistoryRepository = raceHistoryRepository;
        this._fireStoreService = fireStoreService;
    }

    /*
    Get all Race history
     */
    @Override
    public List<RaceHistory> GetHistory() {
        return (List<RaceHistory>) this.raceHistoryRepository.findAll();
    }

    /*
    Create race history to database
     */
    @Override
    public RaceHistory CreateRaceHistory(RaceHistory raceHistory) {
        return this.raceHistoryRepository.save(raceHistory);
    }

    /*
    Generate Simulate data history race
    random data by faker
     */
    @Override
    public void GenerateDataHistoryRace() {
        Faker faker = new Faker();
        RaceHistory data = new RaceHistory();
        data.setBrakeCondition(faker.number().numberBetween(1,3));
        data.setGear(faker.number().numberBetween(1,6));
        data.setSpeed(faker.number().numberBetween(1,300));
        data.setCreatedAt(LocalDateTime.now());
        this.raceHistoryRepository.save(data);
    }

    /*
    Method to update race dashboard , get last data from race history
        and update to firestore to show for dasboard
     */
    @Override
    public void UpdateRaceDashBoard() {
        Page<RaceHistoryModel> dataPage = this.raceHistoryRepository.
                findAll(PageRequest.of(0, 1, Sort.by("id").descending()))
                .map((temp)->{
                    RaceHistoryModel obj = new RaceHistoryModel();
                    obj.setId(temp.getId());
                    obj.setSpeed(temp.getSpeed());
                    obj.setGear(temp.getGear());
                    obj.setCreatedAt(temp.getCreatedAt());
                    obj.setBrakeCondition(temp.getBrakeCondition());
                    return obj;
                });;

        if(!dataPage.getContent().isEmpty()){
            RaceHistoryModel data = dataPage.getContent().get(0);
            data.setId((long)1);
            try {
                _fireStoreService.saveHistory(data);
            }catch (Exception ex){
                logger.error("err -"+ex.getMessage());
            }
        }

    }

    @Override
    public void StartRace() {

    }

    /*
    To get List history last five minutes from createdAt last insert data
     */
    @Override
    public List<RaceHistoryModel> GetHistoryLastFiveMinutes(int offset, int pageSize) {
        Page<RaceHistory> dataLast = this.raceHistoryRepository.
                findAll(PageRequest.of(0, 1, Sort.by("id").descending()));;
        LocalDateTime toDate = dataLast.getContent().get(0).getCreatedAt();
        LocalDateTime fromDate = toDate.minusMinutes(5);
        return this.raceHistoryRepository.findAllByCreatedAtBetweenOrderByCreatedAtDesc(fromDate,toDate).stream().map((temp)->{
            RaceHistoryModel obj = new RaceHistoryModel();
                    obj.setId(temp.getId());
                    obj.setSpeed(temp.getSpeed());
                    obj.setGear(temp.getGear());
                    obj.setCreatedAt(temp.getCreatedAt());
                    obj.setBrakeCondition(temp.getBrakeCondition());
                    return obj;
        }).collect(Collectors.toList());
    }
}
