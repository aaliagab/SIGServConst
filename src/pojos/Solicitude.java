package pojos;
// Generated 29-abr-2022 10:39:04 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Solicitude generated by hbm2java
 */
public class Solicitude  implements java.io.Serializable {


     private Integer id;
     private Cliente cliente;
     private EstadoSolicitude estadoSolicitude;
     private String num;
     private Date data;
     private Set<SolicitudeServico> solicitudeServicos = new HashSet<SolicitudeServico>(0);
     private Set<Venda> vendas = new HashSet<Venda>(0);

    public Solicitude() {
    }

	
    public Solicitude(Cliente cliente, EstadoSolicitude estadoSolicitude, Date data) {
        this.cliente = cliente;
        this.estadoSolicitude = estadoSolicitude;
        this.data = data;
    }
    public Solicitude(Cliente cliente, EstadoSolicitude estadoSolicitude, String num, Date data, Set<SolicitudeServico> solicitudeServicos, Set<Venda> vendas) {
       this.cliente = cliente;
       this.estadoSolicitude = estadoSolicitude;
       this.num = num;
       this.data = data;
       this.solicitudeServicos = solicitudeServicos;
       this.vendas = vendas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public EstadoSolicitude getEstadoSolicitude() {
        return this.estadoSolicitude;
    }
    
    public void setEstadoSolicitude(EstadoSolicitude estadoSolicitude) {
        this.estadoSolicitude = estadoSolicitude;
    }
    public String getNum() {
        return this.num;
    }
    
    public void setNum(String num) {
        this.num = num;
    }
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }
    public Set<SolicitudeServico> getSolicitudeServicos() {
        return this.solicitudeServicos;
    }
    
    public void setSolicitudeServicos(Set<SolicitudeServico> solicitudeServicos) {
        this.solicitudeServicos = solicitudeServicos;
    }
    public Set<Venda> getVendas() {
        return this.vendas;
    }
    
    public void setVendas(Set<Venda> vendas) {
        this.vendas = vendas;
    }




}


