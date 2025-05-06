package com.teste.castgroup.conta.model.request;

import java.math.BigDecimal;

public class DebitarValorContaRequest {

    private String codigoAgencia;
    private String numeroConta;
    private BigDecimal valor;

    public DebitarValorContaRequest(){

    }

    public DebitarValorContaRequest(String codigoAgencia, String numeroConta, BigDecimal valor) {
        this.codigoAgencia = codigoAgencia;
        this.numeroConta = numeroConta;
        this.valor = valor;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
}
