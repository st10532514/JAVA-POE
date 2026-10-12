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
    
    private String messageID;
    private int messageNum;
    private String recipientCellphone;
    private String message;
    private String messageHash;
    private static ArrayList<String>sentMessage = new ArrayList<String>();
    private static int totalMessagesSent = 0;
    
    //generate message ID method
    public String generateID(){
        //declaration
        Random generate = new Random();
        String id = "";
        int count = 0;
        
        //while loop
        while (count < 10) {
            id = id + generate.nextInt(10);
            count++;
        }
        return id;
    }
    
    //create message hash method 
    public String createHash() {
        String first2 = messageID.substring(0,2);
        
        String[] words = message.trim().split("");
        String firstWord = words[0];
        String lastWord = words[words.length-1];
        
        firstWord = firstWord.replaceAll("[^A-Za-z0-9]", "");
        lastWord = lastWord.replaceAll("[^A-Za-z0-9]", "");
        
        String hash = first2 +":" + messageNum + ":" + firstWord + lastWord;
         return hash.toUpperCase();
        
    }
    
    //construtor method
    public Message(String recipientCellphone, int messageNum, String message) {
        this.message = message;
        this.messageNum = messageNum;
        this.recipientCellphone = recipientCellphone;
        this.messageID = generateID();
        this.messageHash = createHash();
        
    }
    
    public boolean checkMessageID(){
        boolean check ;
        
        if (messageID.length() <= 10) {
            check = true;
        }else {
            check = false;
        }
        return check;
    }
    
    public String checkRecipientCell() {
        String check;
        
        if (recipientCellphone.matches("^\\+27[0-9]{9}$")) {
            check = "Cellphone number is successfully entered";
        }else {
            check = "Cellphone number is unsuccessfully enetered. Check again";
        }
        
        return check;
    }
    
    public String createMessageHash() {
    
    }
    
    public String sentMessage() {
    
    }
    
    public String printMessage() {
    
    }
    
    public int returnTotalMessages() {
    
    }
    
    public boolean storeMessages() {
    
    }
}
