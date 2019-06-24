package com.group4.carrental.dao;

import com.group4.carrental.model.Password;
import com.group4.carrental.model.User;

import java.util.ArrayList;

public class UpdatePasswordDAOMock implements IUpdatePasswordDAO{

    ArrayList<Password> passwordArrayList;
    ArrayList<User> userArrayList;
    public UpdatePasswordDAOMock(){

        userArrayList = new ArrayList<User>();

        User user1 = new User();
        user1.setName("User 1");
        user1.setPassword("abc@123");
        user1.setUserId(1);

        User user2 = new User();
        user2.setUserId(2);
        user2.setName("user 2");
        user2.setPassword("123@abc");

        userArrayList.add(user1);
        userArrayList.add(user2);


    }

    @Override
    public String getUserOldPassword(int userId) {

        for(User user : userArrayList){
            if(user.getUserId() == userId){
                return user.getPassword();
            }
        }
        return null;
    }

    @Override
    public void updatePassword(int userId, Password password) {

        for(User user : userArrayList){
            if(user.getUserId() == userId){
                user.setPassword(password.getNewPassword());
            }
        }

    }
}
