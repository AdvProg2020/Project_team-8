package Server.DataHandler.DataBase;


import Server.Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfigs {
    private static SessionFactory sessionFactory;
    private static Session session;

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("/Hibernate Maps/hibernate.cfg.xml")
                    .addAnnotatedClass(Manager.class)
                    .addAnnotatedClass(BuyLog.class)
                    .addAnnotatedClass(Buyer.class)
                    .addAnnotatedClass(Discount.class)
                    .addAnnotatedClass(Good.class)
                    .addAnnotatedClass(Manager.class)
                    .addAnnotatedClass(Request.class)
                    .addAnnotatedClass(Sale.class)
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(Score.class)
                    .addAnnotatedClass(Seller.class)
                    .addAnnotatedClass(SellLog.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Supporter.class)
                    .addAnnotatedClass(Chat.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("SessionFactory Creation Failed" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void runDataBase() {
        sessionFactory = buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static void shutdown() {
        session.close();
        sessionFactory.close();
    }
    public static Session getSession() {
        return session;
    }
}
