package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.util.ConnectionUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    @Override
    public List<BuildingEntity> findAll(Map<String,Object> params, List<String> typeCode) {
        String sql = "Select DISTINCT building.* from building ";
        String join = "";
        String where = " WHERE 1=1 ";
        if(params.containsKey("name") && !params.get("name").equals("")){
            where += " AND building.name LIKE '%" + params.get("name") + "%' ";
        }
        if(params.get("floorarea") != null){
            where += " AND building.floorarea = " + params.get("floorarea") +" ";
        }
        if(params.get("districtId") != null){
            where += " AND building.districtId = " + params.get("districtId") +" ";
        }
        if(params.containsKey("ward") && !params.get("ward").equals("")){
            where += " AND building.ward like '%" + params.get("ward") +"%' ";
        }
        if(params.containsKey("street") && !params.get("street").equals("")){
            where += " AND building.street LIKE '%" + params.get("street") +"%' ";
        }
        if(params.get("numberofbasement")!=null){
            where += " AND building.numberofbasement = " + params.get("numberofbasement") +" ";
        }
        if(params.containsKey("direction") && !params.get("direction").equals("")){
            where += " AND building.direction = '%" + params.get("direction") +"%' ";
        }
        if(params.containsKey("level") && !params.get("level").equals("")){
            where += " AND building.level = '%" + params.get("level") +"%' ";
        }
        if(params.get("areaMin") !=null || params.get("areaMax") != null){
            join += " inner join rentarea on building.id = rentarea.buildingid ";
            if (params.get("areaMin") != null) {
                where += " AND rentarea.value >= " + params.get("areaMin") + " ";
            }
            if(params.get("areaMax") != null) {
                where += " AND rentarea.value <= " + params.get("areaMax") + " ";
            }
        }
        if(params.get("rentpriceMin")!=null){
            where += " AND building.rentprice >= " + params.get("rentpriceMin") +" ";
        }
        if(params.get("rentpriceMax")!=null){
            where += " AND building.rentprice <= " + params.get("rentpriceMax") +" ";
        }
        if(params.containsKey("managername") && !params.get("managername").equals("")){
            where += " AND building.managername like '%" + params.get("managername") +"%' ";
        }
        if(params.containsKey("managerphonenumber") && !params.get("managerphonenumber").equals("")){
            where += " AND building.managerphonenumber like '%" + params.get("managerphonenumber") +"%' ";
        }
        if(params.get("staffid") != null ){
            join += " inner join assignmentbuilding on building.id = assignmentbuilding.buildingid ";
            where += " AND assignmentbuilding.staffid = " + params.get("staffid") +" ";
        }
        if(typeCode != null){
            join += " inner join buildingrenttype on building.id = buildingrenttype.buildingid" +
                    " inner join  renttype on renttype.id = buildingrenttype.renttypeid ";
            where += " AND (";
            for(String s : typeCode){
                where += " renttype.code like '%" + s + "%' OR ";
            }
            where = where.substring(0, where.lastIndexOf("OR"));
            where += ") ";
        }
        sql += join + where;
        List<BuildingEntity> result = new ArrayList<BuildingEntity>();
        try(Connection conn = ConnectionUtil.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)){
            while(rs.next()){
                BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setId(rs.getInt("id"));
                buildingEntity.setName(rs.getString("name"));
                buildingEntity.setWard(rs.getString("ward"));
                buildingEntity.setStreet(rs.getString("street"));
                buildingEntity.setDistrictId(rs.getInt("districtId"));
                buildingEntity.setNumberOfBasement(rs.getLong("numberOfBasement"));
                buildingEntity.setFloorArea(rs.getLong("floorArea"));
                buildingEntity.setRentPrice(rs.getLong("rentPrice"));
                buildingEntity.setServiceFee(rs.getLong("serviceFee"));
                buildingEntity.setBrokerageFee(rs.getLong("brokerageFee"));
                buildingEntity.setManagerName(rs.getString("managerName"));
                buildingEntity.setManagerPhoneNumber(rs.getString("managerPhoneNumber"));
                result.add(buildingEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
