package ru.sboychenko.vaadin.todo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String text;
    private LocalDate due;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private boolean done = false;

    public Todo() {
    }

    public Todo(String text, LocalDate due, Priority priority) {
        this.text = text;
        this.due = due;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
