package dao;

import pojos.Usuario;

/**
 *
 * @author aaliagab generate
 */
public interface UsuarioDAO extends GenericDAO<Usuario, Integer>{
    Usuario getByUserName(String getByUserName) throws BussinessException;
}