package com.octaviocorzo.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.octaviocorzo.system.Principal;
import com.octaviocorzo.controllers.MateriaController;
import com.octaviocorzo.models.dao.MateriaDaoImpl;
import com.octaviocorzo.models.domain.Materia;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author informatica
 */
public class MateriaController implements Initializable {

    private ObservableList<Materia> listaMateria;
    private Principal escenarioPrincipal;
    private final String PAQUETE_IMAGE = "com/octaviocorzo/resources/images/";

    @FXML
    private ImageView imgNuevo;

    @FXML
    private Button btnNuevo;

    @FXML
    private ImageView imgEditar;

    @FXML
    private Button btnEditar;

    @FXML
    private ImageView imgEliminar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ImageView imgRegresar;
    @FXML
    private TableView tblMaterias;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colCiclo;
    @FXML
    private TableColumn ColHorarioInicio;
    @FXML
    private TableColumn colHorarioFinal;
    @FXML
    private TableColumn colCatedratico;
    @FXML
    private TableColumn ColCupoMaximo;
    @FXML
    private TableColumn ColCupoMinimo;
    @FXML
    private TableColumn colNota;
    @FXML
    private TableColumn colSalon;
    @FXML
    private JFXTimePicker tmtHorarioInicio;
    @FXML
    private JFXTimePicker tmtHorarioFinal;
    @FXML
    private Spinner<Integer> spnCupoMax;
    @FXML
    private Spinner<Integer> spnCupoMin;
    @FXML
    private TextField txtNombre;
    @FXML
    private Spinner<Integer> spnNota;
    @FXML
    private TextField txtCatedratico;
    @FXML
    private TextField txtSalon;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCiclo;
    

    private SpinnerValueFactory<Integer> valueFactoryCupoMinimo;

    private SpinnerValueFactory<Integer> valueFactoryCupoMaximo;

    private SpinnerValueFactory<Integer> valueFactoryNota;
    @FXML
    private JFXTextField txtContador;

    private enum Operacion {
        NINGUNO, GUARDAR, ACTUALIZAR
    }

    public boolean validacionDatos() {
        return (txtNombre.getText().isEmpty() || txtCatedratico.getText().isEmpty() || txtSalon.getText().isEmpty()
                || txtCiclo.getText().isEmpty() || spnCupoMax.getValue().equals("") || spnCupoMin.getValue().equals("")
                || spnNota.getValue().equals("") || tmtHorarioInicio.getEditor().getText().equals("")
                || tmtHorarioFinal.getEditor().getText().equals(""));
    }
    
    public void contador() {
        MateriaDaoImpl materia = new MateriaDaoImpl();
        int contador;
        contador = materia.getAll().size();
        txtContador.setText(String.valueOf(contador));
    }

    private void deshabilitarCampos() {
        txtId.setEditable(false);
        txtId.setDisable(true);

        txtNombre.setEditable(false);
        txtNombre.setDisable(true);

        txtCiclo.setEditable(false);
        txtCiclo.setDisable(true);

        tmtHorarioInicio.setDisable(true);
        tmtHorarioFinal.setDisable(true);

        txtCatedratico.setEditable(false);
        txtCatedratico.setDisable(true);

        txtSalon.setEditable(false);
        txtSalon.setDisable(true);

        spnCupoMax.setEditable(false);
        spnCupoMax.setDisable(true);

        spnCupoMin.setEditable(false);
        spnCupoMin.setDisable(true);

        spnNota.setEditable(false);
        spnNota.setDisable(true);

    }

    private void habilitarCampos() {
        txtId.setEditable(false);
        txtId.setDisable(true);

        txtNombre.setEditable(true);
        txtNombre.setDisable(false);

        txtCiclo.setEditable(true);
        txtCiclo.setDisable(false);

        tmtHorarioInicio.setDisable(false);
        tmtHorarioFinal.setDisable(false);

        txtCatedratico.setEditable(true);
        txtCatedratico.setDisable(false);

        txtSalon.setEditable(true);
        txtSalon.setDisable(false);

        //spnCupoMax.setEditable(true);
        spnCupoMax.setDisable(false);

        //spnCupoMin.setEditable(true);
        spnCupoMin.setDisable(false);

        //spnNota.setEditable(true);
        spnNota.setDisable(false);
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.setText("");
        txtCiclo.setText("");
        tmtHorarioInicio.getEditor().clear();
        tmtHorarioFinal.getEditor().clear();
        txtCatedratico.setText("");
        txtSalon.setText("");
        spnCupoMax.getValueFactory().setValue(20);
        spnCupoMin.getValueFactory().setValue(5);
        spnNota.getValueFactory().setValue(0);
    }

    private Operacion operacion = Operacion.NINGUNO;

    @FXML
    void clicEditar(ActionEvent event) {
        switch (operacion) {
            case NINGUNO: //editar una inserción
                if (existeElementoSeleccionado()) {
                    habilitarCampos();

                    btnNuevo.setDisable(true);
                    btnNuevo.setVisible(false);
                    imgNuevo.setVisible(false);

                    btnEditar.setText("Guardar");
                    imgEditar.setImage(new Image(PAQUETE_IMAGE + "disquete.png"));

                    btnEliminar.setText("Cancelar");
                    imgEliminar.setImage(new Image(PAQUETE_IMAGE + "cancelar.png"));
                    operacion = Operacion.ACTUALIZAR;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Control Academico");
                    alert.setHeaderText(null);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGE + "icono.png"));
                    alert.setContentText("Antes de continuar, seleccione un registro.");
                    alert.show();
                }
                break;
            case GUARDAR: //Cancela la inserción
                btnNuevo.setText("Nuevo");
                imgNuevo.setImage(new Image(PAQUETE_IMAGE + "agregar-archivo.png"));

                btnEditar.setText("Editar");
                imgEditar.setImage(new Image(PAQUETE_IMAGE + "usuario.png"));

                btnEliminar.setDisable(false);
                btnEliminar.setVisible(true);
                imgEliminar.setVisible(true);

                limpiarCampos();
                deshabilitarCampos();

                tblMaterias.setDisable(false);

                operacion = Operacion.NINGUNO;
                break;
            case ACTUALIZAR:

                if (validacionDatos()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Control Materia");
                    alert.setContentText("Antes de finalizar la edición rellene los campos.");

                    Image icon = new Image(PAQUETE_IMAGE + "/icono.png");

                    Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                    stageAlert.getIcons().add(icon);
                    alert.showAndWait();
                } else {

                    if (existeElementoSeleccionado()) {

                        if (editarMateria()) {
                            cargarDatos();
                            limpiarCampos();

                            tblMaterias.setDisable(false);
                            tblMaterias.getSelectionModel().clearSelection();

                            btnNuevo.setText("Nuevo");
                            btnNuevo.setDisable(false);
                            btnNuevo.setVisible(true);
                            imgNuevo.setVisible(true);
                            imgNuevo.setImage(new Image(PAQUETE_IMAGE + "agregar-archivo.png"));

                            btnEditar.setText("Editar");
                            imgEditar.setImage(new Image(PAQUETE_IMAGE + "usuario.png"));

                            btnEliminar.setText("Eliminar");
                            imgEliminar.setImage(new Image(PAQUETE_IMAGE + "eliminar.png"));

                            btnEliminar.setDisable(false);
                            btnEliminar.setVisible(true);
                            imgEliminar.setVisible(true);

                            deshabilitarCampos();

                            operacion = Operacion.NINGUNO;
                        }
                    }

                    break;

                }
        }
    }

    private boolean editarMateria() {
        MateriaDaoImpl materiaDao = new MateriaDaoImpl();
        Materia materia = new Materia();

        materia.setNombreMateria(txtNombre.getText());
        materia.setCiclo(Integer.parseInt(txtCiclo.getText()));
        materia.setHorarioInicio(Time.valueOf(tmtHorarioInicio.getValue()));
        materia.setHorarioFinal(Time.valueOf(tmtHorarioFinal.getValue()));
        materia.setCatedratico(txtCatedratico.getText());
        materia.setSalon(txtSalon.getText());
        materia.setCupoMaximo(spnCupoMax.getValue());
        materia.setCupoMinimo(spnCupoMin.getValue());
        materia.setNota(spnNota.getValue());
        materia.setIdMateria(Integer.parseInt(txtId.getText()));
        return materiaDao.update(materia);
    }

    @FXML
    void clicEliminar(ActionEvent event) {
        switch (operacion) {
            case ACTUALIZAR: //Cancelar una modificación.
                btnNuevo.setDisable(false);
                btnNuevo.setVisible(true);
                imgNuevo.setVisible(true);

                btnEditar.setText("Editar");
                imgEditar.setImage(new Image(PAQUETE_IMAGE + "usuario.png"));

                btnEliminar.setText("Eliminar");
                imgEliminar.setImage(new Image(PAQUETE_IMAGE + "eliminar.png"));

                limpiarCampos();
                deshabilitarCampos();

                tblMaterias.getSelectionModel().clearSelection();

                operacion = Operacion.NINGUNO;
                break;
            case NINGUNO:
                if (existeElementoSeleccionado()) {

                    Alert alertNew = new Alert(Alert.AlertType.CONFIRMATION);
                    alertNew.setHeaderText(null);
                    alertNew.setTitle("KINAL \"CONTROL MATERIAS\"");
                    alertNew.setContentText(null);
                    alertNew.setContentText("¿Desea eliminar el registro?");

                    Stage stage = (Stage) alertNew.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(PAQUETE_IMAGE + "icono.png"));

                    Optional<ButtonType> result = alertNew.showAndWait();

                    if (result.get().equals(ButtonType.OK)) {

                        if (eliminarMateria()) {
                            cargarDatos();

                            System.out.println("\n");
                            limpiarCampos();
                            cargarDatos();
                            contador();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setTitle("KINAL \"CONTROL ACÁDEMICO\"");
                            alert.setContentText("Registro eliminado exitosamente");
                            Image icon = new Image(PAQUETE_IMAGE + "icono.png");
                            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                            stageAlert.getIcons().add(icon);
                            alert.show();
                        } else if (result.get().equals(ButtonType.CANCEL)) {
                            System.out.println("\nCancelando Operacion");
                            tblMaterias.getSelectionModel().clearSelection();
                            limpiarCampos();
                        }
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Antes de seguir selecciona un registro");
                    Image icon = new Image(PAQUETE_IMAGE + "icono.png");
                    Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                    stageAlert.getIcons().add(icon);
                    alert.show();
                }
                break;
        }
    }

    public boolean eliminarMateria() {
        MateriaDaoImpl materiaDao = new MateriaDaoImpl();
        int id = Integer.parseInt(txtId.getText());
        return materiaDao.delete(id);
    }

    @FXML
    void clicNuevo(ActionEvent event) {
        switch (operacion) {
            case NINGUNO: //Primer Clic Nuevo
                habilitarCampos();
                tblMaterias.getSelectionModel().clearSelection();
                tblMaterias.setDisable(true);

                limpiarCampos();

                btnNuevo.setText("Guardar");
                imgNuevo.setImage(new Image(PAQUETE_IMAGE + "agregar-archivo.png"));

                btnEditar.setText("Cancelar");
                imgEditar.setImage(new Image(PAQUETE_IMAGE + "cancelar.png"));

                btnEliminar.setDisable(true);
                btnEliminar.setVisible(false);
                imgEliminar.setVisible(false);

                operacion = Operacion.GUARDAR;

                break;

            case GUARDAR:
                if (validacionDatos()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Control Materia");
                    alert.setContentText("Antes de finalizar la insersión debe ingresar los datos"
                            + " solicitados en cada uno de los campos");

                    Image icon = new Image(PAQUETE_IMAGE + "/icono.png");

                    Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                    stageAlert.getIcons().add(icon);
                    alert.showAndWait();
                } else {
                    if (agregarMateria()) {
                        cargarDatos();
                        limpiarCampos();
                        deshabilitarCampos();
                        tblMaterias.setDisable(false);
                        btnNuevo.setText("Nuevo");
                        imgNuevo.setImage(new Image(PAQUETE_IMAGE + "agregar-archivo.png"));

                        btnEditar.setText("Editar");
                        imgEditar.setImage(new Image(PAQUETE_IMAGE + "usuario.png"));

                        btnEliminar.setText("Eliminar");
                        imgEliminar.setImage(new Image(PAQUETE_IMAGE + "eliminar.png"));

                        btnEliminar.setDisable(false);
                        btnEliminar.setVisible(true);
                        imgEliminar.setVisible(true);
                        contador();

                        operacion = Operacion.NINGUNO;

                    }
                    break;
                }
        }
    }

    private boolean agregarMateria() {
        MateriaDaoImpl materiaDao = new MateriaDaoImpl();
        Materia materia = new Materia();

        materia.setNombreMateria(txtNombre.getText());
        materia.setCiclo(Integer.parseInt(txtCiclo.getText()));
        materia.setHorarioInicio(Time.valueOf(tmtHorarioInicio.getValue()));
        materia.setHorarioFinal(Time.valueOf(tmtHorarioFinal.getValue()));
        materia.setCatedratico(txtCatedratico.getText());
        materia.setSalon(txtSalon.getText());
        materia.setCupoMaximo(spnCupoMax.getValue());
        materia.setCupoMinimo(spnCupoMin.getValue());
        materia.setNota(spnNota.getValue());
        return materiaDao.add(materia);
    }

    @FXML
    void clicRegresar(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    public void seleccionarElemento() {

        if (existeElementoSeleccionado()) {

            String id = String.valueOf(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getIdMateria());

            txtId.setText(id);

            txtNombre.setText(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getNombreMateria());

            txtCiclo.setText(String.valueOf(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getCiclo()));

            tmtHorarioInicio.setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getHorarioInicio().toLocalTime());

            tmtHorarioFinal.setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getHorarioFinal().toLocalTime());

            txtCatedratico.setText(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getCatedratico());

            txtSalon.setText(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getSalon());

            spnCupoMax.getValueFactory().setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getCupoMaximo());

            spnCupoMin.getValueFactory().setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getCupoMinimo());

            spnNota.getValueFactory().setValue(((Materia) tblMaterias.getSelectionModel().getSelectedItem()).getNota());
        }
    }

    public boolean existeElementoSeleccionado() {
        return (tblMaterias.getSelectionModel().getSelectedItem() != null);

    }

    public void initialize(URL url, ResourceBundle rb) {
        valueFactoryCupoMaximo = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 50, 0);
        spnCupoMax.setValueFactory(valueFactoryCupoMaximo);

        valueFactoryCupoMinimo = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 15, 0);
        spnCupoMin.setValueFactory(valueFactoryCupoMinimo);

        valueFactoryNota = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 70, 0);
        spnNota.setValueFactory(valueFactoryNota);

        cargarDatos();
        contador();
    }

    public void cargarDatos() {
        MateriaDaoImpl materiaDaoImpl = new MateriaDaoImpl();
        tblMaterias.setItems(materiaDaoImpl.getAll());
        colId.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("idMateria"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("nombreMateria"));
        colCiclo.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("ciclo"));
        ColHorarioInicio.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("horarioInicio"));
        colHorarioFinal.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("horarioFinal"));
        colCatedratico.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("catedratico"));
        colSalon.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("salon"));
        ColCupoMaximo.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("cupoMaximo"));
        ColCupoMinimo.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("cupoMinimo"));
        colNota.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("nota"));
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

}
