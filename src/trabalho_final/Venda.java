package trabalho_final;

public class Venda {

    private Cliente cliente;
    private Mercadoria produto;
    private String datavenda;
    private int quantidade;
    private int ID;

    public Venda(Cliente cliente, Mercadoria produto, String datavenda, int quantidade, int ID) {
        this.cliente = cliente;
        this.produto = produto;
        this.datavenda = datavenda;
        this.quantidade = quantidade;
        this.ID = ID;
    }

    public void setDatavenda(String datavenda) {
        this.datavenda = datavenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getDatavenda() {
        return datavenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mercadoria getProduto() {
        return produto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProduto(Mercadoria produto) {
        this.produto = produto;
    }

}
