package CONTROLADOR;

import MODELO.ITaller;
import MODELO.Taller;

public class ControladorTaller {
    private final ITaller servicio;

    public ControladorTaller(ITaller servicio) {
        this.servicio = servicio;
    }

    public void inscribirTaller(String nombre, String participante) {
        Taller t = new Taller(nombre, participante);
        servicio.registrarInscripcion(t);
    }

    public int totalInscritos() {
        return servicio.contarParticipantes();
    }
}
