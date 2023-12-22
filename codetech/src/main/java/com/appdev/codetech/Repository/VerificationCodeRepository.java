package com.appdev.codetech.Repository;

import org.springframework.data.repository.CrudRepository;

import com.appdev.codetech.Entity.VerificationCode;

public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String> {

    // Custom query methods, if needed
}
