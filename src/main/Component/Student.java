package Component;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Course> enrolledCourses = new ArrayList<>();

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    public boolean isAlreadyRegistered(String courseId) {
        for (Course course : enrolledCourses) {
            if (course.getCode().equalsIgnoreCase(courseId)) {
                return true;
            }
        }
        return false;
    }
}
