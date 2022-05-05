package dao;

import pojos.Acesso;

/**
 *
 * @author aaliagab generate
 */
public interface AcessoDAO extends GenericDAO<Acesso, Integer>{
    Acesso getByNome(String nome) throws BussinessException;
}