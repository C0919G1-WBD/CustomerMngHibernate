package service;

import java.util.List;

public interface IGeneralService<E> {
    List<E> findAll();

    E findById(int id);

    void save(E e);

    void update(int id, E e);

    void delete(int id);
}
