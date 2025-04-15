package com;
    import java.sql.SQLException;
    import java.util.Scanner;

    import com.gym.service.MembershipService;
    import com.gym.service.UserService;
    import com.gym.service.WorkoutClassService;
    import com.gym.user.MemberShip;
    import com.gym.user.User;
    import com.gym.user.WorkoutClass;

    public class Main {
        private static final Scanner scanner = new Scanner(System.in);
        private static final UserService userService = new UserService();
        private static final WorkoutClassService workoutClassService = new WorkoutClassService();
        private static final MembershipService membershipService = new MembershipService();


        public static void main(String[] args) {
            System.out.println("Welcome to the Gym Management System!");
            System.out.println("Please select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            scanner.close();
        }

        private static void registerUser() {
            System.out.println("Registering a new user:");
            System.out.println("----------------------");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter address: ");
            String address = scanner.nextLine();
            System.out.print("Enter role (Admin/Trainer/Member):  ");
            String role = scanner.nextLine();
            System.out.println("----------------------");

            User user = new User(0, username, password, email, phoneNumber, address, role);
            boolean success = userService.register(user);
            if (success) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("Failed to register user.");
            }
        }

        private static void loginUser() {
            System.out.println("Logging in as an existing user:");
            System.out.println("----------------------");
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            System.out.println("----------------------");
        


            User user = userService.login(email, password); 
            if (user != null) {
                System.out.println(" Login successful! Welcome, " + user.getUsername());
                switch (user.getRole()) {
                    case "Admin":
                        AdminMenu(user);
                        break;
                    case "Trainer":
                        try {
                            TrainerMenu(user);
                        } catch (SQLException e) {
                            System.out.println("Error occurred while accessing Trainer Menu: " + e.getMessage());
                        }
                        break;
                    case "Member":
                        try {
                            MemberMenu(user);
                        } catch (SQLException e) {
                            System.out.println("Error occurred while accessing Member Menu: " + e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid role.");
                }
            } else {
                System.out.println("Invalid username or password.");
            }
            }

        private static void AdminMenu(User Admin) {
            while(true){
            System.out.println("\n(U+1F464) Admin Menu:");
            System.out.println("1. Get all users");
            System.out.println("2. Delete user");
            System.out.println("3. View all Memberships and Revenue");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.getAllUsers();
                    break;
                case "2":
                    System.out.print("Enter the ID of the user to delete: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); 
                    userService.deleteUser(userId);
                    break;
                case "3":
                    membershipService.viewTotalRevenue();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    AdminMenu(Admin);
            }
            }
        }

        

        private static void TrainerMenu(User trainer) throws SQLException {
        while (true) {
            System.out.println("\n(U+1F4AA) Trainer Menu:");
            System.out.println("1. Add Workout Class");
            System.out.println("2. Update Workout Class");
            System.out.println("3. Delete Workout Class");
            System.out.println("4. View All Workout Classes");
            System.out.println("5. Purchase Membership");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
           

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                System.out.println("Enter workout class details:");
                    System.out.print("Class Name: ");
                    String className = scanner.nextLine();
                    System.out.print("Class Description: ");
                    String classDescription = scanner.nextLine();
                

                    WorkoutClass workoutClass = new WorkoutClass(0, className, classDescription, trainer.getId());
                    try {
                        workoutClassService.addClass(workoutClass);
                    } catch (SQLException e) {
                        System.out.println("Error adding workout class: " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Enter workout class details to update:");
                    System.out.print("Class ID: ");
                    int classId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Updated Class Name: ");
                    String updatedClassName = scanner.nextLine();
                    System.out.print("Updated Class Description: ");
                    String updatedClassDescription = scanner.nextLine();

                    WorkoutClass updatedWorkoutClass = new WorkoutClass(classId, updatedClassName, updatedClassDescription, trainer.getId());
                    try {
                        workoutClassService.updateWorkoutClass(updatedWorkoutClass);
                    } catch (SQLException e) {
                        System.out.println("Error updating workout class: " + e.getMessage());
                    }
                    break;
                case "3":
                    deleteClass();
                    break;
                case "4":
                    workoutClassService.viewAllClasses();
                    break;
                case "5":
                    purchasemembership(trainer);
                    break;
                case "6":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                        TrainerMenu(trainer);             
                }  
        }
        }
    
        private static void deleteClass() {
            System.out.print("Enter the ID of the workout class to delete: ");
            int classId = scanner.nextInt();
            scanner.nextLine(); 
            try {
                workoutClassService.deleteClass(classId);
            } catch (SQLException e) {
                System.out.println("Error deleting workout class: " + e.getMessage());
            }
        }
        

        public static void MemberMenu(User member) throws SQLException {
        while (true) {
            System.out.println("\n(U+1F3CB) Member Menu:");
            System.out.println("1. View Workout Classes");
            System.out.println("2. Purchase Membership");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            scanner.nextLine(); 

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    workoutClassService.viewAllClasses();
                    break;
                case "2":
                    purchasemembership(member);
                    break;
                case "3":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            }
        }
    private static void purchasemembership(User member) throws SQLException {
    MemberShip membership = new MemberShip(member.getId(), "Basic Plan", "Active", 50.0, 12);
    membershipService.purchasemembership(membership);
    System.out.println("Membership purchased successfully!");
    }



    }
            



        