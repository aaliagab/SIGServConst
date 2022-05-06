/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import pojos.SolicitudeServico;


/**
 *
 * @author 
 */
public class SolicitudeServicoReport {
    private String nome;
    private int quantidade;
    private double valor;

    public SolicitudeServicoReport(SolicitudeServico obj) {
        this.nome = obj.getServico().getNome();
        this.quantidade = obj.getQuantidade();
        this.valor = obj.getServico().getValor();
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }
    
    
    
}
