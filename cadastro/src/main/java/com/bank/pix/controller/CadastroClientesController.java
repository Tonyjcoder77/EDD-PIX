package com.bank.pix.controller;

import com.bank.pix.dto.ClienteDTO;
import com.bank.pix.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
@RequiredArgsConstructor
public class CadastroClientesController {

    private final ClienteService clienteService;

    @PostMapping
    public ClienteDTO cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {

        return clienteService.salvarCliente(clienteDTO);
    }

}
