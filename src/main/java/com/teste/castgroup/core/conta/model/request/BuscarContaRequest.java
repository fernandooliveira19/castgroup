package com.teste.castgroup.core.conta.model.request;

public class BuscarContaRequest {

    private String codigoAgencia;
    private String numeroConta;


    public BuscarContaRequest() {
    }

    public BuscarContaRequest(String codigoAgencia, String numeroConta) {
        this.codigoAgencia = codigoAgencia;
        this.numeroConta = numeroConta;
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
}
