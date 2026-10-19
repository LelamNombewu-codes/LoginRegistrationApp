/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lelam
 */
package com.mycompany.loginregistrationapp;

// This will import the Test annotation from JUnit
import org.junit.jupiter.api.Test;

// This will import the methods used to check if tests pass
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // This will test if the message ID is generated correctly
    @Test
    public void testCheckMessageID() {

        // This will create a new message with a cellphone number and message text
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        // This will check if the generated message ID is valid
        assertTrue(message.checkMessageID());
    }

    // This will test if a correctly formatted cellphone number is accepted
    @Test
    public void testCheckRecipientCell() {

        // This will create a message with a valid international cellphone number
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        // This will check if the correct success message is returned
        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    // This will test if an incorrectly formatted cellphone number is rejected
    @Test
    public void testIncorrectRecipientCell() {

        // This will create a message with a local-format cellphone number
        Message message = new Message(
                "08575975889",
                "Hi Keegan, did you receive the payment?"
        );

        // This will check if the correct error message is returned
        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    // This will test if a message with fewer than 250 characters is accepted
    @Test
    public void testMessageLength() {

        // This will create a message that is within the character limit
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        // This will check if the message is ready to send
        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    // This will test if a message longer than 250 characters is rejected
    @Test
    public void testLongMessage() {

        // This will create a message containing 251 characters
        String longText = "A".repeat(251);

        // This will create a message using the long text
        Message message = new Message(
                "+27718693002",
                longText
        );

        // This will check if the error reports one extra character
        assertEquals(
                "Message exceeds 250 characters by 1; please reduce the size.",
                message.checkMessageLength()
        );
    }

    // This will test if the message hash contains the first and last words
    @Test
    public void testMessageHash() {

        // This will create a message for testing the hash
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        // This will check if the hash ends with the first and last words
        assertTrue(message.createMessageHash().endsWith(":HITONIGHT"));
    }
}