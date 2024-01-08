package com.bank.pix.dto;

public class ClienteDTO {

    public String cpfcnpj;
    public String endereco;
    public String senha;

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getSenha() {
        return senha;
    }
}
