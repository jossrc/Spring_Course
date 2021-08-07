package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

// Al extender también viene integrado la anotación Repository
public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
