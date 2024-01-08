package com.bank.pix.controller;

import com.bank.pix.dto.PixDTO;
import com.bank.pix.dto.PixStatus;
import com.bank.pix.service.PixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/pix")
@RequiredArgsConstructor
public class PixController {

    private final PixService pixService;

    @PostMapping
    public ResponseEntity<HttpStatus> salvarPix(@RequestBody PixDTO pixDTO) {

        if(pixService.processarPix(pixDTO)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
