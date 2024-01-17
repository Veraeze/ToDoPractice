package todolist;

import java.time.LocalDateTime;

public class Task {
    private final String id;
    private String title;
    private String body;
    private LocalDateTime dateCreated;

    public Task(String id, String title, String body, LocalDateTime dateCreated) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.dateCreated = dateCreated;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void date(){
        dateCreated = LocalDateTime.now();
    }

    public String getID() {
        return id;
    }

    public String toString(){
        return String.format("""
                            ==========================
                            Task ID: %d
                            Title of task: %s
                            Body of task: %s
                            Date created: %s
                            ==========================
                             """, id, title, body, dateCreated);
    }

}
