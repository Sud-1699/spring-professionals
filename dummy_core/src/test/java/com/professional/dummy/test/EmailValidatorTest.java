package com.professional.dummy.test;

import com.professional.dummy.utils.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    private final EmailValidator emailValidator = new EmailValidator();

    @Test
    public void isEmailValid() {
        assertThat(emailValidator.test("hello123@gmail.com")).isTrue();
    }

    @Test
    public void isEmailValidWithoutAnnotation() {
        assertThat(emailValidator.test("hello123gmail.com")).isFalse();
    }

    @Test
    public void isEmailValidWithoutDotCom() {
        assertThat(emailValidator.test("hello123@gmail")).isFalse();
    }

}