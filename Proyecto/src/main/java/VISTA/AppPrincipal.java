package VISTA;

//import VISTA.Modulo1.RegistroUsuarioFrame;
import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorDenuncia;
import CONTROLADOR.ControladorRecursoEducativo;
import CONTROLADOR.ControladorTaller;
import CONTROLADOR.ControladorUsuario;
import MODELO.DAO.DenunciaDAO;
import MODELO.INTERFACES.IDenuncia;
import MODELO.INTERFACES.IRecursoEducativo;
import MODELO.INTERFACES.ITaller;
import MODELO.INTERFACES.IUsuario;
import MODELO.DAO.RecursoEducativoDAO;
import MODELO.DAO.TallerDAO;
import MODELO.DAO.UsuarioDAO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class AppPrincipal {
    public static void main(String[] args) {
        ConexionBD conexion = new ConexionBD();
        if (conexion.establecerConexion() != null) {
        JOptionPane.showMessageDialog(null, "Conexión inicial exitosa a la base de datos.");
        }
        
        // DAO y controladores (si luego los necesitas)
        IRecursoEducativo recursoDAO = new RecursoEducativoDAO(conexion);
        ControladorRecursoEducativo controladorRecursos = new ControladorRecursoEducativo(recursoDAO);

        IDenuncia denunciaDAO = new DenunciaDAO(conexion);
        ControladorDenuncia controladorDenuncia = new ControladorDenuncia(denunciaDAO);

        ITaller tallerDAO = new TallerDAO(conexion);
        ControladorTaller controladorTaller = new ControladorTaller(tallerDAO);

        IUsuario usuarioDAO = new UsuarioDAO(conexion);
        ControladorUsuario controladorUsuario = new ControladorUsuario(usuarioDAO);

        // Lanzar interfaz gráfica
        //javax.swing.SwingUtilities.invokeLater(() -> {new RegistroUsuarioFrame().setVisible(true);});
        SwingUtilities.invokeLater(() -> new BienvenidaFrame().setVisible(true));
    }
}
