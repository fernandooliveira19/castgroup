package com.teste.castgroup.core.agencia.controller;

import com.teste.castgroup.core.agencia.model.request.CriarAgenciaRequest;
import com.teste.castgroup.core.agencia.model.usecase.CriarAgenciaUseCase;
import com.teste.castgroup.shared.messages.CastGroupMessages;
import com.teste.castgroup.utils.MessageUtils;
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
    private final MessageUtils messageUtils;
    private static final String SUCESSO = "success";
    private static final String FALHA = "fail";

    public AgenciaController(CriarAgenciaUseCase criarAgenciaUseCase, MessageUtils messageUtils) {
        this.criarAgenciaUseCase = criarAgenciaUseCase;
        this.messageUtils = messageUtils;
    }

    @GetMapping("/cadastrar")
    public String cadastrar(CriarAgenciaRequest request) {

        return "agencias/cadastro";
    }

    @PostMapping(path = "/criar" )
    public String criar(@ModelAttribute CriarAgenciaRequest request, RedirectAttributes attr) {

        try {
            criarAgenciaUseCase.handle(request);
            attr.addFlashAttribute(SUCESSO, messageUtils.getMessage(CastGroupMessages.AGENCIA_CADASTRADA_SUCESSO.getMessageKey()));
        }catch (Exception e){
            attr.addFlashAttribute(FALHA, e.getMessage());
        }

        return "redirect:cadastrar";
    }
}
