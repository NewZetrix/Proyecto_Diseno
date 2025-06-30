package CONTROLADOR;

import MODELO.DatosComplementarios;
import MODELO.INTERFACES.IDatosComplementarios;

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
