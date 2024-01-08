package com.bank.pix.dto;

public class ContaDTO {

    private Integer id;
    private Integer agencia;
    private Integer saldo;
    private Status status;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public void setSaldo(Integer saldo) {
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

    public Integer getSaldo() {
        return saldo;
    }

    public Status getStatus() {
        return status;
    }
}
