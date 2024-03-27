package com.javaweb.springbootnonjwt.service;

import com.javaweb.springbootnonjwt.DTO.BuidingDTO;
import com.javaweb.springbootnonjwt.repository.DistrictRepository;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService{
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public DistrictEntity findById(int id) {
        DistrictEntity districtEntity = districtRepository.findById(id);
        return districtEntity;
    }
}
