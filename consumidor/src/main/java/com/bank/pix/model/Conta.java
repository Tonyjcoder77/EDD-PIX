package com.bank.pix.model;

import com.bank.pix.dto.ContaDTO;
import com.bank.pix.dto.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "Conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer agencia;
    private Double saldo;
    @Enumerated(EnumType.STRING)
    private Status status;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Status getStatus() {
        return status;
    }

    public static Conta toEntity(ContaDTO contaDTO) {
        Conta conta = new Conta();
        conta.setAgencia(contaDTO.getAgencia());
        conta.setStatus(contaDTO.getStatus());
        conta.setSaldo(contaDTO.getSaldo());
        return conta;
    }

}
