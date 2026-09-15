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
    
    public boolean checkUserName(String strUsername) {
        boolean result = false;

        if (strUsername != null && strUsername.contains("_") && strUsername.length() <= 5) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
    
    public boolean checkPasswordComplexity(String strPassword) {
        boolean blnHasMinLength = false;
        boolean blnHasCapital = false;
        boolean blnHasNumber = false;
        boolean blnHasSpecialChar = false;
        boolean result = false;

        if (strPassword != null && strPassword.length() >= 8) {
            blnHasMinLength = true;
        }

        if (strPassword != null) {
            int intIndex = 0;
            while (intIndex < strPassword.length()) {
                char chrCurrent = strPassword.charAt(intIndex);

                if (Character.isUpperCase(chrCurrent)) {
                    blnHasCapital = true;
                } else if (Character.isDigit(chrCurrent)) {
                    blnHasNumber = true;
                } else if (!Character.isLetterOrDigit(chrCurrent)) {
                    blnHasSpecialChar = true;
                }

                intIndex++;
            }
        }

        if (blnHasMinLength && blnHasCapital && blnHasNumber && blnHasSpecialChar) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
    
    public boolean checkCellPhoneNumber(String strCellPhoneNumber) {
        boolean result = false;
        String strPattern = "^\\+27[0-9]{9}$";

        if (strCellPhoneNumber != null && Pattern.matches(strPattern, strCellPhoneNumber)) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
    
}