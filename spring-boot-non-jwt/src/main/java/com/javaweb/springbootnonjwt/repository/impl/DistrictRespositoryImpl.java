package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.repository.DistrictRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.repository.entity.DistrictEntity;
import com.javaweb.springbootnonjwt.util.ConnectionUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DistrictRespositoryImpl implements DistrictRepository {
    @Override
    public DistrictEntity findById(int id) {
        String sql = "Select * from district ";

        String where = "Where id = " + id;
        sql += where;
       DistrictEntity districtEntity = new DistrictEntity();
        try(Connection conn = ConnectionUtil.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)){
            while(rs.next()) {
                districtEntity.setId(rs.getInt("id"));
                districtEntity.setCode(rs.getString("code"));
                districtEntity.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return districtEntity;
    }
}
