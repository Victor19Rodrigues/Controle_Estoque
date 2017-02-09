package Limite;

import Controle.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import trabalho_final.Mercadoria;

public class LimiteAtualizaMercadoria extends LimiteUtil implements ActionListener {

    ControleMercadoria ctrMercadoria;

    //Frame
    JFrame janela;

    //Label
    JLabel labelCodigo, labelQuantidade;

    //TextField
    JTextField textCodigo, textQuantidade;

    //JButton
    JButton botaoAtualizar;

    //Painel
    JPanel panel1;

    public LimiteAtualizaMercadoria(ControleMercadoria pCtrMercadoria) {
        ctrMercadoria = pCtrMercadoria;

        janela = new JFrame("Atualiza Mercadoria");
        panel1 = new JPanel();

        //Label e TextField
        labelCodigo = new JLabel("Informe o código: ");
        textCodigo = new JTextField(10);

        labelQuantidade = new JLabel("Informe a quantidade: ");
        textQuantidade = new JTextField(5);

        //Botão
        botaoAtualizar = new JButton("Atualizar Mercadoria");
        botaoAtualizar.addActionListener(this);

        //Organizando os componentes na janela
        panel1.setLayout(new GridBagLayout());

        addComp(panel1, labelCodigo, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textCodigo, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel1, labelQuantidade, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textQuantidade, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel1, botaoAtualizar, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        janela.add(panel1);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int codigo = Integer.parseInt(textCodigo.getText());
        int quantidade = Integer.parseInt(textQuantidade.getText());
        JButton geral = (JButton) e.getSource();
        boolean b1 = false;

        //Se clicar no botão
        //Primeiro: chama o metodo JanelaDados() para abrir o frame de Dados da mercadoria
        //Sedgundo: compara o numero do CPF e seta os dados a textArea
        if (geral.equals(botaoAtualizar)) {

            for (Mercadoria p : ctrMercadoria.getCtrPrincipal().getCtrMercadoria().getListaMercadoria()) {
                if (p.getCodigo() == codigo) {
                    b1 = true;
                    ctrMercadoria.aumentaMercadoria(codigo, quantidade);
                    JOptionPane.showMessageDialog(null, "Produto atualizado!");
                    textCodigo.setText("");
                    textQuantidade.setText("");
                }
            }
        }

        if (b1 == false) {
            JOptionPane.showMessageDialog(null, "Produto não cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            textCodigo.setText("");
            textQuantidade.setText("");
        }

    }
}
