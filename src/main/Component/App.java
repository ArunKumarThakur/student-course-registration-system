package Component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

import static java.lang.System.exit;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        RegistrationService service = context.getBean("registrationService", RegistrationService.class);

        int choice;
        Scanner scanner = new Scanner(System.in);
        int next = 1;
        while(next != 0) {
            System.out.println("1. Register\n2. Courses provided\n3. Enrolled Students\n4. Enrolled course status\n5. " +
                    "Generate Pdf");
            System.out.println("Enter Your choice");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 :
                    System.out.println("Enter your id");
                    String sId = scanner.next();

                    boolean isStudentPresent = service.checkStudent(sId);
                    String name = "";
                    if(!isStudentPresent) {
                        System.out.println("Enter your name");
                        name = scanner.next();
                    } else {
                        Student student = service.getStudent(sId);
                        System.out.println(student.getStudentName() + " is already present");
                        name = student.getStudentName();
                    }
                    System.out.println("Enter course id");
                    String courseId = scanner.next();
                    System.out.println("\n");
                    service.register(sId, name, courseId);
                    break;
                case 2:
                    service.getCourses();
                    break;
                case 3:
                    service.displayStudents();
                    break;
                case 4:
                    System.out.println("Enter student id");
                    String studentId = scanner.next();
                    service.studentEnrollCourse(studentId);
                    break;
                case 5:
                    System.out.println("Generate Pdf");
                    service.exportEnrolledStudentsToPDF("enrolled_students.pdf");
                    break;
                default:
                    exit(1);
            }
            System.out.println("Do you want to continue 1 for Yes and 0 for No");
            next = scanner.nextInt();
        }

    }
}
