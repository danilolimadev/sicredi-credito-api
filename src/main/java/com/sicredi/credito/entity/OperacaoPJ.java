package com.sicredi.credito.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "operacao_pj")
@Getter
@Setter
public class OperacaoPJ {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "operacao_id", nullable = false)
    private String operacaoId;

    @Column(name = "id_associado", nullable = false)
    private Long idAssociado;
}