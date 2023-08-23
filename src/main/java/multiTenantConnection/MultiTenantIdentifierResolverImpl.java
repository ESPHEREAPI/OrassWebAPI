/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multiTenantConnection;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 *
 * @author JIATOU FRANCK
 */
public class MultiTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    public static ThreadLocal<String> tenantIdentifier = new ThreadLocal<>();

    @Override
    public String resolveCurrentTenantIdentifier() {
        String currentTenantIdentifier = tenantIdentifier.get();
        if (currentTenantIdentifier == null) {
            currentTenantIdentifier = "public";
        }
        return currentTenantIdentifier;

    }

    @Override
    public boolean validateExistingCurrentSessions() {
      return true;
    }

}
