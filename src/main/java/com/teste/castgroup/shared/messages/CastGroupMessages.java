package com.teste.castgroup.shared.messages;

public enum CastGroupMessages {

    AGENCIA_NAO_ENCONTRADA("agencia.nao.encontrada"),
    AGENCIA_JA_CADASTRADA("agencia.ja.cadastrada"),
    CONTA_NAO_ENCONTRADA("conta.nao.encontrada"),
    CONTA_JA_CADASTRADA("conta.ja.cadastrada"),
    CONTA_SALDO_INSUFICIENTE("conta.saldo.insuficiente");


    private final String messageKey;

    CastGroupMessages(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String toString() {
        return messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
