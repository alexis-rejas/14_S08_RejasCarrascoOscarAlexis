package vallegrande.edu.pe.model;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPrestamooRepository {

    private List<Prestamo> lista = new ArrayList<>();

    public void agregar(Prestamo p) {
        lista.add(p);
    }

    public List<Prestamo> listar() {
        return lista;
    }

    public void eliminar(int index) {
        if(index >= 0 && index < lista.size()){
            lista.remove(index);
        }
    }

    public void actualizar(int index, Prestamo p) {
        if(index >= 0 && index < lista.size()){
            lista.set(index, p);
        }
    }
}