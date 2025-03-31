package Biblioteca.principaisClasses;

import javax.swing.JOptionPane;

// A classe Livro é uma subclasse da classe Item. Ela herda os atributos e métodos da classe Item.
public class Livro extends Item{
    private String genero;
    private int numeroPaginas;

    public Livro(String titulo, String autor, int anoPublicacao, String genero, int numeroPaginas)
    {
        // Chama o construtor da classe pai Item.
        super(titulo, autor, anoPublicacao, false);
        setGenero(genero);
        setNumeroPaginas(numeroPaginas);
    }

    public void exibirInformacoes() {
        JOptionPane.showMessageDialog(null, "Título: " + getTitulo() + "\nAutor: " + getAutor() +
        "\nAno de publicação: " + getAnoPublicacao() + "\nEmprestado: " + getEmprestado() + "\nGênero: " + getGenero() +
        "\nNúmero de páginas: " + getNumeroPaginas(), "Informações do Livro", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getGenero() {
        return this.genero;
    }

    // Método para validar o gênero do livro. Se for nulo ou vazio, lança uma exceção.
    public void setGenero(String genero) {
        if (genero == null || genero.isEmpty()) {
            throw new IllegalArgumentException("O gênero do livro não pode ser vazio!");
        }
        this.genero = genero;
    }

    public int getNumeroPaginas() {
        return this.numeroPaginas;
    }

    // Método para validar o número de páginas do livro. Se for menor ou igual a zero, lança uma exceção.
    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas <= 0) {
            throw new IllegalArgumentException("O número de páginas do livro deve ser maior que zero!");
        }
        this.numeroPaginas = numeroPaginas;
    }
}