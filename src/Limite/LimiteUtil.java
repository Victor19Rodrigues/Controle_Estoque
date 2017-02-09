package Limite;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JPanel;
/*
    Classe que contém métodos utilizados em todas as views
*/
public class LimiteUtil {

    public void addComp(JPanel painel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place,
            int stretch) {

        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        painel.add(comp, gridConstraints);

    }
}
