package crud;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            PessoaDAO dao = new PessoaDAO();

            // Criando tabela se não existir
            dao.criarTabelaPessoa();

            // Adicionando pessoa
            dao.adicionarPessoa(new Pessoa("Maria", 25));

            // Listando pessoas
            for (Pessoa p: dao.listarPessoas()) {
                System.out.println(p.getId() + " - " + p.getNome() + " (" + p.getIdade() + ")");
            }

            // Atualizando
            dao.atualizarPessoa(new Pessoa(1, "Maria Oliveira", 26));

            //Deletando
            dao.deletarPessoa(1);

            // Fechando conexão
            dao.fecharConexao();
        } catch (SQLException e) {
            throw new Exception("Erro no banco de dados: " + e.getMessage());
        
        } catch (IllegalArgumentException e) {
            throw new Exception("Erro de validação: " + e.getMessage());
        
        } catch (Exception e) {
            throw new Exception("Erro: " + e.getMessage());
        }
    }
}