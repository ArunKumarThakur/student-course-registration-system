package Component;

import java.util.HashMap;
import java.util.Map;

public class CourseService {

    private final Map<String, Course> courseMap = new HashMap<>();

    public void init() {
        Course c1 = new Course("MATH201", "Advanced Mathematics");
        preCourseService(c1);
        Course c2 = new Course("CS101", "Introduction to Computer Science");
        preCourseService(c2);
    }

    public void preCourseService(Course course) {
        courseMap.put(course.getCode(), course);
    }

    public void addCourse(Course course) {
        courseMap.put(course.getCode(), course);
        System.out.println("📚 Course added: " + course.getCode() + " - " + course.getTitle());
    }

    public Course findCourseById(String id) {
        Course course = courseMap.get(id);
        if (course != null) {
            return course;
        } else {
            System.out.println("❌ Course with ID " + id + " not found.");
            return null;
        }
    }

    public void displayAllCourses() {
        System.out.println("📘 Available Courses:");
        for (Course course : courseMap.values()) {
            System.out.println("➤ " + course.getCode() + " - " + course.getTitle());
        }
    }
}
