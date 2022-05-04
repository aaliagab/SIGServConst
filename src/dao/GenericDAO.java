/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.BussinessException;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Adriel
 */
public interface GenericDAO<T,ID extends Serializable> {
    T create() throws BussinessException;
    void save(T entity) throws BussinessException;
    Integer saveID(T entity) throws BussinessException;
    void update(T entity) throws BussinessException;
    T get(ID id) throws BussinessException;
    void delete(ID id) throws BussinessException;
    List<T> findAll() throws BussinessException;
}
