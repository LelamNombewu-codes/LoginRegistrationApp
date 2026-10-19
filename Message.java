/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginregistrationapp;

// This will allow the program to store messages in a list
import java.util.ArrayList;

// This will allow the program to manage a list of messages
import java.util.List;

// This will allow the program to write messages to a file
import java.io.FileWriter;

// This will allow the program to handle file errors
import java.io.IOException;

public class Message {

    // This will store the message ID
    private String messageID;

    // This will store the recipient cellphone number
    private String recipient;

    // This will store the message text
    private String message;

    // This will store the message hash
    private String messageHash;

    // This will count the messages that are created
    private static int messageCounter = 0;

    // This will count the messages that are sent
    private static int totalMessagesSent = 0;

    // This will store all successfully sent messages
    private static List<Message> messages = new ArrayList<>();

    // This will store the message number used in the hash
    private int messageNumber;

    // This will create a new message with an automatically generated ID
    public Message(String recipient, String message) {

        // This will increase the message counter
        messageCounter++;

        // This will generate a message ID containing 10 digits
        this.messageID = String.format("%010d", messageCounter);

        // This will set the first message number to 0
        this.messageNumber = messageCounter - 1;

        // This will store the recipient number
        this.recipient = recipient;

        // This will store the message text
        this.message = message;

        // This will create the message hash
        this.messageHash = createMessageHash();
    }

    // This will check if the message ID is no longer than 10 characters
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // This will check if the recipient cellphone number is valid
    public String checkRecipientCell() {

        // This will check if the number starts with +27 and has 9 digits after it
        if (recipient != null && recipient.matches("\\+27[0-9]{9}")) {
            return "Cell phone number successfully captured.";
        }

        // This will return an error if the number is invalid
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // This will create the message hash
    public String createMessageHash() {

        // This will get the first two characters of the message ID
        String firstTwoDigits = messageID.substring(0, 2);

        // This will remove spaces at the beginning and end of the message
        String cleanMessage = message == null ? "" : message.trim();

        // This will split the message into words
        String[] words = cleanMessage.isEmpty()
                ? new String[0] : cleanMessage.split("\\s+");

        // This will store the first and last words
        String firstWord = "";
        String lastWord = "";

        // This will get the first and last word
        if (words.length > 0) {
            firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
            lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
        }

        // This will combine the message details into a hash
        return (firstTwoDigits + ":" + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    // This will process the user's choice for the message
    public String SentMessage(int choice) {

        // This will send the message and add it to the sent messages list
        if (choice == 1) {
            messages.add(this);
            totalMessagesSent++;
            return "Message successfully sent";
        }

        // This will tell the user that the message was disregarded
        if (choice == 2) {
            return "Press 0 to delete the message";
        }

        // This will store the message in a JSON file
        if (choice == 3) {
            if (storeMessage()) {
                return "Message successfully stored";
            }

            return "Message could not be stored.";
        }

        // This will display an error if the choice is invalid
        return "Invalid message option.";
    }

    // This will display all messages that have been sent
    public String printMessages() {

        // This will create a place to collect the message details
        StringBuilder details = new StringBuilder();

        // This will go through all sent messages
        for (Message sentMessage : messages) {
            details.append(sentMessage.getMessageDetails()).append("\n\n");
        }

        // This will return the message details
        return details.toString().trim();
    }

    // This will return the total number of messages sent
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    // This will check if the message is within the 250 character limit
    public String checkMessageLength() {

        // This will check if the message has 250 characters or fewer
        if (message != null && message.length() <= 250) {
            return "Message ready to send.";
        }

        // This will calculate how many characters are over the limit
        int extraCharacters = message == null ? 0 : message.length() - 250;

        // This will return the message length error
        return "Message exceeds 250 characters by " + extraCharacters
                + "; please reduce the size.";
    }

    // This will return the message details in the required order
    public String getMessageDetails() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // This will store the message details in a JSON file
    public boolean storeMessage() {

        // This will prepare the message details in JSON format
        String json = "{\n"
                + "  \"messageID\": \"" + escapeJson(messageID) + "\",\n"
                + "  \"messageHash\": \"" + escapeJson(messageHash) + "\",\n"
                + "  \"recipient\": \"" + escapeJson(recipient) + "\",\n"
                + "  \"message\": \"" + escapeJson(message) + "\"\n"
                + "}\n";

        // This will write the message to messages.json
        try (FileWriter writer = new FileWriter("messages.json", true)) {

            // This will add the message to the file
            writer.write(json);

            // This will confirm that the message was stored
            return true;

        } catch (IOException e) {

            // This will report that the message could not be stored
            return false;
        }
    }

    // This will protect special characters when saving JSON
    private String escapeJson(String value) {

        // This will return an empty string if the value is missing
        if (value == null) {
            return "";
        }

        // This will escape special characters in the text
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // This will return the message ID
    public String getMessageID() {
        return messageID;
    }

    // This will return the message hash
    public String getMessageHash() {
        return messageHash;
    }
}