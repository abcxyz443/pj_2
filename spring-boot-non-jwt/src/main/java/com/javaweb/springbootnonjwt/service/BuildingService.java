package com.javaweb.springbootnonjwt.service;

import com.javaweb.springbootnonjwt.DTO.BuidingDTO;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuidingDTO> findAll(Map<String,Object> params, List<String> typeCode);
}
