package com.appdev.codetech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdev.codetech.Entity.ContactUsEntity;
import com.appdev.codetech.Service.ContactUsService;

@RestController
@CrossOrigin
@RequestMapping("/contactus")
public class ContactUsController {
    @Autowired
    ContactUsService cserv;

    @PostMapping(("/insertContactUs"))
    public ContactUsEntity insertContactUs(@RequestBody ContactUsEntity contactus) {
        return cserv.insertContactUs(contactus);
    }

    @GetMapping("/getAllContactUs")
    public List<ContactUsEntity> getAllContactUs() {
        return cserv.getAllContactUs();
    }

    @PutMapping("/updateContactUs")
    public ContactUsEntity updateContactUs(@RequestParam int contactusid,
            @RequestBody ContactUsEntity newContactUsEntity) {
        return cserv.updateContactUs(contactusid, newContactUsEntity);
    }

    @PutMapping("/removeContactUs")
    public ContactUsEntity removeContactUs(@RequestParam int contactusid) {
        return cserv.removeContactUs(contactusid);
    }

    @DeleteMapping("/deleteContactUs/{contactusid}")
    public String deleteContactUs(@PathVariable int contactusid) {
        return cserv.deleteContactUs(contactusid);
    }
}
