package mk.ukim.finki.wp.lab.respository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {


    public static List<Course> courses = new ArrayList<>();

    private final StudentRepository studentRepository;

    public CourseRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void init() {

        courses.add(new Course( "Veb Programiranje", "vp", new ArrayList<>()));
        courses.add(new Course("Implementacija na sistemi so otvoren kod", "inssok", new ArrayList<>()));
        courses.add(new Course( "Menadzment na informaciski sistemi", "mis", new ArrayList<>()));
        courses.add(new Course( "Multimediski sistemi", "ms",new ArrayList<>()));
        courses.add(new Course("Sistemi na znaenje", "snz", new ArrayList<>()));
    }


    public List<Course> findAllCourses() {
        return courses;
    }

    public Course findById(Long courseId) {
        for(Course c : courses) {
            if(c.getCourseId().equals(courseId))
                return c;
        }
        return null;
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        for (Course c : courses) {
            if(c.getCourseId().equals(courseId)) {
                return c.getStudents();
            }
        }
        return null;
    }

    public Course addStudentToCourse(Student student, Course course) {
        List<Student> temp = course.getStudents();
        if(temp.stream().anyMatch(s -> s.getUsername().equals(student.getUsername())))
            return course;
        temp.add(student);
        course.setStudents(temp);
        return course;
    }


}
