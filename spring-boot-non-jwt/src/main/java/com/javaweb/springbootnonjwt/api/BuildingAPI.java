package com.javaweb.springbootnonjwt.api;

import com.javaweb.springbootnonjwt.DTO.BuidingDTO;
import com.javaweb.springbootnonjwt.DTO.ErrorResponseDTO;
import com.javaweb.springbootnonjwt.customexception.FiledRequiredException;
import com.javaweb.springbootnonjwt.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BuildingAPI {

    /*@GetMapping(value = "/api/building")
    public Object findBuilding(@RequestParam Map<String,Object> params,
                               @RequestParam("typeCode") List<String> typeCode){
        System.out.println(5/0);
        List<BuidingDTO> list = new ArrayList<>();
        BuidingDTO buidingDTO1 = new BuidingDTO();
        buidingDTO1.setName("ManhBuilDing");
        buidingDTO1.setStreet("Quang Trung");
        buidingDTO1.setWard("War");
        BuidingDTO buidingDTO2 = new BuidingDTO();
        buidingDTO2.setName("Duc");
        list.add(buidingDTO1);
        list.add(buidingDTO2);
        return list;
    }*/
    @Autowired
    private BuildingService buildingService;
    @GetMapping(value = "/api/building")
    public List<BuidingDTO> findBuilding(@RequestParam Map<String,Object> params,
                                         @RequestParam(value = "typeCode",required = false) List<String> typeCode){

        List<BuidingDTO> rs = buildingService.findAll(params, typeCode);
        return rs;
    }
//
//    public void validateData(BuidingDTO buidingDTO){
//        if(buidingDTO.getName() == null || buidingDTO.getName().equals("") || buidingDTO.getManagerName()==null){
//            throw new FiledRequiredException("Name or districtId is null");
//        }
//    }
//    @PostMapping(value = "/building")
//    public Object createBuilding(@RequestBody BuidingDTO buidingDTO){
//        validateData(buidingDTO);
//        System.out.println("OK");
//        return buidingDTO;
//    }
//
//
//    @DeleteMapping(value="/building/{ids}/{name}")
//    public void deleteBuilding(@PathVariable Long[] ids,
//                               @PathVariable String name){
//        System.out.println(ids[1] + " " + ids[2] + " " + ids[0] +" "+name);
//    }
}
