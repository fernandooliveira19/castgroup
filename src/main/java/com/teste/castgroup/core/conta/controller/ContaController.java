package com.teste.castgroup.core.conta.controller;

import com.teste.castgroup.core.conta.model.request.CreditarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.CriarContaRequest;
import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.TransferirValorContaRequest;
import com.teste.castgroup.core.conta.model.usecase.CreditarValorContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.CriarContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.DebitarValorContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.TransferirValorContaUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contas")
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



    @GetMapping("/cadastrar")
    public String cadastrar(CriarContaRequest request){
        return "contas/cadastro";
    }

    @PostMapping(path="/criar")
    public String criar(CriarContaRequest request, RedirectAttributes attr){

        criarContaUseCase.handle(request);

        attr.addFlashAttribute("success", "Conta cadastrada com sucesso");

        return "redirect:cadastrar";
    }

    @PutMapping(path = "/creditar")
    public String creditar(@RequestBody CreditarValorContaRequest request){
        creditarValorContaUseCase.handle(request);
        return "";
    }

    @PutMapping(path = "/debitar")
    public String debitar(@RequestBody DebitarValorContaRequest request){
        debitarValorContaUseCase.handle(request);
        return "";
    }

    @PutMapping(path = "/transferir")
    public String transferir(@RequestBody TransferirValorContaRequest request){
        transferirValorContaUseCase.handle(request);
        return "";
    }


}
