package MODELO;

import java.util.ArrayList;
import java.util.List;

public class DatosComplementariosPublisher {
    private final List<DatosObserver> observadores = new ArrayList<>();

    public void agregarObserver(DatosObserver observer) {
        observadores.add(observer);
    }

    public void notificar(DatosComplementarios datos) {
        for (DatosObserver obs : observadores) {
            obs.actualizar(datos);
        }
    }
}
