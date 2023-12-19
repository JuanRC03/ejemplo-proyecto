package com.backend.servicios;
import java.util.List;

public interface AuditoriaService <T> {
    public void crearTriggerDB();
    public T save(T objeto);
    public T update(T objeto);
    public T findById(Integer id);
    public List<T> findAll();
    public void delete(Integer id);
}
