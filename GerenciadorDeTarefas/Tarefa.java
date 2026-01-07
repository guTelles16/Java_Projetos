public class Tarefa {
    private String descricao;
    private boolean concluida;

    // Construtor: método que inicializa o objeto
    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.concluida = false; // Nunca uma tarefa começa já concluída
    }

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void concluir() {
        this.concluida = true;
    }

    @Override 
    // Sobrescreve o método da classe Object; representação textual da tarefa
    public String toString() {
        return (concluida ? "[X] " : "[ ] ") + descricao;
    }
}