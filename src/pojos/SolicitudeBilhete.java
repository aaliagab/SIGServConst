package pojos;
// Generated 22-may-2022 19:11:40 by Hibernate Tools 4.3.1



/**
 * SolicitudeBilhete generated by hbm2java
 */
public class SolicitudeBilhete  implements java.io.Serializable {


     private Integer id;
     private Bilhete bilhete;
     private Solicitude solicitude;
     private int quantidade;

    public SolicitudeBilhete() {
    }

    public SolicitudeBilhete(Bilhete bilhete, Solicitude solicitude, int quantidade) {
       this.bilhete = bilhete;
       this.solicitude = solicitude;
       this.quantidade = quantidade;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Bilhete getBilhete() {
        return this.bilhete;
    }
    
    public void setBilhete(Bilhete bilhete) {
        this.bilhete = bilhete;
    }
    public Solicitude getSolicitude() {
        return this.solicitude;
    }
    
    public void setSolicitude(Solicitude solicitude) {
        this.solicitude = solicitude;
    }
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }




}


