package Limite;

import Controle.ControleCliente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import trabalho_final.Cliente;

public class LimiteCadastraCliente extends LimiteUtil implements ActionListener {

    ControleCliente ctrCLiente;

    //Frame da janela
    JFrame janela;

    //Campos de texto
    JTextField textNome, textEndereco, textEmail, textCPF;

    //Painel
    JPanel panel1;

    //Labels
    JLabel labelNome, labelEndereco, labelEmail, labelCPF;

    //Botão de cadastro
    JButton botaoCadastrar;

    public LimiteCadastraCliente(ControleCliente pCtrCliente) throws Exception {

        ctrCLiente = pCtrCliente;

        janela = new JFrame("Cadastrar Cliente");

        //Criacao das labels
        labelNome = new JLabel("Informe o nome: ");
        labelCPF = new JLabel("Informe o CPF: ");
        labelEndereco = new JLabel("Informe o endereço: ");
        labelEmail = new JLabel("Informe o email: ");

        //Criacao dos campos de texto 
        textNome = new JTextField(15);
        textCPF = new JTextField(15);
        textEndereco = new JTextField(15);
        textEmail = new JTextField(25);

        //Botao
        botaoCadastrar = new JButton("Cadastrar Cliente");
        botaoCadastrar.addActionListener(this);

        //Seta o painel e seu layout
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        addComp(panel1, labelNome, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textNome, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, labelCPF, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textCPF, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, labelEndereco, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textEndereco, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, labelEmail, 0, 3, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel1, textEmail, 1, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        addComp(panel1, botaoCadastrar, 0, 4, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        //panel1.setBackground(new Color(0, 67, 68));
        janela.add(panel1);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setResizable(false);
        ctrCLiente.desserializaCliente();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton geral = (JButton) e.getSource();
        String cpf = textCPF.getText();

        if (geral.equals(botaoCadastrar)) {
            
            //Se os campos estiverem vazios
            if (textNome.getText().isEmpty() || textCPF.getText().isEmpty() || textEmail.getText().isEmpty()
                    || textEndereco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor preencha os campos!");
            } else  {
                ctrCLiente.insereCliente(textNome.getText(), textEndereco.getText(), textEmail.getText(), textCPF.getText());
                JOptionPane.showMessageDialog(null, "Cliente cadastrada com Sucesso!");
                textNome.setText("");
                textEndereco.setText("");
                textCPF.setText("");
                textEmail.setText("");
            }
        }
    }

}
