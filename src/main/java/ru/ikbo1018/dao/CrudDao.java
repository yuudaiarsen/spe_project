package ru.ikbo1018.dao;

public interface CrudDao<T> {
    void create(T model);
    void update(T model);
    void delete(Integer id);
    T find(Integer id);
}
