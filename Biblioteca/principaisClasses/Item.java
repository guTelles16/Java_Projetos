package Biblioteca.principaisClasses;

import javax.swing.JOptionPane;

public abstract class Item {
    protected String titulo;
    protected String autor;
    protected int anoPublicacao;
    protected boolean emprestado;

    protected Item(String titulo, String autor, int anoPublicacao, boolean emprestado) {
        setTitulo(titulo);
        setAutor(autor);
        setAnoPublicacao(anoPublicacao);
        this.emprestado = false; // Inicialmente, o livro não está emprestado.
    }

    abstract protected void exibirInformacoes();

    public String getTitulo() {
        return this.titulo;
    }

    // Método para validar o título do livro. Se for nulo ou vazio, lança uma exceção.
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título do livro não pode ser vazio!");
        }
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    // Método para validar o autor do livro. Se for nulo ou vazio, lança uma exceção.
    public void setAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("O autor do livro não pode ser vazio!");
        }
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return this.anoPublicacao;
    }

    // Método para validar o ano de publicação do livro.
    public void setAnoPublicacao(int anoPublicacao) {
        int anoAtual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        if (anoPublicacao < 1500 || anoPublicacao > anoAtual) {
            throw new IllegalArgumentException("O ano de publicação do livro deve ser entre 1500 e " + anoAtual + "!");
        }
        this.anoPublicacao = anoPublicacao;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public boolean getEmprestado() {
        return this.emprestado;
    }
    // Método para emprestar um livro. Se false => empresta, se true => já emprestado.
    public void emprestar() {
        if (!emprestado) {
            emprestado = true;
            JOptionPane.showMessageDialog(null, "O livro \"" + getTitulo() + "\" foi emprestado!");
            return;
        }
        JOptionPane.showMessageDialog(null,"O livro \"" + getTitulo() + "\" já está emprestado!");
    }

    // Método para devolver um livro. Se true => devolve, se false => já devolvido.
    public void devolver() {
        if (emprestado) {
            emprestado = false;
            JOptionPane.showMessageDialog(null, "O livro \"" + getTitulo() + "\" foi devolvido!");
            return;
        }
        JOptionPane.showMessageDialog(null, "O livro \"" + getTitulo() + "\" já está disponível!");
    }
}