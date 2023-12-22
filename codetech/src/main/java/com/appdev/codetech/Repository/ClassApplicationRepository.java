package com.appdev.codetech.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdev.codetech.Entity.ClassApplicationEntity;
import com.appdev.codetech.Entity.UserEntity;

@Repository
public interface ClassApplicationRepository extends JpaRepository<ClassApplicationEntity, String> {
    // Add any custom query methods if needed

    List<ClassApplicationEntity> findByUser(UserEntity user);
}
