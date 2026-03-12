import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login.HashTable users = new Login.HashTable(10);

        // Add some sample users
        users.put("admin", "admin123");
        users.put("student1", "pass1");
        users.put("student2", "pass2");

        // Sample data for demonstration
        AttendanceAnalytics analytics = new AttendanceAnalytics();

        Student student1 = new Student("Alice", "101", "CSE");
        student1.addSubject("DSA");
        student1.addSubject("Java");
        student1.addAttendanceRecord(new AttendanceRecord("2023-10-01", "DSA", true));
        student1.addAttendanceRecord(new AttendanceRecord("2023-10-02", "Java", false));
        student1.addAttendanceRecord(new AttendanceRecord("2023-10-03", "DSA", true));

        Student student2 = new Student("Bob", "102", "CSE");
        student2.addSubject("DSA");
        student2.addSubject("Java");
        student2.addAttendanceRecord(new AttendanceRecord("2023-10-01", "DSA", true));
        student2.addAttendanceRecord(new AttendanceRecord("2023-10-02", "Java", true));
        student2.addAttendanceRecord(new AttendanceRecord("2023-10-03", "DSA", true));

        analytics.addStudent(student1);
        analytics.addStudent(student2);

        System.out.println("Welcome to PresenceIQ – Intelligent Attendance Analytics");

        boolean loggedIn = false;
        while (!loggedIn) {
            loggedIn = Login.authenticate(users, sc);
        }

        // Home Page (Dashboard)
        System.out.println("\n=== Dashboard ===");
        System.out.println("Total Students: " + analytics.getTotalStudents());
        System.out.println("Average Attendance: " + String.format("%.2f", analytics.getAverageAttendance()) + "%");

        // Show top performers
        List<Student> topPerformers = analytics.getTopPerformers(5);
        System.out.println("\nTop Performers:");
        for (Student s : topPerformers) {
            System.out.println(s);
        }

        // Profile Page (for demonstration, show first student)
        System.out.println("\n=== Profile Page ===");
        Student profileStudent = student1; // In real app, this would be the logged-in user
        System.out.println("Name: " + profileStudent.getName());
        System.out.println("Roll Number: " + profileStudent.getRollNumber());
        System.out.println("Branch: " + profileStudent.getBranch());
        System.out.println("Subjects: " + profileStudent.getSubjects());
        System.out.println("Total Classes: " + profileStudent.getTotalClasses());
        System.out.println("Present Classes: " + profileStudent.getPresentClasses());
        System.out.println("Attendance Percentage: " + String.format("%.2f", profileStudent.getAttendancePercentage()) + "%");

        System.out.println("\nAttendance Records:");
        for (AttendanceRecord record : profileStudent.getAttendanceRecords()) {
            System.out.println(record);
        }

        sc.close();
    }
}