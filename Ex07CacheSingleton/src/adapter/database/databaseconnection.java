package adapter.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class databaseconnection {
    private static databaseconnection instance;
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/CacheMenager";
    private static final String user = "root";
    private static final String password = "BrUn@1304";

    private databaseconnection(){
        try {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            throw new RuntimeErrorException("Erro ao conectar com o banco de dados", e);
        }
    }

    public static synchronized databaseconnection getInstance(){
        if (instance == null){
            instance = new databaseconnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
