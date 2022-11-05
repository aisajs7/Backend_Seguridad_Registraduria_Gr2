package com.mintic.seguridadGr2.Repositorios;

import com.mintic.seguridadGr2.Modelos.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRoles extends MongoRepository<Roles, String> {
}
