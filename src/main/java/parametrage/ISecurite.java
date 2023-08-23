package parametrage;

//import exception.LicenceException;
import exception.GlobalException;
import exception.LicenceException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

import modele.OrclassActions;

import modele.OrclassEntreprises;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassModules;

import modele.OrclassProfils;
import modele.OrclassProfilsAcces;

import modele.OrclassUtilisateurs;
import modele.OrclassUtilisateursAcces;
import modele.Societe;


/**
 *
 * @author fabrice
 */
@Local
public interface ISecurite {

    /**
     * recuperer les modules d'un user
     *
     * @param u Entity of Personne
     * @param pr
     * @return Collection of ModuleSecurite
     */
    public Collection<OrclassModules> getModuleByUser(OrclassUtilisateurs u, OrclassProfils pr);

    /**
     * recuperer les modules attribuer a un utilisateur et un profile bien
     * precis
     *
     * @param u
     * @param p
     * @return
     */
    public Collection<OrclassModules> getModuleByUserByProfile(OrclassUtilisateurs u, OrclassProfils p);

    /*
    recuperer les modules d un  profile
     */
    public Collection<OrclassModules> getModuleByProfile(OrclassProfils p);

    /**
     * recuperer les modules qui ne sont pas a un profile
     *
     * @param p
     * @param u
     * @return
     */
    public Collection<OrclassModules> getModuleForNotProfile(OrclassProfils p);

    /**
     * recuperer tous les modules
     *
     * @return Collection of ModuleSecurite
     */
    public Collection<OrclassModules> getAllModules();

    /**
     * recuperer les modules qui ne sont pas a un user
     *
     * @param u Entity of Personne
     * @param pr
     * @return Collection of ModuleSecurite
     */
    public Collection<OrclassModules> getModuleForNotUser(OrclassUtilisateurs u, OrclassProfils pr);

    /**
     * recuperer les menus d'un module
     *
     * @param mod Entity of Modulesecurite
     * @return Collection of Menu
     */
    public Collection<OrclassMenus> getMenusByModule(OrclassModules mod);

    /**
     * recuperer les fonctionnalite d un module
     *
     * @param mod Entity of Modulesecurite
     * @return Collection of fonctionnalite
     */
    public Collection<OrclassFonctionnalites> getFonctionnaliterByModule(OrclassModules mod);

    /**
     * recuperer les actions d une fonctionnalite
     *
     * @param u
     * @param m
     * @param f
     * @param p
     * @return
     */
    public Collection<OrclassActions> getActionByFonctionnalite(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils p);

    /**
     * recuperer les fonctionnalite d un profil
     *
     * @param p
     * @return
     */
    public Collection<OrclassFonctionnalites> getFonctionnaliteByProfil(OrclassProfils p, OrclassModules m);

    /**
     * recuperer les menus d'un user pour un module precis
     *
     * @param u Entity of Personne
     * @param m Entity of Modulesecurite
     * @return Collection of Menu
     */
    public Collection<OrclassMenus> getMenusbyModuleForUser(OrclassUtilisateurs u, OrclassModules m);

    public Collection<OrclassFonctionnalites> getFonctionnnalitebyModuleForProfile(OrclassModules m, OrclassProfils p);

    /**
     * recuperer les menus d'un user
     *
     * @param u Entity of Personne
     * @param m Entity of Modulesecurite
     * @return Collection of Menu
     */
//    public Collection<Menu> getMenusForUser(Personne u);
    public Collection<OrclassUtilisateursAcces> getUtilisateurAccesForUser(OrclassUtilisateurs u);

    public Collection<OrclassActions> getActionNotForUserForFonctionnalite(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils p);

    public Collection<OrclassMenus> getMenusByUser(OrclassUtilisateurs u);

    /**
     * recuperer les menus non attribue a un user pour un module precis
     *
     * @param u Entity of Personne
     * @param m Entity of Modulesecurite
     * @return Collection of Menu
     */
    public Collection<OrclassMenus> getMenusForNotModuleUser(OrclassUtilisateurs u, OrclassModules m);

    /**
     * recupere les fonctionnalite non attribuer a un user et un profile
     *
     * @param u
     * @param m
     * @param p
     * @return
     */
    public Collection<OrclassFonctionnalites> getFonctionnalitesesForNotModuleUserProfile(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p);

    /**
     * recuper les fonctionnalite non attribuer a un user et un profile precis
     *
     * @param u
     * @param m
     * @return
     */
    public Collection<OrclassFonctionnalites> getFonctionnaliteForNotModuleUserProfile(OrclassMenus u, OrclassModules m);

    /**
     * ajouter des modules a un user
     *
     * @param u Entity of Personne
     * @param mod List Entity of Modulesecurite
     * @return <code>void</code>
     */
    public void addModulesToUser(OrclassUtilisateurs u, List<OrclassModules> mod, OrclassProfils p);

    public void addModuleToFonctionnaliteActionProfile(OrclassModules mod, OrclassFonctionnalites f, List<OrclassActions> a, OrclassProfils p);

    /**
     * retirer les modules a un user
     *
     * @param u Entity of Personne
     * @param mod List Entity of Modulesecurite
     * @return <code>void</code>
     */
    public void removeModulesToUser(OrclassUtilisateurs u, List<OrclassModules> mod, OrclassProfils p);

    /**
     * ajouter un menu a un user
     *
     * @param u Entity of Personne
     * @param m Entity of Menu
     * @param f
     * @param pr
     * @return <code>void</code>
     */
    public void addMenutoUser(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils pr);

    public void addActiontoUser(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils pr, OrclassActions act);

    /**
     * retirer un menu a un user
     *
     * @param u Entity of Personne
     * @param m Entity of Menu
     * @return <code>void</code>
     */
    public void removeMenutoUser(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p, OrclassFonctionnalites f);

    public void removeActiontoUser(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p, OrclassFonctionnalites f, OrclassActions a);

    /**
     * recuperere les menu d'un role
     *
     * @param role Entity of Role
     * @return Collection of Menu
     */
//     public Collection<Menu> getMenusByRole(Role role);
    /**
     * recuperere les Modules d'un role
     *
     * @param role Entity of Role
     * @return Collection of Menu
     */
//     public Collection<Modulesecurite> getModulesByRole(Role role);
    //*******gestion des roles,modules et menu***/
    /**
     * recuperer les menus d'un role pour un module precis
     *
     * @param r Entity of Role
     * @param m Entity of Modulesecurite
     * @return Collection of Menu
     */
//    public Collection<Menu> getMenusbyModuleForRole(Role r,Modulesecurite m);
    /**
     * recuperer les menus non attribue a un Role pour un module precis
     *
     * @param r Entity of Role
     * @param m Entity of Modulesecurite
     * @return Collection of Menu
     */
//     public Collection<Menu> getMenusForNotModuleRole(Role r,Modulesecurite m);
    /**
     * ajouter des menus a un role
     *
     * @param r Entity of Role
     * @param menus Collection Entity of Menu
     * @return <code>void</code>
     */
//     public void addMenusToRole(Role r,List<Menu> menus);
    /**
     * retirer le Menu a un Role
     *
     * @param r Entity of Role
     * @param menu Entity of Menu
     * @return <code>void</code>
     */
//     public void removeMenusToRole(Role r,Menu menu);
    /**
     * Permet de verifier si l utilisateur qui est entrain de se connecter
     * existe
     *
     * @param u
     * @return
     */
    public OrclassUtilisateurs getAuthentification(OrclassUtilisateurs u);

    /**
     * Retourne tous les parametre d'un module
     *
     * @param modulesecurite
     * @return
     */
    public OrclassModules getModule(OrclassModules modulesecurite);

    public void addModuleToUser(OrclassUtilisateurs u, OrclassModules m);

//    public void addMenuToRole(modele.Role r, modele.Menu m);
    /**
     * Insert un user dans les logs
     *
     * @param u
     */
    public void insertUserInLogger(OrclassUtilisateurs u);

    /**
     *
     * @throws LicenceException
     */
//    public void checkDateConnexionWithLastConnexion(Date date) throws LicenceException;
    /**
     * Reset le password d'un user
     *
     * @param p
     * @param newPassword
     */
    public void resetPassword(OrclassUtilisateurs u, String newPassword);

    public void resetCleSecurite(OrclassUtilisateurs u, Societe e);

    /**
     * Insert un user dans les logs
     *
     * @param u
     */
//    public void insertUserInLogger(Personne u);
    /**
     *
     * @throws LicenceException
     */
    public void checkDateConnexionWithLastConnexion(Date date) throws LicenceException;

    public Boolean testeFirstConnection(OrclassUtilisateurs user);

    public void deleteModuleSecurite(OrclassModules m);

    public void addUtilisateur(OrclassUtilisateurs u, List<OrclassProfils> profil, Societe e);

    public Collection<OrclassFonctionnalites> getFonctionnaliteByModuleByUserByProfil(OrclassModules m, OrclassUtilisateurs u, OrclassProfils p);

    public Collection<OrclassModules> getAllModulesByEntreprise(Societe e);

    public String addProfilForAccess(Collection<OrclassModules> modules, OrclassFonctionnalites[][] fonction, OrclassProfils pr) throws GlobalException;

    public Collection<OrclassFonctionnalites> getFonctionaliteHaveAction(OrclassModules m);

    public Collection<OrclassActions> getAllActionByFonctionnaliteAndProfile(OrclassModules modules, OrclassFonctionnalites f, OrclassProfils pr);

    public Collection<OrclassActions> getAllActionByFonctionnaliteNotHaveProfile(OrclassFonctionnalites f, OrclassProfils pr);

    public Boolean checkEtatProfilExisteForUserAccess(OrclassProfils pr);

    public void deleteActionForProfilAccess(OrclassProfilsAcces pa);

   }
