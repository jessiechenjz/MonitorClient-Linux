package com.ymt.envagency.dao;

import org.springframework.stereotype.Component;

import com.ymt.envagency.model.Book;

@Component
public class BookDao {
	   public void add(Book book){
	        System.out.print("Add");
	    }
	    public void update(Book book){
	        System.out.print("Update");
	    }
}
