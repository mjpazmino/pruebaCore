package ec.edu.espe.pruebaserver.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EstadoTransaccionEnum {

  EJECUTADO("EJE", "Ejecutado"),
  BLOQUEADO("BLO", "Bloqueado");

  private final String value;
  private final String text;
}
