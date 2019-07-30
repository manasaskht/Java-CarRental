package com.group4.carrental.service;

import com.group4.carrental.model.Email;

public interface IEmailsendingSubjcet {

    public void attach(IObserver observer);
    public void detach(IObserver observer);
    public void notifyUpadte(Email email);
}
