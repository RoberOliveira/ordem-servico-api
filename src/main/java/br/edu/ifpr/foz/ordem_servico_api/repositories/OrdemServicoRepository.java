package br.edu.ifpr.foz.ordem_servico_api.repositories;

import br.edu.ifpr.foz.ordem_servico_api.models.OrdemServico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    List<OrdemServico> findByClienteEmail(String email);
}