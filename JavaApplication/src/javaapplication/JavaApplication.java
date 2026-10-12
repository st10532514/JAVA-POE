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
        String lastName = "";
        String username = "";
        String password = "";
        String cellPhoneNumber = "";

        // First name capture (no validation rule specified in brief)
        System.out.print("Enter first name: ");
        firstName = objScanner.nextLine();

        System.out.print("Enter last name: ");
        lastName = objScanner.nextLine();

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
        String registerMessage = objLogin.registerUser(username, password, cellPhoneNumber, firstName, lastName);
        System.out.println(registerMessage);

        // Login flow
        System.out.print("Enter username to login: ");
        String loginUsername = objScanner.nextLine();
        System.out.print("Enter password to login: ");
        String loginPassword = objScanner.nextLine();

        boolean successLogin = objLogin.loginUser(loginUsername, loginPassword);
        String loginStatus = objLogin.returnLoginStatus(successLogin, firstName, lastName);
        System.out.println(loginStatus);

        // ===================== PART 2 =====================
        // Messaging is only allowed if the login was successful
        String failedLoginMessage = "Username or password incorrect, please try again.";
        if (loginStatus.equals(failedLoginMessage)) {
            System.out.println("Login failed. You cannot use QuickChat.");
        } else {
            runQuickChat(objScanner);
        }

        objScanner.close();

    }

    // Keeps asking until the user types a whole number (stops crashes on bad input)
    public static int readNumber(Scanner input, String prompt) {
        int number = -1;
        boolean valid = false;
        while (!valid) {
            System.out.println(prompt);
            String text = input.nextLine();
            try {
                number = Integer.parseInt(text.trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return number;
    }

    // Part 2: welcome message, numeric menu and message sending
    public static void runQuickChat(Scanner input) {
        System.out.println("Welcome to QuickChat.");

        // The user sets how many messages they will enter, once, at the start
        int numMessages = readNumber(input, "How many messages do you wish to enter?");
        boolean messagesEntered = false;
        Message lastMessage = null;   // used to read the running total

        boolean running = true;
        while (running) {
            System.out.println("\n--- QuickChat Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            int option = readNumber(input, "Choose an option:");

            switch (option) {
                case 1:
                    if (messagesEntered) {
                        System.out.println("You have already entered your " + numMessages + " messages.");
                    } else {
                        // The loop counter i is the message number (starts at 0)
                        for (int i = 0; i < numMessages; i++) {
                            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");

                            // Recipient: repeat until correctly formatted
                            String recipient = "";
                            Message message = null;
                            boolean recipientOk = false;
                            while (!recipientOk) {
                                System.out.println("Enter recipient cell number:");
                                recipient = input.nextLine();
                                message = new Message(i, recipient, "placeholder");
                                String recipientResult = message.checkRecipientCell();
                                System.out.println(recipientResult);
                                if (recipientResult.equals("Cell phone number successfully captured.")) {
                                    recipientOk = true;
                                }
                            }

                            // Message text: repeat until 250 characters or fewer
                            String messageText = "";
                            boolean lengthOk = false;
                            while (!lengthOk) {
                                System.out.println("Enter your message (max 250 characters):");
                                messageText = input.nextLine();
                                message = new Message(i, recipient, messageText);
                                String lengthResult = message.checkMessageLength();
                                System.out.println(lengthResult);
                                if (lengthResult.equals("Message ready to send.")) {
                                    lengthOk = true;
                                }
                            }

                            // Send / Disregard / Store
                            System.out.println("\nWhat would you like to do with this message?");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message to send later");
                            int choice = readNumber(input, "Choose an option:");
                            System.out.println(message.SentMessage(choice));

                            if (choice == 1) {
                                // Full details: Message ID, Message Hash, Recipient, Message
                                System.out.println("\n" + message.getFullDetails());
                            } else if (choice == 2) {
                                String deleteInput = input.nextLine();
                                if (deleteInput.trim().equals("0")) {
                                    System.out.println("Message deleted.");
                                }
                            }

                            lastMessage = message;
                        }
                        messagesEntered = true;

                        // Total accumulated once all messages are done
                        int total = 0;
                        if (lastMessage != null) {
                            total = lastMessage.returnTotalMessagess();
                        }
                        System.out.println("\nTotal messages sent: " + total);
                    }
                    break;

                case 2:
                    String recentMessages = Message.printMessages();
                    if (recentMessages.equals("")) {
                        System.out.println("No messages have been sent yet.");
                    } else {
                        System.out.println("\n--- Recently Sent Messages ---");
                        System.out.println(recentMessages);
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2 or 3.");
                    break;
            }
        }
    }

}