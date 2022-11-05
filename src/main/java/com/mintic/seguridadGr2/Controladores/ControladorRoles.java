package com.mintic.seguridadGr2.Controladores;

import com.mintic.seguridadGr2.Modelos.Roles;
import com.mintic.seguridadGr2.Repositorios.RepositorioRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class ControladorRoles {
    @Autowired
    private RepositorioRoles miRepositorioRoles;

    @GetMapping("")
    public List<Roles> index() {
        return this.miRepositorioRoles.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Roles create(@RequestBody Roles infoRoles) {
        return this.miRepositorioRoles .save(infoRoles);
    }

    @PutMapping("{id}")
    public Roles update(@PathVariable String id, @RequestBody Roles infoRoles) {
        Roles rol = this.miRepositorioRoles .findById(id).orElse(null);
        if (rol != null) {
            rol.setNombre(infoRoles.getNombre());
            rol.setDescripcion(infoRoles.getDescripcion());
            return this.miRepositorioRoles.save(rol);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Roles rol = this.miRepositorioRoles.findById(id).orElse(null);
        if (rol != null) {
            this.miRepositorioRoles.delete(rol);
        }
    }

    @GetMapping("{id}")
    public Roles show(@PathVariable String id) {
        Roles rol = this.miRepositorioRoles.findById(id).orElse(null);
        return rol;
    }


}
