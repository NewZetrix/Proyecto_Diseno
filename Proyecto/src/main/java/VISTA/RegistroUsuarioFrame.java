package VISTA;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorUsuario;
import MODELO.IUsuario;
import MODELO.InvocadorCommand;
import MODELO.RegistrarUsuarioCommand;
import MODELO.Usuario;
import MODELO.UsuarioDAO;
import java.awt.BorderLayout;
import static java.awt.Color.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
//import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistroUsuarioFrame extends JFrame {

    
    private JTextField txtNombre, txtTelefono, txtDireccion, txtCorreo;
    private JComboBox<String> cbTipoUsuario;
    private JButton btnAceptar, btnCancelar, btnRegresar;
    

    private ControladorUsuario controladorUsuario;

    public RegistroUsuarioFrame() {
        // Controlador
        IUsuario usuarioDAO = new UsuarioDAO(new ConexionBD());
        controladorUsuario = new ControladorUsuario(usuarioDAO);

        setTitle("Registro de Usuario");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel navegación módulos (superior)
        JPanel panelModulos = new JPanel(new GridLayout(1, 4));
        String[] modulos = {"M1", "M2", "M3", "M4"};
        for (String mod : modulos) {
        JButton btn = new JButton(mod);
        
            if (mod.equals("M4")) {
                btn.setEnabled(true); // M4 habilitado, puedes asignar funcionalidad real
            } else {
                btn.setEnabled(true); // M1, M2, M3 habilitados pero con aviso
                btn.addActionListener(e -> JOptionPane.showMessageDialog(
                    this,
                    "Este módulo no está activo en estos momentos.",
                    "Módulo Inactivo",
                    JOptionPane.INFORMATION_MESSAGE
                ));
            }

            panelModulos.add(btn);
        }
        panelPrincipal.add(panelModulos, BorderLayout.NORTH);

        // Panel pasos
        JPanel panelPasos = new JPanel(new FlowLayout());
        JButton btnPaso1 = new JButton("1...");
        JButton btnPaso2 = new JButton("2...");
        JButton btnPaso3 = new JButton("3...");
        btnPaso1.setBackground(cyan);
        btnPaso2.setBackground(cyan);
        btnPaso3.setBackground(cyan);
        panelPasos.add(btnPaso1);
        panelPasos.add(btnPaso2);
        panelPasos.add(btnPaso3);
        panelPrincipal.add(panelPasos, BorderLayout.CENTER);

        // Panel de campos
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("1. Registro de Usuario");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCampos.add(lblTitulo);

        txtNombre = agregarCampo(panelCampos, "Nombre:");
        //txtTipo = agregarCampo(panelCampos, "Tipo de Usuario:");
        // Tipo de usuario con JComboBox
        JPanel filaTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTipo = new JLabel("Tipo de Usuario:");
        lblTipo.setPreferredSize(new Dimension(120, 20));
        String[] tipos = {"Seleccione una opción","Administrador", "Tallerista", "Participante"};
        cbTipoUsuario = new JComboBox<>(tipos);
        cbTipoUsuario.setPreferredSize(new Dimension(200, 25));
        filaTipo.add(lblTipo);
        filaTipo.add(cbTipoUsuario);
        panelCampos.add(filaTipo);
        txtTelefono = agregarCampo(panelCampos, "Telefono:");
        txtDireccion = agregarCampo(panelCampos, "Direccion:");
        txtCorreo = agregarCampo(panelCampos, "Correo:");

        // Botones Aceptar / Cancelar
        JPanel panelBotones = new JPanel(new FlowLayout());

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(GREEN);
        btnAceptar.addActionListener(e -> registrarUsuario());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(RED);
        btnCancelar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        panelCampos.add(panelBotones);

        panelPrincipal.add(panelCampos, BorderLayout.SOUTH);
        add(panelPrincipal);
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText().trim();
        String tipo = (String) cbTipoUsuario.getSelectedItem();
        if (tipo.equals("Seleccione una opción")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de usuario válido.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (nombre.isEmpty() || tipo.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Usuario nuevoUsuario = new Usuario(nombre, tipo, telefono, direccion, correo);

            RegistrarUsuarioCommand comando = new RegistrarUsuarioCommand(controladorUsuario, nuevoUsuario);
            InvocadorCommand invocador = new InvocadorCommand();
            invocador.setCommand(comando);
            invocador.ejecutarComando();

            int usuarioId = comando.getUsuarioId();
            JOptionPane.showMessageDialog(this, "Usuario registrado con ID: " + usuarioId);

            new DatosComplementsFrame(usuarioId).setVisible(true);
            dispose();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private JTextField agregarCampo(JPanel panel, String etiqueta) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl = new JLabel(etiqueta);
        lbl.setPreferredSize(new Dimension(120, 20));
        JTextField campo = new JTextField(25);
        fila.add(lbl);
        fila.add(campo);
        panel.add(fila);
        return campo;
    }

    /*private void regresarPaso() {
        if (pasoActual > 1) {
            pasoActual--;
            actualizarPaso();
        }
    }*/
    private void limpiarCampos() {
        txtNombre.setText("");
        cbTipoUsuario.setSelectedIndex(0);
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
    }

}
