package com.mintic.seguridadGr2.Controladores;

import com.mintic.seguridadGr2.Modelos.Permisos;
import com.mintic.seguridadGr2.Modelos.PermisosRoles;
import com.mintic.seguridadGr2.Modelos.Roles;
import com.mintic.seguridadGr2.Repositorios.RepositorioPermisos;
import com.mintic.seguridadGr2.Repositorios.RepositorioPermisosRoles;
import com.mintic.seguridadGr2.Repositorios.RepositorioRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos-roles")
public class ControladorPermisoRoles {
    @Autowired
    private RepositorioPermisosRoles miRepositorioPermisosRoles;

    @Autowired
    private RepositorioPermisos miRepositorioPermiso;

    @Autowired
    private RepositorioRoles miRepositorioRol;

    @GetMapping("")
    public List<PermisosRoles> index() {
        return miRepositorioPermisosRoles.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles create(@PathVariable String id_rol, @PathVariable String id_permiso) {
        PermisosRoles permisosRoles = new PermisosRoles();
        Roles rol = this.miRepositorioRol.findById(id_rol).orElse(null);
        Permisos permiso = this.miRepositorioPermiso.findById(id_permiso).orElse(null);
        if (rol != null && permiso != null) {
            permisosRoles.setRoles(rol);
            permisosRoles.setPermisos(permiso);
            return this.miRepositorioPermisosRoles.save(permisosRoles);
        } else {
            return null;
        }
    }

    @PutMapping("{id_permiso_rol}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles update(@PathVariable String id_permiso_rol, @PathVariable String id_rol, @PathVariable String id_permiso) {
        PermisosRoles permisosRoles = this.miRepositorioPermisosRoles.findById(id_permiso_rol).orElse(null);
        Roles rol = this.miRepositorioRol.findById(id_rol).orElse(null);
        Permisos permiso = this.miRepositorioPermiso.findById(id_permiso).orElse(null);

        if (permisosRoles != null && rol != null && permiso != null) {
            permisosRoles.setRoles(rol);
            permisosRoles.setPermisos(permiso);
            return this.miRepositorioPermisosRoles.save(permisosRoles);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        PermisosRoles permisosRoles = this.miRepositorioPermisosRoles.findById(id).orElse(null);
        if (permisosRoles != null) {
            this.miRepositorioPermisosRoles.delete(permisosRoles);
        }
    }

    @GetMapping("{id}")
    public PermisosRoles show(@PathVariable String id) {
        PermisosRoles permisosRoles = this.miRepositorioPermisosRoles.findById(id).orElse(null);
        return permisosRoles;
    }


}
