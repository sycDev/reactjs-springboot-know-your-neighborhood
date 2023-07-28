# Know-Your-Neighborhood

![React](https://img.shields.io/badge/React-18.2.0-5ed3f3)
![SpringBoot](https://img.shields.io/badge/Spring%20Boot-2.7.14-6db33f)
![JDK](https://img.shields.io/badge/JDK-1.8-c74634)
![MySQL](https://img.shields.io/badge/MySQL-8.0.33-f29111)

This repository consists of two applications which are Spring Boot REST API called `springboot-backend` and ReactJS application called `reactjs-frontend`.

## Applications

### springboot-backend

This is the Spring Boot Backend application that exposes a REST API to manage users and stores.
Its secured endpoints can just be accessed if an access token (JWT) is provided and verified.
The data is stored in a MySQL database. This project included the OAuth2 Integration.

### reactjs-frontend

This is the ReactJS Frontend application where users can search and view Stores details such as name, 
contact number, opening hours and address. 
In order to perform CRUD for Store, users must be authenticated using username and password registered through 
the Registration page or through social login.
All the requests coming from the reactjs-frontend to secured endpoints in springboot-backend have an access
token (JWT) that is generated when users logged in.

## Features

There are two roles: USER & ADMIN
<br/>
USER can only view stores but ADMIN can edit and delete existing stores.
<br/>
Ps: Currently, only DELETE endpoints are denied access for role USER. USER able view, add and edit stores.
<br/>
Note: USER can register for an account on the website, while ADMIN accounts are created manually by the system 
administrator.

#### User Module

- [x] Register through registration page
- [x] Authentication & Authorization
  - [x] Social Login
    - [x] Google

##### Extras:

- [ ] Confirmation email upon registration
- [ ] Forgot Password
- [ ] Update Profile
  - [ ] Change username
  - [ ] Change password
  - [ ] Upload profile picture

---

#### Store Module

- [x] Add new store
- [x] View store details
- [x] Edit existing store
- [x] Delete store

##### Extras:

- [ ] Images upload for store

## Pages

### Public

- Home
- About Us
- Contact Us
- Registration
- Login
- Terms and Conditions

### Protected

- Store (CRUD)
- User Profile

## Prerequisites

- JDK 8 or higher
- MySQL (with a database named `db_kyn`)
- Node.js & npm (Node Package Manager)
- JWT

## Running Locally

Clone the repository and navigate to the folder

```
git clone https://github.com/sycDev/reactjs-springboot-know-your-neighborhood.git
cd reactjs-springboot-know-your-neighborhood
```

#### Database Configuration

The application uses MySQL as its primary database. You can configure the database connection properties in the 
`application.properties` file located in the `src/main/resources` directory:

```properties
# database
spring.datasource.url=jdbc:mysql://localhost:3306/db_kyn
spring.datasource.username=root
spring.datasource.password=admin
```

You can modify these properties to match your database configuration.

Note: Make sure you have MySQL installed and running on your machine before starting the application.
<br>
The database SQL script is located under the directory `src/main/resources/sql`.

Insert the default roles into the table `role`.
```sql
--- Insert role USER and ADMIN
INSERT INTO db_kyn.`role`(`role_id`, `role_name`) VALUES(1, "USER"), (2, "ADMIN");
```

#### Backend

Navigate to the `springboot-backend` folder then build and run the Spring Boot application

```bash
cd springboot-backend
./mvnw clean package
./mvnw spring-boot:run
```

#### Frontend

Launch another terminal and navigate to the `reactjs-frontend` folder
then install dependencies using npm and start the React development server

```bash
cd reactjs-frontend
npm install
npm start
```

To access the application, navigate to http://localhost:3000/ in your web browser.
