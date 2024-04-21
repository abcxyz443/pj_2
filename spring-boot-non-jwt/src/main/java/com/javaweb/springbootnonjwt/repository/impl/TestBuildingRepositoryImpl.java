package com.javaweb.springbootnonjwt.repository.impl;

import com.javaweb.springbootnonjwt.builder.BuildingSearchBuilder;
import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Primary //đánh dấu là thằng chính
@Repository
public class TestBuildingRepositoryImpl implements BuildingRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
//        String jpql = "Select b from BuildingEntity b Where 1=1 And b.name like '%building%' and b.numberOfBasement = 1 ";
//        Query query = entityManager.createQuery( jpql,BuildingEntity.class);
        String sql = "Select * from building b where 1=1 And b.name like '%building%' and b.numberOfBasement = 1";
        Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
        return query.getResultList();
    }
}
