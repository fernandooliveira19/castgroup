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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final BuscarContaUseCase buscarContaUseCase;

    public ContaController(CriarContaUseCase criarContaUseCase, CreditarValorContaUseCase creditarValorContaUseCase, DebitarValorContaUseCase debitarValorContaUseCase, TransferirValorContaUseCase transferirValorContaUseCase, BuscarContaUseCase buscarContaUseCase) {
        this.criarContaUseCase = criarContaUseCase;
        this.creditarValorContaUseCase = creditarValorContaUseCase;
        this.debitarValorContaUseCase = debitarValorContaUseCase;
        this.transferirValorContaUseCase = transferirValorContaUseCase;
        this.buscarContaUseCase = buscarContaUseCase;
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

    @PostMapping(path="/criar")
    public ModelAndView criar(CriarContaRequest request, RedirectAttributes attr){

        ContaDetailResponse response = criarContaUseCase.handle(request);
        ModelAndView modelAndView = getModelAndViewDetail(response);

        attr.addFlashAttribute("success", "Conta cadastrada com sucesso");

        return modelAndView;
    }

    @PostMapping(path = "/creditar")
    public ModelAndView creditar(CreditarValorContaRequest request){

        ContaDetailResponse response = creditarValorContaUseCase.handle(request);
        return getModelAndViewDetail(response);

    }

    @PostMapping(path = "/debitar")
    public ModelAndView debitar(DebitarValorContaRequest request){
        ContaDetailResponse response = debitarValorContaUseCase.handle(request);
        return getModelAndViewDetail(response);

    }

    @PostMapping(path = "/transferir")
    public ModelAndView transferir(TransferirValorContaRequest request){
        ContaDetailResponse response = transferirValorContaUseCase.handle(request);
        return getModelAndViewDetail(response);

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
