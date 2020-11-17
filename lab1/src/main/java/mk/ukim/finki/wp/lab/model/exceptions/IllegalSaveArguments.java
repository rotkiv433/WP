package mk.ukim.finki.wp.lab.model.exceptions;

public class IllegalSaveArguments extends RuntimeException {

    public IllegalSaveArguments() {
        super("Invalid save credentials!");
    }
}
