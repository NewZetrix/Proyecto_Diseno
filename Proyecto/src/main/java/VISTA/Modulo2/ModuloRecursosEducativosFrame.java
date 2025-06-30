package VISTA.Modulo2;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorRecursoEducativo;
import MODELO.DAO.RecursoEducativoDAO;
import MODELO.INTERFACES.IRecursoEducativo;
import MODELO.RecursoEducativo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModuloRecursosEducativosFrame extends JFrame{
    private JTable tablaRecursos;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cbCategoria;
    private ControladorRecursoEducativo controlador;
    private List<RecursoEducativo> recursosTotales;

    public ModuloRecursosEducativosFrame() {
        setTitle("Recursos Educativos sobre Igualdad de Género");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        IRecursoEducativo dao = new RecursoEducativoDAO(new ConexionBD());
        controlador = new ControladorRecursoEducativo(dao);

        inicializarComponentes();
        cargarRecursos();
    }

    private void inicializarComponentes() {
        JPanel panelSuperior = new JPanel(new BorderLayout());

        // Filtro por categoría
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.add(new JLabel("Filtrar por categoría:"));

        cbCategoria = new JComboBox<>(new String[]{"Todos", "Educación", "Salud", "Derechos", "Otro"});
        cbCategoria.addActionListener(e -> filtrarPorCategoria());
        panelFiltro.add(cbCategoria);

        panelSuperior.add(panelFiltro, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Título", "Categoría", "Descripción"}, 0);
        tablaRecursos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaRecursos);
        panelSuperior.add(scroll, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnVer = new JButton("📖 Ver recurso seleccionado");
        btnVer.addActionListener(e -> mostrarRecursoSeleccionado());

        JButton btnRecibir = new JButton("📩 Recibir recurso");
        btnRecibir.addActionListener(e -> recibirRecurso());

        panelBotones.add(btnVer);
        panelBotones.add(btnRecibir);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarRecursos() {
        modeloTabla.setRowCount(0);
        recursosTotales = controlador.obtenerRecursos();
        for (RecursoEducativo r : recursosTotales) {
            modeloTabla.addRow(new Object[]{r.getId(), r.getTitulo(), r.getCategoria(), r.getContenido()});
        }
    }
    
    private void filtrarPorCategoria() {
        String categoriaSeleccionada = cbCategoria.getSelectedItem().toString();
        modeloTabla.setRowCount(0);

        List<RecursoEducativo> filtrados = recursosTotales;
        if (!categoriaSeleccionada.equalsIgnoreCase("Todos")) {
            filtrados = recursosTotales.stream()
                .filter(r -> r.getCategoria().equalsIgnoreCase(categoriaSeleccionada))
                .collect(Collectors.toList());
        }

        for (RecursoEducativo r : filtrados) {
            modeloTabla.addRow(new Object[]{r.getId(), r.getTitulo(), r.getCategoria(), r.getContenido()});
        }
    }
    
    private void mostrarRecursoSeleccionado() {
        int fila = tablaRecursos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un recurso.");
            return;
        }

        String titulo = modeloTabla.getValueAt(fila, 1).toString();
        String descripcion = modeloTabla.getValueAt(fila, 3).toString();

        JTextArea txt = new JTextArea(descripcion);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setEditable(false);
        txt.setBackground(new Color(245, 245, 245));

        JScrollPane scroll = new JScrollPane(txt);
        scroll.setPreferredSize(new Dimension(400, 200));

        JOptionPane.showMessageDialog(this, scroll, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void recibirRecurso() {
        int fila = tablaRecursos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un recurso.");
            return;
        }

        String titulo = modeloTabla.getValueAt(fila, 1).toString();
        JOptionPane.showMessageDialog(this, "Has recibido el recurso: " + titulo, "Recurso recibido", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void dispose() {
        super.dispose();
        new VISTA.Modulo1.RegistroUsuarioFrame().setVisible(true);
    }
}
