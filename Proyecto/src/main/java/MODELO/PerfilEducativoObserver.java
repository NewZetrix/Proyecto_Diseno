package MODELO;

import javax.swing.JOptionPane;

public class PerfilEducativoObserver implements DatosObserver{

    @Override
    public void actualizar(DatosComplementarios datos) {
        String nivel = datos.getNivelEducativo().toLowerCase();
        if (nivel.contains("primaria") || nivel.contains("secundaria")) {
            JOptionPane.showMessageDialog(null, "📘 Recomendamos visitar los recursos introductorios de igualdad de género.");
        } else if (nivel.contains("universidad") || nivel.contains("superior")) {
            JOptionPane.showMessageDialog(null, "🎓 Tenemos recursos avanzados disponibles para ti.");
        }
    }
    
}
