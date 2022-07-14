package ec.edu.espe.pruebaserver.dao;

import ec.edu.espe.pruebaserver.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByCedula(String cedula);

    Optional<Cliente> findByNombreCompleto(String nombreCompleto);
}
