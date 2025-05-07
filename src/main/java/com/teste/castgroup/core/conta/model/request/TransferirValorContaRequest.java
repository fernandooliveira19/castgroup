package com.teste.castgroup.core.conta.model.request;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.math.BigDecimal;

public class TransferirValorContaRequest {

    private String codigoAgenciaDebito;
    private String numeroContaDebito;

    private String codigoAgenciaCredito;
    private String numeroContaCredito;
    @NumberFormat(style= Style.CURRENCY, pattern="#,##0.00")
    private BigDecimal valor;

    public String getCodigoAgenciaDebito() {
        return codigoAgenciaDebito;
    }

    public void setCodigoAgenciaDebito(String codigoAgenciaDebito) {
        this.codigoAgenciaDebito = codigoAgenciaDebito;
    }

    public String getNumeroContaDebito() {
        return numeroContaDebito;
    }

    public void setNumeroContaDebito(String numeroContaDebito) {
        this.numeroContaDebito = numeroContaDebito;
    }

    public String getCodigoAgenciaCredito() {
        return codigoAgenciaCredito;
    }

    public void setCodigoAgenciaCredito(String codigoAgenciaCredito) {
        this.codigoAgenciaCredito = codigoAgenciaCredito;
    }

    public String getNumeroContaCredito() {
        return numeroContaCredito;
    }

    public void setNumeroContaCredito(String numeroContaCredito) {
        this.numeroContaCredito = numeroContaCredito;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
