import javax.swing.JOptionPane;
import java.util.HashMap;

public class Banco {
    private HashMap<Integer, Conta> contas;
    
    /*
     * HashMap: cada chave é única e está associado a um valor correspondente
    */

    public Banco() {
        this.contas = new HashMap<>();
    }

    public void adicionarConta(Conta conta) {
        contas.put(conta.getNumeroConta(), conta); // Adiciona a conta ao HashMap usando o número da conta como chave
    }

    public Conta buscarConta(Number numeroConta) {
        return contas.get(numeroConta);
    }

    public boolean transferir(Number origem, Number destino, Number valor) {
        int origemInt = origem.intValue();
        int destinoInt = destino.intValue();
        
        double valorDouble = valor.doubleValue();
        
        Conta contaOrigem = contas.get(origemInt);
        Conta contaDestino = contas.get(destinoInt);

        while (true) {
            if (contaOrigem != null && contaDestino != null) {
                try {
                    contaOrigem.sacar(valorDouble); // Tenta sacar o valor da conta de origem
                    contaDestino.depositar(valorDouble); // Deposita o valor na conta de destino
                    contaOrigem.registrarExtrato("Transferência de R$" + valorDouble + " para conta " + destinoInt);
                    contaDestino.registrarExtrato("Recebido R$" + valorDouble + " de conta " + origemInt);
                    return true; // Retorna verdadeiro se a transferência for bem-sucedida

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao transferir: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}