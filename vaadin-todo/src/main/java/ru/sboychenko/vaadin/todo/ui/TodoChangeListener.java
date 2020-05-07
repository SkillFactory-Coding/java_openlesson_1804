package ru.sboychenko.vaadin.todo.ui;

import ru.sboychenko.vaadin.todo.model.Todo;

public interface TodoChangeListener {
    void changed(Todo todo);
}
