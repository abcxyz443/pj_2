package com.javaweb.springbootnonjwt.repository.entity;

public class RentAreaEntity {
    private int id;
    private int buildingId;
    private int value;

    public int getId() {
        return id;
    }

    public void setId(int
                              id) {
        this.id = id;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
