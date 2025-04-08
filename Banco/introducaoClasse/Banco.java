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

    public Conta buscarConta(int numeroConta) {
        return contas.get(numeroConta);
    }

    public boolean transferir(int origem, int destino, double valor) {
        Conta contaOrigem = contas.get(origem);
        Conta contaDestino = contas.get(destino);

        while (true) {
            if (contaOrigem != null && contaDestino != null) {
                try {
                    contaOrigem.sacar(valor); // Tenta sacar o valor da conta de origem
                    contaDestino.depositar(valor); // Deposita o valor na conta de destino
                    contaOrigem.registrarExtrato("Transferência de R$" + valor + " para conta " + destino);
                    contaDestino.registrarExtrato("Recebido R$" + valor + " de conta " + origem);
                    return true; // Retorna verdadeiro se a transferência for bem-sucedida
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}