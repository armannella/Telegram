package manager;

import java.util.ArrayList;
import models.Identifiable;

public class Finder<T extends Identifiable > {

    public T findByID(ArrayList<T> list , int id)
    {
        for (T item : list)
        {
            if(item.getID() == id)
            {
                return item ;
            }
        }
        return null ;
    }
}
