package com.bank.pix.dto;

public class ContaDTO {

    private Integer id;
    private Integer agencia;
    private Double saldo;
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
}
