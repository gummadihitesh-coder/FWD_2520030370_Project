public class AttendanceRecord {
    private String date;
    private String subject;
    private boolean present;

    public AttendanceRecord(String date, String subject, boolean present) {
        this.date = date;
        this.subject = subject;
        this.present = present;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isPresent() {
        return present;
    }

    @Override
    public String toString() {
        return date + " - " + subject + " - " + (present ? "Present" : "Absent");
    }
}