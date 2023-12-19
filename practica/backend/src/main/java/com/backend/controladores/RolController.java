package com.backend.controladores;

import com.backend.modelo.Rol;
import com.backend.servicios.RolService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rol")
@CrossOrigin("*")
public class RolController {

    
    @Autowired
    private RolService rolService;

    //Guardar
    @PostMapping("/save")
    public Object save(@RequestBody Rol oC)
    {
        return rolService.save(oC);    
    }
    
    //Actualizar
    @PutMapping("/update")
    public Object update(@RequestBody Rol oC)
    {
        return rolService.update(oC);    
    }
    
    //Listar
    @GetMapping("/find-all")
    public List<Rol> findAll()
    {
        return rolService.findAll();
    }
    
    //Obteneer modelo por id
    @GetMapping("/find-by-id/{idRol}")
    public Object findById(@PathVariable("idRol") Integer idRol)
    {
        return rolService.findById(idRol);
    }
    
}
