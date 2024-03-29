package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

// Al extender también viene integrado la anotación Repository
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

}
