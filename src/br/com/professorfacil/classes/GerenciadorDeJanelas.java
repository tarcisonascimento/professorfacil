package br.com.professorfacil.classes;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class GerenciadorDeJanelas {

    private static JDesktopPane jDesktopPane;

    public GerenciadorDeJanelas(JDesktopPane jDesktopPane) {

        GerenciadorDeJanelas.jDesktopPane = jDesktopPane;

    }

    public void AbrirJanelas(JInternalFrame jInternalFrame) {

        if (jInternalFrame.isVisible()) {

            jInternalFrame.toFront();
            jInternalFrame.requestFocus();

        } else {
            jDesktopPane.add(jInternalFrame);
            jInternalFrame.setVisible(true);

        }

    }

}
