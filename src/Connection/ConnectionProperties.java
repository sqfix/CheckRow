package Connection;

import com.CheckboxesEntity;
import com.vaadin.ui.GridLayout;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * Created by Makc on 19.02.2017.
 */
public class ConnectionProperties {

    public void testDB(){
        try(Session session = ConnectionInstall.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            CheckboxesEntity entity = new CheckboxesEntity(0, 0);
            session.save(entity);
            session.getTransaction().commit();
        }
    }


    public void updateValues(int x, int y, boolean value)
    {
        if(value)
            addRecord(x, y);
        else
            removeRecord(x, y);
    }

    private void addRecord(int x, int y) {
        if(getIDByValue(y, x) == 0) {
            try (Session session = ConnectionInstall.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(new CheckboxesEntity(x, y));
                session.getTransaction().commit();
            }
        }
    }

    private void removeRecord(int x, int y) {
        try(Session session = ConnectionInstall.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(new CheckboxesEntity(getIDByValue(y, x)));
            session.getTransaction().commit();
        }
    }

    public int getIDByValue(int y, int x)
    {
        int removeID = 0;
        for(CheckboxesEntity entity : getAll())
        {
            if(entity.getCheckboxCol() == y && entity.getCheckboxRow() == x)
            {
                removeID = entity.getId();
                break;
            }
        }
        return removeID;
    }

    public boolean getValueByValue(GridLayout.Area area)
    {
        return (getIDByValue(area.getRow1(), area.getColumn1()) != 0);
    }

    public List<CheckboxesEntity> getAll()
    {
        try(Session session = ConnectionInstall.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            return session.createCriteria(CheckboxesEntity.class).list();
        }
    }

}
