### **REQUISITOS NÃO FUNCIONAIS**

| ID | Requisito | Prioridade | Requisitos Relacionados |
| :--- | :--- | :--- | :--- |
| RNF01 | O sistema deve garantir que apenas usuários autenticados e autorizados possam acessar as funcionalidades de cadastro, visualização e edição. | Alta | RF01, RF02, RF03 |
| RNF02 | A listagem de funcionários (RF02) deve ser exibida em no máximo 2 segundos, mesmo com 1.000 registros na base de dados. | Média | RF02 |
| RNF03 | O sistema deve realizar backups automáticos e semanais do banco de dados para prevenir a perda de informações dos funcionários. | Alta | RF01, RF02, RF03 |
| RNF04 |	O sistema deve ser compatível com os sistemas operacionais macOS, Windows e Linux nas suas versões mais recentes. |	Média |	RF01, RF02, RF03
| RNF05	| O sistema deve estar disponível 99,9% do tempo, exceto durante manutenções programadas. |	Alta	| Todos |
| RNF06	| O sistema deve utilizar criptografia SSL/TLS para proteger a comunicação entre o navegador do usuário e o servidor.	 |Alta |	RF01 |
