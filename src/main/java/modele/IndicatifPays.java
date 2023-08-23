/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ESPH_indicatifPays")
public class IndicatifPays implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESPH_SEQ")
    @SequenceGenerator(name = "ESPH_SEQ", sequenceName = "SEQUENCE1", allocationSize = 1)
    @Column(name = "Id")
    private Integer Id;

    @Column(name = "Indicatif", nullable = false, unique = true)
    private String indicatif;

    public IndicatifPays() {
    }

    public IndicatifPays(String indicatif) {
        this.indicatif = indicatif;
    }

    public String getIndicatif() {
        return indicatif;
    }

    public void setIndicatif(String indicatif) {
        this.indicatif = indicatif;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Id != null ? Id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicatifPays)) {
            return false;
        }
        IndicatifPays other = (IndicatifPays) object;
        if ((this.Id == null && other.Id != null) || (this.Id != null && !this.Id.equals(other.Id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IndicatifPays{" + "Indicatif=" + indicatif + '}';
    }

}
