package com.bank.pix.service;

import com.bank.pix.dto.PixDTO;
import com.bank.pix.dto.PixStatus;
import com.bank.pix.dto.Status;
import com.bank.pix.model.Conta;
import com.bank.pix.model.Pix;
import com.bank.pix.model.Pixkey;
import com.bank.pix.repository.ContaRepository;
import com.bank.pix.repository.PixKeyRepository;
import com.bank.pix.repository.PixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PixService {

    @Autowired
    private final PixRepository pixRepository;

    @Autowired
    private final PixKeyRepository pixKeyRepository;
    @Autowired
    private final ContaRepository contaRepository;

    @Autowired
    private final KafkaTemplate<String, PixDTO>  kafkaTemplate;
    @Autowired
    private EmailSenderService senderService;
    public Boolean processarPix(PixDTO pixDTO) {

        Optional<Pixkey> origem = pixKeyRepository.findByChave(pixDTO.getChaveOrigem());
        if(origem.isEmpty()){
            return false;
        }

        pixDTO.setIdentifier(UUID.randomUUID().toString());
        pixDTO.setDataTransferencia(LocalDateTime.now());
        Optional<Conta> conta = contaRepository.findById(origem.get().getId());

        if(conta.isEmpty()){
            pixDTO.setStatus(PixStatus.CONTA_INEXISTENTE);
            pixRepository.save(Pix.toEntity(pixDTO));
            return false;
        }

        if (conta.get().getStatus().equals(Status.Inativa)){
            pixDTO.setStatus(PixStatus.CONTA_INATIVA);
            pixRepository.save(Pix.toEntity(pixDTO));
            return false;
        }

        if (pixDTO.getValor() < pixDTO.getValor()){
            pixDTO.setStatus(PixStatus.SALDO_INSUFICIENTE);
            pixRepository.save(Pix.toEntity(pixDTO));
            return false;
        }

        Double saldo = conta.get().getSaldo();
        saldo = saldo - pixDTO.getValor();
        conta.get().setSaldo(saldo);
        contaRepository.save(conta.get());

        pixDTO.setStatus(PixStatus.EM_PROCESSAMENTO);
        pixRepository.save(Pix.toEntity(pixDTO));

        kafkaTemplate.send("pix-topic", pixDTO.getIdentifier(), pixDTO);
        senderService.sendSimpleEmail("user444@gmail.com",
                                      "PIX requisitado",
                                      "Transacao de tranferencia de valor via PIX");
        return true;
    }

}
