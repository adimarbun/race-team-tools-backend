package com.raceteam.racetools.dto;

import java.time.LocalDateTime;

public class RaceHistoryModel {
    private Long id;
    private int speed;
    private int brakeCondition;
    private int gear;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String brakeConditionName = conversionBrake(brakeCondition);
    private String gearName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBrakeCondition() {
        return brakeCondition;
    }

    public void setBrakeCondition(int brakeCondition) {
        this.brakeCondition = brakeCondition;
        this.brakeConditionName = conversionBrake(brakeCondition);
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
        this.gearName = conversionGear(gear);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getBrakeConditionName() {
        return brakeConditionName;
    }

    public void setBrakeConditionName(String brakeConditionName) {
        this.brakeConditionName = brakeConditionName;
    }

//    public void setBrakeConditionName(){
//        this.brakeConditionName = conversionBrake(this.brakeCondition);
//    }

    public String getGearName() {
        return gearName;
    }

    public void setGearName(String gearName) {
        this.gearName = gearName;
    }
//    public void setGearName(){
//        this.gearName = conversionGear(this.gear);
//    }

    public String conversionGear(int gear){
        switch (gear){
            case 1:
                return "1";
            case 2:
                return "2";
            case 3:
                return  "3";
            case 4:
                return  "4";
            case 5:
                return  "5";
            case 6:
                return  "R";
            default:
                return  "";
        }
    }

    public String conversionBrake(int brake){
        switch (brake){
            case 1:
                return "Cold";
            case 2:
                return "Warm";
            case 3:
                return "Hot";
            default:
                return "";
        }
    }
}



