# API de Gestão de Ordens de Serviço

## Disciplina de Desenvolvimento Web III
[cite_start]**Professor:** Jefferson Chaves [cite: 4]
**Aluno:** [Rober Panta de Oliveira]
[cite_start]**Instituição:** Instituto Federal do Paraná (IFPR) [cite: 1]

---

## 1. Descrição do Projeto

Este projeto consiste na implementação de uma API REST para a gestão de ordens de serviço, utilizando o framework Spring Boot. [cite_start]A API permite criar, listar, finalizar, cancelar e adicionar comentários a ordens de serviço, que estão sempre associadas a um cliente. [cite: 9, 10, 11]

## 2. Tecnologias Utilizadas

* [cite_start]**Java 17+** [cite: 50]
* **Spring Boot**
* [cite_start]**Spring Web:** Para a construção de endpoints REST. [cite: 55]
* [cite_start]**Spring Data JPA:** Para a persistência de dados e comunicação com o banco. [cite: 56]
* **Hibernate:** Como implementação da JPA.
* [cite_start]**Maven:** Para gerenciamento de dependências. [cite: 46]
* [cite_start]**Lombok:** Para reduzir código boilerplate. [cite: 54]
* [cite_start]**MySQL:** Como sistema de gerenciamento de banco de dados. [cite: 39]

## 3. Pré-requisitos

Antes de executar o projeto, certifique-se de que você tem os seguintes softwares instalados:

* JDK 17 ou superior
* Maven 3.x
* MySQL Server

## 4. Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    cd ordem-servico-api
    ```

2.  **Configure o Banco de Dados:**
    Abra o arquivo `src/main/resources/application.properties` e configure as credenciais do seu banco de dados MySQL.

    ```properties
    # Configuração do Servidor
    server.port=8080

    # Configuração do Banco de Dados
    spring.datasource.url=jdbc:mysql://localhost:3306/ordem_servico_db?createDatabaseIfNotExist=true
    spring.datasource.username=seu_usuario_mysql
    spring.datasource.password=sua_senha_mysql

    # Configuração do Hibernate
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3.  **Execute a Aplicação:**
    Utilize o Maven para compilar e executar o projeto:
    ```bash
    mvn spring-boot:run
    ```
    A API estará disponível em `http://localhost:8080`.

## 5. Endpoints da API

A seguir estão os endpoints disponíveis na API.

| Método | Endpoint                             | Descrição                                         | Exemplo de Corpo (Body)                                                                                             |
| :----- | :----------------------------------- | :-------------------------------------------------- | :------------------------------------------------------------------------------------------------------------------ |
| `POST` | `/ordens`                            | [cite_start]Cria uma nova ordem de serviço. [cite: 13]                   | `{ "descricao": "...", "preco": 150.0, "cliente": { "nome": "...", "email": "...", "telefone": "..." } }` |
| `GET`  | `/ordens`                            | [cite_start]Lista todas as ordens de serviço. [cite: 15]                 | N/A                                                                                                                 |
| `GET`  | `/ordens?email={email}`              | [cite_start]Lista ordens de serviço de um cliente específico.  | N/A                                                                                                                 |
| `POST` | `/ordens/{id}/comentarios`           | [cite_start]Adiciona um comentário a uma ordem de serviço. [cite: 14]      | `{ "descricao": "Serviço finalizado." }`                                                                          |
| `PUT`  | `/ordens/{id}/finalizar`             | Finaliza uma ordem de serviço.                      | N/A                                                                                                                 |
| `PUT`  | `/ordens/{id}/cancelar`              | Cancela uma ordem de serviço.                       | N/A                                                                                                                 |
