package pojos;
// Generated 29-abr-2022 10:39:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * EstadoSolicitude generated by hbm2java
 */
public class EstadoSolicitude  implements java.io.Serializable {


     private Integer id;
     private String nome;
     private Set<Solicitude> solicitudes = new HashSet<Solicitude>(0);

    public EstadoSolicitude() {
    }

	
    public EstadoSolicitude(String nome) {
        this.nome = nome;
    }
    public EstadoSolicitude(String nome, Set<Solicitude> solicitudes) {
       this.nome = nome;
       this.solicitudes = solicitudes;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Set<Solicitude> getSolicitudes() {
        return this.solicitudes;
    }
    
    public void setSolicitudes(Set<Solicitude> solicitudes) {
        this.solicitudes = solicitudes;
    }




}

