package com.bolsadeideas.springboot.form.app.services;

import com.bolsadeideas.springboot.form.app.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    private List<Role> roles;
    public RoleServiceImpl() {
        this.roles = new ArrayList<Role>();
        this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
        this.roles.add(new Role(2, "Usuario", "ROLE_USER"));
        this.roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
    }

    @Override
    public List<Role> listar() {
        return roles;
    }

    @Override
    public Role obtenerPorId(Integer id) {
        Role resultado = this.roles.stream()
                .filter(role -> Objects.equals(role.getId(), id))
                .findAny()
                .orElse(null);

        return resultado;
    }
}
