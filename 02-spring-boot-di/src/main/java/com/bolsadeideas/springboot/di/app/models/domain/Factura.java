package com.bolsadeideas.springboot.di.app.models.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


/**
 * El RequestScope
 * Se recomienda usarlo cuando no queremos que sea del tipo singleton
 * y que no tenga atributos del usuario ya que cada vez que se actualiza
 * la página, el o los objetos se construirán en la request y estarán propensos
 * a ser modificados.
 * Nuestro componente se crea al iniciar la request y se destruye al actualizar.
 * Es atómico y único por cada usuario que se registra, así que al Cliente
 * también se le debe agregar para evitar que se repitan los datos en la Factura
 * ya que se destruirá y volverá a construirse.
 *
 */
@Component
@RequestScope
public class Factura {

    // En vez de RequestScope también se puede usar @SessionScope para que se
    // almacene en la session y tendrá que ser Serializable (implements Serializable)

    @Value("${factura.descripcion}")
    private String descripcion;

    @Autowired
    private Cliente cliente;

    @Autowired // Proveniente del AppConfig
    @Qualifier("default")
    private List<ItemFactura> items;

    /**
     * El @PostConstruct permite hacer una acción después de que el objeto se haya construido
     */
    @PostConstruct
    public void inicializar() {
        cliente.setNombre(cliente.getNombre().concat(" ").concat("Manuel"));
        // Los strings son inmutables, se recomienda crear uno nuevo e instanciar
        descripcion = descripcion.concat(" del cliente: ").concat(cliente.getNombre());
    }

    /**
     * Por defecto es Singleton y dura hasta que finalice nuestra app
     * Cuando se usa el SessionScope no se usa el preDestroy ya que
     * el servlet es el que lo controla
     */
    @PreDestroy
    public void destruir() {
        // Antes de que se destruya que imprima en consola
        System.out.println("Factura destruida: ".concat(descripcion));
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }
}
