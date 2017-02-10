package com.ymt.envagency.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ymt.envagency.model.Book;
import com.ymt.envagency.service.BookService;

@Controller
@RequestMapping("/book.do")
public class BookController {
	 private BookService bookService;
	    @RequestMapping(params = "method=add")
//	 @RequestMapping(value={"/add"}, method = {RequestMethod.GET})
	    public String add(Book book){
	        System.out.println("bookname:"+book.getName());
	        System.out.println("author:"+book.getAuthor());
	        bookService.add(book);
	        return "success";
	    }
	    @RequestMapping(params = "method=update")
	    public String update(Book book) {
	        bookService.update(book);
	        return "success";
	    }
	    public BookService getBookService() {
	        return bookService;
	    }
	    @Resource
	    public void setBookService(BookService bookService) {
	        this.bookService = bookService;
	    }
}
