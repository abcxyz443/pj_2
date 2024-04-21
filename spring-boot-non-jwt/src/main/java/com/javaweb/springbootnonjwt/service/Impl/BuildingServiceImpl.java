package com.javaweb.springbootnonjwt.service.Impl;

import com.javaweb.springbootnonjwt.DTO.BuidingDTO;
import com.javaweb.springbootnonjwt.builder.BuildingSearchBuilder;
import com.javaweb.springbootnonjwt.converter.BuildingConverter;
import com.javaweb.springbootnonjwt.converter.BuildingSearchBuilderConverter;
import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.DistrictRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;
import com.javaweb.springbootnonjwt.service.BuildingService;
import com.javaweb.springbootnonjwt.service.DistrictService;
import com.javaweb.springbootnonjwt.service.RentAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService{
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Override
    public List<BuidingDTO> findAll(Map<String,Object> params, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
//        List<BuildingEntity> buildingEntities = buildingRepository.findByNameContainingAndWardContaining(buildingSearchBuilder.getName(), buildingSearchBuilder.getWard());
//        BuildingEntity buildingEntity = buildingRepository.findById(1L).get();
        List<BuidingDTO> result = new ArrayList<BuidingDTO>();
        for(BuildingEntity item:buildingEntities){
            BuidingDTO buidingDTO = buildingConverter.toBuildingDTO(item);
            result.add(buidingDTO);
        }
        return result;
    }
}
