package jdbcfirstprogram;

import java.sql.*;
import java.util.Scanner;

public class StudentManagementPrepared {

    static String url = "jdbc:mysql://localhost:3306/school";
    static String user = "root";
    static String password = "HOnest617*";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> searchStudent(sc);
                case 4 -> updateMarks(sc);
                case 5 -> deleteStudent(sc);
                case 6 -> {
                    System.out.println("Thank you, Jancy! 👋");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // 1️⃣ Add Student
    static void addStudent(Scanner sc) {
        String sql = "INSERT INTO students (name, age, mark1, mark2, mark3, average, grade) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();

            System.out.print("Enter Mark1: ");
            int m1 = sc.nextInt();
            System.out.print("Enter Mark2: ");
            int m2 = sc.nextInt();
            System.out.print("Enter Mark3: ");
            int m3 = sc.nextInt();

            int avg = (m1 + m2 + m3) / 3;
            String grade = (avg >= 90) ? "A" :
                           (avg >= 75) ? "B" :
                           (avg >= 60) ? "C" : "D";

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, m1);
            ps.setInt(4, m2);
            ps.setInt(5, m3);
            ps.setInt(6, avg);
            ps.setString(7, grade);

            ps.executeUpdate();
            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2️⃣ View All Students
    static void viewStudents() {
        String sql = "SELECT * FROM students";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\nID Name Age M1 M2 M3 Avg Grade");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getInt("age") + " " +
                        rs.getInt("mark1") + " " +
                        rs.getInt("mark2") + " " +
                        rs.getInt("mark3") + " " +
                        rs.getInt("average") + " " +
                        rs.getString("grade")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ Search Student by ID ⭐
    static void searchStudent(Scanner sc) {
        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nStudent Found:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Marks: " +
                        rs.getInt("mark1") + ", " +
                        rs.getInt("mark2") + ", " +
                        rs.getInt("mark3"));
                System.out.println("Average: " + rs.getInt("average"));
                System.out.println("Grade: " + rs.getString("grade"));
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4️⃣ Update Marks
    static void updateMarks(Scanner sc) {
        String sql = "UPDATE students SET mark1=?, mark2=?, mark3=?, average=?, grade=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Mark1: ");
            int m1 = sc.nextInt();
            System.out.print("Enter New Mark2: ");
            int m2 = sc.nextInt();
            System.out.print("Enter New Mark3: ");
            int m3 = sc.nextInt();

            int avg = (m1 + m2 + m3) / 3;
            String grade = (avg >= 90) ? "A" :
                           (avg >= 75) ? "B" :
                           (avg >= 60) ? "C" : "D";

            ps.setInt(1, m1);
            ps.setInt(2, m2);
            ps.setInt(3, m3);
            ps.setInt(4, avg);
            ps.setString(5, grade);
            ps.setInt(6, id);

            ps.executeUpdate();
            System.out.println("Marks updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5️⃣ Delete Student
    static void deleteStudent(Scanner sc) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.print("Enter Student ID to delete: ");
            int id = sc.nextInt();

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Student deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
