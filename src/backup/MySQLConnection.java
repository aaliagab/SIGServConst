/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;
import java.io.Serializable;
/**
 *
 * @author yunieski
 */
public class MySQLConnection implements Serializable 
{
    String IP = "";
    String Port = "";
    String UserName = "";
    String Password = "";
    String DataBase = "";
    String mysql = "";

    public MySQLConnection() 
    {
        this.IP = "127.0.0.1";
        this.Port = "3306";
        this.UserName = "root";
        this.Password = "";
        this.DataBase = "DB";
        this.mysql = "C:\\xampp\\mysql\\bin";
    }

    public MySQLConnection(String ip, String port, String username, String password, String db,String mysql) 
    {
        this.IP = ip;
        this.Port = port;
        this.UserName = username;
        this.Password = password;
        this.DataBase = db;
        this.mysql = mysql;
    }
    
    public MySQLConnection(MySQLConnection mysqlconnection) 
    {
        this.IP = mysqlconnection.IP;
        this.Port = mysqlconnection.Port;
        this.UserName = mysqlconnection.UserName;
        this.Password = mysqlconnection.Password;
        this.DataBase = mysqlconnection.DataBase;
        this.mysql = mysqlconnection.mysql;
    }
    
    public String getURL()
    {
        return "jdbc:mysql://"+this.IP+":"+this.Port+"/"+DataBase;
    }
}