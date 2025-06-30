package MODELO.OBSERVER;

import MODELO.DatosComplementarios;
import MODELO.INTERFACES.IDatosObserver;
import javax.swing.JOptionPane;

public class SeguimientoObserver implements IDatosObserver{

    @Override
    public void actualizar(DatosComplementarios datos) {
        if (datos.getEdad() < 18) {
            JOptionPane.showMessageDialog(null, "Usuario menor de edad detectado. Se recomienda seguimiento especial.");
        }
    }
    
}
