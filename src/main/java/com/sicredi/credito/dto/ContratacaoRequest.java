package com.sicredi.credito.dto;

import com.sicredi.credito.enums.Segmento;
import lombok.Data;

@Data
public class ContratacaoRequest {

    private Long idAssociado;
    private Double valorOperacao;
    private Segmento segmento;
    private String codigoProdutoCredito;
    private String codigoConta;
    private Double areaBeneficiadaHa;
}