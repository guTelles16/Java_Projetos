import javax.swing.JOptionPane;

public class Main {
    public static void main (String[] args) {
        Banco banco = null; // Inicializa o objeto banco, mas somente se os inputs forem bem-sucedidos.
        
        // Loop para garantir o input correto do usuário.
        while (true) {
            try {
                int opcao = JOptionPane.showOptionDialog(null, "Que operação deseja realizar?", "Banco", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                new Object[]{"Criar Conta", "Depositar", "Sacar", "Transferir", "Exibir Saldo", "Ver Extrato", "Sair"}, "Sair");

                if (opcao == 0) { // Criar Conta}
                    String nome = obterEntrada("Nome:");
                    String cpf = obterEntrada("CPF:");
            
                    Number numeroConta = obterValorNumerico("Número da conta:");
                
                    if (numeroConta == null) {
                        OperationCanceledException e = new OperationCanceledException("Entrada cancelada!");
                        JOptionPane.showMessageDialog(null, "Aviso: " + e.getMessage(),
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                        continue; // Se o usuário cancelar a entrada, volta ao menu
                    } else if (numeroConta instanceof Double){
                        JOptionPane.showMessageDialog(null, "Número da conta inválido!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                        continue;
                    } else {
                        int inteiro = (Integer) numeroConta;
                    }

                    banco.adicionarConta(new Conta(nome, cpf, numeroConta));
                    JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                } 
                else if (opcao == 1) { // Depositar
                    Number contaDeposito = obterValorNumerico("Numero da conta de depósito:");
                    
                    if (contaDeposito == null) {
                        OperationCanceledException e = new OperationCanceledException("Entrada cancelada!");
                        JOptionPane.showMessageDialog(null, "Aviso: " + e.getMessage(),
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                        continue;
                    } else if (contaDeposito instanceof Double) {
                        JOptionPane.showMessageDialog(null, "Número da conta inválido!", 
                            "Erro", JOptionPane.ERROR_MESSAGE);
                        continue;
                    } else {
                        int inteiro = (Integer) contaDeposito;
                    }
                
                    Number valor = obterValorNumerico("Valor do depósito:");
                    
                    if (valor == null) {
                        OperationCanceledException e = new OperationCanceledException("Entrada cancelada!");
                        JOptionPane.showMessageDialog(null, "Aviso: " + e.getMessage(),
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                        continue;
                    } else if (valor instanceof Integer) {
                        int inteiro = (Integer) valor;
                        continue;
                    } else {
                        double decimal = (Double) valor;
                    }

                    Conta conta = depositar(valor);
                } else if (opcao == 2) { // Sacar
                    Number contaSaque = obterValorNumerico("Número da conta de depósito:");

                    if (contaSaque == null) {
                        OperationCanceledException e = new OperationCanceledException("Entrada cancelada!");
                        JOptionPane.showMessageDialog(null, "Aviso: " + e.getMessage(),
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                            continue;
                    } else if (contaSaque instanceof Double) {
                        JOptionPane.showMessageDialog(null, "Número da conta inválido!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int inteiro = (Integer) contaSaque;
                    }

                    Number valor = obterValorNumerico("Digite o valor do saque:");
                    
                    if (valor == null) {
                        OperationCanceledException e = new OperationCanceledException("Entrada cancelada!");
                        JOptionPane.showMessageDialog(null, "Aviso: " + e.getMessage(),
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                        continue;
                    } else if (valor instanceof Integer) {
                        int inteiro = (Integer) valor;
                    } else {
                        double decimal = (Double) valor;
                    } 

                    Conta conta.sacar(valor);
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

    // Métodos auxiliares para obter entrada de dados do usuário
    private static String obterEntrada(String mensagem) {
        String input;
        // Executa o loop enquanto a entrada for nula ou vazia. Se não for, retorna a entrada
        while (true) {
            try {
                input = JOptionPane.showInputDialog(mensagem);
                if (input == null) { // Se o usuário cancelar a entrada, exibe uma mensagem e encerra o programa
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
        while (true) { // Loop para garantir que o usuário digite um valor numérico válido
            try {
                String input = JOptionPane.showInputDialog(mensagem);
                if (input.trim().isEmpty()) {
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

    // Exceção personalizada para entrada cancelada
    public class OperationCanceledException extends Exception {
        public OperationCanceledException(String mensagem) {
            super(mensagem);
        }
    }
}
