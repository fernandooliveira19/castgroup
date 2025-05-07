package com.teste.castgroup.core.agencia.model.service;

import com.teste.castgroup.core.agencia.model.entity.Agencia;
import com.teste.castgroup.core.agencia.model.repository.AgenciaRepository;
import com.teste.castgroup.core.agencia.model.request.CriarAgenciaRequest;
import com.teste.castgroup.core.agencia.model.response.AgenciaDetailResponse;
import com.teste.castgroup.core.agencia.model.usecase.CriarAgenciaUseCase;
import com.teste.castgroup.core.conta.model.exception.ContaException;
import com.teste.castgroup.shared.messages.CastGroupMessages;
import com.teste.castgroup.utils.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CriarAgenciaService implements CriarAgenciaUseCase {

    private final AgenciaRepository agenciaRepository;
    private final MessageUtils messageUtils;

    public CriarAgenciaService(AgenciaRepository agenciaRepository, MessageUtils messageUtils) {
        this.agenciaRepository = agenciaRepository;
        this.messageUtils = messageUtils;
    }

    @Override
    public AgenciaDetailResponse handle(CriarAgenciaRequest request) {

        List<AgenciaDetailResponse> response = new ArrayList<>();
        agenciaRepository.findByCodigo(request.getCodigo()).ifPresentOrElse(
                agencia -> {
                    throw new ContaException(messageUtils.getMessage(CastGroupMessages.AGENCIA_JA_CADASTRADA.getMessageKey()));
                },
                () -> {

                    Agencia agencia = new Agencia().criar(request.getCodigo());
                    agenciaRepository.save(agencia);
                    response.add(new AgenciaDetailResponse(agencia.getId(),agencia.getCodigo()));
                }
        );

        return response.stream().findAny().orElse(new AgenciaDetailResponse());
    }
}
