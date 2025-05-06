package com.teste.castgroup.core.agencia.model.response;

import java.util.UUID;

public class AgenciaDetailResponse {

    private UUID id;
    private String codigo;

    public AgenciaDetailResponse() {
    }

    public AgenciaDetailResponse(UUID id, String codigo) {
        this.id = id;
        this.codigo = codigo;
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
