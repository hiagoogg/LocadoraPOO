package dao;

import java.sql.Connection;

public interface ConexaoDB{
    
    /*
     * Obtem uma conexão com o banco de dados
    */
    Connection obterConexao() throws Exception;
    
    /*
     *Fecha uma conexão com o banco de dados.
    */
    void fecharConexao(Connection conexao);
    
}