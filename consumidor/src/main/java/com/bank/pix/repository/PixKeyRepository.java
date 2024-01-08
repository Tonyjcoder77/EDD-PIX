package com.bank.pix.repository;

import com.bank.pix.model.Pixkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixKeyRepository extends JpaRepository<Pixkey, Integer> {
    Pixkey findByChave(String key);
}
