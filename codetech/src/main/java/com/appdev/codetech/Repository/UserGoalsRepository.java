package com.appdev.codetech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdev.codetech.Entity.UserGoalsEntity;

@Repository
public interface UserGoalsRepository extends JpaRepository<UserGoalsEntity, Integer>{
	
}

