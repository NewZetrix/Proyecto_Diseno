package MODELO;

import java.util.ArrayList;
import java.util.List;

public class DatosComplementariosPublisher {
    private final List<IDatosObserver> observadores = new ArrayList<>();

    public void agregarObserver(IDatosObserver observer) {
        observadores.add(observer);
    }

    public void notificar(DatosComplementarios datos) {
        for (IDatosObserver obs : observadores) {
            obs.actualizar(datos);
        }
    }
}
