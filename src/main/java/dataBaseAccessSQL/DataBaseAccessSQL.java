package dataBaseAccessSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseAccessSQL {
    public static Connection connectionSMS;
    public static Connection getConnectionSMS() {
        try {
            if (connectionSMS == null || connectionSMS.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connectionSMS = DriverManager.getConnection("jdbc:sqlserver://10.195.105.247; encrypt=false ; trusteaservercertificate = false", "Training", "Aa123456");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connectionSMS;
    }
}
