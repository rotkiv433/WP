package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Data
public class Course implements Comparable<Course> {

    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;
    private Teacher teacher;

    public Course(String name, String description, List<Student> students, Teacher teacher) {

        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
    }

    public Course(String name, String description, Teacher teacher) {
        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }


    @Override
    public int compareTo(Course o) {
        String string1 = name.toLowerCase();
        String string2 = o.getName().toLowerCase();

        return string1.compareTo(string2);
    }
}
