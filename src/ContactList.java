/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 1
  Author: Ngo Quang Trung
  ID: 3742774
  Created  date: 15/11/2019
  Last modified: 15/11/2019
  Acknowledgement: Lecturer's slides,
                    List interface: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html
*/

import java.util.*;

//Stores the collection of contacts
class ContactList {
    private List<Contact> contactList;

    ContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    //get the size of the contact list
    int getLength(){
        return contactList.size();
    }

    //get the contact in the contact list by index
    Contact getContact(int index){
        return contactList.get(index);
    }

    //print all contacts in the list to console
    void printAllContacts(){
        for (int index = 0; index < contactList.size(); index++){
            Contact tempContact = contactList.get(index);
            System.out.printf("%d. ", index + 1);
            tempContact.printContact();
            System.out.println();
        }
    }

    //add contact by user input fields after validation
    void addContact(Contact contact){
        this.contactList.add(contact);
    }

    //edit contact by user input fields after validation
    void editContact(int index, Contact contact){
        contactList.get(index).setFullName(contact.getFullName());
        contactList.get(index).setPhoneNumber(contact.getPhoneNumber());
        contactList.get(index).setAddress(contact.getAddress());
        contactList.get(index).setEmail(contact.getEmail());
    }

    //delete contact in the list by index
    void deleteContact(int index){
        contactList.remove(index);
    }

    //delete all contacts when loading the contacts from file
    void deleteAllContacts(){
        contactList.clear();
    }

    //search contact by user's given a part of full name
    void searchByName(String name){
        for (int index = 0; index < contactList.size(); index++ ){
            Contact tempContact = contactList.get(index);
            if (tempContact.getFullName().contains(name)){
                System.out.printf("%d. ", index + 1);
                tempContact.printContact();
                System.out.println();
            }
        }
    }

    //search contact by user's given a part of phone number
    void searchByPhoneNumber(String phoneNumber){
        for (int index = 0; index < contactList.size(); index++ ){
            Contact tempContact = contactList.get(index);
            if (tempContact.getPhoneNumber().contains(phoneNumber)){
                System.out.printf("%d. ", index + 1);
                tempContact.printContact();
                System.out.println();
            }
        }
    }

    //search contact by user's given a part of email
    void searchByEmail(String email){
        for (int index = 0; index < contactList.size(); index++ ){
            Contact tempContact = contactList.get(index);
            if (tempContact.getEmail().contains(email)){
                System.out.printf("%d. ", index + 1);
                tempContact.printContact();
                System.out.println();
            }
        }
    }

    //search contact by user's given a part of address
    void searchByAddress(String address){
        for (int index = 0; index < contactList.size(); index++ ){
            Contact tempContact = contactList.get(index);
            if (tempContact.getAddress().contains(address)){
                System.out.printf("%d. ", index + 1);
                tempContact.printContact();
                System.out.println();
            }
        }
    }

    //sort contact list by name ascending
    void sortByName(){
        contactList.sort(Comparator.comparing(Contact::getFullName));
    }

    //sort contact list by phone number ascending
    void sortByPhoneNumber(){
        contactList.sort(Comparator.comparing(Contact::getPhoneNumber));
    }

    //sort contact list by email ascending
    void sortByEmail(){
        contactList.sort(Comparator.comparing(Contact::getEmail));
    }

    //sort contact list by address ascending
    void sortByAddress(){
        contactList.sort(Comparator.comparing(Contact::getAddress));
    }

}
