package manager;

import java.util.ArrayList;
import models.Identifiable;
import models.exception.IdNotFoundException;

public class Finder<T extends Identifiable > {

    public T findByID(ArrayList<T> list , int id) throws IdNotFoundException
    {
        for (T item : list)
        {
            if(item.getID() == id)
            {
                return item ;
            }
        }
        throw new IdNotFoundException(id);
    }
}
