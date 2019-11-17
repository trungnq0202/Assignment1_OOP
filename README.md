# Assignment1_OOP

RMIT University Vietnam Course: INTE2512 Object-Oriented Programming </br>
Semester: 2019C </br>
Assessment: Assignment 1 </br>
Student Name: Ngo Quang Trung </br>
Student ID: 3742774 </br>

## Introduction
This software allows you to manage contact records which includes 4 fields (full name, phone number, email, address). All the command input are done by CLI, and output are printed to console.

## Features
* Load all contact records from text file to a contact list object (named "contacts.txt").
* Print all contacts of the contact list to the console.
* Add a new contact to the contact list. All fields are input from CLI ( and validated). 
* Edit an existed contact in the contact list.  All fields are input from CLI ( and validated).
* Delete an existed contact in the contact list using index.
* Search a contact by a given sequence according to 1 out of 4 available fields.
* Sort the contact list ascending/alphabetically by 1 out of 4 available fields.
* Save and overwrite the edited contact list to text file (named "contacts.txt").

## Installation
* Open the project using **Intellij IDEA 2019**.
* Setup the JDK to version 11.
* Open class Main.java.
* Press **Shift + F10** or go to **Run** tools and hit **Run  'Main'**.

## Known bugs
All input exceptions have been handled by printing error message to the console during running the software.

## Acknowledgement
* Mr Quang's lecturer slides.
* List interface: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html
* fullname, email regex pattern: https://www.regextester.com/
* try, catch: Week 9 lecturer's slides