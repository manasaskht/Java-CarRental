package com.group4.carrental.service.implementation;

import com.group4.carrental.model.Email;
import com.group4.carrental.service.IEmailsendingSubjcet;
import com.group4.carrental.service.IObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service("EmailSenderSubject")
public class EmailSenderSubject implements IEmailsendingSubjcet {

    private final List<IObserver> observers;

    public EmailSenderSubject()
    {
        observers = new ArrayList<IObserver>();
    }

    @Override
    public void attach(IObserver observer) {

        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);

    }

    @Override
    public void notifyUpadte(Email email) {

        ListIterator<IObserver> iter = observers.listIterator();
        while (iter.hasNext())
        {
            iter.next().sendEmail(email);
        }
    }
}
