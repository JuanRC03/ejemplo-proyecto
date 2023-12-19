package com.backend.servicios.impl;

import com.backend.modelo.Usuario;
import com.backend.repositorios.UsuarioRepository;
import com.backend.servicios.UsuarioService;
import com.backend.utils.UsuarioValidations;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.backend.utils.Fechas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//--------------------------------------------------
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.backend.modelo.Rol;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
//--------------------------------------------------

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private UsuarioValidations validations;
    
    @Autowired
    private Fechas fechas;
    
    //Guardar datos
    @Override
    public Usuario save(Object objeto){
        Usuario oC=(Usuario) objeto;
        try{
            validations.validar(oC);
            validations.validarGuardar(oC);
            oC.setContrasenia(this.bCryptPasswordEncoder.encode(oC.getPassword()));
            oC.setFechaCreacion(fechas.getFechaActual());
            oC = repository.save(oC);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oC;
    }
    
    //Actualizar datos
    @Override
    public Usuario update(Object objeto){
        Usuario oC=(Usuario) objeto;
        try {
            validations.validar(oC);
            validations.validarActualizar(oC);
            oC.setFechaActualizacion(fechas.getFechaActual());
            Usuario oD=findById(oC.getIdUsuario());
            oC.setFechaCreacion(oD.getFechaCreacion());
            oC.setCreadoPor(oD.getCreadoPor());
            oC= repository.save(oC);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oC;
    }
    
    //Devolver modelo por id
    @Override
    public Usuario findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
    //Devolver lista por vigencia
    @Override
    public List<Usuario> findAllByVigencia(boolean vigencia) {
        return repository.findByVigenciaAndRolIdRol(vigencia, 2);
    }
    
    //Desactivar datos
    @Override
    public void delete(Integer id) {
        Usuario oC=repository.findById(id).orElse(null);
        oC.setFechaActualizacion(fechas.getFechaActual());
        oC.setVigencia(false);
        repository.save(oC);
    }
    
    //Activar datos
    @Override
    public void restore(Integer id) {
        Usuario oC=repository.findById(id).orElse(null);
        oC.setFechaActualizacion(fechas.getFechaActual());
        oC.setVigencia(true);
        repository.save(oC);
    }
    
    //Listar paginada
    @Override
    public Page<Usuario> findAllPaginableByVigenciaBusqueda(int page,int size, String busqueda1, Boolean vigencia) {
        Usuario c = new Usuario();
        c.setVigencia(vigencia);
        Rol rol=new Rol();
        rol.setIdRol(2);
        c.setRol(rol);
        if (busqueda1 != null && !busqueda1.isEmpty()) {
            c.setEmail(busqueda1);
        }
        PageRequest pageable = PageRequest.of(page, size);
        Example<Usuario> listFiltros = Example.of(c);
        return repository.findAll(listFiltros, pageable); 
    }
    
    //Listar paginada
    @Override
    public Page<Usuario> findAllPaginableByVigenciaBusquedaFechaRol(int page,int size, String busqueda1, Boolean vigencia, String fecha, Integer idRol) {
        Usuario c = new Usuario();
        c.setVigencia(vigencia);
        Rol rol=new Rol();
        rol.setIdRol(idRol);
        c.setRol(rol);
        if (busqueda1 != null && !busqueda1.isEmpty()) {
            c.setEmail(busqueda1);
        }
        if(fecha != null && !fecha.isEmpty()){
            Fechas fechas=new Fechas();
            c.setFechaNacimiento(fechas.Fecha(fecha));
        }
        PageRequest pageable = PageRequest.of(page, size);
        Example<Usuario> listFiltros = Example.of(c);
        return repository.findAll(listFiltros, pageable); 
    }
    
    
    //Listar paginada
    @Override
    public Page<Usuario> findAllByFechaNacimientoBetweenAndRolIdRolAndVigenciaAndBusqueda(int page, int size, String busqueda1, Boolean vigencia, String fechaMin, String fechaMax, Integer idRol ) {
        PageRequest pageable = PageRequest.of(page, size);

        
        if(!fechaMin.isEmpty() && !fechaMax.isEmpty() && !busqueda1.isEmpty()){
            Fechas fechas = new Fechas();
            Date fechaInicioDate = fechas.Fecha(fechaMin);
            Date fechaFinDate = fechas.Fecha(fechaMax);
            return repository.findByFechaNacimientoBetweenAndRolIdRolAndVigenciaAndEmailContaining(fechaInicioDate, fechaFinDate,  idRol, vigencia, busqueda1, pageable);
        }
        
        if(!fechaMin.isEmpty() && !fechaMax.isEmpty()){
            Fechas fechas = new Fechas();
            Date fechaInicioDate = fechas.Fecha(fechaMin);
            Date fechaFinDate = fechas.Fecha(fechaMax);
            return repository.findByFechaNacimientoBetweenAndRolIdRolAndVigencia(fechaInicioDate, fechaFinDate,  idRol, vigencia, pageable);
        }
        
        Usuario c = new Usuario();
        c.setVigencia(vigencia);
        Rol rol = new Rol();
        rol.setIdRol(idRol);
        c.setRol(rol);
        if (busqueda1 != null && !busqueda1.isEmpty()) {
            c.setEmail(busqueda1);
        }
        Example<Usuario> listFiltros = Example.of(c);
        return repository.findAll(listFiltros, pageable);
    }

    //Listar paginada
    @Override
    public Page<Usuario> findAllByEdadBetweenAndRolIdRolAndVigenciaAndBusqueda(Boolean vigencia, Integer idRol, String busqueda1, Integer edadMin, Integer edadMax, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        
        if(edadMin!=-1 && edadMax!=-1 && !busqueda1.isEmpty()){
            if (edadMin >= 0 && edadMax >= 0 && edadMin <= edadMax) {
                return repository.findByEdadBetweenAndRolIdRolAndVigenciaAndEmailContaining(edadMin, edadMax, idRol, vigencia, busqueda1, pageable);
            }
        }
        
        if(edadMin!=-1 && edadMax!=-1){
            if (edadMin >= 0 && edadMax >= 0 && edadMin <= edadMax) {
                return repository.findByEdadBetweenAndRolIdRolAndVigencia(edadMin, edadMax, idRol, vigencia, pageable);
            }
        }
        
        Usuario c = new Usuario();
        c.setVigencia(vigencia);
        Rol rol = new Rol();
        rol.setIdRol(idRol);
        c.setRol(rol);
        
        if (busqueda1 != null && !busqueda1.isEmpty()) {
            c.setEmail(busqueda1);
        }

        Example<Usuario> listFiltros = Example.of(c);
        return repository.findAll(listFiltros, pageable);
    }
    
    //Crear PDF
    @Override
     public byte[] dowloadPdf(Boolean vigencia, Integer idRol) {
        List<Usuario> usuarios= repository.findByVigenciaAndRolIdRol(vigencia,idRol);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Font font = new Font(FontFamily.COURIER, 16, Font.BOLD, BaseColor.BLACK);
            
            // 3 columnas para Nombre, Apellido y Cédula
            PdfPTable table = new PdfPTable(3); 
            
            // Añade encabezados de columna
            PdfPCell cell = new PdfPCell(new Phrase("Nombre", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Apellido", font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Cédula", font));
            table.addCell(cell);

            // Llena la tabla con los datos de los usuarios
            for (Usuario usuario : usuarios) {
                table.addCell(usuario.getNombre());
                table.addCell(usuario.getApellido());
                table.addCell(usuario.getCedula());
            }
            document.add(table);
            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Crear Excel
    @Override
    public byte[] dowloadExcel(Boolean vigencia, Integer idRol) {
        List<Usuario> usuarios= repository.findByVigenciaAndRolIdRol(vigencia,idRol);
        try{
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet("Usuarios");

            // Crear encabezados
            Row headerRow = sheet.createRow(0);
            String[] columnas = {"ID", "Nombre", "Apellido", "Cédula"};
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
            }

            // Agregar datos de usuarios a la tabla
            for (int i = 0; i < usuarios.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Usuario usuario = usuarios.get(i);
                row.createCell(0).setCellValue(usuario.getIdUsuario().toString());
                row.createCell(1).setCellValue(usuario.getNombre());
                row.createCell(2).setCellValue(usuario.getApellido());
                row.createCell(3).setCellValue(usuario.getCedula());
            }

            for (int i = 0; i < columnas.length; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
} 
