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
        printHeader();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                int choice = readMenuChoice(scanner);

                switch (choice) {
                    case 1 -> addStudent(scanner, gradeTracker);
                    case 2 -> gradeTracker.displayAllStudents();
                    case 3 -> searchStudent(scanner, gradeTracker);
                    case 4 -> viewStudentReport(scanner, gradeTracker);
                    case 5 -> {
                        System.out.println("\nThank you for using the Student Grade Tracker. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("\nInvalid menu choice. Please select a valid option.");
                }
            }
        }
    }

    private static void printHeader() {
        System.out.println("========================================");
        System.out.println("      STUDENT GRADE TRACKER");
        System.out.println("========================================");
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. View Student Report");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    private static int readMenuChoice(Scanner scanner) {
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addStudent(Scanner scanner, GradeTracker gradeTracker) {
        System.out.print("\nEnter Student ID: ");
        String studentId = readRequiredText(scanner, "Student ID");

        if (gradeTracker.searchStudentById(studentId) != null) {
            System.out.println("A student with ID '" + studentId + "' already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String studentName = readRequiredText(scanner, "Student Name");

        int[] marks = new int[5];
        for (int i = 0; i < SUBJECTS.length; i++) {
            marks[i] = readMark(scanner, SUBJECTS[i]);
        }

        Student student = new Student(studentId, studentName, marks);
        boolean added = gradeTracker.addStudent(student);

        if (added) {
            System.out.println("\nStudent added successfully!");
        } else {
            System.out.println("\nUnable to add student. Please try again.");
        }
    }

    private static void searchStudent(Scanner scanner, GradeTracker gradeTracker) {
        System.out.print("\nEnter Student ID to search: ");
        String studentId = scanner.nextLine().trim();

        Student student = gradeTracker.searchStudentById(studentId);
        if (student == null) {
            System.out.println("\nStudent with ID '" + studentId + "' was not found.");
            return;
        }

        System.out.println("\nStudent found:");
        System.out.println(student);
    }

    private static void viewStudentReport(Scanner scanner, GradeTracker gradeTracker) {
        System.out.print("\nEnter Student ID to view report: ");
        String studentId = scanner.nextLine().trim();
        gradeTracker.displayStudentReport(studentId);
    }

    private static String readRequiredText(Scanner scanner, String fieldName) {
        while (true) {
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.print(fieldName + " cannot be empty. Please enter again: ");
        }
    }

    private static int readMark(Scanner scanner, String subjectName) {
        while (true) {
            System.out.print("Enter mark for " + subjectName + " (0-100): ");
            String input = scanner.nextLine().trim();

            try {
                int mark = Integer.parseInt(input);
                if (mark >= 0 && mark <= 100) {
                    return mark;
                }
                System.out.println("Invalid mark. Please enter a value between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number entered. Please enter a valid integer value.");
            }
        }
    }
}
