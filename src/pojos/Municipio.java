package pojos;
// Generated 22-may-2022 19:11:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Municipio generated by hbm2java
 */
public class Municipio  implements java.io.Serializable {


     private Integer id;
     private Provincia provincia;
     private String nome;
     private Set<Bairro> bairros = new HashSet<Bairro>(0);

    public Municipio() {
    }

	
    public Municipio(Provincia provincia, String nome) {
        this.provincia = provincia;
        this.nome = nome;
    }
    public Municipio(Provincia provincia, String nome, Set<Bairro> bairros) {
       this.provincia = provincia;
       this.nome = nome;
       this.bairros = bairros;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Provincia getProvincia() {
        return this.provincia;
    }
    
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Set<Bairro> getBairros() {
        return this.bairros;
    }
    
    public void setBairros(Set<Bairro> bairros) {
        this.bairros = bairros;
    }




}


