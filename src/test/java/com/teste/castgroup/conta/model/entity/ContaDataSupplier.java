package com.teste.castgroup.conta.model.entity;

import com.teste.castgroup.agencia.model.entity.AgenciaDataSupplier;
import com.teste.castgroup.core.conta.model.entity.Conta;

import java.math.BigDecimal;
import java.util.UUID;

public class ContaDataSupplier {

    private ContaDataSupplier(){

    }
    public static Conta getConta(){
        return new Conta(
                UUID.randomUUID(),
                AgenciaDataSupplier.getAgencia(),
                "7878",
                BigDecimal.ZERO
        );
    }
}
