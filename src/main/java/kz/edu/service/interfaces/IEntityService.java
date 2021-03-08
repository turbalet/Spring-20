package kz.edu.service.interfaces;

import java.util.List;

public interface IEntityService<T> {
    T add(T entity);
    void update(T entity);
    List<T> getAll();
    T getById(long id);
    void delete(long id);
}
