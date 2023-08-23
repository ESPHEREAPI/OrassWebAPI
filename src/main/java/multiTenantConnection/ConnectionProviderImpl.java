/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multiTenantConnection;

import dao.AbstractJpaDAO;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

/**
 *
 * @author JIATOU FRANCK
 */
public class ConnectionProviderImpl implements ConnectionProvider {

    @Override
    public Connection getConnection() throws SQLException {
        String jndiName = AbstractJpaDAO.PersistanceUnit;
        DataSource ds = null;
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup(jndiName);
            return ds.getConnection();
        } catch (NamingException e) {
            return null;
        }
    }

    @Override
    public void closeConnection(Connection cnctn) throws SQLException {
      cnctn.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
      return false;
    }

    @Override
    public boolean isUnwrappableAs(Class type) {
       return false;
    }

    @Override
    public <T> T unwrap(Class<T> type) {
       return null;
    }

}
