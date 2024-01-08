package com.bank.pix.service;

import com.bank.pix.dto.PixKeyDTO;
import com.bank.pix.model.Pixkey;
import com.bank.pix.repository.PixKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PixkeyService {

    @Autowired
    private final PixKeyRepository pixKeyRepository;

    public PixKeyDTO salvarPixKey(PixKeyDTO pixKeyDTO) {
        pixKeyRepository.save(Pixkey.toEntity(pixKeyDTO));
        //pixKeyRepository. save(pixKeyDTO);
        return pixKeyDTO;
    }

}
