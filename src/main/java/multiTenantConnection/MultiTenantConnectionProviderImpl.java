/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multiTenantConnection;

import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.Stoppable;

/**
 *
 * @author JIATOU FRANCK
 */
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider, Stoppable {

    private final ConnectionProvider connectionProvider = new ConnectionProviderImpl();

    @Override
    public Connection getAnyConnection() throws SQLException {
        return connectionProvider.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection cnctn) throws SQLException {
        connectionProvider.closeConnection(cnctn);
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection conn = getAnyConnection();
        setSchema(tenantIdentifier, conn);
        return conn;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection cnctn) throws SQLException {
        setSchema("public", cnctn);
        releaseAnyConnection(cnctn);
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

    @Override
    public void stop() {

    }

    private void setSchema(String tenantIdentifier, Connection conn) {
        try {
            conn.createStatement().execute("SET SCHEMA '" + tenantIdentifier + "'");
        } catch (SQLException e) {
            throw new HibernateException("MultiTenantConnectionProviderImpl::Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
        }
    }
}
