package com.backend;

import com.backend.modelo.Rol;
import com.backend.modelo.Usuario;
import com.backend.servicios.AuditoriaService;
import com.backend.servicios.RolService;
import com.backend.servicios.UsuarioService;
import com.backend.utils.Fechas;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class main implements CommandLineRunner{

    @Autowired
    private AuditoriaService auditoriaService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private Fechas fechas;

    @Autowired
    private RolService rolService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //Crear triger
        try {
            auditoriaService.crearTriggerDB();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        
        //Guardar roles
        try {
            Rol rol = new Rol();
            rol.setIdRol(1);
            rol.setNombreRol("ADMINISTRADOR");
            rolService.save(rol);
            rol.setIdRol(2);
            rol.setNombreRol("EMPLEADO");
            rolService.save(rol);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        
        //Guardar usuario admin
        try {
            Rol rol = new Rol();
            rol.setIdRol(1);
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(1);
            usuario.setEmail("admin@gmail.com");
            usuario.setNombre("Juan");
            usuario.setApellido("Maigua");
            Date fechaNacimiento = new Date(98, 8, 03); 
            usuario.setContrasenia("12345");
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setCreadoPor("Juan");
            usuario.setCedula("0550458681");
            usuario.setFechaCreacion(fechas.getFechaActual());
            usuario.setRol(rol);
            usuarioService.save(usuario);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        

        System.out.println("---------------------------Java Spring Boot Iniciado-------------------------------------");

    }

}
