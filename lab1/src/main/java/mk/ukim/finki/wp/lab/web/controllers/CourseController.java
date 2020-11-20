package mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {


    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;


    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;

    }


    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        List<Course> listCourses = this.courseService.listAll();
        Collections.sort(listCourses);
        model.addAttribute("courses", listCourses);
        return "listCourses";
    }

    @GetMapping("/add-course")
    public String getAddCoursePage(Model model) {
        List<Teacher> teachers = this.teacherService.findAllTeachers();
        model.addAttribute("teachers", teachers);
        return "add-course";
    }
    @PostMapping(value = "/add-student", params = "submitBtn")
    public String addStudents(Model model) {
        List<Student> students = this.studentService.listAll();
//        Long courseId = (Long) model.getAttribute("courseId");
        model.addAttribute("students", students);
        return "listStudents";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteCourse(id);
        return "redirect:/courses";
    }
    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long idTeacher) {

        this.courseService.saveCourse(name, description, idTeacher);
        return "redirect:/courses";
    }


    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        Course courseFound = this.courseService.findById(id);
        if(courseFound==null) {
            return "redirect:/courses?error=No%20Course%20Found%20With%20Id:%20" + id;
        }
        List<Teacher> teachers = this.teacherService.findAllTeachers();
        model.addAttribute("course", courseFound);
        model.addAttribute("teachers", teachers);

        return "add-course";
    }
    


}
