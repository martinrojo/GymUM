package com.um.gym.application.service;

import com.um.gym.application.ExceptionHandler.MyResourceNotFoundException;

import java.io.Serializable;
import java.util.List;

public interface Service<T, ID extends Serializable> {
    T create(final T entity) throws MyResourceNotFoundException;
    void delete(final T entity);
    T update(final T entity);
    void deleteById(final ID id);
    T findById(final ID id) throws MyResourceNotFoundException;
    List<T> findAll();
}
