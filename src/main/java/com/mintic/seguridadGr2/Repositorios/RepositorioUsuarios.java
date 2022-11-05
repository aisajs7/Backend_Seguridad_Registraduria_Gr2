package com.mintic.seguridadGr2.Repositorios;

import com.mintic.seguridadGr2.Modelos.Usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuarios extends MongoRepository<Usuarios, String> {
}
