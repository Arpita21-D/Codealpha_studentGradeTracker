import java.util.ArrayList;

public class GradeTracker {
    private final ArrayList<Student> students;

    public GradeTracker() {
        this.students = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }

        for (Student existingStudent : students) {
            if (existingStudent.getStudentId().equalsIgnoreCase(student.getStudentId())) {
                return false;
            }
        }

        students.add(student);
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public Student searchStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students available yet.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("ALL STUDENTS SUMMARY");
        System.out.println("========================================");

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("========================================\n");
    }

    public void displaySummaryReport() {
        if (students.isEmpty()) {
            System.out.println("\nNo student records available to generate a summary report.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("OVERALL STUDENT REPORT");
        System.out.println("========================================");
        System.out.println("ID        NAME             TOTAL    AVG     HIGHEST LOWEST GRADE");

        for (Student student : students) {
            System.out.printf("%-9s %-15s %-8d %-7.2f %-7d %-6d %-5s%n",
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getTotalMarks(),
                    student.getAverageMarks(),
                    student.getHighestMark(),
                    student.getLowestMark(),
                    student.getGrade());
        }

        System.out.println("========================================\n");
    }

    public void displayStudentReport(String studentId) {
        Student student = searchStudentById(studentId);
        if (student == null) {
            System.out.println("\nStudent with ID '" + studentId + "' was not found.");
            return;
        }

        String[] subjectNames = {"Mathematics", "Science", "English", "History", "Computer Science"};
        int[] marks = student.getMarks();

        System.out.println("\n========================================");
        System.out.println("STUDENT REPORT");
        System.out.println("========================================");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Student Name: " + student.getStudentName());
        System.out.println("----------------------------------------");

        for (int i = 0; i < subjectNames.length; i++) {
            System.out.println(subjectNames[i] + ": " + marks[i]);
        }

        System.out.println("----------------------------------------");
        System.out.println("Total Marks: " + student.getTotalMarks());
        System.out.println("Average Marks: " + String.format("%.2f", student.getAverageMarks()));
        System.out.println("Highest Mark: " + student.getHighestMark());
        System.out.println("Lowest Mark: " + student.getLowestMark());
        System.out.println("Grade: " + student.getGrade());
        System.out.println("========================================\n");
    }
}
