/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginregistrationapp;

// This will allow the program to display pop-up windows
import javax.swing.JOptionPane;

public class LoginRegistrationApp {

    public static void main(String[] args) {

        // This will create a new Login object
        Login login = new Login();

        // This will ask the user for their first name
        String firstName = JOptionPane.showInputDialog("Enter your first name:");

        // This will ask the user for their last name
        String lastName = JOptionPane.showInputDialog("Enter your last name:");

        // This will ask the user for a username
        String username = JOptionPane.showInputDialog("Enter your username:");

        // This will ask the user for a password
        String password = JOptionPane.showInputDialog("Enter your password:");

        // This will ask the user for their cellphone number
        String cellphoneNumber = JOptionPane.showInputDialog(
                "Enter your cellphone number starting with +27:");

        // This will register the user
        String registrationMessage = login.registerUser(
                firstName, lastName, username, password, cellphoneNumber);

        // This will display the registration result
        JOptionPane.showMessageDialog(null, registrationMessage);

        // This will continue only if registration was successful
        if (registrationMessage.toLowerCase().contains("successfully")) {

            // This will ask the user to log in
            String loginUsername = JOptionPane.showInputDialog("Enter your username to log in:");
            String loginPassword = JOptionPane.showInputDialog("Enter your password to log in:");

            // This will check the login details
            boolean loginSuccessful = login.loginUser(loginUsername, loginPassword);

            // This will display the login result
            JOptionPane.showMessageDialog(null, login.returnLoginStatus());

            // This will open the message menu if login was successful
            if (loginSuccessful) {
                messageMenu();
            }
        }
    }

    // This will display the message menu
    public static void messageMenu() {

        // This will create a variable to count and display messages
        Message message = null;

        // This will keep the menu running
        boolean running = true;

        while (running) {

            // This will display the available options
            String option = JOptionPane.showInputDialog(
                    "MESSAGE MENU\n"
                    + "1. Create a message\n"
                    + "2. View sent messages\n"
                    + "3. View total messages\n"
                    + "4. Exit\n\n"
                    + "Enter your choice:");

            // This will stop the menu if the user presses Cancel
            if (option == null) {
                break;
            }

            // This will check which option the user selected
            switch (option) {

                case "1":

                    // This will ask for the recipient cellphone number
                    String recipient = JOptionPane.showInputDialog(
                            "Enter the recipient cellphone number:");

                    // This will ask for the message
                    String messageText = JOptionPane.showInputDialog(
                            "Enter your message (maximum 250 characters):");

                    // This will return to the menu if the user cancels
                    if (recipient == null || messageText == null) {
                        continue;
                    }

                    // This will create a message with an automatically generated ID
                    message = new Message(recipient, messageText);

                    // This will check if the recipient number is valid
                    String recipientResult = message.checkRecipientCell();

                    // This will check if the message is too long
                    String lengthResult = message.checkMessageLength();

                    // This will display an error if the recipient number is invalid
                    if (!recipientResult.equals("Cell phone number successfully captured.")) {
                        JOptionPane.showMessageDialog(null, recipientResult);
                        continue;
                    }

                    // This will display an error if the message is too long
                    if (!lengthResult.equals("Message ready to send.")) {
                        JOptionPane.showMessageDialog(null, lengthResult);
                        continue;
                    }

                    // This will display the message hash
                    JOptionPane.showMessageDialog(null,
                            "Message ID: " + message.getMessageID()
                            + "\nMessage Hash: " + message.getMessageHash());

                    // This will ask the user what to do with the message
                    String messageChoice = JOptionPane.showInputDialog(
                            "Choose an option:\n"
                            + "1. Send Message\n"
                            + "2. Disregard Message\n"
                            + "3. Store Message to send later");

                    // This will return to the menu if the user cancels
                    if (messageChoice == null) {
                        continue;
                    }

                    // This will send the message
                    if (messageChoice.equals("1")) {

                        JOptionPane.showMessageDialog(null, message.SentMessage(1));

                        // This will display the message details
                        JOptionPane.showMessageDialog(null, message.getMessageDetails());

                    // This will disregard the message
                    } else if (messageChoice.equals("2")) {

                        JOptionPane.showMessageDialog(null, message.SentMessage(2));

                    // This will store the message
                    } else if (messageChoice.equals("3")) {

                        JOptionPane.showMessageDialog(null, message.SentMessage(3));

                    // This will display an error for an invalid option
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid choice.");
                    }

                    break;

                case "2":

                    // This will display all sent messages
                    if (message != null) {
                        String sentMessages = message.printMessages();

                        if (sentMessages.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No messages have been sent.");
                        } else {
                            JOptionPane.showMessageDialog(null, sentMessages);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No messages have been created.");
                    }

                    break;

                case "3":

                    // This will display the total number of sent messages
                    if (message != null) {
                        JOptionPane.showMessageDialog(null,
                                "Total messages sent: " + message.returnTotalMessagess());
                    } else {
                        JOptionPane.showMessageDialog(null, "Total messages sent: 0");
                    }

                    break;

                case "4":

                    // This will close the menu
                    running = false;
                    break;

                default:

                    // This will display an error for an invalid menu choice
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please choose 1, 2, 3 or 4.");
                    break;
            }
        }
    }
}