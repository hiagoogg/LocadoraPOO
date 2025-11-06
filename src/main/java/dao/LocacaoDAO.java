package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO {
    private Connection conn;

    public LocacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public void registrarLocacao(int idCliente, int idLocadora, int idFilme, String dataLocacao, String dataDevolucao) throws SQLException {
        String sql = "INSERT INTO Locacao (data_locacao, data_devolucao, id_cliente, id_locadora, id_filme) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dataLocacao);
            stmt.setString(2, dataDevolucao);
            stmt.setInt(3, idCliente);
            stmt.setInt(4, idLocadora);
            stmt.setInt(5, idFilme);
            stmt.executeUpdate();
        }
    }

    public List<String> listarLocacoes() throws SQLException {
        List<String> locacoes = new ArrayList<>();
        String sql = "SELECT l.id_locacao, c.nome AS cliente, f.nome AS filme, l.data_locacao, l.data_devolucao " +
                     "FROM Locacao l " +
                     "JOIN Cliente c ON l.id_cliente = c.id_cliente " +
                     "JOIN Filme f ON l.id_filme = f.id_filme";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                locacoes.add("Locação " + rs.getInt("id_locacao") + ": " +
                             rs.getString("cliente") + " alugou '" + rs.getString("filme") +
                             "' de " + rs.getString("data_locacao") + " até " + rs.getString("data_devolucao"));
            }
        }
        return locacoes;
    }
}
