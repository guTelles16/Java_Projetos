import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.SQLException;

public class ConexaoComBanco {
    public static void main(String[] args) {
        // Variáveis de conexão
        String url = "jdbc:mysql://localhost:3306/DB_Conexao";
        String usuario = "root";
        // String senha; // Senha do banco de dados, se necessário

        // Tentativa de conexão
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conectado com sucesso ao banco de dados!");

            // Criar tabela se não existir
            String sqlCriarTabela = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(50) NOT NULL," +
                "email VARCHAR(50) NOT NULL)";
            conexao.createStatement().execute(sqlCriarTabela);
            System.out.println("Tabela criada com sucesso!");

            // Inserir dados na tabela
            String sqlInserir = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
            PreparedStatement stmtInserir = conexao.PreparedStatement(sqlInserir);
            stmtInserir.setString(1, "Bruno");
            stmtInserir.setString(2, "teste@email.com");
            stmtInserir.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

            // Consultar dados na tabela
            String sqlSelect = "SELECT * FROM usuarios";
            ResultSet resultados = conexao.createStatement().executeQuery(sqlInserir);

            // Exibir os resultados
            while (resultados.next()) {
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                String email = resultados.getString("email");

                System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
            }

        // Captura de exceções de SQL
        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao conectar ou executar comandos: " + e.getMessage());
        }
    }
}