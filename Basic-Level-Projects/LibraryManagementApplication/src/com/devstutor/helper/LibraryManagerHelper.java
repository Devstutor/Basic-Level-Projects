package com.devstutor.helper;

import com.devstutor.book.Book;
import com.devstutor.library.Library;
import com.devstutor.user.Librarian;
import com.devstutor.user.RegularUser;
import com.devstutor.user.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryManagerHelper {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void displayMenu() {
        int choice = 0;
        do {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display catalog");
            System.out.println("4. Add user");
            System.out.println("5. Remove user");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Display Users");
            System.out.println("9. Exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                processChoice(choice);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        } while (choice != 9);
        scanner.close();
    }

    private static void processChoice(int choice) {
        switch (choice) {
            case 1 -> addBook();
            case 2 -> removeBook();
            case 3 -> displayCatalog();
            case 4 -> addUser();
            case 5 -> removeUser();
            case 6 -> borrowBook();
            case 7 -> returnBook();
            case 8 -> displayUsers();
            case 9 -> System.out.println("Exiting...");
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void displayUsers() {
        library.displayUsers();
    }

    private static void returnBook() {
        System.out.print("Enter the title of the book to return: ");
        String title = scanner.nextLine();
        Book bookToReturn = findBook(title);
        if (bookToReturn != null) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            User user = findUser(username);
            if (user != null)
                library.returnBook(bookToReturn, user);
            else
                System.out.println("User not found.");
        } else
            System.out.println("Book not found.");
    }

    private static void borrowBook() {
        System.out.print("Enter title of the book to borrow: ");
        String title = scanner.nextLine();
        Book bookToBorrow = findBook(title);
        if (bookToBorrow != null) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            User user = findUser(username);
            if (user != null)
                library.borrowBook(bookToBorrow, user);
            else
                System.out.println("User not found");
        } else
            System.out.println("Book not found.");
    }

    private static User findUser(String username) {
        for (User u : library.getUsers()) {
            if (u.getUserName().equalsIgnoreCase(username))
                return u;
        }
        return null;
    }

    private static Book findBook(String title) {
        for (Book book : library.getCatalog().keySet()) {
            if (book.getTitle().equalsIgnoreCase(title))
                return book;
        }
        return null;
    }

    private static void removeUser() {
        System.out.print("Enter the username of the user to remove: ");
        String username = scanner.nextLine();
        User user = findUser(username);
        if (user != null) {
            library.removeUser(user);
            System.out.println("User removed successfully");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void addUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter user type (1 for Regular user, 2 for Librarian): ");
        int userType = scanner.nextInt();
        scanner.nextLine();
        if (userType == 1) {
            RegularUser user = new RegularUser(username);
            library.addUser(user);
            System.out.println("Regular user added successfully.");
        } else if (userType == 2) {
            Librarian librarian = new Librarian(username);
            library.addUser(librarian);
            System.out.println("Librarian added successfully.");
        } else {
            System.out.println("Invalid user type.");
        }
    }

    private static void displayCatalog() {
        library.displayCatalog();
    }

    private static void removeBook() {
        System.out.print("Enter the title of the book to remove: ");
        String title = scanner.nextLine();
        Book bookToRemove = findBook(title);
        if (bookToRemove != null) {
            library.removeBook(bookToRemove);
            System.out.println("Book removed successfully");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        Book book = new Book(title, author, genre);
        library.addBook(book, quantity);
        System.out.println("Book added successfully");
    }
}
