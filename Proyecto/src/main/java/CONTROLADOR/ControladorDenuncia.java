package CONTROLADOR;

import MODELO.Denuncia;
import MODELO.INTERFACES.IDenuncia;

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
