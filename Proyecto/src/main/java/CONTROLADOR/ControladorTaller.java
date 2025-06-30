package CONTROLADOR;

import MODELO.INTERFACES.ITaller;
import MODELO.Taller;
import java.util.List;

public class ControladorTaller {
    private final ITaller servicio;

    public ControladorTaller(ITaller servicio) {
        this.servicio = servicio;
    }

    public List<Taller> obtenerTalleres(){
        return this.servicio.obtenerTalleres();
    }
    
    public void inscribirTaller(int idTaller, String nombreTaller) {
        servicio.inscribirTaller(idTaller,nombreTaller);
    }
}
