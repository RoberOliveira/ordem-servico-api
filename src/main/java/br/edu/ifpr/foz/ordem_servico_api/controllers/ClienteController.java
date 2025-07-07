package br.edu.ifpr.foz.ordem_servico_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.foz.ordem_servico_api.models.Cliente;
import br.edu.ifpr.foz.ordem_servico_api.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<Cliente> findAll(){
        
        return clienteService.findAll();

    }
}
