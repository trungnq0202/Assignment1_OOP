/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 1
  Author: Ngo Quang Trung
  ID: 3742774
  Created  date: 15/11/2019
  Last modified: 15/11/2019
  Acknowledgement: Lecturer's slides
*/

class Contact {
    private String fullName; //stores contact's full name
    private String phoneNumber; //stores contact's phone number
    private String email; //stores contact's email
    private String address; //stores contact's address

    Contact(String fullName, String phoneNumber, String email, String address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    //print this contact object to console
    void printContact(){
        System.out.printf("%s; ", this.getFullName());
        System.out.printf("%s; ", this.getPhoneNumber());
        System.out.printf("%s; ", this.getEmail());
        System.out.printf("%s ", this.getAddress());
    }

    String getFullName() {
        return fullName;
    }

    void setFullName(String fullName) {
        this.fullName = fullName;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    String getEmail() {
        return email;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    void setEmail(String email) {
        this.email = email;
    }

}
