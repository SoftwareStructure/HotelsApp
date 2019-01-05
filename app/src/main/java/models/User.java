package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

    private String email;
    private String name;

   // List<Vacation> vacationsList;

    public User(){};

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, String email){

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
