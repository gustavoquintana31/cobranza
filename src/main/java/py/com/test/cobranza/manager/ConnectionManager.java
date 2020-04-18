package py.com.test.cobranza.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/cobranzas";
        Properties props = new Properties();
        props.setProperty("user","castulo");
        props.setProperty("password","tulocas2007");

        return DriverManager.getConnection(url, props);

    }


}
