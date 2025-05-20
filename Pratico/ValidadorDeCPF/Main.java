package ValidadorDeCPF;

public class Main {
    public static void main(String[] args) {
        String cpf = "123.456.789-89"; // Exemplo de CPF inválido
        // String cpf = "123.456.789-09"; // Exemplo de CPF válido
        boolean resultado = ValidadorDeCPF.ValidadorCPF.validarCPF(cpf);
        System.out.println("O CPF " + cpf + " é " + (resultado ? "válido" : "inválido"));
    }
}