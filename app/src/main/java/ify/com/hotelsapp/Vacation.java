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


    public static int getLOCAL() {
        return LOCAL;
    }

    public static int getABROAD() {
        return ABROAD;
    }

    public String getCounrty() {
        return Counrty;
    }

    public String getCheckIn() {
        return CheckIn;
    }

    public String getCheckOut() {
        return CheckOut;
    }

    public String getHotelName() {
        return HotelName;
    }

    public int getPrice() {
        return Price;
    }

    public int getLocal() {
        return local;
    }

    public void setCounrty(String counrty) {
        Counrty = counrty;
    }

    public void setCheckIn(String checkIn) {
        CheckIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        CheckOut = checkOut;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setLocal(int local) {
        this.local = local;
    }
}
