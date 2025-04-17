# Gym Management System Documentation

## User Documentation

### 1. Overview of the Application

The Gym Management System is a software solution designed to manage the operations of a gym. The system allows users to perform various tasks such as registering, logging in, purchasing memberships, managing workout classes, and tracking revenue. The system is built with different user roles, including Admin, Trainer, and Member, each with distinct functionalities.

- **Admin**: Has full access to the system, including viewing all users, deleting users, and viewing total revenue.
- **Trainer**: Can manage workout classes (add, update, delete, view) and also purchase memberships for themselves or others.
- **Member**: Can view available workout classes, purchase memberships, and track their own membership status.

### 2. Explanation of All Classes and Their Interactions

Here is a breakdown of the core classes and how they interact:

- **User**: The base class that represents all users in the system. It includes properties such as username, password, email, phone number, address, and role. It is inherited by **Admin**, **Trainer**, and **Member** classes.
  
- **Admin, Trainer, Member**: These are subclasses of the User class, each with specific roles. The Admin can manage users and view revenue, the Trainer manages workout classes, and the Member views and purchases memberships.

- **UserDAO**: This class handles the database interactions for user-related operations like registration, login, and user retrieval.

- **UserService**: Provides the business logic for user operations such as registration and login. It interacts with the UserDAO class to perform CRUD operations.

- **WorkoutClass**: Represents a workout class in the gym. It has properties like class name, description, and trainer ID.

- **WorkoutClassDAO**: Manages the database interactions for workout class-related operations.

- **WorkoutClassService**: Contains the logic for managing workout classes. It communicates with the WorkoutClassDAO for CRUD operations.

- **Membership**: Represents a gym membership that a member can purchase. It contains details like membership type, description, and cost.

- **MembershipDAO**: Responsible for database interactions related to memberships, including adding new memberships and calculating revenue.

- **MembershipService**: Handles business logic for purchasing memberships and viewing membership details.

### 3. Class Diagram

The class diagram illustrates the relationships between these classes. The User class is the parent class, and the Admin, Trainer, and Member classes inherit from it. Other classes like WorkoutClass, Membership, and their corresponding service and DAO classes interact with the User class indirectly.

         +-----------------------------+
         |           User             |
         +-----------------------------+
         | - id: int                  |
         | - username: String         |
         | - password: String         |
         | - email: String            |
         | - phoneNumber: String      |
         | - address: String          |
         | - role: String             |
         +-----------------------------+
              /     |     \
             /      |      \
        Trainer   Member   Admin



### 4. Instructions on How to Start and Use the System

1. **Installation**: 
   - Clone the project repository from GitHub.
   - Set up a MySQL database and import the schema to create necessary tables (Users, WorkoutClasses, Memberships).
   - Configure the database connection in the application.

2. **Running the Application**:
   - Ensure you have Java 8 or higher installed.
   - Run the Main class to start the Gym Management System.

3. **Using the Application**:
   - Upon running the system, you'll be prompted to either register or log in.
   - Admins can manage users and view revenue.
   - Trainers can manage workout classes and purchase memberships.
   - Members can view workout classes and purchase memberships.

## Development Documentation

### 1. Javadoc Documentation

Key methods and classes in the system have Javadoc comments for better understanding. Here is an example for the UserService class:

java
/**
 * UserService handles user registration, login, and other user-related operations.
 */
public class UserService {
    
    private final UserDAO userDAO = new UserDAO();

    /**
     * Registers a new user.
     *
     * @param user The user object to register.
     * @return true if the user is successfully registered, false otherwise.
     */
    public boolean register(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return userDAO.registerUser(user);
    }

    /**
     * Logs in a user using email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The user object if login is successful, null otherwise.
     */
    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}


### 2. Project Directory Structure Explanation

**Entry Point:**
- Main.java: Handles login, registration, and role-based menu navigation.

**Models:**
- User.java
- WorkoutClass.java
- Membership.java

**Data Access Layer (DAO):**
- UserDAO.java
- WorkoutClassDAO.java
- MembershipDAO.java

**Service Layer:**
- UserService.java
- WorkoutClassService.java
- MembershipService.java

**Database Connection:**
- DatabaseConnection.java: Central class for establishing DB connections.


### 3. Build Process and Dependencies Used

- **Build Process**: The project can be built using a Java IDE like IntelliJ or VS Code, or by using Maven/Gradle.
- **Dependencies**:
  - BCrypt for password hashing.
  - MySQL JDBC driver for database connectivity.

### 4. How to Set Up the Database for Development

1. Create a MySQL database and import the necessary schema provided in the repository.
2. Configure the database connection in the DatabaseConnection class.

### 5. How to Clone and Run the Project from GitHub

1. Clone the repository:
   
bash
   git clone https://github.com/PrincessBz/Gym-Management-System.git

2. Navigate into the project directory:
   
bash
   cd gym-management

3. Run the project using an IDE or via the command line using Maven/Gradle.

## Individual Report

- **Contributions**:
  - Developed the user registration, login, and role-based menu functionalities.
  - Implemented CRUD operations for workout classes and memberships.
  - Created service classes to encapsulate business logic and DAO classes for database interactions.

- **Challenges**:
  - Ensuring secure password storage and matching using BCrypt.
  - Handling different user roles (Admin, Trainer, Member) and ensuring correct access to features.


Thank You
