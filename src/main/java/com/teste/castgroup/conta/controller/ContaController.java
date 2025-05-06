package com.teste.castgroup.conta.controller;

import com.teste.castgroup.conta.model.request.CreditarValorContaRequest;
import com.teste.castgroup.conta.model.request.CriarContaRequest;
import com.teste.castgroup.conta.model.request.DebitarValorContaRequest;
import com.teste.castgroup.conta.model.request.TransferirValorContaRequest;
import com.teste.castgroup.conta.usecase.CreditarValorContaUseCase;
import com.teste.castgroup.conta.usecase.CriarContaUseCase;
import com.teste.castgroup.conta.usecase.DebitarValorContaUseCase;
import com.teste.castgroup.conta.usecase.TransferirValorContaUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/contas")
public class ContaController {

    private final CriarContaUseCase criarContaUseCase;
    private final CreditarValorContaUseCase creditarValorContaUseCase;
    private final DebitarValorContaUseCase debitarValorContaUseCase;
    private final TransferirValorContaUseCase transferirValorContaUseCase;

    public ContaController(CriarContaUseCase criarContaUseCase, CreditarValorContaUseCase creditarValorContaUseCase, DebitarValorContaUseCase debitarValorContaUseCase, TransferirValorContaUseCase transferirValorContaUseCase) {
        this.criarContaUseCase = criarContaUseCase;
        this.creditarValorContaUseCase = creditarValorContaUseCase;
        this.debitarValorContaUseCase = debitarValorContaUseCase;
        this.transferirValorContaUseCase = transferirValorContaUseCase;
    }

    @PostMapping
    public String criar(CriarContaRequest request){

        criarContaUseCase.handle(request);

        return "redirect:criarConta";
    }

    @PutMapping(path = "/creditar")
    public String creditar(CreditarValorContaRequest request){
        creditarValorContaUseCase.handle(request);
        return "";
    }

    @PutMapping(path = "/transferir")
    public String transferir(TransferirValorContaRequest request){
        transferirValorContaUseCase.handle(request);
        return "";
    }


}
