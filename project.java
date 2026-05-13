import java.io.*;
import java.util.*;

class Student {
    int id;
    String name;
    String department;
    double gpa;

    Student(int id, String name, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gpa = gpa;
    }
}

public class SecureStudentManagementSystem {

    static Scanner input = new Scanner(System.in);

    // Simple login security
    static boolean login() {

        String username = "studentadmin";
        String password = "gebremedhn2026";

        System.out.print("Username: ");
        String user = input.nextLine();

        System.out.print("Password: ");
        String pass = input.nextLine();

        if(user.equals(username) && pass.equals(password)) {
            return true;
        }

        return false;
    }

    // Save student to local file
    static void saveStudent(Student s) {

        try {
            FileWriter fw = new FileWriter("students.txt", true);

            fw.write(
                s.id + "," +
                s.name + "," +
                s.department + "," +
                s.gpa + "\n"
            );

            fw.close();

        } catch(Exception e) {
            System.out.println("can't svae the file.");
        }
    }

    // Display all students
    static void viewStudents() {

        try {
            File file = new File("students.txt");

            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }

            reader.close();

        } catch(Exception e) {
            System.out.println("No records found.");
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Secure Student Management System ===");

        if(!login()) {
            System.out.println("Access Denied!");
            return;
        }

        int choice;

        do {

            System.out.println("\n1. Add Student");
            System.out.println("2. View all Students");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            choice = input.nextInt();

            input.nextLine();

            switch(choice) {

                case 1:

                    System.out.print("Student ID: ");
                    int id = input.nextInt();

                    input.nextLine();

                    System.out.print("Student Name: ");
                    String name = input.nextLine();

                    System.out.print("Department: ");
                    String dept = input.nextLine();

                    System.out.print("GPA: ");
                    double gpa = input.nextDouble();

                    Student s = new Student(id, name, dept, gpa);

                    saveStudent(s);

                    System.out.println("Student saved successfully.");

                    break;

                case 2:

                    viewStudents();

                    break;

                case 3:

                    System.out.println("System Closed.");

                    break;

                default:

                    System.out.println("Invalid choice.");
            }

        } while(choice != 3);
    }
}