/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.DatosComplementarios;
import MODELO.IDatosComplementarios;

/**
 *
 * @author User
 */
public class ControladorDatosComplementarios {
    private final IDatosComplementarios servicio;

    public ControladorDatosComplementarios(IDatosComplementarios servicio) {
        this.servicio = servicio;
    }

    public void registrar(int usuarioId, String nivel, String ocupacion, int edad, String genero, String interes) {
        DatosComplementarios datos = new DatosComplementarios(usuarioId, nivel, ocupacion, edad, genero, interes);
        servicio.registrarDatos(datos);
    }
}
