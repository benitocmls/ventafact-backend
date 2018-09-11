package com.ventafact.service;

import java.util.List;


public interface ICRUD<T> {

	T save(T t);
	T update(T t);
	void delete(int id);
	T getById(int id);
	List<T> getAll();

}
