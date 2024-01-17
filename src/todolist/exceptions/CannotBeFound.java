package oop.todolist.exceptions;

public class CannotBeFound extends RuntimeException{
    public CannotBeFound(String message){
        super(message);
    }
}
