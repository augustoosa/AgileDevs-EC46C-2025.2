# 1. História de Usuário

A Tabela 3 a seguir contém as Histórias de Usuárias elicitadas. 

<table>
    <thead>
        <tr style="background-color: purple; color: white" >
            <th style="border-style:solid;border-width:1px;text-align:center">ID</th>
            <th style="border-style:solid;border-width:1px;text-align:center">História de Usuário</th>
            <th style="border-style:solid;border-width:1px;text-align:center">Critérios de aceitação</th>
            <th style="border-style:solid;border-width:1px;text-align:center">Prioridade</th>
            <th style="border-style:solid;border-width:1px;text-align:center">RF/RNF relacionado</th>
            <th style="border-style:solid;border-width:1px;text-align:center">Story Points</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US01</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como administrador do sistema, quero cadastrar novos funcionários informando nome, categoria, salário e departamento, para que eu possa manter um registro organizado da equipe.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>O sistema deve permitir inserir nome, categoria, salário e departamento.</li><li>O sistema deve confirmar o cadastro com uma mensagem de sucesso.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF01</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US02</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como administrador do sistema, quero visualizar a lista de funcionários cadastrados com suas principais informações, para que eu consiga acompanhar e consultar rapidamente os dados da equipe.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>O sistema deve exibir todos os funcionários cadastrados em formato de lista.</li><li> Cada funcionário deve mostrar nome, categoria, salário e departamento.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle"> Média </td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF02</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
        </tr>
        <tr>
            <span id="ustory-01"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">US03</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1">Como administrador do sistema, quero editar as informações de um funcionário já cadastrado, para que os dados fiquem sempre corretos e atualizados.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle" rowspan="1"><ol><li>O sistema deve permitir selecionar um funcionário já cadastrado.</li><li>O sistema deve permitir editar dados como categoria, salário e departamento.</li><li>As alterações devem ser salvas e refletidas imediatamente na consulta de funcionários.</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF03</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
        </tr>
        <tr>
            <span id="ustory-04"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">US04</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Como administrador do sistema, quero excluir um funcionário do sistema, para que eu possa manter a base de dados atualizada apenas com os colaboradores ativos.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle"><ol><li>O sistema deve permitir selecionar um funcionário para exclusão</li><li>O sistema deve solicitar confirmação antes de excluir o funcionário</li><li>O funcionário excluído não deve mais aparecer na lista de consulta</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF04</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
        </tr>
        <tr>
            <span id="ustory-05"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">US05</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Como administrador do sistema, quero filtrar a lista de funcionários por departamento, para que eu consiga visualizar apenas os colaboradores de um setor específico.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle"> <ol><li>O sistema deve disponibilizar um campo de filtro por departamento</li><li>A lista deve atualizar exibindo apenas os funcionários pertencentes ao departamento selecionado</li><li>Deve ser possível limpar o filtro para voltar à lista completa</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Média</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF05</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
        </tr>
        <tr>
            <span id="ustory-06"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">US06</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Como administrador do sistema, quero visualizar o valor total da folha de pagamento de todos os funcionários, para que eu                 possa ter controle sobre os custos com salários.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle"><ol><li>O sistema deve somar os salários de todos os funcionários cadastrados</li><li>O sistema deve exibir o valor total da folha de pagamento em tela</li><li>O valor exibido deve atualizar automaticamente quando houver inclusão, edição ou exclusão de funcionários</li></ol></td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF06</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
        </tr>
        <tr>
            <span id="ustory-07"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">US07</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Como administrador do sistema, quero que o sistema valide os dados ao inserir ou editar um funcionário para garantir a qualidade e consistência das informações.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">
                <ol>
                    <li>Nome não pode ser vazio e salário deve ser numérico &gt; 0.</li>
                    <li>Categoria deve pertencer ao catálogo (gerente, programador, analista de dados, etc.).</li>
                    <li>Departamento deve existir (validação de chave estrangeira).</li>
                    <li>Em caso de erro, não salva e exibe mensagem específica por campo.</li>
                </ol>
            </td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Alta</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF08</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
        </tr>
        <tr>
            <span id="ustory-08"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">US08</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Como administrador do sistema, quero gerar relatórios dinâmicos com total de funcionários e média salarial (geral e filtrada) para acompanhar indicadores do quadro de pessoal.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">
                <ol>
                    <li>Permite filtrar por departamento, categoria e período de admissão.</li>
                    <li>Apresenta total de funcionários e média salarial (global e conforme filtros).</li>
                    <li>Cálculos feitos diretamente no BD respeitando os filtros.</li>
                    <li>Permite drill-down para listar funcionários que compõem cada indicador.</li>
                </ol>
            </td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Média</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF09</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
        </tr>
        <tr>
            <span id="ustory-09"></span>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">US09</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Como administrador do sistema, quero visualizar uma consolidação por departamento (quantidade, média, menor e maior salário) e exportar o resultado para apoiar decisões de alocação.</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">
                <ol>
                    <li>Painel por departamento com quantidade, média, mínimo e máximo salarial.</li>
                    <li>Filtros por categoria e status (ativo/inativo, se existir no modelo).</li>
                    <li>Exportação da visão atual (com filtros aplicados) para CSV.</li>
                    <li>Dados consultados no BD com tempo de resposta adequado (&lt;= 2s para até 10k registros agregados).</li>
                </ol>
            </td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">Média</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">RF09</td>
            <td style="border-style:solid;border-width:1px;text-align:center;vertical-align:middle">-</td>
        </tr>
    </tbody>
</table>
```

