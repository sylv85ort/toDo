package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//Hi


public class ToDoList {
    private static ToDoList instance;
    private SessionFactory factory;

    public ToDoList() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ToDoItem.class)
                .buildSessionFactory();
    }

    public static ToDoList getInstance() {
        if (instance == null) {
            instance = new ToDoList();
        }
        return instance;
    }

    public void addItem(ToDoItem item) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public void removeItem(int id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            ToDoItem item = session.get(ToDoItem.class, id);
            if (item != null) {
                session.delete(item);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public List<ToDoItem> getItems() {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List<ToDoItem> items = session.createQuery("from ToDoItem", ToDoItem.class).getResultList();
            session.getTransaction().commit();
            return items;
        } finally {
            session.close();
        }
    }

    public void clearItems() {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from ToDoItem").executeUpdate();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
