package com.javaweb.springbootnonjwt.service.Impl;

import com.javaweb.springbootnonjwt.DTO.BuidingDTO;
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
    private DistrictService districtService;
    @Autowired
    private RentAreaService rentAreaService;
    @Override
    public List<BuidingDTO> findAll(Map<String,Object> params, List<String> typeCode) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typeCode);
        List<BuidingDTO> result = new ArrayList<BuidingDTO>();
        for(BuildingEntity item:buildingEntities){
            BuidingDTO buildingDTO = new BuidingDTO();
            buildingDTO.setName(item.getName());
            DistrictEntity districtEntity = districtService.findById(item.getDistrictId());
            buildingDTO.setStreet(item.getStreet() +"," + item.getWard() +"," + districtEntity.getName());
            buildingDTO.setBasementNumber(item.getNumberOfBasement());
            buildingDTO.setManagerName(item.getManagerName());
            buildingDTO.setManagerPhone(item.getManagerPhoneNumber());
            buildingDTO.setFloorArea(item.getFloorArea());
            buildingDTO.setEmptyArea(null);

            List<RentAreaEntity> rentAreaEntities = rentAreaService.findById(item.getId());
            List<String> s = new ArrayList<String>();
            for(RentAreaEntity rentAreaEntity : rentAreaEntities){
                s.add(String.valueOf(rentAreaEntity.getValue()));
            }
            String rentedArea = String.join(",",s);

            buildingDTO.setRentedArea(rentedArea);
            buildingDTO.setRentCode(item.getRentPrice());
            buildingDTO.setServiceFee(item.getServiceFee());
            buildingDTO.setBrokerageFee(item.getBrokerageFee());
            result.add(buildingDTO);
        }
        return result;
    }
}
