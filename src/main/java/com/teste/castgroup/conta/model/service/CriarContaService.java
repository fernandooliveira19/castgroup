package com.teste.castgroup.conta.model.service;

import com.teste.castgroup.conta.model.entity.Conta;
import com.teste.castgroup.conta.model.messages.ContaMessages;
import com.teste.castgroup.conta.model.repository.AgenciaRepository;
import com.teste.castgroup.conta.model.repository.ContaRepository;
import com.teste.castgroup.conta.model.request.CriarContaRequest;
import com.teste.castgroup.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.conta.usecase.CriarContaUseCase;
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
        if(contaRepository.findByAgenciaAndNumero(request.getNumeroAgencia(), request.getNumeroConta())
                .isPresent()) {
            throw new RuntimeException(messageUtils.getMessage(ContaMessages.CONTA_JA_CADASTRADA.getMessageKey()));
        }

        agenciaRepository.findByCodigo(request.getNumeroAgencia()).ifPresentOrElse(
                agencia -> {
                    Conta conta = new Conta().criar(agencia, request.getNumeroConta());
                    contaRepository.save(conta);
                    response.add(new ContaDetailResponse(agencia.getCodigo(), conta.getNumero(),conta.getSaldo()));
                },
                () -> {
                    throw new RuntimeException(messageUtils.getMessage(ContaMessages.AGENCIA_NAO_ENCONTRADA.getMessageKey()));
                }
        );

        return response.stream().findAny().orElse(null);
    }
}
