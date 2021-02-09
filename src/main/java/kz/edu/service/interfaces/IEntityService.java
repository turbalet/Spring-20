package kz.edu.service.interfaces;

import java.util.List;

public interface IEntityService<T> {
    void add(T entity);
    void update(T entity);
    List<T> getAll();
    T getById(int id);
    void delete(int id);
}
