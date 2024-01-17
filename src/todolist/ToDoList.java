package oop.todolist;

import oop.diary.exceptions.CannotBeFound;
import oop.diary.exceptions.WrongPassword;
import oop.todolist.exceptions.LoginError;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToDoList {
    private final String username;

    private final String password;
    private boolean isLoggedin;
    private int sizeOfTasks;
    ArrayList<Task> tasks;

    public ToDoList(String username, String password) {
        this.username = username;
        this.password = password;
        tasks = new ArrayList<>();
    }


    public void login(String password) {
        validate(password);
        isLoggedin = true;
    }

    private void validate(String password) {
        if (!password.equals(this.password)) {
            throw new WrongPassword("Incorrect details");
        }
    }

    public boolean isLoggedin() {
        return isLoggedin;
    }

    public void logout() {
        isLoggedin = false;
    }

    public Task writeTask(String title, String body) {
        validateLogin();
        sizeOfTasks++;
        Task  task = new Task(generateID(), title, body, LocalDateTime.now());
        tasks.add(task);
        return task;
    }


    private void validateLogin() {
        if (isLoggedin) throw new LoginError("Error! Log in to perform action");
    }

    public String generateID() {
        return "TDL" +sizeOfTasks;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public void deleteTask(String id) {
        tasks.remove(findTask(id));
    }

    public Task findTask(String id) {
        validateLogin();
        for (Task task: tasks){
            if(task.getID().equals(id)) return task;
        }
        throw new CannotBeFound("Task cannot be found");
    }

    public void updateTask(String id, String title, String body){
        validateLogin();
        Task task = findTask(id);
        String message = task.getBody() + " " + body;
        task.setBody(message);
    }

    public int findIndexOf(String id) {
        for (int index = 0; index < tasks.size(); index++) {
            if (tasks.get(index).getID().equals(id)) return index;
        }
        throw new CannotBeFound("Task cannot be found");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }
}
