package com.javaweb.springbootnonjwt.DTO;

import java.util.List;

public class BuidingDTO {
    private String name;
    private String street;//street,ward,districtName
    private Long basementNumber;

    private String managerName;
    private String managerPhone;
    private Long floorArea;
    private Long emptyArea;
    private String rentedArea;
    private Long rentCode;
    private Long serviceFee;

    private Long brokerageFee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getBasementNumber() {
        return basementNumber;
    }

    public void setBasementNumber(Long basementNumber) {
        this.basementNumber = basementNumber;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public Long getEmptyArea() {
        return emptyArea;
    }

    public void setEmptyArea(Long emptyArea) {
        this.emptyArea = emptyArea;
    }

    public String getRentedArea() {
        return rentedArea;
    }

    public void setRentedArea(String rentedArea) {
        this.rentedArea = rentedArea;
    }

    public Long getRentCode() {
        return rentCode;
    }

    public void setRentCode(Long rentCode) {
        this.rentCode = rentCode;
    }

    public Long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Long brokerageFee) {
        this.brokerageFee = brokerageFee;
    }
}
