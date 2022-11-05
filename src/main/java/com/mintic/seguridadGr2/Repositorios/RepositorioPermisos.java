package com.mintic.seguridadGr2.Repositorios;

import com.mintic.seguridadGr2.Modelos.Permisos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermisos extends MongoRepository<Permisos, String> {
}
