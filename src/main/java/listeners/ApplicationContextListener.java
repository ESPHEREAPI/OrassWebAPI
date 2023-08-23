package listeners;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import parametrage.IInitializeDB;

/**
 *
 * @author fabrice
 */
@WebListener
public class ApplicationContextListener implements ServletContextListener {

    static final Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);

    @EJB
    private IInitializeDB initDB;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        initDB.getAllModuleSecurite();

//        initDB.getAllPays();
//        initDB.getAllReligions();
        initDB.getAllTitres();
        initDB.addIndicatifPays();

        initDB.getAllMenusByModuleAdministration();
        initDB.getAllMenusByModuleADP();
        initDB.getAllMenusByModuleParamettrage();

        initDB.getAllMenusByModuleAgricolte();

        initDB.getAllMenusByModuleAssuranceAuto();

        initDB.getAllMenusByModuleAssuranceIrd();

        initDB.getAllMenusByModuleCautionCredit();

        initDB.getAllMenusByModuleComptabiliteAgence();

        initDB.getAllMenusByModuleComptabiliteGeneral();

        initDB.getAllMenusByModuleGestionAssure();

        initDB.getAllMenusByModuleReassurance();

        initDB.getAllMenusByModuleReporting();

        initDB.getAllMenusByModuleTransport();

        initDB.getAllFonctionnaliteByModuleParametrage();
        initDB.getAllFonctionnaliteByModuleAssuranceAdp();
        initDB.getAllFonctionnaliteByModuleAdministration();
//
        initDB.getAllFonctionnaliteByModuleAgricolte();

        initDB.getAllFonctionnaliteByModuleAssuranceAuto();

        initDB.getAllFonctionnaliteByModuleAssuranceIrd();

        initDB.getAllFonctionnaliteByModuleCautionCredit();

        initDB.getAllFonctionnaliteByModuleComptabiliteAgence();

        initDB.getAllFonctionnaliteByModuleComptabiliteGeneral();

        initDB.getAllFonctionnaliteByModuleGestionAssure();

        initDB.getAllFonctionnaliteByModuleReassurance();

        initDB.getAllFonctionnaliteByModuleReporting();

        initDB.getAllFonctionnaliteByModuleTransport();

        initDB.getActionByFonctionnaliteByModuleAdministration();
        initDB.getActionByFonctionnaliteByModuleParamettrage();
        initDB.getActionByFonctionnaliteByAdp();
        initDB.getAllMenuByFonctionnaliteByActionByModuleAdmininstration();
        initDB.getAllMenuByFonctionnaliteByActionByModuleParametrage();
        initDB.getAllMenuByFonctionnaliteByActionByModuleAdp();
        initDB.getAllFonctionnaliteByModuleReporting();
        initDB.getAdmin();
        initDB.createCompagnieUtilisateurSysteme();
        initDB.creteProfilAdmin();
        initDB.createModuleByProfileAdmin();
        initDB.addProfileAdmin();
//        initDB.addVille();
//        initDB.addTypeBureau();
//        initDB.addTypeApporteur();
//        initDB.addClasse();
//        initDB.addBranches();
//        initDB.addCategorie();
//        initDB.addTypeReference();
//        initDB.addUniteCaracteristique();
//        initDB.addTypeCaracteristique();
//        initDB.addTypeTarif();
//        initDB.addTypeQualite();
//        initDB.addTypeTimbreDimension();
//        initDB.addDevise();
//        initDB.addExoneration();
//        initDB.addReduction();
//        initDB.addConvention();
        initDB.createActionByCaracterisque();
        initDB.addMenuAccessForAdmin();
//        initDB.createObjetClassByBrancheSante();
//        initDB.createCaracteristiqueByObjetClassByBrancheSante();
//        initDB.creatCodePrincipale();
//        initDB.getSeverMessagerie();
//        initDB.createPeriodicite();
//        initDB.createObjetClassByBrancheAutomobile();
//        initDB.createNatureDocument();
//        initDB.createOperationStock();
//        initDB.creatGenreAuto();
//        initDB.createUsageAuto();
     
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Destruction");
    }
}
