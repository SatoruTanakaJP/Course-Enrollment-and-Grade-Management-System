import java.util.*;


class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private ArrayList<Student> enrolledStudents;

    private static int totalEnrolled = 0; // static variable to track all enrollments

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getMaxCapacity() { return maxCapacity; }
    public ArrayList<Student> getEnrolledStudents() { return enrolledStudents; }

    public boolean enrollStudent(Student student) {
        if (enrolledStudents.size() < maxCapacity) {
            enrolledStudents.add(student);
            totalEnrolled++;
            return true;
        } else {
            System.out.println("Course " + courseName + " is full!");
            return false;
        }
    }

    public static int getTotalEnrolled() {
        return totalEnrolled;
    }
}

// -------------------------------
// Class: Student
// -------------------------------
class Student {
    private String name;
    private String studentID;
    private HashMap<Course, Double> enrolledCourses; // store course and grade

    public Student(String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
        this.enrolledCourses = new HashMap<>();
    }

    public String getName() { return name; }
    public String getStudentID() { return studentID; }

    public void enrollCourse(Course course) {
        if (course.enrollStudent(this)) {
            enrolledCourses.put(course, null);
            System.out.println(name + " has been enrolled in " + course.getCourseName());
        }
    }

    public void assignGrade(Course course, double grade) {
        if (enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
            System.out.println("Grade " + grade + " assigned to " + name + " for " + course.getCourseName());
        } else {
            System.out.println("Student not enrolled in this course.");
        }
    }

    public HashMap<Course, Double> getEnrolledCourses() {
        return enrolledCourses;
    }
}

// -------------------------------
// Class: CourseManagement
// -------------------------------
class CourseManagement {
    private static ArrayList<Course> courseList = new ArrayList<>();

    public static void addCourse(Course course) {
        courseList.add(course);
        System.out.println("Course " + course.getCourseName() + " added successfully.");
    }

    public static void enrollStudent(Student student, Course course) {
        student.enrollCourse(course);
    }

    public static void assignGrade(Student student, Course course, double grade) {
        student.assignGrade(course, grade);
    }

    public static double calculateOverallGrade(Student student) {
        HashMap<Course, Double> grades = student.getEnrolledCourses();
        double sum = 0;
        int count = 0;

        for (Double grade : grades.values()) {
            if (grade != null) {
                sum += grade;
                count++;
            }
        }

        double overall = (count == 0) ? 0 : sum / count;
        System.out.println("Overall Grade for " + student.getName() + ": " + overall);
        return overall;
    }

    public static ArrayList<Course> getCourses() {
        return courseList;
    }
}

// -------------------------------
// Class: AdministratorInterface
// -------------------------------
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        boolean running = true;
        while (running) {
            System.out.println("\n===== Course Enrollment and Grade Management System =====");
            System.out.println("1. Add New Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate Overall Grade");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter course code: ");
                    String code = sc.nextLine();
                    System.out.print("Enter course name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter max capacity: ");
                    int cap = sc.nextInt();
                    Course newCourse = new Course(code, name, cap);
                    CourseManagement.addCourse(newCourse);
                    break;

                case 2:
                    System.out.print("Enter student name: ");
                    String sname = sc.nextLine();
                    System.out.print("Enter student ID: ");
                    String sid = sc.nextLine();
                    Student st = new Student(sname, sid);
                    students.add(st);

                    System.out.println("Available Courses:");
                    for (int i = 0; i < CourseManagement.getCourses().size(); i++) {
                        System.out.println((i + 1) + ". " + CourseManagement.getCourses().get(i).getCourseName());
                    }
                    System.out.print("Choose course number: ");
                    int cindex = sc.nextInt() - 1;
                    if (cindex >= 0 && cindex < CourseManagement.getCourses().size()) {
                        CourseManagement.enrollStudent(st, CourseManagement.getCourses().get(cindex));
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                    break;

                case 3:
                    System.out.print("Enter student ID to assign grade: ");
                    String gid = sc.nextLine();
                    Student target = null;
                    for (Student s : students) {
                        if (s.getStudentID().equals(gid)) target = s;
                    }

                    if (target == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.println("Available Courses:");
                    for (int i = 0; i < CourseManagement.getCourses().size(); i++) {
                        System.out.println((i + 1) + ". " + CourseManagement.getCourses().get(i).getCourseName());
                    }
                    System.out.print("Select course number: ");
                    int gindex = sc.nextInt() - 1;
                    System.out.print("Enter grade: ");
                    double grade = sc.nextDouble();
                    if (gindex >= 0 && gindex < CourseManagement.getCourses().size()) {
                        CourseManagement.assignGrade(target, CourseManagement.getCourses().get(gindex), grade);
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                    break;

                case 4:
                    System.out.print("Enter student ID to calculate overall grade: ");
                    String cid = sc.nextLine();
                    for (Student s : students) {
                        if (s.getStudentID().equals(cid)) {
                            CourseManagement.calculateOverallGrade(s);
                        }
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}
