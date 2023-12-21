package com.appdev.codetech.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdev.codetech.Service.ContactUsService;
import com.appdev.codetech.Service.TicketService;
import com.appdev.codetech.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/count")
public class CountController {

    private final UserService userService;
    private final ContactUsService contactUsService;
    private final TicketService ticketService;

    public CountController(UserService userService, ContactUsService contactUsService, TicketService ticketService) {
        this.userService = userService;
        this.contactUsService = contactUsService;
        this.ticketService = ticketService;
    }

    @GetMapping("/getTotalEducators")
    public long getTotalEducators() {
        return userService.getCountByRole("educator");
    }

    @GetMapping("/getTotalLearners")
    public long getTotalLearners() {
        return userService.getCountByRole("learner");
    }

    @GetMapping("/getTotalMessages")
    public long getTotalMessages() {
        return contactUsService.getTotalMessages();
    }

    @GetMapping("/getTotalTickets")
    public long getTotalTickets() {
        return ticketService.getTotalTickets();
    }
}
