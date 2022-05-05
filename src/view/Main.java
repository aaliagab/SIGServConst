/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Control;
import controller.Toast;
import criptographia.Encriptado;
import dao.BussinessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import pojos.Acesso;
import pojos.Usuario;

/**
 *
 * @author
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Control control;
    private Toast msg;
    private Usuario utilizador_login;

    public Main() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        control = new Control();
        jLabel1.setSize(this.getWidth(), this.getHeight());
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/resource/fondo.jpg");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                setSizeImage();
            }
        });
        inserirAcessos();
        inserirSuperUser();
        LoginDialog obj = new LoginDialog(this, true);
        obj.show();
        this.setIconImage(Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("resource/icon50.png")));
    }

    public void inserirSuperUser() {
        try {
            Usuario superuser = control.getUsuarioDAO().getByUserName("admin");
            Acesso admin = control.getAcessoDAO().getByNome("admin");
            if (superuser == null) {
                if (admin == null) {
                    admin = new Acesso("admin");
                    control.getAcessoDAO().save(admin);
                }
                superuser = new Usuario(admin, "admin", Encriptado.getEncriptadoForte("adminadmin"));
                control.getUsuarioDAO().save(superuser);
            }
        } catch (BussinessException ex) {
            msg = new Toast(ex.getMessage(), 2000);
            msg.showToast();
        }

    }

    public void setUsuario(Usuario utilizador) {
        this.utilizador_login = utilizador;
    }

    public Usuario getUsuario_login() {
        return utilizador_login;
    }

    public void setBemvindo(String text) {
        this.setTitle(this.getTitle() + " SEJA BEM-VIND@ " + text.toUpperCase());
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("resource/icon50.png"));

        return retValue;
    }

    public void setSizeImage() {
        jLabel1.setSize(this.getWidth(), this.getHeight());
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/resource/fondo.jpg");
    }

    public void inserirAcessos() {
        try {
            Acesso admin = control.getAcessoDAO().getByNome("admin");
            Acesso gerente = control.getAcessoDAO().getByNome("gerente");
            Acesso vendedor = control.getAcessoDAO().getByNome("vendedor");
            if (admin == null) {
                admin = new Acesso("admin");
                control.getAcessoDAO().save(admin);
            }
            if (gerente == null) {
                gerente = new Acesso("gerente");
                control.getAcessoDAO().save(gerente);
            }
            if (vendedor == null) {
                vendedor = new Acesso("vendedor");
                control.getAcessoDAO().save(vendedor);
            }
        } catch (BussinessException ex) {
            msg = new Toast(ex.getMessage(), 2000);
            msg.showToast();
        }
    }

    public void menuChefeDpto() {
        //operacoes
        menuSeguranca.setVisible(false);
            
        menuEmigracao.setVisible(true);
        menuDepartamento.setVisible(true);
        //relatorios
        menuImpressoes.setVisible(true);
    }

    public void menuChefeSessao() {
        menuSeguranca.setVisible(false);
            
        menuEmigracao.setVisible(true);
        menuDepartamento.setVisible(false);
        //relatorios
        menuImpressoes.setVisible(true);
    }

    public void menuAdmin() {
        menuSeguranca.setVisible(true);
            
        menuEmigracao.setVisible(false);
        menuDepartamento.setVisible(false);
        //relatorios
        menuImpressoes.setVisible(false);
    }

    public void showMenuAcesso() {
        if (utilizador_login.getAcesso().getNome().equals("gerente")) {
            menuChefeDpto();
        } else if (utilizador_login.getAcesso().getNome().equals("vendedor")) {
            menuChefeSessao();
        } else {
            menuAdmin();
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

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuSeguranca = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuDepartamento = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        menuItemGenero = new javax.swing.JMenuItem();
        menuItemProvincia = new javax.swing.JMenuItem();
        menuItemMunicipio = new javax.swing.JMenuItem();
        menuItemBairros = new javax.swing.JMenuItem();
        menuItemFuncionario = new javax.swing.JMenuItem();
        menuItemFuncionario1 = new javax.swing.JMenuItem();
        menuEmigracao = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        menuItemNatureza = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuItemHorarioManha = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem28 = new javax.swing.JMenuItem();
        menuImpressoes = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMIG");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/fondo.jpg"))); // NOI18N

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_file_submodule_28px.png"))); // NOI18N
        jMenu1.setText("Operações");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        menuSeguranca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_security_configuration_28px_1.png"))); // NOI18N
        menuSeguranca.setText("Segurança");
        menuSeguranca.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_access_28px_1.png"))); // NOI18N
        jMenuItem2.setText("Acessos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuSeguranca.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_access_28px_2.png"))); // NOI18N
        jMenuItem1.setText("Usuários");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuSeguranca.add(jMenuItem1);

        jMenu1.add(menuSeguranca);

        menuDepartamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_people_28px.png"))); // NOI18N
        menuDepartamento.setText("Pessoas");
        menuDepartamento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_male_and_female_28px_1.png"))); // NOI18N
        jMenuItem14.setText("Gênero");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        menuDepartamento.add(jMenuItem14);

        menuItemGenero.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemGenero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_identification_documents_28px.png"))); // NOI18N
        menuItemGenero.setText("Cargos");
        menuItemGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGeneroActionPerformed(evt);
            }
        });
        menuDepartamento.add(menuItemGenero);

        menuItemProvincia.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemProvincia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_australia_map_28px.png"))); // NOI18N
        menuItemProvincia.setText("Províncias");
        menuItemProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemProvinciaActionPerformed(evt);
            }
        });
        menuDepartamento.add(menuItemProvincia);

        menuItemMunicipio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemMunicipio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_libya_map_28px.png"))); // NOI18N
        menuItemMunicipio.setText("Municípios");
        menuItemMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemMunicipioActionPerformed(evt);
            }
        });
        menuDepartamento.add(menuItemMunicipio);

        menuItemBairros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemBairros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_maps_28px.png"))); // NOI18N
        menuItemBairros.setText("Bairros");
        menuItemBairros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBairrosActionPerformed(evt);
            }
        });
        menuDepartamento.add(menuItemBairros);

        menuItemFuncionario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_private_account_male_28px.png"))); // NOI18N
        menuItemFuncionario.setText("Funcionários");
        menuItemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemFuncionarioActionPerformed(evt);
            }
        });
        menuDepartamento.add(menuItemFuncionario);

        menuItemFuncionario1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemFuncionario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_human_head_28px.png"))); // NOI18N
        menuItemFuncionario1.setText("Clientes");
        menuItemFuncionario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemFuncionario1ActionPerformed(evt);
            }
        });
        menuDepartamento.add(menuItemFuncionario1);

        jMenu1.add(menuDepartamento);

        menuEmigracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_service_28px.png"))); // NOI18N
        menuEmigracao.setText("Serviços");
        menuEmigracao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_type_28px.png"))); // NOI18N
        jMenuItem3.setText("Tipos de serviços");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuEmigracao.add(jMenuItem3);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_percentage_28px.png"))); // NOI18N
        jMenuItem12.setText("Iva");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        menuEmigracao.add(jMenuItem12);

        menuItemNatureza.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemNatureza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_check_book_28px_1.png"))); // NOI18N
        menuItemNatureza.setText("Métodos de pagamentos");
        menuItemNatureza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNaturezaActionPerformed(evt);
            }
        });
        menuEmigracao.add(menuItemNatureza);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_service_28px.png"))); // NOI18N
        jMenuItem5.setText("Serviço");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuEmigracao.add(jMenuItem5);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_application_window_28px.png"))); // NOI18N
        jMenuItem4.setText("Solicitude");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuEmigracao.add(jMenuItem4);

        menuItemHorarioManha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemHorarioManha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_money_bag_28px.png"))); // NOI18N
        menuItemHorarioManha.setText("Vendas");
        menuItemHorarioManha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemHorarioManhaActionPerformed(evt);
            }
        });
        menuEmigracao.add(menuItemHorarioManha);

        jMenu1.add(menuEmigracao);
        jMenu1.add(jSeparator1);

        jMenuItem28.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem28.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_update_user_28px.png"))); // NOI18N
        jMenuItem28.setText("Mudar de usuário");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem28);

        jMenuBar1.add(jMenu1);

        menuImpressoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_info_28px.png"))); // NOI18N
        menuImpressoes.setText("Informação");
        menuImpressoes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem15.setText("Geral de Autorização de Residência");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem15);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem16.setText("Geral de Visto de Permanência Temporária");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem16);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem6.setText("Geral de Visto de Trabalho");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem6);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem11.setText("Geral de Cartão de Residência");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem11);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem8.setText("Autorização de Residência em Município");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem9.setText("Visto de Permanência Temporária em Município");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem10.setText("Visto de Trabalho em Município");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem10);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem13.setText("Cartão de Residência em Município");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem13);

        jMenuBar1.add(menuImpressoes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemProvinciaActionPerformed
        ProvinciaDialog obj = new ProvinciaDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_menuItemProvinciaActionPerformed

    private void menuItemMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemMunicipioActionPerformed
        try {
            if (control.getProvinciaDAO().findAll().size() == 0) {
                msg = new Toast("Província deve ser inserida antes", 2000);
                msg.showToast();
            } else {
                MunicipioDialog obj = new MunicipioDialog(this, true);
                obj.setVisible(true);
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemMunicipioActionPerformed

    private void menuItemHorarioManhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemHorarioManhaActionPerformed
        TipoServicoDialog obj = new TipoServicoDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_menuItemHorarioManhaActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed

        LoginDialog l = new LoginDialog(this, true);
        l.show();
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void menuItemGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGeneroActionPerformed
        CargoDialog obj = new CargoDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_menuItemGeneroActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        UsuariosDialog obj = new UsuariosDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AcessoDialog obj = new AcessoDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuItemFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemFuncionarioActionPerformed
        try {
            if (control.getBairroDAO().findAll().size() == 0) {
                msg = new Toast("Bairro deve ser inserido antes.", 2000);
                msg.showToast();
            } else if (control.getCargoDAO().findAll().size() == 0) {
                msg = new Toast("Cargo deve ser inserido antes.", 2000);
                msg.showToast();
            } else {
                FuncionarioDialog obj = new FuncionarioDialog(this, true);
                obj.setVisible(true);
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemFuncionarioActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
//        try {
//            List<ResidentesReport> objs = new ArrayList<>();
//            List<Emigrante> dist = control.getEmigranteDAO().findAll();
//            for (Emigrante obj : dist) {
//                if(obj.getCategoriaMigratoria().getTipoVisto().getNome().equals("Autorização de Residência"))
//                objs.add(new ResidentesReport(obj));
//            }
//            try {
//                JasperReport reporte = null;
//                URL path = this.getClass().getResource("/reports/autorizacao_report_geral.jasper");
//                Map<String, Object> parametros = new HashMap<>();
//                parametros.put("logo", this.getClass().getResource("/resource/logo.jpg").toString());
//                reporte = (JasperReport) JRLoader.loadObject(path);
//                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(objs));
//                JasperViewer jviewer = new JasperViewer(jprint, false);
//                jviewer.setVisible(true);
//                jviewer.setTitle("autorizacao_report_geral");
//
//            } catch (Exception e) {
//                msg = new Toast(e.getMessage(), 2000);
//                msg.showToast();
//            }
//        } catch (BussinessException ex) {
//            control.messageErroBussiness(ex);
//        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
//      try {
//            List<VistoPermanenciaReport> objs = new ArrayList<>();
//            List<Emigrante> dist = control.getEmigranteDAO().findAll();
//            for (Emigrante obj : dist) {
//                if(obj.getCategoriaMigratoria().getTipoVisto().getNome().equals("Visto de Permanência Temporária"))
//                objs.add(new VistoPermanenciaReport(obj));
//            }
//            try {
//                JasperReport reporte = null;
//                URL path = this.getClass().getResource("/reports/permanencia_report_geral.jasper");
//                Map<String, Object> parametros = new HashMap<>();
//                parametros.put("titulo", "VISTO DE PERMANÊNCIA TEMPORÁRIA");
//                parametros.put("logo", this.getClass().getResource("/resource/logo.jpg").toString());
//                reporte = (JasperReport) JRLoader.loadObject(path);
//                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(objs));
//                JasperViewer jviewer = new JasperViewer(jprint, false);
//                jviewer.setVisible(true);
//                jviewer.setTitle("permanencia_report_geral");
//
//            } catch (Exception e) {
//                msg = new Toast(e.getMessage(), 2000);
//                msg.showToast();
//            }
//        } catch (BussinessException ex) {
//            control.messageErroBussiness(ex);
//        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void menuItemNaturezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNaturezaActionPerformed
        MetodoPagamentoDialog obj = new MetodoPagamentoDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_menuItemNaturezaActionPerformed

    private void menuItemBairrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemBairrosActionPerformed
        try {
            if (control.getMunicipioDAO().findAll().size() == 0) {
                msg = new Toast("Município deve ser inserido antes", 2000);
                msg.showToast();
            } else {
                BairroDialog obj = new BairroDialog(this, true);
                obj.setVisible(true);
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemBairrosActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        IvaDialog obj = new IvaDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
//        try {
//            List<VistoPermanenciaReport> objs = new ArrayList<>();
//            List<Emigrante> dist = control.getEmigranteDAO().findAll();
//            for (Emigrante obj : dist) {
//                if(obj.getCategoriaMigratoria().getTipoVisto().getNome().equals("Visto de Trabalho"))
//                objs.add(new VistoPermanenciaReport(obj));
//            }
//            try {
//                JasperReport reporte = null;
//                URL path = this.getClass().getResource("/reports/permanencia_report_geral.jasper");
//                Map<String, Object> parametros = new HashMap<>();
//                parametros.put("titulo", "VISTO DE TRABALHO");
//                parametros.put("logo", this.getClass().getResource("/resource/logo.jpg").toString());
//                reporte = (JasperReport) JRLoader.loadObject(path);
//                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(objs));
//                JasperViewer jviewer = new JasperViewer(jprint, false);
//                jviewer.setVisible(true);
//                jviewer.setTitle("trabalho_report_geral");
//
//            } catch (Exception e) {
//                msg = new Toast(e.getMessage(), 2000);
//                msg.showToast();
//            }
//        } catch (BussinessException ex) {
//            control.messageErroBussiness(ex);
//        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        TipoServicoDialog obj = new TipoServicoDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
//        try {
//            if (control.getPaisDAO().findAll().size() == 0) {
//                msg = new Toast("País deve ser inserido antes", 2000);
//                msg.showToast();
//            } else {
//                NacionalidadeDialog obj = new NacionalidadeDialog(this, true);
//                obj.setVisible(true);
//            }
//        } catch (BussinessException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
//        InstitucaoDialog obj = new InstitucaoDialog(this, true);
//        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
//        try {
//            List<CartaoReport> objs = new ArrayList<>();
//            List<Emigrante> dist = control.getEmigranteDAO().findAll();
//            for (Emigrante obj : dist) {
//                if(obj.getCategoriaMigratoria().getTipoVisto().getNome().equals("Cartão de Residência"))
//                objs.add(new CartaoReport(obj));
//            }
//            try {
//                JasperReport reporte = null;
//                URL path = this.getClass().getResource("/reports/cartao_report_geral.jasper");
//                Map<String, Object> parametros = new HashMap<>();
//                parametros.put("logo", this.getClass().getResource("/resource/logo.jpg").toString());
//                reporte = (JasperReport) JRLoader.loadObject(path);
//                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(objs));
//                JasperViewer jviewer = new JasperViewer(jprint, false);
//                jviewer.setVisible(true);
//                jviewer.setTitle("cartao_report_geral");
//
//            } catch (Exception e) {
//                msg = new Toast(e.getMessage(), 2000);
//                msg.showToast();
//            }
//        } catch (BussinessException ex) {
//            control.messageErroBussiness(ex);
//        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
//        try {
//            if (control.getMunicipioDAO().findAll().size() == 0) {
//                msg = new Toast("Município deve ser inserido antes", 2000);
//                msg.showToast();
//            } else {
//                MunicipioReportDialog obj = new MunicipioReportDialog(this, false);
//                obj.tipoAutoPermTrabCart(true, false, false, false);
//                obj.setVisible(true);
//            }
//        } catch (BussinessException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
//        try {
//            if (control.getMunicipioDAO().findAll().size() == 0) {
//                msg = new Toast("Município deve ser inserido antes", 2000);
//                msg.showToast();
//            } else {
//                MunicipioReportDialog obj = new MunicipioReportDialog(this, false);
//                obj.tipoAutoPermTrabCart(false, true, false, false);
//                obj.setVisible(true);
//            }
//        } catch (BussinessException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
//        try {
//            if (control.getMunicipioDAO().findAll().size() == 0) {
//                msg = new Toast("Município deve ser inserido antes", 2000);
//                msg.showToast();
//            } else {
//                MunicipioReportDialog obj = new MunicipioReportDialog(this, false);
//                obj.tipoAutoPermTrabCart(false, false, true, false);
//                obj.setVisible(true);
//            }
//        } catch (BussinessException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
//        try {
//            if (control.getMunicipioDAO().findAll().size() == 0) {
//                msg = new Toast("Município deve ser inserido antes", 2000);
//                msg.showToast();
//            } else {
//                MunicipioReportDialog obj = new MunicipioReportDialog(this, false);
//                obj.tipoAutoPermTrabCart(false, false, false, true);
//                obj.setVisible(true);
//            }
//        } catch (BussinessException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        GeneroDialog obj = new GeneroDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void menuItemFuncionario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemFuncionario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItemFuncionario1ActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (Exception e) {
            Toast msg2 = new Toast(e.getMessage(), 2000);
            msg2.showToast();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuDepartamento;
    private javax.swing.JMenu menuEmigracao;
    private javax.swing.JMenu menuImpressoes;
    private javax.swing.JMenuItem menuItemBairros;
    private javax.swing.JMenuItem menuItemFuncionario;
    private javax.swing.JMenuItem menuItemFuncionario1;
    private javax.swing.JMenuItem menuItemGenero;
    private javax.swing.JMenuItem menuItemHorarioManha;
    private javax.swing.JMenuItem menuItemMunicipio;
    private javax.swing.JMenuItem menuItemNatureza;
    private javax.swing.JMenuItem menuItemProvincia;
    private javax.swing.JMenu menuSeguranca;
    // End of variables declaration//GEN-END:variables
}
