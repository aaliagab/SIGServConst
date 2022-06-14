/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;
import java.io.*;
/**
 *
 * @author Yunieski
 */
public class Config {
    //public static String filename = "C:\\Conf\\config.dat";
    public static String filename = "config.dat";

    public Config() {
    }
    
    public static void saveConfiguration(MySQLConnection mysqlconnection)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mysqlconnection);
            oos.close();
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } 
    }
    
    public static MySQLConnection loadConfiguration()
    {
        MySQLConnection mysqlconnection = new MySQLConnection();
        try {
            if (ifFileConfigurationExist()) {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                mysqlconnection = (MySQLConnection) ois.readObject();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return mysqlconnection;
    }
    
    public static boolean ifFileConfigurationExist()
    {
        File file = new File(filename);
        return file.exists();
    }
}
