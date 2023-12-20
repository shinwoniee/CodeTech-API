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

import com.appdev.codetech.Entity.TicketEntity;
import com.appdev.codetech.Service.TicketService;

@RestController
@CrossOrigin
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService tserv;

    @PostMapping(("/insertTicket"))
    public TicketEntity insertTicket(@RequestBody TicketEntity ticket) {
        return tserv.insertTicket(ticket);
    }

    @GetMapping("/getAllTickets")
    public List<TicketEntity> getAllTickets() {
        return tserv.getAllTickets();
    }

    @PutMapping("/updateTicket")
    public TicketEntity updateTicket(@RequestParam int ticketid, @RequestBody TicketEntity newTicketEntity) {
        return tserv.updateTicket(ticketid, newTicketEntity);
    }

    @PutMapping("/removeTicket")
    public TicketEntity removeTicket(@RequestParam int ticketid) {
        return tserv.removeTicket(ticketid);
    }

    @DeleteMapping("/deleteTicket/{ticketid}")
    public String deleteTicket(@PathVariable int ticketid) {
        return tserv.deleteTicket(ticketid);
    }
}
