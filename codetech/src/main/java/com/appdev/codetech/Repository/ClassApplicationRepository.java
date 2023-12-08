package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassApplicationRepository extends JpaRepository<ClassApplicationEntity, String> {
    // Add any custom query methods if needed
}
