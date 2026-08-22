import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String[] SUBJECTS = {
        "Mathematics",
        "Science",
        "English",
        "History",
        "Computer Science"
    };

    public static void main(String[] args) {
        GradeTracker gradeTracker = new GradeTracker();

        System.out.println("========================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("========================================");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. View Student Report");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    addStudent(scanner, gradeTracker);
                    break;

                case 2:
                    gradeTracker.displayAllStudents();
                    break;

                case 3:
                    searchStudent(scanner, gradeTracker);
                    break;

                case 4:
                    viewStudentReport(scanner, gradeTracker);
                    break;

                case 5:
                    System.out.println("\nThank you for using the Student Grade Tracker. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid menu choice.");
            }
        }

        scanner.close();
    }

    private static void addStudent(
            Scanner scanner,
            GradeTracker gradeTracker) {

        System.out.print("\nEnter Student ID: ");
        String studentId = scanner.nextLine().trim();

        if (studentId.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return;
        }

        if (gradeTracker.searchStudentById(studentId) != null) {
            System.out.println("A student with this ID already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine().trim();

        if (studentName.isEmpty()) {
            System.out.println("Student Name cannot be empty.");
            return;
        }

        int[] marks = new int[SUBJECTS.length];

        for (int i = 0; i < SUBJECTS.length; i++) {
            marks[i] = readMark(scanner, SUBJECTS[i]);
        }

        Student student =
            new Student(studentId, studentName, marks);

        gradeTracker.addStudent(student);

        System.out.println("\nStudent added successfully!");
    }

    private static int readMark(
            Scanner scanner,
            String subjectName) {

        while (true) {
            System.out.print(
                "Enter mark for " + subjectName + " (0-100): "
            );

            try {
                int mark = Integer.parseInt(scanner.nextLine());

                if (mark >= 0 && mark <= 100) {
                    return mark;
                }

                System.out.println(
                    "Please enter a mark between 0 and 100."
                );

            } catch (NumberFormatException e) {
                System.out.println(
                    "Please enter a valid number."
                );
            }
        }
    }

    private static void searchStudent(
            Scanner scanner,
            GradeTracker gradeTracker) {

        System.out.print("\nEnter Student ID to search: ");
        String id = scanner.nextLine().trim();

        Student student =
            gradeTracker.searchStudentById(id);

        if (student == null) {
            System.out.println(
                "Student with ID '" + id + "' was not found."
            );
        } else {
            System.out.println("\nStudent found:");
            System.out.println(student);
        }
    }

    private static void viewStudentReport(
            Scanner scanner,
            GradeTracker gradeTracker) {

        System.out.print("\nEnter Student ID: ");
        String id = scanner.nextLine().trim();

        gradeTracker.displayStudentReport(id);
    }
}


/* ================================
   STUDENT CLASS
   ================================ */

class Student {

    private String studentId;
    private String studentName;
    private int[] marks;

    public Student(
            String studentId,
            String studentName,
            int[] marks) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.marks = marks;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int[] getMarks() {
        return marks;
    }

    public double calculateAverage() {

        int total = 0;

        for (int mark : marks) {
            total += mark;
        }

        return (double) total / marks.length;
    }

    public String getGrade() {

        double average = calculateAverage();

        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {

        return "Student ID: " + studentId +
               "\nStudent Name: " + studentName +
               "\nAverage: " +
               String.format("%.2f", calculateAverage()) +
               "\nGrade: " + getGrade();
    }
}


/* ================================
   GRADE TRACKER CLASS
   ================================ */

class GradeTracker {

    private ArrayList<Student> students;

    public GradeTracker() {
        students = new ArrayList<Student>();
    }

    public boolean addStudent(Student student) {

        if (searchStudentById(student.getStudentId()) != null) {
            return false;
        }

        students.add(student);
        return true;
    }

    public Student searchStudentById(String id) {

        for (Student student : students) {

            if (student.getStudentId().equalsIgnoreCase(id)) {
                return student;
            }
        }

        return null;
    }

    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }

        System.out.println("\n========== ALL STUDENTS ==========");

        for (Student student : students) {
            System.out.println("\n" + student);
        }
    }

    public void displayStudentReport(String id) {

        Student student = searchStudentById(id);

        if (student == null) {
            System.out.println(
                "\nStudent with ID '" + id + "' was not found."
            );
            return;
        }

        System.out.println("\n========== STUDENT REPORT ==========");

        System.out.println("Student ID: " +
            student.getStudentId());

        System.out.println("Student Name: " +
            student.getStudentName());

        System.out.println("\nMarks:");

        int[] marks = student.getMarks();

        String[] subjects = {
            "Mathematics",
            "Science",
            "English",
            "History",
            "Computer Science"
        };

        for (int i = 0; i < subjects.length; i++) {
            System.out.println(
                subjects[i] + ": " + marks[i]
            );
        }

        System.out.println(
            "\nAverage: " +
            String.format("%.2f", student.calculateAverage())
        );

        System.out.println(
            "Grade: " + student.getGrade()
        );
    }
}