/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 1
  Author: Ngo Quang Trung
  ID: 3742774
  Created  date: 15/11/2019
  Last modified: 15/11/2019
  Acknowledgement:  - Lecturer's slides,
                    - List interface: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html
                    - fullname, email regex pattern: https://www.regextester.com/
                    - try, catch: next few weeks lecturer's slides

*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //Const string array for defining valid user input of 3 program menu
    private static final String[] VALIDCOMMANDS = {"1","2","3","4","5","6","7","8","9"};
    private static final String[] VALIDSORTCHOICES = {"1","2","3","4"};
    private static final String[] VALIDSEARCHCHOICES = {"1","2","3","4"};

    //Const string for regular expression of each contact field
    private static final String FULLNAME_PATTERN = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    private static final String PHONENUMBER_PATTERN = "^[+]{0,1}[\\s0-9]{5,15}$";
    private static final String EMAIL_PATTERN = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
    private static final String ADDRESS_PATTERN = "^(\\d+) (.*)$";

    //Enum type which defines each type of error
    private enum Error{
        INPUT_FILE_NOT_FOUND,
        OUTPUT_FILE_NOT_FOUND,
        INVALID_INPUT,
        INVALID_CONTACT_INDEX,
        EMPTY_CONTACT_LIST,
        INVALID_SORT_CHOICE,
        INVALID_SEARCH_CHOICE,
        UNSUPPORTED_ENCODING,
        INVALID_FULLNAME_FORMAT,
        INVALID_PHONENUMBER_FORMAT,
        INVALID_EMAIL_FORMAT,
        INVALID_ADDRESS_FORMAT,
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        //Create a contacts list object
        ContactList contactList = new ContactList( new ArrayList<Contact>() );

        while (true){
            printMainMenu();
            String command = scanner.nextLine(); //Get command
            if (checkValidInput(command,VALIDCOMMANDS)){ //If the input format is right
                if (command.equals("9")) { //If the user want to exit
                    scanner.close();        //close scanner
                    return;
                }
                else executeCommand(command, contactList,scanner); // Handle chosen program command
            } else {
                printErrorMessage(Error.INVALID_INPUT); //print error to console
            }
        }
    }

    //Function for printing the program's command menu
    private static void printMainMenu(){
        System.out.println("-------------- Command Menu --------------");
        System.out.println("1. Load contacts from file");
        System.out.println("2. View all contacts");
        System.out.println("3. Add new contact");
        System.out.println("4. Edit a contact");
        System.out.println("5. Delete a contact");
        System.out.println("6. Search contact list");
        System.out.println("7. Sort contact list");
        System.out.println("8. Save contacts to file");
        System.out.println("9. Quit");
        System.out.println();
        System.out.print("Select a function (1-9): ");
    }

    //Function for printing the program's sorting menu
    private static void printSortMenu(){
        System.out.println("Please enter the choice of field you want to sort the contact list");
        System.out.println("1. Sort by full name.");
        System.out.println("2. Sort by phone number.");
        System.out.println("3. Sort by email.");
        System.out.println("4. Sort by address.");
        System.out.println();
        System.out.print("Choice (1-4): ");
    }

    //Function for printing the program's search menu
    private static void printSearchMenu(){
        System.out.println("Please enter the choice of field you want to search the contact list");
        System.out.println("1. Search by full name .");
        System.out.println("2. Search by phone number .");
        System.out.println("3. Search by email .");
        System.out.println("4. Search by address .");
        System.out.println();
        System.out.print("Choice (1-4): ");
    }

    //Function for validating user's input
    private static boolean checkValidInput(String command, String[] validChoiceSet){ //Check if the input command is from 1 - 9
        return Arrays.asList(validChoiceSet).contains(command);
    }

    //Function for validating the input contact index in contact list
    private static boolean checkValidContactIndex(ContactList contactList, int index){
        return index >= 1 || index <= contactList.getLength();
    }

    //Function for checking if the contact list is empty
    private static boolean checkEmptycontactList(ContactList contactList){
        return contactList.getLength() > 0;
    }

    //Handle user input command
    private static void executeCommand(String command, ContactList contactList, Scanner scanner){
        switch (command){
            case "1":
                loadContactsFromFile(contactList);
                break;
            case "2":
                printContacts(contactList);
                break;
            case "3":
                addContact(contactList,scanner);
                break;
            case "4":
                editContact(contactList,scanner);
                break;
            case "5":
                deleteContact(contactList,scanner);
                break;
            case "6":
                searchContact(contactList,scanner);
                break;
            case "7":
                sortContactList(contactList, scanner);
                break;
            case "8":
                saveContactsToFile(contactList);
                break;
            default:
                break;
        }
    }

    //print error message to console
    private static void printErrorMessage(Error error){
        switch (error){
            case INVALID_INPUT:
                System.out.println("Invalid option! Please enter option 1 - 9 as listed.");
                break;
            case INPUT_FILE_NOT_FOUND:
                System.out.println("Cannot find text file to load contacts!");
                break;
            case OUTPUT_FILE_NOT_FOUND:
                System.out.println("Cannot find text file to save contacts!");
                break;
            case INVALID_CONTACT_INDEX:
                System.out.println("Invalid contact index, please look at the contacts list again to choose the right index!");
                break;
            case EMPTY_CONTACT_LIST:
                System.out.println("No contact record in the contacts list!");
                break;
            case INVALID_SORT_CHOICE:
                System.out.println("Invalid sort option! Please enter option 1 - 4 as listed.");
                break;
            case INVALID_SEARCH_CHOICE:
                System.out.println("Invalid search option! Please enter option 1 - 4 as listed.");
                break;
            case UNSUPPORTED_ENCODING:
                System.out.println("Contacts being save to output file have unsupported encoding!");
                break;
            case INVALID_FULLNAME_FORMAT:
                System.out.println("Invalid full name, please enter correctly again!");
                System.out.println("Remember, full name must not have numbers!");
                break;
            case INVALID_PHONENUMBER_FORMAT:
                System.out.println("Invalid phone number, please enter correctly again!");
                System.out.println("Remember, phone number must have length between 5 and 15 and (+) is only allowed once at the beginning, no letters!");
                break;
            case INVALID_EMAIL_FORMAT:
                System.out.println("Invalid email, please enter correctly again!");
                break;
            case INVALID_ADDRESS_FORMAT:
                System.out.println("Invalid address, please enter correctly again!");
                System.out.println("Remember the format of address: [House number] [street name], [district], [city] ");
                break;
        }
        System.out.println("/*--------------------------------------------*/");
        System.out.println();
    }

    //take contact list from text file
    private static void loadContactsFromFile(ContactList contactList){
        try{
            File inputFile = new File("contacts.txt"); //create new file object from "contacts.txt"
            Scanner scanner = new Scanner(inputFile);
            contactList.deleteAllContacts();

            while (scanner.hasNextLine()){
                String[] tempContactInfo = scanner.nextLine().split("; ",0); //Split each line of input by regex "; "
                Contact contact = new Contact(tempContactInfo[0],tempContactInfo[1],tempContactInfo[2],tempContactInfo[3]); // create new contact object
                contactList.addContact(contact); // add contact to contact list
            }
            scanner.close();

            System.out.println("Done!");
            System.out.println();
        }
        catch (FileNotFoundException e){ //If the "contacts.txt is not found"
            printErrorMessage(Error.INPUT_FILE_NOT_FOUND); //print error message to console
        }
    }

    //function printing all contacts
    private static void printContacts(ContactList contactList){
        System.out.println("---------------------- Contacts List ----------------------");
        if (contactList.getLength() == 0){ //Check if the contact list is empty
            System.out.println("No contacts found!");
            return;
        }
        contactList.printAllContacts(); //print all contacts to console
        System.out.println("---------------------- End ----------------------");
        System.out.println();
    }

    //function for adding contact
    private static void addContact(ContactList contactList, Scanner scanner){
        System.out.println("---------------------- Add Contact ----------------------");
        Contact newContact = getContactFieldInput(scanner); //getting contact's input field from console (also validate)
        contactList.addContact(newContact); //add contact to contact list
        System.out.println("Add contact successfully!");
        System.out.println("---------------------- End ----------------------");
        System.out.println();
    }

    //function for editing contact with chosen index
    private static void editContact(ContactList contactList, Scanner scanner){
        if (!checkEmptycontactList(contactList)){ //Check if the contact list is empty
            printErrorMessage(Error.EMPTY_CONTACT_LIST); // print empty contact list error to console, no contact to edit
            return;
        }
        System.out.println("---------------------- Edit Contact ----------------------");
        System.out.print("Please input the index of contact you want to edit: ");
        int index = scanner.nextInt(); scanner.nextLine(); // Get the contact user want to edit by index
        if (!checkValidContactIndex(contactList, index)){ // Validate the input index
            printErrorMessage(Error.INVALID_CONTACT_INDEX); //print invalid input index error to console
            return;
        }
        Contact editedContact = getContactFieldInput(scanner); //getting contact's input field from console (also validate)
        contactList.editContact(index - 1, editedContact); //call contactList's method to change contact's field

        System.out.println("Edit contact successfully!");
        System.out.println("--------------------------- End ---------------------------");
        System.out.println();
    }

    //function for deleting contact with chosen index
    private static void deleteContact(ContactList contactList, Scanner scanner){
        if (!checkEmptycontactList(contactList)){ //Check if the contact list is empty
            printErrorMessage(Error.EMPTY_CONTACT_LIST); // print empty contact list error to console, no contact to delete
            return;
        }
        System.out.println("---------------------- Delete Contact ----------------------");
        System.out.print("Please input the index of contact you want to delete: ");
        int index = scanner.nextInt(); // Get the contact user want to delete by index
        if (!checkValidContactIndex(contactList, index)){  // Validate the input index
            printErrorMessage(Error.INVALID_CONTACT_INDEX); //print invalid input index error to console
            return;
        }
        contactList.deleteContact(index - 1); //call contactList's method to delete this contact in the contact list
        System.out.println("Delete contact successfully!");
        System.out.println("--------------------------- End ---------------------------");
        System.out.println();
    }

    //function for searching contact according to different fields
    private static void searchContact(ContactList contactList , Scanner scanner){
        printSearchMenu(); //print search menu to console
        String choice = scanner.nextLine(); //get user's chosen option
        if (!checkValidInput(choice, VALIDSEARCHCHOICES)){ //validate user's input option (1 - 4)
            printErrorMessage(Error.INVALID_SORT_CHOICE); //print invalid user's input option to console
        }
        System.out.println("---------------------- Found Contacts ----------------------");
        switch (choice){
            case "1":
                System.out.print("Please enter a part of the contact's full name you want to search for: ");
                String name = scanner.nextLine(); //get user's input piece of name
                contactList.searchByName(name); //search all contacts with full name containing the given piece of name and print them to console
                break;
            case "2":
                System.out.print("Please enter a part of the contact's phone number you want to search for: ");
                String number = scanner.nextLine(); //get user's input piece of phone number
                contactList.searchByPhoneNumber(number); //search all contacts with phone number containing the given piece of phone number and print them to console
                break;
            case "3":
                System.out.print("Please enter a part of the contact's email you want to search for: ");
                String email = scanner.nextLine(); //get user's input piece of email
                contactList.searchByEmail(email); //search all contacts with email containing the given piece of email and print them to console
                break;
            case "4":
                System.out.print("Please enter a part of the contact's address you want to search for: ");
                String address = scanner.nextLine(); //get user's input piece of address
                contactList.searchByAddress(address); //search all contacts with address containing the given piece of address and print them to console
                break;
            default:
                break;
        }
        System.out.println("--------------------------- End ---------------------------");
        System.out.println();
    }

    //function for sorting contact list ascending according to different fields
    private static void sortContactList(ContactList contactList, Scanner scanner){
        printSortMenu(); //print sort(field) options
        String choice = scanner.nextLine();  //get user's chosen option
        if (!checkValidInput(choice, VALIDSORTCHOICES)){ //validate user's input option (1 - 4)
            printErrorMessage(Error.INVALID_SORT_CHOICE); //print invalid user's input option to console
        } else {
            switch (choice){
                case "1":
                    contactList.sortByName(); //sort by full name
                    break;
                case "2":
                    contactList.sortByPhoneNumber(); //sort by phone number
                    break;
                case "3":
                    contactList.sortByEmail(); //sort by email
                    break;
                case "4":
                    contactList.sortByAddress(); //sort by address
                    break;
                default:
                    break;
            }
            System.out.println("Done!");
            System.out.println();
        }
    }

    //function for saving contact list to contacts.txt
    private static void saveContactsToFile(ContactList contactList) {
        try {
            File outputFile = new File("contacts.txt"); //create new file object from "contacts.txt"
            PrintWriter writer = new PrintWriter(new FileOutputStream(outputFile,false)); //create print writer object and put append mode to false to overwrite the file
            for (int index = 0; index < contactList.getLength(); index++) { //loop through each contacts in the contact list
                Contact contact = contactList.getContact(index); //get contact object
                writer.format("%s; %s; %s; %s", contact.getFullName(), contact.getPhoneNumber(), contact.getEmail(), contact.getAddress()); //print by the same format given at the beginning
                writer.println();
            }
            writer.flush();// flush the printwriter's buffer
            writer.close(); // close printwriter's object
            System.out.println("Done!");
            System.out.println();
        } catch (FileNotFoundException  e){ //catch the file not found error
            printErrorMessage(Error.OUTPUT_FILE_NOT_FOUND); //print file not found error to console
        }
    }

    //function for validating input contact's field
    private static boolean validateField(String fieldInput, String pattern, Error errorMessage){
        if (fieldInput.matches(pattern)) return true; //returning true if the input field matches the regex pattern
        else { printErrorMessage(errorMessage); return false;} // else print error message and return false
    }

    //function for getting input field from console and validate
    private static String getAndValidateInputField(String fieldName, String pattern, Error errorMessage, Scanner scanner){
        String inputField;
        do {
            System.out.printf("Please enter %s: ", fieldName);
            inputField = scanner.nextLine(); //get input field from console until the input field satisfy the regex pattern
        } while (!validateField(inputField, pattern, errorMessage));
        return inputField; //return that validated input field
    }

    //function for getting input field
    private static Contact getContactFieldInput(Scanner scanner){
        //get each input field and validate before creating a contact object
        String fullName = getAndValidateInputField("full name", FULLNAME_PATTERN, Error.INVALID_FULLNAME_FORMAT, scanner);
        String phoneNumber = getAndValidateInputField("phone number", PHONENUMBER_PATTERN, Error.INVALID_PHONENUMBER_FORMAT, scanner);
        String email = getAndValidateInputField("email", EMAIL_PATTERN, Error.INVALID_EMAIL_FORMAT, scanner);
        String address = getAndValidateInputField("address", ADDRESS_PATTERN, Error.INVALID_ADDRESS_FORMAT, scanner);
        return new Contact(fullName, phoneNumber, email, address); //return new contact object with validated input fields
    }

}
