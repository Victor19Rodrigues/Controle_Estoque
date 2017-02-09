package Limite;

import Controle.ControlePrincipal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class LimitePrincipal extends JFrame {
    
    ControlePrincipal pCtrPrincipal;

    //Frame janela principal
    JFrame janela;

    //MenuBar com os menus
    JMenuBar menu_principal;

    //Menus
    JMenu menuMercadorias, menuClientes, menuFaturamento, menuVendas;

    //Submenus
    JMenuItem cadastroMercadoria, consultarMercadoria, atualizaMercadoria, consultarCliente, cadastrarCliente, clienteVendas, periodoVendas,
            produtoVendas, cadastraVendas, sair, exibirEstoque;
    
    public LimitePrincipal(ControlePrincipal pCtrControlePrincipal) {
        
        janela = new JFrame();

        //Cria o MenuBar e seta ele no frame
        menu_principal = new JMenuBar();
        menu_principal.setBackground(new Color(214, 155, 27));
        janela.setJMenuBar(menu_principal);

        //Primeiro botão do menu
        menuMercadorias = new JMenu("Mercadorias");
        menu_principal.add(menuMercadorias);
        menuMercadorias.setForeground(Color.white);

        //Submenus ao menu Mercadorias
        //Cadastro de Mercadorias
        cadastroMercadoria = new JMenuItem("Cadastrar");
        cadastroMercadoria.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pCtrControlePrincipal.getCtrMercadoria().cadastraMercadoria();
                } catch (Exception ex) {
                    Logger.getLogger(LimitePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            
        });
        menuMercadorias.add(cadastroMercadoria);

        //Consulta de Mercadorias
        consultarMercadoria = new JMenuItem("Consultar Mercadorias");
        consultarMercadoria.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pCtrControlePrincipal.getCtrMercadoria().consultaMercadoria();
                } catch (Exception ex) {
                    Logger.getLogger(LimitePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        menuMercadorias.add(consultarMercadoria);

        //Atualiza Mercadorias
        atualizaMercadoria = new JMenuItem("Atualizar Mercadorias");
        atualizaMercadoria.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pCtrControlePrincipal.getCtrMercadoria().atualizaMercadoria();
                } catch (Exception ex) {
                    Logger.getLogger(LimitePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        menuMercadorias.add(atualizaMercadoria);
        menuMercadorias.addSeparator();

        //Exibir Estoque
        exibirEstoque = new JMenuItem("Exibir Estoque");
        exibirEstoque.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pCtrControlePrincipal.getCtrMercadoria().exibeEstoque();
                } catch (Exception ex) {
                    Logger.getLogger(LimitePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        menuMercadorias.add(exibirEstoque);

        //Sair
        sair = new JMenuItem("Sair");
        sair.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.exit(0);
                } catch (Exception ex) {
                    Logger.getLogger(LimitePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        menuMercadorias.addSeparator();
        menuMercadorias.add(sair);

        //Segundo botão do menu
        menuClientes = new JMenu("Clientes");
        menu_principal.add(menuClientes);
        menuClientes.setForeground(Color.white);

        //Submenu ao menu cliente
        cadastrarCliente = new JMenuItem("Cadastrar");
        cadastrarCliente.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pCtrControlePrincipal.getCtrCliente().cadastraCliente();
                } catch (Exception ex) {
                    Logger.getLogger(LimitePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        menuClientes.add(cadastrarCliente);

        //Submenu ao menu cliente
        consultarCliente = new JMenuItem("Consultar");
        consultarCliente.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pCtrControlePrincipal.getCtrCliente().consultaCliente();
                } catch (Exception ex) {
                    Logger.getLogger(LimitePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
            }
            
        });
        menuClientes.add(consultarCliente);

        //Terceiro botão do menu
        menuFaturamento = new JMenu("Faturamento");
        menu_principal.add(menuFaturamento);
        menuFaturamento.setForeground(Color.white);

        //Submenu ao menu faturamento
        clienteVendas = new JMenuItem("Por cliente");
        menuFaturamento.add(clienteVendas);
        
        periodoVendas = new JMenuItem("Por período");
        menuFaturamento.add(periodoVendas);
        
        produtoVendas = new JMenuItem("Por produto");
        menuFaturamento.add(produtoVendas);

        ///Quarto botão do menu
        menuVendas = new JMenu("Vendas");
        menu_principal.add(menuVendas);
        menuVendas.setForeground(Color.white);

        //Submeno ao menu Vendas
        cadastraVendas = new JMenuItem("Cadastrar");
        cadastraVendas.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                pCtrControlePrincipal.getCtrVenda().CriaVenda();
            }
            
        });
        menuVendas.add(cadastraVendas);

        //Seta a imagem da janela
        ImageIcon icon = new ImageIcon("/home/victor/Downloads/Trabalho_Final/src/Imagens/logo.jpg");
        JLabel label = new JLabel(icon);
        
        janela.add(label);
        janela.getContentPane().setBackground(new Color(255, 255, 255));
        janela.setSize(300, 200);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setVisible(true);
        
    }
    
}
