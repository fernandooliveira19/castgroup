package com.teste.castgroup.conta.model.response;

import java.math.BigDecimal;

public class ContaDetailResponse {

    private String codigoAgencia;
    private String numeroConta;
    private BigDecimal saldo;

    public ContaDetailResponse() {

    }

    public ContaDetailResponse(String codigoAgencia, String numeroConta, BigDecimal saldo) {
        this.codigoAgencia = codigoAgencia;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(String codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
