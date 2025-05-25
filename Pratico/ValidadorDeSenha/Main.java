package ValidadorDeSenha;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Criando senha
        char[] senha = {'a', 'b', 'c', 'd', '1', '2', '3', '4'};
        // char [] senha = {'A', 'B', 'C, '1', '2}; // Senha inválida
        TestadorSenha testador = new TestadorSenha(senha);

        // Testando autenticação
        char[] senhaTeste = {'a', 'b', 'c', 'd', '1', '2', '3', '4'};
        // char [] senhaTeste = {'A', 'B', 'C, '1', '2};
        if (testador.autenticar(senhaTeste)) return;

        // Testando redefinição de senha
        char[] novaSenha = {'W', 'X', 'Y', 'Z', '5', '6', '7', '8'};
        // char [] novaSenha = {'W', 'X', 'Y', 'Z', '5', '6'};
        testador.redefinirSenha(senhaTeste, novaSenha);

        // Limpando as senhas
        Arrays.fill(senha, '0');
        testador.limparSenha(senhaTeste);
        testador.limparSenha(novaSenha);
    }
}
