package dao;

import pojos.EstadoSolicitude;

/**
 *
 * @author aaliagab generate
 */
public interface EstadoSolicitudeDAO extends GenericDAO<EstadoSolicitude, Integer>{
    EstadoSolicitude getByNome(String nome) throws BussinessException;
}