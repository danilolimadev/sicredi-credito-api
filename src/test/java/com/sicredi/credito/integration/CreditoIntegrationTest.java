package com.sicredi.credito.integration;

import com.sicredi.credito.client.ProdutoClient;
import com.sicredi.credito.dto.ContratacaoRequest;
import com.sicredi.credito.enums.Segmento;
import com.sicredi.credito.repository.OperacaoCreditoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditoIntegrationTest {

    @Autowired
    private com.sicredi.credito.service.CreditoService service;

    @Autowired
    private OperacaoCreditoRepository repository;

    @Test
    void deveCriarOperacaoComSucesso() {

        ContratacaoRequest request = new ContratacaoRequest();
        request.setIdAssociado(1L);
        request.setValorOperacao(3000.0);
        request.setSegmento(Segmento.AGRO);
        request.setCodigoProdutoCredito("903C");
        request.setCodigoConta("1234567890");
        request.setAreaBeneficiadaHa(10.0);

        String id = service.contratar(request);

        assertNotNull(id);

        var op = repository.findById(id);

        assertTrue(op.isPresent());
        assertEquals(3000.0, op.get().getValorOperacao());
    }
}