package com.sicredi.credito.entity;

import com.sicredi.credito.enums.Segmento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "operacao_credito")
@Getter
@Setter
public class OperacaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "id_associado", nullable = false)
    private Long idAssociado;

    @Column(name = "valor_operacao", nullable = false)
    private Double valorOperacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "segmento", nullable = false)
    private Segmento segmento;

    @Column(name = "codigo_produto_credito", nullable = false)
    private String codigoProdutoCredito;

    @Column(name = "codigo_conta", nullable = false)
    private String codigoConta;

    @Column(name = "area_beneficiada_ha")
    private Double areaBeneficiadaHa;

    @Column(name = "data_hora_contratacao", nullable = false)
    private LocalDateTime dataHoraContratacao;
}