import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    List<Student> registeredStudents = new ArrayList<>();

    Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    int studentID;
    String name;
    List<Course> registeredCourses = new ArrayList<>();

    Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }
}

class Main {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Sample courses
        courses.add(new Course("CSCI101", "Introduction to Programming", "Basic programming concepts", 30, "MWF 10:00 AM"));
        courses.add(new Course("MATH202", "Linear Algebra", "Vector spaces and matrices", 25, "TTh 2:00 PM"));

        while (true) {
            System.out.println("1. Course Listing");
            System.out.println("2. Student Registration");
            System.out.println("3. Course Removal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Available Courses:");
                    for (Course course : courses) {
                        System.out.println(course.code + " - " + course.title + " (" + course.schedule + ")");
                    }
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();

                    Student student = new Student(studentID, studentName);
                    students.add(student);

                    System.out.println("Available Courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course course = courses.get(i);
                        System.out.println(i + ". " + course.code + " - " + course.title);
                    }

                    System.out.print("Enter the index of the course to register: ");
                    int courseIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (courseIndex >= 0 && courseIndex < courses.size()) {
                        Course selectedCourse = courses.get(courseIndex);
                        if (selectedCourse.capacity > selectedCourse.registeredStudents.size()) {
                            student.registeredCourses.add(selectedCourse);
                            selectedCourse.registeredStudents.add(student);
                            System.out.println("Registration successful.");
                        } else {
                            System.out.println("Course is full. Registration failed.");
                        }
                    } else {
                        System.out.println("Invalid course index.");
                    }
                    break;

                case 3:
                    System.out.println("Registered Courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course course = courses.get(i);
                        System.out.println(i + ". " + course.code + " - " + course.title);
                    }

                    System.out.print("Enter the index of the course to remove: ");
                    int courseIndexToRemove = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (courseIndexToRemove >= 0 && courseIndexToRemove < courses.size()) {
                        Course courseToRemove = courses.get(courseIndexToRemove);

                        // Remove the course from students' registered courses
                        for (int i = 0; i < courseToRemove.registeredStudents.size(); i++) {
                            student = courseToRemove.registeredStudents.get(i); 
                            student.registeredCourses.remove(courseToRemove);
                        }

                        // Clear the registered students list for the course
                        courseToRemove.registeredStudents.clear();

                        // Remove the course from the courses list
                        courses.remove(courseIndexToRemove);

                        System.out.println("Course removal successful.");
                    } else {
                        System.out.println("Invalid course index.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}