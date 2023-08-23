package dao;

import enums.LicenseType;
//import exception.LicenceException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modele.Licence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Crypto;
import utils.GlobalFonctions;

/**
 *
 * @author JEANNE
 */
@Stateless
public class LicenceDao extends AbstractJpaDAO<Licence> {

    static Logger logger = LoggerFactory.getLogger(LicenceDao.class);
    //constantes des etats de licence
    public static final int LICENCE_GOOD = 0;
    public static final int LICENCE_EXPIRED = 1;
    public static final int LICENCE_NOT_EXIST = 2;
    public static final int LICENCE_BAD_KEY = 3;
    public static final int LICENCE_BAD_DATE_SERVER = 4;
    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<Licence> getEntityClass() {
        return Licence.class;
    }

    /**
     * Recuperer la licence de l'application
     *
     * @return
     */
    public Licence getLicence() {
        String query = "Select l from Licence l";
        List<Licence> listLicence = em.createQuery(query).getResultList();

        if (listLicence == null || listLicence.isEmpty()) {
            return null;
        }
        return listLicence.get(0);

    }

    public Licence getDernierLicence() {
        String query = "Select  l from Licence l";
        int taille = 0;
        List<Licence> listLicence = em.createQuery(query).getResultList();

        if (listLicence == null || listLicence.isEmpty()) {
            return null;
        }
        taille = listLicence.size();
        taille = taille - 1;

        return listLicence.get(taille);

    }

    /**
     * create a licence
     *
     * @param typeLicence
     * @param numDays
     * @return <code>Licence</code>
     *
     */
    public Licence createLicence(LicenseType typeLicence, int numDays) {
        Licence licence = new Licence();
        licence.setLicenseType(typeLicence);
        licence.setMac(this.getMacAddress());
        licence.setDateCreation(new Date());
        licence.setVersion("2.10");

        if (typeLicence.equals(LicenseType.DEMO)) {
            licence.setDuree(numDays);
        }
        em.persist(licence);
        return licence;
    }

    public Licence createLicence(LicenseType typeLicence, String key, Date dateLimite) {
        Licence licence = new Licence();
        licence.setLicenseType(typeLicence);
        licence.setMac(this.getMacAddress());
        licence.setDateCreation(new Date());

        licence.setVersion("1.0");

        if (typeLicence.equals(LicenseType.FULL)) {
            licence.setDateDateFin(dateLimite);
            licence.setLicenseNumber(Crypto.cryptoMD5(key));
        }
        em.persist(licence);
        return licence;
    }

    public Licence getLicenceByLicenseType() {
        Map<String, Object> param = new HashMap<>();
        param.put("LicenseType", LicenseType.FULL);
        String query = "select l from Licence l where l.LicenseType= :LicenseType";
        return super.findEntityByUsingQuery(query, param);
    }

    /**
     * Insertion ou MAJ de la licence en Ligne
     *
     * @param licence
     * @return
     * @throws LicenceException
     * @throws SQLException
     */
//    public Licence insertInLicenceServer(Licence licence) throws LicenceException, SQLException {
//        Connection con = GlobalFonctions.getRemoteConnection("192.185.48.219", "3306", "betahost_licenceserver", "betahost_lserver", "lserver@");
//        if (con == null) {
//            throw new LicenceException("Error to connect on Online Server Licence");
//        }
//        // create a sql date object so we can use it in our INSERT statement
//        Calendar calendar = Calendar.getInstance();
//        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
//       // Statement stat=con.createStatement();
//
//        //je fabrique la licence avec la mac
//        String licenceKey = this.formatLicenceKey(Crypto.cryptoMD5(licence.getMac()));
//
//        String licenceOnServer = this.getLicenceFromLicenceServer(licence.getMac());
//        int result;
//        if (licenceOnServer == null) {
//            String query = "insert into licenceserver(licencenumber,macadr,nomapplication,typelicence,datecreation,duree,version) values (?,?,?,?,?,?,?)";
//
//            PreparedStatement stm = con.prepareStatement(query);
//            stm.setString(1, licenceKey);
//            stm.setString(2, licence.getMac());
//            stm.setString(3, "globalschool");
//            stm.setString(4, licence.getLicenseType().name());
//            stm.setDate(5, startDate);
//            stm.setInt(6, licence.getDuree());
//            stm.setString(7, licence.getVersion());
//            result = stm.executeUpdate();
//            //con.close();
//        } else {
//            String query="update licenceserver set typelicence='"+licence.getLicenseType().name()+"' WHERE nomapplication='globalschool' and macadr='"+licence.getMac()+"'";
//            Statement stat=con.createStatement();
//            result=stat.executeUpdate(query);
//           
//       }
//        return  (result==1?licence:null);
//    }
    /**
     * Recuperer La Cle de la licence en ligne
     *
     * @param mac
     * @return
     * @throws LicenceException
     * @throws SQLException
     */
//    public String getLicenceFromLicenceServer(String mac) throws LicenceException, SQLException {
//        Connection con = GlobalFonctions.getRemoteConnection("192.185.48.219", "3306", "betahost_licenceserver", "betahost_lserver", "lserver@");
//        if (con == null) {
//            throw new LicenceException("Error to connect on Online Server Licence");
//        }
//        Statement stat = con.createStatement();
//        //je recherche la licence en ligne
//        String query = "select * from  licenceserver where nomapplication='globalschool' and macadr='" + mac + "'";
//
//        ResultSet rs = stat.executeQuery(query);
//        String licenceNumber = null;
//        while (rs.next()) {
//            licenceNumber = rs.getString("licencenumber");
//
//        }
//        return licenceNumber;
//    }
    /**
     * Tester la validite de la licence
     *
     * @param l
     * @return
     */
    public int validateLicence(Licence l) {
        int statut = 0;
        if (l.getLicenseType().equals(LicenseType.DEMO)) {
            //recuperation de la date installation en millisecond
            long dateInstallation = l.getDateCreation().getTime();
            //conversion du nombre de jour en millisecond
            long duree = (long) l.getDuree() * 24 * 60 * 60 * 1000;

            long expiredTime = dateInstallation + duree;

            //current time
            long actuallyTime = System.currentTimeMillis();
            //on verifie que la date en cours est bien superieur a la date install
            if (actuallyTime < dateInstallation) {
                logger.error("PLEASE CHECK YOUR SYSTEM DATE.");
                statut = LICENCE_BAD_DATE_SERVER;
                return statut;

            }
            statut = (actuallyTime > expiredTime) ? LICENCE_EXPIRED : LICENCE_GOOD;

        }

        if (l.getLicenseType().equals(LicenseType.FULL)) {
            long dateCreation = l.getDateCreation().getTime();
            long dateLimite = l.getDateDateFin().getTime();
            //current time
            long actuallyTime = System.currentTimeMillis();
            //on verifie que la date en cours est bien superieur a la date install
            if (actuallyTime < dateCreation) {
                logger.error("PLEASE CHECK YOUR SYSTEM DATE.");
                statut = LICENCE_BAD_DATE_SERVER;
                return statut;
            }
//            String mac = l.getMac();
//            String licenceNumber = l.getLicenseNumber();
//            String cryptMac = Crypto.cryptoMD5(mac);
            statut = (actuallyTime > dateLimite) ? LICENCE_EXPIRED : LICENCE_GOOD;
        } else if (l == null) {
            statut = LICENCE_NOT_EXIST;
        }

        return statut;
    }

    /**
     * Formater la CLE de la licence
     *
     * @param key
     * @return
     */
    public String formatLicenceKey(String key) {
        //recuperation de 11 caracteres
        String licence = key.substring(0, 11).toUpperCase();
        return licence;
    }

    /**
     * Recuperer l'adresse MAC du Server
     *
     * @return
     */
    public String getMacAddress() {
        InetAddress ip;
        StringBuilder sb = new StringBuilder();
        try {
            InetAddress address = InetAddress.getLocalHost();

            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();

            // Afficher l'adresse Mac
            try {
               for (int i = 0; i < mac.length; i++) {
                System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            } 
            } catch (Exception e) {
                sb.append("");
            }
            


        } catch (UnknownHostException | SocketException e) {

            e.printStackTrace();

        }
        return sb.toString();

    }

}
