# Food-Delivery-System
The main objective of this application is to design and implement a food delivery management system for a catering company. The client can order products from the company’s menu. The system should have three types of users that log in using a username and a password: administrator, regular employee, and client. 


## Requirements
IntelliJ IDEA

## Usage
### Requirements
The application allows the login of 3 types of users. If he logs in as an administrator, the application will allow him to perform various operations on the products in the menu (such as adding, deleting, modifying a basic product, as well as creating products composed of a series of basic products), to generate reports on different criteria and to import basic products from an external .csv file.
If the user logs in with the client role, then he will be able to access the menu with the available products and create orders. All orders will be sent to an employee and invoices will be generated as a result of placing an order. Also, if the user logs in as an employee, he will be able to access the list of orders that took place before logging into the application.

### Implementation
In programming, the Business Logic Layer (BLL) serves as an intermediary for data exchange between the presentation and the Data Access Layer (DAL). BLL manages the rules, calculations, and logic within an application that dictate how it behaves. That is, the BLL determines how the data in the database is used and what can and cannot be done within the application.

Composite Design Pattern is a pattern intended to allow individual objects and compositions of objects or "composites" to be treated in the same way. It can be viewed as a tree structure made up of types that inherit from a base type and can represent a single part or an entire hierarchy of objects.

The Observer Design Pattern is a behavioral design pattern. Specifies communication between objects: observables and observers. An observable is an object that informs observers about changes in its state.

The purpose of Design by Contract is to allow programmers to "incorporate software specifications into the source code of the software and have it automatically check itself at runtime." This is done by introducing "contracts" - executable code contained in the source that specifies the obligations for classes, methods and their callers.

## Visuals
### Login page
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">

### Admin page
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">
![2](https://user-images.githubusercontent.com/93877610/232523560-14bba720-fe19-46fe-8a16-7d3b5c0791c6.png)

Import products
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">
![9](https://user-images.githubusercontent.com/93877610/232523901-e8bd1ca1-36b3-45b8-80d7-30f0c9536e2d.png)
![10](https://user-images.githubusercontent.com/93877610/232523906-441d5287-288a-4049-a19d-fa0a0dcb94da.png)

Create composite product
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">
![3](https://user-images.githubusercontent.com/93877610/232524027-8b8ceb51-6f1d-4d26-afd7-45efb66430ba.png)

### Employee page
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">
![7](https://user-images.githubusercontent.com/93877610/232523626-103fad77-9f93-4c52-99d8-bcf49fb79dbd.png)

Notifications
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">
![8](https://user-images.githubusercontent.com/93877610/232524344-1a72eaca-d737-4c08-b046-da1aaa24c6b5.png)


### Client page
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">

![4](https://user-images.githubusercontent.com/93877610/232523672-efe8baa8-22ac-449d-b428-7838eb2c367b.png)

Menu
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">
![5](https://user-images.githubusercontent.com/93877610/232524118-71fd83d3-b984-45ea-a31c-e65e971f648b.png)

Order
<img src="https://user-images.githubusercontent.com/93877610/232523246-5cc20fe5-6e01-4da8-856a-bd7f8994565c.png" width="320" height="200">
![6](https://user-images.githubusercontent.com/93877610/232524237-bceb4405-f3f2-4d08-bbc6-1cb93c20e0a9.png)

