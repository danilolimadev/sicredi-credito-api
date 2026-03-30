package com.sicredi.credito.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperacaoResponse {

    private String id;
    private Long idAssociado;
    private Double valorOperacao;
    private String segmento;
    private String codigoProdutoCredito;
    private String codigoConta;
    private Double areaBeneficiadaHa;
    private LocalDateTime dataHoraContratacao;
}