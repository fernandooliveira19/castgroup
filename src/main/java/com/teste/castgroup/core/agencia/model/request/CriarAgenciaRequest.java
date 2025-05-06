package com.teste.castgroup.core.agencia.model.request;

public class CriarAgenciaRequest {

    private String codigo;

    public CriarAgenciaRequest() {
    }

    public CriarAgenciaRequest(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
