package com.lcwd.todo.controllers;

import com.lcwd.todo.models.Todo;
import com.lcwd.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger= LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    Random random =new Random();

    //createtodo
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){

        int id= random.nextInt(999999);
        todo.setId(id);

        logger.info("create todo");
        Todo todo1=todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodoHandler(){
        List<Todo> alltodos=todoService.getAllTodos();
        return new ResponseEntity<>(alltodos,HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId ){

        Todo todo=todoService.GetTodo(todoId);
        return ResponseEntity.ok(todo);

    }

    //update
    @PutMapping("{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails,@PathVariable int todoId){

        Todo todo=todoService.updateTodo(todoId,todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("todo deleted successfully");

    }
}
