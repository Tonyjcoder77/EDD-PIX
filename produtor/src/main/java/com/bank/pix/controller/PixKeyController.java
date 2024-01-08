package com.bank.pix.controller;

import com.bank.pix.dto.PixKeyDTO;
import com.bank.pix.service.PixkeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pixkey")
@RequiredArgsConstructor
public class PixKeyController {

    private final PixkeyService pixkeyService;

    @PostMapping
    public PixKeyDTO salvarPixKey(@RequestBody PixKeyDTO pixKeyDTO) {

        return pixkeyService.salvarPixKey(pixKeyDTO);
    }
}
