
package Licence;

import java.io.*;
import java.util.*;
/**
 * This is your license object.  It will be written to disk and checked with the program starts.  
 * It contains all the information the managers will need to check the license.
 * @author fabrice
 */
public class License implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String licenseNumber;
    private TypeLicense licenseType;
    private Date expiration;
    private String version;

    public License() {
        name = "";
        email = "";
        licenseNumber = "";
        expiration = new Date();
        version = "";
        licenseType = TypeLicense.TRIAL;
    }

    public License(String name, String email, String licenseNumber, Date expiration, TypeLicense licenseType, String version) {
        this.name = name;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.expiration = expiration;
        this.licenseType = licenseType;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public TypeLicense getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(TypeLicense licenseType) {
        this.licenseType = licenseType;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

   
}
