package com.example.Reto_Tecnico_Spring_Boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("tipo_cambio")
public class TipoCambio {

    @Id
    private long id;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;


}