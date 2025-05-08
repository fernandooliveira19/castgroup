package com.teste.castgroup.conta.model.service;

import com.teste.castgroup.conta.model.entity.ContaDataSupplier;
import com.teste.castgroup.conta.model.request.DebitarValorContaRequestDataSupplier;
import com.teste.castgroup.core.conta.model.entity.Conta;
import com.teste.castgroup.core.conta.model.exception.ContaException;
import com.teste.castgroup.core.conta.model.repository.ContaRepository;
import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.service.DebitarValorContaService;
import com.teste.castgroup.shared.messages.CastGroupMessages;
import com.teste.castgroup.utils.MessageUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DebitarValorContaServiceUnitTest {

    @InjectMocks
    private DebitarValorContaService debitarContaService;
    @Mock
    private ContaRepository contaRepository;
    @Mock
    private MessageUtils messageUtils;

    @DisplayName("Dado valor suficiente, quando debitar conta, deve realizar o debito")
    @Test
    void dadoValorSuficienteQuandoDebitarContaDeveRealizarDebito(){
        Conta conta = ContaDataSupplier.getConta();
        conta.setSaldo(BigDecimal.valueOf(100.0));
        Mockito.when(contaRepository.findByAgenciaAndNumero(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(conta));
        BigDecimal debito = BigDecimal.valueOf(100.0);
        DebitarValorContaRequest request = DebitarValorContaRequestDataSupplier.buildDebitarValorContaRequest(debito);

        ContaDetailResponse response = debitarContaService.handle(request);

        Assertions.assertEquals(BigDecimal.valueOf(0.0), response.getSaldo());

        Mockito.verify(contaRepository).save(Mockito.any(Conta.class));
    }

    @DisplayName("Dado valor insuficiente, quando debitar conta, deve retornar erro")
    @Test
    void dadoValorInsuficienteQuandoDebitarContaDeveRetornarErro(){
        Conta conta = ContaDataSupplier.getConta();
        conta.setSaldo(BigDecimal.valueOf(100.0));
        Mockito.when(contaRepository.findByAgenciaAndNumero(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(conta));
        BigDecimal debito = BigDecimal.valueOf(100.1);
        DebitarValorContaRequest request = DebitarValorContaRequestDataSupplier.buildDebitarValorContaRequest(debito);

        Assertions.assertThrows(ContaException.class, () -> debitarContaService.handle(request));

        Mockito.verify(messageUtils).getMessage(CastGroupMessages.CONTA_SALDO_INSUFICIENTE.getMessageKey());
    }

    @DisplayName("Dado conta nao encontrada, quando debitar conta, deve retornar erro")
    @Test
    void dadoContaNaoEncontradaQuandoDebitarContaDeveRetornarErro(){
        Mockito.when(contaRepository.findByAgenciaAndNumero(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.empty());
        BigDecimal debito = BigDecimal.valueOf(100.0);
        DebitarValorContaRequest request = DebitarValorContaRequestDataSupplier.buildDebitarValorContaRequest(debito);

        Assertions.assertThrows(ContaException.class, () -> debitarContaService.handle(request));

        Mockito.verify(messageUtils).getMessage(CastGroupMessages.CONTA_NAO_ENCONTRADA.getMessageKey());

    }
}
