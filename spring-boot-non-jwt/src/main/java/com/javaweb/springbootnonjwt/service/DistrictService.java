package com.javaweb.springbootnonjwt.service;


import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;

import java.util.List;

public interface DistrictService {
    DistrictEntity findById(int id);
}
