package com.sicredi.credito.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "operacao_credito")
@Data
public class OperacaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long idAssociado;

    private Double valorOperacao;

    private String segmento;

    private String codigoProdutoCredito;

    private String codigoConta;

    private Double areaBeneficiadaHa;

    private LocalDateTime dataHoraContratacao;
}