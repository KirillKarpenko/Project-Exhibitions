package model.dao;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity);
    void update(T entity);
    void deleteById(int id);
    void close();
}
