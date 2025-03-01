const API_URL = "http://localhost:8080/api/students"; // Base API URL

// ✅ Fetch and display all students
async function fetchStudents() {
    try {
        const response = await fetch(`${API_URL}/all`);

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const students = await response.json();
        console.log("Fetched students:", students);
        displayStudents(students);

    } catch (error) {
        console.error("Error fetching students:", error);
    }
}

// ✅ Display students in the table
function displayStudents(students) {
    const tableBody = document.getElementById("studentsTableBody");

    if (!tableBody) {
        console.error("Error: Table body not found!");
        return;
    }

    tableBody.innerHTML = ""; // Clear previous data

    students.forEach(student => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${student.id}</td>
            <td>${student.firstname || "N/A"}</td>
            <td>${student.lastname || "N/A"}</td>
            <td>${student.email || "N/A"}</td>
        `;

        tableBody.appendChild(row);
    });
}

// ✅ Add a new student
async function addStudent() {
    const firstnameInput = document.getElementById("firstname");
    const lastnameInput = document.getElementById("lastname");
    const emailInput = document.getElementById("email");

    if (!firstnameInput || !lastnameInput || !emailInput) {
        console.error("One or more input fields are missing in the DOM.");
        return;
    }

    const firstname = firstnameInput.value.trim();
    const lastname = lastnameInput.value.trim();
    const email = emailInput.value.trim();

    if (!firstname || !lastname || !email) {
        alert("Please fill in all fields before adding a student!");
        return;
    }

    try {
        const response = await fetch(`${API_URL}/add`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ firstname, lastname, email }),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        alert("Student added successfully!");
        fetchStudents(); // Refresh table

        // ✅ Clear input fields
        firstnameInput.value = "";
        lastnameInput.value = "";
        emailInput.value = "";

    } catch (error) {
        console.error("Error adding student:", error);
    }
}


// ✅ Update student details (with dropdown selection)
async function updateStudent() {
    const id = document.getElementById("updateId").value.trim();
    const field = document.getElementById("updateField").value.trim();
    const value = document.getElementById("updateValue").value.trim();

    if (!id || !field || !value) {
        alert("Please fill in all fields before updating!");
        return;
    }

    try {
        // Fetch existing student data
        const response = await fetch(`${API_URL}/${id}`);

        if (!response.ok) {
            throw new Error(`Student not found! Status: ${response.status}`);
        }

        const existingStudent = await response.json();
        existingStudent[field] = value; // Update only the selected field

        // Send updated data
        const updateResponse = await fetch(`${API_URL}/update/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(existingStudent),
        });

        if (!updateResponse.ok) {
            throw new Error(`HTTP error! Status: ${updateResponse.status}`);
        }

        alert("Student updated successfully!");
        fetchStudents(); // Refresh table

        // Clear input fields
        document.getElementById("updateId").value = "";
        document.getElementById("updateField").value = "";
        document.getElementById("updateValue").value = "";

    } catch (error) {
        console.error("Error updating student:", error);
    }
}





// ✅ Delete a student by ID
async function deleteStudent() {
    const id = document.getElementById("deleteId").value.trim();

    if (!id) {
        alert("Please enter a valid Student ID to delete!");
        return;
    }

    try {
        const response = await fetch(`${API_URL}/delete/${id}`, {
            method: "DELETE",
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        alert("Student deleted successfully!");
        fetchStudents(); // Refresh table

        // Clear input field
        document.getElementById("deleteId").value = "";

    } catch (error) {
        console.error("Error deleting student:", error);
    }
}


// ✅ Delete all students
async function deleteAllStudents() {
    if (confirm("Are you sure you want to delete all students?")) {
        try {
            const response = await fetch(`${API_URL}/clear`, { method: "DELETE" });

            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

            alert("All students deleted successfully!");
            fetchStudents(); // Refresh table

        } catch (error) {
            console.error("Error deleting all students:", error);
        }
    }
}

// ✅ Load students on page load
document.addEventListener("DOMContentLoaded", fetchStudents);
