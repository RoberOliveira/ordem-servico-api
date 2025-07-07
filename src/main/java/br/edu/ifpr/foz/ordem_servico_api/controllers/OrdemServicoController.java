package br.edu.ifpr.foz.ordem_servico_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.foz.ordem_servico_api.models.OrdemServico;
import br.edu.ifpr.foz.ordem_servico_api.services.OrdemServicoService;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {

    @Autowired
    OrdemServicoService ordemServicoService;

    @PostMapping
    public OrdemServico save(@RequestBody OrdemServico ordemServico){

        return ordemServicoService.save(ordemServico);

    }

    
}
