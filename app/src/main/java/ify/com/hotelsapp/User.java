package ify.com.hotelsapp;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String email;
    private String name;

    List<Vacation> vacationsList;

    public User(){};

    public User(String name,String email){

        this.name=name;
        this.email=email;


        }

    /*public User(){
       id;
        vacationsList=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Vacation> getVacationsList() {
        return vacationsList;
    }

    public void setVacationsList(List<Vacation> vacationsList) {
        this.vacationsList = vacationsList;
    }*/
}
