package com.sicredi.credito.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "operacao_pj")
@Data
public class OperacaoPJ {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String operacaoId;

    private Long idAssociado;
}