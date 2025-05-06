package com.teste.castgroup.conta.model.request;

import java.math.BigDecimal;

public class CreditarValorContaRequest {

    private String codigoAgencia;
    private String numeroConta;
    private BigDecimal valor;

    public CreditarValorContaRequest() {

    }

    public CreditarValorContaRequest(String codigoAgencia, String numeroConta, BigDecimal valor) {
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
