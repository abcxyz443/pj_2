package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.util.ConnectionUtil;
import com.javaweb.springbootnonjwt.util.NumberUtil;
import com.javaweb.springbootnonjwt.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    //hàm chuyên sử lý join
    public void queryJoin(Map<String,Object> params, List<String> typeCode, StringBuilder sql){
        String rentAreaFrom = (String)params.get("areaMin");
        String rentAreaTo = (String)params.get("areaMax");
        if(StringUtil.checkString(rentAreaFrom) || StringUtil.checkString(rentAreaTo)){
            sql.append(" join rentarea r on r.buildingid = b.id ");
        }
        String staffId = (String)params.get("staffId");
        if(StringUtil.checkString(staffId)){
            sql.append(" JOIN assignmentbuilding asm on asm.buildingid = b.id ");
        }
        if(typeCode !=null && !typeCode.isEmpty()){
            sql.append(" inner join buildingrenttype brt on b.id = brt.buildingid" +
                    " inner join  renttype rt on rt.id = brt.renttypeid ");
        }
    }
    public void queryWhereNomal(Map<String,Object> params, List<String> typeCode, StringBuilder where){
        for(Map.Entry<String,Object> item: params.entrySet()){
            if(!item.getKey().equals("staffId") && !item.getKey().equals("typeCode") && !item.getKey().startsWith("area")
                && !item.getKey().startsWith("rentPrice")){
                String data = item.getValue().toString();
                if(StringUtil.checkString(data)){
                    if(NumberUtil.checkNumber(data)){
                        where.append(" AND b." + item.getKey().toLowerCase() + " = "+ data);
                    }else{
                        where.append(" AND b." + item.getKey().toLowerCase() + " like '%" + data + "%' ");
                    }
                }
            }
        }
    }

    public void queryWhereSpecial(Map<String,Object> params, List<String> typeCode, StringBuilder where){
        String staffId = (String)params.get("staffId");
        if(StringUtil.checkString(staffId)){
            where.append(" AND asm.staffid = " + staffId);
        }
        String rentAreaFrom = (String)params.get("areaMin");
        String rentAreaTo = (String)params.get("areaMax");
        if(StringUtil.checkString(rentAreaFrom) ){
            where.append(" AND r.value >= " + rentAreaFrom);
        }
        if(StringUtil.checkString(rentAreaTo)){
            where.append(" AND r.value <= " + rentAreaTo);
        }
        String rentPriceFrom = (String)params.get("rentPriceMin");
        String rentPriceTo = (String)params.get("rentPriceMax");
        if(StringUtil.checkString(rentAreaFrom) ){
            where.append(" AND b.rentprice >= " + rentPriceFrom);
        }
        if(StringUtil.checkString(rentAreaTo)){
            where.append(" AND b.rentprice <= " + rentPriceTo);
        }
        //Java 7
        if(typeCode != null && !typeCode.isEmpty()){
            for(int i=0;i<typeCode.size();i++){
                where.append(" renttype.code like '%" + typeCode.get(i) +"%' ");
                if(i != typeCode.size()-1){
                    where.append(" OR ");
                }
            }
        }
    }
    @Override
    public List<BuildingEntity> findAll(Map<String,Object> params, List<String> typeCode) {
        StringBuilder sql = new StringBuilder("Select b.id, b.name, b.districtid, b.street, b.ward" +
                ", b.numberofbasement, b.managername, b.managerphonenumber" +
                ", b.floorarea, b.rentprice, b.brokeragefee, b.servicefee" +
                " from building b ");

        queryJoin(params,typeCode,sql);

        StringBuilder where = new StringBuilder("Where 1=1 ");
        queryWhereNomal(params,typeCode,where);
        queryWhereSpecial(params,typeCode,where);

        sql.append(where);
        sql.append(" Group by b.id");
        List<BuildingEntity> result = new ArrayList<BuildingEntity>();
        try(Connection conn = ConnectionUtil.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql.toString())){
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
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return result;
    }
}
