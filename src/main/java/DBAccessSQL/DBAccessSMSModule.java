package DBAccessSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccessSMSModule {
    public static Connection SMSModule;
    public static Connection getSMSModule() {
        try {
            if (SMSModule == null || SMSModule.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                SMSModule = DriverManager.getConnection("jdbc:sqlserver://10.195.105.247; encrypt=false; trustedServerCertificate=false", "Training", "Aa123456");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return SMSModule;
    }
}
