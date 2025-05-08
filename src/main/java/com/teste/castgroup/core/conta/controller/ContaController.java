package com.teste.castgroup.core.conta.controller;

import com.teste.castgroup.core.conta.model.request.BuscarContaRequest;
import com.teste.castgroup.core.conta.model.request.CreditarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.CriarContaRequest;
import com.teste.castgroup.core.conta.model.request.DebitarValorContaRequest;
import com.teste.castgroup.core.conta.model.request.TransferirValorContaRequest;
import com.teste.castgroup.core.conta.model.response.ContaDetailResponse;
import com.teste.castgroup.core.conta.model.usecase.BuscarContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.CreditarValorContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.CriarContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.DebitarValorContaUseCase;
import com.teste.castgroup.core.conta.model.usecase.TransferirValorContaUseCase;
import com.teste.castgroup.shared.messages.CastGroupMessages;
import com.teste.castgroup.utils.MessageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contas")
public class ContaController {

    private final CriarContaUseCase criarContaUseCase;
    private final CreditarValorContaUseCase creditarValorContaUseCase;
    private final DebitarValorContaUseCase debitarValorContaUseCase;
    private final TransferirValorContaUseCase transferirValorContaUseCase;
    private final BuscarContaUseCase buscarContaUseCase;
    private final MessageUtils messageUtils;

    private static final String SUCESSO = "success";
    private static final String FALHA = "fail";

    public ContaController(CriarContaUseCase criarContaUseCase, CreditarValorContaUseCase creditarValorContaUseCase, DebitarValorContaUseCase debitarValorContaUseCase, TransferirValorContaUseCase transferirValorContaUseCase, BuscarContaUseCase buscarContaUseCase, MessageUtils messageUtils) {
        this.criarContaUseCase = criarContaUseCase;
        this.creditarValorContaUseCase = creditarValorContaUseCase;
        this.debitarValorContaUseCase = debitarValorContaUseCase;
        this.transferirValorContaUseCase = transferirValorContaUseCase;
        this.buscarContaUseCase = buscarContaUseCase;
        this.messageUtils = messageUtils;
    }



    @GetMapping("/cadastro")
    public String cadastrar(){
        return "contas/cadastro";
    }

    @GetMapping("/credito")
    public String credito(){
        return "contas/credito";
    }

    @GetMapping("/debito")
    public String debito(){
        return "contas/debito";
    }

    @GetMapping("/transferencia")
    public String transferencia(){
        return "contas/transferencia";
    }

    @GetMapping("/busca")
    public String busca(){

        return "contas/busca";
    }

    @GetMapping("/detalhe/{codigoAgencia}/{numeroConta}")
    public String detalhe(@PathVariable("codigoAgencia") String codigoAgencia, @PathVariable("numeroConta") String numeroConta) {
        ModelAndView modelAndView = new ModelAndView();


        return "contas/detalhe";
    }

    @PostMapping(path="/criar")
    public ModelAndView criar(@ModelAttribute CriarContaRequest request){

        ModelAndView modelAndView = new ModelAndView("contas/detalhe");
        ContaDetailResponse response = new ContaDetailResponse();
        try {
            response = criarContaUseCase.handle(request);
            modelAndView.addObject(SUCESSO, messageUtils.getMessage(CastGroupMessages.CONTA_CADASTRADA_SUCESSO.getMessageKey()));
        } catch (Exception e) {
            modelAndView.addObject(FALHA, e.getMessage());
        }
        modelAndView.addObject("conta", response);

        return modelAndView;
    }

    @PostMapping(path = "/creditar")
    public ModelAndView creditar(CreditarValorContaRequest request){
        ModelAndView modelAndView = new ModelAndView("contas/detalhe");
        ContaDetailResponse response = new ContaDetailResponse();

        try {
            response = creditarValorContaUseCase.handle(request);
            modelAndView.addObject(SUCESSO, messageUtils.getMessage(CastGroupMessages.CONTA_CREDITADA_SUCESSO.getMessageKey()));
        } catch (Exception e) {
            modelAndView.addObject(FALHA, e.getMessage());
        }
        modelAndView.addObject("conta", response);
        return modelAndView;

    }

    @PostMapping(path = "/debitar")
    public ModelAndView debitar(DebitarValorContaRequest request){
        ModelAndView modelAndView = new ModelAndView("contas/detalhe");
        ContaDetailResponse response = new ContaDetailResponse();
        try {
            response = debitarValorContaUseCase.handle(request);
            modelAndView.addObject(SUCESSO, messageUtils.getMessage(CastGroupMessages.CONTA_DEBITADA_SUCESSO.getMessageKey()));
        } catch (Exception e) {
            modelAndView.addObject(FALHA, e.getMessage());
        }
        modelAndView.addObject("conta", response);
        return modelAndView;

    }

    @PostMapping(path = "/transferir")
    public ModelAndView transferir(TransferirValorContaRequest request){
        ModelAndView modelAndView = new ModelAndView("contas/detalhe");
        ContaDetailResponse response = new ContaDetailResponse();
        try {
            response = transferirValorContaUseCase.handle(request);
            modelAndView.addObject(SUCESSO, messageUtils.getMessage(CastGroupMessages.CONTA_TRANSFERENCIA_SUCESSO.getMessageKey()));
        } catch (Exception e) {
            modelAndView.addObject(FALHA, e.getMessage());
        }
        modelAndView.addObject("conta", response);
        return modelAndView;

    }

    @GetMapping(path = "/buscar")
    public ModelAndView buscar(@RequestParam("codigoAgencia") String codigoAgencia, @RequestParam("numeroConta") String numeroConta){

        ContaDetailResponse response = buscarContaUseCase.handle(new BuscarContaRequest(codigoAgencia, numeroConta));

        return getModelAndViewDetail(response);
    }

    private static ModelAndView getModelAndViewDetail(ContaDetailResponse response) {
        ModelAndView modelAndView = new ModelAndView("contas/detalhe");
        modelAndView.addObject("conta", response);
        return modelAndView;
    }

}
