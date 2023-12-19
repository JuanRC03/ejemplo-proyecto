package com.backend.controladores;

import com.backend.modelo.Usuario;
import com.backend.servicios.UsuarioService;

import java.util.List;
import org.springframework.web.bind.annotation.*;

//-------------------------------------------------------
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//-------------------------------------------------------


@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //Guardar usuario
    @PostMapping("/save")
    public Usuario save(@RequestBody Usuario usuario) throws Exception{
        return (Usuario) service.save(usuario);
    }
    
    @PutMapping("/update")
    public Usuario update(@RequestBody Usuario usuario){
        return (Usuario) service.update(usuario);
    }
    
    //Obtener un usuario
    @GetMapping("/find-by-id/{id}")
    public Usuario findById(@PathVariable("id") Integer id){
        return (Usuario) service.findById(id);
    }
    
    //Listar paginada
    @GetMapping("/find-all-paginable-by-vigencia-busqueda")
    public Page<Usuario> findAllPaginableByVigenciaBusqueda(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "")String busqueda1,
            @RequestParam(defaultValue = "true") Boolean vigencia){
        return service.findAllPaginableByVigenciaBusqueda(page, size, busqueda1, vigencia);
    }
    
    //Listar paginada vigente
    @GetMapping("/find-all-paginable-by-vigencia-usuario-fecha-rol")
    public Page<Usuario> findAllPaginableByVigenciaUsuarioFechaRol(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "")String busqueda1,
            @RequestParam(defaultValue = "true") Boolean vigencia,
            @RequestParam(defaultValue = "")String fecha,
            @RequestParam(defaultValue = "1")int idRol){
        return service.findAllPaginableByVigenciaBusquedaFechaRol(page, size, busqueda1, vigencia, fecha, idRol);
    }
    
    //Listar paginada vigente
    @GetMapping("/find-all-by-fecha-nacimiento-between-and-rol-idrol-and-vigencia-and-usuario")
    public Page<Usuario> obtenerUsuariosVigentesPaginableFiltroFechas(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "")String busqueda1,
            @RequestParam(defaultValue = "true") Boolean vigencia,
            @RequestParam(defaultValue = "")String fechaInicio,
            @RequestParam(defaultValue = "")String fechaFin,
            @RequestParam(defaultValue = "1")int idRol){
        return service.findAllByFechaNacimientoBetweenAndRolIdRolAndVigenciaAndBusqueda( page,  size,  busqueda1,  vigencia,  fechaInicio,  fechaFin,  idRol );
    }
    
    //Listar vigentes
    @GetMapping("/find-all-by-vigente")
    public List<Usuario> findAllByVigente(){
        return service.findAllByVigencia(true);
    }
    
    //Listar no vigentes
    @GetMapping("/find-all-by-no-vigente")
    public List<Usuario> findAllByNoVigente(){
        return service.findAllByVigencia( false);
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
    
    //Obtener pdf
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> dowloadPDF() {
        byte[] pdfBytes = service.dowloadPdf(true, 2); 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "usuarios.pdf"); 
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
    
    //Obtener excel
    @GetMapping("/excel")
    public ResponseEntity<byte[]> dowloadExcel() {
        byte[] pdfBytes = service.dowloadExcel(true, 2); 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "usuarios.xlsx"); 
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
    
}
