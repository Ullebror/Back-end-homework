package com.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Bookstore.model.BookRepository;

import org.springframework.ui.Model;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value = {"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
}
