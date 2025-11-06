# AgileDevs-EC46C-2025.2
> *Sistema de Gestão de Funcionários*

---

- **Grupo/Empresa:** AgileDevs  
- **Logo:**
 <img width="479" height="185" alt="image" src="https://github.com/user-attachments/assets/ced57404-9493-4d4b-b035-f795e389a523" />
  
---

- **Disciplina:** Desenvolvimento Ágil  
- **Ano/Semestre:** 2025/2  
- **Professora:** Erica  
- **Instituição:** UTFPR  

---

## 👥 Integrantes do grupo

| Foto | Nome | GitHub |
|:----:|------|:------:|
| <img src="https://github.com/augustoosa.png" alt="Augusto" width="80"/> | Augusto da Silva de Sá | [@augustoosa](https://github.com/augustoosa) |
| <img src="https://github.com/lucaspagnan01.png" alt="Lucas" width="80"/> | Lucas Vieira Pagnan | [@lucaspagnan01](https://github.com/lucaspagnan01) |
| <img src="https://github.com/fzka.png" alt="Allan" width="80"/> | Allan Guilherme de Oliveira Soares de Souza | [@fzka](https://github.com/fzka) |
| <img src="https://github.com/LuanTarumoto.png" alt="Luan" width="80"/> | Luan Tarumoto de Macedo | [@LuanTarumoto](https://github.com/LuanTarumoto) |
| <img src="https://github.com/BrunoNav.png" alt="Bruno" width="80"/> | Bruno Navarro Piatto | [@BrunoNav](https://github.com/BrunoNav) |
| <img src="https://github.com/ricardomarchezan.png" alt="Ricardo" width="80"/> | Ricardo Sampaio Marchezan | [@ricardomarchezan](https://github.com/ricardomarchezan) |

---

## 🧭 Descrição do projeto
**Objetivo:**  
O projeto tem como objetivo atuar como sistema interno (CRUD) utilizado por uma **empresa de desenvolvimento de software** para gerenciar diferentes **tipos de funcionários**, como **gerentes, programadores e analistas de dados**.

**Principais funcionalidades:**  
- Cadastro e exibição de funcionários de diferentes categorias.  
- Manipulação de dados como **nome**, **salário** e **departamento**.  

**Público-alvo:**  
- Empresas de desenvolvimento tecnológico que buscam um sistema simples de gestao de seus funcionários.

---

## 🗂️ Documentos no repositório (Sumário)
- Requisitos Funcionais
- Requisitos Não Funcionais

## 🚀 Como Executar:

Siga estes passos para configurar e executar o projeto em seu ambiente local.

### 1. Pré-requisitos

* JDK 18 ou superior.
* Uma IDE Java (o projeto foi desenvolvido no Apache NetBeans).
* SGBD MySQL Workbench

### 2. Configuração do Banco de Dados

1.  Abra seu gerenciador de banco de dados 
2.  Execute o script SQL abaixo para criar o banco `banco_POO2` e todas as tabelas:

```sql
CREATE DATABASE IF NOT EXISTS `banco_POO2` DEFAULT CHARACTER SET utf8mb4;
USE `banco_POO2`;

CREATE TABLE IF NOT EXISTS `funcionario` (
  `cpf` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NULL,
  `salario` DECIMAL(10, 2) NOT NULL,
  `cargo` VARCHAR(20) NOT NULL,
  `departamento` VARCHAR(45) NULL,
  `qtdEquipesGerenc` INT NULL,
  `ferramenta` VARCHAR(45) NULL,
  `qtdRelatorios` INT NULL,
  `linguagemP` VARCHAR(45) NULL,
  `nivelSen` VARCHAR(20) NULL,
  PRIMARY KEY (`cpf`)
);

CREATE TABLE IF NOT EXISTS `projeto` (
  `id_projeto` INT NOT NULL AUTO_INCREMENT,
  `nomeProjeto` VARCHAR(100) NOT NULL UNIQUE,
  `status` VARCHAR(45) NULL,
  `dataInicio` DATE NULL,
  `prazoFinal` DATE NULL,
  `gerente_responsavel_cpf` INT NULL,
  PRIMARY KEY (`id_projeto`),
  CONSTRAINT `fk_projeto_gerente`
    FOREIGN KEY (`gerente_responsavel_cpf`)
    REFERENCES `funcionario` (`cpf`)
    ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS `projeto_equipe` (
  `projeto_id` INT NOT NULL,
  `funcionario_cpf` INT NOT NULL,
  PRIMARY KEY (`projeto_id`, `funcionario_cpf`),
  CONSTRAINT `fk_equipe_projeto`
    FOREIGN KEY (`projeto_id`)
    REFERENCES `projeto` (`id_projeto`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_equipe_funcionario`
    FOREIGN KEY (`funcionario_cpf`)
    REFERENCES `funcionario` (`cpf`)
    ON DELETE CASCADE
);
```
3. Configuração do Projeto
Abra o projeto no NetBeans.

Navegue até o arquivo connection/ConexaoMySQL.java.

Altere as variáveis USER e PASSWORD para corresponder às suas credenciais do MySQL:
```
// dentro de connection/ConexaoMySQL.java
private static final String USER = "root";
private static final String PASSWORD = "sua_senha_aqui"; // <-- MUDE AQUI
```

4. Executando a Aplicação
Clique com o botão direito no projeto e selecione "Limpar e Construir" (Clean and Build).

Encontre o arquivo View/FormPrincipal.java.

Clique com o botão direito nele e selecione "Executar Arquivo" (Run File).

O formulário principal será aberto.
