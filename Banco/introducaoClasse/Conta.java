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
        this.saldo = 0; // Saldo inicial é 0
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
        return nome;
    }

    // Validação do nome do cliente. Se for nulo ou vazio, lança uma exceção.
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    // Validação de CPF. Se for nulo ou vazio, lança uma exceção.
    public void setCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF inválido!");
        }
        this.cpf = cpf;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    // Validação do número da conta. Se for menor ou igual a zero, lança uma exceção
    public void setNumeroConta(int numeroConta) {
        if (numeroConta <= 0) {
            throw new IllegalArgumentException("Número da conta inválido!");
        }
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    // Validação do saque. Se for maior que o saldo, lança uma exceção.
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {;
            saldo -= valor;
            extrato.add("Saque de R$" + valor);
            return true;
        } else {
            throw new IllegalArgumentException("Valor inválido ou saldo insuficiente!");
        } 
    }

    // Validação do depósito. Se for menor ou igual a zero, lança uma exceção
    public boolean depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            extrato.add("Depósito de R$" + valor);
            return true;
        } else {
            throw new IllegalArgumentException("Valor inválido para deposito!");
        }
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
