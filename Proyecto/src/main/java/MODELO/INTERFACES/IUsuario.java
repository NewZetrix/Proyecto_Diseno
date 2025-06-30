package MODELO.INTERFACES;

import MODELO.Usuario;

public interface IUsuario {
    void registrarUsuario(Usuario usuario);

    public int registrarYDevolverId(Usuario usuario);

    public String obtenerCorreoPorId(int usuarioId);
}
