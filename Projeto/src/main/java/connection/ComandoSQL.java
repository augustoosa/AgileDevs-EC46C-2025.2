package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Classe utilitária para executar comandos SQL.
 * As mensagens de sucesso e erro são exibidas em pop-ups (JOptionPane).
 */
public class ComandoSQL {

    /**
     * MÉTODO 1: Para INSERT, UPDATE e DELETE.
     * Executa comandos que modificam dados no banco.
     * @param sql O comando SQL a ser executado.
     */
    public boolean executarUpdate(String sql) { 
        Connection con = null;
        Statement st = null;
        boolean sucesso = false; 

        try {
            // Abre a conexão
            con = ConexaoMySQL.getConexao();

            // Cria o objeto Statement
            st = con.createStatement();

            // Executa o comando de atualização
            st.executeUpdate(sql);

            // Se chegou aqui, deu certo
            sucesso = true; // <-- Marca como sucesso
            // NENHUMA MENSAGEM DE SUCESSO AQUI

        } catch (SQLException e) {
            // MENSAGEM DE ERRO GENÉRICA (inclui CPF duplicado e outros erros)
            JOptionPane.showMessageDialog(
                null,
                "ERRO: Este funcionario ja esta cadastrado no sistema\n", // Mostra a mensagem do MySQL
                "Erro de SQL",
                JOptionPane.ERROR_MESSAGE
            );
            sucesso = false; // <-- Marca como falha
        } finally {
            // Garante que a conexão e o statement sejam fechados
            ConexaoMySQL.fecharConexao(con, st);
        }
        
        return sucesso; // <-- Retorna true ou false
    }

    /**
     * MÉTODO 2: Para SELECT.
     * Executa comandos que buscam dados no banco.
     * @param sql O comando SELECT a ser executado.
     * @return um objeto ResultSet contendo o resultado da busca.
     * @throws SQLException se ocorrer um erro ao acessar o banco de dados.
     */
    public ResultSet executarQuery(String sql) throws SQLException {
        Connection con = null;
        Statement st = null;

        try {
            con = ConexaoMySQL.getConexao();
            st = con.createStatement();
            return st.executeQuery(sql);
            
        } catch (SQLException e) {
            // MENSAGEM DE ERRO COM JOPTIONPANE
             JOptionPane.showMessageDialog(
                null, 
                "ERRO ao realizar a consulta:\n" + e.getMessage(), 
                "Erro de SQL", 
                JOptionPane.ERROR_MESSAGE
            );
            // Fecha os recursos se der erro aqui
            ConexaoMySQL.fecharConexao(con, st);
            return null;
        }
    }
}