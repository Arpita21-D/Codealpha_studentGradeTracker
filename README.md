# Student Grade Tracker

A Java console-based application designed for CodeAlpha Internship Task 1. It helps manage student records, track marks across five subjects, calculate academic performance, and generate student reports.

## Project Description
This project allows users to add multiple students, store their unique IDs and names, enter five subject marks, and calculate their total, average, highest and lowest marks, as well as their grade. The program provides a menu-driven interface for interacting with the student database in a simple and efficient way.

## Features
- Add multiple students
- Store student ID and student name
- Record marks for 5 subjects
- Use ArrayList for student storage
- Calculate total marks, average marks, highest mark, and lowest mark
- Determine final grade using the given grading scale
- View all student records
- Search students by Student ID
- View a detailed report for a specific student
- Input validation for invalid values and menu choices
- Safe handling of errors without crashing

## Grade System
- 90-100 = A+
- 80-89 = A
- 70-79 = B
- 60-69 = C
- 50-59 = D
- Below 50 = F

## Technologies Used
- Java SE
- Standard Console I/O
- ArrayList
- Object-Oriented Programming

## Project Structure
```text
StudentGradeTracker/
├── Main.java
├── Student.java
├── GradeTracker.java
├── README.md
```

## How to Run
1. Open a terminal in the project folder.
2. Compile the Java files:
   ```bash
   javac Main.java Student.java GradeTracker.java
   ```
3. Run the application:
   ```bash
   java Main
   ```

## Sample Output
```text
========================================
      STUDENT GRADE TRACKER
========================================

Menu:
1. Add Student
2. View All Students
3. Search Student
4. View Student Report
5. Exit
Select an option: 1

Enter Student ID: S101
Enter Student Name: Alice Johnson
Enter mark for Mathematics (0-100): 90
Enter mark for Science (0-100): 85
Enter mark for English (0-100): 88
Enter mark for History (0-100): 80
Enter mark for Computer Science (0-100): 92

Student added successfully!
```

## Future Improvements
- Add file-based persistence using text files or databases
- Implement editing and deleting student records
- Add sorting by student name or grade
- Include subject-wise statistics and class performance summary
- Upgrade the interface with GUI or web-based version

## Author
Arpita Desai
