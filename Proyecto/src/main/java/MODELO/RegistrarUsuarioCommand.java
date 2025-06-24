package MODELO;

import CONTROLADOR.ControladorUsuario;

public class RegistrarUsuarioCommand implements ICommand{
    private final ControladorUsuario controlador;
    private final Usuario usuario;
    private int usuarioId;

    public RegistrarUsuarioCommand(ControladorUsuario controlador, Usuario usuario) {
        this.controlador = controlador;
        this.usuario = usuario;
    }

    @Override
    public void ejecutar() {
        usuarioId = controlador.registrarYObtenerId(
            usuario.getNombre(),
            usuario.getTipoUsuario(),
            usuario.getTelefono(),
            usuario.getDireccion(),
            usuario.getCorreo()
        );
    }

    public int getUsuarioId() {
        return usuarioId;
    }
}
