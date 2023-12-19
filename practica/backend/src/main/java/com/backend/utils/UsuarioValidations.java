package com.backend.utils;

import com.backend.modelo.Usuario;
import com.backend.repositorios.UsuarioRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidations {

    @Autowired
    UsuarioRepository repository;

    //Validar vacio
    public void validar(Usuario request) throws Exception  {
        Usuario objNull = new Usuario();
        if (request.equals(objNull)) {
            throw new Exception("Vacio");
        }
    }
    
    //Validar guardar
    public void validarGuardar(Usuario request) throws Exception {
        Usuario usuarioLocal = repository.findByEmail(request.getEmail());
        if(usuarioLocal != null){
            throw new UsuarioFoundException("El usuario ya existe");
        }
        if (request.getNombre()== null || request.getNombre().isEmpty()) {
            throw new Exception("Falta datos");
        }
        if (request.getApellido()== null || request.getApellido().isEmpty()) {
           throw new Exception("Falta datos");
        }
        if (request.getContrasenia()== null || request.getContrasenia().isEmpty()) {
            throw new Exception("Falta datos");
        }
    }
    
    //Validar actualizar
    public void validarActualizar(Usuario request) throws Exception {
        if (request.getIdUsuario()== null) {
            throw new Exception("Falta datos");
        }
        if (!repository.existsById(request.getIdUsuario())) {
            throw new Exception("No existe");
        }
    }
    
    //Validar listar
    public void validarLista(List<Usuario> request) throws Exception  {
        if (!request.isEmpty()) {
            throw new Exception("No existen datos");
        }
    }
}
