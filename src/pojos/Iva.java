package pojos;
// Generated 29-abr-2022 10:39:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Iva generated by hbm2java
 */
public class Iva  implements java.io.Serializable {


     private Integer id;
     private double porcetagem;
     private Set<Servico> servicos = new HashSet<Servico>(0);

    public Iva() {
    }

	
    public Iva(double porcetagem) {
        this.porcetagem = porcetagem;
    }
    public Iva(double porcetagem, Set<Servico> servicos) {
       this.porcetagem = porcetagem;
       this.servicos = servicos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public double getPorcetagem() {
        return this.porcetagem;
    }
    
    public void setPorcetagem(double porcetagem) {
        this.porcetagem = porcetagem;
    }
    public Set<Servico> getServicos() {
        return this.servicos;
    }
    
    public void setServicos(Set<Servico> servicos) {
        this.servicos = servicos;
    }




}


