package dbconexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;;

public class ConexaoComBanco {
    public static void main(String[] args) throws Exception {
        // Variáveis de conexão
        String url = System.getenv("DB_URL");
        String usuario = System.getenv("DB_USER");
        String senha = System.getenv("DB_PASSWORD");

        // Verificação do driver JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver JDBC não encontrado: " + e.getMessage());
        }
        
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
            PreparedStatement stmtInserir = conexao.prepareStatement(sqlInserir);
            stmtInserir.setString(1, "Bruno");
            stmtInserir.setString(2, "teste@email.com");
            stmtInserir.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

            // Consultar dados na tabela
            String sqlSelect = "SELECT * FROM usuarios";
            ResultSet resultados = conexao.createStatement().executeQuery(sqlSelect);

            // Exibir os resultados
            while (resultados.next()) {
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                String email = resultados.getString("email");

                System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
            }

        // Captura de exceções de SQL
        } catch (SQLException e) {
            throw new Exception("Erro no banco de dados: " + e.getMessage());
        
        } catch (Exception e) {
            throw new Exception("Erro: " + e.getMessage());
        }
    }
}