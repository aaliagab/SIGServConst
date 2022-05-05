package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Usuario;
import util.HibernateUtil;

/**
 *
 * @author aaliagab generate
 */
public class UsuarioDAOImplement extends GenericDAOImplement<Usuario, Integer> implements UsuarioDAO{
    private final static Logger LOGGER = Logger.getLogger(GenericDAOImplement.class.getName());
    @Override
    public Usuario getByUserName(String getByUserName) throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            List<Usuario> entities = session.createQuery("SELECT e FROM Usuario e WHERE e.nome='"+getByUserName+"'").list();
            tx.commit();
            return entities.size()==0?null:entities.get(0);
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
    
}