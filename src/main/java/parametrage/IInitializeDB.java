/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parametrage;

import java.util.Collection;
import modele.OrclassActions;
import modele.OrclassEntreprises;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassMenusAcces;
import modele.OrclassModules;
import modele.OrclassUtilisateurs;
import modele.Societe;
import modele.Titre;


/**
 *
 * @author hypoass
 */
public interface IInitializeDB {

    public void addIndicatifPays();

//    public Collection<Religion> getAllReligions();
//
    public Collection<Titre> getAllTitres();
//
//    public Collection<Pays> getAllPays();

    public Collection<OrclassModules> getAllModuleSecurite();

    public Collection<OrclassMenus> getAllMenusByModuleAdministration();

    public Collection<OrclassMenus> getAllMenusByModuleADP();

    public Collection<OrclassMenus> getAllMenusByModuleParamettrage();

    //*****************************************************************//
    public Collection<OrclassMenus> getAllMenusByModuleAgricolte();

    public Collection<OrclassMenus> getAllMenusByModuleAssuranceAuto();

    public Collection<OrclassMenus> getAllMenusByModuleAssuranceIrd();

    public Collection<OrclassMenus> getAllMenusByModuleCautionCredit();

    public Collection<OrclassMenus> getAllMenusByModuleComptabiliteAgence();

    public Collection<OrclassMenus> getAllMenusByModuleComptabiliteGeneral();

    public Collection<OrclassMenus> getAllMenusByModuleGestionAssure();

    public Collection<OrclassMenus> getAllMenusByModuleReassurance();

    public Collection<OrclassMenus> getAllMenusByModuleReporting();

    public Collection<OrclassMenus> getAllMenusByModuleTransport();

    //*****************************************************************//
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAdministration();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleParametrage();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAssuranceAdp();

    //*****************************************************************//
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAgricolte();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAssuranceAuto();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAssuranceIrd();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleCautionCredit();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleComptabiliteAgence();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleComptabiliteGeneral();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleGestionAssure();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleReassurance();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleReporting();

    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleTransport();

    //*****************************************************************//
    public Collection<OrclassActions> getActionByFonctionnaliteByModuleAdministration();

    public Collection<OrclassActions> getActionByFonctionnaliteByModuleParamettrage();

    public Collection<OrclassActions> getActionByFonctionnaliteByAdp();

    public Collection<OrclassMenusAcces> getAllMenuByFonctionnaliteByActionByModuleAdmininstration();

    public Collection<OrclassMenusAcces> getAllMenuByFonctionnaliteByActionByModuleParametrage();

    public Collection<OrclassMenusAcces> getAllMenuByFonctionnaliteByActionByModuleAdp();
    public void creteProfilAdmin();
    public void createModuleByProfileAdmin();

    public void addProfileAdmin();

//     public Collection<Groupe> ge
    public OrclassUtilisateurs getAdmin();

    public Societe createCompagnieUtilisateurSysteme();// name compagnie is GlobalCompagnie , code is 0000

//    public void addTypeBureau();
//
//    public void addVille();
//
//    public void addTypeApporteur();
//
//    public void addClasse();
//
//    public void addBranches();
//
//    public void addCategorie();
//
//    public void addTypeReference();
//
//    public void addTypeCaracteristique();
//
//    public void addUniteCaracteristique();
//
//    public void addTypeTarif();
//
//    public void addTypeTimbreDimension();
//
//    public void addTypeQualite();
//
//    public void addDevise();
//
//    public void addExoneration();
//
//    public void addReduction();
//
//    public void addConvention();
    public void createActionByCaracterisque();

    /*
    ajout des autres modules au profile admin
     */
    public void addMenuAccessForAdmin();

//    /*
//    creation des classe objet
//     */
//    public void createObjetClassByBrancheSante();
//
//    public void createObjetClassByBrancheAutomobile();
//
//    public void createCaracteristiqueByObjetClassByBrancheSante();
//
//    public void creatCodePrincipale();
//
//    public Collection<SeverMessagerie> getSeverMessagerie();
//
//    public void createPeriodicite();
//
//    public void createNatureDocument();
//
//    public void createOperationStock();
//
//    public void createUsageAuto();
//
//    public void creatGenreAuto();
    /*
    attribuer les acces au profil administrateur
     */
//    public Collection<OrclassProfilsAcces> getAccesByProfilAdmin();
}
