package com.jennilyn;

import com.jennilyn.Helpers.DatabaseManager;
import com.jennilyn.Model.Stat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            List<Stat> results = Stat.findAll(db);
            for (Stat stat : results) {
                //ok, let's update all wins to 0, then update them
                //System.out.println(stat);
                stat.setWins(0);
                stat.updateStat();
            }

            results = Stat.findAll(db);
            for (Stat stat : results) {
                System.out.println(stat);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your database connections.");
            ex.printStackTrace();
        }
    }
}
