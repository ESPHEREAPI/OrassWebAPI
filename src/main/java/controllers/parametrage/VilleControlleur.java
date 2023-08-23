/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.parametrage;

import dao.VilleDao;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modele.Ville;

/**
 *
 * @author JIATOU FRANCK
 */
@Named(value = "villeControlleur")
@ViewScoped
public class VilleControlleur implements Serializable{
    @EJB
    VilleDao villeDao;
    Ville ville;
}
