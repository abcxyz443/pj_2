package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.builder.BuildingSearchBuilder;
import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.util.ConnectionUtil;
import com.javaweb.springbootnonjwt.util.NumberUtil;
import com.javaweb.springbootnonjwt.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    //hàm chuyên sử lý join
    public void queryJoin(BuildingSearchBuilder builder, StringBuilder sql){
        Long rentAreaFrom = builder.getAreaFrom();
        Long rentAreaTo = builder.getAreaTo();
        if(rentAreaTo != null || rentAreaFrom != null) {
            sql.append(" join rentarea r on r.buildingid = b.id ");
        }
        Long staffId = builder.getStaffId();
        if(staffId != null){
            sql.append(" JOIN assignmentbuilding asm on asm.buildingid = b.id ");
        }
        List<String> typeCode = builder.getTypeCode();
        if(typeCode !=null && !typeCode.isEmpty()){
            sql.append(" inner join buildingrenttype brt on b.id = brt.buildingid" +
                    " inner join  renttype rt on rt.id = brt.renttypeid ");
        }
    }
    public void queryWhereNomal(BuildingSearchBuilder builder, StringBuilder where){


        //        for(Map.Entry<String,Object> item: params.entrySet()){
//            if(!item.getKey().equals("staffId") && !item.getKey().equals("typeCode") && !item.getKey().startsWith("area")
//                && !item.getKey().startsWith("rentPrice")){
//                String data = item.getValue().toString();
//                if(StringUtil.checkString(data)){
//                    if(NumberUtil.checkNumber(data)){
//                        where.append(" AND b." + item.getKey().toLowerCase() + " = "+ data);
//                    }else{
//                        where.append(" AND b." + item.getKey().toLowerCase() + " like '%" + data + "%' ");
//                    }
//                }
//            }
//        }

        try{
            // Chua ten truong du lieu
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item: fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
               && !fieldName.startsWith("rentPrice")){
                    Object value = item.get(builder);
                    if(value!=null){
                        if(item.getType().getName().equals("java.lang.Long")){
                            where.append(" AND b." + fieldName.toLowerCase() + " = "+ value);
                        }else if(item.getType().getName().equals("java.lang.Integer")){
                            where.append(" AND b." + fieldName.toLowerCase() + " = "+ value);
                        }else if(item.getType().getName().equals("java.lang.String")){
                            where.append(" AND b." + fieldName.toLowerCase() + " like '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch(Exception err){
            err.printStackTrace();
        }
    }

    public void queryWhereSpecial(BuildingSearchBuilder builder, StringBuilder where){
        Long staffId = builder.getStaffId();
        if(staffId != null){
            where.append(" AND asm.staffid = " + staffId);
        }
        Long rentAreaFrom = builder.getAreaFrom();
        Long rentAreaTo = builder.getAreaTo();
        if( rentAreaFrom != null ){
            where.append(" AND r.value >= " + rentAreaFrom);
        }
        if(rentAreaTo != null){
            where.append(" AND r.value <= " + rentAreaTo);
        }
        Long rentPriceFrom = builder.getRentPriceFrom();
        Long rentPriceTo = builder.getRentPriceTo();
        if(rentPriceFrom != null ){
            where.append(" AND b.rentprice >= " + rentPriceFrom);
        }
        if(rentPriceTo!= null){
            where.append(" AND b.rentprice <= " + rentPriceTo);
        }
        //Java 7
//        if(typeCode != null && !typeCode.isEmpty()){
//            for(int i=0;i<typeCode.size();i++){
//                where.append(" renttype.code like '%" + typeCode.get(i) +"%' ");
//                if(i != typeCode.size()-1){
//                    where.append(" OR ");
//                }
//            }
//        }
        List<String> typeCode = builder.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            where.append(" AND (");
            String sqlJoin = typeCode.stream().map(item -> "rt.code LIKE '%" + item + "%' ").collect(Collectors.joining(" OR "));
            where.append(sqlJoin + ") ");
        }
    }
    public String heloWold(){
        return "hello";
    }
    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("Select b.id, b.name, b.districtid, b.street, b.ward" +
                ", b.numberofbasement, b.managername, b.managerphonenumber" +
                ", b.floorarea, b.rentprice, b.brokeragefee, b.servicefee" +
                " from building b ");

        queryJoin(builder,sql);

        StringBuilder where = new StringBuilder("Where 1=1 ");
        queryWhereNomal(builder,where);
        queryWhereSpecial(builder,where);

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
