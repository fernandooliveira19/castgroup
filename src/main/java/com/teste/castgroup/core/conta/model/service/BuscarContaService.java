package com.teste.castgroup.core.conta.model.service;

import com.teste.castgroup.core.agencia.model.repository.AgenciaRepository;
import com.teste.castgroup.core.conta.model.entity.Conta;
import com.teste.castgroup.core.conta.model.exception.ContaException;
import com.teste.castgroup.core.conta.model.messages.ContaMessages;
import com.teste.castgroup.core.conta.model.repository.ContaRepository;
import com.teste.castgroup.core.conta.model.request.BuscarContaRequest;
import com.teste.castgroup.core.conta.model.request.CriarContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.usecase.BuscarContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.CriarContaUseCase;
import com.teste.castgroup.utils.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class BuscarContaService implements BuscarContaUseCase {

    private final ContaRepository contaRepository;

    private final MessageUtils messageUtils;

    public BuscarContaService(ContaRepository contaRepository,  MessageUtils messageUtils) {
        this.contaRepository = contaRepository;

        this.messageUtils = messageUtils;
    }

    @Override
    public ContaDetailResponse handle(BuscarContaRequest request) {

        List<ContaDetailResponse> response = new ArrayList<>();


        contaRepository.findByAgenciaAndNumero(request.getCodigoAgencia(), request.getNumeroConta())
                .ifPresentOrElse(conta -> {
                            response.add(new ContaDetailResponse(conta.getAgencia().getCodigo(), conta.getNumero(), conta.getSaldo()));
                        },
                        () -> {
                            throw new ContaException(messageUtils.getMessage(ContaMessages.CONTA_NAO_ENCONTRADA.getMessageKey()));
                        });
        return response.stream().findAny().orElse(new ContaDetailResponse());
    }
}
