const STORAGE_KEY = "studentGradeTrackerData";
const SUBJECTS = [
  { key: "maths", label: "Mathematics" },
  { key: "science", label: "Science" },
  { key: "english", label: "English" },
  { key: "history", label: "History" },
  { key: "computerScience", label: "Computer Science" },
];

let students = loadStudents();

const elements = {
  totalStudentsCard: document.getElementById("totalStudentsCard"),
  averageGradeCard: document.getElementById("averageGradeCard"),
  highestGradeCard: document.getElementById("highestGradeCard"),
  lowestGradeCard: document.getElementById("lowestGradeCard"),
  searchInput: document.getElementById("searchInput"),
  addStudentBtn: document.getElementById("addStudentBtn"),
  emptyAddBtn: document.getElementById("emptyAddBtn"),
  studentFormPanel: document.getElementById("studentFormPanel"),
  studentForm: document.getElementById("studentForm"),
  formTitle: document.getElementById("formTitle"),
  cancelBtn: document.getElementById("cancelBtn"),
  tableBody: document.getElementById("studentTableBody"),
  emptyState: document.getElementById("emptyState"),
  reportCards: document.getElementById("reportCards"),
  viewModal: document.getElementById("viewModal"),
  closeModalBtn: document.getElementById("closeModalBtn"),
  studentReportContent: document.getElementById("studentReportContent"),
  studentId: document.getElementById("studentId"),
  studentName: document.getElementById("studentName"),
  maths: document.getElementById("maths"),
  science: document.getElementById("science"),
  english: document.getElementById("english"),
  history: document.getElementById("history"),
  computerScience: document.getElementById("computerScience"),
};

function loadStudents() {
  const saved = localStorage.getItem(STORAGE_KEY);
  if (!saved) {
    return [
      {
        studentId: "S101",
        studentName: "Alice Johnson",
        maths: 90,
        science: 85,
        english: 88,
        history: 80,
        computerScience: 92,
      },
      {
        studentId: "S102",
        studentName: "Daniel Lee",
        maths: 72,
        science: 68,
        english: 75,
        history: 80,
        computerScience: 74,
      },
      {
        studentId: "S103",
        studentName: "Priya Shah",
        maths: 55,
        science: 60,
        english: 52,
        history: 58,
        computerScience: 63,
      },
    ];
  }

  try {
    const parsed = JSON.parse(saved);
    return Array.isArray(parsed) ? parsed : [];
  } catch {
    return [];
  }
}

function saveStudents() {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(students));
}

function getStudentMetrics(student) {
  const marks = SUBJECTS.map((subject) => Number(student[subject.key]));
  const total = marks.reduce((sum, mark) => sum + mark, 0);
  const average = marks.length ? total / marks.length : 0;
  const highest = Math.max(...marks, 0);
  const lowest = marks.length ? Math.min(...marks) : 0;
  const grade = getGradeLetter(average);
  const status = getStatus(average);

  return { total, average, highest, lowest, grade, status };
}

function getGradeLetter(average) {
  if (average >= 90) return "A+";
  if (average >= 80) return "A";
  if (average >= 70) return "B";
  if (average >= 60) return "C";
  if (average >= 50) return "D";
  return "F";
}

function getStatus(average) {
  if (average >= 80) return "Pass";
  if (average >= 50) return "Warning";
  return "Fail";
}

function getBadgeClass(grade) {
  if (grade === "A+" || grade === "A") {
    return "excellent";
  }
  if (grade === "B") {
    return "good";
  }
  if (grade === "C" || grade === "D") {
    return "average";
  }
  return "poor";
}

function renderSummaryCards() {
  const allMetrics = students.map(getStudentMetrics);
  const totalStudents = students.length;
  const averageMark = totalStudents
    ? allMetrics.reduce((sum, item) => sum + item.average, 0) / totalStudents
    : 0;
  const highestMark = totalStudents
    ? Math.max(...allMetrics.map((item) => item.highest), 0)
    : 0;
  const lowestMark = totalStudents
    ? Math.min(...allMetrics.map((item) => item.lowest), 100)
    : 0;

  elements.totalStudentsCard.textContent = totalStudents;
  elements.averageGradeCard.textContent = `${averageMark.toFixed(1)}%`;
  elements.highestGradeCard.textContent = `${highestMark}%`;
  elements.lowestGradeCard.textContent = `${lowestMark}%`;
}

function renderReportList() {
  if (!students.length) {
    elements.reportCards.innerHTML = "";
    return;
  }

  const topStudent = students
    .map((student) => ({ ...student, metrics: getStudentMetrics(student) }))
    .sort((a, b) => b.metrics.average - a.metrics.average)[0];

  const lowestStudent = students
    .map((student) => ({ ...student, metrics: getStudentMetrics(student) }))
    .sort((a, b) => a.metrics.average - b.metrics.average)[0];

  const averageOverall = students.reduce((sum, student) => {
    return sum + getStudentMetrics(student).average;
  }, 0) / students.length;

  const reportData = [
    { label: "Top Performer", value: topStudent.studentName },
    { label: "Average Score", value: `${averageOverall.toFixed(1)}%` },
    { label: "Lowest Performer", value: lowestStudent.studentName },
  ];

  elements.reportCards.innerHTML = reportData
    .map(
      (item) => `
        <div class="report-item">
          <div class="label">${item.label}</div>
          <strong>${item.value}</strong>
        </div>
      `
    )
    .join("");
}

function renderTable() {
  const searchTerm = elements.searchInput.value.trim().toLowerCase();
  const filteredStudents = students.filter((student) => {
    const studentName = student.studentName.toLowerCase();
    const studentId = student.studentId.toLowerCase();
    return studentName.includes(searchTerm) || studentId.includes(searchTerm);
  });

  elements.tableBody.innerHTML = "";

  if (!filteredStudents.length) {
    elements.emptyState.classList.remove("hidden");
    return;
  }

  elements.emptyState.classList.add("hidden");

  const rows = filteredStudents.flatMap((student) => {
    const metrics = getStudentMetrics(student);

    return SUBJECTS.map((subject) => {
      const mark = Number(student[subject.key]);
      const grade = getGradeLetter(mark);
      const status = mark >= 50 ? "Pass" : "Fail";
      const isHighlighted = subject.key === "computerScience";

      return `
        <tr>
          <td class="student-name">${isHighlighted ? student.studentName : ""}</td>
          <td>${isHighlighted ? student.studentId : ""}</td>
          <td>${subject.label}</td>
          <td>${mark}</td>
          <td><span class="badge grade-badge ${getBadgeClass(grade)}">${grade}</span></td>
          <td><span class="status-badge ${status === "Pass" ? "pass" : "fail"}">${status}</span></td>
          <td>
            <div class="actions">
              <button type="button" class="action-btn view" data-action="view" data-id="${student.studentId}">View</button>
              <button type="button" class="action-btn edit" data-action="edit" data-id="${student.studentId}">Edit</button>
              <button type="button" class="action-btn delete" data-action="delete" data-id="${student.studentId}">Delete</button>
            </div>
          </td>
        </tr>
      `;
    });
  });

  elements.tableBody.innerHTML = rows.join("");

  const itemRows = [...elements.tableBody.querySelectorAll("tr")];
  itemRows.forEach((row, index) => {
    const currentStudent = filteredStudents[Math.floor(index / SUBJECTS.length)];
    const subjectIndex = index % SUBJECTS.length;

    if (subjectIndex === 0) {
      row.querySelector(".student-name").textContent = currentStudent.studentName;
      row.querySelectorAll("td")[1].textContent = currentStudent.studentId;
    } else {
      row.querySelectorAll("td")[0].textContent = "";
      row.querySelectorAll("td")[1].textContent = "";
    }

    const actionButtons = row.querySelector(".actions");
    if (actionButtons) {
      actionButtons.innerHTML = `
        <button type="button" class="action-btn view" data-action="view" data-id="${currentStudent.studentId}">View</button>
        <button type="button" class="action-btn edit" data-action="edit" data-id="${currentStudent.studentId}">Edit</button>
        <button type="button" class="action-btn delete" data-action="delete" data-id="${currentStudent.studentId}">Delete</button>
      `;
    }
  });

  elements.tableBody.querySelectorAll("button[data-action]").forEach((button) => {
    button.addEventListener("click", (event) => {
      const { action, id } = event.currentTarget.dataset;
      if (action === "view") {
        openViewModal(id);
      }
      if (action === "edit") {
        openEditForm(id);
      }
      if (action === "delete") {
        deleteStudent(id);
      }
    });
  });
}

function openAddStudentForm() {
  elements.studentForm.reset();
  elements.formTitle.textContent = "Add Student";
  elements.studentFormPanel.classList.remove("hidden");
  elements.studentId.focus();
}

function openEditForm(studentId) {
  const student = students.find((item) => item.studentId === studentId);
  if (!student) {
    return;
  }

  elements.formTitle.textContent = "Edit Student";
  elements.studentFormPanel.classList.remove("hidden");
  elements.studentId.value = student.studentId;
  elements.studentName.value = student.studentName;
  elements.maths.value = student.maths;
  elements.science.value = student.science;
  elements.english.value = student.english;
  elements.history.value = student.history;
  elements.computerScience.value = student.computerScience;
  elements.studentId.focus();
}

function closeForm() {
  elements.studentFormPanel.classList.add("hidden");
  elements.studentForm.reset();
}

function validateStudentForm(formData) {
  if (!formData.studentId.trim()) {
    alert("Student ID is required.");
    return false;
  }

  if (!formData.studentName.trim()) {
    alert("Student name cannot be empty.");
    return false;
  }

  const marks = SUBJECTS.map((subject) => Number(formData[subject.key]));
  for (let i = 0; i < marks.length; i++) {
    if (Number.isNaN(marks[i]) || marks[i] < 0 || marks[i] > 100) {
      alert(`${SUBJECTS[i].label} mark must be between 0 and 100.`);
      return false;
    }
  }

  return true;
}

function handleStudentSubmit(event) {
  event.preventDefault();

  const formData = {
    studentId: elements.studentId.value.trim(),
    studentName: elements.studentName.value.trim(),
    maths: Number(elements.maths.value),
    science: Number(elements.science.value),
    english: Number(elements.english.value),
    history: Number(elements.history.value),
    computerScience: Number(elements.computerScience.value),
  };

  if (!validateStudentForm(formData)) {
    return;
  }

  const existingIndex = students.findIndex((student) => student.studentId === formData.studentId);

  if (existingIndex !== -1 && elements.formTitle.textContent === "Add Student") {
    alert("A student with this ID already exists.");
    return;
  }

  if (existingIndex >= 0) {
    students[existingIndex] = { ...students[existingIndex], ...formData };
  } else {
    students.push(formData);
  }

  saveStudents();
  render();
  closeForm();
}

function deleteStudent(studentId) {
  const student = students.find((item) => item.studentId === studentId);
  if (!student) {
    return;
  }

  const confirmed = window.confirm(`Delete ${student.studentName} from the records?`);
  if (!confirmed) {
    return;
  }

  students = students.filter((item) => item.studentId !== studentId);
  saveStudents();
  render();
}

function openViewModal(studentId) {
  const student = students.find((item) => item.studentId === studentId);
  if (!student) {
    return;
  }

  const metrics = getStudentMetrics(student);

  elements.studentReportContent.innerHTML = `
    <div class="detail-row"><span>Student Name</span><strong>${student.studentName}</strong></div>
    <div class="detail-row"><span>Student ID</span><strong>${student.studentId}</strong></div>
    <div class="detail-row"><span>Total Marks</span><strong>${metrics.total}</strong></div>
    <div class="detail-row"><span>Average Marks</span><strong>${metrics.average.toFixed(2)}%</strong></div>
    <div class="detail-row"><span>Highest Mark</span><strong>${metrics.highest}</strong></div>
    <div class="detail-row"><span>Lowest Mark</span><strong>${metrics.lowest}</strong></div>
    <div class="detail-row"><span>Grade</span><strong>${metrics.grade}</strong></div>
    <div class="detail-row"><span>Status</span><strong>${metrics.status}</strong></div>
    <div class="detail-row"><span>Subjects</span><strong>${SUBJECTS.map((subject) => `${subject.label}: ${student[subject.key]}`).join(" | ")}</strong></div>
  `;

  elements.viewModal.classList.remove("hidden");
  elements.viewModal.setAttribute("aria-hidden", "false");
}

function closeViewModal() {
  elements.viewModal.classList.add("hidden");
  elements.viewModal.setAttribute("aria-hidden", "true");
}

function render() {
  renderSummaryCards();
  renderReportList();
  renderTable();
}

elements.addStudentBtn.addEventListener("click", openAddStudentForm);
elements.emptyAddBtn.addEventListener("click", openAddStudentForm);
elements.cancelBtn.addEventListener("click", closeForm);
elements.studentForm.addEventListener("submit", handleStudentSubmit);
elements.searchInput.addEventListener("input", renderTable);
elements.closeModalBtn.addEventListener("click", closeViewModal);
elements.viewModal.addEventListener("click", (event) => {
  if (event.target.dataset.close === "true") {
    closeViewModal();
  }
});

render();
