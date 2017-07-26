package com.jennilyn.Helpers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    Statement statement;

    //method throws SQLException and you must handle it in main.
    public DatabaseManager(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public void dropStatsTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS stats");

    }

    public void createStatsTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER)");
    }

    //statement.executeUpdate("INSERT INTO stats (name, wins, losses) VALUES('Jennilyn', 10, 2)");
}
