package com.bank.pix.service;

import com.bank.pix.dto.ContaDTO;
import com.bank.pix.model.Conta;
import com.bank.pix.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {

    @Autowired
    private final ContaRepository contaRepository;

    public ContaDTO salvarConta(ContaDTO contaDTO) {
        contaRepository.save(Conta.toEntity(contaDTO));
        return contaDTO;
    }
}
