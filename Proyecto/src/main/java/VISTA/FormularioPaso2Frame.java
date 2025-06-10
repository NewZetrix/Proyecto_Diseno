/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VISTA;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorDatosComplementarios;
import MODELO.DatosComplementariosDAO;
import MODELO.IDatosComplementarios;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author User
 */
public class FormularioPaso2Frame extends JFrame {
    private final int usuarioId;
    private JTextField txtNivel, txtOcupacion, txtEdad, txtInteres;
    private JComboBox<String> cbGenero;
    private JButton btnAceptar, btnCancelar;
    private ControladorDatosComplementarios controlador;

    public FormularioPaso2Frame(int usuarioId) {
        this.usuarioId = usuarioId;

        IDatosComplementarios dao = new DatosComplementariosDAO(new ConexionBD());
        controlador = new ControladorDatosComplementarios(dao);

        setTitle("Paso 2 - Datos Complementarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel navegación módulos
        JPanel panelModulos = new JPanel(new GridLayout(1, 4));
        String[] modulos = {"M1", "M2", "M3", "M4"};
        for (String mod : modulos) {
            JButton btn = new JButton(mod);
            btn.setEnabled(false);
            panelModulos.add(btn);
        }
        panelPrincipal.add(panelModulos, BorderLayout.NORTH);

        // Panel pasos
        JPanel panelPasos = new JPanel(new FlowLayout());
        panelPasos.add(new JButton("1..."));
        panelPasos.add(new JButton("2..."));
        panelPasos.add(new JButton("3..."));
        panelPrincipal.add(panelPasos, BorderLayout.CENTER);

        // Panel campos
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
        JLabel lblTitulo = new JLabel("2. Datos Complementarios");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCampos.add(lblTitulo);

        txtNivel = agregarCampo(panelCampos, "Nivel educativo:");
        txtOcupacion = agregarCampo(panelCampos, "Ocupación:");
        txtEdad = agregarCampo(panelCampos, "Edad:");

        JPanel filaGenero = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setPreferredSize(new Dimension(120, 20));
        cbGenero = new JComboBox<>(new String[]{"Femenino", "Masculino", "Otro"});
        filaGenero.add(lblGenero);
        filaGenero.add(cbGenero);
        panelCampos.add(filaGenero);

        txtInteres = agregarCampo(panelCampos, "Interés en talleres:");

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBackground(Color.GREEN);
        btnAceptar.addActionListener(e -> registrarDatos());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(Color.RED);
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        panelCampos.add(panelBotones);

        panelPrincipal.add(panelCampos, BorderLayout.SOUTH);
        add(panelPrincipal);
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

    private void registrarDatos() {
        try {
            String nivel = txtNivel.getText().trim();
            String ocupacion = txtOcupacion.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            String genero = (String) cbGenero.getSelectedItem();
            String interes = txtInteres.getText().trim();

            if (nivel.isEmpty() || ocupacion.isEmpty() || interes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "❌ Complete todos los campos.");
                return;
            }

            controlador.registrar(usuarioId, nivel, ocupacion, edad, genero, interes);
            JOptionPane.showMessageDialog(this, "✅ Datos complementarios registrados con éxito.");

            // Ir al formulario paso 3
            new FormularioPaso3Frame(usuarioId).setVisible(true);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "❌ Edad debe ser un número válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }
}
