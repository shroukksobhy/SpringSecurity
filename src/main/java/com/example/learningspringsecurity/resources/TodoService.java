package com.example.learningspringsecurity.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();
	private static int todoCount=0;
	
	static {
		todos.add(new Todo(todoCount++,"Title0","Description"));
		todos.add(new Todo(todoCount++,"Title1","Description"));
		todos.add(new Todo(todoCount++,"Title2","Description"));
		todos.add(new Todo(todoCount++,"Title3","Description"));
	}
	
	
	public List<Todo> getAllTodos(){
		return todos;
	}
	
	public List<Todo> getTodoBytitle(String title){
				
	Predicate<? super Todo> predicate = todo -> todo.getTitle().equalsIgnoreCase(title);
		return todos.stream().filter(predicate).toList();
	}
	public Todo createTodos(String title,String description) {
		Todo newTodo = new Todo(todoCount++,title,description);
		todos.add(newTodo);
		return newTodo;
	}
	

	public Todo getTodoById(int id) {
	    Predicate<? super Todo> predicate = todo -> todo.getId() == id;
	    return todos.stream().filter(predicate).findFirst().orElse(null);
	}

	public Todo updateTodo(int todoId, Todo todo) {
		Todo existingTodo = getTodoById(todoId);
		if(existingTodo == null) {
			return null;
		}
		existingTodo.setTitle(todo.getTitle());
		existingTodo.setDescription(todo.getDescription());
		return existingTodo;
	}

	public boolean deleteById(int id) {
	    return todos.removeIf(todo -> todo.getId() == id);
	}

	


}
