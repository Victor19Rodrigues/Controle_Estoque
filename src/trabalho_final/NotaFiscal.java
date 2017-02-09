package trabalho_final;

import java.util.Vector;

public class NotaFiscal {

    private String Cpf;
    private String Nome;
    private Vector Listaprodutos;

    public NotaFiscal(String Cpf, String Nome, Vector Listaprodutos) {
        this.Cpf = Cpf;
        this.Nome = Nome;
        this.Listaprodutos = Listaprodutos;
    }

    public String getCpf() {
        return Cpf;
    }

    public String getNome() {
        return Nome;
    }

    public Vector getListaprodutos() {
        return Listaprodutos;
    }

    public void setListaprodutos(Vector Listaprodutos) {
        this.Listaprodutos = Listaprodutos;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    @Override
    public String toString() {
        return super.toString(); 
    }

}
