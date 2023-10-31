package com.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Bookstore.model.Book;
import com.Bookstore.model.BookRepository;
import com.Bookstore.model.Category;
import com.Bookstore.model.CategoryRepository;


import org.springframework.ui.Model;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	 @RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }	
	
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
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {	
    	return repository.findById(id);
    }   
    
 // RESTful service to get book by id
    @RequestMapping(value="/category/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long id) {	
    	return crepository.findById(id);
    }   
    
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addBook(Model model){
	model.addAttribute("book", new Book());
	model.addAttribute("categories", crepository.findAll());
	return "addbook";
	}
    
    @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
	repository.save(book);
	return "redirect:booklist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	repository.deleteById(bookId);
	return "redirect:../booklist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/category/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("name") Long Id, Model model) {
	crepository.deleteById(Id);
	return "redirect:../booklist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model){
	model.addAttribute("book", repository.findById(bookId));
	model.addAttribute("categories", crepository.findAll());
	return "edit";
	}
	
	
}
