package vallegrande.edu.pe.view;

import javax.swing.*;
import java.awt.*;

public class MiniPaginaView extends JFrame {

    public MiniPaginaView() {
        setTitle("Sistema de Biblioteca - Portal Principal");
        setSize(750, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // 🔵 HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(21, 67, 96));
        header.setPreferredSize(new Dimension(750, 80));

        JLabel titulo = new JLabel("📚 Sistema de Biblioteca");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        header.add(titulo);

        // ⚪ CENTRO
        JPanel centro = new JPanel();
        centro.setBackground(Color.WHITE);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        centro.add(Box.createVerticalStrut(15));

        // 🔥 LOGO (MEJOR UBICADO)
        ImageIcon icon = new ImageIcon("logo.png");
        JLabel logo = new JLabel(icon);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(logo);

        centro.add(Box.createVerticalStrut(10));

        JLabel subtitulo = new JLabel("Panel Principal");
        subtitulo.setFont(new Font("Arial", Font.BOLD, 16));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descripcion1 = new JLabel("Gestione libros y préstamos fácilmente");
        descripcion1.setFont(new Font("Arial", Font.PLAIN, 13));
        descripcion1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descripcion2 = new JLabel("Sistema desarrollado con Java Swing");
        descripcion2.setFont(new Font("Arial", Font.PLAIN, 13));
        descripcion2.setAlignmentX(Component.CENTER_ALIGNMENT);

        centro.add(subtitulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(descripcion1);
        centro.add(descripcion2);
        centro.add(Box.createVerticalStrut(25));

        // 🔘 BOTONES
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);

        JButton btnLibros = new JButton("📚 Libros");
        JButton btnPrestamos = new JButton("📖 Préstamos");
        JButton btnUsuarios = new JButton("👤 Usuarios");

        btnLibros.setBackground(new Color(40, 116, 166));
        btnLibros.setForeground(Color.WHITE);

        btnPrestamos.setBackground(new Color(52, 152, 219));
        btnPrestamos.setForeground(Color.WHITE);

        btnUsuarios.setBackground(new Color(39, 174, 96));
        btnUsuarios.setForeground(Color.WHITE);

        btnLibros.setFocusPainted(false);
        btnPrestamos.setFocusPainted(false);
        btnUsuarios.setFocusPainted(false);

        // EVENTOS
        btnLibros.addActionListener(e -> {
            new LibroCrudView().setVisible(true);
            dispose();
        });

        btnPrestamos.addActionListener(e -> {
            new PrestamoCrudView().setVisible(true);
            dispose();
        });

        // 🔥 AHORA SÍ FUNCIONA USUARIOS
        btnUsuarios.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Módulo de usuarios en construcción");
        });

        panelBotones.add(btnLibros);
        panelBotones.add(btnPrestamos);
        panelBotones.add(btnUsuarios);

        centro.add(panelBotones);

        // 🔽 FOOTER
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 240, 240));
        footer.setPreferredSize(new Dimension(750, 30));

        JLabel copy = new JLabel("© 2026 Sistema de Biblioteca");
        copy.setFont(new Font("Arial", Font.PLAIN, 11));

        footer.add(copy);

        // AGREGAR TODO
        panel.add(header, BorderLayout.NORTH);
        panel.add(centro, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);

        add(panel);
    }
}