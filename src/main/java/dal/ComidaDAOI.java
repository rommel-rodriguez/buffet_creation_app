package dal;
import java.util.*;
import models.entities.Comida;

public interface ComidaDAOI {

    void createComida(Comida categoria);

    void storeComida(Comida categoria);

    List<Comida> listComidas();

    Comida showComida(int id);

    void updateComida(Comida categoria);

    void deleteComida(int id);

}
