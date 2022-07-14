package ec.edu.espe.pruebaserver.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Document(collection = "transacciones")
@TypeAlias("transacciones")
public class Transaccion {

  @Id private String id;

  @Indexed(name = "idxu_transacciones_idInterno", unique = true)
  private String idInterno;

  private Date fecha;

  private Integer cuentaOrigen;

  private Integer cuentaDestino;

  private BigDecimal valor;

  private String estado;
}
