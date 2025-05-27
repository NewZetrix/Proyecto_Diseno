/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VISTA;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorUsuario;
import MODELO.IUsuario;
import MODELO.UsuarioDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class RegistroUsuarioFrame extends JFrame {
    private JPanel panelPrincipal, panelCampos;
    private JButton btnM1, btnM2, btnM3, btnM4;
    private JButton btnPaso1, btnPaso2, btnPaso3;
    private JButton btnAceptar, btnCancelar, btnRegresar;
    private JLabel lblPasoActual;
    private JTextField[] camposTexto;
    private int pasoActual = 1;

    private ControladorUsuario controladorUsuario;

    public RegistroUsuarioFrame() {
        // Inyección de dependencias
        IUsuario usuarioDAO = new UsuarioDAO(new ConexionBD());
        controladorUsuario = new ControladorUsuario(usuarioDAO);

        setTitle("Registro de Usuario");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
        actualizarPaso();
    }

    private void inicializarComponentes() {
        panelPrincipal = new JPanel(new BorderLayout());
        add(panelPrincipal);

        // Navegación de módulos
        JPanel panelNavegacion = new JPanel(new GridLayout(1, 4));
        btnM1 = new JButton("M1");
        btnM2 = new JButton("M2");
        btnM3 = new JButton("M3");
        btnM4 = new JButton("M4");

        ActionListener modNoDisponible = e -> JOptionPane.showMessageDialog(this, "Este módulo no se encuentra disponible");
        btnM1.addActionListener(modNoDisponible);
        btnM2.addActionListener(modNoDisponible);
        btnM3.addActionListener(modNoDisponible);
        btnM4.addActionListener(e -> dispose()); // salir

        panelNavegacion.add(btnM1);
        panelNavegacion.add(btnM2);
        panelNavegacion.add(btnM3);
        panelNavegacion.add(btnM4);

        panelPrincipal.add(panelNavegacion, BorderLayout.NORTH);

        // Panel de pasos
        JPanel panelPasos = new JPanel(new FlowLayout());
        btnPaso1 = new JButton("1...");
        btnPaso2 = new JButton("2...");
        btnPaso3 = new JButton("3...");
        btnPaso1.setEnabled(false);
        btnPaso2.setEnabled(false);
        btnPaso3.setEnabled(false);

        panelPasos.add(btnPaso1);
        panelPasos.add(btnPaso2);
        panelPasos.add(btnPaso3);
        panelPrincipal.add(panelPasos, BorderLayout.CENTER);

        // Campos de entrada
        panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
        lblPasoActual = new JLabel("Paso 1...");
        panelCampos.add(lblPasoActual);

        String[] etiquetas = {"Nombre", "Tipo de usuario"};
        camposTexto = new JTextField[etiquetas.length];
        for (int i = 0; i < etiquetas.length; i++) {
            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lbl = new JLabel(etiquetas[i] + ":");
            camposTexto[i] = new JTextField(20);
            fila.add(lbl);
            fila.add(camposTexto[i]);
            panelCampos.add(fila);
        }

        panelPrincipal.add(panelCampos, BorderLayout.SOUTH);

        // Botones de acción
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        btnRegresar = new JButton("Regresar");

        btnAceptar.addActionListener(e -> validarYConfirmar());
        btnCancelar.addActionListener(e -> limpiarCampos());
        btnRegresar.addActionListener(e -> regresarPaso());

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnRegresar);
        panelCampos.add(panelBotones);
    }

    private void actualizarPaso() {
        lblPasoActual.setText("Paso " + pasoActual + "...");
        btnPaso1.setEnabled(pasoActual == 1);
        btnPaso2.setEnabled(pasoActual == 2);
        btnPaso3.setEnabled(pasoActual == 3);
        limpiarCampos();
    }

    private void mostrarConfirmacion() {
        String nombre = camposTexto[0].getText().trim();
        String tipo = camposTexto[1].getText().trim();

        String mensaje = "<html><h3>Confirme sus datos:</h3>"
                       + "<ul>"
                       + "<li><b>Nombre:</b> " + nombre + "</li>"
                       + "<li><b>Tipo:</b> " + tipo + "</li>"
                       + "</ul></html>";

        JOptionPane.showMessageDialog(this, mensaje, "Confirmación de Usuario", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarFinalizacion() {
        int opcion = JOptionPane.showOptionDialog(
            this,
            "🎉 Usuario registrado con éxito.\n¿Desea registrar otro?",
            "Registro completado",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[]{"Sí, otro", "No, salir"},
            "Sí, otro"
        );

        if (opcion == JOptionPane.YES_OPTION) {
            pasoActual = 1;
            actualizarPaso();
        } else {
            dispose(); // Cierra la ventana
        }
    }
    
    private void validarYConfirmar() {
        String nombre = camposTexto[0].getText().trim();
        String tipo = camposTexto[1].getText().trim();

        if (nombre.isEmpty() || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar ambos campos.");
            return;
        }

        if (pasoActual == 1) {
            mostrarConfirmacion();
            pasoActual = 2;
            actualizarPaso();
        } else if (pasoActual == 2) {
            try {
                controladorUsuario.registrarNuevoUsuario(nombre, tipo);
                pasoActual = 3;
                actualizarPaso();
                mostrarFinalizacion();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error al registrar: " + ex.getMessage());
            }
        }
    }

    private void regresarPaso() {
        if (pasoActual > 1) {
            pasoActual--;
            actualizarPaso();
        }
    }

    private void limpiarCampos() {
        for (JTextField campo : camposTexto) {
            campo.setText("");
        }
    }

    
}
