package com.sicredi.credito.service;

import com.sicredi.credito.client.ProdutoClient;
import com.sicredi.credito.dto.ContratacaoRequest;
import com.sicredi.credito.entity.OperacaoCredito;
import com.sicredi.credito.repository.OperacaoCreditoRepository;
import com.sicredi.credito.repository.OperacaoPJRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreditoServiceTest {

    private final OperacaoCreditoRepository repository = Mockito.mock(OperacaoCreditoRepository.class);
    private final OperacaoPJRepository pjRepository = Mockito.mock(OperacaoPJRepository.class);
    private final ProdutoClient produtoClient = Mockito.mock(ProdutoClient.class);

    private final CreditoService service = new CreditoService(repository, pjRepository, produtoClient);

    @Test
    void devePermitirContratacaoQuandoProdutoValido() {

        ContratacaoRequest request = new ContratacaoRequest();
        request.setSegmento("AGRO");
        request.setValorOperacao(3000.0);
        request.setCodigoProdutoCredito("903C");
        request.setAreaBeneficiadaHa(10.0);

        // Mock da API externa
        Mockito.when(produtoClient.permiteContratacao(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyDouble()
        )).thenReturn(Map.of("permiteContratar", true));

        // 🔥 Mock do save (ESSENCIAL)
        Mockito.when(repository.save(Mockito.any(OperacaoCredito.class)))
                .thenAnswer(invocation -> {
                    OperacaoCredito op = invocation.getArgument(0);
                    op.setId("123"); // simula ID gerado
                    return op;
                });

        String id = service.contratar(request);

        assertNotNull(id);
        assertEquals("123", id);
    }

    @Test
    void deveLancarErroQuandoProdutoNaoPermite() {

        ContratacaoRequest request = new ContratacaoRequest();
        request.setSegmento("AGRO");
        request.setValorOperacao(3000.0);
        request.setCodigoProdutoCredito("903C");
        request.setAreaBeneficiadaHa(10.0);

        Mockito.when(produtoClient.permiteContratacao(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyDouble()
        )).thenReturn(Map.of("permiteContratar", false));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.contratar(request);
        });

        assertEquals("Produto não permite contratação", exception.getMessage());
    }
}