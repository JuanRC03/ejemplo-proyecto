package com.backend.servicios;
import java.util.List;
import org.springframework.data.domain.Page;

public interface MService <T> {
    public T save(T objeto);
    public T update(T objeto);
    public T findById(Integer id);
    public List<T> findAll();
    public List<T> findAllByVigencia(Boolean vigencia);
    public Page<T> findAllPaginableByVigenciaBusqueda(int page,int size, String busqueda1, Boolean vigencia);
    public void delete(Integer id);
    public void restore(Integer id);
}
