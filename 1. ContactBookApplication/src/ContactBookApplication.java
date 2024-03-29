import com.devstutor.Contact;
import com.devstutor.ContactBook;

import java.util.Scanner;

public class ContactBookApplication {
    private static final String FILE_NAME = "contacts.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactBook addressBook = new ContactBook();

        // We can load Contacts from file at Start
        addressBook.loadContactsFromFile(FILE_NAME);

        while(true){
            System.out.println("\nContact Book List:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove from Contact");
            System.out.println("3. Show All Contacts");
            System.out.println("4. Search from Contact");
            System.out.println("5. Save the Contact to file");
            System.out.println("6. Load the Contacts from file");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consumes new line

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    addressBook.addContact(new Contact(name, phoneNumber, email));
                }
                case 2 ->{
                    System.out.print("Enter name to remove: ");
                    String nameToRemove = scanner.nextLine();
                    Contact contactToRemove = addressBook.searchContact(nameToRemove);
                    if (contactToRemove != null){
                        addressBook.removeContact(contactToRemove);
                        System.out.println("Contact removed successfully");
                    } else
                        System.out.println("Contact not found");
                }
                case 3 -> addressBook.displayAllContacts();
                case 4 ->{
                    System.out.print("Enter name to search ");
                    String nameToSearch = scanner.nextLine();
                    Contact foundContact = addressBook.searchContact(nameToSearch);
                    if (foundContact != null)
                        System.out.println("Contact found: "+foundContact);
                    else
                        System.out.println("Contact not found");
                }
                case 5 -> addressBook.saveContactsToFile(FILE_NAME);
                case 6 ->{
                    addressBook.loadContactsFromFile(FILE_NAME);
                }
                case 7 ->{
                    addressBook.saveContactsToFile(FILE_NAME);
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please Enter a number between 1 && 6");
            }
        }
    }
}