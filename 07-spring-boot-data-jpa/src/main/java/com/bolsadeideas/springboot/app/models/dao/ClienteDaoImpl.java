package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.stereotype.Repository;

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
    @Override
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    @Override
    public Cliente findOne(Long id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    public void save(Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0) {
            entityManager.merge(cliente);
        } else {
            entityManager.persist(cliente);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findOne(id));
    }

}
