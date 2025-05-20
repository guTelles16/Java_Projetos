package ValidadorDeCPF;

public class ValidadorCPF {
    public static boolean validarCPF(String cpf) {
        // Remove pontuação e caracteres não numéricos
        cpf = cpf.replaceAll("[^\\d]", "");

        // Verifica se o CPF tem 11 dígitos ou se possui sequências repetidas
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        try {
            // Cálculo do primeiro dígito verificador
            int totalSomaPonderada = 0;
            for (int i = 0; i < 9; i++) {
                totalSomaPonderada += (cpf.charAt(i) - '0') * (10 - i);
            }

            int resto = totalSomaPonderada % 11;
            int primeiroDigito = (resto < 2) ? 0 : 11 - resto;

            // Verifica se o primeiro digito verificador está correto
            if (primeiroDigito != (cpf.charAt(9) - '0')) {
                return false;
            }

            // Cálculo do segundo dígito verificador
            totalSomaPonderada = 0;
            for (int i = 0; i < 10; i++) {
                totalSomaPonderada += (cpf.charAt(i) - '0') * (11 - i);
            }

            resto = totalSomaPonderada % 11;
            int segundoDigito = (resto < 2) ? 0 : 11 - resto;

            // Verifica se o segundo dígito verificador está correto
            if (segundoDigito == (cpf.charAt(10) - '0')) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}