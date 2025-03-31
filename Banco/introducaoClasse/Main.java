import javax.swing.JOptionPane;

public class Main {
    public static void main (String[] args) {
        Banco banco = null; // Inicializa o objeto banco, mas somente se os inputs forem bem-sucedidos.
        
        // Loop para garantir o input correto do usuário.
        while (banco == null) {
            try{
                String nome = obterEntrada("Digite o nome do banco:");
                String nomeUsuario = obterEntrada("Digite o seu nome:");

                double saldoInicial = obterValorNumerico("Digite o saldo inicial:");
                if (saldoInicial == -1) return; // Se o usuário cancelar a entrada, encerra o programama.

                banco = new Banco(nome, nomeUsuario, saldoInicial);

                // Loop para exibir o menu de opções.
                while (true) {
                    int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Banco", 
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                        new Object[]{"Sacar", "Depositar", "Exibir Saldo", "Sair"}, "Sair");

                    if (opcao == 0) { // Sacar
                        double valor = obterValorNumerico("Digite o valor do saque:");
                        if (valor == -1) continue; // Se o usuário cancelar a entrada, volta ao menu.
                        banco.sacar(valor);
                    } else if (opcao == 1) { // Depositar
                        double valor = obterValorNumerico("Digite o valor do depósito:");
                        if (valor == -1) continue;
                        banco.depositar(valor);
                    } else if (opcao == 2) { // Exibir Saldo
                        banco.exibirSaldo();
                    } else { // Sair
                        JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                        return; // Encerra o programa.
                    }
                }

             // Captura as exceções e exibe uma mensagem de erro. 
            } catch (NumberFormatException e) { 
                JOptionPane.showMessageDialog(null, "Erro na entrada de dados: " + e.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Métodos auxiliares para obter entrada de dados do usuário.
    private static String obterEntrada(String mensagem) {
        String input;
        // Executa o loop enquanto a entrada for nula ou vazia. Se não for, retorna a entrada.
        while (true) {
            try {
                input = JOptionPane.showInputDialog(mensagem);
                if (input == null) { // Se o uauário cancelar a entrada, exibe uma mensagem e encerra o programa.
                    JOptionPane.showMessageDialog(null, "Entrada cancelada!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                    System.exit(0); // Encerra o programa
                } else if (input.trim().isEmpty()) { // Verifica se a entrada é vazia ou só tem espaços.
                    JOptionPane.showMessageDialog(null, "O campo não pode ser vazio!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    return input;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Preencha o campo corretamente!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static double obterValorNumerico(String mensagem) {
        while (true) { // Loop para garantir que o usuário digite um valor numérico válido.
            try {
                String input = JOptionPane.showInputDialog(mensagem);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Entrada cancelada!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                    return -1; // Retorna um valor inválido para indicar o cancelamento.
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor númerico válido!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}