package br.edu.ifpr.foz.ordem_servico_api.controllers;

import br.edu.ifpr.foz.ordem_servico_api.dtos.ComentarioDTO;
import br.edu.ifpr.foz.ordem_servico_api.models.Comentario;
import br.edu.ifpr.foz.ordem_servico_api.models.OrdemServico;
import br.edu.ifpr.foz.ordem_servico_api.services.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService service;

    /**
     * Endpoint para CRIAR uma nova Ordem de Serviço.
     * Mapeado para: POST /ordens
     */
    @PostMapping
    public ResponseEntity<OrdemServico> criar(@RequestBody OrdemServico ordemServico) {
        // CORREÇÃO APLICADA AQUI
        OrdemServico criada = service.criar(ordemServico);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    /**
     * Endpoint para LISTAR Ordens de Serviço.
     * Mapeado para: GET /ordens
     * Pode filtrar por email com: GET /ordens?email=exemplo@email.com
     */
    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar(@RequestParam(required = false) String email) {
        List<OrdemServico> ordens;
        if (email != null && !email.isBlank()) {
            ordens = service.listarPorEmailCliente(email);
        } else {
            ordens = service.listar();
        }
        return ResponseEntity.ok(ordens);
    }

    /**
     * Endpoint para FINALIZAR uma Ordem de Serviço.
     * Mapeado para: PUT /ordens/{id}/finalizar
     */
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizar(@PathVariable Long id) {
        service.finalizar(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para CANCELAR uma Ordem de Serviço.
     * Mapeado para: PUT /ordens/{id}/cancelar
     */
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelar(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para ADICIONAR um comentário.
     * Mapeado para: POST /ordens/{id}/comentarios
     */
    @PostMapping("/{id}/comentarios")
    public ResponseEntity<Comentario> adicionarComentario(
            @PathVariable Long id,
            @RequestBody ComentarioDTO comentarioDTO) {
        Comentario comentario = service.adicionarComentario(id, comentarioDTO.getDescricao());
        return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
    }
}