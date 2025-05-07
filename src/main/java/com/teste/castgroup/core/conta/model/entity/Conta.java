package com.teste.castgroup.core.conta.model.entity;

import com.teste.castgroup.core.agencia.model.entity.Agencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "conta")
public class Conta {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name="agencia_id", nullable=false)
    private Agencia agencia;

    private String numero;
    @Column(nullable = false, columnDefinition="DECIMAL(7,2) DEFAULT 0.00")
    private BigDecimal saldo;


    public Conta criar(Agencia agencia, String numero){
        this.id = UUID.randomUUID();
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = BigDecimal.ZERO;

        return this;
    }

    public Conta creditar(BigDecimal valor){
        this.saldo = saldo.add(valor);
        return this;
    }

    public Conta debitar(BigDecimal valor){
        this.saldo = saldo.subtract(valor);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
