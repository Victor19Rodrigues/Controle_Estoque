package Controle;

import java.util.*;
import trabalho_final.*;
import Limite.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class ControleMercadoria {

    private ArrayList<Mercadoria> ListaMercadorias = new ArrayList<>();
    ControlePrincipal ctrPrincipal;
    private final String Arquivo = "Mercadoria.dat";

    public ControleMercadoria() throws Exception {
        desserializaMercadoria();
    }

    public ControleMercadoria(ControlePrincipal pCtrPrincipal) {
        ctrPrincipal = pCtrPrincipal;

        try {
            desserializaMercadoria();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void cadastraMercadoria() throws Exception {
        new LimiteCadastraMercadoria(this);
    }

    public void consultaMercadoria() throws Exception {
        new LimiteConsultaMercadoria(this);
    }

    public void atualizaMercadoria() throws Exception {
        new LimiteAtualizaMercadoria(this);
    }

    public void exibeEstoque() throws Exception {
        new LimiteExibeEstoque(this);
    }

    public void insereMercadoria(int codigo, String descricao, float preco_compra, float preco_venda, int quantidade) throws Exception {
        Mercadoria produto = new Mercadoria(codigo, descricao, preco_compra, preco_venda, quantidade);
        int flag = 0;

        if (ListaMercadorias.size() == 0) {
            ListaMercadorias.add(produto);
            serializaMercadoria();
        } else {
            for (int i = 0; i < ListaMercadorias.size(); i++) {
                Mercadoria objMerc = (Mercadoria) ListaMercadorias.get(i);
                if (objMerc.getCodigo() == codigo) {
                    objMerc.setQuantidade(objMerc.getQuantidade() + quantidade);
                    ListaMercadorias.set(i, objMerc);
                    serializaMercadoria();
                    flag = 1;
                }
            }

            if (flag == 0) {
                ListaMercadorias.add(produto);
                serializaMercadoria();
            }
        }
    }

    public boolean vendeMercadoria(int codigo, int quantidadeARemover) {
        int b = 0;
        for (int i = 0; i < ListaMercadorias.size(); i++) {
            Mercadoria objMerc = (Mercadoria) ListaMercadorias.get(i);
            if ((objMerc.getCodigo() == codigo) && (objMerc.getQuantidade() >= quantidadeARemover)) {
                objMerc.setQuantidade(objMerc.getQuantidade() - quantidadeARemover);
                ListaMercadorias.set(i, objMerc);
                //Mandar para a nota fiscal de venda
                b = 1;
            } else {
                JOptionPane.showMessageDialog(null, "Produto " + objMerc.getDescricao() + " indisponível no estoque", "Erro", JOptionPane.ERROR_MESSAGE);
                b = 0;
            }
        }

        if (b == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void aumentaMercadoria(int codigo, int quantidadeAAdicionar) {
        for (int i = 0; i < ListaMercadorias.size(); i++) {
            Mercadoria objMerc = (Mercadoria) ListaMercadorias.get(i);
            if (objMerc.getCodigo() == codigo) {
                objMerc.setQuantidade(objMerc.getQuantidade() + quantidadeAAdicionar);
                ListaMercadorias.set(i, objMerc);
                //Mandar para a nota fiscal de venda
            }
        }
    }

    public int retornaQuantidade(int codigo) {
        int quantidade_disponivel = -1;

        for (int i = 0; i < ListaMercadorias.size(); i++) {
            Mercadoria objMerc = (Mercadoria) ListaMercadorias.get(i);
            if (objMerc.getCodigo() == codigo) {
                quantidade_disponivel = objMerc.getQuantidade();
            }
        }

        if (quantidade_disponivel == -1) {
            JOptionPane.showMessageDialog(null, "Produto não cadastrado");
        }

        return quantidade_disponivel;
    }

    public int quantidadeTotalProdutosNoEstoque() {
        int quantidade_total = 0;

        for (int i = 0; i < ListaMercadorias.size(); i++) {
            Mercadoria objMerc = (Mercadoria) ListaMercadorias.get(i);
            quantidade_total += objMerc.getQuantidade();
        }

        return quantidade_total;
    }

    public String imprimeMercadorias(int codigo) {
        String resultado = "";
        Mercadoria objMerc = null;
        for (int i = 0; i < ListaMercadorias.size(); i++) {
            objMerc = (Mercadoria) ListaMercadorias.get(i);
            if (objMerc.getCodigo() == codigo) {
                resultado += imprimeMercadoria(objMerc, codigo);
            }

        }
        if (resultado.equalsIgnoreCase("")) {
            return "Não existem mercadorias cadastradas.";
        } else {
            return resultado;
        }
    }

    //Método utilizado para imprimir um determinado cliente
    public String imprimeMercadoria(Mercadoria mercadoria, int codigo) {
        return "Descrição: " + mercadoria.getDescricao()
                + "\nValor de venda: " + mercadoria.getPreco_venda()
                + "\nQuantidade em estoque: " + retornaQuantidade(codigo);
    }

    public String imprimeEstoqueTodo() {
        String estq = "";

        for (int i = 0; i < ListaMercadorias.size(); i++) {
            Mercadoria objMerc = (Mercadoria) ListaMercadorias.get(i);

            estq += "Código: " + objMerc.getCodigo() + "\nDescrição: " + objMerc.getDescricao()
                    + "\nValor de venda: R$" + objMerc.getPreco_venda() + "\nQuantidade: " + objMerc.getQuantidade() + "\n\n";
        }

        return estq;
    }

    public void calculaFaturamento(int codigo) {
        float lucro = 0, porcentagem = 0;

        for (int i = 0; i < ListaMercadorias.size(); i++) {
            Mercadoria objMerc = (Mercadoria) ListaMercadorias.get(i);
            if (objMerc.getCodigo() == codigo) {
                lucro = objMerc.getPreco_venda() - objMerc.getPreco_compra();
                porcentagem = ((objMerc.getPreco_venda() - objMerc.getPreco_compra()) / objMerc.getPreco_compra()) * 100;
            }
        }

        System.out.println("Lucro: R$" + lucro + "\nPorcentagem de lucro: " + porcentagem + "%");
    }

    public ControlePrincipal getCtrPrincipal() {
        return ctrPrincipal;
    }

    public ArrayList<Mercadoria> getListaMercadoria() {
        return ListaMercadorias;
    }

    public void serializaMercadoria() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(Arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(ListaMercadorias);
        objOS.flush();
        objOS.close();
    }

    public void desserializaMercadoria() throws Exception {
        File objFile = new File(Arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(Arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            ListaMercadorias = (ArrayList<Mercadoria>) objIS.readObject();
            objIS.close();
        }
    }

}
