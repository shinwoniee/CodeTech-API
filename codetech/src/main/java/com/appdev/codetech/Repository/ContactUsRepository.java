package com.appdev.codetech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdev.codetech.Entity.ContactUsEntity;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUsEntity, Integer> {

}
