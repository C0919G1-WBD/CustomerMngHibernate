package repository;

import java.util.*;

public interface IGeneralRepository<E> {
    List<E> findAll();

    E findById(int id);

    void save(E e);

    void remove(int id);

    void update(int id, E e);
}
