package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.EstadoSolicitude;
import util.HibernateUtil;

/**
 *
 * @author aaliagab generate
 */
public class EstadoSolicitudeDAOImplement extends GenericDAOImplement<EstadoSolicitude, Integer> implements EstadoSolicitudeDAO{
private final static Logger LOGGER = Logger.getLogger(GenericDAOImplement.class.getName());
    @Override
    public EstadoSolicitude getByNome(String nome) throws BussinessException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();        
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            session.isOpen();
            List<EstadoSolicitude> entities = session.createQuery("SELECT e FROM EstadoSolicitude e WHERE e.nome='"+nome+"'").list();            
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