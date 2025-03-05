document.addEventListener("DOMContentLoaded", () => {
    loadStudents(); // Cargar estudiantes al iniciar

    document.getElementById("add-student-form").addEventListener("submit", (e) => {
        e.preventDefault();
        addStudent();
    });

    document.querySelector(".filter-input").addEventListener("input", filterStudents);
});

let students = JSON.parse(localStorage.getItem("students")) || [];

// 🔍 Cargar y mostrar estudiantes
function loadStudents() {
    const tableBody = document.querySelector("#students tbody");
    tableBody.innerHTML = "";
    
    students.forEach((student, index) => {
        let row = document.createElement("tr");
        row.innerHTML = `
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.email}</td>
            <td>${student.enrollmentDate}</td>
            <td>
                <button onclick="editStudent(${index})">✏️</button>
                <button onclick="deleteStudent(${index})">🗑️</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// ➕ Agregar estudiante
function addStudent() {
    let name = document.getElementById("student-name").value;
    let email = document.getElementById("student-email").value;
    let date = document.getElementById("student-date").value;

    let newStudent = {
        id: students.length + 1,
        name,
        email,
        enrollmentDate: date
    };

    students.push(newStudent);
    localStorage.setItem("students", JSON.stringify(students));
    
    document.getElementById("add-student-form").reset();
    loadStudents();
}

// ❌ Eliminar estudiante
function deleteStudent(index) {
    students.splice(index, 1);
    localStorage.setItem("students", JSON.stringify(students));
    loadStudents();
}

// ✏️ Editar estudiante
function editStudent(index) {
    let student = students[index];
    let newName = prompt("Nuevo nombre:", student.name);
    let newEmail = prompt("Nuevo email:", student.email);
    let newDate = prompt("Nueva fecha de inscripción:", student.enrollmentDate);

    if (newName && newEmail && newDate) {
        students[index] = { id: student.id, name: newName, email: newEmail, enrollmentDate: newDate };
        localStorage.setItem("students", JSON.stringify(students));
        loadStudents();
    }
}

// 🔍 Filtrar estudiantes
function filterStudents() {
    let query = document.querySelector(".filter-input").value.toLowerCase();
    let filteredStudents = students.filter(student => 
        student.name.toLowerCase().includes(query) || student.email.toLowerCase().includes(query)
    );

    const tableBody = document.querySelector("#students tbody");
    tableBody.innerHTML = "";

    filteredStudents.forEach((student, index) => {
        let row = document.createElement("tr");
        row.innerHTML = `
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.email}</td>
            <td>${student.enrollmentDate}</td>
            <td>
                <button onclick="editStudent(${index})">✏️</button>
                <button onclick="deleteStudent(${index})">🗑️</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}
