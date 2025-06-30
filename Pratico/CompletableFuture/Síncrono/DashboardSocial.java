import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class DashboardSocial {
    public static void main(String[] args) {
        System.out.println("Iniciando a busca de ID dos usuário...");
        long inicio = System.currentTimeMillis();

        // Buscando o ID do primeiro usuário
        int joaoID = buscarIDUsuario("João");
        System.out.println("ID de João é " + joaoID);

        int brunoID = buscarIDUsuario("Bruno");
        System.out.println("ID de Bruno é " + brunoID);

        // Buscando o nome do usuário pelo ID
        String nomeUsuario = buscarNomeUsuario(brunoID);
        System.out.println("Iniciando a montagem do Dashboard para " + nomeUsuario);
        
        // Buscando o perfil do usuário pelo ID
        String perfilUsuario = buscarPerfil(brunoID);

        // Buscando as postagens pelo ID do usuário
        int postID = buscarIDPosts("Post #1");
        System.out.println("ID da postagem é " + postID);

        // Buscando os comentários pelo ID da postagem
        String comentarioPost = buscarComentariosPost(postID);

        // Buscando as notificações pelo ID de um usuário
        int notificacoesUsuario = buscarNotificacoes(userID);

        long fim = System.currentTimeMillis();

        // Calculando o tempo total da operação
        System.out.println("Tempo total da operação: " + (fim - inicio) + " milissegundos.");
    }

    public static int buscarIDUsuario(String nomeUsuario) {
        System.out.println("--> Buscando o ID de: " + nomeUsuario);
        sleep(2); // Simulando tempo de busca
        System.out.println("ID de " + nomeUsuario + " encontrado.");
        return nomeUsuario.equals("João") ? 1 : 2;
    }

    public static String buscarNomeUsuario(int usuarioID) {
        validarIDUsuario(usuarioID);
        System.out.println("--> Buscando nome pelo ID de usuário " + usuarioID);        
        sleep(2);
        System.out.println("--> Nome de usuário encontrado.");
        return usuarioID == 1 ? "João" : "Bruno";
    }

    public static String buscarPerfil(int usuarioID) {
        String nomeUsuario = buscarNomeUsuario(usuarioID);
        System.out.println("--> Buscando perfil de " + nomeUsuario);
        sleep(2);
        System.out.println("Perfil de " + nomeUsuario + " encontrado.");
        return "Perfil de " + nomeUsuario;
    }

    public static int buscarIDPost(String tituloPost) {
        System.out.println("--> Buscando ID da postagem " + '"' + tituloPost + '"');        
        sleep(2);
        System.out.println("ID da postagem buscado.");
        return tituloPost.equals("Post #1") ? 1 : 2;    
    }

    public static String buscarTituloPost(int postID) {
        validarPostID(postID);
        System.out.println("--> Buscando título da postagem com ID " + postID);
        sleep(2);
        System.out.println("Título da postagem encontrado.");
        return postID == 1 ? "Post #1" : "Post #2";
    }

    public static String buscarComentariosPosts (int postID) {
        validarPostID(postID);
        String tituloPost = buscarTituloPost(postID);
        System.out.println("--> Buscando comentários da postagem " + '"' + tituloPost + '"');
        sleep(2);
        System.out.println("Comentários para " + '"' + tituloPost + '"' +  "encontrados.");
        return postID == 1 ? "Ótimo post!" : "Concordo!";
    }

    public static int buscarNotificacoes (int usuarioID) {
        validarIDUsuario(usuarioID);
        String nomeUsuario = buscarNomeUsuario(usuarioID);
        System.out.println("--> Buscando notificações de " + nomeUsuario);
        sleep(2);
        System.out.println("Notificações contadas.");
        return 30; // Simulando 30 notificações
    }

    public static void sleep(int segundos) {
        try { TimeUnit.SECONDS.sleep(segundos); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public static void validarIDUsuario(int usuarioID) {
        if (usuarioID <= 0) {
            throw new IllegalArgumentException("ID de usuário inválido: não pode ser menor ou igual a zero.");
        }
    }

    public static void validarPostID(int postID) {
        if (postID <= 0) {
            throw new IllegalArgumentException("ID de postagem inválido: não pode ser menor ou igual a zero.");
        }
    }
}
