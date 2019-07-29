package com.group4.carrental.authentication;



import javax.servlet.http.HttpSession;


public class Authentication implements IAuthentication {

    private static  Authentication authentication = null;
    private HttpSession session;
    private int userId = 0;
    private int adminId = 0;

    private Authentication(){

    }

    public static Authentication getInstance(){
        if(authentication == null){
            authentication = new Authentication();
        }
        return authentication;
    }
    @Override
    public boolean isValidUserSession(HttpSession session) {
        userId = 0;
        boolean isValid = true;

        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            isValid = false;
        }
        return isValid;
    }

    @Override
    public boolean isValidAdminSession() {

         adminId = 0;
         boolean isValid =true;
        try{
            adminId = (int) session.getAttribute("admin");
        }catch(NullPointerException exception){
            isValid = false;
        }
        return isValid;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public int getAdminId() {
        return adminId;
    }




}
