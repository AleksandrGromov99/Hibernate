package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User(name, lastName, age);
        try (Connection connection = Util.getConnection(); Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Connection connection = Util.getConnection(); Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try (Connection connection = Util.getConnection(); Session session = Util.getSessionFactory().openSession()) {
            users = session.createQuery("from User", User.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Connection connection = Util.getConnection(); Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
