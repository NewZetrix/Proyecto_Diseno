package CONTROLADOR;

import MODELO.INTERFACES.IPreferenciasUsuario;
import MODELO.PreferenciasUsuario;

public class ControladorPreferenciasUsuario {
    private final IPreferenciasUsuario servicio;

    public ControladorPreferenciasUsuario(IPreferenciasUsuario servicio) {
        this.servicio = servicio;
    }

    public void registrar(int usuarioId, String horario, String medio, String recibe, String comentario) {
        PreferenciasUsuario p = new PreferenciasUsuario(usuarioId, horario, medio, recibe, comentario);
        servicio.registrarPreferencias(p);
    }
}
