package com.backend.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "m")
public class M {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_m")
    private Integer idM;    
    private String m;
    
//    private Integer im;
//    @Temporal(TemporalType.DATE)
//    private Date fecha;
    
    //Muchos a uno
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_usuario")
//    private Usuario usuario;
    
    //Uno a muchos
//    @OneToMany(mappedBy = "m",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<Usuario> usuario = new HashSet<>();
    
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private String creadoPor;
    private String actualizadoPor;
    private Boolean vigencia=true;

    public M() {
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public Integer getIdM() {
        return idM;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public String getActualizadoPor() {
        return actualizadoPor;
    }

    public Boolean getVigencia() {
        return vigencia;
    }

    public void setIdM(Integer idM) {
        this.idM = idM;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public void setActualizadoPor(String actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }

    public void setVigencia(Boolean vigencia) {
        this.vigencia = vigencia;
    }
}