package VISTA.Modulo4;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorTaller;
import MODELO.DAO.TallerDAO;
import MODELO.INTERFACES.ITaller;
import MODELO.Taller;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class ModuloTallerFrame extends JFrame{
    private JTable tablaTalleres;
    private DefaultTableModel modeloTabla;
    private ControladorTaller controlador;

    public ModuloTallerFrame() {
        setTitle("🎓 Talleres - Empoderamiento de la Mujer");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        ITaller dao = new TallerDAO(new ConexionBD());
        controlador = new ControladorTaller(dao);

        inicializarComponentes();
        cargarTalleres();
    }

    private void inicializarComponentes() {
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción", "Fecha", "Cupo"}, 0);
        tablaTalleres = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaTalleres);

        JLabel lblTitulo = new JLabel("Talleres disponibles", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnInscribirse = new JButton("Inscribirse en taller");
        btnInscribirse.setBackground(Color.orange);
        btnInscribirse.addActionListener(e -> inscribirseTaller());

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnInscribirse);

        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarTalleres() {
        modeloTabla.setRowCount(0);
        List<Taller> talleres = controlador.obtenerTalleres();

        for (Taller t : talleres) {
            modeloTabla.addRow(new Object[]{
                t.getId(), t.getNombre(), t.getDescripcion(), t.getFecha(), t.getCupo()
            });
        }
    }

    private void inscribirseTaller() {
        int fila = tablaTalleres.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un taller.");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        String nombre = modeloTabla.getValueAt(fila, 1).toString();
        int cupo = (int) modeloTabla.getValueAt(fila, 4);

        if (cupo <= 0) {
            JOptionPane.showMessageDialog(this, "Este taller ya no tiene cupo disponible.");
            return;
        }

        try {
            controlador.inscribirTaller(id, nombre);
            JOptionPane.showMessageDialog(this, "Te has inscrito exitosamente en: " + nombre);
            cargarTalleres(); // Recargar cupos
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al inscribirse: " + e.getMessage());
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        new VISTA.Modulo1.RegistroUsuario().setVisible(true);
    }
}
