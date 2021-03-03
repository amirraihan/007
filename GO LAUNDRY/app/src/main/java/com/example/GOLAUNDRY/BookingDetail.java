package com.example.GOLAUNDRY;

public class BookingDetail {

    String name, phone, time, capacity;

    public BookingDetail(){

    }

    public BookingDetail(String name, String phone, String time, String capacity){
        this.name = name;
        this.phone = phone;
        this.time = time;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {this.capacity = capacity;
    }
}
