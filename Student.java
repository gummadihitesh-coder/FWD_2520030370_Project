import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String rollNumber;
    private String branch;
    private List<AttendanceRecord> attendanceRecords;
    private List<String> subjects;

    public Student(String name, String rollNumber, String branch) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.attendanceRecords = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getBranch() {
        return branch;
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void addAttendanceRecord(AttendanceRecord record) {
        attendanceRecords.add(record);
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void addSubject(String subject) {
        subjects.add(subject);
    }

    public int getTotalClasses() {
        return attendanceRecords.size();
    }

    public int getPresentClasses() {
        int present = 0;
        for (AttendanceRecord record : attendanceRecords) {
            if (record.isPresent()) {
                present++;
            }
        }
        return present;
    }

    public double getAttendancePercentage() {
        if (attendanceRecords.isEmpty()) return 0.0;
        return (double) getPresentClasses() / getTotalClasses() * 100;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll: " + rollNumber + ", Branch: " + branch +
               ", Attendance: " + String.format("%.2f", getAttendancePercentage()) + "%";
    }
}