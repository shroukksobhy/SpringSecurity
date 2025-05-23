package com.example.learningspringsecurity.resources;

public class Todo{
	private int id;
	private String title;
	private String description;

	
	  // Default constructor for JSON deserialization (important for @RequestBody)
    public Todo() {
    }

    // Constructor for creating new Todos (ID will be generated)
    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Constructor for existing Todos (with ID)
    public Todo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
