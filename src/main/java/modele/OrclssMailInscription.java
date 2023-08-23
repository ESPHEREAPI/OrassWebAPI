/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JIATOU FRANCK
 */
@Entity
@Table(name = "ESPH_MAILE_INSCR")
@NamedQueries({
    @NamedQuery(name = "OrclssMailInscription.findAll", query = "SELECT o FROM OrclssMailInscription o"),
    @NamedQuery(name = "OrclssMailInscription.findById", query = "SELECT o FROM OrclssMailInscription o WHERE o.id = :id"),
    @NamedQuery(name = "OrclssMailInscription.findByBody", query = "SELECT o FROM OrclssMailInscription o WHERE o.body = :body"),
    @NamedQuery(name = "OrclssMailInscription.findByEmail", query = "SELECT o FROM OrclssMailInscription o WHERE o.email = :email"),
    @NamedQuery(name = "OrclssMailInscription.findBySubject", query = "SELECT o FROM OrclssMailInscription o WHERE o.subject = :subject")})
public class OrclssMailInscription implements DAOEntry {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 1000)
    @Column(name = "BODY")
    private String body;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 500)
    @Column(name = "SUBJECT")
    private String subject;

    public OrclssMailInscription() {
    }

    public OrclssMailInscription(String email, String subject, String body) {
        this.body = body;
        this.email = email;
        this.subject = subject;
    }
    

   
    

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrclssMailInscription)) {
            return false;
        }
        OrclssMailInscription other = (OrclssMailInscription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.OrclssMailInscr[ id=" + id + " ]";
    }
    
}
