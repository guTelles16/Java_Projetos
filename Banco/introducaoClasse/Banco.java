import javax.swing.JOptionPane;

public class Banco {
    private String nome;
    private String nomeUsuario;
    private double saldo;

    // Construtor: inicializar os valores inicais do objeto.
    public Banco(String nome, String nomeUsuario, double saldoInicial) {
        setNome(nome);
        setNomeUsuario(nomeUsuario);
        setSaldo(saldoInicial); // Define o saldo inicial do banco.
    }

    /*
     * Métodos: ações que o objeto pode realizar.
     * Getters: obter o valor de um atributo.
     * Setters: alterar o valor de um atributo.
    */

    public void exibirSaldo() {
        JOptionPane.showMessageDialog(null, "Usuário: " + getNomeUsuario() +"\nNome do banco: " +
        getNome() + "\nSaldo: R$ " + getSaldo(), "Saldo", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getNome() {
        return this.nome;
    }

    // Validação do nome do banco. Se for nulo ou vazio, lança uma exceção.
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    // Validação do nome do usuário. Se for nulo ou vazio, lança uma exceção.
    public void setNomeUsuario(String nomeUsuario) {
        if (nomeUsuario == null || nomeUsuario.isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário inválido!");
        }
        this.nomeUsuario = nomeUsuario;
    }

    // Validação do saque. Se for maior que o saldo, lança uma exceção.
    public void sacar(double saque) {
        if (saque > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente!");
        }
        else if (saque <= 0) {
            throw new IllegalArgumentException("Valor inválido!");
        }
        this.saldo -= saque;
    }

    // Validação do depósito. Se for menor ou igual a zero, lança uma exceção
    public void depositar(double deposito) {
        if (deposito <= 0) {
            throw new IllegalArgumentException("Valor inválido!");
        }
        this.saldo += deposito;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) { // Permite saldo inicial zero.
            throw new IllegalArgumentException("Valor inválido!");
        }
        this.saldo = saldo;
    }
}