/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import java.util.Date;
import pojos.Servico;

/**
 *
 * @author Adriel Alejandro
 */
public class ServicosReport {
    private String nome;
    private String tipo;
    private Date data;
    private double iva;
    private double valor;
    private double valor_final;

    public ServicosReport(Servico p) {
        this.nome = p.getNome();
        this.tipo = p.getTipoServico().getNome();
        this.data = p.getDataCriacao();
        this.iva = p.getIva().getPorcetagem();
        this.valor = p.getValor();
        this.valor_final = valor+iva*valor/100;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getData() {
        return data;
    }

    public double getIva() {
        return iva;
    }

    public double getValor() {
        return valor;
    }

    public double getValor_final() {
        return valor_final;
    }

    
    
    
    
}
