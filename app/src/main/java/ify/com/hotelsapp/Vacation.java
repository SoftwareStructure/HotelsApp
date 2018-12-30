package ify.com.hotelsapp;


import java.util.Date;

public class Vacation {

    final static int LOCAL=0;
    final static int ABROAD=1;

    String Counrty;
    String CheckIn;
    String CheckOut;
    String HotelName;
    int Price;
    int local;

    public Vacation(String counrty, String checkIn, String checkOut, String hotelName, int price,int a) {
        Counrty = counrty;
        CheckIn = checkIn;
        CheckOut =checkOut;
        HotelName = hotelName;
        Price = price;
        local =a;
    }







}
