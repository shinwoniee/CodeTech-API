package com.appdev.codetech.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdev.codetech.Entity.TicketEntity;
import com.appdev.codetech.Entity.UserEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    List<TicketEntity> findByUser(UserEntity user);
}
