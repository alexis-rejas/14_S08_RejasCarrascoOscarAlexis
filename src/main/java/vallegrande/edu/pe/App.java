package vallegrande.edu.pe;

import vallegrande.edu.pe.view.MiniPaginaView;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {

        // 🔥 Ejecutar en hilo de Swing (buena práctica)
        SwingUtilities.invokeLater(() -> {
            new MiniPaginaView().setVisible(true);
        });

    }
}