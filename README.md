Employee Reimbursement System
Project Description:
This project is a full-stack application that stores users, their reimbursement tickets, and the roles and types associated with each respectively. Users are comprised of Managers and Employees, with both being able to log in and brought to the page of their role-type. Once an Employee is logged-in, they may view previous tickets and create new ones (all new tickets are pending until updated by a manager). Managers are able to log in, view tickets by all, sorted pending, sorted approved, and sorted approved denied. Furthermore, managers will have two options for denying and approving tickets by their ID. Both users will then be able to log out, terminating their session thus preventing any tampering. 

Technologies Used

    Log4j - version 1.2.17
    JUnit - version 4.12
    Mockito - version 3.7.7
    PostgreSQL – Version 9.1
    Jackson – Version 2.9.9
    JavaScript – ES6
    JAVA – Version 8
    Maven – Version 3.1
    Servlets
    

Features

List of features ready and TODOs for future development

    Disabled use of the back-button in any web-browser
    Manager can view all three sorted ticket-types simultaneously
    If the Employee inputs as invalid ticket-type, it automatically changes it into “Other”

To-do list:

    More visual enhancements for the data being viewed
    Change input of ticket-type on the Employee page to a radio-button choice, in place of manual input

Getting Started

Clone repo to your workspace or a directory of your choosing.
Using Spring Tool Suite, import the project. 
In STS package explorer, navigate to src/main.webapp/resources/html 
Once in this directory, right click the index.html and choose “run as” then select “Run on server”
These steps will open your web-browser and bring you to the login page

Usage
The database being used is not guaranteed to be active at the time one may choose to run this program, so in that unfortunate event the program will not operate as intended.
