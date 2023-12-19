package com.backend.servicios;
import java.util.List;

public interface RolService <T> {
    public T save(T objeto);
    public T update(T objeto);
    public T findById(Integer id);
    public List<T> findAll();
}
