package models;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Vacation {
    
    String userId;
    String country;
    String checkIn;
    String checkOut;
    String hotelName;
    String price;
    String local;
    
    public Vacation(){}

    public Vacation(String userId, String country, String checkIn, String checkOut, String hotelName, String price,String a) {

        this.userId=userId;
        this.country = country;
        this.checkIn = checkIn;
        this.checkOut =checkOut;
        this.hotelName = hotelName;
        this.price = price;
        this.local =a;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
/*
    public Map<String,Object> toMap() {

            HashMap<String, Object> result = new HashMap<>();

            result.put("userId", userId);
            result.put("country", this.counrty);
            result.put("checkIn", this.checkIn);
            result.put("checkOut", this.checkOut);
            result.put("price", this.price);
            result.put("hotelName", this.hotelName);
              result.put("localOrAbroad", this.local);

            return result;
        }
        */
    }

