package ValidadorDeSenha;

import java.util.Arrays;

public class TestadorSenha {
    private char[] senha; // Senha como array de char (forma mais segura)
    private boolean senhaAtivada;

    public TestadorSenha(char[] senha) {
        this.senhaAtivada = false;

        // Validar a senha antes de definir
        if (validarSenha(senha)) {
            this.senha = Arrays.copyOf(senha, senha.length); // Cópia segura da senha
            this.senhaAtivada = true;
            System.out.println("Senha definida com sucesso!");
        } else {
            System.out.println("Senha inválida! Use ao menos 8 caracteres, incluindo números e letras.");
        }
    }

    // Validar força da senha
    private boolean validarSenha(char[] senha) {
        if (senha.length < 8) return false;

        boolean temNumero = false;
        boolean temLetra = false;

        for (char c : senha) {
            if (Character.isDigit(c)) temNumero = true;
            if (Character.isLetter(c)) temLetra = true;
        }

        return temNumero && temLetra; // Senha deve conter números e letras
    }

    // Método para autenticar
    public boolean autenticar(char[] senhaDigitada) {
        if (!senhaAtivada) {
            System.out.println("Senha não definida!");
            return false;
        }
        
        if (Arrays.equals(this.senha, senhaDigitada)) {
            System.out.println("Acesso autorizado!");
            return true;
        } else {
            System.out.println("Senha incorreta!");
            return false;
        }
    }

    // Método para redefinir a senha
    public void redefinirSenha (char[] senhaAntiga, char[] novaSenha) {
        if (autenticar(senhaAntiga)) {
            if (validarSenha(novaSenha)) {
                this.senha = Arrays.copyOf(novaSenha, novaSenha.length);
                System.out.println("Senha redefinida com sucesso!");
            } else {
                System.out.println("Nova senha inválida! Use ao menos 8 caracteres, incluindo números e letras.");
            }
        } else {
            System.out.println("Senha antiga incorreta! Redefinição falhou.");
        }
    }

    // Limpando a senha da memória
    public void limparSenha(char[] senhaDigitada) {
        if (this.senha != null && senhaDigitada != null) {
            if (Arrays.equals(this.senha, senhaDigitada)) {
                Arrays.fill(this.senha, '0');
                System.out.println("Senha removida da memória!");
            } else {
                System.out.println("Senha incorreta! Não foi possível remover.");
            }
        } else {
            System.out.println("Nenhuma senha para remover!");
        }
    }
}