package com.karpenko;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Олег on 11.10.2016.
 */
public class DBConnect {
    private static final String URL = "jdbc:mysql://"+ARMs.serverIp+":3306/tabeldb"; // URL базы
    private static final String userName = "root";
    private static final String password = "root";
    Connection connection;

    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    DBConnect() {
        try {
            // с помощью драйверМенеджера получаем подлючение
            connection = DriverManager.getConnection(URL,userName,password);
            System.out.println("подключились");
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null,"Ошибка поключения к серверу");
        }
    }
}