# Project Setup and Configuration Guide

# Prerequisites
Before you start, make sure you have the following:

JDK 17+ installed on your machine.
Maven installed for dependency management.
MySQL or any other supported database installed and running (for persistence).
A Google Developer account for setting up OAuth2 credentials (Google login).



1. # Clone the Repository
   
To get started, clone the repository to your local machine.
git clone https://github.com/your-username/Role_Based_Access_Control.git

Change to the project directory:
cd Role_Based_Access_Control


2. # Set Up Google Cloud Project for OAuth2 Authentication

   
1. # Create a Google Cloud Project:
Go to the Google Cloud Console.
Sign in with your Google account and create a new project.
Name the project (e.g., OAuth2App).

2. # Enable the Google Identity API:
In the Google Cloud Console, go to APIs & Services > Library.
Search for Google Identity Services and enable it for your project.

3. # Create OAuth 2.0 Credentials:
In the APIs & Services > Credentials section, click Create Credentials and choose OAuth 2.0 Client ID.
Set the Application Type to Web Application.

Under Authorized JavaScript origins, add:
http://localhost:8080

Under Authorized Redirect URIs, add:
http://localhost:8080/login/oauth2/code/google
Click Create. You'll receive the Client ID and Client Secret.


2. # Configure the Application
   
1. Set Up Your Application Properties:
Open the src/main/resources/application.properties file and add the following configuration:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
spring.datasource.username=root
spring.datasource.password=yourpassword

# Google OAuth2 Configuration
spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET

Replace YOUR_GOOGLE_CLIENT_ID and YOUR_GOOGLE_CLIENT_SECRET with the credentials you received from the Google Developer Console.
Update the spring.datasource values to match your database connection settings.


3. # Database Setup
Ensure that you have a MySQL database (or another database of your choice) running and that you've created a database for your project. For example, create a database rbac.

sql
CREATE DATABASE rbac;


4. # Build and Run the Project
   
1. # Build the Project:
Use Maven to build the project. Run the following command in the root directory of the project:
mvn clean install

2. # Run the Application:
Once the build is complete, you can run the application using the following command:
mvn spring-boot:run
The application should now be running at http://localhost:8080.


5. # Test the OAuth2 Login with Google
Open your browser and go to http://localhost:8080.
Click on the "Login with Google" button.
Authenticate using your Google account.
Upon successful login, you should be redirected to the /welcome page (or the default page you configured).


6. # Additional Configuration
   
1. # Create a User in the Database:
After logging in with Google, the user details (such as email and name) will be saved to the database. You can inspect the database to verify the user details.

7. # Troubleshooting
If you face any issues during the setup, here are a few tips:

# OAuth2 Login Issues:
Ensure that your Redirect URI in Google Cloud Console matches exactly with the one in application.properties (http://localhost:8080/login/oauth2/code/google).

# Database Connection Issues:
Double-check the MySQL connection settings in application.properties. Ensure that the username, password, and database URL are correct.

# Google OAuth Errors:
If you get errors such as invalid_client, make sure the Client ID and Client Secret are correct and match what you generated in the Google Cloud Console.

# MySQL Issues:
Ensure that MySQL is running and the database exists. You can use MySQL Workbench or a similar tool to verify the connection and check for any errors.

8. # Conclusion
Now, your Spring Boot application is set up with Google OAuth2 login and is ready for use. You can extend the project by adding more features, such as user roles, admin management, and additional OAuth2 providers (like Facebook, GitHub, etc.).


# Features:
# Login with Google OAuth2:
Users can sign in with their Google account. Google’s OAuth2 services handle the authentication process, and once authenticated, users are redirected back to the application.

# User Registration and Authentication:
Users can register with an email and password, which are stored securely in the database. Passwords are hashed using BCrypt for security.
Once registered, users can log in with their credentials.

# Role Assignment:
After logging in, users are assigned a role (USER or ADMIN). The roles control access to various parts of the application.
Admins have more privileges, such as accessing user lists and modifying user roles.

# MySQL Database Integration:
The application uses MySQL to store user data, including email, password, and role information.
The application uses Spring Data JPA for database interaction.

# Role-Based Access Control (RBAC):
The application controls which resources a user can access based on their role.
The /list_user page is accessible only by users with the ADMIN role.

# Logout:
Users can log out, invalidating their session and redirecting them to the login page.



# Technology Stack:

# Backend:
Spring Boot: Main framework for building the application.
Spring Security: Provides authentication and authorization features.
Spring Data JPA: Simplifies database interaction and CRUD operations.
MySQL: Relational database for user and role data storage.
JWT (JSON Web Token): (Optional) used for securing API endpoints.
BCrypt: For securely hashing user passwords.
OAuth2 Authentication:

# Google OAuth2: 
Enables Google authentication via OAuth2 for users to log in using their Google account.

# Frontend:
JSP (Java Server Pages): For rendering dynamic content (e.g., login page, user list).
HTML/CSS: For basic page structure and styling.
Spring Tag Libraries: For dynamic content rendering in the views.


# How It Works:

# User Registration:
A user can sign up via the registration page, where they provide their email, password, and role. The email and password are saved in the database, and the password is hashed using BCrypt for security.

# User Login:
# Users can log in with either:
A Google account via OAuth2 authentication.
A traditional login with their registered credentials (email and password).


# Authentication Process:
Upon login, the application verifies the credentials. If they match, the user is authenticated and a session is created.
Google users are authenticated through OAuth2, and the user data is fetched from Google’s APIs to store in the database.

# Role Management:
After authentication, users are assigned a role (USER or ADMIN). This determines their access to different sections of the application.
ADMIN users have access to the /list_user page, where they can view and manage other users, while regular USER accounts have limited access.

# Google OAuth2:
Users can sign in using their Google credentials, which are authenticated through Google’s OAuth2 system.
Upon successful authentication, the user’s details (such as name and email) are saved to the database.

# Access Control:
The application uses Spring Security to restrict access to certain endpoints based on the user’s role.
For example, /list_user is accessible only by users with the ADMIN role. Regular users are redirected to an error page or home page if they try to access restricted resources.

# Database Schema:
The database stores user details, including:
User Table:
id (Primary Key)
email (Unique)
password (Hashed)
role (e.g., USER, ADMIN)


# Security Considerations:

# Password Hashing: 
User passwords are securely hashed using BCrypt before being stored in the database.

# OAuth2 Authentication: 
The application integrates Google OAuth2 for secure third-party authentication.

# Role-Based Access Control (RBAC): 
Users are assigned roles (USER, ADMIN), and access to specific resources is restricted based on roles.
# JWT: 
JWT tokens can be used for securing API endpoints, ensuring only authenticated users can access them.
