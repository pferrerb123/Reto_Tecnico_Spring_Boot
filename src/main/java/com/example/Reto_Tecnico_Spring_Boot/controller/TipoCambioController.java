package com.example.Reto_Tecnico_Spring_Boot.controller;

import com.example.Reto_Tecnico_Spring_Boot.model.CambioResponse;
import com.example.Reto_Tecnico_Spring_Boot.model.TipoCambio;
import com.example.Reto_Tecnico_Spring_Boot.model.ErrorResponse;
import com.example.Reto_Tecnico_Spring_Boot.model.TipoCambioException;
import com.example.Reto_Tecnico_Spring_Boot.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-cambio")
public class TipoCambioController {

    private final TipoCambioService tipoCambioService;

    @Autowired
    public TipoCambioController(TipoCambioService tipoCambioService) {
        this.tipoCambioService = tipoCambioService;
    }

    @GetMapping("/aplicar")
    public Mono<ResponseEntity<CambioResponse>> aplicarTipoCambio(
            @RequestParam BigDecimal monto,
            @RequestParam String monedaOrigen,
            @RequestParam String monedaDestino) {

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TipoCambioException("El monto debe ser mayor que cero.");
        }

        return tipoCambioService.aplicarTipoCambio(monto, monedaOrigen, monedaDestino)
                .map(cambioResponse -> ResponseEntity.ok(cambioResponse))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)));
    }

    @PostMapping("/actualizar")
    public Mono<ResponseEntity<TipoCambio>> actualizarTipoCambio(@RequestBody TipoCambio tipoCambio) {
        // Verificar que el ID sea positivo
        return Optional.ofNullable(tipoCambio.getId())
                .filter(id -> id > 0)
                .map(id -> tipoCambioService.actualizarTipoCambio(tipoCambio)
                        .map(updatedTipoCambio -> ResponseEntity.ok(updatedTipoCambio))
                        .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)))
                )
                .orElseGet(() -> Mono.just(ResponseEntity.badRequest().body(null))); // ID no válido
    }

}