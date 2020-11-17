package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface CourseService {

    public List<Course> listAll();

    public List<Student> listStudentsByCourse(Long courseId);

    public Course addStudentInCourse(String username, Long courseId);

    Course findById(Long courseId);
}
