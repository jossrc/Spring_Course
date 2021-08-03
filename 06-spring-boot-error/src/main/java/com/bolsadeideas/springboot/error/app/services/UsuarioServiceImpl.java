package com.bolsadeideas.springboot.error.app.services;

import com.bolsadeideas.springboot.error.app.models.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Permite inyectar en el controlador
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private List<Usuario> lista;

    public UsuarioServiceImpl() {
        this.lista = new ArrayList<>();
        this.lista.add(new Usuario(1, "Andrés", "Guzmán"));
        this.lista.add(new Usuario(2, "Pepa", "García"));
        this.lista.add(new Usuario(3, "Lalo", "Mena"));
        this.lista.add(new Usuario(4, "Lucy", "Fernández"));
        this.lista.add(new Usuario(5, "Pato", "González"));
        this.lista.add(new Usuario(6, "Paco", "Rodríguez"));
        this.lista.add(new Usuario(7, "José", "Robles"));
    }

    @Override
    public List<Usuario> listar() {
        return this.lista;
    }

    @Override
    public Usuario obtenerPorId(Integer id) {

        Usuario resultado = this.lista.stream()
                .filter( usuario -> Objects.equals(usuario.getId(), id))
                .findFirst().orElse(null);// solo porque es un objeto sino usar ==

        return resultado;
    }

    @Override
    public Optional<Usuario> obtenerPorIdOptional(Integer id) {

        Usuario usuario = this.obtenerPorId(id);
        return Optional.ofNullable(usuario); // acepta nulos y retorna un objeto optional
    }
}
