package com.bank.pix.service;

import com.bank.pix.dto.ClienteDTO;
import com.bank.pix.model.Cliente;
import com.bank.pix.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        clienteRepository.save(Cliente.toEntity(clienteDTO));
        return clienteDTO;
    }

}
