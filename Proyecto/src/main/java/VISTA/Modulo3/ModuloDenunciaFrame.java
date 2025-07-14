package VISTA.Modulo3;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorDenuncia;
import MODELO.DAO.DenunciaDAO;
import MODELO.INTERFACES.IDenuncia;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class ModuloDenunciaFrame extends JFrame{
    private JTextArea txtDescripcion;
    private JCheckBox chkAnonima;
    private JButton btnEnviar, btnCancelar;
    private ControladorDenuncia controlador;

    public ModuloDenunciaFrame() {
        setTitle("Registro de Denuncia");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        IDenuncia dao = new DenunciaDAO(new ConexionBD());
        controlador = new ControladorDenuncia(dao);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Describa su denuncia:");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        txtDescripcion = new JTextArea(6, 40);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtDescripcion);
        panel.add(scroll);

        chkAnonima = new JCheckBox("🔒 Enviar como denuncia anónima");
        panel.add(chkAnonima);

        // Botones
        JPanel botones = new JPanel(new FlowLayout());
        btnEnviar = new JButton("Enviar");
        btnEnviar.setBackground(new Color(0, 153, 0));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.addActionListener(e -> enviarDenuncia());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(e -> dispose());

        botones.add(btnEnviar);
        botones.add(btnCancelar);
        panel.add(botones);

        add(panel, BorderLayout.CENTER);
    }

    private void enviarDenuncia() {
        String descripcion = txtDescripcion.getText().trim();
        boolean anonima = chkAnonima.isSelected();
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor describa la denuncia.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            controlador.enviarDenuncia(descripcion, anonima, fecha);
            JOptionPane.showMessageDialog(this, "Denuncia registrada correctamente.\nGracias por tu confianza.");
            dispose(); // O volver al menú si lo deseas
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar denuncia: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        new VISTA.Modulo1.RegistroUsuario().setVisible(true); // volver al menú
    }
}
