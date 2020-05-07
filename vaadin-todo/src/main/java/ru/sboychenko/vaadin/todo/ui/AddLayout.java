package ru.sboychenko.vaadin.todo.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import ru.sboychenko.vaadin.todo.model.Priority;
import ru.sboychenko.vaadin.todo.model.Todo;

import java.time.LocalDate;
import java.util.Locale;

@SpringComponent
@UIScope
public class AddLayout extends HorizontalLayout {

    private final TextField textField;
    private final DatePicker datePicker;
    private final Select<Priority> select;
    private final Button button;

    public AddLayout() {
        setWidth("100%");
        textField = new TextField();
        textField.setPlaceholder("Need to do...");
        textField.setWidthFull();
        textField.setRequired(true);

        datePicker = new DatePicker();
        datePicker.setClearButtonVisible(true);
        datePicker.setLocale(Locale.getDefault());
        datePicker.setPlaceholder("Due date");
        datePicker.setWidth("200px");
        datePicker.setMin(LocalDate.now());

        select = new Select<>();
        select.setItems(Priority.values());
        select.setPlaceholder("Priority");
        select.setWidth("100px");
        select.setValue(Priority.NORMAL);

        button = new Button(new Icon(VaadinIcon.PLUS));
        button.addClickShortcut(Key.ENTER);

        add(textField, datePicker, select, button);
    }

    public Todo getTodo() {
        return new Todo(textField.getValue(), datePicker.getValue(), select.getValue());
    }

    public void addButtonClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        button.addClickListener(listener);
    }
}
