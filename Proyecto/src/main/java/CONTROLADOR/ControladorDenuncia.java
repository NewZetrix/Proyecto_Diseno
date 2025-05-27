/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.Denuncia;
import MODELO.IDenuncia;

/**
 *
 * @author User
 */
public class ControladorDenuncia {
    private final IDenuncia servicio;

    public ControladorDenuncia(IDenuncia servicio) {
        this.servicio = servicio;
    }

    public void enviarDenuncia(String desc, boolean anonima, String fecha) {
        Denuncia denuncia = new Denuncia(desc, anonima, fecha);
        servicio.registrarDenuncia(denuncia);
    }
}
