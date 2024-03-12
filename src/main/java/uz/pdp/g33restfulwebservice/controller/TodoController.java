package uz.pdp.g33restfulwebservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.g33restfulwebservice.dto.TodoCreationDto;
import uz.pdp.g33restfulwebservice.domain.Todo;
import uz.pdp.g33restfulwebservice.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<Todo> todos(){
        return todoService.getTodos();
    }

    @PostMapping(consumes = {"application/json", "application/xml"})
    public Todo save(@RequestBody @Valid TodoCreationDto dto){
        return todoService.save(dto);
    }

    @GetMapping(value = "/{id}", produces = {"application/xml", "application/json"})
    public Todo getById(@PathVariable("id") Long id){
        return todoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        todoService.delete(id);
        return "Todo successfully deleted";
    }
}
