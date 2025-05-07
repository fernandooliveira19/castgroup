package com.teste.castgroup.core.conta.model.service;

import com.teste.castgroup.core.conta.model.messages.ContaMessages;
import com.teste.castgroup.core.conta.model.repository.ContaRepository;
import com.teste.castgroup.core.conta.model.request.CreditarValorContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.usecase.CreditarValorContaUseCase;
import com.teste.castgroup.utils.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CreditarValorContaService implements CreditarValorContaUseCase {

    private final ContaRepository contaRepository;
    private final MessageUtils messageUtils;

    public CreditarValorContaService(ContaRepository contaRepository, MessageUtils messageUtils) {
        this.contaRepository = contaRepository;
        this.messageUtils = messageUtils;
    }

    @Override
    public ContaDetailResponse handle(CreditarValorContaRequest request) {

        List<ContaDetailResponse> response = new ArrayList<>();

        contaRepository.findByAgenciaAndNumero(request.getCodigoAgencia(), request.getNumeroConta()).ifPresentOrElse(
                conta -> {

                    conta.creditar(request.getValor());
                    contaRepository.save(conta);
                    response.add(new ContaDetailResponse(conta.getAgencia().getCodigo(), conta.getNumero(),conta.getSaldo()));
                },
                () -> {
                    throw new RuntimeException(messageUtils.getMessage(ContaMessages.CONTA_NAO_ENCONTRADA.getMessageKey()));
                }
        );

        return response.stream().findAny().orElse(new ContaDetailResponse());
    }
}
