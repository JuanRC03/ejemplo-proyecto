package com.backend.repositorios;

import com.backend.modelo.Usuario;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    
    public Usuario findByEmail(String email);
    public List<Usuario> findByVigenciaAndRolIdRol( Boolean vigencia, Integer idRol);
    public Page<Usuario> findAllByRolIdRolAndVigencia(Integer idRol,Boolean vigencia, Pageable pageable);
    
    public Page<Usuario> findByEdadBetweenAndRolIdRolAndVigencia(int edadMin, int edadMax, Integer idRol, Boolean vigencia,Pageable pageable);
    public Page<Usuario> findByEdadBetweenAndRolIdRolAndVigenciaAndEmailContaining(int edadMin, int edadMax, Integer idRol, Boolean vigencia, String busqueda1,Pageable pageable);
    
    public Page<Usuario> findByFechaNacimientoBetweenAndRolIdRolAndVigencia(Date fechaNacimientoMin, Date fechaNacimientoMax, Integer rolId, Boolean vigencia, Pageable pageable);
    public Page<Usuario> findByFechaNacimientoBetweenAndRolIdRolAndVigenciaAndEmailContaining(Date fechaNacimientoMin, Date fechaNacimientoMax, Integer rolId, Boolean vigencia, String busqueda1, Pageable pageable);
    
}
