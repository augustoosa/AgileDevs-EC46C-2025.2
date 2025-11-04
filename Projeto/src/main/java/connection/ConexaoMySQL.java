// Ajustado para o pacote "connection"
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados MySQL.
 * Fornece métodos para obter a conexão e fechar os recursos de forma segura.
 */
public class ConexaoMySQL {

    // --- Constantes com os dados da conexão ---
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/banco_POO2";
    private static final String USER = "root";
    private static final String PASSWORD = "Saaugusto09!";

    /**
     * Abre a conexão com o banco de dados.
     * @return um objeto do tipo Connection.
     */
    public static Connection getConexao() {
        try {
            // Carrega o driver JDBC
            Class.forName(DRIVER);
            // Retorna a conexão estabelecida
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Lança uma exceção em tempo de execução se a conexão falhar
            throw new RuntimeException("Erro na conexão com o banco de dados: ", e);
        }
    }
    /**
     * Fecha a conexão com o banco de dados.
     * @param con A conexão a ser fechada.
     */
    public static void fecharConexao(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Erro ao fechar conexao:"+e.getMessage(),
                        null,
                        JOptionPane.QUESTION_MESSAGE
                );
            }
        }
    }
    /**
     * Fecha a conexão e o Statement.
     * @param con A conexão a ser fechada.
     * @param stmt O Statement a ser fechado.
     */
    public static void fecharConexao(Connection con, Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Erro ao fechar conexao:"+e.getMessage(),
                        null,
                        JOptionPane.QUESTION_MESSAGE
                );
            }
        }
        fecharConexao(con); // Reutiliza o método anterior
    }
    /**
     * Fecha a conexão, o PreparedStatement e o ResultSet.
     * @param con A conexão a ser fechada.
     * @param pstmt O PreparedStatement a ser fechado.
     * @param rs O ResultSet a ser fechado.
     */
    public static void fecharConexao(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar o ResultSet: " + e.getMessage());
            }
        }
        fecharConexao(con, stmt); // Reutiliza o método anterior
    }
}
