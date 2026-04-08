package vallegrande.edu.pe.model;

import java.util.ArrayList;
import java.util.List;

public class InMemoryLibroRepository {

    private List<Libro> lista = new ArrayList<>();

    public void agregar(Libro libro) {
        lista.add(libro);
        guardarCSV(); // 🔥 guarda automático
    }

    public List<Libro> listar() {
        return lista;
    }

    public void eliminar(int index) {
        if(index >= 0 && index < lista.size()){
            lista.remove(index);
            guardarCSV(); // 🔥 guarda automático
        }
    }

    public void actualizar(int index, Libro libro) {
        if(index >= 0 && index < lista.size()){
            lista.set(index, libro);
            guardarCSV(); // 🔥 guarda automático
        }
    }

    // 🔥 GUARDAR CSV
    public void guardarCSV(){
        try{
            java.io.PrintWriter pw = new java.io.PrintWriter("libros.csv");
            for(Libro l: lista){
                pw.println(l.getTitulo()+","+l.getAutor()+","+l.getCategoria()+","+l.getEstado());
            }
            pw.close();
        }catch(Exception e){
            System.out.println("Error al guardar CSV");
        }
    }

    // 🔥 CARGAR CSV
    public void cargarCSV(){
        try{
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("libros.csv"));
            String line;
            lista.clear(); // evita duplicados

            while((line = br.readLine()) != null){
                String[] d = line.split(",");
                if(d.length == 4){
                    lista.add(new Libro(d[0], d[1], d[2], d[3]));
                }
            }
            br.close();
        }catch(Exception e){
            System.out.println("No hay archivo CSV aún");
        }
    }
}