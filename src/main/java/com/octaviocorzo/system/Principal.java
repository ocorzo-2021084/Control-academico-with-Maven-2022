package com.octaviocorzo.system;

/**
 *
 * @author informatica
 */
import com.octaviocorzo.controllers.MateriaController;
import com.octaviocorzo.controllers.MenuPrincipalController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Principal extends Application {

    private Stage escenarioPrincipal;
    private final String PAQUETE_IMAGE = "com/octaviocorzo/resources/images/";
    private final String PAQUETE_VIEW = "../views/";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.escenarioPrincipal = primaryStage;
        this.escenarioPrincipal.setTitle("Control Materia");
        this.escenarioPrincipal.getIcons().add(new Image(PAQUETE_IMAGE + "icono.png"));
        this.escenarioPrincipal.setResizable(false);
        this.escenarioPrincipal.centerOnScreen();
        mostrarEscenaMateria();
    }
    
    public void mostrarEscenaPrincipal() {
        try {
            MenuPrincipalController menuController = (MenuPrincipalController) cambiarEscena("MenuPrincipalView.fxml", 950, 600);
            menuController.setEscenarioPrincipal(this);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("\nSe produjo en error al intenar mostrar la vista principal");
        }
    }
    
    public void mostrarEscenaMateria() {
        try {
            MateriaController materiaController = (MateriaController) cambiarEscena("MateriasView.fxml", 950, 600);
            materiaController.setEscenarioPrincipal(this);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nSe produjo en error al intenar mostrar la vista Materias");

        }
    }
    
    public Initializable cambiarEscena(String vistaFxml, int ancho, int alto) throws IOException {
        System.out.println("Cambiando escena " + PAQUETE_VIEW + vistaFxml);
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(PAQUETE_VIEW + vistaFxml));
        Scene scene = new Scene((AnchorPane) cargadorFXML.load(), ancho, alto);
        this.escenarioPrincipal.setScene(scene);
        this.escenarioPrincipal.sizeToScene();
        this.escenarioPrincipal.show();
        return (Initializable) cargadorFXML.getController();
    }
}

