package com.sicredi.credito.controller;

import com.sicredi.credito.dto.ContratacaoRequest;
import com.sicredi.credito.dto.ContratacaoResponse;
import com.sicredi.credito.dto.OperacaoResponse;
import com.sicredi.credito.service.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credito")
@RequiredArgsConstructor
public class CreditoController {

    private final CreditoService service;

    @PostMapping
    public ContratacaoResponse contratar(@RequestBody ContratacaoRequest request) {
        String id = service.contratar(request);
        return new ContratacaoResponse(id);
    }

    @GetMapping("/{id}")
    public OperacaoResponse buscar(@PathVariable String id) {
        var op = service.buscarPorId(id);

        OperacaoResponse response = new OperacaoResponse();
        org.springframework.beans.BeanUtils.copyProperties(op, response);

        return response;
    }
}