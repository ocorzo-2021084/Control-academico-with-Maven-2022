package com.octaviocorzo.controllers;

import com.octaviocorzo.system.Principal;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

/**
 *
 * @author Octavio Alejandro Corzo Reyes
 * @date 26/09/2022
 * @time 18:34:54
 *
 * Código Técnico: IN5BM
 *
 */
public class MenuPrincipalController {

    private Principal escenarioPrincipal;
    private final String PAQUETE_IMAGE = "com/octaviocorzo/resources/images/";

    public void initialize(URL url, ResourceBundle rb) {

    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    void clicMateria(ActionEvent event) {
        escenarioPrincipal.mostrarEscenaMateria();
    }
}
