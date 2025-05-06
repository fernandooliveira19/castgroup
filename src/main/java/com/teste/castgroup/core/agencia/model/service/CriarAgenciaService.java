package com.teste.castgroup.core.agencia.model.service;

import com.teste.castgroup.core.agencia.model.entity.Agencia;
import com.teste.castgroup.core.agencia.model.repository.AgenciaRepository;
import com.teste.castgroup.core.agencia.model.request.CriarAgenciaRequest;
import com.teste.castgroup.core.agencia.model.response.AgenciaDetailResponse;
import com.teste.castgroup.core.agencia.model.usecase.CriarAgenciaUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CriarAgenciaService implements CriarAgenciaUseCase {

    private final AgenciaRepository agenciaRepository;

    public CriarAgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    @Override
    public AgenciaDetailResponse handle(CriarAgenciaRequest request) {

        List<AgenciaDetailResponse> response = new ArrayList<>();
        agenciaRepository.findByCodigo(request.getCodigo()).ifPresentOrElse(
                agencia -> {
                    throw new RuntimeException("");
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
