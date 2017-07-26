package com.jennilyn.Helpers;

import com.jennilyn.Model.Stat;

import java.sql.Connection;
import java.sql.ResultSet;
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

    public Statement getStatement() {
        return statement;
    }

    public ResultSet find(String tableName) throws SQLException {
        String formattedSQL = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = statement.executeQuery(formattedSQL);
        return rs;
    }

    public ResultSet findByName(String tableName, String playerName) throws SQLException {
        String formattedSQL = String.format("SELECT * FROM %s WHERE name = '%s'", tableName, playerName);
        ResultSet rs = statement.executeQuery(formattedSQL);
        return rs;
    }


}
