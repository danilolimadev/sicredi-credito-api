package com.sicredi.credito.repository;

import com.sicredi.credito.entity.OperacaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacaoCreditoRepository extends JpaRepository<OperacaoCredito, String> {
}