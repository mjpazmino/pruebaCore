package ec.edu.espe.pruebaserver.dao;

import ec.edu.espe.pruebaserver.model.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TransaccionRepository extends MongoRepository<Transaccion, String> {

    List<Transaccion> findByFechaBetween (Date inicio, Date fin);
}
