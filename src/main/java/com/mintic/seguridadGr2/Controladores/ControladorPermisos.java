package com.mintic.seguridadGr2.Controladores;

import com.mintic.seguridadGr2.Modelos.Permisos;
import com.mintic.seguridadGr2.Repositorios.RepositorioPermisos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos")
public class ControladorPermisos {
    @Autowired
    private RepositorioPermisos miRepositorioPermisos;

    @GetMapping("")
    public List<Permisos> index() {
        return this.miRepositorioPermisos.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permisos create(@RequestBody Permisos infoPermiso) {
        return this.miRepositorioPermisos.save(infoPermiso);
    }

    @PutMapping("{id}")
    public Permisos update(@PathVariable String id, @RequestBody Permisos infoPermiso) {
        Permisos permiso = this.miRepositorioPermisos.findById(id).orElse(null);
        if (permiso != null) {
            permiso.setUrl(infoPermiso.getUrl());
            permiso.setMetodo(infoPermiso.getMetodo());
            return this.miRepositorioPermisos.save(permiso);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Permisos permiso = this.miRepositorioPermisos.findById(id).orElse(null);
        if (permiso != null) {
            this.miRepositorioPermisos.delete(permiso);
        }
    }

    @GetMapping("{id}")
    public Permisos show(@PathVariable String id) {
        Permisos permiso = this.miRepositorioPermisos.findById(id).orElse(null);
        return permiso;
    }
}
