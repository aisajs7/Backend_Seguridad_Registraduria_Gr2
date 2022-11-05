package com.mintic.seguridadGr2.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PermisosRoles {
    @Id
    private String _id;
    @DBRef
    private Roles rol;
    @DBRef
    private Permisos permiso;

    public PermisosRoles() {
    }

    public String get_id() {
        return _id;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRoles(Roles rol) {
        this.rol = rol;
    }

    public Permisos getPermiso() {
        return permiso;
    }

    public void setPermisos(Permisos permiso) {
        this.permiso = permiso;
    }
}
