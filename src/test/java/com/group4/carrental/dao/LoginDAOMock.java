package com.group4.carrental.dao;

import com.group4.carrental.model.User;

import java.util.ArrayList;

public class LoginDAOMock implements ILoginDAO {
    private ArrayList<User> userArrayList;

    public LoginDAOMock() {
        this.userArrayList = new ArrayList<User>();
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        user1.setPassword("Dec@0307");
        user1.setUserId(1);

        User user2 = new User();
        user2.setEmail("mana@gmail.com");
        user2.setPassword("nov@0307");
        user2.setUserId(1);

        userArrayList.add(user1);
        userArrayList.add(user2);
    }

    @Override
    public String getPassword(String email) {


        for (User user : userArrayList) {
            if (user.getEmail().equals(email)) {
                return user.getPassword();

            }
        }

        return null;
    }

    @Override
    public int getUserId(User user) {
        for (User userdb : userArrayList) {
            if (userdb.getEmail().equals(user.getEmail())) {
                return getUserId(user);
            }
        }return 0;
    }


}



