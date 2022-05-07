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
import pojos.EstadoSolicitude;
import pojos.Servico;
import pojos.Usuario;
import reports.OrdenarFuncionariosVentaAnoAtual;
import reports.OrdenarFuncionariosVentaMes;
import reports.ServicosReport;
import reports.VendaReport;

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
        inserirEstados();
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
    
    public void inserirEstados() {
        try {
            EstadoSolicitude novo = control.getEstadoSolicitudeDAO().getByNome("Novo");
            EstadoSolicitude processo = control.getEstadoSolicitudeDAO().getByNome("Em processo");
            EstadoSolicitude realizado = control.getEstadoSolicitudeDAO().getByNome("Realizado");
            if (novo == null) {
                novo = new EstadoSolicitude("Novo");
                control.getEstadoSolicitudeDAO().save(novo);
            }
            if (processo == null) {
                processo = new EstadoSolicitude("Em processo");
                control.getEstadoSolicitudeDAO().save(processo);
            }
            if (realizado == null) {
                realizado = new EstadoSolicitude("Realizado");
                control.getEstadoSolicitudeDAO().save(realizado);
            }
        } catch (BussinessException ex) {
            msg = new Toast(ex.getMessage(), 2000);
            msg.showToast();
        }
    }

    public void menuGerente() {
        //operacoes
        menuSeguranca.setVisible(false);
            
        menuServicos.setVisible(true);
            menuItemCriarPed.setVisible(true);
            menuItemIva.setVisible(true);
            menuItemMetodos.setVisible(true);
            menuItemPedNaoVend.setVisible(true);
            menuItemServicos.setVisible(true);
            menuItemTipo.setVisible(true);
            menuItemVendidos.setVisible(true);
        menuPessoas.setVisible(true);
            menuItemBairros.setVisible(true);
            menuItemCargo.setVisible(true);
            menuItemCliente.setVisible(true);
            menuItemFuncionario.setVisible(true);
            menuItemMunicipio.setVisible(true);
            menuItemGenero.setVisible(true);
            menuItemProvincia.setVisible(true);
        //relatorios
        menuImpressoes.setVisible(true);
    }

    public void menuVendedor() {
        menuSeguranca.setVisible(false);
            
        menuServicos.setVisible(true);
            menuItemCriarPed.setVisible(true);
            menuItemIva.setVisible(false);
            menuItemMetodos.setVisible(true);
            menuItemPedNaoVend.setVisible(true);
            menuItemServicos.setVisible(false);
            menuItemTipo.setVisible(false);
            menuItemVendidos.setVisible(true);
        menuPessoas.setVisible(true);
            menuItemBairros.setVisible(true);
            menuItemCargo.setVisible(false);
            menuItemCliente.setVisible(true);
            menuItemFuncionario.setVisible(false);
            menuItemMunicipio.setVisible(true);
            menuItemGenero.setVisible(false);
            menuItemProvincia.setVisible(true);
        //relatorios
        menuImpressoes.setVisible(true);
    }

    public void menuAdmin() {
        menuSeguranca.setVisible(true);
            
        menuServicos.setVisible(true);
            menuItemCriarPed.setVisible(false);
            menuItemIva.setVisible(true);
            menuItemMetodos.setVisible(true);
            menuItemPedNaoVend.setVisible(false);
            menuItemServicos.setVisible(false);
            menuItemTipo.setVisible(true);
            menuItemVendidos.setVisible(false);
        menuPessoas.setVisible(true);
            menuItemBairros.setVisible(true);
            menuItemCargo.setVisible(true);
            menuItemCliente.setVisible(false);
            menuItemFuncionario.setVisible(true);
            menuItemMunicipio.setVisible(true);
            menuItemGenero.setVisible(false);
            menuItemProvincia.setVisible(true);
        //relatorios
        menuImpressoes.setVisible(false);
    }

    public void showMenuAcesso() {
        if (utilizador_login.getAcesso().getNome().equals("gerente")) {
            menuGerente();
        } else if (utilizador_login.getAcesso().getNome().equals("vendedor")) {
            menuVendedor();
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
        menuPessoas = new javax.swing.JMenu();
        menuItemGenero = new javax.swing.JMenuItem();
        menuItemCargo = new javax.swing.JMenuItem();
        menuItemProvincia = new javax.swing.JMenuItem();
        menuItemMunicipio = new javax.swing.JMenuItem();
        menuItemBairros = new javax.swing.JMenuItem();
        menuItemFuncionario = new javax.swing.JMenuItem();
        menuItemCliente = new javax.swing.JMenuItem();
        menuServicos = new javax.swing.JMenu();
        menuItemTipo = new javax.swing.JMenuItem();
        menuItemIva = new javax.swing.JMenuItem();
        menuItemMetodos = new javax.swing.JMenuItem();
        menuItemServicos = new javax.swing.JMenuItem();
        menuItemCriarPed = new javax.swing.JMenuItem();
        menuItemPedNaoVend = new javax.swing.JMenuItem();
        menuItemVendidos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem28 = new javax.swing.JMenuItem();
        menuImpressoes = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIGServConst");

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

        menuPessoas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_people_28px.png"))); // NOI18N
        menuPessoas.setText("Pessoas");
        menuPessoas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        menuItemGenero.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemGenero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_male_and_female_28px_1.png"))); // NOI18N
        menuItemGenero.setText("Gênero");
        menuItemGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemGeneroActionPerformed(evt);
            }
        });
        menuPessoas.add(menuItemGenero);

        menuItemCargo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_identification_documents_28px.png"))); // NOI18N
        menuItemCargo.setText("Cargos");
        menuItemCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCargoActionPerformed(evt);
            }
        });
        menuPessoas.add(menuItemCargo);

        menuItemProvincia.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemProvincia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_australia_map_28px.png"))); // NOI18N
        menuItemProvincia.setText("Províncias");
        menuItemProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemProvinciaActionPerformed(evt);
            }
        });
        menuPessoas.add(menuItemProvincia);

        menuItemMunicipio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemMunicipio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_libya_map_28px.png"))); // NOI18N
        menuItemMunicipio.setText("Municípios");
        menuItemMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemMunicipioActionPerformed(evt);
            }
        });
        menuPessoas.add(menuItemMunicipio);

        menuItemBairros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemBairros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_maps_28px.png"))); // NOI18N
        menuItemBairros.setText("Bairros");
        menuItemBairros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemBairrosActionPerformed(evt);
            }
        });
        menuPessoas.add(menuItemBairros);

        menuItemFuncionario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_private_account_male_28px.png"))); // NOI18N
        menuItemFuncionario.setText("Funcionários");
        menuItemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemFuncionarioActionPerformed(evt);
            }
        });
        menuPessoas.add(menuItemFuncionario);

        menuItemCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuItemCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_human_head_28px.png"))); // NOI18N
        menuItemCliente.setText("Clientes");
        menuItemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemClienteActionPerformed(evt);
            }
        });
        menuPessoas.add(menuItemCliente);

        jMenu1.add(menuPessoas);

        menuServicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_service_28px.png"))); // NOI18N
        menuServicos.setText("Serviços");
        menuServicos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        menuItemTipo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_type_28px.png"))); // NOI18N
        menuItemTipo.setText("Tipos de serviços");
        menuItemTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemTipoActionPerformed(evt);
            }
        });
        menuServicos.add(menuItemTipo);

        menuItemIva.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemIva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_percentage_28px.png"))); // NOI18N
        menuItemIva.setText("Iva");
        menuItemIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemIvaActionPerformed(evt);
            }
        });
        menuServicos.add(menuItemIva);

        menuItemMetodos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemMetodos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_check_book_28px_1.png"))); // NOI18N
        menuItemMetodos.setText("Métodos de pagamentos");
        menuItemMetodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemMetodosActionPerformed(evt);
            }
        });
        menuServicos.add(menuItemMetodos);

        menuItemServicos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemServicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_service_28px.png"))); // NOI18N
        menuItemServicos.setText("Serviços");
        menuItemServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemServicosActionPerformed(evt);
            }
        });
        menuServicos.add(menuItemServicos);

        menuItemCriarPed.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemCriarPed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_application_window_28px.png"))); // NOI18N
        menuItemCriarPed.setText("Criar pedidos");
        menuItemCriarPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCriarPedActionPerformed(evt);
            }
        });
        menuServicos.add(menuItemCriarPed);

        menuItemPedNaoVend.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemPedNaoVend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_application_window_28px.png"))); // NOI18N
        menuItemPedNaoVend.setText("Pedidos não vendidos");
        menuItemPedNaoVend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPedNaoVendActionPerformed(evt);
            }
        });
        menuServicos.add(menuItemPedNaoVend);

        menuItemVendidos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemVendidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_money_bag_28px.png"))); // NOI18N
        menuItemVendidos.setText("Pedidos vendidos");
        menuItemVendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemVendidosActionPerformed(evt);
            }
        });
        menuServicos.add(menuItemVendidos);

        jMenu1.add(menuServicos);
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

        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem21.setText("Serviços");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem21);

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem17.setText("Vendas Totais de Funcionários no Mês");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem17);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem18.setText("Vendas Totais de Funcionários no Ano");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem18);

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem19.setText("Vendas de Funcionário por período");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem19);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_pdf_28px_3.png"))); // NOI18N
        jMenuItem20.setText("Vendas no período");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        menuImpressoes.add(jMenuItem20);

        jMenuBar1.add(menuImpressoes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void menuItemVendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemVendidosActionPerformed
        try {
            if (control.getVendaDAO().findAll().size() == 0) {
                msg = new Toast("Venda deve ser feita antes", 2000);
                msg.showToast();
            } else {
                PedidoVendidoDialog obj = new PedidoVendidoDialog(this, false);
                obj.setVisible(true);
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemVendidosActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed

        LoginDialog l = new LoginDialog(this, true);
        l.show();
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void menuItemCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCargoActionPerformed
        CargoDialog obj = new CargoDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_menuItemCargoActionPerformed

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

    private void menuItemMetodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemMetodosActionPerformed
        MetodoPagamentoDialog obj = new MetodoPagamentoDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_menuItemMetodosActionPerformed

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

    private void menuItemIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemIvaActionPerformed
        IvaDialog obj = new IvaDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_menuItemIvaActionPerformed

    private void menuItemTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemTipoActionPerformed
        TipoServicoDialog obj = new TipoServicoDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_menuItemTipoActionPerformed

    private void menuItemCriarPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCriarPedActionPerformed
        try {
            if (control.getClienteDAO().findAll().size() == 0) {
                msg = new Toast("Cliente deve ser inserido antes", 2000);
                msg.showToast();
            } else if (control.getServicoDAO().findAll().size() == 0) {
                msg = new Toast("Serviço deve ser inserido antes", 2000);
                msg.showToast();
            } else {
                SolicitudeDialog obj = new SolicitudeDialog(this, false);
                obj.setVisible(true);
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemCriarPedActionPerformed

    private void menuItemServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemServicosActionPerformed
        try {
            if (control.getTipoServicoDAO().findAll().size() == 0) {
                msg = new Toast("Tipo de serviço deve ser inserido antes", 2000);
                msg.showToast();
            } else {
                ServicoDialog obj = new ServicoDialog(this, false);
                obj.setVisible(true);
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemServicosActionPerformed

    private void menuItemGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemGeneroActionPerformed
        GeneroDialog obj = new GeneroDialog(this, true);
        obj.setVisible(true);
    }//GEN-LAST:event_menuItemGeneroActionPerformed

    private void menuItemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemClienteActionPerformed
        try {
            if (control.getBairroDAO().findAll().size() == 0) {
                msg = new Toast("Bairro deve ser inserido antes.", 2000);
                msg.showToast();
            } else if (control.getGeneroDAO().findAll().size() == 0) {
                msg = new Toast("Género deve ser inserido antes.", 2000);
                msg.showToast();
            } else {
                ClienteDialog obj = new ClienteDialog(this, true);
                obj.setVisible(true);
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemClienteActionPerformed

    private void menuItemPedNaoVendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPedNaoVendActionPerformed
        try {
            if (control.getEstadoSolicitudeDAO().findAll().size() == 0) {
                msg = new Toast("Estado de Pedido deve ser inserido antes.", 2000);
                msg.showToast();
            } else {
                PedidoNaoVendidoDialog obj = new PedidoNaoVendidoDialog(this, false);
                obj.setVisible(true);
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemPedNaoVendActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        List<VendaReport> objs = new ArrayList<>();
        OrdenarFuncionariosVentaMes obj = new OrdenarFuncionariosVentaMes();
        objs = obj.getVenda_ordenadas();
        try {
            JasperReport reporte = null;
            URL path = this.getClass().getResource("/reports/func_vendas_mes_report.jasper");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("logo", this.getClass().getResource("/resource/logo.jpeg").toString());
            reporte = (JasperReport) JRLoader.loadObject(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(objs));
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setVisible(true);
            jviewer.setTitle("func_vendas_mes_report");

        } catch (Exception e) {
            msg = new Toast(e.getMessage(), 2000);
            msg.showToast();
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        List<VendaReport> objs = new ArrayList<>();
        OrdenarFuncionariosVentaAnoAtual obj = new OrdenarFuncionariosVentaAnoAtual();
        objs = obj.getVenda_ordenadas();
        try {
            JasperReport reporte = null;
            URL path = this.getClass().getResource("/reports/func_vendas_ano_report.jasper");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("logo", this.getClass().getResource("/resource/logo.jpeg").toString());
            parametros.put("ano", (((new Date().getYear())+1900)+"").substring((((new Date().getYear())+1900)+"").length()-4));
            reporte = (JasperReport) JRLoader.loadObject(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(objs));
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setVisible(true);
            jviewer.setTitle("func_vendas_ano_report");

        } catch (Exception e) {
            msg = new Toast(e.getMessage(), 2000);
            msg.showToast();
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        try {
            if (control.getFuncionarioDAO().findAll().size() == 0) {
                msg = new Toast("Funcionário deve ser inserido antes.", 2000);
                msg.showToast();
            } else {
                VendaFuncionarioPeriodo obj = new VendaFuncionarioPeriodo(this, false);
                obj.setVisible(true);
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        VendaPeriodo obj = new VendaPeriodo(this, false);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        try {
            List<ServicosReport> objs = new ArrayList<>();
            List<Servico> produtos = control.getServicoDAO().findAll();
            for (Servico produto : produtos) {
                objs.add(new ServicosReport(produto));
            }
            try {
                JasperReport reporte = null;
                URL path = this.getClass().getResource("/reports/servico_report.jasper");
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("logo", this.getClass().getResource("/resource/logo.jpeg").toString());
                reporte = (JasperReport) JRLoader.loadObject(path);
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(objs));
                JasperViewer jviewer = new JasperViewer(jprint, false);
                jviewer.setVisible(true);
                jviewer.setTitle("servicos_report");

            } catch (Exception e) {
                msg = new Toast(e.getMessage(), 2000);
                msg.showToast();
            }
        } catch (BussinessException ex) {
            control.messageErroBussiness(ex);
        }

    }//GEN-LAST:event_jMenuItem21ActionPerformed

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
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
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
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuImpressoes;
    private javax.swing.JMenuItem menuItemBairros;
    private javax.swing.JMenuItem menuItemCargo;
    private javax.swing.JMenuItem menuItemCliente;
    private javax.swing.JMenuItem menuItemCriarPed;
    private javax.swing.JMenuItem menuItemFuncionario;
    private javax.swing.JMenuItem menuItemGenero;
    private javax.swing.JMenuItem menuItemIva;
    private javax.swing.JMenuItem menuItemMetodos;
    private javax.swing.JMenuItem menuItemMunicipio;
    private javax.swing.JMenuItem menuItemPedNaoVend;
    private javax.swing.JMenuItem menuItemProvincia;
    private javax.swing.JMenuItem menuItemServicos;
    private javax.swing.JMenuItem menuItemTipo;
    private javax.swing.JMenuItem menuItemVendidos;
    private javax.swing.JMenu menuPessoas;
    private javax.swing.JMenu menuSeguranca;
    private javax.swing.JMenu menuServicos;
    // End of variables declaration//GEN-END:variables
}
