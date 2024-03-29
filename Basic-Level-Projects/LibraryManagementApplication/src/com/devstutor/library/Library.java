package com.devstutor.library;

import com.devstutor.book.Book;
import com.devstutor.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private static final String DATA_FILE = "library_data.ser";
    private Map<Book, Integer> catalog;
    private final List<User> users;

    public Library() {
        this.catalog = new HashMap<>();
        this.users = new ArrayList<>();
        loadLibraryData();
    }

    public void addBook(Book book, int quantity) {
        catalog.put(book, quantity);
        book.setAvailable(true); // Mark the book as available when added
    }

    public void removeBook(Book book) {
        catalog.remove(book);
    }

    public void displayCatalog() {
        if (catalog.isEmpty()) {
            System.out.println("Library Catalog is empty.");
            return;
        }

        System.out.println("Library Catalog:");
        for (Map.Entry<Book, Integer> entry : catalog.entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(book + " - Quantity: " + quantity);
        }
    }

    public Map<Book, Integer> getCatalog() {
        return catalog;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void displayUsers() {
        System.out.println("Library Users:");
        for (User user : users)
            System.out.println(user.getUserName());
    }

    public synchronized void borrowBook(Book book, User user) {
        if (!book.isAvailable()) {
            System.out.println("Sorry, this book is currently not available for borrowing.");
            return;
        }
        int quantity = catalog.getOrDefault(book, 0);
        if (quantity > 0) {
            catalog.put(book, quantity - 1);
            book.setAvailable(false);
            System.out.println(user.getUserName() + " has borrowed the book: " + book.getTitle());
            saveLibraryData(); // updated data saved to the file
        } else {
            System.out.println("Sorry, the book is out of stock");
        }
    }

    public synchronized void returnBook(Book book, User user) {
        int quantity = catalog.getOrDefault(book, 0);
        catalog.put(book, quantity + 1);
        book.setAvailable(true);
        System.out.println(user.getUserName() + " has returned the book: " + book.getTitle());
        saveLibraryData();
    }

    @SuppressWarnings("unchecked")
    public void loadLibraryData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            // If the file doesn't exist, create an empty catalog
            catalog = new HashMap<>();
            return;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Object data = inputStream.readObject();
            if (data instanceof Map) {
                catalog = (Map<Book, Integer>) data;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading library data: " + e.getMessage());
        }
    }

    private void saveLibraryData() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            outputStream.writeObject(catalog);
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        }
    }

    public List<User> getUsers() {
        return users;
    }
}
