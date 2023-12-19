package com.backend.controladores;

import com.backend.modelo.M;
import com.backend.servicios.MService;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/m")
@CrossOrigin("*")
public class MController {

    @Autowired
    private MService service;
    
    //Guardar
    @PostMapping("/save")
    public M save(@RequestBody M object){
        return (M) service.save(object);
    }
    
    //Actualizar
    @PutMapping("/update")
    public M update(@RequestBody M object){
        return (M) service.update(object);
    }
    
    //Obtener por id
    @GetMapping("/find-by-id/{id}")
    public M findById(@PathVariable("id") Integer id){
        return (M) service.findById(id);
    }
    
    //Obtener todo
    @GetMapping("/find-all")
    public List<M> findAll(){
        return service.findAll();
    }
    
    //Obtener lista vigente
    @GetMapping("/find-all-by-vigente")
    public List<M> findAllByVigente(){
        return service.findAllByVigencia(true);
    }
    
    //Obtener lista no vigente
    @GetMapping("/find-all-by-no-vigente")
    public List<M> findAllByNoVigente(){
        return service.findAllByVigencia(false);
    }
    
    //Listar paginada
    @GetMapping("/find-all-paginable-by-vigencia-busqueda")
    public Page<M> findAllPaginableByVigenciaBusqueda(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "")String busqueda1,
            @RequestParam(defaultValue = "true") Boolean vigencia){
        return service.findAllPaginableByVigenciaBusqueda(page, size, busqueda1, vigencia);
    }
    
    //Desactivar datos
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }
    
    //Activar datos
    @DeleteMapping("/restore/{id}")
    public void restore(@PathVariable("id") Integer id){
        service.restore(id);
    }
    
}
