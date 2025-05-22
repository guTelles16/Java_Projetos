package Banco.introducaoClasse;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Conta {
    private String nome;
    private String cpf;
    private int numeroConta;
    private double saldo;
    private ArrayList<String> extrato;

    // Construtor: inicializar os valores inicais do objeto
    public Conta(String nome, String cpf, int numeroConta) {
        setNome(nome);
        setCpf(cpf);
        setNumeroConta(numeroConta);
        setSaldo(0); // Define o saldo 0 na conta
        this.extrato = new ArrayList<>(); // Inicializa o extrato como uma lista vazia
    }

    /*
     * Métodos: ações que o objeto pode realizar.
     * Getters: obter o valor de um atributo.
     * Setters: alterar o valor de um atributo.
    */

    public void exibirConta() {
        JOptionPane.showMessageDialog(null, "Cliente: " + getNome() +"\nCPF: " +
            getCpf() + "\nNúmero da conta: " + getNumeroConta() + "\nSaldo: " + getSaldo(),
            "Informações da Conta", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getNome() {
        return this.nome;
    }

    // Validação do nome do cliente. Se for nulo ou vazio, lança uma exceção.
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    // Validação de CPF. Se for nulo ou vazio, lança uma exceção.
    public void setCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("Nome de usuário inválido!");
        }
        this.cpf = cpf;
    }

    public int getNumeroConta() {
        return this.numeroConta;
    }

    // Validação do número da conta. Se for menor ou igual a zero, lança uma exceção
    public void setNumeroConta(int numeroConta) {
        if (numeroConta <= 0) {
            throw new IllegalArgumentException("Número da conta inválido!");
        }
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return this.saldo;
    }

    // Permite saldo inicial zero.
    public void setSaldo(double saldo) {;
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo inválido!");
        }
        this.saldo = saldo;
    }

    // Validação do saque. Se for maior que o saldo, lança uma exceção.
    public boolean sacar(double valor) {
        if (valor > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente!");
        }
        else if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido!");
        }
        this.saldo -= valor;
        extrato.add("Saque de R$" + valor);
        return true; // Retorna verdadeiro se o saque for bem-sucedido
    }

    // Validação do depósito. Se for menor ou igual a zero, lança uma exceção
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido!");
        }
        this.saldo += valor;
        extrato.add("Depósito de R$" + valor);
    }

    // Adiciona uma operação ao extrato da conta
    public void registrarExtrato(String operacao) {
        extrato.add(operacao); //
    }

    // Exibe o extrato da conta
    public String verExtrato() {
        StringBuilder sb = new StringBuilder("Extrato da conta " + getNumeroConta() + ":\n");
        for (String linha: extrato) {
            sb.append(linha).append("\n"); // Adiciona cada linha do extrato ao StringBuilder
        }
        return sb.toString(); // Retorna o extrato formatado como uma string
    }
}
