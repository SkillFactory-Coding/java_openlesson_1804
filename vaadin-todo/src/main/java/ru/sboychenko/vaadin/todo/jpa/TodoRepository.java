package ru.sboychenko.vaadin.todo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sboychenko.vaadin.todo.model.Todo;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Transactional
    void deleteByDone(boolean done);

    int countByDone(boolean done);

    List<Todo> findByOrderByDueAsc();

    List<Todo> findByDoneFalseAndDueNotNull();
}
