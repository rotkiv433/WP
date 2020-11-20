package mk.ukim.finki.wp.lab.model.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super("Student was not found with that username!");
    }
}
