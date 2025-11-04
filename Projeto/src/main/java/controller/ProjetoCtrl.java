package controller;

import connection.ComandoSQL;
import connection.ConexaoMySQL;
import model.Funcionario;
import model.Programador;
import model.Gerente;
import model.Projeto;
import model.AnalistaDeDados;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProjetoCtrl {

    private final ComandoSQL comando;
    private final GerenteCtrl gerenteCtrl;

    public ProjetoCtrl() {
        this.comando = new ComandoSQL();
        this.gerenteCtrl = new GerenteCtrl();
    }

    // --- MÉTODOS CRUD PARA O PROJETO ---

    public void adiciona(Projeto p) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataInicioSQL = (p.getDataInicio() != null) ? "'" + sdf.format(p.getDataInicio()) + "'" : "NULL";
        String prazoFinalSQL = (p.getPrazoFinal() != null) ? "'" + sdf.format(p.getPrazoFinal()) + "'" : "NULL";
        Integer gerenteCpf = (p.getGerenteResponsavel() != null) ? (int) p.getGerenteResponsavel().getCpf() : null;

        String sql = "INSERT INTO projeto (nomeProjeto, status, dataInicio, prazoFinal, gerente_responsavel_cpf) VALUES ("
                + "'" + p.getNomeProjeto() + "', '" + p.getStatus() + "', " + dataInicioSQL + ", "
                + prazoFinalSQL + ", " + gerenteCpf + ")";
        comando.executarUpdate(sql);
    }

    public void atualiza(Projeto p) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataInicioSQL = (p.getDataInicio() != null) ? "'" + sdf.format(p.getDataInicio()) + "'" : "NULL";
        String prazoFinalSQL = (p.getPrazoFinal() != null) ? "'" + sdf.format(p.getPrazoFinal()) + "'" : "NULL";
        Integer gerenteCpf = (p.getGerenteResponsavel() != null) ? (int) p.getGerenteResponsavel().getCpf() : null;

        String sql = "UPDATE projeto SET status = '" + p.getStatus() + "', dataInicio = " + dataInicioSQL + ", "
                + "prazoFinal = " + prazoFinalSQL + ", gerente_responsavel_cpf = " + gerenteCpf + " "
                + "WHERE id_projeto = " + p.getIdProjeto(); // Atualiza pelo ID
        comando.executarUpdate(sql);
    }

    public void exclui(int idProjeto) {
        // O BD está configurado com ON DELETE CASCADE,
        // então apagar o projeto também remove os vínculos da equipe.
        String sql = "DELETE FROM projeto WHERE id_projeto = " + idProjeto;
        comando.executarUpdate(sql);
    }

    public Projeto buscaPorId(int idProjeto) {
        String sql = "SELECT * FROM projeto WHERE id_projeto = " + idProjeto;
        ResultSet rs = null;
        Projeto p = null;
        try {
            rs = comando.executarQuery(sql);
            if (rs != null && rs.next()) {
                p = new Projeto();
                p.setIdProjeto(rs.getInt("id_projeto"));
                p.setNomeProjeto(rs.getString("nomeProjeto"));
                p.setStatus(rs.getString("status"));
                p.setDataInicio(rs.getDate("dataInicio"));
                p.setPrazoFinal(rs.getDate("prazoFinal"));
                
                // Busca o objeto Gerente responsável
                int gerenteCpf = rs.getInt("gerente_responsavel_cpf");
                if (!rs.wasNull()) {
                    Gerente g = new GerenteCtrl().buscaPorCpf(gerenteCpf);
                    p.setGerenteResponsavel(g);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar projeto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) { fecharRecursos(rs); }
        }
        return p;
    }

    public List<Projeto> listaTodos() {
        String sql = "SELECT * FROM projeto";
        ResultSet rs = null;
        List<Projeto> projetos = new ArrayList<>();
        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    Projeto p = new Projeto();
                    p.setIdProjeto(rs.getInt("id_projeto"));
                    p.setNomeProjeto(rs.getString("nomeProjeto"));
                    p.setStatus(rs.getString("status"));
                    p.setDataInicio(rs.getDate("dataInicio"));
                    p.setPrazoFinal(rs.getDate("prazoFinal"));
                    
                    // Pega o CPF do gerente
                    int gerenteCpf = rs.getInt("gerente_responsavel_cpf");
                    if (!rs.wasNull()) {
                        // Para otimizar, podemos apenas criar um gerente "fake" com o CPF
                        // ou buscar o gerente completo. Vamos buscar o completo.
                         Gerente g = new GerenteCtrl().buscaPorCpf(gerenteCpf);
                         p.setGerenteResponsavel(g);
                    }
                    projetos.add(p);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar projetos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) { fecharRecursos(rs); }
        }
        return projetos;
    }

    // --- MÉTODOS PARA GERENCIAR A EQUIPE ---

    public void adicionarMembroEquipe(int idProjeto, long cpfFuncionario) {
        String sql = "INSERT INTO projeto_equipe (projeto_id, funcionario_cpf) VALUES ("
                + idProjeto + ", "
                + cpfFuncionario + ")";
        
        comando.executarUpdate(sql);
    }

    public void removerMembroEquipe(int idProjeto, long cpfFuncionario) {
        String sql = "DELETE FROM projeto_equipe WHERE projeto_id = " + idProjeto
                + " AND funcionario_cpf = " + cpfFuncionario;
        comando.executarUpdate(sql);
    }

    public List<Funcionario> listarMembros(int idProjeto) {
        // Este SQL busca todos os funcionários que ESTÃO na tabela projeto_equipe
        String sql = "SELECT * FROM funcionario WHERE cpf IN (SELECT funcionario_cpf FROM projeto_equipe WHERE projeto_id = " + idProjeto + ")";
        return new FuncionarioCtrl().executarBusca(sql);
    }

    public List<Funcionario> listarNaoMembros(int idProjeto) {
        // Este SQL busca todos os funcionários que NÃO ESTÃO na tabela projeto_equipe
        String sql = "SELECT * FROM funcionario WHERE cpf NOT IN (SELECT funcionario_cpf FROM projeto_equipe WHERE projeto_id = " + idProjeto + ")";
        return new FuncionarioCtrl().executarBusca(sql);
    }
    
    // Método utilitário para fechar recursos
    private void fecharRecursos(ResultSet rs) {
        try {
            Statement st = rs.getStatement();
            Connection con = st.getConnection();
            ConexaoMySQL.fecharConexao(con, st, rs);
        } catch (SQLException e) {
            System.err.println("Erro Crítico: Falha ao fechar os recursos da consulta.");
        }
    }
    
    /**
     * ATUALIZADO: Este método agora insere o projeto E RETORNA o ID gerado.
     * @param p O projeto a ser salvo.
     * @return O ID (id_projeto) gerado pelo banco de dados, ou -1 se falhar.
     */
    public int adicionaEretornaId(Projeto p) {
        String sql = "INSERT INTO projeto (nomeProjeto, status, dataInicio, prazoFinal, gerente_responsavel_cpf) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int idGerado = -1;

        try {
            con = ConexaoMySQL.getConexao();
            // Pede ao PreparedStatement para retornar as chaves geradas (o ID)
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // 1. Nome do Projeto
            pstmt.setString(1, p.getNomeProjeto());
            // 2. Status
            pstmt.setString(2, p.getStatus());

            // 3. Data Início
            if (p.getDataInicio() != null) {
                pstmt.setDate(3, new java.sql.Date(p.getDataInicio().getTime()));
            } else {
                pstmt.setNull(3, java.sql.Types.DATE);
            }

            // 4. Prazo Final
            if (p.getPrazoFinal() != null) {
                pstmt.setDate(4, new java.sql.Date(p.getPrazoFinal().getTime()));
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }

            // 5. Gerente Responsável
            if (p.getGerenteResponsavel() != null) {
                pstmt.setLong(5, p.getGerenteResponsavel().getCpf());
            } else {
                pstmt.setNull(5, java.sql.Types.INTEGER);
            }

            // Executa a inserção
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                // Recupera o ID gerado
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar projeto: " + e.getMessage(), "Erro de SQL", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fecha todos os recursos
            ConexaoMySQL.fecharConexao(con, pstmt, rs);
        }
        return idGerado; // Retorna o ID
    }
    
    /**
     * NOVO MÉTODO: Busca projetos com base em filtros dinâmicos.
     */
    public List<Projeto> buscarPorFiltros(String nome, String status, String gerenteNome) { // <-- 1. Parâmetro mudou
        String sql = "SELECT * FROM projeto WHERE 1=1";

        if (nome != null && !nome.trim().isEmpty()) {
            sql += " AND nomeProjeto LIKE '%" + nome.trim() + "%'";
        }
        if (status != null && !status.trim().isEmpty()) {
            sql += " AND status LIKE '%" + status.trim() + "%'";
        }
        
        // --- 2. Lógica do filtro de gerente foi alterada ---
        if (gerenteNome != null && !gerenteNome.trim().isEmpty()) {
            // Agora, fazemos uma sub-consulta para encontrar o CPF do gerente pelo NOME
            sql += " AND gerente_responsavel_cpf IN (";
            sql += " SELECT cpf FROM funcionario";
            sql += " WHERE cargo = 'Gerente' AND nome LIKE '%" + gerenteNome.trim() + "%'";
            sql += ")";
        }
        // --- Fim da alteração ---
        
        // O restante do método (try-catch-finally para executar a busca)
        // continua exatamente o mesmo.
        
        ResultSet rs = null;
        List<Projeto> projetos = new ArrayList<>();
        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    Projeto p = new Projeto();
                    p.setIdProjeto(rs.getInt("id_projeto"));
                    p.setNomeProjeto(rs.getString("nomeProjeto"));
                    p.setStatus(rs.getString("status"));
                    p.setDataInicio(rs.getDate("dataInicio"));
                    p.setPrazoFinal(rs.getDate("prazoFinal"));
                    int cpf = rs.getInt("gerente_responsavel_cpf");
                    if (!rs.wasNull()) {
                         p.setGerenteResponsavel(gerenteCtrl.buscaPorCpf(cpf));
                    }
                    projetos.add(p);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar projetos por filtro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    Statement st = rs.getStatement();
                    Connection con = st.getConnection();
                    ConexaoMySQL.fecharConexao(con, st, rs);
                } catch (SQLException e) {
                    System.err.println("Erro Crítico: Falha ao fechar os recursos da consulta.");
                }
            }
        }
        return projetos;
    }
    /**
     * NOVO MÉTODO: Busca membros de uma equipe e retorna por tipo.
     */
    public List<Funcionario> listarMembrosPorCargo(int idProjeto, String cargo) {
        String sql = "SELECT * FROM funcionario f " +
                     "JOIN projeto_equipe eq ON f.cpf = eq.funcionario_cpf " +
                     "WHERE eq.projeto_id = " + idProjeto + " AND f.cargo = '" + cargo + "'";
        
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    // Instancia o objeto correto
                    if(cargo.equals("Programador")) {
                        Programador p = new Programador();
                        // Lógica de "preencherProgramador" está aqui
                        p.setCpf(rs.getInt("cpf"));
                        p.setNome(rs.getString("nome"));
                        p.setEmail(rs.getString("email"));
                        p.setSalario(rs.getDouble("salario"));
                        p.setLinguagemP(rs.getString("linguagemP"));
                        p.setNivelSen(rs.getString("nivelSen"));
                        funcionarios.add(p);
                    } else if (cargo.equals("AnalistaDeDados")) {
                        AnalistaDeDados a = new AnalistaDeDados();
                        // Lógica de "preencherAnalista" está aqui
                        a.setCpf(rs.getInt("cpf"));
                        a.setNome(rs.getString("nome"));
                        a.setEmail(rs.getString("email"));
                        a.setSalario(rs.getDouble("salario"));
                        a.setFerramenta(rs.getString("ferramenta"));
                        a.setQtdRelatorios(rs.getInt("qtdRelatorios"));
                        funcionarios.add(a);
                    }
                }
            }
        } catch (Exception e) { // Pega SQLException e as exceções de setNivel/setQtdEquipes
            JOptionPane.showMessageDialog(null, "Erro ao listar membros da equipe: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Lógica de "fecharRecursosConsulta" está aqui
            if (rs != null) {
                try {
                    Statement st = rs.getStatement();
                    Connection con = st.getConnection();
                    ConexaoMySQL.fecharConexao(con, st, rs);
                } catch (SQLException e) {
                    System.err.println("Erro Crítico: Falha ao fechar os recursos da consulta.");
                }
            }
        }
        return funcionarios;
    }
    
}