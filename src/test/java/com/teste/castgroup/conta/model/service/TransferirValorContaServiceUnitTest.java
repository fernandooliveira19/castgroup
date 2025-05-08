package com.teste.castgroup.conta.model.service;

import com.teste.castgroup.conta.model.entity.ContaDataSupplier;
import com.teste.castgroup.conta.model.request.DebitarValorContaRequestDataSupplier;
import com.teste.castgroup.conta.model.request.TransferirValorContaRequestDataSupplier;
import com.teste.castgroup.core.agencia.model.entity.Agencia;
import com.teste.castgroup.core.conta.model.entity.Conta;
import com.teste.castgroup.core.conta.model.exception.ContaException;
import com.teste.castgroup.core.conta.model.repository.ContaRepository;
import com.teste.castgroup.core.conta.model.request.CreditarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.TransferirValorContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.service.CreditarValorContaService;
import com.teste.castgroup.core.conta.model.service.DebitarValorContaService;
import com.teste.castgroup.core.conta.model.service.TransferirValorContaService;
import com.teste.castgroup.shared.messages.CastGroupMessages;
import com.teste.castgroup.utils.MessageUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TransferirValorContaServiceUnitTest {

    @InjectMocks
    private TransferirValorContaService transferirValorContaService;
    @Mock
    private DebitarValorContaService debitarValorContaService;
    @Mock
    private CreditarValorContaService creditarValorContaService;

    @DisplayName("Dado valor suficiente, quando transferir valor conta, deve realizar o debito e credito")
    @Test
    void dadoValorSuficienteQuandoTransferirContaDeveRealizarDebitoCredito(){
        Conta contaDebito = new Conta();
        contaDebito.setAgencia(new Agencia(UUID.randomUUID(), "1111"));
        contaDebito.setNumero("2222");
        contaDebito.setSaldo(BigDecimal.valueOf(100.0));
        BigDecimal valor = BigDecimal.valueOf(80.0);

        TransferirValorContaRequest request = TransferirValorContaRequestDataSupplier.buildTransferirValorContaRequest(valor);

        ContaDetailResponse response = new ContaDetailResponse(contaDebito.getAgencia().getCodigo(), contaDebito.getNumero(), BigDecimal.valueOf(20.0));

        transferirValorContaService.handle(request);

        Assertions.assertEquals(BigDecimal.valueOf(20.0), response.getSaldo());

        Mockito.verify(creditarValorContaService).handle(Mockito.any(CreditarValorContaRequest.class));
    }



}
