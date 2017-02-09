/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Limite.LimiteCadastraVenda;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import trabalho_final.*;

public class ControleVenda {

    private ControlePrincipal ctrprin;
    private ArrayList<Venda> listavendas = new ArrayList<>();
    private ArrayList<NotaFiscal> listanotafiscal = new ArrayList<>();

    public ControleVenda(ControlePrincipal ctrprins) {
        ctrprin = ctrprins;
    }

    public ControlePrincipal getCtrprin() {
        return ctrprin;
    }

    public ArrayList<Venda> getListavendas() {
        return listavendas;
    }

    public void InserirVenda(String cpfcliente, int codMercadoria, String data, int quant, int ID) {
        Cliente cliente = null;
        Mercadoria produto = null;
        for (int i = 0; i < ctrprin.getCtrCliente().getListaClientes().size(); i++) {
            Cliente clientetemp = (Cliente) ctrprin.getCtrCliente().getListaClientes().get(i);
            if (clientetemp.getCpf().equals(cpfcliente)) {
                cliente = clientetemp;
            }
        }
        for (int j = 0; j < ctrprin.getCtrMercadoria().getListaMercadoria().size(); j++) {
            Mercadoria merctemp = (Mercadoria) ctrprin.getCtrMercadoria().getListaMercadoria().get(j);
            if (merctemp.getCodigo() == codMercadoria) {
                produto = merctemp;
                if (ctrprin.getCtrMercadoria().vendeMercadoria(codMercadoria, quant) == true) {
                    listavendas.add(new Venda(cliente, produto, data, quant, ID));
                    JOptionPane.showMessageDialog(null, "Mercadoria adicionada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Produto " + merctemp.getDescricao() + " indisponível no estoque", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    public void CriaVenda() {
        new LimiteCadastraVenda(this);
    }

    public void InserirNotaFiscal(int id) {
        Vector listaparc = new Vector();
        Venda auxiliar = null;
        for (int k = 0; k < listavendas.size(); k++) {
            Venda venda = (Venda) listavendas.get(k);
            if (venda.getID() == id) {
                listaparc.add(venda.getProduto());
                auxiliar = venda;
            }
        }
        if (auxiliar != null) {
            listanotafiscal.add(new NotaFiscal(auxiliar.getCliente().getCpf(), auxiliar.getCliente().getNome(), listaparc));
        }
    }

    public void ConsultarNotaFiscal(String cpf) {
        String retorno = "";
        for (int k = 0; k < listanotafiscal.size(); k++) {
            NotaFiscal nota = (NotaFiscal) listanotafiscal.get(k);
            if (nota.getCpf().equals(cpf)) {
                retorno = "Cpf : " + nota.getCpf() + "Nome";
            }
        }
    }

}
