package com.sicredi.credito.service;

import com.sicredi.credito.client.ProdutoClient;
import com.sicredi.credito.dto.ContratacaoRequest;
import com.sicredi.credito.entity.OperacaoCredito;
import com.sicredi.credito.entity.OperacaoPJ;
import com.sicredi.credito.exception.ProdutoServiceException;
import com.sicredi.credito.repository.OperacaoCreditoRepository;
import com.sicredi.credito.repository.OperacaoPJRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final OperacaoCreditoRepository repository;
    private final OperacaoPJRepository pjRepository;
    private final ProdutoClient produtoClient;

    public String contratar(ContratacaoRequest request) {

        // 🔹 Regra AGRO (obrigatória no desafio)
        if ("AGRO".equals(request.getSegmento())) {
            if (request.getAreaBeneficiadaHa() == null || request.getAreaBeneficiadaHa() <= 0) {
                throw new RuntimeException("Área beneficiada inválida");
            }
        }

        // 🔹 Criar entidade
        OperacaoCredito op = new OperacaoCredito();
        BeanUtils.copyProperties(request, op);
        op.setDataHoraContratacao(LocalDateTime.now());

        Boolean permite;

        try {
            var response = produtoClient.permiteContratacao(
                    request.getCodigoProdutoCredito(),
                    request.getSegmento(),
                    request.getValorOperacao()
            );
            permite = response.get("permiteContratar");

        } catch (Exception e) {
            throw new ProdutoServiceException("Erro ao consultar serviço de produtos");
        }

        if (!permite) {
            throw new RuntimeException("Produto não permite contratação");
        }

        repository.save(op);

        if ("PJ".equals(request.getSegmento())) {
            OperacaoPJ pj = new OperacaoPJ();
            pj.setOperacaoId(op.getId());
            pj.setIdAssociado(request.getIdAssociado());
            pjRepository.save(pj);
        }

        return op.getId();
    }

    public OperacaoCredito buscarPorId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operação não encontrada"));
    }
}