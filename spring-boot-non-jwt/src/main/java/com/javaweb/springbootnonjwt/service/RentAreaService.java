package com.javaweb.springbootnonjwt.service;

import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaService {
    List<RentAreaEntity> findByBuildingId(int id);
}
