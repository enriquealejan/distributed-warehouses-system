package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;


public class bd_create {

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
    }
}