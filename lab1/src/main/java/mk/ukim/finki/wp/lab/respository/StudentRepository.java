package mk.ukim.finki.wp.lab.respository;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    public static List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init() {
        students.add(new Student("student1", "pass1", "Viktor", "Ilievski"));
        students.add(new Student("student2", "pass2", "Milan", "Krajinovikj"));
        students.add(new Student("student3", "pass3", "Kristijan", "Stojanovski"));
        students.add(new Student("student4", "pass4", "Milos", "Tonchevski"));
        students.add(new Student("student5", "pass5", "Teodor", "Jovanovski"));
    }

    public List<Student> findAllStudents() {
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text) {
        List<Student> listOfFound = null;
        for(Student s : students) {
            if(s.getName().equals(text) || s.getSurname().equals(text))
                listOfFound.add(s);
        }

        return listOfFound;
    }

    public Student save(Student s) {
        if(s==null || s.getUsername().isEmpty() || s.getUsername()==null) {
            return null;
        }

        students.removeIf(r->r.getUsername().equals(s.getUsername()));
        students.add(s);
        return s;
    }





}
