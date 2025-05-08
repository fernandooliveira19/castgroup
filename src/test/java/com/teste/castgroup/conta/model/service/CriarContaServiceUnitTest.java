package com.teste.castgroup.conta.model.service;

import com.teste.castgroup.agencia.model.entity.AgenciaDataSupplier;
import com.teste.castgroup.conta.model.entity.ContaDataSupplier;
import com.teste.castgroup.core.conta.model.entity.Conta;
import com.teste.castgroup.core.conta.model.exception.ContaException;
import com.teste.castgroup.shared.messages.CastGroupMessages;
import com.teste.castgroup.core.agencia.model.repository.AgenciaRepository;
import com.teste.castgroup.core.conta.model.repository.ContaRepository;
import com.teste.castgroup.core.conta.model.request.CriarContaRequest;
import com.teste.castgroup.conta.model.request.CriarContaRequestDataSupplier;
import com.teste.castgroup.core.conta.model.service.CriarContaService;
import com.teste.castgroup.utils.MessageUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CriarContaServiceUnitTest {

    @InjectMocks
    private CriarContaService criarContaService;

    @Mock
    private ContaRepository contaRepository;
    @Mock
    private AgenciaRepository agenciaRepository;
    @Mock
    private MessageUtils messageUtils;

    @Test
    @DisplayName("Dado agencia nao encontrada,  deve retornar erro")
    void dadoAgenciaNaoEncontradaDeveRetornarErro(){
        Mockito.when(agenciaRepository.findByCodigo(Mockito.anyString())).thenReturn(Optional.empty());

        CriarContaRequest request = CriarContaRequestDataSupplier.getCriarContaRequest();

        Assertions.assertThrows(ContaException.class, () -> criarContaService.handle(request));

        Mockito.verify(messageUtils).getMessage(CastGroupMessages.AGENCIA_NAO_ENCONTRADA.getMessageKey());

    }

    @Test
    @DisplayName("Dado agencia e conta existente, quando criar conta, deve retornar erro")
    void dadoAgenciaContaExistenteNaoEncontradaDeveRetornarErro(){
        Mockito.when(agenciaRepository.findByCodigo(Mockito.anyString()))
                .thenReturn(Optional.of(AgenciaDataSupplier.getAgencia()));
        Mockito.when(contaRepository.findByAgenciaAndNumero(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(ContaDataSupplier.getConta()));

        CriarContaRequest request = CriarContaRequestDataSupplier.getCriarContaRequest();

        Assertions.assertThrows(ContaException.class, ()-> criarContaService.handle(request));

        Mockito.verify(messageUtils).getMessage(CastGroupMessages.CONTA_JA_CADASTRADA.getMessageKey());

    }

    @Test
    @DisplayName("Dado agencia e conta inexistente, quando criar conta, deve criar conta")
    void dadoAgenciaContaInexistenteDeveCriarConta(){
        Mockito.when(agenciaRepository.findByCodigo(Mockito.anyString()))
                .thenReturn(Optional.of(AgenciaDataSupplier.getAgencia()));
        Mockito.when(contaRepository.findByAgenciaAndNumero(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.empty());

        CriarContaRequest request = CriarContaRequestDataSupplier.getCriarContaRequest();
        criarContaService.handle(request);

        Mockito.verify(contaRepository).save(Mockito.any(Conta.class));

    }

}
