package ify.com.hotelsapp;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;

    List<Vacation> vacationsList;

    public User(){
        id="";
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
    }
}
