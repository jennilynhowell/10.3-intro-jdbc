package com.jennilyn;

import com.jennilyn.Helpers.DatabaseManager;
import com.jennilyn.Model.Stat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
	    Class.forName("org.sqlite.JDBC");

	    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {

            //let's make a UI for this app

            welcomeMenu();

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your database connections.");
            ex.printStackTrace();
        }
    }

    public static void welcomeMenu() {
        System.out.println("\n================");
        System.out.println("Welcome to Stat Database 5400! What would you like to do?");
        System.out.println("1) Show all stats.");
        System.out.println("2) Add a new stat.");
        System.out.println("3) Update an existing stat.");
        System.out.println("================");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                System.out.println("Now showing all stat data.");
                break;
            case 2:
                System.out.println("What's your new stat?");
                break;
            case 3:
                System.out.println("Which stat would you like to update?");
                break;
            default:
                System.out.println("Sorry, that's not an option on this menu.");
                break;
        }

        welcomeMenu();
    }
}
