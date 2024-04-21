package com.javaweb.springbootnonjwt.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rentarea")


public class RentAreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="value")
    private Long value;

    @ManyToOne
    @JoinColumn(name ="buildingid")
    private BuildingEntity building;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public BuildingEntity getBuildingId() {
        return building;
    }

    public void setBuildingId(BuildingEntity buildingId) {
        this.building = buildingId;
    }
}
