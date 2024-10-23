package com.example.Reto_Tecnico_Spring_Boot.repository;

import com.example.Reto_Tecnico_Spring_Boot.model.TipoCambio;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TipoCambioRepository extends R2dbcRepository<TipoCambio, Long> {
    Mono<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
