package com.empresa.gestion_almacen;

import java.sql.DriverManager;
import java.sql.SQLException;


public class bd_create_test {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:warehouse.db";

        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // SQL statement for creating a new table
        var sql = "CREATE TABLE products ("
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "	name text NOT NULL,"
                + "	description TEXT,"
                + " stock INTEGER NOT NULL,"
                + " minimum_stock INTEGER NOT NULL"
                + ");";

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        var id = new int[] {1, 2, 3};
        var name = new String[] {"product1", "product2", "product3"};
        var description = new String[] {"Puta mierda", "Puta mierda", "Puta mierda"};
        var stock = new int[] {1000,2000,3000};
        var minimum_stock = new int[] {100,200,300};

        String insert_query = "INSERT INTO products(id, name, description, stock, minimum_stock) VALUES(?,?,?,?,?)";

        try (var conn = DriverManager.getConnection(url);
             var stmt = conn.prepareStatement(insert_query)) {

            for(int i = 0; i < 3; i++){
                stmt.setInt(1, id[i]);
                stmt.setString(2, name[i]);
                stmt.setString(3, description[i]);
                stmt.setInt(4, stock[i]);
                stmt.setInt(5, minimum_stock[i]);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // Consulta SQL para leer datos (ajústala según tu esquema)
        String query = "SELECT * FROM products";

        try (var connection = DriverManager.getConnection(url);
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query)) {

            // Recorrer los resultados de la consulta
            while (resultSet.next()) {
                // Accede a las columnas según sus nombres o índices
                int id_product = resultSet.getInt("id");
                String nombre = resultSet.getString("name");
                String descripcion = resultSet.getString("description");
                int stock_product = resultSet.getInt("stock");
                int minimum_stock_product = resultSet.getInt("minimum_stock");
                System.out.println("ID: " + id + ", Nombre: " + nombre + " Descripcion: " + descripcion + " Stock: " + stock_product + " Stock minimo: " + minimum_stock_product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}