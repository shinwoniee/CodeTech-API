package com.appdev.codetech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdev.codetech.Entity.ClassApplicationEntity;

@Repository
public interface ClassApplicationRepository extends JpaRepository<ClassApplicationEntity, String> {
    // Add any custom query methods if needed
}
