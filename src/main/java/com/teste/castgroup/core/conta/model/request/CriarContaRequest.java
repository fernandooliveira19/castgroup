package com.teste.castgroup.core.conta.model.request;

public class CriarContaRequest {

    private String numeroAgencia;
    private String numeroConta;


    public CriarContaRequest() {
    }

    public CriarContaRequest(String numeroAgencia, String numeroConta) {
        this.numeroAgencia = numeroAgencia;
        this.numeroConta = numeroConta;
    }

    public String getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
}
