import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AttendanceAnalytics {
    private List<Student> students;

    public AttendanceAnalytics() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public int getTotalStudents() {
        return students.size();
    }

    public double getAverageAttendance() {
        if (students.isEmpty()) return 0.0;
        double sum = 0.0;
        for (Student s : students) {
            sum += s.getAttendancePercentage();
        }
        return sum / students.size();
    }

    public List<Student> getTopPerformers(int n) {
        List<Student> copy = new ArrayList<>(students);
        Collections.sort(copy, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return Double.compare(b.getAttendancePercentage(), a.getAttendancePercentage());
            }
        });
        if (n > copy.size()) n = copy.size();
        return copy.subList(0, n);
    }
}