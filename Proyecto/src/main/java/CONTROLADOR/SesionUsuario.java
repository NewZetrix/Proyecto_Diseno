package CONTROLADOR;

public class SesionUsuario {
    private static boolean registroCompletado = false;

    public static void marcarRegistroComoCompleto() {
        registroCompletado = true;
    }

    public static boolean estaRegistroCompleto() {
        return registroCompletado;
    }
}
