import javax.swing.JOptionPane;

public class Main {
    public static void main (String[] args) {
        Conta conta = null; // Inicializa o objeto banco, mas somente se os inputs forem bem-sucedidos.
        
        // Loop para garantir o input correto do usuário.
        while (conta == null) {
            try{
                String nome = obterEntrada("Digite o nome do banco:");
                String cpf = obterEntrada("Digite o seu nome:");

                Number numeroConta = obterValorNumerico("Digite o número da conta:");
                if (numeroConta == null) OperationCanceledException(); // Se o usuário cancelar a entrada, encerra o programama.

                conta = new Conta(nome, cpf, numeroConta);

                // Loop para exibir o menu de opções.
                while (true) {
                    int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Banco", 
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                        new Object[]{"Sacar", "Depositar", "Exibir Saldo", "Sair"}, "Sair");

                    if (opcao == 0) { // Sacar
                        Number valor = obterValorNumerico("Digite o valor do saque:");
                        if (valor == null) continue; // Se o usuário cancelar a entrada, volta ao menu.
                        conta.sacar(valor);
                    } else if (opcao == 1) { // Depositar
                        Number valor = obterValorNumerico("Digite o valor do depósito:");
                        if (valor == null) continue;
                        conta.depositar(valor);
                    } else if (opcao == 2) { // Exibir Saldo
                        conta.exibirSaldo();
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
                } else if (input.trim().isEmpty()) { // Verifica se a entrada é vazia ou só tem espaços
                    JOptionPane.showMessageDialog(null, "O campo não pode ser vazio!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                    continue; // Continua o loop para solicitar novamente a entrada  
                } else {
                    return input;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Preencha o campo corretamente!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static Number obterValorNumerico(String mensagem) {
        while (true) { // Loop para garantir que o usuário digite um valor numérico válido.
            try {
                String input = JOptionPane.showInputDialog(mensagem);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Entrada cancelada!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                    throw new OperationCanceledException(); // Retorna um valor inválido para indicar o cancelamento.
                } else if (input.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O campo não pode ser vazio!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                } else {
                    double numero = Double.parseDouble(input);
                
                    //Verifica se o número tem parte decimal
                    if (numero == (int) numero) {
                        return (int) numero; // Retorna como inteiro
                    } else {
                        return numero; // Retorna como double
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor númerico válido!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static class OperationCanceledException extends Exception {
        public OperationCanceledException() {
            super("Operação cancelada pelo usuário!");
            System.exit(0); 
        }
    }
}