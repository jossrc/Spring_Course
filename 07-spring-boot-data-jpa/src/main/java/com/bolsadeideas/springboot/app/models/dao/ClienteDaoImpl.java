package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; // Cuidado

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// Es una anotación de Spring para marcar la clase como un componente de persistencia de acceso a datos
@Repository("clienteDaoJPA") // extiende de Components
public class ClienteDaoImpl implements IClienteDao {

    // El EntityManager se encarga de manejar las clases de entidades (no a la bd sino a la entidad - clase)
    @PersistenceContext // Inyecta el EntityManager según la unidad de persistencia que contiene el dataSource, el JPA - usa H2 (requiere instalación de dependencia) en caso de no tener una BD en uso
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true) // Marcamos el método como transaccional, pero como es un listado se le pone como lectura
    @Override
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }
}
