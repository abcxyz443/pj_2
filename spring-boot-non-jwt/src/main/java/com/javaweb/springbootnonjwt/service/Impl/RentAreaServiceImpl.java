package com.javaweb.springbootnonjwt.service.Impl;

import com.javaweb.springbootnonjwt.repository.RentAreaReponsitory;
import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;
import com.javaweb.springbootnonjwt.service.RentAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentAreaServiceImpl implements  RentAreaService{
    @Autowired
    private RentAreaReponsitory rentAreaReponsitory;

    @Override
    public List<RentAreaEntity> findByBuildingId(int id) {
        List<RentAreaEntity> rentAreaEntities = rentAreaReponsitory.findByBuildingId(id);
        return rentAreaEntities;
    }
}
