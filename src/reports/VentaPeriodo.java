/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import controller.Control;
import dao.BussinessException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pojos.Funcionario;
import pojos.Venda;

/**
 *
 * @author 
 */
public class VentaPeriodo {
    private List<VendaReport> vendas_ordenadas;
    private Control control;

    public VentaPeriodo(Date ini, Date fim) {
        control = new Control();
        try {
            List<Venda> vendas = control.getVendaDAO().findAll();
            vendas_ordenadas = new ArrayList<>();
            for (Venda venda : vendas) {
                if (venda.getSolicitude().getData().compareTo(ini)>=0 &&
                        venda.getSolicitude().getData().compareTo(fim)<=0) {
                    vendas_ordenadas.add(new VendaReport(venda));
                }
            }

        } catch (BussinessException ex) {
            control.messageErroBussiness(ex);
        }
    }

    public List<VendaReport> getVenda_ordenadas() {
        return vendas_ordenadas;
    }
    
    
}
