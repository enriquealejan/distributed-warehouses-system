package com.empresa.gui;

import com.empresa.modelos.Almacen;
import com.empresa.modelos.Producto;
import com.empresa.modelos.Registro;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class AlmacenGUI extends Application {

    private static final String BASE_URL_ALMACEN = "http://localhost:8080/api/almacenes";
    private static final String BASE_URL_PRODUCTO = "http://localhost:8080/api/productos";
    private static final String BASE_URL_REGISTRO = "http://localhost:8080/api/registros";
    private final RestTemplate restTemplate;

    public AlmacenGUI() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            TabPane tabPane = new TabPane();

            Tab almacenTab = new Tab("Gestión de Almacenes", createAlmacenLayout());
            Tab productoTab = new Tab("Gestión de Productos", createProductoLayout());
            Tab registroTab = new Tab("Gestión de Registros", createRegistroLayout());

            tabPane.getTabs().addAll(almacenTab, productoTab, registroTab);

            Scene scene = new Scene(tabPane, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestión de Almacenes, Productos y Registros");
            primaryStage.setOnCloseRequest(e -> Platform.exit());
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    private VBox createAlmacenLayout() {
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

        TableColumn<Almacen, String> nameColumn = new TableColumn<>("Nombre del Almacén");
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));

        TableColumn<Almacen, String> locationColumn = new TableColumn<>("Ubicación");
        locationColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUbicacion()));

        table.getColumns().addAll(idColumn, nameColumn, locationColumn);

        addButton.setOnAction(e -> {
            String id = idField.getText();
            String nombre = almacenField.getText();
            String ubicacion = ubicacionField.getText();

            if (!nombre.isEmpty() && !ubicacion.isEmpty()) {
                try {
                    agregarAlmacen(id, nombre, ubicacion);
                    limpiarCampos(idField, almacenField, ubicacionField);
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Almacén agregado exitosamente.");
                    actualizarTablaAlmacenes(table);
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
                    actualizarTablaAlmacenes(table);
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
                    actualizarTablaAlmacenes(table);
                } catch (Exception ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el almacén: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "El ID es requerido para eliminar un almacén.");
            }
        });

        viewButton.setOnAction(e -> actualizarTablaAlmacenes(table));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                titleLabel, idField, almacenField, ubicacionField,
                addButton, updateButton, deleteButton, viewButton, table
        );

        return layout;
    }

    private VBox createProductoLayout() {
        Label titleLabel = new Label("Gestión de Productos");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField idField = new TextField();
        idField.setPromptText("ID del Producto (opcional para agregar)");

        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre del Producto");

        TextField descripcionField = new TextField();
        descripcionField.setPromptText("Descripción del Producto");

        TextField stockField = new TextField();
        stockField.setPromptText("Stock del Producto");

        TextField stockMinimoField = new TextField();
        stockMinimoField.setPromptText("Stock Mínimo");

        Button addButton = new Button("Agregar Producto");
        Button updateButton = new Button("Actualizar Producto");
        Button deleteButton = new Button("Eliminar Producto");
        Button viewButton = new Button("Ver Productos");

        TableView<Producto> table = new TableView<>();

        TableColumn<Producto, String> idColumn = new TableColumn<>("ID del Producto");
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));

        TableColumn<Producto, String> nombreColumn = new TableColumn<>("Nombre del Producto");
        nombreColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));

        TableColumn<Producto, String> descripcionColumn = new TableColumn<>("Descripción");
        descripcionColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));

        TableColumn<Producto, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStock()).asObject());

        table.getColumns().addAll(idColumn, nombreColumn, descripcionColumn, stockColumn);

        addButton.setOnAction(e -> {
            String id = idField.getText();
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            int stock;
            int stockMinimo;

            try {
                stock = Integer.parseInt(stockField.getText());
                stockMinimo = Integer.parseInt(stockMinimoField.getText());
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "Stock y Stock Mínimo deben ser números válidos.");
                return;
            }

            if (!nombre.isEmpty() && !descripcion.isEmpty()) {
                try {
                    agregarProducto(id, nombre, descripcion, stock, stockMinimo);
                    limpiarCampos(idField, nombreField, descripcionField, stockField, stockMinimoField);
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Producto agregado exitosamente.");
                    actualizarTablaProductos(table);
                } catch (Exception ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "No se pudo agregar el producto: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "El nombre y la descripción no pueden estar vacíos.");
            }
        });

        updateButton.setOnAction(e -> {
            String id = idField.getText();
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            int stock;
            int stockMinimo;

            try {
                stock = Integer.parseInt(stockField.getText());
                stockMinimo = Integer.parseInt(stockMinimoField.getText());
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "Stock y Stock Mínimo deben ser números válidos.");
                return;
            }

            if (!id.isEmpty() && !nombre.isEmpty() && !descripcion.isEmpty()) {
                try {
                    actualizarProducto(id, nombre, descripcion, stock, stockMinimo);
                    limpiarCampos(idField, nombreField, descripcionField, stockField, stockMinimoField);
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Producto actualizado exitosamente.");
                    actualizarTablaProductos(table);
                } catch (Exception ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el producto: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "ID, nombre y descripción son requeridos para actualizar.");
            }
        });

        deleteButton.setOnAction(e -> {
            String id = idField.getText();

            if (!id.isEmpty()) {
                try {
                    eliminarProducto(id);
                    limpiarCampos(idField, nombreField, descripcionField, stockField, stockMinimoField);
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Producto eliminado exitosamente.");
                    actualizarTablaProductos(table);
                } catch (Exception ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el producto: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "El ID es requerido para eliminar un producto.");
            }
        });

        viewButton.setOnAction(e -> actualizarTablaProductos(table));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                titleLabel, idField, nombreField, descripcionField, stockField, stockMinimoField,
                addButton, updateButton, deleteButton, viewButton, table
        );

        return layout;
    }

    private VBox createRegistroLayout() {
        Label titleLabel = new Label("Gestión de Registros");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField idField = new TextField();
        idField.setPromptText("ID del Registro (opcional para agregar)");

        TextField productoIdField = new TextField();
        productoIdField.setPromptText("ID del Producto");

        TextField tipoMovimientoField = new TextField();
        tipoMovimientoField.setPromptText("Tipo de Movimiento (ENTRADA/SALIDA)");

        TextField cantidadField = new TextField();
        cantidadField.setPromptText("Cantidad");


        Button addButton = new Button("Agregar Registro");
        Button deleteButton = new Button("Eliminar Registro");
        Button viewButton = new Button("Ver Registros");

        TableView<Registro> table = new TableView<>();

        TableColumn<Registro, String> idColumn = new TableColumn<>("ID del Registro");
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));

        TableColumn<Registro, String> productoIdColumn = new TableColumn<>("ID del Producto");
        productoIdColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIdProducto()));

        TableColumn<Registro, String> tipoMovimientoColumn = new TableColumn<>("Tipo de Movimiento");
        tipoMovimientoColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTipoMovimiento()));

        TableColumn<Registro, Integer> cantidadColumn = new TableColumn<>("Cantidad");
        cantidadColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCantidad()).asObject());

        TableColumn<Registro, String> fechaMovimientoColumn = new TableColumn<>("Fecha de Movimiento");
        //fechaMovimientoColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFechaMovimiento().toString()));
        //fechaMovimientoColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty());

        fechaMovimientoColumn.setCellValueFactory(data -> {
            LocalDateTime fecha = data.getValue().getFechaMovimiento();
            return new javafx.beans.property.SimpleStringProperty(fecha != null ? fecha.toString() : "");
        });

        table.getColumns().addAll(idColumn, productoIdColumn, tipoMovimientoColumn, cantidadColumn, fechaMovimientoColumn);

        addButton.setOnAction(e -> {
            String id = idField.getText();
            String productoId = productoIdField.getText();
            String tipoMovimiento = tipoMovimientoField.getText();
            int cantidad;

            try {
                cantidad = Integer.parseInt(cantidadField.getText());
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "La cantidad debe ser un número válido.");
                return;
            }

            if (!productoId.isEmpty() && !tipoMovimiento.isEmpty()) {
                try {
                    agregarRegistro(id, productoId, tipoMovimiento, cantidad);
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Registro agregado exitosamente.");
                    actualizarTablaRegistros(table);
                } catch (Exception ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "No se pudo agregar el registro: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "Todos los campos son obligatorios.");
            }
        });

        deleteButton.setOnAction(e -> {
            String id = idField.getText();

            if (!id.isEmpty()) {
                try {
                    eliminarRegistro(id);
                    limpiarCampos(idField, productoIdField, tipoMovimientoField, cantidadField);
                    showAlert(Alert.AlertType.INFORMATION, "Éxito", "Registro eliminado exitosamente.");
                    actualizarTablaRegistros(table);
                } catch (Exception ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el registro: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Advertencia", "El ID es requerido para eliminar un registro.");
            }
        });

        viewButton.setOnAction(e -> actualizarTablaRegistros(table));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                titleLabel, idField, productoIdField, tipoMovimientoField, cantidadField,
                addButton, deleteButton, viewButton, table
        );

        return layout;
    }

    private void actualizarTablaAlmacenes(TableView<Almacen> table) {
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

    private void actualizarTablaProductos(TableView<Producto> table) {
        try {
            List<Producto> productos = obtenerProductos();
            Platform.runLater(() -> table.getItems().setAll(productos));
        } catch (Exception ex) {
            Platform.runLater(() -> {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo obtener la lista de productos: " + ex.getMessage());
                ex.printStackTrace();
            });
        }
    }

    private void agregarAlmacen(String id, String nombre, String ubicacion) {
        Almacen nuevoAlmacen = new Almacen();
        if (id != null && !id.isEmpty()) nuevoAlmacen.setId(id);
        nuevoAlmacen.setNombre(nombre);
        nuevoAlmacen.setUbicacion(ubicacion);
        restTemplate.postForObject(BASE_URL_ALMACEN, nuevoAlmacen, Void.class);
    }

    private void actualizarAlmacen(String id, String nombre, String ubicacion) {
        Almacen almacenActualizado = new Almacen();
        almacenActualizado.setId(id);
        almacenActualizado.setNombre(nombre);
        almacenActualizado.setUbicacion(ubicacion);
        restTemplate.put(BASE_URL_ALMACEN + "/" + id, almacenActualizado);
    }

    private void eliminarAlmacen(String id) {
        restTemplate.delete(BASE_URL_ALMACEN + "/" + id);
    }

    private List<Almacen> obtenerAlmacenes() {
        Almacen[] almacenes = restTemplate.getForObject(BASE_URL_ALMACEN, Almacen[].class);
        return Arrays.asList(almacenes != null ? almacenes : new Almacen[0]);
    }

    private void agregarProducto(String id, String nombre, String descripcion, int stock, int stockMinimo) {
        Producto nuevoProducto = new Producto();
        if (id != null && !id.isEmpty()) nuevoProducto.setId(id);
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setDescripcion(descripcion);
        nuevoProducto.setStock(stock);
        nuevoProducto.setStockMinimo(stockMinimo);
        restTemplate.postForObject(BASE_URL_PRODUCTO, nuevoProducto, Void.class);
    }

    private void actualizarProducto(String id, String nombre, String descripcion, int stock, int stockMinimo) {
        Producto productoActualizado = new Producto();
        productoActualizado.setId(id);
        productoActualizado.setNombre(nombre);
        productoActualizado.setDescripcion(descripcion);
        productoActualizado.setStock(stock);
        productoActualizado.setStockMinimo(stockMinimo);
        restTemplate.put(BASE_URL_PRODUCTO + "/" + id, productoActualizado);
    }

    private void eliminarProducto(String id) {
        restTemplate.delete(BASE_URL_PRODUCTO + "/" + id);
    }

    private List<Producto> obtenerProductos() {
        Producto[] productos = restTemplate.getForObject(BASE_URL_PRODUCTO, Producto[].class);
        return Arrays.asList(productos != null ? productos : new Producto[0]);
    }

    private void agregarRegistro(String id, String productoId, String tipoMovimiento, int cantidad) {
        try {
            Registro nuevoRegistro = new Registro();
            if (id != null && !id.isEmpty()) nuevoRegistro.setId(id);
            nuevoRegistro.setIdProducto(productoId);
            nuevoRegistro.setTipoMovimiento(tipoMovimiento);
            nuevoRegistro.setCantidad(cantidad);
            nuevoRegistro.setFechaMovimiento(LocalDateTime.now());

            restTemplate.postForObject(BASE_URL_REGISTRO, nuevoRegistro, List.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void eliminarRegistro(String id) {
        restTemplate.delete(BASE_URL_REGISTRO + "/" + id);
    }

    private void actualizarTablaRegistros(TableView<Registro> table) {
        try {
            List<Registro> registros = obtenerRegistros();
            Platform.runLater(() -> table.getItems().setAll(registros));
        } catch (Exception ex) {
            Platform.runLater(() -> {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo obtener la lista de registros: " + ex.getMessage());
                ex.printStackTrace();
            });
        }
    }

    private List<Registro> obtenerRegistros() {
        Registro[] registros = restTemplate.getForObject(BASE_URL_REGISTRO, Registro[].class);
        return Arrays.asList(registros != null ? registros : new Registro[0]);
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
