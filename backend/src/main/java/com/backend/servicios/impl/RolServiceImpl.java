package com.backend.servicios.impl;

import com.backend.modelo.Rol;
import com.backend.repositorios.RolRepository;
import com.backend.servicios.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    //Guardar datos
    @Override
    public Rol save(Object objeto) {
        Rol oA=(Rol) objeto;
        return rolRepository.save(oA);
    }
    
    //Actualizar datos
    @Override
    public Rol update(Object objeto) {
        Rol oA=(Rol) objeto;
        return rolRepository.save(oA);
    }
    
    //Obtener modelo por id
    @Override
    public Rol findById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    //Listar
    @Override
    public List findAll() {
        return rolRepository.findAll();
    }
    
    
}
