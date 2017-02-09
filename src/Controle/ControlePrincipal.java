package Controle;

import Limite.LimitePrincipal;

public class ControlePrincipal {

    private ControleCliente ctrCliente;
    private ControleMercadoria ctrMercadoria;
    private LimitePrincipal limPrincipal;
    private ControleVenda ctrVenda;

    public ControlePrincipal() {
        ctrCliente = new ControleCliente(this);
        ctrMercadoria = new ControleMercadoria(this);
        limPrincipal = new LimitePrincipal(this);
        ctrVenda = new ControleVenda(this);
    }

    public ControleCliente getCtrCliente() {
        return ctrCliente;
    }

    public ControleMercadoria getCtrMercadoria() {
        return ctrMercadoria;
    }

    public ControleVenda getCtrVenda() {
        return ctrVenda;
    }
    
    
}
