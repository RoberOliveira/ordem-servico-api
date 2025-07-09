package br.edu.ifpr.foz.ordem_servico_api.services;

import br.edu.ifpr.foz.ordem_servico_api.models.Cliente;
import br.edu.ifpr.foz.ordem_servico_api.models.Comentario;
import br.edu.ifpr.foz.ordem_servico_api.models.OrdemServico;
import br.edu.ifpr.foz.ordem_servico_api.models.StatusOrdemServico;
import br.edu.ifpr.foz.ordem_servico_api.repositories.ClienteRepository;
import br.edu.ifpr.foz.ordem_servico_api.repositories.ComentarioRepository;
import br.edu.ifpr.foz.ordem_servico_api.repositories.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Injeta todas as dependências 'final' via construtor
public class OrdemServicoService {

    // Injeção de dependência consistente via construtor
    private final OrdemServicoRepository ordemServicoRepository;
    private final ClienteRepository clienteRepository;
    private final ComentarioRepository comentarioRepository;

    /**
     * Cria uma nova Ordem de Serviço, definindo valores padrão.
     * Renomeado de 'save' para 'criar' para corresponder ao seu controller.
     */
    @Transactional
    public OrdemServico criar(OrdemServico ordemServico) {
        // Lógica para buscar ou criar o cliente
        Cliente cliente = clienteRepository.findByEmail(ordemServico.getCliente().getEmail())
                .orElse(ordemServico.getCliente());

        ordemServico.setCliente(cliente);

        // As linhas abaixo podem ser REMOVIDAS, pois @PrePersist já faz isso!
        // ordemServico.setStatus(StatusOrdemServico.ABERTA);
        // ordemServico.setDataAbertura(LocalDateTime.now());
        // ordemServico.setDataFinalizacao(null);

        return ordemServicoRepository.save(ordemServico);
    }

    /**
     * Lista todas as Ordens de Serviço.
     */
    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    /**
     * Lista Ordens de Serviço filtrando pelo e-mail do cliente.
     */
    public List<OrdemServico> listarPorEmailCliente(String email) {
        return ordemServicoRepository.findByClienteEmail(email);
    }

    /**
     * Finaliza uma Ordem de Serviço, aplicando as regras de negócio.
     */
    @Transactional
    public Optional<OrdemServico> finalizar(Long id) {
        return ordemServicoRepository.findById(id).map(ordem -> {
            if (ordem.getStatus() != StatusOrdemServico.ABERTA) {
                throw new IllegalStateException("Apenas ordens com status ABERTA podem ser finalizadas.");
            }
            ordem.setStatus(StatusOrdemServico.FINALIZADA);
            ordem.setDataFinalizacao(LocalDateTime.now());
            return ordemServicoRepository.save(ordem);
        });
    }

    /**
     * Cancela uma Ordem de Serviço, aplicando as regras de negócio.
     */
    @Transactional
    public Optional<OrdemServico> cancelar(Long id) {
        return ordemServicoRepository.findById(id).map(ordem -> {
            if (ordem.getStatus() != StatusOrdemServico.ABERTA) {
                throw new IllegalStateException("Apenas ordens com status ABERTA podem ser canceladas.");
            }
            ordem.setStatus(StatusOrdemServico.CANCELADA);
            ordem.setDataFinalizacao(LocalDateTime.now()); // Registra a data do evento
            return ordemServicoRepository.save(ordem);
        });
    }

    /**
     * Adiciona um comentário a uma Ordem de Serviço existente.
     */
    @Transactional
    public Comentario adicionarComentario(Long ordemId, String descricao) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemId)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + ordemId));

        Comentario comentario = new Comentario();
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);
        comentario.setDataEnvio(LocalDateTime.now()); // Define a data de envio do comentário

        return comentarioRepository.save(comentario);
    }
}