package MODELO.OBSERVER;

import MODELO.DatosComplementarios;
import MODELO.INTERFACES.IDatosObserver;
import javax.swing.JOptionPane;

public class PerfilEducativoObserver implements IDatosObserver{

    @Override
    public void actualizar(DatosComplementarios datos) {
        String nivel = datos.getNivelEducativo().toLowerCase();
        if (nivel.contains("Primaria") || nivel.contains("Secundaria")|| nivel.contains("Ténico")) {
            JOptionPane.showMessageDialog(null, "Recomendamos visitar los recursos introductorios de igualdad de género.");
        } else if (nivel.contains("universidad") || nivel.contains("superior")) {
            JOptionPane.showMessageDialog(null, "Tenemos recursos avanzados disponibles para ti.");
        }
    }
    
}
