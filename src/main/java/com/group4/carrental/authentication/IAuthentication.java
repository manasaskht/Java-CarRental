package com.group4.carrental.authentication;

import javax.servlet.http.HttpSession;

public interface IAuthentication {

    public boolean isValidUserSession(HttpSession session);
    public boolean isValidAdminSession(HttpSession session);
    public int getUserId();
    public int getAdminId();
}
