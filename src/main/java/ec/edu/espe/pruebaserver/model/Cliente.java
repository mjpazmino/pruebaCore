package ec.edu.espe.pruebaserver.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "clientes")
@TypeAlias("clientes")
public class Cliente {

  @Id private String id;

  @Indexed(name = "idxu_clientes_cedula", unique = true)
  private String cedula;

  private String nombreCompleto;

  private String estado;
}
