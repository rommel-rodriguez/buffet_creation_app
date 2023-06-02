package dal;
import java.util.*;
import models.entities.Insumo;

public interface InsumoDAOI {
	
	
    void createInsumo(Insumo insumo);

    void storeInsumo(Insumo insumo);

    List<Insumo> listInsumos();

    Insumo showInsumo(int id);

    void updateInsumo(Insumo insumo);

    void deleteInsumo(int id);

}
