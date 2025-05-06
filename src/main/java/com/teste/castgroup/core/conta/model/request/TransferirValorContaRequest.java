package com.teste.castgroup.core.conta.model.request;

import java.math.BigDecimal;

public class TransferirValorContaRequest {

    private String codigoAgenciaOrigem;
    private String numeroContaOrigem;

    private String codigoAgenciaDestino;
    private String numeroContaDestino;

    private BigDecimal valor;

    public String getCodigoAgenciaOrigem() {
        return codigoAgenciaOrigem;
    }

    public void setCodigoAgenciaOrigem(String codigoAgenciaOrigem) {
        this.codigoAgenciaOrigem = codigoAgenciaOrigem;
    }

    public String getNumeroContaOrigem() {
        return numeroContaOrigem;
    }

    public void setNumeroContaOrigem(String numeroContaOrigem) {
        this.numeroContaOrigem = numeroContaOrigem;
    }

    public String getCodigoAgenciaDestino() {
        return codigoAgenciaDestino;
    }

    public void setCodigoAgenciaDestino(String codigoAgenciaDestino) {
        this.codigoAgenciaDestino = codigoAgenciaDestino;
    }

    public String getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(String numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
