package ru.sboychenko.vaadin.todo.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.olli.FileDownloadWrapper;
import ru.sboychenko.vaadin.todo.ics.CalendarService;
import ru.sboychenko.vaadin.todo.model.Todo;
import ru.sboychenko.vaadin.todo.jpa.TodoRepository;

import java.io.ByteArrayInputStream;
import java.util.List;


@SpringComponent
@UIScope
public class TodoListLayout extends VerticalLayout implements TodoChangeListener {

    private final TodoRepository repository;
    private final CalendarService calendarService;

    @Autowired
    public TodoListLayout(TodoRepository repository, CalendarService calendarService) {
        this.repository = repository;
        this.calendarService = calendarService;
        setWidth("80%");
        update();
    }

    public void save(Todo todo) {
        repository.save(todo);
        update();
    }

    public int countCompleted() {
        return repository.countByDone(true);
    }

    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }

    @Override
    public void changed(Todo todo) {
        save(todo);
    }

    private void update() {
        removeAll();
        List<Todo> all = repository.findByOrderByDueAsc();

        double step = (double) 100 / all.size();
        ProgressBar progress = new ProgressBar(0, 100, step);
        progress.setValue(all.stream().filter(Todo::isDone).count() * step);

        Button deleteButton = new Button("Delete completed", click -> {
            if (countCompleted() == 0) {
                Notification.show("No completed task");
                return;
            }
            deleteCompleted();
            Notification.show("Deleted...");
        });

        Button calendarButton = new Button("Export to ics", new Icon(VaadinIcon.CALENDAR));
        FileDownloadWrapper buttonWrapper = new FileDownloadWrapper(
                new StreamResource("todo.ics", () -> new ByteArrayInputStream(calendarService.getEvents().getBytes())));
        buttonWrapper.wrapComponent(calendarButton);

        add(progress);
        all.forEach(todo -> add(new TodoItemLayout(todo, this)));
        add(new HorizontalLayout(deleteButton, buttonWrapper));
    }
}
