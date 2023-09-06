package Com.dnb.jdbcdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
@Component
public class DBUtils {

public DBUtils(){

}
@Autowired
private Environment environment;

    public Optional<Connection> getConnection(){

        try {
            Connection connection = DriverManager.getConnection(environment.getProperty("jdbc.url"),environment.getProperty("jdbc.username"),environment.getProperty("jdbc.password"));
            return Optional.of(connection);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(Connection connection){

        try {
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
