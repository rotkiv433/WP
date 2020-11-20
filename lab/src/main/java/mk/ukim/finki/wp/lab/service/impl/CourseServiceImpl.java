package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.StudentNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.respository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepository courseRepository, TeacherService teacherService, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        List<Student> studentList = studentService.listAll();
        Student foundStudent = null;
        for(Student s : studentList) {
            if(s.getUsername().equals(username)) {
                foundStudent = s;
                break;
            }
        }
        Course foundCourse = courseRepository.findById(courseId);
        if(foundCourse==null) {
            throw new CourseNotFoundException();
        }
        if(foundStudent==null) {
            throw new StudentNotFoundException();
        }

        return courseRepository.addStudentToCourse(foundStudent, foundCourse);
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Optional<Course> saveCourse(String name, String description, Long teacherId) {
        Teacher teacher = teacherService.findById(teacherId).
                orElseThrow(TeacherNotFoundException::new);
        Course course = new Course(name, description, teacher);
        course.setStudents(this.studentService.listAll());


        return this.courseRepository.save(course);

    }


    @Override
    public void deleteCourse(Long id) {
        Course temp = this.courseRepository.findById(id);
        this.courseRepository.deleteById(temp);
    }


}
