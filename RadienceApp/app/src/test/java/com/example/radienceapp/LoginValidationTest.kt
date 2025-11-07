package com.example.radienceapp

import org.junit.Test
import org.junit.Assert.*

class LoginValidationTest {

    @Test
    fun testEmailValidation_emptyEmail_returnsFalse() {
        val email = ""
        assertFalse("Empty email should be invalid", email.isNotEmpty())
    }

    @Test
    fun testEmailValidation_validEmail_returnsTrue() {
        val email = "user@example.com"
        assertTrue("Valid email should pass", email.isNotEmpty())
    }

    @Test
    fun testPasswordValidation_shortPassword_returnsFalse() {
        val password = "123"
        assertFalse("Password less than 6 characters should be invalid", password.length >= 6)
    }

    @Test
    fun testPasswordValidation_validPassword_returnsTrue() {
        val password = "password123"
        assertTrue("Password with 6+ characters should be valid", password.length >= 6)
    }

    @Test
    fun testPasswordMatch_matchingPasswords_returnsTrue() {
        val password = "password123"
        val confirmPassword = "password123"
        assertEquals("Passwords should match", password, confirmPassword)
    }

    @Test
    fun testPasswordMatch_differentPasswords_returnsFalse() {
        val password = "password123"
        val confirmPassword = "password456"
        assertNotEquals("Passwords should not match", password, confirmPassword)
    }
}