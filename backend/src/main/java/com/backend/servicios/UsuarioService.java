package com.backend.servicios;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UsuarioService <T>{
    
    public T save(T objeto) throws Exception;
    public T update(T objeto);
    public T findById(Integer usuarioId);
    public Page<T> findAllPaginableByVigenciaBusqueda(int page,int size, String busqueda1, Boolean vigencia);
    public Page<T> findAllPaginableByVigenciaBusquedaFechaRol(int page,int size, String busqueda1, Boolean vigencia, String fecha, Integer idRol);
    public Page<T> findAllByEdadBetweenAndRolIdRolAndVigenciaAndBusqueda(Boolean vigencia, Integer idRol, String busqueda1, Integer edadMin, Integer edadMax, int page, int size);
    public Page<T> findAllByFechaNacimientoBetweenAndRolIdRolAndVigenciaAndBusqueda(int page, int size, String busqueda1, Boolean vigencia, String fechaMin, String fechaMax, Integer idRol);
    public List<T> findAllByVigencia(boolean vigencia);
    public void delete(Integer usuarioId);
    public void restore(Integer usuarioId);
    public byte[] dowloadPdf(Boolean vigencia, Integer idRol);
    public byte[] dowloadExcel(Boolean vigencia, Integer idRol);

}
