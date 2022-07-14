package ec.edu.espe.pruebaserver.dao;

import ec.edu.espe.pruebaserver.model.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CuentaRepository extends MongoRepository<Cuenta, String> {

    Optional<Cuenta> findByIdCliente(String idCliente);

    Optional<Cuenta> findByIdInterno(Integer idInterno);
}
