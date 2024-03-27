package com.javaweb.springbootnonjwt.service;

import com.javaweb.springbootnonjwt.repository.RentAreaReponsitory;
import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentAreaServiceImpl implements  RentAreaService{
    @Autowired
    private RentAreaReponsitory rentAreaReponsitory;

    @Override
    public List<RentAreaEntity> findById(int id) {
        List<RentAreaEntity> rentAreaEntities = rentAreaReponsitory.findById(id);
        return rentAreaEntities;
    }
}
