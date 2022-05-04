/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author Adriel
 */
@Transactional
public class GenericDAOImplement<T,ID extends Serializable> implements GenericDAO<T, ID>{

    private final static Logger LOGGER = Logger.getLogger(GenericDAOImplement.class.getName());
    public GenericDAOImplement() {
       
    }
    
    @Override
    public T create() throws BussinessException {
        try {
            return getEntityClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void save(T entity) throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            session.save(entity);
            tx.commit();
        } catch (HibernateException he) {
            try {
                if (tx!=null && tx.isActive()) {
                    tx.rollback();
                }
                else
                    LOGGER.log(Level.WARNING,"Falló al hacer un rollback", he);
                he.printStackTrace();
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING,"Falló al hacer un rollback", exc);
            }
            throw new BussinessException(he);
        }
    }

    @Override
    public void update(T entity) throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            session.update(entity);
            tx.commit();
        } catch (HibernateException he) {
            try {
                if (tx!=null && tx.isActive()) {
                    tx.rollback();
                }
                else
                    LOGGER.log(Level.WARNING,"Falló al hacer un rollback", he);
                he.printStackTrace();
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING,"Falló al hacer un rollback", exc);
            }
            throw new BussinessException(he);
        }
    }

    @Override
    public T get(ID id) throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            T entity = (T) session.get(getEntityClass(), id);                
            tx.commit();
            return entity;
        } catch (HibernateException he) {
            try {
                if (tx!=null && tx.isActive()) {
                    tx.rollback();
                }
                else
                    LOGGER.log(Level.WARNING,"Falló al hacer un rollback", he);
                    he.printStackTrace();
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING,"Falló al hacer un rollback", exc);
            }
            throw new BussinessException(he);
        }
    }

    @Override
    public void delete(ID id) throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            T entity = (T) session.get(getEntityClass(), id); 
            if (entity == null) {
                throw new BussinessException(new HibernateException("Los datos a borrar ya no existen"));
            }
            session.delete(entity);
            tx.commit();            
        } catch (HibernateException he) {
            try {
                if (tx!=null && tx.isActive()) {
                    tx.rollback();
                }
                else
                    LOGGER.log(Level.WARNING,"Falló al hacer un rollback", he);
                he.printStackTrace();
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING,"Falló al hacer un rollback", exc);
            }
            throw new BussinessException(he);
        }
    }

    @Override    
    public List<T> findAll() throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();        
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            session.isOpen();
            List<T> entities = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e").list();            
            tx.commit();            
            return entities;
        } catch (HibernateException he) {
            try {
                if (tx!=null && tx.isActive()) {
                    tx.rollback();
                }
                else
                    LOGGER.log(Level.WARNING,"Falló al hacer un rollback", he);
                he.printStackTrace();
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING,"Falló al hacer un rollback", exc);
            }
            throw new BussinessException(he);
        }
    }
    
    @Override
    public Integer saveID(T entity) throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=null;
        Integer id = null;
        try {
            tx=session.beginTransaction();
            id = (Integer) session.save(entity);
            tx.commit();
            return id;
        } catch (HibernateException he) {
            try {
                if (tx!=null && tx.isActive()) {
                    tx.rollback();
                }
                else
                    LOGGER.log(Level.WARNING,"Falló al hacer un rollback", he);
                he.printStackTrace();
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING,"Falló al hacer un rollback", exc);
            }
            throw new BussinessException(he);
        }        
    }
    
    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }  
}
