/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriel
 */
public class Backup {
    
    public Backup() {
    
    }

    public static void backup(String path_arquivo) {
        String path_mysql = Config.loadConfiguration().mysql.replace("\\", "/");
        path_arquivo = path_arquivo.replace("\\", "/");

        String arquivo = path_arquivo + "/("
                + new Date().toGMTString().split(" ")[0] + "-"
                + new Date().toGMTString().split(" ")[1] + "-"
                + new Date().toGMTString().split(" ")[2]+")" + "backup.sql";
        try {
            String cad = "\"" + path_mysql + "/mysqldump.exe\" --opt --password=" + 
                    Config.loadConfiguration().Password + " --user=" +
                    Config.loadConfiguration().UserName + " " +
                    Config.loadConfiguration().DataBase + " > \"" +
                    arquivo +"\"\n";
            
            File fcopi = new File("copia_seguridad.bat");
            FileWriter fw = new FileWriter(fcopi);
            fw.write(cad, 0, cad.length());
            fw.close();
            Runtime.getRuntime().exec("copia_Seguridad.bat");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void restore(String arquivo) {
        String path_mysql = Config.loadConfiguration().mysql.replace("\\", "/");        
        arquivo = arquivo.replace("\\", "/");
        
        try { 
            String cad = "\"" + path_mysql
                    + "/mysql.exe\" --password=" 
                    + Config.loadConfiguration().Password +
                    " --user=" + Config.loadConfiguration().UserName
                    + " " + Config.loadConfiguration().DataBase +
                    " < \"" + arquivo +"\"\n";
            System.out.println(cad);
            File fcopi = new File("copia_seguridad.bat");
            FileWriter fw = new FileWriter(fcopi);
            fw.write(cad, 0, cad.length());
            fw.close();
            int proceCom = Runtime.getRuntime().exec("copia_Seguridad.bat").waitFor();
            if (proceCom == 0) {
                JOptionPane.showMessageDialog(null, "Backup Restaurado com sucesso !");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao restaurar backup. "
                        + "\n Verifique as configurações ou entre em contato com o suporte !");                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
