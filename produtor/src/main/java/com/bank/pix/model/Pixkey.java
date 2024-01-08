package com.bank.pix.model;


import com.bank.pix.dto.PixKeyDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "Pixkey")
public class Pixkey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String chave;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Integer getId() {
        return id;
    }

    public String getChave() {
        return chave;
    }

    public static Pixkey toEntity(PixKeyDTO pixKeyDTO) {
        Pixkey pixkey = new Pixkey();
        pixkey.setChave(pixKeyDTO.getChave());
        return pixkey;
    }
}
