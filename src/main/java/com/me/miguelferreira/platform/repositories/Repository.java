package com.me.miguelferreira.platform.repositories;

import com.me.miguelferreira.platform.model.BaseEntity;

import java.util.List;

public interface Repository<T extends BaseEntity> {

    public void add(final T entity);

    public List<T> findAll();

}
