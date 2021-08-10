package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Evita tener que accederlo desde el controlador
// También podemos manejar las transacciones acá en vez del DAO
// En la clase service se puede interactuar con otros DAOS
@Service("clienteService") // Está basado en el patrón Fachada, es igual que Component solo que para el patron Facade
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        // El findAll retorna un Iterable
        return (List<Cliente>) clienteDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        // Retorna lista en páginas
        return clienteDAO.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        // El findById retorna un Optional<T>
        return clienteDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDAO.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDAO.deleteById(id);
    }
}
