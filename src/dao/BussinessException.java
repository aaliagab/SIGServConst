/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.HibernateException;

/**
 *
 * @author Osvaldo Pedro
 */
public class BussinessException extends Exception {

  
    public BussinessException(HibernateException cve) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
