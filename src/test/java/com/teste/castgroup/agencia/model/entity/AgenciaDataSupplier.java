package com.teste.castgroup.agencia.model.entity;

import com.teste.castgroup.core.agencia.model.entity.Agencia;

import java.util.UUID;

public class AgenciaDataSupplier {

    private AgenciaDataSupplier(){}

    public static Agencia getAgencia(){
        return new Agencia(UUID.randomUUID(),"1234");
    }
}
