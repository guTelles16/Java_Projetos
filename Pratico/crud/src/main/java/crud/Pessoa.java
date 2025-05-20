package crud;

public class Pessoa {
    private int id;
    private String nome;
    private int idade;

    // Construtor para criação (CREATE)
    public Pessoa(String nome, int idade) {
        setNome(nome);
        setIdade(idade);
    }

    // Construtor para atualização (UPDATE)
    public Pessoa(int id, String nome, int idade) {
        setNome(nome);
        setIdade(idade);
        setId(id);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id inválido!");
        }
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        this.nome = nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade inválida!");
        }
        this.idade = idade;
    }
}
