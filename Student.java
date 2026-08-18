public class Student {
    private String studentId;
    private String studentName;
    private int[] marks;

    public Student(String studentId, String studentName, int[] marks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.marks = marks;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public int getTotalMarks() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public double getAverageMarks() {
        if (marks == null || marks.length == 0) {
            return 0.0;
        }
        return getTotalMarks() / (double) marks.length;
    }

    public int getHighestMark() {
        int highest = marks[0];
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] > highest) {
                highest = marks[i];
            }
        }
        return highest;
    }

    public int getLowestMark() {
        int lowest = marks[0];
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] < lowest) {
                lowest = marks[i];
            }
        }
        return lowest;
    }

    public String getGrade() {
        double average = getAverageMarks();

        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + " | Name: " + studentName +
                " | Total: " + getTotalMarks() + " | Average: " + String.format("%.2f", getAverageMarks()) +
                " | Highest: " + getHighestMark() + " | Lowest: " + getLowestMark() + " | Grade: " + getGrade();
    }
}
