package com.example.demo.Models;

public class CoffeeHouse {

    private Integer id;
    private String address;
    private String phone;

    public CoffeeHouse(Integer id, String address, String phone) {
        this.id = id;
        this.address = address;
        this.phone = phone;
    }

    public Integer id() {
        return id;
    }

    public void id(Integer coffeeHouseId) {
        this.id = coffeeHouseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
