package Limite;

import Controle.ControleMercadoria;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class LimiteCadastraMercadoria extends LimiteUtil implements ActionListener {

    ControleMercadoria ctrMercadoria;

    //Frame da janela
    JFrame janela;

    //Campos de texto
    JTextField textCodigo, textPrecoCompra, textPrecoVenda, textQuantidade;

    //Painel
    JPanel panel1;

    //Labels
    JLabel labelCodigo, labelPrecoVenda, labelPrecoCompra, labelQuantidade, labelDescricao;

    //Botão de cadastro
    JButton botaoCadastrar;

    //Area da descricao do produto
    JTextArea textAreaDescricao;

    public LimiteCadastraMercadoria(ControleMercadoria pCtrMercadoria) {

        ctrMercadoria = pCtrMercadoria;

        janela = new JFrame("Cadastrar Mercadoria");

        //Criacao das labels
        labelCodigo = new JLabel("Informe o código do produto: ");
        labelPrecoCompra = new JLabel("Informe o preço de compra: ");
        labelPrecoVenda = new JLabel("Informe o preço de venda: ");
        labelQuantidade = new JLabel("Informe a quantidade: ");
        labelDescricao = new JLabel("Descrição do produto: ");

        //Criacao dos campos de texto 
        textCodigo = new JTextField(15);
        textPrecoCompra = new JTextField(15);
        textPrecoVenda = new JTextField(15);
        textQuantidade = new JTextField(15);
        textAreaDescricao = new JTextArea(6, 15);
        textAreaDescricao.setLineWrap(true); //Quebra de linha

        //Botao
        botaoCadastrar = new JButton("Cadastrar Mercadoria");
        botaoCadastrar.addActionListener(this);

        //label imagem
        ImageIcon icon = new ImageIcon("/home/victor/Área de Trabalho/Trabalho_Final/src/Imagens/controle.png");
        JLabel labelImagem = new JLabel(icon);

        //Seta o painel e seu layout
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        addComp(panel1, labelImagem, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

        addComp(panel1, labelCodigo, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textCodigo, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, labelPrecoCompra, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textPrecoCompra, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, labelPrecoVenda, 0, 3, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textPrecoVenda, 1, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, labelQuantidade, 0, 4, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textQuantidade, 1, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, labelDescricao, 0, 5, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textAreaDescricao, 1, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, botaoCadastrar, 0, 6, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        //panel1.setBackground(new Color(0, 67, 68));
        janela.add(panel1);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton geral = (JButton) e.getSource();

        if (geral.equals(botaoCadastrar)) {

            //Se os campos estiverem vazios
            if (textCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor preencha os campos!");
            } else {
                try {
                    ctrMercadoria.insereMercadoria(Integer.parseInt(textCodigo.getText()), textAreaDescricao.getText(),
                            Float.parseFloat(textPrecoCompra.getText()), Float.parseFloat(textPrecoVenda.getText()),
                            Integer.parseInt(textQuantidade.getText()));
                } catch (Exception ex) {
                    Logger.getLogger(LimiteCadastraMercadoria.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Mercadoria cadastrada com Sucesso!");

                textCodigo.setText("");
                textPrecoCompra.setText("");
                textPrecoVenda.setText("");
                textAreaDescricao.setText("");
                textQuantidade.setText("");
            }
        }
    }

}
