package pojos;
// Generated 22-may-2022 19:11:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Tipo generated by hbm2java
 */
public class Tipo  implements java.io.Serializable {


     private Integer id;
     private String nome;
     private Set<Bilhete> bilhetes = new HashSet<Bilhete>(0);

    public Tipo() {
    }

	
    public Tipo(String nome) {
        this.nome = nome;
    }
    public Tipo(String nome, Set<Bilhete> bilhetes) {
       this.nome = nome;
       this.bilhetes = bilhetes;
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
    public Set<Bilhete> getBilhetes() {
        return this.bilhetes;
    }
    
    public void setBilhetes(Set<Bilhete> bilhetes) {
        this.bilhetes = bilhetes;
    }




}

