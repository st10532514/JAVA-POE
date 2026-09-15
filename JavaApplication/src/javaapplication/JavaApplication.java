/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class JavaApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner objScanner = new Scanner(System.in);
        Login objLogin = new Login();
        
        // Declare user information
        String firstName;
        String lastName;
        String username = "";
        String password = "";
        String cellPhoneNumber = "";
        
        // First name capture (no validation rule specified in brief)
        System.out.print("Enter first name: ");
        firstName = objScanner.nextLine();
        
        System.out.print("Enter last name: ");
        firstName = objScanner.nextLine();
        
        // Username registration
        boolean usernameValid = false;
        while (!usernameValid) {
            System.out.print("Enter username: ");
            username = objScanner.nextLine();
            usernameValid = objLogin.checkUserName(username);
            if (!usernameValid) {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }
        
        // Password registration
        boolean passwordValid = false;
        while (!passwordValid) {
            System.out.print("Enter password: ");
            password = objScanner.nextLine();
            passwordValid = objLogin.checkPasswordComplexity(password);
            if (!passwordValid) {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }
        
        // Cellphone registration
        boolean phoneValid = false;
        while (!phoneValid) {
            System.out.print("Please enter your registered South African number (with the country code: +27): ");
            cellPhoneNumber = objScanner.nextLine();
            phoneValid = objLogin.checkCellPhoneNumber(cellPhoneNumber);
            if (!phoneValid) {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }
        
        // Register the user and display the exact required message
    }
    
}
