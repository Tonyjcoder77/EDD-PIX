package com.bank.pix.model;


import com.bank.pix.dto.PixDTO;
import com.bank.pix.dto.PixStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pix")
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;
    private LocalDateTime dataTransferencia;
    @Enumerated(EnumType.STRING)
    private PixStatus status;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setChaveOrigem(String chaveOrigem) {
        this.chaveOrigem = chaveOrigem;
    }

    public void setChaveDestino(String chaveDestino) {
        this.chaveDestino = chaveDestino;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setDataTransferencia(LocalDateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public void setStatus(PixStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getChaveOrigem() {
        return chaveOrigem;
    }

    public String getChaveDestino() {
        return chaveDestino;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public PixStatus getStatus() {
        return status;
    }

    public static Pix toEntity(PixDTO pixDTO) {
        Pix pix = new Pix();
        pix.setIdentifier(pixDTO.getIdentifier());
        pix.setChaveDestino(pixDTO.getChaveDestino());
        pix.setStatus(pixDTO.getStatus());
        pix.setValor(pixDTO.getValor());
        pix.setDataTransferencia(pixDTO.getDataTransferencia());
        pix.setChaveOrigem(pixDTO.getChaveOrigem());
        return pix;
    }
}
