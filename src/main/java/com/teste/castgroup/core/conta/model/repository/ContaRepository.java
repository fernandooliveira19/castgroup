package com.teste.castgroup.core.conta.model.repository;

import com.teste.castgroup.core.conta.model.entity.Conta;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    @Lock(value= LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM conta c INNER JOIN c.agencia ag WHERE c.numero = :numeroConta AND ag.codigo = :codigoAgencia")
    Optional<Conta> findByAgenciaAndNumero(@Param("codigoAgencia") String codigoAgencia, @Param("numeroConta") String numero);

}
