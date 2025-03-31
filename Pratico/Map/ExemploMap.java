package Pratico.Map;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

public class ExemploMap {
    public static void main(String[] args) {
        Map<String, String> capitais = new HashMap<>();
        
        /*
         * Map: Estrutura de dados que permite associar chave a valor
         * HashMap: cada chave é única e está associado a um valor correspondente
        */

        // Adicionando pares chave->valor no mapa
        capitais.put("Brasil", "Brasília");
        capitais.put("Argentina", "Buenos Aires");
        capitais.put("Chile", "Santiago");

        String pais = obterEntrada("Digite o nome de um país:");

        // Verifica se o país está no mapa
        String capital = capitais.get(pais);

        if (capital != null) {
            JOptionPane.showMessageDialog(null, "A capital de " + pais + " é " + capital + ".");
        } else {
            JOptionPane.showMessageDialog(null, "País não encontrado no banco de dados!", 
            "Erro", JOptionPane.ERROR_MESSAGE);
        }

        int opcao = JOptionPane.showConfirmDialog(null, "Deseja continuar?", 
        "Confirmação", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            main(args); // Chama o método main novamente para reiniciar o programa
        } else {
            JOptionPane.showMessageDialog(null, "Saindo do programa...");
        }
    }

    // Método auxiliar para obter uma entrada do usuário
    private static String obterEntrada(String mensagem) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(mensagem);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Entrada cancelada!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                    System.exit(0); // Encerra o programa
                }

                if (input.trim().isEmpty()) { // Verifica se a entrada é vazia ou se tem espaços
                    JOptionPane.showMessageDialog(null, "O campo não pode ser vazio!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                    continue; // Retorna para a tela
                }

                return input; // Retorna a entrada válida
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Preencha o campo corretamente!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}