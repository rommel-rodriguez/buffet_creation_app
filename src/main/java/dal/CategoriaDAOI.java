package dal;
import java.util.*;
import models.entities.Categoria;

public interface CategoriaDAOI {
	
	
    void createCategoria(Categoria categoria);

    void storeCategoria(Categoria categoria);

    List<Categoria> listCategorias();

    Categoria showCategoria(int id);

    void updateCategoria(Categoria categoria);

    void deleteCategoria(int id);

}
