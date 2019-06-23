package com.group4.carrental.dao;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;

import java.util.ArrayList;

public class UserSignUpDAOMock implements IUserSignUpDAO{


    private ArrayList<City> CityList;
    private ArrayList<User> userArrayList;

    public UserSignUpDAOMock()
    {
        this.CityList= new ArrayList<City>();
        this.userArrayList=new ArrayList<User>();
        City city1 = new City();
        city1.setCityId(1);
        city1.setCityName("halifax");

        City city2 = new City();
        city2.setCityId(2);
        city2.setCityName("dartmouth");

        City city3 = new City();
        city3.setCityId(3);
        city3.setCityName("brunswick");

        CityList.add(city1);
        CityList.add(city2);
        CityList.add(city3);

        User user1 =new User();
        user1.setUserId(1);
        user1.setPassword("Dec@0307");
        user1.setName("abc");
        user1.setEmail("abc@dal.ca");

        User user2 =new User();
        user2.setUserId(2);
        user2.setPassword("Jan@0307");
        user2.setName("abc");
        user2.setEmail("xyz@0dal.ca");

        userArrayList.add(user1);
        userArrayList.add(user2);

    }

    @Override
    public void saveUserSignUpDetails(User user) {
        this.userArrayList.add(user);

    }

    @Override
    public User getUserDetails(Integer userId) {

        return this.userArrayList.get(userId);
    }

    @Override
    public void updateUserProfileDetails(User user)
    {
        int i = user.getUserId();
        this.userArrayList.remove(userArrayList.get(i));
        this.userArrayList.add(user);

    }

    @Override
    public boolean isEmailExist(String email) {

        boolean isExist=false;

        for(int i=0;i<this.userArrayList.size();i++)
        {
            if(email.equals(userArrayList.get(i).getEmail()))
            {
                isExist=true;
            }
        }
        return isExist;
    }

    @Override
    public ArrayList<City> getCityList()
    {
        return this.CityList;
    }
}
