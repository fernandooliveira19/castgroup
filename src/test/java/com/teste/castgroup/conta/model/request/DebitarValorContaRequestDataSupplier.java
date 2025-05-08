package com.teste.castgroup.conta.model.request;

import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;

import java.math.BigDecimal;

public class DebitarValorContaRequestDataSupplier {

    private DebitarValorContaRequestDataSupplier(){}

    public static DebitarValorContaRequest buildDebitarValorContaRequest(BigDecimal valor){
        return new DebitarValorContaRequest("1234", "4545", valor);
    }
}
