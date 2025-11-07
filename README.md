# 🎓 Course Enrollment and Grade Management System

## 🧩 Overview
This project is a **Java console-based system** that allows administrators to manage courses, enroll students, assign grades, and compute overall averages.  
Developed for a **University of the People programming assignment**, it demonstrates **object-oriented programming, collections, encapsulation, and control flow**.  
The program supports multiple students and courses within a simple, menu-driven interface.

---

## ⚙️ Features
- **Add new courses** with course code, name, and maximum capacity  
- **Enroll students** in available courses with automatic capacity checks  
- **Assign grades** to enrolled students  
- **Compute and display overall grades** for each student  
- Uses a **menu-driven interface** for continuous interaction  
- Validates input and handles common user errors gracefully  

---

## 🧠 Concepts Used

| Concept | Description |
|----------|-------------|
| **Classes and Objects** | `Course`, `Student`, `CourseManagement`, and `Main` represent modular components |
| **Encapsulation** | Private fields with getter/setter methods protect internal data |
| **Static Variables and Methods** | Track total enrollments and provide class-level management functions |
| **ArrayList** | Store collections of students and courses dynamically |
| **HashMap** | Associate courses with their grades per student |
| **Conditional Statements & Loops** | Control menu navigation, input validation, and logic flow |
| **Composition** | Each `Course` contains enrolled `Student` objects; `Student` holds `Course` references |
| **Abstraction** | Course and student details are managed via high-level methods in `CourseManagement` |
| **Exception Prevention** | Input handling and bounds checking reduce runtime errors |

---

## ▶️ How to Run

1️⃣ Open a terminal in the project directory.  
2️⃣ Compile all classes:
```bash
javac Main.java

```

3️⃣ Run the program:
```bash
java Main
```


## 🏫 Educational Context

This project was created for a Computer Science course at the University of the People to demonstrate:

Core OOP principles (encapsulation, composition, static data)

Use of Java collections (ArrayList, HashMap)

User-driven control flow via console interface
It provides hands-on experience with data modeling and system design fundamentals in Java.
