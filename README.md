# VollMed

###

Vollmed é uma API RESTful desenvolvida em Java utilizando o Spring Boot. O projeto tem como objetivo gerenciar o cadastro de médicos, pacientes e consultas, oferecendo métodos de autenticação para acessar a API. Este projeto pessoal foi desenvolvido para testar minhas habilidades com Java e Spring Boot. 

###

## Tecnologias Utilizadas

- Java
- Spring Boot
- MySQL
- Flyway (ferramenta de migração de banco de dados)
- Bean Validation (para validações)
- Spring Security (para segurança)
- JSON Web Token (JWT) (para controle de acesso)
- Springdoc OpenAPI (para documentação das rotas)

###

## Funcionalidades

- Cadastro de Médicos: Gerenciamento completo do ciclo de vida dos médicos.
- Cadastro de Pacientes: Gerenciamento completo do ciclo de vida dos pacientes.
- Cadastro de Consultas: Gerenciamento completo do ciclo de vida das consultas.
- Autenticação e Autorização: Utilização de JWT para controlar o acesso à API.
- Validações: Uso do Bean Validation para garantir a integridade dos dados.
- Documentação das Rotas: Utilização do Springdoc para gerar a documentação das rotas da API.

###

## Migração do Banco de Dados

O Flyway é utilizado para gerenciar as migrações do banco de dados. Certifique-se de que as migrações estão na pasta resources/db/migration. Para aplicar as migrações, basta iniciar a aplicação e o Flyway cuidará do resto.
    
