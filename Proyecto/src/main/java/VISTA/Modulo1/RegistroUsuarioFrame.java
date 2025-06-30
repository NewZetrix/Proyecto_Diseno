package VISTA.Modulo1;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorUsuario;
import CONTROLADOR.SesionUsuario;
import MODELO.INTERFACES.IUsuario;
import MODELO.COMMAND.InvocadorCommand;
import MODELO.COMMAND.RegistrarUsuarioCommand;
import MODELO.Usuario;
import MODELO.DAO.UsuarioDAO;
import java.awt.BorderLayout;
import static java.awt.Color.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
    private JButton btnAceptar, btnCancelar;
    

    private ControladorUsuario controladorUsuario;

    public RegistroUsuarioFrame() {
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
        
            switch (mod) {
                case "M1":
                    btn.addActionListener(e -> {
                        if (SesionUsuario.estaRegistroCompleto()) {
                            JOptionPane.showMessageDialog(
                                this,
                                "Ya se encuentra dentro del sistema. Puede acceder a los módulos desde aquí.",
                                "Módulo activo",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(
                                this,
                                "Aún no ha completado el registro. Por favor, regístrese primero.",
                                "Registro incompleto",
                                JOptionPane.WARNING_MESSAGE
                            );
                        }
                    });
                    break;
                case "M2":
                    btn.addActionListener(e -> {
                        new VISTA.Modulo2.ModuloRecursosEducativosFrame().setVisible(true);
                        dispose(); // opcional, para cerrar el menú actual
                    });
                    break;
                case "M3":
                    btn.addActionListener(e -> {
                        new VISTA.Modulo3.ModuloDenunciaFrame().setVisible(true);
                        dispose(); // opcional, si quieres cerrar esta ventana
                    });
                    break;
                case "M4":
                    btn.addActionListener(e -> {
                        new VISTA.Modulo4.ModuloTallerFrame().setVisible(true);
                        dispose(); // opcional: cierra el menú actual
                    });
                    break;
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
    
    private void limpiarCampos() {
        txtNombre.setText("");
        cbTipoUsuario.setSelectedIndex(0);
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
    }

}
