package com.jennilyn;

import com.jennilyn.Helpers.DatabaseManager;
import com.jennilyn.Model.Stat;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
	    Class.forName("org.sqlite.JDBC");

	    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {

            //let's make a UI for this app
            DatabaseManager dbm = new DatabaseManager(connection);
            welcomeMenu(dbm);

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your database connections.");
            ex.printStackTrace();
        }
    }

    public static void welcomeMenu(DatabaseManager dbm) throws SQLException {
        System.out.println("================");
        System.out.println("Welcome to Stat Database 5400! What would you like to do?");
        System.out.println("1) Show all stats.");
        System.out.println("2) Add a new stat.");
        System.out.println("3) Update an existing stat.");
        System.out.println("================\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                System.out.println("Now showing all stat data.");
                List<Stat> results = Stat.findAll(dbm);
                for (Stat stat : results){
                    System.out.println(stat);
                }

                break;

            case 2:
                System.out.println("What's the player's name?");
                String name = scanner.next();

                System.out.println("How many wins?");
                int wins = scanner.nextInt();

                System.out.println("How many losses?");
                int losses = scanner.nextInt();

                new Stat(name, wins, losses, dbm.getStatement()).saveStat();
                System.out.println("Saved!");

                break;

            case 3:
                System.out.println("Which stat would you like to view/update?");
                String searchName = scanner.next();

                Stat result = Stat.findByName(dbm, searchName);
                System.out.println(result);

                System.out.println("Would you like to update this player? Y/N");
                String continueUpdate = scanner.next();

                switch (continueUpdate){
                    case "Y":
                        System.out.println("Okay, what are the updated wins?");
                        int newWins = scanner.nextInt();

                        System.out.println("Updated losses?");
                        int newLosses = scanner.nextInt();

                        result.setWins(newWins);
                        result.setLosses(newLosses);
                        result.updateStat();
                        System.out.println("All done!");
                        break;
                    case "N":
                        System.out.println("Cool, thanks!");
                        break;
                }

                break;

            default:
                System.out.println("Sorry, that's not an option on this menu.");
                break;
        }

        welcomeMenu(dbm);
    }
}
