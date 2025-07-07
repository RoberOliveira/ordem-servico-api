package br.edu.ifpr.foz.ordem_servico_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpr.foz.ordem_servico_api.models.Cliente;
import br.edu.ifpr.foz.ordem_servico_api.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAll(){

        return clienteRepository.findAll();
        
    }
    
}
