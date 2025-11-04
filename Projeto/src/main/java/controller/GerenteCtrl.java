package controller;

import connection.ComandoSQL;
import connection.ConexaoMySQL;
import model.Gerente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Exceptions.QtdEquipesInvException; // Import da sua exceção

public class GerenteCtrl {

    private final ComandoSQL comando;

    public GerenteCtrl() {
        this.comando = new ComandoSQL();
    }

    public boolean adiciona(Gerente g) {
        String sql = "INSERT INTO funcionario (cpf, nome, email, salario, cargo, departamento, qtdEquipesGerenc) VALUES ("
                    + g.getCpf() + ", '" + g.getNome() + "', '" + g.getEmail() + "', " + g.getSalario() + ", "
                    + "'Gerente', '" + g.getDepartamento() + "', " + g.getQtdEquipesGerenc() + ")";
        boolean sucesso = comando.executarUpdate(sql);

        // Se o ComandoSQL retornou true, mostramos a mensagem de sucesso
        if (sucesso) {
             JOptionPane.showMessageDialog(null, "Programador cadastrado com sucesso!");
        }
        
        // Repassa o resultado para o Formulário
        return sucesso;
    }

    public void atualiza(Gerente g) {
        String sql = "UPDATE funcionario SET nome = '" + g.getNome() + "', email = '" + g.getEmail() + "', "
                + "salario = " + g.getSalario() + ", departamento = '" + g.getDepartamento() + "', "
                + "', qtdEquipesGerenc = " + g.getQtdEquipesGerenc() + " "
                + "WHERE cpf = " + g.getCpf() + " AND cargo = 'Gerente'";
        comando.executarUpdate(sql);
    }

    public void exclui(int cpf) {
        String sql = "DELETE FROM funcionario WHERE cpf = " + cpf + " AND cargo = 'Gerente'";
        comando.executarUpdate(sql);
    }

    // MÉTODO NOVO - Busca um único gerente pelo CPF
    public Gerente buscaPorCpf(int cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = " + cpf + " AND cargo = 'Gerente'";
        ResultSet rs = null;
        Gerente g = null;

        try {
            rs = comando.executarQuery(sql);
            if (rs != null && rs.next()) {
                g = new Gerente();
                g.setCpf(rs.getInt("cpf"));
                g.setNome(rs.getString("nome"));
                g.setEmail(rs.getString("email"));
                g.setSalario(rs.getDouble("salario"));
                g.setDepartamento(rs.getString("departamento"));
                try {
                    g.setQtdEquipesGerenc(rs.getInt("qtdEquipesGerenc"));
                } catch (QtdEquipesInvException e) {
                    JOptionPane.showMessageDialog(null, "Dado inválido no banco para a quantidade de equipes!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar gerente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return g;
    }

    // MÉTODO ATUALIZADO - Lista todos os gerentes
    public List<Gerente> listaTodos() {
        String sql = "SELECT * FROM funcionario WHERE cargo = 'Gerente'";
        ResultSet rs = null;
        List<Gerente> gerentes = new ArrayList<>();

        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    Gerente g = new Gerente();
                    g.setCpf(rs.getInt("cpf"));
                    g.setNome(rs.getString("nome"));
                    g.setEmail(rs.getString("email"));
                    g.setSalario(rs.getDouble("salario"));
                    g.setDepartamento(rs.getString("departamento"));
                    try {
                        g.setQtdEquipesGerenc(rs.getInt("qtdEquipesGerenc"));
                    } catch (QtdEquipesInvException e) {
                        System.err.println("Dado inválido no banco para a quantidade de equipes: " + e.getMessage());
                    }
                    gerentes.add(g);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar gerentes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return gerentes;
    }
    public List<Gerente> buscarPorFiltros(String nome, String departamento, String qtdEquipes) {
        String sql = "SELECT * FROM funcionario WHERE cargo = 'Gerente'";

        // Adiciona os filtros dinamicamente
        if (nome != null && !nome.trim().isEmpty()) {
            sql += " AND nome LIKE '%" + nome.trim() + "%'";
        }
        if (departamento != null && !departamento.trim().isEmpty()) {
            sql += " AND departamento LIKE '%" + departamento.trim() + "%'";
        }
        if (qtdEquipes != null && !qtdEquipes.trim().isEmpty()) {
            try {
                int qtd = Integer.parseInt(qtdEquipes.trim());
                sql += " AND qtdEquipesGerenc = " + qtd;
            } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(null, "Filtro 'Qtd. Equipes' deve ser um número.", "Erro de Filtro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Executa a busca
        ResultSet rs = null;
        List<Gerente> gerentes = new ArrayList<>();
        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    Gerente g = new Gerente();
                    g.setCpf(rs.getInt("cpf"));
                    g.setNome(rs.getString("nome"));
                    g.setEmail(rs.getString("email"));
                    g.setSalario(rs.getDouble("salario"));
                    g.setDepartamento(rs.getString("departamento"));
                    try {
                        g.setQtdEquipesGerenc(rs.getInt("qtdEquipesGerenc"));
                    } catch (QtdEquipesInvException e) {
                        System.err.println("Dado inválido no banco para Qtd. Equipes: " + e.getMessage());
                    }
                    gerentes.add(g);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar gerentes por filtro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return gerentes;
    }
}