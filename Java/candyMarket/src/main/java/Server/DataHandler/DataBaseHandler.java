package Server.DataHandler;

import PathHandler.PathHandler;
import Server.Model.*;
import com.google.gson.JsonArray;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import javax.persistence.criteria.CriteriaBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class DataBaseHandler {
    private static SessionFactory sessionFactory = null;
    private static StandardServiceRegistryBuilder serviceRegistry = null;
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<User> users2 = new ArrayList<>();
    private static void configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure("/Hibernate Maps/hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
    }
    private void resetData(){

    }
    public static void main(String[] args) throws SQLException {
        ArrayList<String> strings = new ArrayList<>();
        System.out.println(strings.toString());
        User user1 = new User("a","arash","iran");
        user1.addItems("yek");
        user1.addItems("do");
        user1.addItems("se");
        users.add(new User("b","ali","afghanestan"));
        users.add(user1);
        setAllDataToSql();
        getAllDataFromSql();
    }
    public static void setAllDataToSql() throws SQLException {
        //setUsersData();
        //setManagersData();
        setBuyersData();
    }
    public static void getAllDataFromSql() throws SQLException {
        //getUsersData();
        //getManagersData();
        getBuyersData();
    }
    private static void getUsersData(){
        Configuration configuration = new Configuration();
        configuration.configure("/Hibernate Maps/hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            users2 = (ArrayList<User>) users;
            for (User user : users2) {
                System.out.println(user.getName());
            }
            for (String item : users2.get(1).getItems()) {
                System.out.println(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private static void setUsersData(){
        Configuration configuration = new Configuration();
        configuration.configure("/Hibernate Maps/hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for (User user : users) {
                session.save(user);
            }
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private static void setManagersData(){
        ManageInfo.allManagers.add(new Manager("a","a","a","b","c","d"));
        Configuration configuration = new Configuration();
        configuration.configure("/Hibernate Maps/manager.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for (Manager manager : ManageInfo.allManagers) {
                session.save(manager);
            }
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private static void setBuyersData(){
        Category category = new Category("c",null);
        Seller seller = new Seller("s","a","a","a","3","w","co");
        Good good = new Good("g","g",2,seller,2,category,"",null,null);
        Buyer buyer1 = new Buyer("buyer1","a","a","b","c","d");
        HashMap<Good,Integer> goods = new HashMap<>();
        goods.put(good,2);
        //buyer1.addMyLogs(new BuyLog(2,1,goods,"buyer2"));
        ManageInfo.allBuyers.add(buyer1);
        Configuration configuration = new Configuration();
        configuration.configure("/Hibernate Maps/buyer.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for (Buyer buyer : ManageInfo.allBuyers) {
                session.save(buyer);
            }
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private static void getManagersData() throws SQLException {
        Configuration configuration = new Configuration();
        configuration.configure("/Hibernate Maps/manager.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            List<Manager> managers = session.createQuery("from Manager").list();
            ManageInfo.allManagers = (ArrayList<Manager>) managers;
            for (Manager manager : ManageInfo.allManagers) {
                System.out.println(manager.getUsername());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    private static void getBuyersData() throws SQLException {
        Configuration configuration = new Configuration();
        configuration.configure("/Hibernate Maps/buyer.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            List<Buyer> buyers = session.createQuery("from Buyer").list();
            ManageInfo.allBuyers = (ArrayList<Buyer>) buyers;
            for (Buyer buyer : ManageInfo.allBuyers) {
                System.out.println(buyer.getUsername());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
