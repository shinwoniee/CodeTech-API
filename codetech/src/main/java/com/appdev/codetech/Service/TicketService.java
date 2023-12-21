package com.appdev.codetech.Service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdev.codetech.Entity.TicketEntity;
import com.appdev.codetech.Entity.UserEntity;
import com.appdev.codetech.Repository.TicketRepository;
import com.appdev.codetech.Repository.UserRepository;

@Service
public class TicketService {
    @Autowired
    TicketRepository trepo;

    @Autowired
    private UserRepository urepo;

    public TicketEntity insertTicket(TicketEntity ticket) {
        int userId = ticket.getUser().getUserid();

        System.err.println("userid: " + userId);

        UserEntity user = urepo.findById(userId)
                .orElseThrow(() -> {
                    return new EntityNotFoundException("User not found");
                });

        ticket.setUser(user);

        return trepo.save(ticket);
    }

    public List<TicketEntity> getAllTickets() {
        return trepo.findAll();
    }

    public List<TicketEntity> getTicketsByUserId(int userid) {
        UserEntity user = urepo.findById(userid)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return trepo.findByUser(user);
    }

    public TicketEntity updateTicket(int ticketid, TicketEntity newTicketEntityDetails) {
        TicketEntity ticket = new TicketEntity();
        try {
            ticket = trepo.findById(ticketid).get();
            ticket.setTitle(newTicketEntityDetails.getTitle());
            ticket.setCategory(newTicketEntityDetails.getCategory());
            ticket.setEmail(newTicketEntityDetails.getEmail());
            ticket.setDetails(newTicketEntityDetails.getDetails());
            ticket.setStatus(newTicketEntityDetails.getStatus());
            ticket.setTimestamp(newTicketEntityDetails.getTimestamp());
            ticket.setIsDelete(newTicketEntityDetails.getIsDelete());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Ticket: " + ticketid + " does not exist!");
        }

        return trepo.save(ticket);

    }

    public TicketEntity removeTicket(int ticketid) {
        TicketEntity ticket = new TicketEntity();
        try {
            ticket = trepo.findById(ticketid).get();
            ticket.setTitle(null);
            ticket.setCategory(null);
            ticket.setEmail(null);
            ticket.setDetails(null);
            ticket.setStatus(null);
            ticket.setTimestamp(null);
            ticket.setUser(null);
            ticket.setIsDelete(true);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Ticket: " + ticketid + " does not exist!");
        }

        return trepo.save(ticket);

    }

    public String deleteTicket(int ticketid) {
        String msg = "";

        if (trepo.findById(ticketid) != null) {
            trepo.deleteById(ticketid);
            msg = "Ticket " + ticketid + " is successfully deleted!";
        } else
            msg = "Ticket " + ticketid + " does not exist";

        return msg;
    }

    public long getTotalTickets() {
        return trepo.count();
    }
}
