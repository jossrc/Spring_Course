package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "clientes") // Opcional, si no va el nombre de la clase es el de la tabla
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // Indica que es llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identificamos que será identity
    private Long id;

    private String nombre;
    private String apellido;
    private String email;

    // Indicamos que la columna tiene otro nombre, en caso contrario será igual a la propiedad
    @Column(name="create_at")
    @Temporal(TemporalType.DATE) // Indica el formato que tendrá la fecha a guardar en la BD
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
