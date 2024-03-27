package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.RentAreaReponsitory;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.repository.entity.RentAreaEntity;
import com.javaweb.springbootnonjwt.util.ConnectionUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentAreaReponsitoryImpl implements RentAreaReponsitory {
    @Override
    public List<RentAreaEntity> findById(int id) {
        String sql = "Select * from rentarea " +
                "inner join building on building.id = rentarea.buildingid " +
                "where building.id = " + id;
        List<RentAreaEntity> result = new ArrayList<RentAreaEntity>();
        try(Connection conn = ConnectionUtil.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)){
            while(rs.next()){
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setId(rs.getInt("id"));
                rentAreaEntity.setBuildingId(rs.getInt("buildingid"));
                rentAreaEntity.setValue(rs.getInt("value"));
                result.add(rentAreaEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
