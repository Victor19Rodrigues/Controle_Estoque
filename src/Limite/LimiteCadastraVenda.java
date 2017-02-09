package Limite;

import Controle.ControleVenda;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import trabalho_final.Cliente;
import trabalho_final.Mercadoria;

public class LimiteCadastraVenda extends LimiteUtil {

    ControleVenda ctrvenda;
    JPanel painel, painel2, painel3, panelEsquerda, panelDireita, panelData;
    JButton btnprox, btnCarrinho;
    JLabel lblcpf, lblcod, lblquant, lblNome, lblData, lblEnd, lblProd;
    JTextField txtquant, txtdata, txtcod, txtData;
    JComboBox cbcpf, cbNomeProd;
    JFrame janela, janela2, janela3;
    int ID = 0;

    public LimiteCadastraVenda(ControleVenda pCtrVenda) {

        ctrvenda = pCtrVenda;

        janela = new JFrame("Cliente");

        //Label
        lblcpf = new JLabel("CPF do cliente: ");

        //ComboBox
        cbcpf = new JComboBox();

        for (int i = 0; i < ctrvenda.getCtrprin().getCtrCliente().getListaClientes().size(); i++) {
            Cliente cliente = (Cliente) ctrvenda.getCtrprin().getCtrCliente().getListaClientes().get(i);
            cbcpf.addItem("Nome: " + cliente.getNome() + " -- CPF: " + cliente.getCpf());
        }

        //Botão Venda
        btnprox = new JButton("Proximo");
        btnprox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = "";
                String nome = "";

                if (e.getSource() == btnprox) {
                    //Pega Nome e o CPF do ComboBox

                    cpf = ctrvenda.getCtrprin().getCtrCliente().getListaClientes().get(cbcpf.getSelectedIndex()).getCpf();
                    nome = ctrvenda.getCtrprin().getCtrCliente().getListaClientes().get(cbcpf.getSelectedIndex()).getNome();
                    janela.dispose();
                    new LimiteCadastraVenda(ctrvenda);
                    Janela2(cpf, nome, ctrvenda);

                }
            }
        });

        //Painel
        painel = new JPanel();
        painel.setLayout(new GridBagLayout());

        addComp(painel, lblcpf, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(painel, cbcpf, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(painel, btnprox, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        janela.add(painel);
        janela.pack();
        /// janela.setSize(300, 250);
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(true);

    }

    public void Janela2(String cpf, String nome, ControleVenda pCtrVenda) {

        janela2 = new JFrame("Produto");

        //Botões
        btnCarrinho = new JButton("Inserir no carrinho");
        btnprox = new JButton("Finalizar compra");

        //Labels e TextFields
        lblNome = new JLabel("Nome: " + nome);
        lblcpf = new JLabel("CPF: " + cpf);
        lblData = new JLabel("Data: ");
        lblProd = new JLabel("Escolha o produto: ");
        lblquant = new JLabel("Quantidade: ");
        txtData = new JTextField(8);
        txtquant = new JTextField(5);

        //ComboBox
        cbNomeProd = new JComboBox();

        //Paineis
        panelDireita = new JPanel();
        panelEsquerda = new JPanel();
        panelData = new JPanel();

        //Adiciona itens ao ComboBox
        for (int i = 0; i < pCtrVenda.getCtrprin().getCtrMercadoria().getListaMercadoria().size(); i++) {
            Mercadoria merc = (Mercadoria) pCtrVenda.getCtrprin().getCtrMercadoria().getListaMercadoria().get(i);
            cbNomeProd.addItem(merc.getDescricao());
        }
        
        //Setando os layouts dos paineis
        panelDireita.setLayout(new BoxLayout(panelDireita, BoxLayout.Y_AXIS));
        panelEsquerda.setLayout(new BoxLayout(panelEsquerda, BoxLayout.Y_AXIS));
        panelData.setLayout(new FlowLayout());
        panelData.add(lblData);
        panelData.add(txtData);

        //Box
        Box infoCliente = Box.createVerticalBox();
        infoCliente.add(lblNome);
        infoCliente.add(lblcpf);
        infoCliente.setBorder(BorderFactory.createTitledBorder("INFORMAÇÕES DO CLIENTE: "));
        infoCliente.setPreferredSize(new Dimension(300, 50));

        //Botão próximo
        btnprox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ID++;
                pCtrVenda.InserirNotaFiscal(ID - 1);
                janela2.dispose();
            }
        });

        //Botão carrinho
        btnCarrinho.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int codigoProduto = pCtrVenda.getCtrprin().getCtrMercadoria().getListaMercadoria().get(cbNomeProd.getSelectedIndex()).getCodigo();
                pCtrVenda.InserirVenda(lblcpf.getText(), codigoProduto, txtData.getText(), Integer.parseInt(txtquant.getText()), ID);
            }
        });

        //Painel
        painel = new JPanel();

        painel.setLayout(new GridBagLayout());

        addComp(painel, infoCliente, 0, 0, 2, 2, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(painel, panelData, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(painel, lblProd, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(painel, cbNomeProd, 1, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(painel, lblquant, 0, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(painel, txtquant, 1, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(painel, btnCarrinho, 0, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(painel, btnprox, 1, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        janela2.add(painel);
        janela2.pack();
        janela.setSize(400, 250);
        janela2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela2.setLocationRelativeTo(null);
        janela2.setVisible(true);
        janela2.setResizable(false);
    }

    /* public void janela3(String nome, String cpf) {

     janela3 = new JFrame("Nota Fiscal");

     //Labels e TextFields
     lblNome = new JLabel("Nome: " + nome);
     lblcpf = new JLabel("CPF: " + cpf);
     lblEnd = new JLabel("Código da Mercadoria: ");
     lblquant = new JLabel("Quantidade de Mercadorias: ");

     for (int i = 0; i < pCtrVenda.getCtrprin().getCtrMercadoria().getListaMercadorias().size(); i++) {
     Mercadoria merc = (Mercadoria) pCtrVenda.getCtrprin().getCtrMercadoria().getListaMercadorias().get(i);
     int cod = merc.getCodigo();

     txtcod = new JTextField(8);
     txtquant = new JTextField(5);

     txtcod.setEnabled(false);
     txtcod.setText(String.valueOf(cod));

     txtquant.setEnabled(true);
     txtquant.setText("0");

     panelCod.add(txtcod);
     panelQuant.add(txtquant);
     }

     //Botão Venda
     btnprox = new JButton("Continuar");
     btnprox.addActionListener(new ActionListener() {

     @Override
     public void actionPerformed(ActionEvent e) {
     JButton auxBotao = (JButton) e.getSource();

     if (auxBotao.equals(btnprox)) {

     }
     }
     });

     //Painel
     painel = new JPanel();
     painel.setLayout(new GridBagLayout());

     addComp(painel, lblNome, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
     addComp(painel, lblcpf, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

     addComp(painel, lblcod, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
     addComp(painel, lblquant, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

     addComp(painel, panelCod, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
     addComp(painel, panelQuant, 1, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

     addComp(painel, btnprox, 0, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

     janela2.add(painel);
     janela2.pack();
     /// janela.setSize(300, 250);
     janela2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     janela2.setLocationRelativeTo(null);
     janela2.setVisible(true);
     janela2.setResizable(false);
     }*/
}
