package com.appdev.codetech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdev.codetech.Entity.QuizQuestionEntity;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestionEntity, Integer> {
    // Add any custom query methods if needed
}
