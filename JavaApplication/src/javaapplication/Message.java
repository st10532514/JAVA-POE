/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Student
 */
public class Message {

    // Details of ONE message
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Shared by ALL messages (static) - keeps track while the program runs
    private static ArrayList<String> sentMessages = new ArrayList<String>();
    private static int totalMessagesSent = 0;

    // Constructor: runs when we write  new Message(0, "+27...", "Hi...")
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // ---------- Getters and setters ----------
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }

    // Setter so a unit test can use a known ID
    public void setMessageID(String messageID) {
        this.messageID = messageID;
        this.messageHash = createMessageHash(); // rebuild hash with new ID
    }

    // ---------- Message ID ----------

    // Builds a random ten-digit ID, one digit at a time
    private String generateMessageID() {
        Random random = new Random();
        String id = "";
        int count = 0;
        while (count < 10) {
            id = id + random.nextInt(10);
            count++;
        }
        return id;
    }

    // True if the ID is not more than ten characters
    public boolean checkMessageID() {
        boolean result;
        if (messageID.length() <= 10) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    // ---------- Recipient ----------

    // Reuses the Part 1 South African regex: +27 followed by 9 digits
    public String checkRecipientCell() {
        String result;
        if (recipient.matches("^\\+27[0-9]{9}$")) {
            result = "Cell phone number successfully captured.";
        } else {
            result = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        return result;
    }

    // ---------- Message length ----------

    public String checkMessageLength() {
        String result;
        if (messageText.length() <= 250) {
            result = "Message ready to send.";
        } else {
            int extra = messageText.length() - 250;
            result = "Message exceeds 250 characters by " + extra + "; please reduce the size.";
        }
        return result;
    }

    // ---------- Message Hash ----------

    // Format: first two digits of ID : message number : FIRSTWORDLASTWORD
    // Example: 00:0:HITONIGHT
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);

        String[] words = messageText.trim().split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        // Remove punctuation such as "?" or "," so "tonight?" becomes "tonight"
        firstWord = firstWord.replaceAll("[^A-Za-z0-9]", "");
        lastWord = lastWord.replaceAll("[^A-Za-z0-9]", "");

        String hash = firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // ---------- Send / Disregard / Store ----------

    // choice: 1 = Send, 2 = Disregard, 3 = Store
    public String SentMessage(int choice) {
        String result;
        switch (choice) {
            case 1:
                sentMessages.add(getFullDetails());
                totalMessagesSent++;
                result = "Message successfully sent.";
                break;
            case 2:
                result = "Press 0 to delete the message.";
                break;
            case 3:
                storeMessage();
                result = "Message successfully stored.";
                break;
            default:
                result = "Invalid choice.";
                break;
        }
        return result;
    }

    // Message details in the required order: ID, Hash, Recipient, Message
    public String getFullDetails() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText + "\n";
    }

    // All messages sent while the program is running
    public static String printMessages() {
        String allMessages = "";
        int index = 0;
        while (index < sentMessages.size()) {
            allMessages = allMessages + sentMessages.get(index) + "\n";
            index++;
        }
        return allMessages;
    }

    // Total number of messages sent
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    // ---------- JSON storage ----------

    /*
     * Attribution: json-simple library (https://code.google.com/archive/p/json-simple/)
     * and the append-to-file approach from my own JSON research notes.
     * Reads the existing messages.json (if any), adds this message, writes it back.
     */
    @SuppressWarnings("unchecked")
    public boolean storeMessage() {
        boolean stored;
        String filePath = "messages.json";
        JSONArray messageArray = new JSONArray();

        // Step 1: load messages already in the file
        File file = new File(filePath);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(reader);
                if (obj instanceof JSONArray) {
                    messageArray = (JSONArray) obj;
                }
            } catch (IOException | ParseException e) {
                System.out.println("Could not read existing file, starting fresh.");
            }
        }

        // Step 2: add this message
        JSONObject messageObject = new JSONObject();
        messageObject.put("messageID", messageID);
        messageObject.put("messageHash", messageHash);
        messageObject.put("recipient", recipient);
        messageObject.put("message", messageText);
        messageArray.add(messageObject);

        // Step 3: write everything back
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(messageArray.toJSONString());
            writer.flush();
            stored = true;
        } catch (IOException e) {
            System.out.println("Error writing JSON file: " + e.getMessage());
            stored = false;
        }
        return stored;
    }
}