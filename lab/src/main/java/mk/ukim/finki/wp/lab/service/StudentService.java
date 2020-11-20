package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface StudentService {

    public List<Student> listAll();

    public List<Student> searchByNameOrSurname(String text);

    public Student save(String username, String password, String name, String surname);
}
