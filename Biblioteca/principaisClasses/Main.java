package Biblioteca.principaisClasses;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Livro livro = null; // Inicializa o objeto Livro, mas não até o input ser bem-sucedido.
              
        // Loop para garantir o input correto do usuário.
        while (livro == null) {
            try {
                String titulo = obterEntrada("Digite o nome do livro:");
                String autor = obterEntrada("Digite o nome do autor:");

                int anoPublicacao = obterAnoPublicacao("Digite o ano de publicação:");

                String genero = obterEntrada("Digite o gênero do livro:");

                int numeroPaginas = obterValorNumerico("Digite o número de páginas:");

                // Se o usuário cancelar a entrada, encerra o programa.
                if (anoPublicacao == -1 || numeroPaginas == -1) return;

                livro = new Livro(titulo, autor, anoPublicacao, genero, numeroPaginas);
                
                // Loop para exibir o menu de opções.
                while (true) {
                    int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Biblioteca",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    new Object[] {"Emprestar", "Devolver", "Exibir informações do Livro", "Sair"}, "Sair");

                    if (opcao == 0) { // Emprestar
                        livro.emprestar();
                    } else if (opcao == 1) { // Devolver 
                        livro.devolver();
                    } else if (opcao == 2) { // Exibir informações do livro
                        livro.exibirInformacoes();
                    } else { // Sair
                        JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                        return; // Encerra o programa.
                    }
                }

            // Captura as exceções exibe uma mensagem de erro.
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro na entrada de dados: " + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } 
        }
    }

    //Métodos auxiliares para obter entrada do usuário.
    private static String obterEntrada(String mensagem) {
        String input;
        // Executa o loop enquanto a entrada for nula ou vazia. Se não for, retorna a entrada.
        while (true) {
            try {
                input = JOptionPane.showInputDialog(mensagem);
                if (input == null) { // Se o usuário cancelar a entrada, exibe uma mensagem e encerra o programa.
                    JOptionPane.showMessageDialog(null, "Entrada cancelada!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                    System.exit(0); // Encerra o programa.
                } 
                
                if (input.trim().isEmpty()) { // Verifica se a entrada é vazia ou só tem espaços.
                    JOptionPane.showMessageDialog(null, "O campo não pode ser vazio!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                } 

                return input; // Retorna a entrada válida.
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Preencha o campo corretamente!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static int obterValorNumerico(String mensagem) {
        while (true) { // Loop para garantir que o usuário digite um valor numérico válido.
            try {
                String input = JOptionPane.showInputDialog(mensagem);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Entrada cancelada!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                    return -1; // Retorna um valor inválido para indicar o cancelamento.
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor numérico válido!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static int obterAnoPublicacao(String mensagem) {
        int anoAtual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

        while (true) {
            try{
                String input = JOptionPane.showInputDialog(mensagem);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Entrada cancelada!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                    return -1;
                }

                int anoPublicacao = Integer.parseInt(input);

                // Verifica se o ano de publicação está dentro do intervalo permitido.
                if (anoPublicacao < 1500 || anoPublicacao > anoAtual) {
                    JOptionPane.showMessageDialog(null, "O ano de publicação do livro deve ser entre 1500 e " + 
                    anoAtual + "!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    return anoPublicacao;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor numérico válido!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}