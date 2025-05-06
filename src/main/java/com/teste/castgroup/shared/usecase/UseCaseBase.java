package com.teste.castgroup.shared.usecase;

public interface UseCaseBase<Input, Output>{

    Output handle(Input request);
}
