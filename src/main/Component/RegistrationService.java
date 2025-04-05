package Component;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RegistrationService {

    //Map<StudentId, Student>
    private final Map<String, Student> studentMap;
    private final CourseService courseService;

    public RegistrationService(Map<String, Student> studentMap,  CourseService courseService) {
        this.studentMap = studentMap;
        this.courseService = courseService;
    }



    public void register(String studentId, String studentName, String courseId) {
        // Check if student already exists
        Student student = studentMap.get(studentId);

        if (student == null) {
            student = new Student(studentId, studentName.toUpperCase());
            studentMap.put(studentId, student);
            System.out.println("🆕 New student added: " + student.getStudentName().toUpperCase());
        }

        if(student.isAlreadyRegistered(courseId)) {
            System.out.println("⚠️  Already Registered in that course");
            return ;
        }
        // Find course from CourseService
        Course course = courseService.findCourseById(courseId);

        if (course != null) {
            student.enrollCourse(course);
            System.out.println("✅ " + student.getStudentName().toUpperCase() + " registered for " + course.getTitle());
        } else {
            System.out.println("❌ Course not found!");
        }
    }

    public void displayStudents() {
        System.out.println("Register Students");
        for(Student student : studentMap.values()) {
            System.out.println(student.getStudentName().toUpperCase());
        }
    }

    public boolean checkStudent(String studentId) {
        for(String id : studentMap.keySet()) {
            if(id.equalsIgnoreCase(studentId)) return true;
        }
        return false;
    }

    public Student getStudent(String sId) {
        return studentMap.get(sId);
    }

    public void getCourses() {
        courseService.displayAllCourses();
    }

    public void studentEnrollCourse(String sId) {
        Student student = studentMap.get(sId);
        if(student != null) {
            List<Course> courseList = student.getEnrolledCourses();

            for(Course c : courseList) {
                System.out.println(c);
            }
        }
    }

    public void exportEnrolledStudentsToPDF(String fileName) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Enrolled Students Report");
            contentStream.endText();


            contentStream.setFont(PDType1Font.HELVETICA, 12);
            int y = 720;

            for (Student student : studentMap.values()) {
                List<Course> courseList = student.getEnrolledCourses();
                for (Course course : courseList) {
                    if (y < 50) {
                        contentStream.close(); // close old page content
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        contentStream = new PDPageContentStream(document, page);
                        contentStream.setFont(PDType1Font.HELVETICA, 12);
                        y = 750;
                    }

                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, y);
                    contentStream.showText("ID: " + student.getStudentId() +
                            ", Name: " + student.getStudentName() +
                            ", Course: " + course.getCode() + " - " + course.getTitle());
                    contentStream.endText();
                    y -= 20;
                }
            }

            contentStream.close();
            document.save(fileName);
            System.out.println("📄 Enrolled student data exported to PDF: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error writing PDF: " + e.getMessage());
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
