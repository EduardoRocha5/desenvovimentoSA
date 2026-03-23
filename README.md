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

# 📘 API - Gerenciamento de Funcionários

End Points

## 📂 Departamentos

### 🔹 POST /departamentos
Cria um novo departamento.

**Body:**
```json
{
  "nmDepartamento": "RH",
  "telDepartamento": "11999999999",
  "catDepartamento": "Administrativo"
}
```

**Response:**
```json
{
  "idDepartamento": 1,
  "nmDepartamento": "RH",
  "telDepartamento": "11999999999",
  "catDepartamento": "Administrativo"
}
```

---

### 🔹 GET /departamentos
Lista todos os departamentos.

**Response:**
```json
[
  {
    "idDepartamento": 1,
    "nmDepartamento": "RH",
    "telDepartamento": "11999999999",
    "catDepartamento": "Administrativo"
  }
]
```

---

### 🔹 GET /departamentos/nome/{nome}
Busca um departamento pelo nome.

---

### 🔹 PUT /departamentos/{id}
Atualiza um departamento.

**Body:**
```json
{
  "nmDepartamento": "Financeiro",
  "telDepartamento": "11888888888",
  "catDepartamento": "Financeiro"
}
```

---

### 🔹 DELETE /departamentos/nome/{nome}
Exclui um departamento pelo nome.

---

## 👨‍💼 Funcionários

### 🔹 POST /funcionarios
Cria um novo funcionário.

**Body:**
```json
{
  "nmFuncionario": "João",
  "cpfFuncionario": "123456789",
  "dtAdmissao": "2025-03-22",
  "dtDemissao": null,
  "dtNascimento": "2000-02-23",
  "endFuncionario": "Rua X",
  "salFuncionario": 1000,
  "genFuncionario": "M",
  "carFuncionario": "Dev",
  "idDepartamento": 1,
  "idProjeto": 1
}
```

---

### 🔹 GET /funcionarios
Lista todos os funcionários.

---

### 🔹 GET /funcionarios/{id}
Busca funcionário por ID.

---

### 🔹 DELETE /funcionarios/cpf/{cpf}
Exclui funcionário pelo CPF.

---

## 📊 Projetos

### 🔹 POST /projetos
Cria um novo projeto.

**Body:**
```json
{
  "nmProjeto": "Sistema X",
  "descProjeto": "Projeto de teste",
  "statusProjeto": "EM_ANDAMENTO"
}
```

---

### 🔹 GET /projetos
Lista todos os projetos.

---

### 🔹 GET /projetos/status/{nomeProjeto}
Retorna o status do projeto.

**Response:**
```json
"EM_ANDAMENTO"
```

---

### 🔹 DELETE /projetos/{nome}
Exclui projeto pelo nome.

---

