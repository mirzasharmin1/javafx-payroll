package com.programming2.payroll.services;

import com.programming2.payroll.models.BaseModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseService {

    protected Session createSession() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        return sessionFactory.openSession();
    }

    public boolean createModel(BaseModel model) {
        if (model == null) return false;

        Session session = null;

        try {
            session = createSession();

            session.beginTransaction();
            session.save(model);
            session.getTransaction().commit();

            return true;

        } catch (Exception e) {

            if (session != null) {
                session.getTransaction().rollback();
            }

            return false;
        } finally {
            closeSession(session);
        }
    }

    protected <T> List<T> getRecords(Class<T> type, String hql, Map<String, Object> params) {

        Session session = null;

        try {
            session = createSession();
            session.beginTransaction();

            Query<T> query = session.createQuery(hql, type);

            Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<String, Object> pair = it.next();
                query.setParameter(pair.getKey(), pair.getValue());
                it.remove();
            }

            List<T> data = query.list();

            session.getTransaction().commit();

            return data;
        } catch (Exception e) {

            if (session != null) {
                session.getTransaction().rollback();
            }

            return null;

        } finally {
            closeSession(session);
        }
    }

    protected <T> List<T> getAll(Class<T> type) {

        Session session = null;

        try {
            session = createSession();

            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            criteria.from(type);
            List<T> data = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();

            return data;
        } catch (Exception e) {

            if (session != null) {
                session.getTransaction().rollback();
            }

            return null;
        } finally {

            closeSession(session);
        }
    }

    protected <T> T getById(Class<T> type, int id) {

        Session session = null;

        try {
            session = createSession();

            session.beginTransaction();
            T data = session.get(type, id);
            session.getTransaction().commit();

            return data;
        } catch (Exception e) {

            if (session != null) {
                session.getTransaction().rollback();
            }

            return null;
        } finally {

            closeSession(session);
        }
    }

    protected void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }
}
