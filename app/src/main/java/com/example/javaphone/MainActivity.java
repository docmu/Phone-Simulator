package com.example.javaphone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainActivity{
    private static Scanner scan;
    private static Scanner strScan;
    private static ArrayList<Contact> contacts;
    private static int id = 0; //this id simulates a real life phone's messaging system
    /*
    private static File file;
    private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static FileInputStream fis;
    private static ObjectInputStream ois;
    */

    public static void main(String args[])throws Exception {
        scan = new Scanner(System.in);
        contacts = new ArrayList<>();
        /*
        file = new File("Contacts.txt");
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        fis = new FileInputStream(file);
        ois = new ObjectInputStream(fis);
        */
        initialPage();

    }

    public static void initialPage()throws Exception {
        System.out.println("1.Contacts\n2.Send Messages\n3.Quit\n");
        scan = new Scanner(System.in);
        try {
            int input = scan.nextInt();

            switch (input) {
                case 1:
                    manageContacts();
                    break;
                case 2:
                    sendMessage();
                    break;
                case 3:
                    break;
                default:
                    initialPage();
                    break;
            }
        }
        catch (InputMismatchException e){
            System.out.println("Not a valid input");
        }

    }

    private static void manageContacts()throws Exception {
        try{
        System.out.println("1.add new contact\n2.show all contacts\n3.remove a contact\n" +
                "4.search contact\n5.back");
            int input = scan.nextInt();

            switch (input) {
                case 1:
                    addNewContact();
                    break;
                case 2:
                    showContacts();
                    break;
                case 3:
                    removeContact();
                    break;
                case 4:
                    searchContact();
                    break;
                case 5:
                    initialPage();
                    break;
                default:
                    manageContacts();
                    break;
            }
            manageContacts();
        }

        catch (InputMismatchException e){
            System.out.println("Invalid input");
        }

    }

    private static void addNewContact()throws Exception {
        scan = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scan.nextLine();

        scan = new Scanner(System.in);
        System.out.print("Number: ");
        long number = scan.nextLong();

        scan = new Scanner(System.in);
        System.out.print("Email: " );
        String email = scan.nextLine();


        Contact newContact = new Contact(name, email, number);
        contacts.add(newContact);

        //oos.writeObject(newContact);

        System.out.println(name + " has been added to your contacts");
        initialPage();

    }

    private static void showContacts()throws Exception {

        if(contacts.size() < 1) {
            System.out.println("you have no contacts");
        }
        else{

            for(int i = 0; i < contacts.size(); i++){
                contacts.get(i).showContactDetails();
                System.out.println("************************");
                //Contact c = (Contact) ois.readObject();
                //System.out.printf("Name: %s\nNumber: %f\nEmail: %s\n\n", c.getName(),c.getNumber(),c.getEmail());
            }
        }
        manageContacts();
    }

    private static void removeContact()throws Exception {
        System.out.println("Enter the contact name you would like to remove");
        scan = new Scanner(System.in);
        String input = scan.next();

        if(!contacts.isEmpty()){
            for(int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getName().equals(input)) {
                    System.out.println("Are you sure you want to remove " + input + " from your contacts? Y/N");
                    String answer = scan.next();
                    switch (answer) {
                        case "Y":
                            contacts.remove(contacts.get(i));
                            System.out.println(input + " has been removed from your contacts");
                            break;
                        case "N":
                        default:
                            break;
                    }
                }
            }
        }
        else{
            System.out.println("That contact does not exist");
        }

        manageContacts();
    }

    private static void searchContact()throws Exception {
        System.out.println("Enter contact name ");
        scan = new Scanner(System.in);
        String input = scan.nextLine();
        boolean exists = false;
        if(!contacts.isEmpty()) {
            for (int i = 0; i < contacts.size(); i++) {
                if(contacts.get(i).getName().equals(input)){
                    exists = true;
                    System.out.println("1.show contact details\n2.send message\n3.show messages\n" +
                            "4.remove contact\n5.back");
                    int answer = scan.nextInt();
                    switch (answer){
                        case 1:
                            contacts.get(i).showContactDetails();
                            break;
                        case 2:
                            System.out.println("Enter your message: ");
                            scan = new Scanner(System.in);
                            String text = scan.nextLine();
                            id++;
                            Message message = new Message(text, input,id);
                            for(int j = 0; j < contacts.size(); j++){
                                if(contacts.get(j).getName().equals(input)){
                                    contacts.get(j).addMessage(message);
                                }
                            }
                            break;
                        case 3:
                            for(int j = 0; j < contacts.size(); j++) {
                                if (contacts.get(j).getName().equals(input)) {
                                    if(contacts.get(i).getMessages().size() == 0){
                                        System.out.println("No messages available");
                                    }
                                    else {
                                        System.out.println(contacts.get(j).printMessage());
                                    }
                                }
                            }
                            break;
                        case 4:
                            contacts.remove(contacts.get(i));
                            break;
                        case 5:
                            manageContacts();
                            break;
                        default:
                            break;
                    }
                }
            }
            if(exists == false){
                System.out.println(input + " does not exist in your contacts");
            }
        }
        else{
            System.out.println("You have no contacts");
        }
        searchContact();
    }

    private static void sendMessage()throws Exception {
        System.out.println("Who would you like to send a message to?");
        scan = new Scanner(System.in);
        String input = scan.nextLine();
        boolean doesExist = false;

        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).getName().equals(input)){
                doesExist = true;
            }
        }

        if(!doesExist){
            System.out.println(input + " is not a contact on this device");
        }
        else {
            System.out.println("Enter your message: ");
            scan = new Scanner(System.in);
            String text = scan.nextLine();
            id++;
            Message message = new Message(text, input,id);
            for(int i = 0; i < contacts.size(); i++){
                if(contacts.get(i).getName().equals(input)){
                    contacts.get(i).addMessage(message);
                }
            }
            System.out.println("Message sent!");
        }
        initialPage();
    }
}
