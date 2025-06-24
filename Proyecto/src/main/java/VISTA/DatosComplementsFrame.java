package VISTA;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorDatosComplementarios;
import MODELO.DatosComplementarios;
import MODELO.DatosComplementariosDAO;
import MODELO.DatosComplementariosPublisher;
import MODELO.IDatosComplementarios;
import MODELO.PerfilEducativoObserver;
import MODELO.SeguimientoObserver;

import javax.swing.*;
import java.awt.*;

public class DatosComplementsFrame extends JFrame {
    private final int usuarioId;
    private JTextField txtOcupacion, txtEdad;
    private JComboBox<String> cbNivelEducativo;
    private JComboBox<String> cbInteresTaller;
    private JComboBox<String> cbGenero;
    private JButton btnAceptar, btnCancelar;
    private ControladorDatosComplementarios controlador;

    public DatosComplementsFrame(int usuarioId) {
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

        // Nivel educativo
        JPanel filaNivel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNivel = new JLabel("Nivel educativo:");
        lblNivel.setPreferredSize(new Dimension(120, 20));
        String[] niveles = {
            "Seleccione un nivel",
            "Primaria",
            "Secundaria",
            "Técnico",
            "Universitario",
            "Posgrado"
        };
        cbNivelEducativo = new JComboBox<>(niveles);
        cbNivelEducativo.setPreferredSize(new Dimension(200, 25));
        filaNivel.add(lblNivel);
        filaNivel.add(cbNivelEducativo);
        panelCampos.add(filaNivel);
        txtOcupacion = agregarCampo(panelCampos, "Ocupación:");
        txtEdad = agregarCampo(panelCampos, "Edad:");

        //Genero
        JPanel filaGenero = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setPreferredSize(new Dimension(120, 20));
        cbGenero = new JComboBox<>(new String[]{"Femenino", "Masculino", "Otro"});
        filaGenero.add(lblGenero);
        filaGenero.add(cbGenero);
        panelCampos.add(filaGenero);

        // Interés Taller
        JPanel filaTaller = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTaller = new JLabel("Interés Taller:");
        lblTaller.setPreferredSize(new Dimension(120, 20));
        String[] talleres = {
            "Seleccione un interés",
            "Empoderamiento",
            "Autocuidado",
            "Derechos",
            "Educación Financiera"
        };
        cbInteresTaller = new JComboBox<>(talleres);
        cbInteresTaller.setPreferredSize(new Dimension(200, 25));
        filaTaller.add(lblTaller);
        filaTaller.add(cbInteresTaller);
        panelCampos.add(filaTaller);

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
            String nivel = (String) cbNivelEducativo.getSelectedItem();
            String ocupacion = txtOcupacion.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            String genero = (String) cbGenero.getSelectedItem();
            String interes = (String) cbInteresTaller.getSelectedItem();
            
            if (nivel.equals("Seleccione un nivel") || interes.equals("Seleccione un interés")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar nivel educativo e interés válido.");
                return;
            }

            if (nivel.isEmpty() || ocupacion.isEmpty() || interes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            controlador.registrar(usuarioId, nivel, ocupacion, edad, genero, interes);
            JOptionPane.showMessageDialog(this, "Datos complementarios registrados con éxito.");

            // Ir al formulario paso 3
            new PreferenciaUsuarioFrame(usuarioId).setVisible(true);
            dispose();

            // Crear publisher y observers
            DatosComplementariosPublisher publisher = new DatosComplementariosPublisher();
            publisher.agregarObserver(new PerfilEducativoObserver());
            publisher.agregarObserver(new SeguimientoObserver());

            // Notificar observers con los datos ingresados
            DatosComplementarios datos = new DatosComplementarios(usuarioId, nivel, ocupacion, edad, genero, interes);
            publisher.notificar(datos);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad debe ser un número válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
       
    }
}
