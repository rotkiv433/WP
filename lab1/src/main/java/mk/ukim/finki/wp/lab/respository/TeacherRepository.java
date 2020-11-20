package mk.ukim.finki.wp.lab.respository;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {


    public static List<Teacher> teachers = new ArrayList<>();


    @PostConstruct
    public void init() {

        teachers.add(new Teacher("Ана", "Тодоровска"));
        teachers.add(new Teacher("Костадин", "Мишев"));
        teachers.add(new Teacher("Александар", "Стојменски"));
        teachers.add(new Teacher("Наси", "Јофче"));
        teachers.add(new Teacher("Катарина", "Тројачанец"));

    }

    public List<Teacher> findAllTeachers() {
        return teachers;
    }

    public Optional<Teacher> findTeacherById(Long id) {
        return teachers.stream().filter(t -> t.getId().equals(id)).findFirst();
    }



}
