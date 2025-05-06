package com.teste.castgroup.conta.usecase;

public interface UseCaseBase<Input, Output>{

    Output handle(Input request);
}
