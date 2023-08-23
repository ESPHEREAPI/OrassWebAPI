///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.adp;
//
//import Excell.IExcell;
//import dao.IndicatifPaysDao;
//import dao.OrclassActiviteDao;
//import dao.OrclassApporteurDao;
//import dao.OrclassAssureDao;
//import dao.OrclassAvenantDao;
//import dao.OrclassBranchesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassCommission_Prime_ApporteurDao;
//import dao.OrclassConventionDao;
//import dao.OrclassDetailPolicePlafondDao;
//import dao.OrclassDeviseDao;
//import dao.OrclassElement_Liste_CaracteristiqueDao;
//import dao.OrclassElt_Categorie_CompagnieDao;
//import dao.OrclassExonerationDao;
//import dao.OrclassFractionnementCategoriesDao;
//import dao.OrclassGroupePoliceDao;
//import dao.OrclassImageFamilleRisqueDao;
//import dao.OrclassImageRisqueDao;
//import dao.OrclassIntermediairesDao;
//import dao.OrclassMajorationDureeDao;
//import dao.OrclassPlafondMaladieDao;
//import dao.OrclassPoliceCaracteristiqueDao;
//import dao.OrclassPoliceDao;
//import dao.OrclassPoliceGarantieDao;
//import dao.OrclassPolicePlafondDao;
//import dao.OrclassPrestationDao;
//import dao.OrclassProfessionDao;
//import dao.OrclassPropositionDao;
//import dao.OrclassQualiteDao;
//import dao.OrclassQuitanceDao;
//import dao.OrclassReductionDao;
//import dao.OrclassRegistreProductionDao;
//import dao.OrclassRisqueDao;
//import dao.OrclassRisqueFamilleDao;
//import dao.OrclassRubriqueCaracteristiqueDao;
//import dao.OrclassRubriqueCategorieDao;
//import dao.OrclassRubriqueGarantieDao;
//import dao.OrclassRubriquePrestationDao;
//import dao.OrclassSuspensionBrancheIntemediaireDao;
//import dao.OrclassSuspensionCategorieIntermediaireDao;
//import dao.OrclassTimbreDimensionDao;
//import dao.OrclassTypeTarifDao;
//import dao.OrclassVilleDao;
//import dao.Orclass_AccessAvenat_CaracteristiqueDao;
//import dao.Orclass_Access_AvenantDao;
//import dao.Orclass_ObjetDao;
//import dao.Orclass_TypeAvenantDao;
//import dao.OrclasseRefGroupeDao;
//import dao.PaysDao;
//import enums.AdherentZone;
//import enums.AssureZone;
//import enums.CaracteristiqueZone;
//import enums.Contrat;
//import enums.FamilleZone;
//import enums.GarantiesZone;
//import enums.GroupeZone;
//import enums.LibelleBranche;
//import enums.LibelleRisque;
//import enums.LienParente;
//import enums.ModeCalcul;
//import enums.ModeCalculDetailMaladie;
//import enums.NatureAvenant;
//import enums.NatureContrat;
//import enums.PoliceAutresInformation;
//import enums.PrestationZone;
//import enums.QuittanceObjet;
//import enums.Sexe;
//import enums.StatutCaracteristique;
//import enums.TypeDetailMaladie;
//import enums.TypePieces;
//import enums.TypeQuittance;
//import enums.policeSouscripteurZone;
//import exception.GlobalException;
//import exception.Success;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Objects;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.faces.model.SelectItem;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.swing.ImageIcon;
//import modele.OrclassActivite;
//import modele.OrclassApporteur;
//import modele.OrclassAssure;
//import modele.OrclassAvenant;
//import modele.OrclassBranches;
//import modele.OrclassCaracteristiques;
//import modele.OrclassCategories;
//import modele.OrclassCommission_Prime_Apporteur;
//import modele.OrclassConvention;
//import modele.OrclassDetailPolicePlafond;
//import modele.OrclassDevise;
//import modele.OrclassDuree;
//import modele.OrclassElement_Liste_Caracteristique;
//import modele.OrclassElt_Categorie_Compagnie;
//import modele.OrclassEntreprises;
//import modele.OrclassExoneration;
//import modele.OrclassFractionnement;
//import modele.OrclassFractionnementCategories;
//import modele.OrclassGarantie;
//import modele.OrclassGroupePolice;
//import modele.OrclassImageFamilleRisque;
//import modele.OrclassImageRisque;
//import modele.OrclassIntermediaires;
//import modele.OrclassMajorationDuree;
//import modele.OrclassPlafondMaladie;
//import modele.OrclassPolice;
//import modele.OrclassPoliceCaracteristique;
//import modele.OrclassPoliceCommissionApporteur;
//import modele.OrclassPoliceGarantie;
//import modele.OrclassPolicePlafond;
//import modele.OrclassPrestation;
//import modele.OrclassProfession;
//import modele.OrclassProposition;
//import modele.OrclassQualite;
//import modele.OrclassQuitance;
//import modele.OrclassReduction;
//import modele.OrclassRegistreProduction;
//import modele.OrclassRisque;
//import modele.OrclassRisqueFamille;
//import modele.OrclassRubriqueCaracteristique;
//import modele.OrclassRubriqueCategorie;
//import modele.OrclassRubriqueGarantie;
//import modele.OrclassRubriquePrestation;
//import modele.OrclassSuspensionBrancheIntemediaire;
//import modele.OrclassSuspensionCategorieIntermediaire;
//import modele.OrclassTypeTarif;
//import modele.OrclassUtilisateurs;
//import modele.OrclassVille;
//import modele.Orclass_AccessAvenat_Caracteristique;
//import modele.Orclass_Access_Avenant;
//import modele.Orclass_Objet;
//import modele.Orclass_TypeAvenant;
//import modele.OrclasseRefGroupe;
//import modele.OrclasseTimbreDimension;
//import modele.Pays;
//import net.sf.jasperreports.engine.JRException;
//import org.hibernate.exception.ConstraintViolationException;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.FileUploadEvent;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.event.TabChangeEvent;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sante.Contrat_Sante;
//import sante.ISante;
//import sante.avenantSante;
//import utils.GlobalFonctions;
//import utils.IdleDate;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author E-SPHERE
// */
//@Named(value = "avenantController")
//@ViewScoped
//public class AvenantCongtroller implements Serializable {
//    
//    private static final Logger logger = LoggerFactory.getLogger(AvenantCongtroller.class);
//    @EJB
//    OrclassRegistreProductionDao productionDao;
//    OrclassRegistreProduction production;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//    
//    @EJB
//    OrclassActiviteDao activiteDao;
//    OrclassActivite activite;
//    @EJB
//    OrclassQualiteDao qualiteDao;
//    OrclassQualite qualite;
//    @EJB
//    PaysDao paysDao;
//    Pays pays;
//    @EJB
//    OrclassProfessionDao professionDao;
//    OrclassProfession profession;
//    @EJB
//    IExcell serviceExcell;
//    @EJB
//    OrclassBranchesDao branchesDao;
//    OrclassBranches branches;
//    @EJB
//    OrclassSuspensionBrancheIntemediaireDao suspensionBrancheIntemediaireDao;
//    OrclassSuspensionBrancheIntemediaire suspensionBrancheIntemediaire;
//    @EJB
//    OrclassFractionnementCategoriesDao fractionnementCategoriesDao;
//    OrclassFractionnementCategories fractionnementCategories;
//    OrclassFractionnement fractionnement;
//    @EJB
//    OrclassSuspensionCategorieIntermediaireDao suspensionCategorieIntermediaireDao;
//    OrclassSuspensionCategorieIntermediaire suspensionCategorieIntermediaire;
//    @EJB
//    OrclassIntermediairesDao intermediairesDao;
//    OrclassIntermediaires intermediaires;
//    @EJB
//    OrclassPoliceDao policeDao;
//    @EJB
//    OrclassElt_Categorie_CompagnieDao elt_Categorie_CompagnieDao;
//    OrclassElt_Categorie_Compagnie elt_Categorie_Compagnie;
//    @EJB
//    OrclassVilleDao villeDao;
//    @EJB
//    IndicatifPaysDao indicatifPaysDao;
//    @EJB
//    OrclassMajorationDureeDao majorationDureeDao;
//    OrclassMajorationDuree majorationDuree;
//    OrclassDuree duree;
//    @EJB
//    OrclassTypeTarifDao typeTarifDao;
//    @EJB
//    OrclassReductionDao reductionDao;
//    @EJB
//    OrclassCommission_Prime_ApporteurDao commission_Prime_ApporteurDao;
//    OrclassCommission_Prime_Apporteur commission_Prime_Apporteur;
//    @EJB
//    OrclassDeviseDao deviseDao;
//    @EJB
//    OrclassConventionDao conventionDao;
//    @EJB
//    OrclassTimbreDimensionDao timbreDimensionDao;
//    @EJB
//    OrclassExonerationDao exonerationDao;
//    @EJB
//    OrclassApporteurDao apporteurDao;
//    @EJB
//    OrclassPlafondMaladieDao plafondMaladieDao;
//    OrclassPlafondMaladie plafondMaladie;
//    @EJB
//    OrclassRubriqueCategorieDao rubriqueCategorieDao;
//    OrclassRubriqueCategorie rubriqueCategorie;
//    @EJB
//    OrclassRubriquePrestationDao rubriquePrestationDao;
//    @EJB
//    OrclassDetailPolicePlafondDao detailPolicePlafondDao;
//    OrclassDetailPolicePlafond detailPolicePlafond;
//    @EJB
//    OrclassPrestationDao prestationDao;
//    @EJB
//    OrclassRubriqueCaracteristiqueDao rubriqueCaracteristiqueDao;
//    OrclassRubriqueCaracteristique rubriqueCaracteristique;
//    @EJB
//    OrclassElement_Liste_CaracteristiqueDao element_Liste_CaracteristiqueDao;
//    @EJB
//    OrclassRubriqueGarantieDao rubriqueGarantieDao;
//    OrclassRubriqueGarantie rubriqueGarantie;
//    @EJB
//    OrclassPoliceGarantieDao policeGarantieDao;
//    OrclassPoliceGarantie policeGarantie;
//    OrclassQuitance quitance;
//    @EJB
//    ISante santeService;
//    @EJB
//    OrclassQuitanceDao quitanceDao;
//    @EJB
//    OrclassRisqueFamilleDao risqueFamilleDao;
//    @EJB
//    OrclassPoliceCaracteristiqueDao policeCaracteristiqueDao;
//    @EJB
//    OrclassPolicePlafondDao policePlafondDao;
//    @EJB
//    OrclassRisqueDao risqueDao;
//    @EJB
//    OrclassAssureDao assureDao;
//    OrclassAssure assureCheck;
//    OrclassApporteur apporteur;
//    OrclassIntermediaires intermediairesSelect;
//    @EJB
//    OrclasseRefGroupeDao refGroupeDao;
//    OrclasseRefGroupe refGroupe;
//    OrclasseRefGroupe refGroupeSelect;
//    @EJB
//    OrclassImageFamilleRisqueDao imageFamilleRisqueDao;
//    OrclassImageFamilleRisque imageFamilleRisque;
//    @EJB
//    OrclassImageRisqueDao imageRisqueDao;
//    OrclassImageRisque imageRisque;
//    OrclassImageRisque imageRisqueSelecte;
//    private FileUploadEvent file;
//    @EJB
//    OrclassPropositionDao propositionDao;
//    @EJB
//    Orclass_TypeAvenantDao typeAvenantDao;
//    Orclass_TypeAvenant typeAvenant;
//    @EJB
//    OrclassAvenantDao avenantDao;
//    OrclassAvenant avenant;
//    @EJB
//    Orclass_AccessAvenat_CaracteristiqueDao accessAvenat_CaracteristiqueDao;
//    Orclass_AccessAvenat_Caracteristique accessAvenat_Caracteristique;
//    @EJB
//    Orclass_Access_AvenantDao access_AvenantDao;
//    Orclass_Access_Avenant access_Avenant;
//    @EJB
//    Orclass_ObjetDao objetDao;
//    Orclass_Objet orclass_Objet;
//    OrclassPolicePlafond policePlafond;
//    BigDecimal tva = BigDecimal.valueOf(19.25);
//    @EJB
//    OrclassGroupePoliceDao groupePoliceDao;
//    /**
//     * Creates a new instance of ProductionController
//     */
//    private String currentFolder = "/photos";
//    private String activitExcell = currentFolder + "/activite.xls";
//    private String qualiteExcell = currentFolder + "/qualite.xls";
//    private String paysExcell = currentFolder + "/activite.xls";
//    private String prof1Excell = currentFolder + "/profession1.xls";
//    private String prof2Excell = currentFolder + "/profession2.xls";
//    private String pathFormat = currentFolder + "/formatGroupe.xls";
//    
//    private List<OrclassCategories> lisCategoriesBySante = new ArrayList<>();
//    private List<OrclassRisque> listeRisque = new ArrayList<>();
//    private List<OrclasseRefGroupe> listeRefGroup = new ArrayList<>();
//    private List<OrclassCategories> listSuspensionCategorieIntermediaire = new ArrayList<>();
//    private List<OrclassActivite> listActivite = new ArrayList<>();
//    private List<OrclassQualite> listQualite = new ArrayList<>();
//    private List<Pays> listPays = new ArrayList<>();
//    private List<OrclassProfession> listProfessionnel = new ArrayList<>();
//    private List<OrclassRisque> listRisqueDetailWithGroupe = new ArrayList<>();
//    private List<OrclassRisque> listRisqueDetailWithGroupe2 = new ArrayList<>();
//    private List<OrclassRisque> listRisqueControle = new ArrayList<>();
//    private List<OrclassPolice> listePolice = new ArrayList<>();
//    private List<OrclassVille> listeVille = new ArrayList<>();
//    private List<OrclassMajorationDuree> listeMajorationDurees = new ArrayList<>();
//    private List<OrclassDuree> listeDurees = new ArrayList<>();
//    private List<OrclassReduction> listeReduction = new ArrayList<>();
//    private List<OrclassCommission_Prime_Apporteur> listeCommission_Prime_Apporteur = new ArrayList<>();
//    private List<OrclassTypeTarif> listeTarif = new ArrayList<>();
//    private List<OrclassFractionnementCategories> listeFractionnementCategories = new ArrayList<>();
//    private List<OrclassFractionnement> listeFractionnement = new ArrayList<>();
//    private List<OrclassConvention> listeConvention = new ArrayList<>();
//    private List<OrclassDevise> listeDevise = new ArrayList<>();
//    private List<OrclassExoneration> listeExoneration = new ArrayList<>();
//    private List<OrclasseTimbreDimension> listeTimbreDimension = new ArrayList<>();
//    private List<OrclassApporteur> listeApporteur = new ArrayList<>();
//    private List<OrclassRisqueFamille> listeRisqueFamille = new ArrayList<>();
//    private List<OrclassRisqueFamille> colRisqueFamille = new ArrayList<>();
//    private List<OrclassPlafondMaladie> listePlafondMaladie = new ArrayList<>();
//    private List<OrclassRubriqueCategorie> listeRubriqueCategorie = new ArrayList<>();
//    private List<OrclassRubriquePrestation> listeRubriquePrestation = new ArrayList<>();
//    private List<OrclassDetailPolicePlafond> listeDetailPolicePlafond = new ArrayList<>();
//    private List<OrclassDetailPolicePlafond> filterDetailPolicePlafond;
//    private List<OrclassCaracteristiques> listeRubriqueCaracteristique = new ArrayList<>();
//    private List<OrclassPoliceCaracteristique> listePoliceCaracteristique = new ArrayList<>();
//    private List<OrclassCaracteristiques> filterRubriqueCaracteristique;
//    private List<OrclassPoliceCaracteristique> filterPoliceCaracteristique;
//    private List<OrclassPrestation> filterPrestation;
//    private List<OrclassElement_Liste_Caracteristique> listeElement_Liste_Caracteristique = new ArrayList<>();
//    private List<OrclassGarantie> listeRubriqueGarantie = new ArrayList<>();
//    private List<OrclassPoliceGarantie> listePoliceGarantie = new ArrayList<>();
//    private List<OrclassGarantie> filterRubriqueGarantie;
//    private List<OrclassPoliceGarantie> filterPoliceGarantie;
//    private OrclassRisqueFamille risqueFamille;
//    private List<OrclassPrestation> selectePrestations = new ArrayList<>();
//    private List<OrclassPrestation> listePrestation = new ArrayList<>();
//    private List<OrclassPoliceCommissionApporteur> listePoliceCommissionApporteur = new ArrayList<>();
//    private List<OrclassPolice> listeOrclassContrats = new ArrayList<>();
//    private List<Orclass_TypeAvenant> listeTypeAvenant = new ArrayList<>();
//    
//    List<Contrat_Sante> colContrat_Sante = new ArrayList<>();
//    List<avenantSante> colAvenantSante = new ArrayList<>();
//    private List<OrclassIntermediaires> listeIntermediaires = new ArrayList<>();
//    private List<OrclassImageRisque> listeImageRisques = new ArrayList<>();
//    private List<OrclassImageFamilleRisque> listeImageFamilleRisque = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//    private OrclassUtilisateurs user;
//    private Boolean suspensionBrance = Boolean.FALSE;
//    private OrclassPolice police;
//    private OrclassPolice policeSelect;
//    private BigInteger numero_police;
//    private String summary = "", msgdetail = "";
//    private OrclassPoliceCommissionApporteur policeCommissionApporteur;
//    private OrclasseRefGroupe lastGroupSelecte = null;
//    
//    private OrclassDevise devise = new OrclassDevise();
//    private OrclassRisque risque;
//    private String designationPlafondMaladie;
//    OrclassPoliceCaracteristique policeCaracteristique;
//    private BigDecimal totalProrata = BigDecimal.ZERO;
//    private BigDecimal totalPrime = BigDecimal.ZERO;
//    private OrclassCommission_Prime_Apporteur prime_Apporteur;
//    Boolean print, validation = Boolean.FALSE;
//    
//    private StreamedContent updateFormatFile;
//    private OrclasseRefGroupe refGroupeSelectForAdherent;
//    private OrclassImageFamilleRisque imageFamilleRisqueSelecte;
//    private Boolean modecalculForcerManuel = Boolean.FALSE;
//    private BigInteger numeroAvenant = BigInteger.ZERO;
//    private BigDecimal ancienMontantAccessoire = BigDecimal.ZERO;
////        private OrclasseRefGroupe refGroupeSelectForAdherent;
//    private OrclasseRefGroupe refGroupeSelectForPrestation;
//    private OrclasseRefGroupe refGroupeSelectForCaracteristiqueAndGarantie;
//    private OrclasseRefGroupe refGroupeSelectForGarantie;
//    private List<OrclassAvenant> listeAvenantByPolice = new ArrayList<>();
//    private OrclassAvenant avenantConsultation;
//    OrclasseRefGroupe oldGroup = null;
//
//    /*Orclass
//    valeur pour afficher les tabView 
//     */
//    private Boolean tabShowGroup = Boolean.FALSE, tabShowFamille = Boolean.FALSE, tabShowIndividuel = Boolean.FALSE;
//    private OrclassAssure assure;
//    /*
//    creation des variables de controle pour les objets de caracteristique
//     */
//    // objet police souscripteur
//    Boolean _PSqualite, _PSraison, _PSactivite, _PSdate_creation, _PSbp, _PSnumero_pantent, _PSville, _PStelephone, _PSemail, _PSdate_effet, _PSdate_echeance, _PSnom,
//            _PSprenom, _PSsexe, _PSlieu_naissance, _PSprofession, _PStype_pieces, _PSdate_naissance, _PSnumero_piece = Boolean.FALSE;
//    Boolean _PSsupprimer, _PSAjouter, _PSmodifier = Boolean.FALSE;
//    //objet police Autreinformation
//    Boolean _PAUcontrat, _PAUnature, _PAUduree, _PAUvaleur_duree, _PAUfractionnement, _PAUtype_tarif, _PAUreduction, _PAUapporteur, _PAUdevise, _PAUconvention, _PAUreference,
//            _PAUtimbre_dimension, _PAUexoneration, _PAUaccessoires, _PAunumero_bordereau = Boolean.FALSE;
//    Boolean _PAUsupprimer, _PAUAjouter, _PAUmodifier = Boolean.FALSE;
//    // objet adherent zone
//    Boolean _ADmembre, _ADlien_parente, _ADdate_naissance, _ADsexe, _ADMatricule, _ADgroup = Boolean.FALSE;
//    Boolean _ADsupprimer, _ADAjouter, _ADmodifier = Boolean.FALSE;
//    // objet Assure Zone
//    Boolean _ASSnom, _ASSdate_naissance, _ASSlieu_naissance, _ASSsexe, _ASSemail, _ASSbp, _ASStelephone, _ASSville, _ASSprofession, _ASSactivite, _ASStype_pieces, _ASSnumero_piece, _ASSdateEntre = Boolean.FALSE;
//    Boolean _ASSsupprimer, _ASSAjouter, _ASSmodifier = Boolean.FALSE;
//    //objet caracteristique
//    Boolean _CARdesignation = Boolean.FALSE;
//    Boolean _CARsupprimer, _CARAjouter, _CARmodifier = Boolean.FALSE;
//    //objet famille zone
//    Boolean _FAMmembre, _FAMlien_parente, _FAMdate_naissance, _FAMsexe, _FAMdateEntre = Boolean.FALSE;
//    Boolean _FAMsupprimer, _FAMAjouter, _FAMmodifier = Boolean.FALSE;
//    //objet Garantie Zone
//    Boolean _GARgarantie_capital, _GARgarantie_taux, _GARgarantie_pourcentage, _GARgarantie_prime, _GARgarantie_proarata = Boolean.FALSE;
//    Boolean _GARsupprimer, _GARAjouter, _GARmodifier = Boolean.FALSE;
//    //Objet Group
//    Boolean _GROmatricule, _GROnom, _GROdate_naissance, _GROsexe = Boolean.TRUE;
//    Boolean _GROsupprimer, _GROAjouter, _GROmodifier = Boolean.FALSE;
//    // objet prestation Zone
//    Boolean _PREplafond_maladie, _PREdesignation, _PREprestation_taux, _PREprestation_forfait, _PREprestation_type, _PREprestation_plafon, _PREprestation_bareme, _PREprestation_designation, _PREprestation_modecal = Boolean.FALSE;
//    Boolean _PREsupprimer, _PREAjouter, _PREmodifier = Boolean.FALSE;
//    // Objet Quittance
//    Boolean _QUItype_quittance, _QUITva, _QUIdate_emission = Boolean.FALSE;
//    Boolean _QUIsupprimer, _QUIAjouter, _QUImodifier = Boolean.FALSE;
//    List arrays[] = new List[5];
//    List arrays_Tab2[][];
//    int compteur_tab2 = 0;
//
//    /*
//    possi
//     */
//    public void chargePolicePalafond() {
//        if (plafondMaladie != null && plafondMaladie.getId() != null && typeAvenant != null && typeAvenant.getId() != null && refGroupeSelect != null && refGroupeSelect.getId() != null) {
////            policePlafond = policePlafondDao.chargePolicePlafonMaladie(policeSelect, entreprise);
////            if (policePlafond == null) {
//            policePlafond = new OrclassPolicePlafond();
//            
//            policePlafond.setIdGroup((refGroupeSelect == null || refGroupeSelect.getId() == null) ? null : refGroupeSelect);
//            policePlafond.setIdPlafondMaladie(plafondMaladie);
//            policePlafond.setTypeAvenant(typeAvenant);
////            }
//            refGroupeSelect.setPolicePlafond(policePlafond);
//        }
//    }
//    
//    public AvenantCongtroller() {
//        police = new OrclassPolice();
//        assure = new OrclassAssure();
//        duree = new OrclassDuree();
//        fractionnement = new OrclassFractionnement();
//        policeCommissionApporteur = new OrclassPoliceCommissionApporteur();
//        police.setDate_cours_devise(new Date());
//        apporteur = new OrclassApporteur();
//        imageFamilleRisque = new OrclassImageFamilleRisque();
//        imageFamilleRisqueSelecte = new OrclassImageFamilleRisque();
//        
//        risque = new OrclassRisque();
//        rubriqueCaracteristique = new OrclassRubriqueCaracteristique();
//        rubriqueGarantie = new OrclassRubriqueGarantie();
//        quitance = new OrclassQuitance();
//        prime_Apporteur = new OrclassCommission_Prime_Apporteur();
//        policeSelect = new OrclassPolice();
//        intermediairesSelect = new OrclassIntermediaires();
//        refGroupe = new OrclasseRefGroupe();
//        refGroupeSelect = new OrclasseRefGroupe();
//        imageRisque = new OrclassImageRisque();
//        imageRisqueSelecte = new OrclassImageRisque();
//    }
//    
//    public void chargeRisqueByGroup() {
//        OrclassImageRisque r = null;
//        List<OrclassImageRisque> listImageRisque = new ArrayList<>();
//        listeImageRisques.clear();
//        if (refGroupeSelect != null && refGroupeSelect.getId() != null && policeSelect != null && police.getId() != null) {
//            listeImageRisques = imageRisqueDao.listeImageRisqueFamille(entreprise, refGroupeSelect, numero_police, assure.getRaison_social().toUpperCase());
//            if (listeImageRisques.isEmpty()) {
//                r = new OrclassImageRisque();
//                r.setIdGroup(refGroupeSelect);
//                listeImageRisques.add(r);
//                
//            } else {
//                r = new OrclassImageRisque();
//                r.setIdGroup(refGroupeSelect);
//                listeImageRisques.add(r);
//                reverseListeRique();
//                
//            }
//        }
//        
//        this.updateTableRisque();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//    
//    public void chargeTypeAvenantByChois() {
//        if (police == null || police.getId() == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE SELECT POLICE..."));
//            typeAvenant = new Orclass_TypeAvenant();
//            return;
//        }
//        if (typeAvenant != null && typeAvenant.getId() != null && police != null && police.getId() != null) {
//            this.initialiser();
//            this.listRisqueControle.clear();
//            
//            avenant = avenantDao.lastAvenantByPolice(policeSelect, entreprise);
//            if (avenant != null && avenant.getId() != null) {
//                switch (avenant.getIdTypeAvenant().getNatureAvenant()) {
//                    case suspension:
////                        if (typeAvenant.getNatureAvenant().equals(NatureAvenant.remise_vigueur_normal)) {
////                            PrimeFaces.current().dialog().openDynamic("SUSPENDED ENDORSEMENT  ");
////                        } else {
//                        if (!typeAvenant.getNatureAvenant().equals(NatureAvenant.remise_vigueur_normal)) {
//                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CETTE POLICE EST SUSPENDUE...", "ACTION NON EFFECTUEE"));
//                            typeAvenant = new Orclass_TypeAvenant();
//                        }
//
////                        }
//                        break;
//                    
//                    case annulation_acte_anterieur:
//                        PrimeFaces.current().dialog().openDynamic("CANCEL ENDORSEMENT ");
//                        break;
//                    case arret_couvertre_ristourne:
////                        PrimeFaces.current().dialog().openDynamic("CETTE POLICE EST EN ARRET COUVERTURE ");
//                        if (!typeAvenant.getNatureAvenant().equals(NatureAvenant.remise_vigueur_normal)) {
//                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CETTE POLICE EST EN ARRET...", "ACTION NON EFFECTUEE"));
//                            typeAvenant = new Orclass_TypeAvenant();
//                        }
//                        break;
//                    case arret_couverture_sans_ristourne:
////                        PrimeFaces.current().dialog().openDynamic("CETTE POLICE EST EN ARRET COUVERTURE ");
//                        if (!typeAvenant.getNatureAvenant().equals(NatureAvenant.remise_vigueur_normal)) {
//                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CETTE POLICE EST EN ARRET...", "ACTION NON EFFECTUEE"));
//                            typeAvenant = new Orclass_TypeAvenant();
//                        }
//                        break;
//                    
//                    case resiliation:
//                        if (!typeAvenant.getNatureAvenant().equals(NatureAvenant.remise_vigueur_normal)) {
//                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CETTE POLICE EST RESILIE...", "ACTION NON EFFECTUEE"));
//                            typeAvenant = new Orclass_TypeAvenant();
//                        }
//                        
//                        break;
//                    case resiliation_avec_calcul_de_prime_courue:
//                        PrimeFaces.current().dialog().openDynamic("CETTE POLICE EST RESILIE ");
//                        break;
//                    case terme:
//                        if (!typeAvenant.getNatureAvenant().equals(NatureAvenant.remise_vigueur_normal)) {
//                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("CETTE POLICE EST A TERME...", "ACTION NON EFFECTUEE"));
//                            typeAvenant = new Orclass_TypeAvenant();
//                        }
////                        PrimeFaces.current().dialog().openDynamic("CETTE POLICE A TERME ");
//                        break;
//                    
//                    default:
//                        /*
//                        nous rechercons les acces au type avenant
//                        
//                         */
//                        this.controlleTypeAvenant();
//                        
//                        break;
//                    
//                }
//            } else {//ici l avenat n existe il s agit de l avenant numero 1
//                // traitement
//
//                switch (typeAvenant.getNatureAvenant()) {
//                    case suspension:
////                        if (typeAvenant.getNatureAvenant().equals(NatureAvenant.remise_vigueur_normal)) {
////                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("TRAITEMENT REMISE ENVIGUEUR "));
////                        } else {
//
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("TRAITEMENT SUSPENSION ", "POLICE NUMERO " + this.police.getPolice()));
//
////                        }
//                        break;
//                    
//                    case annulation_acte_anterieur:
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("TRAITEMENT ACTE ANTERIEUR", "POLICE NUMERO " + this.police.getPolice()));
////                        controlleTypeAvenant();
//                        break;
//                    case arret_couvertre_ristourne:
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("TRAITEMENTE ARRET COUVERTURE AVEC RISTOURNE", "POLICE NUMERO " + this.police.getPolice()));
//                        break;
//                    case arret_couverture_sans_ristourne:
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ARRET COUVERTURE SANS RISTOURNE", "POLICE NUMERO " + this.police.getPolice()));
//                        break;
//                    
//                    case resiliation:
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("TRAITEMENT RESILIATION ", "POLICE NUMERO " + this.police.getPolice()));
//                        break;
//                    case resiliation_avec_calcul_de_prime_courue:
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("TRAITEMENT RESILIATION AVEC CALCUL DE PRIME", "POLICE NUMERO " + this.police.getPolice()));
//                        break;
//                    case terme:
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("TRAITEMENT TERME ", "POLICE NUMERO " + this.police.getPolice()));
//                        break;
//                    
//                    default:
//                        
//                        this.controlleTypeAvenant();
//                        break;
//                }
////            PrimeFaces.current().ajax().update(":form1");
//            }
//        }
//    }
//    
//    public void controlleTypeAvenant() {
//        for (Orclass_Objet ob : objetDao.findAllEntitiesHavingValue("idBranche", branches)) {
//            access_Avenant = access_AvenantDao.finKey(entreprise, branches, ob, typeAvenant);
//            if (access_Avenant == null) {
//                continue;
//            }
//            switch (ob.getClasseObjet()) {
//                case police:
//                    this._PSAjouter = access_Avenant.getAjouter();
//                    
//                    this._PSmodifier = access_Avenant.getModifier();
//                    this._PSsupprimer = access_Avenant.getSupprimer();
//                    if (Objects.equals(this._PSmodifier, Boolean.TRUE)) {
//                        for (Orclass_AccessAvenat_Caracteristique ar : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                            if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.activite.name())) {
//                                this._PSactivite = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.bp.name())) {
//                                this._PSbp = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.date_creation.name())) {
//                                this._PSdate_creation = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.date_echeance.name())) {
//                                this._PSdate_echeance = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.date_effet.name())) {
//                                this._PSdate_effet = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.email.name())) {
//                                this._PSemail = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.lieu_naissance.name())) {
//                                this._PSlieu_naissance = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.nom.name())) {
//                                this._PSnom = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.numero_pantent.name())) {
//                                this._PSnumero_pantent = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.numero_piece.name())) {
//                                this._PSnumero_piece = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.prenom.name())) {
//                                this._PSprenom = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.profession.name())) {
//                                this._PSprofession = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.qualite.name())) {
//                                this._PSqualite = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.raison.name())) {
//                                this._PSraison = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.sexe.name())) {
//                                this._PSsexe = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.telephone.name())) {
//                                this._PStelephone = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(policeSouscripteurZone.type_pieces.name())) {
//                                this._PStype_pieces = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.accessoires.name())) {
//                                this._PAUaccessoires = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.apporteur.name())) {
//                                this._PAUapporteur = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.contrat.name())) {
//                                this._PAUcontrat = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.convention.name())) {
//                                this._PAUconvention = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.devise.name())) {
//                                this._PAUdevise = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.duree.name())) {
//                                this._PAUduree = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.exoneration.name())) {
//                                this._PAUexoneration = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.fractionnement.name())) {
//                                this._PAUfractionnement = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.nature.name())) {
//                                this._PAUnature = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.numero_bordereau.name())) {
//                                this._PAunumero_bordereau = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.reduction.name())) {
//                                this._PAUreduction = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.reference.name())) {
//                                this._PAUreference = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.timbre_dimension.name())) {
//                                this._PAUtimbre_dimension = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PoliceAutresInformation.type_tarif.name())) {
//                                this._PAUtype_tarif = Boolean.TRUE;
//                            }
//                        }
//                    }
//                    
//                    break;
//                case adherent:
//                    this._ADsupprimer = access_Avenant.getSupprimer();
//                    this._ADmodifier = access_Avenant.getModifier();
//                    this._ADAjouter = access_Avenant.getAjouter();
//                    if (Objects.equals(_ADAjouter, Boolean.TRUE)) {
//                        if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                            if (avenant == null || avenant.getId() == null) {
//                                listeRisque = risqueDao.listeRisqueByPoliceHaveGroup(entreprise, policeSelect);
//                            }
//                            
//                            risque = new OrclassRisque();
//                            risque.setTypeAvenant(typeAvenant);
//                            risque.setAjouter(Boolean.TRUE);
//                            risque.setRetire_de_la_police(Boolean.FALSE);
//                            risque.setModifier(Boolean.FALSE);
//                            risque.setIdGroup(refGroupe);
//                            listeRisque.add(risque);
//                            this.reverseListeRique();
//                            this.updateTableRisque();
//                        }
//                        
//                    }
//                    
//                    if (Objects.equals(this._ADmodifier, Boolean.TRUE)) {
//                        for (Orclass_AccessAvenat_Caracteristique ar : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                            if (ar.getIdCaracteristiques().getLibelle().equals(AdherentZone.date_naissance.name())) {
//                                this._ADdate_naissance = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AdherentZone.lien_parente.name())) {
//                                this._ADlien_parente = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AdherentZone.membre.name())) {
//                                this._ADmembre = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AdherentZone.date_naissance.name())) {
//                                this._ADdate_naissance = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AdherentZone.sexe.name())) {
//                                this._ADsexe = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AdherentZone.matricule.name())) {
//                                this._ADMatricule = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AdherentZone.group.name())) {
//                                this._ADgroup = Boolean.TRUE;
//                            }
//                            
//                        }
//                    }
//                    
//                    break;
//                case assure:
//                    this._ASSsupprimer = access_Avenant.getSupprimer();
//                    this._ASSmodifier = access_Avenant.getModifier();
//                    this._ASSAjouter = access_Avenant.getAjouter();
//                    if (Objects.equals(this._ASSmodifier, Boolean.TRUE)) {
//                        for (Orclass_AccessAvenat_Caracteristique ar : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                            if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.activite.name())) {
//                                this._ASSactivite = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.bp.name())) {
//                                this._ASSbp = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.dateEntre.name())) {
//                                this._ASSdateEntre = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.date_naissance.name())) {
//                                this._ASSdate_naissance = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.email.name())) {
//                                this._ASSemail = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.lieu_naissance.name())) {
//                                this._ASSlieu_naissance = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.nom.name())) {
//                                this._ASSnom = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.numero_piece.name())) {
//                                this._ASSnumero_piece = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.profession.name())) {
//                                this._ASSprofession = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.sexe.name())) {
//                                this._ASSsexe = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.telephone.name())) {
//                                this._ASStelephone = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(AssureZone.type_pieces.name())) {
//                                this._ASStype_pieces = Boolean.TRUE;
//                            }
//                        }
//                    }
//                    break;
//                case caracteristique:
//                    this._CARmodifier = access_Avenant.getModifier();
//                    this._CARAjouter = access_Avenant.getAjouter();
//                    this._CARsupprimer = access_Avenant.getSupprimer();
//                    if (Objects.equals(this._CARAjouter, Boolean.TRUE)) {
//                        policeCaracteristique = new OrclassPoliceCaracteristique();
//                        listePoliceCaracteristique.add(policeCaracteristique);
//                        if (avenant == null || avenant.getId() == null) {
//                            listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, categories, rubriqueCategorie.getIdRubrique());
//                            
//                        }
//                        
//                        this.reverseListeCaracteristique();
//                        this.updateDataTablePoliceCarzacteristique();
//                        this.updateDataTableRubriqueCaracteristique();
//                    }
//                    
//                    if (Objects.equals(this._CARmodifier, Boolean.TRUE)) {
//                        for (Orclass_AccessAvenat_Caracteristique ar : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                            if (ar.getIdCaracteristiques().getLibelle().equals(CaracteristiqueZone.designation.name())) {
//                                this._CARdesignation = Boolean.TRUE;
//                                
//                            }
//                        }
//                        
//                    }
//                    break;
//                case famille:
//                    this._FAMsupprimer = access_Avenant.getSupprimer();
//                    this._FAMmodifier = access_Avenant.getModifier();
//                    this._FAMAjouter = access_Avenant.getAjouter();
//                    if (Objects.equals(this._FAMAjouter, Boolean.TRUE)) {
//                        if (Objects.equals(tabShowFamille, Boolean.TRUE)) {
//                            risqueFamille = new OrclassRisqueFamille();
//                            if (listeRisqueFamille == null) {
//                                listeRisqueFamille = new ArrayList<>();
//                            }
//                            
//                            listeRisqueFamille.add(risqueFamille);
//                            if (avenant == null || avenant.getId() == null) {
//                                this.setRisque(risqueDao.risqueByPolice(entreprise, police));
//                            }
//                            
//                            this.reverseListeFamilleRiques();
//                            this.updateDataTableRisqueFamille();
//                        }
//                        
//                    }
//                    if (Objects.equals(this._FAMmodifier, Boolean.TRUE)) {
//                        for (Orclass_AccessAvenat_Caracteristique ar : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                            if (ar.getIdCaracteristiques().getLibelle().equals(FamilleZone.dateEntre.name())) {
//                                this._FAMdateEntre = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(FamilleZone.date_naissance.name())) {
//                                this._FAMdate_naissance = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(FamilleZone.lien_parente.name())) {
//                                this._FAMlien_parente = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(FamilleZone.membre.name())) {
//                                this._FAMmembre = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(FamilleZone.sexe.name())) {
//                                this._FAMsexe = Boolean.TRUE;
//                            }
//                        }
//                        
//                    }
//                    
//                    break;
//                case garanties:
//                    this._GARmodifier = access_Avenant.getModifier();
//                    this._GARAjouter = access_Avenant.getAjouter();
//                    this._GARsupprimer = access_Avenant.getSupprimer();
//                    if (Objects.equals(this._GARAjouter, Boolean.TRUE)) {
//                        if (avenant == null || avenant.getId() == null) {
//                            listePoliceGarantie = policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, police);
//                            listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorie(policeSelect.getIdTypeTarif(), rubriqueCategorie, entreprise);
//                            
//                        }
//                        
//                        policeGarantie = new OrclassPoliceGarantie();
//                        listePoliceGarantie.add(policeGarantie);
//                        this.reverseListePoliceGarantie();
//                        this.updateDataTablePoliceGaranties();
//                        this.updateDataTableRubriqueGarantie();
//                        
//                    }
//                    if (Objects.equals(this._GARmodifier, Boolean.TRUE)) {
//                        for (Orclass_AccessAvenat_Caracteristique ar : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                            if (ar.getIdCaracteristiques().getLibelle().equals(GarantiesZone.garantie_capital.name())) {
//                                this._GARgarantie_capital = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(GarantiesZone.garantie_pourcentage.name())) {
//                                this._GARgarantie_pourcentage = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(GarantiesZone.garantie_prime.name())) {
//                                this._GARgarantie_prime = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(GarantiesZone.garantie_proarata.name())) {
//                                this._GARgarantie_proarata = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(GarantiesZone.garantie_taux.name())) {
//                                this._GARgarantie_taux = Boolean.TRUE;
//                            }
//                        }
//                    }
//                    break;
//                case groupe:
//                    this._GROAjouter = access_Avenant.getAjouter();
//                    this._GROmodifier = access_Avenant.getModifier();
//                    this._GROsupprimer = access_Avenant.getSupprimer();
//                    
//                    if (Objects.equals(this._GROmodifier, Boolean.TRUE)) {
//                        for (Orclass_AccessAvenat_Caracteristique ar : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                            if (ar.getIdCaracteristiques().getLibelle().equals(GroupeZone.date_naissance.name())) {
//                                this._GROdate_naissance = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(GroupeZone.matricule.name())) {
//                                this._GROmatricule = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(GroupeZone.nom.name())) {
//                                this._GROnom = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(GroupeZone.sexe.name())) {
//                                this._GROsexe = Boolean.TRUE;
//                            }
//                        }
//                        
//                    }
//                    break;
//                
//                case prestation:
//                    this._PREAjouter = access_Avenant.getAjouter();
//                    this._PREmodifier = access_Avenant.getModifier();
//                    this._PREsupprimer = access_Avenant.getSupprimer();
//                    if (Objects.equals(this._PREAjouter, Boolean.TRUE)) {
//                        detailPolicePlafond = new OrclassDetailPolicePlafond();
//                        listeDetailPolicePlafond.add(detailPolicePlafond);
//                        if (avenant == null || avenant.getId() == null) {
//                            listePrestation = prestationDao.listePrestationByCompagnie(rubriqueCategorie, entreprise);
//                        }
//                        
//                        this.reverseListePolicedetailPlafond();
////                        this.updateTabledetailPolicePlafond();
////                        this.updateTablePrestation();
//
//                    }
//                    if (Objects.equals(this._PREmodifier, Boolean.TRUE)) {
//                        for (Orclass_AccessAvenat_Caracteristique ar : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                            if (ar.getIdCaracteristiques().getLibelle().equals(PrestationZone.designation.name())) {
//                                this._PREdesignation = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PrestationZone.plafond_maladie.name())) {
//                                this._PREplafond_maladie = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PrestationZone.prestation_bareme.name())) {
//                                this._PREprestation_bareme = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PrestationZone.prestation_forfait.name())) {
//                                this._PREprestation_forfait = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PrestationZone.prestation_plafon.name())) {
//                                this._PREprestation_plafon = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PrestationZone.prestation_taux.name())) {
//                                this._PREprestation_taux = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(PrestationZone.prestation_type.name())) {
//                                this._PREprestation_type = Boolean.TRUE;
//                            }
//                        }
//                        
//                    }
//                    break;
//                case quittance:
//                    this._QUImodifier = access_Avenant.getModifier();
//                    this._QUIsupprimer = access_Avenant.getSupprimer();
//                    this._QUIAjouter = access_Avenant.getAjouter();
//                    if (Objects.equals(this._QUImodifier, Boolean.TRUE)) {
//                        for (Orclass_AccessAvenat_Caracteristique ar : accessAvenat_CaracteristiqueDao.listCaracteristiqueByAccessAvenant(access_Avenant, entreprise)) {
//                            if (ar.getIdCaracteristiques().getLibelle().equals(QuittanceObjet.date_emission.name())) {
//                                
//                                this._QUIdate_emission = Boolean.TRUE;
//                                
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(QuittanceObjet.type_quittance.name())) {
//                                this._QUItype_quittance = Boolean.TRUE;
//                            } else if (ar.getIdCaracteristiques().getLibelle().equals(QuittanceObjet.tva.name())) {
//                                this._QUITva = Boolean.TRUE;
//                            }
//                        }
//                        
//                    }
//                    break;
//                
//            }
//        }
////        PrimeFaces.current().ajax().update(":form1");
//
//    }
//    
//    public void initialiser() {
//        //initial quittance
//        quitance = new OrclassQuitance();
//
//        // objet police souscripteur
//        _PSqualite = Boolean.FALSE;
//        _PSraison = Boolean.FALSE;
//        _PSactivite = Boolean.FALSE;
//        _PSdate_creation = Boolean.FALSE;
//        _PSbp = Boolean.FALSE;
//        _PSnumero_pantent = Boolean.FALSE;
//        _PSville = Boolean.FALSE;
//        _PStelephone = Boolean.FALSE;
//        _PSemail = Boolean.FALSE;
//        _PSdate_effet = Boolean.FALSE;
//        _PSdate_echeance = Boolean.FALSE;
//        _PSnom = Boolean.FALSE;
//        _PSprenom = Boolean.FALSE;
//        _PSsexe = Boolean.FALSE;
//        _PSlieu_naissance = Boolean.FALSE;
//        _PSprofession = Boolean.FALSE;
//        _PStype_pieces = Boolean.FALSE;
//        _PSnumero_piece = Boolean.FALSE;
//        _PSsupprimer = Boolean.FALSE;
//        _PSAjouter = Boolean.FALSE;
//        _PSmodifier = Boolean.FALSE;
//        //objet police Autreinformation
//        _PAUcontrat = Boolean.FALSE;
//        _PAUnature = Boolean.FALSE;
//        _PAUduree = Boolean.FALSE;
//        _PAUvaleur_duree = Boolean.FALSE;
//        _PAUfractionnement = Boolean.FALSE;
//        _PAUtype_tarif = Boolean.FALSE;
//        _PAUreduction = Boolean.FALSE;
//        _PAUapporteur = Boolean.FALSE;
//        _PAUdevise = Boolean.FALSE;
//        _PAUconvention = Boolean.FALSE;
//        _PAUreference = Boolean.FALSE;
//        _PAUtimbre_dimension = Boolean.FALSE;
//        _PAUexoneration = Boolean.FALSE;
//        _PAUaccessoires = Boolean.FALSE;
//        _PAunumero_bordereau = Boolean.FALSE;
//        _PAUsupprimer = Boolean.FALSE;
//        _PAUAjouter = Boolean.FALSE;
//        _PAUmodifier = Boolean.FALSE;
//        // objet adherent zone
//        _ADmembre = Boolean.FALSE;
//        _ADlien_parente = Boolean.FALSE;
//        _ADdate_naissance = Boolean.FALSE;
//        _ADsexe = Boolean.FALSE;
//        _ADsupprimer = Boolean.FALSE;
//        _ADAjouter = Boolean.FALSE;
//        _ADmodifier = Boolean.FALSE;
//        // objet Assure Zone
//        _ASSnom = Boolean.FALSE;
//        _ASSdate_naissance = Boolean.FALSE;
//        _ASSlieu_naissance = Boolean.FALSE;
//        _ASSsexe = Boolean.FALSE;
//        _ASSemail = Boolean.FALSE;
//        _ASSbp = Boolean.FALSE;
//        _ASStelephone = Boolean.FALSE;
//        _ASSville = Boolean.FALSE;
//        _ASSprofession = Boolean.FALSE;
//        _ASSactivite = Boolean.FALSE;
//        _ASStype_pieces = Boolean.FALSE;
//        _ASSnumero_piece = Boolean.FALSE;
//        _ASSdateEntre = Boolean.FALSE;
//        _ASSsupprimer = Boolean.FALSE;
//        _ASSAjouter = Boolean.FALSE;
//        _ASSmodifier = Boolean.FALSE;
//        //objet caracteristique
//        _CARdesignation = Boolean.FALSE;
//        _CARsupprimer = Boolean.FALSE;
//        _CARAjouter = Boolean.FALSE;
//        _CARmodifier = Boolean.FALSE;
//        //objet famille zone
//        _FAMmembre = Boolean.FALSE;
//        _FAMlien_parente = Boolean.FALSE;
//        _FAMdate_naissance = Boolean.FALSE;
//        _FAMsexe = Boolean.FALSE;
//        _FAMdateEntre = Boolean.FALSE;
//        _FAMsupprimer = Boolean.FALSE;
//        _FAMAjouter = Boolean.FALSE;
//        _FAMmodifier = Boolean.FALSE;
//        //objet Garantie Zone
//        _GARgarantie_capital = Boolean.FALSE;
//        _GARgarantie_taux = Boolean.FALSE;
//        _GARgarantie_pourcentage = Boolean.FALSE;
//        _GARgarantie_prime = Boolean.FALSE;
//        _GARgarantie_proarata = Boolean.FALSE;
//        _GARsupprimer = Boolean.FALSE;
//        _GARAjouter = Boolean.FALSE;
//        _GARmodifier = Boolean.FALSE;
//        //Objet Group
//        _GROmatricule = Boolean.FALSE;
//        _GROnom = Boolean.FALSE;
//        _GROdate_naissance = Boolean.FALSE;
//        _GROsexe = Boolean.TRUE;
//        _GROsupprimer = Boolean.FALSE;
//        _GROAjouter = Boolean.FALSE;
//        _GROmodifier = Boolean.FALSE;
//        // objet prestation Zone
//        _PREplafond_maladie = Boolean.FALSE;
//        _PREdesignation = Boolean.FALSE;
//        _PREprestation_taux = Boolean.FALSE;
//        _PREprestation_forfait = Boolean.FALSE;
//        _PREprestation_type = Boolean.FALSE;
//        _PREprestation_plafon = Boolean.FALSE;
//        _PREprestation_bareme = Boolean.FALSE;
//        _PREsupprimer = Boolean.FALSE;
//        _PREAjouter = Boolean.FALSE;
//        _PREmodifier = Boolean.FALSE;
//        // Objet Quittance
//        _QUItype_quittance = Boolean.FALSE;
//        _QUIdate_emission = Boolean.FALSE;
//        _QUIsupprimer = Boolean.FALSE;
//        _QUIAjouter = Boolean.FALSE;
//        _QUImodifier = Boolean.FALSE;
//        
//    }
//    
//    public void showDetailImageFamilleRisque(OrclassRisqueFamille item, int index) {
//
////        int indexRisque = 0;
////        indexRisque = listeRisque.indexOf(risque);
////        index = risque.getListeRisqueFamille().indexOf(item);
//        item.setTypeAvenant(typeAvenant);
//        item.setAjouter(Boolean.TRUE);
//        item.setRetire_de_la_police(Boolean.FALSE);
//        item.setModifier(Boolean.FALSE);
//
////        risque.getListeRisqueFamille().add(item);
////        risque.getListeRisqueFamille().set(index, item);
////        listeRisque.set(risque.getIndex(), risque);
//        listeRisqueFamille.set(index, item);
//        risque.setListeRisqueFamille(listeRisqueFamille);
//        listeRisque.set(risque.getIndex(), risque);
//        risqueFamille = new OrclassRisqueFamille();
//        risqueFamille.setIdRisque(risque);
//        risqueFamille.setAjouter(Boolean.TRUE);
//        risqueFamille.setRetire_de_la_police(Boolean.FALSE);
//        risqueFamille.setModifier(Boolean.FALSE);
//        risqueFamille.setTypeAvenant(typeAvenant);
//        listeRisqueFamille.add(risqueFamille);
//
////        risque.getListeRisqueFamille().add(risqueFamille);
//        this.reverseListeFamilleRique();
////        this.updateTableRisque();
//        this.updateDataTableRisqueFamille();
//        PrimeFaces.current().ajax().update(":form2");
//        PrimeFaces.current().executeScript("PF('familleRisqueDialog').show()");
//        
//    }
//    
//    public void fermeListeRisqueFamille() {
//        risque.setListeRisqueFamille(listeRisqueFamille);
//        listeRisque.set(risque.getIndex(), risque);
//        this.updateTableRisque();
//    }
//    
//    public void removeRisqueFamilleByListe(OrclassRisqueFamille item, int index) {
//        if (item != null && item.getId() != null && item.getTypeAvenant() != null && item.getTypeAvenant().getId() != null) {
//            item.setRetire_de_la_police(Boolean.TRUE);
//            item.setCodeid_retirer(item.getCodeid_retirer());
//            item.setAjouter(Boolean.FALSE);
//            item.setModifier(Boolean.FALSE);
//            listeRisqueFamille.set(index, item);
//            risque.setListeRisqueFamille(listeRisqueFamille);
//            listeRisque.set(risque.getIndex(), risque);
//
////            int index = listeRisque.indexOf(risque);
////            risque.getListeRisqueFamille().remove(item);
////            listeRisque.set(index, risque);
////            risqueFamille = new OrclassRisqueFamille();
////            risque.getListeRisqueFamille().add(risqueFamille);
//////            risqueFamille=listeRisqueFamille.get(0);
////           listeRisqueFamille.remove(risqueFamille);
////            risque.setListeRisqueFamille(listeRisqueFamille);
////            this.chargeFamilleRisqueByRisque(risque)
//            this.updateDataTableRisqueFamille();
//            PrimeFaces.current().ajax().update(":form2");
//            PrimeFaces.current().executeScript("PF('familleRisqueDialog').show()");
//        }
//        
//    }
//    
//    public void listeAvenantByPolices(OrclassPolice item) {
//
////        if (this.avenantConsultation != null && this.avenantConsultation .getId() != null) {
//        if (item != null && item.getId() != null) {
//            listeAvenantByPolice = avenantDao.listeAvenantByPolice(item, entreprise);
//            if (listeAvenantByPolice.isEmpty()) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("LIST IS EMPTY", "AUCUN AVENANT EXISTANT"));
//                return;
//            } else {
//                this.updateDataTableAvenant();
////                PrimeFaces.current().ajax().update(":form2");
//                PrimeFaces.current().executeScript("PF('manageAvenantDialog').show()");
//                PrimeFaces.current().executeScript("PF('managePoliceDialog').hide()");
//            }
//            
//        }
//    }
//    
//    public void updateFamilleByListe(OrclassRisqueFamille item, int index) {
//        if (item != null && item.getId() != null) {
////            int index = listeRisqueFamille.indexOf(item);
////            item.setTypeAvenant(typeAvenant);
//            item.setModifier(Boolean.TRUE);
//            risqueFamilleDao.update(item);
//            listeRisqueFamille.set(index, item);
//            
//            this.updateDataTableRisqueFamille();
//            PrimeFaces.current().ajax().update(":form2");
//            PrimeFaces.current().executeScript("PF('familleRisqueDialog').show()");
//        }
//        
//    }
//
////    public void showDetailImageFamilleRisque(OrclassImageRisque item) {
//////        if (imageRisque==null || im) {
////        this.setImageRisque(item);
////
//////        }
////    }
//    public void chargeRisqueByImageRisque() {
//        if (refGroupeSelectForAdherent != null && refGroupeSelectForAdherent.getId() != null && (police == null || police.getId() == null)) {
//            listeImageRisques = imageRisqueDao.listeImageRisqueFamille(entreprise, refGroupeSelectForAdherent, numero_police, assure.getRaison_social().toUpperCase());
//            
//        } else if (refGroupeSelectForAdherent != null && refGroupeSelectForAdherent.getId() != null && (police != null && police.getId() != null)) {
//            listeRisque = risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelectForAdherent, police);
//        }
//    }
//    
//    public void chargeImageRisqueFamilleByImageRisque() {
//        OrclassImageFamilleRisque ifr = null;
//        listeImageFamilleRisque.clear();
//        if (imageRisqueSelecte != null && imageRisqueSelecte.getId() != null && (police == null || police.getId() == null)) {
//            listeImageFamilleRisque = imageFamilleRisqueDao.listeImageFamilleRisqueByImageRisque(imageRisqueSelecte);
//            
//            if (listeImageFamilleRisque.isEmpty()) {
//                ifr = new OrclassImageFamilleRisque();
//                listeImageFamilleRisque.add(ifr);
//                
//            } else {
//                ifr = new OrclassImageFamilleRisque();
//                listeImageFamilleRisque.add(ifr);
//                
//            }
//            this.reverseListeImageFamilleRique();
//            this.updateTableImageFamilleRisque();
//        } else if (risque != null && risque.getId() != null && (police != null && police.getId() != null)) {
//            listeRisqueFamille = (List<OrclassRisqueFamille>) risqueFamilleDao.findAllEntitiesHavingValue("idRisque", risque);
//            this.updateDataTableRisqueFamille();
//        }
//        
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//    
//    public void forceModeCalulPrimeManuelle() {
//        this.setModecalculForcerManuel(true);
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//    
//    public void addRisque(int index) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        OrclassRisque item = listeRisque.get(index);
//        risque = item;
//        if (risque != null && risque.getMatricule() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "MATRICULE IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (risque != null && risque.getDateNaissance() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "DATE OF BIRTH IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (risque != null && risque.getLibelle() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "NAME IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
////        int indice = listeRisque.indexOf(risque);
//        risque.setTypeAvenant(typeAvenant);
//        risque.setAjouter(Boolean.TRUE);
//        risque.setRetire_de_la_police(Boolean.FALSE);
//        risque.setModifier(Boolean.FALSE);
//        if (Objects.equals(tabShowGroup, Boolean.TRUE) && (risque.getIdGroup() == null || risque.getIdGroup().getId() == null)) {
//            risque.setIdGroup(refGroupeSelect);
//        }
//        listeRisque.set(index, risque);
//        this.chrgaLigneForAddRisque();
//        
//        this.updateTableRisque();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//        
//    }
//    
//    public void updateRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        int index = 0;
//        
//        if (risque != null && risque.getId() != null && typeAvenant != null && typeAvenant.getId() != null) {
//            index = listeRisque.indexOf(risque);
//            
//            risque.setTypeAvenant(typeAvenant);
//            listeRisque.set(index, risque);
//            
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.UPDATE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        
//        this.updateTableRisque();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//        
//    }
//    
//    public void deleteImageRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        
//        if (imageRisque != null && imageRisque.getId() != null) {
//            
//            imageRisqueDao.delete(imageRisque);
//            
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.DELETE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        listeImageRisques = imageRisqueDao.listeImageRisqueFamille(entreprise, refGroupeSelect, numero_police, assure.getRaison_social());
//        imageRisque = new OrclassImageRisque();
//        imageRisque.setIdGroup(refGroupeSelect);
//        listeImageRisques.add(imageRisque);
////        this.reverseListeImageRique();
//        this.updateTableImageRisque();
//        
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//        
//    }
//    
//    public void addImageFamilleRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
////        if (imageFamilleRisque != null && imageFamilleRisque.getMatricule() == null) {
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "MATRICULE IS EMPTY", "PLEASE TRY AGAINST"));
////            return;
////        }
//        if (imageFamilleRisqueSelecte != null && imageFamilleRisqueSelecte.getNom_membre() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "MEMBER NAME IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (imageFamilleRisqueSelecte != null && imageFamilleRisqueSelecte.getSexe() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "SELECT SEX", "PLEASE TRY AGAINST"));
//            return;
//        }
//        
//        if (imageFamilleRisqueSelecte != null && imageFamilleRisqueSelecte.getLienParente() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "SELECT VALUE", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (imageFamilleRisqueSelecte != null && imageFamilleRisqueSelecte.getDateNaissance() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "DATE OF BIRTH IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (imageFamilleRisqueDao.finKey(imageRisqueSelecte, imageFamilleRisqueSelecte.getNom_membre(), imageFamilleRisqueSelecte.getDateNaissance()) == null) {
//            imageFamilleRisqueSelecte.setIdRisque(imageRisqueSelecte);
//            
//            imageFamilleRisqueDao.create(imageFamilleRisqueSelecte);
//            
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        listeImageFamilleRisque = imageFamilleRisqueDao.listeImageFamilleRisqueByImageRisque(imageRisqueSelecte);
//        imageFamilleRisque = new OrclassImageFamilleRisque();
////        imageRisque.setIdGroup(refGroupeSelect);
//        listeImageFamilleRisque.add(imageFamilleRisque);
//        this.reverseListeImageFamilleRique();
//        this.updateTableImageFamilleRisque();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//        
//    }
//    
//    public void updateImageFamilleRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        
//        if (imageFamilleRisqueSelecte != null && imageFamilleRisqueSelecte.getId() != null) {
//            
//            imageFamilleRisqueDao.update(imageFamilleRisqueSelecte);
//            
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.UPDATE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        listeImageFamilleRisque = imageFamilleRisqueDao.listeImageFamilleRisqueByImageRisque(imageRisqueSelecte);
//        imageFamilleRisque = new OrclassImageFamilleRisque();
////        imageRisque.setIdGroup(refGroupeSelect);
//        listeImageFamilleRisque.add(imageFamilleRisque);
//        this.reverseListeImageFamilleRique();
//        this.updateTableImageFamilleRisque();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//        
//    }
//    
//    public void deleteImageFamilleRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        
//        if (imageFamilleRisqueSelecte != null && imageFamilleRisqueSelecte.getId() != null) {
//            
//            imageFamilleRisqueDao.delete(imageFamilleRisqueSelecte);
//            
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.DELETE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        listeImageFamilleRisque = imageFamilleRisqueDao.listeImageFamilleRisqueByImageRisque(imageRisqueSelecte);
//        imageFamilleRisque = new OrclassImageFamilleRisque();
////        imageRisque.setIdGroup(refGroupeSelect);
//        listeImageFamilleRisque.add(imageFamilleRisque);
//        this.reverseListeImageFamilleRique();
//        this.updateTableImageFamilleRisque();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//        
//    }
//    
//    public void updateTableImageRisque() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tabprincipal:idRisqueTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('RisqueTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableRisque() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tabprincipal:idRisqueTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('RisqueTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableImageFamilleRisque() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tabprincipal:idimageFamilleRisqueTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('imageFamilleRisqueTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateTableRubriqueGarantie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tabprincipal:idirgTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('rgTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void reverseListeRique() {
//        
//        List<OrclassRisque> result = new ArrayList<>();
//        for (int i = listeRisque.size() - 1; i >= 0; i--) {
//            result.add(listeRisque.get(i));
//        }
//        
//        this.setListeRisque(result);
////        this.updateTableRubriqueCategorie();
//    }
//    
//    public void reverseListeImageFamilleRique() {
//        
//        List<OrclassImageFamilleRisque> result = new ArrayList<>();
//        for (int i = listeImageFamilleRisque.size() - 1; i >= 0; i--) {
//            result.add(listeImageFamilleRisque.get(i));
//        }
//        
//        this.setListeImageFamilleRisque(result);
////        this.updateTableRubriqueCategorie();
//    }
//    
//    public void reverseListeFamilleRique() {
//        
//        List<OrclassRisqueFamille> result = new ArrayList<>();
//        for (int i = risque.getListeRisqueFamille().size() - 1; i >= 0; i--) {
//            result.add(risque.getListeRisqueFamille().get(i));
//        }
//        this.risque.setListeRisqueFamille(result);
////        this.setListeRisqueFamille(result);
////        this.updateTableRubriqueCategorie();
//    }
//    
//    public void reverseListeFamilleRiques() {
//        
//        List<OrclassRisqueFamille> result = new ArrayList<>();
//        for (int i = listeRisqueFamille.size() - 1; i >= 0; i--) {
//            result.add(listeRisqueFamille.get(i));
//        }
//        this.setListeRisqueFamille(result);
////        this.setListeRisqueFamille(result);
////        this.updateTableRubriqueCategorie();
//    }
//    
//    public void calculTotalProrata() {
//        BigDecimal montant = BigDecimal.ZERO;
//        for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//            if (pg != null && pg.getId() != null) {
//                continue;
//            }
//            if (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null) {
//                continue;
//            }
//            if (pg.getPrime() != null && pg.getPrime().intValue() != 0) {
//                montant = montant.add(pg.getProrata());
//            }
//            
//        }
//        this.setTotalProrata(montant);
//        
//        this.updateDataTablePoliceGaranties();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal:");
//        
//    }
//    
//    public void updateForm() {
//        PrimeFaces.current().ajax().update(":form1:tabprincipal:");
//    }
//    
//    public void caculTotalPrime() {
//        BigDecimal montant = BigDecimal.ZERO;
//        for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//            if (pg != null && pg.getId() != null) {
//                continue;
//            }
//            if (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null) {
//                continue;
//            }
//            if (pg.getPrime() != null && pg.getPrime().intValue() != 0) {
//                montant = montant.add(pg.getPrime());
//                pg.setProrata(pg.getPrime());
//            }
//            
//        }
//        this.setTotalPrime(montant);
//        
//        this.calculTotalProrata();
//        
//        this.updateDataTablePoliceGaranties();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//    
//    public Boolean checkValeurTYpeCaracterisitique(int i, OrclassPoliceCaracteristique caract) {
//        //  
//        Boolean etat = Boolean.FALSE;
//        
//        switch (i) {
//            case 1:// le type de carateritique est de type texte
//                try {
//                if (caract.getIdCaracteristiques().getType_Caracteristique().getLibelle().equals("Texte")) {
//                    etat = Boolean.TRUE;
//                } else {
//                    etat = Boolean.FALSE;
//                }
//                
//            } catch (Exception e) {
//                etat = Boolean.FALSE;
//            }
//            
//            break;
//            case 2:// valeur
//                try {
//                if (caract.getIdCaracteristiques().getType_Caracteristique().getLibelle().equals("Valeur")) {
//                    etat = Boolean.TRUE;
//                } else {
//                    etat = Boolean.FALSE;
//                }
//                
//            } catch (Exception e) {
//                etat = Boolean.FALSE;
//            }
//            
//            break;
//            case 3://Liste
//                try {
//                if (caract.getIdCaracteristiques().getType_Caracteristique().getLibelle().equals("Liste")) {
//                    etat = Boolean.TRUE;
//                    //charger la liste Caracteristique
//                    listeElement_Liste_Caracteristique = element_Liste_CaracteristiqueDao.listCaracteristiqueByCompagnieHaveListeElment(caract.getIdCaracteristiques(), entreprise);
//                } else {
//                    etat = Boolean.FALSE;
//                }
//                
//            } catch (Exception e) {
//                etat = Boolean.FALSE;
//            }
//            
//            break;
//            case 4:// date
//                try {
//                if (caract.getIdCaracteristiques().getType_Caracteristique().getLibelle().equals("Date") || caract.getIdCaracteristiques().getType_Caracteristique().getLibelle().equals("Date référence")) {
//                    etat = Boolean.TRUE;
//                    
//                } else {
//                    etat = Boolean.FALSE;
//                }
//                
//            } catch (Exception e) {
//                etat = Boolean.FALSE;
//            }
//            
//            break;
//            case 5:// valeur entire
//                try {
//                if (caract.getIdCaracteristiques().getType_Caracteristique().getLibelle().equals("Val. Entière")) {
//                    etat = Boolean.TRUE;
//                    
//                } else {
//                    etat = Boolean.FALSE;
//                }
//                
//            } catch (Exception e) {
//                etat = Boolean.FALSE;
//            }
//            
//            break;
////            case 0:
////                etat = Boolean.TRUE;
////                break;
//            case 6:
//
//                try {
//                if (caract.getIdCaracteristiques().getType_Caracteristique().getLibelle().equals("Boolean")) {
//                    etat = Boolean.TRUE;
//                    
//                } else {
//                    etat = Boolean.FALSE;
//                }
//                
//            } catch (Exception e) {
//                etat = Boolean.FALSE;
//            }
//            break;
//        }
//        return etat;
//    }
//    
//    public void addReferenceGroupe() {
//        BigInteger nbre = BigInteger.ZERO;
//        
//        OrclasseRefGroupe groupeDetailElement = null;
//        OrclassGroupePolice gp = null;
//        if (refGroupe.getLibelle() != null && refGroupeDao.findEntityHavingValue("libelle", refGroupe.getLibelle()) == null) {
//            nbre = refGroupeDao.getlastNumeroOrder();
//            nbre = nbre.add(BigInteger.ONE);
////            refGroupe.setNumero_ordre(nbre);
////            refGroupeDao.create(refGroupe);
//
////            refGroupe = new OrclasseRefGroupe();
//            groupeDetailElement = new OrclasseRefGroupe();
//            groupeDetailElement.setLibelle(refGroupe.getLibelle().toUpperCase());
//            nbre = refGroupeDao.getlastNumeroOrder();
//            nbre = nbre.add(BigInteger.ONE);
//            groupeDetailElement.setNumero_ordre(nbre);
//            
//            refGroupeDao.create(groupeDetailElement);
//            this.listeRefGroup.add(groupeDetailElement);
//            this.setRefGroupeSelect(groupeDetailElement);
//            if (groupePoliceDao.finKey(entreprise, police, groupeDetailElement) == null) {
//                gp = new OrclassGroupePolice();
//                gp.setAdresseGroup(refGroupe.getAdresseGroup());
//                gp.setDateCreation(new Date());
//                gp.setIdEntreprise(entreprise);
//                gp.setIdPolice(police);
//                gp.setIdRefGroupe(groupeDetailElement);
//                gp.setIdVille(refGroupe.getVille());
//                gp.setObservation(refGroupe.getObservation());
//                gp.setAdresseGroup(refGroupe.getAdresseGroup());
//                gp.setAjouter(Boolean.TRUE);
//                
//                gp.setSaisir_par(user.getNom());
//                groupePoliceDao.create(gp);
//            }
//            groupeDetailElement.setAdresseGroup(refGroupe.getAdresseGroup());
//            groupeDetailElement.setVille(refGroupe.getVille());
//            groupeDetailElement.setObservation(refGroupe.getObservation());
//            this.listeRefGroup.add(groupeDetailElement);
//            this.setRefGroupeSelect(groupeDetailElement);
//            refGroupe = new OrclasseRefGroupe();
//            groupeDetailElement = new OrclasseRefGroupe();
////            this.setRefGroupeSelect(refGroupe);
//        } else {
//            refGroupe = refGroupeDao.findEntityHavingValue("libelle", refGroupe.getLibelle());
//            if (listeRefGroup.isEmpty()) {
//                listeRefGroup.add(refGroupe);
//                this.setRefGroupeSelect(refGroupe);
//            } else {
//                if (listeRefGroup.contains(refGroupe) == false) {
//                    listeRefGroup.add(refGroupe);
//                }
//                this.setRefGroupeSelect(refGroupe);
//                
//            }
//        }
//        chargeRisqueByGroup();
//        PrimeFaces.current().executeScript("PF('dlgGroup').hide()");
//        PrimeFaces.current().ajax().update(":form1:tabprinciipal");
//        
//    }
//    
//    public void handleFileUpload(FileUploadEvent event) throws IOException {
////        ImageIcon inconImage=null;
//        this.file = event;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        if (refGroupeSelect == null || refGroupeSelect.getId() == null) {
////            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN.toString(), " PLEASE SELECT GROUP " + " TRY AGAINST");
////            FacesContext.getCurrentInstance().addMessage("msg", message);
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "PLEASE SELECT GROUP", " TRY AGAINST"));
//            return;
//        }
//        
//        if (file != null) {
//            serviceExcell.chargeFileExcellProduction(file.getFile().getInputStream(), file.getFile().getFileName(), refGroupeSelect);
//            this.listeImageRisques = serviceExcell.listeImageRisque();
//            this.listeImageFamilleRisque = serviceExcell.listeImageFamilleRisque();
//            addListeImageRisque();
////            FacesMessage message = new FacesMessage("Successful", file.getFile().getFileName() + " is uploaded.");
////            FacesContext.getCurrentInstance().addMessage("msg", message);
//            this.chargeRisqueByGroup();
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, file.getFile().getFileName(), " is uploaded."));
//            PrimeFaces.current().executeScript("PF('fichier').hide()");
//            
//        }
//    }
//    
//    public Boolean affichelisteFamilleHAveRisque(OrclassRisque item) {
//        if (item == null || item.getLibelle() == null) {
//            return Boolean.FALSE;
//        }
//        
//        return item.getLibelle() != null && item.getLibelle() != "";
//    }
//    
//    public void addListeImageRisque() {
//        OrclassImageRisque imageRisque = null;
//        OrclassImageFamilleRisque imageFamilleRisque = null;
//        List<OrclassImageRisque> colImageRisque = new ArrayList<>(listeImageRisques);
//        listeImageRisques.clear();
//        if (typeAvenant != null && typeAvenant.getId() != null) {
//            for (OrclassImageRisque ir : colImageRisque) {
//                listeRisqueFamille = new ArrayList<>();
//                risque = new OrclassRisque();
//                risque.setIdEntreprise(entreprise);
//                risque.setDateNaissance(ir.getDateNaissance());
//                risque.setIdGroup(refGroupeSelect);
//                risque.setLibelle(ir.getLibelle());
//                risque.setMatricule(ir.getMatricule());
//                risque.setSexe(ir.getSexe());
//                risque.setTypeAvenant(typeAvenant);
//                
//                for (OrclassImageFamilleRisque irf : listeImageFamilleRisque) {
//                    if (risque.getMatricule().equals(irf.getMatricule())) {
//                        
//                        risqueFamille = new OrclassRisqueFamille();
//                        risqueFamille.setDateNaissance(irf.getDateNaissance());
//                        risqueFamille.setMatricule(irf.getMatricule());
//                        risqueFamille.setNom_membre(irf.getNom_membre());
//                        risqueFamille.setLienParente(irf.getLienParente());
//                        risqueFamille.setSexe(irf.getSexe());
//                        risqueFamille.setTypeAvenant(typeAvenant);
//                        risqueFamille.setIdRisque(risque);
//                        listeRisqueFamille.add(risqueFamille);
//                        
//                    }
//                    risque.setListeRisqueFamille(listeRisqueFamille);
//                    listeRisque.add(risque);
//                }
//                
//            }
//            this.updateTableRisque();
//            this.updateDataTableRisqueFamille();
//        }
//
////        listeImageRisques.clear();
//    }
//    
//    public void chargeFamilleRisqueByRisque(OrclassRisque item, int index) {
////        int index = 0;
//        if (risque == null || risque.getId() == null) {
//            this.setRisque(item);
//            
//        }
//        risque.setIndex(index);
////        if (avenant != null && avenant.getId() != null) {
//        if (item.getListeRisqueFamille().isEmpty()) {
//            listeRisqueFamille = risqueFamilleDao.allRisqueFamilleByPolice(entreprise, police, item);
//            if (listeRisqueFamille.isEmpty()) {
//                risque.setListeRisqueFamille(listeRisqueFamille);
//            }
//            if (Objects.equals(tabShowGroup, Boolean.TRUE) && (risque.getIdGroup() == null || risque.getIdGroup().getId() == null)) {
//                risque.setIdGroup(refGroupeSelect);
//            }
//            for (int i = 0; i < listeRisqueFamille.size(); i++) {
//                if (Objects.equals(listeRisqueFamille.get(i).getRetire_de_la_police(), Boolean.TRUE) && listeRisqueFamille.get(i).getCodeid_retirer() != null) {
//                    listeRisqueFamille.remove(i);
//                }
//            }
//            risqueFamille = new OrclassRisqueFamille();
//            risqueFamille.setIdRisque(item);
//            risqueFamille.setAjouter(Boolean.TRUE);
//            risqueFamille.setRetire_de_la_police(Boolean.FALSE);
//            risqueFamille.setModifier(Boolean.FALSE);
//            risqueFamille.setTypeAvenant(typeAvenant);
//            listeRisqueFamille.add(risqueFamille);
////                risque.getListeRisqueFamille().add(risqueFamille);
////                risque.setListeRisqueFamille(listeRisqueFamille);
////                index = listeRisque.indexOf(item);
////                listeRisque.set(index, risque);
//        } else {
////                if (listRisqueControle.contains(item) == Boolean.FALSE) {
////                    risqueFamille = new OrclassRisqueFamille();
////                    risqueFamille.setIdRisque(risque);
////                    risque.getListeRisqueFamille().add(risqueFamille);
////
////                    this.reverseListeFamilleRique();
////                    listRisqueControle.add(item);
////                }
//            listeRisqueFamille = item.getListeRisqueFamille();
//            risqueFamille = new OrclassRisqueFamille();
//            risqueFamille.setIdRisque(item);
//            risqueFamille.setAjouter(Boolean.TRUE);
//            risqueFamille.setRetire_de_la_police(Boolean.FALSE);
//            risqueFamille.setModifier(Boolean.FALSE);
//            risqueFamille.setTypeAvenant(typeAvenant);
//            listeRisqueFamille.add(risqueFamille);
//        }
//
////        } else {
////            listeRisqueFamille.clear();
////            if (risque != null || risque.getId() != null || risque.getLibelle() != null) {
////                listeRisqueFamille = new ArrayList<>();
////
////                if (item.getListeRisqueFamille().isEmpty()) {
////                    if (item != null && item.getId() != null) {
////                        risque.setListeRisqueFamille((List<OrclassRisqueFamille>) risqueFamilleDao.listeRisqueFamilleByPoliceNotHaveAvenant(entreprise, police));
////                        index = listeRisque.indexOf(item);
////                        listeRisque.set(index, risque);
////                    } else {
////                        if (listRisqueControle.contains(item) == Boolean.FALSE) {
////                            risqueFamille = new OrclassRisqueFamille();
////                            risqueFamille.setIdRisque(risque);
////                            risque.getListeRisqueFamille().add(risqueFamille);
////
////                            this.reverseListeFamilleRique();
////                            listRisqueControle.add(item);
////                        }
////                    }
////
//////                listeRisqueFamille = risque.getListeRisqueFamille();
////                } else {
////                    listeRisqueFamille = item.getListeRisqueFamille();
////
////                }
////                if (listRisqueControle.contains(item) == Boolean.FALSE) {
////                    risqueFamille = new OrclassRisqueFamille();
////                    risqueFamille.setIdRisque(risque);
////                    risque.getListeRisqueFamille().add(risqueFamille);
////
////                    this.reverseListeFamilleRique();
////                    listRisqueControle.add(item);
////                }
////            }
////
////        }
//        this.reverseListeFamilleRique();
////        this.updateTableRisque();
//        this.updateDataTableRisqueFamille();
//        PrimeFaces.current().ajax().update(":form2");
//        PrimeFaces.current().executeScript("PF('familleRisqueDialog').show()");
//    }
//    
//    public void chargeFile() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        
//        try {
//            String path = extContext.getRealPath("") + this.pathFormat;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            GlobalFonctions.downloadFile(file);
//            
//        } catch (Exception e) {
//            
//        }
//        
//        updateFormatFile = DefaultStreamedContent.builder()
//                .name("downloaded_boromir.jpg")
//                .contentType("image/jpg")
//                //                .stream(new InputStream(new File(""+FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/boromir.jpg"))))
//                //                .stream(() -> )
//                .build();
//    }
//    
//    public void chargeCaracteristiqueRubrique() {
////        listePoliceCaracteristique = new ArrayList<>();
////        listeRubriqueCaracteristique = new ArrayList<>();
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        if (categories != null && categories.getIdCategorie() != null && listeRubriqueCaracteristique.isEmpty() && listePoliceCaracteristique.isEmpty()) {
//            listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, categories, StatutCaracteristique.obligatoire);
//            if (listeRubriqueCaracteristique.isEmpty()) {
//                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "caracterist.rubrique.not", null, myLoc);
//                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                return;
//            }
//            for (OrclassCaracteristiques pr : listeRubriqueCaracteristique) {
//                policeCaracteristique = new OrclassPoliceCaracteristique();
//                policeCaracteristique.setIdCaracteristiques(pr);
//                if (pr.getUnite_Caracteristique() != null && pr.getUnite_Caracteristique().getLibelle() != null) {
//                    policeCaracteristique.getIdCaracteristiques().setLibelle(pr.getLibelle() + " " + pr.getUnite_Caracteristique().getLibelle());
//                }
//                listePoliceCaracteristique.add(policeCaracteristique);
//                
//            }
//            if (listePoliceCaracteristique.isEmpty()) {
//                policeCaracteristique = new OrclassPoliceCaracteristique();
//                policeCaracteristique.getIdCaracteristiques().setLibelle("");
//                listePoliceCaracteristique.add(policeCaracteristique);
//                
//            } else {
//                policeCaracteristique = new OrclassPoliceCaracteristique();
//                policeCaracteristique.getIdCaracteristiques().setLibelle("");
//                listePoliceCaracteristique.add(policeCaracteristique);
//                this.reverseListeCaracteristique();
//            }
//            listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotSatutObligatoire(entreprise, categories, rubriqueCategorie.getIdRubrique(), StatutCaracteristique.obligatoire);
//            
//        }
//        
//    }
//    
//    public void chargeLignePolicecaracteristique() {
//        policeCaracteristique = new OrclassPoliceCaracteristique();
//        policeCaracteristique.getIdCaracteristiques().setLibelle("");
//        listePoliceCaracteristique.add(policeCaracteristique);
//        this.reverseListeCaracteristique();
//    }
//    
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//    
//    public void showDetailsRubrique(OrclassCaracteristiques item) {
//        List<OrclassCaracteristiques> listeCaracteristique = new ArrayList<>(policeCaracteristiqueDao.listeCaracteristiqueHavePoliceCaracteristique(entreprise, policeSelect));
//        if (rubriqueCaracteristique.getIdCaracteristiques() == null || rubriqueCaracteristique.getIdCaracteristiques().getId() == null && item != null) {
//            this.getRubriqueCaracteristique().setIdCaracteristiques(item);
//        }
//        if (listeCaracteristique.contains(rubriqueCaracteristique.getIdCaracteristiques()) == Boolean.TRUE) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("This Feature has already been assigned"));
//            return;
//        }
//        policeCaracteristique = new OrclassPoliceCaracteristique();
//        policeCaracteristique.setIdCaracteristiques(rubriqueCaracteristique.getIdCaracteristiques());
//        policeCaracteristique.setAjouter(Boolean.TRUE);
//        policeCaracteristique.setRetire_de_la_police(Boolean.FALSE);
//        policeCaracteristique.setModifier(Boolean.FALSE);
//        policeCaracteristique.setTypeAvenant(typeAvenant);
//        policeCaracteristique.setIdGroup((refGroupeSelect == null || refGroupeSelect.getId() == null) ? null : refGroupeSelect);
//        
//        listePoliceCaracteristique.add(policeCaracteristique);
//        this.updateDataTablePoliceCarzacteristique();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
////                this.reverseListeCaracteristique();
//
//    }
//    
//    public void showDetailsRubriqueGarantie(OrclassGarantie item) {
//        List<OrclassGarantie> listPg = new ArrayList<>();
//        if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//            listPg = policeGarantieDao.listeGarantiesHavePoliceGarantie(entreprise, police, refGroupeSelect);
//            
//        } else {
//            
//            listPg = policeGarantieDao.allGarantiesHavePolice(entreprise, police);
//            
//        }
//        
//        if (rubriqueGarantie.getIdGarantie() == null || rubriqueGarantie.getIdGarantie().getId() == null && item != null) {
//            this.getRubriqueGarantie().setIdGarantie(item);
//        }
//        
//        if (listPg.contains(this.rubriqueGarantie.getIdGarantie()) == Boolean.TRUE) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("THIS GUARANTEE HAS ALREADY BEEN AWARDED ... "));
//            return;
//        }
//        policeGarantie = new OrclassPoliceGarantie();
//        policeGarantie.setIdGarantie(rubriqueGarantie.getIdGarantie());
//        if (rubriqueGarantie.getIdGarantie().getModeCalcul().equals(ModeCalcul.Automatique)) {
//            policeGarantie.setEditer(Boolean.FALSE);
//            policeGarantie.setPrime(this.calculPrimeAutomatiqueGarantie(rubriqueGarantie.getIdGarantie()));
//            policeGarantie.setProrata(policeGarantie.getPrime());
//        } else if (rubriqueGarantie.getIdGarantie().getModeCalcul().equals(ModeCalcul.manuel)) {
//            policeGarantie.setEditer(Boolean.TRUE);
//        }
//        policeGarantie.setAjouter(Boolean.TRUE);
//        policeGarantie.setTypeAvenant(typeAvenant);
//        policeGarantie.setRetire_de_la_police(Boolean.FALSE);
//        policeGarantie.setModifier(Boolean.FALSE);
//        policeGarantie.setIdGroup((refGroupeSelect == null || refGroupeSelect.getId() == null) ? null : refGroupeSelect);
//        listePoliceGarantie.add(policeGarantie);
//        this.updateDataTablePoliceGaranties();
//        this.updateDataTableRubriqueGarantie();
////                this.reverseListeCaracteristique();
//
//    }
//    
//    public void chargeMajoratioDureeByDuree() {
//        if (duree != null && duree.getId() != null && categories != null && categories.getIdCategorie() != null) {
//            majorationDuree = majorationDureeDao.lastRowMajorationDuree(categories, entreprise, duree);
//            if (majorationDuree != null && majorationDuree.getId() != null) {
//                police.setIdMajorationDuree(majorationDuree);
//                PrimeFaces.current().ajax().update(":form1:tabprincipal:tabsecond");
//                PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//            }
//        }
//    }
//    
//    public void removeLigneForDetailPolicePlafond(int index) {
//        OrclassDetailPolicePlafond item = listeDetailPolicePlafond.get(index);
//        if (item != null && item.getId() != null) {
////            int indice = listeDetailPolicePlafond.indexOf(item);
//            item.setRetire_de_la_police(Boolean.TRUE);
//            item.setCodeid_retirer(item.getId());
//            item.setAjouter(Boolean.FALSE);
//            item.setModifier(Boolean.FALSE);
//            item.setIdGroup((refGroupeSelect == null || refGroupeSelect.getId() == null) ? null : refGroupeSelect);
//            item.setTypeAvenant(typeAvenant);
//            listeDetailPolicePlafond.set(index, item);
//            this.updateTabledetailPolicePlafond();
//        }
//        
//    }
//    
//    public void removeLigneForCaracterisquePolice(int index) {
//        OrclassPoliceCaracteristique item = listePoliceCaracteristique.get(index);
//        if (item != null && item.getId() != null) {
////            int indice = listePoliceCaracteristique.indexOf(item);
//            item.setRetire_de_la_police(Boolean.TRUE);
//            item.setCodeid_retirer(item.getId());
//            item.setAjouter(Boolean.FALSE);
//            item.setModifier(Boolean.FALSE);
//            item.setIdGroup((refGroupeSelect == null || refGroupeSelect.getId() == null) ? null : refGroupeSelect);
//            item.setTypeAvenant(typeAvenant);
//            listePoliceCaracteristique.set(index, item);
//            this.updateDataTablePoliceCarzacteristique();
//        }
//        
//    }
//    
//    public void removeLigneForGarantiePolice(int index) {
//        OrclassPoliceGarantie item = listePoliceGarantie.get(index);
//        if (item != null && item.getId() != null) {
////            int indice = listePoliceGarantie.indexOf(item);
//            item.setRetire_de_la_police(Boolean.TRUE);
//            item.setCodeid_retirer(item.getId());
//            item.setAjouter(Boolean.FALSE);
//            item.setModifier(Boolean.FALSE);
//            item.setIdGroup((refGroupeSelect == null || refGroupeSelect.getId() == null) ? null : refGroupeSelect);
//            item.setTypeAvenant(typeAvenant);
//            listePoliceGarantie.set(index, item);
//            this.updateDataTablePoliceGaranties();
//        }
//        
//    }
//    
//    public void updateLigneForDetailPolicePlafond(int index) {
//        OrclassDetailPolicePlafond item = listeDetailPolicePlafond.get(index);
//        if (item != null && item.getId() != null) {
////            int indice = listeDetailPolicePlafond.indexOf(item);
//            item.setTypeAvenant(typeAvenant);
//            item.setModifier(Boolean.TRUE);
//            detailPolicePlafondDao.update(item);
//            listeDetailPolicePlafond.set(index, item);
////            this.updateTabledetailPolicePlafond();
//        }
//    }
//    
//    public void updateLigneFocaracteristiquePolice(int index) {
//        OrclassPoliceCaracteristique item = listePoliceCaracteristique.get(index);
//        if (item != null && item.getId() != null) {
////            int indice = listePoliceCaracteristique.indexOf(item);
//            item.setTypeAvenant(typeAvenant);
//            listePoliceCaracteristique.set(index, item);
////            this.updateDataTablePoliceCarzacteristique();
//        }
//    }
//    
//    public void removeLigneForRisque(int index) {
//        OrclassRisque item = listeRisque.get(index);
//        if (item != null && item.getId() != null) {
////            int indice = listeRisque.indexOf(item);
//            item.setRetire_de_la_police(Boolean.TRUE);
//            item.setCodeid_retirer(item.getId());
//            item.setAjouter(Boolean.FALSE);
//            item.setModifier(Boolean.FALSE);
//            item.setTypeAvenant(typeAvenant);
//            item.setIdGroup((refGroupeSelect == null || refGroupeSelect.getId() == null) ? null : refGroupeSelect);
//            listeRisque.set(index, item);
//            this.updateTableRisque();
//        }
//        
//    }
//    
//    public void removeLigneForFamilleRisque(int index) {
//        OrclassRisqueFamille item = listeRisqueFamille.get(index);
//        if (item != null && item.getId() != null) {
//            
//            item.setRetire_de_la_police(Boolean.TRUE);
//            item.setCodeid_retirer(item.getId());
//            item.setTypeAvenant(typeAvenant);
//            
//            listeRisqueFamille.set(index, item);
//            this.updateDataTableRisqueFamille();
//        }
//        
//    }
//    
//    public void updateLigneForRisque(int index) {
//        OrclassRisque item = listeRisque.get(index);
//        if (item != null && item.getId() != null) {
////            int indice = listeRisque.indexOf(item);
//            item.setModifier(Boolean.TRUE);
//            item.setAjouter(Boolean.FALSE);
//            item.setTypeAvenant(typeAvenant);
//            risqueDao.update(item);
//            listeRisque.set(index, item);
//
////            this.updateTableRisque();
//        }
//    }
//    
//    public void updateLigneForRisqueFamille(int index) {
//        OrclassRisqueFamille item = listeRisqueFamille.get(index);
//        if (item != null && item.getId() != null) {
////            int indice = listeRisqueFamille.indexOf(item);
//            item.setTypeAvenant(typeAvenant);
//            item.setModifier(Boolean.TRUE);
//            risqueFamilleDao.update(item);
//            listeRisqueFamille.set(index, item);
//
////            this.updateTableRisque();
//        }
//    }
//    
//    public List<SelectItem> getModeCalculDetailPlafondMaladie() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (ModeCalculDetailMaladie dm : ModeCalculDetailMaladie.values()) {
//            items.add(new SelectItem(dm, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, dm.name(), null, myLoc)));
//            
//        }
//        return items;
//    }
//    
//    public List<SelectItem> getTypeDetailPlafondMaladie() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (TypeDetailMaladie dm : TypeDetailMaladie.values()) {
//            items.add(new SelectItem(dm, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, dm.name(), null, myLoc)));
//            
//        }
//        return items;
//    }
//    
//    public void chargeFractionnementCategoriesByFractionnement() {
//        if (fractionnement != null && fractionnement.getId() != null && categories != null && categories.getIdCategorie() != null) {
//            fractionnementCategories = fractionnementCategoriesDao.lastRowFractionnement(categories, entreprise, fractionnement);
//            if (fractionnementCategories != null && fractionnementCategories.getId() != null) {
//                police.setIdFractionnementCategories(fractionnementCategories);
//                PrimeFaces.current().ajax().update(":form1:tabprincipal:tabsecond");
//                PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//            }
//        }
//    }
//    
//    public void chargeFractionnementByCategories() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            listeFractionnement = fractionnementCategoriesDao.listFractionnementHaveCategories(categories, entreprise);
//            
//        }
//    }
//    
//    public void chargeCommissionApporteurByCategorie() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            listeCommission_Prime_Apporteur = commission_Prime_ApporteurDao.getPrimeCommissionByCategories(categories, entreprise);
//        }
//    }
//    
//    @PostConstruct
//    void initialiseSession() {
//        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//        if (entreprise == null) {
//            entreprise = (OrclassEntreprises) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(GlobalFonctions.ENTREPRISE_ACTIF);
//            
//        }
//        
//        user = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
//        /*
//        verifions a quel intemediaire il appartien
//         */
//        if (Objects.equals(user.getAllAccessForIntermediaire(), Boolean.TRUE)) {
//            intermediaires = new OrclassIntermediaires();
//            listeIntermediaires = intermediairesDao.listeIntermediaireByEntreprise(entreprise);
//            branches = branchesDao.findEntityHavingValue("libelle", LibelleBranche.sante);
//            lisCategoriesBySante = categoriesDao.listeCategorieByBranche(branches, entreprise);
//            
//            PrimeFaces.current().executeScript("PF('agence').show();");
//        } else {
//            intermediaires = user.getIdIntermediaire();
//            
//        }
//        
//        if (intermediaires != null && intermediaires.getIdIntermediaire() != null && (Objects.equals(user.getAllAccessForIntermediaire(), Boolean.FALSE) || user.getAllAccessForIntermediaire() == null)) {
//            branches = branchesDao.findEntityHavingValue("libelle", LibelleBranche.sante);
//            if (branches != null && branches.getIdBranche() != null) {
//                lisCategoriesBySante = categoriesDao.listeCategorieByBranche(branches, entreprise);
//                suspensionBrancheIntemediaire = suspensionBrancheIntemediaireDao.finKey(intermediaires, entreprise, branches);
//                if (suspensionBrancheIntemediaire == null) {
//                    suspensionBrance = Boolean.FALSE;
//                    listSuspensionCategorieIntermediaire = suspensionCategorieIntermediaireDao.listeBrancheSuspendueForIntermediaire(intermediaires, entreprise, branches);
//                    if (listSuspensionCategorieIntermediaire.isEmpty() && suspensionBrancheIntemediaire != null) {
//                        lisCategoriesBySante.clear();
//                    } else {
//                        lisCategoriesBySante.removeAll(listSuspensionCategorieIntermediaire);
//                    }
//                } else {
//                    suspensionBrance = Boolean.TRUE;
//                    listSuspensionCategorieIntermediaire = suspensionCategorieIntermediaireDao.listeBrancheSuspendueForIntermediaire(intermediaires, entreprise, branches);
//                    if (listSuspensionCategorieIntermediaire.isEmpty() && suspensionBrancheIntemediaire != null) {
//                        lisCategoriesBySante.clear();
//                    } else {
//                        lisCategoriesBySante.removeAll(listSuspensionCategorieIntermediaire);
//                    }
//                    
//                }
//            }
//        }
//        if ((user.getAllAccessForIntermediaire() == null || (Objects.equals(user.getAllAccessForIntermediaire(), Boolean.FALSE))) && intermediaires != null && intermediaires.getIdIntermediaire() != null) {
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraValideByAgence(intermediaires, entreprise, branches);
//        }
//        
//        listeVille = (List<OrclassVille>) villeDao.findAll();
//        listeTarif = (List<OrclassTypeTarif>) typeTarifDao.findAll();
//        listeReduction = (List<OrclassReduction>) reductionDao.findAll();
//        listeConvention = (List<OrclassConvention>) conventionDao.findAll();
//        listeDevise = (List<OrclassDevise>) deviseDao.findAll();
//        listeTimbreDimension = (List<OrclasseTimbreDimension>) timbreDimensionDao.findAll();
//        listeExoneration = (List<OrclassExoneration>) exonerationDao.findAll();
//        listeApporteur = commission_Prime_ApporteurDao.listeApporteurHaveCommissionByEntreprise(entreprise);
//        this.listeTypeAvenant = (List<Orclass_TypeAvenant>) typeAvenantDao.listeTypeAvenantByCompagnie(entreprise);
//        devise = deviseDao.findEntityHavingValue("code", "CFA");
//
//
//        /*
//        charge les element  a partir du fichier Excell
//         */
//        if (activiteDao.findAll().isEmpty()) {
//            this.initialActiviteByExcell();
//        } else {
//            listActivite = (List<OrclassActivite>) activiteDao.findAll();
//        }
//        
//        if (qualiteDao.findAll().isEmpty()) {
//            this.initialQualiteByExcell();
//        } else {
//            listQualite = (List<OrclassQualite>) qualiteDao.findAll();
//        }
//        
//        if (professionDao.findAll() == null || professionDao.findAll().isEmpty()) {
//            this.initialProfessionByExcell();
//            this.initialProfessionByExcell2();
//        } else {
//            listProfessionnel = (List<OrclassProfession>) professionDao.findAll();
//        }
//        if (paysDao.findAll().isEmpty()) {
//            this.initialPaysByExcell();
//        } else {
//            if (paysDao.findAll().size() < 100) {
//                this.initialPaysByExcell();
//            } else {
//                listPays = (List<Pays>) paysDao.findAll();
//            }
//        }
////        if (!policeDao.findAll().isEmpty() && propositionDao.findAll().isEmpty()) {
////            OrclassProposition prop = null;
////            List<OrclassPolice> allPolice = new ArrayList<>(policeDao.findAllEntitiesHavingValue("idEntreprises", entreprise));
////            for (OrclassPolice p : allPolice) {
////                prop = propositionDao.recuperLapoliceProser(p.getPolicePK().getNumero_polic());
////                if (prop == null) {
////                    prop = new OrclassProposition();
////                    prop.setPolice_proposition(p.getPolicePK().getNumero_polic());
////                    propositionDao.create(prop);
////                }
////            }
////           
////            
////        }
//        this.updateDataTableContrat();
//        this.updateTableImageRisque();
//        
//    }
//    
//    public void chargeProduitNotSupensionByUserHaveAccesAllagence() {
//        suspensionBrancheIntemediaire = suspensionBrancheIntemediaireDao.finKey(intermediaires, entreprise, branches);
//        if (suspensionBrancheIntemediaire == null) {
//            suspensionBrance = Boolean.FALSE;
//            listSuspensionCategorieIntermediaire = suspensionCategorieIntermediaireDao.listeBrancheSuspendueForIntermediaire(intermediaires, entreprise, branches);
//            if (listSuspensionCategorieIntermediaire.isEmpty() && suspensionBrancheIntemediaire != null) {
//                lisCategoriesBySante.clear();
//            } else {
//                lisCategoriesBySante.removeAll(listSuspensionCategorieIntermediaire);
//            }
//        } else {
//            suspensionBrance = Boolean.TRUE;
//            listSuspensionCategorieIntermediaire = suspensionCategorieIntermediaireDao.listeBrancheSuspendueForIntermediaire(intermediaires, entreprise, branches);
//            if (listSuspensionCategorieIntermediaire.isEmpty() && suspensionBrancheIntemediaire != null) {
//                lisCategoriesBySante.clear();
//            } else {
//                lisCategoriesBySante.removeAll(listSuspensionCategorieIntermediaire);
//            }
//            
//        }
//    }
//    
//    public void initialActiviteByExcell() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassActivite> listActivites = new ArrayList<>();
//        OrclassActivite ref;
//        OrclassEntreprises en = null;
//        listActivite.clear();
//        try {
//            
//            String path = extContext.getRealPath("") + this.activitExcell;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listActivites = serviceExcell.recuperListActiviteByExcell(targetStream, path);
//            if (listActivites == null) {
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//                return;
//            }
//            for (OrclassActivite act : listActivites) {
//                ref = activiteDao.findEntityHavingValue("code", act.getCode());
//                if (ref == null) {
//                    ref = new OrclassActivite();
//                    ref.setCode(act.getCode());
//                    ref.setLibelle(act.getLibelle());
//                    activiteDao.create(ref);
//                    listActivite.add(ref);
//                    
//                }
//                
//            }
//        } catch (IOException e) {
//            System.out.println("controllers.apd.ProductionController.initialActiviteByExcell() ...une erreur inattandue.." + e.getMessage());
//            
//        }
//    }
//    
//    public void initialQualiteByExcell() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassQualite> listQualites = new ArrayList<>();
//        OrclassQualite ref;
//        OrclassEntreprises en = null;
//        listQualite.clear();
//        try {
//            
//            String path = extContext.getRealPath("") + this.qualiteExcell;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listQualites = serviceExcell.recuperListQualiteByExcell(targetStream, path);
//            if (listQualites == null) {
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//                return;
//            }
//            for (OrclassQualite q : listQualites) {
//                ref = qualiteDao.findEntityHavingValue("code", q.getCode());
//                if (ref == null) {
//                    ref = new OrclassQualite();
//                    ref.setCode(q.getCode().replace(".0", ""));
//                    ref.setLibelle(q.getLibelle());
//                    qualiteDao.create(ref);
//                    listQualite.add(ref);
//                    
//                }
//                
//            }
//        } catch (IOException e) {
//            System.out.println("controllers.apd.ProductionController.initialQualiteByExcell() ...une erreur inattandue.." + e.getMessage());
//            
//        }
//    }
//    
//    public void initialProfessionByExcell() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassProfession> listProfessionnels = new ArrayList<>();
//        OrclassProfession ref;
//        OrclassEntreprises en = null;
//        listProfessionnel.clear();
//        try {
//            
//            String path = extContext.getRealPath("") + this.prof1Excell;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listProfessionnels = serviceExcell.recuperListProfessionsByExcell(targetStream, path);
//            if (listProfessionnels == null) {
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//            }
//            for (OrclassProfession p : listProfessionnels) {
//                ref = professionDao.findEntityHavingValue("code", p.getCode());
//                if (ref == null) {
//                    ref = new OrclassProfession();
//                    ref.setCode(p.getCode().replace(".0", ""));
//                    ref.setLibelle(p.getLibelle());
//                    professionDao.create(ref);
//                    listProfessionnel.add(ref);
//                    
//                }
//                
//            }
//        } catch (IOException e) {
//            System.out.println("controllers.apd.ProductionController.initialProfessionByExcell() ...une erreur inattandue.." + e.getMessage());
//            
//        }
//    }
//    
//    public void initialProfessionByExcell2() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<OrclassProfession> listProfessionnels = new ArrayList<>();
//        OrclassProfession ref;
//        OrclassEntreprises en = null;
//        try {
//            
//            String path = extContext.getRealPath("") + this.prof2Excell;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listProfessionnels = serviceExcell.recuperListProfessionsByExcell(targetStream, path);
//            if (listProfessionnels == null) {
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//            }
//            for (OrclassProfession p : listProfessionnels) {
//                ref = professionDao.findEntityHavingValue("code", p.getCode());
//                if (ref == null) {
//                    ref = new OrclassProfession();
//                    ref.setCode(p.getCode().replace(".0", ""));
//                    ref.setLibelle(p.getLibelle());
//                    professionDao.create(ref);
//                    listProfessionnel.add(ref);
//                    
//                }
//                
//            }
//        } catch (IOException e) {
//            System.out.println("controllers.apd.ProductionController.initialProfessionByExcell2() ...une erreur inattandue.." + e.getMessage());
//            
//        }
//    }
//    
//    public void initialPaysByExcell() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        List<Pays> listPayss = new ArrayList<>();
//        Pays ref;
//        OrclassEntreprises en = null;
//        this.listPays.clear();
//        try {
//            
//            String path = extContext.getRealPath("") + this.paysExcell;
//            File file = new File(path);
//            InputStream targetStream = new FileInputStream(file);
//            listPayss = serviceExcell.recuperListPaysByExcell(targetStream, path);
//            if (listPayss == null) {
//                System.err.println("Une erruer est survenue dans l extraction des donnes sur le fichier Excell");
//                return;
//            }
//            for (Pays p : listPayss) {
//                ref = paysDao.findEntityHavingValue("code", p.getCode());
//                if (ref == null) {
//                    ref = new Pays();
//                    ref.setCode(p.getCode());
//                    ref.setLibelle(p.getLibelle());
//                    paysDao.create(ref);
//                    listPays.add(ref);
//                    
//                }
//                
//            }
//        } catch (IOException e) {
//            System.out.println("controllers.apd.ProductionController.initialPaysByExcell() ...une erreur inattandue.." + e.getMessage());
//            
//        }
//    }
//    
//    public void chargeByCategorieSelct() throws InterruptedException {
//        if (user != null && user.getIdUtilisateur() != null && Objects.equals(user.getAllAccessForIntermediaire(), Boolean.TRUE)) {
//            if (policeSelect != null && policeSelect.getId() != null && policeSelect.getNumero_police() != null && policeSelect.getIdIntermediaire() != null) {
//                PrimeFaces.current().executeScript("PF('agence').hide();");
//            } else {
//                if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null) {
//                    PrimeFaces.current().executeScript("PF('agence').hide();");
//                    this.chargerInformationGeneralByCategorieSelectionner();
//                    
//                } else {
//                    PrimeFaces.current().executeScript("PF('agence').show();");
//                    
//                }
//                
//            }
////            PrimeFaces.current().executeScript("PF('agence').show();");
//
//        } else {
//            this.chargerInformationGeneralByCategorieSelectionner();
//        }
//
////          this.updateDataTableContrat();
////            this.updateDataTableRisqueFamille();
////            this.updateTablePrestation();
////            this.updateTabledetailPolicePlafond();
////            this.updateDataTablePoliceGaranties();
////            this.updateDataTableRubriqueGarantie();
////            PrimeFaces.current().ajax().update(":form1");
////            PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//    
//    public void refrescheTableContra() {
//        this.updateDataTableContrat();
//        PrimeFaces.current().ajax().update(":form2");
//    }
//    
//    public void chargerInformationGeneralByCategorieSelectionner() throws InterruptedException {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        //get default locale
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        BigInteger valeur = BigInteger.ZERO;
//        summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "registre.non.creer", null, myLoc);
//        msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//        OrclassProposition police_propose = null;
//        BigInteger proposition = BigInteger.ZERO;
////        listeIntermediaires = new ArrayList();
//        if (categories != null && categories.getIdCategorie() != null) {
//            // charger les apporteurs liers au produit selectionner
//            this.setNumero_police(BigInteger.ZERO);
////            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
//            // recuperation du numero de police
//
//            if (policeDao.findAll().isEmpty()) {
//                if (!propositionDao.findAll().isEmpty()) {
//                    for (OrclassProposition pr : propositionDao.findAll()) {
//                        
//                        propositionDao.delete(pr);
//                    }
//                }
//                elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
//                if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null) {
//                    production = elt_Categorie_Compagnie.getIdRegistreProduction();
//                    if (production == null || production.getId() == null) {
//                        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
////                        Thread.sleep(10000);
//                        return;
//                    } else {
//                        this.setNumero_police(production.getNum_debut().negate());
//                        police_propose = propositionDao.recuperLapoliceProser(numero_police, intermediaires, entreprise, categories);
//                        if (police_propose == null) {
//                            police_propose = new OrclassProposition();
//                            police_propose.setPolice_proposition(numero_police);
//                            police_propose.setIdCategories(categories);
//                            police_propose.setIdEntreprises(entreprise);
//                            police_propose.setIdIntermediaire(intermediaires);
//                            propositionDao.create(police_propose);
//                            
//                        } else {
//                            proposition = numero_police;
//                            while (propositionDao.recuperLapoliceProser(proposition, intermediaires, entreprise, categories) != null) {
//                                proposition = proposition.add(BigInteger.ONE.negate());
//                            }
//                            police_propose = propositionDao.recuperLapoliceProser(proposition, intermediaires, entreprise, categories);
//                            if (police_propose == null) {
//                                police_propose = new OrclassProposition();
//                                police_propose.setPolice_proposition(proposition);
//                                police_propose.setIdCategories(categories);
//                                police_propose.setIdEntreprises(entreprise);
//                                police_propose.setIdIntermediaire(intermediaires);
//                                propositionDao.create(police_propose);
//                                this.setNumero_police(proposition);
//                                
//                            }
//                            
//                        }
//                        
//                        if (elt_Categorie_Compagnie.getRisque() == null) {
//                            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "risque.non.definir", null, myLoc);
//                            msgdetail = " " + LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "caracteris.cat.non.definir", null, myLoc);
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                            
//                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                            return;
//                        }
//                    }
//                    
//                }
//                
//            } else {
////                for (OrclassProposition pr : propositionDao.findAll()) {
////                    if (Objects.equals(policeDao.policehaveNumepolice(entreprise, intermediaires, categories, pr.getPolice_proposition()), Boolean.FALSE)) {
////                        propositionDao.delete(pr);
////                    }
////                }
//                valeur = policeDao.retourneNumeroPoliceByAgenceForCategorie(intermediaires, categories, entreprise);
//                if (valeur == BigInteger.ZERO) {
//                    elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
//                    if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null) {
//                        production = elt_Categorie_Compagnie.getIdRegistreProduction();
//                        if (production == null || production.getId() == null) {
//                            
//                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                            return;
//                        } else {
//                            valeur = production.getNum_debut();
//                            this.setNumero_police(valeur.negate());
////                             this.setNumero_police(production.getNum_debut().negate());
//                            police_propose = propositionDao.recuperLapoliceProser(numero_police, intermediaires, entreprise, categories);
//                            if (police_propose == null) {
//                                police_propose = new OrclassProposition();
//                                police_propose.setPolice_proposition(numero_police);
//                                police_propose.setIdCategories(categories);
//                                police_propose.setIdIntermediaire(intermediaires);
//                                police_propose.setIdEntreprises(entreprise);
//                                propositionDao.create(police_propose);
//                                
//                            } else {
//                                proposition = numero_police;
//                                while (propositionDao.recuperLapoliceProser(proposition, intermediaires, entreprise, categories) != null) {
//                                    proposition = proposition.add(BigInteger.ONE.negate());
//                                }
//                                this.setNumero_police(proposition);
//                            }
//                            if (elt_Categorie_Compagnie.getRisque() == null) {
//                                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "risque.non.definir", null, myLoc);
//                                msgdetail = " " + LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "caracteris.cat.non.definir", null, myLoc);
//                                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                                
//                                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                                return;
//                            }
////                            return;
//                        }
//                    } else {
//                        summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "risque.non.definir", null, myLoc);
//                        msgdetail = " " + LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "caracteris.cat.non.definir", null, myLoc);
//                        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                        
//                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                        return;
//                    }
//                    
//                } else {
//                    if (valeur.signum() == -1) {
//                        valeur = valeur.add(BigInteger.ONE.negate());
////                        valeur = valeur.negate();
//                    }
//                    police = policeDao.finKey(intermediaires, valeur, entreprise, categories);
//                    if (police != null && police.getId() != null) {
//                        proposition = police.getNumero_police();
//                        while (policeDao.finKey(intermediaires, proposition, entreprise, categories) != null) {
//                            proposition = proposition.add(BigInteger.ONE.negate());
//                        }
//                        police_propose = propositionDao.recuperLapoliceProser(proposition, intermediaires, entreprise, categories);
//                        if (police_propose == null) {
//                            police_propose = new OrclassProposition();
//                            police_propose.setPolice_proposition(proposition);
//                            police_propose.setIdCategories(categories);
//                            police_propose.setIdIntermediaire(intermediaires);
//                            police_propose.setIdEntreprises(entreprise);
//                            propositionDao.create(police_propose);
//                            this.setNumero_police(proposition);
//                            
//                        } else {
//                            while (propositionDao.recuperLapoliceProser(proposition, intermediaires, entreprise, categories) != null) {
//                                proposition = proposition.add(BigInteger.ONE.negate());
//                            }
//                            police_propose = propositionDao.recuperLapoliceProser(proposition, intermediaires, entreprise, categories);
//                            if (police_propose == null) {
//                                police_propose = new OrclassProposition();
//                                police_propose.setPolice_proposition(proposition);
//                                police_propose.setIdCategories(categories);
//                                police_propose.setIdIntermediaire(intermediaires);
//                                police_propose.setIdEntreprises(entreprise);
//                                propositionDao.create(police_propose);
//                                this.setNumero_police(proposition);
//                                
//                            }
//                            
//                        }
////                        valeur = valeur.add(BigInteger.ONE);
////                        police = policeDao.finKey(intermediaires, valeur, entreprise);
////                        if (police != null && police.getPolicePK() != null) {
////                            summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "numero.police.non.valide", null, myLoc);
////                            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
////                            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
////                            return;
////                        }
//                        elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
//                        if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null) {
//                            production = elt_Categorie_Compagnie.getIdRegistreProduction();
//                            if (production.getNum_fin().compareTo(this.numero_police) == -1) {
//                                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "numero.max.police.atteind", null, myLoc);
//                                
//                                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                                return;
//                            }
//                            if (elt_Categorie_Compagnie.getRisque() == null) {
//                                summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "risque.non.definir", null, myLoc);
//                                msgdetail = " " + LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "caracteris.cat.non.definir", null, myLoc);
//                                ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                                
//                                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, "..." + msgdetail));
//                                return;
//                            }
//                        }
////                        this.setNumero_police(valeur);
//                        police = new OrclassPolice();
//                        
//                    } else {
//                        police_propose = propositionDao.recuperLapoliceProser(valeur, intermediaires, entreprise, categories);
//                        if (police_propose == null) {
//                            police_propose = new OrclassProposition();
//                            police_propose.setPolice_proposition(valeur);
//                            police_propose.setIdCategories(categories);
//                            police_propose.setIdEntreprises(entreprise);
//                            police_propose.setIdIntermediaire(intermediaires);
//                            propositionDao.create(police_propose);
//                            this.setNumero_police(valeur);
//                            
//                        } else {
//                            proposition = valeur;
//                            
//                            while (propositionDao.recuperLapoliceProser(proposition, intermediaires, entreprise, categories) != null) {
//                                proposition = proposition.add(BigInteger.ONE.negate());
//                            }
//                            police_propose = propositionDao.recuperLapoliceProser(proposition, intermediaires, entreprise, categories);
//                            if (police_propose == null) {
//                                police_propose = new OrclassProposition();
//                                police_propose.setPolice_proposition(proposition);
//                                police_propose.setIdCategories(categories);
//                                police_propose.setIdEntreprises(entreprise);
//                                police_propose.setIdIntermediaire(intermediaires);
//                                propositionDao.create(police_propose);
//                                this.setNumero_police(proposition);
//                                
//                            }
//                        }
//                    }
//                    
//                }
//                
//            }
//            if (police == null) {
//                police = new OrclassPolice();
//
////                PrimeFaces.current().ajax().update(":form1");
//            }
//            police.setDate_effet(new Date());
//            police.setDate_saisie(new Date());
//            // etablir la date d echeance en fonction de la date d effet
//            police.setDate_echeance(this.chargeDateEcheanceByDateEffet(police.getDate_effet()));
//
////            //verification normal final
////            proposition = this.getNumero_police();
////            while (policeDao.policehaveNumepolice(proposition) != null) {
////                proposition = proposition.add(BigInteger.ONE.negate());
////            }
////            police_propose = propositionDao.recuperLapoliceProser(proposition);
////            if (police_propose == null) {
////                police_propose = new OrclassProposition();
////                police_propose.setPolice_proposition(proposition);
////                propositionDao.create(police_propose);
////                this.setNumero_police(proposition);
////
////            }
//            police.setIdTypeTarif(typeTarifDao.findEntityHavingValue("libelle", "Normal"));
//            /*
//            Charge les elements de travail 
//             */
//            if (devise != null && devise.getId() != null) {
//                police.setIdDevise(devise);
//            }
//            this.choixTabView();
//            listeMajorationDurees.clear();
//            listePlafondMaladie.clear();
//            listePrestation.clear();
//            listeRubriqueCategorie.clear();
//            listeRubriquePrestation.clear();
//            listeDurees.clear();
//            listeDetailPolicePlafond.clear();
//            listePoliceGarantie.clear();
//            listeRubriqueGarantie.clear();
//            plafondMaladie = new OrclassPlafondMaladie();
//            rubriqueGarantie = new OrclassRubriqueGarantie();
//            policeGarantie = new OrclassPoliceGarantie();
//            this.updateDataTableRisqueFamille();
//            this.updateTablePrestation();
//            this.updateTabledetailPolicePlafond();
//            this.updateDataTablePoliceGaranties();
//            this.updateDataTableRubriqueGarantie();
//
////            if (numero_police.intValue() != 0) {
////                numero_police = numero_police.negate();
////            }
//        }
//    }
//    
//    public Date chargeDateEcheanceByDateEffet(Date effet) {
//        return IdleDate.dateEcheanceByDateEffetForOneYear(effet);
//    }
//    
//    public void chargeDateEcheance() {
//        if (police.getDate_effet() != null) {
//            police.setDate_echeance(this.chargeDateEcheanceByDateEffet(police.getDate_effet()));
//            PrimeFaces.current().ajax().update(":form1");
//        }
//    }
//    
//    public void chargeAgenceSelect() throws InterruptedException {
//        if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null) {
//            this.setIntermediaires(intermediairesSelect);
//            chargeProduitNotSupensionByUserHaveAccesAllagence();
//            this.chargerInformationGeneralByCategorieSelectionner();
//            PrimeFaces.current().executeScript("PF('agence').hide();");
//            PrimeFaces.current().ajax().update(":form1:tabprincipal");
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraByAgenceForAllEtat(intermediaires, entreprise);
//            this.updateDataTableContrat();
//        } else {
//            PrimeFaces.current().executeScript("PF('agence').show();");
//        }
//        
//    }
//    
//    public void choixTabView() {
//        OrclassElt_Categorie_Compagnie elt = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
//        if (elt == null || elt.getRisque() == null) {
//            return;
//        }
//        switch (elt.getRisque()) {
//            case famille:
//                this.setTabShowFamille(Boolean.TRUE);
//                this.setTabShowGroup(Boolean.FALSE);
//                this.setTabShowIndividuel(Boolean.FALSE);
//                
//                break;
//            case individuel:
//                this.setTabShowFamille(Boolean.FALSE);
//                this.setTabShowGroup(Boolean.FALSE);
//                this.setTabShowIndividuel(Boolean.TRUE);
//                if (user != null && user.getIdUtilisateur() != null && Objects.equals(user.getAllAccessForIntermediaire(), Boolean.TRUE) && policeSelect != null && policeSelect.getId() != null) {
//                    if (policeSelect != null && policeSelect.getId() != null && policeSelect.getNumero_police() != null && policeSelect.getIdIntermediaire() != null) {
//                        PrimeFaces.current().executeScript("PF('agence').hide();");
//                    } else {
//                        if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null) {
//                            PrimeFaces.current().executeScript("PF('agence').hide();");
//                        } else {
//                            PrimeFaces.current().executeScript("PF('agence').show();");
//                        }
//                        
//                    }
//                    
//                }
//                break;
//            case groupe:
//                this.setTabShowFamille(Boolean.FALSE);
//                this.setTabShowGroup(Boolean.TRUE);
//                this.setTabShowIndividuel(Boolean.FALSE);
//                listeRefGroup = (List<OrclasseRefGroupe>) refGroupeDao.findAll();
//                if (user != null && user.getIdUtilisateur() != null && Objects.equals(user.getAllAccessForIntermediaire(), Boolean.TRUE)) {
////                    PrimeFaces.current().executeScript("PF('agence').show();");
//                    if (policeSelect != null && policeSelect.getId() != null && policeSelect.getId() != null && policeSelect.getIdIntermediaire() != null) {
//                        PrimeFaces.current().executeScript("PF('agence').hide();");
//                    } else {
//                        if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null) {
//                            PrimeFaces.current().executeScript("PF('agence').hide();");
//                        } else {
//                            PrimeFaces.current().executeScript("PF('agence').show();");
//                        }
//                    }
//                    
//                }
//                break;
//            default:
//                this.setTabShowFamille(Boolean.FALSE);
//                this.setTabShowGroup(Boolean.FALSE);
//                this.setTabShowIndividuel(Boolean.FALSE);
//            
//        }
//    }
//    
//    public void choixTabView(OrclassElt_Categorie_Compagnie elt) {
////        OrclassElt_Categorie_Compagnie elt = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
//        if (elt == null || elt.getRisque() == null) {
//            return;
//        }
//        switch (elt.getRisque()) {
//            case famille:
//                this.setTabShowFamille(Boolean.TRUE);
//                this.setTabShowGroup(Boolean.FALSE);
//                this.setTabShowIndividuel(Boolean.FALSE);
//                
//                break;
//            case individuel:
//                this.setTabShowFamille(Boolean.FALSE);
//                this.setTabShowGroup(Boolean.FALSE);
//                this.setTabShowIndividuel(Boolean.TRUE);
//                
//                break;
//            case groupe:
//                this.setTabShowFamille(Boolean.FALSE);
//                this.setTabShowGroup(Boolean.TRUE);
//                this.setTabShowIndividuel(Boolean.FALSE);
//                
//                break;
//            default:
//                this.setTabShowFamille(Boolean.FALSE);
//                this.setTabShowGroup(Boolean.FALSE);
//                this.setTabShowIndividuel(Boolean.FALSE);
//            
//        }
//    }
//    
//    public List<SelectItem> getSexe() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (Sexe s : Sexe.values()) {
//            items.add(new SelectItem(s, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, s.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getContrats() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (Contrat s : Contrat.values()) {
//            items.add(new SelectItem(s, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, s.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getNatureContrats() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (NatureContrat s : NatureContrat.values()) {
//            items.add(new SelectItem(s, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, s.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getEmissions() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (TypeQuittance em : TypeQuittance.values()) {
//            items.add(new SelectItem(em, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, em.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getModeCalcul() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (ModeCalcul s : ModeCalcul.values()) {
//            items.add(new SelectItem(s, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, s.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getLeinParente() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (LienParente s : LienParente.values()) {
//            items.add(new SelectItem(s, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, s.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public List<SelectItem> getTypePiece() {
//        List<SelectItem> items = new ArrayList<>();
//        
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        for (TypePieces s : TypePieces.values()) {
//            items.add(new SelectItem(s, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, s.name(), null, myLoc)));
//        }
//        
//        return items;
//    }
//    
//    public BigDecimal caculTotalPrimeByForQuittance() {
//        BigDecimal montant = BigDecimal.ZERO;
//        if (police != null && police.getId() != null) {
//            for (OrclassPoliceGarantie pg : policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, police)) {
//                if (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null) {
//                    continue;
//                }
//                if (pg.getProrata() != null && pg.getProrata().intValue() != 0 && Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                    montant = montant.add(pg.getProrata());
//                    
//                }
//                
//            }
//            for (OrclassPoliceGarantie pgg : listePoliceGarantie) {
//                if (pgg.getIdGarantie() == null || pgg.getIdGarantie().getId() == null) {
//                    continue;
//                }
//                
//                if (pgg.getId() == null && pgg.getIdGarantie() != null && pgg.getIdGarantie().getId() != null && pgg.getProrata() != null && pgg.getProrata().intValue() != 0 && Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                    montant = montant.add(pgg.getProrata());
//                    
//                }
//            }
//            
//        } else if (police == null || police.getId() == null) {
//            for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//                if (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null) {
//                    continue;
//                }
//                if (pg.getProrata() != null && pg.getProrata().intValue() != 0 && Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                    montant = montant.add(pg.getProrata());
//                    
//                }
//                
//            }
//            
//        }
//        
//        return montant;
//    }
//    
//    public void changeValueTva() {
////        if (police != null && police.getId() != null) {
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        
//        if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//            quitance.setPrimeNette(this.caculTotalPrimeByForQuittance());
//            
//            try {
//                quitance.setTaxePrime((this.caculTotalPrimeByForQuittance().multiply(this.getTva())).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//            } catch (Exception e) {
//                quitance.setTaxePrime(BigDecimal.ZERO);
//            }
//            
//        } else {
//            quitance.setPrimeNette(this.totalProrata);
//            try {
//                quitance.setTaxePrime((this.totalProrata.multiply(this.getTva())).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//            } catch (Exception e) {
//                quitance.setTaxePrime(BigDecimal.ZERO);
//            }
////                    quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//        }
//        quitance.setMontant_Accessoire(police.getMontantaccessoir());
////                quitance.setTypQuittance(TypeQuittance.emmission);
//
//        try {
//            quitance.setPrimeTaxe(quitance.getPrimeNette().add(quitance.getTaxePrime()));
//        } catch (Exception e) {
//            quitance.setPrimeTaxe(BigDecimal.ZERO);
//        }
//        try {
//            quitance.setTaxeAccessoir((quitance.getMontant_Accessoire().multiply(this.getTva())).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//        } catch (Exception e) {
//            quitance.setTaxeAccessoir(BigDecimal.ZERO);
//        }
//        try {
//            quitance.setAccessoirTaxe(quitance.getMontant_Accessoire().add(quitance.getTaxeAccessoir()));
//        } catch (Exception e) {
//            quitance.setAccessoirTaxe(BigDecimal.ZERO);
//        }
//        
//        quitance.setTotalTaxes(quitance.getTaxePrime().add(quitance.getTaxeAccessoir()));
//        quitance.setTimbreGradue(BigDecimal.ZERO);
//        quitance.setTimbreDimension(BigDecimal.ZERO);
//        try {
//            if (quitance.getMontant_Accessoire() == null) {
//                quitance.setMontant_Accessoire(BigDecimal.ZERO);
//                quitance.setTaxeAccessoir(BigDecimal.ZERO);
//            }
//            if (quitance.getTimbreDimension() == null) {
//                quitance.setTimbreDimension(BigDecimal.ZERO);
//            }
//            if (quitance.getTimbreGradue() == null) {
//                quitance.setTimbreGradue(BigDecimal.ZERO);
//            }
//            
//            total_a_paye = quitance.getPrimeNette().add(quitance.getTaxePrime()).add(quitance.getMontant_Accessoire()).add(quitance.getTaxeAccessoir()).add(quitance.getTimbreDimension())
//                    .add(quitance.getTimbreGradue());
//            quitance.setTotal_a_paye(total_a_paye);
//        } catch (Exception e) {
//            total_a_paye = BigDecimal.ZERO;
//            
//        }
//        PrimeFaces.current().ajax().update("form1:tabprincipal");
//        if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//            PrimeFaces.current().executeScript("PF('accordP').select(6);");
//        } else {
//            PrimeFaces.current().executeScript("PF('accordP').select(5);");
//        }
//
////        }
//    }
//    
//    public void chargeTvaForQuittance() {
//        if (policeSelect != null && policeSelect.getId() != null) {
//            OrclassQuitance q = quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect);
//            if (q != null && q.getId() != null && q.getTaxePrime().intValue() == 0) {
//                tva = BigDecimal.ZERO;
//            } else {
//                BigDecimal prcet = BigDecimal.valueOf(100.0);
//                try {
//                    tva = BigDecimal.valueOf(GlobalFonctions.getDoubleAfterVirgule((q.getTaxePrime().multiply(prcet)).divide(q.getPrimeNette()).doubleValue()));
//                } catch (Exception e) {
//                    System.out.println("taxe prime :" + q.getTaxePrime().toString());
//                    System.out.println("taxe prime :" + (q.getTaxePrime().multiply(prcet)).toString());
//                    System.out.println(" prime :" + q.getPrimeNette());
//                }
//                
//            }
//            
//        }
//    }
//
//    /*
//    retrouver les primes d un apporteur
//     */
//    public void chargePrimeApproteur() {
//        /*
//        verifion les primes apporteur lier au garanties du projet
//         */
//        BigDecimal taux_apport = BigDecimal.ZERO;
//        BigDecimal taux_Gesgtion = BigDecimal.ZERO;
//        OrclassCommission_Prime_Apporteur commission_Prime_Apporteur;
//        OrclassPoliceCommissionApporteur pcap;
//        listePoliceCommissionApporteur.clear();
//        if (apporteur != null && apporteur.getIdApporteur() != null) {
//            police.setIdApporteur(apporteur);
//        }
//        
//        if (police.getIdApporteur() != null && police.getIdApporteur().getIdApporteur() != null) {
//            for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//                //verifions si la prime est fixer sur la garantie 
//
//                if (pg.getIdGarantie().getIdRefGaranties() == null || pg.getIdGarantie().getIdRefGaranties().getId() == null) {
//                    continue;
//                }
//                commission_Prime_Apporteur = commission_Prime_ApporteurDao.getPrimeCommissionByApporteurHaveGarantie(police.getIdApporteur(), categories, pg.getIdGarantie(), entreprise);
//                if (commission_Prime_Apporteur != null && commission_Prime_Apporteur.getId() != null) {
//                    taux_Gesgtion = taux_Gesgtion.add(commission_Prime_Apporteur.getTaux_gestion());
//                    taux_apport = taux_apport.add(commission_Prime_Apporteur.getTaux_apport());
//                    pcap = new OrclassPoliceCommissionApporteur();
//                    pcap.setIdCommission_Prime_Apporteur(commission_Prime_Apporteur);
//                    pcap.setTaux_apport(commission_Prime_Apporteur.getTaux_apport());
//                    pcap.setTaux_gestion(commission_Prime_Apporteur.getTaux_gestion());
//                    listePoliceCommissionApporteur.add(pcap);
//                    continue;
//                }
//
//                // verifions que la prime est directement lier a l apporteur
//                commission_Prime_Apporteur = commission_Prime_ApporteurDao.getPrimeCommissionByApporteur(police.getIdApporteur(), categories, entreprise);
//                
//                if (commission_Prime_Apporteur != null && commission_Prime_Apporteur.getId() != null) {
//                    taux_Gesgtion = taux_Gesgtion.add(commission_Prime_Apporteur.getTaux_gestion());
//                    taux_apport = taux_apport.add(commission_Prime_Apporteur.getTaux_apport());
//                    pcap = new OrclassPoliceCommissionApporteur();
//                    pcap.setIdCommission_Prime_Apporteur(commission_Prime_Apporteur);
//                    pcap.setTaux_apport(commission_Prime_Apporteur.getTaux_apport());
//                    pcap.setTaux_gestion(commission_Prime_Apporteur.getTaux_gestion());
//                    listePoliceCommissionApporteur.add(pcap);
//                    continue;
//                }
//                //verifion si la prime conserne le type d apporteur
//                commission_Prime_Apporteur = commission_Prime_ApporteurDao.getPrimeCommissionByTypeApporteurHaveGarantie(police.getIdApporteur().getIdTypeApporteur(), categories, pg.getIdGarantie(), entreprise);
//                
//                if (commission_Prime_Apporteur != null && commission_Prime_Apporteur.getId() != null) {
//                    taux_Gesgtion = taux_Gesgtion.add(commission_Prime_Apporteur.getTaux_gestion());
//                    taux_apport = taux_apport.add(commission_Prime_Apporteur.getTaux_apport());
//                    pcap = new OrclassPoliceCommissionApporteur();
//                    pcap.setIdCommission_Prime_Apporteur(commission_Prime_Apporteur);
//                    pcap.setTaux_apport(commission_Prime_Apporteur.getTaux_apport());
//                    pcap.setTaux_gestion(commission_Prime_Apporteur.getTaux_gestion());
//                    listePoliceCommissionApporteur.add(pcap);
//                    continue;
//                }
//                
//                commission_Prime_Apporteur = commission_Prime_ApporteurDao.getPrimeCommissionByTypeApporteur(police.getIdApporteur().getIdTypeApporteur(), categories, entreprise);
//                
//                if (commission_Prime_Apporteur != null && commission_Prime_Apporteur.getId() != null) {
//                    taux_Gesgtion = taux_Gesgtion.add(commission_Prime_Apporteur.getTaux_gestion());
//                    taux_apport = taux_apport.add(commission_Prime_Apporteur.getTaux_apport());
//                    pcap = new OrclassPoliceCommissionApporteur();
//                    pcap.setIdCommission_Prime_Apporteur(commission_Prime_Apporteur);
//                    pcap.setTaux_apport(commission_Prime_Apporteur.getTaux_apport());
//                    pcap.setTaux_gestion(commission_Prime_Apporteur.getTaux_gestion());
//                    listePoliceCommissionApporteur.add(pcap);
//                    continue;
//                }
//                
//            }
//            this.quitance.setTaux_apport(taux_apport);
//            this.quitance.setTaux_gestion(taux_Gesgtion);
//        }
//        
//    }
//    
//    public void updateDataTableRisqueFamille() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idrisqueFamilleTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('risqueFamilleTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateDataTableAvenant() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idAvenantTable");
//        table.setValueExpression("sortBy", null);
//        PrimeFaces.current().executeScript("PF('avenantTable').clearFilters();");
//        
//    }
//    
//    public void updateDataTablePoliceCarzacteristique() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idpTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('pTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateGrid() {
//        UIComponent gridElt = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:gridCom");
//        gridElt.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateDataTableRubriqueCaracteristique() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idrbTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('rbTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateDataTablePoliceGaranties() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idpgTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('pgTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateDataTableRubriqueGarantie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idrgTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('rgTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//    
//    public void updateDataTableContrat() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idcontractTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('contractTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    /*
//    charger la maorationDureer pour une categoris precise
//     */
//    public void chargeMajorationDuree() {
//        if (categories != null && categories.getIdCategorie() != null) {
////            listeMajorationDurees = majorationDureeDao.listMajorationByCategories(entreprise, categories);
//            listeDurees = majorationDureeDao.listDureeHaveCategories(categories, entreprise);
//        }
//        
//    }
//    
//    public void onTabChange(TabChangeEvent event) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        this.chargeFractionnementByCategories();
//        /*
//        verifition les champs obligatoire
//        
//        
//         */
//        if (categories == null || categories.getIdCategorie() == null) {
//            if (policeSelect == null || policeSelect.getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE SELECT POLICE..."));
//                return;
//            }
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE SELECT CATEGORY", "TRY AGAINST"));
//            return;
//        }
//        
//        if (police != null && police.getId() != null) {
////            if (event.getTab().getId().equals("assurer")) {
////                this.setRisque(risqueDao.risqueByPolice(entreprise, police));
////                if (risque == null || risque.getId() == null) {
////                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR RISQ IS NOT EXIST... PLEASE TRY AGAINST"));
////                    return;
////                }
////
////            }
//            if (event.getTab().getId().equals("quittance")) {
//                BigDecimal taxe = BigDecimal.valueOf(19.25);
//                BigDecimal total_a_paye = BigDecimal.ZERO;
////                BigDecimal totalPrimeByPoliceSelection = policeGarantieDao.sommePrimeByPoliceNotHaveAvenant(entreprise, police);
//
////                if (police.getDate_effet() != null) {
////                    quitance.setDate_effet(police.getDate_effet());
////                }
////                if (police.getDate_echeance() != null) {
////                    quitance.setDate_echeance(police.getDate_echeance());
////                }
////                quitance.setDateEmission(new Date());
////                totalPrimeByPoliceSelection = this.totalProrata.subtract(totalPrimeByPoliceSelection);
//                quitance.setPrimeNette(this.totalProrata);
////                if (police.getMontantaccessoir().compareTo(policeSelect.getMontantaccessoir()) == 0) {
////                    quitance.setMontant_Accessoire(BigDecimal.ZERO);
////                } else {
////                    quitance.setMontant_Accessoire(policeSelect.getMontantaccessoir());
////                }
//                if (policeSelect.getMontantaccessoir() == null) {
//                    
//                    quitance.setMontant_Accessoire(BigDecimal.ZERO);
//                } else {
//                    if (police.getMontantaccessoir().equals(ancienMontantAccessoire)) {
//                        quitance.setMontant_Accessoire(BigDecimal.ZERO);
//                    } else {
//                        
//                        if (quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, police).getMontant_Accessoire().equals(police.getMontantaccessoir())) {
//                            quitance.setMontant_Accessoire(BigDecimal.ZERO);
//                        } else {
//                            quitance.setMontant_Accessoire(police.getMontantaccessoir());
//                        }
//                        
//                    }
//                    
//                }
//                
//                quitance.setTypQuittance(TypeQuittance.emmission);
//                try {
//                    
//                    quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                } catch (Exception e) {
//                    quitance.setTaxePrime(BigDecimal.ZERO);
//                }
//                try {
//                    quitance.setPrimeTaxe(quitance.getPrimeNette().add(quitance.getTaxePrime()));
//                } catch (Exception e) {
//                    quitance.setPrimeTaxe(BigDecimal.ZERO);
//                }
//                try {
//                    quitance.setTaxeAccessoir((quitance.getMontant_Accessoire().multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                } catch (Exception e) {
//                    quitance.setTaxeAccessoir(BigDecimal.ZERO);
//                }
//                try {
//                    quitance.setAccessoirTaxe(quitance.getMontant_Accessoire().add(quitance.getTaxeAccessoir()));
//                } catch (Exception e) {
//                    quitance.setAccessoirTaxe(BigDecimal.ZERO);
//                }
//                
//                quitance.setTotalTaxes(quitance.getTaxePrime().add(quitance.getTaxeAccessoir()));
//                quitance.setTimbreGradue(BigDecimal.ZERO);
//                quitance.setTimbreDimension(BigDecimal.ZERO);
//                try {
//                    if (quitance.getMontant_Accessoire() == null) {
//                        quitance.setMontant_Accessoire(BigDecimal.ZERO);
//                        quitance.setTaxeAccessoir(BigDecimal.ZERO);
//                    }
//                    if (quitance.getTimbreDimension() == null) {
//                        quitance.setTimbreDimension(BigDecimal.ZERO);
//                    }
//                    if (quitance.getTimbreGradue() == null) {
//                        quitance.setTimbreGradue(BigDecimal.ZERO);
//                    }
//                    
//                    total_a_paye = quitance.getPrimeNette().add(quitance.getTaxePrime()).add(quitance.getMontant_Accessoire()).add(quitance.getTaxeAccessoir()).add(quitance.getTimbreDimension())
//                            .add(quitance.getTimbreGradue());
//                    quitance.setTotal_a_paye(total_a_paye);
//                } catch (Exception e) {
//                    total_a_paye = BigDecimal.ZERO;
//                    
//                }
//                // effectuer les derniers sauvegarde sur les derniers element
//                if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                    if (refGroupeSelect != null && refGroupeSelect.getId() != null && refGroupeSelect.getIndice_tab2() != -1) {
//                        arrays_Tab2[refGroupeSelect.getIndice_tab2()][0] = listeRisque;
//                        arrays_Tab2[refGroupeSelect.getIndice_tab2()][1] = listeRisqueFamille;
//                        arrays_Tab2[refGroupeSelect.getIndice_tab2()][2] = listeDetailPolicePlafond;
//                        arrays_Tab2[refGroupeSelect.getIndice_tab2()][3] = listePoliceCaracteristique;
//                        arrays_Tab2[refGroupeSelect.getIndice_tab2()][4] = listePoliceGarantie;
//                    } else if (refGroupeSelect != null && refGroupeSelect.getId() != null && refGroupeSelect.getIndice_tab2() == -1) {
//                        arrays_Tab2[compteur_tab2][0] = listeRisque;
//                        arrays_Tab2[compteur_tab2][1] = listeRisqueFamille;
//                        arrays_Tab2[compteur_tab2][2] = listeDetailPolicePlafond;
//                        arrays_Tab2[compteur_tab2][3] = listePoliceCaracteristique;
//                        arrays_Tab2[compteur_tab2][4] = listePoliceGarantie;
//                        refGroupeSelect.setIndice_tab2(compteur_tab2);
//                        for (int i = 0; i < listeRefGroup.size(); i++) {
//                            if (refGroupeSelect.equals(listeRefGroup.get(i)) == Boolean.TRUE) {
//                                listeRefGroup.set(i, refGroupeSelect);
//                            }
//                            
//                        }
//                        if (compteur_tab2 < listeRefGroup.size() - 1) {
//                            compteur_tab2++;
//                        }
//                    }
//                }
//
////                if (police.getIdApporteur() != null && police.getIdApporteur().getIdApporteur() != null) {
////                    chargePrimeApproteur();adherent
////                }
//            } else if (event.getTab().getId().equals("prestation")) {
//                listePlafondMaladie = listePlafondMaladie.isEmpty() ? listePlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise) : listePlafondMaladie;
//                listeRubriqueCategorie = listeRubriqueCategorie.isEmpty() ? listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise) : listeRubriqueCategorie;
//                if (listeDetailPolicePlafond.isEmpty()) {
//                    detailPolicePlafond = new OrclassDetailPolicePlafond();
//                    detailPolicePlafond.setIdEntreprise(entreprise);
//                    listeDetailPolicePlafond.add(detailPolicePlafond);
//                    
//                }
//            } else if (event.getTab().getId().equals("caracteristique")) {
//                if (listePoliceCaracteristique.isEmpty()) {
//                    policeCaracteristique = new OrclassPoliceCaracteristique();
//                    policeCaracteristique.setIdEntreprise(entreprise);
//                    listePoliceCaracteristique.add(policeCaracteristique);
//                    this.updateDataTablePoliceCarzacteristique();
//                }
//            } else if (event.getTab().getId().equals("garantie")) {
//                if (listePoliceGarantie.isEmpty()) {
//                    policeGarantie = new OrclassPoliceGarantie();
//                    policeGarantie.setIdEntreprise(entreprise);
//                    listePoliceGarantie.add(policeGarantie);
//                    this.updateDataTablePoliceGaranties();
//                }
//                if (listeRubriqueGarantie.isEmpty()) {
//                    if (rubriqueCategorie == null && policeSelect != null && policeSelect.getId() != null) {
//                        rubriqueCategorie = new OrclassRubriqueCategorie();
//                        rubriqueCategorie.setIdCategories(policeSelect.getIdCategories());
//                    }
//                    listeRubriqueGarantie = listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorie(policeSelect.getIdTypeTarif(), rubriqueCategorie, entreprise);
//                    this.updateDataTableRubriqueGarantie();
//                }
//                
//            }
//        }
//        
//    }
//    
//    public BigDecimal calculPrimeAutomatiqueGarantie(OrclassGarantie g) {
//        /*
//        ici sera appliquzr l agorithme du calcul automatique  des primes ou proarata
//         */
//        return BigDecimal.ZERO;
//    }
//    
//    public void calculTotal_Paye() {
//        
//    }
//    
//    public List<String> completeText(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                results = assureDao.getAssueRaisonSocialWithFilters(entreprise, query.toUpperCase());
//            } else {
//                results = assureDao.getAssuerCodeWithFilters(entreprise, query.toUpperCase());
//            }
//        }
//        
//        return results;
//    }
//    
//    public List<String> completeTextReferenceGroup(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = refGroupeDao.getReferenceGroupByLibelleithFilters(query);
//        }
//        
//        return results;
//    }
//    
//    public void onItemSelect(SelectEvent<String> event) {
//        if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//            assureCheck = assureDao.finKeyAssure(assure.getRaison_social(), entreprise);
//        } else {
//            assureCheck = assureDao.finKeyAssure(assure.getNom(), entreprise);
//        }
//        if (assureCheck != null && assureCheck.getId() != null) {
//            PrimeFaces.current().executeScript("PF('dlg').show()");
//        }
//    }
//    
//    public void chargerAssurCheck() {
//        this.setAssure(assureCheck);
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//    
//    public void chargeContratByGrouselected() {
//        if (refGroupeSelect != null && refGroupeSelect.getId() != null) {
//            
//            List<OrclassPoliceCaracteristique> listePoliceCaracteristiqueByAvenant = new ArrayList<>();
//            List<OrclassPoliceGarantie> listePoliceGarantieByAvenant = new ArrayList<>();
//            List<OrclassRisque> listeRisqueByAvenant = new ArrayList<>();
//            List<OrclassRisqueFamille> listeRisqueFamilleByAvenant = new ArrayList<>();
//            List<OrclassDetailPolicePlafond> listeDetailPoliceByAvenant = new ArrayList<>();
//            List arrays[] = new List[5];
//            HashMap<Long, List[]> mapForObjetGroupe = new HashMap<Long, List[]>();
////            OrclasseRefGroupe oldGroup = null;
//            //           arrays = new List[5];
//            if (avenant != null && avenant.getId() != null) {
//                
//                mapForObjetGroupe = oldGroup.getMapForObjetGroupe();
//                //appelle de cette fonction montre que on a selection un autre groupe
//                //avant tout nous allons verifier si le groupe selection par defaurt existe 
//                if ((mapForObjetGroupe.isEmpty())) {
//                    // la liste est vide 
//
//                    arrays[0] = listeRisque;
//                    arrays[1] = listeRisqueFamille;
//                    arrays[2] = listeDetailPolicePlafond;
//                    arrays[3] = listePoliceCaracteristique;
//                    arrays[4] = listePoliceGarantie;
//                    
//                    mapForObjetGroupe.put(oldGroup.getId(), arrays);
//                    //on modifit l instance du groupedd
//                    if (compteur_tab2 < listeRefGroup.size() - 1) {
//                        arrays_Tab2[compteur_tab2][0] = listeRisque;
//                        arrays_Tab2[compteur_tab2][1] = listeRisqueFamille;
//                        arrays_Tab2[compteur_tab2][2] = listeDetailPolicePlafond;
//                        arrays_Tab2[compteur_tab2][3] = listePoliceCaracteristique;
//                        arrays_Tab2[compteur_tab2][4] = listePoliceGarantie;
//                        oldGroup.setIndice_tab2(compteur_tab2);
//                        compteur_tab2++;
//                    }
//                    
//                    this.updateInstanceGroupe(mapForObjetGroupe, oldGroup);
//
////                    arrays = new List[5];
//                    lastGroupSelecte = refGroupeSelect;
//                    //chargeons les elements en fonction du groupe selectionner
//                    listeRisque = new ArrayList<>();
//                    listeRisqueFamille = new ArrayList<>();
//                    listeDetailPolicePlafond = new ArrayList<>();
//                    listePoliceCaracteristique = new ArrayList<>();
//                    listePoliceGarantie = new ArrayList<>();
//                    for (OrclassRisque rav : risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, policeSelect)) {
//                        if (rav.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listeRisqueByAvenant.add(risqueDao.find(rav.getCodeid_retirer()));
//                            continue;
//                        }
////                        if (!listeRisque.isEmpty()) {
////                            listeRisque = new ArrayList<>();
////                        }
//                        if (listeRisque.contains(rav) == Boolean.FALSE) {
//                            listeRisque.add(rav);
//                        }
//                    }
//                    if (!listeRisqueByAvenant.isEmpty()) {
//                        listeRisque.removeAll(listeRisqueByAvenant);
//                    }
//
//                    // controlle general sur les elements
//                    for (OrclassPoliceCaracteristique pc : policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect, refGroupeSelect)) {
//                        if (pc.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listePoliceCaracteristiqueByAvenant.add(policeCaracteristiqueDao.find(pc.getCodeid_retirer()));
//                            continue;
//                        }
//                        listePoliceCaracteristique.add(pc);
//                    }
//                    if (!listePoliceCaracteristiqueByAvenant.isEmpty()) {
//                        listePoliceCaracteristique.removeAll(listePoliceCaracteristiqueByAvenant);
//                    }
//
//                    ///recuperation des garatiens
//                    for (OrclassPoliceGarantie pg : policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect, refGroupeSelect)) {
//                        if (pg.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listePoliceGarantieByAvenant.add(policeGarantieDao.find(pg.getCodeid_retirer()));
//                            continue;
//                        }
//                        listePoliceGarantie.add(pg);
//                    }
//                    
//                    if (!listePoliceGarantieByAvenant.isEmpty()) {
//                        listePoliceGarantie.removeAll(listePoliceGarantieByAvenant);
//                    }
//
//                    // recuperons les detail plafond prestation;
//                    for (OrclassDetailPolicePlafond dpp : detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police, refGroupeSelect)) {
//                        if (dpp.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listeDetailPoliceByAvenant.add(detailPolicePlafondDao.find(dpp.getCodeid_retirer()));
//                            continue;
//                        }
//                        listeDetailPolicePlafond.add(dpp);
//                    }
//                    if (!listeDetailPolicePlafond.isEmpty()) {
//                        listeDetailPolicePlafond.removeAll(listeDetailPoliceByAvenant);
//                    }
//                    
//                    if (!listeDetailPolicePlafond.isEmpty()) {
//                        detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                        this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                        listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                    }
//                    
//                } else {
//                    if (this.lastGroupSelecte.getMapForObjetGroupe().containsKey(lastGroupSelecte.getId()) == Boolean.FALSE && (this.refGroupeSelect.getMapForObjetGroupe().containsKey(refGroupeSelect.getId()) == Boolean.FALSE)) {
////                        arrays = new List[5];
//                        arrays[0] = listeRisque;
//                        arrays[1] = listeRisqueFamille;
//                        arrays[2] = listeDetailPolicePlafond;
//                        arrays[3] = listePoliceCaracteristique;
//                        arrays[4] = listePoliceGarantie;
//                        mapForObjetGroupe.put(lastGroupSelecte.getId(), arrays);
//                        // on modifit l instance du groupe;o
//                        if (compteur_tab2 < listeRefGroup.size()) {
//                            arrays_Tab2[compteur_tab2][0] = listeRisque;
//                            arrays_Tab2[compteur_tab2][1] = listeRisqueFamille;
//                            arrays_Tab2[compteur_tab2][2] = listeDetailPolicePlafond;
//                            arrays_Tab2[compteur_tab2][3] = listePoliceCaracteristique;
//                            arrays_Tab2[compteur_tab2][4] = listePoliceGarantie;
//                            lastGroupSelecte.setIndice_tab2(compteur_tab2);
//                            compteur_tab2++;
//                            
//                        }
//                        
//                        this.updateInstanceGroupe(mapForObjetGroupe, lastGroupSelecte);
//                        
//                        lastGroupSelecte = refGroupeSelect;
//                        //chargeons les elements en fonction du groupe selectionner
//                        listeRisque = new ArrayList<>();
//                        listeRisqueFamille = new ArrayList<>();
//                        listeDetailPolicePlafond = new ArrayList<>();
//                        listePoliceCaracteristique = new ArrayList<>();
//                        listePoliceGarantie = new ArrayList<>();
//                        
//                        for (OrclassRisque rav : risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police)) {
//                            if (rav.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                                listeRisqueByAvenant.add(risqueDao.find(rav.getCodeid_retirer()));
//                                continue;
//                            }
////                            if (!listeRisque.isEmpty()) {
////                                listeRisque = new ArrayList<>();
////                            }
//                            if (listeRisque.contains(rav) == Boolean.FALSE) {
//                                listeRisque.add(rav);
//                            }
//                        }
//                        if (!listeRisqueByAvenant.isEmpty()) {
//                            listeRisque.removeAll(listeRisqueByAvenant);
//                        }
//
//                        // controlle general sur les elements
//                        for (OrclassPoliceCaracteristique pc : policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect, refGroupeSelect)) {
//                            if (pc.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                                listePoliceCaracteristiqueByAvenant.add(policeCaracteristiqueDao.find(pc.getCodeid_retirer()));
//                                continue;
//                            }
//                            listePoliceCaracteristique.add(pc);
//                        }
//                        if (!listePoliceCaracteristiqueByAvenant.isEmpty()) {
//                            listePoliceCaracteristique.removeAll(listePoliceCaracteristiqueByAvenant);
//                        }
//
//                        ///recuperation des garatiens
//                        for (OrclassPoliceGarantie pg : policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect, refGroupeSelect)) {
//                            if (pg.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                                listePoliceGarantieByAvenant.add(policeGarantieDao.find(pg.getCodeid_retirer()));
//                                continue;
//                            }
//                            listePoliceGarantie.add(pg);
//                        }
//                        
//                        if (!listePoliceGarantieByAvenant.isEmpty()) {
//                            listePoliceGarantie.removeAll(listePoliceGarantieByAvenant);
//                        }
//
//                        // recuperons les detail plafond prestation;
//                        for (OrclassDetailPolicePlafond dpp : detailPolicePlafondDao.allDetailPlafondPolice(entreprise, policeSelect, refGroupeSelect)) {
//                            if (dpp.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                                listeDetailPoliceByAvenant.add(detailPolicePlafondDao.find(dpp.getCodeid_retirer()));
//                                continue;
//                            }
//                            listeDetailPolicePlafond.add(dpp);
//                        }
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            listeDetailPolicePlafond.removeAll(listeDetailPoliceByAvenant);
//                        }
//                        
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                            this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                            listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                        }
//                        
//                    } else if (lastGroupSelecte.getMapForObjetGroupe().containsKey(lastGroupSelecte.getId()) == Boolean.TRUE && refGroupeSelect.getMapForObjetGroupe().containsKey(refGroupeSelect.getId()) == Boolean.FALSE) {
//                        
//                        arrays[0] = listeRisque;
//                        arrays[1] = listeRisqueFamille;
//                        arrays[2] = listeDetailPolicePlafond;
//                        arrays[3] = listePoliceCaracteristique;
//                        arrays[4] = listePoliceGarantie;
//                        mapForObjetGroupe.put(lastGroupSelecte.getId(), arrays);
//                        // on modifit l instance du groupe;
////                        if (compteur_tab2 < listeRefGroup.size()) {
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][0] = listeRisque;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][1] = listeRisqueFamille;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][2] = listeDetailPolicePlafond;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][3] = listePoliceCaracteristique;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][4] = listePoliceGarantie;
//
////                        }
//                        this.updateInstanceGroupe(mapForObjetGroupe, lastGroupSelecte);
//                        
//                        lastGroupSelecte = refGroupeSelect;
//                        //chargeons les elements en fonction du groupe selectionner
//                        listeRisque = new ArrayList<>();
//                        listeRisqueFamille = new ArrayList<>();
//                        listeDetailPolicePlafond = new ArrayList<>();
//                        listePoliceCaracteristique = new ArrayList<>();
//                        listePoliceGarantie = new ArrayList<>();
//                        
//                        for (OrclassRisque rav : risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police)) {
//                            if (rav.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                                listeRisqueByAvenant.add(risqueDao.find(rav.getCodeid_retirer()));
//                                continue;
//                            }
////                            if (!listeRisque.isEmpty()) {
////                                listeRisque = new ArrayList<>();
////                            }
//                            if (listeRisque.contains(rav) == Boolean.FALSE) {
//                                listeRisque.add(rav);
//                            }
//                        }
//                        if (!listeRisqueByAvenant.isEmpty()) {
//                            listeRisque.removeAll(listeRisqueByAvenant);
//                        }
//
//                        // controlle general sur les elements
//                        for (OrclassPoliceCaracteristique pc : policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect, refGroupeSelect)) {
//                            if (pc.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                                listePoliceCaracteristiqueByAvenant.add(policeCaracteristiqueDao.find(pc.getCodeid_retirer()));
//                                continue;
//                            }
//                            listePoliceCaracteristique.add(pc);
//                        }
//                        if (!listePoliceCaracteristiqueByAvenant.isEmpty()) {
//                            listePoliceCaracteristique.removeAll(listePoliceCaracteristiqueByAvenant);
//                        }
//
//                        ///recuperation des garatiens
//                        for (OrclassPoliceGarantie pg : policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect, refGroupeSelect)) {
//                            if (pg.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                                listePoliceGarantieByAvenant.add(policeGarantieDao.find(pg.getCodeid_retirer()));
//                                continue;
//                            }
//                            listePoliceGarantie.add(pg);
//                        }
//                        
//                        if (!listePoliceGarantieByAvenant.isEmpty()) {
//                            listePoliceGarantie.removeAll(listePoliceGarantieByAvenant);
//                        }
//
//                        // recuperons les detail plafond prestation;
//                        for (OrclassDetailPolicePlafond dpp : detailPolicePlafondDao.allDetailPlafondPolice(entreprise, policeSelect, refGroupeSelect)) {
//                            if (dpp.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                                listeDetailPoliceByAvenant.add(detailPolicePlafondDao.find(dpp.getCodeid_retirer()));
//                                continue;
//                            }
//                            listeDetailPolicePlafond.add(dpp);
//                        }
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            listeDetailPolicePlafond.removeAll(listeDetailPoliceByAvenant);
//                        }
//                        
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                            this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                            listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                        }
//                    } else if (lastGroupSelecte.getMapForObjetGroupe().containsKey(lastGroupSelecte.getId()) == Boolean.TRUE && this.refGroupeSelect.getMapForObjetGroupe().containsKey(refGroupeSelect.getId()) == Boolean.TRUE) {
//                        
//                        arrays[0] = listeRisque;
//                        arrays[1] = listeRisqueFamille;
//                        arrays[2] = listeDetailPolicePlafond;
//                        arrays[3] = listePoliceCaracteristique;
//                        arrays[4] = listePoliceGarantie;
//                        mapForObjetGroupe.put(lastGroupSelecte.getId(), arrays);
//                        // on modifit l instance du groupe;
////                                              if (compteur_tab2 < listeRefGroup.size()) {
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][0] = listeRisque;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][1] = listeRisqueFamille;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][2] = listeDetailPolicePlafond;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][3] = listePoliceCaracteristique;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][4] = listePoliceGarantie;
//
////                        }
//                        this.updateInstanceGroupe(mapForObjetGroupe, lastGroupSelecte);
//                        
//                        for (Map.Entry<Long, List[]> entry : refGroupeSelect.getMapForObjetGroupe().entrySet()) {
//                            if (Boolean.TRUE == entry.getKey().equals(refGroupeSelect.getId())) {
//                                arrays = entry.getValue();
//                                listeRisque = new ArrayList<>(arrays[0]);
//                                listeRisqueFamille = new ArrayList<>(arrays[1]);
//                                listeDetailPolicePlafond = new ArrayList<>(arrays[2]);
//                                listePoliceCaracteristique = new ArrayList<>(arrays[3]);
//                                listePoliceGarantie = new ArrayList<>(arrays[4]);
//                                lastGroupSelecte = refGroupeSelect;
//                                
//                                break;
//                                
//                            }
//                            
//                        }
//                    } else if (lastGroupSelecte.getMapForObjetGroupe().containsKey(lastGroupSelecte.getId()) == Boolean.FALSE && this.refGroupeSelect.getMapForObjetGroupe().containsKey(refGroupeSelect.getId()) == Boolean.TRUE) {
//                        arrays[0] = listeRisque;
//                        arrays[1] = listeRisqueFamille;
//                        arrays[2] = listeDetailPolicePlafond;
//                        arrays[3] = listePoliceCaracteristique;
//                        arrays[4] = listePoliceGarantie;
//                        mapForObjetGroupe.put(lastGroupSelecte.getId(), arrays);
////                        lastGroupSelecte.setMapForObjetGroupe(mapForObjetGroupe);
//                        if (compteur_tab2 < listeRefGroup.size()) {
//                            arrays_Tab2[compteur_tab2][0] = listeRisque;
//                            arrays_Tab2[compteur_tab2][1] = listeRisqueFamille;
//                            arrays_Tab2[compteur_tab2][2] = listeDetailPolicePlafond;
//                            arrays_Tab2[compteur_tab2][3] = listePoliceCaracteristique;
//                            arrays_Tab2[compteur_tab2][4] = listePoliceGarantie;
//                            lastGroupSelecte.setIndice_tab2(compteur_tab2);
//                            compteur_tab2++;
//                            
//                        }
//                        
//                        this.updateInstanceGroupe(mapForObjetGroupe, lastGroupSelecte);
////                        lastGroupSelecte = refGroupeSelect;
//                        //chargeons les elements en fonction du groupe selectionner
//                        listeRisque = new ArrayList<>();
//                        listeRisqueFamille = new ArrayList<>();
//                        listeDetailPolicePlafond = new ArrayList<>();
//                        listePoliceCaracteristique = new ArrayList<>();
//                        listePoliceGarantie = new ArrayList<>();
//                        
//                        for (Map.Entry<Long, List[]> entry : refGroupeSelect.getMapForObjetGroupe().entrySet()) {
//                            if (Boolean.TRUE == entry.getKey().equals(refGroupeSelect.getId())) {
//                                arrays = entry.getValue();
//                                listeRisque = new ArrayList<>(arrays[0]);
//                                listeRisqueFamille = new ArrayList<>(arrays[1]);
//                                listeDetailPolicePlafond = new ArrayList<>(arrays[2]);
//                                listePoliceCaracteristique = new ArrayList<>(arrays[3]);
//                                listePoliceGarantie = new ArrayList<>(arrays[4]);
//                                lastGroupSelecte = refGroupeSelect;
//                                
//                                break;
//                                
//                            }
//                            
//                        }
//                        
//                    }
//                }
//            } else {
//
//                //appelle de cette fonction montre que on a selection un autre groupe
//                //avant tout nous allons verifier si le groupe selection par defaurt existe 
//                mapForObjetGroupe = oldGroup.getMapForObjetGroupe();
//                if ((mapForObjetGroupe.isEmpty())) {
//                    // la liste est vide 
////                    arrays = new List[5];
//                    arrays[0] = listeRisque;
//                    arrays[1] = listeRisqueFamille;
//                    arrays[2] = listeDetailPolicePlafond;
//                    arrays[3] = listePoliceCaracteristique;
//                    arrays[4] = listePoliceGarantie;
//                    mapForObjetGroupe.put(oldGroup.getId(), arrays);
//                    if (compteur_tab2 < listeRefGroup.size()) {
//                        arrays_Tab2[compteur_tab2][0] = listeRisque;
//                        arrays_Tab2[compteur_tab2][1] = listeRisqueFamille;
//                        arrays_Tab2[compteur_tab2][2] = listeDetailPolicePlafond;
//                        arrays_Tab2[compteur_tab2][3] = listePoliceCaracteristique;
//                        arrays_Tab2[compteur_tab2][4] = listePoliceGarantie;
//                        oldGroup.setIndice_tab2(compteur_tab2);
//                        compteur_tab2++;
//                    }
//                    
//                    this.updateInstanceGroupe(mapForObjetGroupe, oldGroup);
//                    
//                    lastGroupSelecte = refGroupeSelect;
//                    //chargeons les elements en fonction du groupe selectionner
//                    listeRisque = risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police);
//                    listePoliceCaracteristique = policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect, refGroupeSelect);
//                    listePoliceGarantie = policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect, refGroupeSelect);
//                    listeDetailPolicePlafond = detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police, refGroupeSelect);
//                    // controlle general sur les elements
//
//                    if (!listeDetailPolicePlafond.isEmpty()) {
//                        detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                        this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                        listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                    }
//                    
//                } else {
//                    if (this.lastGroupSelecte.getMapForObjetGroupe().containsKey(lastGroupSelecte.getId()) == Boolean.FALSE && (this.refGroupeSelect.getMapForObjetGroupe().containsKey(refGroupeSelect.getId()) == Boolean.FALSE)) {
////                        arrays = new List[5];
//                        arrays[0] = listeRisque;
//                        arrays[1] = listeRisqueFamille;
//                        arrays[2] = listeDetailPolicePlafond;
//                        arrays[3] = listePoliceCaracteristique;
//                        arrays[4] = listePoliceGarantie;
//                        mapForObjetGroupe.put(lastGroupSelecte.getId(), arrays);
//                        if (compteur_tab2 < listeRefGroup.size()) {
//                            arrays_Tab2[compteur_tab2][0] = listeRisque;
//                            arrays_Tab2[compteur_tab2][1] = listeRisqueFamille;
//                            arrays_Tab2[compteur_tab2][2] = listeDetailPolicePlafond;
//                            arrays_Tab2[compteur_tab2][3] = listePoliceCaracteristique;
//                            arrays_Tab2[compteur_tab2][4] = listePoliceGarantie;
//                            lastGroupSelecte.setIndice_tab2(compteur_tab2);
//                            compteur_tab2++;
//                        }
//                        
//                        this.updateInstanceGroupe(mapForObjetGroupe, lastGroupSelecte);
//                        lastGroupSelecte = refGroupeSelect;
//                        //chargeons les elements en fonction du groupe selectionner
//
//                        listeRisque = risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police);
//                        listePoliceCaracteristique = policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect, refGroupeSelect);
//                        listePoliceGarantie = policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect, refGroupeSelect);
//                        listeDetailPolicePlafond = detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police, refGroupeSelect);
//                        
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                            this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                            listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                        }
//                        
//                    } else if (this.lastGroupSelecte.getMapForObjetGroupe().containsKey(lastGroupSelecte.getId()) == Boolean.TRUE && this.refGroupeSelect.getMapForObjetGroupe().containsKey(refGroupeSelect.getId()) == Boolean.FALSE) {
//                        
//                        arrays[0] = listeRisque;
//                        arrays[1] = listeRisqueFamille;
//                        arrays[2] = listeDetailPolicePlafond;
//                        arrays[3] = listePoliceCaracteristique;
//                        arrays[4] = listePoliceGarantie;
//                        mapForObjetGroupe.put(lastGroupSelecte.getId(), arrays);
////                                if (compteur_tab2 < listeRefGroup.size()) {
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][0] = listeRisque;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][1] = listeRisqueFamille;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][2] = listeDetailPolicePlafond;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][3] = listePoliceCaracteristique;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][4] = listePoliceGarantie;
////                        lastGroupSelecte.setIndice_tab2(compteur_tab2);
////                    }
//                        this.updateInstanceGroupe(mapForObjetGroupe, lastGroupSelecte);
//                        lastGroupSelecte = refGroupeSelect;
//
//                        //chargeons les elements en fonction du groupe selectionner
//                        listeRisque = risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police);
//                        listePoliceCaracteristique = policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect, refGroupeSelect);
//                        listePoliceGarantie = policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect, refGroupeSelect);
//                        listeDetailPolicePlafond = detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police, refGroupeSelect);
//                        
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                            this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                            listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                        }
//                    } else if (this.lastGroupSelecte.getMapForObjetGroupe().containsKey(lastGroupSelecte.getId()) == Boolean.FALSE && this.refGroupeSelect.getMapForObjetGroupe().containsKey(refGroupeSelect.getId()) == Boolean.TRUE) {
////                        arrays = new List[5];
//
//                        arrays[0] = listeRisque;
//                        arrays[1] = listeRisqueFamille;
//                        arrays[2] = listeDetailPolicePlafond;
//                        arrays[3] = listePoliceCaracteristique;
//                        arrays[4] = listePoliceGarantie;
//                        mapForObjetGroupe.put(lastGroupSelecte.getId(), arrays);
//                        if (compteur_tab2 < listeRefGroup.size()) {
//                            arrays_Tab2[compteur_tab2][0] = listeRisque;
//                            arrays_Tab2[compteur_tab2][1] = listeRisqueFamille;
//                            arrays_Tab2[compteur_tab2][2] = listeDetailPolicePlafond;
//                            arrays_Tab2[compteur_tab2][3] = listePoliceCaracteristique;
//                            arrays_Tab2[compteur_tab2][4] = listePoliceGarantie;
//                            lastGroupSelecte.setIndice_tab2(compteur_tab2);
//                            compteur_tab2++;
//                        }
//                        
//                        this.updateInstanceGroupe(mapForObjetGroupe, lastGroupSelecte);
////                        lastGroupSelecte = refGroupeSelect;
//                        for (Map.Entry<Long, List[]> entry : refGroupeSelect.getMapForObjetGroupe().entrySet()) {
//                            if (Boolean.TRUE == entry.getKey().equals(refGroupeSelect.getId())) {
//                                arrays = entry.getValue();
//                                listeRisque = new ArrayList<>(arrays[0]);
//                                listeRisqueFamille = new ArrayList<>(arrays[1]);
//                                listeDetailPolicePlafond = new ArrayList<>(arrays[2]);
//                                listePoliceCaracteristique = new ArrayList<>(arrays[3]);
//                                listePoliceGarantie = new ArrayList<>(arrays[4]);
//                                lastGroupSelecte = refGroupeSelect;
//                                break;
//                                
//                            }
//                            
//                        }
//                    } else if (this.lastGroupSelecte.getMapForObjetGroupe().containsKey(lastGroupSelecte.getId()) == Boolean.TRUE && this.refGroupeSelect.getMapForObjetGroupe().containsKey(refGroupeSelect.getId()) == Boolean.TRUE) {
//                        arrays[0] = listeRisque;
//                        arrays[1] = listeRisqueFamille;
//                        arrays[2] = listeDetailPolicePlafond;
//                        arrays[3] = listePoliceCaracteristique;
//                        arrays[4] = listePoliceGarantie;
//                        mapForObjetGroupe.put(lastGroupSelecte.getId(), arrays);
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][0] = listeRisque;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][1] = listeRisqueFamille;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][2] = listeDetailPolicePlafond;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][3] = listePoliceCaracteristique;
//                        arrays_Tab2[lastGroupSelecte.getIndice_tab2()][4] = listePoliceGarantie;
//                        this.updateInstanceGroupe(mapForObjetGroupe, lastGroupSelecte);
////                        lastGroupSelecte = refGroupeSelect;
//                        for (Map.Entry<Long, List[]> entry : refGroupeSelect.getMapForObjetGroupe().entrySet()) {
//                            if (Boolean.TRUE == entry.getKey().equals(refGroupeSelect.getId())) {
//                                arrays = entry.getValue();
//                                listeRisque = new ArrayList<>(arrays[0]);
//                                listeRisqueFamille = new ArrayList<>(arrays[1]);
//                                listeDetailPolicePlafond = new ArrayList<>(arrays[2]);
//                                listePoliceCaracteristique = new ArrayList<>(arrays[3]);
//                                listePoliceGarantie = new ArrayList<>(arrays[4]);
//                                lastGroupSelecte = refGroupeSelect;
//                                break;
//                                
//                            }
//                            
//                        }
//                    }
//                }
//            }
//            listePlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
//            if (!listeDetailPolicePlafond.isEmpty()) {
//                detailPolicePlafond = listeDetailPolicePlafond.get(0);
//            }
//            
//            plafondMaladie = (detailPolicePlafond == null || detailPolicePlafond.getId() == null) ? new OrclassPlafondMaladie() : detailPolicePlafond.getIdPolicePlafond().getIdPlafondMaladie();
//            listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorie(policeSelect.getIdTypeTarif(), rubriqueCategorie, entreprise);
//            listePrestation = prestationDao.listePrestationByCompagnieNotHavePolicePlafond(rubriqueCategorie, entreprise, police, refGroupeSelect);
//            
//            this.updateDataTablePoliceCarzacteristique();
//            this.updateDataTablePoliceGaranties();
//            this.updateDataTableRisqueFamille();
//            this.updateTableRisque();
//            this.updateDataTablePoliceGaranties();
//            
//        }
//    }
////    }
//
//    public void updateInstanceGroupe(HashMap<Long, List[]> mapForObjetGroupe, OrclasseRefGroupe g) {
//        List<OrclasseRefGroupe> listeGroupe = new ArrayList<>(this.listeRefGroup);
//        OrclasseRefGroupe gr = null;
//        for (int i = 0; i < listeGroupe.size(); i++) {
//            gr = listeGroupe.get(i);
//            if (g.getId().equals(gr.getId()) == Boolean.TRUE) {
//                g.setMapForObjetGroupe(mapForObjetGroupe);
//                this.listeRefGroup.set(i, g);
//                break;
//            }
//            
//        }
//        
//    }
//    
//    public void chargeContractSelecte() {
//        
//        if (policeSelect != null && policeSelect.getNumero_police() != null) {
//            typeAvenant = new Orclass_TypeAvenant();
//            List<OrclassPoliceCaracteristique> listePoliceCaracteristiqueByAvenant = new ArrayList<>();
//            List<OrclassPoliceGarantie> listePoliceGarantieByAvenant = new ArrayList<>();
//            List<OrclassRisque> listeRisqueByAvenant = new ArrayList<>();
//            List<OrclassRisqueFamille> listeRisqueFamilleByAvenant = new ArrayList<>();
//            List<OrclassDetailPolicePlafond> listeDetailPoliceByAvenant = new ArrayList<>();
//            avenant = avenantDao.lastAvenantByPolice(policeSelect, entreprise);
//            this.chargeTvaForQuittance();
//            elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(policeSelect.getIdCategories(), entreprise);
//            this.choixTabView(elt_Categorie_Compagnie);
//            this.setPolice(policeSelect);
//            this.setAssure(policeSelect.getIdAssure());
//            this.setApporteur(policeSelect.getIdApporteur());
//            this.setCategories(policeSelect.getIdCategories());
//            this.setDuree(policeSelect.getIdMajorationDuree().getIdDuree());
//            this.setDesignationPlafondMaladie(policeSelect.getDesignationPlafonMaladie());
//            this.chargeMajoratioDureeByDuree();
//            if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
//                this.setNumero_police(policeSelect.getPolice().toBigInteger());
//            } else {
//                this.setNumero_police(policeSelect.getNumero_police());
//            }
//            
//            if (avenant != null && avenant.getId() != null) {
//                this.ancienMontantAccessoire = (avenant.getMontantaccessoir() != null && avenant.getMontantaccessoir().intValue() != 0) ? avenant.getMontantaccessoir() : policeSelect.getMontantaccessoir();
//                this.ancienMontantAccessoire = this.ancienMontantAccessoire == null ? BigDecimal.ZERO : this.ancienMontantAccessoire;
//                this.quitance = quitanceDao.quittanceForPoliceByAvenant(entreprise, policeSelect, avenant);//recuperation de la quittance de la police sans avenant
//                // recuperation sur les caracteristique et controller de
//                if (tabShowFamille.equals(Boolean.TRUE) || tabShowIndividuel.equals(Boolean.TRUE)) {
//                    for (OrclassPoliceCaracteristique pc : policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect)) {
//                        if (pc.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listePoliceCaracteristiqueByAvenant.add(policeCaracteristiqueDao.find(pc.getCodeid_retirer()));
//                            continue;
//                        }
//                        listePoliceCaracteristique.add(pc);
//                    }
//                    if (!listePoliceCaracteristiqueByAvenant.isEmpty()) {
//                        listePoliceCaracteristique.removeAll(listePoliceCaracteristiqueByAvenant);
//                    }
//
//                    ///recuperation des garatiens
//                    for (OrclassPoliceGarantie pg : policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect)) {
//                        if (pg.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listePoliceGarantieByAvenant.add(policeGarantieDao.find(pg.getCodeid_retirer()));
//                            continue;
//                        }
//                        listePoliceGarantie.add(pg);
//                    }
//                    
//                    if (!listePoliceGarantieByAvenant.isEmpty()) {
//                        listePoliceGarantie.removeAll(listePoliceGarantieByAvenant);
//                    }
//
//                    // recuperons les detail plafond prestation;
//                    for (OrclassDetailPolicePlafond dpp : detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police)) {
//                        if (dpp.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listeDetailPoliceByAvenant.add(detailPolicePlafondDao.find(dpp.getCodeid_retirer()));
//                            continue;
//                        }
//                        listeDetailPolicePlafond.add(dpp);
//                    }
//                    if (!listeDetailPoliceByAvenant.isEmpty()) {
//                        listeDetailPolicePlafond.removeAll(listeDetailPoliceByAvenant);
//                    }
//                    
//                    if (!listeDetailPolicePlafond.isEmpty()) {
//                        detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                        this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                        listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                    }
//                    
//                    this.setRisque(risqueDao.risqueByPolice(entreprise, policeSelect));
//                    this.setListeRisqueFamille(risqueFamilleDao.allRisqueFamilleByPolice(entreprise, policeSelect));
//                    
//                } else if (tabShowGroup.equals(Boolean.TRUE)) {
//                    listeRefGroup = (List<OrclasseRefGroupe>) risqueDao.allReffeGrouByPolice(entreprise, policeSelect, categories);
//                    listeImageFamilleRisque.clear();
//                    listeRisque.clear();
//                    refGroupeSelect = listeRefGroup.get(0);
//                    oldGroup = refGroupeSelect;
//                    arrays_Tab2 = new List[listeRefGroup.size()][5];
//                    for (OrclassRisque rav : risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police)) {
//                        if (rav.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listeRisqueByAvenant.add(risqueDao.find(rav.getCodeid_retirer()));
//                            continue;
//                        }
//                        listeRisque.add(rav);
//                    }
//                    if (!listeRisqueByAvenant.isEmpty()) {
//                        listeRisque.removeAll(listeRisqueByAvenant);
//                    }
//
//                    // controlle general sur les elements
//                    listePoliceCaracteristique.clear();
//                    for (OrclassPoliceCaracteristique pc : policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect, refGroupeSelect)) {
//                        if (pc.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listePoliceCaracteristiqueByAvenant.add(policeCaracteristiqueDao.find(pc.getCodeid_retirer()));
//                            continue;
//                        }
//                        listePoliceCaracteristique.add(pc);
//                    }
//                    if (!listePoliceCaracteristiqueByAvenant.isEmpty()) {
//                        listePoliceCaracteristique.removeAll(listePoliceCaracteristiqueByAvenant);
//                    }
//
//                    ///recuperation des garatiens
//                    listePoliceGarantie.clear();
//                    for (OrclassPoliceGarantie pg : policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect, refGroupeSelect)) {
//                        if (pg.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listePoliceGarantieByAvenant.add(policeGarantieDao.find(pg.getCodeid_retirer()));
//                            continue;
//                        }
//                        listePoliceGarantie.add(pg);
//                    }
//                    
//                    if (!listePoliceGarantieByAvenant.isEmpty()) {
//                        listePoliceGarantie.removeAll(listePoliceGarantieByAvenant);
//                    }
//
//                    // recuperons les detail plafond prestation;
//                    listeDetailPolicePlafond.clear();
//                    for (OrclassDetailPolicePlafond dpp : detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police, refGroupeSelect)) {
//                        if (dpp.getRetire_de_la_police().equals(Boolean.TRUE)) {
//                            listeDetailPoliceByAvenant.add(detailPolicePlafondDao.find(dpp.getCodeid_retirer()));
//                            continue;
//                        }
//                        listeDetailPolicePlafond.add(dpp);
//                    }
//                    if (!listeDetailPolicePlafond.isEmpty()) {
//                        listeDetailPolicePlafond.removeAll(listeDetailPoliceByAvenant);
//                    }
//                    
//                    if (!listeDetailPolicePlafond.isEmpty()) {
//                        detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                        this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                        listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                    }
//                    
//                }
//                
//                if (avenant.getAdresse() != null) {
//                    if (avenant.getAdresse().getBp() != null && !"".equals(avenant.getAdresse().getBp())) {
//                        assure.getAdresse().setBp(avenant.getAdresse().getBp());
//                    }
//                    if (avenant.getAdresse().getEmail() != null && !"".equals(avenant.getAdresse().getEmail())) {
//                        assure.getAdresse().setEmail(avenant.getAdresse().getEmail());
//                    }
//                    if (avenant.getAdresse().getEmail() != null && !"".equals(avenant.getAdresse().getEmail())) {
//                        assure.getAdresse().setEmail(avenant.getAdresse().getEmail());
//                    }
//                    if (avenant.getAdresse().getQuartier() != null && !"".equals(avenant.getAdresse().getQuartier())) {
//                        assure.getAdresse().setQuartier(avenant.getAdresse().getQuartier());
//                    }
//                    
//                    if (avenant.getAdresse().getTel() != null && !"".equals(avenant.getAdresse().getTel())) {
//                        assure.getAdresse().setTel(avenant.getAdresse().getTel());
//                    }
//                    if (avenant.getAdresse().getVille() != null && !"".equals(avenant.getAdresse().getVille())) {
//                        assure.getAdresse().setVille(avenant.getAdresse().getVille());
//                    }
//                    
//                }
//                if (avenant.getContrat() != null) {
//                    police.setContrat(avenant.getContrat());
//                }
//                if (avenant.getDate_echeance() != null) {
//                    police.setDate_echeance(avenant.getDate_echeance());
//                }
//                if (avenant.getDate_effet() != null) {
//                    police.setDate_effet(avenant.getDate_effet());
//                }
//                if (avenant.getDate_naissance() != null) {
//                    assure.setDate_naissance(avenant.getDate_naissance());
//                    
//                }
//                if (avenant.getIdMajorationDuree() != null) {
//                    police.setIdMajorationDuree(avenant.getIdMajorationDuree());
//                }
//                if (avenant.getIdTypeTarif() != null) {
//                    police.setIdTypeTarif(avenant.getIdTypeTarif());
//                }
//                if (avenant.getIdVille() != null) {
//                    assure.setIdVille(avenant.getIdVille());
//                }
//                if (avenant.getLieu_naissance() != null) {
//                    assure.setLieu_naissance(avenant.getLieu_naissance());
//                }
//                if (avenant.getMontantaccessoir() != null) {
//                    police.setMontantaccessoir(avenant.getMontantaccessoir());
//                    
//                }
//                
//                if (avenant.getDate_naissance() != null) {
//                    assure.setDate_naissance(avenant.getDate_naissance());
//                    
//                }
//                
//                if (avenant.getNatureContrat() != null) {
//                    police.setNatureContrat(avenant.getNatureContrat());
//                    
//                }
//                
//                if (avenant.getDesignationPlafonMaladie() != null) {
//                    police.setDesignationPlafonMaladie(avenant.getDesignationPlafonMaladie());
//                    
//                }
//                if (avenant.getNom() != null) {
//                    assure.setNom(avenant.getNom());
//                }
//                if (avenant.getRaison_social() != null) {
//                    assure.setRaison_social(avenant.getNom());
//                }
//                if (avenant.getPrenom() != null) {
//                    assure.setPrenom(avenant.getPrenom());
//                }
//                if (avenant.getRef_intermediaire() != null) {
//                    police.setRef_intermediaire(avenant.getRef_intermediaire());
//                }
//                if (avenant.getSexe() != null) {
//                    police.setSexe(avenant.getSexe());
//                }
//                if (avenant.getSexe() != null) {
//                    assure.setSexe(avenant.getSexe());
//                }
//                if (avenant.getUniteDuree() != null) {
//                    police.setUniteDuree(avenant.getUniteDuree());
//                }
//                if (avenant.getValeurDuree() != null) {
//                    police.setValeurDuree(avenant.getValeurDuree());
//                }
//                
//            } else {
//                this.ancienMontantAccessoire = policeSelect.getMontantaccessoir();
//                this.ancienMontantAccessoire = this.ancienMontantAccessoire == null ? BigDecimal.ZERO : this.ancienMontantAccessoire;
//                this.quitance = quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect);//recuperation de la quittance de la police sans avenant
//                if (tabShowFamille.equals(Boolean.TRUE) || tabShowIndividuel.equals(Boolean.TRUE)) {
//                    this.setListePoliceCaracteristique((List<OrclassPoliceCaracteristique>) policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprise, policeSelect));
//                    this.setListePoliceGarantie((List<OrclassPoliceGarantie>) policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, policeSelect));
//                    this.setPlafondMaladie(policePlafondDao.chargePlafonMaladie(policeSelect, entreprise));
//                    this.setListeDetailPolicePlafond(detailPolicePlafondDao.listeDetailPlafond(policeSelect));
//                    if (!listeDetailPolicePlafond.isEmpty()) {
//                        detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                        this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                        listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                    }
//                    this.setRisque(risqueDao.risqueByPolice(entreprise, policeSelect));
//                    this.setListeRisqueFamille((List<OrclassRisqueFamille>) risqueFamilleDao.listeRisqueFamilleByPoliceNotHaveAvenant(entreprise, policeSelect));
//                    
//                } else if (tabShowGroup.equals(Boolean.TRUE)) {
//                    listeRefGroup = (List<OrclasseRefGroupe>) risqueDao.listeRiefeGrouByPolice(entreprise, policeSelect, categories);
//                    listeImageFamilleRisque.clear();
//                    
//                    refGroupeSelect = listeRefGroup.get(0);
//                    listeRisque = risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police);
//                    listePoliceCaracteristique = policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect, refGroupeSelect);
//                    listePoliceGarantie = policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect, refGroupeSelect);
//                    listeDetailPolicePlafond = detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police, refGroupeSelect);
//                    // instanciation du tab2
//                    arrays_Tab2 = new List[listeRefGroup.size()][5];
//                    if (!listeDetailPolicePlafond.isEmpty()) {
//                        detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                        this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                        listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                    }
//                    
//                }
//            }
//            listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeCaracteristiqueNotHavePoliceCaracteristique(entreprise, police);
//            
//            listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorie(policeSelect.getIdTypeTarif(), policeSelect.getIdCategories(), entreprise);
////            if (rubriqueCategorie==null && !listeRubriqueGarantie.isEmpty()) {
////                rubriqueCategorie=rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, listeRubriqueGarantie.get(0).getid, entreprise)
////                
////            }
//            if (detailPolicePlafond != null && detailPolicePlafond.getId() != null) {
//                rubriqueCategorie.setIdRubrique(detailPolicePlafond.getIdRubrique());
//                  listePrestation = prestationDao.listePrestationByCompagnieNotHavePolicePlafond(rubriqueCategorie, entreprise, police);
//          
//            }
//            listePlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
//            plafondMaladie = (detailPolicePlafond == null || detailPolicePlafond.getId() == null) ? new OrclassPlafondMaladie() : detailPolicePlafond.getIdPolicePlafond().getIdPlafondMaladie();
//            
//            this.caculTotalPrime();
//            this.calculTotalProrata();
//            
//            PrimeFaces.current().executeScript("PF('managePoliceDialog').hide();");
//            PrimeFaces.current().ajax().update(":form1");
//            
//        }
//        
//    }
//    
//    public void addLigneByRisqueFamille() {
//        risqueFamille = new OrclassRisqueFamille();
//        listeRisqueFamille.add(risqueFamille);
//        this.reverseListeFamilleRiques();
//        this.updateDataTableRisqueFamille();
//    }
////     public void updateDataTableRisqueFamille() {
////        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idrisqueFamilleTable");
////        table.setValueExpression("sortBy", null);
////
////        PrimeFaces.current().executeScript("PF('risqueFamilleTable').clearFilters();");
//////         PrimeFaces.current().ajax().update("form1_1");
////
////    }
//
//    public void chargerPrestionByRubrique() {
////        listePoliceGarantie = new ArrayList<>();
////        listeRubriqueGarantie = new ArrayList<>();
//        if (categories != null && categories.getIdCategorie() != null && rubriqueCategorie != null && rubriqueCategorie.getId() != null) {
//            
//            listePrestation = prestationDao.listePrestationByCompagnie(rubriqueCategorie, entreprise);
//            
//            if (listeDetailPolicePlafond.isEmpty()) {
//                detailPolicePlafond = new OrclassDetailPolicePlafond();
//                listeDetailPolicePlafond.add(detailPolicePlafond);
//            }
////            else {
////                detailPolicePlafond = listeDetailPolicePlafond.get(0);
////                if (detailPolicePlafond.getIdPrestation() == null || detailPolicePlafond.getIdPrestation().getId() == null) {
//////                    this.chargeCaracteristiqueRubrique();
////                    return;
////                }
////                detailPolicePlafond = new OrclassDetailPolicePlafond();
////                listeDetailPolicePlafond.add(detailPolicePlafond);
////                this.reverseListe();
////
////            }
//
////            this.chargeCaracteristiqueRubrique();
//            this.updateTablePrestation();
//            this.updateTabledetailPolicePlafond();
//            this.updateDataTablePoliceCarzacteristique();
//            this.updateDataTableRubriqueCaracteristique();
//            this.updateDataTableRubriqueGarantie();
//            this.updateDataTablePoliceGaranties();
//            PrimeFaces.current().ajax().update(":form1:tabprincipal");
//        }
//    }
//    
//    public void chargePrestaionForRubriqueCategorie() {
//        
//        if (refGroupeSelect == null || refGroupeSelect.getId() == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE SELECT VALUE GROUP", "GROUP VALUE IS EMPTY"));
//            return;
//        }
//        List<OrclassPrestation> listePrestation = new ArrayList<>(detailPolicePlafondDao.listePrestationHavaDetailPolice(entreprise, policeSelect, refGroupeSelect));
//        if (!selectePrestations.isEmpty()) {
//            for (OrclassPrestation p : selectePrestations) {
//                if (listePrestation.contains(p) == Boolean.TRUE) {
//                    continue;
//                }
//                detailPolicePlafond = new OrclassDetailPolicePlafond();
//                detailPolicePlafond.setIdPrestation(p);
//                detailPolicePlafond.setIdRubrique(rubriqueCategorie.getIdRubrique());
//                detailPolicePlafond.setIdRubrique(rubriqueCategorie.getIdRubrique());
//                detailPolicePlafond.setTypeAvenant(typeAvenant);
//                detailPolicePlafond.setIdGroup((refGroupeSelect == null || refGroupeSelect.getId() == null) ? null : refGroupeSelect);
//                if (policePlafond == null || policePlafond.getId() == null) {
//                    detailPolicePlafond.setIdPolicePlafond(policePlafondDao.chargePolicePlafonMaladie(policeSelect, entreprise));
//                }
//                detailPolicePlafond.setAjouter(Boolean.TRUE);
//                detailPolicePlafond.setRetire_de_la_police(Boolean.FALSE);
//                detailPolicePlafond.setModifier(Boolean.FALSE);
//                listeDetailPolicePlafond.add(detailPolicePlafond);
//                
//            }
////              this.reverseListe();
////            listePrestation.removeAll(selectePrestations);
//
//            selectePrestations.clear();
//        }
//        this.updateTabledetailPolicePlafond();
//        this.updateTablePrestation();
//        PrimeFaces.current().ajax().update(":form1:idrubriquePrestationTable,:form1");
//        
//    }
//    
//    public void updateTabledetailPolicePlafond() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:iddpmgTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('dpmTable').clearFilters();");
//        
//    }
//    
//    public void updateTablePrestation() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idprestationableTable");
//        table.setValueExpression("sortBy", null);
//        
//        PrimeFaces.current().executeScript("PF('prestationableTable').clearFilters();");
//        
//    }
//    
//    public void reverseListe() {
//        
//        List<OrclassDetailPolicePlafond> result = new ArrayList<>();
//        for (int i = listeDetailPolicePlafond.size() - 1; i >= 0; i--) {
//            result.add(listeDetailPolicePlafond.get(i));
//        }
//        
//        this.setListeDetailPolicePlafond(result);
//        
//    }
//    
//    public void reverseListeCaracteristique() {
//        
//        List<OrclassPoliceCaracteristique> result = new ArrayList<>();
//        for (int i = listePoliceCaracteristique.size() - 1; i >= 0; i--) {
//            result.add(listePoliceCaracteristique.get(i));
//        }
//        
//        this.setListePoliceCaracteristique(result);
//        
//    }
//    
//    public void reverseListePoliceGarantie() {
//        
//        List<OrclassPoliceGarantie> result = new ArrayList<>();
//        for (int i = listePoliceGarantie.size() - 1; i >= 0; i--) {
//            result.add(listePoliceGarantie.get(i));
//        }
//        
//        this.setListePoliceGarantie(result);
//        
//    }
//    
//    public void reverseListePolicedetailPlafond() {
//        
//        List<OrclassDetailPolicePlafond> result = new ArrayList<>();
//        for (int i = listeDetailPolicePlafond.size() - 1; i >= 0; i--) {
//            result.add(listeDetailPolicePlafond.get(i));
//        }
//        
//        this.setListeDetailPolicePlafond(result);
//        
//    }
//    
//    public void ajoutteLigneForListeRisFamily() {
//        risqueFamille = new OrclassRisqueFamille();
//        listeRisqueFamille.add(risqueFamille);
//        updateDataTableRisqueFamille();
//    }
//    
//    public void printNonValide() throws IOException, JRException {
//        print = Boolean.TRUE;
//        validation = Boolean.FALSE;
//        elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
//        if (elt_Categorie_Compagnie == null || elt_Categorie_Compagnie.getId() == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "ELEMENT CATEGORY IS NULL", "PLEASE CHECK AGAINST"));
//            return;
//        }
//        if ((police == null || police.getId() == null)) {
//
////            this.addProjetSante();
//        } else {
//            this.creatEtatPrint();
//        }
//        
//    }
//    
//    public String quittanceByPolice(OrclassPolice p) {
//        String montant = "0";
//        if (p != null && p.getId() != null) {
//            OrclassQuitance quitance = quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, p);
//            if (quitance != null && quitance.getId() != null) {
//                montant = GlobalFonctions.formatNumberGeneral(quitance.getTotal_a_paye().longValue()) + p.getIdDevise().getCode();
//                return montant;
//            }
//        }
//        return montant;
//    }
//    
//    public void validationProjet() throws IOException, JRException {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        print = Boolean.TRUE;
//        validation = Boolean.TRUE;
//        OrclassProposition proposition = null;
//        BigInteger policeValide = BigInteger.ZERO;
//        if (police != null && Objects.equals(police.getValidation(), Boolean.TRUE)) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "the contract has already been validated"));
//            return;
//        }
//        /*
//        ici il est question de recuperer la derniere la ligne d un produit valide de sorte que le numero de validation soit generer automatiquement par ordre de validation du 
//        contrat
//         */
//        policeValide = policeDao.retourneNumeroPoliceByAgenceForCategorieForValidation(intermediaires, categories, entreprise);
//        if (policeValide == BigInteger.ZERO || policeValide == null) {
//            elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
//            policeValide = elt_Categorie_Compagnie.getIdRegistreProduction().getNum_debut();
//            proposition = propositionDao.recuperLapoliceProser(police.getNumero_police(), intermediaires, entreprise, categories);
//            if (proposition != null && proposition.getId() != null) {
//                proposition.setPolice(policeValide);
//                proposition.setIdPolice(police);
//                propositionDao.update(proposition);
//            }
//            
//        } else {
//            policeValide = policeValide.add(BigInteger.ONE);
//            while (policeDao.finKeyPoliceValide(intermediaires, policeValide, entreprise, categories) != null) {
//                policeValide = policeValide.add(BigInteger.ONE);
//            }
//            proposition = propositionDao.recuperLapoliceProser(police.getNumero_police(), intermediaires, entreprise, categories);
//            if (proposition != null && proposition.getId() != null) {
//                proposition.setPolice(policeValide);
//                proposition.setIdPolice(police);
//                propositionDao.update(proposition);
//            }
//            
//        }
//        
//        this.police.setPolice(policeValide);
//        police.setValidation(Boolean.TRUE);
//        police.setDate_validation(new Date());
//        police.setValider_par(user.getNom() + " " + user.getPrenom());
//        policeDao.update(police);
//        
//        this.setNumero_police(policeValide);
//        if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//            for (OrclassRisque r : listeRisque) {
//                this.setRisque(r);
//                
//                risqueDao.update(risque);
//                
//            }
//        } else if ((elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null) && (elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.individuel) || elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.famille))) {
//            
//            risqueDao.update(risque);
//        }
//        
//        assureDao.update(assure);
////            quitance.setDateModification(new Date());
////            quitance.setModifier_par(user.getNom() + " " + user.getPrenom());
////            quitanceDao.update(quitance);
//
//        for (OrclassRisqueFamille rf : listeRisqueFamille) {
//            risqueFamilleDao.update(rf);
//        }
//        for (OrclassDetailPolicePlafond dp : listeDetailPolicePlafond) {
//            detailPolicePlafondDao.update(dp);
//        }
//        for (OrclassPoliceCaracteristique pc : listePoliceCaracteristique) {
//            policeCaracteristiqueDao.update(pc);
//        }
//        
//        quitanceDao.update(quitance);
//        for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//            policeGarantieDao.update(pg);
//            
//        }
//        if (Objects.equals(user.getAllAccessForIntermediaire(), Boolean.TRUE)) {
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraByAgenceForAllEtat(intermediaires, entreprise);
//        } else {
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraByAgenceForAllEtat(intermediaires, entreprise, user);
//        }
//
////        this.updateContrat();
////        this.creatEtatPrint();
//        this.updateDataTableContrat();
////        GlobalFonctions.displayMessages("msg", ctx, FacesMessage.SEVERITY_INFO, "police", Success.OPERATION_SUCCESS + "", new Object[]{policeValide});
//        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, Success.OPERATION_SUCCESS.toString(), "VALID PROJECT"));
//        PrimeFaces.current().ajax().update(":form1");
//    }
//    
//    public void annullationContrat() throws IOException {
//        if (police != null && police.getNumero_police() != null && police.getIdIntermediaire() != null) {
//            police.setAnnulation(Boolean.TRUE);
//            police.setAnnuler_par(user.getNom() + " " + user.getPrenom());
//            policeDao.update(police);
//            this.reload();
//        }
//    }
//    
//    public void updateContrat() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        BigDecimal taxe = BigDecimal.valueOf(19.25);
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (police != null && police.getNumero_police() != null && police.getIdIntermediaire() != null) {
//            police.setDateModification(new Date());
//            police.setModifier_par(user.getNom() + " " + user.getPrenom());
//            policeDao.update(police);
//            if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                for (OrclassRisque r : listeRisque) {
//                    this.setRisque(r);
//                    risque.setDateModification(new Date());
//                    risque.setModifier_par(user.getNom() + " " + user.getPrenom());
//                    risqueDao.update(risque);
//                    
//                }
//            } else if ((elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null) && (elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.individuel) || elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.famille))) {
//                risque.setDateModification(new Date());
//                risque.setModifier_par(user.getNom() + " " + user.getPrenom());
//                risqueDao.update(risque);
//            }
//            
//            assure.setDateModification(new Date());
//            assure.setModifier_par(user.getNom() + " " + user.getPrenom());
//            assureDao.update(assure);
////            quitance.setDateModification(new Date());
////            quitance.setModifier_par(user.getNom() + " " + user.getPrenom());
////            quitanceDao.update(quitance);
//
//            for (OrclassRisqueFamille rf : listeRisqueFamille) {
//                risqueFamilleDao.update(rf);
//            }
//            for (OrclassDetailPolicePlafond dp : listeDetailPolicePlafond) {
//                detailPolicePlafondDao.update(dp);
//            }
//            for (OrclassPoliceCaracteristique pc : listePoliceCaracteristique) {
//                policeCaracteristiqueDao.update(pc);
//            }
//            if (police.getMontantaccessoir() == null) {
//                quitance.setMontant_Accessoire(BigDecimal.ZERO);
//            } else {
//                quitance.setMontant_Accessoire(police.getMontantaccessoir());
//            }
//            
//            quitance.setDate_echeance(police.getDate_echeance());
//            quitance.setPrimeNette(this.totalProrata);
//            quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//            quitance.setPrimeTaxe(quitance.getPrimeNette().add(quitance.getTaxePrime()));
//            quitance.setTaxeAccessoir((quitance.getMontant_Accessoire().multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//            quitance.setAccessoirTaxe(quitance.getMontant_Accessoire().add(quitance.getTaxeAccessoir()));
//            quitance.setTotalTaxes(quitance.getTaxePrime().add(quitance.getTaxeAccessoir()));
////            quitance.getPrimeNette().add(quitance.getTaxePrime()).add(quitance.getMontant_Accessoire()).add(quitance.getTaxeAccessoir()).add(quitance.getTimbreDimension());
//            total_a_paye = quitance.getPrimeNette().add(quitance.getTaxePrime()).add(quitance.getMontant_Accessoire()).add(quitance.getTaxeAccessoir()).add(quitance.getTimbreDimension())
//                    .add(quitance.getTimbreGradue());
//            quitance.setTotal_a_paye(total_a_paye);
//            quitance.setDate_effet(police.getDate_effet());
//            quitance.setDateModification(new Date());
//            quitance.setModifier_par(user.getNom() + " " + user.getPrenom());
//            quitanceDao.update(quitance);
//            for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//                policeGarantieDao.update(pg);
//                
//            }
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "police", Success.UPDATE_SUCCESS + "", new Object[]{numero_police});
//            
//        }
//    }
//    
//    public void addAvenant() throws IOException {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        try {
//
//            //traitement de la photo
//            if (typeAvenant != null && typeAvenant.getId() != null) {
//                if (Objects.equals(tabShowFamille, Boolean.TRUE) || Objects.equals(tabShowIndividuel, Boolean.TRUE)) {
//                    listeRisque.add(risque);
//                }
//                
//                if ((typeAvenant.getNatureAvenant().equals(NatureAvenant.resiliation) || typeAvenant.getNatureAvenant().equals(NatureAvenant.suspension) || typeAvenant.getNatureAvenant().equals(NatureAvenant.terme) || typeAvenant.getNatureAvenant().equals(NatureAvenant.arret_couverture_sans_ristourne)) && (avenant == null || avenant.getId() == null)) {
//                    avenant = new OrclassAvenant();
//                    avenant.setIdPolice(police);
//                    avenant.setIdTypeAvenant(typeAvenant);
//                    avenant.setDateCreation(new Date());
//                    avenant.setIdUtilisateur(user);
//                    avenantDao.create(avenant);
//                    valider = Boolean.TRUE;
//                } else {
//                    
//                    if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                        santeService.chargeElementsTab(arrays_Tab2);
//                        valider = santeService.addAvenantGroupMaladie(entreprise, assure, police, typeAvenant, quitance, policePlafond == null ? new OrclassPolicePlafond() : policePlafond, listeRefGroup, user);
//                    } else {
//                        valider = santeService.addAenant(typeAvenant, assure, police, listeRisque, listeRisqueFamille, policePlafond == null ? new OrclassPolicePlafond() : policePlafond, listeDetailPolicePlafond, listePoliceCaracteristique, listePoliceGarantie, quitance, listePoliceCommissionApporteur, entreprise, user);
//                        
//                    }
//                    
//                }
//                if (Objects.equals(valider, Boolean.TRUE)) {
//                    
//                    this.reload();
//                } else {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("une erreur c est produit … ressayer a nouveau", "an error c is produced ... try again"));
//                    return;
//                }
////                this.chargePrimeApproteur();
//
//            } else {
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "" + numero_police, null);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "avenant", exception.Error.DUPLICATE_ERROR_INSERT + "", new Object[]{typeAvenant.toString()});
//                return;
//            }
//            
//            if (Objects.equals(valider, Boolean.TRUE)) {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "avenant", Success.INSERT_SUCCESS + "", new Object[]{typeAvenant.toString()});
////                this.creatEtatPrint();
//
//            } else {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "avenant", exception.Error.INSERT_ERROR + "", new Object[]{typeAvenant.toString()});
//                
//            }
//            
//            System.out.println("etat insertion-->" + valider);
//            
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, "" + this.numeroAvenant, e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "avenant", exception.Error.FATAL_ERROR + "", new Object[]{"avenant"});
//            logger.error("Erreur Survenu", th);
//        }
//        
//    }
//    
//    public void addAvenantPrint() throws IOException {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        try {
//
//            //traitement de la photo
//            if (typeAvenant != null || typeAvenant.getId() != null) {
//                
//                if (Objects.equals(tabShowFamille, Boolean.TRUE) || Objects.equals(tabShowIndividuel, Boolean.TRUE)) {
//                    listeRisque.add(risque);
//                }
//                if ((typeAvenant.getNatureAvenant().equals(NatureAvenant.resiliation) || typeAvenant.getNatureAvenant().equals(NatureAvenant.suspension) || typeAvenant.getNatureAvenant().equals(NatureAvenant.terme) || typeAvenant.getNatureAvenant().equals(NatureAvenant.arret_couverture_sans_ristourne)) && (avenant == null || avenant.getId() == null)) {
//                    avenant = new OrclassAvenant();
//                    avenant.setIdPolice(police);
//                    avenant.setIdTypeAvenant(typeAvenant);
//                    avenant.setDateCreation(new Date());
//                    avenant.setIdUtilisateur(user);
//                    avenant.setIdEntreprises(entreprise);
//                    avenantDao.create(avenant);
//                    valider = Boolean.TRUE;
//                } else {
//                    if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                        valider = santeService.addAvenantGroupMaladie(entreprise, assure, police, typeAvenant, quitance, policePlafond == null ? new OrclassPolicePlafond() : policePlafond, listeRefGroup, user);
//                    } else {
//                        valider = santeService.addAenant(typeAvenant, assure, police, listeRisque, listeRisqueFamille, policePlafond, listeDetailPolicePlafond, listePoliceCaracteristique, listePoliceGarantie, quitance, listePoliceCommissionApporteur, entreprise, user);
//                        
//                    }
//                    
//                }
//                //                this.chargePrimeApproteur();
//                if (Objects.equals(valider, Boolean.TRUE)) {
//                    this.creatEtatPrint();
//                } else {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("une erreur c est produit … ressayer a nouveau", "an error c is produced ... try again"));
//                    return;
//                }
//                
//            } else {
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "" + numero_police, null);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "avenant", exception.Error.DUPLICATE_ERROR_INSERT + "", new Object[]{typeAvenant.toString()});
//                return;
//            }
//            
//            if (Objects.equals(valider, Boolean.TRUE)) {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "avenant", Success.INSERT_SUCCESS + "", new Object[]{typeAvenant.toString()});
////                this.creatEtatPrint();
//
//            } else {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "avenant", exception.Error.INSERT_ERROR + "", new Object[]{typeAvenant.toString()});
//                
//            }
//            
//            System.out.println("etat insertion-->" + valider);
//            
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, "" + this.numeroAvenant, e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "avenant", exception.Error.FATAL_ERROR + "", new Object[]{"avenant"});
//            logger.error("Erreur Survenu", th);
//            System.err.println(" location Message  " + th.getLocalizedMessage() + " Message " + th.getMessage() + " causse " + th.getCause());
//        }
//        
//    }
//    
//    public void consultationAvenantPrint(OrclassAvenant item) throws IOException, JRException {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        Contrat_Sante contrat_Sante;
//        avenantSante avenantAsante = null;
//        OrclassPolice police = null;
//        List<OrclassPoliceGarantie> colPoliceGarantiePrint = new ArrayList<>();
//        colContrat_Sante.clear();
//        this.setAvenantConsultation(item);
//        elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(avenantConsultation.getIdPolice().getIdCategories(), entreprise);
//        OrclassAssure assures = avenantConsultation.getIdPolice().getIdAssure();
//        String texte = "";
//
//        //traitement de la photo
//        if (avenantConsultation != null && avenantConsultation.getId() != null) {
//            numero_police = (Objects.equals(avenantConsultation.getIdPolice().getValidation(), Boolean.TRUE)) ? avenantConsultation.getIdPolice().getPolice().toBigInteger() : avenantConsultation.getIdPolice().getNumero_police();
//            colPoliceGarantiePrint = policeGarantieDao.listeGarantiesForPoliceHaveAvenant(entreprise, avenantConsultation.getIdPolice(), avenantConsultation);
//            if (!colPoliceGarantiePrint.isEmpty()) {
//                for (OrclassPoliceGarantie pg : colPoliceGarantiePrint) {
//                    avenantAsante = new avenantSante();
//                    //information sur la police et l agance
//                    avenantAsante.setLibelleAgence(this.avenantConsultation.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    avenantAsante.setCodeAgence(this.avenantConsultation.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getCode());
//                    avenantAsante.setPolice("" + this.avenantConsultation.getIdPolice().getPolice());
//                    
//                    avenantAsante.setTelAgence(this.avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse().getTel());
//                    if (this.avenantConsultation.getIdPolice() == null) {
//                        avenantAsante.setRefPolice("");
//                    } else {
//                        avenantAsante.setRefPolice(this.avenantConsultation.getIdPolice().getRef_intermediaire());
//                    }
//                    if (this.avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse() != null && this.avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse().getFax() != null) {
//                        avenantAsante.setFaxAgence(this.avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse().getFax());
//                    } else {
//                        avenantAsante.setFaxAgence("");
//                    }
//                    
//                    avenantAsante.setAdresseBpAgence("" + this.avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse().getBp());
//                    avenantAsante.setVilleAgence(this.avenantConsultation.getIdPolice().getIdIntermediaire().getIdVille().getLibelle());
//                    //information du souscripteur;
////                    assures = avenantConsultation.get;
////                    if (assures != null && assures.getId() != null) {
//                    if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                        avenantAsante.setNomComplet(avenantConsultation.getRaison_social() == null ? avenantConsultation.getIdPolice().getIdAssure().getRaison_social().toUpperCase() : avenantConsultation.getRaison_social().toUpperCase());
//                        
//                    } else {
////                                if (avenantConsultation.getNom()==null) {
//                        avenantAsante.setNomComplet(assures.getNom() == null ? avenantConsultation.getIdPolice().getIdAssure().getNom().toUpperCase() + " " + avenantConsultation.getIdPolice().getIdAssure().getPrenom() : assures.getPrenom() == null ? avenantConsultation.getNom() : avenantConsultation.getNom() + " " + avenantConsultation.getPrenom());
//                        
//                    }
//                    texte = (avenantConsultation.getAdresse() == null || avenantConsultation.getAdresse().getTel() == null) ? assures.getAdresse().getIndicatifPays() : avenantConsultation.getAdresse().getIndicatifPays();
//                    texte += (avenantConsultation.getAdresse() == null || avenantConsultation.getAdresse().getTel() == null) ? assures.getAdresse().getTel() : avenantConsultation.getAdresse().getTel();
//                    avenantAsante.setTel(texte);
//                    avenantAsante.setAdresseBp((avenantConsultation.getAdresse() == null || avenantConsultation.getAdresse().getBp() == null) ? assures.getAdresse().getBp() : avenantConsultation.getAdresse().getBp());
//                    avenantAsante.setActivite(avenantConsultation.getIdActivite() == null ? assures.getIdActivite().getLibelle() : avenantConsultation.getIdActivite().getLibelle());
//                    avenantAsante.setMail((avenantConsultation.getAdresse() == null || avenantConsultation.getAdresse().getEmail() == null) ? assures.getAdresse().getEmail() : avenantConsultation.getAdresse().getEmail());
//                    avenantAsante.setFax((avenantConsultation.getAdresse() == null || avenantConsultation.getAdresse().getFax() == null) ? assures.getAdresse().getFax() : avenantConsultation.getAdresse().getFax());
//                    avenantAsante.setCodeAssure(assures.getCode());
//                    if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                        avenantAsante.setDateNaissance("");
//                    } else {
//                        avenantAsante.setDateNaissance(GlobalFonctions.formatDate(assures.getDate_naissance()) + "- Sexe " + assures.getSexe().name());
//                    }
//
////                    }
//                    //information periode de garantie
//                    avenantAsante.setGarantie(pg.getIdGarantie().getIdRefGaranties().getLibelle());
//                    avenantAsante.setDateDebutGarantie(GlobalFonctions.formatDate(avenantConsultation.getIdPolice().getDate_effet()) + " 00:00 ");
//                    avenantAsante.setDateFinGarantie(GlobalFonctions.formatDate(avenantConsultation.getIdPolice().getDate_echeance()) + " 23:59 ");
//                    avenantAsante.setDureGarantie("" + avenantConsultation.getIdPolice().getValeurDuree() + " " + GlobalFonctions.valueObject(avenantConsultation.getIdPolice().getIdMajorationDuree().getIdDuree().getUniteDuree()));
//                    avenantAsante.setContrat(GlobalFonctions.valueObject(avenantConsultation.getIdPolice().getContrat()) + " " + GlobalFonctions.valueObject(avenantConsultation.getIdPolice().getNatureContrat()));
//
//                    //information sur la quittance
//                    this.quitance = quitanceDao.quittanceForPoliceByAvenant(entreprise, avenantConsultation.getIdPolice(), avenantConsultation);
//                    if (this.quitance != null && this.quitance.getId() != null) {
//                        if (quitance.getMontant_Accessoire() == null || quitance.getMontant_Accessoire().intValue() == 0) {
////                            avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(this.police.getMontantaccessoir().longValue()));
//                            avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(BigDecimal.ZERO.longValue()));
//                        } else {
//                            avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(this.quitance.getMontant_Accessoire().longValue()));
//                        }
//                        
//                        avenantAsante.setDevise(this.avenantConsultation.getIdPolice().getIdDevise().getCode());
//                        avenantAsante.setTotalTaxe(GlobalFonctions.formatNumberGeneral(this.quitance.getTotalTaxes().longValue()));
//                        avenantAsante.setPrimeNetteQuittance(GlobalFonctions.formatNumberGeneral(this.quitance.getPrimeNette().longValue()));
//                        avenantAsante.setTotal_a_paye(GlobalFonctions.formatNumberGeneral(this.quitance.getTotal_a_paye().longValue()));
//                        
//                    }
//
//                    // information sur la garantie
//                    avenantAsante.setGarantie(pg.getIdGarantie().getIdRefGaranties().getLibelle());
//                    if (pg.getCapital() == null) {
//                        avenantAsante.setCapital("");
//                    } else {
//                        avenantAsante.setCapital(GlobalFonctions.formatNumberGeneral(pg.getCapital().longValue()));
//                    }
//                    
//                    if (pg.getTaux() == null) {
//                        avenantAsante.setTaux("");
//                    } else {
//                        avenantAsante.setTaux(GlobalFonctions.formatNumberGeneral(pg.getTaux().longValue()));
//                    }
//                    
//                    if (pg.getPourcentage() == null) {
//                        avenantAsante.setPourcentage("");
//                    } else {
//                        avenantAsante.setPourcentage(GlobalFonctions.formatNumberGeneral(pg.getPourcentage().longValue()));
//                    }
//                    
//                    if (pg.getPrime() == null) {
//                        avenantAsante.setPrime("");
//                    } else {
//                        avenantAsante.setPrime(GlobalFonctions.formatNumberGeneral(pg.getPrime().longValue()));
//                    }
//                    
//                    if (pg.getProrata() == null) {
//                        avenantAsante.setProrate("");
//                    } else {
//                        avenantAsante.setProrate(GlobalFonctions.formatNumberGeneral(pg.getProrata().longValue()));
//                    }
//                    
//                    if (avenantConsultation.getIdPolice().getIdCategories().getLibelleAutre() == null) {
//                        avenantAsante.setCategorie(GlobalFonctions.valueObject(avenantConsultation.getIdPolice().getIdCategories().getLibelle()));
//                    } else {
//                        avenantAsante.setCategorie(avenantConsultation.getIdPolice().getIdCategories().getLibelleAutre());
//                    }
//                    //infos sur l avenant
//                    if (this.avenantConsultation.getDate_effet() != null) {
//                        avenantAsante.setDateAvenantEffet(GlobalFonctions.formatDate(this.avenantConsultation.getDate_effet()));
//                    } else {
//                        avenantAsante.setDateAvenantEffet(GlobalFonctions.formatDate(this.avenantConsultation.getIdPolice().getDate_effet()));
//                    }
//                    if (this.avenantConsultation.getDate_echeance() != null) {
//                        avenantAsante.setDateAvenantEcheance(GlobalFonctions.formatDate(this.avenantConsultation.getDate_echeance()));
//                    } else {
//                        avenantAsante.setDateAvenantEcheance(GlobalFonctions.formatDate(this.avenantConsultation.getIdPolice().getDate_echeance()));
//                    }
//                    avenantAsante.setDateAvenant(GlobalFonctions.formatDate(this.avenantConsultation.getDateCreation()));
//                    if (this.avenantConsultation.getIdUtilisateur().getPrenom() != null) {
//                        avenantAsante.setUserAvenant(this.avenantConsultation.getIdUtilisateur().getNom() + " " + this.avenantConsultation.getIdUtilisateur().getPrenom());
//                    } else {
//                        avenantAsante.setUserAvenant(this.avenantConsultation.getIdUtilisateur().getNom());
//                    }
//                    avenantAsante.setTypeAvenant(this.avenantConsultation.getIdTypeAvenant().getIdRefAvenant().getLibelle());
//                    avenantAsante.setNumAvenant("" + avenantConsultation.getNumero_avenant());
//                    if (avenantConsultation.getIdProfession() != null && avenantConsultation.getIdProfession().getId() != null) {
//                        avenantAsante.setProfession(avenantConsultation.getIdProfession().getLibelle());
//                    } else {
//                        avenantAsante.setProfession(assures.getIdProfession() == null ? "NI" : assures.getIdProfession().getLibelle());
//                    }
//                    colAvenantSante.add(avenantAsante);
//                    
//                }
//            } else {
//                avenantAsante = new avenantSante();
//                //information sur la police et l agance
//                avenantAsante.setLibelleAgence(avenantConsultation.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                avenantAsante.setCodeAgence(this.avenantConsultation.getIdPolice().getIdIntermediaire().getIdRefIntermediaire().getCode());
//                avenantAsante.setPolice("" + avenantConsultation.getIdPolice().getPolice());
//                
//                avenantAsante.setTelAgence(avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse().getTel());
//                if (avenantConsultation.getIdPolice().getRef_intermediaire() == null) {
//                    avenantAsante.setRefPolice("");
//                } else {
//                    avenantAsante.setRefPolice(avenantConsultation.getIdPolice().getRef_intermediaire());
//                }
//                if (avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse() != null && avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse().getFax() != null) {
//                    avenantAsante.setFaxAgence(avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse().getFax());
//                } else {
//                    avenantAsante.setFaxAgence("");
//                }
//                
//                avenantAsante.setAdresseBpAgence("" + avenantConsultation.getIdPolice().getIdIntermediaire().getAdresse().getBp());
//                avenantAsante.setVilleAgence(avenantConsultation.getIdPolice().getIdIntermediaire().getIdVille().getLibelle());
//                //information du souscripteur;
////                if (assures != null && assures.getId() != null) {
////                    if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
////                        avenantAsante.setNomComplet(assures.getRaison_social());
////                    } else {
////                        avenantAsante.setNomComplet(assures.getNom());
////                        if (assure.getPrenom() != null) {
////                            avenantAsante.setNomComplet(assures.getNom() + " " + assures.getPrenom());
////
////                        }
////                    }
//                if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                    avenantAsante.setNomComplet(avenantConsultation.getRaison_social() == null ? avenantConsultation.getIdPolice().getIdAssure().getRaison_social().toUpperCase() : avenantConsultation.getRaison_social().toUpperCase());
//                    
//                } else {
////                                if (avenantConsultation.getNom()==null) {
//                    avenantAsante.setNomComplet(avenantConsultation.getNom() == null ? avenantConsultation.getIdPolice().getIdAssure().getNom().toUpperCase() + " " + avenantConsultation.getIdPolice().getIdAssure().getPrenom() : assures.getPrenom() == null ? avenantConsultation.getNom() : avenantConsultation.getNom() + " " + avenantConsultation.getPrenom());
//                    
//                }
//                String tel = (avenantConsultation.getAdresse() == null || avenantConsultation.getAdresse().getIndicatifPays() == null) ? avenantConsultation.getIdPolice().getIdAssure().getAdresse().getIndicatifPays() : avenantConsultation.getAdresse().getIndicatifPays();
//                tel += (avenantConsultation.getAdresse() == null || avenantConsultation.getAdresse().getTel() == null) ? avenantConsultation.getIdPolice().getIdAssure().getAdresse().getTel() : avenantConsultation.getAdresse().getTel();
////                 avenantAsante.setTel(avenantConsultation.getAdresse() == null ? avenantConsultation.getIdPolice().getIdAssure().getAdresse().getIndicatifPays() + "" + avenantConsultation.getIdPolice().getIdAssure().getAdresse().getTel() : avenantConsultation.getAdresse().getIndicatifPays() + " " + avenantConsultation.getAdresse().getTel());
//                avenantAsante.setTel(tel);
//                
//                avenantAsante.setAdresseBp(avenantConsultation.getAdresse() == null ? avenantConsultation.getIdPolice().getIdAssure().getAdresse().getBp() : avenantConsultation.getAdresse().getBp());
//                avenantAsante.setActivite(avenantConsultation.getIdActivite() == null ? avenantConsultation.getIdPolice().getIdAssure().getIdActivite().getLibelle() : avenantConsultation.getIdActivite().getLibelle());
//                avenantAsante.setMail(avenantConsultation.getAdresse() == null ? avenantConsultation.getIdPolice().getIdAssure().getAdresse().getEmail() : avenantConsultation.getAdresse().getEmail());
//                avenantAsante.setFax(avenantConsultation.getAdresse() == null ? avenantConsultation.getIdPolice().getIdAssure().getAdresse().getFax() : avenantConsultation.getAdresse().getFax());
//                avenantAsante.setCodeAssure(avenantConsultation.getIdPolice().getIdAssure().getCode());
//                if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                    avenantAsante.setDateNaissance("");
//                } else {
//                    String date_naissance = GlobalFonctions.formatDate(avenantConsultation.getDate_naissance() == null ? avenantConsultation.getIdPolice().getIdAssure().getDate_naissance() : avenantConsultation.getDate_naissance());
//                    
//                    date_naissance += "- Sexe ";
//                    date_naissance += avenantConsultation.getSexe() == null ? avenantConsultation.getIdPolice().getIdAssure().getSexe().toString() : avenantConsultation.getSexe().toString();
//                    
//                    avenantAsante.setDateNaissance(date_naissance);
//                }
//
////                }
//                //information periode de garantie
//                avenantAsante.setGarantie("");
//                avenantAsante.setDateDebutGarantie(GlobalFonctions.formatDate(avenantConsultation.getIdPolice().getDate_effet()) + " 00:00 ");
//                avenantAsante.setDateFinGarantie(GlobalFonctions.formatDate(avenantConsultation.getIdPolice().getDate_echeance()) + " 23:59 ");
//                avenantAsante.setDureGarantie("" + avenantConsultation.getIdPolice().getValeurDuree() + " " + GlobalFonctions.valueObject(avenantConsultation.getIdPolice().getIdMajorationDuree().getIdDuree().getUniteDuree()));
//                avenantAsante.setContrat(GlobalFonctions.valueObject(avenantConsultation.getIdPolice().getContrat()) + " " + GlobalFonctions.valueObject(avenantConsultation.getIdPolice().getNatureContrat()));
//
//                //information sur la quittance
//                this.quitance = quitanceDao.quittanceForPoliceByAvenant(entreprise, this.avenantConsultation.getIdPolice(), avenantConsultation);
//                if (this.quitance != null && this.quitance.getId() != null) {
//                    if (quitance.getMontant_Accessoire() == null || quitance.getMontant_Accessoire().intValue() == 0) {
////                        if (ancienMontantAccessoire==police.getMontantaccessoir()) {
////                              avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(this.police.getMontantaccessoir().longValue()));
////                        }
//                        avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(BigDecimal.ZERO.longValue()));
//                        
//                    } else {
//                        avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(this.quitance.getMontant_Accessoire().longValue()));
//                    }
//                    avenantAsante.setDevise(avenantConsultation.getIdPolice().getIdDevise().getCode());
//                    avenantAsante.setTotalTaxe(GlobalFonctions.formatNumberGeneral(this.quitance.getTotalTaxes().longValue()));
//                    avenantAsante.setPrimeNetteQuittance(GlobalFonctions.formatNumberGeneral(this.quitance.getPrimeNette().longValue()));
//                    avenantAsante.setTotal_a_paye(GlobalFonctions.formatNumberGeneral(this.quitance.getTotal_a_paye().longValue()));
//                    
//                }
//
//                // information sur la garantie
//                avenantAsante.setGarantie("");
//                
//                avenantAsante.setCapital("");
//                
//                avenantAsante.setTaux("");
//                
//                avenantAsante.setPourcentage("");
//                
//                avenantAsante.setPrime("");
//                
//                avenantAsante.setProrate("");
//                
//                if (this.avenantConsultation.getIdPolice().getIdCategories().getLibelleAutre() == null) {
//                    avenantAsante.setCategorie(GlobalFonctions.valueObject(this.avenantConsultation.getIdPolice().getIdCategories().getLibelle()));
//                } else {
//                    avenantAsante.setCategorie(this.avenantConsultation.getIdPolice().getIdCategories().getLibelleAutre());
//                }
//                //infos sur l avenant
//                if (this.avenantConsultation.getDate_effet() != null) {
//                    avenantAsante.setDateAvenantEffet(GlobalFonctions.formatDate(this.avenantConsultation.getDate_effet()));
//                } else {
//                    avenantAsante.setDateAvenantEffet(GlobalFonctions.formatDate(this.avenantConsultation.getIdPolice().getDate_effet()));
//                }
//                if (this.avenantConsultation.getDate_echeance() != null) {
//                    avenantAsante.setDateAvenantEcheance(GlobalFonctions.formatDate(this.avenantConsultation.getDate_echeance()));
//                } else {
//                    avenantAsante.setDateAvenantEcheance(GlobalFonctions.formatDate(this.avenantConsultation.getIdPolice().getDate_echeance()));
//                }
//                avenantAsante.setDateAvenant(GlobalFonctions.formatDate(this.avenantConsultation.getDateCreation()));
//                
//                if (this.avenantConsultation.getIdUtilisateur().getPrenom() != null) {
//                    avenantAsante.setUserAvenant(this.avenantConsultation.getIdUtilisateur().getNom() + " " + this.avenantConsultation.getIdUtilisateur().getPrenom());
//                } else {
//                    avenantAsante.setUserAvenant(this.avenantConsultation.getIdUtilisateur().getNom());
//                }
//                avenantAsante.setTypeAvenant(avenantConsultation.getIdTypeAvenant().getIdRefAvenant().getLibelle());
//                avenantAsante.setNumAvenant("" + avenantConsultation.getNumero_avenant().intValue());
//                if (avenantConsultation.getIdProfession() != null && avenantConsultation.getIdProfession().getId() != null) {
//                    avenantAsante.setProfession(avenantConsultation.getIdProfession().getLibelle());
//                } else {
//                    if (avenantConsultation.getIdPolice().getIdAssure().getIdProfession() != null && avenantConsultation.getIdPolice().getIdAssure().getIdProfession().getId() != null) {
//                        avenantAsante.setProfession(avenantConsultation.getIdPolice().getIdAssure().getIdProfession().getLibelle());
//                    } else {
//                        avenantAsante.setProfession("");
//                    }
//                    
//                }
//                colAvenantSante.add(avenantAsante);
//            }
//            
//            this.printEtatAvenantConsultation();
//            this.reload();
//        }
//        
//    }
//    
//    public void editNameForMemberFamille(OrclassRisqueFamille item) {
//        if (typeAvenant != null && typeAvenant.getId() != null && (item.getNom_membre() != null || item.getNom_membre() != "")) {
//            int index = listeRisqueFamille.indexOf(item);
//            item.setTypeAvenant(typeAvenant);
//            item.setIdRisque(risque);
//            listeRisqueFamille.set(index, item);
//            updateDataTableRisqueFamille();
//        }
//        
//    }
//    
//    public void creatEtatPrint() throws IOException, JRException {
//        Contrat_Sante contrat_Sante;
//        avenantSante avenantAsante = null;
//        OrclassPolice police = null;
//        List<OrclassPoliceGarantie> colPoliceGarantiePrint = new ArrayList<>();
//        colContrat_Sante.clear();
//        this.setPoliceSelect(policeDao.find(this.police.getId()));
//        if (typeAvenant == null || typeAvenant.getId() == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("Error Please select Value... the value is empty"));
//            return;
//        }
//        this.avenant = avenantDao.lastAvenantByPolice(this.police, entreprise);
//        if (avenant != null && avenant.getId() != null) {
//            colPoliceGarantiePrint = policeGarantieDao.listeGarantiesForPoliceHaveAvenant(entreprise, this.police, avenant);
//            if (!colPoliceGarantiePrint.isEmpty()) {
//                for (OrclassPoliceGarantie pg : colPoliceGarantiePrint) {
//                    avenantAsante = new avenantSante();
//                    //information sur la police et l agance
//                    avenantAsante.setLibelleAgence(this.police.getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                    avenantAsante.setCodeAgence(this.police.getIdIntermediaire().getIdRefIntermediaire().getCode());
//                    avenantAsante.setPolice("" + this.police.getPolice());
//                    
//                    avenantAsante.setTelAgence(this.police.getIdIntermediaire().getAdresse().getTel());
//                    if (this.police.getRef_intermediaire() == null) {
//                        avenantAsante.setRefPolice("");
//                    } else {
//                        avenantAsante.setRefPolice(this.police.getRef_intermediaire());
//                    }
//                    if (this.police.getIdIntermediaire().getAdresse() != null && this.police.getIdIntermediaire().getAdresse().getFax() != null) {
//                        avenantAsante.setFaxAgence(this.police.getIdIntermediaire().getAdresse().getFax());
//                    } else {
//                        avenantAsante.setFaxAgence("");
//                    }
//                    
//                    avenantAsante.setAdresseBpAgence("" + this.police.getIdIntermediaire().getAdresse().getBp());
//                    avenantAsante.setVilleAgence(this.police.getIdIntermediaire().getIdVille().getLibelle());
//                    //information du souscripteur;
//                    if (assure != null && assure.getId() != null) {
//                        if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                            avenantAsante.setNomComplet(assure.getRaison_social());
//                        } else {
//                            avenantAsante.setNomComplet(assure.getNom());
//                            if (assure.getPrenom() != null) {
//                                avenantAsante.setNomComplet(assure.getNom() + " " + assure.getPrenom());
//                                
//                            }
//                        }
//                        
//                        avenantAsante.setTel(assure.getAdresse().getIndicatifPays() + "" + assure.getAdresse().getTel());
//                        avenantAsante.setAdresseBp(assure.getAdresse().getBp());
//                        avenantAsante.setActivite(assure.getIdActivite().getLibelle());
//                        avenantAsante.setMail(assure.getAdresse().getEmail());
//                        avenantAsante.setFax(assure.getAdresse().getFax());
//                        avenantAsante.setCodeAssure(assure.getCode());
//                        if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                            avenantAsante.setDateNaissance("");
//                        } else {
//                            avenantAsante.setDateNaissance(GlobalFonctions.formatDate(assure.getDate_naissance()) + "- Sexe " + assure.getSexe().name());
//                        }
//                        
//                    }
//                    //information periode de garantie
//                    avenantAsante.setGarantie(pg.getIdGarantie().getIdRefGaranties().getLibelle());
//                    avenantAsante.setDateDebutGarantie(GlobalFonctions.formatDate(policeSelect.getDate_effet()) + " 00:00 ");
//                    avenantAsante.setDateFinGarantie(GlobalFonctions.formatDate(policeSelect.getDate_echeance()) + " 23:59 ");
//                    avenantAsante.setDureGarantie("" + policeSelect.getValeurDuree() + " " + GlobalFonctions.valueObject(policeSelect.getIdMajorationDuree().getIdDuree().getUniteDuree()));
//                    avenantAsante.setContrat(GlobalFonctions.valueObject(policeSelect.getContrat()) + " " + GlobalFonctions.valueObject(policeSelect.getNatureContrat()));
//
//                    //information sur la quittance
//                    this.quitance = quitanceDao.quittanceForPoliceByAvenant(entreprise, policeSelect, avenant);
//                    if (this.quitance != null && this.quitance.getId() != null) {
//                        if (quitance.getMontant_Accessoire() == null || quitance.getMontant_Accessoire().intValue() == 0) {
////                            avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(this.police.getMontantaccessoir().longValue()));
//                            avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(BigDecimal.ZERO.longValue()));
//                        } else {
//                            avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(this.quitance.getMontant_Accessoire().longValue()));
//                        }
//                        
//                        avenantAsante.setDevise(this.police.getIdDevise().getCode());
//                        avenantAsante.setTotalTaxe(GlobalFonctions.formatNumberGeneral(this.quitance.getTotalTaxes().longValue()));
//                        avenantAsante.setPrimeNetteQuittance(GlobalFonctions.formatNumberGeneral(this.quitance.getPrimeNette().longValue()));
//                        avenantAsante.setTotal_a_paye(GlobalFonctions.formatNumberGeneral(this.quitance.getTotal_a_paye().longValue()));
//                        
//                    }
//
//                    // information sur la garantie
//                    avenantAsante.setGarantie(pg.getIdGarantie().getIdRefGaranties().getLibelle());
//                    if (pg.getCapital() == null) {
//                        avenantAsante.setCapital("");
//                    } else {
//                        avenantAsante.setCapital(GlobalFonctions.formatNumberGeneral(pg.getCapital().longValue()));
//                    }
//                    
//                    if (pg.getTaux() == null) {
//                        avenantAsante.setTaux("");
//                    } else {
//                        avenantAsante.setTaux(GlobalFonctions.formatNumberGeneral(pg.getTaux().longValue()));
//                    }
//                    
//                    if (pg.getPourcentage() == null) {
//                        avenantAsante.setPourcentage("");
//                    } else {
//                        avenantAsante.setPourcentage(GlobalFonctions.formatNumberGeneral(pg.getPourcentage().longValue()));
//                    }
//                    
//                    if (pg.getPrime() == null) {
//                        avenantAsante.setPrime("");
//                    } else {
//                        avenantAsante.setPrime(GlobalFonctions.formatNumberGeneral(pg.getPrime().longValue()));
//                    }
//                    
//                    if (pg.getProrata() == null) {
//                        avenantAsante.setProrate("");
//                    } else {
//                        avenantAsante.setProrate(GlobalFonctions.formatNumberGeneral(pg.getProrata().longValue()));
//                    }
//                    
//                    if (policeSelect.getIdCategories().getLibelleAutre() == null) {
//                        avenantAsante.setCategorie(GlobalFonctions.valueObject(policeSelect.getIdCategories().getLibelle()));
//                    } else {
//                        avenantAsante.setCategorie(policeSelect.getIdCategories().getLibelleAutre());
//                    }
//                    //infos sur l avenant
//                    if (this.avenant.getDate_effet() != null) {
//                        avenantAsante.setDateAvenantEffet(GlobalFonctions.formatDate(this.avenant.getDate_effet()));
//                    } else {
//                        avenantAsante.setDateAvenantEffet(GlobalFonctions.formatDate(this.police.getDate_effet()));
//                    }
//                    if (this.avenant.getDate_echeance() != null) {
//                        avenantAsante.setDateAvenantEcheance(GlobalFonctions.formatDate(this.avenant.getDate_echeance()));
//                    } else {
//                        avenantAsante.setDateAvenantEcheance(GlobalFonctions.formatDate(this.police.getDate_echeance()));
//                    }
//                    avenantAsante.setDateAvenant(GlobalFonctions.formatDate(this.avenant.getDateCreation()));
//                    if (this.avenant.getIdUtilisateur().getPrenom() != null) {
//                        avenantAsante.setUserAvenant(this.avenant.getIdUtilisateur().getNom() + " " + this.avenant.getIdUtilisateur().getPrenom());
//                    } else {
//                        avenantAsante.setUserAvenant(this.avenant.getIdUtilisateur().getNom());
//                    }
//                    avenantAsante.setTypeAvenant(typeAvenant.getIdRefAvenant().getLibelle());
//                    avenantAsante.setNumAvenant("" + avenant.getNumero_avenant());
//                    if (avenant.getIdProfession() != null && avenant.getIdProfession().getId() != null) {
//                        avenantAsante.setProfession(avenant.getIdProfession().getLibelle());
//                    } else {
//                        avenantAsante.setProfession(assure.getIdProfession().getLibelle());
//                    }
//                    colAvenantSante.add(avenantAsante);
//                    
//                }
//            } else {
//                avenantAsante = new avenantSante();
//                //information sur la police et l agance
//                avenantAsante.setLibelleAgence(policeSelect.getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                avenantAsante.setCodeAgence(this.police.getIdIntermediaire().getIdRefIntermediaire().getCode());
//                avenantAsante.setPolice("" + policeSelect.getPolice());
//                
//                avenantAsante.setTelAgence(policeSelect.getIdIntermediaire().getAdresse().getTel());
//                if (policeSelect.getRef_intermediaire() == null) {
//                    avenantAsante.setRefPolice("");
//                } else {
//                    avenantAsante.setRefPolice(policeSelect.getRef_intermediaire());
//                }
//                if (policeSelect.getIdIntermediaire().getAdresse() != null && policeSelect.getIdIntermediaire().getAdresse().getFax() != null) {
//                    avenantAsante.setFaxAgence(policeSelect.getIdIntermediaire().getAdresse().getFax());
//                } else {
//                    avenantAsante.setFaxAgence("");
//                }
//                
//                avenantAsante.setAdresseBpAgence("" + policeSelect.getIdIntermediaire().getAdresse().getBp());
//                avenantAsante.setVilleAgence(policeSelect.getIdIntermediaire().getIdVille().getLibelle());
//                //information du souscripteur;
//                if (assure != null && assure.getId() != null) {
//                    if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                        avenantAsante.setNomComplet(assure.getRaison_social());
//                    } else {
//                        avenantAsante.setNomComplet(assure.getNom());
//                        if (assure.getPrenom() != null) {
//                            avenantAsante.setNomComplet(assure.getNom() + " " + assure.getPrenom());
//                            
//                        }
//                    }
//                    
//                    avenantAsante.setTel(assure.getAdresse().getIndicatifPays() + "" + assure.getAdresse().getTel());
//                    avenantAsante.setAdresseBp(assure.getAdresse().getBp());
//                    avenantAsante.setActivite(assure.getIdActivite().getLibelle());
//                    avenantAsante.setMail(assure.getAdresse().getEmail());
//                    avenantAsante.setFax(assure.getAdresse().getFax());
//                    avenantAsante.setCodeAssure(assure.getCode());
//                    if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                        avenantAsante.setDateNaissance("");
//                    } else {
//                        avenantAsante.setDateNaissance(GlobalFonctions.formatDate(assure.getDate_naissance()) + "- Sexe " + assure.getSexe().name());
//                    }
//                    
//                }
//                //information periode de garantie
//                avenantAsante.setGarantie("");
//                avenantAsante.setDateDebutGarantie(GlobalFonctions.formatDate(policeSelect.getDate_effet()) + " 00:00 ");
//                avenantAsante.setDateFinGarantie(GlobalFonctions.formatDate(policeSelect.getDate_echeance()) + " 23:59 ");
//                avenantAsante.setDureGarantie("" + policeSelect.getValeurDuree() + " " + GlobalFonctions.valueObject(policeSelect.getIdMajorationDuree().getIdDuree().getUniteDuree()));
//                avenantAsante.setContrat(GlobalFonctions.valueObject(policeSelect.getContrat()) + " " + GlobalFonctions.valueObject(policeSelect.getNatureContrat()));
//
//                //information sur la quittance
//                this.quitance = quitanceDao.quittanceForPoliceByAvenant(entreprise, this.police, avenant);
//                if (this.quitance != null && this.quitance.getId() != null) {
//                    if (quitance.getMontant_Accessoire() == null || quitance.getMontant_Accessoire().intValue() == 0) {
////                        if (ancienMontantAccessoire==police.getMontantaccessoir()) {
////                              avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(this.police.getMontantaccessoir().longValue()));
////                        }
//                        avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(BigDecimal.ZERO.longValue()));
//                        
//                    } else {
//                        avenantAsante.setAccessoire(GlobalFonctions.formatNumberGeneral(this.quitance.getMontant_Accessoire().longValue()));
//                    }
//                    avenantAsante.setDevise(this.police.getIdDevise().getCode());
//                    avenantAsante.setTotalTaxe(GlobalFonctions.formatNumberGeneral(this.quitance.getTotalTaxes().longValue()));
//                    avenantAsante.setPrimeNetteQuittance(GlobalFonctions.formatNumberGeneral(this.quitance.getPrimeNette().longValue()));
//                    avenantAsante.setTotal_a_paye(GlobalFonctions.formatNumberGeneral(this.quitance.getTotal_a_paye().longValue()));
//                    
//                }
//
//                // information sur la garantie
//                avenantAsante.setGarantie("");
//                
//                avenantAsante.setCapital("");
//                
//                avenantAsante.setTaux("");
//                
//                avenantAsante.setPourcentage("");
//                
//                avenantAsante.setPrime("");
//                
//                avenantAsante.setProrate("");
//                
//                if (this.police.getIdCategories().getLibelleAutre() == null) {
//                    avenantAsante.setCategorie(GlobalFonctions.valueObject(this.police.getIdCategories().getLibelle()));
//                } else {
//                    avenantAsante.setCategorie(this.police.getIdCategories().getLibelleAutre());
//                }
//                //infos sur l avenant
//                if (this.avenant.getDate_effet() != null) {
//                    avenantAsante.setDateAvenantEffet(GlobalFonctions.formatDate(this.avenant.getDate_effet()));
//                } else {
//                    avenantAsante.setDateAvenantEffet(GlobalFonctions.formatDate(this.avenant.getIdPolice().getDate_effet()));
//                }
//                if (this.avenant.getDate_echeance() != null) {
//                    avenantAsante.setDateAvenantEcheance(GlobalFonctions.formatDate(this.avenant.getDate_echeance()));
//                } else {
//                    avenantAsante.setDateAvenantEcheance(GlobalFonctions.formatDate(this.avenant.getIdPolice().getDate_echeance()));
//                }
//                avenantAsante.setDateAvenant(GlobalFonctions.formatDate(this.avenant.getDateCreation()));
//                
//                if (this.avenant.getIdUtilisateur().getPrenom() != null) {
//                    avenantAsante.setUserAvenant(this.avenant.getIdUtilisateur().getNom() + " " + this.avenant.getIdUtilisateur().getPrenom());
//                } else {
//                    avenantAsante.setUserAvenant(this.avenant.getIdUtilisateur().getNom());
//                }
//                avenantAsante.setTypeAvenant(typeAvenant.getIdRefAvenant().getLibelle());
//                avenantAsante.setNumAvenant("" + avenant.getNumero_avenant().intValue());
//                if (avenant.getIdProfession() != null && avenant.getIdProfession().getId() != null) {
//                    avenantAsante.setProfession(avenant.getIdProfession().getLibelle());
//                } else {
//                    if (assure.getIdProfession() != null && assure.getIdProfession().getId() != null) {
//                        avenantAsante.setProfession(assure.getIdProfession().getLibelle());
//                    } else {
//                        avenantAsante.setProfession("");
//                    }
//                    
//                }
//                colAvenantSante.add(avenantAsante);
//            }
//            
//        }
//        
//        this.printEtatAvenant();
//        this.reload();
//    }
//    
//    public void showDetailImageRisque(OrclassImageRisque item) {
//        this.setImageRisque(item);
//    }
//    
//    public void showDetailRisque(OrclassRisque item) {
//        this.setRisque(item);
//    }
//    
//    public void chrgaLigneForAddRisque() {
//        risque = new OrclassRisque();
//        risque.setIdGroup((refGroupeSelect == null || refGroupeSelect.getId() == null) ? null : refGroupeSelect);
//        risque.setAjouter(Boolean.TRUE);
//        risque.setRetire_de_la_police(Boolean.FALSE);
//        risque.setModifier(Boolean.FALSE);
//        risque.setTypeAvenant(typeAvenant);
//        listeRisque.add(risque);
//        this.reverseListeRique();
//        this.updateTableRisque();
//    }
//    
//    public void printEtatAvenantConsultation() throws IOException, JRException {
//        Map<String, Object> map = new HashMap<>();
////        ComparatorChain chain = new ComparatorChain();
//        ImageIcon logo = null;
//        ImageIcon img = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        if (entreprise.getImage() != null) {
//            logo = new ImageIcon((byte[]) entreprise.getImage());
//            
//            map.put("logo", logo.getImage());
//        }
//        map.put("societe", entreprise.getRaisonSociale());
//        map.put("tel", entreprise.getAdresse().getTel());
//        map.put("bp", entreprise.getAdresse().getBp());
//        map.put("date", GlobalFonctions.formatDate(new Date()));
//        if (Objects.equals(avenantConsultation.getValidation(), Boolean.FALSE)) {
//            map.put("proposition", "PROPOSITION");
//        } else {
//            map.put("proposition", "");
//        }
//// GlobalFonctions.printPdf(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierAvenant + File.separator + GlobalFonctions.dossierDefauts + File.separator + "avenant", listeAvenantByPolice, map, null);
////     
//        GlobalFonctions.printPdf(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierAvenant + File.separator + GlobalFonctions.dossierDefauts + File.separator + "avenant", colAvenantSante, map, null);
////        momtantTT = BigDecimal.ZERO;
//    }
//    
//    public void printEtatAvenant() throws IOException, JRException {
//        Map<String, Object> map = new HashMap<>();
////        ComparatorChain chain = new ComparatorChain();
//        ImageIcon logo = null;
//        ImageIcon img = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        
//        if (entreprise.getImage() != null) {
//            logo = new ImageIcon((byte[]) entreprise.getImage());
//            
//            map.put("logo", logo.getImage());
//        }
//        map.put("societe", entreprise.getRaisonSociale());
//        map.put("tel", entreprise.getAdresse().getTel());
//        map.put("bp", entreprise.getAdresse().getBp());
//        map.put("date", GlobalFonctions.formatDate(new Date()));
//        if (numero_police.signum() < 0) {
//            map.put("proposition", "PROPOSITION");
//        } else {
//            map.put("proposition", "");
//        }
//
////        if (paramModule.getParamModule().getImageFillGramme() == true) {
////            if (paramModule.getParamModule().getImage_fond() != null) {
////                map.put("image", paramModule.getParamModule().getImage_fond());
////            }
////
////        }
////        if (Objects.equals(paramModule.getParamModule().getChargeImageCachet(), Boolean.TRUE) && paramModule.getParamModule().getImage() != null) {
//////            if (logo != null) {
////            img = new ImageIcon((byte[]) entreprise.getEmployeur().getLogos());
////            map.put("cachet", img.getImage());
//////            }
////
////        }
////        map.put("annee", mois.getAnnee().getId());
////        map.put("montant", GlobalFonctions.formatNumberGeneral(momtantTT.longValue()));
////
////        map.put("mois", LocaleHelper.getLocaleString(RecupBundle.FichierBundle, mois.getTypeMois().name(), null, myLoc));
////
//////        chain.addComparator(SalarierEntreprise.ByMoisASC);
//////        chain.addComparator(SalarierEntreprise.ByNomASC);
//////        Collections.sort((List<SalarierEntreprise>) colSalaireEntreprises, chain);
//        GlobalFonctions.printPdf(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierAvenant + File.separator + GlobalFonctions.dossierDefauts + File.separator + "avenant", colAvenantSante, map, null);
////        momtantTT = BigDecimal.ZERO;
//    }
//    
//    public Pays getPays() {
//        return pays;
//    }
//    
//    public void setPays(Pays pays) {
//        this.pays = pays;
//    }
//    
//    public List<OrclassCategories> getLisCategoriesBySante() {
//        return lisCategoriesBySante;
//    }
//    
//    public void setLisCategoriesBySante(List<OrclassCategories> lisCategoriesBySante) {
//        this.lisCategoriesBySante = lisCategoriesBySante;
//    }
//    
//    public List<OrclassActivite> getListActivite() {
//        return listActivite;
//    }
//    
//    public void setListActivite(List<OrclassActivite> listActivite) {
//        this.listActivite = listActivite;
//    }
//    
//    public List<OrclassQualite> getListQualite() {
//        return listQualite;
//    }
//    
//    public void setListQualite(List<OrclassQualite> listQualite) {
//        this.listQualite = listQualite;
//    }
//    
//    public List<Pays> getListPays() {
//        return listPays;
//    }
//    
//    public void setListPays(List<Pays> listPays) {
//        this.listPays = listPays;
//    }
//    
//    public List<OrclassProfession> getListProfessionnel() {
//        return listProfessionnel;
//    }
//    
//    public void setListProfessionnel(List<OrclassProfession> listProfessionnel) {
//        this.listProfessionnel = listProfessionnel;
//    }
//    
//    public List<OrclassPolice> getListePolice() {
//        return listePolice;
//    }
//    
//    public void setListePolice(List<OrclassPolice> listePolice) {
//        this.listePolice = listePolice;
//    }
//    
//    public OrclassPolice getPolice() {
//        return police;
//    }
//    
//    public void setPolice(OrclassPolice police) {
//        this.police = police;
//    }
//    
//    public BigInteger getNumero_police() {
//        return numero_police;
//    }
//    
//    public void setNumero_police(BigInteger numero_police) {
//        this.numero_police = numero_police;
//    }
//    
//    public Boolean getTabShowGroup() {
//        return tabShowGroup;
//    }
//    
//    public void setTabShowGroup(Boolean tabShowGroup) {
//        this.tabShowGroup = tabShowGroup;
//    }
//    
//    public Boolean getTabShowFamille() {
//        return tabShowFamille;
//    }
//    
//    public void setTabShowFamille(Boolean tabShowFamille) {
//        this.tabShowFamille = tabShowFamille;
//    }
//    
//    public Boolean getTabShowIndividuel() {
//        return tabShowIndividuel;
//    }
//    
//    public void setTabShowIndividuel(Boolean tabShowIndividuel) {
//        this.tabShowIndividuel = tabShowIndividuel;
//    }
//    
//    public OrclassIntermediaires getIntermediaires() {
//        return intermediaires;
//    }
//    
//    public void setIntermediaires(OrclassIntermediaires intermediaires) {
//        this.intermediaires = intermediaires;
//    }
//    
//    public OrclassCategories getCategories() {
//        return categories;
//    }
//    
//    public void setCategories(OrclassCategories categories) {
//        this.categories = categories;
//    }
//    
//    public OrclassAssure getAssure() {
//        return assure;
//    }
//    
//    public void setAssure(OrclassAssure assure) {
//        this.assure = assure;
//    }
//    
//    public List<OrclassVille> getListeVille() {
//        return listeVille;
//    }
//    
//    public void setListeVille(List<OrclassVille> listeVille) {
//        this.listeVille = listeVille;
//    }
//    
//    public IndicatifPaysDao getIndicatifPaysDao() {
//        return indicatifPaysDao;
//    }
//    
//    public void setIndicatifPaysDao(IndicatifPaysDao indicatifPaysDao) {
//        this.indicatifPaysDao = indicatifPaysDao;
//    }
//    
//    public List<OrclassMajorationDuree> getListeMajorationDurees() {
//        return listeMajorationDurees;
//    }
//    
//    public void setListeMajorationDurees(List<OrclassMajorationDuree> listeMajorationDurees) {
//        this.listeMajorationDurees = listeMajorationDurees;
//    }
//    
//    public OrclassDuree getDuree() {
//        return duree;
//    }
//    
//    public void setDuree(OrclassDuree duree) {
//        this.duree = duree;
//    }
//    
//    public List<OrclassDuree> getListeDurees() {
//        return listeDurees;
//    }
//    
//    public void setListeDurees(List<OrclassDuree> listeDurees) {
//        this.listeDurees = listeDurees;
//    }
//    
//    public OrclassFractionnement getFractionnement() {
//        return fractionnement;
//    }
//    
//    public void setFractionnement(OrclassFractionnement fractionnement) {
//        this.fractionnement = fractionnement;
//    }
//    
//    public List<OrclassFractionnement> getListeFractionnement() {
//        return listeFractionnement;
//    }
//    
//    public void setListeFractionnement(List<OrclassFractionnement> listeFractionnement) {
//        this.listeFractionnement = listeFractionnement;
//    }
//    
//    public List<OrclassTypeTarif> getListeTarif() {
//        return listeTarif;
//    }
//    
//    public void setListeTarif(List<OrclassTypeTarif> listeTarif) {
//        this.listeTarif = listeTarif;
//    }
//    
//    public List<OrclassReduction> getListeReduction() {
//        return listeReduction;
//    }
//    
//    public void setListeReduction(List<OrclassReduction> listeReduction) {
//        this.listeReduction = listeReduction;
//    }
//    
//    public List<OrclassCommission_Prime_Apporteur> getListeCommission_Prime_Apporteur() {
//        return listeCommission_Prime_Apporteur;
//    }
//    
//    public void setListeCommission_Prime_Apporteur(List<OrclassCommission_Prime_Apporteur> listeCommission_Prime_Apporteur) {
//        this.listeCommission_Prime_Apporteur = listeCommission_Prime_Apporteur;
//    }
//    
//    public OrclassPoliceCommissionApporteur getPoliceCommissionApporteur() {
//        return policeCommissionApporteur;
//    }
//    
//    public void setPoliceCommissionApporteur(OrclassPoliceCommissionApporteur policeCommissionApporteur) {
//        this.policeCommissionApporteur = policeCommissionApporteur;
//    }
//    
//    public List<OrclassDevise> getListeDevise() {
//        return listeDevise;
//    }
//    
//    public void setListeDevise(List<OrclassDevise> listeDevise) {
//        this.listeDevise = listeDevise;
//    }
//    
//    public List<OrclassConvention> getListeConvention() {
//        return listeConvention;
//    }
//    
//    public void setListeConvention(List<OrclassConvention> listeConvention) {
//        this.listeConvention = listeConvention;
//    }
//    
//    public List<OrclasseTimbreDimension> getListeTimbreDimension() {
//        return listeTimbreDimension;
//    }
//    
//    public void setListeTimbreDimension(List<OrclasseTimbreDimension> listeTimbreDimension) {
//        this.listeTimbreDimension = listeTimbreDimension;
//    }
//    
//    public List<OrclassExoneration> getListeExoneration() {
//        return listeExoneration;
//    }
//    
//    public void setListeExoneration(List<OrclassExoneration> listeExoneration) {
//        this.listeExoneration = listeExoneration;
//    }
//    
//    public List<OrclassApporteur> getListeApporteur() {
//        return listeApporteur;
//    }
//    
//    public void setListeApporteur(List<OrclassApporteur> listeApporteur) {
//        this.listeApporteur = listeApporteur;
//    }
//    
//    public OrclassRisque getRisque() {
//        return risque;
//    }
//    
//    public void setRisque(OrclassRisque risque) {
//        this.risque = risque;
//    }
//    
//    public List<OrclassRisqueFamille> getListeRisqueFamille() {
//        return listeRisqueFamille;
//    }
//    
//    public void setListeRisqueFamille(List<OrclassRisqueFamille> listeRisqueFamille) {
//        this.listeRisqueFamille = listeRisqueFamille;
//    }
//    
//    public OrclassPlafondMaladie getPlafondMaladie() {
//        return plafondMaladie;
//    }
//    
//    public void setPlafondMaladie(OrclassPlafondMaladie plafondMaladie) {
//        this.plafondMaladie = plafondMaladie;
//    }
//    
//    public List<OrclassPlafondMaladie> getListePlafondMaladie() {
//        return listePlafondMaladie;
//    }
//    
//    public void setListePlafondMaladie(List<OrclassPlafondMaladie> listePlafondMaladie) {
//        this.listePlafondMaladie = listePlafondMaladie;
//    }
//    
//    public String getDesignationPlafondMaladie() {
//        return designationPlafondMaladie;
//    }
//    
//    public void setDesignationPlafondMaladie(String designationPlafondMaladie) {
//        this.designationPlafondMaladie = designationPlafondMaladie;
//    }
//    
//    public OrclassRubriqueCategorie getRubriqueCategorie() {
//        return rubriqueCategorie;
//    }
//    
//    public void setRubriqueCategorie(OrclassRubriqueCategorie rubriqueCategorie) {
//        this.rubriqueCategorie = rubriqueCategorie;
//    }
//    
//    public OrclassDetailPolicePlafond getDetailPolicePlafond() {
//        return detailPolicePlafond;
//    }
//    
//    public void setDetailPolicePlafond(OrclassDetailPolicePlafond detailPolicePlafond) {
//        this.detailPolicePlafond = detailPolicePlafond;
//    }
//    
//    public List<OrclassFractionnementCategories> getListeFractionnementCategories() {
//        return listeFractionnementCategories;
//    }
//    
//    public void setListeFractionnementCategories(List<OrclassFractionnementCategories> listeFractionnementCategories) {
//        this.listeFractionnementCategories = listeFractionnementCategories;
//    }
//    
//    public List<OrclassRubriqueCategorie> getListeRubriqueCategorie() {
//        return listeRubriqueCategorie;
//    }
//    
//    public void setListeRubriqueCategorie(List<OrclassRubriqueCategorie> listeRubriqueCategorie) {
//        this.listeRubriqueCategorie = listeRubriqueCategorie;
//    }
//    
//    public List<OrclassRubriquePrestation> getListeRubriquePrestation() {
//        return listeRubriquePrestation;
//    }
//    
//    public void setListeRubriquePrestation(List<OrclassRubriquePrestation> listeRubriquePrestation) {
//        this.listeRubriquePrestation = listeRubriquePrestation;
//    }
//    
//    public List<OrclassDetailPolicePlafond> getListeDetailPolicePlafond() {
//        return listeDetailPolicePlafond;
//    }
//    
//    public void setListeDetailPolicePlafond(List<OrclassDetailPolicePlafond> listeDetailPolicePlafond) {
//        this.listeDetailPolicePlafond = listeDetailPolicePlafond;
//    }
//    
//    public List<OrclassDetailPolicePlafond> getFilterDetailPolicePlafond() {
//        return filterDetailPolicePlafond;
//    }
//    
//    public void setFilterDetailPolicePlafond(List<OrclassDetailPolicePlafond> filterDetailPolicePlafond) {
//        this.filterDetailPolicePlafond = filterDetailPolicePlafond;
//    }
//    
//    public List<OrclassPrestation> getSelectePrestations() {
//        return selectePrestations;
//    }
//    
//    public void setSelectePrestations(List<OrclassPrestation> selectePrestations) {
//        this.selectePrestations = selectePrestations;
//    }
//    
//    public List<OrclassPrestation> getListePrestation() {
//        return listePrestation;
//    }
//    
//    public void setListePrestation(List<OrclassPrestation> listePrestation) {
//        this.listePrestation = listePrestation;
//    }
//    
//    public OrclassRubriqueCaracteristique getRubriqueCaracteristique() {
//        return rubriqueCaracteristique;
//    }
//    
//    public void setRubriqueCaracteristique(OrclassRubriqueCaracteristique rubriqueCaracteristique) {
//        this.rubriqueCaracteristique = rubriqueCaracteristique;
//    }
//    
//    public List<OrclassCaracteristiques> getListeRubriqueCaracteristique() {
//        return listeRubriqueCaracteristique;
//    }
//    
//    public void setListeRubriqueCaracteristique(List<OrclassCaracteristiques> listeRubriqueCaracteristique) {
//        this.listeRubriqueCaracteristique = listeRubriqueCaracteristique;
//    }
//    
//    public List<OrclassPoliceCaracteristique> getListePoliceCaracteristique() {
//        return listePoliceCaracteristique;
//    }
//    
//    public void setListePoliceCaracteristique(List<OrclassPoliceCaracteristique> listePoliceCaracteristique) {
//        this.listePoliceCaracteristique = listePoliceCaracteristique;
//    }
//    
//    public List<OrclassCaracteristiques> getFilterRubriqueCaracteristique() {
//        return filterRubriqueCaracteristique;
//    }
//    
//    public void setFilterRubriqueCaracteristique(List<OrclassCaracteristiques> filterRubriqueCaracteristique) {
//        this.filterRubriqueCaracteristique = filterRubriqueCaracteristique;
//    }
//    
//    public List<OrclassElement_Liste_Caracteristique> getListeElement_Liste_Caracteristique() {
//        return listeElement_Liste_Caracteristique;
//    }
//    
//    public void setListeElement_Liste_Caracteristique(List<OrclassElement_Liste_Caracteristique> listeElement_Liste_Caracteristique) {
//        this.listeElement_Liste_Caracteristique = listeElement_Liste_Caracteristique;
//    }
//    
//    public List<OrclassPoliceCaracteristique> getFilterPoliceCaracteristique() {
//        return filterPoliceCaracteristique;
//    }
//    
//    public void setFilterPoliceCaracteristique(List<OrclassPoliceCaracteristique> filterPoliceCaracteristique) {
//        this.filterPoliceCaracteristique = filterPoliceCaracteristique;
//    }
//    
//    public List<OrclassGarantie> getListeRubriqueGarantie() {
//        return listeRubriqueGarantie;
//    }
//    
//    public void setListeRubriqueGarantie(List<OrclassGarantie> listeRubriqueGarantie) {
//        this.listeRubriqueGarantie = listeRubriqueGarantie;
//    }
//    
//    public List<OrclassPoliceGarantie> getListePoliceGarantie() {
//        return listePoliceGarantie;
//    }
//    
//    public void setListePoliceGarantie(List<OrclassPoliceGarantie> listePoliceGarantie) {
//        this.listePoliceGarantie = listePoliceGarantie;
//    }
//    
//    public List<OrclassGarantie> getFilterRubriqueGarantie() {
//        return filterRubriqueGarantie;
//    }
//    
//    public void setFilterRubriqueGarantie(List<OrclassGarantie> filterRubriqueGarantie) {
//        this.filterRubriqueGarantie = filterRubriqueGarantie;
//    }
//    
//    public List<OrclassPoliceGarantie> getFilterPoliceGarantie() {
//        return filterPoliceGarantie;
//    }
//    
//    public void setFilterPoliceGarantie(List<OrclassPoliceGarantie> filterPoliceGarantie) {
//        this.filterPoliceGarantie = filterPoliceGarantie;
//    }
//    
//    public OrclassRubriqueGarantie getRubriqueGarantie() {
//        return rubriqueGarantie;
//    }
//    
//    public void setRubriqueGarantie(OrclassRubriqueGarantie rubriqueGarantie) {
//        this.rubriqueGarantie = rubriqueGarantie;
//    }
//    
//    public BigDecimal getTotalProrata() {
//        return totalProrata;
//    }
//    
//    public void setTotalProrata(BigDecimal totalProrata) {
//        this.totalProrata = totalProrata;
//    }
//    
//    public BigDecimal getTotalPrime() {
//        return totalPrime;
//    }
//    
//    public void setTotalPrime(BigDecimal totalPrime) {
//        this.totalPrime = totalPrime;
//    }
//    
//    public OrclassQuitance getQuitance() {
//        return quitance;
//    }
//    
//    public void setQuitance(OrclassQuitance quitance) {
//        this.quitance = quitance;
//    }
//    
//    public OrclassCommission_Prime_Apporteur getPrime_Apporteur() {
//        return prime_Apporteur;
//    }
//    
//    public void setPrime_Apporteur(OrclassCommission_Prime_Apporteur prime_Apporteur) {
//        this.prime_Apporteur = prime_Apporteur;
//    }
//    
//    public OrclassCommission_Prime_Apporteur getCommission_Prime_Apporteur() {
//        return commission_Prime_Apporteur;
//    }
//    
//    public void setCommission_Prime_Apporteur(OrclassCommission_Prime_Apporteur commission_Prime_Apporteur) {
//        this.commission_Prime_Apporteur = commission_Prime_Apporteur;
//    }
//    
//    public List<OrclassPolice> getListeOrclassContrats() {
//        return listeOrclassContrats;
//    }
//    
//    public void setListeOrclassContrats(List<OrclassPolice> listeOrclassContrats) {
//        this.listeOrclassContrats = listeOrclassContrats;
//    }
//    
//    public OrclassPolice getPoliceSelect() {
//        return policeSelect;
//    }
//    
//    public void setPoliceSelect(OrclassPolice policeSelect) {
//        this.policeSelect = policeSelect;
//    }
//    
//    public OrclassApporteur getApporteur() {
//        return apporteur;
//    }
//    
//    public void setApporteur(OrclassApporteur apporteur) {
//        this.apporteur = apporteur;
//    }
//    
//    public List<OrclassIntermediaires> getListeIntermediaires() {
//        return listeIntermediaires;
//    }
//    
//    public void setListeIntermediaires(List<OrclassIntermediaires> listeIntermediaires) {
//        this.listeIntermediaires = listeIntermediaires;
//    }
//    
//    public OrclassIntermediaires getIntermediairesSelect() {
//        return intermediairesSelect;
//    }
//    
//    public void setIntermediairesSelect(OrclassIntermediaires intermediairesSelect) {
//        this.intermediairesSelect = intermediairesSelect;
//    }
//    
//    public OrclasseRefGroupe getRefGroupe() {
//        return refGroupe;
//    }
//    
//    public void setRefGroupe(OrclasseRefGroupe refGroupe) {
//        this.refGroupe = refGroupe;
//    }
//    
//    public OrclasseRefGroupe getRefGroupeSelect() {
//        return refGroupeSelect;
//    }
//    
//    public void setRefGroupeSelect(OrclasseRefGroupe refGroupeSelect) {
//        this.refGroupeSelect = refGroupeSelect;
//    }
//    
//    public List<OrclasseRefGroupe> getListeRefGroup() {
//        return listeRefGroup;
//    }
//    
//    public void setListeRefGroup(List<OrclasseRefGroupe> listeRefGroup) {
//        this.listeRefGroup = listeRefGroup;
//    }
//    
//    public List<OrclassRisque> getListRisqueDetailWithGroupe() {
//        return listRisqueDetailWithGroupe;
//    }
//    
//    public void setListRisqueDetailWithGroupe(List<OrclassRisque> listRisqueDetailWithGroupe) {
//        this.listRisqueDetailWithGroupe = listRisqueDetailWithGroupe;
//    }
//    
//    public List<OrclassRisque> getListRisqueDetailWithGroupe2() {
//        return listRisqueDetailWithGroupe2;
//    }
//    
//    public void setListRisqueDetailWithGroupe2(List<OrclassRisque> listRisqueDetailWithGroupe2) {
//        this.listRisqueDetailWithGroupe2 = listRisqueDetailWithGroupe2;
//    }
//    
//    public List<OrclassImageRisque> getListeImageRisques() {
//        return listeImageRisques;
//    }
//    
//    public void setListeImageRisques(List<OrclassImageRisque> listeImageRisques) {
//        this.listeImageRisques = listeImageRisques;
//    }
//    
//    public List<OrclassImageFamilleRisque> getListeImageFamilleRisque() {
//        return listeImageFamilleRisque;
//    }
//    
//    public void setListeImageFamilleRisque(List<OrclassImageFamilleRisque> listeImageFamilleRisque) {
//        this.listeImageFamilleRisque = listeImageFamilleRisque;
//    }
//    
//    public FileUploadEvent getFile() {
//        return file;
//    }
//    
//    public void setFile(FileUploadEvent file) {
//        this.file = file;
//    }
//    
//    public StreamedContent getUpdateFormatFile() {
//        return updateFormatFile;
//    }
//    
//    public void setUpdateFormatFile(StreamedContent updateFormatFile) {
//        this.updateFormatFile = updateFormatFile;
//    }
//    
//    public OrclassImageFamilleRisque getImageFamilleRisque() {
//        return imageFamilleRisque;
//    }
//    
//    public void setImageFamilleRisque(OrclassImageFamilleRisque imageFamilleRisque) {
//        this.imageFamilleRisque = imageFamilleRisque;
//    }
//    
//    public OrclassImageRisque getImageRisque() {
//        return imageRisque;
//    }
//    
//    public void setImageRisque(OrclassImageRisque imageRisque) {
//        this.imageRisque = imageRisque;
//    }
//    
//    public OrclasseRefGroupe getRefGroupeSelectForAdherent() {
//        return refGroupeSelectForAdherent;
//    }
//    
//    public void setRefGroupeSelectForAdherent(OrclasseRefGroupe refGroupeSelectForAdherent) {
//        this.refGroupeSelectForAdherent = refGroupeSelectForAdherent;
//    }
//    
//    public OrclassImageRisque getImageRisqueSelecte() {
//        return imageRisqueSelecte;
//    }
//    
//    public void setImageRisqueSelecte(OrclassImageRisque imageRisqueSelecte) {
//        this.imageRisqueSelecte = imageRisqueSelecte;
//    }
//    
//    public OrclassImageFamilleRisque getImageFamilleRisqueSelecte() {
//        return imageFamilleRisqueSelecte;
//    }
//    
//    public void setImageFamilleRisqueSelecte(OrclassImageFamilleRisque imageFamilleRisqueSelecte) {
//        this.imageFamilleRisqueSelecte = imageFamilleRisqueSelecte;
//    }
//    
//    public List<OrclassRisque> getListeRisque() {
//        return listeRisque;
//    }
//    
//    public void setListeRisque(List<OrclassRisque> listeRisque) {
//        this.listeRisque = listeRisque;
//    }
//    
//    public Boolean getModecalculForcerManuel() {
//        return modecalculForcerManuel;
//    }
//    
//    public void setModecalculForcerManuel(Boolean modecalculForcerManuel) {
//        this.modecalculForcerManuel = modecalculForcerManuel;
//    }
//    
//    public Boolean getPSqualite() {
//        return _PSqualite;
//    }
//    
//    public void setPSqualite(Boolean _PSqualite) {
//        this._PSqualite = _PSqualite;
//    }
//    
//    public Boolean getPSraison() {
//        return _PSraison;
//    }
//    
//    public void setPSraison(Boolean _PSraison) {
//        this._PSraison = _PSraison;
//    }
//    
//    public Boolean getPSactivite() {
//        return _PSactivite;
//    }
//    
//    public void setPSactivite(Boolean _PSactivite) {
//        this._PSactivite = _PSactivite;
//    }
//    
//    public Boolean getPSdate_creation() {
//        return _PSdate_creation;
//    }
//    
//    public void setPSdate_creation(Boolean _PSdate_creation) {
//        this._PSdate_creation = _PSdate_creation;
//    }
//    
//    public Boolean getPSbp() {
//        return _PSbp;
//    }
//    
//    public void setPSbp(Boolean _PSbp) {
//        this._PSbp = _PSbp;
//    }
//    
//    public Boolean getPSnumero_pantent() {
//        return _PSnumero_pantent;
//    }
//    
//    public void setPSnumero_pantent(Boolean _PSnumero_pantent) {
//        this._PSnumero_pantent = _PSnumero_pantent;
//    }
//    
//    public Boolean getPSville() {
//        return _PSville;
//    }
//    
//    public void setPSville(Boolean _PSville) {
//        this._PSville = _PSville;
//    }
//    
//    public Boolean getPStelephone() {
//        return _PStelephone;
//    }
//    
//    public void setPStelephone(Boolean _PStelephone) {
//        this._PStelephone = _PStelephone;
//    }
//    
//    public Boolean getPSemail() {
//        return _PSemail;
//    }
//    
//    public void setPSemail(Boolean _PSemail) {
//        this._PSemail = _PSemail;
//    }
//    
//    public Boolean getPSdate_effet() {
//        return _PSdate_effet;
//    }
//    
//    public void setPSdate_effet(Boolean _PSdate_effet) {
//        this._PSdate_effet = _PSdate_effet;
//    }
//    
//    public Boolean getPSdate_echeance() {
//        return _PSdate_echeance;
//    }
//    
//    public void setPSdate_echeance(Boolean _PSdate_echeance) {
//        this._PSdate_echeance = _PSdate_echeance;
//    }
//    
//    public Boolean getPSnom() {
//        return _PSnom;
//    }
//    
//    public void setPSnom(Boolean _PSnom) {
//        this._PSnom = _PSnom;
//    }
//    
//    public Boolean getPSprenom() {
//        return _PSprenom;
//    }
//    
//    public void setPSprenom(Boolean _PSprenom) {
//        this._PSprenom = _PSprenom;
//    }
//    
//    public Boolean getPSsexe() {
//        return _PSsexe;
//    }
//    
//    public void setPSsexe(Boolean _PSsexe) {
//        this._PSsexe = _PSsexe;
//    }
//    
//    public Boolean getPSlieu_naissance() {
//        return _PSlieu_naissance;
//    }
//    
//    public void setPSlieu_naissance(Boolean _PSlieu_naissance) {
//        this._PSlieu_naissance = _PSlieu_naissance;
//    }
//    
//    public Boolean getPSprofession() {
//        return _PSprofession;
//    }
//    
//    public void setPSprofession(Boolean _PSprofession) {
//        this._PSprofession = _PSprofession;
//    }
//    
//    public Boolean getPStype_pieces() {
//        return _PStype_pieces;
//    }
//    
//    public void setPStype_pieces(Boolean _PStype_pieces) {
//        this._PStype_pieces = _PStype_pieces;
//    }
//    
//    public Boolean getPSnumero_piece() {
//        return _PSnumero_piece;
//    }
//    
//    public void setPSnumero_piece(Boolean _PSnumero_piece) {
//        this._PSnumero_piece = _PSnumero_piece;
//    }
//    
//    public Boolean getPSsupprimer() {
//        return _PSsupprimer;
//    }
//    
//    public void setPSsupprimer(Boolean _PSsupprimer) {
//        this._PSsupprimer = _PSsupprimer;
//    }
//    
//    public Boolean getPSAjouter() {
//        return _PSAjouter;
//    }
//    
//    public void setPSAjouter(Boolean _PSAjouter) {
//        this._PSAjouter = _PSAjouter;
//    }
//    
//    public Boolean getPSmodifier() {
//        return _PSmodifier;
//    }
//    
//    public void setPSmodifier(Boolean _PSmodifier) {
//        this._PSmodifier = _PSmodifier;
//    }
//    
//    public Boolean getPAUcontrat() {
//        return _PAUcontrat;
//    }
//    
//    public void setPAUcontrat(Boolean _PAUcontrat) {
//        this._PAUcontrat = _PAUcontrat;
//    }
//    
//    public Boolean getPAUnature() {
//        return _PAUnature;
//    }
//    
//    public void setPAUnature(Boolean _PAUnature) {
//        this._PAUnature = _PAUnature;
//    }
//    
//    public Boolean getPAUduree() {
//        return _PAUduree;
//    }
//    
//    public void setPAUduree(Boolean _PAUduree) {
//        this._PAUduree = _PAUduree;
//    }
//    
//    public Boolean getPAUvaleur_duree() {
//        return _PAUvaleur_duree;
//    }
//    
//    public void setPAUvaleur_duree(Boolean _PAUvaleur_duree) {
//        this._PAUvaleur_duree = _PAUvaleur_duree;
//    }
//    
//    public Boolean getPAUfractionnement() {
//        return _PAUfractionnement;
//    }
//    
//    public void setPAUfractionnement(Boolean _PAUfractionnement) {
//        this._PAUfractionnement = _PAUfractionnement;
//    }
//    
//    public Boolean getPAUtype_tarif() {
//        return _PAUtype_tarif;
//    }
//    
//    public void setPAUtype_tarif(Boolean _PAUtype_tarif) {
//        this._PAUtype_tarif = _PAUtype_tarif;
//    }
//    
//    public Boolean getPAUreduction() {
//        return _PAUreduction;
//    }
//    
//    public void setPAUreduction(Boolean _PAUreduction) {
//        this._PAUreduction = _PAUreduction;
//    }
//    
//    public Boolean getPAUapporteur() {
//        return _PAUapporteur;
//    }
//    
//    public void setPAUapporteur(Boolean _PAUapporteur) {
//        this._PAUapporteur = _PAUapporteur;
//    }
//    
//    public Boolean getPAUdevise() {
//        return _PAUdevise;
//    }
//    
//    public void setPAUdevise(Boolean _PAUdevise) {
//        this._PAUdevise = _PAUdevise;
//    }
//    
//    public Boolean getPAUconvention() {
//        return _PAUconvention;
//    }
//    
//    public void setPAUconvention(Boolean _PAUconvention) {
//        this._PAUconvention = _PAUconvention;
//    }
//    
//    public Boolean getPAUreference() {
//        return _PAUreference;
//    }
//    
//    public void setPAUreference(Boolean _PAUreference) {
//        this._PAUreference = _PAUreference;
//    }
//    
//    public Boolean getPAUtimbre_dimension() {
//        return _PAUtimbre_dimension;
//    }
//    
//    public void setPAUtimbre_dimension(Boolean _PAUtimbre_dimension) {
//        this._PAUtimbre_dimension = _PAUtimbre_dimension;
//    }
//    
//    public Boolean getPAUexoneration() {
//        return _PAUexoneration;
//    }
//    
//    public void setPAUexoneration(Boolean _PAUexoneration) {
//        this._PAUexoneration = _PAUexoneration;
//    }
//    
//    public Boolean getPAUaccessoires() {
//        return _PAUaccessoires;
//    }
//    
//    public void setPAUaccessoires(Boolean _PAUaccessoires) {
//        this._PAUaccessoires = _PAUaccessoires;
//    }
//    
//    public Boolean getPAunumero_bordereau() {
//        return _PAunumero_bordereau;
//    }
//    
//    public void setPAunumero_bordereau(Boolean _PAunumero_bordereau) {
//        this._PAunumero_bordereau = _PAunumero_bordereau;
//    }
//    
//    public Boolean getPAUsupprimer() {
//        return _PAUsupprimer;
//    }
//    
//    public void setPAUsupprimer(Boolean _PAUsupprimer) {
//        this._PAUsupprimer = _PAUsupprimer;
//    }
//    
//    public Boolean getPAUAjouter() {
//        return _PAUAjouter;
//    }
//    
//    public void setPAUAjouter(Boolean _PAUAjouter) {
//        this._PAUAjouter = _PAUAjouter;
//    }
//    
//    public Boolean getPAUmodifier() {
//        return _PAUmodifier;
//    }
//    
//    public void setPAUmodifier(Boolean _PAUmodifier) {
//        this._PAUmodifier = _PAUmodifier;
//    }
//    
//    public Boolean getADmembre() {
//        return _ADmembre;
//    }
//    
//    public void setADmembre(Boolean _ADmembre) {
//        this._ADmembre = _ADmembre;
//    }
//    
//    public Boolean getADlien_parente() {
//        return _ADlien_parente;
//    }
//    
//    public void setADlien_parente(Boolean _ADlien_parente) {
//        this._ADlien_parente = _ADlien_parente;
//    }
//    
//    public Boolean getADdate_naissance() {
//        return _ADdate_naissance;
//    }
//    
//    public void setADdate_naissance(Boolean _ADdate_naissance) {
//        this._ADdate_naissance = _ADdate_naissance;
//    }
//    
//    public Boolean getADsexe() {
//        return _ADsexe;
//    }
//    
//    public void setADsexe(Boolean _ADsexe) {
//        this._ADsexe = _ADsexe;
//    }
//    
//    public Boolean getADsupprimer() {
//        return _ADsupprimer;
//    }
//    
//    public void setADsupprimer(Boolean _ADsupprimer) {
//        this._ADsupprimer = _ADsupprimer;
//    }
//    
//    public Boolean getADAjouter() {
//        return _ADAjouter;
//    }
//    
//    public void setADAjouter(Boolean _ADAjouter) {
//        this._ADAjouter = _ADAjouter;
//    }
//    
//    public Boolean getADmodifier() {
//        return _ADmodifier;
//    }
//    
//    public void setADmodifier(Boolean _ADmodifier) {
//        this._ADmodifier = _ADmodifier;
//    }
//    
//    public Boolean getASSnom() {
//        return _ASSnom;
//    }
//    
//    public void setASSnom(Boolean _ASSnom) {
//        this._ASSnom = _ASSnom;
//    }
//    
//    public Boolean getASSdate_naissance() {
//        return _ASSdate_naissance;
//    }
//    
//    public void setASSdate_naissance(Boolean _ASSdate_naissance) {
//        this._ASSdate_naissance = _ASSdate_naissance;
//    }
//    
//    public Boolean getASSlieu_naissance() {
//        return _ASSlieu_naissance;
//    }
//    
//    public void setASSlieu_naissance(Boolean _ASSlieu_naissance) {
//        this._ASSlieu_naissance = _ASSlieu_naissance;
//    }
//    
//    public Boolean getASSsexe() {
//        return _ASSsexe;
//    }
//    
//    public void setASSsexe(Boolean _ASSsexe) {
//        this._ASSsexe = _ASSsexe;
//    }
//    
//    public Boolean getASSemail() {
//        return _ASSemail;
//    }
//    
//    public void setASSemail(Boolean _ASSemail) {
//        this._ASSemail = _ASSemail;
//    }
//    
//    public Boolean getASSbp() {
//        return _ASSbp;
//    }
//    
//    public void setASSbp(Boolean _ASSbp) {
//        this._ASSbp = _ASSbp;
//    }
//    
//    public Boolean getASStelephone() {
//        return _ASStelephone;
//    }
//    
//    public void setASStelephone(Boolean _ASStelephone) {
//        this._ASStelephone = _ASStelephone;
//    }
//    
//    public Boolean getASSville() {
//        return _ASSville;
//    }
//    
//    public void setASSville(Boolean _ASSville) {
//        this._ASSville = _ASSville;
//    }
//    
//    public Boolean getASSprofession() {
//        return _ASSprofession;
//    }
//    
//    public void setASSprofession(Boolean _ASSprofession) {
//        this._ASSprofession = _ASSprofession;
//    }
//    
//    public Boolean getASSactivite() {
//        return _ASSactivite;
//    }
//    
//    public void setASSactivite(Boolean _ASSactivite) {
//        this._ASSactivite = _ASSactivite;
//    }
//    
//    public Boolean getASSnumero_piece() {
//        return _ASSnumero_piece;
//    }
//    
//    public void setASSnumero_piece(Boolean _ASSnumero_piece) {
//        this._ASSnumero_piece = _ASSnumero_piece;
//    }
//    
//    public Boolean getASSdateEntre() {
//        return _ASSdateEntre;
//    }
//    
//    public void setASSdateEntre(Boolean _ASSdateEntre) {
//        this._ASSdateEntre = _ASSdateEntre;
//    }
//    
//    public Boolean getASSsupprimer() {
//        return _ASSsupprimer;
//    }
//    
//    public void setASSsupprimer(Boolean _ASSsupprimer) {
//        this._ASSsupprimer = _ASSsupprimer;
//    }
//    
//    public Boolean getASSAjouter() {
//        return _ASSAjouter;
//    }
//    
//    public void setASSAjouter(Boolean _ASSAjouter) {
//        this._ASSAjouter = _ASSAjouter;
//    }
//    
//    public Boolean getASSmodifier() {
//        return _ASSmodifier;
//    }
//    
//    public void setASSmodifier(Boolean _ASSmodifier) {
//        this._ASSmodifier = _ASSmodifier;
//    }
//    
//    public Boolean getCARdesignation() {
//        return _CARdesignation;
//    }
//    
//    public void setCARdesignation(Boolean _CARdesignation) {
//        this._CARdesignation = _CARdesignation;
//    }
//    
//    public Boolean getCARsupprimer() {
//        return _CARsupprimer;
//    }
//    
//    public void setCARsupprimer(Boolean _CARsupprimer) {
//        this._CARsupprimer = _CARsupprimer;
//    }
//    
//    public Boolean getCARAjouter() {
//        return _CARAjouter;
//    }
//    
//    public void setCARAjouter(Boolean _CARAjouter) {
//        this._CARAjouter = _CARAjouter;
//    }
//    
//    public Boolean getCARmodifier() {
//        return _CARmodifier;
//    }
//    
//    public void setCARmodifier(Boolean _CARmodifier) {
//        this._CARmodifier = _CARmodifier;
//    }
//    
//    public Boolean getFAMmembre() {
//        return _FAMmembre;
//    }
//    
//    public void setFAMmembre(Boolean _FAMmembre) {
//        this._FAMmembre = _FAMmembre;
//    }
//    
//    public Boolean getFAMlien_parente() {
//        return _FAMlien_parente;
//    }
//    
//    public void setFAMlien_parente(Boolean _FAMlien_parente) {
//        this._FAMlien_parente = _FAMlien_parente;
//    }
//    
//    public Boolean getFAMdate_naissance() {
//        return _FAMdate_naissance;
//    }
//    
//    public void setFAMdate_naissance(Boolean _FAMdate_naissance) {
//        this._FAMdate_naissance = _FAMdate_naissance;
//    }
//    
//    public Boolean getFAMsexe() {
//        return _FAMsexe;
//    }
//    
//    public void setFAMsexe(Boolean _FAMsexe) {
//        this._FAMsexe = _FAMsexe;
//    }
//    
//    public Boolean getFAMdateEntre() {
//        return _FAMdateEntre;
//    }
//    
//    public void setFAMdateEntre(Boolean _FAMdateEntre) {
//        this._FAMdateEntre = _FAMdateEntre;
//    }
//    
//    public Boolean getFAMsupprimer() {
//        return _FAMsupprimer;
//    }
//    
//    public void setFAMsupprimer(Boolean _FAMsupprimer) {
//        this._FAMsupprimer = _FAMsupprimer;
//    }
//    
//    public Boolean getFAMAjouter() {
//        return _FAMAjouter;
//    }
//    
//    public void setFAMAjouter(Boolean _FAMAjouter) {
//        this._FAMAjouter = _FAMAjouter;
//    }
//    
//    public Boolean getFAMmodifier() {
//        return _FAMmodifier;
//    }
//    
//    public void setFAMmodifier(Boolean _FAMmodifier) {
//        this._FAMmodifier = _FAMmodifier;
//    }
//    
//    public Boolean getGARgarantie_capital() {
//        return _GARgarantie_capital;
//    }
//    
//    public void setGARgarantie_capital(Boolean _GARgarantie_capital) {
//        this._GARgarantie_capital = _GARgarantie_capital;
//    }
//    
//    public Boolean getGARgarantie_taux() {
//        return _GARgarantie_taux;
//    }
//    
//    public void setGARgarantie_taux(Boolean _GARgarantie_taux) {
//        this._GARgarantie_taux = _GARgarantie_taux;
//    }
//    
//    public Boolean getGARgarantie_pourcentage() {
//        return _GARgarantie_pourcentage;
//    }
//    
//    public void setGARgarantie_pourcentage(Boolean _GARgarantie_pourcentage) {
//        this._GARgarantie_pourcentage = _GARgarantie_pourcentage;
//    }
//    
//    public Boolean getGARgarantie_prime() {
//        return _GARgarantie_prime;
//    }
//    
//    public void setGARgarantie_prime(Boolean _GARgarantie_prime) {
//        this._GARgarantie_prime = _GARgarantie_prime;
//    }
//    
//    public Boolean getGARgarantie_proarata() {
//        return _GARgarantie_proarata;
//    }
//    
//    public void setGARgarantie_proarata(Boolean _GARgarantie_proarata) {
//        this._GARgarantie_proarata = _GARgarantie_proarata;
//    }
//    
//    public Boolean getGARsupprimer() {
//        return _GARsupprimer;
//    }
//    
//    public void setGARsupprimer(Boolean _GARsupprimer) {
//        this._GARsupprimer = _GARsupprimer;
//    }
//    
//    public Boolean getGARAjouter() {
//        return _GARAjouter;
//    }
//    
//    public void setGARAjouter(Boolean _GARAjouter) {
//        this._GARAjouter = _GARAjouter;
//    }
//    
//    public Boolean getGARmodifier() {
//        return _GARmodifier;
//    }
//    
//    public void setGARmodifier(Boolean _GARmodifier) {
//        this._GARmodifier = _GARmodifier;
//    }
//    
//    public Boolean getGROmatricule() {
//        return _GROmatricule;
//    }
//    
//    public void setGROmatricule(Boolean _GROmatricule) {
//        this._GROmatricule = _GROmatricule;
//    }
//    
//    public Boolean getGROnom() {
//        return _GROnom;
//    }
//    
//    public void setGROnom(Boolean _GROnom) {
//        this._GROnom = _GROnom;
//    }
//    
//    public Boolean getGROdate_naissance() {
//        return _GROdate_naissance;
//    }
//    
//    public void setGROdate_naissance(Boolean _GROdate_naissance) {
//        this._GROdate_naissance = _GROdate_naissance;
//    }
//    
//    public Boolean getGROsexe() {
//        return _GROsexe;
//    }
//    
//    public void setGROsexe(Boolean _GROsexe) {
//        this._GROsexe = _GROsexe;
//    }
//    
//    public Boolean getGROsupprimer() {
//        return _GROsupprimer;
//    }
//    
//    public void setGROsupprimer(Boolean _GROsupprimer) {
//        this._GROsupprimer = _GROsupprimer;
//    }
//    
//    public Boolean getGROAjouter() {
//        return _GROAjouter;
//    }
//    
//    public void setGROAjouter(Boolean _GROAjouter) {
//        this._GROAjouter = _GROAjouter;
//    }
//    
//    public Boolean getGROmodifier() {
//        return _GROmodifier;
//    }
//    
//    public void setGROmodifier(Boolean _GROmodifier) {
//        this._GROmodifier = _GROmodifier;
//    }
//    
//    public Boolean getPREplafond_maladie() {
//        return _PREplafond_maladie;
//    }
//    
//    public void setPREplafond_maladie(Boolean _PREplafond_maladie) {
//        this._PREplafond_maladie = _PREplafond_maladie;
//    }
//    
//    public Boolean getPREdesignation() {
//        return _PREdesignation;
//    }
//    
//    public void setPREdesignation(Boolean _PREdesignation) {
//        this._PREdesignation = _PREdesignation;
//    }
//    
//    public Boolean getPREprestation_taux() {
//        return _PREprestation_taux;
//    }
//    
//    public void setPREprestation_taux(Boolean _PREprestation_taux) {
//        this._PREprestation_taux = _PREprestation_taux;
//    }
//    
//    public Boolean getPREprestation_forfait() {
//        return _PREprestation_forfait;
//    }
//    
//    public void setPREprestation_forfait(Boolean _PREprestation_forfait) {
//        this._PREprestation_forfait = _PREprestation_forfait;
//    }
//    
//    public Boolean getPREprestation_type() {
//        return _PREprestation_type;
//    }
//    
//    public void setPREprestation_type(Boolean _PREprestation_type) {
//        this._PREprestation_type = _PREprestation_type;
//    }
//    
//    public Boolean getPREprestation_plafon() {
//        return _PREprestation_plafon;
//    }
//    
//    public void setPREprestation_plafon(Boolean _PREprestation_plafon) {
//        this._PREprestation_plafon = _PREprestation_plafon;
//    }
//    
//    public Boolean getPREprestation_bareme() {
//        return _PREprestation_bareme;
//    }
//    
//    public void setPREprestation_bareme(Boolean _PREprestation_bareme) {
//        this._PREprestation_bareme = _PREprestation_bareme;
//    }
//    
//    public Boolean getPREsupprimer() {
//        return _PREsupprimer;
//    }
//    
//    public void setPREsupprimer(Boolean _PREsupprimer) {
//        this._PREsupprimer = _PREsupprimer;
//    }
//    
//    public Boolean getPREAjouter() {
//        return _PREAjouter;
//    }
//    
//    public void setPREAjouter(Boolean _PREAjouter) {
//        this._PREAjouter = _PREAjouter;
//    }
//    
//    public Boolean getPREmodifier() {
//        return _PREmodifier;
//    }
//    
//    public void setPREmodifier(Boolean _PREmodifier) {
//        this._PREmodifier = _PREmodifier;
//    }
//    
//    public Boolean getQUItype_quittance() {
//        return _QUItype_quittance;
//    }
//    
//    public void setQUItype_quittance(Boolean _QUItype_quittance) {
//        this._QUItype_quittance = _QUItype_quittance;
//    }
//    
//    public Boolean getQUIdate_emission() {
//        return _QUIdate_emission;
//    }
//    
//    public void setQUIdate_emission(Boolean _QUIdate_emission) {
//        this._QUIdate_emission = _QUIdate_emission;
//    }
//    
//    public Boolean getQUIsupprimer() {
//        return _QUIsupprimer;
//    }
//    
//    public void setQUIsupprimer(Boolean _QUIsupprimer) {
//        this._QUIsupprimer = _QUIsupprimer;
//    }
//    
//    public Boolean getQUIAjouter() {
//        return _QUIAjouter;
//    }
//    
//    public void setQUIAjouter(Boolean _QUIAjouter) {
//        this._QUIAjouter = _QUIAjouter;
//    }
//    
//    public Boolean getQUImodifier() {
//        return _QUImodifier;
//    }
//    
//    public void setQUImodifier(Boolean _QUImodifier) {
//        this._QUImodifier = _QUImodifier;
//    }
//    
//    public OrclassRegistreProductionDao getProductionDao() {
//        return productionDao;
//    }
//    
//    public void setProductionDao(OrclassRegistreProductionDao productionDao) {
//        this.productionDao = productionDao;
//    }
//    
//    public OrclassRegistreProduction getProduction() {
//        return production;
//    }
//    
//    public void setProduction(OrclassRegistreProduction production) {
//        this.production = production;
//    }
//    
//    public OrclassCategoriesDao getCategoriesDao() {
//        return categoriesDao;
//    }
//    
//    public void setCategoriesDao(OrclassCategoriesDao categoriesDao) {
//        this.categoriesDao = categoriesDao;
//    }
//    
//    public OrclassActiviteDao getActiviteDao() {
//        return activiteDao;
//    }
//    
//    public void setActiviteDao(OrclassActiviteDao activiteDao) {
//        this.activiteDao = activiteDao;
//    }
//    
//    public OrclassActivite getActivite() {
//        return activite;
//    }
//    
//    public void setActivite(OrclassActivite activite) {
//        this.activite = activite;
//    }
//    
//    public OrclassQualiteDao getQualiteDao() {
//        return qualiteDao;
//    }
//    
//    public void setQualiteDao(OrclassQualiteDao qualiteDao) {
//        this.qualiteDao = qualiteDao;
//    }
//    
//    public OrclassQualite getQualite() {
//        return qualite;
//    }
//    
//    public void setQualite(OrclassQualite qualite) {
//        this.qualite = qualite;
//    }
//    
//    public PaysDao getPaysDao() {
//        return paysDao;
//    }
//    
//    public void setPaysDao(PaysDao paysDao) {
//        this.paysDao = paysDao;
//    }
//    
//    public OrclassProfessionDao getProfessionDao() {
//        return professionDao;
//    }
//    
//    public void setProfessionDao(OrclassProfessionDao professionDao) {
//        this.professionDao = professionDao;
//    }
//    
//    public OrclassProfession getProfession() {
//        return profession;
//    }
//    
//    public void setProfession(OrclassProfession profession) {
//        this.profession = profession;
//    }
//    
//    public IExcell getServiceExcell() {
//        return serviceExcell;
//    }
//    
//    public void setServiceExcell(IExcell serviceExcell) {
//        this.serviceExcell = serviceExcell;
//    }
//    
//    public OrclassBranchesDao getBranchesDao() {
//        return branchesDao;
//    }
//    
//    public void setBranchesDao(OrclassBranchesDao branchesDao) {
//        this.branchesDao = branchesDao;
//    }
//    
//    public OrclassBranches getBranches() {
//        return branches;
//    }
//    
//    public void setBranches(OrclassBranches branches) {
//        this.branches = branches;
//    }
//    
//    public OrclassSuspensionBrancheIntemediaireDao getSuspensionBrancheIntemediaireDao() {
//        return suspensionBrancheIntemediaireDao;
//    }
//    
//    public void setSuspensionBrancheIntemediaireDao(OrclassSuspensionBrancheIntemediaireDao suspensionBrancheIntemediaireDao) {
//        this.suspensionBrancheIntemediaireDao = suspensionBrancheIntemediaireDao;
//    }
//    
//    public OrclassSuspensionBrancheIntemediaire getSuspensionBrancheIntemediaire() {
//        return suspensionBrancheIntemediaire;
//    }
//    
//    public void setSuspensionBrancheIntemediaire(OrclassSuspensionBrancheIntemediaire suspensionBrancheIntemediaire) {
//        this.suspensionBrancheIntemediaire = suspensionBrancheIntemediaire;
//    }
//    
//    public OrclassFractionnementCategoriesDao getFractionnementCategoriesDao() {
//        return fractionnementCategoriesDao;
//    }
//    
//    public void setFractionnementCategoriesDao(OrclassFractionnementCategoriesDao fractionnementCategoriesDao) {
//        this.fractionnementCategoriesDao = fractionnementCategoriesDao;
//    }
//    
//    public OrclassFractionnementCategories getFractionnementCategories() {
//        return fractionnementCategories;
//    }
//    
//    public void setFractionnementCategories(OrclassFractionnementCategories fractionnementCategories) {
//        this.fractionnementCategories = fractionnementCategories;
//    }
//    
//    public OrclassSuspensionCategorieIntermediaireDao getSuspensionCategorieIntermediaireDao() {
//        return suspensionCategorieIntermediaireDao;
//    }
//    
//    public void setSuspensionCategorieIntermediaireDao(OrclassSuspensionCategorieIntermediaireDao suspensionCategorieIntermediaireDao) {
//        this.suspensionCategorieIntermediaireDao = suspensionCategorieIntermediaireDao;
//    }
//    
//    public OrclassSuspensionCategorieIntermediaire getSuspensionCategorieIntermediaire() {
//        return suspensionCategorieIntermediaire;
//    }
//    
//    public void setSuspensionCategorieIntermediaire(OrclassSuspensionCategorieIntermediaire suspensionCategorieIntermediaire) {
//        this.suspensionCategorieIntermediaire = suspensionCategorieIntermediaire;
//    }
//    
//    public OrclassIntermediairesDao getIntermediairesDao() {
//        return intermediairesDao;
//    }
//    
//    public void setIntermediairesDao(OrclassIntermediairesDao intermediairesDao) {
//        this.intermediairesDao = intermediairesDao;
//    }
//    
//    public OrclassPoliceDao getPoliceDao() {
//        return policeDao;
//    }
//    
//    public void setPoliceDao(OrclassPoliceDao policeDao) {
//        this.policeDao = policeDao;
//    }
//    
//    public OrclassElt_Categorie_CompagnieDao getElt_Categorie_CompagnieDao() {
//        return elt_Categorie_CompagnieDao;
//    }
//    
//    public void setElt_Categorie_CompagnieDao(OrclassElt_Categorie_CompagnieDao elt_Categorie_CompagnieDao) {
//        this.elt_Categorie_CompagnieDao = elt_Categorie_CompagnieDao;
//    }
//    
//    public OrclassElt_Categorie_Compagnie getElt_Categorie_Compagnie() {
//        return elt_Categorie_Compagnie;
//    }
//    
//    public void setElt_Categorie_Compagnie(OrclassElt_Categorie_Compagnie elt_Categorie_Compagnie) {
//        this.elt_Categorie_Compagnie = elt_Categorie_Compagnie;
//    }
//    
//    public OrclassVilleDao getVilleDao() {
//        return villeDao;
//    }
//    
//    public void setVilleDao(OrclassVilleDao villeDao) {
//        this.villeDao = villeDao;
//    }
//    
//    public OrclassMajorationDureeDao getMajorationDureeDao() {
//        return majorationDureeDao;
//    }
//    
//    public void setMajorationDureeDao(OrclassMajorationDureeDao majorationDureeDao) {
//        this.majorationDureeDao = majorationDureeDao;
//    }
//    
//    public OrclassMajorationDuree getMajorationDuree() {
//        return majorationDuree;
//    }
//    
//    public void setMajorationDuree(OrclassMajorationDuree majorationDuree) {
//        this.majorationDuree = majorationDuree;
//    }
//    
//    public OrclassTypeTarifDao getTypeTarifDao() {
//        return typeTarifDao;
//    }
//    
//    public void setTypeTarifDao(OrclassTypeTarifDao typeTarifDao) {
//        this.typeTarifDao = typeTarifDao;
//    }
//    
//    public OrclassReductionDao getReductionDao() {
//        return reductionDao;
//    }
//    
//    public void setReductionDao(OrclassReductionDao reductionDao) {
//        this.reductionDao = reductionDao;
//    }
//    
//    public OrclassCommission_Prime_ApporteurDao getCommission_Prime_ApporteurDao() {
//        return commission_Prime_ApporteurDao;
//    }
//    
//    public void setCommission_Prime_ApporteurDao(OrclassCommission_Prime_ApporteurDao commission_Prime_ApporteurDao) {
//        this.commission_Prime_ApporteurDao = commission_Prime_ApporteurDao;
//    }
//    
//    public OrclassDeviseDao getDeviseDao() {
//        return deviseDao;
//    }
//    
//    public void setDeviseDao(OrclassDeviseDao deviseDao) {
//        this.deviseDao = deviseDao;
//    }
//    
//    public OrclassConventionDao getConventionDao() {
//        return conventionDao;
//    }
//    
//    public void setConventionDao(OrclassConventionDao conventionDao) {
//        this.conventionDao = conventionDao;
//    }
//    
//    public OrclassTimbreDimensionDao getTimbreDimensionDao() {
//        return timbreDimensionDao;
//    }
//    
//    public void setTimbreDimensionDao(OrclassTimbreDimensionDao timbreDimensionDao) {
//        this.timbreDimensionDao = timbreDimensionDao;
//    }
//    
//    public OrclassExonerationDao getExonerationDao() {
//        return exonerationDao;
//    }
//    
//    public void setExonerationDao(OrclassExonerationDao exonerationDao) {
//        this.exonerationDao = exonerationDao;
//    }
//    
//    public OrclassApporteurDao getApporteurDao() {
//        return apporteurDao;
//    }
//    
//    public void setApporteurDao(OrclassApporteurDao apporteurDao) {
//        this.apporteurDao = apporteurDao;
//    }
//    
//    public OrclassPlafondMaladieDao getPlafondMaladieDao() {
//        return plafondMaladieDao;
//    }
//    
//    public void setPlafondMaladieDao(OrclassPlafondMaladieDao plafondMaladieDao) {
//        this.plafondMaladieDao = plafondMaladieDao;
//    }
//    
//    public OrclassRubriqueCategorieDao getRubriqueCategorieDao() {
//        return rubriqueCategorieDao;
//    }
//    
//    public void setRubriqueCategorieDao(OrclassRubriqueCategorieDao rubriqueCategorieDao) {
//        this.rubriqueCategorieDao = rubriqueCategorieDao;
//    }
//    
//    public OrclassRubriquePrestationDao getRubriquePrestationDao() {
//        return rubriquePrestationDao;
//    }
//    
//    public void setRubriquePrestationDao(OrclassRubriquePrestationDao rubriquePrestationDao) {
//        this.rubriquePrestationDao = rubriquePrestationDao;
//    }
//    
//    public OrclassDetailPolicePlafondDao getDetailPolicePlafondDao() {
//        return detailPolicePlafondDao;
//    }
//    
//    public void setDetailPolicePlafondDao(OrclassDetailPolicePlafondDao detailPolicePlafondDao) {
//        this.detailPolicePlafondDao = detailPolicePlafondDao;
//    }
//    
//    public OrclassPrestationDao getPrestationDao() {
//        return prestationDao;
//    }
//    
//    public void setPrestationDao(OrclassPrestationDao prestationDao) {
//        this.prestationDao = prestationDao;
//    }
//    
//    public OrclassRubriqueCaracteristiqueDao getRubriqueCaracteristiqueDao() {
//        return rubriqueCaracteristiqueDao;
//    }
//    
//    public void setRubriqueCaracteristiqueDao(OrclassRubriqueCaracteristiqueDao rubriqueCaracteristiqueDao) {
//        this.rubriqueCaracteristiqueDao = rubriqueCaracteristiqueDao;
//    }
//    
//    public OrclassElement_Liste_CaracteristiqueDao getElement_Liste_CaracteristiqueDao() {
//        return element_Liste_CaracteristiqueDao;
//    }
//    
//    public void setElement_Liste_CaracteristiqueDao(OrclassElement_Liste_CaracteristiqueDao element_Liste_CaracteristiqueDao) {
//        this.element_Liste_CaracteristiqueDao = element_Liste_CaracteristiqueDao;
//    }
//    
//    public OrclassRubriqueGarantieDao getRubriqueGarantieDao() {
//        return rubriqueGarantieDao;
//    }
//    
//    public void setRubriqueGarantieDao(OrclassRubriqueGarantieDao rubriqueGarantieDao) {
//        this.rubriqueGarantieDao = rubriqueGarantieDao;
//    }
//    
//    public OrclassPoliceGarantieDao getPoliceGarantieDao() {
//        return policeGarantieDao;
//    }
//    
//    public void setPoliceGarantieDao(OrclassPoliceGarantieDao policeGarantieDao) {
//        this.policeGarantieDao = policeGarantieDao;
//    }
//    
//    public OrclassPoliceGarantie getPoliceGarantie() {
//        return policeGarantie;
//    }
//    
//    public void setPoliceGarantie(OrclassPoliceGarantie policeGarantie) {
//        this.policeGarantie = policeGarantie;
//    }
//    
//    public ISante getSanteService() {
//        return santeService;
//    }
//    
//    public void setSanteService(ISante SanteService) {
//        this.santeService = SanteService;
//    }
//    
//    public OrclassQuitanceDao getQuitanceDao() {
//        return quitanceDao;
//    }
//    
//    public void setQuitanceDao(OrclassQuitanceDao quitanceDao) {
//        this.quitanceDao = quitanceDao;
//    }
//    
//    public OrclassRisqueFamilleDao getRisqueFamilleDao() {
//        return risqueFamilleDao;
//    }
//    
//    public void setRisqueFamilleDao(OrclassRisqueFamilleDao risqueFamilleDao) {
//        this.risqueFamilleDao = risqueFamilleDao;
//    }
//    
//    public OrclassPoliceCaracteristiqueDao getPoliceCaracteristiqueDao() {
//        return policeCaracteristiqueDao;
//    }
//    
//    public void setPoliceCaracteristiqueDao(OrclassPoliceCaracteristiqueDao policeCaracteristiqueDao) {
//        this.policeCaracteristiqueDao = policeCaracteristiqueDao;
//    }
//    
//    public OrclassPolicePlafondDao getPolicePlafondDao() {
//        return policePlafondDao;
//    }
//    
//    public void setPolicePlafondDao(OrclassPolicePlafondDao policePlafondDao) {
//        this.policePlafondDao = policePlafondDao;
//    }
//    
//    public OrclassRisqueDao getRisqueDao() {
//        return risqueDao;
//    }
//    
//    public void setRisqueDao(OrclassRisqueDao risqueDao) {
//        this.risqueDao = risqueDao;
//    }
//    
//    public OrclassAssureDao getAssureDao() {
//        return assureDao;
//    }
//    
//    public void setAssureDao(OrclassAssureDao assureDao) {
//        this.assureDao = assureDao;
//    }
//    
//    public OrclassAssure getAssureCheck() {
//        return assureCheck;
//    }
//    
//    public void setAssureCheck(OrclassAssure assureCheck) {
//        this.assureCheck = assureCheck;
//    }
//    
//    public OrclasseRefGroupeDao getRefGroupeDao() {
//        return refGroupeDao;
//    }
//    
//    public void setRefGroupeDao(OrclasseRefGroupeDao refGroupeDao) {
//        this.refGroupeDao = refGroupeDao;
//    }
//    
//    public OrclassImageFamilleRisqueDao getImageFamilleRisqueDao() {
//        return imageFamilleRisqueDao;
//    }
//    
//    public void setImageFamilleRisqueDao(OrclassImageFamilleRisqueDao imageFamilleRisqueDao) {
//        this.imageFamilleRisqueDao = imageFamilleRisqueDao;
//    }
//    
//    public OrclassImageRisqueDao getImageRisqueDao() {
//        return imageRisqueDao;
//    }
//    
//    public void setImageRisqueDao(OrclassImageRisqueDao imageRisqueDao) {
//        this.imageRisqueDao = imageRisqueDao;
//    }
//    
//    public OrclassPropositionDao getPropositionDao() {
//        return propositionDao;
//    }
//    
//    public void setPropositionDao(OrclassPropositionDao propositionDao) {
//        this.propositionDao = propositionDao;
//    }
//    
//    public Orclass_TypeAvenantDao getTypeAvenantDao() {
//        return typeAvenantDao;
//    }
//    
//    public void setTypeAvenantDao(Orclass_TypeAvenantDao typeAvenantDao) {
//        this.typeAvenantDao = typeAvenantDao;
//    }
//    
//    public Orclass_TypeAvenant getTypeAvenant() {
//        return typeAvenant;
//    }
//    
//    public void setTypeAvenant(Orclass_TypeAvenant typeAvenant) {
//        this.typeAvenant = typeAvenant;
//    }
//    
//    public OrclassAvenantDao getAvenantDao() {
//        return avenantDao;
//    }
//    
//    public void setAvenantDao(OrclassAvenantDao avenantDao) {
//        this.avenantDao = avenantDao;
//    }
//    
//    public OrclassAvenant getAvenant() {
//        return avenant;
//    }
//    
//    public void setAvenant(OrclassAvenant avenant) {
//        this.avenant = avenant;
//    }
//    
//    public Orclass_AccessAvenat_CaracteristiqueDao getAccessAvenat_CaracteristiqueDao() {
//        return accessAvenat_CaracteristiqueDao;
//    }
//    
//    public void setAccessAvenat_CaracteristiqueDao(Orclass_AccessAvenat_CaracteristiqueDao accessAvenat_CaracteristiqueDao) {
//        this.accessAvenat_CaracteristiqueDao = accessAvenat_CaracteristiqueDao;
//    }
//    
//    public Orclass_AccessAvenat_Caracteristique getAccessAvenat_Caracteristique() {
//        return accessAvenat_Caracteristique;
//    }
//    
//    public void setAccessAvenat_Caracteristique(Orclass_AccessAvenat_Caracteristique accessAvenat_Caracteristique) {
//        this.accessAvenat_Caracteristique = accessAvenat_Caracteristique;
//    }
//    
//    public Orclass_Access_AvenantDao getAccess_AvenantDao() {
//        return access_AvenantDao;
//    }
//    
//    public void setAccess_AvenantDao(Orclass_Access_AvenantDao access_AvenantDao) {
//        this.access_AvenantDao = access_AvenantDao;
//    }
//    
//    public Orclass_Access_Avenant getAccess_Avenant() {
//        return access_Avenant;
//    }
//    
//    public void setAccess_Avenant(Orclass_Access_Avenant access_Avenant) {
//        this.access_Avenant = access_Avenant;
//    }
//    
//    public Orclass_ObjetDao getObjetDao() {
//        return objetDao;
//    }
//    
//    public void setObjetDao(Orclass_ObjetDao objetDao) {
//        this.objetDao = objetDao;
//    }
//    
//    public Orclass_Objet getOrclass_Objet() {
//        return orclass_Objet;
//    }
//    
//    public void setOrclass_Objet(Orclass_Objet orclass_Objet) {
//        this.orclass_Objet = orclass_Objet;
//    }
//    
//    public String getCurrentFolder() {
//        return currentFolder;
//    }
//    
//    public void setCurrentFolder(String currentFolder) {
//        this.currentFolder = currentFolder;
//    }
//    
//    public String getActivitExcell() {
//        return activitExcell;
//    }
//    
//    public void setActivitExcell(String activitExcell) {
//        this.activitExcell = activitExcell;
//    }
//    
//    public String getQualiteExcell() {
//        return qualiteExcell;
//    }
//    
//    public void setQualiteExcell(String qualiteExcell) {
//        this.qualiteExcell = qualiteExcell;
//    }
//    
//    public String getPaysExcell() {
//        return paysExcell;
//    }
//    
//    public void setPaysExcell(String paysExcell) {
//        this.paysExcell = paysExcell;
//    }
//    
//    public String getProf1Excell() {
//        return prof1Excell;
//    }
//    
//    public void setProf1Excell(String prof1Excell) {
//        this.prof1Excell = prof1Excell;
//    }
//    
//    public String getProf2Excell() {
//        return prof2Excell;
//    }
//    
//    public void setProf2Excell(String prof2Excell) {
//        this.prof2Excell = prof2Excell;
//    }
//    
//    public String getPathFormat() {
//        return pathFormat;
//    }
//    
//    public void setPathFormat(String pathFormat) {
//        this.pathFormat = pathFormat;
//    }
//    
//    public List<OrclassCategories> getListSuspensionCategorieIntermediaire() {
//        return listSuspensionCategorieIntermediaire;
//    }
//    
//    public void setListSuspensionCategorieIntermediaire(List<OrclassCategories> listSuspensionCategorieIntermediaire) {
//        this.listSuspensionCategorieIntermediaire = listSuspensionCategorieIntermediaire;
//    }
//    
//    public List<OrclassPrestation> getFilterPrestation() {
//        return filterPrestation;
//    }
//    
//    public void setFilterPrestation(List<OrclassPrestation> filterPrestation) {
//        this.filterPrestation = filterPrestation;
//    }
//    
//    public OrclassRisqueFamille getRisqueFamille() {
//        return risqueFamille;
//    }
//    
//    public void setRisqueFamille(OrclassRisqueFamille risqueFamille) {
//        this.risqueFamille = risqueFamille;
//    }
//    
//    public List<OrclassPoliceCommissionApporteur> getListePoliceCommissionApporteur() {
//        return listePoliceCommissionApporteur;
//    }
//    
//    public void setListePoliceCommissionApporteur(List<OrclassPoliceCommissionApporteur> listePoliceCommissionApporteur) {
//        this.listePoliceCommissionApporteur = listePoliceCommissionApporteur;
//    }
//    
//    public List<Orclass_TypeAvenant> getListeTypeAvenant() {
//        return listeTypeAvenant;
//    }
//    
//    public void setListeTypeAvenant(List<Orclass_TypeAvenant> listeTypeAvenant) {
//        this.listeTypeAvenant = listeTypeAvenant;
//    }
//    
//    public List<Contrat_Sante> getColContrat_Sante() {
//        return colContrat_Sante;
//    }
//    
//    public void setColContrat_Sante(List<Contrat_Sante> colContrat_Sante) {
//        this.colContrat_Sante = colContrat_Sante;
//    }
//    
//    public OrclassEntreprises getEntreprise() {
//        return entreprise;
//    }
//    
//    public void setEntreprise(OrclassEntreprises entreprise) {
//        this.entreprise = entreprise;
//    }
//    
//    public OrclassUtilisateurs getUser() {
//        return user;
//    }
//    
//    public void setUser(OrclassUtilisateurs user) {
//        this.user = user;
//    }
//    
//    public Boolean getSuspensionBrance() {
//        return suspensionBrance;
//    }
//    
//    public void setSuspensionBrance(Boolean suspensionBrance) {
//        this.suspensionBrance = suspensionBrance;
//    }
//    
//    public String getSummary() {
//        return summary;
//    }
//    
//    public void setSummary(String summary) {
//        this.summary = summary;
//    }
//    
//    public String getMsgdetail() {
//        return msgdetail;
//    }
//    
//    public void setMsgdetail(String msgdetail) {
//        this.msgdetail = msgdetail;
//    }
//    
//    public OrclassDevise getDevise() {
//        return devise;
//    }
//    
//    public void setDevise(OrclassDevise devise) {
//        this.devise = devise;
//    }
//    
//    public OrclassPoliceCaracteristique getPoliceCaracteristique() {
//        return policeCaracteristique;
//    }
//    
//    public void setPoliceCaracteristique(OrclassPoliceCaracteristique policeCaracteristique) {
//        this.policeCaracteristique = policeCaracteristique;
//    }
//    
//    public Boolean getPrint() {
//        return print;
//    }
//    
//    public void setPrint(Boolean print) {
//        this.print = print;
//    }
//    
//    public Boolean getValidation() {
//        return validation;
//    }
//    
//    public void setValidation(Boolean validation) {
//        this.validation = validation;
//    }
//    
//    public Boolean getASStype_pieces() {
//        return _ASStype_pieces;
//    }
//    
//    public void setASStype_pieces(Boolean _ASStype_pieces) {
//        this._ASStype_pieces = _ASStype_pieces;
//    }
//    
//    public BigInteger getNumeroAvenant() {
//        if (typeAvenant != null && typeAvenant.getId() != null && policeSelect != null && policeSelect != null) {
//            numeroAvenant = avenantDao.lastNumeroAvenantByPolice(policeSelect, entreprise);
//            numeroAvenant = numeroAvenant.add(BigInteger.ONE);
//        }
//        return numeroAvenant;
//    }
//    
//    public void setNumeroAvenant(BigInteger numeroAvenant) {
//        this.numeroAvenant = numeroAvenant;
//    }
//    
//    public Boolean getPREprestation_designation() {
//        return _PREprestation_designation;
//    }
//    
//    public void setPREprestation_designation(Boolean _PREprestation_designation) {
//        this._PREprestation_designation = _PREprestation_designation;
//    }
//    
//    public Boolean getPREprestation_modecal() {
//        return _PREprestation_modecal;
//    }
//    
//    public void setPREprestation_modecal(Boolean _PREprestation_modecal) {
//        this._PREprestation_modecal = _PREprestation_modecal;
//    }
//    
//    public Boolean getADMatricule() {
//        return _ADMatricule;
//    }
//    
//    public void setADMatricule(Boolean _ADMatricule) {
//        this._ADMatricule = _ADMatricule;
//    }
//    
//    public Boolean getADgroup() {
//        return _ADgroup;
//    }
//    
//    public void setADgroup(Boolean _ADgroup) {
//        this._ADgroup = _ADgroup;
//    }
//    
//    public Boolean getPSdate_naissance() {
//        return _PSdate_naissance;
//    }
//    
//    public void setPSdate_naissance(Boolean _PSdate_naissance) {
//        this._PSdate_naissance = _PSdate_naissance;
//    }
//    
//    public Boolean getQUITva() {
//        return _QUITva;
//    }
//    
//    public void setQUITva(Boolean _QUITva) {
//        this._QUITva = _QUITva;
//    }
//    
//    public BigDecimal getTva() {
//        return tva;
//    }
//    
//    public void setTva(BigDecimal tva) {
//        this.tva = tva;
//    }
//    
//    public List<OrclassAvenant> getListeAvenantByPolice() {
//        return listeAvenantByPolice;
//    }
//    
//    public void setListeAvenantByPolice(List<OrclassAvenant> listeAvenantByPolice) {
//        this.listeAvenantByPolice = listeAvenantByPolice;
//    }
//    
//    public OrclassAvenant getAvenantConsultation() {
//        return avenantConsultation;
//    }
//    
//    public void setAvenantConsultation(OrclassAvenant avenantConsultation) {
//        this.avenantConsultation = avenantConsultation;
//    }
//    
//}
