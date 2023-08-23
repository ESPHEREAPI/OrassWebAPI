package modele;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fabrice
 */
@Entity
@Table(name = "ESPH_historiques")
@NamedQueries({
    @NamedQuery(name = orclass_historique.FIND_BY_LAST_CONNEXION, query = "SELECT c FROM ConnectionLogger c ORDER BY c.id")
})
public class orclass_historique implements DAOEntry {

    public static final String FIND_BY_LAST_CONNEXION = "orclass_historique.getLastConnexion";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id_logger;

     @Column( name="user_name")
    private String usert_insert;

    private String role;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateConnexion;
   
    private  Boolean on_line=Boolean.FALSE;

    public orclass_historique() {
    }

    public orclass_historique(Long Id) {
        this.id_logger = Id;
    }

    public Long getId() {
        return id_logger;
    }

    public void setId(Long id) {
        this.id_logger = id;
    }

    public String getUtilisateur() {
        return usert_insert;
    }

    public void setUtilisateur(String utilisateur) {
        this.usert_insert = utilisateur;
    }

 

//    public Date getDateConnexion() {
//        return dateConnexion;
//    }
//
//    public void setDateConnexion(Date dateConnexion) {
//        this.dateConnexion = dateConnexion;
//    }

    public boolean isOn_line() {
        return on_line;
    }

    public void setOn_line(boolean online) {
        this.on_line = online;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id_logger);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final orclass_historique other = (orclass_historique) obj;
        if (!Objects.equals(this.id_logger, other.id_logger)) {
            return false;
        }
        return true;
    }


}
