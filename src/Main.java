// Malak Zaher Al-Zaharna
//220255689
//201
import java.util.ArrayList;
import java.util.Scanner;

// Contact Class
class Contact {
    String name;
    String type;
    ArrayList<String> numbers = new ArrayList<>();

    Contact(String name, String type, String number) {
        this.name = name;
        this.type = type;
        this.numbers.add(number);
    }
}

// Main Class
public class Main {

    static ArrayList<Contact> addressBook = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    // Main Method
    public static void main(String[] args) {

        while (true) {

            System.out.println("Welcome to Address Book");
            System.out.println("1. Add new contact");
            System.out.println("2. Search by name");
            System.out.println("3. Search by number");
            System.out.println("4. Delete contact by name");
            System.out.println("5. Delete contact by number");
            System.out.println("6. Show all contacts");
            System.out.println("7. Exit");
            System.out.print("Please choose what you want to do: ");

            int choice = input.nextInt();
            input.nextLine();

            // التحقق من الرقم
            if (choice < 1 || choice > 7) {
                System.out.println("Invalid choice. Please choose between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1:
                    addContact();
                    break;

                case 2:
                    searchByName();
                    break;

                case 3:
                    searchByNumber();
                    break;

                case 4:
                    deleteByName();
                    break;

                case 5:
                    deleteByNumber();
                    break;

                case 6:
                    showAllContacts();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("This step is not implemented yet.");
            }
        }
    }

    // 1. Add Contact
    public static void addContact() {

        System.out.print("Enter contact name: ");
        String name = input.nextLine();

        System.out.print("Enter contact type (Family, Personal, Work, Other): ");
        String type = input.nextLine();

        if (!type.equalsIgnoreCase("Family") &&
                !type.equalsIgnoreCase("Personal") &&
                !type.equalsIgnoreCase("Work") &&
                !type.equalsIgnoreCase("Other")) {

            type = "Other";
            System.out.println("Contact types must be only [Family, Personal, Work, Other]. Set to Other");
        }

        System.out.print("Enter contact number: ");
        String number = input.nextLine();

        for (Contact contact : addressBook) {
            if (contact.numbers.contains(number)) {
                System.out.println("Process rejected: This number is already stored.");
                return;
            }
        }

        Contact c = new Contact(name, type, number);
        addressBook.add(c);

        System.out.println("Contact added successfully.");
    }

    // 2. Search by Name
    public static void searchByName() {

        System.out.print("Enter contact's name to find: ");
        String target = input.nextLine().toLowerCase();
        boolean found = false;

        for (Contact c : addressBook) {
            if (c.name.toLowerCase().contains(target)) {
                System.out.println("(" + c.name + ", " + c.type + ", " + c.numbers + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Not found.");
        }
    }

    // 3. Search by Number
    public static void searchByNumber() {

        System.out.print("Enter contact's number to find: ");
        String number = input.nextLine();
        boolean found = false;

        for (Contact c : addressBook) {
            if (c.numbers.contains(number)) {
                System.out.println("(" + c.name + ", " + c.type + ", " + c.numbers + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Not found.");
        }
    }

    // 4. Delete by Name
    public static void deleteByName() {

        System.out.print("Enter name to delete: ");
        String name = input.nextLine();

        int initialSize = addressBook.size();

        addressBook.removeIf(c -> c.name.equalsIgnoreCase(name));

        int deletedCount = initialSize - addressBook.size();

        if (deletedCount > 0) {
            System.out.println("Process success: Deleted " + deletedCount + " contacts.");
        } else {
            System.out.println("Not found.");
        }
    }

    // 5. Delete by Number
    public static void deleteByNumber() {

        System.out.print("Enter number to delete: ");
        String number = input.nextLine();

        boolean removed = addressBook.removeIf(c -> c.numbers.contains(number));

        if (removed) {
            System.out.println("Process success.");
        } else {
            System.out.println("Not found.");
        }
    }

    // 6. Show All Contacts
    public static void showAllContacts() {

        if (addressBook.isEmpty()) {
            System.out.println("Address book is empty.");
        } else {
            for (Contact c : addressBook) {
                System.out.println("(" + c.name + ", " + c.type + ", " + String.join(", ", c.numbers) + ")");
            }
        }
    }
}