package VISTA;

import VISTA.Modulo1.RegistroUsuarioFrame;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.*;

public class BienvenidaFrame extends JFrame {
    public BienvenidaFrame() {
        setTitle("Bienvenida");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel("Bienvenido/a al sistema. Por favor, regístrese para continuar.", SwingConstants.CENTER);
        panel.add(lbl, BorderLayout.CENTER);

        JButton btnIniciar = new JButton("Iniciar Registro");
        btnIniciar.addActionListener(e -> {
            new RegistroUsuarioFrame().setVisible(true);
            dispose(); // cierra la pantalla de bienvenida
        });
        panel.add(btnIniciar, BorderLayout.SOUTH);

        add(panel);
    }
}