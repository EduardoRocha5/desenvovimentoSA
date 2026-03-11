# Sistema de Gerenciamento de Funcionários

API desenvolvida em **Java com Spring Boot** para gerenciamento de funcionários, departamentos e projetos.  
O sistema permite cadastrar, consultar, atualizar e excluir dados relacionados aos colaboradores e suas atividades na empresa.

## 📁 Estrutura do Projeto

O repositório do projeto está organizado da seguinte forma:

- **src/** : Contém o código-fonte da aplicação.
  - **controllers/** : Responsáveis por receber as requisições da API e retornar as respostas.
  - **services/** : Implementação das regras de negócio do sistema.
  - **repositories/** : Interfaces responsáveis pela comunicação com o banco de dados utilizando JPA.
  - **model/** : Classes que representam as entidades do sistema (Funcionário, Departamento e Projeto).
  - **dto/** : Objetos de transferência de dados utilizados na comunicação da API.

- **docs/** : Documentação do projeto, como diagramas e explicações da arquitetura.

- **tests/** : Testes unitários e de integração utilizados para validar o funcionamento da aplicação.

- **scripts/** : Scripts auxiliares para configuração ou execução do projeto.

- **README.md** : Documento principal com informações e instruções sobre o projeto.
