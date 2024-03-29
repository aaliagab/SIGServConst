package pojos;
// Generated 22-may-2022 19:11:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private int id;
     private Pessoa pessoa;
     private String num;
     private boolean empresa;
     private String niif;
     private Set<Solicitude> solicitudes = new HashSet<Solicitude>(0);

    public Cliente() {
    }

	
    public Cliente(Pessoa pessoa, String num, boolean empresa) {
        this.pessoa = pessoa;
        this.num = num;
        this.empresa = empresa;
    }
    public Cliente(Pessoa pessoa, String num, boolean empresa, String niif, Set<Solicitude> solicitudes) {
       this.pessoa = pessoa;
       this.num = num;
       this.empresa = empresa;
       this.niif = niif;
       this.solicitudes = solicitudes;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public String getNum() {
        return this.num;
    }
    
    public void setNum(String num) {
        this.num = num;
    }
    public boolean isEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(boolean empresa) {
        this.empresa = empresa;
    }
    public String getNiif() {
        return this.niif;
    }
    
    public void setNiif(String niif) {
        this.niif = niif;
    }
    public Set<Solicitude> getSolicitudes() {
        return this.solicitudes;
    }
    
    public void setSolicitudes(Set<Solicitude> solicitudes) {
        this.solicitudes = solicitudes;
    }




}


