package com.bank.pix.model;

import com.bank.pix.dto.ClienteDTO;
import com.bank.pix.dto.ContaDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    private String cpfcnpj;
    private String endereco;
    private String senha;

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

    public static Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setCpfcnpj(clienteDTO.getCpfcnpj());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setSenha(clienteDTO.getSenha());
        return cliente;
    }

}
