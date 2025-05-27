/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.IRecursoEducativo;
import MODELO.RecursoEducativo;
import java.util.List;

/**
 *
 * @author User
 */
public class ControladorRecursoEducativo {
    private final IRecursoEducativo servicio;

    public ControladorRecursoEducativo(IRecursoEducativo servicio) {
        this.servicio = servicio;
    }

    public List<RecursoEducativo> obtenerRecursos() {
        return servicio.listarRecursos();
    }
    public void agregarRecurso(String titulo, String contenido) {
        RecursoEducativo nuevo = new RecursoEducativo(0, titulo, contenido);
        servicio.guardarRecurso(nuevo);
    }
}
