package dataHolder;

import java.util.List;

import ify.com.hotelsapp.User;
import ify.com.hotelsapp.Vacation;

public class UserDataHolder {

    private User currentUser = null;

    private static UserDataHolder dataHolder = new UserDataHolder();

    private UserDataHolder(){
    currentUser=new User();
}

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String id){
       // this.currentUser.setId(id);
    }

    public static UserDataHolder getDataHolder() {
        return dataHolder;
    }

}
