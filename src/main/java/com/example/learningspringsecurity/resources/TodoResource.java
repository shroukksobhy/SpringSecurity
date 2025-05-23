package com.example.learningspringsecurity.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {
	
	@Autowired
	private TodoService todoService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

//	private static final List<Todo> TODOS_LIST = 
//			List.of(new Todo("Learn AWS"), new Todo("in28minutes"));
//	
	@GetMapping("/todos")
	public List<Todo> retrieveTodos() {
		return todoService.getAllTodos();
	}
	
	@GetMapping("/users/{title}/todo")
	public List<Todo> retrieveTodosForSpecificUser(@PathVariable String title) {
//		return TODOS_LIST.get(0);
		return todoService.getTodoBytitle(title);
	 }

	@PostMapping("/save")
	public Todo saveData(@RequestBody Todo todo) {	
//		return ResponseEntity.ok(todo);
		return todoService.createTodos(todo.getTitle(), todo.getDescription());
	}
	
	@GetMapping("/todos/{todoId}")
	public ResponseEntity<Todo> getTodo(@PathVariable int todoId) {
		Todo todo =  todoService.getTodoById(todoId);
		if(todo==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(todo);
	}

	@PutMapping("/todos/{todoId}")
	public ResponseEntity<Todo> updateTodo(@PathVariable int todoId, @RequestBody Todo todo){
		Todo updatedTodo = todoService.updateTodo(todoId,todo);
		if(updatedTodo == null) {
			return ResponseEntity.notFound().build();	
		}
		return ResponseEntity.ok(updatedTodo);
	}
	
	@DeleteMapping("/todos/{todoId}")
	public ResponseEntity<Void> deleteTodo(@PathVariable int todoId) {
		boolean deletedTodo = todoService.deleteById(todoId);
		if(!deletedTodo) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	
}
//record Todo (String username,String description) {}