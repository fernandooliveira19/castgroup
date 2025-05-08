package com.teste.castgroup.shared.messages;

public enum CastGroupMessages {
    AGENCIA_CADASTRADA_SUCESSO("agencia.cadastrada.sucesso"),
    AGENCIA_NAO_ENCONTRADA("agencia.nao.encontrada"),
    AGENCIA_JA_CADASTRADA("agencia.ja.cadastrada"),
    CONTA_NAO_ENCONTRADA("conta.nao.encontrada"),
    CONTA_JA_CADASTRADA("conta.ja.cadastrada"),
    CONTA_SALDO_INSUFICIENTE("conta.saldo.insuficiente"),
    CONTA_CADASTRADA_SUCESSO("conta.cadastrada.sucesso"),
    CONTA_DEBITADA_SUCESSO("conta.debitada.sucesso"),
    CONTA_CREDITADA_SUCESSO("conta.creditada.sucesso"),
    CONTA_TRANSFERENCIA_SUCESSO("conta.transferencia.sucesso");



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
