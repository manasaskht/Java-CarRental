package com.group4.carrental.service;

import com.group4.carrental.model.Email;

public interface IObserver {

    public void sendEmail(Email email);
}
