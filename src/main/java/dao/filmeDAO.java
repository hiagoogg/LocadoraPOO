import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    private Connection conn;

    public FilmeDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarFilme(String nome, int quantidade, int idCategoria, int idLocadora) throws SQLException {
        String sql = "INSERT INTO Filme (nome, quantidade, id_categoria, id_locadora) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, quantidade);
            stmt.setInt(3, idCategoria);
            stmt.setInt(4, idLocadora);
            stmt.executeUpdate();
        }
    }

    public List<String> listarFilmes() throws SQLException {
        List<String> filmes = new ArrayList<>();
        String sql = "SELECT f.id_filme, f.nome, f.quantidade, c.nome AS categoria FROM Filme f " +
                     "JOIN Categoria c ON f.id_categoria = c.id_categoria";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                filmes.add(rs.getInt("id_filme") + " - " + rs.getString("nome") + 
                           " | Categoria: " + rs.getString("categoria") + 
                           " | Qtde: " + rs.getInt("quantidade"));
            }
        }
        return filmes;
    }
}
