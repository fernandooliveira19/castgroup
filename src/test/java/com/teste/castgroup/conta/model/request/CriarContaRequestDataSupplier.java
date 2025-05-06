package com.teste.castgroup.conta.model.request;

import com.teste.castgroup.core.conta.model.request.CriarContaRequest;

public class CriarContaRequestDataSupplier {

    private CriarContaRequestDataSupplier(){}

    public static CriarContaRequest getCriarContaRequest(){
        return new CriarContaRequest("1234", "4545");
    }
}
