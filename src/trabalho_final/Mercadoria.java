package trabalho_final;

import java.io.Serializable;

public class Mercadoria implements Serializable {

    private int codigo, quantidade;
    private String descricao;
    private float preco_compra, preco_venda;

    public Mercadoria(int codigo, String descricao, float preco_compra, float preco_venda, int quantidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco_compra = preco_compra;
        this.preco_venda = preco_venda;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getPreco_compra() {
        return preco_compra;
    }

    public float getPreco_venda() {
        return preco_venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco_compra(float preco_compra) {
        this.preco_compra = preco_compra;
    }

    public void setPreco_venda(float preco_venda) {
        this.preco_venda = preco_venda;
    }
}
