package com.ymt.envagency.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ymt.envagency.dao.BookDao;
import com.ymt.envagency.model.Book;

@Component
public class BookService {
	 private BookDao bookDao;

	    public BookDao getBookDao() {
	        return bookDao;
	    }

	    @Resource
	    public void setBookDao(BookDao bookDao) {
	        this.bookDao = bookDao;
	    }
	    
	    public void add(Book book){
	        bookDao.add(book);
	    }
	    public void update(Book book){
	        bookDao.update(book);
	    }
}
