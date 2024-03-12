package uz.pdp.g33restfulwebservice.service;


import org.springframework.stereotype.Service;
import uz.pdp.g33restfulwebservice.dto.TodoCreationDto;
import uz.pdp.g33restfulwebservice.domain.Todo;
import uz.pdp.g33restfulwebservice.exception.TodoNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    private static final List<Todo> TODOS = new ArrayList<>();
    private static final AtomicLong index = new AtomicLong(1);

    public Todo save(TodoCreationDto dto) {
        Todo todo = new Todo(
                index.getAndIncrement(),
                dto.getAction(),
                dto.getAssignee(),
                dto.getTime()
        );
        TODOS.add(todo);
        return todo;
    }

    public List<Todo> getTodos(){
        return TODOS;
    }

    public Todo findById(Long id) {
        return TODOS.stream()
                .filter(t -> Objects.equals(id, t.getId()))
                .findFirst()
                .orElseThrow(
                        () -> new TodoNotFoundException("Todo not found with id - " + id)
                );
    }

    public void delete(Long id) {
        TODOS.remove(findById(id));
    }
}
