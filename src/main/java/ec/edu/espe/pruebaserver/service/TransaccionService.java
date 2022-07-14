package ec.edu.espe.pruebaserver.service;

import ec.edu.espe.pruebaserver.dao.CuentaRepository;
import ec.edu.espe.pruebaserver.dao.TransaccionRepository;
import ec.edu.espe.pruebaserver.enums.EstadoTransaccionEnum;
import ec.edu.espe.pruebaserver.exception.NotFoundException;
import ec.edu.espe.pruebaserver.model.Cuenta;
import ec.edu.espe.pruebaserver.model.Transaccion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final CuentaRepository cuentaRepository;

    public void transferir(Integer cuentaOrigen, Integer cuentaDestino, BigDecimal valor) {
        Cuenta cuentaOrigenDB = existeCuenta(cuentaOrigen);
        Cuenta cuentaDestinoDB = existeCuenta(cuentaDestino);

        Transaccion nuevaTransaccion = Transaccion.builder()
                .idInterno(UUID.randomUUID().toString())
                .cuentaOrigen(cuentaOrigenDB.getIdInterno())
                .cuentaDestino(cuentaDestinoDB.getIdInterno())
                .fecha(new Date())
                .valor(valor)
                .estado(EstadoTransaccionEnum.EJECUTADO.getValue())
                .build();

        this.transaccionRepository.save(nuevaTransaccion);
    }

    private Cuenta existeCuenta(Integer cuenta)
            throws NotFoundException {
        return cuentaRepository
                .findByIdInterno(cuenta)
                .orElseThrow(() -> new NotFoundException("La cuenta indicada no existe"));
    }

}
