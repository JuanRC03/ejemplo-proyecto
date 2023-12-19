package com.backend.repositorios;

import com.backend.modelo.Auditoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriaRepository extends JpaRepository<Auditoria,Integer> {
    
}
