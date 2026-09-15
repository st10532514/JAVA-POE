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
        boolean blnUsernameValid = false;
        while (!blnUsernameValid) {
            System.out.print("Enter username: ");
            username = objScanner.nextLine();
            blnUsernameValid = objLogin.checkUserName(username);
            if (!blnUsernameValid) {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }
    }
    
}
