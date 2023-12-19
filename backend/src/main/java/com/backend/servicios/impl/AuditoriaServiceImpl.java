package com.backend.servicios.impl;

import com.backend.modelo.Auditoria;
import com.backend.repositorios.AuditoriaRepository;
import com.backend.servicios.AuditoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaRepository repository;

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Auditoria save(Object objeto) {
        Auditoria oC=(Auditoria) objeto;
        return repository.save(oC);
    }
    
    @Override
    public Auditoria update(Object objeto) {
        Auditoria oC=(Auditoria) objeto;
        return repository.save(oC);
    }
    
    @Override
    public Auditoria findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
    
    @Override
    @Transactional
    public void crearTriggerDB() {
        if (!verificarTriggerInsert()) {
            String query = "CREATE TRIGGER trigger_insert_auditoria_save AFTER INSERT ON usuario " +
                           "FOR EACH ROW INSERT INTO auditoria(usuario, fecha, accion) " +
                           "VALUES (CURRENT_USER(), NOW(), 'Ingreso')";

            entityManager.createNativeQuery(query).executeUpdate();
        }
        if (!verificarTriggerUpdate()) {
            String query = "CREATE TRIGGER trigger_insert_auditoria_update AFTER UPDATE ON usuario " +
                           "FOR EACH ROW INSERT INTO auditoria(usuario, fecha, accion) " +
                           "VALUES (CURRENT_USER(), NOW(), 'Egreso')";

            entityManager.createNativeQuery(query).executeUpdate();
        }
    }
    
    
    public boolean verificarTriggerInsert() {
        String query = "SELECT trigger_name" +
                       " FROM information_schema.triggers" +
                       " WHERE trigger_schema = 'db-practica'" +
                       " AND trigger_name = 'trigger_insert_auditoria_save'";
        Query verificationQuery = entityManager.createNativeQuery(query);
        try {
            verificationQuery.getSingleResult(); 
            return true; 
        } catch (NoResultException ex) {
            return false;
        }
    }
    
    public boolean verificarTriggerUpdate() {
        String query = "SELECT trigger_name" +
                       " FROM information_schema.triggers" +
                       " WHERE trigger_schema = 'db-practica'" +
                       " AND trigger_name = 'trigger_insert_auditoria_update'";
        Query verificationQuery = entityManager.createNativeQuery(query);
        try {
            verificationQuery.getSingleResult(); 
            return true; 
        } catch (NoResultException ex) {
            return false; 
        }
    }
}
