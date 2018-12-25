package ify.com.hotelsapp;


import java.util.Date;

public class Vacation {
    final static int LOCAL=0;
    final static int ABROAD=1;
    String Counrty;
    int CheckIn;
    int CheckOut;
    String HotelName;
    int Price;
    int local;

    public Vacation(String counrty, int checkIn, int checkOut, String hotelName, int price,int a) {
        Counrty = counrty;
        CheckIn = checkIn;
        CheckOut = checkOut;
        HotelName = hotelName;
        Price = price;
        local =a;
    }







}
