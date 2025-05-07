package com.teste.castgroup.core.conta.model.service;

import com.teste.castgroup.core.conta.model.exception.ContaException;
import com.teste.castgroup.core.conta.model.messages.ContaMessages;
import com.teste.castgroup.core.conta.model.repository.ContaRepository;
import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.usecase.DebitarValorContaUseCase;
import com.teste.castgroup.utils.MessageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DebitarValorContaService implements DebitarValorContaUseCase {

    private final ContaRepository contaRepository;
    private final MessageUtils messageUtils;

    public DebitarValorContaService(ContaRepository contaRepository, MessageUtils messageUtils) {
        this.contaRepository = contaRepository;
        this.messageUtils = messageUtils;
    }

    @Override
    public ContaDetailResponse handle(DebitarValorContaRequest request) {

        List<ContaDetailResponse> response = new ArrayList<>();

        contaRepository.findByAgenciaAndNumero(request.getCodigoAgencia(), request.getNumeroConta()).ifPresentOrElse(
                conta -> {
                    validarOperacaoDebito(conta.getSaldo(), request.getValor());

                    conta.debitar(request.getValor());
                    contaRepository.save(conta);
                    response.add(new ContaDetailResponse(conta.getAgencia().getCodigo(), conta.getNumero(),conta.getSaldo()));
                },
                () -> {
                    throw new RuntimeException(messageUtils.getMessage(ContaMessages.CONTA_NAO_ENCONTRADA.getMessageKey()));
                }
        );

        return response.stream().findAny().orElse(new ContaDetailResponse());
    }

    private void validarOperacaoDebito(BigDecimal saldo, BigDecimal valorDebito){

        if(valorDebito.compareTo(saldo) > 0){
            throw new ContaException("Saldo insufiente para a operacao");
        }
    }
}
