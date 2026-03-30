package com.sicredi.credito.dto;

import lombok.Data;

@Data
public class ContratacaoRequest {

    private Long idAssociado;
    private Double valorOperacao;
    private String segmento;
    private String codigoProdutoCredito;
    private String codigoConta;
    private Double areaBeneficiadaHa;
}