package com.mintic.seguridadGr2.Repositorios;

import com.mintic.seguridadGr2.Modelos.PermisosRoles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermisosRoles extends MongoRepository<PermisosRoles, String> {
}
