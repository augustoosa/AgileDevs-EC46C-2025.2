package controller;

import connection.ComandoSQL;
import connection.ConexaoMySQL;
import model.Funcionario;
import model.Gerente;
import model.Programador;
import model.AnalistaDeDados;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controller genérico para operações que envolvem todos os tipos de funcionários.
 */
public class FuncionarioCtrl {

    private final ComandoSQL comando = new ComandoSQL();

    /**
     * Método genérico que executa uma busca por funcionários e
     * converte o resultado em uma lista de objetos.
     */
    public List<Funcionario> executarBusca(String sql) {
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            rs = comando.executarQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String cargo = rs.getString("cargo");
                    Funcionario f = null;

                    // Instancia o objeto correto com base no cargo
                    switch (cargo) {
                        case "Gerente":
                            f = new Gerente();
                            ((Gerente) f).setDepartamento(rs.getString("departamento"));
                            ((Gerente) f).setQtdEquipesGerenc(rs.getInt("qtdEquipesGerenc"));
                            break;
                        case "Programador":
                            f = new Programador();
                            ((Programador) f).setLinguagemP(rs.getString("linguagemP"));
                            ((Programador) f).setNivelSen(rs.getString("nivelSen"));
                            break;
                        case "AnalistaDeDados":
                            f = new AnalistaDeDados();
                            ((AnalistaDeDados) f).setFerramenta(rs.getString("ferramenta"));
                            ((AnalistaDeDados) f).setQtdRelatorios(rs.getInt("qtdRelatorios"));
                            break;
                    }

                    if (f != null) {
                        // Preenche os dados comuns da classe mãe
                        f.setCpf(rs.getLong("cpf"));
                        f.setNome(rs.getString("nome"));
                        f.setEmail(rs.getString("email"));
                        f.setSalario(rs.getDouble("salario"));
                        funcionarios.add(f);
                    }
                }
            }
        } catch (Exception e) { // Pega SQLException e as exceções de setNivel/setQtdEquipes
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionários: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        return funcionarios;
    }
}