package controller;

import connection.ComandoSQL;
import connection.ConexaoMySQL;
import model.AnalistaDeDados;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AnalistaDeDadosCtrl {

    private final ComandoSQL comando;

    public AnalistaDeDadosCtrl() {
        this.comando = new ComandoSQL();
    }

    public boolean adiciona(AnalistaDeDados a) {
        
        String sql = "INSERT INTO funcionario (cpf, nome, email, salario, cargo, ferramenta, qtdRelatorios) VALUES ("
                    + a.getCpf() + ", '" + a.getNome() + "', '" + a.getEmail() + "', " + a.getSalario() + ", "
                    + "'AnalistaDeDados', '" + a.getFerramenta() + "', " + a.getQtdRelatorios() + ")";

        boolean sucesso = comando.executarUpdate(sql);

        // Se o ComandoSQL retornou true, mostramos a mensagem de sucesso
        if (sucesso) {
             JOptionPane.showMessageDialog(null, "Programador cadastrado com sucesso!");
        }
        
        // Repassa o resultado para o Formulário
        return sucesso;
    }

    public void atualiza(AnalistaDeDados a) {
        String sql = "UPDATE funcionario SET nome = '" + a.getNome() + "', email = '" + a.getEmail() + "', "
                + "salario = " + a.getSalario() + ", ferramenta = '" + a.getFerramenta() + "', "
                + "qtdRelatorios = " + a.getQtdRelatorios() + " "
                + "WHERE cpf = " + a.getCpf() + " AND cargo = 'AnalistaDeDados'";
        comando.executarUpdate(sql);
    }

    public void exclui(int cpf) {
        String sql = "DELETE FROM funcionario WHERE cpf = " + cpf + " AND cargo = 'AnalistaDeDados'";
        comando.executarUpdate(sql);
    }

    // MÉTODO NOVO - Busca um único analista pelo CPF
    public AnalistaDeDados buscaPorCpf(int cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = " + cpf + " AND cargo = 'AnalistaDeDados'";
        ResultSet rs = null;
        AnalistaDeDados a = null;

        try {
            rs = comando.executarQuery(sql);
            if (rs != null && rs.next()) {
                a = new AnalistaDeDados();
                a.setCpf(rs.getInt("cpf"));
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));
                a.setSalario(rs.getDouble("salario"));
                a.setFerramenta(rs.getString("ferramenta"));
                a.setQtdRelatorios(rs.getInt("qtdRelatorios"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar analista: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return a;
    }

    // MÉTODO ATUALIZADO - Lista todos os analistas
    public List<AnalistaDeDados> listaTodos() {
        String sql = "SELECT * FROM funcionario WHERE cargo = 'AnalistaDeDados'";
        ResultSet rs = null;
        List<AnalistaDeDados> analistas = new ArrayList<>();

        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    AnalistaDeDados a = new AnalistaDeDados();
                    a.setCpf(rs.getInt("cpf"));
                    a.setNome(rs.getString("nome"));
                    a.setEmail(rs.getString("email"));
                    a.setSalario(rs.getDouble("salario"));
                    a.setFerramenta(rs.getString("ferramenta"));
                    a.setQtdRelatorios(rs.getInt("qtdRelatorios"));
                    analistas.add(a);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar analistas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return analistas;
    }
    
    /*MÉTODO: Busca analistas com base em filtros dinâmicos.
     * @param nome Filtro para o nome (usa LIKE)
     * @param ferramenta Filtro para a ferramenta (usa LIKE)
     * @param qtdRelatorios Filtro para a quantidade (usa =)
     * @return Uma lista de analistas que atendem aos filtros.
     */
    public List<AnalistaDeDados> buscarPorFiltros(String nome, String ferramenta, String qtdRelatorios) {
        String sql = "SELECT * FROM funcionario WHERE cargo = 'AnalistaDeDados'";

        // Adiciona os filtros dinamicamente
        if (nome != null && !nome.trim().isEmpty()) {
            sql += " AND nome LIKE '%" + nome.trim() + "%'";
        }
        if (ferramenta != null && !ferramenta.trim().isEmpty()) {
            sql += " AND ferramenta LIKE '%" + ferramenta.trim() + "%'";
        }
        if (qtdRelatorios != null && !qtdRelatorios.trim().isEmpty()) {
            // Tenta converter para número, pois a coluna é numérica
            try {
                int qtd = Integer.parseInt(qtdRelatorios.trim());
                sql += " AND qtdRelatorios = " + qtd;
            } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(null, "Filtro 'Qtd. Relatórios' deve ser um número.", "Erro de Filtro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Executa a busca
        ResultSet rs = null;
        List<AnalistaDeDados> analistas = new ArrayList<>();
        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    AnalistaDeDados a = new AnalistaDeDados();
                    a.setCpf(rs.getInt("cpf"));
                    a.setNome(rs.getString("nome"));
                    a.setEmail(rs.getString("email"));
                    a.setSalario(rs.getDouble("salario"));
                    a.setFerramenta(rs.getString("ferramenta"));
                    a.setQtdRelatorios(rs.getInt("qtdRelatorios"));
                    analistas.add(a);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar analistas por filtro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return analistas;
    }
}