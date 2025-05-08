package com.teste.castgroup.conta.model.service;

import com.teste.castgroup.conta.model.entity.ContaDataSupplier;
import com.teste.castgroup.conta.model.request.CreditarValorContaRequestDataSupplier;
import com.teste.castgroup.conta.model.request.DebitarValorContaRequestDataSupplier;
import com.teste.castgroup.core.conta.model.entity.Conta;
import com.teste.castgroup.core.conta.model.exception.ContaException;
import com.teste.castgroup.core.conta.model.repository.ContaRepository;
import com.teste.castgroup.core.conta.model.request.CreditarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.service.CreditarValorContaService;
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
class CreditarValorContaServiceUnitTest {

    @InjectMocks
    private CreditarValorContaService creditarValorContaService;
    @Mock
    private ContaRepository contaRepository;
    @Mock
    private MessageUtils messageUtils;

    @DisplayName("Dado valor suficiente, quando creditar conta, deve realizar o credito")
    @Test
    void dadoValorSuficienteQuandoCreditaContaDeveRealizarCredito(){
        Conta conta = ContaDataSupplier.getConta();
        conta.setSaldo(BigDecimal.valueOf(100.0));
        Mockito.when(contaRepository.findByAgenciaAndNumero(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(conta));
        BigDecimal credito = BigDecimal.valueOf(40.0);
        CreditarValorContaRequest request = CreditarValorContaRequestDataSupplier.buildCreditarValorContaRequest(credito);

        ContaDetailResponse response = creditarValorContaService.handle(request);

        Assertions.assertEquals(BigDecimal.valueOf(140.0), response.getSaldo());

        Mockito.verify(contaRepository).save(Mockito.any(Conta.class));
    }


    @DisplayName("Dado conta nao encontrada, quando creditar conta, deve retornar erro")
    @Test
    void dadoContaNaoEncontradaQuandoDebitarContaDeveRetornarErro(){
        Mockito.when(contaRepository.findByAgenciaAndNumero(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.empty());
        BigDecimal credito = BigDecimal.valueOf(40.0);
        CreditarValorContaRequest request = CreditarValorContaRequestDataSupplier.buildCreditarValorContaRequest(credito);

        Assertions.assertThrows(ContaException.class, () -> creditarValorContaService.handle(request));

        Mockito.verify(messageUtils).getMessage(CastGroupMessages.CONTA_NAO_ENCONTRADA.getMessageKey());

    }
}
