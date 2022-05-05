/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Toast;
import dao.BussinessException;
import dao.ServicoDAOImplement;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import pojos.Bairro;
import pojos.TipoServico;
import pojos.Endereco;
import pojos.Servico;
import pojos.Iva;
import pojos.Municipio;
import pojos.Pessoa;
import pojos.Provincia;

/**
 *
 * @author
 */
public class ServicoDialog extends javax.swing.JDialog {

    /**
     * Creates new form ClienteDialog
     */
    private final String busca1 = "Busca por código",
            busca2 = "Busca por nome";
    Main pai;
    Toast msg;
    List<Servico> list_atual;

    List<TipoServico> tipos;
    List<Iva> ivas;
    ServicoDAOImplement daoObject;

    public ServicoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pai = (Main) parent;
        daoObject = pai.control.getServicoDAO();
        this.setLocationRelativeTo(null);
        //Atualizar tabela
        updateJTable();
        AutoCompleteDecorator.decorate(comboIva);
        AutoCompleteDecorator.decorate(comboTipo);
        preecherCombo();

        String toolTip = "Servico";
        jButton2.setToolTipText("Inserir " + toolTip);
        jButton4.setToolTipText("Apagar seleção de " + toolTip);
        botao_actualizar1.setToolTipText("Atualizar " + toolTip);
    }

    public void inicializarForm() {
        nome.setText("");
        valor.setText("");
        descricao.setText("");
    }

    public void preecherCombo() {
        try {

            tipos = pai.control.getTipoServicoDAO().findAll();
            ivas = pai.control.getIvaDAO().findAll();
            for (TipoServico obj : tipos) {
                comboTipo.addItem(obj.getNome());
            }
            for (Iva obj : ivas) {
                comboIva.addItem(obj.getPorcetagem() + " %");
            }
        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }

    public void addRowTableModel(DefaultTableModel model, Servico obj) {
        model.addRow(new Object[]{
            obj.getNome(),
            obj.getTipoServico().getNome(),
            obj.getValor(),
            obj.getIva().getPorcetagem() + " %",
            obj.getDescricao(),
            obj.getDataCriacao()
        });
    }

    public void updateJTable() {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        try {
            list_atual = daoObject.findAll();
            for (Servico obj : list_atual) {
                addRowTableModel(model, obj);
            }
        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }

    public void updateJTableBusca(List<Servico> list) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        for (Servico obj : list) {
            addRowTableModel(model, obj);
        }
    }

    public void editar(Servico obj) {
        if (!nome.getText().equals("")
                && !valor.getText().equals("")
                && !descricao.getText().equals("")) {
            try {
                obj.setIva(comboIva.getSelectedIndex() == 0 ? null : ivas.get(comboIva.getSelectedIndex() - 1));
                obj.setDescricao(descricao.getText());
                obj.setNome(nome.getText());
                obj.setValor(Double.parseDouble(valor.getText()));
                obj.setTipoServico(tipos.get(comboTipo.getSelectedIndex()));
                daoObject.update(obj);

                updateJTable();
                inicializarForm();
                pai.control.messageOperacaoSucesso();
            } catch (BussinessException ex) {
                pai.control.messageErroBussiness(ex);
            }
        } else {
            pai.control.messageFieldEmpty();
        }
    }

    public void focusGained(JTextField jtf, String field) {
        if (jtf.getText().equals(field)) {
            jtf.setText("");
        }
        jtf.setForeground(Color.BLACK);
    }

    public void focusLost(JTextField jtf, String field) {
        if (jtf.getText().equals("")) {
            jtf.setText(field);
        }
        if (jtf.getText().equals(field)) {
            jtf.setForeground(new Color(153, 153, 153));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        busca_nome = new javax.swing.JTextField();
        botao_actualizar1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        descricao = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboIva = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        valor = new javax.swing.JTextField();
        botao_actualizar2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GERENCIAR SERVIÇOS");

        tabela.setAutoCreateRowSorter(true);
        tabela.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Tipo", "Valor", "IVA", "Descrição", "Data de criação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jLabel1.setText("Nome:");

        nome.setName("nome"); // NOI18N

        jButton2.setText("Inserir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Remover");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        busca_nome.setForeground(new java.awt.Color(153, 153, 153));
        busca_nome.setText("Busca por nome");
        busca_nome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                busca_nomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                busca_nomeFocusLost(evt);
            }
        });
        busca_nome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_nomeKeyPressed(evt);
            }
        });

        botao_actualizar1.setText("Editar");
        botao_actualizar1.setToolTipText("Actualizar dados do funcionário");
        botao_actualizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botao_actualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_actualizar1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Descrição:");

        descricao.setName("nome"); // NOI18N

        jLabel11.setText("IVA:");

        comboIva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------" }));

        jLabel13.setText("Tipo:");

        jLabel5.setText("Valor:");

        valor.setName("nome"); // NOI18N
        valor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorKeyTyped(evt);
            }
        });

        botao_actualizar2.setText("Estudo de Factibilidad");
        botao_actualizar2.setToolTipText("Actualizar dados do funcionário");
        botao_actualizar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botao_actualizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_actualizar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(14, 14, 14)
                        .addComponent(jButton4)
                        .addGap(14, 14, 14)
                        .addComponent(botao_actualizar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botao_actualizar2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(busca_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1140, 1140, 1140))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboIva, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(busca_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11)
                    .addComponent(comboIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botao_actualizar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botao_actualizar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botao_actualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_actualizar1ActionPerformed
        int opcao = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja modificar este registro?",
                "CONFIRMAR A MODIFICAÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao == JOptionPane.YES_OPTION) {
            int[] rows = tabela.getSelectedRows();
            if (rows.length > 0) {
                if (rows.length == 1) {
                    Servico obj = list_atual.get(rows[0]);
                    editar(obj);
                } else {
                    pai.control.messageUmaLinha();
                }
            } else {
                pai.control.messageSelecaoEditar();
            }
        }
    }//GEN-LAST:event_botao_actualizar1ActionPerformed

    private void busca_nomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busca_nomeKeyPressed
        List<Servico> list = new ArrayList<>();
        updateJTable();
        boolean flag = false;
        for (Servico list1 : list_atual) {
            if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE
                    && !list1.getNome().toUpperCase().contains(
                            (busca_nome.getText().substring(0, busca_nome.getText().length() - 1)).toUpperCase()
                    )) {
                flag = true;
            } else if (evt.getKeyCode() != KeyEvent.VK_BACK_SPACE
                    && !list1.getNome().toUpperCase().contains(
                            (busca_nome.getText() + evt.getKeyChar()).toUpperCase()
                    )) {
                flag = true;
            } else {
                list.add(list1);
            }
        }
        list_atual = list;
        updateJTableBusca(list);
    }//GEN-LAST:event_busca_nomeKeyPressed

    private void busca_nomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_busca_nomeFocusLost
        focusLost(busca_nome, busca2);
    }//GEN-LAST:event_busca_nomeFocusLost

    private void busca_nomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_busca_nomeFocusGained
        focusGained(busca_nome, busca2);
    }//GEN-LAST:event_busca_nomeFocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int opcao = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja excluir esses registros?",
                "CONFIRMAR REMOÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                int[] rows = tabela.getSelectedRows();
                if (rows.length != 0) {
                    for (int indice : rows) {
                        pai.control.getServicoDAO().delete(list_atual.get(indice).getId());
                    }
                    inicializarForm();
                    updateJTable();
                    pai.control.messageOperacaoSucesso();
                } else {
                    pai.control.messageLinhasExcluir();
                }

            } catch (BussinessException ex) {
                pai.control.messageErroBussiness(ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (nome.getText().equals("")
                || valor.getText().equals("")
                || descricao.getText().equals("")) {
            pai.control.messageFieldEmpty();
        } else {
            try {
                Servico obj = new Servico();
                obj.setDataCriacao(new Date());
                obj.setIva(comboIva.getSelectedIndex() == 0 ? null : ivas.get(comboIva.getSelectedIndex() - 1));
                obj.setDescricao(descricao.getText());
                obj.setNome(nome.getText());
                obj.setValor(Double.parseDouble(valor.getText()));
                obj.setTipoServico(tipos.get(comboTipo.getSelectedIndex()));
                daoObject.save(obj);

                updateJTable();
                inicializarForm();
                pai.control.messageOperacaoSucesso();
            } catch (BussinessException ex) {
                pai.control.messageErroBussiness(ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        Servico obj = list_atual.get(tabela.getSelectedRow());
        nome.setText(obj.getNome());
        valor.setText(obj.getValor()+"");
        descricao.setText(obj.getDescricao());
        comboIva.setSelectedItem(obj.getIva()!=null?obj.getIva().getPorcetagem()+" %":"-------");
        comboTipo.setSelectedItem(obj.getTipoServico().getNome());

    }//GEN-LAST:event_tabelaMouseClicked

    private void valorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorKeyTyped
        //VALIDAÑÇAO DE DECIMALES
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != '.')) {
            evt.consume();
            msg = new Toast("Entrada inválida", 2000);
            msg.showToast();
        }
        if (valor.getText().contains(".") && (car < '0' || car > '9')) {
            evt.consume();
            msg = new Toast("Entrada inválida", 2000);
            msg.showToast();
        }
        if (valor.getText().length() == 0 && car == '.') {
            evt.consume();
            msg = new Toast("Entrada inválida", 2000);
            msg.showToast();
        }
        if (valor.getText().length() > 15) {
            evt.consume();
            msg = new Toast("Comprimento excedido", 2000);
            msg.showToast();
        }
    }//GEN-LAST:event_valorKeyTyped

    private void botao_actualizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_actualizar2ActionPerformed
        int opcao = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja modificar este registro?",
                "CONFIRMAR A MODIFICAÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao == JOptionPane.YES_OPTION) {
            int[] rows = tabela.getSelectedRows();
            if (rows.length > 0) {
                if (rows.length == 1) {
                    Servico obj = list_atual.get(rows[0]);
                    
                    EstudoFactibilidadeDialog form = new EstudoFactibilidadeDialog(pai, true);
                    form.setServico(obj,this);
                    form.setVisible(true);
                    
                } else {
                    pai.control.messageUmaLinha();
                }
            } else {
                pai.control.messageSelecaoEditar();
            }
        }
    }//GEN-LAST:event_botao_actualizar2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServicoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServicoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServicoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServicoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ServicoDialog dialog = new ServicoDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_actualizar1;
    private javax.swing.JButton botao_actualizar2;
    private javax.swing.JTextField busca_nome;
    private javax.swing.JComboBox<String> comboIva;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JTextField descricao;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nome;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField valor;
    // End of variables declaration//GEN-END:variables
}
