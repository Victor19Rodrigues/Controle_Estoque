package Limite;

import Controle.ControleMercadoria;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimiteExibeEstoque extends LimiteUtil implements ActionListener {

    ControleMercadoria ctrMercadoria;

    //Frame da janela
    JFrame janela;

    //Painel
    JPanel panel1;

    //Labels
    JLabel labelQuantidade;

    //Botão de cadastro
    JButton botaoOK;

    //Area da descricao do produto
    JTextArea textEstoque;

    public LimiteExibeEstoque(ControleMercadoria pCtrMercadoria) {

        ctrMercadoria = pCtrMercadoria;

        janela = new JFrame("Estoque");

        //Criacao da label
        labelQuantidade = new JLabel("Quantidade de produtos no estoque: " + pCtrMercadoria.quantidadeTotalProdutosNoEstoque());

        //Criacao do campo de texto
        textEstoque = new JTextArea(100, 50);
        textEstoque.setText(ctrMercadoria.imprimeEstoqueTodo());
        textEstoque.setEditable(false); //Não editável

        //Botao
        botaoOK = new JButton("OK");
        botaoOK.addActionListener(this);

        //Seta o painel e seu layout
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        addComp(panel1, labelQuantidade, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, textEstoque, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel1, botaoOK, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        //panel1.setBackground(new Color(0, 67, 68));
        janela.add(panel1);
        janela.pack();
        janela.setSize(700, 450);
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton geral = (JButton) e.getSource();

        if (geral.equals(botaoOK)) {
            janela.dispose();
        }
    }
}
