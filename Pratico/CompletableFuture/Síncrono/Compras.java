import java.util.concurrent.TimeUnit;

public class Compras {
    public static void main(String[] args) {
        System.out.println("Iniciando a busca de preço dos produtos...");
        long inicio = System.currentTimeMillis();
        
        // Buscando o preço do primeiro produto
        double precoTeclado = buscarPreco("teclado");
        System.out.println("Preço do teclado é: " + precoTeclado);

        // Buscando o preço do segundo produto
        double precoMouse = buscarPreco("mouse");
        System.out.println("Preço do mouse é: " + precoMouse);

        // Calculando total da compra
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
        System.out.println("--> Buscando preço de: " + nomeProduto);
        try {
            // Simula o tempo de execução do método para 2 segundos
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--> Preço do " + produto + " encontrado.");
        return nomeProduto.equals("teclado") ? 250.0 : 90.0;
    }
}
