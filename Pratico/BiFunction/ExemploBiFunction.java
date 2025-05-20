package BiFunction;

import javax.swing.JOptionPane;
import java.util.function.BiFunction;

public class ExemploBiFunction {
    public static void main(String[] args) {
        // BiFunction que soma dois números
        BiFunction<Double, Double, Double> soma = (numero1, numero2) -> numero1 + numero2;

        /*
         * BiFunction: Interface funcional que recebe dois argumentos do mesmo tipo e retorna um resultado
         * Lambda: (numero1, numero2) -> numero1 + numero2
         * A lambida simplifica a implementação de operações em interfaces funcionais
        */
        
        double numero1 = obterNumero("Digite o primeiro número:");
        double numero2 = obterNumero("Digite o segundo número:");

        if (Double.isNaN(numero1) || Double.isNaN(numero2)) {
            return; // Se o usuário cancelar a entrada, encerra o programa
        }

        double resultado = soma.apply(numero1, numero2);

        JOptionPane.showMessageDialog(null, "A soma de " + numero1 + " e " + numero2 + " é: " + resultado + ".",
        "Resultado", JOptionPane.INFORMATION_MESSAGE);

        int opcao = JOptionPane.showConfirmDialog(null, "Deseja continuar?", 
        "Confirmação", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            main(args); // Chama o método main novamente para reiniciar o programa
        } else {
            JOptionPane.showMessageDialog(null, "Saindo do programa...");
        }
    }

    // Método auxiliar para obter um número do usuário
    private static double obterNumero(String mensagem) {
        while (true) { // Loop para garantir que o usuário digite um valor númerico válido
            try {
                String input = JOptionPane.showInputDialog(mensagem);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Entrada cancelada!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                    return Double.NaN; // Retorna um valor inválido para indicar o cancelamento
                }

                if (input.trim().isEmpty()) { // Verifica se a entrada é vazia ou só tem espaços
                    JOptionPane.showMessageDialog(null, "O campo não pode ser vazio!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                }

                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor númerico válido!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}