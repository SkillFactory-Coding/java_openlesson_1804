package ru.sboychenko.vaadin.todo.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import ru.sboychenko.vaadin.todo.model.Priority;
import ru.sboychenko.vaadin.todo.model.Todo;

import java.time.LocalDate;

public class TodoItemLayout extends HorizontalLayout {

    private final Checkbox done;
    private final TextField text;
    private final DatePicker due;

    public TodoItemLayout(Todo todo, TodoChangeListener listener) {
        setWidth("100%");
        done = new Checkbox();
        text = new TextField();
        text.setWidthFull();
        due = new DatePicker();
        due.setMin(LocalDate.now());

        if (todo.isDone()) {
            text.addClassName("done");
            text.setReadOnly(true);
            due.addClassName("done");
            due.setReadOnly(true);
        }

        text.setValueChangeMode(ValueChangeMode.ON_BLUR);

        Binder<Todo> binder = new Binder<>(Todo.class);
        binder.bindInstanceFields(this);
        binder.setBean(todo);

        add(done, text, due, createPriority(todo.getPriority()));

        binder.addValueChangeListener(event -> listener.changed(todo));
    }

    private Component createPriority(Priority priority) {
        Label label = new Label();

        if (priority == null || priority == Priority.NORMAL) {
            label.add(" ");
            label.setWidth("44px");
            return label;
        }

        switch (priority) {
            case LOW:
                label.add(new Icon(VaadinIcon.ANGLE_DOWN));
                break;
            case HIGH:
                label.add(new Icon(VaadinIcon.ANGLE_DOUBLE_UP));
                break;
            default:
        }
        return label;
    }

}
