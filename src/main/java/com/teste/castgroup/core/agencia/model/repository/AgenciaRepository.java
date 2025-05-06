package com.teste.castgroup.core.agencia.model.repository;

import com.teste.castgroup.core.agencia.model.entity.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, UUID> {

    Optional<Agencia> findByCodigo(String codigo);
}
