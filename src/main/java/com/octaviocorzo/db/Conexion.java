package com.octaviocorzo.db;

/**
 *
 * @author informatica
 */
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private final String URL;
    private final String IP_SERVER;
    private final String PORT;
    private final String DB;
    private final String USER;
    private final String PASSWORD;
    private Connection conexion;

    private static Conexion instancia;

    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Conexion() {
        IP_SERVER = "localhost";
        PORT = "3306";
        DB = "db_control_materia_in5bm";
        USER = "kinal";
        PASSWORD = "admin";

        URL = "jdbc:mysql://" + IP_SERVER + ":" + PORT + "/" + DB + "?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa!");

            DatabaseMetaData dma = conexion.getMetaData();
            System.out.println("\nConnected to: " + dma.getURL());
            System.out.println("\nDriver: " + dma.getDriverName());
            System.out.println("\nVersion: " + dma.getDriverVersion());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (CommunicationsException e) {
            System.err.println("No se puede establecer comunicacion con el servidor de MySQL");
            System.err.println("Verifique que el servicio de MySQL este levantado,"
                    + "que la IP_SERVER y el puerto este correcto.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.print("Se produjo un error de tipo SQL Exception");
            System.out.println("SQL State:" + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            System.out.println("\n");
        } catch (Exception e) {
            System.err.println("Se produjo un error al intentar establecer una conexion con la base de datos");
            e.printStackTrace();
        }

    }

    public Connection getConexion() {
        return conexion;
    }
    

}
