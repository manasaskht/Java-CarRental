package com.group4.carrental.dao;
import com.group4.carrental.model.User;

import java.util.ArrayList;

public class ForgotPasswordDAOMock implements IForgotPasswordDAO {
    private ArrayList<User> userArrayList;

    public ForgotPasswordDAOMock() {
        this.userArrayList = new ArrayList<User>();
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        user1.setTokenID("abcd123456");
        user1.setPassword("Dec@0307");
        user1.setUserId(1);

        User user2 = new User();
        user2.setEmail("mana@gmail.com");
        user2.setPassword("nov@0307");
        user2.setTokenID("d734567qki");
        user2.setUserId(2);

        userArrayList.add(user1);
        userArrayList.add(user2);
    }

    @Override
    public String findUserByEmail(String email) {
        for (User user : userArrayList) {
            if (user.getEmail().equals(email)) {
                return user.getEmail();

            }
        }
        return null;
    }

    @Override
    public void addToken(String email, String Token) {
        for (User user : userArrayList) {
            if (user.getEmail().equals(email)) {
                user.setTokenID(Token);
            }
        }
    }

    @Override
    public String findUserByResetToken(String Email) {
        for (User user : userArrayList) {
            if (user.getEmail().equals(Email)) {
                return user.getTokenID();

            }
        }
        return null;
    }

    @Override
    public String validate(String Token) {
    	
        for (User user : userArrayList) {
            if (user.getTokenID().equals(Token)) {
                return user.getTokenID();
            }

        }
        return null;
    }
        @Override
        public void updatePassword (String email, String password){
            for (User user : userArrayList) {
                if (user.getEmail().equals(email)) {
                    user.setPassword(password);
                }
            }
        }
    }

