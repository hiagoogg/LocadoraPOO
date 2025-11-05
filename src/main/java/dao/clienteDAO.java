import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarCliente(String nome, String cpf, String telefone) throws SQLException {
        String sql = "INSERT INTO Cliente (nome, cpf, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.executeUpdate();
        }
    }

    public List<String> listarClientes() throws SQLException {
        List<String> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(rs.getInt("id_cliente") + " - " + rs.getString("nome") + " (" + rs.getString("cpf") + ")");
            }
        }
        return clientes;
    }
}
