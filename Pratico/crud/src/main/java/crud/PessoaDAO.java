package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    // Criando tabela se não existir
    public void criarTabelaPessoa() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS pessoas (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," + 
            "nome VARCHAR (50) NOT NULL," +
            "idade INT NOT NULL)";
        
        try (Connection conexao = ConexaoBanco.conectar()) {
            conexao.createStatement().execute(sql);
            System.out.println("Tabela criada com sucesso!");
        } catch (Exception e) {
            throw new Exception("Erro ao criar tabela: " + e.getMessage());
        }
    } 

    // CREATE
    public void adicionarPessoa(Pessoa pessoa) throws Exception {
        String sql = "INSERT INTO pessoas (nome, idade) VALUES (?, ?)";
        
        try (Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getIdade());
            
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
        } catch (Exception e) {
            throw new Exception("Erro ao salvar as informações: " + e.getMessage());
        }
    } 

    // READ
    public List<Pessoa> listarPessoas() throws Exception {
        String sql = "SELECT * FROM pessoas";
        
        List<Pessoa> lista = new ArrayList<>();

        try (Connection conexao = ConexaoBanco.conectar();
            ResultSet rs = conexao.createStatement().executeQuery(sql)) {
        
            while (rs.next()) {
                Pessoa p = new Pessoa(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idade")
                );
            lista.add(p);
            }
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro ao listar todas as pessoas: " + e.getMessage());
        } 
    }

    // UPDATE
    public void atualizarPessoa(Pessoa pessoa) throws Exception {
        String sql = "UPDATE pessoas SET nome = ?, idade = ? WHERE id = ?";

        try (Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getIdade());
            stmt.setInt(3, pessoa.getId());

            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso!");
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar as informações: " + e.getMessage());
        }
    }

    // DELETE
    public void deletarPessoa(int id) throws Exception {
        String sql = "DELETE FROM pessoas WHERE id = ?";

        try (Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Registro deletado com sucesso!");
        } catch (Exception e) {
            throw new Exception("Erro ao deletar pessoa: " + e.getMessage());
        } 
    }
}
