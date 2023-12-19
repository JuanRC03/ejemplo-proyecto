package com.backend.utils;

import com.backend.modelo.M;
import com.backend.repositorios.MRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MValidations {

    @Autowired
    MRepository repository;

    //Validar vacio
    public void validar(M request) throws Exception  {
        M objNull = new M();
        if (request.equals(objNull)) {
            throw new Exception("Vacio");
        }
    }
    
    //Validar guardar
    public void validarGuardar(M request) throws Exception {
        if (request.getM()== null || request.getM().isEmpty()) {
            throw new Exception("Falta datos");
        }
    }
    
    //Validar actualizar
    public void validarActualizar(M request) throws Exception {
        if (request.getIdM()== null) {
            throw new Exception("Falta datos");
        }
        if (!repository.existsById(request.getIdM())) {
            throw new Exception("No existe");
        }
    }
    
    //Validar listar
    public void validarLista(List<M> request) throws Exception  {
        if (!request.isEmpty()) {
            throw new Exception("No existen datos");
        }
    }
}
