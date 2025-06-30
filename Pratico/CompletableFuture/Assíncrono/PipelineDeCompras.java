import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class PipelineDeCompras {
    public static void main(String[] args) {
        System.out.println("Iniciando a busca de preço dos produtos...");
        long inicio = System.currentTimeMillis();

        // Buscando o preço do primeiro produto
        CompletableFuture<Double> futuroPrecoTeclado = CompletableFuture.supplyAsync(() -> buscarPreco("teclado"));

        // Buscando o preço do segundo produto
        CompletableFuture<Double> futuroPrecoMouse = CompletableFuture.supplyAsync(() -> buscarPreco("mouse"));

        // A thread principal já definiu as "tarefas" paralelas às threads secundárias
        System.out.println("Ordens de busca enviadas. A thread principal está livre para fazer outras coisas.");

        // A thread principal recebe os resultados
        double precoTeclado = futuroPrecoTeclado.join(); // '.join()' faz com que a thread principal aguarde pela conclusão das threads secundárias
        double precoMouse = futuroPrecoMouse.join();

        // Calculo total
        double total = precoTeclado + precoMouse;
        System.out.println("O total da compra é: " + total);

        long fim = System.currentTimeMillis();
        
        // Calcula o tempo de execução da tarefa
        System.out.println("Tempo total da tarefa: " + (fim - inicio) + " milissegundos.");

    }

    /**
     * Simulação de método de consulta ao banco de dados que busca o preço dos produtos. 
     * 
     * @param nomeProduto Nome do produto.
     * @return Um preço fixo para um exemplo dos produtos.
     * @throws InterruptedException Se ocorrer algum erro no tempo de execução do método.
     */
    public static double buscarPreco(String nomeProduto) throws InterruptedException {
        System.out.println("--> [Thread: " + Thread.currentThread().getName() + "] Buscando o preço de: " + nomeProduto);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--> [Thread: " + Thread.currentThread().getName() + "] Preço do " + nomeProduto + " encontrado.");
        return nomeProduto.equals("teclado") ? 250.0 : 90.0;
    }
}
