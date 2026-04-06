package com.sicredi.credito.service;

import com.sicredi.credito.client.ProdutoClient;
import com.sicredi.credito.dto.ContratacaoRequest;
import com.sicredi.credito.entity.OperacaoCredito;
import com.sicredi.credito.entity.OperacaoPJ;
import com.sicredi.credito.enums.Segmento;
import com.sicredi.credito.exception.ProdutoServiceException;
import com.sicredi.credito.repository.OperacaoCreditoRepository;
import com.sicredi.credito.repository.OperacaoPJRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreditoServiceImpl implements CreditoService {

    private final OperacaoCreditoRepository operacaoRepository;
    private final OperacaoPJRepository pjRepository;
    private final ProdutoClient produtoClient;

    @Transactional
    @Override
    public String contratar(ContratacaoRequest request) {

        validarAgro(request);

        validarProduto(request);

        OperacaoCredito op = criarOperacao(request);

        operacaoRepository.save(op);

        salvarSePJ(request, op);

        return op.getId();
    }

    @Override
    public OperacaoCredito buscarPorId(String id) {
        return operacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Operação não encontrada"));
    }

    private void validarAgro(ContratacaoRequest request) {
        if (Segmento.AGRO.equals(request.getSegmento())) {
            if (request.getAreaBeneficiadaHa() == null || request.getAreaBeneficiadaHa() <= 0) {
                throw new RuntimeException("Área beneficiada inválida");
            }
        }
    }

    private void validarProduto(ContratacaoRequest request) {
        Boolean permite;

        try {
            var response = produtoClient.permiteContratacao(
                    request.getCodigoProdutoCredito(),
                    request.getSegmento().name(),
                    request.getValorOperacao()
            );

            if (response == null || !response.containsKey("permiteContratar")) {
                throw new ProdutoServiceException("Resposta inválida do serviço de produtos");
            }

            permite = response.get("permiteContratar");

        } catch (Exception e) {
            throw new ProdutoServiceException("Erro ao consultar serviço de produtos", e);
        }

        if (!Boolean.TRUE.equals(permite)) {
            throw new IllegalArgumentException("Produto não permite contratação");
        }
    }

    private OperacaoCredito criarOperacao(ContratacaoRequest request) {
        OperacaoCredito op = new OperacaoCredito();
        BeanUtils.copyProperties(request, op);
        op.setDataHoraContratacao(LocalDateTime.now());
        return op;
    }

    private void salvarSePJ(ContratacaoRequest request, OperacaoCredito op) {
        if (Segmento.PJ.equals(request.getSegmento())) {
            OperacaoPJ pj = new OperacaoPJ();
            pj.setOperacaoId(op.getId());
            pj.setIdAssociado(request.getIdAssociado());
            pjRepository.save(pj);
        }
    }
}