package com.example.javaphone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Contact implements Serializable {
    private String name;
    private String email;
    private long number;
    private ArrayList<Message> messages;
    private Scanner scan;

    public Contact(String name, String email, long number, ArrayList<Message> messages) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.messages = messages;
        
    }

    /*****************************************************************************************
     * this constructor prevents problems when trying to add a new contact(has no messages)
     * adding a null for ArrayList<Message> when trying to create new contact will cause problems
     ****************************************************************************************/
    public Contact(String name, String email, long number) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.messages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public void showContactDetails(){
        System.out.println("Name: " + getName() + "\nNumber: " + getNumber() + "\nEmail: " + getEmail());
    }

    public void addMessage(Message text){
        this.messages.add(text);
    }

    public String printMessage(){
        for (int i = 0; i < messages.size(); i++){
            //if there are messages to show
            if(!messages.get(i).equals(null)){
                //return getMessages().toString() + "\n";
                return getMessages().get(i).toString();
            }

        }
        return "";
    }

}
