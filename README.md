# Flight-Booking-System
Simple Flight Booking System using Spring Boot V.3

## Description

This is a RESTful API for a simple flight booking system that enable the user to apply CRUD operations on the flights and for the passenger to book, update or cancel a reservation. This projects satisfies the task objectives with a lack of Business logic which will be developed in the future reference. The main focus was on applying CRUD operations and get familiar with Spring boot framework.

## Technologies Used

- Spring Boot V.3.
- PostgreSQL Database.
- Postman.

## ER Diagram

![ER Diagram-2](https://user-images.githubusercontent.com/40776283/205482552-da44595b-95b8-4205-b0a3-dc251f573bc2.png)

## Connection to Database

Make sure you are using PostgreSQL and created a database with name flight_booking

```
spring.datasource.url=jdbc:postgresql://localhost:5432/flight_booking
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

```
