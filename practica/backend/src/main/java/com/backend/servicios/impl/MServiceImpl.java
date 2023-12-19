package com.backend.servicios.impl;

import com.backend.modelo.M;
import com.backend.repositorios.MRepository;
import com.backend.utils.MValidations;
import com.backend.servicios.MService;

import com.backend.utils.Fechas;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class MServiceImpl implements MService {

    @Autowired
    private MRepository repository;

    @Autowired
    private MValidations validations;
    
    @Autowired
    private Fechas fechas;

    //Guardar datos
    @Override
    public M save(Object objeto) {
        M oC=(M) objeto;
        try {
            validations.validar(oC);
            validations.validarGuardar(oC);
            oC.setFechaCreacion(fechas.getFechaActual());
            oC = repository.save(oC);
        } catch (Exception ex) {
            Logger.getLogger(MServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oC;
    }

    //Actualizar datos
    @Override
    public M update(Object objeto) {
        M oC=(M) objeto;
        try {
            validations.validar(oC);
            validations.validarActualizar(oC);
            M oD=repository.getById(oC.getIdM());
            oC.setCreadoPor(oD.getCreadoPor());
            oC.setFechaCreacion(oD.getFechaCreacion());
            oC.setFechaActualizacion(fechas.getFechaActual());
            oC= repository.save(oC);
        } catch (Exception ex) {
            Logger.getLogger(MServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oC;
    }

    //Devolver modelo por id
    @Override
    public M findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    //Devolver lista
    @Override
    public List<M> findAll() {
        return repository.findAll();
    }
    
    //Devolver lista
    @Override
    public List<M> findAllByVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    //Listar paginada
    @Override
    public Page<M> findAllPaginableByVigenciaBusqueda(int page,int size, String busqueda1, Boolean vigencia) {
        M c = new M();
        c.setVigencia(vigencia);
        if (busqueda1 != null && !busqueda1.isEmpty()) {
            c.setM(busqueda1);
        }
        PageRequest pageable = PageRequest.of(page, size);
        Example<M> listFiltros = Example.of(c);
        return repository.findAll(listFiltros, pageable); 
    }

    //Desactivar datos
    @Override
    public void delete(Integer id) {
        M oC=repository.getById(id);
        oC.setVigencia(false);
        oC.setFechaActualizacion(fechas.getFechaActual());
        repository.save(oC);
    }

    //Reactivar datos
    @Override
    public void restore(Integer id) {
        M oC=repository.getById(id);
        oC.setVigencia(true);
        oC.setFechaActualizacion(fechas.getFechaActual());
        repository.save(oC);
    }
} 