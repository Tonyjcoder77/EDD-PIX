package com.bank.pix.consumidor;

import com.bank.pix.dto.PixDTO;
import com.bank.pix.dto.PixStatus;
import com.bank.pix.dto.Status;
import com.bank.pix.model.Conta;
import com.bank.pix.model.Pix;
import com.bank.pix.model.Pixkey;
import com.bank.pix.repository.ContaRepository;
import com.bank.pix.repository.PixKeyRepository;
import com.bank.pix.repository.PixRepository;
import com.bank.pix.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PixValidator {

    @Autowired
    private PixKeyRepository pixKeyRepository;

    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private EmailSenderService senderService;

    @KafkaListener(topics = "pix-topic", groupId = "grupo")
    public void processaPix(PixDTO pixDTO) {
        System.out.println("Pix  recebido: " + pixDTO.getIdentifier());

        Pix pix = creditaContaDestino(pixDTO);

        pixRepository.save(pix);
        senderService.sendSimpleEmail("user444@gmail.com",
                "PIX concluido",
                "Tranferencia de valor via PIX concluida");
    }

    public Pix creditaContaDestino(PixDTO pixDTO) {

        Pixkey ctaOrigem = pixKeyRepository.findByChave(pixDTO.getChaveOrigem());
        Pixkey ctaDestino = pixKeyRepository.findByChave(pixDTO.getChaveDestino());
        if (ctaDestino == null) {
            revertDebitoDaContaOrigem(ctaOrigem, pixDTO);
            pixDTO.setStatus(PixStatus.ERRO);
            return Pix.toEntity(pixDTO);
        }

        Optional<Conta> contaDestino = contaRepository.findById(ctaDestino.getId());
        if(contaDestino.isEmpty()){
            revertDebitoDaContaOrigem(ctaOrigem, pixDTO);
            pixDTO.setStatus(PixStatus.CONTA_INEXISTENTE);
            return Pix.toEntity(pixDTO);
        }

        if(contaDestino.get().getStatus().equals(Status.Inativa)){
            revertDebitoDaContaOrigem(ctaOrigem, pixDTO);
            pixDTO.setStatus(PixStatus.CONTA_INATIVA);
            return Pix.toEntity(pixDTO);
        }

        Double saldo = contaDestino.get().getSaldo();
        Double newSaldo = saldo + pixDTO.getValor();
        contaDestino.get().setSaldo(newSaldo);
        contaRepository.save(contaDestino.get());

        pixDTO.setStatus(PixStatus.PROCESSADO);
        return Pix.toEntity(pixDTO);
    }

    public Boolean revertDebitoDaContaOrigem(Pixkey ctaOrigem, PixDTO pixDTO){
        Optional<Conta> contaOrigem = contaRepository.findById(ctaOrigem.getId());
        if(contaOrigem.isPresent()) {
            Double saldo = contaOrigem.get().getSaldo();
            Double newSaldo = saldo + pixDTO.getValor();
            contaOrigem.get().setSaldo(newSaldo);
            contaRepository.save(contaOrigem.get());
            return true;
        }
        return false;
    }

}
