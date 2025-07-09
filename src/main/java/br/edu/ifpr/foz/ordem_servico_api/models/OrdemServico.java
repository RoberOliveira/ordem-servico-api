package br.edu.ifpr.foz.ordem_servico_api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Double preco;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFinalizacao;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    // CORREÇÃO IMPORTANTE: Adicionado o cascade para salvar o cliente junto com a ordem.
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    /**
     * Este método é executado automaticamente antes de uma nova
     * Ordem de Serviço ser salva pela primeira vez.
     * Ele define os valores padrão.
     */
    @PrePersist
    public void prePersist() {
        this.dataAbertura = LocalDateTime.now();
        this.status = StatusOrdemServico.ABERTA;
    }
}