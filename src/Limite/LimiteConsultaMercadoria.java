package Limite;

import Controle.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import trabalho_final.Mercadoria;

public class LimiteConsultaMercadoria extends LimiteUtil implements ActionListener {

    ControleMercadoria ctrMercadoria;

    //Frame
    JFrame janela, janela1;

    //Label
    JLabel labelCodigo, labelDados;

    //TextField
    JTextField textCodigo;

    //TextArea
    JTextArea textDados;

    //JButton
    JButton botaoConsultar;

    //Painel
    JPanel panel1, panel2;

    public LimiteConsultaMercadoria(ControleMercadoria pCtrMercadoria) {
        ctrMercadoria = pCtrMercadoria;

        janela = new JFrame("Consulta Mercadoria");
        panel1 = new JPanel();

        //Label e TextField
        labelCodigo = new JLabel("Informe o código: ");
        textCodigo = new JTextField(10);

        //Botão
        botaoConsultar = new JButton("Consultar");
        botaoConsultar.addActionListener(this);

        //Organizando os componentes na janela
        panel1.setLayout(new GridBagLayout());

        addComp(panel1, labelCodigo, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textCodigo, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, botaoConsultar, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        janela.add(panel1);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(false);
    }

    public void janelaDados(String resultado) throws Exception {
        janela1 = new JFrame("Dados da Mercadoria");
        panel2 = new JPanel();

        labelDados = new JLabel("Dados: ");
        textDados = new JTextArea(7, 66);
        textDados.setText(resultado);
        textDados.setEditable(false); //Não editável

        panel2.setLayout(new GridBagLayout());

        addComp(panel2, labelDados, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel2, textDados, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        janela1.add(panel2);
        janela1.pack();
        janela1.setSize(300, 200);
        janela1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela1.setLocationRelativeTo(null);
        janela1.setVisible(true);
        janela1.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int codigo = Integer.parseInt(textCodigo.getText());
        JButton geral = (JButton) e.getSource();
        String resultado = "";
        boolean b1 = false;

        //Se clicar no botão
        //Primeiro: chama o metodo JanelaDados() para abrir o frame de Dados da mercadoria
        //Sedgundo: compara o numero do CPF e seta os dados a textArea
        if (geral.equals(botaoConsultar)) {
            
             ControleMercadoria ctrMercadorias = null;

            try {
                ctrMercadorias = new ControleMercadoria();
            } catch (Exception ex) {
                Logger.getLogger(LimiteConsultaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ctrMercadorias.desserializaMercadoria();
            } catch (Exception ex) {
                Logger.getLogger(LimiteConsultaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for (Mercadoria p : ctrMercadoria.getListaMercadoria()) {
                if (p.getCodigo() == codigo) {
                    b1 = true;
                     try {
                        janelaDados(ctrMercadorias.imprimeMercadorias(codigo));
                    } catch (Exception ex) {
                        Logger.getLogger(LimiteConsultaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textCodigo.setText("");
                }
            }
        }

        if (b1 == false) {
            JOptionPane.showMessageDialog(null, "Produto não cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            textCodigo.setText("");
        }
    }
}
