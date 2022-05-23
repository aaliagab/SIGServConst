package pojos;
// Generated 22-may-2022 19:11:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cargo generated by hbm2java
 */
public class Cargo  implements java.io.Serializable {


     private Integer id;
     private String nome;
     private Set<Funcionario> funcionarios = new HashSet<Funcionario>(0);

    public Cargo() {
    }

	
    public Cargo(String nome) {
        this.nome = nome;
    }
    public Cargo(String nome, Set<Funcionario> funcionarios) {
       this.nome = nome;
       this.funcionarios = funcionarios;
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
    public Set<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }
    
    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }




}


