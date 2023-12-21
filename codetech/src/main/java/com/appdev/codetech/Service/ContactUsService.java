package com.appdev.codetech.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.codetech.Entity.ContactUsEntity;
import com.appdev.codetech.Repository.ContactUsRepository;

@Service
public class ContactUsService {
    @Autowired
    ContactUsRepository crepo;

    public ContactUsEntity insertContactUs(ContactUsEntity contactUs) {
        return crepo.save(contactUs);
    }

    public List<ContactUsEntity> getAllContactUs() {
        return crepo.findAll();
    }

    public ContactUsEntity updateContactUs(int contactusid, ContactUsEntity newContactUsEntityDetails) {
        ContactUsEntity user = new ContactUsEntity();
        try {
            user = crepo.findById(contactusid).get();
            user.setName(newContactUsEntityDetails.getName());
            user.setMessage(newContactUsEntityDetails.getMessage());
            user.setEmail(newContactUsEntityDetails.getEmail());
            user.setIsDelete(newContactUsEntityDetails.getIsDelete());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("ContactUs: " + contactusid + " does not exist!");
        }

        return crepo.save(user);

    }

    public ContactUsEntity removeContactUs(int contactusid) {
        ContactUsEntity user = new ContactUsEntity();
        try {
            user = crepo.findById(contactusid).get();
            user.setName(null);
            user.setMessage(null);
            user.setEmail(null);
            user.setIsDelete(true);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("ContactUs: " + contactusid + " does not exist!");
        }

        return crepo.save(user);

    }

    public String deleteContactUs(int contactusid) {
        String msg = "";

        if (crepo.findById(contactusid) != null) {
            crepo.deleteById(contactusid);
            msg = "ContactUs " + contactusid + " is successfully deleted!";
        } else
            msg = "ContactUs " + contactusid + " does not exist";

        return msg;
    }

    public long getTotalMessages() {
        return crepo.count();
    }
}
