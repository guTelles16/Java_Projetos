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

            System.out.println("Saindo do programa...até logo!");
        } catch (SQLException e) {
            System.err.println("Erro no banco de dados: " + e.getMessage());
        
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        
        } catch (Exception e) {
            System.out.println("Erro fatal de inicialização do sistema: " + e.getMessage());
            System.out.println("Por favor, verifique a conexão com o banco de dados e as configurações!");;
        }
    }
}