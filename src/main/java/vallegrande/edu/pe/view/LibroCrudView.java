package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.LibroController;
import vallegrande.edu.pe.model.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibroCrudView extends JFrame {

    private LibroController controller = new LibroController();
    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtTitulo, txtAutor, txtCategoria, txtEstado, txtBuscar;

    public LibroCrudView() {
        setTitle("Gestión de Libros");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔵 HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(21, 67, 96));
        JLabel titulo = new JLabel("📚 Gestión de Libros");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        header.add(titulo);
        add(header, BorderLayout.NORTH);

        // ⚪ FORMULARIO
        JPanel form = new JPanel(new GridLayout(3,4,10,10));
        form.setBorder(BorderFactory.createTitledBorder("Datos del Libro"));
        form.setBackground(Color.WHITE);

        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtCategoria = new JTextField();
        txtEstado = new JTextField();
        txtBuscar = new JTextField();

        form.add(new JLabel("Título:"));
        form.add(txtTitulo);
        form.add(new JLabel("Autor:"));
        form.add(txtAutor);
        form.add(new JLabel("Categoría:"));
        form.add(txtCategoria);
        form.add(new JLabel("Estado:"));
        form.add(txtEstado);

        // 🔍 BUSCADOR
        form.add(new JLabel("Buscar:"));
        form.add(txtBuscar);

        add(form, BorderLayout.BEFORE_FIRST_LINE);

        // 📋 TABLA
        modelo = new DefaultTableModel(new String[]{"Título","Autor","Categoría","Estado"},0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // 🔘 BOTONES
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.setBackground(Color.WHITE);

        JButton btnVolver = new JButton("⬅ Menú");
        JButton btnAgregar = new JButton("➕ Agregar");
        JButton btnEditar = new JButton("✏️ Editar");
        JButton btnEliminar = new JButton("🗑️ Eliminar");

        btnVolver.setBackground(Color.GRAY);
        btnVolver.setForeground(Color.WHITE);

        btnAgregar.setBackground(new Color(40, 116, 166));
        btnAgregar.setForeground(Color.WHITE);

        btnEditar.setBackground(new Color(52, 152, 219));
        btnEditar.setForeground(Color.WHITE);

        btnEliminar.setBackground(new Color(192, 57, 43));
        btnEliminar.setForeground(Color.WHITE);

        botones.add(btnVolver);
        botones.add(btnAgregar);
        botones.add(btnEditar);
        botones.add(btnEliminar);

        add(botones, BorderLayout.SOUTH);

        // EVENTOS

        btnVolver.addActionListener(e -> {
            new MiniPaginaView().setVisible(true);
            dispose();
        });

        btnAgregar.addActionListener(e -> {
            if(validar()){
                controller.agregar(
                        txtTitulo.getText(),
                        txtAutor.getText(),
                        txtCategoria.getText(),
                        txtEstado.getText()
                );
                limpiar();
                cargarTabla();
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if(fila >= 0){
                controller.eliminar(fila);
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro");
            }
        });

        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if(fila >= 0){
                Libro l = controller.listar().get(fila);

                String t = JOptionPane.showInputDialog("Título:", l.getTitulo());
                String a = JOptionPane.showInputDialog("Autor:", l.getAutor());
                String c = JOptionPane.showInputDialog("Categoría:", l.getCategoria());
                String es = JOptionPane.showInputDialog("Estado:", l.getEstado());

                if(t == null || a == null || c == null || es == null ||
                        t.trim().isEmpty() || a.trim().isEmpty() ||
                        c.trim().isEmpty() || es.trim().isEmpty()){

                    JOptionPane.showMessageDialog(this, "Campos vacíos no permitidos");
                    return;
                }

                controller.editar(fila, t, a, c, es);
                cargarTabla();

            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro");
            }
        });

        // 🔥 BÚSQUEDA EN TIEMPO REAL
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                modelo.setRowCount(0);

                for(Libro l : controller.listar()){
                    if(l.getTitulo().toLowerCase().contains(txtBuscar.getText().toLowerCase()) ||
                            l.getAutor().toLowerCase().contains(txtBuscar.getText().toLowerCase())){

                        modelo.addRow(new Object[]{
                                l.getTitulo(),
                                l.getAutor(),
                                l.getCategoria(),
                                l.getEstado()
                        });
                    }
                }
            }
        });

        // 🔥 CARGA INICIAL
        cargarTabla();
    }

    private boolean validar(){
        if(txtTitulo.getText().trim().isEmpty() ||
                txtAutor.getText().trim().isEmpty() ||
                txtCategoria.getText().trim().isEmpty() ||
                txtEstado.getText().trim().isEmpty()){

            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return false;
        }
        return true;
    }

    private void limpiar(){
        txtTitulo.setText("");
        txtAutor.setText("");
        txtCategoria.setText("");
        txtEstado.setText("");
    }

    private void cargarTabla(){
        modelo.setRowCount(0);
        for(Libro l : controller.listar()){
            modelo.addRow(new Object[]{
                    l.getTitulo(),
                    l.getAutor(),
                    l.getCategoria(),
                    l.getEstado()
            });
        }
    }
}