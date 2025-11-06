package dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoSQL implements ConexaoDB {
    // trocar a senha de acordo com o SQL
    private static final String URL = "jdbc:mysql://localhost:3307/locadoradb?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "catolica";

    @Override
    public Connection obterConexao() throws Exception {
        try {
            // Carrega o driver e abre a conexão
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void fecharConexao(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
