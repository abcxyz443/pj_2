package com.javaweb.springbootnonjwt.converter;

import com.javaweb.springbootnonjwt.builder.BuildingSearchBuilder;
import com.javaweb.springbootnonjwt.util.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params, List<String> typeCode){

        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtil.getObject(params, "name", String.class))
                .setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
                .setWard(MapUtil.getObject(params, "ward", String.class))
                .setStreet(MapUtil.getObject(params, "street", String.class))
                .setDistrictId(MapUtil.getObject(params, "districtId", Long.class))
                .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Long.class))
                .setTypeCode(typeCode)
                .setManagerName(MapUtil.getObject(params, "managerName", String.class))
                .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhone", String.class))
                .setRentPriceTo(MapUtil.getObject(params, "rentPriceMax", Long.class))
                .setRentPriceFrom(MapUtil.getObject(params, "rentPriceMin", Long.class))
                .setAreaFrom(MapUtil.getObject(params, "areaMin", Long.class))
                .setAreaTo(MapUtil.getObject(params, "areaMax", Long.class))
                .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
                .setDirection(MapUtil.getObject(params, "direction", String.class))
                .setLevel(MapUtil.getObject(params, "level", String.class))
                .build();
        return buildingSearchBuilder;
    }
}
