import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
    private static final String JNDI_NAME = "java:comp/env/jdbc/seniorcare";

    public static Connection getConnection() throws SQLException {
        try {
            // Obtain the JNDI context
            Context ctx = new InitialContext();
            // Look up the DataSource
            DataSource ds = (DataSource) ctx.lookup(JNDI_NAME);
            // Get a connection from the DataSource
            return ds.getConnection();
        } catch (NamingException e) {
            throw new SQLException("JNDI lookup failed for " + JNDI_NAME, e);
        }
    }
}
