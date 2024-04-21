package com.javaweb.springbootnonjwt.converter;

import com.javaweb.springbootnonjwt.DTO.BuidingDTO;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;
import com.javaweb.springbootnonjwt.service.BuildingService;
import com.javaweb.springbootnonjwt.service.DistrictService;
import com.javaweb.springbootnonjwt.service.RentAreaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
//    @Autowired
//    private DistrictService districtService;
//    @Autowired
//    private RentAreaService rentAreaService;
    @Autowired
    private ModelMapper modelMapper;
    public BuidingDTO toBuildingDTO(BuildingEntity item){
        BuidingDTO buildingDTO = modelMapper.map(item, BuidingDTO.class); //genaric class
        buildingDTO.setStreet(item.getStreet() +"," + item.getWard() +"," + item.getDistrict().getName());

        String rentedArea  = item.getRentAreas().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        buildingDTO.setRentedArea(rentedArea);
        buildingDTO.setServiceFee(item.getServiceFee());
        buildingDTO.setBrokerageFee(item.getBrokerageFee());
        return buildingDTO;
    }
}
