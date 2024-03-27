package com.javaweb.springbootnonjwt.repository;

import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;

import java.util.List;
import java.util.Map;

public interface DistrictRepository {
    DistrictEntity findById(int id);
}
