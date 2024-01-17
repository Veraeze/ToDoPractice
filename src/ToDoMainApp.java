package todolist;

import java.util.Scanner;

public class ToDoMainApp {
        public static void main(String[] args) {
            mainMenu();
        }
        public static void mainMenu(){
            print("""
                ============================
                        ToDoList
                ============================
                press 1 -> Register
                press 2 -> Login
                press 3 -> Logout
                press 4 -> exit
                """);
            String select = input(String.class);
            switch (select){
                case "1" -> register();
                case "2" -> login();
                case "3" -> logout();
                case "4" -> exit();
            }
        }
        static ToDoList myToDoList;

        private static void exit() {
            System.exit(0);

        }

        private static void logout(){
            if (myToDoList ==  null){
                print("Account does not exist");
                mainMenu();
            }
            try {
                myToDoList.logout();
                mainMenu();
            }
            catch (Exception exception){
                print(exception.getMessage());
                mainMenu();
            }

        }

        private static void login() {
            if (myToDoList ==  null){
                print("Account does not exist!");
                mainMenu();
            }
            print("Enter password");
            String password = input(String.class);
            try {
                myToDoList.login(password);
            }
            catch (Exception exception){
                print(exception.getMessage());
                mainMenu();
            }
            loggedIn();
        }

        private static void loggedIn(){
            System.out.println("""
                press 1 -> Add Task
                press 2 -> Find Task
                press 3 -> Update Task
                press 4 -> Delete Task
                press 5 -> Main Menu
                """);
            String select = input(String.class);
            switch (select){
                case "1" -> addTask();
                case "2" -> findTask();
                case "3" -> updateTask();
                case "4" -> deleteTask();
                default -> mainMenu();
            }
        }

        private static void deleteTask() {
            print("Enter task id");
            String id = input(String.class);
            try {
                myToDoList.deleteTask(id);
                print("Task deleted!");
                login();
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
                login();
            }
        }

        private static void updateTask() {
            print("Enter task id");
            String id = input(String.class);

            print("Compose a title for your task");
            String title = input(String.class);

            print("What goal do you want to set today?");
            String body = input(String.class);
            try {
                myToDoList.updateTask(id, title, body);
                print("Task has been updated");
                login();
            }
            catch (Exception exception){
                print(exception.getMessage());
                login();
            }
        }

        private static void findTask() {
            print("Enter task id");
            String id = input(String.class);
            try {
                System.out.println(myToDoList.findTask(id));
                login();
            }
            catch (Exception exception){
                print(exception.getMessage());
                login();
            }
        }

        private static void addTask() {
            print("Compose a title for your task");
            String title = input(String.class);

            print("What goal do you want to set today?");
            String body = input(String.class);

            System.out.println(myToDoList.writeTask(title, body));
            print("Task added!");

            login();
        }

        private static void register() {
            print("Enter username");
            String username = input(String.class);
            if (username.matches("\\d+") || !username.matches("[a-z]+")){
                System.out.println("error");
                register();
            }

            print("Enter password");
            String password = input(String.class);

            myToDoList = new ToDoList(username, password);
            mainMenu();
        }


        private static <T> T input(Class<T> value){
            Scanner scanner = new Scanner(System.in);
            T element = null;

            if (value.equals(String.class)){
                element = value.cast(scanner.nextLine());
            }
            else if (value.equals(Integer.class)){
                element = value.cast(scanner.nextInt());
            }
            else if (value.equals(Double.class)){
                element = value.cast(scanner.nextDouble());
            }
            else if (value.equals(Long.class)){
                element = value.cast(scanner.nextLong());
            }
            return element;
        }

        private static void print(String message){
            System.out.println(message);
        }
    }


