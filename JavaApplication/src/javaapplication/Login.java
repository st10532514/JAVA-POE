/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

import java.util.regex.Pattern;

/**
 *
 * @author Student
 */
public class Login {
    private String strRegisteredUsername;
    private String strRegisteredPassword;
    private String strRegisteredCellPhoneNumber;
    private String strRegisteredFirstName;
    private String strRegisteredLastName;
    
    public boolean checkUserName(String username) {
        boolean result = false;

        if (username != null && username.contains("_") && username.length() <= 5) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
    
    public boolean checkPasswordComplexity(String password) {
        boolean hasMinLength = false;
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        boolean result = false;

        if (password != null && password.length() >= 8) {
            hasMinLength = true;
        }

        if (password != null) {
            int index = 0;
            while (index < password.length()) {
                char chrCurrent = password.charAt(index);

                if (Character.isUpperCase(chrCurrent)) {
                    hasCapital = true;
                } else if (Character.isDigit(chrCurrent)) {
                    hasNumber = true;
                } else if (!Character.isLetterOrDigit(chrCurrent)) {
                    hasSpecialChar = true;
                }

                index++;
            }
        }

        if (hasMinLength && hasCapital && hasNumber && hasSpecialChar) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
    
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        boolean result = false;
        String pattern = "^\\+27[0-9]{9}$";

        if (cellPhoneNumber != null && Pattern.matches(pattern, cellPhoneNumber)) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
    
    public String registerUser(String username, String password, String cellPhoneNumber,
                                String firstName, String lastName) {
        String strMessage;
        boolean usernameValid = checkUserName(username);
        boolean passwordValid = checkPasswordComplexity(password);
        boolean cellValid = checkCellPhoneNumber(cellPhoneNumber);

        if (!usernameValid) {
            strMessage = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (!passwordValid) {
            strMessage = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else if (!cellValid) {
            strMessage = "Cell phone number incorrectly formatted or does not contain international code.";
        } else {
            strRegisteredUsername = username;
            strRegisteredPassword = password;
            strRegisteredCellPhoneNumber = cellPhoneNumber;
            strRegisteredFirstName = firstName;
            strRegisteredLastName = lastName;
            strMessage = "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nYou have registered successfully.";
        }

        return strMessage;
    }
    
    public boolean loginUser(String username, String password) {
        boolean result = false;

        if (strRegisteredUsername != null && strRegisteredPassword != null
                && strRegisteredUsername.equals(username)
                && strRegisteredPassword.equals(password)) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
    
    public String returnLoginStatus(boolean successLogin, String firstName, String lastName) {
        String message;

        if (successLogin) {
            message = "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            message = "Username or password incorrect, please try again.";
        }

        return message;
    }
    
}