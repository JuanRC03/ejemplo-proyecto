package com.backend.controladores;

import com.backend.modelo.Auditoria;
import com.backend.servicios.AuditoriaService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auditoria")
@CrossOrigin("*")
public class AuditoriaController {

    
    @Autowired
    private AuditoriaService service;

    //guardar
    @PostMapping("/save")
    public Object guardar(@RequestBody Auditoria oC)
    {
        return service.save(oC);    
    }
    
    //actualizar
    @PutMapping("/update")
    public Object update(@RequestBody Auditoria oC)
    {
        return service.update(oC);    
    }
    
    //Obtener modelo por id
    @GetMapping("/find-by-id/{id}")
    public Object findById(@PathVariable("id") Integer id)
    {
        return service.findById(id);
    }
    
    //Listar
    @GetMapping("/find-all")
    public List<Auditoria> findAll()
    {
        return service.findAll();
    }
    
    //Eliminar
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id)
    {
        service.delete(id);
    }
    
}
