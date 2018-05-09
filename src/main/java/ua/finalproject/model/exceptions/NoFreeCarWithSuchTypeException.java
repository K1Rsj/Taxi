package ua.finalproject.model.exceptions;

public class NoFreeCarWithSuchTypeException extends Exception {

    @Override
    public String getMessage() {
        return "No free car such type yet";
    }

}
