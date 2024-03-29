package com.devstutor;

import java.io.*;
import java.util.ArrayList;

public class ContactBook {
    private ArrayList<Contact> contacts;

    public ContactBook(){
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact){
//        if (!Validator.isValidName(contact.getName()))
//            throw new IllegalArgumentException("Name must not be empty and have max 20 characters.");

        if (!Validator.isValidEmail(contact.getEmail()))
            throw new IllegalArgumentException("Invalid email.");

        if (!Validator.isValidPhoneNumber(contact.getPhoneNumber()))
            throw new IllegalArgumentException("Invalid Phone number.");

        contacts.add(contact);
    }

    public void removeContact(Contact contact){
        contacts.remove(contact);
    }

    public void displayAllContacts(){
        if (contacts.isEmpty())
            System.out.println("Address book is empty.");
        else {
            System.out.println("All Contacts:");
            for (Contact contact : contacts)
                System.out.println(contact);
        }
    }

    public Contact searchContact(String name){
        for(Contact contact : contacts)
            if(contact.getName().equalsIgnoreCase(name))
                return contact;
        return null;
    }

    public void saveContactsToFile(String fileName){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(contacts);
            System.out.println("Contacts saved to file successfully");
        } catch (IOException e){
            System.err.println("Error saving contacts to file: "+e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadContactsFromFile(String fileName){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))){
            contacts = (ArrayList<Contact>) objectInputStream.readObject();
            System.out.println("Contacts loaded from file successfully");
            System.out.println(contacts);
        } catch (IOException | ClassNotFoundException e){
            System.err.println("Error loading contacts from file: "+e.getMessage());
        }
    }
}
