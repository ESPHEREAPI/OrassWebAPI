package parametrage;

import dao.AsvCodeDeviseDao;
import dao.IndicatifPaysDao;
import dao.MoisDao;
import dao.OrclassActionsDao;

import dao.OrclassFonctionnalitesDao;

import dao.OrclassMenusAccesDao;
import dao.OrclassMenusDao;
import dao.OrclassModulesDao;

import dao.OrclassProfilModuleDao;
import dao.OrclassProfilsAccesDao;
import dao.OrclassProfilsDao;

import dao.OrclassUtilisateursAccesDao;
import dao.OrclassUtilisateursDao;
import dao.SocieteDao;
import dao.TitreDao;
import dao.VilleDao;

import enums.Actions;
import enums.FonctionnaliteDefault;
import enums.FonctionnaliteModuleAdministration;
import enums.FonctionnaliteModuleAssuranceAdp;
import enums.FonctionnaliteModuleParametrage;
import enums.TypeUtilisateur;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import modele.IndicatifPays;

import modele.OrclassActions;

import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassMenusAcces;
import modele.OrclassModules;
import modele.OrclassProfilModule;
import modele.OrclassProfils;
import modele.OrclassProfilsAcces;
import modele.OrclassUtilisateurs;
import modele.OrclassUtilisateursAcces;
import modele.Societe;
import modele.Titre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Crypto;

/**
 *
 * @author frank
 */
//@Singleton
//@Startup
//@Lock(LockType.READ)
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ConfigInitialBean implements IInitializeDB {

    @EJB
    MoisDao moisDao;

    @EJB
    VilleDao villleDao;
    @EJB
    private OrclassModulesDao modsecdao;

    @EJB
    private OrclassUtilisateursDao userDao;

    @EJB
    private OrclassMenusDao menudao;

    @EJB
    private ISecurite securiteServices;

    @EJB
    private IndicatifPaysDao indicatifPaysDao;
    @EJB
    private OrclassProfilsAccesDao profilsAccesDao;

    @EJB
    SocieteDao societeDao;

    private static final Logger logger = LoggerFactory.getLogger(ConfigInitialBean.class);
    @EJB
    OrclassFonctionnalitesDao fonctionnalitesDao;
    @EJB
    OrclassActionsDao actionsDao;
    @EJB
    OrclassMenusAccesDao menusAccesDao;
    @EJB
    OrclassProfilsDao profilsDao;
    @EJB
    OrclassProfilModuleDao profilModuleDao;

    @EJB
    OrclassUtilisateursAccesDao utilisateursAccesDao;
    @EJB
    TitreDao titredao;
    @EJB
    AsvCodeDeviseDao asvCodeDeviseDao;

    public ConfigInitialBean() {
    }

//    @Override
//    public Collection<Pays> getAllPays() {
//        String Continent = "Afrique";
//        // on teste s'il n'y a pas encore de pays
//        Collection<Pays> listpays = new ArrayList<>();
//        listpays = paysbean.findAll();
//        if (listpays == null || listpays.isEmpty()) {
//
//            listpays.add(new Pays("CM", "CAMEROUN", "CAMEROON", Continent));
//            listpays.add(new Pays("GA", "GABON", "GABON", Continent));
//            listpays.add(new Pays("ZA", "AFRIQUE DU SUD", "SOUTH AFRICA", Continent));
//            listpays.add(new Pays("AO", "ANGOLA", "ANGOLA", Continent));
//            listpays.add(new Pays("BJ", "BENIN", "BENIN", Continent));
//            listpays.add(new Pays("BW", "BOTSWANA", "BOTSWANA", Continent));
//            listpays.add(new Pays("BF", "BURKINA FASO", "BURKINA FASO", Continent));
//            listpays.add(new Pays("CV", "CAP-VERT", "CAPE VERDE", Continent));
//            listpays.add(new Pays("CF", "CENTRAFRIQUE", "CENTRAFRICA", Continent));
//            listpays.add(new Pays("CG", "CONGO", "CONGO", Continent));
//            listpays.add(new Pays("CD", "CONGO RD", "CONGO RD", Continent));
//            listpays.add(new Pays("CI", "COTE D'IVOIRE", "COTE D'IVOIRE", Continent));
//            listpays.add(new Pays("EG", "EGYPTE", "EGYPT", Continent));
//            listpays.add(new Pays("ET", "ETHIOPIE", "ETHIOPIA", Continent));
//            listpays.add(new Pays("GM", "GAMBIE", "GAMBIA", Continent));
//            listpays.add(new Pays("GH", "GHANA", "GHANA", Continent));
//            listpays.add(new Pays("GN", "GUINEE", "GUINEA", Continent));
//            listpays.add(new Pays("GW", "GUINEE-BISSAU", "GUINEA-BISSAU", Continent));
//            listpays.add(new Pays("GQ", "GUINEE EQUATORIALE", "EQUATORIAL GUINEA", Continent));
//            listpays.add(new Pays("KE", "KENYA", "KENYA", Continent));
//            listpays.add(new Pays("LR", "LIBERIA", "LIBERIA", Continent));
//            listpays.add(new Pays("MG", "MADAGASCAR", "MADAGASCAR", Continent));
//            listpays.add(new Pays("ML", "MALI", "MALI", Continent));
//            listpays.add(new Pays("NA", "NAMIBIE", "NAMIBIA", Continent));
//            listpays.add(new Pays("NG", "NIGERIA", "NIGERIA", Continent));
//            listpays.add(new Pays("NE", "NIGER", "NIGER", Continent));
//            listpays.add(new Pays("SN", "SENEGAL", "SENEGAL", Continent));
//            listpays.add(new Pays("TD", "TCHAD", "CHAD", Continent));
//            listpays.add(new Pays("TG", "TOGO", "Gabon", Continent));
//            listpays.add(new Pays("TN", "TUNISIE", "TUNISIA", Continent));
//            listpays.add(new Pays("ZM", "ZAMBIE", "ZAMBIA", Continent));
//            listpays.add(new Pays("ZW", "ZIMBABWE", "ZIMBABWE", Continent));
//            paysbean.createAll(listpays);
//        }
//        return listpays;
//    }
//
//    @Override
//    public Collection<Religion> getAllReligions() {
//        Collection<Religion> listreligion = new ArrayList<>();
//        listreligion = religionsdao.findAll();
//        if (listreligion == null || listreligion.isEmpty()) {
//            listreligion.add(new Religion("C", "Catholique", new Date()));
//            listreligion.add(new Religion("I", "Islam", new Date()));
//            listreligion.add(new Religion("P", "Protestant", new Date()));
//            religionsdao.createAll(listreligion);
//        }
//        return listreligion;
//    }
    @Override
    public Collection<Titre> getAllTitres() {
        Collection<Titre> listetitres = new ArrayList<>();
        listetitres = titredao.findAll();
        if (listetitres == null || listetitres.isEmpty()) {
            listetitres.add(new Titre("Dr", "Docteur"));
            listetitres.add(new Titre("Ing", "Ingénieur"));
            listetitres.add(new Titre("M.", "Messieur"));
            listetitres.add(new Titre("Mlle", "Mademoiselle"));
            listetitres.add(new Titre("Mme", "Madame"));
            listetitres.add(new Titre("Mr", "Monsieur"));
            listetitres.add(new Titre("Ms", "Maître"));
            listetitres.add(new Titre("Pf", "Professeur"));
            listetitres.add(new Titre("Ps", "Pasteur"));
            titredao.createAll(listetitres);
        }
        return listetitres;
    }

    @Override
    public OrclassUtilisateurs getAdmin() {
        Map<String, Object> parMap = new HashMap<>();
        String query;
        query = "SELECT u FROM OrclassUtilisateurs u WHERE u.login = :matricule";
        parMap.put("matricule", "SYSTEME");
        OrclassUtilisateurs user = new OrclassUtilisateurs();
        user = userDao.findEntityByUsingQuery(query, parMap);
//        user=userDao.getPersonneByMatricule("admin");
//       String querry= "SELECT p FROM Personne p WHERE p.matricule = :matricule";
//       user=userDao.findEntityByUsingQueryName(querry, parMap);

        if (user == null) {
            if (userDao.findEntityHavingValue("login", "SYSTEME") == null) {
                user = new OrclassUtilisateurs();
                user.setLogin("SYSTEME");
                user.setActif(Boolean.TRUE);
                user.setDateCreation(new Date());
                user.setLangue("fr");
                user.setNom("E_SPHERE");
                user.setPassword(Crypto.sha256("SYSTEME"));
                user.setCle_securite(Crypto.sha256("141087"));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe
                user.setTypeUtilisateur(TypeUtilisateur.UTILISATEUR_SYSTEM);

                userDao.create(user);
            }

        }
        return user;
    }

    @Override
    public Collection<OrclassModules> getAllModuleSecurite() {
        Collection<OrclassModules> modsec = new ArrayList<>();
//        modsec = modsecdao.findAll();
//  Short i=Short.valueOf("1");
        if (modsec == null || modsec.isEmpty()) {
            modsec.add(new OrclassModules(ModuleMenu.mdsAdministration, "Administration"));

            modsec.add(new OrclassModules(ModuleMenu.mdsAssure_IRD, "Assurance  IRD"));
            modsec.add(new OrclassModules(ModuleMenu.mdsAssurance_Adp, "Assurance ADP"));
            modsec.add(new OrclassModules(ModuleMenu.mdsAssurance_Auto, "Assurance Auto"));
            modsec.add(new OrclassModules(ModuleMenu.mdsTransport, "Transport "));
            modsec.add(new OrclassModules(ModuleMenu.mdsCaution_Credit, "Caution et Crédit "));
//            modsec.add(new OrclassModules(ModuleMenu.mADroitAcces, "Droit d access "));
            modsec.add(new OrclassModules(ModuleMenu.mdsAgricolte, "Agricole"));
            modsec.add(new OrclassModules(ModuleMenu.mdsGestion_Assure, "Gestion Assurés"));
            modsec.add(new OrclassModules(ModuleMenu.mdsCompt_Gene, "Comptabilité Général"));
            modsec.add(new OrclassModules(ModuleMenu.mdsCompAgence, "Comptabilité Agence"));
            modsec.add(new OrclassModules(ModuleMenu.mdsReporting, "Reporting"));
            modsec.add(new OrclassModules(ModuleMenu.mdsReinsurance, "Reinssurance"));
            modsec.add(new OrclassModules(ModuleMenu.mdsParametrage, "mod.ref"));
            modsec.add(new OrclassModules(ModuleMenu.mdsADP, "Assurance adp"));

            for (OrclassModules m : modsec) {
                if (modsecdao.findEntityHavingValue("code", m.getCode()) == null) {
//                    m.setIdModule(i);
                    modsecdao.create(m);
//                    i++;
                }
            }

//            modsecdao.createAll(modsec);
        }

        return modsec;
    }

    @Override
    public void addIndicatifPays() {
        Collection<IndicatifPays> colIndicatif = new ArrayList<>();
//        colIndicatif = indicatifPaysDao.getAllIndicatif();
        if (colIndicatif == null || colIndicatif.isEmpty()) {
            colIndicatif.add(new IndicatifPays("237"));
            colIndicatif.add(new IndicatifPays("221"));
            colIndicatif.add(new IndicatifPays("222"));
            colIndicatif.add(new IndicatifPays("223"));
            colIndicatif.add(new IndicatifPays("224"));
            colIndicatif.add(new IndicatifPays("225"));
            colIndicatif.add(new IndicatifPays("226"));
            colIndicatif.add(new IndicatifPays("227"));
            colIndicatif.add(new IndicatifPays("228"));
            colIndicatif.add(new IndicatifPays("229"));
            for (IndicatifPays in : colIndicatif) {
                if (indicatifPaysDao.findEntityHavingValue("indicatif", in.getIndicatif()) == null) {
                    indicatifPaysDao.create(in);
                }
            }

        }

    }

    /*
     *Permet de créer les Rôle par défaut a partir des valeurs définis dans l'énumération DefaultRole
     */
 /*
     *Permet de créer les Profil par défaut a partir des valeurs définis dans l'énumération DefaultRole
     */
 /*
     * cette methode permet d'ajouter les menus au role Administration 
     */
 /*
     * cette methode permet d'ajouter les menus au role Enseignant 
     */
    @Override
    public Collection<OrclassMenus> getAllMenusByModuleAdministration() {

        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAdministration);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAdministration, "Administration"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAdministration);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
//            menuone.add(new OrclassMenus(ModuleMenu.mAConfiguration, "Configurations ", false, "/administration/configuration", modsec5));
            menuone.add(new OrclassMenus(ModuleMenu.mAOptionetab, "Option Entreprise ", false, "/administration/optionEntreprise", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mSProfil, "Profil  Utilisateur", false, "/administration/profile", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mSUser, "utilisateur ", false, "/administration/utilisateur", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mAEntreprise, "Gestion Entreprise ", false, "/administration/entreprise", modsec5, "#"));
            menuone.add(new OrclassMenus("droit.access", "Droit d'acceés", false, "/administration/droitAcces", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mAIntermediaire, "Intermediaire", false, "/administration/intermediaire", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mABranche, "Branche", false, "/administration/branche", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mACategorie, "Categorie", false, "/administration/categorie", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mAClass, "classe", false, "/administration/classe", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mAUserAccess, "access Utilisateur", false, "/administration/userAccess", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mAOptionetab, "Gestion property Entreprise ", false, "/administration/optionEntreprise", modsec5, "#"));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAdministration() {

        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAdministration);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAdministration, "Administration"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAdministration);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        menuone = new ArrayList<>();
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
//            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.configuration_entite.name(), FonctionnaliteModuleAdministration.configuration_entite.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.gestion_Droit_Acces.name(), FonctionnaliteModuleAdministration.gestion_Droit_Acces.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.gestion_branche.name(), FonctionnaliteModuleAdministration.gestion_branche.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.gestion_categorie.name(), FonctionnaliteModuleAdministration.gestion_categorie.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.gestion_classe.name(), FonctionnaliteModuleAdministration.gestion_classe.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.gestion_intermediaire.name(), FonctionnaliteModuleAdministration.gestion_intermediaire.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.gestion_profil.name(), FonctionnaliteModuleAdministration.gestion_profil.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.gestion_utilisateur.name(), FonctionnaliteModuleAdministration.gestion_utilisateur.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.gestion_entreprise.name(), FonctionnaliteModuleAdministration.gestion_entreprise.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.findEntityHavingValue("libelle", f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;

    }

    @Override
    public Collection<OrclassActions> getActionByFonctionnaliteByModuleAdministration() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAdministration);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAdministration, "Administration"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAdministration);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
        Collection<OrclassActions> colActions = new ArrayList<>();
        OrclassActions actions;
        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null && !menuone.isEmpty()) {
            for (OrclassFonctionnalites fonction : menuone) {
                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.configuration_entite.name())) {

                    for (Actions action : Actions.values()) {
                        colActions = new ArrayList<>();
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.configuration_entite.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }

                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_entreprise.name())) {

                    for (Actions action : Actions.values()) {
                        colActions = new ArrayList<>();
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_entreprise.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }

                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_Droit_Acces.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_Droit_Acces.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_branche.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_branche.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_categorie.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_categorie.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_classe.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_classe.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_entite.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_entite.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_intermediaire.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_intermediaire.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_profil.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_profil.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_utilisateur.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAdministration.gestion_utilisateur.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
            }
        }
        return colActions;
    }

    /*
    attribution des menus du module administration avec ses fonctionnalites
     */
    @Override
    public Collection<OrclassMenusAcces> getAllMenuByFonctionnaliteByActionByModuleAdmininstration() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAdministration);
        OrclassMenusAcces menus_acces;
        Collection<OrclassMenusAcces> colMenu = new ArrayList<>();
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAdministration, "Administration"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAdministration);

        }

        if (modsec5 != null && menusAccesDao.findAll().isEmpty()) {
            OrclassFonctionnalites fonct = null;
            for (OrclassMenus menu : menudao.getMenuByModule(modsec5)) {
                System.out.println("parcour Menu :" + menu.getCode());
                // ici lier le menu a la fonctionnalite
                if (menu.getCode().equals("entite.profil")) {
                    fonct = fonctionnalitesDao.findEntityHavingValue("code", "gestion_profil");
                    if (fonct != null && fonct.getIdFonctionnalite() != null) {
                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(fonct, modsec5)) {
                            System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + fonct.getCode() + " action :" + action);

                            if (actionsDao.finkey(fonct, modsec5, action.getLibelle()) == null) {
                                continue;
                            }
                            if (menusAccesDao.finkey(menu, fonct, action) != null) {
                                continue;
                            }
                            menus_acces = new OrclassMenusAcces();
                            menus_acces.setIdFonctionnalite(fonct);
                            menus_acces.setIdMenu(menu);
                            menus_acces.setIdAction(action);
                            colMenu.add(menus_acces);

                        }

                    }
                } else if (menu.getCode().equals("entite.user")) {
                    fonct = fonctionnalitesDao.findEntityHavingValue("code", "gestion_utilisateur");
                    if (fonct != null && fonct.getIdFonctionnalite() != null) {
                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(fonct, modsec5)) {
                            System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + fonct.getCode() + " action :" + action);

                            if (actionsDao.finkey(fonct, modsec5, action.getLibelle()) == null) {
                                continue;
                            }
                            if (menusAccesDao.finkey(menu, fonct, action) != null) {
                                continue;
                            }
                            menus_acces = new OrclassMenusAcces();
                            menus_acces.setIdFonctionnalite(fonct);
                            menus_acces.setIdMenu(menu);
                            menus_acces.setIdAction(action);
                            colMenu.add(menus_acces);

                        }

                    }
                } else if (menu.getCode().equals("droit.access")) {
                    fonct = fonctionnalitesDao.findEntityHavingValue("code", "gestion_Droit_Acces");
                    if (fonct != null && fonct.getIdFonctionnalite() != null) {
                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(fonct, modsec5)) {
                            System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + fonct.getCode() + " action :" + action);

                            if (actionsDao.finkey(fonct, modsec5, action.getLibelle()) == null) {
                                continue;
                            }
                            if (menusAccesDao.finkey(menu, fonct, action) != null) {
                                continue;
                            }
                            menus_acces = new OrclassMenusAcces();
                            menus_acces.setIdFonctionnalite(fonct);
                            menus_acces.setIdMenu(menu);
                            menus_acces.setIdAction(action);
                            colMenu.add(menus_acces);

                        }

                    }
                } else if (menu.getCode().equals("codeconfiguration")) {
                    fonct = fonctionnalitesDao.findEntityHavingValue("code", "configuration_entite");
                    if (fonct != null && fonct.getIdFonctionnalite() != null) {
                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(fonct, modsec5)) {
                            System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + fonct.getCode() + " action :" + action);

                            if (actionsDao.finkey(fonct, modsec5, action.getLibelle()) == null) {
                                continue;
                            }
                            if (menusAccesDao.finkey(menu, fonct, action) != null) {
                                continue;
                            }
                            menus_acces = new OrclassMenusAcces();
                            menus_acces.setIdFonctionnalite(fonct);
                            menus_acces.setIdMenu(menu);
                            menus_acces.setIdAction(action);
                            colMenu.add(menus_acces);

                        }

                    }
                } else if (menu.getCode().equals("intermediaire")) {
                    fonct = fonctionnalitesDao.findEntityHavingValue("code", "gestion_intermediaire");
                    if (fonct != null && fonct.getIdFonctionnalite() != null) {
                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(fonct, modsec5)) {
                            System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + fonct.getCode() + " action :" + action);

                            if (actionsDao.finkey(fonct, modsec5, action.getLibelle()) == null) {
                                continue;
                            }
                            if (menusAccesDao.finkey(menu, fonct, action) != null) {
                                continue;
                            }
                            menus_acces = new OrclassMenusAcces();
                            menus_acces.setIdFonctionnalite(fonct);
                            menus_acces.setIdMenu(menu);
                            menus_acces.setIdAction(action);
                            colMenu.add(menus_acces);

                        }

                    }
                } else if (menu.getCode().equals("branche")) {
                    fonct = fonctionnalitesDao.findEntityHavingValue("code", "gestion_branche");
                    if (fonct != null && fonct.getIdFonctionnalite() != null) {
                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(fonct, modsec5)) {
                            System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + fonct.getCode() + " action :" + action);

                            if (actionsDao.finkey(fonct, modsec5, action.getLibelle()) == null) {
                                continue;
                            }
                            if (menusAccesDao.finkey(menu, fonct, action) != null) {
                                continue;
                            }
                            menus_acces = new OrclassMenusAcces();
                            menus_acces.setIdFonctionnalite(fonct);
                            menus_acces.setIdMenu(menu);
                            menus_acces.setIdAction(action);
                            colMenu.add(menus_acces);

                        }

                    }
                } else if (menu.getCode().equals("categorie")) {
                    fonct = fonctionnalitesDao.findEntityHavingValue("code", "gestion_categorie");
                    if (fonct != null && fonct.getIdFonctionnalite() != null) {
                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(fonct, modsec5)) {
                            System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + fonct.getCode() + " action :" + action);

                            if (actionsDao.finkey(fonct, modsec5, action.getLibelle()) == null) {
                                continue;
                            }
                            if (menusAccesDao.finkey(menu, fonct, action) != null) {
                                continue;
                            }
                            menus_acces = new OrclassMenusAcces();
                            menus_acces.setIdFonctionnalite(fonct);
                            menus_acces.setIdMenu(menu);
                            menus_acces.setIdAction(action);
                            colMenu.add(menus_acces);

                        }

                    }
                } else if (menu.getCode().equals("classe")) {
                    fonct = fonctionnalitesDao.findEntityHavingValue("code", "gestion_classe");
                    if (fonct != null && fonct.getIdFonctionnalite() != null) {
                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(fonct, modsec5)) {
                            System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + fonct.getCode() + " action :" + action);

                            if (actionsDao.finkey(fonct, modsec5, action.getLibelle()) == null) {
                                continue;
                            }
                            if (menusAccesDao.finkey(menu, fonct, action) != null) {
                                continue;
                            }
                            menus_acces = new OrclassMenusAcces();
                            menus_acces.setIdFonctionnalite(fonct);
                            menus_acces.setIdMenu(menu);
                            menus_acces.setIdAction(action);
                            colMenu.add(menus_acces);

                        }

                    }
                } else if (menu.getCode().equals("entite.entreprise")) {
                    fonct = fonctionnalitesDao.findEntityHavingValue("code", "gestion_entreprise");
                    if (fonct != null && fonct.getIdFonctionnalite() != null) {
                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(fonct, modsec5)) {
                            System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + fonct.getCode() + " action :" + action);

                            if (actionsDao.finkey(fonct, modsec5, action.getLibelle()) == null) {
                                continue;
                            }
                            if (menusAccesDao.finkey(menu, fonct, action) != null) {
                                continue;
                            }
                            menus_acces = new OrclassMenusAcces();
                            menus_acces.setIdFonctionnalite(fonct);
                            menus_acces.setIdMenu(menu);
                            menus_acces.setIdAction(action);
                            colMenu.add(menus_acces);

                        }

                    }

                }

            }
//            if (menusAccesDao.findAll().isEmpty()==Boolean.TRUE) {
            menusAccesDao.createAll(colMenu);
//            }else{
//                for (OrclassMenusAcces ma : colMenu) {
//                    if (menusAccesDao.) {
//                        
//                    } else {
//                    }
//                }
//            }

        }
        return colMenu;
    }

    @Override
    public void creteProfilAdmin() {
        OrclassProfils p;
        p = profilsDao.findEntityHavingValue("code", "admin");
        if (p == null) {
            p = new OrclassProfils();
            p.setCode("admin");
            p.setDateCreation(new Date());
            p.setLibelle("admin");
            p.setActif(Boolean.TRUE);
            profilsDao.create(p);

        }
    }

    @Override
    public void createModuleByProfileAdmin() {
        OrclassProfils p = null;
        List<OrclassProfilModule> colPM = new ArrayList<>();
        p = p = profilsDao.findEntityHavingValue("code", "admin");
        if (p != null && p.getIdProfil() != null) {
            for (OrclassModules m : modsecdao.findAll()) {
                if (profilModuleDao.finkey(p, m) == null) {
                    colPM.add(new OrclassProfilModule(m, p));
                }

            }
            profilModuleDao.createAll(colPM);
        }
    }

    @Override
    public void addProfileAdmin() {
        OrclassProfils p;
        Collection<OrclassProfilModule> colPM = new ArrayList<>();
        List<OrclassProfilsAcces> colPA = new ArrayList<>();
        Collection<OrclassMenusAcces> colMa = new ArrayList<>();
        Collection<OrclassMenus> colMenus = new ArrayList<>();
        p = new OrclassProfils();

        p = profilsDao.findEntityHavingValue("code", "admin");

        colPM = profilModuleDao.findAllEntitiesHavingValue("idProfil", p);

        for (OrclassProfilModule pm : colPM) {
            colMenus = menudao.getMenuByModule(pm.getIdModule());
            for (OrclassMenus menus : colMenus) {
                colMa = menusAccesDao.findAllEntitiesHavingValue("idMenu", menus);
                for (OrclassMenusAcces maccess : colMa) {
                    if (profilsAccesDao.finkey(pm.getIdModule(), maccess.getIdFonctionnalite(), maccess.getIdAction(), p) != null) {
                        continue;
                    }
                    colPA.add(new OrclassProfilsAcces(maccess.getIdAction(), maccess.getIdFonctionnalite(), pm.getIdModule(), p, Boolean.TRUE));
                }
            }
        }
        if (!colPA.isEmpty()) {
            Iterator<OrclassProfilsAcces> its = colPA.iterator();
            int i = 0;
            while (its.hasNext()) {
                OrclassProfilsAcces next = its.next();

                System.out.println("valeur i :" + i);
                if (next.getIdFonctionnalite() == null || next.getIdAction() == null || next.getIdProfil() == null || next.getIdModule() == null) {
                    continue;
                }
                profilsAccesDao.create(next);
                i++;
            }

        }

//        if (profilsAccesDao.findAll().isEmpty()) {
//
//            if (profilsDao.findEntityHavingValue("code", "admin") == null) {
//                p = new OrclassProfils();
//                p.setCode("admin");
//                p.setDateCreation(new Date());
//                p.setLibelle("admin");
//                p.setActif(Boolean.TRUE);
//                profilsDao.create(p);
//
//            } else {
//                p = profilsDao.findEntityHavingValue("code", "admin");
//            }
//
//            /*
//        attribuer tout les module au profile admin
//             */
//            if (profilModuleDao.findAll().isEmpty()) {
//                for (OrclassModules m : modsecdao.findAll()) {
//                    colPM.add(new OrclassProfilModule(m, p));
//                }
//                profilModuleDao.createAll(colPM);
//                for (OrclassProfilModule pm : profilModuleDao.findAll()) {
//
//                    for (OrclassFonctionnalites f : fonctionnalitesDao.getAllFonctionnaireByModule(pm.getIdModule())) {
//                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(f, pm.getIdModule())) {
//
//                            if (actionsDao.finkey(f, pm.getIdModule(), action.getLibelle()) == null) {
//                                continue;
//                            }
//                            if (profilsAccesDao.finkey(pm.getIdModule(), f, action, p) != null) {
//                                continue;
//                            }
//
//                            colPA.add(new OrclassProfilsAcces(action, f, pm.getIdModule(), p, Boolean.TRUE));
//
//                        }
//
//                    }
//                }
//                if (!colPA.isEmpty()) {
//
//                    profilsAccesDao.createAll(colPA);
//                }
//            } else {
//                for (OrclassProfilModule pm : profilModuleDao.findAll()) {
//
//                    for (OrclassFonctionnalites f : fonctionnalitesDao.getAllFonctionnaireByModule(pm.getIdModule())) {
//                        for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(f, pm.getIdModule())) {
//
//                            if (actionsDao.finkey(f, pm.getIdModule(), action.getLibelle()) == null) {
//                                continue;
//                            }
//                            if (profilsAccesDao.finkey(pm.getIdModule(), f, action, p) != null) {
//                                continue;
//                            }
//                            colPA.add(new OrclassProfilsAcces(action, f, pm.getIdModule(), p, Boolean.TRUE));
//
//                        }
//
//                    }
//                }
//                if (!colPA.isEmpty()) {
//                    profilsAccesDao.createAll(colPA);
//                }
//
//            }
//        }
    }
//
//    @Override
//    public void addTypeBureau() {
////        if (typeBureauDao.findAll().isEmpty()) {
//        OrclassTypeBureau tbs = null;
//        for (TypeBureau tb : TypeBureau.values()) {
//            if (tb.equals(TypeBureau.Autre)) {
//                continue;
//            } else if (typeBureauDao.findEntityHavingValue("typeBureau", tb) == null) {
//                tbs = new OrclassTypeBureau();
//                tbs.setTypeBureau(tb);
//                tbs.setShowAllCompagnies(Boolean.TRUE);
//                switch (tb) {
//                    case Acceptation:
//                        tbs.setCode("I");
//                        break;
//                    case Bureau_Direct:
//                        tbs.setCode("B");
//                        break;
//                    case agence:
//                        tbs.setCode("D");
//                        break;
//                    case apporteur:
//                        tbs.setCode("P");
//                        break;
//                    case agent_general:
//                        tbs.setCode("A");
//                        break;
//                    case bureau_souscription:
//                        tbs.setCode("N");
//                        break;
//                    case coassureur:
//                        tbs.setCode("O");
//                        break;
//                    case compagnie:
//                        tbs.setCode("C");
//                        break;
//                    case courtier:
//                        tbs.setCode("T");
//                        break;
//                    case employe:
//                        tbs.setCode("E");
//                        break;
//                    case mandataire_non_salaire:
//                        tbs.setCode("M");
//                        break;
//                    case reassureur:
//                        tbs.setCode("R");
//                        break;
//                    case siege:
//                        tbs.setCode("S");
//                        break;
//                }
//
//                typeBureauDao.create(tbs);
//            }
//        }
////        }
//    }

//    @Override
//    public void addVille() {
//        if (villeDao.findAll().isEmpty()) {
//            OrclassVille v = null;
//            //enregistrement ville douala
//            if (villeDao.findEntityHavingValue("code", "2001") == null) {
//                v = new OrclassVille();
//                v.setCode("2001");
//                v.setLibelle("Douala");
//                villeDao.create(v);
//            }
//        }
//    }

    /*
    creation des menus assurance adp
     */
    @Override
    public Collection<OrclassMenus> getAllMenusByModuleADP() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsADP);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsADP, "Assurance ADP"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsADP);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.mds_mAccidentCorporel, "Accidents Corporels ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mds_mProduction, "Production ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mds_mSinistre, "Sinistre ", false, "#", modsec5, "#"));

            menuone.add(new OrclassMenus(ModuleMenu.mds_Assurance, "Assurance ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mds_mVoyage, "Voyage", false, "/assurance_adp/assurance/production/voyage", modsec5, "" + ModuleMenu.mds_Assurance + "|" + ModuleMenu.mds_mProduction));

            menuone.add(new OrclassMenus(ModuleMenu.mds_Reglment, "Reglement ", false, "#", modsec5, "#"));

            menuone.add(new OrclassMenus(ModuleMenu.mds_Sante, "Santé ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mAffaireNouvelle, "Affaires Nouvelles ", false, "/assurance_adp/sante/production/affaire_nouvelle", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.mAvenant, "Avenant ", false, "/assurance_adp/sante/production/avenant", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.mEcheance, "Echeance ", false, "/assurance_adp/sante/production/echeance", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.mEncaissement, "Encaissement ", false, "/assurance_adp/sante/production/encaissement", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.mVersement, "Versement ", false, "/assurance_adp/sante/production/versement", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mProduction));

            /*
            sante ---sinistre
             */
            menuone.add(new OrclassMenus(ModuleMenu.mds_mPrise_En_Charge, "Prise en Charge ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mMaladie, "Maladie ", false, "/assurance_adp/sante/sinistre/prise_en_charge/maladie", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mSinistre + "|" + ModuleMenu.mds_mPrise_En_Charge));
            menuone.add(new OrclassMenus(ModuleMenu.mSinistrePEC_saisir_validation, "Prise en Charge Saisir et validation ", false, "/assurance_adp/sante/sinistre/prise_en_charge/prise_en_charge_saisir_validation", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mSinistre + "|" + ModuleMenu.mds_mPrise_En_Charge));
            menuone.add(new OrclassMenus(ModuleMenu.mSinistrePEC_consultation, "Prise en Charge Consultation ", false, "/assurance_adp/sante/sinistre/prise_en_charge/prise_en_charge_consultation", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mSinistre + "|" + ModuleMenu.mds_mPrise_En_Charge));
            menuone.add(new OrclassMenus(ModuleMenu.mSinistrePEC_devalidation, "Prise en Charge devalidation ", false, "/assurance_adp/sante/sinistre/prise_en_charge/prise_en_charge_devalidation", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mSinistre + "|" + ModuleMenu.mds_mPrise_En_Charge));
            /*
            sante ---facture tier payant maladie
             */
            menuone.add(new OrclassMenus(ModuleMenu.mds_mFacture_tier, "Facture tiers ", false, "#", modsec5, "#"));
            /*
            sante ---remboursement maladie
             */
            menuone.add(new OrclassMenus(ModuleMenu.mds_mRemboursement, "Remboursement ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mMaladie_remboursement, "Maladie Remboursement  ", false, "/assurance_adp/sante/sinistre/remboursement/maladie", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mSinistre + "|" + ModuleMenu.mds_mRemboursement));
            /*
            sante ---reglement bordereau
             */
            menuone.add(new OrclassMenus(ModuleMenu.mSinistre_reglement_bordereau_malad, "Reglement Bordereau Maladie ", false, "#", modsec5, "#"));

            /*
            sante ---reglement facture_tiers payant
             */
            menuone.add(new OrclassMenus(ModuleMenu.mSinistre_reglement_facture_tier_payant, "Reglement facture tier payant ", false, "#", modsec5, "#"));

            menuone.add(new OrclassMenus(ModuleMenu.mds_mSinistre, "Sinistre ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mSinistre_encaissement_recours, "Encaissement recours ", false, "/assurance_adp/sante/sinistre/encaissements_recours", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mSinistre_evenement_consultation, "evenement Consultation", false, "/assurance_adp/sante/sinistre/evenement_consultation", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mSinistre_consultation, "Sinistre Consultation", false, "/assurance_adp/sante/sinistre/sinistre_consultation", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mSinistre_suivi, "Sinistre Suivi", false, "/assurance_adp/sante/sinistre/sinistre_suivi", modsec5, "#"));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAssuranceAdp() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsADP);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsADP, "Assurance ADP"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsADP);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAssuranceAdp.gestion_assurance_produit_voyage.name(), FonctionnaliteModuleAssuranceAdp.gestion_assurance_produit_voyage.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAssuranceAdp.gestion_avenant_sante.name(), FonctionnaliteModuleAssuranceAdp.gestion_avenant_sante.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAssuranceAdp.gestion_echeance_sante.name(), FonctionnaliteModuleAssuranceAdp.gestion_echeance_sante.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAssuranceAdp.gestion_prise_en_charge_sante_maladie.name(), FonctionnaliteModuleAssuranceAdp.gestion_prise_en_charge_sante_maladie.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAssuranceAdp.gestion_produit_sante.name(), FonctionnaliteModuleAssuranceAdp.gestion_produit_sante.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAssuranceAdp.gestion_remboursement_sante_maladie.name(), FonctionnaliteModuleAssuranceAdp.gestion_remboursement_sante_maladie.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAssuranceAdp.gestion_versement_sante.name(), FonctionnaliteModuleAssuranceAdp.gestion_versement_sante.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAssuranceAdp.gestoion_encaissement_sante.name(), FonctionnaliteModuleAssuranceAdp.gestoion_encaissement_sante.name(), new Date(), modsec5));
//            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleAdministration.gestion_entreprise.name(), FonctionnaliteModuleAdministration.gestion_entreprise.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleParamettrage() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsParametrage);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsParametrage, "Réferentiel"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsParametrage);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.mdsp_caracteristique, "Caracterisque", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_type_caracteristique, "Type Caracteristique", false, "/parametrage/caracteristiques/type_caracteristique", modsec5, "" + ModuleMenu.mdsp_caracteristique));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_assure_caract, " Caracteristique", false, "/parametrage/caracteristiques/caracteristique", modsec5, "" + ModuleMenu.mdsp_caracteristique));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_group_caract, "Groupe  Caracteristique", false, "/parametrage/caracteristiques/relation", modsec5, "" + ModuleMenu.mdsp_caracteristique));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_ref_caract, "Reference Caracteristique", false, "/parametrage/caracteristiques/reference", modsec5, "" + ModuleMenu.mdsp_caracteristique));

            menuone.add(new OrclassMenus(ModuleMenu.mdsp_commission, "Commissions  Primes", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_apporteur, " apporteurs", false, "/parametrage/commissions/apporteur", modsec5, "" + ModuleMenu.mdsp_commission));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_intermediaire, " intermediaire", false, "/parametrage/commissions/intermediaire", modsec5, "" + ModuleMenu.mdsp_commission));

            menuone.add(new OrclassMenus(ModuleMenu.mdsp_garantie, "Garanties  ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_assure_garantie, "Categorie Garantie", false, "/parametrage/garanties/categorieGarantie", modsec5, "" + ModuleMenu.mdsp_garantie));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_ref_garantie, "Reference Garantie", false, "/parametrage/garanties/reference", modsec5, "" + ModuleMenu.mdsp_garantie));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_group_garantie, "Garanties Caracteristique", false, "/parametrage/garanties/caracterisque_Garantie", modsec5, "" + ModuleMenu.mdsp_garantie));

            menuone.add(new OrclassMenus(ModuleMenu.mdsp_prestation, "Prestation ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_ref_prestation, " Prestation", false, "/parametrage/prestation/prestation", modsec5, "" + ModuleMenu.mdsp_prestation));

            menuone.add(new OrclassMenus(ModuleMenu.mdsp_rubrique, "Rubrique ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_assure_rubrique, "Rubrique Categorie", false, "/parametrage/prestation/rubrique/rubriqueCategorie", modsec5, "" + ModuleMenu.mdsp_prestation + "|" + ModuleMenu.mdsp_rubrique));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_group_rubrique, "Rubrique Prestation", false, "/parametrage/prestation/rubrique/rubriquePrestation", modsec5, "" + ModuleMenu.mdsp_prestation + "|" + ModuleMenu.mdsp_rubrique));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_ref_rubrique, "Reference  Rubrique", false, "/parametrage/prestation/rubrique/rubrique", modsec5, "" + ModuleMenu.mdsp_prestation + "|" + ModuleMenu.mdsp_rubrique));

            menuone.add(new OrclassMenus(ModuleMenu.mdsp_registre, "Registre ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_registre_production, "Registre Production", false, "/parametrage/registre/registre_production", modsec5, "" + ModuleMenu.mdsp_registre));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_registre_sinistre, "Registre Sinistre", false, "/parametrage/registre/registre_sinistre", modsec5, "" + ModuleMenu.mdsp_registre));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_initialisation_registre, "Initialisation des Registre de Production", false, "/parametrage/registre/initiation_registre", modsec5, "" + ModuleMenu.mdsp_registre));

            menuone.add(new OrclassMenus(ModuleMenu.mTypeAvenant, "Type Avenant", false, "/parametrage/avenant/typeAvenant", modsec5, "avenant"));
            menuone.add(new OrclassMenus(ModuleMenu.mAgencementAvenant, "Agencement type Avenant", false, "/parametrage/avenant/agencementTypeAvenant", modsec5, "avenant"));
            menuone.add(new OrclassMenus(ModuleMenu.mAccessDroitAvenant, "Access Type Avenant", false, "/parametrage/avenant/accessTypeAvenant", modsec5, "avenant"));

            menuone.add(new OrclassMenus(ModuleMenu.mds_mSinistre, "Sinistre  ", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mExercice, "Exercice", false, "/parametrage/sinistre/exercice", modsec5, "" + ModuleMenu.mds_mSinistre));
            menuone.add(new OrclassMenus(ModuleMenu.mPrestataire, "Prestataire", false, "/parametrage/sinistre/prestataire", modsec5, "" + ModuleMenu.mds_mSinistre));
            menuone.add(new OrclassMenus(ModuleMenu.mNatureMaladie, "Nature Maladie", false, "/parametrage/sinistre/natureMaladie", modsec5, "" + ModuleMenu.mds_mSinistre));

            menuone.add(new OrclassMenus(ModuleMenu.mdsp_duree, "Durée ", false, "/parametrage/durees", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_tarif, "Tarif ", false, "/parametrage/tarif", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mdsp_exoneration_taxe, "Exoneration Taxe ", false, "/parametrage/exonerationTaxe", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mReFfraction, "Fractionnement ", false, "/parametrage/fractionnement", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mPeriodicite, "Periodicite ", false, "/parametrage/periodicite", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mRduction, "Reduction ", false, "/parametrage/reduction", modsec5, "#"));

            menuone.add(new OrclassMenus(ModuleMenu.mdsp_plafond_maladie, "Plafond Maladie ", false, "/parametrage/plafondMaladie", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mTypetaxe, " Taxe ", false, "/parametrage/taxe", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mRegimeTaxe, "Regime Taxe", false, "/parametrage/regimeTaxes", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mTaxePrime, "Taxe Prime", false, "/parametrage/taxeprime", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mTypeDocument, "Type Document", false, "/parametrage/typeDocument", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mDocumentCategorie, "Document Categorie", false, "/parametrage/documentCategorie", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mGestionDocumentStock, "Gestion Stock Document", false, "/parametrage/gestionDocumentCompagnie", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mTimbreGradue, "Timbre Gradue", false, "/parametrage/timbre_gradue", modsec5, "#"));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleParametrage() {
        OrclassModules modsec5 = (OrclassModules) this.modsecdao.findEntityHavingValue("code", "mod.ref");
        if (modsec5 == null) {
            OrclassModules mds = new OrclassModules("mod.ref", "R");
            this.modsecdao.create(mds);
            modsec5 = (OrclassModules) this.modsecdao.findEntityHavingValue("code", "mod.ref");
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
        if (modsec5 != null) {
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.type_caracteristique.name(), FonctionnaliteModuleParametrage.type_caracteristique.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.caracteristique_assure.name(), FonctionnaliteModuleParametrage.caracteristique_assure.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.caracteristique_group.name(), FonctionnaliteModuleParametrage.caracteristique_group.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.caracteristique_reference.name(), FonctionnaliteModuleParametrage.caracteristique_reference.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.commission_intermediaire.name(), FonctionnaliteModuleParametrage.commission_intermediaire.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.commission_apporteur.name(), FonctionnaliteModuleParametrage.commission_apporteur.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.garantie_assure.name(), FonctionnaliteModuleParametrage.garantie_assure.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.garantie_groupe.name(), FonctionnaliteModuleParametrage.garantie_groupe.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.garantie_reference.name(), FonctionnaliteModuleParametrage.garantie_reference.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.gestion_duree.name(), FonctionnaliteModuleParametrage.gestion_duree.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.prestation_reference.name(), FonctionnaliteModuleParametrage.prestation_reference.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.rubrique_assure.name(), FonctionnaliteModuleParametrage.rubrique_assure.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.rubrique_group.name(), FonctionnaliteModuleParametrage.rubrique_group.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.rubrique_reference.name(), FonctionnaliteModuleParametrage.rubrique_reference.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.initialisation_registre.name(), FonctionnaliteModuleParametrage.initialisation_registre.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.registre_production.name(), FonctionnaliteModuleParametrage.registre_production.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.registre_sinistre.name(), FonctionnaliteModuleParametrage.registre_sinistre.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.gestion_Tarif.name(), FonctionnaliteModuleParametrage.gestion_Tarif.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.exoneration_taxe.name(), FonctionnaliteModuleParametrage.exoneration_taxe.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.plafond_maladie.name(), FonctionnaliteModuleParametrage.plafond_maladie.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.gestion_type_avenant.name(), FonctionnaliteModuleParametrage.gestion_type_avenant.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.droit_access_type_avenant.name(), FonctionnaliteModuleParametrage.droit_access_type_avenant.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.agencement_avenant.name(), FonctionnaliteModuleParametrage.agencement_avenant.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.fractionnement.name(), FonctionnaliteModuleParametrage.fractionnement.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.exoneration.name(), FonctionnaliteModuleParametrage.exoneration.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.reduction.name(), FonctionnaliteModuleParametrage.reduction.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.timbre_dimension.name(), FonctionnaliteModuleParametrage.timbre_dimension.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.periodicite.name(), FonctionnaliteModuleParametrage.periodicite.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.natureMaladie.name(), FonctionnaliteModuleParametrage.natureMaladie.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.typeTaxe.name(), FonctionnaliteModuleParametrage.typeTaxe.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.regimetaxe.name(), FonctionnaliteModuleParametrage.regimetaxe.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.taxeprime.name(), FonctionnaliteModuleParametrage.taxeprime.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.typeDocument.name(), FonctionnaliteModuleParametrage.typeDocument.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.documentCategorie.name(), FonctionnaliteModuleParametrage.documentCategorie.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.gestionDocument_stock.name(), FonctionnaliteModuleParametrage.gestionDocument_stock.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.gestionDocument_compagne_detail.name(), FonctionnaliteModuleParametrage.gestionDocument_compagne_detail.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.gestionDocument_agence_detail.name(), FonctionnaliteModuleParametrage.gestionDocument_agence_detail.name(), new Date(), modsec5));

            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.timbre_gradue.name(), FonctionnaliteModuleParametrage.timbre_gradue.name(), new Date(), modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteModuleParametrage.exercice.name(), FonctionnaliteModuleParametrage.exercice.name(), new Date(), modsec5));
            for (OrclassFonctionnalites f : menuone) {
                if (this.fonctionnalitesDao.findEntityHavingValue("libelle", f.getLibelle()) == null) {
                    this.fonctionnalitesDao.create(f);
                }
            }
        }
        return menuone;
    }

//    @Override
//    public void addTypeApporteur() {
//        OrclassTypeApporteur tps = null;
//        for (TypeApporteur tb : TypeApporteur.values()) {
//            if (tb.equals(TypeApporteur.autres)) {
//                continue;
//            } else if (typeApporteurDao.findEntityHavingValue("typeApporteur", tb) == null) {
//                tps = new OrclassTypeApporteur();
//                tps.setTypeApporteur(tb);
//                tps.setShowAllCompagnies(Boolean.TRUE);
//                switch (tb) {
//                    case administrateur:
//                        tps.setCode("AD");
//                        break;
//                    case agent_general:
//                        tps.setCode("GG");
//                        break;
//                    case apporteur_agree:
//                        tps.setCode("AA");
//                        break;
//                    case apporteur_libre:
//                        tps.setCode("LB");
//                        break;
//                    case apporteur_mandaté:
//                        tps.setCode("AM");
//                        break;
//                    case chef_bureau_direct:
//                        tps.setCode("CB");
//                        break;
//                    case client:
//                        tps.setCode("CL");
//                        break;
//                    case courtier:
//                        tps.setCode("CT");
//                        break;
//
//                    case employe:
//                        tps.setCode("EM");
//                        break;
//                    case dg:
//                        tps.setCode("DG");
//                        break;
//                    case employe_commercial:
//                        tps.setCode("EA");
//                        break;
//                    case commercial:
//                        tps.setCode("AL");
//                        break;
//                }
//
//                typeApporteurDao.create(tps);
//            }
//        }
//    }
//    @Override
//    public void addClasse() {
//        OrclassClasses cl = null;
//        for (LibelleClasse lib : LibelleClasse.values()) {
//            if (classesDao.findClasseByLibelleClass(lib) != null) {
//                continue;
//            }
//            cl = new OrclassClasses();
//            cl.setLibelle(lib);
//            cl.setShowAllCompagnies(Boolean.TRUE);
//            cl.setDateCreation(new Date());
//            switch (lib) {
//                case acceptation:
//                    cl.setCode("9");
//                    break;
//                case assurance_credit_caution:
//                    cl.setCode("7");
//                    break;
//                case assurance_personne:
//                    cl.setCode("8");
//                    break;
//                case assurance_transport:
//                    cl.setCode("6");
//                    break;
//                case incendit_risque_annexe:
//                    cl.setCode("1");
//                    break;
//                case responsable_civile:
//                    cl.setCode("4");
//                    break;
//                case risque_divers:
//                    cl.setCode("3");
//                    break;
//                case vehicule_terrestre_moteur:
//                    cl.setCode("5");
//                    break;
//                case risque_technique:
//                    cl.setCode("2");
//                    break;
//            }
//            classesDao.create(cl);
//        }
//    }
//    @Override
//    public void addBranches() {
//        OrclassBranches br = null;
//        OrclassClasses cl = null;
//        if (classesDao.findAll() != null) {
//            for (LibelleClasse lib : LibelleClasse.values()) {
//                if (lib.equals(LibelleClasse.autres)) {
//                    continue;
//                }
//                cl = classesDao.findEntityHavingValue("libelle", lib);
//                if (cl != null) {
//                    switch (lib) {
//                        case acceptation:
//
//                            break;
//                        case assurance_credit_caution:
//                            for (LibelleBranche libr : LibelleBranche.values()) {
//
//                                switch (libr) {
//
//                                    case credit:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("23");
//                                        br.setLibelle(libr);
//                                        br.setIdClasse(cl);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                    case caution:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("24");
//                                        br.setLibelle(libr);
//                                        br.setIdClasse(cl);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                }
//
//                            }
//                            break;
//                        case assurance_personne:
//                            for (LibelleBranche libr : LibelleBranche.values()) {
//
//                                switch (libr) {
//
//                                    case accident_corporel:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("20");
//                                        br.setLibelle(libr);
//                                        br.setIdClasse(cl);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                    case sante:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("21");
//                                        br.setLibelle(libr);
//                                        br.setIdClasse(cl);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                    case assistance_voyage:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("22");
//                                        br.setLibelle(libr);
//                                        br.setIdClasse(cl);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                }
//
//                            }
//                            break;
//                        case assurance_transport:
//                            for (LibelleBranche libr : LibelleBranche.values()) {
//
//                                switch (libr) {
//
//                                    case transport_aerien:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("6");
//                                        br.setIdClasse(cl);
//                                        br.setLibelle(libr);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                    case transport_maritine:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("7");
//                                        br.setIdClasse(cl);
//                                        br.setLibelle(libr);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                    case autre_transport:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("8");
//                                        br.setLibelle(libr);
//                                        br.setIdClasse(cl);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                }
//
//                            }
//                            break;
//                        case incendit_risque_annexe:
//                            for (LibelleBranche libr : LibelleBranche.values()) {
//
//                                switch (libr) {
//
//                                    case incendie_autre_domage:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("4");
//                                        br.setIdClasse(cl);
//                                        br.setLibelle(libr);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                    case perte_peniculaire_diverse:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("9");
//                                        br.setIdClasse(cl);
//                                        br.setLibelle(libr);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                }
//
//                            }
//                            break;
//                        case responsable_civile:
//                            for (LibelleBranche libr : LibelleBranche.values()) {
//
//                                switch (libr) {
//
//                                    case responsabilite_civil_general:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("3");
//                                        br.setIdClasse(cl);
//                                        br.setLibelle(libr);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                }
//
//                            }
//                            break;
//                        case risque_divers:
//                            for (LibelleBranche libr : LibelleBranche.values()) {
//
//                                switch (libr) {
//
//                                    case autre_risque_direc_dommage:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("5");
//                                        br.setIdClasse(cl);
//                                        br.setLibelle(libr);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//
//                                }
//
//                            }
//                            break;
//                        case vehicule_terrestre_moteur:
//                            for (LibelleBranche libr : LibelleBranche.values()) {
//
//                                switch (libr) {
//
//                                    case automobile:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("1");
//                                        br.setIdClasse(cl);
//                                        br.setLibelle(libr);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                }
//
//                            }
//                            break;
//                        case risque_technique:
//                            for (LibelleBranche libr : LibelleBranche.values()) {
//
//                                switch (libr) {
//
//                                    case autre_assurance_dommage_personne:
//                                        if (branchesDao.findBrancheByLibelleBranche(libr) != null) {
//                                            continue;
//                                        }
//                                        br = new OrclassBranches();
//                                        br.setDateCreation(new Date());
//                                        br.setShowAllCompagnies(Boolean.TRUE);
//                                        br.setCode("25");
//                                        br.setIdClasse(cl);
//                                        br.setLibelle(libr);
//                                        br.setDateCreation(new Date());
//                                        branchesDao.create(br);
//                                        break;
//                                }
//
//                            }
//                            break;
//
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void addCategorie() {
//        /*
//        ajout des categories visibles par tout les compagnies du pool
//         */
//        OrclassCategories cat = null;
//        Collection<OrclassBranches> colBranches = new ArrayList<>();
//        colBranches = branchesDao.findAll();
//        if (!colBranches.isEmpty()) {
//            for (OrclassBranches br : colBranches) {
//                switch (br.getLibelle()) {
//                    case automobile:
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_tpv) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1044");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_tpv);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.assistance_etudiant) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2209");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.assistance_etudiant);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.retrocession_pool) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1114");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.retrocession_pool);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.garagiste_6a) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1010");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.garagiste_6a);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.transport_prive_marchandise_inferieur3point5tonne) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1020");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.transport_prive_marchandise_inferieur3point5tonne);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.transport_prive_marchandise_superieur3point5tonne) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1030");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.transport_prive_marchandise_superieur3point5tonne);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.taxis_ville_4a) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1040");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.taxis_ville_4a);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.personnel_roues_2a3) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1050");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.personnel_roues_2a3);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.auto_ecole) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1070");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.auto_ecole);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.auto_location) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1080");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.auto_location);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.english_mobile_chantier) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1090");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.english_mobile_chantier);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.ambulance) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1100");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.ambulance);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.flotte) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1110");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.flotte);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.tpv_car_auto_et_bus) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1041");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.tpv_car_auto_et_bus);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.transport_personnel_eleve) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1042");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.transport_personnel_eleve);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.transport_personnel_eleve) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1042");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.transport_personnel_eleve);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.commercial_roue_2a3) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1051");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.commercial_roue_2a3);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.garanti_2_3_roues) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1061");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.garanti_2_3_roues);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.tourisme_vehicule_personnel) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1052");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.tourisme_vehicule_personnel);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.auto_ecole_utilitaire_taxi) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1071");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.auto_ecole_utilitaire_taxi);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.auto_ecole_2a3_roues) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1072");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.auto_ecole_2a3_roues);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.autre_flotte) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1111");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.autre_flotte);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.colletivite_public) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1101");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.colletivite_public);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.tracteur_agricole) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1102");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.tracteur_agricole);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.tracteur_forestier) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1103");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.tracteur_forestier);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.camion_vidange) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1104");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.camion_vidange);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.ramassage_ordur) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1105");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.ramassage_ordur);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.autre_camion_tracteur) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1106");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.autre_camion_tracteur);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.tourisme_vehicule_personnel) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1011");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.tourisme_vehicule_personnel);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.flotte_pack) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1113");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.flotte_pack);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
////                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.package_nz) == null) {
////                            cat = new OrclassCategories();
////                            cat.setCode("1115");
////                            cat.setShowAllCompagnies(Boolean.TRUE);
////                            cat.setDateCreation(new Date());
////                            cat.setLibelle(LibelleCategorie.package_nz);
////                            cat.setIdBranche(br);
////                            categoriesDao.create(cat);
////
////                        }
////                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.package_np) == null) {
////                            cat = new OrclassCategories();
////                            cat.setCode("1116");
////                            cat.setShowAllCompagnies(Boolean.TRUE);
////                            cat.setDateCreation(new Date());
////                            cat.setLibelle(LibelleCategorie.package_np);
////                            cat.setIdBranche(br);
////                            categoriesDao.create(cat);
////
////                        }
////
////                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.package_nb) == null) {
////                            cat = new OrclassCategories();
////                            cat.setCode("1117");
////                            cat.setShowAllCompagnies(Boolean.TRUE);
////                            cat.setDateCreation(new Date());
////                            cat.setLibelle(LibelleCategorie.package_nb);
////                            cat.setIdBranche(br);
////                            categoriesDao.create(cat);
////
////                        }
//                        break;
//                    case sante:
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.maladie_groupe) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2103");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.maladie_groupe);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.maladie_individuel) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2102");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.maladie_individuel);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.maladi_famille) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2101");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.maladi_famille);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.maladie_complementaire) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2104");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.maladie_complementaire);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.maladie_association) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2105");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.maladie_association);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.multi_risque_sante_individuel) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2106");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.multi_risque_sante_individuel);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.multirisque_sante_groupe) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2107");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.multirisque_sante_groupe);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//                    case assistance_voyage:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.voyage_individuel_afrique) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2203");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.voyage_individuel_afrique);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.voyage_individuel_europe) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2204");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.voyage_individuel_europe);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.voyage_individul_monde_basic) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2205");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.voyage_individul_monde_basic);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.voyage_individuel_monde_plus) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2206");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.voyage_individuel_monde_plus);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.voyage_individuel_perinage) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2207");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.voyage_individuel_perinage);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.assistance_etudiant) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2209");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.assistance_etudiant);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.voyage_individuel) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2201");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.voyage_individuel);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.voyage_group) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2202");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.voyage_group);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//                    case transport_aerien:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.corps_aerien) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("6002");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.corps_aerien);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_transport_aerien) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("6003");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_transport_aerien);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.faculte_aerien) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("6001");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.faculte_aerien);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.faculte_aerien) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("6001");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.faculte_aerien);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//
//                    case caution:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.caution_soumission) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1503");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.caution_soumission);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.caution_bon_fin) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1504");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.caution_bon_fin);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.caution_avance_demarrage) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1505");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.caution_avance_demarrage);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.cautio_retenu_garantie) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1506");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.cautio_retenu_garantie);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.caution_proffessionnel) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1507");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.caution_proffessionnel);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.caution_direct) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1501");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.caution_direct);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_caution) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1508");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_caution);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//
//                    case incendie_autre_domage:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_incendie_autre_dommage) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("4006");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_incendie_autre_dommage);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.risque_simple) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("4001");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.risque_simple);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.risque_commerciaux) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("4002");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.risque_commerciaux);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.risque_industriel) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("4003");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.risque_industriel);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.global_dommage) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("4004");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.global_dommage);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.multi_risque_habitation) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("4005");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.multi_risque_habitation);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//                    case responsabilite_civil_general:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_rc) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3015");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_rc);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_chef_entreprise) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3001");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_chef_entreprise);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_manifestation_sportif) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3002");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_manifestation_sportif);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_affreteur_navire) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3003");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_affreteur_navire);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_aerien) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3004");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_aerien);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_chef_famille) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3005");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_chef_famille);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_proffessionel) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3006");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_proffessionel);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_proprietaire_immeuble) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3007");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_proprietaire_immeuble);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_constructeur_navire) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3008");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_constructeur_navire);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_decennal) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3009");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_decennal);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_transporteur) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3010");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_transporteur);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_risque_petrolier) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3011");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_risque_petrolier);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_exploitation_aviation) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3012");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_exploitation_aviation);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_scolaire) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3013");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_scolaire);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.rc_diverse) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("3014");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.rc_diverse);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//
//                    case autre_risque_direc_dommage:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.bris_machine) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("5001");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.bris_machine);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.tous_risque_chantier) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("5002");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.tous_risque_chantier);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.tous_risque_informatique) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("5003");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.tous_risque_informatique);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.tourisque_montage) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("5004");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.tourisque_montage);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.global_banque) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("5005");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.global_banque);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.transport_fond) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("5006");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.transport_fond);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_autr_risque_dommage) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("5007");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_autr_risque_dommage);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//
//                    case credit:
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_credit) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1406");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_credit);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.caution_indirect) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1502");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.caution_indirect);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.credit_insolvabilite) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1401");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.credit_insolvabilite);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.credit_exportation) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1402");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.credit_exportation);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.credit_vente_temperament) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1403");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.credit_vente_temperament);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.credit_hypothecaire) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1404");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.credit_hypothecaire);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.credit_agricole) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1405");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.credit_agricole);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.credit_agricole) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("1405");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.credit_agricole);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//
//                    case transport_maritine:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.faculte_maritime) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7001");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.faculte_maritime);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.faculte_fluvial) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7002");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.faculte_fluvial);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.off_shore) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7003");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.off_shore);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.corps_maritime) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7004");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.corps_maritime);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.corps_fluviaux) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7005");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.corps_fluviaux);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.corps_plaissance) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7006");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.corps_plaissance);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.corps_peche) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7007");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.corps_peche);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.fap_sauf) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7008");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.fap_sauf);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.tous_risque) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7009");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.tous_risque);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.vol) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7010");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.vol);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.transport_faculte_maritime_terrestre_aerien) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7011");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.transport_faculte_maritime_terrestre_aerien);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.accident_caracterise) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7012");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.accident_caracterise);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.coulage) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7013");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.coulage);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.dommage_corps) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7014");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.dommage_corps);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.responsabilite_cilvil) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7015");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.responsabilite_cilvil);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
////                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.falcute_maritime) == null) {
////                            cat = new OrclassCategories();
////                            cat.setCode("7111");
////                            cat.setShowAllCompagnies(Boolean.TRUE);
////                            cat.setDateCreation(new Date());
////                            cat.setLibelle(LibelleCategorie.falcute_maritime);
////                            cat.setIdBranche(br);
////                            categoriesDao.create(cat);
////
////                        }
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_transport_maritine) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7016");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_transport_maritine);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_corps_maritime) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7017");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_corps_maritime);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_off_shore) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("7018");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_off_shore);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//                    case autre_transport:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.acceptation_autre_transport) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("8005");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.acceptation_autre_transport);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.faculte_terrestre) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("8001");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.faculte_terrestre);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.faculte_feroviaire) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("8002");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.faculte_feroviaire);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.corps_ferroviaire) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("8003");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.corps_ferroviaire);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.police_voyage) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("8004");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.police_voyage);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.police_abonement) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("8111");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.police_abonement);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//
//                    case perte_peniculaire_diverse:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.perte_exploitation_apres_incendie) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("9001");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.perte_exploitation_apres_incendie);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.perte_exploitation_apres_bris_machine) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("9002");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.perte_exploitation_apres_bris_machine);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.perte_exploitation_apres_tri) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("9003");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.perte_exploitation_apres_tri);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.perte_exploitation_apres_trm) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("9004");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.perte_exploitation_apres_trm);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//
//                    case accident_corporel:
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.incendi_accient_personnel) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2001");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.incendi_accient_personnel);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.individuel_accident_group) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2002");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.individuel_accident_group);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.individuel_aviation_personnel_navigation) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2003");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.individuel_aviation_personnel_navigation);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.individuel_aviation_passagers) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2004");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.individuel_aviation_passagers);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.individuel_voyage) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2005");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.individuel_voyage);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.complementaire_accident_travail) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2006");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.complementaire_accident_travail);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        if (categoriesDao.findEntityHavingValue("libelle", LibelleCategorie.frais_funerail_group) == null) {
//                            cat = new OrclassCategories();
//                            cat.setCode("2007");
//                            cat.setShowAllCompagnies(Boolean.TRUE);
//                            cat.setDateCreation(new Date());
//                            cat.setLibelle(LibelleCategorie.frais_funerail_group);
//                            cat.setIdBranche(br);
//                            categoriesDao.create(cat);
//
//                        }
//
//                        break;
//
//                }
//
//            }
//        }
//    }
//
//    @Override
//    public void addTypeReference() {
//        OrclasseType_Garantie ref;
//        int i = 100;
//        for (TypeGarantie tg : TypeGarantie.values()) {
//            if (tg.equals(TypeGarantie.autres)) {
//                continue;
//            }
//            ref = type_GarantieDao.findEntityHavingValue("libelle", tg);
//            if (ref == null) {
//                ref = new OrclasseType_Garantie();
//                ref.setCode("" + i);
//                ref.setLibelle(tg);
//                ref.setShowAllCompagnies(Boolean.TRUE);
//                type_GarantieDao.create(ref);
//                i++;
//
//            }
//        }
//    }
//
//    @Override
//    public void addTypeCaracteristique() {
//        List<OrclassType_Caracteristique> colTC = new ArrayList<>();
//        OrclassType_Caracteristique tc;
//        if (type_CaracteristiqueDao.findAll() == null || type_CaracteristiqueDao.findAll().isEmpty()) {
//            tc = new OrclassType_Caracteristique("B", "Boolean");
//            colTC.add(tc);
//            tc = new OrclassType_Caracteristique("C", "Liste");
//            colTC.add(tc);
//            tc = new OrclassType_Caracteristique("D", "Date");
//            colTC.add(tc);
//            tc = new OrclassType_Caracteristique("A", "Val. Entière");
//            colTC.add(tc);
//            tc = new OrclassType_Caracteristique("R", "Date référence");
//            colTC.add(tc);
//            tc = new OrclassType_Caracteristique("S", "Liste à Classe");
//            colTC.add(tc);
//            tc = new OrclassType_Caracteristique("T", "Texte");
//            colTC.add(tc);
//            tc = new OrclassType_Caracteristique("V", "Valeur");
//            colTC.add(tc);
//            type_CaracteristiqueDao.createAll(colTC);
//        }
//    }
//
//    @Override
//    public void addUniteCaracteristique() {
//        List<OrclassUnite_Caracteristique> colUC = new ArrayList<>();
//        OrclassUnite_Caracteristique uc;
//        if (unite_CaracteristiqueDao.findAll() == null || unite_CaracteristiqueDao.findAll().isEmpty()) {
//            uc = new OrclassUnite_Caracteristique("-99", "Monnaie police");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("21", "¿");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("22", "%°");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("30", "pièce(s)");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("1", "Quintal");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("2", "Tonnes");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("3", "Tonneaux");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("4", "Kgs");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("5", "FCFA");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("6", "m ²");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("7", "Unité");
//            colUC.add(uc);
//
//            uc = new OrclassUnite_Caracteristique("8", "Ans");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("9", "Chevaux");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("10", "m");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("11", "Jours");
//            colUC.add(uc);
//
//            uc = new OrclassUnite_Caracteristique("12", "Ha");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("13", "Qx/Ha");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("14", "DA/Qtl");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("15", "Litres");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("16", "$ US");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("17", "%");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("18", "Mois");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("19", "DA/Kgs");
//            colUC.add(uc);
//            uc = new OrclassUnite_Caracteristique("20", "FCFA");
//            colUC.add(uc);
//            unite_CaracteristiqueDao.createAll(colUC);
//
//        }
//    }
    @Override
    public Collection<OrclassActions> getActionByFonctionnaliteByModuleParamettrage() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsParametrage);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsParametrage, "Réferentiel"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsParametrage);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
        Collection<OrclassActions> colActions = new ArrayList<>();
        OrclassActions actions;
        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null && !menuone.isEmpty() && (actionsDao.getAllactionByFonctionnaliteForUserAccess(modsec5) == null || actionsDao.getAllactionByFonctionnaliteForUserAccess(modsec5).isEmpty())) {
            for (OrclassFonctionnalites fonction : menuone) {
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.caracteristique_assure.name())) {

                    for (Actions action : Actions.values()) {
//                        colActions = new ArrayList<>();
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.caracteristique_assure.name())) {
                            try {
                                if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                    colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                                }
                            } catch (Exception e) {

                                System.err.println("sortie action: " + actionsDao.finkey(fonction, modsec5, action.toString()));
                                System.err.println("sortie action: " + action);
                            }

                        }

                    }

                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.caracteristique_group.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.caracteristique_group.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.caracteristique_reference.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.caracteristique_reference.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.commission_apporteur.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.commission_apporteur.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.commission_intermediaire.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.commission_intermediaire.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.garantie_assure.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.garantie_assure.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.garantie_groupe.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.garantie_groupe.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.garantie_reference.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.garantie_reference.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.gestion_duree.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.gestion_duree.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.initialisation_registre.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.initialisation_registre.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.registre_production.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.registre_production.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.registre_sinistre.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.registre_sinistre.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.prestation_reference.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.prestation_reference.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.rubrique_assure.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.rubrique_assure.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.rubrique_group.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.rubrique_group.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.rubrique_reference.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.rubrique_reference.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.gestion_Tarif.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleParametrage.gestion_Tarif.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
            }
        }
        return colActions;
    }

    @Override
    public Collection<OrclassActions> getActionByFonctionnaliteByAdp() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsADP);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsADP, "ADP"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsADP);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
        Collection<OrclassActions> colActions = new ArrayList<>();
        OrclassActions actions;
        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null && !menuone.isEmpty()) {
            for (OrclassFonctionnalites fonction : menuone) {
                if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_assurance_produit_voyage.name())) {

                    for (Actions action : Actions.values()) {
                        colActions = new ArrayList<>();
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_assurance_produit_voyage.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }

                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_avenant_sante.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_avenant_sante.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_echeance_sante.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_echeance_sante.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_prise_en_charge_sante_maladie.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_prise_en_charge_sante_maladie.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_produit_sante.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_produit_sante.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_remboursement_sante_maladie.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_remboursement_sante_maladie.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }
                if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_versement_sante.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestion_versement_sante.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

                if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestoion_encaissement_sante.name())) {

                    for (Actions action : Actions.values()) {
                        if (fonction.getLibelle().equals(FonctionnaliteModuleAssuranceAdp.gestoion_encaissement_sante.name())) {
                            if (actionsDao.finkey(fonction, modsec5, action.toString()) == null) {
                                colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonction));

                            }
                        }

                    }
                    actionsDao.createAll(colActions);

                }

            }
        }
        return colActions;
    }

    @Override
    public Collection<OrclassMenusAcces> getAllMenuByFonctionnaliteByActionByModuleParametrage() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsParametrage);
        OrclassMenusAcces menus_acces;
        OrclassMenus menu = null;
        Collection<OrclassMenusAcces> colMenu = new ArrayList<>();
        Collection<OrclassFonctionnalites> colFonctionnalite = new ArrayList<>();
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsParametrage, "Réferentiel"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsParametrage);

        }

        if (modsec5 != null) {
            colFonctionnalite = fonctionnalitesDao.getAllFonctionnaireByModule(modsec5);
            for (OrclassFonctionnalites f : colFonctionnalite) {
                menu = this.getMenusParametrageByFonctionnalite(f);
                if (menu == null) {
                    continue;
                }
                for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(f, modsec5)) {
                    System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + f.getCode() + " action :" + action);
                    if (menusAccesDao.finkey(menu, f, action) != null) {
                        continue;
                    }
                    menus_acces = new OrclassMenusAcces();
                    menus_acces.setIdFonctionnalite(f);
                    menus_acces.setIdMenu(menu);
                    menus_acces.setIdAction(action);
                    colMenu.add(menus_acces);
                }

            }

            menusAccesDao.createAll(colMenu);

        }
        return colMenu;
    }

    @Override
    public Collection<OrclassMenusAcces> getAllMenuByFonctionnaliteByActionByModuleAdp() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsADP);
        OrclassMenusAcces menus_acces;
        OrclassMenus menu = null;
        Collection<OrclassMenusAcces> colMenu = new ArrayList<>();
        Collection<OrclassFonctionnalites> colFonctionnalite = new ArrayList<>();
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsADP, "ADP"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsADP);

        }

        if (modsec5 != null) {
            colFonctionnalite = fonctionnalitesDao.getAllFonctionnaireByModule(modsec5);
            for (OrclassFonctionnalites f : colFonctionnalite) {
                menu = this.getMenusAdpByFonctionnalite(f);
                if (menu == null) {
                    continue;
                }
                for (OrclassActions action : actionsDao.getAllactionByFonctionnalite(f, modsec5)) {
                    System.out.println("traitement : menu: " + menu.getCode() + " fonctionnalite :" + f.getCode() + " action :" + action);
                    if (menusAccesDao.finkey(menu, f, action) != null) {
                        continue;
                    }
                    menus_acces = new OrclassMenusAcces();
                    menus_acces.setIdFonctionnalite(f);
                    menus_acces.setIdMenu(menu);
                    menus_acces.setIdAction(action);
                    colMenu.add(menus_acces);
                }

            }

            menusAccesDao.createAll(colMenu);

        }
        return colMenu;
    }

    public OrclassMenus getMenusParametrageByFonctionnalite(OrclassFonctionnalites f) {
        OrclassMenus menu = null;
        FonctionnaliteModuleParametrage pf = FonctionnaliteModuleParametrage.valueOf(f.getCode());
        switch (pf) {
            case caracteristique_assure:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_assure_caract);
                break;
            case caracteristique_group:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_group_caract);
                break;
            case caracteristique_reference:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_caracteristique);
                break;
            case commission_apporteur:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_apporteur);
                break;
            case commission_intermediaire:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_intermediaire);
                break;
            case garantie_assure:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_garantie);
                break;
            case garantie_groupe:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_group_garantie);
                break;
            case garantie_reference:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_ref_garantie);
                break;
            case gestion_duree:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_duree);
                break;
            case initialisation_registre:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_initialisation_registre);
                break;
            case prestation_reference:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_prestation);
                break;
            case registre_production:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_registre_production);
                break;
            case registre_sinistre:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_registre_sinistre);
                break;
            case rubrique_assure:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_assure_rubrique);
                break;
            case rubrique_group:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_group_rubrique);
                break;
            case rubrique_reference:
                menu = menudao.getMenuByCode(ModuleMenu.mdsp_rubrique);
                break;
        }
        return menu;
    }

    public OrclassMenus getMenusAdpByFonctionnalite(OrclassFonctionnalites f) {
        OrclassMenus menu = null;
        FonctionnaliteModuleAssuranceAdp pf = FonctionnaliteModuleAssuranceAdp.valueOf(f.getCode());
        switch (pf) {
            case gestion_assurance_produit_voyage:
                menu = menudao.getMenuByCode(ModuleMenu.mds_mVoyage);
                break;
            case gestion_avenant_sante:
                menu = menudao.getMenuByCode(ModuleMenu.mAvenant);
                break;
            case gestion_echeance_sante:
                menu = menudao.getMenuByCode(ModuleMenu.mEcheance);
                break;

            case gestion_produit_sante:
                menu = menudao.getMenuByCode(ModuleMenu.mAffaireNouvelle);
                break;
            case gestion_remboursement_sante_maladie:
                menu = menudao.getMenuByCode(ModuleMenu.mMaladie_remboursement);
                break;
            case gestion_versement_sante:
                menu = menudao.getMenuByCode(ModuleMenu.mVersement);
                break;
            case gestoion_encaissement_sante:
                menu = menudao.getMenuByCode(ModuleMenu.mEncaissement);
                break;

        }
        return menu;
    }

//    @Override
//    public void addTypeTarif() {
//        List<OrclassTypeTarif> colTarif = new ArrayList<>();
//        OrclassTypeTarif tarif;
//        if (typeTarifDao.findAll() == null || typeTarifDao.findAll().isEmpty()) {
//            tarif = new OrclassTypeTarif("N", "Normal");
//            colTarif.add(tarif);
//            tarif = new OrclassTypeTarif("NS", "Normal Sinistre");
//            colTarif.add(tarif);
//            typeTarifDao.createAll(colTarif);
//        }
//
//    }
//    @Override
//    public void addTypeTimbreDimension() {
//        List<OrclasseTimbreDimension> colTimbreDimension = new ArrayList<>();
//        OrclasseTimbreDimension t;
//        if (timbreDimensionDao.findAll() == null || timbreDimensionDao.findAll().isEmpty()) {
//            t = new OrclasseTimbreDimension("v", "VIGNETTE");
//            colTimbreDimension.add(t);
//            t = new OrclasseTimbreDimension("T", "Coût de Police");
//            colTimbreDimension.add(t);
//            t = new OrclasseTimbreDimension("0", "Aucun");
//            colTimbreDimension.add(t);
//            t = new OrclasseTimbreDimension("A", "ASAC FGA");
//            colTimbreDimension.add(t);
//            t = new OrclasseTimbreDimension("D", "DEPOSIT");
//            colTimbreDimension.add(t);
//            timbreDimensionDao.createAll(colTimbreDimension);
//        }
//    }
//    @Override
//    public void addTypeQualite() {// type quitance
//        List<OrclassTypeQuitance> colTypeQuitance = new ArrayList<>();
//        OrclassTypeQuitance q;
//        if (typeQuitanceDao.findAll() == null || typeQuitanceDao.findAll().isEmpty()) {
//            q = new OrclassTypeQuitance("C", "Emission");
//            colTypeQuitance.add(q);
//            q = new OrclassTypeQuitance("R", "Ristourne");
//            colTypeQuitance.add(q);
//            q = new OrclassTypeQuitance("A", "Annulation et Abandon");
//            colTypeQuitance.add(q);
//
//            timbreDimensionDao.createAll(colTypeQuitance);
//
//        }
//    }
//    @Override
//    public void addDevise() {
//        List<OrclassDevise> colTypeDevise = new ArrayList<>();
//        OrclassDevise d;
//        if (deviseDao.findAll() == null || deviseDao.findAll().isEmpty()) {
//            d = new OrclassDevise("EURO", "EUROPEAN CURRENCY");
//            colTypeDevise.add(d);
//            d = new OrclassDevise("CFA", "FRANC CFA");
//            colTypeDevise.add(d);
//            d = new OrclassDevise("$", "THE DOLLAR");
//            colTypeDevise.add(d);
//            d = new OrclassDevise("SF", "SUISSE FRNC");
//            colTypeDevise.add(d);
//
//            deviseDao.createAll(colTypeDevise);
//
//        }
//    }
//
//    @Override
//    public void addExoneration() {
//        List<OrclassExoneration> colExoneration = new ArrayList<>();
//        OrclassExoneration d;
//        if (exonerationDao.findAll() == null || exonerationDao.findAll().isEmpty()) {
//            d = new OrclassExoneration("3", "TVA/ACC.");
//            colExoneration.add(d);
//            d = new OrclassExoneration("0", "AUCUN");
//            colExoneration.add(d);
//            d = new OrclassExoneration("1", "TVA");
//            colExoneration.add(d);
//            d = new OrclassExoneration("2", "TVA_VIGNETTE");
//            colExoneration.add(d);
//            d = new OrclassExoneration("9", "Toutes Taxes(TVA+tva/ASACetac");
//            colExoneration.add(d);
//            d = new OrclassExoneration("4", "VIGNETTE");
//            colExoneration.add(d);
//            d = new OrclassExoneration("5", " pool tpv");
//            colExoneration.add(d);
//            d = new OrclassExoneration("RN", "Régime Normal Taxe");
//            colExoneration.add(d);
//
//            exonerationDao.createAll(colExoneration);
//
//        }
//    }
//
//    @Override
//    public void addReduction() {
//        List<OrclassRefReduction> colReduction = new ArrayList<>();
//        OrclassRefReduction d;
//        if (reductionDao.findAll() == null || reductionDao.findAll().isEmpty()) {
//            d = new OrclassRefReduction("10", "Reduction e-insurance à 15%");
//            colReduction.add(d);
//            d = new OrclassRefReduction("R1", "Reduction de 10% RC");
//            colReduction.add(d);
//
//            reductionDao.createAll(colReduction);
//
//        }
//    }
//
//    @Override
//    public void addConvention() {
//        List<OrclassConvention> colConvention = new ArrayList<>();
//        OrclassConvention d;
//        if (conventionDao.findAll() == null || conventionDao.findAll().isEmpty()) {
//            d = new OrclassConvention("1", "Convention e-insurance");
//            colConvention.add(d);
//
//            reductionDao.createAll(colConvention);
//
//        }
//    }
    @Override
    public void addMenuAccessForAdmin() {
        //ajouter le menu type caracteristique dans le profil admin et le Menu Access profil
        OrclassMenus menu = null;
        OrclassProfils profils = null;
        OrclassFonctionnalites fonctionnalites = null;
        OrclassUtilisateursAcces uaccess = null;
        profils = profilsDao.findEntityHavingValue("code", "admin");

        List<OrclassActions> listActions = new ArrayList<>();
        OrclassActions actions = null;
        OrclassMenusAcces mAccess = null;
        OrclassProfilsAcces pAccess = null;
        OrclassUtilisateursAcces userAccess = null;
        OrclassUtilisateurs user = null;
        List<String> colString = new ArrayList<>();
        List<OrclassUtilisateurs> listUsersForProfilAdmin = new ArrayList<>();
        List<OrclassUtilisateursAcces> listeUaccess = new ArrayList<>(utilisateursAccesDao.findAllEntitiesHavingValue("idProfil", profils));
        FonctionnaliteModuleParametrage fonctionnaliteModuleParametrage = null;
        if (profils == null || !listeUaccess.isEmpty()) {
            return;
        }

        colString.add(ModuleMenu.mdsp_assure_caract);
        colString.add(ModuleMenu.mdsp_type_caracteristique);
        colString.add(ModuleMenu.mdsp_ref_caract);
        colString.add(ModuleMenu.mdsp_ref_prestation);
        colString.add(ModuleMenu.mdsp_assure_garantie);
        colString.add(ModuleMenu.mdsp_tarif);
        // reference.rubrique
        colString.add(ModuleMenu.mdsp_ref_rubrique);
        colString.add(ModuleMenu.mdsp_exoneration_taxe);
        colString.add(ModuleMenu.mdsp_plafond_maladie);
        listUsersForProfilAdmin = userDao.userByProfil(profils);

        for (String m : colString) {
            menu = menudao.getMenuByCode(m);
            if (menu.getCode().equals(ModuleMenu.mdsp_assure_caract)) {
                fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(menu.getIdModule(), FonctionnaliteModuleParametrage.caracteristique_assure.toString());
                fonctionnaliteModuleParametrage = FonctionnaliteModuleParametrage.caracteristique_assure;

            } else if (menu.getCode().equals(ModuleMenu.mdsp_type_caracteristique)) {
                fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(menu.getIdModule(), FonctionnaliteModuleParametrage.type_caracteristique.toString());
                fonctionnaliteModuleParametrage = FonctionnaliteModuleParametrage.type_caracteristique;
            } else if (menu.getCode().equals(ModuleMenu.mdsp_ref_caract)) {
                fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(menu.getIdModule(), FonctionnaliteModuleParametrage.caracteristique_reference.toString());
                fonctionnaliteModuleParametrage = FonctionnaliteModuleParametrage.caracteristique_reference;
            } else if (menu.getCode().equals(ModuleMenu.mdsp_ref_prestation)) {
                fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(menu.getIdModule(), FonctionnaliteModuleParametrage.prestation_reference.toString());
                fonctionnaliteModuleParametrage = FonctionnaliteModuleParametrage.prestation_reference;
            } else if (menu.getCode().equals(ModuleMenu.mdsp_assure_garantie)) {
                fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(menu.getIdModule(), FonctionnaliteModuleParametrage.garantie_assure.toString());
                fonctionnaliteModuleParametrage = FonctionnaliteModuleParametrage.garantie_assure;
            } else if (menu.getCode().equals(ModuleMenu.mdsp_tarif)) {
                fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(menu.getIdModule(), FonctionnaliteModuleParametrage.gestion_Tarif.toString());
                fonctionnaliteModuleParametrage = FonctionnaliteModuleParametrage.gestion_Tarif;
            } else if (menu.getCode().equals(ModuleMenu.mdsp_plafond_maladie)) {
                fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(menu.getIdModule(), FonctionnaliteModuleParametrage.plafond_maladie.toString());
                fonctionnaliteModuleParametrage = FonctionnaliteModuleParametrage.plafond_maladie;
            } else if (menu.getCode().equals(ModuleMenu.mdsp_ref_rubrique)) {
                fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(menu.getIdModule(), FonctionnaliteModuleParametrage.rubrique_reference.toString());
                fonctionnaliteModuleParametrage = FonctionnaliteModuleParametrage.rubrique_reference;
            } else if (menu.getCode().equals(ModuleMenu.mdsp_exoneration_taxe)) {
                fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(menu.getIdModule(), FonctionnaliteModuleParametrage.exoneration_taxe.toString());
                fonctionnaliteModuleParametrage = FonctionnaliteModuleParametrage.exoneration_taxe;
            }
            if (menu == null) {
                continue;

            }

            if (profils != null && profils.getIdProfil() != null && menu != null && menu.getIdMenu() != null) {
                //creation du profil access pour le menu type caracteristique

                if (fonctionnalites == null) {
                    fonctionnalites = new OrclassFonctionnalites();
                    fonctionnalites.setCode(fonctionnaliteModuleParametrage.toString());
                    fonctionnalites.setLibelle(fonctionnaliteModuleParametrage.toString());
                    fonctionnalites.setIdModule(menu.getIdModule());
                    fonctionnalites.setDateCreation(new Date());
                    fonctionnalitesDao.create(fonctionnalites);

                }
                listActions = actionsDao.getAllactionByFonctionnalite(fonctionnalites, menu.getIdModule());
                if (listActions.isEmpty()) {
                    for (Actions act : Actions.values()) {
                        actions = actionsDao.finkey(fonctionnalites, menu.getIdModule(), act.toString());
                        if (actions == null) {
                            actions = new OrclassActions();
                            actions.setCode(act.toString());
                            actions.setLibelle(act.toString());
                            actions.setDateCreation(new Date());
                            actions.setIdFonctionnalite(fonctionnalites);
                            actionsDao.create(actions);

                        }
                        // creation du profil access
                        pAccess = profilsAccesDao.finkey(menu.getIdModule(), fonctionnalites, actions, profils);
                        if (pAccess == null) {
                            if (profilModuleDao.finkey(profils, menu.getIdModule()) == null) {
                                return;
                            }
                            pAccess = new OrclassProfilsAcces();
                            pAccess.setAutorisation(Boolean.TRUE);
                            pAccess.setIdAction(actions);
                            pAccess.setIdFonctionnalite(fonctionnalites);
                            pAccess.setIdProfil(profils);
                            pAccess.setIdModule(menu.getIdModule());
                            profilsAccesDao.create(pAccess);
                        }
                        mAccess = menusAccesDao.finkey(menu, fonctionnalites, actions);
                        if (mAccess == null) {
                            mAccess = new OrclassMenusAcces();
                            mAccess.setIdAction(actions);
                            mAccess.setIdFonctionnalite(fonctionnalites);
                            mAccess.setIdMenu(menu);
                            menusAccesDao.create(mAccess);
                        }
                        // ajoutons le a l utilisateur de profil admin
                        for (OrclassUtilisateurs u : listUsersForProfilAdmin) {
                            userAccess = utilisateursAccesDao.finKey(pAccess, u);
                            if (userAccess == null) {
                                userAccess = new OrclassUtilisateursAcces();
                                userAccess.setAutorisation_action(Boolean.TRUE);
                                userAccess.setAutorisation_fonctionnalite(Boolean.TRUE);
                                userAccess.setAutorisation_action(Boolean.TRUE);
                                userAccess.setIdAction(actions);
                                userAccess.setIdFonctionnalite(fonctionnalites);
                                userAccess.setIdModule(menu.getIdModule());
                                userAccess.setIdProfil(profils);
                                userAccess.setIdUtilisateur(u);
                                utilisateursAccesDao.create(userAccess);
                            }
                        }

                    }
                }
                for (OrclassActions a : listActions) {
                    actions = a;

                    // creation du profil access
                    pAccess = profilsAccesDao.finkey(menu.getIdModule(), fonctionnalites, actions, profils);
                    if (pAccess == null) {
                        if (profilModuleDao.finkey(profils, menu.getIdModule()) == null) {
                            return;
                        }
                        pAccess = new OrclassProfilsAcces();
                        pAccess.setAutorisation(Boolean.TRUE);
                        pAccess.setIdAction(actions);
                        pAccess.setIdFonctionnalite(fonctionnalites);
                        pAccess.setIdProfil(profils);
                        pAccess.setIdModule(menu.getIdModule());
                        profilsAccesDao.create(pAccess);
                    }
                    mAccess = menusAccesDao.finkey(menu, fonctionnalites, actions);
                    if (mAccess == null) {
                        mAccess = new OrclassMenusAcces();
                        mAccess.setIdAction(actions);
                        mAccess.setIdFonctionnalite(fonctionnalites);
                        mAccess.setIdMenu(menu);
                        menusAccesDao.create(mAccess);
                    }

                    // ajoutons le a l utilisateur de profil admin
                    for (OrclassUtilisateurs u : listUsersForProfilAdmin) {
                        userAccess = utilisateursAccesDao.finKey(pAccess, u);
                        if (userAccess == null) {
                            userAccess = new OrclassUtilisateursAcces();
                            userAccess.setAutorisation_action(Boolean.TRUE);
                            userAccess.setAutorisation_fonctionnalite(Boolean.TRUE);
//                            userAccess.setAutorisation_action(Boolean.TRUE);
                            userAccess.setAutorisation_principal(Boolean.TRUE);
                            userAccess.setIdAction(actions);
                            userAccess.setIdFonctionnalite(fonctionnalites);
                            userAccess.setIdModule(menu.getIdModule());
                            userAccess.setIdProfil(profils);
                            userAccess.setIdUtilisateur(u);
                            utilisateursAccesDao.create(userAccess);
                        }
                    }
                }

            }

        }

    }

    @Override
    public void createActionByCaracterisque() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsParametrage);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsParametrage, "Parametrage"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsParametrage);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
        Collection<OrclassActions> colActions = new ArrayList<>();
        OrclassActions actions;
        OrclassFonctionnalites fonctionnalites = null;
        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null && !menuone.isEmpty()) {
            fonctionnalites = fonctionnalitesDao.getFonctionnaliteByModule(modsec5, FonctionnaliteModuleParametrage.caracteristique_assure.name());
            if (fonctionnalites.getLibelle().equals(FonctionnaliteModuleParametrage.caracteristique_assure.name())) {

                for (Actions action : Actions.values()) {
//                        colActions = new ArrayList<>();

                    try {
                        if (actionsDao.finkey(fonctionnalites, modsec5, action.toString()) == null) {
                            colActions.add(new OrclassActions(action.name(), action.name(), new Date(), fonctionnalites));

                        }
                    } catch (Exception e) {

                        System.err.println("sortie action: " + actionsDao.finkey(fonctionnalites, modsec5, action.toString()));
                        System.err.println("sortie action: " + action);
                    }

                }

                actionsDao.createAll(colActions);

            }

        }
    }

//    @Override
//    public void createObjetClassByBrancheSante() {
//        Orclass_Objet obCl = null;
//        OrclassBranches br = branchesDao.findEntityHavingValue("libelle", LibelleBranche.sante);
//        if (br == null || br.getIdBranche() == null) {
//            return;
//        }
//        for (ClasseObjet ob : ClasseObjet.values()) {
//            if (ob.equals(ClasseObjet.vehicule) || ob.equals(ClasseObjet.donnee_special)) {
//                continue;
//            }
//            obCl = orclass_ObjetDao.finKey(ob, br);
//            if (obCl == null) {
//                obCl = new Orclass_Objet();
//                obCl.setClasseObjet(ob);
//                obCl.setIdBranche(br);
//                obCl.setCode(orclass_ObjetDao.getcodeObjet());
//                orclass_ObjetDao.create(obCl);
//            }
//        }
//    }
//
//    @Override
//    public void createCaracteristiqueByObjetClassByBrancheSante() {
//        List<Orclass_Objet> colObjets = new ArrayList<>(orclass_ObjetDao.findAll());
//        OrclassCaracteristiques caracteristiques = null;
//        String libelle = "Liste à Classe";
//        OrclassType_Caracteristique tc = type_CaracteristiqueDao.findEntityHavingValue("libelle", libelle);
////        if (colObjets.isEmpty() == Boolean.FALSE) {
//        for (Orclass_Objet obj : colObjets) {
//            /*
//                les caracteristique de type police
//             */
//            switch (obj.getClasseObjet()) {
//                case police:
//                    //on enregistre en premier  la zone souscripteur
//                    for (policeSouscripteurZone ps : policeSouscripteurZone.values()) {
//                        caracteristiques = caracteristiquesDao.finKeyByObjet(ps.name(), obj);
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(ps.name().substring(0, 2));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//
//                    //enregistrement sur autre information
//                    for (PoliceAutresInformation ps : PoliceAutresInformation.values()) {
//                        caracteristiques = caracteristiquesDao.finKeyByObjet(ps.name(), obj);
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(ps.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//                    break;
//
//                case adherent:
//                    for (AdherentZone ps : AdherentZone.values()) {
//                        caracteristiques = caracteristiquesDao.finKeyByObjet(ps.name(), obj);
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(ps.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//                    break;
//
//                case assure:
//                    for (AssureZone ps : AssureZone.values()) {
//                        caracteristiques = caracteristiquesDao.finKeyByObjet(ps.name(), obj);
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            if (ps.name().length() < 3) {
//                                caracteristiques.setCode(ps.name());
//                            } else {
//                                caracteristiques.setCode(ps.name().substring(0, 3));
//                            }
//
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//                    break;
//                case caracteristique:
//                    for (CaracteristiqueZone ps : CaracteristiqueZone.values()) {
//                        caracteristiques = caracteristiquesDao.finKeyByObjet(ps.name(), obj);
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(ps.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//                    break;
//
//                case famille:
//                    for (FamilleZone ps : FamilleZone.values()) {
//                        caracteristiques = caracteristiquesDao.finKeyByObjet(ps.name(), obj);
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(ps.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//                    break;
//
//                case garanties:
//                    for (GarantiesZone ps : GarantiesZone.values()) {
//                        caracteristiques = caracteristiquesDao.finKeyByObjet(ps.name(), obj);
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(ps.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//
//                    break;
//
//                case groupe:
//                    for (GroupeZone ps : GroupeZone.values()) {
//                        caracteristiques = caracteristiquesDao.finKeyByObjet(ps.name(), obj);
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(ps.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//                    break;
//
//                case prestation:
//                    for (PrestationZone ps : PrestationZone.values()) {
//                        caracteristiques = caracteristiquesDao.finKeyByObjet(ps.name(), obj);
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(ps.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//                    break;
//
//                case quittance:
//                    for (QuittanceObjet ps : QuittanceObjet.values()) {
//                        caracteristiques = caracteristiquesDao.findEntityHavingValue("libelle", ps.name());
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(ps.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(ps.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//                    break;
//
//                case vehicule:
//                    for (VehiculeObjet v : VehiculeObjet.values()) {
//                        caracteristiques = caracteristiquesDao.findEntityHavingValue("libelle", v.name());
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(v.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(v.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//
//                    break;
//
//                case donnee_special:
//                    for (Donnee_Special d : Donnee_Special.values()) {
//                        caracteristiques = caracteristiquesDao.findEntityHavingValue("libelle", d.name());
//                        if (caracteristiques == null || caracteristiques.getId() == null) {
//                            caracteristiques = new OrclassCaracteristiques();
//                            caracteristiques.setCode(d.name().substring(0, 3));
//                            caracteristiques.setDateCreation(new Date());
//                            caracteristiques.setLibelle(d.name());
//                            caracteristiques.setType_Caracteristique(tc);
//                            caracteristiques.setShowAllCompagnies(Boolean.TRUE);
//                            caracteristiques.setSysteme(Boolean.TRUE);
//                            caracteristiques.setOrclass_Objet(obj);
//                            caracteristiquesDao.create(caracteristiques);
//
//                        }
//                    }
//
//                    break;
//            }
//        }
////        }
//    }
    @Override
    public Collection<OrclassMenus> getAllMenusByModuleAgricolte() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAgricolte);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAgricolte, "Agricolte"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAgricolte);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.m_production_agricolte, "production agricolte", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_production_agricolte_principal, "principal agricolte", false, "/agricolte/production/principal", modsec5, "" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistreagricolte, "sinistre Agricolte", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_agricolte_principal, "principal sinistre", false, "/agricolte/sinistre/principal", modsec5, "" + ModuleMenu.mds_mSinistre));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleAssuranceAuto() {

        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAssurance_Auto);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAssurance_Auto, "Assurance auto"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAssurance_Auto);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.m_automobile, "Automobile", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mAffaireNouvelle_auto, "Affaires Nouvelles ", false, "/assurance_auto/automobile/production_mono/affaire_nouvelle", modsec5, "" + ModuleMenu.m_automobile + "|" + ModuleMenu.mds_mProduction_mono));
            menuone.add(new OrclassMenus(ModuleMenu.mAvenant_auto, "Avenant ", false, "/assurance_auto/automobile/production_mono/avenant", modsec5, "" + ModuleMenu.m_automobile + "|" + ModuleMenu.mds_mProduction_mono));
//            menuone.add(new OrclassMenus(ModuleMenu.mEcheance, "Echeance ", false, "/assurance_adp/sante/production/echeance", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.mEncaissement_auto, "Encaissement ", false, "/assurance_auto/automobile/production_mono/encaissement", modsec5, "" + ModuleMenu.m_automobile + "|" + ModuleMenu.mds_mProduction_mono));
//            menuone.add(new OrclassMenus(ModuleMenu.mVersement, "Versement ", false, "/assurance_adp/sante/production/versement", modsec5, "" + ModuleMenu.mds_Sante + "|" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.mds_mSinistre_auto, "Sinistre", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.mds_mProduction_flotte, "Production Flotte", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_production_assurance_auto_principal, "principal Assurance Auto", false, "/assurance_auto/production/principal", modsec5, "" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_assurance_auto, "sinistre Assurance auto", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_assurance_auto_principal, "principal sinistre Assurance auto", false, "/assurance_auto/sinistre/principal", modsec5, "" + ModuleMenu.mds_mSinistre));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleAssuranceIrd() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAssure_IRD);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAssure_IRD, "Assurance ird"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAssure_IRD);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.m_production_assurance_ird, "production Assurance Ird", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_production_assurance_ird_principal, "principal Assurance ird", false, "/assurance_ird/production/principal", modsec5, "" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_assurance_ird, "sinistre Assurance ird", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_assurance_ird_principal, "principal sinistre Assurance ird", false, "/assurance_ird/sinistre/principal", modsec5, "" + ModuleMenu.mds_mSinistre));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;

    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleCautionCredit() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCaution_Credit);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsCaution_Credit, "Caution Credit"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCaution_Credit);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.m_production_caution_credit, "production Caution Credit", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_production_caution_credit_principal, "principal Caution Credit", false, "/caution_credit/production/principal", modsec5, "" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_caution_credit, "sinistre Caution Credit", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_caution_credit_principal, "principal sinistre caution Credit", false, "/caution_credit/sinistre/principal", modsec5, "" + ModuleMenu.mds_mSinistre));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleComptabiliteAgence() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCompAgence);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsCompAgence, "comptabilite agence"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCompAgence);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.m_production_comptabilite_agence, "production Comptabilite Agence", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_production_comptabilite_agence_principal, "principal Comptabilite agence", false, "/comptabilite_agence/production/principal", modsec5, "" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_comptabilite_agence, "sinistre Comptabilite agence", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_comptabilite_agencet_principal, "principal sinistre comptabilite agence", false, "/comptabilite_agence/sinistre/principal", modsec5, "" + ModuleMenu.mds_mSinistre));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleComptabiliteGeneral() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCompt_Gene);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsCompt_Gene, "comptabilite general"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCompt_Gene);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.m_production_comptabilite_general, "production Comptabilite General", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_production_comptabilite_general_principal, "principal Comptabilite general", false, "/comptabilite_general/production/principal", modsec5, "" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_comptabilite_general, "sinistre Comptabilite general", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_comptabilite_general_principal, "principal sinistre comptabilite general", false, "/comptabilite_general/sinistre/principal", modsec5, "" + ModuleMenu.mds_mSinistre));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleGestionAssure() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsGestion_Assure);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsGestion_Assure, "Gestion Assure"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsGestion_Assure);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.m_production_gestion_assure, "production Gestion Assure", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_production_gestion_assure_principal, "principal gestion assure", false, "/gestion_assure/production/principal", modsec5, "" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_gestion_assure, "sinistre Gestion Assure", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_gestion_assure_principal, "principal sinistre gestion assure", false, "/gestion_assure/sinistre/principal", modsec5, "" + ModuleMenu.mds_mSinistre));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleReassurance() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsReinsurance);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsReinsurance, "Gestion Reassurance"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsReinsurance);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            menuone.add(new OrclassMenus(ModuleMenu.m_production_reassurance, "production Reassurance", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_production_reassurance_principal, "principal reassurance", false, "/reassurance/production/principal", modsec5, "" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_reassurance, "sinistre reassurance", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_reassurance_principal, "principal sinistre reassurance", false, "/reassurance/sinistre/principal", modsec5, "" + ModuleMenu.mds_mSinistre));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleReporting() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsReporting);
        if (modsec5 == null) {
            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsReporting, "etats"));

            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsReporting);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();

            // menu de traitement d etat assurance adp
            menuone.add(new OrclassMenus(ModuleMenu.mdsEtats, "etats", false, "/reporting/etat", modsec5, ModuleMenu.mdsReporting));

            menuone.add(new OrclassMenus(ModuleMenu.mdsADPEtat, "Assurance adp Report", false, "#", modsec5, ModuleMenu.mdsReporting));
            menuone.add(new OrclassMenus(ModuleMenu.mds_SanteEtat, "Sante", false, "#", modsec5, "" + ModuleMenu.mdsReporting + "|" + ModuleMenu.mdsADPEtat));
            menuone.add(new OrclassMenus(ModuleMenu.mds_mProductionEtat, "Production", false, "#", modsec5, "" + ModuleMenu.mds_SanteEtat + "|" + "" + ModuleMenu.mdsReporting + "|" + ModuleMenu.mdsADPEtat));
            menuone.add(new OrclassMenus(ModuleMenu.mAffaireNouvelleEtat, "Affaires Nouvelles", false, "#", modsec5, ModuleMenu.mds_mProductionEtat + "|" + ModuleMenu.mds_SanteEtat + "|" + "" + ModuleMenu.mdsReporting + "|" + ModuleMenu.mdsADPEtat));
            menuone.add(new OrclassMenus(ModuleMenu.mContratSanteEtat, "Contrat Production", false, "/reporting/assurance_adp/sante/production/affaire_nouvelle/contrat_production", modsec5, ModuleMenu.mAffaireNouvelleEtat + "|" + ModuleMenu.mds_mProductionEtat + "|" + ModuleMenu.mds_SanteEtat + "|" + "" + ModuleMenu.mdsReporting + "|" + ModuleMenu.mdsADPEtat));

            menuone.add(new OrclassMenus(ModuleMenu.mAvenantEtat, "Avenant Reporting", false, "#", modsec5, ModuleMenu.mds_mProductionEtat + "|" + ModuleMenu.mds_SanteEtat + "|" + "" + ModuleMenu.mdsReporting + "|" + ModuleMenu.mdsADPEtat));
//          menuone.add(new OrclassMenus(ModuleMenu.mContratSanteEtat, "Contrat Production", false, "/reporting/assurance_adp/production/affaire_nouvelle/contrat_production", modsec5, ModuleMenu.mAffaireNouvelleEtat+"|"+ModuleMenu.mds_mProductionEtat + "|" + ModuleMenu.mds_SanteEtat + "|" + "" + ModuleMenu.mdsReporting + "|" + ModuleMenu.mdsADPEtat));

//            menuone.add(new OrclassMenus(ModuleMenu.mAvenant, "Avenant", false, "#", modsec5, ModuleMenu.mds_mProductionEtat + "|" + ModuleMenu.mds_SanteEtat + "|" + "" + ModuleMenu.mdsReporting + "|" + ModuleMenu.mdsADPEtat));
            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassMenus> getAllMenusByModuleTransport() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsTransport);

        if (modsec5 == null) {
            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsTransport, "Transport"));

            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsTransport);

        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassMenus> menuone = new ArrayList<>();
//        menuone = menudao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, parMap);

        if (modsec5 != null) {
            menuone = new ArrayList<>();
            menuone.add(new OrclassMenus(ModuleMenu.m_production_transport, "production Transport", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_production_transport_principal, "principal transport", false, "/transport/production/principal", modsec5, "" + ModuleMenu.mds_mProduction));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_transport, "sinistre transport", false, "#", modsec5, "#"));
            menuone.add(new OrclassMenus(ModuleMenu.m_sinistre_transport_principal, "principal sinistre transport", false, "/transport/sinistre/principal", modsec5, "" + ModuleMenu.mds_mSinistre));

            for (OrclassMenus menu : menuone) {
                if (menudao.getMenuByCode(menu.getCode()) == null) {
                    menudao.create(menu);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAgricolte() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAgricolte);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAgricolte, "agricolte"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAgricolte);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAssuranceAuto() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAssurance_Auto);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAssurance_Auto, "assurance auto"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAssurance_Auto);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleAssuranceIrd() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAssure_IRD);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsAssure_IRD, "assurance ird"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsAssure_IRD);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleCautionCredit() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCaution_Credit);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsCaution_Credit, "caution credit"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCaution_Credit);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleComptabiliteAgence() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCompAgence);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsCompAgence, "comptabilite agence"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCompAgence);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleComptabiliteGeneral() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCompt_Gene);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsCompt_Gene, "comptabilite general"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsCompt_Gene);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleGestionAssure() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsGestion_Assure);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsGestion_Assure, "Gestion Assures"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsGestion_Assure);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleReassurance() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsReinsurance);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsReinsurance, "reassurance"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsReinsurance);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleReporting() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsReporting);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsReporting, "Reporting"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsReporting);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

    @Override
    public Collection<OrclassFonctionnalites> getAllFonctionnaliteByModuleTransport() {
        OrclassModules modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsTransport);
        if (modsec5 == null) {

            OrclassModules mds = (new OrclassModules(ModuleMenu.mdsTransport, "Transport"));
            modsecdao.create(mds);
            modsec5 = modsecdao.findEntityHavingValue("code", ModuleMenu.mdsTransport);
        }
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("moduleid", modsec5.getIdModule());
        Collection<OrclassFonctionnalites> menuone = new ArrayList<>();
//        menuone = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, parMap);
        if (modsec5 != null) {

//            menuone.add(new Menu(ModuleMenu.mCogestionCommande, "gestion des commandes ", false, "/commerce/gestionComande", modsec5));
            menuone.add(new OrclassFonctionnalites(FonctionnaliteDefault.afficher.name(), FonctionnaliteDefault.afficher.name(), new Date(), modsec5));

            for (OrclassFonctionnalites f : menuone) {
                if (fonctionnalitesDao.getFonctionnaliteByModule(modsec5, f.getLibelle()) == null) {
                    fonctionnalitesDao.create(f);
                }
            }

        }
        return menuone;
    }

//    @Override
//    public void creatCodePrincipale() {
//        // charger les code principal des branche
//        OrclassBranches b = null;
//        for (OrclassBranches br : branchesDao.allBranchesHaveCodePrincipalNull()) {
//            try {
//                b = br;
//                b.setCodePrincipal(new BigInteger(br.getCode().trim()));
//                branchesDao.update(b);
//            } catch (NumberFormatException n) {
//
//            }
//
//        }
//        OrclassCategories cat = null;
//        for (OrclassCategories c : categoriesDao.allCategoriesHaveCodePrincipalNull()) {
//            try {
//                cat = c;
//                System.out.println("code categorie " + c.getCode());
//                cat.setCodePrincipal(new BigInteger("" + c.getCode().trim()));
//                categoriesDao.update(cat);
//
//            } catch (NumberFormatException n) {
//
//            }
//
//        }
//        OrclassApporteur app = null;
//        for (OrclassApporteur p : apporteurDao.allApporteurHaveCodePrincipalNull()) {
//            try {
//                app = p;
//                app.setCodePrincipal(new BigInteger(p.getIdRefApporteur().getCode().trim()));
//                apporteurDao.update(app);
//            } catch (NumberFormatException n) {
//
//            }
//
//        }
//
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public Societe createCompagnieUtilisateurSysteme() {
        Societe compagnie = null;
        if (societeDao.findEntityHavingValue("codesoci", Short.valueOf("-1")) == null) {
            compagnie = new Societe();
            compagnie.setCodesoci(Short.valueOf("-1"));
            compagnie.setRaissoci("ESPHERE COMPAGNY");
            compagnie.setDgcontact("Utilisateur systeme");
            compagnie.setDateCreation(new Date());
            compagnie.setAbresoci("00000000");
            compagnie.setVillsoci(villleDao.getSocieteByCode(2001));

            compagnie.setMonnsoci(asvCodeDeviseDao.getDeviByCode("FCFA"));

            societeDao.create(compagnie);
            //lister tous les utilisateur Systeme enfin de les attribuer cette compagnie
            for (OrclassUtilisateurs user : userDao.findAllEntitiesHavingValue("typeUtilisateur", TypeUtilisateur.UTILISATEUR_SYSTEM)) {
                user.setIdSociete(compagnie);
                userDao.update(user);
            }
        }
        return compagnie;
    }

//    @Override
//    public Collection<SeverMessagerie> getSeverMessagerie() {
//        Collection<SeverMessagerie> colR = new ArrayList<>();
//        if (severMessagerieDao.findAll().isEmpty()) {
//            SeverMessagerie r;
//            r = new SeverMessagerie("smtp.gmail.com", "465", "messagerie gmail.com");
//            colR.add(r);
//            r = new SeverMessagerie("smtp.office365.com", "", "office360");
//            colR.add(r);
//            r = new SeverMessagerie("smtp.mail.yahoo.com", "465", "messagerie yahoo.com/yahoo.fr");
//            colR.add(r);
//
//            severMessagerieDao.createAll(colR);
//        }
//        return colR;
//
//    }
//
//    @Override
//    public void createPeriodicite() {
//        OrclassFractionnement f = null;
//        Collection<OrclassFractionnement> colFraction = new ArrayList<>(fractionnementDao.findAll());
//        if (colFraction.isEmpty()) {
//            f = new OrclassFractionnement("1", "Une seul fois", 1);
//            colFraction.add(f);
//            f = new OrclassFractionnement("2", "Deux fois", 2);
//            colFraction.add(f);
//            f = new OrclassFractionnement("3", "Trois fois", 3);
//            colFraction.add(f);
//            f = new OrclassFractionnement("4", "Quatre fois", 4);
//            colFraction.add(f);
//            f = new OrclassFractionnement("5", "Cinq fois", 5);
//            colFraction.add(f);
//            f = new OrclassFractionnement("6", "Six fois", 6);
//            colFraction.add(f);
//            fractionnementDao.createAll(colFraction);
//
//        }
//    }
//
//    @Override
//    public void createObjetClassByBrancheAutomobile() {
//        Orclass_Objet obCl = null;
//        OrclassBranches br = branchesDao.findEntityHavingValue("libelle", LibelleBranche.automobile);
//        if (br == null || br.getIdBranche() == null) {
//            return;
//        }
//        for (ClasseObjet ob : ClasseObjet.values()) {
////            if (ob.equals(ClasseObjet.vehicule) || ob.equals(ClasseObjet.donnee_special)) {
////                continue;
////            }
//            obCl = orclass_ObjetDao.finKey(ob, br);
//            if (obCl == null) {
//                obCl = new Orclass_Objet();
//                obCl.setClasseObjet(ob);
//                obCl.setIdBranche(br);
//                obCl.setCode(orclass_ObjetDao.getcodeObjet());
//                orclass_ObjetDao.create(obCl);
//            }
//        }
//    }
//
//    @Override
//    public void createNatureDocument() {
//        OrclassNatureDocument nd = null;
//        Collection<OrclassNatureDocument> colNatureDocument = new ArrayList<>(natureDocumentDao.findAll());
//        if (colNatureDocument.isEmpty()) {
//            nd = new OrclassNatureDocument("P3", "POOL 3 MOIS");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("P4", "POOL 4 MOIS");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("P6", "POOL 6 MOIS");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("PX", "POOL 12 MOIS");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("B", "BLEU");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("J", "JAUNE");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("P", "POOL");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("S", "STANDART");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("R", "ROSE");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("V", "VERT");
//            colNatureDocument.add(nd);
//            nd = new OrclassNatureDocument("M", "MARRON");
//            colNatureDocument.add(nd);
//            for (OrclassNatureDocument ndcs : colNatureDocument) {
//                if (natureDocumentDao.findEntityHavingValue("code", ndcs.getCode()) == null) {
//                    natureDocumentDao.create(ndcs);
//                }
//            }
//
//        }
//
//    }
//
//    @Override
//    public void createOperationStock() {
//        OrclassOperationStock op = null;
//        Collection<OrclassOperationStock> colOperationStock = new ArrayList<>(operationStockDao.findAll());
//        if (colOperationStock.isEmpty()) {
//            op = new OrclassOperationStock("RC", TypeOperation.alimentation_stock_siege);
//            colOperationStock.add(op);
//            op = new OrclassOperationStock("AN", TypeOperation.annulation_document_siege);
//            colOperationStock.add(op);
//            op = new OrclassOperationStock("AA", TypeOperation.correction_annulation_document);
//            colOperationStock.add(op);
//            op = new OrclassOperationStock("RT", TypeOperation.retour_au_stock);
//            colOperationStock.add(op);
//            op = new OrclassOperationStock("RA", TypeOperation.retour_document_annuller);
//            colOperationStock.add(op);
//            op = new OrclassOperationStock("TR", TypeOperation.transfert_doc_entre_entite);
//            op = new OrclassOperationStock("AF", TypeOperation.affectation);
//            colOperationStock.add(op);
//            for (OrclassOperationStock ops : colOperationStock) {
//                if (operationStockDao.findEntityHavingValue("code", ops.getCode()) == null) {
//                    operationStockDao.create(ops);
//                }
//            }
//
//        }
//    }
//
//    @Override
//    public void createUsageAuto() {
//        Collection<UsageAuto> colUsageAuto = new ArrayList<>(usageAutoDao.findAll());
//        UsageAuto usageAuto;
//        if (colUsageAuto.isEmpty()) {
//            usageAuto = new UsageAuto("PREA", "PROMENADES ET AFFAIRES");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("TPDM", "TRANSPORT PRIVE DE MARCHANDISES");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("TPDVT", "TRANSPORT PUBLIC DES VOYAGEURS PAR TAXIS");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("TDPE", "TRANSPORT DU PERSONNEL ET DES ELEVES");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("AE", "AUTO-ECOLE");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("LDV", "LOCATIONS DES VEHICULES");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("EPEM", "ENGIN PORTUAIRE ET DE MANUTENTION");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("EM", "ENGINS MOBILES");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("FG", "FOURGONS");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("FGF", "FOURGONS FUNEBRES");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("VA23R", "VEHICULES A 2 OU 3 ROUES");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("PICKUPC", "PICK UP (CAMIONNETTE)");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("POOL", "POOL");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("VEHU", "VEHICULE UTILITAIRE");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("VEHU2R", "VEHICULE 2 ROUES");
//            colUsageAuto.add(usageAuto);
//            usageAuto = new UsageAuto("VEHU3R", "VEHICULE 3 ROUES");
//            colUsageAuto.add(usageAuto);
//            usageAutoDao.createAll(colUsageAuto);
//
//        } else {
//            usageAuto = new UsageAuto("BPDP", "BESOINS PROFESSIONNELS ET DEPLACEMENT PRIVES");
//            if (usageAutoDao.findEntityHavingValue("libelle", usageAuto.getLibelle()) == null) {
//                usageAutoDao.create(usageAuto);
//
//            }
//        }
//    }
//
//    @Override
//    public void creatGenreAuto() {
//        Collection<GenreAuto> colGenreAuto = new ArrayList<>(genreAutoDao.findAll());
//        GenreAuto genreAuto;
//        if (colGenreAuto.isEmpty()) {
//            genreAuto = new GenreAuto("VT", "VEHICULE DE TOURISME");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("RMTNA", "REMORQUE (TRACTEUR NON ASSURE)");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("VEU", "VEHICULE UTILITAIRE");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("SREM", "SEMI-REMORQUE");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("CM", "CAMION");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("TRACTEUR", "TRACTEUR");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("REMORQUE", "REMORQUE");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("CAMION", "CAMION");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("CAMCI", "CAMION CITERNE");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("TAXIS", "TAXIS");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("CARS", "CARS");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("BUS", "BUS");
//            colGenreAuto.add(genreAuto);
//
//            genreAuto = new GenreAuto("MB", "MINIBUS");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("CT", "CATERPILLAR");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("ENGINS", "ENGINS");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("COB", "COBILLARDS");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("AMB", "AMBULANCES");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("CAMBE", "CAMIONS BENNES");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("MOTCY", "MOTOCYCLETTE");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("POOL", "POOL");
//            colGenreAuto.add(genreAuto);
//            genreAuto = new GenreAuto("FB", "FOURGONS");
//            colGenreAuto.add(genreAuto);
//            genreAutoDao.createAll(colGenreAuto);
//
//        }
//    }
}
