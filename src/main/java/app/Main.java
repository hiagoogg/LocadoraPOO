package app;

import dao.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static ConexaoDB db = new ConexaoSQL();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
     
        System.out.println("========== SISTEMA LOCADORA ==========\n");
        while (true) {
            System.out.println("1-Clientes | 2-Filmes | 3-Locações | 0-Sair");
            System.out.print("Opção: ");
            switch (ler()) {
                case 1: menuClientes(); break;
                case 2: menuFilmes(); break;
                case 3: menuLocacoes(); break;
                case 0: System.out.println("Saindo..."); return;
                default: System.out.println("Inválido!\n");
            }
        }
    }   

    // ========== CLIENTES ==========
    private static void menuClientes() {
        while (true) {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1-Cadastrar | 2-Listar | 3-Buscar | 4-Atualizar | 5-Excluir | 0-Voltar");
            System.out.print("Opção: ");
            switch (ler()) {
                case 1: cadastrarCliente(); break;
                case 2: listarClientes(); break;
                case 3: buscarCliente(); break;
                case 4: atualizarCliente(); break;
                case 5: excluirCliente(); break;
                case 0: return;
                default: System.out.println("Inválido!");
            }
        }
    }

    private static void cadastrarCliente() {
        sc.nextLine();
        System.out.print("Nome: ");
        String n = sc.nextLine();
        System.out.print("CPF: ");
        String c = sc.nextLine();
        System.out.print("Tel: ");
        String t = sc.nextLine();
        try (Connection conn = db.obterConexao()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Cliente (nome,cpf,telefone) VALUES (?,?,?)");
            ps.setString(1, n); ps.setString(2, c); ps.setString(3, t);
            ps.executeUpdate();
            System.out.println("✓ Cadastrado!\n");
        } catch (Exception e) { System.out.println("✗ Erro: " + e.getMessage() + "\n"); }
    }

    private static void listarClientes() {
        try (Connection conn = db.obterConexao()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Cliente");
            System.out.println();
            while (rs.next()) 
                System.out.println("ID:" + rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4));
            System.out.println();
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    private static void buscarCliente() {
        System.out.print("ID: ");
        int id = ler();
        try (Connection conn = db.obterConexao()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cliente WHERE id_cliente=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) System.out.println("\n" + rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + "\n");
            else System.out.println("✗ Não encontrado\n");
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    private static void atualizarCliente() {
        System.out.print("ID: ");
        int id = ler();
        sc.nextLine();
        System.out.print("Nome: ");
        String n = sc.nextLine();
        System.out.print("CPF: ");
        String c = sc.nextLine();
        System.out.print("Tel: ");
        String t = sc.nextLine();
        try (Connection conn = db.obterConexao()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Cliente SET nome=?,cpf=?,telefone=? WHERE id_cliente=?");
            ps.setString(1, n); ps.setString(2, c); ps.setString(3, t); ps.setInt(4, id);
            if (ps.executeUpdate() > 0) System.out.println("✓ Atualizado!\n");
            else System.out.println("✗ Não encontrado\n");
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    private static void excluirCliente() {
        System.out.print("ID: ");
        int id = ler();
        try (Connection conn = db.obterConexao()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Cliente WHERE id_cliente=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) System.out.println("✓ Excluído!\n");
            else System.out.println("✗ Não encontrado\n");
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    // ========== FILMES ==========
    private static void menuFilmes() {
        while (true) {
            System.out.println("\n--- FILMES ---");
            System.out.println("1-Cadastrar | 2-Listar | 3-Buscar | 4-Atualizar | 5-Excluir | 0-Voltar");
            System.out.print("Opção: ");
            switch (ler()) {
                case 1: cadastrarFilme(); break;
                case 2: listarFilmes(); break;
                case 3: buscarFilme(); break;
                case 4: atualizarFilme(); break;
                case 5: excluirFilme(); break;
                case 0: return;
                default: System.out.println("Inválido!");
            }
        }
    }

    private static void cadastrarFilme() {
        sc.nextLine();
        System.out.print("Nome: ");
        String n = sc.nextLine();
        System.out.print("Qtd: ");
        int q = sc.nextInt();
        System.out.print("ID Cat: ");
        int cat = sc.nextInt();
        System.out.print("ID Loc: ");
        int loc = sc.nextInt();
        try (Connection conn = db.obterConexao()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Filme (nome,quantidade,id_categoria,id_locadora) VALUES (?,?,?,?)");
            ps.setString(1, n); ps.setInt(2, q); ps.setInt(3, cat); ps.setInt(4, loc);
            ps.executeUpdate();
            System.out.println("✓ Cadastrado!\n");
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    private static void listarFilmes() {
        try (Connection conn = db.obterConexao()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Filme");
            System.out.println();
            while (rs.next()) 
                System.out.println("ID:" + rs.getInt(1) + " | " + rs.getString(2) + " | Qtd:" + rs.getInt(3) + " | Cat:" + rs.getInt(4) + " | Loc:" + rs.getInt(5));
            System.out.println();
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    private static void buscarFilme() {
        System.out.print("ID: ");
        int id = ler();
        try (Connection conn = db.obterConexao()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Filme WHERE id_filme=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) System.out.println("\n" + rs.getInt(1) + " | " + rs.getString(2) + " | Qtd:" + rs.getInt(3) + " | Cat:" + rs.getInt(4) + " | Loc:" + rs.getInt(5) + "\n");
            else System.out.println("✗ Não encontrado\n");
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    private static void atualizarFilme() {
        System.out.print("ID: ");
        int id = ler();
        sc.nextLine();
        System.out.print("Nome: ");
        String n = sc.nextLine();
        System.out.print("Qtd: ");
        int q = sc.nextInt();
        System.out.print("ID Cat: ");
        int cat = sc.nextInt();
        System.out.print("ID Loc: ");
        int loc = sc.nextInt();
        try (Connection conn = db.obterConexao()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE Filme SET nome=?,quantidade=?,id_categoria=?,id_locadora=? WHERE id_filme=?");
            ps.setString(1, n); ps.setInt(2, q); ps.setInt(3, cat); ps.setInt(4, loc); ps.setInt(5, id);
            if (ps.executeUpdate() > 0) System.out.println("✓ Atualizado!\n");
            else System.out.println("✗ Não encontrado\n");
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    private static void excluirFilme() {
        System.out.print("ID: ");
        int id = ler();
        try (Connection conn = db.obterConexao()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Filme WHERE id_filme=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) System.out.println("✓ Excluído!\n");
            else System.out.println("✗ Não encontrado\n");
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    // ========== LOCAÇÕES ==========
    private static void menuLocacoes() {
        while (true) {
            System.out.println("\n--- LOCAÇÕES ---");
            System.out.println("1-Registrar | 2-Listar | 0-Voltar");
            System.out.print("Opção: ");
            switch (ler()) {
                case 1: registrarLocacao(); break;
                case 2: listarLocacoes(); break;
                case 0: return;
                default: System.out.println("Inválido!");
            }
        }
    }

    private static void registrarLocacao() {
        System.out.print("ID Cliente: ");
        int cli = ler();
        System.out.print("ID Locadora: ");
        int loc = ler();
        System.out.print("ID Filme: ");
        int film = ler();
        sc.nextLine();
        System.out.print("Data Locação (AAAA-MM-DD): ");
        String dLoc = sc.nextLine();
        System.out.print("Data Devolução (AAAA-MM-DD): ");
        String dDev = sc.nextLine();
        try (Connection conn = db.obterConexao()) {
            new LocacaoDAO(conn).registrarLocacao(cli, loc, film, dLoc, dDev);
            System.out.println("✓ Registrado!\n");
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    private static void listarLocacoes() {
        try (Connection conn = db.obterConexao()) {
            var lista = new LocacaoDAO(conn).listarLocacoes();
            System.out.println();
            if (lista.isEmpty()) System.out.println("Nenhuma locação.");
            else lista.forEach(System.out::println);
            System.out.println();
        } catch (Exception e) { System.out.println("✗ Erro\n"); }
    }

    private static int ler() {
        try { return sc.nextInt(); } 
        catch (Exception e) { sc.nextLine(); return -1; }
    }
}
