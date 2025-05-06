package com.teste.castgroup.core.conta.model.service;

import com.teste.castgroup.core.conta.model.request.CreditarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.TransferirValorContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.usecase.TransferirValorContaUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransferirValorContaService implements TransferirValorContaUseCase {


    private final DebitarValorContaService debitarValorContaService;
    private final CreditarValorContaService creditarValorContaService;

    public TransferirValorContaService(DebitarValorContaService debitarValorContaService, CreditarValorContaService creditarValorContaService) {

        this.debitarValorContaService = debitarValorContaService;
        this.creditarValorContaService = creditarValorContaService;
    }

    @Override
    public ContaDetailResponse handle(TransferirValorContaRequest request) {
        DebitarValorContaRequest debitarValorContaRequest =
                new DebitarValorContaRequest(
                        request.getCodigoAgenciaOrigem(), request.getNumeroContaOrigem(), request.getValor());
        ContaDetailResponse contaDetailResponse = debitarValorContaService.handle(debitarValorContaRequest);

        CreditarValorContaRequest creditarValorContaRequest =
                new CreditarValorContaRequest(request.getCodigoAgenciaDestino(), request.getNumeroContaDestino(), request.getValor());
        creditarValorContaService.handle(creditarValorContaRequest);
        return contaDetailResponse;
    }
}
