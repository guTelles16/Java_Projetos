package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    
    // Metodo reutilizável para obter conexão ao banco com validações
    public static Connection conectar() throws Exception {
        String url = System.getenv("MYSQL_URL");
        String user = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASSWORD");

        // Verificação das variáveis de ambiente
        if (url == null || url.trim().isEmpty()) {
            throw new Exception("A variável de ambiente MYSQL_URL não foi definida!");
        }

        if (user == null || user.trim().isEmpty()) {
            throw new Exception("A variável de ambiente MYSQL_USER não foi definida!");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new Exception("A variável de ambiente MYSQL_PASSWORD não foi definida!");
        }

        // Tentativa de conexão com o banco de dados
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o driver JDBC  
            Connection conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso!");
            return conexao;
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver JDBC não encontrado: " + e.getMessage());
            
        } catch (SQLException e) {
            throw new Exception("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}