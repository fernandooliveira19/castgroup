package com.teste.castgroup.core.conta.model.request;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import java.math.BigDecimal;

public class CreditarValorContaRequest {

    private String codigoAgencia;
    private String numeroConta;
    @NumberFormat(style= Style.CURRENCY, pattern="#,##0.00")
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
