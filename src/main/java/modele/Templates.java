/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "ESPH_template")
public class Templates implements DAOEntry {

    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ESPH_SEQ")
    @SequenceGenerator( name="ESPH_SEQ", sequenceName="SEQUENCE1", allocationSize=1)
    private Long id;

    String menuMode;
    String darkMode;
    String layoutPrimaryColor;
    String componentTheme;
    private String topbarTheme;
    private String menuTheme;
    private String profileMode;
    private String inputStyle;
    private Boolean groupedMenu = Boolean.FALSE;
    private Boolean lightLogo = Boolean.FALSE;
    private Boolean defautTemplate=Boolean.FALSE;
     @JoinColumn(name = "ID_ENTREPRISE", referencedColumnName = "ID_ENTREPRISE")
    @ManyToOne(optional = true)
    private OrclassEntreprises idEntreprise;

    public Templates() {
//        idEntreprise=new OrclassEntreprises();
    }

    public Boolean getLightLogo() {
        return lightLogo;
    }

    public void setLightLogo(Boolean lightLogo) {
        this.lightLogo = lightLogo;
    }

    public Boolean getDefautTemplate() {
        return defautTemplate;
    }

    public void setDefautTemplate(Boolean defautTemplate) {
        this.defautTemplate = defautTemplate;
    }

    
    
    public String getMenuMode() {
        return menuMode;
    }

    public void setMenuMode(String menuMode) {
        this.menuMode = menuMode;
    }

    public String getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(String darkMode) {
        this.darkMode = darkMode;
    }

    public String getLayoutPrimaryColor() {
        return layoutPrimaryColor;
    }

    public void setLayoutPrimaryColor(String layoutPrimaryColor) {
        this.layoutPrimaryColor = layoutPrimaryColor;
    }

    public String getComponentTheme() {
        return componentTheme;
    }

    public void setComponentTheme(String componentTheme) {
        this.componentTheme = componentTheme;
    }

    public String getTopbarTheme() {
        return topbarTheme;
    }

    public void setTopbarTheme(String topbarTheme) {
        this.topbarTheme = topbarTheme;
    }

    public String getMenuTheme() {
        return menuTheme;
    }

    public void setMenuTheme(String menuTheme) {
        this.menuTheme = menuTheme;
    }

    public String getProfileMode() {
        return profileMode;
    }

    public void setProfileMode(String profileMode) {
        this.profileMode = profileMode;
    }

    public String getInputStyle() {
        return inputStyle;
    }

    public void setInputStyle(String inputStyle) {
        this.inputStyle = inputStyle;
    }

    public Boolean getGroupedMenu() {
        return groupedMenu;
    }

    public void setGroupedMenu(Boolean groupedMenu) {
        this.groupedMenu = groupedMenu;
    }

    

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrclassEntreprises getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(OrclassEntreprises idEntreprise) {
        this.idEntreprise = idEntreprise;
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
        if (!(object instanceof Templates)) {
            return false;
        }
        Templates other = (Templates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modele.Templates[ id=" + id + " ]";
    }

}
