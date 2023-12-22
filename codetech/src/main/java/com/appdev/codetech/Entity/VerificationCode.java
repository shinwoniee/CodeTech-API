package com.appdev.codetech.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VerificationCode {

    @Id
    private String email;

    private String code;

    public VerificationCode() {
        // Default constructor required by JPA
    }

    public VerificationCode(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}