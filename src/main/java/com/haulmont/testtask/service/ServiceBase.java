package com.haulmont.testtask.service;

import com.haulmont.testtask.model.EmptyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

public abstract class ServiceBase<T extends EmptyEntity, D extends CrudRepository<T,Long>> {

    @Autowired
    protected D repository;

    public Long count() {
        return repository.count();
    }

    public T findById(long id) {
        return repository.findOne(id);
    }

    public T save(T entity) {
        if (entity==null) return null;
        repository.save(entity);
        return entity;
    }

    public void remove(T entity) {
        repository.delete(entity);
    }

    public void removeById(Long id) {
        repository.delete(id);
    }

    public List<T> findAll() {
        List<T> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

}
