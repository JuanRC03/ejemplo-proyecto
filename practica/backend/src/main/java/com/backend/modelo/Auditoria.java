package com.backend.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "auditoria")
public class Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")    
    private Integer idAuditoria;
    
    @Column(length = 100)
    private String usuario;
    
    @Column(length = 150)
    private String accion;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Auditoria() {
    }

    public Auditoria(Integer idAuditoria, String usuario, String accion, Date fecha) {
        this.idAuditoria = idAuditoria;
        this.usuario = usuario;
        this.accion = accion;
        this.fecha = fecha;
    }
    
    

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getAccion() {
        return accion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}