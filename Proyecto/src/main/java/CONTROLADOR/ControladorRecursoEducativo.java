package CONTROLADOR;

import MODELO.INTERFACES.IRecursoEducativo;
import MODELO.RecursoEducativo;
import java.util.List;

public class ControladorRecursoEducativo {
    private final IRecursoEducativo servicio;

    public ControladorRecursoEducativo(IRecursoEducativo servicio) {
        this.servicio = servicio;
    }

    public List<RecursoEducativo> obtenerRecursos() {
        return servicio.listarRecursos();
    }
    public void agregarRecurso(String titulo, String contenido, String categoria) {
        RecursoEducativo nuevo = new RecursoEducativo(0, titulo, contenido, categoria);
        servicio.guardarRecurso(nuevo);
    }
}
