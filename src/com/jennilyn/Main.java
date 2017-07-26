package com.jennilyn;

import com.jennilyn.Helpers.DatabaseManager;
import com.jennilyn.Model.Stat;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
	    Class.forName("org.sqlite.JDBC");

	    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            DatabaseManager db = new DatabaseManager(connection);
            Statement statement = db.getStatement();

            db.dropStatsTable();
            db.createStatsTable();

            Stat lukeBaseballStats = new Stat("Luke", 24, 0, statement);
            lukeBaseballStats.saveStat();

            Stat joeMontana = new Stat("Joe Montana", 750, 2, statement);
            joeMontana.saveStat();

            ResultSet rs = statement.executeQuery("SELECT * FROM stats");
            while (rs.next()) {
                String name = rs.getString("name");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                System.out.printf("%s %s %s", name, wins, losses);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your database connections.");
            ex.printStackTrace();
        }
    }
}
