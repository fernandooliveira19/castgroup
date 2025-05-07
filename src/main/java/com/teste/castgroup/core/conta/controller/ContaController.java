package com.teste.castgroup.core.conta.controller;

import com.teste.castgroup.core.conta.model.request.CreditarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.CriarContaRequest;
import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.TransferirValorContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.usecase.CreditarValorContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.CriarContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.DebitarValorContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.TransferirValorContaUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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



    @GetMapping("/cadastro")
    public String cadastrar(CriarContaRequest request){
        return "contas/cadastro";
    }

    @GetMapping("/credito")
    public String credito(CreditarValorContaRequest request){
        return "contas/credito";
    }

    @GetMapping("/detalhe")
    public ModelAndView detalhe(){
        ModelAndView modelAndView = new ModelAndView("contas/detalhe");
        modelAndView.addObject("numeroConta", "45");
        return modelAndView;
    }

    @PostMapping(path="/criar")
    public ModelAndView criar(CriarContaRequest request, RedirectAttributes attr){

        ContaDetailResponse response = criarContaUseCase.handle(request);
        ModelAndView modelAndView = new ModelAndView("contas/detalhe");
        modelAndView.addObject("conta", response);

        attr.addFlashAttribute("success", "Conta cadastrada com sucesso");

        return modelAndView;
    }

    @PostMapping(path = "/creditar")
    public String creditar(CreditarValorContaRequest request){

        creditarValorContaUseCase.handle(request);


        return "contas/detalhe";
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
