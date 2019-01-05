package dataHolder;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.Vacation;

public class UserDataHolder {

    private User currentUser = null;
    private String userId=null;
    private List<Vacation> vacationList=null;

    private UserDataHolder(){

        currentUser=new User();
        vacationList=new ArrayList<>();
    }

    public static final UserDataHolder dataHolder = new UserDataHolder();


    public static UserDataHolder getUserDataHolder(){return dataHolder;}


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Vacation> getVacationList() {
        return vacationList;
    }

    public void setVacationList(List<Vacation> vacationList) {
        this.vacationList = vacationList;
    }
    public void addNewVacation(Vacation v){
        this.vacationList.add(v);
    }
}
