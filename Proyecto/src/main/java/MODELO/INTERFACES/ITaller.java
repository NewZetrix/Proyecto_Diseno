package MODELO.INTERFACES;

import MODELO.Taller;
import java.util.List;

public interface ITaller {
    List<Taller> obtenerTalleres();
    void inscribirTaller(int idTaller, String nombreTaller);
}
