package mk.ukim.finki.wp.lab.respository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {


    public static List<Course> courses = new ArrayList<>();

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    public CourseRepository(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;

    }

    @PostConstruct
    public void init() {
        List<Teacher> teachers = this.teacherRepository.findAllTeachers();
        courses.add(new Course( "Veb Programiranje", "vp", new ArrayList<>(), teachers.get(0)));
        courses.add(new Course("Implementacija na sistemi so otvoren kod", "inssok", new ArrayList<>(), teachers.get(1)));
        courses.add(new Course( "Menadzment na informaciski sistemi", "mis", new ArrayList<>(), teachers.get(2)));
        courses.add(new Course( "Multimediski sistemi", "ms",new ArrayList<>(), teachers.get(3)));
        courses.add(new Course("Sistemi na znaenje", "snz", new ArrayList<>(), teachers.get(4)));
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


   public Optional<Course> save(Course c) {
        for(Course b : courses) {
            if(b.getName().equals(c.getName()))
                return Optional.of(c);
            else if(b.getCourseId().equals(c.getCourseId()))
                courses.remove(b);
        }
        courses.add(c);
        return Optional.of(c);
   }


    public void deleteById(Course c) {
        courses.remove(c);
    }

}
