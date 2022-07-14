package ec.edu.espe.pruebaserver.service;

import ec.edu.espe.pruebaserver.dao.CuentaRepository;
import ec.edu.espe.pruebaserver.dao.TransaccionRepository;
import ec.edu.espe.pruebaserver.enums.EstadoTransaccionEnum;
import ec.edu.espe.pruebaserver.exception.NotFoundException;
import ec.edu.espe.pruebaserver.model.Cuenta;
import ec.edu.espe.pruebaserver.model.Transaccion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final CuentaRepository cuentaRepository;

    private static final String BASE_URL = "http://localhost:8080/nombre";

    private final RestTemplate restTemplate;

    public TransaccionService() {
        this.restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }

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

    private Cuenta existeCuenta(Integer cuenta) throws NotFoundException {
        return cuentaRepository
                .findByIdInterno(cuenta)
                .orElseThrow(() -> new NotFoundException("La cuenta indicada no existe"));
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        int connectTimeout = 5000;
        int readTimeout = 5000;
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        return clientHttpRequestFactory;
    }

}
