package com.backend.repositorios;

import com.backend.modelo.M;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MRepository extends JpaRepository<M,Integer> {
    public List<M> findByVigencia(Boolean vigencia);    
}
