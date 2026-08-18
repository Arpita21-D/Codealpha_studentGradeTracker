# 🎓 Student Grade Tracker

<p align="center">
  <strong>📚 CodeAlpha Internship – Task 1</strong>
</p>

<p align="center">
  A Java-based Student Grade Tracker for managing student records,
  marks, grades, and performance reports.
</p>

---

## 🌟 Features

- 🆔 Store student ID and student name
- 📝 Record marks for 5 subjects
- 📚 Use ArrayList for student storage
- 🧮 Calculate total marks and average marks
- 📈 Find highest and lowest marks
- 🏆 Determine final grade using the grading scale
- 👥 View all student records
- 🔍 Search students by Student ID
- 📊 View a detailed report for a specific student
- ⚠️ Validate invalid input values
- 🛡️ Handle errors safely without crashing

---

## 🏆 Grade System

| Marks | Grade |
|------:|:-----:|
| 90–100 | 🟢 A+ |
| 80–89 | 🔵 A |
| 70–79 | 🟣 B |
| 60–69 | 🟡 C |
| 50–59 | 🟠 D |
| Below 50 | 🔴 F |

---

## 💻 Technologies Used

| Technology | Purpose |
|---|---|
| ☕ Java SE | Main programming language |
| ⌨️ Standard Console I/O | User input and output |
| 📋 ArrayList | Student data storage |
| 🧩 OOP | Object-oriented program design |

---

## 📁 Project Structure

```text
StudentGradeTracker/
│
├── Main.java
├── Student.java
├── GradeTracker.java
└── README.md
## 🖥️ Sample Output

```text
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