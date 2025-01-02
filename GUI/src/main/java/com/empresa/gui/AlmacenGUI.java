package com.empresa.gui;

import com.empresa.modelos.Almacen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class AlmacenGUI extends Application {

    private static final String BASE_URL = "http://localhost:8080/api/almacenes";
    private final RestTemplate restTemplate;

    public AlmacenGUI() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Crear elementos de la GUI
            Label titleLabel = new Label("Gestión de Almacenes");
            titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            TextField idField = new TextField();
            idField.setPromptText("ID del Almacén (opcional para agregar)");

            TextField almacenField = new TextField();
            almacenField.setPromptText("Nombre del Almacén");

            TextField ubicacionField = new TextField();
            ubicacionField.setPromptText("Ubicación del Almacén");

            Button addButton = new Button("Agregar Almacén");
            Button updateButton = new Button("Actualizar Almacén");
            Button deleteButton = new Button("Eliminar Almacén");
            Button viewButton = new Button("Ver Almacenes");

            TableView<Almacen> table = new TableView<>();

            TableColumn<Almacen, String> idColumn = new TableColumn<>("ID del Almacén");
            idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
            idColumn.setMinWidth(200);

            TableColumn<Almacen, String> nameColumn = new TableColumn<>("Nombre del Almacén");
            nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
            nameColumn.setMinWidth(200);

            TableColumn<Almacen, String> locationColumn = new TableColumn<>("Ubicación");
            locationColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUbicacion()));
            locationColumn.setMinWidth(200);

            table.getColumns().addAll(idColumn, nameColumn, locationColumn);

            // Configurar eventos de los botones
            addButton.setOnAction(e -> {
                String id = idField.getText();
                String nombre = almacenField.getText();
                String ubicacion = ubicacionField.getText();

                if (!nombre.isEmpty() && !ubicacion.isEmpty()) {
                    try {
                        agregarAlmacen(id, nombre, ubicacion);
                        limpiarCampos(idField, almacenField, ubicacionField);
                        showAlert(Alert.AlertType.INFORMATION, "Éxito", "Almacén agregado exitosamente.");
                        actualizarTabla(table);
                    } catch (Exception ex) {
                        showAlert(Alert.AlertType.ERROR, "Error", "No se pudo agregar el almacén: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Advertencia", "El nombre y la ubicación no pueden estar vacíos.");
                }
            });

            updateButton.setOnAction(e -> {
                String id = idField.getText();
                String nombre = almacenField.getText();
                String ubicacion = ubicacionField.getText();

                if (!id.isEmpty() && !nombre.isEmpty() && !ubicacion.isEmpty()) {
                    try {
                        actualizarAlmacen(id, nombre, ubicacion);
                        limpiarCampos(idField, almacenField, ubicacionField);
                        showAlert(Alert.AlertType.INFORMATION, "Éxito", "Almacén actualizado exitosamente.");
                        actualizarTabla(table);
                    } catch (Exception ex) {
                        showAlert(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el almacén: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Advertencia", "ID, nombre y ubicación son requeridos para actualizar.");
                }
            });

            deleteButton.setOnAction(e -> {
                String id = idField.getText();

                if (!id.isEmpty()) {
                    try {
                        eliminarAlmacen(id);
                        limpiarCampos(idField, almacenField, ubicacionField);
                        showAlert(Alert.AlertType.INFORMATION, "Éxito", "Almacén eliminado exitosamente.");
                        actualizarTabla(table);
                    } catch (Exception ex) {
                        showAlert(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el almacén: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Advertencia", "El ID es requerido para eliminar un almacén.");
                }
            });

            viewButton.setOnAction(e -> actualizarTabla(table));

            // Layout principal
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(20));
            layout.getChildren().addAll(
                    titleLabel, idField, almacenField, ubicacionField,
                    addButton, updateButton, deleteButton, viewButton, table
            );

            // Configuración de la ventana principal
            Scene scene = new Scene(layout, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestión de Almacenes");
            primaryStage.setOnCloseRequest(e -> Platform.exit());
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    private void actualizarTabla(TableView<Almacen> table) {
        try {
            List<Almacen> almacenes = obtenerAlmacenes();
            Platform.runLater(() -> table.getItems().setAll(almacenes));
        } catch (Exception ex) {
            Platform.runLater(() -> {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo obtener la lista de almacenes: " + ex.getMessage());
                ex.printStackTrace();
            });
        }
    }

    private void agregarAlmacen(String id, String nombre, String ubicacion) {
        Almacen nuevoAlmacen = new Almacen();
        if (id != null && !id.isEmpty()) nuevoAlmacen.setId(id);
        nuevoAlmacen.setNombre(nombre);
        nuevoAlmacen.setUbicacion(ubicacion);
        restTemplate.postForObject(BASE_URL, nuevoAlmacen, Void.class);
    }

    private void actualizarAlmacen(String id, String nombre, String ubicacion) {
        Almacen almacenActualizado = new Almacen();
        almacenActualizado.setId(id);
        almacenActualizado.setNombre(nombre);
        almacenActualizado.setUbicacion(ubicacion);
        restTemplate.put(BASE_URL + "/" + id, almacenActualizado);
    }

    private void eliminarAlmacen(String id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }

    private List<Almacen> obtenerAlmacenes() {
        Almacen[] almacenes = restTemplate.getForObject(BASE_URL, Almacen[].class);
        return Arrays.asList(almacenes != null ? almacenes : new Almacen[0]);
    }

    private void limpiarCampos(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
