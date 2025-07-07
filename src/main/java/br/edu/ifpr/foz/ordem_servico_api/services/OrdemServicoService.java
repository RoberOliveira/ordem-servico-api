package br.edu.ifpr.foz.ordem_servico_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpr.foz.ordem_servico_api.models.OrdemServico;
import br.edu.ifpr.foz.ordem_servico_api.repositories.OrdemServicoRepository;

@Service
public class OrdemServicoService {

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public OrdemServico save(OrdemServico ordemServico) {
        
        return ordemServicoRepository.save(ordemServico);
    }
    
}
