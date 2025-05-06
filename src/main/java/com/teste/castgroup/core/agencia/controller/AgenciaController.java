package com.teste.castgroup.core.agencia.controller;

import com.teste.castgroup.core.agencia.model.request.CriarAgenciaRequest;
import com.teste.castgroup.core.agencia.model.usecase.CriarAgenciaUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/agencias")
public class AgenciaController {

    private final CriarAgenciaUseCase criarAgenciaUseCase;


    public AgenciaController(CriarAgenciaUseCase criarAgenciaUseCase) {
        this.criarAgenciaUseCase = criarAgenciaUseCase;
    }

    @GetMapping("/cadastrar")
    public String cadastrar(CriarAgenciaRequest request) {

        return "agencias/cadastro";
    }

    @PostMapping(path = "/criar" )
    public String criar(@ModelAttribute CriarAgenciaRequest request, RedirectAttributes attr) {

        criarAgenciaUseCase.handle(request);

        attr.addFlashAttribute("success", "Agencia cadastrada com sucesso");
        return "redirect:cadastrar";
    }
}
