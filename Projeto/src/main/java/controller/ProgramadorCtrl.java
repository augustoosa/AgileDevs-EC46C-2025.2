package controller;

import connection.ComandoSQL;
import connection.ConexaoMySQL;
import model.Programador;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Exceptions.NivelSenioridadeInvException; // Import da sua exceção

public class ProgramadorCtrl {

    private final ComandoSQL comando;

    public ProgramadorCtrl() {
        this.comando = new ComandoSQL();
    }

    public boolean adiciona(Programador p) {
        // Monta a String SQL (como antes)
        String sql = "INSERT INTO funcionario (cpf, nome, email, salario, cargo, linguagemP, nivelSen) VALUES ("
                + p.getCpf() + ", '" + p.getNome() + "', '" + p.getEmail() + "', " + p.getSalario() + ", "
                + "'Programador', '" + p.getLinguagemP() + "', '" + p.getNivelSen() + "')";

        // Chama o ComandoSQL. Se der erro, ele mesmo mostra o JOptionPane.
        boolean sucesso = comando.executarUpdate(sql);

        // Se o ComandoSQL retornou true, mostramos a mensagem de sucesso
        if (sucesso) {
             JOptionPane.showMessageDialog(null, "Programador cadastrado com sucesso!");
        }
        
        // Repassa o resultado para o Formulário
        return sucesso;
    }

    public void atualiza(Programador p) {
        // (Este método continua o mesmo)
        String sql = "UPDATE funcionario SET nome = '" + p.getNome() + "', email = '" + p.getEmail() + "', "
                + "salario = " + p.getSalario() + ", linguagemP = '" + p.getLinguagemP() + "', "
                + "nivelSen = '" + p.getNivelSen() + "' "
                + "WHERE cpf = " + p.getCpf() + " AND cargo = 'Programador'";
        comando.executarUpdate(sql);
    }

    public void exclui(int cpf) {
        // (Este método continua o mesmo)
        String sql = "DELETE FROM funcionario WHERE cpf = " + cpf + " AND cargo = 'Programador'";
        comando.executarUpdate(sql);
    }

    // MÉTODO NOVO - Busca um único programador pelo CPF
    public Programador buscaPorCpf(int cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = " + cpf + " AND cargo = 'Programador'";
        ResultSet rs = null;
        Programador p = null;

        try {
            rs = comando.executarQuery(sql);
            if (rs != null && rs.next()) { // Verifica se encontrou algum resultado
                p = new Programador();
                p.setCpf(rs.getInt("cpf"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setSalario(rs.getDouble("salario"));
                p.setLinguagemP(rs.getString("linguagemP"));
                try {
                    p.setNivelSen(rs.getString("nivelSen"));
                } catch (NivelSenioridadeInvException e) {
                    JOptionPane.showMessageDialog(null, "Dado inválido no banco para o nível de senioridade!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar programador: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return p; // Retorna o programador encontrado ou null se não encontrar
    }

    // MÉTODO ATUALIZADO - Lista todos os programadores
    public List<Programador> listaTodos() {
        String sql = "SELECT * FROM funcionario WHERE cargo = 'Programador'";
        ResultSet rs = null;
        List<Programador> programadores = new ArrayList<>();

        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    Programador p = new Programador();
                    p.setCpf(rs.getInt("cpf"));
                    p.setNome(rs.getString("nome"));
                    p.setEmail(rs.getString("email"));
                    p.setSalario(rs.getDouble("salario"));
                    p.setLinguagemP(rs.getString("linguagemP"));
                    try {
                        p.setNivelSen(rs.getString("nivelSen"));
                    } catch (NivelSenioridadeInvException e) {
                         System.err.println("Dado inválido no banco para o nível de senioridade: " + e.getMessage());
                    }
                    programadores.add(p);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar programadores: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return programadores;
    }
    
    /* NOVO MÉTODO: Busca programadores com base em filtros dinâmicos.
     * @param nome Filtro para o nome (usa LIKE)
     * @param linguagem Filtro para a linguagem (usa LIKE)
     * @param nivel Filtro para o nível (usa LIKE)
     * @return Uma lista de programadores que atendem aos filtros.
     */
    public List<Programador> buscarPorFiltros(String nome, String linguagem, String nivel) {
        // 1. Começa com o SQL base
        String sql = "SELECT * FROM funcionario WHERE cargo = 'Programador'";

        // 2. Adiciona os filtros dinamicamente (SQL INJECTION VULNERABLE)
        if (nome != null && !nome.trim().isEmpty()) {
            sql += " AND nome LIKE '%" + nome.trim() + "%'";
        }
        if (linguagem != null && !linguagem.trim().isEmpty()) {
            sql += " AND linguagemP LIKE '%" + linguagem.trim() + "%'";
        }
        if (nivel != null && !nivel.trim().isEmpty()) {
            sql += " AND nivelSen LIKE '%" + nivel.trim() + "%'";
        }
        
        // 3. Executa a busca (Lógica de 'executarBusca' está aqui)
        ResultSet rs = null;
        List<Programador> programadores = new ArrayList<>();
        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    // Lógica de "preencherObjeto" está aqui
                    Programador p = new Programador();
                    p.setCpf(rs.getInt("cpf"));
                    p.setNome(rs.getString("nome"));
                    p.setEmail(rs.getString("email"));
                    p.setSalario(rs.getDouble("salario"));
                    p.setLinguagemP(rs.getString("linguagemP"));
                    try {
                        p.setNivelSen(rs.getString("nivelSen"));
                    } catch (NivelSenioridadeInvException e) {
                        System.err.println("Nível de senioridade inválido no banco de dados: " + e.getMessage());
                    }
                    programadores.add(p);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar programadores por filtro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return programadores;
    }
}