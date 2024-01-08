package com.bank.pix.controller;

import com.bank.pix.dto.ContaDTO;
import com.bank.pix.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/novaconta")
@RequiredArgsConstructor
public class AberturaContasController {

    @Autowired
    private final ContaService contaService;

    @PostMapping
    public ContaDTO abrirConta(@RequestBody ContaDTO contaDTO) {

        return contaService.salvarConta(contaDTO);
    }

}
