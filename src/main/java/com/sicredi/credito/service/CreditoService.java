package com.sicredi.credito.service;

import com.sicredi.credito.dto.ContratacaoRequest;
import com.sicredi.credito.entity.OperacaoCredito;

public interface CreditoService {
    String contratar(ContratacaoRequest request);
    OperacaoCredito buscarPorId(String id);
}