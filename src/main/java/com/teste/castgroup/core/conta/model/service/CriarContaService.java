package com.teste.castgroup.core.conta.model.service;

import com.teste.castgroup.core.conta.model.entity.Conta;
import com.teste.castgroup.core.conta.model.exception.ContaException;
import com.teste.castgroup.shared.messages.CastGroupMessages;
import com.teste.castgroup.core.agencia.model.repository.AgenciaRepository;
import com.teste.castgroup.core.conta.model.repository.ContaRepository;
import com.teste.castgroup.core.conta.model.request.CriarContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.usecase.CriarContaUseCase;
import com.teste.castgroup.utils.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CriarContaService implements CriarContaUseCase {

    private final ContaRepository contaRepository;
    private final AgenciaRepository agenciaRepository;
    private final MessageUtils messageUtils;

    public CriarContaService(ContaRepository contaRepository, AgenciaRepository agenciaRepository, MessageUtils messageUtils) {
        this.contaRepository = contaRepository;
        this.agenciaRepository = agenciaRepository;
        this.messageUtils = messageUtils;
    }

    @Override
    public ContaDetailResponse handle(CriarContaRequest request) {

        List<ContaDetailResponse> response = new ArrayList<>();

        agenciaRepository.findByCodigo(request.getCodigoAgencia()).ifPresentOrElse(
                agencia -> {

                    if(contaRepository.findByAgenciaAndNumero(agencia.getCodigo(), request.getNumeroConta())
                            .isPresent()) {
                        throw new ContaException(messageUtils.getMessage(CastGroupMessages.CONTA_JA_CADASTRADA.getMessageKey()));
                    }

                    Conta conta = new Conta().criar(agencia, request.getNumeroConta());
                    contaRepository.save(conta);
                    response.add(new ContaDetailResponse(agencia.getCodigo(), conta.getNumero(),conta.getSaldo()));
                },
                () -> {
                    throw new ContaException(messageUtils.getMessage(CastGroupMessages.AGENCIA_NAO_ENCONTRADA.getMessageKey()));
                }
        );

        return response.stream().findAny().orElse(new ContaDetailResponse());
    }
}
