package com.teste.castgroup.core.agencia.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name="agencia")
public class Agencia {
    @Id
    private UUID id;

    private String codigo;

    public Agencia() {
    }

    public Agencia(UUID id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public Agencia criar(String codigo){
        this.id = UUID.randomUUID();
        this.codigo = codigo;

        return this;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
