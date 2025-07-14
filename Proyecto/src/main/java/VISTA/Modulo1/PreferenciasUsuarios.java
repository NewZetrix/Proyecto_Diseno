package VISTA.Modulo1;

import CONEXIONSQL.ConexionBD;
import CONTROLADOR.ControladorPreferenciasUsuario;
import CONTROLADOR.SesionUsuario;
import MODELO.DAO.PreferenciasUsuarioDAO;
import MODELO.DAO.UsuarioDAO;
import MODELO.INTERFACES.IEnvioMaterialService;
import MODELO.INTERFACES.IPreferenciasUsuario;
import MODELO.INTERFACES.IUsuario;
import MODELO.PROXY.EnvioMaterialProxy;
import MODELO.PROXY.EnvioMaterialServiceReal;
import javax.swing.*;

public class PreferenciasUsuarios extends javax.swing.JFrame {

    private final int usuarioId;
    private ControladorPreferenciasUsuario controlador;
    
    public PreferenciasUsuarios(int usuarioId) {
        this.usuarioId = usuarioId;
        IPreferenciasUsuario dao = new PreferenciasUsuarioDAO(new ConexionBD());
        controlador = new ControladorPreferenciasUsuario(dao);

        setTitle("Paso 3 - Preferencias del Usuario");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        
        btnAceptar.addActionListener(e -> registrarPreferencias());
        btnCancelar.addActionListener(e -> this.dispose());
    }

    private void registrarPreferencias() {
        String horario = cbHorario.getSelectedItem().toString();
        String medio = cbMedioContacto.getSelectedItem().toString();
        String recibe = cbRecibeMateriales.getSelectedItem().toString();
        String comentario = txtComentario.getText().trim();
        boolean aceptaMaterial = recibe.equalsIgnoreCase("Sí");

        try {
            IUsuario usuarioDAO = new UsuarioDAO(new ConexionBD());
            String correo = usuarioDAO.obtenerCorreoPorId(usuarioId);

            // Proxy para simular el envío
            IEnvioMaterialService servicio = new EnvioMaterialProxy(new EnvioMaterialServiceReal(), aceptaMaterial);
            servicio.enviarMaterial(correo, comentario);

            controlador.registrar(usuarioId, horario, medio, recibe, comentario);
            JOptionPane.showMessageDialog(this, "Registro completado correctamente.");

            SesionUsuario.marcarRegistroComoCompleto();
            this.dispose();
            new RegistroUsuario().setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnPaso1 = new javax.swing.JButton();
        btnPaso3 = new javax.swing.JButton();
        btnPaso2 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbHorario = new javax.swing.JComboBox<>();
        cbMedioContacto = new javax.swing.JComboBox<>();
        cbRecibeMateriales = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(180, 246, 246));

        btn.setBackground(new java.awt.Color(78, 140, 241));
        btn.setText("M1");

        btn1.setBackground(new java.awt.Color(78, 140, 241));
        btn1.setText("M2");

        btn2.setBackground(new java.awt.Color(78, 140, 241));
        btn2.setText("M3");

        btn3.setBackground(new java.awt.Color(78, 140, 241));
        btn3.setText("M4");

        btnPaso1.setBackground(new java.awt.Color(119, 144, 225));
        btnPaso1.setText("1. Registro");

        btnPaso3.setBackground(new java.awt.Color(119, 144, 225));
        btnPaso3.setText("3. Complementarios");

        btnPaso2.setBackground(new java.awt.Color(119, 144, 225));
        btnPaso2.setText("2. Preferencias");

        btnCancelar.setBackground(new java.awt.Color(223, 74, 74));
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");

        btnAceptar.setBackground(new java.awt.Color(102, 255, 102));
        btnAceptar.setForeground(new java.awt.Color(0, 0, 0));
        btnAceptar.setText("Aceptar");

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Horario Disponible:");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("¿Desea recibir materiales? :");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Medio de Contacto:");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Comentario:");

        cbHorario.setBackground(new java.awt.Color(255, 255, 255));
        cbHorario.setForeground(new java.awt.Color(0, 0, 0));
        cbHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mañana", "Tarde", "Noche" }));

        cbMedioContacto.setBackground(new java.awt.Color(255, 255, 255));
        cbMedioContacto.setForeground(new java.awt.Color(0, 0, 0));
        cbMedioContacto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Correo", "Teléfono", "WhatsApp", "Otro" }));

        cbRecibeMateriales.setBackground(new java.awt.Color(255, 255, 255));
        cbRecibeMateriales.setForeground(new java.awt.Color(0, 0, 0));
        cbRecibeMateriales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sí", "No" }));

        txtComentario.setBackground(new java.awt.Color(255, 255, 255));
        txtComentario.setColumns(20);
        txtComentario.setForeground(new java.awt.Color(0, 0, 0));
        txtComentario.setRows(5);
        jScrollPane1.setViewportView(txtComentario);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("3. Prefencias del Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPaso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(94, 94, 94)
                        .addComponent(btnPaso2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(btnPaso3)))
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29)
                        .addComponent(cbRecibeMateriales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMedioContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPaso1)
                    .addComponent(btnPaso3)
                    .addComponent(btnPaso2))
                .addGap(35, 35, 35)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbMedioContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbRecibeMateriales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPaso1;
    private javax.swing.JButton btnPaso2;
    private javax.swing.JButton btnPaso3;
    private javax.swing.JComboBox<String> cbHorario;
    private javax.swing.JComboBox<String> cbMedioContacto;
    private javax.swing.JComboBox<String> cbRecibeMateriales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtComentario;
    // End of variables declaration//GEN-END:variables
}
