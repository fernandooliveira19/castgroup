package com.teste.castgroup.conta.model.request;

import com.teste.castgroup.core.conta.model.request.TransferirValorContaRequest;

import java.math.BigDecimal;

public class TransferirValorContaRequestDataSupplier {

    private TransferirValorContaRequestDataSupplier(){}

    public static TransferirValorContaRequest buildTransferirValorContaRequest(BigDecimal valor){
        return new TransferirValorContaRequest("1234", "4545", "6677","7899", valor);
    }
}
