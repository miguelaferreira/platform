package com.me.miguelferreira.platform.repositories;

import com.me.miguelferreira.platform.model.BaseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRepository<T extends BaseEntity> implements Repository<T> {

    private final Map<String, T> entities = new HashMap<>();

    @Override
    public void add(final T entity) {
        final String id = Integer.toString(entities.size());
        entity.setId(id);
        entities.put(id, entity);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(entities.values());
    }

}
