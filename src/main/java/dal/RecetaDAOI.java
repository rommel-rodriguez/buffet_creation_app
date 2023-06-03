package dal;
import java.util.*;
import models.entities.Receta;

public interface RecetaDAOI {
	
	
    void createReceta(Receta receta);

    void storeReceta(Receta receta);

    List<Receta> listRecetas();

    Receta showReceta(int id);

    void updateReceta(Receta receta);

    void deleteReceta(int id);

}
