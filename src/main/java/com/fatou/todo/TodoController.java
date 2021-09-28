package com.fatou.todo;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")

public class TodoController {
	
	@Autowired
	TodoService pink;
	
	@RequestMapping(value = "/list-todo", method = RequestMethod.GET)
	public String retrieveTodos(ModelMap model) {
		model.addAttribute("todos", pink.retrieveTodos("yellow"));
		return "list-todo";
		}
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showaddpage(ModelMap model) {
		model.addAttribute("todo", new Todo());
		return "todo";
		}
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addtodo(ModelMap model,Todo todo) {
		pink.addTodo("yellow", todo.getDesc(), new Date(), false);
		model.clear();
		return "redirect:list-todo";
	}	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deletetodo(@RequestParam int id) {
		pink.deleteTodo(id);
		return "redirect:list-todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(ModelMap model, @RequestParam int id) {
		model.addAttribute("todo", pink.retrieveTodo(id));
		return "todo";
	}
	
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model,  Todo todo) {
		

		todo.setUser("in28Minutes"); //TODO:Remove Hardcoding Later
		pink.updateTodo(todo);

		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-todos";
	}
}
	