# REQUISITOS FUNCIONAIS

| ID   |                                 Requisito                                      | Prioridade | Requisitos Relacionados |
| :--: | :----------------------------------------------------------------------------: | :--------: | :---------: |
| RF01 |       O usuário(administrador) deve poder cadastrar novos funcionários                       |  Alta      |      -      |
| RF02 |       O usuário deve poder visualizar uma lista de funcionários cadastrados.   |  Média     |    RF01     |
| RF03 |       O usuário deve poder editar dados já cadastrados de um funcionário       |  Alta      |    RF01     |
| RF04 |       O usuário deve poder excluir um funcionário do sistema.                  | Média      | RF01  |
| RF05 |  O sistema deve permitir filtrar a lista de funcionários por departamento.     | Média | RF02 |
| RF06 |  O sistema deve exibir o salário total da folha de pagamento de todos os funcionários. | Baixa | RF02 |
| RF07 | O sistema deve armazenar todos os dados de funcionários em um banco de dados relacional. | Alta | - |
| RF08 | O sistema deve validar dados no momento da inserção/edição | Alta | RF01, RF03 |
| RF09 | O sistema deve gerar relatórios dinâmicos (quantidade de funcionários, média salarial) a partir do BD. | Média | RF02 |
| RF10 | O usuário pode iniciar um novo projeto, inserindo suas características | Alta | |
| RF11 | O usuário deve inserir funcionarios ja cadastrados a um projeto | Alta | |