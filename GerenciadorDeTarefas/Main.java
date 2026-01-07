import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1 - Adicionar tarefa");
            System.out.println("2 - Concluir tarefa");
            System.out.println("3 - Listar tarefa");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    gerenciador.adicionarTarefa(descricao);
                    break;
                case 2:
                    System.out.print("Índice da tarefa: ");
                    int indice = scanner.nextInt();
                    gerenciador.concluirTarefa(indice);
                    break;
                case 3:
                    gerenciador.listarTarefas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}