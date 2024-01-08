package com.bank.pix.repository;

import com.bank.pix.model.Pixkey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PixKeyRepository extends JpaRepository<Pixkey, Integer> {
    Optional<Pixkey> findByChave(String key);
}
