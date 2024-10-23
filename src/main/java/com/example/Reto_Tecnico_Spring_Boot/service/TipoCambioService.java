package com.example.Reto_Tecnico_Spring_Boot.service;

import com.example.Reto_Tecnico_Spring_Boot.model.CambioResponse;
import com.example.Reto_Tecnico_Spring_Boot.model.TipoCambio;
import com.example.Reto_Tecnico_Spring_Boot.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class TipoCambioService {

    private final TipoCambioRepository tipoCambioRepository;

    @Autowired  // Verifica que este campo sea inyectado
    public TipoCambioService(TipoCambioRepository tipoCambioRepository) {
        this.tipoCambioRepository = tipoCambioRepository;
    }

    public Mono<CambioResponse> aplicarTipoCambio(BigDecimal monto, String monedaOrigen, String monedaDestino) {
        return tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino)
                .switchIfEmpty(Mono.error(new RuntimeException("Tipo de cambio no encontrado para las monedas: "
                        + monedaOrigen + " a " + monedaDestino)))
                .map(tipoCambio -> {
                    BigDecimal montoConTipoCambio = monto.multiply(tipoCambio.getTipoCambio());
                    return new CambioResponse(monto, montoConTipoCambio, monedaOrigen, monedaDestino, tipoCambio.getTipoCambio());
                });
    }

    public Mono<TipoCambio> actualizarTipoCambio(TipoCambio tipoCambio) {
        return tipoCambioRepository.findById(tipoCambio.getId())
                .flatMap(existing -> {
                    existing.setMonedaOrigen(tipoCambio.getMonedaOrigen());
                    existing.setMonedaDestino(tipoCambio.getMonedaDestino());
                    existing.setTipoCambio(tipoCambio.getTipoCambio());
                    return tipoCambioRepository.save(existing);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Tipo de cambio no encontrado"))); // Mensaje personalizado
    }
}