package VISTA;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorPreferenciasUsuario;
import MODELO.IPreferenciasUsuario;
import MODELO.PreferenciasUsuarioDAO;
import java.awt.BorderLayout;
import static java.awt.Color.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class PreferenciaUsuarioFrame extends JFrame{
    private final int usuarioId;
    private JComboBox<String> cbHorario, cbMedioContacto, cbRecibeMateriales;
    private JTextArea txtComentario;
    private JButton btnAceptar, btnCancelar;

    private ControladorPreferenciasUsuario controlador;

    public PreferenciaUsuarioFrame(int usuarioId) {
        this.usuarioId = usuarioId;

        IPreferenciasUsuario dao = new PreferenciasUsuarioDAO(new ConexionBD());
        controlador = new ControladorPreferenciasUsuario(dao);

        setTitle("Paso 3 - Preferencias del Usuario");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Módulos
        JPanel panelModulos = new JPanel(new GridLayout(1, 4));
        for (String mod : new String[]{"M1", "M2", "M3", "M4"}) {
            JButton btn = new JButton(mod);
            btn.setEnabled(false);
            panelModulos.add(btn);
        }
        panelPrincipal.add(panelModulos, BorderLayout.NORTH);

        // Pasos
        JPanel panelPasos = new JPanel(new FlowLayout());
        panelPasos.add(new JButton("1..."));
        panelPasos.add(new JButton("2..."));
        panelPasos.add(new JButton("3..."));
        panelPrincipal.add(panelPasos, BorderLayout.CENTER);

        // Campos
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("3. Preferencias del Usuario");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCampos.add(lblTitulo);

        cbHorario = agregarCombo(panelCampos, "Horario disponible:", new String[]{"Mañana", "Tarde", "Noche"});
        cbMedioContacto = agregarCombo(panelCampos, "Medio de contacto:", new String[]{"Correo", "Teléfono", "WhatsApp", "Otro"});
        cbRecibeMateriales = agregarCombo(panelCampos, "¿Desea recibir materiales?:", new String[]{"Sí", "No"});

        // Comentario
        JPanel filaComentario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl = new JLabel("Comentario:");
        lbl.setPreferredSize(new Dimension(140, 20));
        txtComentario = new JTextArea(3, 25);
        txtComentario.setLineWrap(true);
        txtComentario.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtComentario);
        filaComentario.add(lbl);
        filaComentario.add(scroll);
        panelCampos.add(filaComentario);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(GREEN);
        btnAceptar.addActionListener(e -> registrarPreferencias());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(RED);
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        panelCampos.add(panelBotones);

        panelPrincipal.add(panelCampos, BorderLayout.SOUTH);
        add(panelPrincipal);
    }

    private JComboBox<String> agregarCombo(JPanel panel, String etiqueta, String[] opciones) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl = new JLabel(etiqueta);
        lbl.setPreferredSize(new Dimension(140, 20));
        JComboBox<String> combo = new JComboBox<>(opciones);
        fila.add(lbl);
        fila.add(combo);
        panel.add(fila);
        return combo;
    }

    private void registrarPreferencias() {
        String horario = (String) cbHorario.getSelectedItem();
        String medio = (String) cbMedioContacto.getSelectedItem();
        String recibe = (String) cbRecibeMateriales.getSelectedItem();
        String comentario = txtComentario.getText().trim();

        try {
            controlador.registrar(usuarioId, horario, medio, recibe, comentario);
            JOptionPane.showMessageDialog(this, "Registro completado con éxito.\n¡Gracias por participar!");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar preferencias: " + e.getMessage());
        }
    }
}
