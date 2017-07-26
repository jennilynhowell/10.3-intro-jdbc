package com.jennilyn;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
	    Class.forName("org.sqlite.JDBC");

	    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS stats");
            statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY KEY, string STRING, wins INTEGER, losses INTEGER)");
        } catch (SQLException ex) {
            System.out.println("Something went wrong with your database connections.");
        }
    }
}
