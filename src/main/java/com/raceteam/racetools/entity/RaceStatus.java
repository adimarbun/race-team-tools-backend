package com.raceteam.racetools.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class RaceStatus {
    @Id
    private int id;
    private Boolean isRaceRun = true;
    private LocalDateTime startRaceAt = LocalDateTime.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getRaceRun() {
        return isRaceRun;
    }

    public void setRaceRun(Boolean raceRun) {
        isRaceRun = raceRun;
    }

    public LocalDateTime getStartRaceAt() {
        return startRaceAt;
    }

    public void setStartRaceAt(LocalDateTime startRaceAt) {
        this.startRaceAt = startRaceAt;
    }
}
