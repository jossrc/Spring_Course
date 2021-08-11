package com.bolsadeideas.springboot.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "clientes") // Opcional, si no va el nombre de la clase es el de la tabla
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // Indica que es llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identificamos que será identity
    private Long id;

    @NotEmpty // Para String
    @Size(min = 3, max = 30)
    private String nombre;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String apellido;

    @NotEmpty
    @Email
    private String email;

    @NotNull // Para object
    @Column(name="create_at") // Indicamos que la columna tiene otro nombre, en caso contrario será igual a la propiedad
    @Temporal(TemporalType.DATE) // Indica el formato que tendrá la fecha a guardar en la BD
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    private String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

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
