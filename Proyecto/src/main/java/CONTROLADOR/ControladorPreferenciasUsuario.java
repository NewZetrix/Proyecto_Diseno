/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.IPreferenciasUsuario;
import MODELO.PreferenciasUsuario;

/**
 *
 * @author User
 */
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
