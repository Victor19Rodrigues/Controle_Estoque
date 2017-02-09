package Limite;

import Controle.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import trabalho_final.*;

public class LimiteConsultaCliente extends LimiteUtil implements ActionListener {

    ControleCliente ctrCliente;

    //Frame
    JFrame janela, janela1;

    //Label
    JLabel labelCPF, labelDados;

    //TextField
    JTextField textCPF;

    //TextArea
    JTextArea textDados;

    //JButton
    JButton botaoConsultar;

    //Painel
    JPanel panel1, panel2;

    public LimiteConsultaCliente(ControleCliente pCtrCliente) {

        ctrCliente = pCtrCliente;

        janela = new JFrame("Consultar Cliente");

        //Label
        labelCPF = new JLabel("Informe o CPF: ");

        //TextField
        textCPF = new JTextField(15);

        botaoConsultar = new JButton("Consultar");
        botaoConsultar.addActionListener(this);

        //Seta o painel e seu layout
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        addComp(panel1, labelCPF, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textCPF, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, botaoConsultar, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        //panel1.setBackground(new Color(0, 67, 68));
        janela.add(panel1);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(false);

    }

    //Metodo para cria a janela de Dados do cliente
    public void JanelaDados(String resultado) throws Exception {
        janela1 = new JFrame("Dados do Cliente");

        //TextArea e label
        textDados = new JTextArea(6, 15);
        textDados.setText(resultado);
        textDados.setEditable(false); //Não editável
        labelDados = new JLabel("Dados: ");

        //Seta o painel e seu layout
        panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());

        addComp(panel2, labelDados, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel2, textDados, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        janela1.add(panel2);
        janela1.pack();
        janela1.setSize(300, 250);
        janela1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela1.setLocationRelativeTo(null);
        janela1.setVisible(true);
        janela1.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cpf = textCPF.getText();

        JButton geral = (JButton) e.getSource();

        String resultado = "";
        boolean b1 = false;

        //Se clicar no botão
        //Primeiro: chama o metodo JanelaDados() para abrir o frame de Dados do cliente
        //Sedgundo: compara o numero do CPF e seta os dados a textArea
        if (geral.equals(botaoConsultar)) {

            ControleCliente ctrClientes = null;

            try {
                ctrClientes = new ControleCliente();
            } catch (Exception ex) {
                Logger.getLogger(LimiteConsultaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ctrClientes.desserializaCliente();
            } catch (Exception ex) {
                Logger.getLogger(LimiteConsultaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (Cliente p : ctrCliente.getCtrPrincipal().getCtrCliente().getListaClientes()) {
                if (p.getCpf().equals(cpf)) {
                    b1 = true;

                    try {
                        JanelaDados(ctrClientes.imprimeClientes(cpf));
                    } catch (Exception ex) {
                        Logger.getLogger(LimiteConsultaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    textCPF.setText("");

                }
            }

            if (b1 == false) {

                JOptionPane.showMessageDialog(null, "Cliente não cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                textCPF.setText("");
            }
        }
    }

}
