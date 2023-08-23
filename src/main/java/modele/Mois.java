/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import enums.TypeMois;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author frankjiatou
 */
@Entity
@Table(name = "orclass_mois", uniqueConstraints = @UniqueConstraint(columnNames = {"Code", "mois", "anneeid"}))
public class Mois implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(max = 50)
    @Column(name = "Code")
    private String code;

    @Column(name = "mois")
    @Enumerated(EnumType.STRING)
    private TypeMois typeMois;
    @Column(name = "numero")
    private int numero;
    private BigInteger jourOuvrable = BigInteger.ZERO;

    @JoinColumn(name = "anneeid", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Annee annee;

    
    @Transient
    Property p;

    public BigInteger getJourOuvrable() {
        return jourOuvrable;
    }

    public void setJourOuvrable(BigInteger jourOuvrable) {
        this.jourOuvrable = jourOuvrable;
    }

    public Mois(String code, int numero, TypeMois typeMois) {
        this.code = code;

        this.numero = numero;
        this.typeMois = typeMois;
    }

    public Mois() {
        annee = new Annee();
        this.p=new Property();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TypeMois getTypeMois() {
        return typeMois;
    }

    public void setTypeMois(TypeMois typeMois) {
        this.typeMois = typeMois;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

   

    public Property getP() {
        return p;
    }

    public void setP(Property p) {
        this.p = p;
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
        if (!(object instanceof Mois)) {
            return false;
        }
        Mois other = (Mois) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return typeMois.name();
    }

}
