package com.sicredi.credito.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "produtoClient", url = "https://desafio-credito-sicredi.wiremockapi.cloud")
public interface ProdutoClient {

    @GetMapping("/produtos-credito/{codigo}/permite-contratacao")
    Map<String, Boolean> permiteContratacao(
            @PathVariable String codigo,
            @RequestParam String segmento,
            @RequestParam Double valorFinanciado
    );
}