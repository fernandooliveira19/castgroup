package com.teste.castgroup.conta.model.entity;

import com.teste.castgroup.core.conta.model.entity.Conta;

class ContaDataSupllier {

    private ContaDataSupllier(){

    }
    public static Conta getConta(){
        return new Conta();
    }
}
