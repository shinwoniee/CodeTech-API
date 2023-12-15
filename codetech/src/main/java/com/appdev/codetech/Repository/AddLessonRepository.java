package com.appdev.codetech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appdev.codetech.Entity.AddLessonEntity;

public interface AddLessonRepository extends JpaRepository<AddLessonEntity, Integer> {
}