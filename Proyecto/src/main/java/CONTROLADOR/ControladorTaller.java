/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ITaller;
import MODELO.Taller;

/**
 *
 * @author User
 */
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
