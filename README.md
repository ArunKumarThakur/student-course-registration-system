# student-course-registration-system
# 📚 Student Course Registration System

A simple **Spring-based Java console application** that allows students to register for courses, track enrollments, and export reports in PDF format. Designed with **loose coupling** using **Spring XML configuration** for easy maintenance and extension.


## 🔧 Technologies Used

- **Java 21**
- **Spring Framework (Core, XML-based Configuration)**
- **Apache PDFBox** – for PDF report generation
- **Maven** – for dependency management (optional)
- **Git** – version control


## ✨ Features

- Register students to courses
- Prevent duplicate registrations
- Display all enrolled students
- Display a student’s enrolled courses
- Export enrolled student information to a **PDF report**
- Designed with **loose coupling** using **Spring Dependency Injection**

---

## 📄 Sample PDF Output

A sample PDF is generated when calling the method:
```java
registrationService.exportEnrolledStudentsToPDF("enrolled_students.pdf");

**Future Improvements** 
Add Admin and Student Portals
Create CSV Export support
Build a GUI using JavaFX or Swing
Add database integration for persistence
