package br.edu.ifpr.foz.ordem_servico_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.foz.ordem_servico_api.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> { // Alterado de Integer para Long
    
    /**
     * Busca um cliente pelo seu endereço de e-mail.
     * O Spring Data JPA cria a query automaticamente baseando-se no nome do método.
     * @param email O e-mail a ser pesquisado.
     * @return um Optional contendo o Cliente, se encontrado.
     */
    Optional<Cliente> findByEmail(String email);

    /**
     * Verifica se um cliente existe pelo seu endereço de e-mail.
     * @param email O e-mail a ser verificado.
     * @return true se o cliente existir, false caso contrário.
     */
    boolean existsByEmail(String email);
}