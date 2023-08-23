///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.automobile;
//
//import Excell.IExcell;
//import auto.Contrat_Auto;
//import auto.IAuto;
//import dao.GenreAutoDao;
//import dao.IndicatifPaysDao;
//import dao.OrclassActiviteDao;
//import dao.OrclassApporteurDao;
//import dao.OrclassAssureDao;
//import dao.OrclassAttestationAssuranceDao;
//import dao.OrclassBonusMalusDao;
//import dao.OrclassBranchesDao;
//import dao.OrclassCaracteristiquesDao;
//import dao.OrclassCarrosserieDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassCommission_Prime_ApporteurDao;
//import dao.OrclassConventionDao;
//import dao.OrclassDeviseDao;
//import dao.OrclassDocumentCategoriesDao;
//import dao.OrclassElement_Liste_CaracteristiqueDao;
//import dao.OrclassElt_Categorie_CompagnieDao;
//import dao.OrclassExonerationDao;
//import dao.OrclassExonerationTaxeDao;
//import dao.OrclassFormuleDao;
//import dao.OrclassFractionnementCategoriesDao;
//import dao.OrclassGarantieDao;
//import dao.OrclassGestionCompagnieAgenceDao;
//import dao.OrclassGestionStockAgenceDao;
//import dao.OrclassGroupCaracteristiqueGarantieDao;
//import dao.OrclassGroupPlafondPrestationDao;
//import dao.OrclassGroupeGarantiePoliceDao;
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
//import dao.OrclassProfessionDao;
//import dao.OrclassPropositionDao;
//import dao.OrclassQualiteDao;
//import dao.OrclassQuitanceDao;
//import dao.OrclassReductionDao;
//import dao.OrclassRefTimbreGradueDao;
//import dao.OrclassRegistreProductionDao;
//import dao.OrclassRisqueDao;
//import dao.OrclassRisqueFamilleDao;
//import dao.OrclassRubriqueCaracteristiqueDao;
//import dao.OrclassRubriqueCategorieDao;
//import dao.OrclassRubriqueDao;
//import dao.OrclassRubriqueGarantieDao;
//import dao.OrclassRubriquePrestationDao;
//import dao.OrclassSousUsageTPVDao;
//import dao.OrclassSuspensionBrancheIntemediaireDao;
//import dao.OrclassSuspensionCategorieIntermediaireDao;
//import dao.OrclassTarifConditionDao;
//import dao.OrclassTarifDao;
//import dao.OrclassTaxePrimeDao;
//import dao.OrclassTimbreDao;
//import dao.OrclassTimbreDimensionDao;
//import dao.OrclassTypeTarifDao;
//import dao.OrclassVilleDao;
//import dao.OrclassZoneTransportDao;
//import dao.Orclass_ConducteurDao;
//import dao.OrclasseRefGroupeDao;
//import dao.PaysDao;
//import dao.UsageAutoDao;
//import enums.BaseCalculTaxePrime;
//import enums.Contrat;
//import enums.Energie;
//import enums.LibelleBranche;
//import enums.LibelleFrais;
//import enums.LibelleRisque;
//import enums.LienParente;
//import enums.ModeCalcul;
//import enums.NatureContrat;
//import enums.NatureGarantie;
//import enums.NatureRisque;
//import enums.OperationsTarif;
//import enums.PoliceAutresInformation;
//import enums.Sexe;
//import enums.StatutCaracteristique;
//import enums.TypePermis;
//import enums.TypePieces;
//import enums.TypeQuittance;
//import enums.TypeVehicule;
//import enums.VehiculeObjet;
//import enums.Zone;
//import exception.GlobalException;
//import exception.Success;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
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
//import modele.GenreAuto;
//import modele.OrclassActivite;
//import modele.OrclassApporteur;
//import modele.OrclassAssure;
//import modele.OrclassAttestationAssurance;
//import modele.OrclassAttestationConducteur;
//import modele.OrclassBonusMalus;
//import modele.OrclassBranches;
//import modele.OrclassCaracteristiques;
//import modele.OrclassCarrosserie;
//import modele.OrclassCategories;
//import modele.OrclassCommission_Prime_Apporteur;
//import modele.OrclassConducteur;
//import modele.OrclassConvention;
//import modele.OrclassDetailPolicePlafond;
//import modele.OrclassDevise;
//import modele.OrclassDocumentCategories;
//import modele.OrclassDuree;
//import modele.OrclassElement_Liste_Caracteristique;
//import modele.OrclassElt_Categorie_Compagnie;
//import modele.OrclassEntreprises;
//import modele.OrclassExoneration;
//import modele.OrclassExonerationTaxe;
//import modele.OrclassFormule;
//import modele.OrclassFractionnement;
//import modele.OrclassFractionnementCategories;
//import modele.OrclassGarantie;
//import modele.OrclassGestionCompagnieAgence;
//import modele.OrclassGroupeGarantiePolice;
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
//import modele.OrclassPrestation;
//import modele.OrclassProfession;
//import modele.OrclassProposition;
//import modele.OrclassQualite;
//import modele.OrclassQuitance;
//import modele.OrclassReduction;
//import modele.OrclassRefGaranties;
//import modele.OrclassRefTimbreGradue;
//import modele.OrclassRegistreProduction;
//import modele.OrclassRisque;
//import modele.OrclassRisqueFamille;
//import modele.OrclassRubrique;
//import modele.OrclassRubriqueCaracteristique;
//import modele.OrclassRubriqueCategorie;
//import modele.OrclassRubriqueGarantie;
//import modele.OrclassRubriquePrestation;
//import modele.OrclassSousUsageTPV;
//import modele.OrclassSuspensionBrancheIntemediaire;
//import modele.OrclassSuspensionCategorieIntermediaire;
//import modele.OrclassTarif;
//import modele.OrclassTarifCondition;
//import modele.OrclassTaxePrime;
//import modele.OrclassTimbre;
//import modele.OrclassTypeTarif;
//import modele.OrclassUtilisateurs;
//import modele.OrclassVille;
//import modele.OrclassZoneTransport;
//import modele.OrclasseRefGroupe;
//import modele.OrclasseTimbreDimension;
//import modele.Pays;
//import modele.UsageAuto;
//import net.sf.jasperreports.engine.JRException;
//import org.apache.commons.collections.comparators.ComparatorChain;
//import org.apache.poi.ss.formula.functions.NumericFunction;
//import org.hibernate.exception.ConstraintViolationException;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.FileUploadEvent;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.event.TabChangeEvent;
//import org.primefaces.model.StreamedContent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sante.Contrat_Sante;
//import sante.GroupeSanteElement;
//import sante.ISante;
//import utils.FonctionTable;
//import utils.GlobalFonctions;
//import utils.IdleDate;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Named(value = "productionMonoController")
//@ViewScoped
//public class ProductionMonoController implements Serializable {
//
//    /**
//     * Creates a new instance of ProductionMonoController
//     *
//     */
//    private static final Logger logger = LoggerFactory.getLogger(ProductionMonoController.class);
//    @EJB
//    OrclassRegistreProductionDao productionDao;
//    OrclassRegistreProduction production;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//    @EJB
//    OrclassGestionStockAgenceDao gestionStockAgenceDao;
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
//    OrclassAttestationAssuranceDao attestationAssuranceDao;
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
//
//    @EJB
//    OrclassRubriqueCaracteristiqueDao rubriqueCaracteristiqueDao;
//    OrclassRubriqueCaracteristique rubriqueCaracteristique;
//    @EJB
//    OrclassElement_Liste_CaracteristiqueDao element_Liste_CaracteristiqueDao;
//    @EJB
//    OrclassRubriqueGarantieDao rubriqueGarantieDao;
//    OrclassRubriqueGarantie rubriqueGarantie;
//    OrclassRubriqueGarantie rubriqueGaranties;
//    @EJB
//    OrclassPoliceGarantieDao policeGarantieDao;
//    OrclassPoliceGarantie policeGarantie;
//    OrclassQuitance quitance;
//    @EJB
//    ISante SanteService;
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
//    OrclassGarantieDao garantieDao;
//    @EJB
//    OrclassGroupeGarantiePoliceDao groupeGarantiePoliceDao;
//    @EJB
//    OrclassGroupCaracteristiqueGarantieDao groupCaracteristiqueGarantieDao;
//    @EJB
//    OrclassGroupPlafondPrestationDao groupPlafondPrestationDao;
//    @EJB
//    OrclassGroupePoliceDao groupePoliceDao;
//    @EJB
//    OrclassZoneTransportDao zoneTransportDao;
//    @EJB
//    OrclassSousUsageTPVDao sousUsageTPVDao;
//    @EJB
//    OrclassCarrosserieDao carrosserieDao;
//    @EJB
//    OrclassBonusMalusDao bonusMalusDao;
//    OrclassBonusMalus bonusMalus;
//    @EJB
//    OrclassTarifDao tarifDao;
//    OrclassTarif tarif;
//    @EJB
//    OrclassFormuleDao formuleDao;
//    OrclassFormule formule;
//    @EJB
//    OrclassCaracteristiquesDao caracteristiquesDao;
//    @EJB
//    OrclassRubriqueDao rubriqueDao;
//    @EJB
//    OrclassExonerationTaxeDao exonerationTaxeDao;
//    @EJB
//    OrclassTaxePrimeDao taxePrimeDao;
//    @EJB
//    OrclassTimbreDao timbreDao;
//    @EJB
//    OrclassRefTimbreGradueDao refTimbreGradueDao;
//    @EJB
//    OrclassGestionCompagnieAgenceDao gestionCompagnieAgenceDao;
//    @EJB
//    IAuto serviceAuto;
//    @EJB
//    GenreAutoDao genreAutoDao;
//    @EJB
//    UsageAutoDao usageAutoDao;
//    @EJB
//    OrclassTarifConditionDao tarifConditionDao;
//
//    private List<OrclassCategories> lisCategoriesByAutomobile = new ArrayList<>();
//    private List<OrclassRisque> listeRisque = new ArrayList<>();
//    private List<OrclasseRefGroupe> listeRefGroup = new ArrayList<>();
//    private List<OrclassCategories> listSuspensionCategorieIntermediaire = new ArrayList<>();
//    private List<OrclassActivite> listActivite = new ArrayList<>();
//    private List<OrclassQualite> listQualite = new ArrayList<>();
//    private List<Pays> listPays = new ArrayList<>();
//    private List<OrclassProfession> listProfessionnel = new ArrayList<>();
//    private List<OrclassRisque> listRisqueDetailWithGroupe = new ArrayList<>();
//    private List<OrclassRisque> listRisqueDetailWithGroupe2 = new ArrayList<>();
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
//    private List<OrclassGarantie> listeRubriqueGarantieControleChangeGroup = new ArrayList<>();
//    private List<OrclassCaracteristiques> listeRubriqueCaracteristiqueControleChangeGroup = new ArrayList<>();
//    private List<OrclassPoliceGarantie> listePoliceGarantie = new ArrayList<>();
//    private List<OrclassGarantie> filterRubriqueGarantie;
//    private List<OrclassPoliceGarantie> filterPoliceGarantie;
//    private OrclassRisqueFamille risqueFamille;
//    private List<OrclassPrestation> selectePrestations = new ArrayList<>();
//    private List<OrclassPrestation> listePrestation = new ArrayList<>();
//    private List<OrclassPoliceCommissionApporteur> listePoliceCommissionApporteur = new ArrayList<>();
//    private List<OrclassPolice> listeOrclassContrats = new ArrayList<>();
//    List<Contrat_Auto> colContrat_Auto = new ArrayList<>();
//    private List<OrclassIntermediaires> listeIntermediaires = new ArrayList<>();
//    private List<OrclassImageRisque> listeImageRisques = new ArrayList<>();
//    private List<OrclassImageFamilleRisque> listeImageFamilleRisque = new ArrayList<>();
//    private List<OrclassGarantie> listeGarantieHaveForfairtairOrGratuit = new ArrayList<>();
//    private List<OrclassGroupeGarantiePolice> listeGroupGrantitePolice = new ArrayList<>();
//    private List<OrclasseRefGroupe> listeGrouSave = new ArrayList<>();
//    private List<OrclassDocumentCategories> listeDocumentCategories = new ArrayList<>();
//    private List<GenreAuto> listeGenreAuto = new ArrayList<>();
//    private List<UsageAuto> listeUsageAuto = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//    private OrclassUtilisateurs user;
//    private Boolean suspensionBrance = Boolean.FALSE;
//    private OrclassPolice police;
//    private OrclassPolice policeSelect;
//    private BigInteger numero_police;
//    private String summary = "", msgdetail = "";
//    private OrclassPoliceCommissionApporteur policeCommissionApporteur;
//    OrclassPoliceGarantie policeGarantieAffiche;
//    private List<OrclassZoneTransport> listeZoneTransport = new ArrayList<>();
//    private List<OrclassSousUsageTPV> listeSousUsageTPV = new ArrayList<>();
//    private List<OrclassCarrosserie> listeCarrosserie = new ArrayList<>();
//    private OrclassAttestationAssurance attestationAssurance;
//    private OrclassGarantie garantie;
//    private Date curentDate;
//    private OrclassGestionCompagnieAgence gestionStockCompagnieAgence;
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
//    private OrclasseRefGroupe refGroupeSelectForPrestation;
//    private OrclasseRefGroupe refGroupeSelectForCaracteristiqueAndGarantie;
//    private OrclasseRefGroupe refGroupeSelectForGarantie;
//    private OrclassImageFamilleRisque imageFamilleRisqueSelecte;
//    private Boolean modecalculForcerManuel = Boolean.FALSE;
//    private Boolean etatModifier = Boolean.FALSE;
//    private Boolean enregistrer = Boolean.FALSE;
//    List<GroupeSanteElement> listeGroupeSanteElements = new ArrayList<>();
//    Map<Integer, Object> objetElementGroupePrestation = new HashMap<Integer, Object>();
//    OrclassRisque risqueselecte = new OrclassRisque();
//    OrclassRisqueFamille risqueFamilleSelect = new OrclassRisqueFamille();
//    private int index = 0;
//    private BigDecimal tva = BigDecimal.valueOf(19.25);
//    List<OrclassPoliceCaracteristique> listePoliceCaracteristiquesSave = new ArrayList<>();
//    private List<OrclassRefGaranties> listeGarantieNonEditable = new ArrayList<>();
//    private OrclassConducteur conducteur;
//    private OrclassConducteur conducteurAdd;
//    private List<OrclassConducteur> listeConducteur = new ArrayList<>();
//    private List<OrclassBonusMalus> listeBonusMalus = new ArrayList<>();
//    private BigDecimal monatant_apporteur_commission = BigDecimal.ZERO;
//    private BigDecimal montant_gestion_commission = BigDecimal.ZERO;
//    private BigDecimal total_commission = BigDecimal.ZERO;
//    private BigDecimal reduction_commercial = BigDecimal.ZERO;
//    private BigDecimal reduction_bonus = BigDecimal.ZERO;
//    private BigDecimal total_reduction_majoration_donneçspecial = BigDecimal.ZERO;
//    private BigDecimal majoration_permis = BigDecimal.ZERO;
//    private BigDecimal majoration_age = BigDecimal.ZERO;
//    private BigDecimal reduction_tarif1 = BigDecimal.ZERO;
//    private BigDecimal reduction_tarif2 = BigDecimal.ZERO;
//    private BigDecimal reduction_tarif3 = BigDecimal.ZERO;
//    private BigDecimal matiere_inflamable = BigDecimal.ZERO;
//    private BigDecimal total_reduction = BigDecimal.ZERO;
//    BigDecimal total_taux_red_maj = BigDecimal.ZERO;
//    BigDecimal taxePrime = BigDecimal.ZERO;
//    BigDecimal total_red_maj_specialGarantie = BigDecimal.ZERO;
//    BigDecimal totalReduction = BigDecimal.ZERO;
//    @EJB
//    OrclassDocumentCategoriesDao documentCategoriesDao;
//    private String num_attestation;
//    @EJB
//    Orclass_ConducteurDao conducteurDao;
//
//
//    /*Orclass
//    valeur pour afficher les tabView 
//     */
//    private OrclassAssure assure;
//    private GroupeSanteElement groupeSanteElement;
//
//    private String currentFolder = "/photos";
//    private String activitExcell = currentFolder + "/activite.xls";
//    private String qualiteExcell = currentFolder + "/qualite.xls";
//    private String paysExcell = currentFolder + "/activite.xls";
//    private String prof1Excell = currentFolder + "/profession1.xls";
//    private String prof2Excell = currentFolder + "/profession2.xls";
//    private String pathFormat = currentFolder + "/formatGroupe.xls";
//    private OrclassAttestationConducteur attestationConducteur;
//
//    public ProductionMonoController() {
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
//        refGroupeSelectForCaracteristiqueAndGarantie = new OrclasseRefGroupe();
//        refGroupeSelectForPrestation = new OrclasseRefGroupe();
//        refGroupeSelectForGarantie = new OrclasseRefGroupe();
//        groupeSanteElement = new GroupeSanteElement();
//        attestationAssurance = new OrclassAttestationAssurance();
//        conducteur = new OrclassConducteur();
//        conducteurAdd = new OrclassConducteur();
//        conducteurAdd.setIdTestationConducteur(new OrclassAttestationConducteur());
//        attestationConducteur = new OrclassAttestationConducteur();
//        rubriqueGaranties = new OrclassRubriqueGarantie();
//        garantie = new OrclassGarantie();
//        gestionStockCompagnieAgence = new OrclassGestionCompagnieAgence();
//    }
//
//    public void addConducteurByListe() {
//        if (listeConducteur.isEmpty()) {
//            if (conducteurAdd.getNomComplet() != null) {
//                listeConducteur.add(conducteurAdd);
//            }
//        } else if (conducteurAdd.getNomComplet() != null) {
//            listeConducteur.add(conducteurAdd);
//        }
//        for (OrclassConducteur c : listeConducteur) {
//            System.out.println(" conducteur :" + c.getNomComplet());
//        }
//        conducteurAdd = new OrclassConducteur();
//        conducteurAdd.setIdTestationConducteur(new OrclassAttestationConducteur());
//        PrimeFaces.current().ajax().update(":form3");
//        PrimeFaces.current().executeScript("PF('manageConducteurDialog').show()");
//    }
//
//    public void afficheDialogConducteur() {
//        conducteurAdd = new OrclassConducteur();
//        conducteurAdd.setIdTestationConducteur(new OrclassAttestationConducteur());
//        PrimeFaces.current().ajax().update(":form3");
//        PrimeFaces.current().executeScript("PF('manageConducteurDialog').show()");
//    }
//
//    public void afficheDialogAttestation() {
//        if (attestationConducteur == null) {
//            attestationConducteur = new OrclassAttestationConducteur();
//            PrimeFaces.current().ajax().update(":form2");
//        }
//
//        PrimeFaces.current().executeScript("PF('manageAttestationDialog').show()");
//    }
//
//    public void deletePoliceGarantie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        OrclassRisque r = null;
//        OrclassPoliceCaracteristique newPoliceCaracteristique = null;
//        int index = 0;
//        if (policeGarantie != null && policeGarantie.getId() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "VERIFY ENTITY... KEY IS NULL", "PLEASE TRY AGAINST"));
//            return;
//        }
//
//        policeGarantieDao.delete(policeGarantie);
//        if (listePoliceGarantie.contains(policeGarantie) == Boolean.TRUE) {
//            listePoliceGarantie.remove(policeGarantie);
//        }
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//        this.updateDataTablePoliceCarzacteristique();
//        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//
//    }
//
//    public void haveIndexForListe(int index) {
//        this.index = index;
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
//            case 0:
//                etat = Boolean.FALSE;
//                break;
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
//    public void chargeCaracteristiqueRubrique() {
////        listePoliceCaracteristique = new ArrayList<>();
//        List<OrclassCaracteristiques> listeRubriqueCaracteristiqueNonObligatoir = new ArrayList<>(rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotSatutObligatoire(entreprise, categories, StatutCaracteristique.obligatoire));
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
//            this.listeRubriqueCaracteristiqueControleChangeGroup.clear();
//            listeRubriqueCaracteristiqueControleChangeGroup.addAll(listeRubriqueCaracteristique);
//            if (!listeRubriqueCaracteristiqueNonObligatoir.isEmpty()) {
//                listeRubriqueCaracteristique.addAll(listeRubriqueCaracteristique);
//            }
//
//            for (OrclassCaracteristiques pr : listeRubriqueCaracteristique) {
//                policeCaracteristique = new OrclassPoliceCaracteristique();
//                policeCaracteristique.setIdCaracteristiques(pr);
//                if (pr.getUnite_Caracteristique() != null && pr.getUnite_Caracteristique().getLibelle() != null) {
//                    policeCaracteristique.getIdCaracteristiques().setLibelle(pr.getLibelle() + " " + pr.getUnite_Caracteristique().getLibelle());
//                }
//                policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
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
//            listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeCaracteristiqueByCompagniesNotHave(entreprise, categories);
//
//        }
//
//    }
//
//    public void chargeListeCaracteristique() {
//        List<OrclassPoliceCaracteristique> listePoliceCaracteristiques = new ArrayList<>();
//        OrclasseRefGroupe group = null;
//        OrclassPoliceCaracteristique pcaract = null;
//        List<OrclassCaracteristiques> listeCaracteristiques = new ArrayList<>();
//        int index = 0;
//        if (police == null || police.getId() == null) {
//            if (refGroupeSelectForCaracteristiqueAndGarantie != null && refGroupeSelectForCaracteristiqueAndGarantie.getId() != null) {
//                if (listePoliceCaracteristique != null && !listePoliceCaracteristique.isEmpty()) {
//                    listePoliceCaracteristiquesSave.clear();
////                    listePoliceCaracteristique.remove(0);
//                    listePoliceCaracteristiquesSave.addAll(listePoliceCaracteristique);
//                    listePoliceCaracteristique.clear();
//                    for (OrclassPoliceCaracteristique pc : listePoliceCaracteristiquesSave) {
//                        if (pc.getIdGroup() == null || pc.getIdCaracteristiques() == null || pc.getIdCaracteristiques().getId() == null) {
//                            continue;
//                        }
//                        if (pc.getIdGroup() != null && pc.getIdGroup().getId().equals(refGroupeSelectForCaracteristiqueAndGarantie.getId()) == Boolean.TRUE) {
//                            pc.setAfficher(Boolean.TRUE);
//                            listePoliceCaracteristique.add(pc);
//                        } else {
//                            pc.setAfficher(Boolean.FALSE);
//                            listePoliceCaracteristiques.add(pc);
//                        }
//                    }
//
//                    if (listePoliceCaracteristique.isEmpty()) {
//                        // on recupere tous les caracteristique obligateur pour ce nlouveau group
//
//                        for (OrclassCaracteristiques pr : rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, categories, StatutCaracteristique.obligatoire)) {
//                            policeCaracteristique = new OrclassPoliceCaracteristique();
//                            policeCaracteristique.setIdCaracteristiques(pr);
//                            if (pr.getUnite_Caracteristique() != null && pr.getUnite_Caracteristique().getLibelle() != null) {
//                                policeCaracteristique.getIdCaracteristiques().setLibelle(pr.getLibelle() + " " + pr.getUnite_Caracteristique().getLibelle());
//                            }
//                            policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
//                            listePoliceCaracteristique.add(policeCaracteristique);
//
//                        }
//                        if (!listePoliceCaracteristiques.isEmpty()) {
//                            listePoliceCaracteristique.addAll(listePoliceCaracteristiques);
//                        }
//                    } else {
//                        if (!listePoliceCaracteristiques.isEmpty()) {
//                            listePoliceCaracteristique.addAll(listePoliceCaracteristiques);
//                        }
//                    }
//
//                }
//
//                if (listePoliceCaracteristique != null && !listePoliceCaracteristique.isEmpty()) {
//                    policeCaracteristique = new OrclassPoliceCaracteristique();
//                    policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
//                    listePoliceCaracteristique.add(policeCaracteristique);
//                    this.reverseListeCaracteristique();
//                } else {
//                    this.listePoliceCaracteristique = new ArrayList<>();
//                    policeCaracteristique = new OrclassPoliceCaracteristique();
//                    policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
//                    listePoliceCaracteristique.add(policeCaracteristique);
////                this.reverseListeCaracteristique();
//                }
//
//            }
//
//        } else if (police != null || police.getId() != null && refGroupeSelectForCaracteristiqueAndGarantie != null && refGroupeSelectForCaracteristiqueAndGarantie.getId() != null) {
//            listePoliceCaracteristique = policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprise, police, refGroupeSelectForCaracteristiqueAndGarantie);
//            if (listePoliceCaracteristique == null || listePoliceCaracteristique.isEmpty()) {
//                listePoliceCaracteristique = new ArrayList<>();
//            }
//            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                policeCaracteristique = new OrclassPoliceCaracteristique();
//                policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
//                listePoliceCaracteristique.add(policeCaracteristique);
//                this.reverseListeCaracteristique();
//            }
//            listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, refGroupeSelectForCaracteristiqueAndGarantie);
//
//        }
//        this.updateDataTablePoliceCarzacteristique();
//        this.updateDataTableRubriqueCaracteristique();
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
//    public void updateDataTablePoliceCarzacteristique() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idpTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('pTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
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
//            branches = branchesDao.findEntityHavingValue("libelle", LibelleBranche.automobile);
//            lisCategoriesByAutomobile = categoriesDao.listeCategorieByBranche(branches, entreprise);
//
//            PrimeFaces.current().executeScript("PF('agence').show();");
//        } else {
//            intermediaires = user.getIdIntermediaire();
//
//        }
//
//        if (intermediaires != null && intermediaires.getIdIntermediaire() != null && (Objects.equals(user.getAllAccessForIntermediaire(), Boolean.FALSE) || user.getAllAccessForIntermediaire() == null)) {
//            branches = branchesDao.findEntityHavingValue("libelle", LibelleBranche.automobile);
//            if (branches != null && branches.getIdBranche() != null) {
//                lisCategoriesByAutomobile = categoriesDao.listeCategorieByBranche(branches, entreprise);
//                suspensionBrancheIntemediaire = suspensionBrancheIntemediaireDao.finKey(intermediaires, entreprise, branches);
//                if (suspensionBrancheIntemediaire == null) {
//                    suspensionBrance = Boolean.FALSE;
//                    listSuspensionCategorieIntermediaire = suspensionCategorieIntermediaireDao.listeBrancheSuspendueForIntermediaire(intermediaires, entreprise, branches);
//                    if (listSuspensionCategorieIntermediaire.isEmpty() && suspensionBrancheIntemediaire != null) {
//                        lisCategoriesByAutomobile.clear();
//                    } else {
//                        lisCategoriesByAutomobile.removeAll(listSuspensionCategorieIntermediaire);
//                    }
//                } else {
//                    suspensionBrance = Boolean.TRUE;
//                    listSuspensionCategorieIntermediaire = suspensionCategorieIntermediaireDao.listeBrancheSuspendueForIntermediaire(intermediaires, entreprise, branches);
//                    if (listSuspensionCategorieIntermediaire.isEmpty() && suspensionBrancheIntemediaire != null) {
//                        lisCategoriesByAutomobile.clear();
//                    } else {
//                        lisCategoriesByAutomobile.removeAll(listSuspensionCategorieIntermediaire);
//                    }
//
//                }
//            }
//        }
//        if ((user.getAllAccessForIntermediaire() == null || (Objects.equals(user.getAllAccessForIntermediaire(), Boolean.FALSE))) && intermediaires != null && intermediaires.getIdIntermediaire() != null) {
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraByAgence(intermediaires, entreprise, user, branches);
//        }
//
//        listeVille = (List<OrclassVille>) villeDao.findAll();
//        listeTarif = (List<OrclassTypeTarif>) typeTarifDao.findAll();
////        listeReduction = (List<OrclassReduction>) reductionDao.listeReductionByCompagnie(entreprise);
//        listeConvention = (List<OrclassConvention>) conventionDao.findAll();
//        listeDevise = (List<OrclassDevise>) deviseDao.findAll();
//        listeTimbreDimension = (List<OrclasseTimbreDimension>) timbreDimensionDao.findAll();
//        listeExoneration = (List<OrclassExoneration>) exonerationDao.listeExonerationHaveExonerationTaxe(entreprise);
////        listeApporteur = commission_Prime_ApporteurDao.listeApporteurHaveCommissionByEntreprise(entreprise);
//        devise = deviseDao.findEntityHavingValue("code", "CFA");
//        listeZoneTransport = (List<OrclassZoneTransport>) zoneTransportDao.findAll();
//        listeSousUsageTPV = (List<OrclassSousUsageTPV>) sousUsageTPVDao.findAll();
//        listeCarrosserie = (List<OrclassCarrosserie>) carrosserieDao.findAll();
//        listeBonusMalus = (List<OrclassBonusMalus>) bonusMalusDao.findAll();
//        listeGenreAuto = (List<GenreAuto>) genreAutoDao.findAll();
//        listeUsageAuto = (List<UsageAuto>) usageAutoDao.findAll();
//        if (listeBonusMalus == null || listeBonusMalus.isEmpty()) {
//            bonusMalus = new OrclassBonusMalus("N", "Normal", 1.0);
//            bonusMalusDao.create(bonusMalus);
//            bonusMalus = bonusMalusDao.findEntityHavingValue("libelle", "Normal");
//            if (bonusMalus == null) {
//                bonusMalus = new OrclassBonusMalus();
//            } else {
//                listeBonusMalus.add(bonusMalus);
//            }
//
//        } else {
//            bonusMalus = bonusMalusDao.findEntityHavingValue("libelle", "Normal");
//            if (bonusMalus == null) {
//                bonusMalus = new OrclassBonusMalus();
//            }
//        }
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
//        String date = IdleDate.toString(new Date(), "dd/MM/yyyy");
//        curentDate = IdleDate.parseString(date, "dd/MM/yyyy");
//        System.out.println(" date :" + date);
//        System.out.println("curent date " + curentDate.toString());
//
//        this.updateDataTableContrat();
//
//    }
//
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//
//    public List<String> completeText(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//
//            results = assureDao.getAssuerCodeWithFilters(entreprise, query.toUpperCase());
//
//        }
//
//        return results;
//    }
//
//    public void onItemSelect(SelectEvent<String> event) {
//
//        assureCheck = assureDao.finKeyAssure(assure.getNom(), entreprise);
//
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
//    public void chargeFractionnementByCategories() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            listeFractionnement = fractionnementCategoriesDao.listFractionnementHaveCategories(categories, entreprise);
//
//        }
//    }
//
//    public void chargeReductionByCategories() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            listeReduction = reductionDao.listeReductionByCompagnieForCategorie(entreprise, categories);
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
//    public List<SelectItem> getTypeVehicule() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (TypeVehicule tv : TypeVehicule.values()) {
//            items.add(new SelectItem(tv, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tv.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getEnergie() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (Energie en : Energie.values()) {
//            items.add(new SelectItem(en, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, en.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getZoneVehicule() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (Zone z : Zone.values()) {
//            items.add(new SelectItem(z, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, z.name(), null, myLoc)));
//        }
//
//        return items;
//    }
//
//    public List<SelectItem> getTypePermis() {
//        List<SelectItem> items = new ArrayList<>();
//
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//
//        for (TypePermis tp : TypePermis.values()) {
//            items.add(new SelectItem(tp, LocaleHelper.getLocaleString(RecupBundle.FichierBundle, tp.name(), null, myLoc)));
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
//    public void chargeMajoratioDureeByDuree() {
//        if (duree != null && duree.getId() != null && categories != null && categories.getIdCategorie() != null) {
//            majorationDuree = majorationDureeDao.lastRowMajorationDuree(categories, entreprise, duree);
//            if (majorationDuree != null && majorationDuree.getId() != null) {
//                police.setIdMajorationDuree(majorationDuree);
//                if (duree.getMaxDuree() == null || duree.getMaxDuree().intValue() == 0) {
//                    police.setValeurDuree(duree.getMinDuree());
//                }
//                PrimeFaces.current().ajax().update(":form1:tabprincipal:tabsecond");
//                PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//            }
//        }
//    }
//
//    public void onTabChange(TabChangeEvent event) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
////        this.chargeFractionnementByCategories();
//        List<OrclassRefGaranties> listeRefGaranties = new ArrayList<>();
//        /*
//        verifition les champs obligatoire
//        
//        
//         */
//        if (categories == null || categories.getIdCategorie() == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE SELECT CATEGORY", "TRY AGAINST"));
////               PrimeFaces.current().executeScript("PF('accordP').select(0);");
//            return;
//        }
//        if ((police == null || police.getNumero_police() == null || police.getIdIntermediaire() == null) && categories != null && categories.getIdCategorie() != null) {
//
//            if (event.getTab().getId().equals("couverture")) {
//                if (assure.getIdActivite() == null || assure.getIdActivite().getId() == null) {
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(0);");
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "personne.ativite", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    PrimeFaces.current().dialog().openDynamic(summary + "-" + msgdetail);
//                    return;
//
//                } else if ((assure.getIdProfession() == null || assure.getIdProfession().getId() == null)) {
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(0);");
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "personne.profession", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    return;
//
//                } else if (assure.getDate_naissance() == null) {
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(0);");
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "personne.datenaissance", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    return;
//
//                } else if (assure.getAdresse() != null && assure.getAdresse().getTel() == null) {
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(0);");
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "adresse.telephone", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    return;
//
//                } else if (police.getDate_effet() == null) {
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(0);");
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "datedebut", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    return;
//
//                } else if (police.getDate_echeance() == null) {
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(0);");
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "dateEch", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    return;
//
//                }
//
//                this.chargeMajorationDuree();
//                if (listeDurees.isEmpty()) {
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "liste.duree.non.charge", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                }
//                this.chargeFractionnementByCategories();
//                this.chargeReductionByCategories();
//
//            } else if (event.getTab().getId().equals("donnee_speciale")) {
//                if (police.getValeurDuree() == null || police.getValeurDuree().intValue() == 0) {
//
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("VALEUR DUREE NON DEFINIT", "TRY AGAINST"));
//                    PrimeFaces.current().ajax().update(":form1:tabprincipal");
//                    return;
//
//                }
//                if (duree == null || duree.getId() == null) {
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "duree", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//                } else if (police.getValeurDuree() == null || police.getValeurDuree().intValue() == 0) {
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "valeur", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//
//                }
//                if (!listePoliceGarantie.isEmpty()) {
//                    return;
//                }
//
//                List<OrclassGarantie> listeRubriqueGaranties = new ArrayList<>();
//                List<OrclassGarantie> listeRubriqueGarantiesAllCompagnies = new ArrayList<>();
//                if (police.getIdTypeTarif() != null && police.getIdTypeTarif().getId() != null && categories != null && categories.getIdCategorie() != null) {
//                    if (listePoliceGarantie.isEmpty()) {
////                        listeGarantieNonEditable = garantieDao.listeGarantieNonEditable();
//
//                        /*
//                    on recupere les garanties de natures obligatoires
//                         */
//                        policeGarantie = new OrclassPoliceGarantie();
//                        listePoliceGarantie.add(policeGarantie);
//                        listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorie(police.getIdTypeTarif(), categories, entreprise);
//                        listeRubriqueGarantiesAllCompagnies = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieFprAllCompagnie(police.getIdTypeTarif(), categories);
//                        if (!listeRubriqueGarantiesAllCompagnies.isEmpty()) {
//                            for (OrclassGarantie rubAllComp : listeRubriqueGarantiesAllCompagnies) {
//                                if (listeRubriqueGarantie.contains(rubAllComp) == Boolean.FALSE) {
//                                    listeRubriqueGarantie.add(rubAllComp);
//                                }
//                            }
//
//                        }
//                        listeGarantieHaveForfairtairOrGratuit = garantieDao.listeGarantieHaveGratuitOrForfair(entreprise, categories);
//                        if (!listeGarantieHaveForfairtairOrGratuit.isEmpty()) {
//                            for (OrclassGarantie gf : listeGarantieHaveForfairtairOrGratuit) {
//                                if (listeRubriqueGarantie.contains(gf) == Boolean.FALSE) {
//                                    listeRubriqueGarantie.add(gf);
//                                }
//                            }
////                            listeRubriqueGarantie.addAll(listeGarantieHaveForfairtairOrGratuit);
//                        }
////                        this.listeRubriqueGarantieControleChangeGroup.clear();
////                        this.listeRubriqueGarantieControleChangeGroup.addAll(listeRubriqueGarantie);
//                        for (OrclassGarantie rg : listeRubriqueGarantie) {
////                            if (rg.getNatureGarantie().equals(NatureGarantie.obligatoire)) {
//                            policeGarantie = new OrclassPoliceGarantie();
//                            policeGarantie.setIdGarantie(rg);
//                            if (rg.getModeCalcul().equals(ModeCalcul.Automatique) || Objects.equals(rg.getGlobalCompagnie(), Boolean.TRUE)) {
//                                policeGarantie.setEditer(Boolean.FALSE);
//                                policeGarantie.setTaux_Majoration_reduction(BigDecimal.ZERO);
////                                    policeGarantie = this.calculPrimeAutomatiqueGarantie(policeGarantie);
//
//                            } else if (rg.getModeCalcul().equals(ModeCalcul.manuel)) {
//                                policeGarantie.setTaux_Majoration_reduction(BigDecimal.ZERO);
//                                policeGarantie.setEditer(Boolean.TRUE);
//                            }
//                            System.out.println("id group garantie " + refGroupeSelectForGarantie.getId());
////                                policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                            listePoliceGarantie.add(policeGarantie);
//                            listeRubriqueGaranties.add(rg);
////                            }
//
//                        }
//
//                        listeRubriqueGarantie.removeAll(listeRubriqueGaranties);
//                        // recuperer les garenties forfaitaire ou gratuit
//                        listeGarantieHaveForfairtairOrGratuit = garantieDao.listeGarantieHaveGratuitOrForfair(entreprise, categories);
//                        for (OrclassGarantie ga : listeGarantieHaveForfairtairOrGratuit) {
//
//                            if (listeRubriqueGarantie.contains(ga) == Boolean.FALSE) {
//                                listeRubriqueGarantie.add(ga);
//                            }
//
//                        }
//
//                    } else {
//                        if (Objects.equals(police.getValidation(), Boolean.FALSE) && listeRubriqueGarantie.isEmpty()) {
//                            listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorie(police.getIdTypeTarif(), rubriqueCategorie, entreprise);
//                            listeGarantieHaveForfairtairOrGratuit = garantieDao.listeGarantieHaveGratuitOrForfair(entreprise, rubriqueCategorie.getIdCategories());
//                            for (OrclassGarantie ga : listeGarantieHaveForfairtairOrGratuit) {
//
//                                if (listeRubriqueGarantie.contains(ga) == Boolean.FALSE) {
//                                    listeRubriqueGarantie.add(ga);
//                                }
//
//                            }
//                            for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//                                if (listeRubriqueGarantie.contains(pg.getIdGarantie()) == Boolean.TRUE) {
//                                    listeRubriqueGarantie.remove(pg.getIdGarantie());
//                                }
//                            }
//                        }
//                        this.updateDataTableRubriqueGarantie();
//                    }
//
//                    if (listeRubriqueGarantie.isEmpty() && listeRubriqueGaranties.isEmpty()) {
//                        summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                        msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "garantie.rubrique.not", null, myLoc);
//                        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                        return;
//                    }
//
//                }
//                this.updateDataTablePoliceGaranties();
//                this.updateDataTableRubriqueGarantie();
//                PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//            } else if (event.getTab().getId().equals("garantie")) {
//                List<OrclassGarantie> listeRubriqueGaranties = new ArrayList<>();
//                List<OrclassGarantie> listeRubriqueGarantiesAllCompagnies = new ArrayList<>();
//                if (police.getIdTypeTarif() != null && police.getIdTypeTarif().getId() != null && categories != null && categories.getIdCategorie() != null) {
//                    if (listePoliceGarantie.isEmpty()) {
////                        listeGarantieNonEditable = garantieDao.listeGarantieNonEditable();
//
//                        /*
//                    on recupere les garanties de natures obligatoires
//                         */
//                        policeGarantie = new OrclassPoliceGarantie();
//                        listePoliceGarantie.add(policeGarantie);
//                        listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorie(police.getIdTypeTarif(), categories, entreprise);
//                        listeRubriqueGarantiesAllCompagnies = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieFprAllCompagnie(police.getIdTypeTarif(), categories);
//                        if (!listeRubriqueGarantiesAllCompagnies.isEmpty()) {
//                            listeRubriqueGarantie.addAll(listeRubriqueGarantiesAllCompagnies);
//                        }
////                        this.listeRubriqueGarantieControleChangeGroup.clear();
////                        this.listeRubriqueGarantieControleChangeGroup.addAll(listeRubriqueGarantie);
//                        for (OrclassGarantie rg : listeRubriqueGarantie) {
//                            if (rg.getNatureGarantie().equals(NatureGarantie.obligatoire)) {
//                                policeGarantie = new OrclassPoliceGarantie();
//                                policeGarantie.setIdGarantie(rg);
//                                if (rg.getModeCalcul().equals(ModeCalcul.Automatique) || Objects.equals(rg.getGlobalCompagnie(), Boolean.TRUE)) {
//                                    policeGarantie.setEditer(Boolean.FALSE);
////                                    policeGarantie.setTaux_Majoration_reduction(BigDecimal.ZERO);
//                                    policeGarantie = this.calculPrimeAutomatiqueGarantie(policeGarantie);
//
//                                } else if (rg.getModeCalcul().equals(ModeCalcul.manuel)) {
//                                    policeGarantie.setEditer(Boolean.TRUE);
//                                }
//                                System.out.println("id group garantie " + refGroupeSelectForGarantie.getId());
////                                policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                                listePoliceGarantie.add(policeGarantie);
//                                listeRubriqueGaranties.add(rg);
//                            }
//
//                        }
//
//                        listeRubriqueGarantie.removeAll(listeRubriqueGaranties);
//                        // recuperer les garenties forfaitaire ou gratuit
//                        listeGarantieHaveForfairtairOrGratuit = garantieDao.listeGarantieHaveGratuitOrForfair(entreprise, categories);
//                        for (OrclassGarantie ga : listeGarantieHaveForfairtairOrGratuit) {
//
//                            if (listeRubriqueGarantie.contains(ga) == Boolean.FALSE) {
//                                listeRubriqueGarantie.add(ga);
//                            }
//
//                        }
//
//                    } else if (!listePoliceGarantie.isEmpty()) {
//                        List<OrclassPoliceGarantie> listegaranties = new ArrayList<>(listePoliceGarantie);
//                        listePoliceGarantie.clear();
//                        for (OrclassPoliceGarantie pg : listegaranties) {
//                            if (pg.getIdGarantie().getId() == null) {
//                                listePoliceGarantie.add(pg);
//                                continue;
//                            }
//                            if (pg.getIdGarantie().getModeCalcul().equals(ModeCalcul.Automatique)) {
//
////                                    policeGarantie.setTaux_Majoration_reduction(BigDecimal.ZERO);
//                                policeGarantie = this.calculPrimeAutomatiqueGarantie(pg);
//                                policeGarantie.setEditer(Boolean.FALSE);
//
//                            } else if (pg.getIdGarantie().getModeCalcul().equals(ModeCalcul.manuel)) {
//                                policeGarantie = pg;
////                                policeGarantie.setEditer(Boolean.TRUE);
//                            }
////                            if (pg.getIdGarantie().getId() != null && pg.getPrime() != null && pg.getPrime().intValue() != 0) {
////                                listePoliceGarantie.add(policeGarantie);
////                                continue;
////                            }
//                            listePoliceGarantie.add(policeGarantie);
//
//                        }
//                    }
//
//                }
//                this.caculTotalPrime();
//                this.updateDataTablePoliceGaranties();
//                this.updateDataTableRubriqueGarantie();
//                PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//            } else if (event.getTab().getId().equals("vehicule")) {
//
//            } else if (event.getTab().getId().equals("caracteristique")) {
//                if (listePoliceCaracteristique.isEmpty()) {
//                    chargeCaracteristiqueRubrique();
//                }
//
//                this.updateDataTablePoliceCarzacteristique();
//                this.updateDataTableRubriqueCaracteristique();
//                PrimeFaces.current().ajax().update(":form1:tabprincipal");
//            }
//
//            if (event.getTab().getId().equals("quittance")) {
//                /*
//          recupedes valeurs données speciale
//                 */
//                OrclassExonerationTaxe extaxe = null;
//                BigDecimal taxeprime = BigDecimal.ZERO;
//                total_reduction_majoration_donneçspecial = this.redMajGaranties();
//                quitance.setRedMajDonneeSpecial(total_reduction_majoration_donneçspecial);
//                //verifions si pour ce contrat une exoneration est prise en compte
//
//                if (police != null && police.getIdExoneration() != null && police.getIdExoneration().getIdExoneration() != null) {
//                    extaxe = exonerationTaxeDao.listExonerationTaxe(police.getIdExoneration(), entreprise);
//
//                }
//                this.chexkExoneration(extaxe);
//
//                //                BigDecimal taxe = BigDecimalsetTimbr_Gradue__vignette.valueOf(19.25);
//                BigDecimal total_a_paye = BigDecimal.ZERO;
//                //verifiont le choix sur le timbre dimensionnel
//                quitance.setTimbreDimension(police.getIdTimbreDimension() == null ? BigDecimal.ZERO : police.getValeur_timbre());
//
//                if (police.getDate_effet() != null) {
//                    quitance.setDate_effet(police.getDate_effet());
//                }
//                if (police.getDate_echeance() != null) {
//                    quitance.setDate_echeance(police.getDate_echeance());
//                }
//                quitance.setDateEmission(new Date());
////                quitance.setPrimeNette(this.totalProrata);
//
//                quitance.setPrimeNette(this.totalPrime);
//                try {
//                    System.out.println("taxePrime :" + this.quitance.getTaxe_tva().add(this.quitance.getTaxe_asac_fga()).add(this.quitance.getTaxe_tva_sur_asac()).add(this.quitance.getTaxe_pool_tpv()).add(this.quitance.getTaxe_caterose()));
//                    taxeprime = quitance.getTaxe_tva().add(quitance.getTaxe_asac_fga()).add(quitance.getTaxe_tva_sur_asac()).add(quitance.getTaxe_pool_tpv()).add(quitance.getTaxe_caterose());
//                    quitance.setTaxePrime(taxeprime);
//                    System.out.println("taxePrime :" + taxeprime);
//
//                } catch (Exception e) {
//                    quitance.setTaxePrime(BigDecimal.ZERO);
//                }
////                    quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//
//                quitance.setMontant_Accessoire(police.getMontantaccessoir());
//                quitance.setTypQuittance(TypeQuittance.emmission);
//
//                try {
//                    quitance.setPrimeTaxe(quitance.getPrimeNette().add(quitance.getTaxePrime()));
//                } catch (Exception e) {
//                    quitance.setPrimeTaxe(BigDecimal.ZERO);
//                }
//
//                try {
//                    quitance.setAccessoirTaxe(quitance.getTaxe_tva_acc().add(quitance.getMontant_Accessoire()));
//                } catch (Exception e) {
//                    quitance.setAccessoirTaxe(BigDecimal.ZERO);
//                }
//
//                quitance.setTotalTaxes(quitance.getTaxePrime().add(quitance.getAccessoirTaxe()));
//                quitance.setTimbreGradue(quitance.getTimbr_Gradue__vignette().add(quitance.getTimbr_Gradue_cp()));
//                if (police.getIdReduction() == null) {
//                    totalReduction = BigDecimal.ZERO;
//                    quitance.setReduction(this.totalReduction);
//                } else {
//                    quitance.setReduction(this.totalReduction);
//                    totalReduction = BigDecimal.ZERO;
//                }
//
////                BigDecimal timbreDimension = (police.getValeur_timbre() != null && police.getNombre_timbre() != null) ? BigDecimal.valueOf(police.getNombre_timbre().doubleValue()).multiply(police.getValeur_timbre()) : BigDecimal.ZERO;
////
////                quitance.setTimbreDimension(timbreDimension);
//                try {
//                    if (quitance.getMontant_Accessoire() == null) {
//                        quitance.setMontant_Accessoire(BigDecimal.ZERO);
//                        quitance.setAccessoirTaxe(BigDecimal.ZERO);
//                    }
//                    if (quitance.getTimbreDimension() == null) {
//                        quitance.setTimbreDimension(BigDecimal.ZERO);
//                    }
//                    if (quitance.getTimbreGradue() == null) {
//                        quitance.setTimbreGradue(BigDecimal.ZERO);
//                    }
//
//                    total_a_paye = quitance.getPrimeNette().add(quitance.getTaxePrime()).add(quitance.getMontant_Accessoire()).add(quitance.getAccessoirTaxe()).add(quitance.getTimbreDimension())
//                            .add(quitance.getTimbreGradue());
//                    quitance.setTotal_a_paye(total_a_paye);
//                } catch (Exception e) {
//                    total_a_paye = BigDecimal.ZERO;
//
//                }
////                quitance.setBonus(BigDecimal.ZERO);// a revoir pour son calcul
//                quitance.setTotalTimb(quitance.getTimbreGradue().add(quitance.getTimbreDimension()));
////                if (police.getIdApporteur() != null && police.getIdApporteur().getIdApporteur() != null) {
////                    chargePrimeApproteur();adherent
////                }
//// appelle  de quelque operation
//                this.calculNetForPrimeHaveRedCommercial();
//                this.calculNetForPrimeHaveRedBonus();
//                this.calculNetForPrimeHaveRedTarif1();
//                this.calculNetForPrimeHaveRedTarif2();
//                this.calculNetForPrimeHaveRedTarif2();
//                this.calculNetForPrimeHaveRedTarif3();
//                this.calculNetForPrimeHaveMajAge();
//                this.calculNetForPrimeHaveMajMatiereInflamable();
//                this.calculNetForPrimeHaveMajPermis();
//                this.chargePrimeApproteur();
//            }
//
//        } else if ((police != null && police.getId() != null && Objects.equals(police.getValidation(), Boolean.FALSE)) && categories != null && categories.getIdCategorie() != null) {
//            if (event.getTab().getId().equals("garantie") || event.getTab().getId().equals("donnee_speciale")) {
//                List<OrclassGarantie> listeRubriqueGaranties = new ArrayList<>();
//                List<OrclassGarantie> listeRubriqueGarantiesAllCompagnies = new ArrayList<>();
//
//                if (!listePoliceGarantie.isEmpty() && listePoliceGarantie.size() != 1) {
//                    if (event.getTab().getId().equals("donnee_speciale")) {
//                        this.caculTotalPrime();
//                        this.updateDataTablePoliceGaranties();
//                        this.updateDataTableRubriqueGarantie();
//                        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//                        return;
//                    }
//                    List<OrclassPoliceGarantie> listePoliceGrantie = new ArrayList<>(listePoliceGarantie);
//                    /*
//                 on liste les garantie qui ne sont pas dans le contrat mais prevu 
//                     */
//                    listeRubriqueGarantie = garantieDao.listeGarantiesNotAddPoliceGatie(entreprise, categories, police);
//                    listePoliceGarantie = new ArrayList<>();
//                    if (event.getTab().getId().equals("garantie")) {
//                        if (police.getIdTypeTarif() != null && police.getIdTypeTarif().getId() != null && categories != null && categories.getIdCategorie() != null) {
//
//
//                            /*
//                    on recupere les garanties de natures obligatoires
//                             */
//                            policeGarantie = new OrclassPoliceGarantie();
//                            listePoliceGarantie.add(policeGarantie);
//
//                            for (OrclassPoliceGarantie rg : listePoliceGrantie) {
//                                if (rg.getIdGarantie() == null || rg.getIdGarantie().getId() == null) {
//                                    continue;
//                                }
////                                if (rg.getIdGarantie().getNatureGarantie().equals(NatureGarantie.obligatoire)) {
////                                policeGarantie = new OrclassPoliceGarantie();
////                                policeGarantie.setIdGarantie(rg.getIdGarantie());
//                                policeGarantie = rg;
//                                if (rg.getIdGarantie().getModeCalcul().equals(ModeCalcul.Automatique) || Objects.equals(rg.getIdGarantie().getGlobalCompagnie(), Boolean.TRUE)) {
//                                    policeGarantie.setEditer(Boolean.FALSE);
////                                    policeGarantie.setTaux_Majoration_reduction(BigDecimal.ZERO);
//                                    policeGarantie = this.calculPrimeAutomatiqueGarantie(policeGarantie);
//
//                                } else if (rg.getIdGarantie().getModeCalcul().equals(ModeCalcul.manuel)) {
//                                    policeGarantie.setEditer(Boolean.TRUE);
//                                }
//                                System.out.println("id group garantie " + refGroupeSelectForGarantie.getId());
////                                policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                                listePoliceGarantie.add(policeGarantie);
//                                listeRubriqueGaranties.add(rg.getIdGarantie());
////                                }
//
//                            }
//
//                            listeRubriqueGarantie.removeAll(listeRubriqueGaranties);
//
//                        }
//                        this.caculTotalPrime();
//                        this.updateDataTablePoliceGaranties();
//                        this.updateDataTableRubriqueGarantie();
//                        PrimeFaces.current().ajax().update(":form1:tabprincipal");
////                        return;
//                    }
//
//                    return;
//                }
//                listePoliceGarantie = new ArrayList<>();
//
//                listeRubriqueGarantie = garantieDao.listeGarantiesNotAddPoliceGatie(entreprise, categories, police);
//
//                if (event.getTab().getId().equals("donnee_speciale")) {
////                    if (listePoliceGarantie.isEmpty()) {
////                        listeGarantieNonEditable = garantieDao.listeGarantieNonEditable();
//
//                    /*
//                    on recupere les garanties de natures obligatoires
//                     */
//                    policeGarantie = new OrclassPoliceGarantie();
//                    listePoliceGarantie.add(policeGarantie);
//
//                    for (OrclassGarantie rg : listeRubriqueGarantie) {
////                            if (rg.getNatureGarantie().equals(NatureGarantie.obligatoire)) {
//                        policeGarantie = new OrclassPoliceGarantie();
//                        policeGarantie.setIdGarantie(rg);
//                        if (rg.getModeCalcul().equals(ModeCalcul.Automatique) || Objects.equals(rg.getGlobalCompagnie(), Boolean.TRUE)) {
//                            policeGarantie.setEditer(Boolean.FALSE);
//                            policeGarantie.setTaux_Majoration_reduction(BigDecimal.ZERO);
////                                    policeGarantie = this.calculPrimeAutomatiqueGarantie(policeGarantie);
//
//                        } else if (rg.getModeCalcul().equals(ModeCalcul.manuel)) {
//                            policeGarantie.setTaux_Majoration_reduction(BigDecimal.ZERO);
//                            policeGarantie.setEditer(Boolean.TRUE);
//                        }
//                        System.out.println("id group garantie " + refGroupeSelectForGarantie.getId());
////                                policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                        listePoliceGarantie.add(policeGarantie);
//                        listeRubriqueGaranties.add(rg);
////                            }
//
//                    }
//
//                    listeRubriqueGarantie.removeAll(listeRubriqueGaranties);
//
//                    this.updateDataTablePoliceGaranties();
//                    this.updateDataTableRubriqueGarantie();
//                    PrimeFaces.current().ajax().update(":form1:tabprincipal");
//                    return;
//
//                }
//                if (event.getTab().getId().equals("garantie")) {
//                    if (police.getIdTypeTarif() != null && police.getIdTypeTarif().getId() != null && categories != null && categories.getIdCategorie() != null) {
//
//
//                        /*
//                    on recupere les garanties de natures obligatoires
//                         */
//                        policeGarantie = new OrclassPoliceGarantie();
//                        listePoliceGarantie.add(policeGarantie);
//
//                        for (OrclassGarantie rg : listeRubriqueGarantie) {
//                            if (rg.getNatureGarantie().equals(NatureGarantie.obligatoire)) {
//                                policeGarantie = new OrclassPoliceGarantie();
//                                policeGarantie.setIdGarantie(rg);
//                                if (rg.getModeCalcul().equals(ModeCalcul.Automatique) || Objects.equals(rg.getGlobalCompagnie(), Boolean.TRUE)) {
//                                    policeGarantie.setEditer(Boolean.FALSE);
////                                    policeGarantie.setTaux_Majoration_reduction(BigDecimal.ZERO);
//                                    policeGarantie = this.calculPrimeAutomatiqueGarantie(policeGarantie);
//
//                                } else if (rg.getModeCalcul().equals(ModeCalcul.manuel)) {
//                                    policeGarantie.setEditer(Boolean.TRUE);
//                                }
//                                System.out.println("id group garantie " + refGroupeSelectForGarantie.getId());
////                                policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                                listePoliceGarantie.add(policeGarantie);
//                                listeRubriqueGaranties.add(rg);
//                            }
//
//                        }
//
//                        listeRubriqueGarantie.removeAll(listeRubriqueGaranties);
//
//                    }
//                    this.caculTotalPrime();
//                    this.updateDataTablePoliceGaranties();
//                    this.updateDataTableRubriqueGarantie();
//                    PrimeFaces.current().ajax().update(":form1:tabprincipal");
//                    return;
//                }
//
////                } 
//            } else if (event.getTab().getId().equals("quittance")) {
//                /*
//          recupedes valeurs données speciale
//                 */
//                OrclassExonerationTaxe extaxe = null;
//                BigDecimal taxeprime = BigDecimal.ZERO;
//                total_reduction_majoration_donneçspecial = this.redMajGaranties();
//                quitance.setRedMajDonneeSpecial(total_reduction_majoration_donneçspecial);
//                //verifions si pour ce contrat une exoneration est prise en compte
//
//                if (police != null && police.getIdExoneration() != null && police.getIdExoneration().getIdExoneration() != null) {
//                    extaxe = exonerationTaxeDao.listExonerationTaxe(police.getIdExoneration(), entreprise);
//
//                }
//                this.chexkExoneration(extaxe);
//
//                //                BigDecimal taxe = BigDecimalsetTimbr_Gradue__vignette.valueOf(19.25);
//                BigDecimal total_a_paye = BigDecimal.ZERO;
//                //verifiont le choix sur le timbre dimensionnel
//                quitance.setTimbreDimension(police.getIdTimbreDimension() == null ? BigDecimal.ZERO : police.getValeur_timbre());
//
//                if (police.getDate_effet() != null) {
//                    quitance.setDate_effet(police.getDate_effet());
//                }
//                if (police.getDate_echeance() != null) {
//                    quitance.setDate_echeance(police.getDate_echeance());
//                }
//                quitance.setDateEmission(new Date());
////                quitance.setPrimeNette(this.totalProrata);
//
//                quitance.setPrimeNette(this.totalPrime);
//                try {
//                    System.out.println("taxePrime :" + this.quitance.getTaxe_tva().add(this.quitance.getTaxe_asac_fga()).add(this.quitance.getTaxe_tva_sur_asac()).add(this.quitance.getTaxe_pool_tpv()).add(this.quitance.getTaxe_caterose()));
//                    taxeprime = quitance.getTaxe_tva().add(quitance.getTaxe_asac_fga()).add(quitance.getTaxe_tva_sur_asac()).add(quitance.getTaxe_pool_tpv()).add(quitance.getTaxe_caterose());
//                    quitance.setTaxePrime(taxeprime);
//                    System.out.println("taxePrime :" + taxeprime);
//
//                } catch (Exception e) {
//                    quitance.setTaxePrime(BigDecimal.ZERO);
//                }
////                    quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//
//                quitance.setMontant_Accessoire(police.getMontantaccessoir());
//                quitance.setTypQuittance(TypeQuittance.emmission);
//
//                try {
//                    quitance.setPrimeTaxe(quitance.getPrimeNette().add(quitance.getTaxePrime()));
//                } catch (Exception e) {
//                    quitance.setPrimeTaxe(BigDecimal.ZERO);
//                }
//
//                try {
//                    quitance.setAccessoirTaxe(quitance.getTaxe_tva_acc().add(quitance.getMontant_Accessoire()));
//                } catch (Exception e) {
//                    quitance.setAccessoirTaxe(BigDecimal.ZERO);
//                }
//
//                quitance.setTotalTaxes(quitance.getTaxePrime().add(quitance.getAccessoirTaxe()));
//                quitance.setTimbreGradue(quitance.getTimbr_Gradue__vignette().add(quitance.getTimbr_Gradue_cp()));
//                if (police.getIdReduction() == null) {
//                    totalReduction = BigDecimal.ZERO;
//                    quitance.setReduction(this.totalReduction);
//                } else {
//                    quitance.setReduction(this.totalReduction);
//                    totalReduction = BigDecimal.ZERO;
//                }
//
////                BigDecimal timbreDimension = (police.getValeur_timbre() != null && police.getNombre_timbre() != null) ? BigDecimal.valueOf(police.getNombre_timbre().doubleValue()).multiply(police.getValeur_timbre()) : BigDecimal.ZERO;
////
////                quitance.setTimbreDimension(timbreDimension);
//                try {
//                    if (quitance.getMontant_Accessoire() == null) {
//                        quitance.setMontant_Accessoire(BigDecimal.ZERO);
//                        quitance.setAccessoirTaxe(BigDecimal.ZERO);
//                    }
//                    if (quitance.getTimbreDimension() == null) {
//                        quitance.setTimbreDimension(BigDecimal.ZERO);
//                    }
//                    if (quitance.getTimbreGradue() == null) {
//                        quitance.setTimbreGradue(BigDecimal.ZERO);
//                    }
//
//                    total_a_paye = quitance.getPrimeNette().add(quitance.getTaxePrime()).add(quitance.getMontant_Accessoire()).add(quitance.getAccessoirTaxe()).add(quitance.getTimbreDimension())
//                            .add(quitance.getTimbreGradue());
//                    quitance.setTotal_a_paye(total_a_paye);
//                } catch (Exception e) {
//                    total_a_paye = BigDecimal.ZERO;
//
//                }
//
//            }
//        }
//    }
//
//    /*
//    recuperationdes reductionet malorationsur les garanties
//     */
//    public BigDecimal redMajGaranties() {
//        BigDecimal valeur_taux_reduction = BigDecimal.ZERO;
//        BigDecimal valeur_taux_Majoration = BigDecimal.ZERO;
//        BigDecimal total_red_maj_specialGarantie = BigDecimal.ZERO;
//        BigDecimal taux_red_maj = BigDecimal.ZERO;
//        BigDecimal valeur_taux_red_maj = BigDecimal.ZERO;
//        BigDecimal total_red_maj = BigDecimal.ZERO;
//
//        if (!listePoliceGarantie.isEmpty()) {
//            for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//                if (pg != null && pg.getIdGarantie() != null && pg.getIdGarantie().getId() != null && pg.getTaux_Majoration_reduction() != null && pg.getPrime() != null) {
//                    if (pg.getTaux_Majoration_reduction().signum() == -1) {
//                        valeur_taux_reduction = valeur_taux_reduction.add((pg.getPrime_sans_reduction_ou_operation().multiply(pg.getTaux_Majoration_reduction())).divide(BigDecimal.valueOf(100.0)));
//                    } else {
//                        valeur_taux_Majoration = valeur_taux_Majoration.add((pg.getPrime_sans_reduction_ou_operation().multiply(pg.getTaux_Majoration_reduction())).divide(BigDecimal.valueOf(100.0)));
//                    }
////                    taux_red_maj = pg.getTaux_Majoration_reduction();
////                    valeur_taux_red_maj = (pg.getPrime().multiply(taux_red_maj)).divide(BigDecimal.valueOf(100.0));
////                    total_red_maj_specialGarantie = total_red_maj_specialGarantie.add(valeur_taux_red_maj);
//
//                }
//            }
//            total_red_maj_specialGarantie = valeur_taux_Majoration.add(valeur_taux_reduction);
//        }
//        return total_red_maj_specialGarantie;
//    }
//
//    public void chargePrimeApproteur() {
//        /*
//        verifion les primes apporteur lier au garanties du projet
//         */
//        BigDecimal taux_apport = BigDecimal.ZERO;
//        BigDecimal montant_taux_apport = BigDecimal.ZERO;
//        BigDecimal montant_taux_Gesgtion = BigDecimal.ZERO;
//        BigDecimal taux_Gesgtion = BigDecimal.ZERO;
//        OrclassCommission_Prime_Apporteur commission_Prime_Apporteur;
//        OrclassPoliceCommissionApporteur pcap;
//        listePoliceCommissionApporteur.clear();
////        if (apporteur != null && apporteur.getIdApporteur() != null) {
////            police.setIdApporteur(apporteur);
////        }
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
//                    montant_taux_Gesgtion = montant_taux_Gesgtion.add((commission_Prime_Apporteur.getTaux_gestion().multiply(pg.getPrime())).divide(BigDecimal.valueOf(100.0)));
//                    taux_apport = taux_apport.add(commission_Prime_Apporteur.getTaux_apport());
//                    montant_taux_apport = montant_taux_apport.add((commission_Prime_Apporteur.getTaux_apport().multiply(pg.getPrime())).divide(BigDecimal.valueOf(100.0)));
//                    pcap = new OrclassPoliceCommissionApporteur();
//                    pcap.setIdCommission_Prime_Apporteur(commission_Prime_Apporteur);
//                    pcap.setTaux_apport(commission_Prime_Apporteur.getTaux_apport());
//                    pcap.setTaux_gestion(commission_Prime_Apporteur.getTaux_gestion());
//                    listePoliceCommissionApporteur.add(pcap);
//                    continue;
//                }
//            }
//            // verifions que la prime est directement lier a l apporteur
//            if (listePoliceCommissionApporteur.isEmpty()) {
//                commission_Prime_Apporteur = commission_Prime_ApporteurDao.getPrimeCommissionByApporteur(police.getIdApporteur(), categories, entreprise);
//
//                if (commission_Prime_Apporteur != null && commission_Prime_Apporteur.getId() != null) {
//                    taux_Gesgtion = taux_Gesgtion.add(commission_Prime_Apporteur.getTaux_gestion());
//                    montant_taux_Gesgtion = montant_taux_Gesgtion.add((commission_Prime_Apporteur.getTaux_gestion().multiply(totalPrime)).divide(BigDecimal.valueOf(100.0)));
//
//                    taux_apport = taux_apport.add(commission_Prime_Apporteur.getTaux_apport());
//                    montant_taux_apport = montant_taux_apport.add((commission_Prime_Apporteur.getTaux_apport().multiply(totalPrime)).divide(BigDecimal.valueOf(100.0)));
//                    pcap = new OrclassPoliceCommissionApporteur();
//                    pcap.setIdCommission_Prime_Apporteur(commission_Prime_Apporteur);
//                    pcap.setTaux_apport(commission_Prime_Apporteur.getTaux_apport());
//                    pcap.setTaux_gestion(commission_Prime_Apporteur.getTaux_gestion());
//                    listePoliceCommissionApporteur.add(pcap);
//
//                }
//            }
//
//            //verifion si la prime conserne le type d apporteur
//            if (listePoliceCommissionApporteur.isEmpty()) {
//                for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//                    commission_Prime_Apporteur = commission_Prime_ApporteurDao.getPrimeCommissionByTypeApporteurHaveGarantie(police.getIdApporteur().getIdTypeApporteur(), categories, pg.getIdGarantie(), entreprise);
//
//                    if (commission_Prime_Apporteur != null && commission_Prime_Apporteur.getId() != null) {
//                        taux_Gesgtion = taux_Gesgtion.add(commission_Prime_Apporteur.getTaux_gestion());
//                        montant_taux_Gesgtion = montant_taux_Gesgtion.add((commission_Prime_Apporteur.getTaux_gestion().multiply(totalPrime)).divide(BigDecimal.valueOf(100.0)));
//
//                        taux_apport = taux_apport.add(commission_Prime_Apporteur.getTaux_apport());
//                        montant_taux_apport = montant_taux_apport.add((commission_Prime_Apporteur.getTaux_apport().multiply(totalPrime)).divide(BigDecimal.valueOf(100.0)));
//                        pcap = new OrclassPoliceCommissionApporteur();
//                        pcap.setIdCommission_Prime_Apporteur(commission_Prime_Apporteur);
//                        pcap.setTaux_apport(commission_Prime_Apporteur.getTaux_apport());
//                        pcap.setTaux_gestion(commission_Prime_Apporteur.getTaux_gestion());
//                        listePoliceCommissionApporteur.add(pcap);
//
//                    }
//                }
//            }
//
//            if (listePoliceCommissionApporteur.isEmpty()) {
//                commission_Prime_Apporteur = commission_Prime_ApporteurDao.getPrimeCommissionByTypeApporteur(police.getIdApporteur().getIdTypeApporteur(), categories, entreprise);
//
//                if (commission_Prime_Apporteur != null && commission_Prime_Apporteur.getId() != null) {
//                    taux_Gesgtion = taux_Gesgtion.add(commission_Prime_Apporteur.getTaux_gestion());
//                    montant_taux_Gesgtion = montant_taux_Gesgtion.add((commission_Prime_Apporteur.getTaux_gestion().multiply(totalPrime)).divide(BigDecimal.valueOf(100.0)));
//
//                    taux_apport = taux_apport.add(commission_Prime_Apporteur.getTaux_apport());
//                    montant_taux_apport = montant_taux_apport.add((commission_Prime_Apporteur.getTaux_apport().multiply(totalPrime)).divide(BigDecimal.valueOf(100.0)));
//                    pcap = new OrclassPoliceCommissionApporteur();
//                    pcap.setIdCommission_Prime_Apporteur(commission_Prime_Apporteur);
//                    pcap.setTaux_apport(commission_Prime_Apporteur.getTaux_apport());
//                    pcap.setTaux_gestion(commission_Prime_Apporteur.getTaux_gestion());
//                    listePoliceCommissionApporteur.add(pcap);
//
//                }
//            }
//
//            this.quitance.setTaux_apport(taux_apport);
//            this.quitance.setTaux_gestion(taux_Gesgtion);
//            this.quitance.setMontantApport(montant_taux_apport);
//            this.quitance.setMontantGestion(montant_taux_Gesgtion);
//            total_commission = montant_taux_Gesgtion.add(montant_taux_apport);
//            quitance.setMontantCommision(total_commission);
//        }
//
//    }
//
//    //quelques operation
//    public void calculNetForPrimeHaveRedCommercial() {
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (quitance.getReduction_commercial() != null && quitance.getReduction_commercial() != BigDecimal.ZERO) {
//
//            total_a_paye = quitance.getTotal_a_paye().subtract(quitance.getReduction_commercial());
//            quitance.setTotal_a_paye(total_a_paye);
//
//        }
//    }
//
//    public void calculNetForPrimeHaveRedBonus() {
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (quitance.getBonus() != null && quitance.getBonus() != BigDecimal.ZERO) {
//            total_a_paye = quitance.getTotal_a_paye().subtract(quitance.getBonus());
//            quitance.setTotal_a_paye(total_a_paye);
//
//        }
//    }
//
//    public void calculNetForPrimeHaveRedTarif1() {
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (quitance.getReduction_tarif1() != null && quitance.getReduction_tarif1() != BigDecimal.ZERO) {
//            total_a_paye = quitance.getTotal_a_paye().subtract(quitance.getReduction_tarif1());
//            quitance.setTotal_a_paye(total_a_paye);
//
//        }
//    }
//
//    public void calculNetForPrimeHaveRedTarif2() {
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (quitance.getReduction_tarif2() != null && quitance.getReduction_tarif2() != BigDecimal.ZERO) {
//            total_a_paye = quitance.getTotal_a_paye().subtract(quitance.getReduction_tarif2());
//            quitance.setTotal_a_paye(total_a_paye);
//
//        }
//    }
//
//    public void calculNetForPrimeHaveRedTarif3() {
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (quitance.getReduction_tarif3() != null && quitance.getReduction_tarif3() != BigDecimal.ZERO) {
//            total_a_paye = quitance.getTotal_a_paye().subtract(quitance.getReduction_tarif3());
//            quitance.setTotal_a_paye(total_a_paye);
//
//        }
//    }
//
//    public void calculNetForPrimeHaveMajPermis() {
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (quitance.getMajoration_permis() != null && quitance.getMajoration_permis() != BigDecimal.ZERO) {
//            total_a_paye = quitance.getTotal_a_paye().add(quitance.getMajoration_permis());
//            quitance.setTotal_a_paye(total_a_paye);
//
//        }
//    }
//
//    public void calculNetForPrimeHaveMajAge() {
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (quitance.getMajoration_age() != null && quitance.getMajoration_age() != BigDecimal.ZERO) {
//            total_a_paye = quitance.getTotal_a_paye().add(quitance.getMajoration_age());
//            quitance.setTotal_a_paye(total_a_paye);
//
//        }
//    }
//
//    public void calculNetForPrimeHaveMajMatiereInflamable() {
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (quitance.getMatiere_inflamable() != null && quitance.getMatiere_inflamable() != BigDecimal.ZERO) {
//            total_a_paye = quitance.getTotal_a_paye().add(quitance.getMatiere_inflamable());
//            quitance.setTotal_a_paye(total_a_paye);
//
//        }
//    }
//
//
//    /*
//    controlle des exoneration ou non
//     */
//    public void chexkExoneration(OrclassExonerationTaxe extaxe) {
//        BigDecimal montant = BigDecimal.ZERO;
//        BigDecimal prcent = BigDecimal.TEN.multiply(BigDecimal.TEN);
//        if (extaxe != null && extaxe.getIdExonerationTaxe() != null) {
//            if ((extaxe.getIdTypeTaxe() != null && extaxe.getIdTypeTaxe().getIdTypeTaxe() != null && extaxe.getIdRefTimbreGradue() != null && extaxe.getIdRefTimbreGradue().getId() != null)) {
//                //ici l exoneration est fait sur la taxe et le timbre gradue en meme temps 
//                //recuperons en premier la taxe
//                switch (extaxe.getIdTypeTaxe().getLibelleFrais()) {
//                    case tva: // ce contrat a ete exonerer de la taxe tva 
//                        quitance.setTaxe_tva(BigDecimal.ZERO);
//
//                        break;
//
//                    case asac_fga:
//                        quitance.setTaxe_asac_fga(BigDecimal.ZERO);
//                        break;
//                    case carte_rose:
//
//                        quitance.setTaxe_caterose(BigDecimal.ZERO);
//                        break;
//                    case pool_tpv:
//                        quitance.setTaxe_pool_tpv(BigDecimal.ZERO);
//                        break;
//                    case tva_acc:
//                        quitance.setTaxe_tva_acc(BigDecimal.ZERO);
//                        break;
//                    case tva_sur_asac:
//                        quitance.setTaxe_tva_sur_asac(BigDecimal.ZERO);
//                        break;
//                    case tous_les_taxes:
//                        quitance.setTaxe_tva(BigDecimal.ZERO);
//                        quitance.setTaxe_asac_fga(BigDecimal.ZERO);
//                        quitance.setTaxe_caterose(BigDecimal.ZERO);
//                        quitance.setTaxe_pool_tpv(BigDecimal.ZERO);
//
//                        quitance.setTaxe_tva_acc(BigDecimal.ZERO);
//                        quitance.setTaxe_tva_sur_asac(BigDecimal.ZERO);
//                        break;
//
//                }
//
//                switch (extaxe.getIdRefTimbreGradue().getLibelleFrais()) {
//                    case vignette:
//
//                        quitance.setTimbr_Gradue__vignette(BigDecimal.ZERO);
//                        break;
//                }
//
//            } else if ((extaxe.getIdTypeTaxe() != null && extaxe.getIdTypeTaxe().getIdTypeTaxe() != null && (extaxe.getIdRefTimbreGradue() == null || extaxe.getIdRefTimbreGradue().getId() == null))) {
//
//                if ((extaxe.getIdTypeTaxe() != null && extaxe.getIdTypeTaxe().getIdTypeTaxe() != null && extaxe.getIdRefTimbreGradue() != null && extaxe.getIdRefTimbreGradue().getId() != null)) {
//                    //ici l exoneration est fait sur la taxe et le timbre gradue en meme temps 
//                    //recuperons en premier la taxe
//                    switch (extaxe.getIdTypeTaxe().getLibelleFrais()) {
//                        case tva: // ce contrat a ete exonerer de la taxe tva 
//                            quitance.setTaxe_tva(BigDecimal.ZERO);
//
//                            break;
//
//                        case asac_fga:
//                            quitance.setTaxe_asac_fga(BigDecimal.ZERO);
//                            break;
//                        case carte_rose:
//
//                            quitance.setTaxe_caterose(BigDecimal.ZERO);
//                            break;
//                        case pool_tpv:
//                            quitance.setTaxe_pool_tpv(BigDecimal.ZERO);
//                            break;
//                        case tva_acc:
//                            quitance.setTaxe_tva_acc(BigDecimal.ZERO);
//                            break;
//                        case tva_sur_asac:
//                            quitance.setTaxe_tva_sur_asac(BigDecimal.ZERO);
//                            break;
//                        case tous_les_taxes:
//                            quitance.setTaxe_tva(BigDecimal.ZERO);
//                            quitance.setTaxe_asac_fga(BigDecimal.ZERO);
//                            quitance.setTaxe_caterose(BigDecimal.ZERO);
//                            quitance.setTaxe_pool_tpv(BigDecimal.ZERO);
//                            quitance.setTaxe_pool_tpv(BigDecimal.ZERO);
//                            quitance.setTaxe_tva_acc(BigDecimal.ZERO);
//                            quitance.setTaxe_tva_sur_asac(BigDecimal.ZERO);
//                            break;
//
//                    }
//                }
//
//            } else if (((extaxe.getIdTypeTaxe() == null || extaxe.getIdTypeTaxe().getIdTypeTaxe() == null) && (extaxe.getIdRefTimbreGradue() != null && extaxe.getIdRefTimbreGradue().getId() != null))) {
//
//                switch (extaxe.getIdRefTimbreGradue().getLibelleFrais()) {
//                    case vignette:
//
//                        quitance.setTimbr_Gradue__vignette(BigDecimal.ZERO);
//                        break;
//                }
//            }
//
//        } else if (police != null && (police.getIdExoneration() == null || police.getIdExoneration().getIdExoneration() == null) && extaxe == null) {
//
//            for (OrclassTaxePrime tp : taxePrimeDao.listeTaxePrimeByCategories(entreprise, categories)) {
//                if (tp.getBaseCalculTaxePrime().equals(BaseCalculTaxePrime.taxe_fond)) {
//                    continue;
//                }
//                switch (tp.getIdTypeTaxe().getLibelleFrais()) {
//                    case tva:
//                        montant = tp.getForfaitaire() == false ? ((tp.getTaux().divide(prcent))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//                        System.out.println("taux pourcent:" + montant);
//                        montant = montant.multiply(totalPrime);
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                        quitance.setTaxe_tva(montant);
//                        System.out.println("Taxe_tva:" + montant);
//
//                        break;
//                    case carte_rose:
//
//                        montant = tp.getForfaitaire() == false ? (tp.getTaux().multiply(totalPrime).divide(BigDecimal.valueOf(100.0))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                        quitance.setTaxe_caterose(montant);
//                        System.out.println("Taxe_caterose:" + montant);
//
//                        break;
//                    case asac_fga:
//                        montant = tp.getForfaitaire() == false ? (tp.getTaux().multiply(totalPrime).divide(BigDecimal.valueOf(100.0))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                        quitance.setTaxe_asac_fga(montant);
//                        System.out.println("Taxe_asac_fga:" + montant);
//                        break;
//                    case pool_tpv:
//                        montant = tp.getForfaitaire() == false ? (tp.getTaux().multiply(totalPrime).divide(BigDecimal.valueOf(100.0))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                        quitance.setTaxe_pool_tpv(montant);
//                        System.out.println("Taxe_pool_tpv:" + montant);
//                    case tva_acc:
//                        montant = (tp.getForfaitaire() == false && police.getMontantaccessoir() != null) ? (tp.getTaux().multiply(police.getMontantaccessoir().divide(BigDecimal.valueOf(100.0)))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                        quitance.setTaxe_tva_acc(montant);
//                        System.out.println("tva_acc:" + montant);
//                        break;
//                    case tva_sur_asac:
//                        montant = tp.getForfaitaire() == false ? (tp.getTaux().multiply(totalPrime).divide(BigDecimal.valueOf(100.0))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                        quitance.setTaxe_tva_sur_asac(montant);
//                        System.out.println("Taxe_tva_sur_asac:" + montant);
//                        break;
//                }
//            }
//            //recuperation de la vignette et autre pour les timbre graduéé
//
//            OrclassTimbre t = (risque.getPuissance_vehicule() == null || risque.getPuissance_vehicule().intValue() == 0) ? null : this.getBaremeForTimbreGratueeVignette(risque.getPuissance_vehicule(), LibelleFrais.vignette);
//            if (t == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "LA PUISSANCE DU VEHICULE NON RENSEIGNE OU VALEUR NON EXISTANT"));
//                PrimeFaces.current().executeScript("PF('accordP').select(2);");
//                return;
//            }
//            quitance.setTimbr_Gradue__vignette(t.getValeur_tg_cp_fg());
//            return;
//        }
//        /*
//        ceci est fait pour empecher une autre verification parceque l exoneration est porter sur tous les taxes 
//         */
//
//        if (extaxe.getIdTypeTaxe() != null && extaxe.getIdTypeTaxe().getLibelleFrais().equals(LibelleFrais.tous_les_taxes) == Boolean.TRUE) {
//            if (extaxe.getIdRefTimbreGradue() != null && extaxe.getIdRefTimbreGradue().getLibelleFrais().equals(LibelleFrais.tous_les_taxes) == Boolean.FALSE) {
//                OrclassTimbre t = (extaxe.getIdRefTimbreGradue() != null && extaxe.getIdRefTimbreGradue().getLibelleFrais().equals(LibelleFrais.vignette) == Boolean.TRUE) ? new OrclassTimbre() : (risque.getPuissance_vehicule() == null || risque.getPuissance_vehicule().intValue() == 0) ? null : this.getBaremeForTimbreGratueeVignette(risque.getPuissance_vehicule(), LibelleFrais.vignette);
//                if (t == null) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "LA PUISSANCE DU VEHICULE NON RENSEIGNE OU VALEUR NON EXISTANT"));
//                    PrimeFaces.current().executeScript("PF('accordP').select(2);");
//                    return;
//                }
//
//                quitance.setTimbr_Gradue__vignette(t.getValeur_tg_cp_fg() == null ? BigDecimal.ZERO : t.getValeur_tg_cp_fg());
//            }
//            return;
//        }
//        if (extaxe.getIdRefTimbreGradue() != null && extaxe.getIdRefTimbreGradue().getLibelleFrais().equals(LibelleFrais.tous_les_taxes) == Boolean.TRUE) {
//            return;
//        }
//
//        //verification sur les elements exonerer
//        for (OrclassTaxePrime tp : taxePrimeDao.listeTaxePrimeByCategories(entreprise, categories)) {
//            if (tp.getBaseCalculTaxePrime().equals(BaseCalculTaxePrime.taxe_fond)) {
//                continue;
//            }
//            switch (tp.getIdTypeTaxe().getLibelleFrais()) {
//                case tva:
//                    montant = (extaxe.getIdTypeTaxe() != null && (extaxe.getIdTypeTaxe().getLibelleFrais().equals(tp.getIdTypeTaxe().getLibelleFrais()) == true)) ? BigDecimal.ZERO : (tp.getForfaitaire() == false) ? ((tp.getTaux().divide(prcent))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//                    System.out.println("taux pourcent:" + montant);
//                    montant = montant.multiply(totalPrime);
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                    quitance.setTaxe_tva(montant);
//                    System.out.println("Taxe_tva:" + montant);
//
//                    break;
//                case carte_rose:
//
//                    montant = (extaxe.getIdTypeTaxe() != null && extaxe.getIdTypeTaxe().getLibelleFrais().equals(LibelleFrais.carte_rose) == Boolean.TRUE) ? BigDecimal.ZERO : (tp.getForfaitaire() == false) ? (tp.getTaux().multiply(totalPrime).divide(BigDecimal.valueOf(100.0))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                    quitance.setTaxe_caterose(montant);
//                    System.out.println("Taxe_caterose:" + montant);
//
//                    break;
//                case asac_fga:
//                    montant = (extaxe.getIdTypeTaxe() != null && extaxe.getIdTypeTaxe().getLibelleFrais().equals(LibelleFrais.asac_fga) == Boolean.TRUE) ? BigDecimal.ZERO : (tp.getForfaitaire() == false) ? (tp.getTaux().multiply(totalPrime).divide(BigDecimal.valueOf(100.0))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                    quitance.setTaxe_asac_fga(montant);
//                    System.out.println("Taxe_asac_fga:" + montant);
//                    break;
//                case pool_tpv:
//                    montant = (extaxe.getIdTypeTaxe() != null && extaxe.getIdTypeTaxe().getLibelleFrais().equals(LibelleFrais.pool_tpv) == Boolean.TRUE) ? BigDecimal.ZERO : (tp.getForfaitaire() == false) ? (tp.getTaux().multiply(totalPrime).divide(BigDecimal.valueOf(100.0))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                    quitance.setTaxe_pool_tpv(montant);
//                    System.out.println("Taxe_pool_tpv:" + montant);
//                case tva_acc:
//                    montant = (extaxe.getIdTypeTaxe() != null && extaxe.getIdTypeTaxe().getLibelleFrais().equals(LibelleFrais.tva_acc) == Boolean.TRUE) ? BigDecimal.ZERO : ((tp.getForfaitaire() == false && police.getMontantaccessoir() != null)) ? (tp.getTaux().multiply(police.getMontantaccessoir().divide(BigDecimal.valueOf(100.0)))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                    quitance.setTaxe_tva_acc(montant);
//                    System.out.println("tva_acc:" + montant);
//                    break;
//                case tva_sur_asac:
//                    montant = (extaxe.getIdTypeTaxe() != null && extaxe.getIdTypeTaxe().getLibelleFrais().equals(LibelleFrais.tva_sur_asac) == Boolean.TRUE) ? BigDecimal.ZERO : (tp.getForfaitaire() == false) ? (tp.getTaux().multiply(totalPrime).divide(BigDecimal.valueOf(100.0))) : BigDecimal.valueOf(tp.getMontant_forfaitaire().doubleValue());
//
////                        if (tp.getBaseTaxePrime().equals(BaseTaxePrime.reduit)) {
////                            montant = montant.negate();
////                        }
//                    quitance.setTaxe_tva_sur_asac(montant);
//                    System.out.println("Taxe_tva_sur_asac:" + montant);
//                    break;
//            }
//        }
//        OrclassTimbre t = (extaxe.getIdRefTimbreGradue() != null && extaxe.getIdRefTimbreGradue().getLibelleFrais().equals(LibelleFrais.vignette) == Boolean.TRUE) ? new OrclassTimbre() : (risque.getPuissance_vehicule() == null || risque.getPuissance_vehicule().intValue() == 0) ? null : this.getBaremeForTimbreGratueeVignette(risque.getPuissance_vehicule(), LibelleFrais.vignette);
//        if (t == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR ", "LA PUISSANCE DU VEHICULE NON RENSEIGNE OU VALEUR NON EXISTANT"));
//            PrimeFaces.current().executeScript("PF('accordP').select(2);");
//            return;
//        }
//
//        quitance.setTimbr_Gradue__vignette(t.getValeur_tg_cp_fg() == null ? BigDecimal.ZERO : t.getValeur_tg_cp_fg());
//    }
//
//    public OrclassTimbre getBaremeForTimbreGratueeVignette(BigInteger puissance, LibelleFrais frais) {
//        List<OrclassTimbre> listeBaremeForTimbreGratueeVignette = new ArrayList<>();
//        OrclassTimbre t = null;
//        listeBaremeForTimbreGratueeVignette = timbreDao.listeTimbreByLibelleFrais(entreprise, frais);
//
//        int i = 0;
//        int taille = listeBaremeForTimbreGratueeVignette.size();
//        FonctionTable.localMemory(taille);
//        FonctionTable.chargeChampMin(listeBaremeForTimbreGratueeVignette);
//        FonctionTable.chargeChampMax(listeBaremeForTimbreGratueeVignette);
//        FonctionTable.triInsertion(FonctionTable.getChampMin());
//        FonctionTable.triInsertion(FonctionTable.getChampMax());
//        BigInteger[] champMin = FonctionTable.getChampMin();
//        BigInteger[] champMax = FonctionTable.getChampMax();
//        Boolean etat = true;
//        while (etat) {
//            if (i < taille && taille > 0) {
//                if (champMin[i].intValue() <= puissance.intValue() && champMax[i].intValue() >= puissance.intValue()) {
////                    OrclassRefTimbreGradue refTimbre = refTimbreGradueDao.findEntityHavingValue("libelleFrais", frais);
//                    t = timbreDao.returnRow(entreprise, frais, champMin[i], champMax[i]);
//                    etat = false;
//                    return t;
//                }
//
//                if (Objects.equals(champMax[i].intValue(), Double.valueOf(puissance.intValue())) || Objects.equals(champMin[i].intValue(), puissance.intValue())) {
////                    OrclassRefTimbreGradue refTimbre = refTimbreGradueDao.findEntityHavingValue("libelleFrais", frais);
//                    t = timbreDao.returnRow(entreprise, frais, puissance, puissance);
//                    etat = false;
//                    return t;
//                }
//
//                i++;
//                if (i == taille) {
//                    etat = Boolean.valueOf(false);
//                }
//
//                continue;
//            }
//            etat = Boolean.valueOf(false);
//        }
//
//        return null;
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
//    public void chargeListeGarantie() {
//        List<OrclassPoliceGarantie> listePoliceGaranti = new ArrayList<>();
//        List<OrclassPoliceGarantie> listePoliceGarantiSave = new ArrayList<>();
//        List<OrclassGarantie> listeGaranti = new ArrayList<>();
//        OrclasseRefGroupe group = null;
//        OrclassPoliceGarantie poGaran = null;
//        List<OrclassRefGaranties> listeRefGaranties = new ArrayList<>();
//
//        Set<OrclassGarantie> set = new HashSet<OrclassGarantie>();
//        int index = 0;
//        if (police == null || police.getId() == null) {
//            if (refGroupeSelectForGarantie != null && refGroupeSelectForGarantie.getId() != null) {
//                this.totalPrime = BigDecimal.ZERO;
//                this.totalProrata = BigDecimal.ZERO;
//                if (listePoliceGarantie != null && !listePoliceGarantie.isEmpty()) {
////                    listePoliceGarantie.remove(0);
//                    listePoliceGarantiSave.addAll(listePoliceGarantie);
////                    listePoliceGaranti.addAll(listePoliceGarantie);
//                    listePoliceGarantie.clear();
//                    for (OrclassPoliceGarantie pg : listePoliceGarantiSave) {
//                        if (pg.getIdGroup() == null) {
////                            listePoliceGarantie.add(pg);
//                            try {
//                                System.err.println("index " + listePoliceGarantiSave.indexOf(pg));
//                                System.err.println("garanti " + pg.getIdGarantie().toString());
//                            } catch (Exception e) {
//                                System.err.println("index " + listePoliceGarantiSave.indexOf(pg));
//                                System.err.println("garanti " + pg.getIdGarantie());
//                            }
//
//                            continue;
//
//                        }
//                        if (pg.getIdGroup().getId().equals(refGroupeSelectForGarantie.getId()) == Boolean.TRUE) {
//                            pg.setAfficher(Boolean.TRUE);
//                            if (pg.getPrime() != null && pg.getPrime().intValue() != 0) {
//                                this.totalPrime = this.totalPrime.add(pg.getPrime());
//                            }
//                            if (pg.getProrata() != null && pg.getProrata().intValue() != 0) {
//                                this.totalProrata = this.totalProrata.add(pg.getProrata());
//                            }
//
//                            listePoliceGarantie.add(pg);
//                            if (listeGaranti.contains(pg.getIdGarantie()) == Boolean.FALSE) {
//                                listeGaranti.add(pg.getIdGarantie());
//                            }
//
//                        } else {
//                            pg.setAfficher(Boolean.FALSE);
//                            listePoliceGaranti.add(pg);
//                            if (listeGaranti.contains(pg.getIdGarantie()) == Boolean.FALSE) {
//                                listeGaranti.add(pg.getIdGarantie());
//                            }
////                            listeGaranti.add(pg.getIdGarantie());
////                            if (listeRubriqueGarantieControleChangeGroup.contains(pg.getIdGarantie()) == Boolean.TRUE && listeGaranti.contains(pg.getIdGarantie()) == Boolean.FALSE && pg.getIdGarantie().getNatureGarantie().equals(NatureGarantie.obligatoire)) {
////                                poGaran = new OrclassPoliceGarantie();
////                                poGaran.setIdGroup(refGroupeSelectForGarantie);
////                                poGaran.setIdGarantie(pg.getIdGarantie());
////                                poGaran.setAfficher(Boolean.TRUE);
////                                for (OrclassPoliceGarantie pog : listePoliceGarantie) {
////                                    if (pog.getIdGarantie().equals(pg.getIdGarantie())) {
////                                        if (Objects.equals(pog.getAfficher(), Boolean.TRUE)) {
////                                            break;
////                                        } else {
////                                            listePoliceGarantie.add(poGaran);
////                                            break;
////                                        }
////
////                                    }
////                                }
////
////                            }
////                            for (OrclassGarantie rg : listeRubriqueGarantieControleChangeGroup) {
////                                if (pg.getIdGarantie().equals(rg) == Boolean.TRUE) {
////                                   
////                                    if (listePoliceGarantie.contains(poGaran) == Boolean.FALSE) {
////                                       
////                                    }
////                                    break;
////                                }
////
////                            }
//
//                        }
//
//                    }
//
//                    if (listePoliceGarantie.isEmpty()) {
//                        // on charge tous les garanties obligatoires dans la liste
//                        for (OrclassGarantie g : listeGaranti) {
//                            if (g.getNatureGarantie().equals(NatureGarantie.obligatoire)) {
//                                policeGarantie = new OrclassPoliceGarantie();
//                                policeGarantie.setIdGarantie(g);
//                                policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                                policeGarantie.setAfficher(Boolean.TRUE);
//                                listePoliceGarantie.add(policeGarantie);
//                            }
//                        }
//                        if (!listePoliceGaranti.isEmpty()) {
//                            listePoliceGarantie.addAll(listePoliceGaranti);
//                        }
//                    } else {
//                        if (!listePoliceGaranti.isEmpty()) {
//                            listePoliceGarantie.addAll(listePoliceGaranti);
//                        }
//                    }
//
//                }
////                else {
////                    this.listePoliceGarantie.clear();
////                }
//
//                if (!listePoliceGarantie.isEmpty()) {
//                    policeGarantie = new OrclassPoliceGarantie();
//
//                    listePoliceGarantie.add(policeGarantie);
//                    this.reverseListeGarantie();
//                } else {
//                    policeGarantie = new OrclassPoliceGarantie();
//
//                    listePoliceGarantie.add(policeGarantie);
//                }
//            }
//        } else if (police != null && police.getId() != null && refGroupeSelectForGarantie != null && refGroupeSelectForGarantie.getId() != null) {
//            listePoliceGarantie = policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, police, refGroupeSelectForGarantie);
//            listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieForGroup(police.getIdTypeTarif(), police.getIdCategories(), entreprise, refGroupeSelectForGarantie);
//            set.addAll(listeRubriqueGarantie);
//            listeRubriqueGarantie.clear();
//            for (OrclassGarantie sg : set) {
//                if (listeRubriqueGarantie.contains(sg) == Boolean.FALSE) {
//                    if (listeRefGaranties.contains(sg.getIdRefGaranties()) == Boolean.FALSE) {
//                        listeRefGaranties.add(sg.getIdRefGaranties());
//                        listeRubriqueGarantie.add(sg);
//                    }
//
//                }
//            }
////            listeRubriqueGarantie = new ArrayList<>(set);
//            totalPrime = BigDecimal.ZERO;
//            totalProrata = BigDecimal.ZERO;
//
//            for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//                if (pg.getPrime() == null || pg.getPrime().intValue() == 0) {
//                    continue;
//                }
//                if (pg.getProrata() == null || pg.getProrata().intValue() == 0) {
//                    continue;
//                }
//                if (pg.getPrime() != null && pg.getPrime().intValue() != 0) {
//                    totalPrime = totalPrime.add(pg.getPrime());
//                }
//                if (pg.getProrata() != null && pg.getProrata().intValue() != 0) {
//                    totalProrata = totalProrata.add(pg.getProrata());
//                }
//            }
//            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                policeGarantie = new OrclassPoliceGarantie();
//                policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                listePoliceGarantie.add(policeGarantie);
//                this.reverseListeGarantie();
//            }
//        }
//
//        this.updateDataTablePoliceGaranties();
//        this.updateTableRubriqueGarantie();
//
//    }
//
//    public void updateTableRubriqueGarantie() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form2:idrbTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('rgTable').clearFilters();");
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
//    public void forceModeCalulPrimeManuelle() {
//        List<OrclassPoliceGarantie> liste = new ArrayList<>(listePoliceGarantie);
//        listePoliceGarantie.clear();
////        this.setModecalculForcerManuel(true);
//
//        for (OrclassPoliceGarantie pg : liste) {
//            if (pg.getIdGarantie().getId() == null) {
//                listePoliceGarantie.add(pg);
//                continue;
//            }
//
//            if (pg.getIdGarantie().getModeCalcul().equals(ModeCalcul.Automatique) || Objects.equals(pg.getIdGarantie().getGlobalCompagnie(), Boolean.TRUE)) {
//                pg.setEditer(this.modecalculForcerManuel);
//                listePoliceGarantie.add(pg);
//            } else {
//                listePoliceGarantie.add(pg);
//            }
//        }
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//
//    public void reverseListeGarantie() {
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
//    public OrclassPoliceGarantie calculPrimeAutomatiqueGarantie(OrclassPoliceGarantie p) {
//        /*
//        ici sera appliquzr l agorithme du calcul automatique  des primes ou proarata
//         */
//        // recuperation de la duree du contrat
//        BigInteger dureeContrat = BigInteger.ZERO;
//        BigDecimal primeAnnuel = BigDecimal.ZERO;
//        BigDecimal primePeriode = BigDecimal.ZERO;
//        BigDecimal primeNette = BigDecimal.ZERO;
//        BigDecimal etape1, etape11, etape111 = BigDecimal.ZERO;//cete etape concerne la valeur additive + coefficient divise par le raport de division
//        BigDecimal constant1, constant2 = BigDecimal.ZERO;
//        BigDecimal valeurCarct1, valeurCaract2 = BigDecimal.ZERO;
//        BigDecimal valeur1 = BigDecimal.ZERO, valeur11 = BigDecimal.ZERO, valeur111 = BigDecimal.ZERO, valeur2 = BigDecimal.ZERO, valeur22 = BigDecimal.ZERO, valeur21 = BigDecimal.ZERO;;
//        OperationsTarif operationsTarif1;
//        OperationsTarif operationsTarif2;
//        OrclassTarif t1_rubrique, t11_rubrique, t2_rubrique, t22_rubrique, t222_rubrique = null;
//        OrclassCaracteristiques caracteristique1, caracteristique2;
//        OrclassRubrique rubrique1, rubrique11, rubrique2, rubrique22 = null;
//        BigDecimal valeurCalculer = BigDecimal.ZERO;
//        BigDecimal etape2, etape22, etape222 = BigDecimal.ZERO;//cete etape concerne la valeur additive + coefficient divise par le raport de division
//        OrclassRubriqueGarantie rubriqueGarantieByrubrique1, rubriqueGarantieByrubrique2 = null;
//        OrclassTarif tarifByrubrique1 = null, tarifByrubrique2 = null;
//        BigDecimal etapPrincipal = BigDecimal.ZERO;
////        Date curentdate=IdleDate.toString(new Date(), "dd/MM/yyyy");
//        if (police != null && police.getValeurDuree() != null && police.getValeurDuree().intValue() != 0) {
//
//            dureeContrat = police.getValeurDuree();
//            //recupere la tarification de la garantie
//            if (Objects.equals(p.getIdGarantie().getGlobalCompagnie(), Boolean.TRUE)) {
//                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantiePrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, p.getIdGarantie());
//                if (rubriqueGarantie == null) {
//                    p.setPrime(BigDecimal.ZERO);
//                    p.setPrimeAnnuelle(BigDecimal.ZERO);
//                    p.setProrata(BigDecimal.ZERO);
//                    return p;
//                }
//                tarif = tarifDao.lastTarif(rubriqueGarantie);
//                if (tarif == null) {
//                    p.setPrime(BigDecimal.ZERO);
//                    p.setPrimeAnnuelle(BigDecimal.ZERO);
//                    p.setProrata(BigDecimal.ZERO);
//                    return p;
//                }
//            } else {
//// nous verifions si la garantie est applicable au tarif conditionnel pour cela il doit etre automatique et Forfaitaire est a true
//                if (Objects.equals(p.getIdGarantie().getForfaire(), Boolean.TRUE)) {
//                    //pour ce fait nous devons retourne le tarif en des conditions etablir par les categories du vehicule 
//                    tarif = tarifWithConditionByCategorieVehicule();
//                    if (tarif != null) {
//                        rubriqueGarantie = tarif.getIdRubriqueGarantie();
//                    } else {
//                        if (tarif == null) {
//                            p.setPrime(BigDecimal.ZERO);
//                            p.setPrimeAnnuelle(BigDecimal.ZERO);
//                            p.setProrata(BigDecimal.ZERO);
//                            return p;
//                        }
//                    }
//
//                } else {
//                    rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantiePrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, p.getIdGarantie(), entreprise);
//                    if (rubriqueGarantie == null) {
//                        p.setPrime(BigDecimal.ZERO);
//                        p.setPrimeAnnuelle(BigDecimal.ZERO);
//                        p.setProrata(BigDecimal.ZERO);
//                        return p;
//                    }
//                    tarif = tarifDao.lastTarif(rubriqueGarantie, entreprise);
//                    if (tarif == null) {
//                        p.setPrime(BigDecimal.ZERO);
//                        p.setPrimeAnnuelle(BigDecimal.ZERO);
//                        p.setProrata(BigDecimal.ZERO);
//                        return p;
//                    }
//                }
//
//            }
//            /*
//            on va prendre en consideration deux cas consernant les rubrique 1 et 2
//            le 1 cas les rubriques sont null
//            le 2e cas une rubrique au moins existe
//            il est important de noter que nous c est la valeur de la rubrique prime nette qu on recherche
//             */
//
//            etapPrincipal = (tarif.getValeur_Additif().add(tarif.getCoefficient())).divide(BigDecimal.valueOf(tarif.getRapport_Division().intValue()));
//            //1er cas le tarif existe et les rubriques sont null
//            if ((tarif.getIdRubrique1() == null || "".equals(tarif.getIdRubrique1().trim())) && (tarif.getIdRubrique2() == null || "".equals(tarif.getIdRubrique2().trim()))) {
//                valeur1 = this.calculValeurForFirstRubriqueIsNotExiste(tarif);
//                valeur2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif);
//                primeAnnuel = this.calculPrimeAnnuel(tarif, etapPrincipal, valeur1, valeur2);
//            } else {
//                rubrique1 = (tarif.getIdRubrique1() == null || "".equals(tarif.getIdRubrique1().trim())) ? null : rubriqueDao.findEntityHavingValue("code", tarif.getIdRubrique1());
//                rubrique2 = (tarif.getIdRubrique2() == null || "".equals(tarif.getIdRubrique2().trim())) ? null : rubriqueDao.findEntityHavingValue("code", tarif.getIdRubrique2());
//                if (Objects.equals(p.getIdGarantie().getGlobalCompagnie(), Boolean.TRUE)) {
//                    rubriqueGarantieByrubrique1 = rubrique1 == null ? null : rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, p.getIdGarantie(), rubrique1);
//                    rubriqueGarantieByrubrique2 = rubrique2 == null ? null : rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, p.getIdGarantie(), rubrique2);
//                    tarifByrubrique1 = rubriqueGarantieByrubrique1 == null ? null : tarifDao.lastTarif(rubriqueGarantieByrubrique1);
//                    tarifByrubrique2 = rubriqueGarantieByrubrique2 == null ? null : tarifDao.lastTarif(rubriqueGarantieByrubrique2);
//
//                } else {
//                    rubriqueGarantieByrubrique1 = rubrique1 == null ? null : rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, p.getIdGarantie(), rubrique1, entreprise);
//                    rubriqueGarantieByrubrique2 = rubrique2 == null ? null : rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, p.getIdGarantie(), rubrique2, entreprise);
//                    tarifByrubrique1 = rubriqueGarantieByrubrique1 == null ? null : tarifDao.lastTarif(rubriqueGarantieByrubrique1, entreprise);
//                    tarifByrubrique2 = rubriqueGarantieByrubrique2 == null ? null : tarifDao.lastTarif(rubriqueGarantieByrubrique2, entreprise);
//
//                }
//                if ((tarifByrubrique2 != null && tarifByrubrique2.getId() != null && (tarifByrubrique1 != null && tarifByrubrique1.getId() != null))) {
//
//                    //nous allons etablir un traitement du bas vers le haut c est à dire nous cmmencon avec tarifByrubrique2
//                    //ce permettra d etablir la valeur de la variable 2
//                    if ((tarifByrubrique2 != null && tarifByrubrique2.getId() != null)) {
//                        etape2 = (tarifByrubrique2.getValeur_Additif().add(tarifByrubrique2.getCoefficient())).divide(BigDecimal.valueOf(tarifByrubrique2.getRapport_Division().intValue()));
//                        /*
//                          il faut trouver les valeurs des variables  11 et 22 et le multiplier avec etap1 pour respecter le standart de la formule
//                         */
//                        valeur21 = (tarifByrubrique2.getIdRubrique1() == null || "".equals(tarifByrubrique2.getIdRubrique1().trim())) ? this.calculValeurForFirstRubriqueIsNotExiste(tarifByrubrique2) : BigDecimal.ZERO;// a developper dans le 
//                        valeur22 = (tarifByrubrique2.getIdRubrique2() == null || "".equals(tarifByrubrique2.getIdRubrique2().trim())) ? this.calculValeurForSecondRubriqueIsNotExiste(tarifByrubrique2) : BigDecimal.ZERO;
//
//                        valeur2 = this.calculPrimeAnnuel(tarifByrubrique2, etape2, valeur21, valeur22);
//
//                    }
//                    if ((tarifByrubrique1 != null && tarifByrubrique1.getId() != null)) {
//                        etape1 = (tarifByrubrique1.getValeur_Additif().add(tarifByrubrique1.getCoefficient())).divide(BigDecimal.valueOf(tarifByrubrique1.getRapport_Division().intValue()));
//                        /*
//                          il faut trouver les valeurs des variables  11 et 22 et le multiplier avec etap1 pour respecter le standart de la formule
//                         */
//                        valeur11 = (tarifByrubrique1.getIdRubrique1() == null || "".equals(tarifByrubrique1.getIdRubrique1().trim())) ? this.calculValeurForFirstRubriqueIsNotExiste(tarifByrubrique1) : BigDecimal.ZERO;// a developper dans le 
//                        valeur111 = (tarifByrubrique1.getIdRubrique2() == null || "".equals(tarifByrubrique1.getIdRubrique2().trim())) ? this.calculValeurForSecondRubriqueIsNotExiste(tarifByrubrique1) : BigDecimal.ZERO;
//
//                        valeur1 = this.calculPrimeAnnuel(tarifByrubrique2, etape1, valeur11, valeur111);
//
//                    }
//                    primeAnnuel = this.calculPrimeAnnuel(tarif, etapPrincipal, valeur1, valeur2);
//
//                } else {
//                    if ((tarifByrubrique1 != null && tarifByrubrique1.getIdRubrique1() != null && !"".equals(tarifByrubrique1.getIdRubrique1())) && (tarifByrubrique2 == null || tarifByrubrique2.getIdRubrique2() == null || "".equals(tarifByrubrique2.getIdRubrique2().trim()))) {
//                        etape1 = (tarifByrubrique1.getValeur_Additif().add(tarifByrubrique1.getCoefficient())).divide(BigDecimal.valueOf(tarifByrubrique1.getRapport_Division().intValue()));
//                        /*
//                          il faut trouver les valeurs des variables  11 et 22 et le multiplier avec etap1 pour respecter le standart de la formule
//                         */
//                        valeur11 = (tarifByrubrique1.getIdRubrique1() == null || "".equals(tarifByrubrique1.getIdRubrique1().trim())) ? this.calculValeurForFirstRubriqueIsNotExiste(tarifByrubrique1) : BigDecimal.ZERO;// a developper dans le 
//                        valeur111 = (tarifByrubrique1.getIdRubrique2() == null || "".equals(tarifByrubrique1.getIdRubrique2().trim())) ? this.calculValeurForSecondRubriqueIsNotExiste(tarifByrubrique1) : BigDecimal.ZERO;
//
//                        valeur1 = this.calculPrimeAnnuel(tarifByrubrique1, etape1, valeur11, valeur111);
//
//                        valeur2 = this.calculValeurForSecondRubriqueIsNotExiste(tarifByrubrique2);
//
//                        primeAnnuel = this.calculPrimeAnnuel(tarif, etapPrincipal, valeur1, valeur2);
////ICI
//                    } else if ((tarifByrubrique1 == null || tarifByrubrique1.getIdRubrique1() == null || "".equals(tarifByrubrique1.getIdRubrique1())) && (tarifByrubrique2 != null && tarifByrubrique2.getIdRubrique2() != null && !"".equals(tarifByrubrique2.getIdRubrique2().trim()))) {
//
//                        etape2 = (tarifByrubrique2.getValeur_Additif().add(tarifByrubrique2.getCoefficient())).divide(BigDecimal.valueOf(tarifByrubrique2.getRapport_Division().intValue()));
//                        /*
//                          il faut trouver les valeurs des variables  11 et 22 et le multiplier avec etap1 pour respecter le standart de la formule
//                         */
//                        valeur21 = (tarifByrubrique2.getIdRubrique1() == null || "".equals(tarifByrubrique2.getIdRubrique1().trim())) ? this.calculValeurForFirstRubriqueIsNotExiste(tarifByrubrique2) : BigDecimal.ZERO;// a developper dans le 
//                        valeur22 = (tarifByrubrique2.getIdRubrique2() == null || "".equals(tarifByrubrique2.getIdRubrique2().trim())) ? this.calculValeurForSecondRubriqueIsNotExiste(tarifByrubrique2) : BigDecimal.ZERO;
//
//                        valeur2 = this.calculPrimeAnnuel(tarifByrubrique2, etape2, valeur21, valeur22);
//
//                        valeur1 = this.calculValeurForFirstRubriqueIsNotExiste(tarifByrubrique1);
//
//                        primeAnnuel = this.calculPrimeAnnuel(tarif, etapPrincipal, valeur1, valeur2);
//                    } else {// dans ce cas  soit l un des tarif est null et l autre existe
//                        if (tarifByrubrique2 == null) {
//                            etape1 = (tarifByrubrique1 != null && tarifByrubrique2 == null) ? (tarifByrubrique1.getValeur_Additif().add(tarifByrubrique1.getCoefficient())).divide(BigDecimal.valueOf(tarifByrubrique1.getRapport_Division().intValue())) : BigDecimal.ONE;
//
//                            valeur11 = (tarifByrubrique1 != null && (tarifByrubrique1.getIdRubrique1() == null && tarifByrubrique1.getIdRubrique2() == null)) ? this.calculValeurForFirstRubriqueIsNotExiste(tarifByrubrique1) : BigDecimal.ZERO;
//                            valeur22 = (tarifByrubrique1 != null && (tarifByrubrique1.getIdRubrique1() == null && tarifByrubrique1.getIdRubrique2() == null) && tarifByrubrique2 == null) ? this.calculValeurForSecondRubriqueIsNotExiste(tarifByrubrique1) : BigDecimal.ZERO;
//
//                            valeur1 = this.calculPrimeAnnuel(tarifByrubrique1, etape1, valeur11, valeur22);
//                            valeur2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif);
//                            primeAnnuel = this.calculPrimeAnnuel(tarif, etapPrincipal, valeur1, valeur2);
//                        } else if (tarifByrubrique1 == null) {
//                            etape2 = (tarifByrubrique2 != null && tarifByrubrique1 == null) ? (tarifByrubrique2.getValeur_Additif().add(tarifByrubrique2.getCoefficient())).divide(BigDecimal.valueOf(tarifByrubrique2.getRapport_Division().intValue())) : BigDecimal.ONE;
//                            valeur21 = (tarifByrubrique2 != null && (tarifByrubrique2.getIdRubrique1() == null && tarifByrubrique2.getIdRubrique2() == null)) ? this.calculValeurForFirstRubriqueIsNotExiste(tarifByrubrique2) : BigDecimal.ZERO;
//                            valeur22 = (tarifByrubrique2 != null && (tarifByrubrique2.getIdRubrique1() == null && tarifByrubrique2.getIdRubrique2() == null) && tarifByrubrique2 == null) ? this.calculValeurForSecondRubriqueIsNotExiste(tarifByrubrique2) : BigDecimal.ZERO;
//                            valeur2 = this.calculPrimeAnnuel(tarifByrubrique2, etape2, valeur21, valeur22);
//                            valeur1 = this.calculValeurForFirstRubriqueIsNotExiste(tarif);
//                            primeAnnuel = this.calculPrimeAnnuel(tarif, etapPrincipal, valeur1, valeur2);
//                        }
//
//                    }
//                }
//
//            }
//
//            /**
//             * calculons la prime periode ,pour cela on doit savoir l unite de
//             * la duree jour ,mois ou annee
//             */
//            switch (duree.getUniteDuree()) {
//                case jours:
//                    int nbre_jour = 0;//ceci est le nobre de jour pour le contract saisir entre la date d effet et la dayte d echeance     
//                    nbre_jour = IdleDate.nombreJourFor2Dates(police.getDate_effet(), police.getDate_echeance());
//                    BigDecimal primePeriodeByNbreJour = new BigDecimal("" + nbre_jour).divide(new BigDecimal(dureeContrat), 2, RoundingMode.HALF_UP);
////                    primePeriodeByNbreJour = (primePeriodeByNbreJour * primeAnnuel.intValue());
//                    System.out.println("nombfre de jour:" + nbre_jour);
//                    //arrondissementValeur exact
//                    primePeriode = primePeriodeByNbreJour.multiply(primeAnnuel);
//                    break;
//                case mois:
//                    int nbre_mois = 0;//ceci concerne le nombre de mois pour le contract a effectuer entre la date d effet et la date d echeance
//                    nbre_mois = (IdleDate.nombreJourFor2Dates(police.getDate_effet(), police.getDate_echeance()) / 30);
//                    BigDecimal primePeriodeByNbreMois = (new BigDecimal("" + nbre_mois).divide(new BigDecimal(dureeContrat), 2, RoundingMode.HALF_UP));
//                    //arrondissementValeur exact
//                    System.out.println("nombfre de mois:" + nbre_mois);
//                    primePeriode = primePeriodeByNbreMois.multiply(primeAnnuel);
//                    break;
//                case semaines:
//                    int nbre_semaine = 0;//ceci concerne le nombre de mois pour le contract a effectuer entre la date d effet et la date d echeance
//                    nbre_semaine = (IdleDate.nombreJourFor2Dates(police.getDate_effet(), police.getDate_echeance()) / 7);
//                    BigDecimal primePeriodeByNbreSemaines = new BigDecimal("" + nbre_semaine).divide(new BigDecimal(dureeContrat), 2, RoundingMode.HALF_UP);
//                    //arrondissementValeur exact
//                    System.out.println("nombfre de semaine:" + nbre_semaine);
//                    primePeriode = primePeriodeByNbreSemaines.multiply(primeAnnuel);
//                    break;
//
//            }
//
//            p.setPrimeAnnuelle(primeAnnuel);
//            p.setPrime_sans_reduction_ou_operation(primePeriode);
//            p.setPrime(chexckRedMalForGarantie(p, primePeriode));
//            p.setProrata(p.getPrime());
//
//        }
//        return p;
//
//    }
//
//    public BigDecimal chexckRedMalForGarantie(OrclassPoliceGarantie p, BigDecimal prime) {
//        BigDecimal montant = BigDecimal.ZERO;
//        BigDecimal reduction_garantie_direct = BigDecimal.ZERO;
//        BigDecimal taux_surRedMaj_DonneeSpecial = BigDecimal.ZERO;
//        BigDecimal taux_reduction_garantie = BigDecimal.ZERO;
//        BigDecimal total_taux = BigDecimal.ZERO;
//        BigDecimal montant_marge_red = BigDecimal.ZERO;
//        BigDecimal montant_marge_majoration = BigDecimal.ZERO;
//
//        if (police != null && police.getIdReduction() != null && police.getIdReduction().getId() != null && police.getIdReduction().getIdGarantie().equals(p.getIdGarantie())) {
//            taux_reduction_garantie = police.getIdReduction().getTaux();
//
//            reduction_garantie_direct = (prime.multiply(taux_reduction_garantie)).divide(BigDecimal.valueOf(100.0));
//            totalReduction = totalReduction.add(reduction_garantie_direct);
////            quitance.setReduction(reduction_garantie_direct);hhhhhyh
//
//        } else {
//            reduction_garantie_direct = BigDecimal.ZERO;
//        }
//
//        if (p != null && p.getTaux_Majoration_reduction() != null && p.getTaux_Majoration_reduction().intValue() != 0) {
//            taux_surRedMaj_DonneeSpecial = p.getTaux_Majoration_reduction();
//        }
//        if (taux_surRedMaj_DonneeSpecial.signum() == -1) {
//            //dans ce cas la prime est reduit du montant du taux
//            montant_marge_red = montant_marge_red.add((taux_surRedMaj_DonneeSpecial.multiply(prime)).divide(BigDecimal.valueOf(100.0)));
//        } else {
//            montant_marge_majoration = montant_marge_majoration.add((taux_surRedMaj_DonneeSpecial.multiply(prime)).divide(BigDecimal.valueOf(100.0)));
//        }
//        montant_marge_red = montant_marge_red.abs().add(reduction_garantie_direct);
//
//        montant = (prime.subtract(montant_marge_red)).add(montant_marge_majoration);
//        total_taux = taux_reduction_garantie.add(taux_surRedMaj_DonneeSpecial);
////        montant = (total_taux == BigDecimal.ZERO) ? prime : (prime.subtract((prime.multiply(total_taux)).divide(BigDecimal.valueOf(100.0))));
//        //
//        return montant;
//    }
//
//    public BigDecimal calculPrimeAnnuel(OrclassTarif t, BigDecimal etape1, BigDecimal valeurVariable1, BigDecimal valeurVariable2) {
//        BigDecimal primeAnnuel = BigDecimal.ZERO;
//        BigDecimal valeur = BigDecimal.ZERO;
//        if (t != null && t.getOperations() != null) {
//            switch (t.getOperations()) {
//                case division:
//                    try {
//                    valeur = valeurVariable1.divide(valeurVariable2);
//                } catch (java.lang.ArithmeticException jla) {
//                    valeur = new BigDecimal("" + ((float) (valeurVariable1.doubleValue() * valeurVariable2.doubleValue())));
//                }
//
//                break;
//                case multiplication:
//                    valeur = valeurVariable1.multiply(valeurVariable2);
//                    break;
//                case moins:
//                    valeur = valeurVariable1.subtract(valeurVariable2);
//                    break;
//                case plus:
//                    valeur = valeurVariable1.add(valeurVariable2);
//                    break;
//                case max:
//                    valeur = valeurVariable1.max(valeurVariable2);
//                    break;
//                case min:
//                    valeur = valeurVariable1.min(valeurVariable2);
//                    break;
//            }
//        } else {
////            valeur = valeurVariable2;
//            valeur = valeurVariable1;
//        }
//        primeAnnuel = etape1.multiply(valeur);
//        return primeAnnuel;
//    }
//
//
//    /*
//    calculvaleur rubrique for tarif is null
//     */
//    public BigDecimal calculValeurForFirstRubriqueIsNotExiste(OrclassTarif tarif) {
//        BigDecimal valeur = BigDecimal.ZERO;
//        OrclassCaracteristiques caracteristique = null;
//
//        /*
//        dans ce cas ou la rubrique1  est nulle  force la valeur est une constante ou une caracteristique
//         */
//        if (tarif == null || tarif.getOperationTarif1() == null) {
//            return BigDecimal.ONE;
//        }
//        switch (tarif.getOperationTarif1()) {
//            case contante:
//                valeur = tarif.getConstant1();
//                break;
//            case caracteristique:
//                caracteristique = rubriqueCaracteristiqueDao.caracteristiqueByByRubrique(entreprise, categories, tarif.getIdCaracteristiques1(), tarif.getIdRubriqueGarantie());
////          
////                caracteristique = caracteristiquesDao.findEntityHavingValue("code", tarif.getIdCaracteristiques1());
//                if (caracteristique != null && caracteristique.getId() != null) {
//                    if (caracteristique.getOrclass_Objet() != null && caracteristique.getOrclass_Objet().getId() != null) {
//                        valeur = this.valeuCaracteristiqueObjet(caracteristique);
//                    } else {
//                        for (OrclassPoliceCaracteristique pc : listePoliceCaracteristique) {
//                            if (pc.getIdCaracteristiques().equals(caracteristique) == Boolean.TRUE) {
//                                if (pc.getIdCaracteristiques().getOrclass_Objet() != null) {
//                                    // ici la valeur renseigner par un champ du formulaire
//                                } else {
//                                    if (pc.getValeur() == null) {
//                                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA NATURE DE L OPERATION...VALEUR ATTENDU NULL ..." + pc.getIdCaracteristiques().getLibelle().toUpperCase()));
//                                        return BigDecimal.ZERO;
//                                    }
//                                    valeur = new BigDecimal(pc.getValeur());
//                                }
//
//                            }
//                        }
//                    }
//                }
//
//                break;
//        }
//
//        return valeur;
//    }
//
//    /*
//    recuperation d un valeur valeur de type objet classe c est à dire une valeur  dans le  formulaire
//     */
//    public BigDecimal valeuCaracteristiqueObjet(OrclassCaracteristiques ca) {
//        BigDecimal valeur = BigDecimal.ZERO;
//        if (ca != null && ca.getId() != null && ca.getOrclass_Objet() != null && ca.getOrclass_Objet().getId() != null) {
//            switch (ca.getOrclass_Objet().getClasseObjet()) {
//                case vehicule:
//                    VehiculeObjet vehiculeObjet = VehiculeObjet.valueOf(ca.getLibelle());
//                    switch (vehiculeObjet) {
//                        case puissance:
//                            if (risque != null && risque.getPuissance_vehicule() != null && risque.getPuissance_vehicule().intValue() != 0) {
//                                valeur = BigDecimal.valueOf(risque.getPuissance_vehicule().doubleValue());
//                            }
//                            break;
//                        case nombre_places:
//                            if (risque != null && risque.getNombre_place() != null && risque.getNombre_place().intValue() != 0) {
//                                valeur = BigDecimal.valueOf(risque.getNombre_place().doubleValue());
//                            }
//                            break;
//                        case tonnage_poids:
//                            if (risque != null && risque.getPoids_vehicule() != null && risque.getPoids_vehicule().intValue() != 0) {
//                                valeur = BigDecimal.valueOf(risque.getPoids_vehicule().doubleValue());
//                            }
//                            break;
//                        case valeur_neuf:
//                            if (risque != null && risque.getValeur_catalogue() != null && risque.getValeur_catalogue().intValue() != 0) {
//                                valeur = BigDecimal.valueOf(risque.getValeur_catalogue().doubleValue());
//                            }
//                            break;
//                        case valeur_venal:
//                            if (risque != null && risque.getValeur_declaree() != null && risque.getValeur_declaree().intValue() != 0) {
//                                valeur = BigDecimal.valueOf(risque.getValeur_declaree().doubleValue());
//                            }
//                            break;
//
//                    }
//                    break;
//                case police:
//
//                    PoliceAutresInformation policeAutresInformation = PoliceAutresInformation.valueOf(ca.getLibelle());
//                    switch (policeAutresInformation) {
//                        case duree:
//
//                            if (police != null && police.getValeurDuree() != null && police.getValeurDuree().intValue() != 0) {
//                                valeur = BigDecimal.valueOf(police.getValeurDuree().doubleValue());
//                            }
//                            break;
//
//                    }
////                     policeSouscripteurZone  souscripteurZone=  policeSouscripteurZone.valueOf(ca.getLibelle());
////                      switch(souscripteurZone){
////                          case 
////                      }
//                    break;
//
//            }
//        }
//        return valeur;
//    }
//
//    /*
//    calcul la valeur pour rubrique 2 n existant pas 
//     */
//    public BigDecimal calculValeurForSecondRubriqueIsNotExiste(OrclassTarif tarif) {
//        BigDecimal valeur = BigDecimal.ZERO;
//        OrclassCaracteristiques caracteristique = null;
//
//        /*
//        dans ce cas ou la rubrique1  est nulle  force la valeur est une constante ou une caracteristique
//         */
//        if (tarif == null || tarif.getOperationTarif2() == null) {
//            return BigDecimal.ONE;
//        }
//        switch (tarif.getOperationTarif2()) {
//            case contante:
//                valeur = tarif.getConstant2();
//                break;
//            case caracteristique:
//                caracteristique = rubriqueCaracteristiqueDao.caracteristiqueByByRubrique(entreprise, categories, tarif.getIdCaracteristiques2(), tarif.getIdRubriqueGarantie());
////                caracteristique = rubriqueCaracteristiqueDao.findEntityHavingValue("code", tarif.getIdCaracteristiques2());oooo
//                if (caracteristique != null && caracteristique.getId() != null) {
//                    if (caracteristique.getOrclass_Objet() != null && caracteristique.getOrclass_Objet().getId() != null) {
//                        valeur = this.valeuCaracteristiqueObjet(caracteristique);
//                    } else {
//                        for (OrclassPoliceCaracteristique pc : listePoliceCaracteristique) {
//                            if (pc.getIdCaracteristiques().equals(caracteristique) == Boolean.TRUE) {
//                                if (pc.getIdCaracteristiques().getOrclass_Objet() != null) {
//                                    // ici la valeur renseigner par un champ du formulaire
//                                } else {
//                                    if (pc.getValeurNumerique() == null) {
//                                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA NATURE DE L OPERATION...VALEUR ATTENDU NULL ..." + pc.getIdCaracteristiques().getLibelle().toUpperCase()));
//                                        return BigDecimal.ZERO;
//                                    }
//                                    valeur = pc.getValeurNumerique();
//                                }
//
//                            }
//                        }
//                    }
//                }
//
//                break;
//        }
//
//        return valeur;
//    }
//
//    /*
//     calcul la valeur pour la rubrique1 existant
//     il est question de trouver la valeur par la formule de la rubrique en question en le multipliant par la valeur etape (valeur deja calculer en attente de la multiplication)
//     */
////    public BigDecimal calculValeurForFirstRubriqueIsExiste(OrclassRubrique rubrique, OrclassGarantie g) {
////        BigDecimal valeurRubrique1 = BigDecimal.ZERO;
////        BigDecimal valeurRubrique2 = BigDecimal.ZERO;
////        BigDecimal valeur = BigDecimal.ZERO;
////        BigDecimal valeurCalculer = BigDecimal.ZERO;
////        BigDecimal etape_rubrique = BigDecimal.ZERO;
////        OrclassTarif tarif_rubrique = null;
////        if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
////            rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique);
////
////            tarif_rubrique = tarifDao.lastTarif(rubriqueGarantie);
////        } else {
////            rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique, entreprise);
////
////            tarif_rubrique = tarifDao.lastTarif(rubriqueGarantie, entreprise);
////        }
////        //verifions la valeur de la premier rubrique;
////        /*
////      premier cas cette rubrique est null dans cette partie on appelle la fonction pour recuperer la valeur
////         */
////        if (tarif_rubrique == null) {
////            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
////            return BigDecimal.ZERO;
////        }
////        etape_rubrique = (tarif_rubrique.getValeur_Additif().add(tarif_rubrique.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique.getRapport_Division().intValue()));
////
////        if ((tarif_rubrique.getIdRubrique1() == null || "".equals(tarif_rubrique.getIdRubrique1().trim())) && (tarif_rubrique.getIdRubrique2() == null || "".equals(tarif_rubrique.getIdRubrique2().trim()))) {
////            valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique, tarif_rubrique);
////            valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique);
////            if (tarif_rubrique.getOperations() != null) {
////                switch (tarif_rubrique.getOperations()) {
////                    case plus:
////                        valeurCalculer = valeurRubrique1.add(valeurRubrique2);
////                        break;
////                    case division:
////                        valeurCalculer = valeurRubrique1.divide(valeurRubrique2);
////                        break;
////                    case multiplication:
////                        valeurCalculer = valeurRubrique1.multiply(valeurRubrique2);
////                        break;
////                    case max:
////                        valeurCalculer = valeurRubrique1.max(valeurRubrique2);
////                        break;
////                    case min:
////                        valeurCalculer = valeurRubrique1.min(valeurRubrique2);
////                        break;
////                    case moins:
////                        valeurCalculer = valeurRubrique1.subtract(valeurRubrique2);
////                        break;
////                }
////                valeur = valeurCalculer;//
////            } else {
////                valeur = valeurRubrique1;
////            }
////            valeur = etape_rubrique.multiply(valeur);//conclure la formule
////
////        } else if ((tarif_rubrique.getIdRubrique1() == null || "".equals(tarif_rubrique.getIdRubrique1().trim())) && (tarif_rubrique.getIdRubrique2() != null && !"".equals(tarif_rubrique.getIdRubrique2().trim()))) {
////            BigDecimal valeurRubrique12 = BigDecimal.ZERO;
////            valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique, tarif_rubrique);
////            //retrouvons d abord la rubriGarantie enfinde determiner le tarif
////            OrclassRubrique rubrique2 = rubriqueDao.findEntityHavingValue("code", tarif_rubrique.getIdRubrique2());
////            OrclassTarif tarif_rubrique2 = null;
////            BigDecimal etape_rubrique2 = BigDecimal.ZERO;
////            if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
////                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique2);
////
////                tarif_rubrique2 = tarifDao.lastTarif(rubriqueGarantie);
////            } else {
////                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique2, entreprise);
////
////                tarif_rubrique2 = tarifDao.lastTarif(rubriqueGarantie, entreprise);
////            }
////            if (tarif_rubrique2 == null) {
////                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
////                return BigDecimal.ZERO;
////            }
////            etape_rubrique2 = (tarif_rubrique2.getValeur_Additif().add(tarif_rubrique2.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique2.getRapport_Division().intValue()));
////            if ((tarif_rubrique2.getIdRubrique1() == null || "".equals(tarif_rubrique2.getIdRubrique1().trim())) && (tarif_rubrique2.getIdRubrique2() == null || "".equals(tarif_rubrique2.getIdRubrique2().trim()))) {
////                valeurRubrique12 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique2, tarif_rubrique2);
////                valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique2);
////                if (tarif_rubrique2.getOperations() != null) {
////                    switch (tarif_rubrique.getOperations()) {
////                        case plus:
////                            valeurCalculer = valeurRubrique12.add(valeurRubrique2);
////                            break;
////                        case division:
////                            valeurCalculer = valeurRubrique12.divide(valeurRubrique2);
////                            break;
////                        case multiplication:
////                            valeurCalculer = valeurRubrique12.multiply(valeurRubrique2);
////                            break;
////                        case max:
////                            valeurCalculer = valeurRubrique12.max(valeurRubrique2);
////                            break;
////                        case min:
////                            valeurCalculer = valeurRubrique12.min(valeurRubrique2);
////                            break;
////                        case moins:
////                            valeurCalculer = valeurRubrique12.subtract(valeurRubrique2);
////                            break;
////                    }
//////                    valeur = etape_rubrique2.multiply(valeurCalculer);//
////                } else {
////                    valeurCalculer = valeurRubrique12;
////                }
////                valeurCalculer = etape_rubrique2.multiply(etape_rubrique2);
////            }
////            if (tarif_rubrique.getOperations() != null) {
////                switch (tarif_rubrique.getOperations()) {
////                    case plus:
////                        valeur = valeurRubrique1.add(valeurCalculer);
////                        break;
////                    case division:
////                        valeur = valeurRubrique1.divide(valeurCalculer);
////
////                        break;
////                    case multiplication:
////                        valeur = valeurRubrique1.multiply(valeurCalculer);
////
////                        break;
////                    case max:
////                        valeur = valeurRubrique1.max(valeurCalculer);
////
////                        break;
////                    case min:
////                        valeur = valeurRubrique1.min(valeurCalculer);
////
////                        break;
////                    case moins:
////                        valeur = valeurRubrique1.subtract(valeurCalculer);
////
////                        break;
////                }
////            } else {
////                valeur = BigDecimal.ONE;
////            }
////            /*
////            application la  mutiplication pour complet la formule entre etape 1 et la valeur qui suir
////             */
////            valeur = etape_rubrique.multiply(valeur);
////        } else if ((tarif_rubrique.getIdRubrique1() != null && !"".equals(tarif_rubrique.getIdRubrique1().trim())) && (tarif_rubrique.getIdRubrique2() == null || "".equals(tarif_rubrique.getIdRubrique2().trim()))) {
////            valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique);
////            //retrouvons d abord la rubriGarantie enfinde determiner le tarif
////            OrclassRubrique rubrique1 = rubriqueDao.findEntityHavingValue("code", tarif_rubrique.getIdRubrique1());
////            OrclassTarif tarif_rubrique1 = null;
////            BigDecimal etape_rubrique1 = BigDecimal.ZERO;
////            if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
////                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique1);
////
////                tarif_rubrique1 = tarifDao.lastTarif(rubriqueGarantie);
////            } else {
////                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique1, entreprise);
////
////                tarif_rubrique1 = tarifDao.lastTarif(rubriqueGarantie, entreprise);
////            }
////            if (tarif_rubrique1 == null) {
////                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
////                return BigDecimal.ZERO;
////            }
////            etape_rubrique1 = (tarif_rubrique1.getValeur_Additif().add(tarif_rubrique1.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique1.getRapport_Division().intValue()));
////            if ((tarif_rubrique1.getIdRubrique1() == null || "".equals(tarif_rubrique1.getIdRubrique1().trim())) && (tarif_rubrique1.getIdRubrique2() == null || "".equals(tarif_rubrique1.getIdRubrique2().trim()))) {
////                valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique1, tarif_rubrique1);
////                valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique1);
////                if (tarif_rubrique1.getOperations() != null) {
////                    switch (tarif_rubrique1.getOperations()) {
////                        case plus:
////                            valeurCalculer = valeurRubrique1.add(valeurRubrique2);
////                            break;
////                        case division:
////                            valeurCalculer = valeurRubrique1.divide(valeurRubrique2);
////                            break;
////                        case multiplication:
////                            valeurCalculer = valeurRubrique1.multiply(valeurRubrique2);
////                            break;
////                        case max:
////                            valeurCalculer = valeurRubrique1.max(valeurRubrique2);
////                            break;
////                        case min:
////                            valeurCalculer = valeurRubrique1.min(valeurRubrique2);
////                            break;
////                        case moins:
////                            valeurCalculer = valeurRubrique1.subtract(valeurRubrique2);
////                            break;
////                    }
////                    valeur = etape_rubrique.multiply(valeurCalculer);//
////                } else {
////                    valeur = valeurRubrique1;
////                }
////
////            } else if ((tarif_rubrique.getIdRubrique1() != null && !"".equals(tarif_rubrique.getIdRubrique1().trim())) && (tarif_rubrique.getIdRubrique2() != null && !"".equals(tarif_rubrique.getIdRubrique2().trim()))) {
////
////                // tarif_rubrique.getIdRubrique1() != null
////                OrclassRubrique rubrique11 = rubriqueDao.findEntityHavingValue("code", tarif_rubrique.getIdRubrique1());
////                OrclassTarif tarif_rubrique11 = null;
////                BigDecimal etape_rubrique11 = BigDecimal.ZERO;
////                BigDecimal valeur1 = BigDecimal.ZERO, valeur2 = BigDecimal.ZERO;
////                if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
////                    rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique11);
////
////                    tarif_rubrique11 = tarifDao.lastTarif(rubriqueGarantie);
////                } else {
////                    rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique11, entreprise);
////
////                    tarif_rubrique11 = tarifDao.lastTarif(rubriqueGarantie, entreprise);
////                }
////                if (tarif_rubrique11 == null) {
////                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
////                    return BigDecimal.ZERO;
////                }
////                etape_rubrique11 = (tarif_rubrique11.getValeur_Additif().add(tarif_rubrique11.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique11.getRapport_Division().intValue()));
////                if ((tarif_rubrique11.getIdRubrique1() == null || "".equals(tarif_rubrique11.getIdRubrique1().trim())) && (tarif_rubrique11.getIdRubrique2() == null || "".equals(tarif_rubrique11.getIdRubrique2().trim()))) {
////                    valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique11, tarif_rubrique11);
////                    valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique11);
////                    if (tarif_rubrique11.getOperations() != null) {
////                        switch (tarif_rubrique11.getOperations()) {
////                            case plus:
////                                valeurCalculer = valeurRubrique1.add(valeurRubrique2);
////                                break;
////                            case division:
////                                valeurCalculer = valeurRubrique1.divide(valeurRubrique2);
////                                break;
////                            case multiplication:
////                                valeurCalculer = valeurRubrique1.multiply(valeurRubrique2);
////                                break;
////                            case max:
////                                valeurCalculer = valeurRubrique1.max(valeurRubrique2);
////                                break;
////                            case min:
////                                valeurCalculer = valeurRubrique1.min(valeurRubrique2);
////                                break;
////                            case moins:
////                                valeurCalculer = valeurRubrique1.subtract(valeurRubrique2);
////                                break;
////                        }
////                        valeur1 = etape_rubrique.multiply(valeurCalculer);//
////                    } else {
////                        valeur1 = valeurRubrique1;
////                    }
////
////                }
////
////                //tarif_rubrique.getIdRubrique2() != null
////                OrclassRubrique rubrique22 = rubriqueDao.findEntityHavingValue("code", tarif_rubrique.getIdRubrique2());
////                OrclassTarif tarif_rubrique22 = null;
////                BigDecimal etape_rubrique22 = BigDecimal.ZERO;
////
////                if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
////                    rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique22);
////
////                    tarif_rubrique22 = tarifDao.lastTarif(rubriqueGarantie);
////                } else {
////                    rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique22, entreprise);
////
////                    tarif_rubrique22 = tarifDao.lastTarif(rubriqueGarantie, entreprise);
////                }
////
////                if (tarif_rubrique22 == null) {
////                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
////                    return BigDecimal.ZERO;
////                }
////                etape_rubrique22 = (tarif_rubrique22.getValeur_Additif().add(tarif_rubrique22.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique22.getRapport_Division().intValue()));
////
////                if ((tarif_rubrique22.getIdRubrique1() == null || "".equals(tarif_rubrique22.getIdRubrique1().trim())) && (tarif_rubrique22.getIdRubrique2() == null || "".equals(tarif_rubrique22.getIdRubrique2().trim()))) {
////                    valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique22, tarif_rubrique22);
////                    valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique22);
////                    if (tarif_rubrique22.getOperations() != null) {
////                        switch (tarif_rubrique22.getOperations()) {
////                            case plus:
////                                valeurCalculer = valeurRubrique1.add(valeurRubrique2);
////                                break;
////                            case division:
////                                valeurCalculer = valeurRubrique1.divide(valeurRubrique2);
////                                break;
////                            case multiplication:
////                                valeurCalculer = valeurRubrique1.multiply(valeurRubrique2);
////                                break;
////                            case max:
////                                valeurCalculer = valeurRubrique1.max(valeurRubrique2);
////                                break;
////                            case min:
////                                valeurCalculer = valeurRubrique1.min(valeurRubrique2);
////                                break;
////                            case moins:
////                                valeurCalculer = valeurRubrique1.subtract(valeurRubrique2);
////                                break;
////                        }
////                        valeur2 = etape_rubrique11.multiply(valeurCalculer);//
////                    } else {
////                        valeur2 = valeurRubrique1;
////                    }
////
////                }
////                if (tarif_rubrique.getOperations() != null) {
////                    switch (tarif_rubrique.getOperations()) {
////                        case division:
////                            valeur = valeur1.divide(valeur2);
////                            break;
////                        case multiplication:
////                            valeur = valeur1.multiply(valeur2);
////                            break;
////                        case max:
////
////                            valeur = valeur1.max(valeur2);
////                            break;
////                        case min:
////                            valeur = valeur1.min(valeur2);
////                            break;
////                        case moins:
////                            valeur = valeur1.subtract(valeur2);
////                            break;
////                        case plus:
////
////                            valeur = valeur1.add(valeur2);
////                            break;
////                    }
////                } else {
////                    valeur = valeur1;
////                }
////                /*
////                comment gere le  cas ou tarif_rubrique.getOperations()==null
////                 */
////
////            }
////
////        }
////        valeur = etapePrincipal.multiply(valeur);
////        return valeur;
////
////    }
////    public BigDecimal calculValeurForSecondeRubriqueIsExiste(OrclassRubrique rubrique, OrclassGarantie g) {
////
////        BigDecimal valeurRubrique1 = BigDecimal.ZERO;
////        BigDecimal valeurRubrique2 = BigDecimal.ZERO;
////        BigDecimal valeur = BigDecimal.ZERO;
////        BigDecimal valeurCalculer = BigDecimal.ZERO;
////        BigDecimal etape_rubrique = BigDecimal.ZERO;
////        OrclassTarif tarif_rubrique = null;
////        if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
////            rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique);
////
////            tarif_rubrique = tarifDao.lastTarif(rubriqueGarantie);
////        } else {
////            rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique, entreprise);
////
////            tarif_rubrique = tarifDao.lastTarif(rubriqueGarantie, entreprise);
////        }
////        if (tarif_rubrique == null) {
////            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
////            return BigDecimal.ZERO;
////        }
////        etape_rubrique = (tarif_rubrique.getValeur_Additif().add(tarif_rubrique.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique.getRapport_Division().intValue()));
////        if ((tarif_rubrique.getIdRubrique1() == null || "".equals(tarif_rubrique.getIdRubrique1().trim())) && (tarif_rubrique.getIdRubrique2() == null || "".equals(tarif_rubrique.getIdRubrique2().trim()))) {
////            valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique, tarif_rubrique);
////            valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique);
////            if (tarif_rubrique.getOperations() != null) {
////                switch (tarif_rubrique.getOperations()) {
////                    case division:
////                        valeur = valeurRubrique1.divide(valeurRubrique2);
////                        break;
////                    case multiplication:
////                        valeur = valeurRubrique1.multiply(valeurRubrique2);
////                        break;
////                    case max:
////                        valeur = valeurRubrique1.max(valeurRubrique2);
////                        break;
////                    case min:
////                        valeur = valeurRubrique1.min(valeurRubrique2);
////                        break;
////                    case moins:
////                        valeur = valeurRubrique1.subtract(valeurRubrique2);
////                        break;
////                    case plus:
////                        valeur = valeurRubrique1.add(valeurRubrique2);
////                        break;
////                }
////            }
////
////        } else if (tarif_rubrique.getIdRubrique1() == null && tarif_rubrique.getIdRubrique2() != null) {
////            /*
////            cas pas encore possible mais peutetre pour une version future
////             */
////
////        } else if (tarif_rubrique.getIdRubrique1() != null && tarif_rubrique.getIdRubrique2() != null) {
////            /*
////            cas pas encore possible mais peutetre pour une version future
////             */
////
////        }
//////        return etape_rubrique.multiply(valeur);
////        return valeur;
////    }
//    public BigDecimal calculProrataAutomatiqueGarantie(OrclassGarantie g) {
//        /*
//        ici sera appliquzr l agorithme du calcul automatique  des primes ou proarata
//         */
//        return BigDecimal.ZERO;
//    }
//
//    public void calculTotalProrata() {
//        BigDecimal montant = BigDecimal.ZERO;
//        for (OrclassPoliceGarantie pg : listePoliceGarantie) {
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
//    public void caculTotalPrime() {
//        BigDecimal montant = BigDecimal.ZERO;
//        for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//            if (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null) {
//                continue;
//            }
////            if (pg.getPrime() != null && pg.getPrime().intValue() != 0) {
////                montant = (pg.getTaux_Majoration_reduction() == BigDecimal.ZERO || pg.getTaux_Majoration_reduction().intValue() == 0) ? montant.add(pg.getPrime()) : montant.add(pg.getPrime()).add((pg.getPrime().multiply(pg.getTaux_Majoration_reduction())).divide(BigDecimal.valueOf(100.0)));
////                pg.setProrata(pg.getPrime());
////            }
//
//            if (pg.getPrime() != null && pg.getPrime().intValue() != 0) {
//                montant = montant.add(pg.getPrime());
//                pg.setProrata(pg.getPrime());
//            }
//        }
//
//        this.setTotalPrime(montant);
//
//        this.calculTotalProrata();
//
//        this.updateDataTablePoliceGaranties();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//
//    public void chargeMajorationDuree() {
//        if (categories != null && categories.getIdCategorie() != null) {
////            listeMajorationDurees = majorationDureeDao.listMajorationByCategories(entreprise, categories);
//            listeDurees = majorationDureeDao.listDureeHaveCategories(categories, entreprise);
//        }
//
//    }
//
//    public void chargeAgenceSelect() throws InterruptedException {
//        if (intermediairesSelect != null && intermediairesSelect.getIdIntermediaire() != null) {
//            this.setIntermediaires(intermediairesSelect);
//            chargeProduitNotSupensionByUserHaveAccesAllagence();
//            this.chargerInformationGeneralByCategorieSelectionner();
//            PrimeFaces.current().executeScript("PF('agence').hide();");
//            PrimeFaces.current().ajax().update(":form1:tabprincipal");
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraByAgence(intermediaires, entreprise, branches);
//            this.updateDataTableContrat();
//        } else {
//            PrimeFaces.current().executeScript("PF('agence').show();");
//        }
//
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
//            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
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
//            police.setIdTypeTarif(typeTarifDao.findEntityHavingValue("libelle", "Normal"));
//            /*
//            Charge les elements de travail 
//             */
//            if (devise != null && devise.getId() != null) {
//                police.setIdDevise(devise);
//            }
//
//            listeMajorationDurees.clear();
//
//            listePrestation.clear();
//            listeRubriqueCategorie.clear();
//
//            listeDurees.clear();
//
//            listePoliceGarantie.clear();
//            listeRubriqueGarantie.clear();
//
//            rubriqueGarantie = new OrclassRubriqueGarantie();
//            policeGarantie = new OrclassPoliceGarantie();
////            this.updateDataTableRisqueFamille();
//
////            this.updateDataTablePoliceGaranties();
////            this.updateDataTableRubriqueGarantie();
//        }
//    }
//
//    public void chargeContractSelecte() {
//
//        if (policeSelect != null && policeSelect.getNumero_police() != null) {
//
//            elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(policeSelect.getIdCategories(), entreprise);
//            OrclasseRefGroupe group = null;
//            List<OrclassGroupePolice> gps = new ArrayList<>();
//            this.setPolice(policeSelect);
//            this.setAssure(policeSelect.getIdAssure());
////            this.setIntermediaires(policeSelect.getIdIntermediaire());
//            this.setApporteur(policeSelect.getIdApporteur());
//            this.setCategories(policeSelect.getIdCategories());
//            this.setQuitance(quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect));
//
//            this.setListePoliceCaracteristique((List<OrclassPoliceCaracteristique>) policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprise, policeSelect));
//            if (listePoliceCaracteristique == null) {
//                listePoliceCaracteristique = new ArrayList<>();
//            }
//            this.setListePoliceGarantie((List<OrclassPoliceGarantie>) policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, policeSelect));
//            if (listePoliceGarantie == null) {
//                listePoliceGarantie = new ArrayList<>();
//            }
//            this.chargeMajoratioDureeByDuree();
//
//            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                policeCaracteristique = new OrclassPoliceCaracteristique();
//                listePoliceCaracteristique.add(policeCaracteristique);
//                this.listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, categories);
//                this.reverseListeCaracteristique();
//                policeGarantie = new OrclassPoliceGarantie();
//                listePoliceGarantie.add(policeGarantie);
//                this.listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieNOtExistePoliceGarantie(policeSelect.getIdTypeTarif(), categories, entreprise);
//                this.reverseListeGarantie();
//            }
//            this.setRisque(risqueDao.risqueByPolice(entreprise, policeSelect));
////            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
//            if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
//                if (policeSelect.getPolice() == null || policeSelect.getPolice() == BigDecimal.ZERO) {
//                    this.retourPoliceValider();
//                    policeSelect.setPolice(this.getNumero_police());
//                    policeDao.update(policeSelect);
//
//                } else {
//                    this.setNumero_police(policeSelect.getPolice().toBigInteger());
//                }
//                //charger l attestaion assurance du contrat
//
//                attestationAssurance = attestationAssuranceDao.attestationByPoliceValide(entreprise, policeSelect, risque);
//                if (attestationAssurance == null) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "FATAL ERROR", "POLICE IS VALIDE ATTESTAION NUMBER IS NULL PLEASE CHECK YOUR ADMINISTRATOR..."));
//                    return;
//                }
//                num_attestation = attestationAssurance.getNumumero_Attestation();
//                if (police.getIdApporteur() != null && police.getIdApporteur().getIdApporteur() != null) {
//                    listeApporteur = new ArrayList<>();
//                    listeApporteur.add(police.getIdApporteur());
//                }
//                if (police.getIdFractionnementCategories() != null && police.getIdFractionnementCategories().getId() != null) {
//                    listeFractionnement = new ArrayList<>();
//                    this.setFractionnement(police.getIdFractionnementCategories().getIdFractionnemente());
//
//                    listeFractionnement.add(fractionnement);
//
//                }
//
//            } else {
//                this.setNumero_police(policeSelect.getNumero_police());
////                if (police.getIdApporteur() != null && police.getIdApporteur().getIdApporteur() != null) {
//                listeApporteur = new ArrayList<>(apporteurDao.apporteurByCompagnies(entreprise));
////                    listeApporteur.add(police.getIdApporteur());
////                }
////                if (police.getIdFractionnementCategories() != null && police.getIdFractionnementCategories().getId() != null) {
//                listeFractionnement = new ArrayList<>(fractionnementCategoriesDao.listFractionnementHaveCategories(categories, entreprise));
//
////                }
//            }
//
//            this.setDuree(policeSelect.getIdMajorationDuree().getIdDuree());
//
//            this.chargeMajorationDuree();
//
//            //         //recuperation conducteur and attestation conducteur
//            conducteur = conducteurDao.finKey(entreprise, risque)== null ? new OrclassConducteur():conducteurDao.finKey(entreprise, risque);
//            
//            this.setAttestationConducteur(conducteur.getIdTestationConducteur()==null ? new OrclassAttestationConducteur():conducteur.getIdTestationConducteur());
//            // 
////            if (police.getIdReduction()!=null && police.getIdReduction().getId()!=null) {
//            listeReduction = reductionDao.listeReductionByCompagnieForCategorie(entreprise, categories);
////            }
//            this.caculTotalPrime();
//            this.calculTotalProrata();
//
//            PrimeFaces.current().executeScript("PF('managePoliceDialog').hide();");
//            PrimeFaces.current().ajax().update(":form1");
//            PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//        }
//    }
//
//    public void retourPoliceValider() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        print = Boolean.TRUE;
//        validation = Boolean.TRUE;
//        OrclassProposition proposition = null;
//        BigInteger policeValide = BigInteger.ZERO;
//        if (police != null && Objects.equals(police.getValidation(), Boolean.TRUE) && police.getPolice() != null && police.getPolice() != BigDecimal.ZERO) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "the contract has already been validated"));
//            return;
//        }
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
//        this.police.setPolice(policeValide);
//        this.setNumero_police(policeValide);
//    }
//
//    public void validationProjet() throws IOException, JRException {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        String nomComplet;
//        if (!"".equals(num_attestation) && num_attestation != null) {
//            if (num_attestation != null && !"".equals(num_attestation) && gestionStockCompagnieAgence != null && gestionStockCompagnieAgence.getId() != null && (police != null && police.getId() == null)) {
//                enregistrer = Boolean.TRUE;
//                validation = Boolean.TRUE;
//                print = Boolean.FALSE;
//                this.createContrat();
//                this.police = policeDao.finKey(intermediaires, numero_police, entreprise, categories);
//                this.retourPoliceValider();
//                if (police.getPolice() != null && police.getPolice() != BigDecimal.ZERO) {
//                    policeDao.update(police);
//                }
////                this.reload();
//            } else {
//                if (num_attestation != null && !"".equals(num_attestation) && gestionStockCompagnieAgence != null && gestionStockCompagnieAgence.getId() != null && (police != null && police.getId() != null)) {
//                    // on creer l attestation
//                    OrclassAttestationAssurance newattestaion_ass;
//
//                    if (attestationAssuranceDao.findEntityHavingValue("numumero_Attestation", num_attestation) == null) {
//                        newattestaion_ass = new OrclassAttestationAssurance();
//                        newattestaion_ass.setDateCreation(new Date());
//                        newattestaion_ass.setDate_affectation(attestationAssurance.getDate_affectation());
//                        newattestaion_ass.setDate_echeance(attestationAssurance.getDate_echeance());
//                        newattestaion_ass.setDate_effet(attestationAssurance.getDate_effet());
//                        newattestaion_ass.setNumumero_Attestation(num_attestation);
//                        newattestaion_ass.setIdDocumentCategories(attestationAssurance.getIdDocumentCategories());
//                        newattestaion_ass.setIdEntreprises(entreprise);
//                        newattestaion_ass.setIdPolice(police);
//                        newattestaion_ass.setIdRisque(risque);
//                        newattestaion_ass.setNatureRisque(attestationAssurance.getNatureRisque());
//                        newattestaion_ass.setNum_logique(attestationAssurance.getNum_logique());
//                        newattestaion_ass.setSaisir_par(user.getNom() + " " + user.getPrenom());
//                        attestationAssuranceDao.create(newattestaion_ass);
//                        BigInteger sortie = gestionStockCompagnieAgence.getStockSortie();
//                        gestionStockCompagnieAgence.setStockSortie(sortie.add(BigInteger.ONE));
//                        BigInteger stockFinala = gestionStockCompagnieAgence.getStockFinal();
//                        gestionStockCompagnieAgence.setStockFinal(stockFinala.subtract(BigInteger.ONE));
//                        gestionCompagnieAgenceDao.update(gestionStockCompagnieAgence);
//
//                        nomComplet = user.getNom();
//                        if (user.getPrenom() != null && !"".equals(user.getPrenom())) {
//                            nomComplet = user.getNom() + " " + user.getPrenom();
//
//                        }
//                        police.setValider_par(nomComplet);
//                        police.setDate_validation(new Date());
//                        this.retourPoliceValider();
//                        if (police.getPolice() != null && police.getPolice() != BigDecimal.ZERO) {
//                            police.setValidation(Boolean.TRUE);
//                            policeDao.update(police);
//                        }
//                    }
//                }
//            }
//        } else {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "WRITE THE ATTESTATION NUMBER..", "ACTION NOT FOUND PLEASE WRITE THE NUMBER ATTESTATION"));
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, numero_police.toString(), "WRITE THE ATTESTATION NUMBER..", null);
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "WRITE THE ATTESTATION NUMBER..", "ACTION NOT FOUND PLEASE WRITE THE NUMBER ATTESTATION"));
//            return;
//        }
//
//    }
//
//    public void saveProjetAuto() throws IOException {
//        enregistrer = Boolean.TRUE;
//        validation = Boolean.FALSE;
//        print = Boolean.FALSE;
//        this.createContrat();
////        this.reload();
//
//    }
//
//    public void controlleBefortPrint() throws IOException, JRException {
//        if (police != null) {
//            if (num_attestation != null && !"".equals(num_attestation)) {
//                enregistrer = Boolean.TRUE;
//
//                validation = (police.getValidation() == null || Objects.equals(police.getValidation(), Boolean.FALSE)) ? Boolean.FALSE : Boolean.TRUE;
//                print = Boolean.TRUE;
//
//                if (policeDao.finKey(intermediaires, numero_police, entreprise, categories) == null && Objects.equals(validation, Boolean.FALSE)) {
////                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "CONTRACT  NOT CREATE ... TRY AGAINST"));
////                    return;
//                    this.createContrat();
//                } else if (policeDao.finKey(intermediaires, numero_police, entreprise, categories) != null && Objects.equals(validation, Boolean.FALSE)) {
//                    this.updateContrat();
//
//                }
//                //appelle la fonction print
////                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FONCTION PRINT", "ADD FONCTION PRINT"));
//                this.creatEtatPrint();
//
//            } else {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "CONTRACT DON'T HAVE PRINT ATTESTATION NUMBER IS EMPTY"));
//                return;
//            }
//
//        }
//    }
//
//    public void creatEtatPrint() throws IOException, JRException {
//        Contrat_Auto contrat_Auto;
//        OrclassPolice police = null;
//        OrclassRisque risque = null;
//        colContrat_Auto.clear();
//        ComparatorChain chain = new ComparatorChain();
//        if (this.police.getPolice() != null && this.police.getPolice().intValue() != 0 && this.police.getValidation() == Boolean.TRUE) {
//            police = policeDao.finKeyPoliceValide(intermediaires, this.police.getPolice().toBigInteger(), entreprise, categories);
//        } else {
//            police = policeDao.finKey(intermediaires, numero_police, entreprise, categories);
//        }
//        OrclassAssure assure = police.getIdAssure();
//        OrclassQuitance quittance = quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, police);
//
//        List<OrclassPoliceCaracteristique> colCaracteristiquePrint = new ArrayList<>();
//        List<OrclassPoliceGarantie> listePoliceGarentie = new ArrayList<>();
//
//        if (police != null && police.getId() != null && police.getIdIntermediaire() != null) {
//
//            contrat_Auto = new Contrat_Auto();
////            if (police.getIdCategories().getCode().equals(LibelleCategorie.autres)) {
////                contrat_Sante.setCategorie(police.getIdCategories().getLibelleAutre());
////            } else {
////                contrat_Sante.setCategorie(GlobalFonctions.valueObject(police.getIdCategories().getLibelle()));
////            }
//            if (police.getIdCategories().getLibelleAutre() == null) {
//                contrat_Auto.setCategorie(GlobalFonctions.valueObject(police.getIdCategories().getLibelle()));
//            } else {
//                contrat_Auto.setCategorie(police.getIdCategories().getLibelleAutre());
//            }
////            contrat_Sante.setCategorie(pathFormat);
//            //information sur la police et l agance
//            contrat_Auto.setLibelleAgence(police.getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//            if (police.getPolice() != null && police.getPolice().intValue() != 0) {
//                contrat_Auto.setPolice(BigDecimal.valueOf(police.getPolice().doubleValue()));
//            } else {
//                contrat_Auto.setPolice(BigDecimal.valueOf(police.getNumero_police().doubleValue()));
//            }
//
//            contrat_Auto.setTelAgence(police.getIdIntermediaire().getAdresse().getTel());
//            if (police.getRef_intermediaire() == null) {
//                contrat_Auto.setRefPolice("");
//            } else {
//                contrat_Auto.setRefPolice(police.getRef_intermediaire());
//            }
//            if (police.getIdIntermediaire().getAdresse() != null && police.getIdIntermediaire().getAdresse().getFax() != null) {
//                contrat_Auto.setFaxAgence(police.getIdIntermediaire().getAdresse().getFax());
//            } else {
//                contrat_Auto.setFaxAgence("");
//            }
//
//            contrat_Auto.setAdresseBpAgence("" + police.getIdIntermediaire().getAdresse().getBp());
//            contrat_Auto.setVilleAgence(police.getIdIntermediaire().getIdVille().getLibelle());
//            //information du souscripteur;
//            if (assure != null && assure.getId() != null) {
//                if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                    contrat_Auto.setNomComplet(assure.getRaison_social());
//                } else {
//                    contrat_Auto.setNomComplet(assure.getNom());
//                    if (assure.getPrenom() != null) {
//                        contrat_Auto.setNomComplet(assure.getNom() + " " + assure.getPrenom());
//
//                    }
//                }
//
//                contrat_Auto.setTel(assure.getAdresse().getIndicatifPays() + "" + assure.getAdresse().getTel());
//                contrat_Auto.setAdresseBp(assure.getAdresse().getBp());
//                contrat_Auto.setActivite(assure.getIdActivite().getLibelle());
//                contrat_Auto.setMail(assure.getAdresse().getEmail());
//                contrat_Auto.setFax(assure.getAdresse().getFax());
//                contrat_Auto.setCodeAssure(assure.getCode());
//                if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                    contrat_Auto.setDateNaissance("");
//                } else {
//                    contrat_Auto.setDateNaissance(GlobalFonctions.formatDate(assure.getDate_naissance()) + "- Sexe " + assure.getSexe().name());
//                }
//
//            }
//            //information periode de garantie
//            contrat_Auto.setDateDebutGarantie(GlobalFonctions.formatDate(police.getDate_effet()) + " 00:00 ");
//            contrat_Auto.setDateFinGarantie(GlobalFonctions.formatDate(police.getDate_echeance()) + " 23:59 ");
//            contrat_Auto.setDureGarantie("" + police.getValeurDuree() + " " + GlobalFonctions.valueObject(police.getIdMajorationDuree().getIdDuree().getUniteDuree()));
//            contrat_Auto.setContrat(GlobalFonctions.valueObject(police.getContrat()) + " " + GlobalFonctions.valueObject(police.getNatureContrat()));
//            //information sur la quittance
//            if (quittance != null && quittance.getId() != null) {
//                contrat_Auto.setAccessoire(GlobalFonctions.formatNumberGeneral(quittance.getMontant_Accessoire().longValue()));
//                contrat_Auto.setAsac(quittance.getTaxe_asac_fga() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getTaxe_asac_fga().longValue()));
//                contrat_Auto.setTaux(quittance.getTaxe_tva() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getTaxe_tva().longValue()));
//                contrat_Auto.setCarte_rose(quittance.getTaxe_caterose() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getTaxe_caterose().longValue()));
//                contrat_Auto.setCp(quittance.getTimbr_Gradue_cp() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getTimbr_Gradue_cp().longValue()));
//                contrat_Auto.setVignette(quittance.getTimbr_Gradue__vignette() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getTimbr_Gradue__vignette().longValue()));
//                contrat_Auto.setPooltpv(quittance.getTaxe_pool_tpv() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getTaxe_pool_tpv().longValue()));
//                contrat_Auto.setBonus(quittance.getBonus() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getTaxe_pool_tpv().longValue()));
//                contrat_Auto.setRed_ds(quittance.getBonus() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getBonus().longValue()));
//                contrat_Auto.setRed_rc(quittance.getRed_duree_condit() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getBonus().longValue()));
//                contrat_Auto.setMaj_mat_infl(quittance.getMatiere_inflamable() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getMatiere_inflamable().longValue()));
//                contrat_Auto.setRed_rc(quittance.getReduction() == null ? "0" : GlobalFonctions.formatNumberGeneral(quittance.getReduction().longValue()));
//                contrat_Auto.setDevise(police.getIdDevise().getCode());
//                contrat_Auto.setTotalTaxe(GlobalFonctions.formatNumberGeneral(quittance.getTotalTaxes().longValue()));
//                contrat_Auto.setPrimeNetteQuittance(GlobalFonctions.formatNumberGeneral(quittance.getPrimeNette().longValue()));
////                contrat_Auto.setTotal_reduction(quittance.getr);
//                contrat_Auto.setTotal_a_paye(GlobalFonctions.formatNumberGeneral(quittance.getTotal_a_paye().longValue()));
//
//            }
//            //enregistrement d information vehicule
//            risque = risqueDao.risqueByPolice(entreprise, police);
//            if (risque != null && risque.getId() != null) {
//                contrat_Auto.setTarif(police.getIdTypeTarif().getCode());
//                contrat_Auto.setGenre(risque.getGenre_vehicule());
//                contrat_Auto.setUsage(risque.getUsage_vehicule());
//                contrat_Auto.setZone(GlobalFonctions.valueObject(risque.getZone()));
//                contrat_Auto.setVehicule(risque.getMarque_vehicule() + " " + risque.getRefer_Marq_vehic());
//                contrat_Auto.setImmatriculation(risque.getNum_immatricul());
//                contrat_Auto.setMise_circulation(GlobalFonctions.formatDate(risque.getDateMec()));
//                contrat_Auto.setNum_chasis(risque.getNumero_chassis());
//                contrat_Auto.setNbre_places(risque.getNombre_place().toString());
//                contrat_Auto.setPuissance(risque.getPuissance_vehicule().toString());
//                contrat_Auto.setEnergie(GlobalFonctions.valueObject(risque.getEnergie()));
//                contrat_Auto.setPoids("" + risque.getPoids_vehicule());
//            }
////enregistrement garantie
//            listePoliceGarentie = this.formatageByAffichage(policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, police));
//            colCaracteristiquePrint = policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprise, police);
//
//            contrat_Auto.setColCaracteristiquePrint(colCaracteristiquePrint);
//
//            contrat_Auto.setColGarantiePrint(listePoliceGarentie);
//            if (police.getValider_par() != null) {
//                contrat_Auto.setUser(police.getValider_par());
//            } else {
//                contrat_Auto.setUser("");
//            }
//
//            if (police.getIdApporteur() != null && police.getIdApporteur().getIdApporteur() != null) {
//                contrat_Auto.setCharger_client(police.getIdApporteur().getIdRefApporteur().getRaisonSociale());
//            } else {
//                contrat_Auto.setCharger_client("");
//            }
//            contrat_Auto.setTarif(police.getIdTypeTarif().getLibelle());
//
//            colContrat_Auto.add(contrat_Auto);
//
//            this.printContratAutoMono();
//
//        }
//    }
//
//    public List<OrclassPoliceGarantie> formatageByAffichage(List<OrclassPoliceGarantie> pg) {
//        List<OrclassPoliceGarantie> listePg = new ArrayList<>();
//        if (pg != null && !pg.isEmpty()) {
//            for (OrclassPoliceGarantie plg : pg) {
//                if (Objects.equals(plg.getIdGarantie().getCapital_illimitter(), Boolean.TRUE)) {
//                    plg.setLibelleCapital("ILLIMITE");
//                } else if (plg.getCapital() == null || plg.getCapital() == BigDecimal.ZERO) {
//                    plg.setLibelleCapital("");
//                } else {
//                    plg.setLibelleCapital(GlobalFonctions.formatNumberGeneral(plg.getCapital().longValue()));
//                }
//
//                if (plg.getPrime() == null || plg.getPrime() == BigDecimal.ZERO) {
//                    plg.setLibellePrime("");
//                } else {
//                    plg.setLibellePrime(GlobalFonctions.formatNumberGeneral(plg.getPrime().longValue()));
//                }
//                listePg.add(plg);
//            }
//        }
//        return listePg;
//    }
//
//    public void printContratAutoMono() throws IOException, JRException {
//        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> map_subreport = new HashMap<>();
////        ComparatorChain chain = new ComparatorChain();
//        ImageIcon logo = null;
//        ImageIcon img = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassPolice p = null;
//        OrclassQuitance quittance = null;
//        if (this.police == null || this.police.getId() == null) {
//            p = policeDao.finKey(intermediaires, numero_police, entreprise, categories);
//            if (p == null || p.getId() == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR...", "POLICE NON TROUVE ...."));
//                return;
//            }
//            quittance = quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, p);
//        } else {
//            quittance = quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, police);
//        }
//
//        if (quittance == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR...", "QUITTANCE NON EXISTANT ...."));
//            return;
//        }
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
//        map.put("accessoires", quittance.getMontant_Accessoire());
//        map.put("tva", quittance.getTaxe_tva());
//        map.put("total_net_payer", quittance.getTotal_a_paye());
//        map.put("primenet", quittance.getPrimeNette());
//
//        map_subreport.put("tva_v", this.tva);
//        map_subreport.put("tva_v_1", this.tva.divide(BigDecimal.valueOf(100.0)));
//        map.put("map_subreport", map_subreport);
//        if (numero_police.signum() < 0) {
//            map.put("proposition", "PROPOSITION");
//        } else {
//            map.put("proposition", "");
//        }
////         if (Objects.equals(tabShowIndividuel, Boolean.TRUE)) {
//        GlobalFonctions.printPdfHaveSubReportAuto(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratAuto + File.separator + GlobalFonctions.dossierDefaut + File.separator + "autoMono", colContrat_Auto, map, null);
//
////        } else if (Objects.equals(tabShowFamille, Boolean.TRUE)) {
////            GlobalFonctions.printPdfHaveSubReport(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "FamilleMaladie_1", colContrat_Sante, map, null);
////        }
//    }
//
//    public void createContrat() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean resultat = Boolean.FALSE;
//        OrclassFractionnementCategories fcat = null;
//        try {
//
//            if ((police == null || police.getId() == null)) {
//                police.setNumero_police(numero_police);
//                police.setIdCategories(categories);
//                police.setIdIntermediaire(intermediaires);
//                conducteur.setIdTestationConducteur(attestationConducteur);
//                if (fractionnement != null && fractionnement.getId() != null) {
//                    fcat = fractionnementCategoriesDao.lastRowFractionnement(categories, entreprise, fractionnement);
//                    if (fcat != null && fcat.getId() != null) {
//                        police.setIdFractionnementCategories(fcat);
//                    }
//                }
//                if (listeConducteur.isEmpty()) {
//
//                    listeConducteur.add(conducteur);
//                } else if (listeConducteur.contains(conducteur) == Boolean.FALSE) {
//                    listeConducteur.add(conducteur);
//                }
//                resultat = serviceAuto.addProjetAutoMono(assure, police, risque, listeConducteur, listePoliceCaracteristique, listePoliceGarantie, quitance, listePoliceCommissionApporteur, entreprise, num_attestation, attestationAssurance, gestionStockCompagnieAgence, user, enregistrer, validation, print);
//                if (Objects.equals(resultat, Boolean.TRUE)) {
//                    GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "police", Success.INSERT_SUCCESS + "", new Object[]{numero_police});
//
//                } else {
//                    GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, numero_police.toString(), "OPERATION NOT FOUND", new Object[]{"police"});
//                }
//
//            }
//        } catch (GlobalException g) {
//
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", g.getMessage() + " " + g.getLocalizedMessage() + " " + g.getCause().getMessage(), new Object[]{numero_police});
//
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "police", exception.Error.FATAL_ERROR + "", new Object[]{"police"});
//            logger.error("Erreur Survenu", th);
//        }
//    }
//
//    public void annullationContrat() throws IOException {
//        if (police != null && police.getNumero_police() != null && police.getIdIntermediaire() != null) {
//            police.setAnnulation(Boolean.TRUE);
//            police.setDate_annulation(new Date());
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
//        Boolean resultat = Boolean.FALSE;
//        OrclassFractionnementCategories fcat = null;
//        listeConducteur = new ArrayList<>();
//        try {
//
//            if ((police != null && police.getId() != null)) {
//
//                conducteur.setIdTestationConducteur(attestationConducteur);
//                if (fractionnement != null && fractionnement.getId() != null) {
//                    fcat = fractionnementCategoriesDao.lastRowFractionnement(categories, entreprise, fractionnement);
//                    if (fcat != null && fcat.getId() != null) {
//                        police.setIdFractionnementCategories(fcat);
//                    }
//                }
////                if (listeConducteur.isEmpty()) {
//
//                listeConducteur.add(conducteur);
////                } else if (listeConducteur.contains(conducteur) == Boolean.FALSE) {
////                    listeConducteur.add(conducteur);
////                }
//                resultat = serviceAuto.updateProjetAutoMono(assure, police, risque, listeConducteur, listePoliceCaracteristique, listePoliceGarantie, quitance, listePoliceCommissionApporteur, entreprise, num_attestation, attestationAssurance, gestionStockCompagnieAgence, user);
//                if (Objects.equals(resultat, Boolean.TRUE)) {
//                    GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "police", Success.UPDATE_SUCCESS + "", new Object[]{numero_police});
//
//                } else {
//                    GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, numero_police.toString(), "OPERATION NOT FOUND", new Object[]{"police"});
//                }
//
//            }
//        } catch (GlobalException g) {
//
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", g.getMessage() + " " + g.getLocalizedMessage() + " " + g.getCause().getMessage(), new Object[]{numero_police});
//
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.UPDATE_ERROR + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "police", exception.Error.FATAL_ERROR + "", new Object[]{"police"});
//            logger.error("Erreur Survenu", th);
//        }
//
//    }
//
////    public void updateDataTableRubriqueGarantie() {
////        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:idrgTable");
////        table.setValueExpression("sortBy", null);
////
////        PrimeFaces.current().executeScript("PF('rgTable').clearFilters();");
//////         PrimeFaces.current().ajax().update("form1_1");
////
////    }
////    public void updateDataTablePoliceGaranties() {
////        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idpgTable");
////        table.setValueExpression("sortBy", null);
////
////        PrimeFaces.current().executeScript("PF('pgTable').clearFilters();");
//////         PrimeFaces.current().ajax().update("form1_1");
////
////    }
////    public void updateDataTableRisqueFamille() {
////        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form1:tabprincipal:idrisqueFamilleTable");
////        table.setValueExpression("sortBy", null);
////
////        PrimeFaces.current().executeScript("PF('risqueFamilleTable').clearFilters();");
//////         PrimeFaces.current().ajax().update("form1_1");
////
////    }
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
//    public void chargeProduitNotSupensionByUserHaveAccesAllagence() {
//        suspensionBrancheIntemediaire = suspensionBrancheIntemediaireDao.finKey(intermediaires, entreprise, branches);
//        if (suspensionBrancheIntemediaire == null) {
//            suspensionBrance = Boolean.FALSE;
//            listSuspensionCategorieIntermediaire = suspensionCategorieIntermediaireDao.listeBrancheSuspendueForIntermediaire(intermediaires, entreprise, branches);
//            if (listSuspensionCategorieIntermediaire.isEmpty() && suspensionBrancheIntemediaire != null) {
//                lisCategoriesByAutomobile.clear();
//            } else {
//                lisCategoriesByAutomobile.removeAll(listSuspensionCategorieIntermediaire);
//            }
//        } else {
//            suspensionBrance = Boolean.TRUE;
//            listSuspensionCategorieIntermediaire = suspensionCategorieIntermediaireDao.listeBrancheSuspendueForIntermediaire(intermediaires, entreprise, branches);
//            if (listSuspensionCategorieIntermediaire.isEmpty() && suspensionBrancheIntemediaire != null) {
//                lisCategoriesByAutomobile.clear();
//            } else {
//                lisCategoriesByAutomobile.removeAll(listSuspensionCategorieIntermediaire);
//            }
//
//        }
//    }
//
//    public void controleDureeVariable() {
//        if (duree != null && duree.getId() != null && categories != null && categories.getIdCategorie() != null) {
//            if (duree.getMaxDuree() != null && duree.getMaxDuree().intValue() != 0 && (duree.getMinDuree().intValue() > police.getValeurDuree().intValue() || duree.getMaxDuree().intValue() < police.getValeurDuree().intValue())) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("LA VALEUR DE LA DUREE N EST PAS VALIDE ...[" + duree.getMinDuree().intValue() + "-" + duree.getMaxDuree().intValue() + "]"));
//                police.setValeurDuree(BigInteger.ZERO);
//                return;
//            }
//        }
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
//    public void updateDataTableDetailCommission() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:iddcTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('dcTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
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
//    public void showDetailsRubrique(OrclassCaracteristiques item) {
//
//        if (rubriqueCaracteristique.getIdCaracteristiques() == null || rubriqueCaracteristique.getIdCaracteristiques().getId() == null && item != null) {
//            this.getRubriqueCaracteristique().setIdCaracteristiques(item);
//        }
//        policeCaracteristique = new OrclassPoliceCaracteristique();
//        policeCaracteristique.setIdCaracteristiques(rubriqueCaracteristique.getIdCaracteristiques());
//        policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
//
//        listePoliceCaracteristique.add(policeCaracteristique);
//        this.updateDataTablePoliceCarzacteristique();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
////                this.reverseListeCaracteristique();
//
//    }
//
//    public void showDetailsRubriqueGarantie(OrclassGarantie item) {
//        if (garantie == null || garantie.getId() == null && item != null) {
//            this.setGarantie(garantie);
//        }
//        policeGarantie = new OrclassPoliceGarantie();
//        policeGarantie.setIdGarantie(garantie);
//        if (garantie.getModeCalcul().equals(ModeCalcul.Automatique)) {
//            policeGarantie.setEditer(Boolean.FALSE);
//            policeGarantie = this.calculPrimeAutomatiqueGarantie(policeGarantie);
//            policeGarantie.setProrata(policeGarantie.getPrime());
//        } else if (garantie.getModeCalcul().equals(ModeCalcul.manuel)) {
//            policeGarantie.setEditer(Boolean.TRUE);
//        }
//        policeGarantie.setTaux_Majoration_reduction(BigDecimal.ZERO);
//        listePoliceGarantie.add(policeGarantie);
//        this.updateDataTablePoliceGaranties();
//        this.updateDataTableRubriqueGarantie();
////                this.reverseListeCaracteristique();
//
//    }
//
//    //affecter une attestation ou la creation d un document pour attestaion
//    public void createDocumentAttestaion() {
//        if (num_attestation != null && num_attestation != "" && police != null && police.getId() != null && Objects.equals(police.getValidation(), Boolean.TRUE)) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPERATION NOT FOUND", "ATTESTATION NUMBER IS EXIST"));
//            return;
//        }
//        if (attestationAssurance == null) {
//            attestationAssurance = new OrclassAttestationAssurance();
//
//        }
//        this.chargeCategorieDocument();
//        if (listeDocumentCategories.isEmpty()) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("LIST IS EMPTY", "DOCUMENT DON'T N EXISTS...PLEASE CHECK YOUR ADMINISTRATOR"));
//            return;
//        }
//        attestationAssurance.setDate_echeance(police.getDate_echeance());
//        attestationAssurance.setDate_effet(police.getDate_effet());
//        attestationAssurance.setIdEntreprises(entreprise);
//        attestationAssurance.setNatureRisque(NatureRisque.vehicule);
//        attestationAssurance.setIdRisque(risque);
//        PrimeFaces.current().executeScript("PF('attestationAss').show()");
//
//        // appelle du dialog pour 
//    }
//
//    public void chargeCategorieDocument() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            listeDocumentCategories = documentCategoriesDao.listeDoucumentCategoriesByCompagnie(entreprise, categories);
//
//        }
//
//    }
//
//    public void affecterNumeroAttestaionAuProjet() {
//        // ici nous devons verifier si le document lier a ce numero d attestation existe
//        OrclassGestionCompagnieAgence gsag = null;
//        if (attestationAssuranceDao.findEntityHavingValue("numumero_Attestation", attestationAssurance.getNumumero_Attestation()) != null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, "AFFECTATION NOT FOUND", "ATTESTATION NUMBER DUPPLICATE"));
//            return;
//        }
//        if (attestationAssurance.getNumumero_Attestation() != null && !"".equals(attestationAssurance.getNumumero_Attestation()) && attestationAssurance.getIdDocumentCategories() != null && attestationAssurance.getIdDocumentCategories().getId() != null) {
//            gsag = gestionCompagnieAgenceDao.lasteStockCompagnieAgence(entreprise, attestationAssurance.getIdDocumentCategories().getNatureDocuments(), attestationAssurance.getIdDocumentCategories().getTypeDocument(), intermediaires);
//            if (gsag == null || gsag.getStockFinal() == BigInteger.ZERO) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("AFFECTATION NOT FOUND", "ATTESTATION NUMBER NOT EXISTS FOR AGENCY"));
//                PrimeFaces.current().executeScript("PF('attestationAss').show();");
////                PrimeFaces.current().executeScript("PF('attestationAss').hide();");
//                return;
//
//            }
//            if (((gsag.getDebut_serie().compareTo(new BigInteger(attestationAssurance.getNumumero_Attestation())) == 0) || (gsag.getDebut_serie().compareTo(new BigInteger(attestationAssurance.getNumumero_Attestation())) == 1)) && ((gsag.getFin_serie().compareTo(new BigInteger(attestationAssurance.getNumumero_Attestation()))) == 0 || (gsag.getFin_serie().compareTo(new BigInteger(attestationAssurance.getNumumero_Attestation()))) == 1)) {
//                this.setNum_attestation(attestationAssurance.getNumumero_Attestation());
//                PrimeFaces.current().executeScript("PF('attestationAss').hide();");
//                gestionStockCompagnieAgence = gsag;
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("OPPERATION SUCCESS..", "ATTESTATION NUMBER  EXISTS FOR AGENCY"));
//
//            } else {
//                if (gsag.getDebut_serie().toString().length() != attestationAssurance.getNumumero_Attestation().length()) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE WRITE THE CORRECT NUMBER", "THE SIZE OF THE NUMBER MUST BE " + gsag.getDebut_serie().toString().length()));
//                    PrimeFaces.current().executeScript("PF('attestationAss').show();");
////                PrimeFaces.current().executeScript("PF('attestationAss').hide();");
//                    return;
//                } else {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("AFFECTATION NOT FOUND", "ATTESTATION NUMBER NOT EXISTS FOR AGENCY"));
//                    PrimeFaces.current().executeScript("PF('attestationAss').show();");
////                PrimeFaces.current().executeScript("PF('attestationAss').hide();");
//                    return;
//                }
//
//            }
//
//        }
//
//    }
//
//    /*
//    fonction pour retourner le tarif pour condition precise selon la categorie du vehicule 
//    
//     */
//    public OrclassTarif tarifWithConditionByCategorieVehicule() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            OrclassTarif tarif = null;
//            OrclassTarifCondition tarif_condition = null;
//            List<OrclassTarif> listeValues = new ArrayList<>();
//            List<OrclassTarifCondition> listeValuesForEnergie = new ArrayList<>();
//            OrclassTarifCondition tarifCondition=null;
//            int categorie_vehicule = categories.getCategoriesVehicule() == null ? 0 : categories.getCategoriesVehicule();
//
//            if (categorie_vehicule != 0) {
//                switch (categorie_vehicule) {
//                    case 1: // ici on parle du vehicule categorie 1
//                        // pour cette categorie nous utiliserons ensemble de parametre qui est un ellement de vehicule
//
////                        listeValues = tarifConditionDao.retourneTarifConditionByVehicule(risque, entreprise, categorie_vehicule, police.getIdTypeTarif());
//                        listeValues=tarifDao.retourneTarifByVehicule(risque, entreprise, categorie_vehicule, police.getIdTypeTarif());
//                        if (!listeValues.isEmpty()) {
//                              System.out.println("Taille Liste :"+listeValues.size());
//                            System.out.println(" taille liste :"+listeValues.size());
//                            for (OrclassTarif t : listeValues) {
//                                if (tarif!=null && tarif.getId()!=null) {
//                                    continue;
//                                }
//                                if (tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarif(t, risque.getZone().name(), entreprise)!= null && tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarif(t,risque.getEnergie().name(), entreprise)!=null && tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarif(t, risque.getUsage_vehicule(), entreprise)!=null && tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarif(t, risque.getGenre_vehicule(), entreprise)!=null && tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarif(t, VehiculeObjet.avec_remoque.name(),risque.getAvec_remoque()==null ? false:risque.getAvec_remoque(), entreprise)!=null ) {
//                                    tarifCondition=tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarifForPuissance(t, VehiculeObjet.puissance.name(), risque.getPuissance_vehicule(), entreprise);
//                                    if (tarifCondition!=null && tarifCondition.getId()!=null) {
//                                        tarif=tarifCondition.getIdTarif();
//                                        System.out.println("valeur Min:"+tarifCondition.getValeurMin()+" Valeur Max: "+tarifCondition.getValeurMax()+" id Tarif :"+tarif.getId() );
//                                    }
//                                }
//                            }
////                                try {
//////                                    if (Zone.valueOf(l.getIdRubriqueCaracteristique().getIdCaracteristiques().getLibelle()).equals(risque.getZone())) {
////                                    if (Energie.valueOf(l.getIdRubriqueCaracteristique().getIdCaracteristiques().getLibelle()).equals(risque.getEnergie())) {
////                                        System.out.println("valeurMin: " + l.getValeurMin() + " valeur Max " + l.getValeurMax() + " tarif " + l.getIdTarif() + " " + l.getIdTarif().getConstant1());
////
////                                        if ((l.getValeurMin().compareTo(risque.getPuissance_vehicule()) == -1 || l.getValeurMin().compareTo(risque.getPuissance_vehicule()) == 0) && l.getValeurMax().compareTo(risque.getPuissance_vehicule()) == 1) {
////                                            System.out.println("valeur tarification: " + l.getIdTarif() + " constant" + l.getIdTarif().getConstant1());
////                                            tarif_condition = l;
////                                            tarif = tarif_condition.getIdTarif();
////                                        }
////                                    }
//////                                    }
////                                } catch (java.lang.IllegalArgumentException e) {
////                                    System.err.println("une exception est levée  Value texte :" + l.getValeurTexte());
////                                }
////
////                            }
//
//                        }
//                        break;
//                    case 2: // ici on parle du vehicule categorie 2
//                        //comme parametre de condition nous avons essence,diesel,sans et avec remorque matiere inflamable
//
//                        break;
//                    case 3: // ici on parle du vehicule categorie 3
//
//                        break;
//                    case 4: // ici on parle du vehicule categorie 4
//
//                        break;
//                    case 5: // ici on parle du vehicule categorie 5
//
//                        break;
//                    case 6: // ici on parle du vehicule categorie 6
//
//                        break;
//                    case 7: // ici on parle du vehicule categorie 7
//
//                        break;
//                    case 8: // ici on parle du vehicule categorie 8
//
//                        break;
//                    case 9: // ici on parle du vehicule categorie 9
//
//                        break;
//                    case 10: // ici on parle du vehicule categorie 10
//
//                        break;
//                    default:
//                        throw new AssertionError();
//                }
//
//            }
//            return tarif;
//        }
//        return null;
//    }
//
//    // afficher les detail commissions manageCommissionDialog
//    public void detailCommissions() {
//        if (this.listePoliceCommissionApporteur.isEmpty()) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("LIST IS EMPTY", "YOU DON'T SELECT OR HE NOT EXISTS..."));
//            return;
//
//        }
//        this.updateDataTableDetailCommission();
//        PrimeFaces.current().executeScript("PF('manageCommissionDialog').show();");
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
//    public OrclassCategories getCategories() {
//        return categories;
//    }
//
//    public void setCategories(OrclassCategories categories) {
//        this.categories = categories;
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
//    public OrclassQualite getQualite() {
//        return qualite;
//    }
//
//    public void setQualite(OrclassQualite qualite) {
//        this.qualite = qualite;
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
//    public OrclassFractionnementCategories getFractionnementCategories() {
//        return fractionnementCategories;
//    }
//
//    public void setFractionnementCategories(OrclassFractionnementCategories fractionnementCategories) {
//        this.fractionnementCategories = fractionnementCategories;
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
//    public OrclassIntermediaires getIntermediaires() {
//        return intermediaires;
//    }
//
//    public void setIntermediaires(OrclassIntermediaires intermediaires) {
//        this.intermediaires = intermediaires;
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
//    public OrclassDuree getDuree() {
//        return duree;
//    }
//
//    public void setDuree(OrclassDuree duree) {
//        this.duree = duree;
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
//    public OrclassRubriqueCaracteristique getRubriqueCaracteristique() {
//        return rubriqueCaracteristique;
//    }
//
//    public void setRubriqueCaracteristique(OrclassRubriqueCaracteristique rubriqueCaracteristique) {
//        this.rubriqueCaracteristique = rubriqueCaracteristique;
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
//    public OrclassApporteur getApporteur() {
//        return apporteur;
//    }
//
//    public void setApporteur(OrclassApporteur apporteur) {
//        this.apporteur = apporteur;
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
//    public List<OrclassCategories> getLisCategoriesByAutomobile() {
//        return lisCategoriesByAutomobile;
//    }
//
//    public void setLisCategoriesByAutomobile(List<OrclassCategories> lisCategoriesByAutomobile) {
//        this.lisCategoriesByAutomobile = lisCategoriesByAutomobile;
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
//    public List<OrclassVille> getListeVille() {
//        return listeVille;
//    }
//
//    public void setListeVille(List<OrclassVille> listeVille) {
//        this.listeVille = listeVille;
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
//    public List<OrclassTypeTarif> getListeTarif() {
//        return listeTarif;
//    }
//
//    public void setListeTarif(List<OrclassTypeTarif> listeTarif) {
//        this.listeTarif = listeTarif;
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
//    public List<OrclassFractionnement> getListeFractionnement() {
//        return listeFractionnement;
//    }
//
//    public void setListeFractionnement(List<OrclassFractionnement> listeFractionnement) {
//        this.listeFractionnement = listeFractionnement;
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
//    public List<OrclassDevise> getListeDevise() {
//        return listeDevise;
//    }
//
//    public void setListeDevise(List<OrclassDevise> listeDevise) {
//        this.listeDevise = listeDevise;
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
//    public List<OrclasseTimbreDimension> getListeTimbreDimension() {
//        return listeTimbreDimension;
//    }
//
//    public void setListeTimbreDimension(List<OrclasseTimbreDimension> listeTimbreDimension) {
//        this.listeTimbreDimension = listeTimbreDimension;
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
//    public List<OrclassRisqueFamille> getListeRisqueFamille() {
//        return listeRisqueFamille;
//    }
//
//    public void setListeRisqueFamille(List<OrclassRisqueFamille> listeRisqueFamille) {
//        this.listeRisqueFamille = listeRisqueFamille;
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
//    public List<OrclassPoliceCaracteristique> getFilterPoliceCaracteristique() {
//        return filterPoliceCaracteristique;
//    }
//
//    public void setFilterPoliceCaracteristique(List<OrclassPoliceCaracteristique> filterPoliceCaracteristique) {
//        this.filterPoliceCaracteristique = filterPoliceCaracteristique;
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
//    public List<OrclassGarantie> getListeRubriqueGarantie() {
//        return listeRubriqueGarantie;
//    }
//
//    public void setListeRubriqueGarantie(List<OrclassGarantie> listeRubriqueGarantie) {
//        this.listeRubriqueGarantie = listeRubriqueGarantie;
//    }
//
//    public List<OrclassCaracteristiques> getListeRubriqueCaracteristiqueControleChangeGroup() {
//        return listeRubriqueCaracteristiqueControleChangeGroup;
//    }
//
//    public void setListeRubriqueCaracteristiqueControleChangeGroup(List<OrclassCaracteristiques> listeRubriqueCaracteristiqueControleChangeGroup) {
//        this.listeRubriqueCaracteristiqueControleChangeGroup = listeRubriqueCaracteristiqueControleChangeGroup;
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
//    public List<OrclassPrestation> getListePrestation() {
//        return listePrestation;
//    }
//
//    public void setListePrestation(List<OrclassPrestation> listePrestation) {
//        this.listePrestation = listePrestation;
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
//    public List<OrclassPolice> getListeOrclassContrats() {
//        return listeOrclassContrats;
//    }
//
//    public void setListeOrclassContrats(List<OrclassPolice> listeOrclassContrats) {
//        this.listeOrclassContrats = listeOrclassContrats;
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
//    public List<OrclassGroupeGarantiePolice> getListeGroupGrantitePolice() {
//        return listeGroupGrantitePolice;
//    }
//
//    public void setListeGroupGrantitePolice(List<OrclassGroupeGarantiePolice> listeGroupGrantitePolice) {
//        this.listeGroupGrantitePolice = listeGroupGrantitePolice;
//    }
//
//    public List<OrclasseRefGroupe> getListeGrouSave() {
//        return listeGrouSave;
//    }
//
//    public void setListeGrouSave(List<OrclasseRefGroupe> listeGrouSave) {
//        this.listeGrouSave = listeGrouSave;
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
//    public OrclassRisque getRisque() {
//        return risque;
//    }
//
//    public void setRisque(OrclassRisque risque) {
//        this.risque = risque;
//    }
//
//    public OrclassRisque getRisqueselecte() {
//        return risqueselecte;
//    }
//
//    public void setRisqueselecte(OrclassRisque risqueselecte) {
//        this.risqueselecte = risqueselecte;
//    }
//
//    public OrclassRisqueFamille getRisqueFamilleSelect() {
//        return risqueFamilleSelect;
//    }
//
//    public void setRisqueFamilleSelect(OrclassRisqueFamille risqueFamilleSelect) {
//        this.risqueFamilleSelect = risqueFamilleSelect;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
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
//    public List<OrclassPoliceCaracteristique> getListePoliceCaracteristiquesSave() {
//        return listePoliceCaracteristiquesSave;
//    }
//
//    public void setListePoliceCaracteristiquesSave(List<OrclassPoliceCaracteristique> listePoliceCaracteristiquesSave) {
//        this.listePoliceCaracteristiquesSave = listePoliceCaracteristiquesSave;
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
//    public OrclassCategoriesDao getCategoriesDao() {
//        return categoriesDao;
//    }
//
//    public void setCategoriesDao(OrclassCategoriesDao categoriesDao) {
//        this.categoriesDao = categoriesDao;
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
//    public Pays getPays() {
//        return pays;
//    }
//
//    public void setPays(Pays pays) {
//        this.pays = pays;
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
//    public OrclassPolice getPolice() {
//        return police;
//    }
//
//    public void setPolice(OrclassPolice police) {
//        this.police = police;
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
//    public OrclassPolice getPoliceSelect() {
//        return policeSelect;
//    }
//
//    public void setPoliceSelect(OrclassPolice policeSelect) {
//        this.policeSelect = policeSelect;
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
//    public OrclassRubriqueGarantie getRubriqueGarantie() {
//        return rubriqueGarantie;
//    }
//
//    public void setRubriqueGarantie(OrclassRubriqueGarantie rubriqueGarantie) {
//        this.rubriqueGarantie = rubriqueGarantie;
//    }
//
//    public List<OrclassZoneTransport> getListeZoneTransport() {
//        return listeZoneTransport;
//    }
//
//    public void setListeZoneTransport(List<OrclassZoneTransport> listeZoneTransport) {
//        this.listeZoneTransport = listeZoneTransport;
//    }
//
//    public List<OrclassSousUsageTPV> getListeSousUsageTPV() {
//        return listeSousUsageTPV;
//    }
//
//    public void setListeSousUsageTPV(List<OrclassSousUsageTPV> listeSousUsageTPV) {
//        this.listeSousUsageTPV = listeSousUsageTPV;
//    }
//
//    public List<OrclassCarrosserie> getListeCarrosserie() {
//        return listeCarrosserie;
//    }
//
//    public void setListeCarrosserie(List<OrclassCarrosserie> listeCarrosserie) {
//        this.listeCarrosserie = listeCarrosserie;
//    }
//
//    public OrclassAttestationAssurance getAttestationAssurance() {
//        return attestationAssurance;
//    }
//
//    public void setAttestationAssurance(OrclassAttestationAssurance attestationAssurance) {
//        this.attestationAssurance = attestationAssurance;
//    }
//
//    public OrclassConducteur getConducteur() {
//        return conducteur;
//    }
//
//    public void setConducteur(OrclassConducteur conducteur) {
//        this.conducteur = conducteur;
//    }
//
//    public List<OrclassConducteur> getListeConducteur() {
//        return listeConducteur;
//    }
//
//    public void setListeConducteur(List<OrclassConducteur> listeConducteur) {
//        this.listeConducteur = listeConducteur;
//    }
//
//    public OrclassConducteur getConducteurAdd() {
//        return conducteurAdd;
//    }
//
//    public void setConducteurAdd(OrclassConducteur conducteurAdd) {
//        this.conducteurAdd = conducteurAdd;
//    }
//
//    public OrclassAttestationConducteur getAttestationConducteur() {
//        return attestationConducteur;
//    }
//
//    public void setAttestationConducteur(OrclassAttestationConducteur attestationConducteur) {
//        this.attestationConducteur = attestationConducteur;
//    }
//
//    public List<OrclassBonusMalus> getListeBonusMalus() {
//        return listeBonusMalus;
//    }
//
//    public void setListeBonusMalus(List<OrclassBonusMalus> listeBonusMalus) {
//        this.listeBonusMalus = listeBonusMalus;
//    }
//
//    public OrclassBonusMalus getBonusMalus() {
//        return bonusMalus;
//    }
//
//    public void setBonusMalus(OrclassBonusMalus bonusMalus) {
//        this.bonusMalus = bonusMalus;
//    }
//
//    public OrclassRubriqueGarantie getRubriqueGaranties() {
//        return rubriqueGaranties;
//    }
//
//    public void setRubriqueGaranties(OrclassRubriqueGarantie rubriqueGaranties) {
//        this.rubriqueGaranties = rubriqueGaranties;
//    }
//
//    public OrclassGarantie getGarantie() {
//        return garantie;
//    }
//
//    public void setGarantie(OrclassGarantie garantie) {
//        this.garantie = garantie;
//    }
//
//    public BigDecimal getMonatant_apporteur_commission() {
//        return monatant_apporteur_commission;
//    }
//
//    public void setMonatant_apporteur_commission(BigDecimal monatant_apporteur_commission) {
//        this.monatant_apporteur_commission = monatant_apporteur_commission;
//    }
//
//    public BigDecimal getMontant_gestion_commission() {
//        return montant_gestion_commission;
//    }
//
//    public void setMontant_gestion_commission(BigDecimal montant_gestion_commission) {
//        this.montant_gestion_commission = montant_gestion_commission;
//    }
//
//    public BigDecimal getReduction_commercial() {
//        return reduction_commercial;
//    }
//
//    public void setReduction_commercial(BigDecimal reduction_commercial) {
//        this.reduction_commercial = reduction_commercial;
//    }
//
//    public BigDecimal getReduction_bonus() {
//        return reduction_bonus;
//    }
//
//    public void setReduction_bonus(BigDecimal reduction_bonus) {
//        this.reduction_bonus = reduction_bonus;
//    }
//
//    public BigDecimal getMajoration_permis() {
//        return majoration_permis;
//    }
//
//    public void setMajoration_permis(BigDecimal majoration_permis) {
//        this.majoration_permis = majoration_permis;
//    }
//
//    public BigDecimal getMajoration_age() {
//        return majoration_age;
//    }
//
//    public void setMajoration_age(BigDecimal majoration_age) {
//        this.majoration_age = majoration_age;
//    }
//
//    public BigDecimal getReduction_tarif1() {
//        return reduction_tarif1;
//    }
//
//    public void setReduction_tarif1(BigDecimal reduction_tarif1) {
//        this.reduction_tarif1 = reduction_tarif1;
//    }
//
//    public BigDecimal getReduction_tarif2() {
//        return reduction_tarif2;
//    }
//
//    public void setReduction_tarif2(BigDecimal reduction_tarif2) {
//        this.reduction_tarif2 = reduction_tarif2;
//    }
//
//    public BigDecimal getReduction_tarif3() {
//        return reduction_tarif3;
//    }
//
//    public void setReduction_tarif3(BigDecimal reduction_tarif3) {
//        this.reduction_tarif3 = reduction_tarif3;
//    }
//
//    public BigDecimal getMatiere_inflamable() {
//        return matiere_inflamable;
//    }
//
//    public void setMatiere_inflamable(BigDecimal matiere_inflamable) {
//        this.matiere_inflamable = matiere_inflamable;
//    }
//
//    public BigDecimal getTotal_commission() {
//        return total_commission;
//    }
//
//    public void setTotal_commission(BigDecimal total_commission) {
//        this.total_commission = total_commission;
//    }
//
//    public BigDecimal getTotal_reduction_majoration_donneçspecial() {
//        return total_reduction_majoration_donneçspecial;
//    }
//
//    public void setTotal_reduction_majoration_donneçspecial(BigDecimal total_reduction_majoration_donneçspecial) {
//        this.total_reduction_majoration_donneçspecial = total_reduction_majoration_donneçspecial;
//    }
//
//    public BigDecimal getTotal_reduction() {
//        return total_reduction;
//    }
//
//    public void setTotal_reduction(BigDecimal total_reduction) {
//        this.total_reduction = total_reduction;
//    }
//
//    public List<OrclassDocumentCategories> getListeDocumentCategories() {
//        return listeDocumentCategories;
//    }
//
//    public void setListeDocumentCategories(List<OrclassDocumentCategories> listeDocumentCategories) {
//        this.listeDocumentCategories = listeDocumentCategories;
//    }
//
//    public String getNum_attestation() {
//        return num_attestation;
//    }
//
//    public void setNum_attestation(String num_attestation) {
//        this.num_attestation = num_attestation;
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
//    public List<GenreAuto> getListeGenreAuto() {
//        return listeGenreAuto;
//    }
//
//    public void setListeGenreAuto(List<GenreAuto> listeGenreAuto) {
//        this.listeGenreAuto = listeGenreAuto;
//    }
//
//    public List<UsageAuto> getListeUsageAuto() {
//        return listeUsageAuto;
//    }
//
//    public void setListeUsageAuto(List<UsageAuto> listeUsageAuto) {
//        this.listeUsageAuto = listeUsageAuto;
//    }
//
//}
