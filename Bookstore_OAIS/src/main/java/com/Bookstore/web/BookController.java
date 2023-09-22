package com.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Bookstore.model.Book;
import com.Bookstore.model.BookRepository;
import com.Bookstore.model.CategoryRepository;


import org.springframework.ui.Model;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value = {"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    

	// RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long id) {	
    	return repository.findById(id);
    }   
    
	
	@RequestMapping(value = "/add")
	public String addBook(Model model){
	model.addAttribute("book", new Book());
	model.addAttribute("categories", crepository.findAll());
	return "addbook";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
	repository.save(book);
	return "redirect:booklist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	repository.deleteById(bookId);
	return "redirect:../booklist";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model){
	model.addAttribute("book", repository.findById(bookId));
	model.addAttribute("categories", crepository.findAll());
	return "edit";
	}
	
	/* @RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Book book, @PathVariable("id") Long id){
	repository.save(repository.findById(id));
	return "redirect:booklist"; 
	} */
}
