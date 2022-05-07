/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import controller.Control;
import dao.BussinessException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import pojos.Venda;

/**
 *
 * @author 
 */
public class OrdenarFuncionariosVentaAnoAtual {
    private List<VendaReport> vendas_ordenadas;
    private Control control;

    public OrdenarFuncionariosVentaAnoAtual() {        
        control = new Control();
        try {
            List<Venda> vendas = control.getVendaDAO().findAll();
            vendas_ordenadas = new ArrayList<>();
            for (Venda venda : vendas) {
                if (venda.getSolicitude().getData().getYear() == new Date().getYear()) {
                    vendas_ordenadas.add(new VendaReport(venda));
                }
            }
            Collections.sort(vendas_ordenadas, new Comparator<VendaReport>() {
                @Override
                public int compare(VendaReport v1, VendaReport v2) {
                    return new Double(v2.getValor()).compareTo(new Double(v1.getValor()));
                }
            });

        } catch (BussinessException ex) {
            control.messageErroBussiness(ex);
        }
    }

    public List<VendaReport> getVenda_ordenadas() {
        return vendas_ordenadas;
    }
}
