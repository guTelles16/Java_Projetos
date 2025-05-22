package Banco.introducaoClasse;

import javax.swing.JOptionPane;

public class Main {
    public static void main (String[] args) {
        Banco banco = new Banco();
        
        // Loop para garantir o input correto do usuário.
        while (true) {
            try {
                int opcao = JOptionPane.showOptionDialog(null, "Que operação deseja realizar?", "Banco", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                    new Object[]{"Criar Conta", "Depositar", "Sacar", "Transferir", "Exibir Saldo", "Ver Extrato", "Sair"}, "Sair");

                if (opcao == 0) { // Criar Conta
                    String nome = getEntrada("Nome:");
                    String cpf = getEntrada("CPF:");
                    int numeroConta = (int) getNumero("Número da conta:");
                    
                    banco.adicionarConta(new Conta(nome, cpf, numeroConta));
                    JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                } 
                else if (opcao == 1) { // Depositar
                    int contaDeposito = (int) getNumero("Numero da conta de depósito:");
                    double valor = (double) getNumero("Valor do depósito:");

                    Conta conta = banco.buscarConta(contaDeposito);
                    if (conta != null) {
                        conta.depositar(valor);
                        JOptionPane.showMessageDialog(null, "Deposito realizado!");
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                    }
                } 
                else if (opcao == 2) { // Sacar
                    int contaSaque = (int) getNumero("Número da conta de depósito:");
                    double valor = (double) getNumero("Valor do saque:");

                    Conta conta = banco.buscarConta(contaSaque);
                    if (conta != null) {
                        conta.sacar(valor);
                        JOptionPane.showMessageDialog(null, "Saque realizado!");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                    }
                }
                else if (opcao == 3) { // Transferir
                    int contaOrigem = (int) getNumero("Número da conta:");
                    int contaDestino = (int) getNumero("Número da conta de destino:");
                    double valor = (double) getNumero("Valor da transferência:");

                    Conta conta = banco.buscarConta(contaOrigem);
                    Conta contaDestinoObj = banco.buscarConta(contaDestino);
                    if (conta != null && contaDestinoObj != null) {
                        banco.transferir(contaOrigem, contaDestino, valor);
                        JOptionPane.showMessageDialog(null, "Transferência realizada!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta de origem ou destino não encontrada!");                  }
                }
                else if (opcao == 4) { // Exibir Saldo
                    int contaSaldo = (int) getNumero("Número da conta:");
                    Conta conta = banco.buscarConta(contaSaldo);
                    if (conta != null) {
                        JOptionPane.showMessageDialog(null, "Saldo: R$" + conta.getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                    }
                } 
                else if (opcao == 5) { // Ver Extrato
                    int contaExtrato = (int) getNumero("Número da conta:");
                    Conta conta = banco.buscarConta(contaExtrato);
                    if (conta != null) {
                        String extrato = conta.verExtrato();
                        JOptionPane.showMessageDialog(null, extrato);
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                    }
                } 
                else if (opcao == 6 || opcao == -1) { // Sair
                    break;
                }
                    int dadosConta = (int) getNumero("Número da conta:");

                    Conta conta = banco.buscarConta(dadosConta);
                    if (conta != null) {
                        conta.exibirConta();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                    }

             // Captura as exceções e exibe uma mensagem de erro. 
            } catch (InputCanceledException e) {
                JOptionPane.showMessageDialog(null, "Operação cancelada!",
                    "Cancelado", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro ao transferir: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Métodos auxiliares para obter entrada de dados do usuário
    private static String getEntrada(String mensagem) throws InputCanceledException{
        while (true) {
            String input = JOptionPane.showInputDialog(mensagem);
            if (input == null) throw new InputCanceledException("Entrada cancelada!"); 
            if (input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo não pode ser vazio!",
                     "Erro", JOptionPane.ERROR_MESSAGE);
                continue; // Continua o loop para solicitar novamente a entrada
            }
            return input;
        }
    }

    private static Number getNumero(String mensagem) throws InputCanceledException {
        while (true) { // Loop para garantir que o usuário digite um valor numérico válido
            String input = JOptionPane.showInputDialog(mensagem);
            if (input == null) throw new InputCanceledException("Entrada cancelada!");
            if (input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "O campo não pode ser vazio!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            try {
                double numero = Double.parseDouble(input);
                return numero == (int) numero ? (int ) numero : numero;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor númerico válido!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro:", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static class InputCanceledException extends Exception {
        public InputCanceledException(String message) {
            super(message);
        }
    }
}
