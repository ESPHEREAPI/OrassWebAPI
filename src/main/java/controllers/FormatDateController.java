/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import enums.LibelleBranche;
import enums.LibelleCategorie;
import enums.LibelleClasse;
import enums.TypeApporteur;
import enums.TypeBureau;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;


import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import utils.GlobalFonctions;
import utils.IdleDate;
import utils.LocaleHelper;
import utils.RecupBundle;

/**
 *
 * @author OOOOOO
 */
@ManagedBean
@ViewScoped
public class FormatDateController implements Serializable {

    /**
     * Creates a new instance of FormatDateController
     */
    public FormatDateController() {
    }

    @PostConstruct
    void initialiseSession() {

        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public String formatDate(Date d) {
        if (d != null) {
            int jour = IdleDate.getDayMonth(d);
            int mois = IdleDate.getMonth(d);
            int annee = IdleDate.getYear(d);
            String jr = "" + jour;
            String m = "" + mois;
            if (jr.length() == 1) {
                jr = "0" + jour;

            }
            if (m.length() == 1) {
                m = "0" + mois;
            }
            return "" + jr + "/" + m + "/" + annee;
        }
        return null;
    }

    public String formatNumberGeneral(long number) {

        DecimalFormat format = new DecimalFormat();
        DecimalFormatSymbols s = format.getDecimalFormatSymbols();

        s.setGroupingSeparator(GlobalFonctions.espace);
        format.setDecimalFormatSymbols(s);

        return format.format(number);

    }

    public String valueObject(Object t) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();

        if (t != null) {
            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, t.toString(), null, myLoc);
        }
        return "";
    }

    public String valueObjectByLibelleAutres(Object t) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();

        if (t != null) {
            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, t.toString(), null, myLoc);
        }
        return "";
    }

    public String valueObject(Object t, Object t2) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassBranches b;
//        OrclassCategories categories;
//        OrclassClasses cl;
//        OrclassTypeApporteur tp;
//        OrclassTypeBureau tb;
////        try {
// if (t == null) {
//            return "";
//        }
//        if (t.equals(TypeApporteur.autres)) {
//            tp = (OrclassTypeApporteur) t2;
//            return tp.getLibelle();
//        }
//        if (t.equals(TypeBureau.Autre)) {
//            tb = (OrclassTypeBureau) t2;
//            return tb.getLibelle();
//        }
//        if (t.equals(LibelleBranche.autres)) {
//            b = (OrclassBranches) t2;
//            return b.getLibelleAutre();
//        }
//        if (t.equals(LibelleCategorie.autres)) {
//            categories = (OrclassCategories) t2;
//            return categories.getLibelleAutre();
//        }
//        if (t.equals(LibelleClasse.autres)) {
//            cl = (OrclassClasses) t2;
//            return cl.getLibelleAutre();
//        }
//        if (t != null) {
//            return LocaleHelper.getLocaleString(RecupBundle.FichierBundle, t.toString(), null, myLoc);
//        }
//        } catch (NullPointerException n) {
//            
//        }

        return "";
    }
}
