package com.me.miguelferreira.platform.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.me.miguelferreira.utils.ObjectUtils;

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ObjectUtils.toString(this);
    }

    @Override
    public boolean equals(final Object o) {
        return ObjectUtils.equals(this, o);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(this);
    }
}
