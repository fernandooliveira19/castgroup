package com.teste.castgroup.core.conta.model.messages;

public enum ContaMessages {

    AGENCIA_NAO_ENCONTRADA("agencia.nao.encontrada"),
    CONTA_NAO_ENCONTRADA("conta.nao.encontrada"),
    CONTA_JA_CADASTRADA("conta.ja.cadastrada");


    private final String messageKey;

    ContaMessages(String messageKey) {
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
