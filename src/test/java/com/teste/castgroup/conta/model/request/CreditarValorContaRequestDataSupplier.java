package com.teste.castgroup.conta.model.request;

import com.teste.castgroup.core.conta.model.request.CreditarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;

import java.math.BigDecimal;

public class CreditarValorContaRequestDataSupplier {

    private CreditarValorContaRequestDataSupplier(){}

    public static CreditarValorContaRequest buildCreditarValorContaRequest(BigDecimal valor){
        return new CreditarValorContaRequest("1234", "4545", valor);
    }
}
