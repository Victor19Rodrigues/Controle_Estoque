package Controle;

import Limite.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import trabalho_final.*;

public class ControleCliente {

    private ArrayList<Cliente> ListaClientes = new ArrayList<>();
    private final String arquivo = "Cliente.dat";

    ControlePrincipal ctrPrincipal;

    public ControleCliente() throws Exception {
        desserializaCliente();
    }

    public ControleCliente(ControlePrincipal pCtrPrincipal) {
        ctrPrincipal = pCtrPrincipal;

        try {
            desserializaCliente();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void cadastraCliente() throws Exception {
        new LimiteCadastraCliente(this);
    }

    public void consultaCliente() {
        new LimiteConsultaCliente(this);
    }

    public void insereCliente(String nome, String endereco, String email, String cpf) {
        
        Cliente novoCliente = new Cliente(nome, endereco, email, cpf);
        ListaClientes.add(novoCliente);

        try {
            serializaCliente();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ControlePrincipal getCtrPrincipal() {
        return ctrPrincipal;
    }

    public ArrayList<Cliente> getListaClientes() {
        return ListaClientes;
    }

    public String imprimeClientes(String cpf) {
        String resultado = "";
        Cliente objCliente = null;
        for (int i = 0; i < ListaClientes.size(); i++) {
            objCliente = (Cliente) ListaClientes.get(i);
            if (objCliente.getCpf().equals(cpf)) {
                resultado += imprimeCliente(objCliente);
            }

        }
        if (resultado.equalsIgnoreCase("")) {
            return "Não existem clientes cadastrados.";
        } else {
            return resultado;
        }
    }

    //Método utilizado para imprimir um determinado cliente
    public String imprimeCliente(Cliente cliente) {
        return "Nome: " + cliente.getNome()
                + "\nEndereço: " + cliente.getEndereco()
                + "\nEmail: " + cliente.getEmail();
    }

    public void serializaCliente() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(ListaClientes);
        objOS.flush();
        objOS.close();
    }

    public void desserializaCliente() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            ListaClientes = (ArrayList<Cliente>) objIS.readObject();
            objIS.close();
        }
    }

}
