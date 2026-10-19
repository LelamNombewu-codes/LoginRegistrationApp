/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lelam
 */

package com.mycompany.loginregistrationapp;

// This will create the Login class
public class Login {

    // This will store the user's username
    private String username;

    // This will store the user's password
    private String password;

    // This will store the user's cellphone number
    private String cellphoneNumber;

    // This will store the user's first name
    private String firstName;

    // This will store the user's last name
    private String lastName;

    // This will check if the user is logged in
    private boolean loggedIn = false;

    // This will check if the username contains an underscore and is no longer than 5 characters
    public boolean checkUserName(String username) {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    // This will check if the password has the required complexity
    public boolean checkPasswordComplexity(String password) {

        // This will check if the password has at least 8 characters
        if (password == null || password.length() < 8) {
            return false;
        }

        // This will check if the password contains an uppercase letter
        boolean hasUppercase = false;

        // This will check if the password contains a number
        boolean hasNumber = false;

        // This will check if the password contains a special character
        boolean hasSpecialCharacter = false;

        // This will check each character in the password
        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasUppercase = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecialCharacter = true;
            }
        }

        // This will return true if all password requirements are met
        return hasUppercase && hasNumber && hasSpecialCharacter;
    }

    // This will check if the cellphone number starts with +27 and has 9 digits after it
    public boolean checkCellPhoneNumber(String cellphoneNumber) {
        return cellphoneNumber != null
                && cellphoneNumber.matches("\\+27[0-9]{9}");
    }

    // This will register the user if all details are valid
    public String registerUser(String firstName, String lastName,
            String username, String password, String cellphoneNumber) {

        // This will check if the username is valid
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        // This will check if the password is valid
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        // This will check if the cellphone number is valid
        if (!checkCellPhoneNumber(cellphoneNumber)) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        // This will save the user's details
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;

        // This will confirm that the user has been registered
        return "User registered successfully.";
    }

    // This will check if the login details are correct
    public boolean loginUser(String username, String password) {

        // This will check the entered username and password
        if (this.username != null
                && this.password != null
                && this.username.equals(username)
                && this.password.equals(password)) {

            // This will mark the user as logged in
            loggedIn = true;
            return true;
        }

        // This will mark the user as not logged in
        loggedIn = false;
        return false;
    }

    // This will return the login status message
    public String returnLoginStatus() {

        // This will display a welcome message if the user logged in
        if (loggedIn) {
            return "Welcome " + firstName + " " + lastName
                    + ", it is great to see you again.";
        }

        // This will display an error message if the login failed
        return "Username or password incorrect, please try again.";
    }
}