package Server.DataHandler;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;


public class DBManager {
    public static <T> List<T> loadAllData(Class<T> type) {
        Session session = HibernateConfigs.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return session.createQuery(criteria).getResultList();
    }

    public static <T> T loadObject(Class<T> type, Serializable serializable){
        Session session = HibernateConfigs.getSession();
        return session.get(type, serializable);
    }

    public static void saveObject(Object object){
        Session session = HibernateConfigs.getSession();
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
    }

    public static void deleteObject(Object object){
        Session session = HibernateConfigs.getSession();
        session.beginTransaction();
        session.remove(object);
        session.getTransaction().commit();
    }

}
