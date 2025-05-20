package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private Connection conexao;

    public PessoaDAO() throws Exception {
        Connection conexao = ConexaoBanco.conectar();
        this.conexao = conexao;
    }

    // Criando tabela se não existir
    public void criarTabelaPessoa() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS pessoas (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," + 
            "nome VARCHAR (50) NOT NULL," +
            "idade INT NOT NULL)";
        conexao.createStatement().execute(sql);
        System.out.println("Tabela criada com sucesso!");
    } 

    // CREATE
    public void adicionarPessoa(Pessoa pessoa) throws Exception {
        String sql = "INSERT INTO pessoas (nome, idade) VALUES (?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, pessoa.getNome());
        stmt.setInt(2, pessoa.getIdade());
        stmt.executeUpdate();
        System.out.println("Dados inseridos com sucesso!");
    } 

    // READ
    public List<Pessoa> listarPessoas() throws Exception {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoas";
        ResultSet rs = conexao.createStatement().executeQuery(sql);
        while (rs.next()) {
            Pessoa p = new Pessoa(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("idade")
            );
            lista.add(p);
        }
        return lista;
    }

    // UPDATE
    public void atualizarPessoa(Pessoa pessoa) throws Exception {
        String sql = "UPDATE pessoas SET nome = ?, idade = ? WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, pessoa.getNome());
        stmt.setInt(2, pessoa.getIdade());
        stmt.setInt(3, pessoa.getId());
        stmt.executeUpdate();
        System.out.println("Dados atualizados com sucesso!");
    }

    // DELETE
    public void deletarPessoa(int id) throws Exception {
        String sql = "DELETE FROM pessoas WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("Registro deletado com sucesso!");
    }

    // Fechando conexão
    public void fecharConexao() throws Exception {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
            System.out.println("Conexão fechada com sucesso!");
        } else {
            System.out.println("Conexão já está fechada!");
        }
    }
}
