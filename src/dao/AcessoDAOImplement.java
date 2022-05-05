package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Acesso;
import util.HibernateUtil;

/**
 *
 * @author aaliagab generate
 */
public class AcessoDAOImplement extends GenericDAOImplement<Acesso, Integer> implements AcessoDAO{
private final static Logger LOGGER = Logger.getLogger(GenericDAOImplement.class.getName());
    @Override
    public Acesso getByNome(String nome) throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();        
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            session.isOpen();
            List<Acesso> entities = session.createQuery("SELECT e FROM Acesso e WHERE e.nome='"+nome+"'").list();            
            tx.commit();            
            return entities.size()>0?entities.get(0):null;
        } catch (HibernateException he) {
            try {
                if (tx!=null && tx.isActive()) {
                    tx.rollback();
                }
                else
                    LOGGER.log(Level.WARNING,"Falha ao reverter", he);
                he.printStackTrace();
            } catch (Exception exc) {
                LOGGER.log(Level.WARNING,"Falha ao reverter", exc);
            }
            throw new BussinessException(he);
        }
    }
    
}