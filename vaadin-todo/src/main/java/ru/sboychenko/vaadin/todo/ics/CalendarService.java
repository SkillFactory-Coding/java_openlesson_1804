package ru.sboychenko.vaadin.todo.ics;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sboychenko.vaadin.todo.jpa.TodoRepository;
import ru.sboychenko.vaadin.todo.model.Todo;

import java.util.List;

@Service
public class CalendarService {

    private final TodoRepository repository;

    @Autowired
    public CalendarService(TodoRepository repository) {
        this.repository = repository;
    }

    public String getEvents() {

        List<Todo> all = repository.findByDoneFalseAndDueNotNull();

        Calendar cal = new Calendar();

        all.forEach(todo -> {
            VEvent event = new VEvent(new Date(java.sql.Date.valueOf(todo.getDue())), "TODO: " + todo.getText());
            UidGenerator ug = new RandomUidGenerator();
            event.getProperties().add(ug.generateUid());
            cal.getComponents().add(event);
        });

        return cal.toString();
    }
}
