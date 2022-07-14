package ec.edu.espe.pruebaserver.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@Document(collection = "cuentas")
@TypeAlias("cuentas")
public class Cuenta {

  @Id private String id;

  @Indexed(name = "idxu_cuentas_idInterno", unique = true)
  private Integer idInterno;

  private String idCliente;

  private BigInteger saldo;

  private Date fecha;

  private Date ultimoMovimiento;

  private String estado;
}
