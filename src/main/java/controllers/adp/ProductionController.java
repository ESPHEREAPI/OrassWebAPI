//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers.adp;
//
//import Excell.IExcell;
//import com.mchange.v2.c3p0.impl.C3P0Defaults;
//import dao.IndicatifPaysDao;
//import dao.OrclassActiviteDao;
//import dao.OrclassAssureDao;
//import dao.OrclassAvenantDao;
//import dao.OrclassBranchesDao;
//import dao.OrclassCaracteristiquesDao;
//import dao.OrclassCategoriesDao;
//import dao.OrclassCommission_Prime_ApporteurDao;
//import dao.OrclassConventionDao;
//import dao.OrclassDetailPolicePlafondDao;
//import dao.OrclassDeviseDao;
//import dao.OrclassElement_Liste_CaracteristiqueDao;
//import dao.OrclassElt_Categorie_CompagnieDao;
//import dao.OrclassExonerationDao;
//import dao.OrclassFractionnementCategoriesDao;
//import dao.OrclassGarantieDao;
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
//import dao.OrclassPrestationDao;
//import dao.OrclassProfessionDao;
//import dao.OrclassPropositionDao;
//import dao.OrclassQualiteDao;
//import dao.OrclassQuitanceDao;
//import dao.OrclassReductionDao;
//import dao.OrclassRisqueDao;
//import dao.OrclassRisqueFamilleDao;
//import dao.OrclassRubriqueCaracteristiqueDao;
//import dao.OrclassRubriqueCategorieDao;
//import dao.OrclassRubriqueDao;
//import dao.OrclassRubriqueGarantieDao;
//import dao.OrclassSousCaracteristiqueProduitDao;
//import dao.OrclassSuspensionBrancheIntemediaireDao;
//import dao.OrclassSuspensionCategorieIntermediaireDao;
//import dao.OrclassTarifDao;
//import dao.OrclassTimbreDimensionDao;
//import dao.OrclassTypeTarifDao;
//import dao.OrclassVilleDao;
//import dao.OrclasseRefGroupeDao;
//import modele.OrclassGroupeGarantiePolice;
//import dao.PaysDao;
//import enums.Contrat;
//import enums.TypeQuittance;
//import enums.LibelleBranche;
//import enums.LibelleRisque;
//import enums.LienParente;
//import enums.ModeCalcul;
//import enums.ModeCalculDetailMaladie;
//import enums.NatureContrat;
//import enums.NatureGarantie;
//import enums.OperationsTarif;
//import enums.PoliceAutresInformation;
//import enums.Sexe;
//import enums.StatutCaracteristique;
//import enums.TypeDetailMaladie;
//import enums.TypePieces;
//import enums.VehiculeObjet;
//import exception.GlobalException;
//import exception.Success;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import javax.inject.Named;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
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
//import javax.faces.view.ViewScoped;
//import javax.servlet.http.HttpServletRequest;
//import javax.swing.ImageIcon;
//import modele.Adresse;
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
//import modele.OrclassFormule;
//import modele.OrclassFractionnement;
//import modele.OrclassFractionnementCategories;
//import modele.OrclassGarantie;
//import modele.OrclassGroupPlafondPrestation;
//import modele.OrclassGroupePolice;
//import modele.OrclassImageFamilleRisque;
//import modele.OrclassImageRisque;
//import modele.OrclassIntermediaires;
//import modele.OrclassMajorationDuree;
//import modele.OrclassPlafondMaladie;
//import modele.OrclassPolice;
////import modele.OrclassPolice;
//import modele.OrclassPoliceCaracteristique;
//import modele.OrclassPoliceCommissionApporteur;
//import modele.OrclassPoliceGarantie;
////import modele.OrclassPolicePK;
//import modele.OrclassPrestation;
//import modele.OrclassProfession;
//import modele.OrclassProposition;
//import modele.OrclassQualite;
//import modele.OrclassQuitance;
//import modele.OrclassReduction;
//import modele.OrclassRefGaranties;
//import modele.OrclassRegistreProduction;
//import modele.OrclassRisque;
//import modele.OrclassRisqueFamille;
//import modele.OrclassRubrique;
//import modele.OrclassRubriqueCaracteristique;
//import modele.OrclassRubriqueCategorie;
//import modele.OrclassRubriqueGarantie;
//import modele.OrclassRubriquePrestation;
//import modele.OrclassSousCaracteristiqueProduit;
//import modele.OrclassSuspensionBrancheIntemediaire;
//import modele.OrclassTarif;
//import modele.OrclassTarifCondition;
//import modele.OrclassTypeTarif;
//import modele.OrclassUtilisateurs;
//import modele.OrclassVille;
//import modele.OrclasseRefGroupe;
//import modele.OrclasseTimbreDimension;
//import modele.Pays;
//import net.sf.jasperreports.engine.JRException;
//import org.apache.commons.collections.comparators.ComparatorChain;
//import org.hibernate.exception.ConstraintViolationException;
//import org.primefaces.PrimeFaces;
//import org.primefaces.event.FileUploadEvent;
//import org.primefaces.event.SelectEvent;
//import org.primefaces.event.TabChangeEvent;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sante.CaracteristiquePrint;
//import sante.Contrat_Sante;
//import sante.GarantiePrint;
//import sante.GroupeSanteElement;
//import sante.ISante;
//import sante.PrestationPrints;
//import sante.SousCaracteristique;
//import utils.GlobalFonctions;
//import utils.IdleDate;
//import utils.LocaleHelper;
//import utils.RecupBundle;
//
///**
// *
// * @author hp
// */
//@Named(value = "productionController")
////@SessionScoped
//@ViewScoped
//
//public class ProductionController implements Serializable {
//
//    private static final Logger logger = LoggerFactory.getLogger(ProductionController.class);
//    OrclassRegistreProduction production;
//    @EJB
//    OrclassCategoriesDao categoriesDao;
//    OrclassCategories categories;
//    @EJB
//    OrclassSousCaracteristiqueProduitDao scprdDao;
//
//    @EJB
//    OrclassActiviteDao activiteDao;
//    OrclassActivite activite;
//    @EJB
//    OrclassQualiteDao qualiteDao;
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
//    OrclassPlafondMaladieDao plafondMaladieDao;
//    OrclassPlafondMaladie plafondMaladie;
//    @EJB
//    OrclassRubriqueCategorieDao rubriqueCategorieDao;
//    OrclassRubriqueCategorie rubriqueCategorie;
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
//    OrclassGroupePoliceDao groupePoliceDao;
//    @EJB
//    OrclassTarifDao tarifDao;
//    OrclassTarif tarif;
//    @EJB
//    OrclassRubriqueDao rubriqueDao;
//    OrclassFormule formule;
//    @EJB
//    OrclassCaracteristiquesDao caracteristiquesDao;
////    @EJB
////    OrclassTarifDao tarifConditionDao;
//
//    /**
//     * Creates a new instance of ProductionController
//     */
//    @EJB
//    OrclassAvenantDao avenantDao;
//    OrclassAvenant avenant = null;
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
//    private List<OrclassPrestation> filterPrestation;
//    private List<OrclassPoliceCaracteristique> filterPoliceCaracteristique;
////    private List<OrclassPrestation> filterPrestation;
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
//    List<Contrat_Sante> colContrat_Sante = new ArrayList<>();
//    private List<OrclassIntermediaires> listeIntermediaires = new ArrayList<>();
//    private List<OrclassImageRisque> listeImageRisques = new ArrayList<>();
//    private List<OrclassImageFamilleRisque> listeImageFamilleRisque = new ArrayList<>();
//    private List<OrclassGarantie> listeGarantieHaveForfairtairOrGratuit = new ArrayList<>();
//    private List<OrclassGroupeGarantiePolice> listeGroupGrantitePolice = new ArrayList<>();
//    private List<OrclasseRefGroupe> listeGrouSave = new ArrayList<>();
//    private OrclassEntreprises entreprise;
//    private OrclassUtilisateurs user;
//    private Boolean suspensionBrance = Boolean.FALSE;
//    private OrclassPolice police;
//    private OrclassPolice policeSelect;
//    private BigInteger numero_police;
//    private String summary = "", msgdetail = "";
//    private OrclassPoliceCommissionApporteur policeCommissionApporteur;
//    OrclassPoliceGarantie policeGarantieAffiche;
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
//    List<GroupeSanteElement> listeGroupeSanteElements = new ArrayList<>();
//    Map<Integer, Object> objetElementGroupePrestation = new HashMap<Integer, Object>();
//    OrclassRisque risqueselecte = new OrclassRisque();
//    OrclassRisqueFamille risqueFamilleSelect = new OrclassRisqueFamille();
//    private int index = 0;
//    private BigDecimal tva = BigDecimal.valueOf(19.25);
//    List<OrclassPoliceCaracteristique> listePoliceCaracteristiquesSave = new ArrayList<>();
//    private List<OrclassRefGaranties> listeGarantieNonEditable = new ArrayList<>();
//
//    /*Orclass
//    valeur pour afficher les tabView 
//     */
//    private Boolean tabShowGroup = Boolean.FALSE, tabShowFamille = Boolean.FALSE, tabShowIndividuel = Boolean.FALSE;
//    private OrclassAssure assure;
//    private GroupeSanteElement groupeSanteElement;
//    BigDecimal totalReduction = BigDecimal.ZERO;
//    private int nombre_assure = 0, nombre_membre = 0, nombre_enfant = 0, total_personne = 0;
//
//    public ProductionController() {
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
//
//    }
//
//    public void statiqueAssureAndMembre() {
//        if ((numero_police != null && numero_police.intValue() != 0) && police.getId() == null) {
//            // ici il s agit des enregistrements image
//            police.setNumero_police(numero_police);
//            nombre_assure = imageRisqueDao.nombreAssure(police, entreprise, refGroupeSelect == null ? new OrclasseRefGroupe() : refGroupeSelect).intValue();
//            nombre_membre = imageFamilleRisqueDao.nombreMembre(police, entreprise, refGroupeSelect == null ? new OrclasseRefGroupe() : refGroupeSelect).intValue();
//            nombre_enfant = imageFamilleRisqueDao.nombreEnfant(police, entreprise, refGroupeSelect == null ? new OrclasseRefGroupe() : refGroupeSelect).intValue();
//            total_personne = nombre_assure + nombre_membre + nombre_enfant;
//        } else if (police != null && police.getId() != null && avenant == null) {
//            nombre_assure = risqueDao.nombreAssure(police, entreprise, refGroupeSelect == null ? new OrclasseRefGroupe() : refGroupeSelect).intValue();
//            nombre_enfant = risqueFamilleDao.nombreEnfant(police, entreprise, refGroupeSelect == null ? new OrclasseRefGroupe() : refGroupeSelect).intValue();
//            nombre_membre = risqueFamilleDao.nombreMembre(police, entreprise, refGroupeSelect == null ? new OrclasseRefGroupe() : refGroupeSelect).intValue();
//            total_personne = nombre_assure + nombre_membre + nombre_enfant;
//        } else if (avenant != null && avenant.getId() != null) {
//            nombre_assure = listeRisque.size();
//            nombre_membre = controlleRisqueFamille(LienParente.conjoint);
//            nombre_enfant = controlleRisqueFamille(LienParente.enfant);
//            total_personne = nombre_assure + nombre_membre + nombre_enfant;
//        }
//    }
//
//    public int controlleRisqueFamille(LienParente lienParente) {
//        List<OrclassRisqueFamille> listeRisqueFamilleByAvenant = new ArrayList<>();
//        List<OrclassRisqueFamille> allListeFamilleByGroupe = new ArrayList<>();
//        if (refGroupeSelect != null && refGroupeSelect.getId() != null) {
//            if (lienParente.equals(LienParente.conjoint) || lienParente.equals(LienParente.ascendant)) {
//                allListeFamilleByGroupe = risqueFamilleDao.listeMembre(police, entreprise, refGroupeSelect);
//            } else {
//                allListeFamilleByGroupe = risqueFamilleDao.listeEnfant(police, entreprise, refGroupeSelect);
//            }
//
//        }
//        if (allListeFamilleByGroupe.isEmpty()) {
//            return 0;
//        }
//
//        OrclassRisqueFamille rf = null;
//        Iterator<OrclassRisqueFamille> irf = allListeFamilleByGroupe.iterator();
////        if (Objects.equals(tabShowFamille, Boolean.TRUE)) {
//        while (irf.hasNext()) {
//            rf = irf.next();
//            if (Objects.equals(rf.getRetire_de_la_police(), Boolean.TRUE)) {
////                    listeRisqueFamille.remove(risqueFamilleDao.find(rf.getCodeid_retirer()));
//                listeRisqueFamilleByAvenant.add(risqueFamilleDao.find(rf.getCodeid_retirer()));
//                listeRisqueFamilleByAvenant.add(rf);
//                continue;
//            }
//        }
//        if (!listeRisqueFamilleByAvenant.isEmpty()) {
//            allListeFamilleByGroupe.removeAll(listeRisqueFamilleByAvenant);
//        }
//        return allListeFamilleByGroupe.size();
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
//            if (!listeRubriqueGarantie.isEmpty() && listePoliceGarantie.isEmpty()) {
//                if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//
////                    listeRubriqueGarantie = ;
////                    for (OrclassGarantie g : rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieExistePoliceGarantieOligatoire(police.getIdTypeTarif(), categories, entreprise)) {
////                        policeGarantie = new OrclassPoliceGarantie();
////                        policeGarantie.setIdGarantie(g);
////                        System.out.println("Garanties avec option Empty " + g.getIdRefGaranties().getLibelle());
////                        System.out.println("Etat affiche: " + policeGarantie.getAfficher());
////                        policeGarantie.setIdGroup(refGroupeSelectForGarantie);
////                        listePoliceGarantie.add(policeGarantie);
////
////                    }
//                    for (OrclassGarantie rg : rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorie(police.getIdTypeTarif(), rubriqueCategorie, entreprise)) {
//                        if (rg.getNatureGarantie().equals(NatureGarantie.obligatoire)) {
//                            policeGarantie = new OrclassPoliceGarantie();
//                            policeGarantie.setIdGarantie(rg);
//                            if (rg.getModeCalcul().equals(ModeCalcul.Automatique)) {
//                                policeGarantie.setEditer(Boolean.FALSE);
//                                policeGarantie = this.calculPrimeAutomatiqueGarantie(policeGarantie);
////                                    policeGarantie.setPrime(this.calculPrimeAutomatiqueGarantie(rg));
////                                    policeGarantie.setProrata(policeGarantie.getPrime());
//                            } else if (rg.getModeCalcul().equals(ModeCalcul.manuel)) {
//                                policeGarantie.setEditer(Boolean.TRUE);
//                            }
//                            System.out.println("id group garantie " + refGroupeSelectForGarantie.getId());
//                            policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                            listePoliceGarantie.add(policeGarantie);
//
//                        }
//
//                    }
//                    policeGarantie = new OrclassPoliceGarantie();
//                    policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                    listePoliceGarantie.add(policeGarantie);
//                    this.reverseListeGarantie();
//                    this.updateDataTablePoliceGaranties();
//                    this.updateTableRubriqueGarantie();
//                }
//                return;
//            }
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
//                    if (avenant == null || avenant.getId() == null) {
//                        totalPrime = totalPrime.add(pg.getPrime());
//                    } else if (avenant != null && avenant != null && Objects.equals(pg.getAjouter(), Boolean.TRUE)) {
//                        totalPrime = totalPrime.add(pg.getPrime());
//                    }
//
//                }
//                if (pg.getProrata() != null && pg.getProrata().intValue() != 0) {
//                    if (avenant == null || avenant.getId() == null) {
//                        totalProrata = totalProrata.add(pg.getProrata());
//                    } else if (avenant != null && avenant != null && Objects.equals(pg.getAjouter(), Boolean.TRUE)) {
//                        totalProrata = totalProrata.add(pg.getProrata());
//                    }
//
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
////                if (listePoliceCaracteristiquesSave.isEmpty()) {
////                    if (listePoliceCaracteristique != null && !listePoliceCaracteristique.isEmpty()) {
////                        listePoliceCaracteristique.remove(0);
////                        listePoliceCaracteristiquesSave.addAll(listePoliceCaracteristique);
////                        listePoliceCaracteristique.clear();
////                        for (OrclassPoliceCaracteristique pc : listePoliceCaracteristiquesSave) {
//////                            if (pc.getIdGroup() == null) {
//////                                listePoliceCaracteristique.add(pc);
//////                                continue;
//////
//////                            }
////                            if (pc.getIdGroup() != null && pc.getIdGroup().getId() != null) {
////                                pc.setAfficher(Boolean.TRUE);
////                                pc.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
////                                //recuperation permettra de ne pas afficher une ligne deux fois
////                                listeCaracteristiques.add(pc.getIdCaracteristiques());
////                                listePoliceCaracteristique.add(pc);
////                            }
////
////                        }
////                    }
////                } else {
////                    if (listePoliceCaracteristique != null && !listePoliceCaracteristique.isEmpty()) {
////                        listePoliceCaracteristique.remove(0);
////                        listePoliceCaracteristiquesSave.addAll(listePoliceCaracteristique);
////                        listePoliceCaracteristique.clear();
////                        for (OrclassPoliceCaracteristique pc : listePoliceCaracteristiquesSave) {
//////                            if (pc.getIdGroup() == null) {
//////                                listePoliceCaracteristique.add(pc);
//////                                continue;
//////
//////                            }
////
////                            if (pc.getIdGroup().equals(refGroupeSelectForCaracteristiqueAndGarantie) == Boolean.TRUE) {
////
////                                //recuperation permettra de ne pas afficher une ligne deux fois
//////                                listeCaracteristiques.add(pc.getIdCaracteristiques());
////                                policeCaracteristique = new OrclassPoliceCaracteristique();
////                                policeCaracteristique.setIdCaracteristiques(pc.getIdCaracteristiques());
////                                if (pc.getIdCaracteristiques().getUnite_Caracteristique() != null && pc.getIdCaracteristiques().getUnite_Caracteristique().getLibelle() != null) {
////                                    policeCaracteristique.getIdCaracteristiques().setLibelle(pc.getIdCaracteristiques().getLibelle() + " " + pc.getIdCaracteristiques().getUnite_Caracteristique().getLibelle());
////                                }
////                                policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
////                                policeCaracteristique.setAfficher(Boolean.TRUE);
////                                listePoliceCaracteristique.add(policeCaracteristique);
////                            } else {
////                                pc.setAfficher(Boolean.FALSE);
////                                //recuperation permettra de ne pas afficher une ligne deux fois
////
////                                listePoliceCaracteristiques.add(pc);
////                            }
////
////                        }
////                        if (listePoliceCaracteristique.isEmpty()) {
////                            // on recupere tous les caracteristique obligateur pour ce nlouveau group
////
////                            for (OrclassCaracteristiques pr : rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, categories, rubriqueCategorie.getIdRubrique(), StatutCaracteristique.obligatoire)) {
////                                policeCaracteristique = new OrclassPoliceCaracteristique();
////                                policeCaracteristique.setIdCaracteristiques(pr);
////                                if (pr.getUnite_Caracteristique() != null && pr.getUnite_Caracteristique().getLibelle() != null) {
////                                    policeCaracteristique.getIdCaracteristiques().setLibelle(pr.getLibelle() + " " + pr.getUnite_Caracteristique().getLibelle());
////                                }
////                                policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
////                                listePoliceCaracteristique.add(policeCaracteristique);
////
////                            }
////                            if (!listePoliceCaracteristiques.isEmpty()) {
////                                listePoliceCaracteristique.addAll(listePoliceCaracteristiques);
////                            }
////                        }
////
////                    }
////                }
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
//                for (OrclassCaracteristiques caract : rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagnies(entreprise, categories, StatutCaracteristique.obligatoire)) {
//                    policeCaracteristique = new OrclassPoliceCaracteristique();
//                    policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
//                    policeCaracteristique.setIdCaracteristiques(caract);
//                    policeCaracteristique.setIdPolice(police);
//                    policeCaracteristique.setIdCategories(categories);
//                    listePoliceCaracteristique.add(policeCaracteristique);
//                }
//                policeCaracteristique = new OrclassPoliceCaracteristique();
//                policeCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
//                listePoliceCaracteristique.add(policeCaracteristique);
//                this.reverseListeCaracteristique();
//                listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, refGroupeSelectForCaracteristiqueAndGarantie);
//
//                this.updateDataTablePoliceCarzacteristique();
//                this.updateDataTableRubriqueCaracteristique();
//                return;
////                listePoliceCaracteristique = new ArrayList<>();
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
//    public void chargeListePlafondAndPrestationByGroup() {
//        List<OrclassDetailPolicePlafond> listeDetailPolicePlafonds = new ArrayList<>();
//        OrclassGroupPlafondPrestation gpps = null;
//        OrclasseRefGroupe group = null;
//        OrclassDetailPolicePlafond detailPolicePlafond = null;
//        groupeSanteElement = new GroupeSanteElement();
//        GroupeSanteElement groupeSanteElementLocal = null;
//
//        int index = 0;
//        int i = 0;
//        if (police == null || police.getId() == null) {
//
//            if (refGroupeSelectForPrestation != null && refGroupeSelectForPrestation.getId() != null) {
//
//                if (listeDetailPolicePlafond != null && !listeDetailPolicePlafond.isEmpty()) {
//                    listeDetailPolicePlafonds.addAll(listeDetailPolicePlafond);
//                    listeDetailPolicePlafond.clear();
//                    for (OrclassDetailPolicePlafond dp : listeDetailPolicePlafonds) {
//                        if (dp.getIdGroup() == null) {
//                            listeDetailPolicePlafond.add(dp);
//                            continue;
//                        }
//                        if (dp.getIdGroup().equals(refGroupeSelectForPrestation) == Boolean.TRUE) {
//                            dp.setAfficher(Boolean.TRUE);
////                             dp.setPlafondMaladie(plafondMaladie);
//
//                            listeDetailPolicePlafond.add(dp);
//
//                        } else {
//                            dp.setAfficher(Boolean.FALSE);
//                            listeDetailPolicePlafond.add(dp);
//                        }
//                    }
//                } else {
//                    listeDetailPolicePlafond.clear();
//                }
//
////                    //la police existe 
////                    OrclassPlafondMaladie pm = policePlafondDao.chargePlafonMaladie(police, entreprise, refGroupeSelectForPrestation);
////                    if (pm != null && pm.getId() != null) {
////                        this.setPlafondMaladie(pm);
////                    }
////
////                    listeDetailPolicePlafond = detailPolicePlafondDao.listeDetailPlafond(entreprise, police, refGroupeSelectForPrestation);
////                    if (!listeDetailPolicePlafond.isEmpty()) {
////                        OrclassDetailPolicePlafond dpp = (OrclassDetailPolicePlafond) listeDetailPolicePlafond.toArray()[0];
////                        this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, dpp.getIdRubrique(), entreprise));
////                    }
//            }
//
//        } else if (police != null && police.getId() != null && refGroupeSelectForPrestation != null && refGroupeSelectForPrestation.getId() != null) {
////            if (!listeDetailPolicePlafond.isEmpty()) {
////                listeDetailPolicePlafonds.clear();
////                for (OrclassDetailPolicePlafond dpps : listeDetailPolicePlafond) {
////                    if (dpps.getId()==null && dpps.getIdGroup()!=null && dpps.getIdGroup().getId()!=null && dpps.getIdPrestation()!=null && dpps.getIdPrestation().getId()!=null) {
////                        listeDetailPolicePlafonds.add(dpps);
////                    }
////                }
////            }
//            listeDetailPolicePlafond = detailPolicePlafondDao.listeDetailPlafond(entreprise, police, refGroupeSelectForPrestation);
//            if (!listeDetailPolicePlafond.isEmpty()) {
//                detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                this.setPlafondMaladie(detailPolicePlafond.getIdPolicePlafond().getIdPlafondMaladie());
//                listePlafondMaladie.clear();
//                listePlafondMaladie.add(plafondMaladie);
//                this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//
//            }
//
//            listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//            listePrestation = prestationDao.listePrestationByCompagnie(entreprise, police, refGroupeSelectForPrestation);
//
//            this.updateTablePrestation();
//            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                detailPolicePlafond = new OrclassDetailPolicePlafond();
//                detailPolicePlafond.setPlafondMaladie(plafondMaladie);
//                detailPolicePlafond.setIdRubrique(this.rubriqueCategorie.getIdRubrique());
//                detailPolicePlafond.setIdGroup(refGroupeSelectForPrestation);
//                listeDetailPolicePlafond.add(detailPolicePlafond);
//                this.reverseListe();
//
//            }
//
//        }
//        this.updateTabledetailPolicePlafond();
//
//    }
//
////    public void showDetailPolicePrestation(OrclassDetailPolicePlafond item) {
////        if (detailPolicePlafond != null && detailPolicePlafond.getIdPrestation() != null && detailPolicePlafond.getIdPrestation().getId() != null) {
////
////        }
////    }
//    public void haveIndexForListe(int index) {
//        this.index = index;
//    }
//
//    public void addDetailPrestation() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        OrclassRisque r = null;
//        OrclassDetailPolicePlafond newDetailPolicePlafond = null;
//        int index = 0;
//        if (detailPolicePlafond != null && detailPolicePlafond.getModeCalculDetailMaladie() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "VALUE IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
////        if (risqueselecte != null && risqueselecte.getDateNaissance() == null) {
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "DATE OF BIRTH IS EMPTY", "PLEASE TRY AGAINST"));
////            return;
////        }
////        if (risqueselecte != null && risqueselecte.getLibelle() == null) {
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "NAME IS EMPTY", "PLEASE TRY AGAINST"));
////            return;
////        }
//        if (Objects.equals(tabShowGroup, Boolean.FALSE)) {
//            newDetailPolicePlafond = detailPolicePlafondDao.detailPolicePlafondHaveAvenantForPrestation(entreprise, police, detailPolicePlafond.getIdPrestation());
//        } else {
//            if (refGroupeSelectForPrestation != null && refGroupeSelectForPrestation.getId() != null) {
//                newDetailPolicePlafond = detailPolicePlafondDao.detailPolicePlafondHaveAvenantForPrestation(entreprise, police, detailPolicePlafond.getIdPrestation(), refGroupeSelectForPrestation);
//            }
//
//        }
//        if (newDetailPolicePlafond == null) {
//            newDetailPolicePlafond = new OrclassDetailPolicePlafond();
//            newDetailPolicePlafond.setIdEntreprise(entreprise);
//            newDetailPolicePlafond.setBarem(detailPolicePlafond.getBarem());
//            newDetailPolicePlafond.setCode_ss(detailPolicePlafond.getCode_ss());
//            newDetailPolicePlafond.setForfait(detailPolicePlafond.getForfait());
//            newDetailPolicePlafond.setIdPolicePlafond(policePlafondDao.chargePolicePlafonMaladie(police, entreprise));
//            newDetailPolicePlafond.setIdPrestation(detailPolicePlafond.getIdPrestation());
//            newDetailPolicePlafond.setIdRubrique(this.getRubriqueCategorie().getIdRubrique());
//            newDetailPolicePlafond.setModeCalculDetailMaladie(detailPolicePlafond.getModeCalculDetailMaladie());
//            newDetailPolicePlafond.setPlafond(detailPolicePlafond.getPlafond());
//            newDetailPolicePlafond.setTaux(detailPolicePlafond.getTaux());
//            newDetailPolicePlafond.setTypeDetailMaladie(detailPolicePlafond.getTypeDetailMaladie());
//            if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                newDetailPolicePlafond.setIdGroup(refGroupeSelectForPrestation);
//            }
//            detailPolicePlafondDao.create(newDetailPolicePlafond);
//
//        }
//        if (newDetailPolicePlafond != null && newDetailPolicePlafond.getId() != null) {
////            index = listeDetailPolicePlafond.indexOf(detailPolicePlafond);
//            listeDetailPolicePlafond.set(this.index, newDetailPolicePlafond);
////            detailPolicePlafond = new OrclassDetailPolicePlafond();
////            listeDetailPolicePlafond.add(detailPolicePlafond);
////            this.reverseListe();
//            this.updateTabledetailPolicePlafond();
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//
//        } else {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "" + exception.Error.INSERT_ERROR.name(), "" + exception.Error.OPERATION_FAILED.name()));
//
//        }
//
////            this.setRefGroupeSelect(r.getIdGroup());
////        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//
//    public void addPoliceCaracteristique() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        OrclassRisque r = null;
//        OrclassPoliceCaracteristique newPoliceCaracteristique = null;
//        int index = 0;
//        if (policeCaracteristique != null && (policeCaracteristique.getIdCaracteristiques() == null || policeCaracteristique.getIdCaracteristiques().getId() == null)) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "VALUE IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
////        if (risqueselecte != null && risqueselecte.getDateNaissance() == null) {
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "DATE OF BIRTH IS EMPTY", "PLEASE TRY AGAINST"));
////            return;
////        }
////        if (risqueselecte != null && risqueselecte.getLibelle() == null) {
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "NAME IS EMPTY", "PLEASE TRY AGAINST"));
////            return;
////        }
//        if (Objects.equals(tabShowGroup, Boolean.FALSE)) {
//            newPoliceCaracteristique = policeCaracteristiqueDao.findKey(entreprise, police, policeCaracteristique.getIdCaracteristiques());
//        } else {
//            if (refGroupeSelectForCaracteristiqueAndGarantie != null && refGroupeSelectForCaracteristiqueAndGarantie.getId() != null) {
//                newPoliceCaracteristique = policeCaracteristiqueDao.findKey(entreprise, police, policeCaracteristique.getIdCaracteristiques(), refGroupeSelectForCaracteristiqueAndGarantie);
//            }
//
//        }
//        if (newPoliceCaracteristique == null) {
//            newPoliceCaracteristique = new OrclassPoliceCaracteristique();
//
//            newPoliceCaracteristique.setIdCaracteristiques(policeCaracteristique.getIdCaracteristiques());
//            newPoliceCaracteristique.setIdPolice(police);
//            newPoliceCaracteristique.setIdEntreprise(entreprise);
//            if (policeCaracteristique.getDateValeur() != null) {
//                newPoliceCaracteristique.setDateValeur(policeCaracteristique.getDateValeur());
//            }
//            if (policeCaracteristique.getValeurBoolean() != null) {
//                newPoliceCaracteristique.setValeurBoolean(policeCaracteristique.getValeurBoolean());
//            }
//            if (policeCaracteristique.getValeurCode() != null) {
//                newPoliceCaracteristique.setValeurCode(policeCaracteristique.getValeurCode());
//            }
//            if (policeCaracteristique.getValeurCaracteristique() != null) {
//                newPoliceCaracteristique.setValeurCaracteristique(policeCaracteristique.getValeurCaracteristique());
//            }
//            if (policeCaracteristique.getValeurTexte() != null) {
//                newPoliceCaracteristique.setValeurTexte(policeCaracteristique.getValeurTexte());
//            }
//            if (policeCaracteristique.getValeurNumerique() != null && policeCaracteristique.getValeurNumerique() != null && policeCaracteristique.getValeurNumerique().intValue() != 0) {
//                newPoliceCaracteristique.setValeurNumerique(policeCaracteristique.getValeurNumerique());
//            }
//            newPoliceCaracteristique.setIdCategories(police.getIdCategories());
//
//            if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                newPoliceCaracteristique.setIdGroup(refGroupeSelectForCaracteristiqueAndGarantie);
//            }
//            policeCaracteristiqueDao.create(newPoliceCaracteristique);
//
//        }
//        if (newPoliceCaracteristique != null && newPoliceCaracteristique.getId() != null) {
////            index = listePoliceCaracteristique.indexOf(policeCaracteristique);
//            listePoliceCaracteristique.set(this.index, newPoliceCaracteristique);
////            policeCaracteristique = new OrclassPoliceCaracteristique();
////            listePoliceCaracteristique.add(policeCaracteristique);
////            this.reverseListeCaracteristique();
//            this.updateDataTablePoliceCarzacteristique();
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//
//        } else {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "" + exception.Error.INSERT_ERROR.name(), "" + exception.Error.OPERATION_FAILED.name()));
//
//        }
//    }
//
//    public void deletePoliceCaracteristique() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        OrclassRisque r = null;
//        OrclassPoliceCaracteristique newPoliceCaracteristique = null;
//        int index = 0;
//        if (policeCaracteristique != null && policeCaracteristique.getId() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "VERIFY ENTITY... KEY IS NULL", "PLEASE TRY AGAINST"));
//            return;
//        }
//
//        policeCaracteristiqueDao.delete(policeCaracteristique);
//        if (listePoliceCaracteristique.contains(policeCaracteristique) == Boolean.TRUE) {
//            listePoliceCaracteristique.remove(policeCaracteristique);
//        }
//        this.updateDataTablePoliceCarzacteristique();
//        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//
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
//    public void deletePoliceDetailPrestation() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        OrclassRisque r = null;
//        OrclassPoliceCaracteristique newPoliceCaracteristique = null;
//        int index = 0;
//        if (detailPolicePlafond != null && detailPolicePlafond.getId() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "VERIFY ENTITY... KEY IS NULL", "PLEASE TRY AGAINST"));
//            return;
//        }
//
//        detailPolicePlafondDao.delete(detailPolicePlafond);
//        if (listeDetailPolicePlafond.contains(detailPolicePlafond) == Boolean.TRUE) {
//            listeDetailPolicePlafond.remove(detailPolicePlafond);
//        }
//
//        this.updateDataTablePoliceCarzacteristique();
//        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//
//    }
//
//    public void addPoliceGarantie() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        OrclassRisque r = null;
//        OrclassPoliceGarantie newPoliceGarantie = null;
//        int index = 0;
//        if (policeGarantie != null && (policeGarantie.getIdGarantie() == null || policeGarantie.getIdGarantie().getId() == null)) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "VALUE IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
////        if (risqueselecte != null && risqueselecte.getDateNaissance() == null) {
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "DATE OF BIRTH IS EMPTY", "PLEASE TRY AGAINST"));
////            return;
////        }
////        if (risqueselecte != null && risqueselecte.getLibelle() == null) {
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "NAME IS EMPTY", "PLEASE TRY AGAINST"));
////            return;
////        }
//        if (Objects.equals(tabShowGroup, Boolean.FALSE)) {
//            newPoliceGarantie = policeGarantieDao.OrclassPoliceGarantieByLibelle(entreprise, police, policeGarantie.getIdGarantie());
//        } else {
//            if (refGroupeSelectForGarantie != null && refGroupeSelectForGarantie.getId() != null) {
//                newPoliceGarantie = policeGarantieDao.OrclassPoliceGarantieByLibelle(entreprise, police, policeGarantie.getIdGarantie(), refGroupeSelectForGarantie);
//            }
//
//        }
//        if (newPoliceGarantie == null) {
//            newPoliceGarantie = new OrclassPoliceGarantie();
//            newPoliceGarantie.setCapital(policeGarantie.getCapital());
//            newPoliceGarantie.setIdEntreprise(entreprise);
//            newPoliceGarantie.setIdGarantie(policeGarantie.getIdGarantie());
//            newPoliceGarantie.setIdPolice(police);
//            newPoliceGarantie.setPourcentage(policeGarantie.getPourcentage());
//            newPoliceGarantie.setPrime(policeGarantie.getPrime());
//            newPoliceGarantie.setProrata(policeGarantie.getProrata());
//            newPoliceGarantie.setTaux(policeGarantie.getTaux());
//            if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                newPoliceGarantie.setIdGroup(refGroupeSelectForGarantie);
//            }
//            policeGarantieDao.create(newPoliceGarantie);
//
//        }
//        if (newPoliceGarantie != null && newPoliceGarantie.getId() != null) {
////            index = listePoliceGarantie.indexOf(policeGarantie);
//            listePoliceGarantie.set(this.index, newPoliceGarantie);
////            policeGarantie = new OrclassPoliceGarantie();
////            listePoliceGarantie.add(policeGarantie);
////            this.reverseListeGarantie();
//            this.updateDataTablePoliceGaranties();
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//
//        } else {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "" + exception.Error.INSERT_ERROR.name(), "" + exception.Error.OPERATION_FAILED.name()));
//
//        }
//
////            this.setRefGroupeSelect(r.getIdGroup());
////        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//    }
//
//    public void chargeGroupGarantie(OrclassPoliceGarantie pg) {
//        List<OrclassGroupeGarantiePolice> listeGroupGrantitePoliceAll = new ArrayList<>();
//        OrclassGroupeGarantiePolice ggp = null;
//        listeGroupGrantitePoliceAll.clear();
//
//        int index = 0;
//        if (pg != null && pg.getId() != null) {
//            if (police != null && police.getId() != null && groupeGarantiePoliceDao.finKey(entreprise, police, refGroupe, pg.getIdGarantie()) == null) {
//                return;
//            }
//            if (pg.getListeGroupGrantitePolice().isEmpty()) {
//                this.setPoliceGarantieAffiche(pg);
//                for (OrclasseRefGroupe gr : listeRefGroup) {
//                    ggp = new OrclassGroupeGarantiePolice();
//                    ggp.getIdGroupePolice().setIdRefGroupe(gr);
//                    ggp.setIdEntreprise(entreprise);
//                    ggp.setPrime(BigDecimal.ZERO);
//                    ggp.setPoliceGarantie(pg);
//                    listeGroupGrantitePoliceAll.add(ggp);
//                }
//            } else {
//                listeGroupGrantitePoliceAll.addAll(pg.getListeGroupGrantitePolice());
//            }
//
//            if (!listeGroupGrantitePoliceAll.isEmpty()) {
//                listeGroupGrantitePolice.addAll(listeGroupGrantitePoliceAll);
//                PrimeFaces.current().executeScript("PF('groupPoliceGarantieDialog').hide()");
//            }
//        }
//    }
//
//    public void chargedetailGroupeGarantiePolice() {
//        OrclassGroupeGarantiePolice policeGarantie = null;
//        OrclassPolice pg = null;
//        BigDecimal montantPrime = BigDecimal.ZERO;
//        int index = 0;
//        if (!listeGroupGrantitePolice.isEmpty()) {
//            for (OrclassGroupeGarantiePolice ggps : listeGroupGrantitePolice) {
//                if (ggps.getPrime() != null && ggps.getPrime().intValue() != 0) {
//                    montantPrime = montantPrime.add(ggps.getPrime());
//                }
//            }
//            policeGarantie = (OrclassGroupeGarantiePolice) listeGroupGrantitePolice.toArray()[0];
//            if (policeGarantie != null && policeGarantie.getId() != null) {
//                index = listePoliceGarantie.indexOf(policeGarantie.getPoliceGarantie());
//                listePoliceGarantie.get(index).setListeGroupGrantitePolice(listeGroupGrantitePolice);
//                listePoliceGarantie.get(index).setPrime(montantPrime);
////             this.calculTotalProrata();
//                this.caculTotalPrime();
//                this.updateDataTablePoliceGaranties();
//            }
//        }
//    }
//
//    public void chargeRisqueByGroup() {
//        OrclassImageRisque r = null;
//        List<OrclassImageRisque> listImageRisque = new ArrayList<>();
//        listeImageRisques.clear();
//        if (refGroupeSelect != null && refGroupeSelect.getId() != null && (police == null || police.getId() == null)) {
//            listeImageRisques = imageRisqueDao.listeImageRisqueFamille(entreprise, refGroupeSelect, numero_police, assure.getRaison_social().toUpperCase());
//            if (listeImageRisques.isEmpty()) {
//                r = new OrclassImageRisque();
//                r.setIdGroup(refGroupeSelect);
//                listeImageRisques.add(r);
//                this.updateTableImageRisque();
//            } else {
//                r = new OrclassImageRisque();
//                r.setIdGroup(refGroupeSelect);
//                listeImageRisques.add(r);
//                reverseListeImageRique();
//                this.updateTableImageRisque();
//            }
//        } else if (refGroupeSelect != null && refGroupeSelect.getId() != null && (police != null && police.getId() != null)) {
//            listeRisque = risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police);
//            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                OrclassRisque rq = new OrclassRisque();
//                rq.setIdGroup(refGroupeSelect);
//                rq.setDateCreation(new Date());
//                rq.setDateEntree(new Date());
//                rq.setIdEntreprise(entreprise);
//                listeRisque.add(rq);
//                this.reverseListeRique();
//
//            }
//            this.controlleElementHaveAvenant();
//            this.updateTableRisque();
//        }
//        this.statiqueAssureAndMembre();
//
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//    }
//
//    public void showDetailImageFamilleRisque(OrclassImageFamilleRisque item) {
//
//        this.setImageFamilleRisque(item);
//
//    }
//
//    public void showDetailImageFamilleRisque(OrclassImageFamilleRisque item, int index) {
//        if (item.getMatricule() == null || item.getNom_membre() == null) {
//            this.setImageFamilleRisque(listeImageFamilleRisque.get(index));
//        } else {
//            this.setImageFamilleRisque(item);
//        }
////        if (imageRisque==null || im) {
//
////        }
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
//            if (!listeRisque.isEmpty()) {
//                this.setRisque(listeRisque.get(0));
//                this.chargeImageRisqueFamilleByImageRisque();
//
//            }
//
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
//            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                risqueFamille = new OrclassRisqueFamille();
//                listeRisqueFamille.add(risqueFamille);
//                this.reverseListeFamilleRique();
//
//            }
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
//    public void addImageRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        if (imageRisque != null && imageRisque.getMatricule() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "MATRICULE IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (imageRisque != null && imageRisque.getDateNaissance() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "DATE OF BIRTH IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (imageRisque != null && imageRisque.getLibelle() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "NAME IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (imageRisqueDao.finkey(entreprise, refGroupeSelect, imageRisque.getLibelle(), imageRisque.getMatricule(), imageRisque.getDateNaissance(), numero_police) == null) {
//            imageRisque.setIdEntreprise(entreprise);
//            imageRisque.setIdCategories(categories);
//            imageRisque.setIdIntermediaire(intermediaires);
//            imageRisque.setIdUtilisateur(user);
//            imageRisque.setNumero_polic(numero_police);
//            imageRisque.setSouscripteur(assure.getRaison_social().toUpperCase());
//            imageRisqueDao.create(imageRisque);
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        listeImageRisques = imageRisqueDao.listeImageRisqueFamille(entreprise, refGroupeSelect, numero_police, assure.getRaison_social().toUpperCase());
//        imageRisque = new OrclassImageRisque();
//        imageRisque.setIdGroup(refGroupeSelect);
//        listeImageRisques.add(imageRisque);
//        this.reverseListeImageRique();
//        this.updateTableImageRisque();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//    }
//
//    public void updateImageRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//
//        if (imageRisque != null && imageRisque.getId() != null) {
//
//            imageRisqueDao.update(imageRisque);
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.UPDATE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        listeImageRisques = imageRisqueDao.listeImageRisqueFamille(entreprise, refGroupeSelect, numero_police, assure.getRaison_social());
//        imageRisque = new OrclassImageRisque();
//        imageRisque.setIdGroup(refGroupeSelect);
//        listeImageRisques.add(imageRisque);
//        this.reverseListeImageRique();
//        this.updateTableImageRisque();
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
//            try {
//                imageRisqueDao.delete(imageRisque);
//            } catch (Exception e) {
////               List<OrclassImageFamilleRisque>   familleRisques=(List<OrclassImageFamilleRisque>) imageFamilleRisqueDao.findAllEntitiesHavingValue("idRisque", imageRisque);
////                if (!familleRisques.isEmpty()) {
//                imageFamilleRisqueDao.deleteAll("idRisque", imageRisque);
//                imageRisqueDao.delete(imageRisque);
//
////                }
//            }
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.DELETE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        listeImageRisques = imageRisqueDao.listeImageRisqueFamille(entreprise, refGroupeSelect, numero_police, assure.getRaison_social());
//        imageRisque = new OrclassImageRisque();
//        imageRisque.setIdGroup(refGroupeSelect);
//        listeImageRisques.add(imageRisque);
//        this.reverseListeImageRique();
//        this.updateTableImageRisque();
//
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//    }
//
//    public void addRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//        OrclassRisque r = null;
//        if (risqueselecte != null && risqueselecte.getMatricule() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "MATRICULE IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (risqueselecte != null && risqueselecte.getDateNaissance() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "DATE OF BIRTH IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (risqueselecte != null && risqueselecte.getLibelle() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "NAME IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        r = risqueDao.risqueByLibelle(entreprise, police, risqueselecte.getLibelle());
//        if (r == null || r.getId() == null) {
//            r = new OrclassRisque();
//            r.setDateCreation(new Date());
//            r.setMatricule(risqueselecte.getMatricule());
//            r.setDateEntree(new Date());
//            r.setDateNaissance(risqueselecte.getDateNaissance());
//            r.setIdEntreprise(entreprise);
//            r.setIdPolice(police);
//            r.setLibelle(risqueselecte.getLibelle());
//            r.setSexe(risqueselecte.getSexe());
//            r.setIdGroup(this.getRefGroupeSelect());
//            r.setIdVille(assure.getIdVille());
//            r.setSaisir_par(user.getNom() + " " + user.getPrenom());
//
//            risqueDao.create(r);
////            this.setRefGroupeSelect(r.getIdGroup());
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//            listeRisque = risqueDao.listeRisqueByGroupe(entreprise, this.getRefGroupeSelect(), police);
//        } else {
//            listeRisque = risqueDao.listeRisqueByPolice(entreprise, police);
//        }
//        risque = new OrclassRisque();
//
//        listeRisque.add(risque);
////        imageRisque.setIdGroup(refGroupeSelect);
//
//        this.reverseListeRique();
//        this.updateTableRisque();
////        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//    }
//
//    public void updateRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//
//        if (risqueselecte != null && risqueselecte.getId() != null) {
//
//            risqueDao.update(risqueselecte);
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.UPDATE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//            listeRisque = risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police);
//        } else {
//            listeRisque = risqueDao.listeRisqueByPolice(entreprise, police);
//        }
//        risque = new OrclassRisque();
//
//        listeRisque.add(risque);
//        imageRisque.setIdGroup(refGroupeSelect);
//
//        this.reverseListeRique();
//        this.updateTableRisque();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//    }
//
//    public void deleteRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassImageRisque ir = null;
//
//        if (risqueselecte != null && risqueselecte.getId() != null) {
//
//            risqueDao.delete(risqueselecte);
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.DELETE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//            listeRisque = risqueDao.listeRisqueByGroupe(entreprise, refGroupeSelect, police);
//        } else {
//            listeRisque = risqueDao.listeRisqueByPolice(entreprise, police);
//        }
//        risque = new OrclassRisque();
//
//        listeRisque.add(risque);
////        imageRisque.setIdGroup(refGroupeSelect);
//
//        this.reverseListeRique();
//        this.updateTableRisque();
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
//    public void addFamilleRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassRisqueFamille rf = null;
////        if (imageFamilleRisque != null && imageFamilleRisque.getMatricule() == null) {
////            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "MATRICULE IS EMPTY", "PLEASE TRY AGAINST"));
////            return;
////        }
//        if (this.getRisqueFamilleSelect() != null && this.getRisqueFamilleSelect().getNom_membre() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "MEMBER NAME IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (this.getRisqueFamilleSelect() != null && this.getRisqueFamilleSelect().getSexe() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "SELECT SEX", "PLEASE TRY AGAINST"));
//            return;
//        }
//
//        if (getRisqueFamilleSelect() != null && getRisqueFamilleSelect().getLienParente() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "SELECT VALUE", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (this.getRisqueFamilleSelect() != null && this.getRisqueFamilleSelect().getDateNaissance() == null) {
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "DATE OF BIRTH IS EMPTY", "PLEASE TRY AGAINST"));
//            return;
//        }
//        if (risqueFamilleDao.risqueFamilleFoPoliceBylibelle(entreprise, police, this.getRisqueFamilleSelect().getNom_membre()) == null) {
//            rf = new OrclassRisqueFamille();
//            rf.setDateEntree(risqueFamilleSelect.getDateEntree());
//            rf.setDateNaissance(risqueFamilleSelect.getDateNaissance());
//            rf.setIdEntreprise(entreprise);
//            rf.setIdPolice(police);
//            rf.setIdRisque(risque);
//            rf.setMatricule(risqueFamilleSelect.getMatricule());
//            rf.setNom_membre(risqueFamilleSelect.getNom_membre());
//            rf.setIdVille(assure.getIdVille());
//            rf.setLienParente(risqueFamilleSelect.getLienParente());
////            rf.setNom_membre(rf.getNom_membre());
//
//            rf.setSexe(risqueFamilleSelect.getSexe());
//            risqueFamilleDao.create(rf);
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.INSERT_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//
//        listeRisqueFamille = (List<OrclassRisqueFamille>) risqueFamilleDao.findAllEntitiesHavingValue("idRisque", risque);
//
//        rf = new OrclassRisqueFamille();
////        imageRisque.setIdGroup(refGroupeSelect);
//        listeRisqueFamille.add(rf);
//        this.reverseListeFamilleRique();
//        this.updateDataTableRisqueFamille();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//    }
//
//    public void updateFamilleRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassRisqueFamille rf = null;
//
//        if (risqueFamilleSelect != null && risqueFamilleSelect.getId() != null) {
//
//            risqueFamilleDao.update(risqueFamilleSelect);
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.UPDATE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        listeRisqueFamille = (List<OrclassRisqueFamille>) risqueFamilleDao.findAllEntitiesHavingValue("idRisque", risque);
//
//        rf = new OrclassRisqueFamille();
////        imageRisque.setIdGroup(refGroupeSelect);
//        listeRisqueFamille.add(rf);
//        this.reverseListeFamilleRique();
//        this.updateDataTableRisqueFamille();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//    }
//
//    public void deleteFamilleRisque() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        OrclassRisqueFamille rf = null;
//
//        if (risqueFamilleSelect != null && risqueFamilleSelect.getId() != null) {
//
//            risqueFamilleDao.delete(risqueFamilleSelect);
//
//            ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "" + exception.Success.DELETE_SUCCESS.name(), "" + exception.Success.OPERATION_SUCCESS.name()));
//        }
//        listeRisqueFamille = (List<OrclassRisqueFamille>) risqueFamilleDao.findAllEntitiesHavingValue("idRisque", risque);
//
//        rf = new OrclassRisqueFamille();
////        imageRisque.setIdGroup(refGroupeSelect);
//        listeRisqueFamille.add(rf);
//        this.reverseListeFamilleRique();
//        this.updateDataTableRisqueFamille();
//        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//    }
//
//    public void updateTableImageRisque() {
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form1:tabprincipal:idimageRisqueTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('imageRisqueTable').clearFilters();");
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
//        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("form2:idrbTable");
//        table.setValueExpression("sortBy", null);
//
//        PrimeFaces.current().executeScript("PF('rgTable').clearFilters();");
////         PrimeFaces.current().ajax().update("form1_1");
//
//    }
//
//    public void reverseListeImageRique() {
//
//        List<OrclassImageRisque> result = new ArrayList<>();
//        for (int i = listeImageRisques.size() - 1; i >= 0; i--) {
//            result.add(listeImageRisques.get(i));
//        }
//
//        this.setListeImageRisques(result);
////        this.updateTableRubriqueCategorie();
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
//        for (int i = listeRisqueFamille.size() - 1; i >= 0; i--) {
//            result.add(listeRisqueFamille.get(i));
//        }
//
//        this.setListeRisqueFamille(result);
////        this.updateTableRubriqueCategorie();
//    }
//
//    public void calculTotalProrata() {
//        BigDecimal montant = BigDecimal.ZERO;
//        for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//            if (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null) {
//                continue;
//            }
//            if (pg.getPrime() != null && pg.getPrime().intValue() != 0 && Objects.equals(pg.getAfficher(), Boolean.TRUE) && Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                if (avenant == null || avenant.getId() == null) {
//                    montant = montant.add(pg.getProrata());
//                } else if (avenant != null && avenant.getId() != null && pg.getAjouter() == Boolean.TRUE) {
//                    montant = montant.add(pg.getProrata());
//                }
//
//            } else if (pg.getPrime() != null && pg.getPrime().intValue() != 0 && Objects.equals(tabShowGroup, Boolean.FALSE)) {
////                montant = montant.add(pg.getProrata());
//                if (avenant == null || avenant.getId() == null) {
//                    montant = montant.add(pg.getProrata());
//                } else if (avenant != null && avenant.getId() != null && Objects.equals(pg.getAjouter(), Boolean.TRUE)) {
//                    montant = montant.add(pg.getProrata());
//                }
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
//            if (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null) {
//                continue;
//            }
//            if (Objects.equals(pg.getRetire_de_la_police(), Boolean.TRUE) && pg.getCodeid_retirer() != null) {
//                continue;
//            }
//            if (pg.getPrime() != null && pg.getPrime().intValue() != 0 && Objects.equals(pg.getAfficher(), Boolean.TRUE) && Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                if (avenant == null || avenant.getId() == null) {
//                    montant = montant.add(pg.getPrime());
//                    pg.setProrata(pg.getPrime());
//                } else if (avenant != null && avenant.getId() != null && Objects.equals(pg.getAjouter(), Boolean.TRUE)) {
//                    montant = montant.add(pg.getPrime());
//                    pg.setProrata(pg.getPrime());
//                }
//
//            } else if (pg.getPrime() != null && pg.getPrime().intValue() != 0 && Objects.equals(tabShowGroup, Boolean.FALSE)) {
////                montant = montant.add(pg.getPrime());
////                pg.setProrata(pg.getPrime());
//                if (avenant == null || avenant.getId() == null) {
//                    montant = montant.add(pg.getPrime());
//                    pg.setProrata(pg.getPrime());
//                } else if (avenant != null && avenant.getId() != null && Objects.equals(pg.getAjouter(), Boolean.TRUE)) {
//                    montant = montant.add(pg.getPrime());
//                    pg.setProrata(pg.getPrime());
//                }
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
//        OrclasseRefGroupe groupeDetailElement = null;
//        OrclassGroupePolice gp = null;
//        if (refGroupe.getLibelle() != null && refGroupeDao.findEntityHavingValue("libelle", refGroupe.getLibelle()) == null) {
//            if (police != null && police.getId() != null && Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                groupeDetailElement = new OrclasseRefGroupe();
//                groupeDetailElement.setLibelle(refGroupe.getLibelle().toUpperCase());
//                nbre = refGroupeDao.getlastNumeroOrder();
//                nbre = nbre.add(BigInteger.ONE);
//                groupeDetailElement.setNumero_ordre(nbre);
//                refGroupeDao.create(groupeDetailElement);
//                if (groupePoliceDao.finKey(entreprise, police, groupeDetailElement) == null) {
//                    gp = new OrclassGroupePolice();
//                    gp.setAdresseGroup(refGroupe.getAdresseGroup());
//                    gp.setDateCreation(new Date());
//                    gp.setIdEntreprise(entreprise);
//                    gp.setIdPolice(police);
//                    gp.setIdRefGroupe(groupeDetailElement);
//                    gp.setIdVille(refGroupe.getVille());
//                    gp.setObservation(refGroupe.getObservation());
//                    gp.setAdresseGroup(refGroupe.getAdresseGroup());
//
//                    gp.setSaisir_par(user.getNom());
//                    groupePoliceDao.create(gp);
//                }
//                groupeDetailElement.setAdresseGroup(refGroupe.getAdresseGroup());
//                groupeDetailElement.setVille(refGroupe.getVille());
//                groupeDetailElement.setObservation(refGroupe.getObservation());
//                this.listeRefGroup.add(groupeDetailElement);
//                this.setRefGroupeSelect(groupeDetailElement);
//                refGroupe = new OrclasseRefGroupe();
//                groupeDetailElement = new OrclasseRefGroupe();
//
//            } else if (police == null || police.getId() == null) {
//                refGroupe.setLibelle(refGroupe.getLibelle().toUpperCase());
//                groupeDetailElement = refGroupe;
//                nbre = refGroupeDao.getlastNumeroOrder();
//                nbre = nbre.add(BigInteger.ONE);
//                refGroupe.setNumero_ordre(nbre);
//
//                refGroupeDao.create(refGroupe);
////            refGroupe.setAdresse(groupeDetailElement.getAdresse());
//                refGroupe.setAdresseGroup(groupeDetailElement.getAdresseGroup());
//                refGroupe.setVille(groupeDetailElement.getVille());
//                refGroupe.setObservation(groupeDetailElement.getObservation());
//                this.listeRefGroup.add(refGroupe);
//                this.setRefGroupeSelect(refGroupe);
//                refGroupe = new OrclasseRefGroupe();
//            }
//
////            this.setRefGroupeSelect(refGroupe);
//        } else {
//            refGroupe.setLibelle(refGroupe.getLibelle().toUpperCase());
//            groupeDetailElement = refGroupe;
//            refGroupe = refGroupeDao.findEntityHavingValue("libelle", refGroupe.getLibelle().toUpperCase());
//            refGroupe.setAdresseGroup(groupeDetailElement.getAdresseGroup());
//            refGroupe.setVille(groupeDetailElement.getVille());
//            refGroupe.setObservation(groupeDetailElement.getObservation());
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
//        refGroupe = new OrclasseRefGroupe();
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
//    public void addListeImageRisque() {
//        OrclassImageRisque imageRisque = null;
//        OrclassImageFamilleRisque imageFamilleRisque = null;
//        List<OrclassImageRisque> colImageRisque = new ArrayList<>(listeImageRisques);
//        listeImageRisques.clear();
//
//        for (OrclassImageRisque ir : colImageRisque) {
//            if (imageRisqueDao.finkey(entreprise, ir.getIdGroup(), ir.getLibelle(), ir.getMatricule(), ir.getDateNaissance(), numero_police) == null) {
//                imageRisque = new OrclassImageRisque();
//                imageRisque.setIdEntreprise(entreprise);
//                imageRisque.setDateNaissance(ir.getDateNaissance());
//                imageRisque.setIdGroup(refGroupeSelect);
//                imageRisque.setLibelle(ir.getLibelle());
//                imageRisque.setMatricule(ir.getMatricule());
//                imageRisque.setSexe(ir.getSexe());
//                imageRisque.setSouscripteur(assure.getRaison_social().toUpperCase());
//                imageRisque.setNumero_polic(numero_police);
//                imageRisque.setIdCategories(categories);
//                imageRisque.setIdIntermediaire(intermediaires);
//                imageRisque.setIdUtilisateur(user);
//                try {
//                    imageRisqueDao.create(imageRisque);
//                    listeImageRisques.add(imageRisque);
//                } catch (Exception e) {
//                    System.err.println("risque de l erreur " + imageRisque.getMatricule() + " matricule " + imageRisque.getMatricule());
//                }
//
//            }
//        }
//
//        for (OrclassImageFamilleRisque irf : listeImageFamilleRisque) {
//            for (OrclassImageRisque irs : listeImageRisques) {
//                if (irf.getMatricule().equals(irs.getMatricule())) {
//                    imageRisque = irs;
//                    break;
//                }
//            }
//            if (imageRisque == null) {
//                continue;
//            }
//            if (imageFamilleRisqueDao.finKey(imageRisque, irf.getNom_membre(), irf.getDateNaissance()) == null) {
//                imageFamilleRisque = new OrclassImageFamilleRisque();
//                imageFamilleRisque.setDateNaissance(irf.getDateNaissance());
//                imageFamilleRisque.setMatricule(irf.getMatricule());
//                imageFamilleRisque.setNom_membre(irf.getNom_membre());
//                imageFamilleRisque.setLienParente(irf.getLienParente());
//                imageFamilleRisque.setSexe(irf.getSexe());
//                imageFamilleRisque.setIdRisque(imageRisque);
//                imageFamilleRisqueDao.create(imageFamilleRisque);
//            }
//        }
////        listeImageRisques.clear();
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
//            this.listeRubriqueCaracteristiqueControleChangeGroup.clear();
//            listeRubriqueCaracteristiqueControleChangeGroup.addAll(listeRubriqueCaracteristique);
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
//            listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotSatutObligatoire(entreprise, categories, rubriqueCategorie.getIdRubrique(), StatutCaracteristique.obligatoire);
//
//        }
//
//    }
//
//    public void reload() throws IOException {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//    }
//
//    public void showDetailsRubrique(OrclassCaracteristiques item) {
//        if (tabShowGroup == Boolean.TRUE && (refGroupeSelectForCaracteristiqueAndGarantie == null || refGroupeSelectForCaracteristiqueAndGarantie.getId() == null)) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE SELECT GROUP", "FATAL ERROR..."));
//            return;
//        }
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
//        if (rubriqueGarantie.getIdGarantie() == null || rubriqueGarantie.getIdGarantie().getId() == null && item != null) {
//            this.getRubriqueGarantie().setIdGarantie(item);
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
//        policeGarantie.setIdGroup(refGroupeSelectForGarantie);
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
//                if (duree.getMaxDuree() == null || duree.getMaxDuree().intValue() == 0) {
//                    police.setValeurDuree(duree.getMinDuree());
//                }
//                PrimeFaces.current().ajax().update(":form1:tabprincipal:tabsecond");
//                PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//            }
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
//    public void removeLigneForDetailPolicePlafond() {
//        listeDetailPolicePlafond.remove(detailPolicePlafond);
//        listePrestation.add(detailPolicePlafond.getIdPrestation());
//        this.updateTablePrestation();
//        this.updateTabledetailPolicePlafond();
//
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
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraByAgence(intermediaires, entreprise, user, branches);
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
//    /*
//    fonction pour retourner le tarif pour condition precise selon la categorie du vehicule 
//    
//     */
//    public Boolean verificationDureeAndDateEffet() {
//        Boolean valeur = Boolean.TRUE;
//        if (duree != null && duree.getId() != null) {
//
//            switch (duree.getUniteDuree()) {
//                case jours:
//                    int nbre_jour = 0;//ceci est le nobre de jour pour le contract saisir entre la date d effet et la dayte d echeance     
//                    nbre_jour = IdleDate.nombreJourFor2Dates(police.getDate_effet(), police.getDate_echeance());
//                    if (nbre_jour > police.getValeurDuree().intValue()) {
//                        valeur = Boolean.FALSE;
//                    }
//                    break;
//                case mois:
//                    int nbre_mois = 0;//ceci concerne le nombre de mois pour le contract a effectuer entre la date d effet et la date d echeance
//                    nbre_mois = (IdleDate.nombreJourFor2Dates(police.getDate_effet(), police.getDate_echeance()) / 30);
//                    if (nbre_mois > police.getValeurDuree().intValue()) {
//                        valeur = Boolean.FALSE;
//                    }
//                    break;
//                case semaines:
//                    int nbre_semaine = 0;//ceci concerne le nombre de mois pour le contract a effectuer entre la date d effet et la date d echeance
//                    nbre_semaine = (IdleDate.nombreJourFor2Dates(police.getDate_effet(), police.getDate_echeance()) / 7);
//                    if (nbre_semaine > police.getValeurDuree().intValue()) {
//                        valeur = Boolean.FALSE;
//                    }
//                    break;
//
//            }
//        }
//        return valeur;
//    }
//
//    public OrclassTarif tarifWithConditionByCategorieVehicule() {
//        if (categories != null && categories.getIdCategorie() != null) {
//            OrclassTarif tarif = null;
//            OrclassTarifCondition tarif_condition = null;
//            List<OrclassTarif> listeValues = new ArrayList<>();
//            List<OrclassTarifCondition> listeValuesForEnergie = new ArrayList<>();
//            OrclassTarifCondition tarifCondition = null;
//            int categorie_vehicule = categories.getCategoriesVehicule() == null ? 0 : categories.getCategoriesVehicule();
//
//            if (categorie_vehicule != 0) {
//                switch (categorie_vehicule) {
//                    case 1: // ici on parle du vehicule categorie 1
//                        // pour cette categorie nous utiliserons ensemble de parametre qui est un ellement de vehicule
//
////                        listeValues = tarifConditionDao.retourneTarifConditionByVehicule(risque, entreprise, categorie_vehicule, police.getIdTypeTarif());
//                        listeValues = tarifDao.retourneTarifByVehicule(risque, entreprise, categorie_vehicule, police.getIdTypeTarif());
//                        if (!listeValues.isEmpty()) {
//                            System.out.println("Taille Liste :" + listeValues.size());
//                            System.out.println(" taille liste :" + listeValues.size());
//                            for (OrclassTarif t : listeValues) {
//                                if (tarif != null && tarif.getId() != null) {
//                                    continue;
//                                }
////                                if (tarifConditionDao.(t, risque.getZone().name(), entreprise)!= null && tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarif(t,risque.getEnergie().name(), entreprise)!=null && tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarif(t, risque.getUsage_vehicule(), entreprise)!=null && tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarif(t, risque.getGenre_vehicule(), entreprise)!=null && tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarif(t, VehiculeObjet.avec_remoque.name(),risque.getAvec_remoque()==null ? false:risque.getAvec_remoque(), entreprise)!=null ) {
////                                    tarifCondition=tarifConditionDao.tarificationConditionWithCaracteristiqueAndTarifForPuissance(t, VehiculeObjet.puissance.name(), risque.getPuissance_vehicule(), entreprise);
////                                    if (tarifCondition!=null && tarifCondition.getId()!=null) {
////                                        tarif=tarifCondition.getIdTarif();
////                                        System.out.println("valeur Min:"+tarifCondition.getValeurMin()+" Valeur Max: "+tarifCondition.getValeurMax()+" id Tarif :"+tarif.getId() );
////                                    }
////                                }
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
////    public OrclassPoliceGarantie calculPrimeAutomatiqueGarantie(OrclassPoliceGarantie p) {
////        /*
////        ici sera appliquzr l agorithme du calcul automatique  des primes ou proarata
////         */
////        // recuperation de la duree du contrat
////        BigInteger dureeContrat = BigInteger.ZERO;
////        BigDecimal primeAnnuel = BigDecimal.ZERO;
////        BigDecimal primePeriode = BigDecimal.ZERO;
////        BigDecimal primeNette = BigDecimal.ZERO;
////        BigDecimal etape1, etape11, etape111 = BigDecimal.ZERO;//cete etape concerne la valeur additive + coefficient divise par le raport de division
////        BigDecimal constant1, constant2 = BigDecimal.ZERO;
////        BigDecimal valeurCarct1, valeurCaract2 = BigDecimal.ZERO;
////        BigDecimal valeur1 = BigDecimal.ZERO, valeur11 = BigDecimal.ZERO, valeur111 = BigDecimal.ZERO, valeur2 = BigDecimal.ZERO;
////        OperationsTarif operationsTarif1;
////        OperationsTarif operationsTarif2;
////        OrclassTarif t1_rubrique, t11_rubrique, t2_rubrique, t22_rubrique, t222_rubrique = null;
////        OrclassCaracteristiques caracteristique1, caracteristique2;
////        OrclassRubrique rubrique1, rubrique11, rubrique2, rubrique22 = null;
////        BigDecimal valeurCalculer = BigDecimal.ZERO;
////        BigDecimal etape2, etape22, etape222 = BigDecimal.ZERO;//cete etape concerne la valeur additive + coefficient divise par le raport de division
////
//////        Date curentdate=IdleDate.toString(new Date(), "dd/MM/yyyy");
////        if (police != null && police.getValeurDuree() != null && police.getValeurDuree().intValue() != 0) {
////
////            dureeContrat = police.getValeurDuree();
////            //recupere la tarification de la garantie
////            if (Objects.equals(p.getIdGarantie().getGlobalCompagnie(), Boolean.TRUE)) {
////                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantiePrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, p.getIdGarantie());
////                if (rubriqueGarantie == null) {
////                    p.setPrime(BigDecimal.ZERO);
////                    p.setPrimeAnnuelle(BigDecimal.ZERO);
////                    p.setProrata(BigDecimal.ZERO);
////                    return p;
////                }
////                tarif = tarifDao.lastTarif(rubriqueGarantie);
////                if (tarif == null) {
////                    p.setPrime(BigDecimal.ZERO);
////                    p.setPrimeAnnuelle(BigDecimal.ZERO);
////                    p.setProrata(BigDecimal.ZERO);
////                    return p;
////                }
////            } else {
////
////                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantiePrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, p.getIdGarantie(), entreprise);
////                if (rubriqueGarantie == null) {
////                    p.setPrime(BigDecimal.ZERO);
////                    p.setPrimeAnnuelle(BigDecimal.ZERO);
////                    p.setProrata(BigDecimal.ZERO);
////                    return p;
////                }
////                tarif = tarifDao.lastTarif(rubriqueGarantie, entreprise);
////                if (tarif == null) {
////                    p.setPrime(BigDecimal.ZERO);
////                    p.setPrimeAnnuelle(BigDecimal.ZERO);
////                    p.setProrata(BigDecimal.ZERO);
////                    return p;
////                }
////
////            }
////
////            etape1 = (tarif.getValeur_Additif().add(tarif.getCoefficient())).divide(BigDecimal.valueOf(tarif.getRapport_Division().intValue()));
////            if (tarif.getIdRubrique1() == null && tarif.getIdRubrique2() == null) {
////                //recuperation de la valeur 1 rubrique 1 ==null
////                valeur1 = this.calculValeurForFirstRubriqueIsNotExiste(etape1, tarif);
////                //recuperation de la valeur 2 pour rubrique 2==null
////                valeur2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif);
////
////            } else if (tarif.getIdRubrique1() == null && tarif.getIdRubrique2() != null) {
////                valeur1 = this.calculValeurForFirstRubriqueIsNotExiste(etape1, tarif);
////                rubrique2 = rubriqueDao.findEntityHavingValue("code", tarif.getIdRubrique2());
////                valeur2 = this.calculValeurForSecondeRubriqueIsExiste(rubrique2, p.getIdGarantie());
////            } else if (tarif.getIdRubrique1() != null && tarif.getIdRubrique2() == null) {
////                rubrique1 = rubriqueDao.findEntityHavingValue("code", tarif.getIdRubrique1());
////                valeur1 = this.calculValeurForFirstRubriqueIsExiste(etape1, rubrique1, p.getIdGarantie());
////                valeur2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif);
////            } else if (tarif.getIdRubrique1() != null && tarif.getIdRubrique2() != null) {
////                rubrique1 = rubriqueDao.findEntityHavingValue("code", tarif.getIdRubrique1());
////                valeur1 = this.calculValeurForFirstRubriqueIsExiste(etape1, rubrique1, p.getIdGarantie());
////                rubrique2 = rubriqueDao.findEntityHavingValue("code", tarif.getIdRubrique2());
////                valeur2 = this.calculValeurForSecondeRubriqueIsExiste(rubrique2, p.getIdGarantie());
////
////            }
////
////            if (tarif.getOperations() != null) {
////                switch (tarif.getOperations()) {
////                    case division:
////                        primeAnnuel = valeur1.divide(valeur2);
////                        break;
////                    case multiplication:
////                        primeAnnuel = valeur1.multiply(valeur2);
////                        break;
////                    case moins:
////                        primeAnnuel = valeur1.subtract(valeur2);
////                        break;
////                    case plus:
////                        primeAnnuel = valeur1.add(valeur2);
////                        break;
////                    case max:
////                        primeAnnuel = valeur1.max(valeur2);
////                        break;
////                    case min:
////                        primeAnnuel = valeur1.min(valeur2);
////                        break;
////                }
////            } else {
////                primeAnnuel = valeur1;
////            }
////
////            // le prime annuel est la valeur equivalente a la durere selection 
////            /*
////                calculons la prime periode ,pour cela on doit savoir  l unite de la duree jour ,mois ou annee
////             */
////            switch (duree.getUniteDuree()) {
////                case jours:
////                    int nbre_jour = 0;//ceci est le nobre de jour pour le contract saisir entre la date d effet et la dayte d echeance     
////                    nbre_jour = IdleDate.nombreJourFor2Dates(police.getDate_effet(), police.getDate_echeance());
////                    float primePeriodeByNbreJour = (nbre_jour * primeAnnuel.intValue()) / dureeContrat.intValue();
////                    System.out.println("nombfre de jour:" + nbre_jour);
////                    //arrondissementValeur exact
////                    primePeriode = new BigDecimal((int) primePeriodeByNbreJour);
////                    break;
////                case mois:
////                    int nbre_mois = 0;//ceci concerne le nombre de mois pour le contract a effectuer entre la date d effet et la date d echeance
////                    nbre_mois = (IdleDate.nombreJourFor2Dates(police.getDate_effet(), police.getDate_echeance()) / 30);
////                    float primePeriodeByNbreMois = (nbre_mois * primeAnnuel.intValue()) / dureeContrat.intValue();
////                    //arrondissementValeur exact
////                    System.out.println("nombfre de mois:" + nbre_mois);
////                    primePeriode = new BigDecimal((int) primePeriodeByNbreMois);
////                    break;
////                case semaines:
////                    int nbre_semaine = 0;//ceci concerne le nombre de mois pour le contract a effectuer entre la date d effet et la date d echeance
////                    nbre_semaine = (IdleDate.nombreJourFor2Dates(police.getDate_effet(), police.getDate_echeance()) / 7);
////                    float primePeriodeByNbreSemaines = (nbre_semaine * primeAnnuel.intValue()) / dureeContrat.intValue();
////                    //arrondissementValeur exact
////                    System.out.println("nombfre de semaine:" + nbre_semaine);
////                    primePeriode = new BigDecimal((int) primePeriodeByNbreSemaines);
////                    break;
////
////            }
////
////            p.setPrimeAnnuelle(primeAnnuel);
////            p.setPrime(primePeriode);
////            p.setProrata(primePeriode);
////
////        }
////        return p;
////
////    }
//
//    /*
//    calculvaleur rubrique for tarif is null
//     */
//    public BigDecimal calculValeurForFirstRubriqueIsNotExiste(BigDecimal etape, OrclassTarif tarif) {
//        BigDecimal valeur = BigDecimal.ZERO;
//        OrclassCaracteristiques caracteristique = null;
//
//        /*
//        dans ce cas ou la rubrique1  est nulle  force la valeur est une constante ou une caracteristique
//         */
//        if (tarif.getOperationTarif1() == null) {
//            return etape;
//        }
//        switch (tarif.getOperationTarif1()) {
//            case contante:
//                valeur = tarif.getConstant1();
//                break;
//            case caracteristique:
//                caracteristique = caracteristiquesDao.findEntityHavingValue("code", tarif.getIdCaracteristiques1());
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
//        valeur = etape.multiply(valeur);
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
//    public BigDecimal calculValeurForFirstRubriqueIsExiste(BigDecimal etapePrincipal, OrclassRubrique rubrique, OrclassGarantie g) {
//        BigDecimal valeurRubrique1 = BigDecimal.ZERO;
//        BigDecimal valeurRubrique2 = BigDecimal.ZERO;
//        BigDecimal valeur = BigDecimal.ZERO;
//        BigDecimal valeurCalculer = BigDecimal.ZERO;
//        BigDecimal etape_rubrique = BigDecimal.ZERO;
//        OrclassTarif tarif_rubrique = null;
//        if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
//            rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique);
//
//            tarif_rubrique = tarifDao.lastTarif(rubriqueGarantie);
//        } else {
//            rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique, entreprise);
//
//            tarif_rubrique = tarifDao.lastTarif(rubriqueGarantie, entreprise);
//        }
//        //verifions la valeur de la premier rubrique;
//        /*
//      premier cas cette rubrique est null dans cette partie on appelle la fonction pour recuperer la valeur
//         */
//        if (tarif_rubrique == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
//            return BigDecimal.ZERO;
//        }
//        etape_rubrique = (tarif_rubrique.getValeur_Additif().add(tarif_rubrique.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique.getRapport_Division().intValue()));
//
//        if (tarif_rubrique.getIdRubrique1() == null && tarif_rubrique.getIdRubrique2() == null) {
//            valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique, tarif_rubrique);
//            valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique);
//            if (tarif_rubrique.getOperations() != null) {
//                switch (tarif_rubrique.getOperations()) {
//                    case plus:
//                        valeurCalculer = valeurRubrique1.add(valeurRubrique2);
//                        break;
//                    case division:
//                        valeurCalculer = valeurRubrique1.divide(valeurRubrique2);
//                        break;
//                    case multiplication:
//                        valeurCalculer = valeurRubrique1.multiply(valeurRubrique2);
//                        break;
//                    case max:
//                        valeurCalculer = valeurRubrique1.max(valeurRubrique2);
//                        break;
//                    case min:
//                        valeurCalculer = valeurRubrique1.min(valeurRubrique2);
//                        break;
//                    case moins:
//                        valeurCalculer = valeurRubrique1.subtract(valeurRubrique2);
//                        break;
//                }
//                valeur = valeurCalculer;//
//            } else {
//                valeur = valeurRubrique1;
//            }
//
//        } else if (tarif_rubrique.getIdRubrique1() == null && tarif_rubrique.getIdRubrique2() != null) {
//            BigDecimal valeurRubrique12 = BigDecimal.ZERO;
//            valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique, tarif_rubrique);
//            //retrouvons d abord la rubriGarantie enfinde determiner le tarif
//            OrclassRubrique rubrique2 = rubriqueDao.findEntityHavingValue("code", tarif_rubrique.getIdRubrique2());
//            OrclassTarif tarif_rubrique2 = null;
//            BigDecimal etape_rubrique2 = BigDecimal.ZERO;
//            if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
//                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique2);
//
//                tarif_rubrique2 = tarifDao.lastTarif(rubriqueGarantie);
//            } else {
//                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique2, entreprise);
//
//                tarif_rubrique2 = tarifDao.lastTarif(rubriqueGarantie, entreprise);
//            }
//            if (tarif_rubrique2 == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
//                return BigDecimal.ZERO;
//            }
//            etape_rubrique2 = (tarif_rubrique2.getValeur_Additif().add(tarif_rubrique2.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique2.getRapport_Division().intValue()));
//            if (tarif_rubrique2.getIdRubrique1() == null && tarif_rubrique2.getIdRubrique2() == null) {
//                valeurRubrique12 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique2, tarif_rubrique2);
//                valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique2);
//                if (tarif_rubrique2.getOperations() != null) {
//                    switch (tarif_rubrique.getOperations()) {
//                        case plus:
//                            valeurCalculer = valeurRubrique12.add(valeurRubrique2);
//                            break;
//                        case division:
//                            valeurCalculer = valeurRubrique12.divide(valeurRubrique2);
//                            break;
//                        case multiplication:
//                            valeurCalculer = valeurRubrique12.multiply(valeurRubrique2);
//                            break;
//                        case max:
//                            valeurCalculer = valeurRubrique12.max(valeurRubrique2);
//                            break;
//                        case min:
//                            valeurCalculer = valeurRubrique12.min(valeurRubrique2);
//                            break;
//                        case moins:
//                            valeurCalculer = valeurRubrique12.subtract(valeurRubrique2);
//                            break;
//                    }
////                    valeur = etape_rubrique2.multiply(valeurCalculer);//
//                } else {
//                    valeurCalculer = BigDecimal.ONE;
//                }
//            }
//            if (tarif_rubrique.getOperations() != null) {
//                switch (tarif_rubrique.getOperations()) {
//                    case plus:
//                        valeur = valeurRubrique1.add(valeurCalculer);
//                        break;
//                    case division:
//                        valeur = valeurRubrique1.divide(valeurCalculer);
//
//                        break;
//                    case multiplication:
//                        valeur = valeurRubrique1.multiply(valeurCalculer);
//
//                        break;
//                    case max:
//                        valeur = valeurRubrique1.max(valeurCalculer);
//
//                        break;
//                    case min:
//                        valeur = valeurRubrique1.min(valeurCalculer);
//
//                        break;
//                    case moins:
//                        valeur = valeurRubrique1.subtract(valeurCalculer);
//
//                        break;
//                }
//            } else {
//                valeur = BigDecimal.ONE;
//            }
//
//        } else if (tarif_rubrique.getIdRubrique1() != null && tarif_rubrique.getIdRubrique2() == null) {
//            valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique);
//            //retrouvons d abord la rubriGarantie enfinde determiner le tarif
//            OrclassRubrique rubrique1 = rubriqueDao.findEntityHavingValue("code", tarif_rubrique.getIdRubrique1());
//            OrclassTarif tarif_rubrique1 = null;
//            BigDecimal etape_rubrique1 = BigDecimal.ZERO;
//            if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
//                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique1);
//
//                tarif_rubrique1 = tarifDao.lastTarif(rubriqueGarantie);
//            } else {
//                rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique1, entreprise);
//
//                tarif_rubrique1 = tarifDao.lastTarif(rubriqueGarantie, entreprise);
//            }
//            if (tarif_rubrique1 == null) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
//                return BigDecimal.ZERO;
//            }
//            etape_rubrique1 = (tarif_rubrique1.getValeur_Additif().add(tarif_rubrique1.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique1.getRapport_Division().intValue()));
//            if (tarif_rubrique1.getIdRubrique1() == null && tarif_rubrique1.getIdRubrique2() == null) {
//                valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique1, tarif_rubrique1);
//                valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique1);
//                if (tarif_rubrique1.getOperations() != null) {
//                    switch (tarif_rubrique1.getOperations()) {
//                        case plus:
//                            valeurCalculer = valeurRubrique1.add(valeurRubrique2);
//                            break;
//                        case division:
//                            valeurCalculer = valeurRubrique1.divide(valeurRubrique2);
//                            break;
//                        case multiplication:
//                            valeurCalculer = valeurRubrique1.multiply(valeurRubrique2);
//                            break;
//                        case max:
//                            valeurCalculer = valeurRubrique1.max(valeurRubrique2);
//                            break;
//                        case min:
//                            valeurCalculer = valeurRubrique1.min(valeurRubrique2);
//                            break;
//                        case moins:
//                            valeurCalculer = valeurRubrique1.subtract(valeurRubrique2);
//                            break;
//                    }
//                    valeur = etape_rubrique.multiply(valeurCalculer);//
//                } else {
//                    valeur = valeurRubrique1;
//                }
//
//            } else if (tarif_rubrique.getIdRubrique1() != null && tarif_rubrique.getIdRubrique2() != null) {
//
//                // tarif_rubrique.getIdRubrique1() != null
//                OrclassRubrique rubrique11 = rubriqueDao.findEntityHavingValue("code", tarif_rubrique.getIdRubrique1());
//                OrclassTarif tarif_rubrique11 = null;
//                BigDecimal etape_rubrique11 = BigDecimal.ZERO;
//                BigDecimal valeur1 = BigDecimal.ZERO, valeur2 = BigDecimal.ZERO;
//                if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
//                    rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique11);
//
//                    tarif_rubrique11 = tarifDao.lastTarif(rubriqueGarantie);
//                } else {
//                    rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique11, entreprise);
//
//                    tarif_rubrique11 = tarifDao.lastTarif(rubriqueGarantie, entreprise);
//                }
//                if (tarif_rubrique11 == null) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
//                    return BigDecimal.ZERO;
//                }
//                etape_rubrique11 = (tarif_rubrique11.getValeur_Additif().add(tarif_rubrique11.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique11.getRapport_Division().intValue()));
//                if (tarif_rubrique11.getIdRubrique1() == null && tarif_rubrique11.getIdRubrique2() == null) {
//                    valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique11, tarif_rubrique11);
//                    valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique11);
//                    if (tarif_rubrique11.getOperations() != null) {
//                        switch (tarif_rubrique11.getOperations()) {
//                            case plus:
//                                valeurCalculer = valeurRubrique1.add(valeurRubrique2);
//                                break;
//                            case division:
//                                valeurCalculer = valeurRubrique1.divide(valeurRubrique2);
//                                break;
//                            case multiplication:
//                                valeurCalculer = valeurRubrique1.multiply(valeurRubrique2);
//                                break;
//                            case max:
//                                valeurCalculer = valeurRubrique1.max(valeurRubrique2);
//                                break;
//                            case min:
//                                valeurCalculer = valeurRubrique1.min(valeurRubrique2);
//                                break;
//                            case moins:
//                                valeurCalculer = valeurRubrique1.subtract(valeurRubrique2);
//                                break;
//                        }
//                        valeur1 = etape_rubrique.multiply(valeurCalculer);//
//                    } else {
//                        valeur1 = valeurRubrique1;
//                    }
//
//                }
//
//                //tarif_rubrique.getIdRubrique2() != null
//                OrclassRubrique rubrique22 = rubriqueDao.findEntityHavingValue("code", tarif_rubrique.getIdRubrique2());
//                OrclassTarif tarif_rubrique22 = null;
//                BigDecimal etape_rubrique22 = BigDecimal.ZERO;
//
//                if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
//                    rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique22);
//
//                    tarif_rubrique22 = tarifDao.lastTarif(rubriqueGarantie);
//                } else {
//                    rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique22, entreprise);
//
//                    tarif_rubrique22 = tarifDao.lastTarif(rubriqueGarantie, entreprise);
//                }
//
//                if (tarif_rubrique22 == null) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
//                    return BigDecimal.ZERO;
//                }
//                etape_rubrique22 = (tarif_rubrique22.getValeur_Additif().add(tarif_rubrique22.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique22.getRapport_Division().intValue()));
//
//                if (tarif_rubrique22.getIdRubrique1() == null && tarif_rubrique22.getIdRubrique2() == null) {
//                    valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique22, tarif_rubrique22);
//                    valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique22);
//                    if (tarif_rubrique22.getOperations() != null) {
//                        switch (tarif_rubrique22.getOperations()) {
//                            case plus:
//                                valeurCalculer = valeurRubrique1.add(valeurRubrique2);
//                                break;
//                            case division:
//                                valeurCalculer = valeurRubrique1.divide(valeurRubrique2);
//                                break;
//                            case multiplication:
//                                valeurCalculer = valeurRubrique1.multiply(valeurRubrique2);
//                                break;
//                            case max:
//                                valeurCalculer = valeurRubrique1.max(valeurRubrique2);
//                                break;
//                            case min:
//                                valeurCalculer = valeurRubrique1.min(valeurRubrique2);
//                                break;
//                            case moins:
//                                valeurCalculer = valeurRubrique1.subtract(valeurRubrique2);
//                                break;
//                        }
//                        valeur2 = etape_rubrique11.multiply(valeurCalculer);//
//                    } else {
//                        valeur2 = valeurRubrique1;
//                    }
//
//                }
//                if (tarif_rubrique.getOperations() != null) {
//                    switch (tarif_rubrique.getOperations()) {
//                        case division:
//                            valeur = valeur1.divide(valeur2);
//                            break;
//                        case multiplication:
//                            valeur = valeur1.multiply(valeur2);
//                            break;
//                        case max:
//
//                            valeur = valeur1.max(valeur2);
//                            break;
//                        case min:
//                            valeur = valeur1.min(valeur2);
//                            break;
//                        case moins:
//                            valeur = valeur1.subtract(valeur2);
//                            break;
//                        case plus:
//
//                            valeur = valeur1.add(valeur2);
//                            break;
//                    }
//                } else {
//                    valeur = valeur1;
//                }
//                /*
//                comment gere le  cas ou tarif_rubrique.getOperations()==null
//                 */
//
//            }
//
//        }
//        valeur = etapePrincipal.multiply(valeur);
//        return valeur;
//
//    }
//
//    public BigDecimal calculValeurForSecondeRubriqueIsExiste(OrclassRubrique rubrique, OrclassGarantie g) {
//
//        BigDecimal valeurRubrique1 = BigDecimal.ZERO;
//        BigDecimal valeurRubrique2 = BigDecimal.ZERO;
//        BigDecimal valeur = BigDecimal.ZERO;
//        BigDecimal valeurCalculer = BigDecimal.ZERO;
//        BigDecimal etape_rubrique = BigDecimal.ZERO;
//        OrclassTarif tarif_rubrique = null;
//        if (Objects.equals(g.getGlobalCompagnie(), Boolean.TRUE)) {
//            rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique);
//
//            tarif_rubrique = tarifDao.lastTarif(rubriqueGarantie);
//        } else {
//            rubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieNotPrimeNetteByTarifAndCategorieForCompagnie(police.getIdTypeTarif(), categories, g, rubrique, entreprise);
//
//            tarif_rubrique = tarifDao.lastTarif(rubriqueGarantie, entreprise);
//        }
//        if (tarif_rubrique == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ERREUR SUR LA TARIFICATION NON EXISTANT ..." + g.getIdRefGaranties().getLibelle().toUpperCase()));
//            return BigDecimal.ZERO;
//        }
//        etape_rubrique = (tarif_rubrique.getValeur_Additif().add(tarif_rubrique.getCoefficient())).divide(BigDecimal.valueOf(tarif_rubrique.getRapport_Division().intValue()));
//        if (tarif_rubrique.getIdRubrique1() == null && tarif_rubrique.getIdRubrique2() == null) {
//            valeurRubrique1 = this.calculValeurForFirstRubriqueIsNotExiste(etape_rubrique, tarif_rubrique);
//            valeurRubrique2 = this.calculValeurForSecondRubriqueIsNotExiste(tarif_rubrique);
//            if (tarif_rubrique.getOperations() != null) {
//                switch (tarif_rubrique.getOperations()) {
//                    case division:
//                        valeur = valeurRubrique1.divide(valeurRubrique2);
//                        break;
//                    case multiplication:
//                        valeur = valeurRubrique1.multiply(valeurRubrique2);
//                        break;
//                    case max:
//                        valeur = valeurRubrique1.max(valeurRubrique2);
//                        break;
//                    case min:
//                        valeur = valeurRubrique1.min(valeurRubrique2);
//                        break;
//                    case moins:
//                        valeur = valeurRubrique1.subtract(valeurRubrique2);
//                        break;
//                    case plus:
//                        valeur = valeurRubrique1.add(valeurRubrique2);
//                        break;
//                }
//            }
//
//        } else if (tarif_rubrique.getIdRubrique1() == null && tarif_rubrique.getIdRubrique2() != null) {
//
//        } else if (tarif_rubrique.getIdRubrique1() != null && tarif_rubrique.getIdRubrique2() != null) {
//
//        }
//        return valeur;
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
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraByAgence(intermediaires, entreprise, branches);
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
//                refGroupeSelectForCaracteristiqueAndGarantie = new OrclasseRefGroupe();
//                refGroupeSelectForPrestation = new OrclasseRefGroupe();
////                listeRefGroup = (List<OrclasseRefGroupe>) refGroupeDao.findAll();
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
//            OrclassQuitance q = (avenant == null || avenant.getId() == null) ? quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect) : quitanceDao.quittanceForPoliceByAvenant(entreprise, policeSelect, avenant);
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
//    public void onTabChange(TabChangeEvent event) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        this.chargeFractionnementByCategories();
//        List<OrclassRefGaranties> listeRefGaranties = new ArrayList<>();
//        /*
//        verifition les champs obligatoire
//        
//        
//         */
//        if (categories == null || categories.getIdCategorie() == null) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("PLEASE SELECT CATEGORY", "TRY AGAINST"));
//            return;
//        }
//        if (police != null && police.getId() != null && Objects.equals(police.getValidation(), Boolean.TRUE)) {
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
//                } else if ((assure.getIdProfession() == null || assure.getIdProfession().getId() == null) && (Objects.equals(tabShowGroup, Boolean.FALSE))) {
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(0);");
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "personne.profession", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    return;
//
//                } else if (assure.getDate_naissance() == null && Objects.equals(tabShowGroup, Boolean.FALSE)) {
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
////            this.chargeMajorationDuree();
//            }
//
//            if (event.getTab().getId().equals("assurer")) {
//                if (risque == null) {
//                    risque = new OrclassRisque();
//
//                }
//                risque.setAdresse(assure.getAdresse());
//                risque.setDateCreation(assure.getDateCreation());
//                risque.setDateEntree(new Date());
//                risque.setDateNaissance(assure.getDate_naissance());
//                risque.setNumero_ordre(BigInteger.ONE);
//                if (assure.getIdProfession() != null) {
//                    risque.setLibelleProfession(assure.getIdProfession().getLibelle());
//                }
//                if (assure.getIdActivite() != null) {
//                    risque.setLibelleActivite(assure.getIdActivite().getLibelle());
//                }
//
//                risque.setIdVille(assure.getIdVille());
//
//                risque.setLibelle(assure.getNom() + " " + assure.getPrenom());
//                risque.setDateNaissance(assure.getDate_naissance());
//                risque.setLieu_naissance(assure.getLieu_naissance());
//                risque.setSexe(assure.getSexe());
//                risque.setModeCalcul(ModeCalcul.manuel);
////            PrimeFaces.current().ajax().update("form1:tabprincipal");
////            PrimeFaces.current().executeScript("PF('accordP').select(1);");
//
//            }
//            if (event.getTab().getId().equals("famille")) {
//                if (risque == null) {
//                    risque = new OrclassRisque();
//
//                }
//                risque.setAdresse(assure.getAdresse());
//                risque.setDateCreation(assure.getDateCreation());
//                risque.setDateEntree(assure.getDate_saisie());
//                risque.setDateNaissance(assure.getDate_naissance());
//                risque.setDateEntree(new Date());
//                risque.setModeCalcul(ModeCalcul.manuel);
////            risque.setNumero_ordre(BigInteger.ONE);
//                if (assure.getIdProfession() != null) {
//                    risque.setLibelleProfession(assure.getIdProfession().getLibelle());
//                }
//                if (assure.getIdActivite() != null) {
//                    risque.setLibelleActivite(assure.getIdActivite().getLibelle());
//                }
//
//                risque.setIdVille(assure.getIdVille());
//
//                risque.setLibelle(assure.getNom() + " " + assure.getPrenom());
//                risque.setDateNaissance(assure.getDate_naissance());
//                risque.setLieu_naissance(assure.getLieu_naissance());
//                risque.setSexe(assure.getSexe());
//
//                /*
//            charger la liste de famille pour permettre l inscription
//                 */
//                if (listeRisqueFamille.isEmpty()) {
////                for (int i = 0; i < 10; i++) {
//                    risqueFamille = new OrclassRisqueFamille();
//                    listeRisqueFamille.add(risqueFamille);
////                }
//                }
//            }
//            if (event.getTab().getId().equals("prestation")) {
//                if (Objects.equals(tabShowGroup, Boolean.TRUE) && (listeRefGroup == null || listeRefGroup.isEmpty())) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("List Group IS EMPTY", "FATAL ERROR"));
//                    PrimeFaces.current().executeScript("PF('accordP').select(1);");
//                    return;
//
//                } else if (Objects.equals(tabShowGroup, Boolean.TRUE) && (listeRefGroup != null && !listeRefGroup.isEmpty())) {
//                    refGroupeSelectForPrestation = (OrclasseRefGroupe) listeRefGroup.toArray()[0];
//                }
//                listePlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
////            plafondMaladie = new OrclassPlafondMaladie();
//                if (categories != null && categories.getIdCategorie() != null) {
//
//                    listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
////                rubriqueCategorie = new OrclassRubriqueCategorie();
//
//                }
//            }
//
//            if (event.getTab().getId().equals("caracteristique")) {
//                if (Objects.equals(tabShowGroup, Boolean.TRUE) && (listeRefGroup == null || listeRefGroup.isEmpty())) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("List Group IS EMPTY", "FATAL ERROR"));
//                    PrimeFaces.current().executeScript("PF('accordP').select(1);");
//                    return;
//
//                } else if (Objects.equals(tabShowGroup, Boolean.TRUE) && (listeRefGroup != null && !listeRefGroup.isEmpty())) {
//                    refGroupeSelectForCaracteristiqueAndGarantie = (OrclasseRefGroupe) listeRefGroup.toArray()[0];
//                }
//                if (plafondMaladie == null || plafondMaladie.getId() == null) {
//                    if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                        PrimeFaces.current().executeScript("PF('accordP').select(3);");
//                    } else {
//                        PrimeFaces.current().executeScript("PF('accordP').select(2);");
//                    }
//
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "plafond.Maladie", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    return;
//                }
//                if (rubriqueCategorie == null || rubriqueCategorie.getId() == null) {
//                    if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                        PrimeFaces.current().executeScript("PF('accordP').select(3);");
//                    } else {
//                        PrimeFaces.current().executeScript("PF('accordP').select(2);");
//                    }
//                    summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                    msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "rubrique", null, myLoc);
//                    ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                    return;
//                }
//
//                chargeCaracteristiqueRubrique();
//
//                this.updateDataTablePoliceCarzacteristique();
//                this.updateDataTableRubriqueCaracteristique();
//                PrimeFaces.current().ajax().update(":form1:tabprincipal");
//            }
////        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
////        FacesContext.getCurrentInstance().addMessage(null, msg);
//
//            if (event.getTab().getId().equals("garantie")) {
//                if (Objects.equals(tabShowGroup, Boolean.TRUE) && (listeRefGroup == null || listeRefGroup.isEmpty())) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("List Group IS EMPTY", "FATAL ERROR"));
//                    PrimeFaces.current().executeScript("PF('accordP').select(1);");
//                    return;
//
//                } else if (Objects.equals(tabShowGroup, Boolean.TRUE) && (listeRefGroup != null && !listeRefGroup.isEmpty())) {
//                    if (refGroupeSelectForGarantie == null || refGroupeSelectForGarantie.getId() == null) {
//                        this.setRefGroupeSelectForGarantie(refGroupeSelectForCaracteristiqueAndGarantie);
//                    }
//
//                }
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
//                /*
//                verication sur la duree du choix et la date d echeance
//                ggg
//                 */
//                if (Objects.equals(this.verificationDureeAndDateEffet(), Boolean.FALSE)) {
//                    PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "la date d' echeance depasse la durée  du contrat choisir..."));
//                    return;
//                }
//
//                List<OrclassGarantie> listeRubriqueGaranties = new ArrayList<>();
//                if (police.getIdTypeTarif() != null && police.getIdTypeTarif().getId() != null && rubriqueCategorie != null && rubriqueCategorie.getId() != null) {
//                    if (listePoliceGarantie.isEmpty()) {
////                        listeGarantieNonEditable = garantieDao.listeGarantieNonEditable();
//
//                        /*
//                    on recupere les garanties de natures obligatoires
//                         */
//                        policeGarantie = new OrclassPoliceGarantie();
//                        listePoliceGarantie.add(policeGarantie);
//                        listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorie(police.getIdTypeTarif(), rubriqueCategorie, entreprise);
//                        this.listeRubriqueGarantieControleChangeGroup.clear();
//                        this.listeRubriqueGarantieControleChangeGroup.addAll(listeRubriqueGarantie);
//                        for (OrclassGarantie rg : listeRubriqueGarantie) {
//                            if (rg.getNatureGarantie().equals(NatureGarantie.obligatoire)) {
//                                policeGarantie = new OrclassPoliceGarantie();
//                                policeGarantie.setIdGarantie(rg);
//                                if (rg.getModeCalcul().equals(ModeCalcul.Automatique)) {
//                                    policeGarantie.setEditer(Boolean.FALSE);
//                                    policeGarantie = this.calculPrimeAutomatiqueGarantie(policeGarantie);
////                                    policeGarantie.setPrime(this.calculPrimeAutomatiqueGarantie(rg));
////                                    policeGarantie.setProrata(policeGarantie.getPrime());
//                                } else if (rg.getModeCalcul().equals(ModeCalcul.manuel)) {
//                                    policeGarantie.setEditer(Boolean.TRUE);
//                                }
//                                System.out.println("id group garantie " + refGroupeSelectForGarantie.getId());
//                                policeGarantie.setIdGroup(refGroupeSelectForGarantie);
//                                listePoliceGarantie.add(policeGarantie);
//                                listeRubriqueGaranties.add(rg);
//                            }
//
//                        }
//
//                        listeRubriqueGarantie.removeAll(listeRubriqueGaranties);
//                        // recuperer les garenties forfaitaire ou gratuit
//                        listeGarantieHaveForfairtairOrGratuit = garantieDao.listeGarantieHaveGratuitOrForfair(entreprise, rubriqueCategorie.getIdCategories());
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
//                    if (listeRubriqueGarantie.isEmpty()) {
//                        summary = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "fatal_error", null, myLoc);
//                        msgdetail = LocaleHelper.getLocaleString(RecupBundle.FichierBundle, "garantie.rubrique.not", null, myLoc);
//                        ctx.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, summary, msgdetail));
//                        return;
//                    }
//
//                }
//                this.caculTotalPrime();
//                this.updateDataTablePoliceGaranties();
//                this.updateDataTableRubriqueGarantie();
//                PrimeFaces.current().ajax().update(":form1:tabprincipal");
//            }
//
//            if (event.getTab().getId().equals("quittance")) {
//
////                BigDecimal taxe = BigDecimal.valueOf(19.25);
//                BigDecimal total_a_paye = BigDecimal.ZERO;
//
//                if (police.getDate_effet() != null) {
//                    quitance.setDate_effet(police.getDate_effet());
//                }
//                if (police.getDate_echeance() != null) {
//                    quitance.setDate_echeance(police.getDate_echeance());
//                }
//                quitance.setDateEmission(new Date());
////                quitance.setPrimeNette(this.totalProrata);
//                if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                    quitance.setPrimeNette(this.caculTotalPrimeByForQuittance());
//
//                    try {
//                        quitance.setTaxePrime((this.caculTotalPrimeByForQuittance().multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                    } catch (Exception e) {
//                        quitance.setTaxePrime(BigDecimal.ZERO);
//                    }
//
//                } else {
//                    quitance.setPrimeNette(this.totalProrata);
//                    try {
//                        quitance.setTaxePrime((this.totalProrata.multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                    } catch (Exception e) {
//                        quitance.setTaxePrime(BigDecimal.ZERO);
//                    }
////                    quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                }
//                quitance.setMontant_Accessoire(police.getMontantaccessoir());
//                quitance.setTypQuittance(TypeQuittance.emmission);
//
//                try {
//                    quitance.setPrimeTaxe(quitance.getPrimeNette().add(quitance.getTaxePrime()));
//                } catch (Exception e) {
//                    quitance.setPrimeTaxe(BigDecimal.ZERO);
//                }
//                try {
//                    quitance.setTaxeAccessoir((quitance.getMontant_Accessoire().multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
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
//
////                if (police.getIdApporteur() != null && police.getIdApporteur().getIdApporteur() != null) {
////                    chargePrimeApproteur();adherent
////                }
//            }
//            if (event.getTab().getId().equals("adherent")) {
//                if (Objects.equals(tabShowGroup, Boolean.TRUE) && (listeRefGroup == null || listeRefGroup.isEmpty())) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("List Group IS EMPTY", "FATAL ERROR"));
//                    PrimeFaces.current().executeScript("PF('accordP').select(1);");
//                    return;
//
//                } else if (Objects.equals(tabShowGroup, Boolean.TRUE) && (listeRefGroup != null && !listeRefGroup.isEmpty())) {
//                    refGroupeSelectForAdherent = (OrclasseRefGroupe) listeRefGroup.toArray()[0];
//                    this.chargeRisqueByImageRisque();
//                }
//                if (refGroupeSelectForAdherent == null || refGroupeSelectForAdherent.getId() == null || imageRisqueSelecte == null || imageRisqueSelecte.getId() == null) {
//                    listeImageFamilleRisque.clear();
//                    this.updateTableImageFamilleRisque();
//                }
//
//            }
//            if (event.getTab().getId().equals("groupe")) {
//                if (refGroupeSelect == null || refGroupeSelect.getId() == null || imageRisqueSelecte == null || imageRisqueSelecte.getId() == null) {
//                    listeImageRisques.clear();
//                    this.updateTableImageRisque();
//                }
//
//            }
//        } //        else if(police != null && police.getId() != null && Objects.equals(police.getValidation(), Boolean.TRUE)){
//        //            if (Objects.equals(tabShowGroup, Boolean.FALSE)) {
//        //                
//        //            }
//        //            
//        //        }
//        else if (police != null && police.getId() != null && Objects.equals(tabShowGroup, Boolean.FALSE) && Objects.equals(police.getValidation(), Boolean.FALSE)) {
//            if (event.getTab().getId().equals("groupe")) {
//                refGroupeSelect = new OrclasseRefGroupe();
//                this.risque = new OrclassRisque();
//
//                listeRisque.clear();
//                this.updateTableRisque();
//                PrimeFaces.current().ajax().update("form1:tabprincipal");
//            }
//
//            if (event.getTab().getId().equals("adherent")) {
//                this.refGroupeSelectForAdherent = new OrclasseRefGroupe();
//                this.imageRisqueSelecte = new OrclassImageRisque();
//                this.risque = new OrclassRisque();
//                listeImageFamilleRisque.clear();
//                listeRisqueFamille.clear();
//                this.updateDataTableRisqueFamille();
//                PrimeFaces.current().ajax().update("form1:tabprincipal");
//            }
//
//            if (event.getTab().getId().equals("quittance") && Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                BigDecimal taxe = BigDecimal.valueOf(19.25);
//                BigDecimal total_a_paye = BigDecimal.ZERO;
//
//                if (police.getDate_effet() != null) {
//                    quitance.setDate_effet(police.getDate_effet());
//                }
//                if (police.getDate_echeance() != null) {
//                    quitance.setDate_echeance(police.getDate_echeance());
//                }
////                quitance.setDateEmission(new Date());
////                quitance.setPrimeNette(this.totalProrata);
//                if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                    quitance.setPrimeNette(this.caculTotalPrimeByForQuittance());
//
//                    try {
//                        quitance.setTaxePrime((this.caculTotalPrimeByForQuittance().multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                    } catch (Exception e) {
//                        quitance.setTaxePrime(BigDecimal.ZERO);
//                    }
//
//                } else {
//                    quitance.setPrimeNette(this.totalProrata);
//                    try {
//                        quitance.setTaxePrime((this.totalProrata.multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                    } catch (Exception e) {
//                        quitance.setTaxePrime(BigDecimal.ZERO);
//                    }
////                    quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                }
//                quitance.setMontant_Accessoire(police.getMontantaccessoir());
//                quitance.setTypQuittance(TypeQuittance.emmission);
////                try {
////                    quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
////                } catch (Exception e) {
////                    quitance.setTaxePrime(BigDecimal.ZERO);
////                }
//                try {
//                    quitance.setPrimeTaxe(quitance.getPrimeNette().add(quitance.getTaxePrime()));
//                } catch (Exception e) {
//                    quitance.setPrimeTaxe(BigDecimal.ZERO);
//                }
//                try {
//                    quitance.setTaxeAccessoir((quitance.getMontant_Accessoire().multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
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
//
////                if (police.getIdApporteur() != null && police.getIdApporteur().getIdApporteur() != null) {
////                    chargePrimeApproteur();adherent
////                }
//            }
//
//        } else {
//            if (event.getTab().getId().equals("groupe")) {
//                if (refGroupeSelect != null && refGroupeSelect.getId() != null) {
////                    OrclassRisque r = new OrclassRisque();
////                    r.setIdGroup(refGroupeSelect);
////                    listeRisque.add(r);
////                    this.reverseListeRique();
////                    this.updateTableRisque();
////                    this.getListeRefGroup();
//                }
//
//            } else if (event.getTab().getId().equals("adherent")) {
////                OrclassRisque r = listeRisque.get(0);
////                listeRisque.remove(r);
////                this.getListeRefGroup();
////                this.updateTableRisque();
//            } else if (event.getTab().getId().equals("prestation")) {
//                if (listePlafondMaladie.isEmpty()) {
//                    listePlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
//                }
//
//            } else if (event.getTab().getId().equals("garantie")) {
//                Set<OrclassGarantie> set = new HashSet<OrclassGarantie>();
//                if (listeRubriqueGarantie.isEmpty() && refGroupeSelectForGarantie != null && refGroupeSelectForGarantie.getId() != null) {
//
//                    listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieForGroup(police.getIdTypeTarif(), police.getIdCategories(), entreprise, refGroupeSelectForGarantie);
//                    set.addAll(listeRubriqueGarantie);
//                    listeRubriqueGarantie.clear();
//                    for (OrclassGarantie sg : set) {
//                        if (listeRubriqueGarantie.contains(sg) == Boolean.FALSE) {
//                            if (listeRefGaranties.contains(sg.getIdRefGaranties()) == Boolean.FALSE) {
//                                listeRefGaranties.add(sg.getIdRefGaranties());
//                                listeRubriqueGarantie.add(sg);
//                            }
////                            listeRubriqueGarantie.add(sg);
//                        }
//                    }
//                }
//            }
//            if (event.getTab().getId().equals("quittance")) {
////                BigDecimal taxe = BigDecimal.valueOf(19.25);
//                BigDecimal total_a_paye = BigDecimal.ZERO;
//                if (police.getDate_effet() != null) {
//                    quitance.setDate_effet(police.getDate_effet());
//                }
//                if (police.getDate_echeance() != null) {
//                    quitance.setDate_echeance(police.getDate_echeance());
//                }
//                if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                    quitance.setPrimeNette(this.caculTotalPrimeByForQuittance());
//
//                    try {
//                        quitance.setTaxePrime((this.caculTotalPrimeByForQuittance().multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                    } catch (Exception e) {
//                        quitance.setTaxePrime(BigDecimal.ZERO);
//                    }
//
//                } else {
//                    quitance.setPrimeNette(this.totalProrata);
//                    try {
//                        quitance.setTaxePrime((this.totalProrata.multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                    } catch (Exception e) {
//                        quitance.setTaxePrime(BigDecimal.ZERO);
//                    }
////                    quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//
//                    quitance.setMontant_Accessoire(police.getMontantaccessoir());
//                    quitance.setTypQuittance(TypeQuittance.emmission);
////                try {
////                    quitance.setTaxePrime((this.totalProrata.multiply(taxe)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
////                } catch (Exception e) {
////                    quitance.setTaxePrime(BigDecimal.ZERO);
////                }
//                    try {
//                        quitance.setPrimeTaxe(quitance.getPrimeNette().add(quitance.getTaxePrime()));
//                    } catch (Exception e) {
//                        quitance.setPrimeTaxe(BigDecimal.ZERO);
//                    }
//                    try {
//                        quitance.setTaxeAccessoir((quitance.getMontant_Accessoire().multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//                    } catch (Exception e) {
//                        quitance.setTaxeAccessoir(BigDecimal.ZERO);
//                    }
//                    try {
//                        quitance.setAccessoirTaxe(quitance.getMontant_Accessoire().add(quitance.getTaxeAccessoir()));
//                    } catch (Exception e) {
//                        quitance.setAccessoirTaxe(BigDecimal.ZERO);
//                    }
//
//                    quitance.setTotalTaxes(quitance.getTaxePrime().add(quitance.getTaxeAccessoir()));
//                    quitance.setTimbreGradue(BigDecimal.ZERO);
//                    quitance.setTimbreDimension(BigDecimal.ZERO);
//                    try {
//                        if (quitance.getMontant_Accessoire() == null) {
//                            quitance.setMontant_Accessoire(BigDecimal.ZERO);
//                            quitance.setTaxeAccessoir(BigDecimal.ZERO);
//                        }
//                        if (quitance.getTimbreDimension() == null) {
//                            quitance.setTimbreDimension(BigDecimal.ZERO);
//                        }
//                        if (quitance.getTimbreGradue() == null) {
//                            quitance.setTimbreGradue(BigDecimal.ZERO);
//                        }
//
//                        total_a_paye = quitance.getPrimeNette().add(quitance.getTaxePrime()).add(quitance.getMontant_Accessoire()).add(quitance.getTaxeAccessoir()).add(quitance.getTimbreDimension())
//                                .add(quitance.getTimbreGradue());
//                        quitance.setTotal_a_paye(total_a_paye);
//                    } catch (Exception e) {
//                        total_a_paye = BigDecimal.ZERO;
//
//                    }
//                }
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
//                if (numero_police != null && numero_police.intValue() != 0 && police.getId() == null) {
//                    results = imageRisqueDao.getAssueRaisonSocialWithFilters(entreprise, query.toUpperCase());
//                } else {
//                    results = assureDao.getAssueRaisonSocialWithFilters(entreprise, query.toUpperCase());
//                }
//
//            } else {
//                if (numero_police != null && numero_police.intValue() != 0 && police.getId() == null) {
//                    results = imageRisqueDao.getAssueRaisonSocialWithFilters(entreprise, query.toUpperCase());
//                } else {
//                    results = assureDao.getAssuerCodeWithFilters(entreprise, query.toUpperCase());
//                }
//
//            }
//        }
//
//        return results;
//    }
//
//    public List<String> completeTextReferenceGroup(String query) {
//        List<String> results = new ArrayList<>();
//        if (query != null && !query.trim().equals("")) {
//            results = refGroupeDao.getReferenceGroupByLibelleithFilters(query.toUpperCase());
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
//    public void chargeContractSelecte() {
//
//        if (policeSelect != null && policeSelect.getNumero_police() != null) {//
//            if (categories == null || categories.getIdCategorie() == null) {
//                categories = policeSelect.getIdCategories();
//            }
//            avenant = avenantDao.lastAvenantByPolice(policeSelect, entreprise);
//            this.chargeTvaForQuittance();
//            elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(policeSelect.getIdCategories(), entreprise);
//            OrclasseRefGroupe group = null;
//            List<OrclassGroupePolice> gps = new ArrayList<>();
//            if (avenant != null && avenant.getId() != null) {
//                switch (elt_Categorie_Compagnie.getRisque()) {
//                    case groupe:
//                        listeRefGroup = (List<OrclasseRefGroupe>) risqueDao.listeRiefeGrouByPoliceINRisqueHaveAvenant(entreprise, policeSelect, categories);
//                        this.setPolice(policeSelect);
//                        this.setAssure(policeSelect.getIdAssure());
////            this.setIntermediaires(policeSelect.getIdIntermediaire());
//                        this.setApporteur(policeSelect.getIdApporteur());
//                        this.setCategories(policeSelect.getIdCategories());
////                this.setQuitance(quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect));
//                        if (avenant.getIdMajorationDuree() != null && avenant.getIdMajorationDuree().getId() != null) {
//                            police.setIdMajorationDuree(avenant.getIdMajorationDuree());
//                            police.setValeurDuree(avenant.getValeurDuree());
//                            this.setDuree(avenant.getIdMajorationDuree().getIdDuree());
//                        }
//
//                        this.chargeMajoratioDureeByDuree();
////            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
//                        this.setNumero_police(policeSelect.getPolice().toBigInteger());
////                        if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
////                            this.setNumero_police(policeSelect.getPolice().toBigInteger());
////                        } else {
////                            this.setNumero_police(policeSelect.getNumero_police());
////                        }
//
//                        this.choixTabView(elt_Categorie_Compagnie);
////                        this.setDuree((avenant != null && avenant.getId() != null && avenant.getIdMajorationDuree() != null) ? avenant.getIdMajorationDuree().getIdDuree() : policeSelect.getIdMajorationDuree().getIdDuree());
////                        this.chargeMajorationDuree();
//                        group = listeRefGroup.get(0);
//                        this.setRefGroupeSelect(group);
//                        //charges les risque en fonction du groupe
////                        this.chargeRisqueByGroup();hh
//                        this.setRefGroupeSelectForAdherent(group);
//                        //charger les adherents en fonction du groupe
//
////                        this.chargeImageRisqueFamilleByImageRisque();
//                        this.setRefGroupeSelectForPrestation(group);
//                        this.setRefGroupeSelectForCaracteristiqueAndGarantie(group);
//                        this.setRefGroupeSelectForGarantie(group);
//                        listeRisque = risqueDao.listeRisqueByGroupe(entreprise, group, police);
//                        listePoliceCaracteristique = policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, police, group);
//                        listePoliceGarantie = policeGarantieDao.allGarantiesByPolice(entreprise, police, group);
//                        listeDetailPolicePlafond = detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police, group);
//                        if (!listeRefGroup.isEmpty()) {
//                            //ici l enregistrement n apas ete fait en groupe police juste en un groupe
//
////                            this.setListePoliceCaracteristique((List<OrclassPoliceCaracteristique>) policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect));
////                            if (listePoliceCaracteristique == null) {
////                                listePoliceCaracteristique = new ArrayList<>();
////                            }
////                            this.setListePoliceGarantie((List<OrclassPoliceGarantie>) policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect));
////                            if (listePoliceGarantie == null) {
////                                listePoliceGarantie = new ArrayList<>();
////                            }
////                            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                                policeCaracteristique = new OrclassPoliceCaracteristique();
////                                listePoliceCaracteristique.add(policeCaracteristique);
////                                this.listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, categories);
////                                this.reverseListeCaracteristique();
////                                policeGarantie = new OrclassPoliceGarantie();
////                                listePoliceGarantie.add(policeGarantie);
////                                this.listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieNOtExistePoliceGarantie(policeSelect.getIdTypeTarif(), categories, entreprise);
////                                this.reverseListeGarantie();
////                            }
////           
//                            if (avenant.getDesignationPlafonMaladie() != null) {
//                                this.setDesignationPlafondMaladie(avenant.getDesignationPlafonMaladie());
//                            } else {
//                                this.setDesignationPlafondMaladie(policeSelect.getDesignationPlafonMaladie());
//                            }
////                            this.setPlafondMaladie(policePlafondDao.chargePlafonMaladie(police, entreprise, group));
////                            if (plafondMaladie == null) {
////                                plafondMaladie = new OrclassPlafondMaladie();
////                            }
//                            this.listePlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
////                            this.setListeDetailPolicePlafond(detailPolicePlafondDao.listeDetailPlafond(policeSelect));
////                            if (listeDetailPolicePlafond == null) {
////                                listeDetailPolicePlafond = new ArrayList<>();
////                            }
//
//                            if (!listeDetailPolicePlafond.isEmpty()) {
//                                detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                                this.listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                                this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                                plafondMaladie = detailPolicePlafond.getIdPolicePlafond().getIdPlafondMaladie();
//                            }
////                            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                                detailPolicePlafond = new OrclassDetailPolicePlafond();
////                                listeDetailPolicePlafond.add(detailPolicePlafond);
////                                this.listePrestation = prestationDao.listePrestationtNotExisteForRubriquePrestation(rubriqueCategorie, entreprise);
////                                this.reverseListe();
////                            }
//
//                            this.controlleElementHaveAvenant();
//                            this.risque = listeRisque.get(1);
//
//                        }
////                        else {
////                            if (avenant != null && avenant.getId() != null) {
////                                this.controlleElementHaveAvenant();
////                            }
////                            this.chargeListePlafondAndPrestationByGroup();
////                            // charger les caracteristiques
////
////                            this.chargeListeCaracteristique();
////                            
////                            this.chargeListeGarantie();
////                            
////                        }
//
////                        refGroupe = new OrclasseRefGroupe();
////                        group = new OrclasseRefGroupe();
//                        break;
//                    case famille:
//                        this.setPolice(policeSelect);
//                        this.setAssure(policeSelect.getIdAssure());
////            this.setIntermediaires(policeSelect.getIdIntermediaire());
//                        this.setApporteur(policeSelect.getIdApporteur());
//                        this.setCategories(policeSelect.getIdCategories());
//                        this.setQuitance(quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect));
//                        listePoliceCaracteristique = policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, police);
//                        listePoliceGarantie = policeGarantieDao.allGarantiesByPolice(entreprise, police);
//                        listeDetailPolicePlafond = detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police);
//
//                        this.chargeMajoratioDureeByDuree();
//                        if (avenant.getIdMajorationDuree() != null && avenant.getIdMajorationDuree() != null) {
//                            police.setIdMajorationDuree(avenant.getIdMajorationDuree());
//                            police.setValeurDuree(avenant.getValeurDuree());
//                            this.setDuree(police.getIdMajorationDuree().getIdDuree());
//                        }
////                        if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                            policeCaracteristique = new OrclassPoliceCaracteristique();
////                            listePoliceCaracteristique.add(policeCaracteristique);
////                            this.listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, categories);
////                            this.reverseListeCaracteristique();
////                            policeGarantie = new OrclassPoliceGarantie();
////                            listePoliceGarantie.add(policeGarantie);
////                            this.listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieNOtExistePoliceGarantie(policeSelect.getIdTypeTarif(), categories, entreprise);
////                            this.reverseListeGarantie();
////                        }
////            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
//                        this.setNumero_police(policeSelect.getPolice().toBigInteger());
////                        if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
////                            this.setNumero_police(policeSelect.getPolice().toBigInteger());
////                        } else {
////                            this.setNumero_police(policeSelect.getNumero_police());
////                        }
//
//                        this.choixTabView(elt_Categorie_Compagnie);
//                        if (avenant.getDesignationPlafonMaladie() != null) {
//                            this.setDesignationPlafondMaladie(avenant.getDesignationPlafonMaladie());
//                        } else {
//                            this.setDesignationPlafondMaladie(policeSelect.getDesignationPlafonMaladie());
//                        }
//                        this.listePlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
//
////                        this.setPlafondMaladie(policePlafondDao.chargePlafonMaladie(policeSelect, entreprise));
////                        if (plafondMaladie == null) {
////                            plafondMaladie = new OrclassPlafondMaladie();
////                        }
////                        this.setListeDetailPolicePlafond(detailPolicePlafondDao.listeDetailPlafond(policeSelect));
////                        if (listeDetailPolicePlafond == null) {
////                            listeDetailPolicePlafond = new ArrayList<>();
////                        }
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                            this.listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                            this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                            plafondMaladie = detailPolicePlafond.getIdPolicePlafond().getIdPlafondMaladie();
//                        }
////                        if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                            detailPolicePlafond = new OrclassDetailPolicePlafond();
////                            listeDetailPolicePlafond.add(detailPolicePlafond);
////                            this.listePrestation = prestationDao.listePrestationtNotExisteForRubriquePrestation(rubriqueCategorie, entreprise);
////                            this.reverseListe();
////                        }
////                        this.chargeMajorationDuree();
//
////                listeRisque = (List<OrclassRisque>) risqueDao.findAllEntitiesHavingValue("idPolice", policeSelect);
//                        listeImageFamilleRisque.clear();
////            } else {
//                        this.setRisque(risqueDao.risqueByPolice(entreprise, policeSelect));
//
//                        this.setListeRisqueFamille((List<OrclassRisqueFamille>) risqueFamilleDao.allRisqueFamilleByPolice(entreprise, policeSelect, risque));
//
////                        listeRisqueFamille.add(new OrclassRisqueFamille());
////                        this.reverseListeFamilleRique();
//                        //            }eee
//                        this.setListePlafondMaladie(plafondMaladieDao.listePlafondMaladie(entreprise));
//
//                        this.controlleElementHaveAvenant();
//
//                        this.caculTotalPrime();
//                        this.calculTotalProrata();
//                        break;
//
//                    case individuel:
//
//                        this.setPolice(policeSelect);
//                        this.setAssure(policeSelect.getIdAssure());
//
//                        this.setApporteur(policeSelect.getIdApporteur());
//                        this.setCategories(policeSelect.getIdCategories());
//                        this.setQuitance(quitanceDao.quittanceForPoliceByAvenant(entreprise, policeSelect, avenant));
//                        listePoliceCaracteristique = policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, police);
//                        listePoliceGarantie = policeGarantieDao.allGarantiesByPolice(entreprise, police);
//                        listeDetailPolicePlafond = detailPolicePlafondDao.allDetailPlafondPolice(entreprise, police);
//
//                        this.chargeMajoratioDureeByDuree();
//                        if (avenant.getIdMajorationDuree() != null && avenant.getIdMajorationDuree() != null) {
//                            police.setIdMajorationDuree(avenant.getIdMajorationDuree());
//                            police.setValeurDuree(avenant.getValeurDuree());
//                            this.setDuree(police.getIdMajorationDuree().getIdDuree());
//                        }
////                        if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                            policeCaracteristique = new OrclassPoliceCaracteristique();
////                            listePoliceCaracteristique.add(policeCaracteristique);
////                            this.listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, categories);
////                            this.reverseListeCaracteristique();
////                            policeGarantie = new OrclassPoliceGarantie();
////                            listePoliceGarantie.add(policeGarantie);
//////                            this.listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieNOtExistePoliceGarantie(policeSelect.getIdTypeTarif(), categories, entreprise);
//////                            this.reverseListeGarantie();
////                        }
//                        listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
//                        this.setNumero_police(policeSelect.getPolice().toBigInteger());
////                        if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
////                            this.setNumero_police(policeSelect.getPolice().toBigInteger());
////                        } else {
////                            this.setNumero_police(policeSelect.getNumero_police());
////                        }
//
//                        this.choixTabView(elt_Categorie_Compagnie);
//                        if (avenant.getDesignationPlafonMaladie() != null) {
//                            this.setDesignationPlafondMaladie(avenant.getDesignationPlafonMaladie());
//                        } else {
//                            this.setDesignationPlafondMaladie(policeSelect.getDesignationPlafonMaladie());
//                        }
//
//                        this.setPlafondMaladie(policePlafondDao.chargePlafonMaladie(policeSelect, entreprise));
//                        this.setListePlafondMaladie(plafondMaladieDao.listePlafondMaladie(entreprise));
////                        if (plafondMaladie == null) {
////                            plafondMaladie = new OrclassPlafondMaladie();
////                        }
////                        this.setListeDetailPolicePlafond(detailPolicePlafondDao.listeDetailPlafond(policeSelect));
////                        if (listeDetailPolicePlafond == null) {
////                            listeDetailPolicePlafond = new ArrayList<>();
////                        }
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                            this.listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                            this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
////                            plafondMaladie = detailPolicePlafond.getPlafondMaladie();
//                        }
////                        if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                            detailPolicePlafond = new OrclassDetailPolicePlafond();
////                            listeDetailPolicePlafond.add(detailPolicePlafond);
////                            this.listePrestation = prestationDao.listePrestationtNotExisteForRubriquePrestation(rubriqueCategorie, entreprise);
////                            this.reverseListe();
////                        }
//                        this.chargeMajorationDuree();
//
////                listeRisque = (List<OrclassRisque>) risqueDao.findAllEntitiesHavingValue("idPolice", policeSelect);
////                        listeImageFamilleRisque.clear();
////            } else {
//                        this.setRisque(risqueDao.risqueByPolice(entreprise, policeSelect));
//
////                        this.setListeRisqueFamille((List<OrclassRisqueFamille>) risqueFamilleDao.allRisqueFamilleByPolice(entreprise, policeSelect, risque));
////                        listeRisqueFamille.add(new OrclassRisqueFamille());
////                        this.reverseListeFamilleRique();
//                        //            }eee
//                        this.setListePlafondMaladie(plafondMaladieDao.listePlafondMaladie(entreprise));
//
//                        this.controlleElementHaveAvenant();
//
//                        this.caculTotalPrime();
//                        this.calculTotalProrata();
//                        break;
//
//                }
//
//            } else {
//                switch (elt_Categorie_Compagnie.getRisque()) {
//                    case groupe:
//                        listeRefGroup = (List<OrclasseRefGroupe>) risqueDao.listeRiefeGrouByPolice(entreprise, policeSelect, categories);
//                        this.setPolice(policeSelect);
//                        this.setAssure(policeSelect.getIdAssure());
////            this.setIntermediaires(policeSelect.getIdIntermediaire());
//                        this.setApporteur(policeSelect.getIdApporteur());
//                        this.setCategories(policeSelect.getIdCategories());
////                this.setQuitance(quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect));
//                        this.chargeMajoratioDureeByDuree();
////            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
//                        if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
//                            this.setNumero_police(policeSelect.getPolice().toBigInteger());
//                        } else {
//                            this.setNumero_police(policeSelect.getNumero_police());
//                        }
//
//                        this.choixTabView(elt_Categorie_Compagnie);
//                        this.setDuree(policeSelect.getIdMajorationDuree().getIdDuree());
//                        this.chargeMajorationDuree();
//                        group = listeRefGroup.get(0);
//                        this.setRefGroupeSelect(group);
//                        //charges les risque en fonction du groupe
//                        this.chargeRisqueByGroup();
//                        this.setRefGroupeSelectForAdherent(group);
//                        //charger les adherents en fonction du groupe
//                        this.risque = listeRisque.get(1);
//                        this.chargeImageRisqueFamilleByImageRisque();
//                        this.setRefGroupeSelectForPrestation(group);
//                        this.setRefGroupeSelectForCaracteristiqueAndGarantie(group);
//                        this.setRefGroupeSelectForGarantie(group);
//                        if (!listeRefGroup.isEmpty()) {
//                            //ici l enregistrement n apas ete fait en groupe police juste en un groupe
//
//                            this.setListePoliceCaracteristique((List<OrclassPoliceCaracteristique>) policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect));
//                            if (listePoliceCaracteristique == null) {
//                                listePoliceCaracteristique = new ArrayList<>();
//                            }
//                            this.setListePoliceGarantie((List<OrclassPoliceGarantie>) policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect));
//                            if (listePoliceGarantie == null) {
//                                listePoliceGarantie = new ArrayList<>();
//                            }
//                            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                                policeCaracteristique = new OrclassPoliceCaracteristique();
//                                listePoliceCaracteristique.add(policeCaracteristique);
//                                this.listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, categories);
//                                this.reverseListeCaracteristique();
//                                policeGarantie = new OrclassPoliceGarantie();
//                                listePoliceGarantie.add(policeGarantie);
//                                this.listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieNOtExistePoliceGarantie(policeSelect.getIdTypeTarif(), categories, entreprise);
//                                this.reverseListeGarantie();
//                            }
////           
//
////                            this.setPlafondMaladie(policePlafondDao.chargePlafonMaladie(policeSelect, entreprise));
////                            if (plafondMaladie == null) {
////                                plafondMaladie = new OrclassPlafondMaladie();
////                            }
//                            this.setListeDetailPolicePlafond(detailPolicePlafondDao.listeDetailPlafond(policeSelect));
//                            if (listeDetailPolicePlafond == null) {
//                                listeDetailPolicePlafond = new ArrayList<>();
//                            }
//
//                            if (!listeDetailPolicePlafond.isEmpty()) {
//                                detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                                this.listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                                this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                                plafondMaladie = detailPolicePlafond.getIdPolicePlafond().getIdPlafondMaladie();
//                            }
////                            if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                                detailPolicePlafond = new OrclassDetailPolicePlafond();
////                                listeDetailPolicePlafond.add(detailPolicePlafond);
////                                this.listePrestation = prestationDao.listePrestationtNotExisteForRubriquePrestation(rubriqueCategorie, entreprise);
////                                this.reverseListe();
////                            }
//
//                        }
//                        break;
//                    case famille:
//
//                        this.setPolice(policeSelect);
//                        this.setAssure(policeSelect.getIdAssure());
//
//                        this.setApporteur(policeSelect.getIdApporteur());
//                        this.setCategories(policeSelect.getIdCategories());
//                        this.setQuitance(quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect));
//
//                        this.setListePoliceCaracteristique((List<OrclassPoliceCaracteristique>) policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprise, policeSelect));
//                        if (listePoliceCaracteristique == null) {
//                            listePoliceCaracteristique = new ArrayList<>();
//                        }
//                        this.setListePoliceGarantie((List<OrclassPoliceGarantie>) policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, policeSelect));
//                        if (listePoliceGarantie == null) {
//                            listePoliceGarantie = new ArrayList<>();
//                        }
//                        this.chargeMajoratioDureeByDuree();
//                        if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                            policeCaracteristique = new OrclassPoliceCaracteristique();
//                            listePoliceCaracteristique.add(policeCaracteristique);
//                            this.listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, categories);
//                            this.reverseListeCaracteristique();
//                            policeGarantie = new OrclassPoliceGarantie();
//                            listePoliceGarantie.add(policeGarantie);
//                            this.listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieNOtExistePoliceGarantie(policeSelect.getIdTypeTarif(), categories, entreprise);
//                            this.reverseListeGarantie();
//                        }
////            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
//                        if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
//                            this.setNumero_police(policeSelect.getPolice().toBigInteger());
//                        } else {
//                            this.setNumero_police(policeSelect.getNumero_police());
//                        }
//
//                        this.choixTabView(elt_Categorie_Compagnie);
//                        this.setDuree(policeSelect.getIdMajorationDuree().getIdDuree());
//                        this.setDesignationPlafondMaladie(policeSelect.getDesignationPlafonMaladie());
//                        this.listePlafondMaladie = plafondMaladieDao.listePlafondMaladie(entreprise);
////                        this.setPlafondMaladie(policePlafondDao.chargePlafonMaladie(policeSelect, entreprise));
////                        if (plafondMaladie == null) {
////                            plafondMaladie = new OrclassPlafondMaladie();
////                        }
//                        this.setListeDetailPolicePlafond(detailPolicePlafondDao.listeDetailPlafond(policeSelect));
//                        if (listeDetailPolicePlafond == null) {
//                            listeDetailPolicePlafond = new ArrayList<>();
//                        }
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                            this.listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                            this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                            plafondMaladie = detailPolicePlafond.getIdPolicePlafond().getIdPlafondMaladie();
//                        }
//                        if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
//                            detailPolicePlafond = new OrclassDetailPolicePlafond();
//                            listeDetailPolicePlafond.add(detailPolicePlafond);
//                            this.listePrestation = prestationDao.listePrestationtNotExisteForRubriquePrestation(rubriqueCategorie, entreprise);
//                            this.reverseListe();
//                        }
//                        this.chargeMajorationDuree();
//
////                listeRisque = (List<OrclassRisque>) risqueDao.findAllEntitiesHavingValue("idPolice", policeSelect);
//                        listeImageFamilleRisque.clear();
////            } else {
//                        this.setRisque(risqueDao.risqueByPolice(entreprise, policeSelect));
//
//                        this.setListeRisqueFamille((List<OrclassRisqueFamille>) risqueFamilleDao.listeRisqueFamilleByPoliceNotHaveAvenant(entreprise, policeSelect));
//                        if (Objects.equals(policeSelect.getValidation(), Boolean.FALSE)) {
//                            listeRisqueFamille.add(new OrclassRisqueFamille());
//                            this.reverseListeFamilleRique();
//                        }
//                        //            }
//
//                        this.setListePlafondMaladie(plafondMaladieDao.listePlafondMaladie(entreprise));
//
//                        this.caculTotalPrime();
//                        this.calculTotalProrata();
//
//                        this.updateDataTablePoliceCarzacteristique();
//                        this.updateDataTableRisqueFamille();
//
//                        PrimeFaces.current().executeScript("PF('managePoliceDialog').hide();");
//                        PrimeFaces.current().ajax().update(":form1");
//                        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//
//                        break;
//
//                    case individuel:
//
//                        this.setPolice(policeSelect);
//                        this.setAssure(policeSelect.getIdAssure());
//
//                        this.setApporteur(policeSelect.getIdApporteur());
//                        this.setCategories(policeSelect.getIdCategories());
//                        this.setQuitance(quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect));
//
//                        this.setListePoliceCaracteristique((List<OrclassPoliceCaracteristique>) policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprise, policeSelect));
//                        if (listePoliceCaracteristique == null) {
//                            listePoliceCaracteristique = new ArrayList<>();
//                        }
//                        this.setListePoliceGarantie((List<OrclassPoliceGarantie>) policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, policeSelect));
//                        if (listePoliceGarantie == null) {
//                            listePoliceGarantie = new ArrayList<>();
//                        }
//                        this.chargeMajoratioDureeByDuree();
////                        if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                            policeCaracteristique = new OrclassPoliceCaracteristique();
////                            listePoliceCaracteristique.add(policeCaracteristique);
////                            this.listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, categories);
////                            this.reverseListeCaracteristique();
////                            policeGarantie = new OrclassPoliceGarantie();
////                            listePoliceGarantie.add(policeGarantie);
////                            this.listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieNOtExistePoliceGarantie(policeSelect.getIdTypeTarif(), categories, entreprise);
////                            this.reverseListeGarantie();
////                        }
////            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
//                        if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
//                            this.setNumero_police(policeSelect.getPolice().toBigInteger());
//                        } else {
//                            this.setNumero_police(policeSelect.getNumero_police());
//                        }
//
//                        this.choixTabView(elt_Categorie_Compagnie);
//                        this.setDuree(policeSelect.getIdMajorationDuree().getIdDuree());
//                        this.setDesignationPlafondMaladie(policeSelect.getDesignationPlafonMaladie());
//
////                        this.setPlafondMaladie(policePlafondDao.chargePlafonMaladie(policeSelect, entreprise));
////                        if (plafondMaladie == null) {
////                            plafondMaladie = new OrclassPlafondMaladie();
////                        }
//                        this.listePlafondMaladie=plafondMaladieDao.listePlafondMaladie(entreprise);
//                        this.setListeDetailPolicePlafond(detailPolicePlafondDao.listeDetailPlafond(policeSelect));
//                        if (listeDetailPolicePlafond == null) {
//                            listeDetailPolicePlafond = new ArrayList<>();
//                        }
//                        if (!listeDetailPolicePlafond.isEmpty()) {
//                            detailPolicePlafond = listeDetailPolicePlafond.get(0);
//                            this.listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
//                            this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
//                            plafondMaladie = detailPolicePlafond.getIdPolicePlafond().getIdPlafondMaladie();
//                        }
////                        if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                            detailPolicePlafond = new OrclassDetailPolicePlafond();
////                            listeDetailPolicePlafond.add(detailPolicePlafond);
////                            this.listePrestation = prestationDao.listePrestationtNotExisteForRubriquePrestation(rubriqueCategorie, entreprise);
////                            this.reverseListe();
////                        }
//                        this.chargeMajorationDuree();
//
////                listeRisque = (List<OrclassRisque>) risqueDao.findAllEntitiesHavingValue("idPolice", policeSelect);
//                        listeImageFamilleRisque.clear();
////            } else {
//                        this.setRisque(risqueDao.risqueByPolice(entreprise, policeSelect));
//
//                        this.setListeRisqueFamille((List<OrclassRisqueFamille>) risqueFamilleDao.listeRisqueFamilleByPoliceNotHaveAvenant(entreprise, policeSelect));
//                        if (Objects.equals(policeSelect.getValidation(), Boolean.FALSE)) {
//                            listeRisqueFamille.add(new OrclassRisqueFamille());
//                            this.reverseListeFamilleRique();
//                        }
//                        //            }
//
//                        this.setListePlafondMaladie(plafondMaladieDao.listePlafondMaladie(entreprise));
//
//                        this.caculTotalPrime();
//                        this.calculTotalProrata();
//
//                        this.updateDataTablePoliceCarzacteristique();
//                        this.updateDataTableRisqueFamille();
//
//                        PrimeFaces.current().executeScript("PF('managePoliceDialog').hide();");
//                        PrimeFaces.current().ajax().update(":form1");
//                        PrimeFaces.current().ajax().update(":form1:tabprincipal");
//                        break;
//                }
//            }
////            if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//////                listeRefGroup.clear();
//////                try {
//////                    gps = new ArrayList<>(groupePoliceDao.ListeGroupByCompagnieForPolice(entreprise, policeSelect));
//////                    for (OrclassGroupePolice gp : gps) {
//////                        refGroupe = gp.getIdRefGroupe();
//////                        if (gp.getIdVille() != null && gp.getIdVille().getId() != null) {
//////                            refGroupe.setVille(gp.getIdVille());
//////                        }
//////                        if (gp.getAdresseGroup() != null) {
//////                            refGroupe.setAdresseGroup(gp.getAdresseGroup());
//////                        }
//////                        if (gp.getObservation() != null) {
//////                            refGroupe.setObservation(gp.getObservation());
//////                        }
//////                        listeRefGroup.add(refGroupe);
//////
//////                    }
//////                    if (listeRefGroup.size() != risqueDao.listeRiefeGrouByPolice(entreprise, policeSelect, policeSelect.getIdCategories()).size()) {
//////                        listeRefGroup = (List<OrclasseRefGroupe>) risqueDao.listeRiefeGrouByPolice(entreprise, policeSelect, policeSelect.getIdCategories());
//////
//////                    }
//////                } catch (Exception e) {
//////                    listeRefGroup = (List<OrclasseRefGroupe>) risqueDao.listeRiefeGrouByPolice(entreprise, policeSelect, policeSelect.getIdCategories());
//////
//////                }
//////                if (listeRefGroup.isEmpty() && gps.isEmpty()) {
//////                    listeRefGroup = (List<OrclasseRefGroupe>) risqueDao.listeRiefeGrouByPolice(entreprise, policeSelect, policeSelect.getIdCategories());
//////
//////                }
////                listeRefGroup = (List<OrclasseRefGroupe>) risqueDao.listeRiefeGrouByPolice(entreprise, policeSelect, categories);
////                this.setPolice(policeSelect);
////                this.setAssure(policeSelect.getIdAssure());
//////            this.setIntermediaires(policeSelect.getIdIntermediaire());
////                this.setApporteur(policeSelect.getIdApporteur());
////                this.setCategories(policeSelect.getIdCategories());
//////                this.setQuitance(quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect));
////                this.chargeMajoratioDureeByDuree();
//////            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
////                if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
////                    this.setNumero_police(policeSelect.getPolice().toBigInteger());
////                } else {
////                    this.setNumero_police(policeSelect.getNumero_police());
////                }
////
////                this.choixTabView(elt_Categorie_Compagnie);
////                this.setDuree((avenant != null && avenant.getId() != null && avenant.getIdMajorationDuree() != null) ? avenant.getIdMajorationDuree().getIdDuree() : policeSelect.getIdMajorationDuree().getIdDuree());
////                this.chargeMajorationDuree();
////                group = listeRefGroup.get(0);
////                this.setRefGroupeSelect(group);
////                //charges les risque en fonction du groupe
////                this.chargeRisqueByGroup();
////                this.setRefGroupeSelectForAdherent(group);
////                //charger les adherents en fonction du groupe
////                this.risque = listeRisque.get(1);
////                this.chargeImageRisqueFamilleByImageRisque();
////                this.setRefGroupeSelectForPrestation(group);
////                this.setRefGroupeSelectForCaracteristiqueAndGarantie(group);
////                this.setRefGroupeSelectForGarantie(group);
////                if (!listeRefGroup.isEmpty()) {
////                    //ici l enregistrement n apas ete fait en groupe police juste en un groupe
////
////                    this.setListePoliceCaracteristique((List<OrclassPoliceCaracteristique>) policeCaracteristiqueDao.allPoliciCaracteristique(entreprise, policeSelect));
////                    if (listePoliceCaracteristique == null) {
////                        listePoliceCaracteristique = new ArrayList<>();
////                    }
////                    this.setListePoliceGarantie((List<OrclassPoliceGarantie>) policeGarantieDao.allGarantiesByPolice(entreprise, policeSelect));
////                    if (listePoliceGarantie == null) {
////                        listePoliceGarantie = new ArrayList<>();
////                    }
////                    if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                        policeCaracteristique = new OrclassPoliceCaracteristique();
////                        listePoliceCaracteristique.add(policeCaracteristique);
////                        this.listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, categories);
////                        this.reverseListeCaracteristique();
////                        policeGarantie = new OrclassPoliceGarantie();
////                        listePoliceGarantie.add(policeGarantie);
////                        this.listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieNOtExistePoliceGarantie(policeSelect.getIdTypeTarif(), categories, entreprise);
////                        this.reverseListeGarantie();
////                    }
//////           
////
////                    this.setPlafondMaladie(policePlafondDao.chargePlafonMaladie(policeSelect, entreprise));
////                    if (plafondMaladie == null) {
////                        plafondMaladie = new OrclassPlafondMaladie();
////                    }
////                    this.setListeDetailPolicePlafond(detailPolicePlafondDao.listeDetailPlafond(policeSelect));
////                    if (listeDetailPolicePlafond == null) {
////                        listeDetailPolicePlafond = new ArrayList<>();
////                    }
////
////                    if (!listeDetailPolicePlafond.isEmpty()) {
////                        detailPolicePlafond = listeDetailPolicePlafond.get(0);
////                        this.listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
////                        this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
////                    }
////                    if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                        detailPolicePlafond = new OrclassDetailPolicePlafond();
////                        listeDetailPolicePlafond.add(detailPolicePlafond);
////                        this.listePrestation = prestationDao.listePrestationtNotExisteForRubriquePrestation(rubriqueCategorie, entreprise);
////                        this.reverseListe();
////                    }
////                    if (avenant != null && avenant.getId() != null) {
////                        this.controlleElementHaveAvenant();
////                    }
////
////                } else {
////                    if (avenant != null && avenant.getId() != null) {
////                        this.controlleElementHaveAvenant();
////                    }
////                    this.chargeListePlafondAndPrestationByGroup();
////                    // charger les caracteristiques
////
////                    this.chargeListeCaracteristique();
////
////                    this.chargeListeGarantie();
////
////                }
////
////                refGroupe = new OrclasseRefGroupe();
////                group = new OrclasseRefGroupe();
////
////            } else if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.famille) || elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.individuel)) {
////                this.setPolice(policeSelect);
////                this.setAssure(policeSelect.getIdAssure());
//////            this.setIntermediaires(policeSelect.getIdIntermediaire());
////                this.setApporteur(policeSelect.getIdApporteur());
////                this.setCategories(policeSelect.getIdCategories());
////                this.setQuitance(quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, policeSelect));
////
////                this.setListePoliceCaracteristique((List<OrclassPoliceCaracteristique>) policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprise, policeSelect));
////                if (listePoliceCaracteristique == null) {
////                    listePoliceCaracteristique = new ArrayList<>();
////                }
////                this.setListePoliceGarantie((List<OrclassPoliceGarantie>) policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, policeSelect));
////                if (listePoliceGarantie == null) {
////                    listePoliceGarantie = new ArrayList<>();
////                }
////                this.chargeMajoratioDureeByDuree();
////                if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                    policeCaracteristique = new OrclassPoliceCaracteristique();
////                    listePoliceCaracteristique.add(policeCaracteristique);
////                    this.listeRubriqueCaracteristique = rubriqueCaracteristiqueDao.listeRubriqueCaracteristiqueByCompagniesNotHave(entreprise, police, categories);
////                    this.reverseListeCaracteristique();
////                    policeGarantie = new OrclassPoliceGarantie();
////                    listePoliceGarantie.add(policeGarantie);
////                    this.listeRubriqueGarantie = rubriqueGarantieDao.listeRubriqueGarantieByTarifAndCategorieNOtExistePoliceGarantie(policeSelect.getIdTypeTarif(), categories, entreprise);
////                    this.reverseListeGarantie();
////                }
//////            listeApporteur = commission_Prime_ApporteurDao.listeApporteurHavePrimeIsActi(entreprise, categories);
////                if (Objects.equals(policeSelect.getValidation(), Boolean.TRUE)) {
////                    this.setNumero_police(policeSelect.getPolice().toBigInteger());
////                } else {
////                    this.setNumero_police(policeSelect.getNumero_police());
////                }
////
////                this.choixTabView(elt_Categorie_Compagnie);
////                this.setDuree(policeSelect.getIdMajorationDuree().getIdDuree());
////                this.setDesignationPlafondMaladie(policeSelect.getDesignationPlafonMaladie());
////
////                this.setPlafondMaladie(policePlafondDao.chargePlafonMaladie(policeSelect, entreprise));
////                if (plafondMaladie == null) {
////                    plafondMaladie = new OrclassPlafondMaladie();
////                }
////                this.setListeDetailPolicePlafond(detailPolicePlafondDao.listeDetailPlafond(policeSelect));
////                if (listeDetailPolicePlafond == null) {
////                    listeDetailPolicePlafond = new ArrayList<>();
////                }
////                if (!listeDetailPolicePlafond.isEmpty()) {
////                    detailPolicePlafond = listeDetailPolicePlafond.get(0);
////                    this.listeRubriqueCategorie = rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, entreprise);
////                    this.setRubriqueCategorie(rubriqueCategorieDao.OrclassRubriqueCategorieDao(categories, detailPolicePlafond.getIdRubrique(), entreprise));
////                }
////                if (Objects.equals(police.getValidation(), Boolean.FALSE)) {
////                    detailPolicePlafond = new OrclassDetailPolicePlafond();
////                    listeDetailPolicePlafond.add(detailPolicePlafond);
////                    this.listePrestation = prestationDao.listePrestationtNotExisteForRubriquePrestation(rubriqueCategorie, entreprise);
////                    this.reverseListe();
////                }
////                this.chargeMajorationDuree();
////
//////                listeRisque = (List<OrclassRisque>) risqueDao.findAllEntitiesHavingValue("idPolice", policeSelect);
////                listeImageFamilleRisque.clear();
//////            } else {
////                this.setRisque(risqueDao.risqueByPolice(entreprise, policeSelect));
////                if (Objects.equals(tabShowFamille, Boolean.TRUE)) {
////                    this.setListeRisqueFamille((List<OrclassRisqueFamille>) risqueFamilleDao.listeRisqueFamilleByPoliceNotHaveAvenant(entreprise, policeSelect));
////
////                    listeRisqueFamille.add(new OrclassRisqueFamille());
////                    this.reverseListeFamilleRique();
////                }
////                //            }
////
////                this.setListePlafondMaladie(plafondMaladieDao.listePlafondMaladie(entreprise));
////                if (avenant != null && avenant.getId() != null) {
////                    this.controlleElementHaveAvenant();
////                }
////                this.caculTotalPrime();
////                this.calculTotalProrata();
////            }
////
////            this.updateDataTablePoliceCarzacteristique();
////            this.updateDataTableRisqueFamille();
////
////            PrimeFaces.current().executeScript("PF('managePoliceDialog').hide();");
////            PrimeFaces.current().ajax().update(":form1");
////            PrimeFaces.current().ajax().update(":form1:tabprincipal");
//            this.statiqueAssureAndMembre();
//        }
//    }
//
//    public void controlleElementHaveAvenant() {
//        //controle sur les risque 
//        Long id_elts = 0L;
//        List<OrclassPoliceCaracteristique> listePoliceCaracteristiqueByAvenant = new ArrayList<>();
//        List<OrclassPoliceGarantie> listePoliceGarantieByAvenant = new ArrayList<>();
//        List<OrclassRisque> listeRisqueByAvenant = new ArrayList<>();
//        List<OrclassRisqueFamille> listeRisqueFamilleByAvenant = new ArrayList<>();
//        List<OrclassDetailPolicePlafond> listeDetailPoliceByAvenant = new ArrayList<>();
//        if (!listeRisque.isEmpty()) {
//
//            OrclassRisque r = null;
//            Iterator<OrclassRisque> ir = listeRisque.iterator();
//            while (ir.hasNext()) {
//                r = ir.next();
//                if (Objects.equals(r.getRetire_de_la_police(), Boolean.TRUE)) {
//                    id_elts = r.getCodeid_retirer();
////                    listeRisque.remove(risqueDao.find(id_elts));
//                    listeRisqueByAvenant.add(r);
//                    listeRisqueByAvenant.add(risqueDao.find(id_elts));
//                    continue;
//                }
//            }
//            if (!listeRisqueByAvenant.isEmpty()) {
//                listeRisque.removeAll(listeRisqueByAvenant);
//            }
//
//        }
//
//        //controle sur les prestations
//        OrclassDetailPolicePlafond d = null;
//        Iterator<OrclassDetailPolicePlafond> idp = listeDetailPolicePlafond.iterator();
//        int iii = 0;
//        while (idp.hasNext()) {
//            d = idp.next();
//            if (Objects.equals(d.getRetire_de_la_police(), Boolean.TRUE)) {
//                id_elts = d.getCodeid_retirer();
////                listeDetailPolicePlafond.remove(detailPolicePlafondDao.find(id_elts));
//                listeDetailPoliceByAvenant.add(d);
//                listeDetailPoliceByAvenant.add(detailPolicePlafondDao.find(id_elts));
//                continue;
//
//            }
//            System.out.println("valeur lecture prestation.." + iii);
//            System.out.println("taille prestation a retire.." + listeDetailPoliceByAvenant.size());
//        }
//
//        if (!listeDetailPoliceByAvenant.isEmpty()) {
//            listeDetailPolicePlafond.removeAll(listeDetailPoliceByAvenant);
//
//        }
//
//        //controle sur les carateristiques
//        OrclassPoliceCaracteristique pc = null;
//        Iterator<OrclassPoliceCaracteristique> ic = listePoliceCaracteristique.iterator();
//        while (ic.hasNext()) {
//            pc = ic.next();
//            if (Objects.equals(pc.getRetire_de_la_police(), Boolean.TRUE)) {
//                id_elts = pc.getCodeid_retirer();
////                listePoliceCaracteristique.remove(policeCaracteristiqueDao.find(id_elts));
//                listePoliceCaracteristiqueByAvenant.add(pc);
//                listePoliceCaracteristiqueByAvenant.add(policeCaracteristiqueDao.find(id_elts));
////                listePoliceCaracteristique.remove(pc);
//                continue;
//            }
//
//        }
//        if (!listePoliceCaracteristiqueByAvenant.isEmpty()) {
//
//            listePoliceCaracteristique.removeAll(listePoliceCaracteristiqueByAvenant);
//        }
//        //controlle garantie
//        OrclassPoliceGarantie pg = null;
////        for (int i = 0; i < listePoliceGarantie.size(); i++) {
//        Iterator<OrclassPoliceGarantie> it = listePoliceGarantie.iterator();
//        int ii = 0;
//        while (it.hasNext()) {
//            ii++;
////                Object next = it.next();
//            pg = it.next();
//            if (Objects.equals(pg.getRetire_de_la_police(), Boolean.TRUE)) {
//                id_elts = pg.getCodeid_retirer();
////                listePoliceGarantie.remove(policeGarantieDao.find(id_elts));
//                listePoliceGarantieByAvenant.add(pg);
//                listePoliceGarantieByAvenant.add(policeGarantieDao.find(id_elts));
////                listePoliceGarantie.remove(pg);
//                continue;
//
//            }
//        }
//
//        System.out.println("valeur lecture pg..ii" + ii);
//        System.out.println("taille garantie a retirer..." + listePoliceGarantieByAvenant.size());
////        }
//        if (!listePoliceGarantieByAvenant.isEmpty()) {
//            listePoliceGarantie.removeAll(listePoliceGarantieByAvenant);
//        }
//        OrclassRisqueFamille rf = null;
//        Iterator<OrclassRisqueFamille> irf = listeRisqueFamille.iterator();
////        if (Objects.equals(tabShowFamille, Boolean.TRUE)) {
//        while (irf.hasNext()) {
//            rf = irf.next();
//            if (Objects.equals(rf.getRetire_de_la_police(), Boolean.TRUE)) {
////                    listeRisqueFamille.remove(risqueFamilleDao.find(rf.getCodeid_retirer()));
//                listeRisqueFamilleByAvenant.add(risqueFamilleDao.find(rf.getCodeid_retirer()));
//                listeRisqueFamilleByAvenant.add(rf);
//                continue;
//            }
//        }
//        if (!listeRisqueFamilleByAvenant.isEmpty()) {
//            listeRisqueFamille.removeAll(listeRisqueFamilleByAvenant);
//        }
////        }
//
//    }
//
//    public void addLigneByRisqueFamille(OrclassRisqueFamille item, int index) {
//
//        risqueFamille = new OrclassRisqueFamille();
//        risqueFamille.setMatricule(item.getMatricule());
//        risqueFamille.setNom_membre(item.getNom_membre());
//        risqueFamille.setDateNaissance(item.getDateNaissance());
//        risqueFamille.setDateEntree(item.getDateEntree());
//        risqueFamille.setIdRisque(risque);
//        risqueFamille.setLienParente(item.getLienParente());
//        risqueFamille.setIdVille(assure.getIdVille());
//        risqueFamille.setIdPolice(police);
//        risqueFamille.setIdEntreprise(entreprise);
//        risqueFamille.setSexe(item.getSexe());
//        risqueFamilleDao.create(risqueFamille);
//
//        listeRisqueFamille.set(index, risqueFamille);
//        listeRisqueFamille.add(new OrclassRisqueFamille());
//        this.reverseListeFamilleRique();
//        this.updateDataTableRisqueFamille();
//        risqueFamille = new OrclassRisqueFamille();
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
//            if (police == null || police.getId() == null) {
//
//                listePrestation = prestationDao.listePrestationByCompagnie(rubriqueCategorie, entreprise);
//
//            } else {
//                if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                    listePrestation = prestationDao.listePrestationByCompagnie(entreprise, police, refGroupeSelectForPrestation);
//
//                } else {
//                    listePrestation = prestationDao.listePrestationByCompagnieNotHaveGroupe(entreprise, police);
//
//                }
//            }
////            listePrestation = prestationDao.listePrestationByCompagnie(rubriqueCategorie, entreprise);
//
//            if (listeDetailPolicePlafond.isEmpty()) {
//                detailPolicePlafond = new OrclassDetailPolicePlafond();
//
//                detailPolicePlafond.setIdPrestation(new OrclassPrestation());
//                detailPolicePlafond.setIdRubrique(rubriqueCategorie.getIdRubrique());
//                detailPolicePlafond.setIdGroup(refGroupeSelectForPrestation);
//                detailPolicePlafond.setPlafondMaladie(plafondMaladie);
//
//                listeDetailPolicePlafond.add(detailPolicePlafond);
////                listeDetailPolicePlafond.add(detailPolicePlafond);
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
//        if (!selectePrestations.isEmpty()) {
//            for (OrclassPrestation p : selectePrestations) {
//                detailPolicePlafond = new OrclassDetailPolicePlafond();
//                detailPolicePlafond.setIdPrestation(p);
//                detailPolicePlafond.setIdRubrique(rubriqueCategorie.getIdRubrique());
//                detailPolicePlafond.setIdGroup(refGroupeSelectForPrestation);
//                detailPolicePlafond.setPlafondMaladie(plafondMaladie);
//
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
//            if (policeDao.finKey(intermediaires, numero_police, entreprise, categories) != null) {
//
////                this.creatEtatPrint();
//                if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
////                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ETAT EN DE REALISATION ...", " veillez contacter l administrateur "));
//                    this.creatEtatPrintForGroupMaladie();
//                } else {
//                    this.creatEtatPrint();
//                }
//
//            } else {
//                this.addProjetSante();
//            }
//
//        } else {
////            this.creatEtatPrint();
//            if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ETAT EN DE REALISATION ...", " veillez contacter l administrateur "));
//                this.creatEtatPrintForGroupMaladie();
//            } else {
//                this.creatEtatPrint();
//            }
//
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
//        if (Objects.equals(this.verificationDureeAndDateEffet(), Boolean.FALSE)) {
//            PrimeFaces.current().executeScript("PF('accordP').select(0);");
//            PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "la date d' echeance depasse la durée  du contrat choisir..."));
//            return;
//        }
//        if (police != null && Objects.equals(police.getValidation(), Boolean.TRUE)) {
//            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "the contract has already been validated"));
//            return;
//        }
//
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
////        this.police.setPolice(policeValide);
////        police.setValidation(Boolean.TRUE);
////        police.setDate_validation(new Date());
////        police.setValider_par(user.getNom() + " " + user.getPrenom());
////        policeDao.update(police);
////        this.setNumero_police(policeValide);
//        if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//            for (OrclassRisque r : listeRisque) {
//                if (r == null || r.getId() == null) {
//                    continue;
//                }
//                this.setRisque(r);
////                this.risque.setIdVille(this.assure.getIdVille());
//                for (OrclassRisqueFamille rf : listeRisqueFamille) {
//                    if (rf.getId() == null && (rf.getIdRisque() == null || rf.getIdRisque().getId() == null)) {
//                        continue;
//                    }
//                    risqueFamilleDao.update(rf);
//                }
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
//
//        for (OrclassDetailPolicePlafond dp : listeDetailPolicePlafond) {
//            if (dp.getId() == null && (dp.getIdPrestation() == null || dp.getIdPrestation().getId() == null)) {
//                continue;
//            }
//            detailPolicePlafondDao.update(dp);
//        }
//        for (OrclassPoliceCaracteristique pc : listePoliceCaracteristique) {
//            if (pc.getId() == null && (pc.getIdCaracteristiques() == null || pc.getIdCaracteristiques().getId() == null)) {
//                continue;
//            }
//            policeCaracteristiqueDao.update(pc);
//        }
////            if (police.getMontantaccessoir() == null) {
////                quitance.setMontant_Accessoire(BigDecimal.ZERO);
////            } else {
////                quitance.setMontant_Accessoire(police.getMontantaccessoir());
////            }
//
//        quitanceDao.update(quitance);
//        for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//            if (pg.getId() == null && (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null)) {
//                continue;
//            }
//            policeGarantieDao.update(pg);
//
//        }
//
//        this.police.setPolice(policeValide);
//        police.setValidation(Boolean.TRUE);
//        police.setDate_validation(new Date());
//        police.setValider_par(user.getNom() + " " + user.getPrenom());
//        policeDao.update(police);
//        this.setNumero_police(policeValide);
//
//        if (Objects.equals(user.getAllAccessForIntermediaire(), Boolean.TRUE)) {
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraByAgence(intermediaires, entreprise);
//        } else {
//            listeOrclassContrats = (List<OrclassPolice>) policeDao.listeContraByAgence(intermediaires, entreprise, user, branches);
//        }
//
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
//            police.setDate_annulation(new Date());
//            police.setAnnuler_par(user.getNom() + " " + user.getPrenom());
//            policeDao.update(police);
//            this.reload();
//        }
//    }
//
//    public void devaliderContrat() throws IOException {
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
////        BigDecimal taxe = BigDecimal.valueOf(19.25);
//        BigDecimal total_a_paye = BigDecimal.ZERO;
//        if (police != null && police.getNumero_police() != null && police.getIdIntermediaire() != null) {
//            police.setDateModification(new Date());
//            police.setModifier_par(user.getNom() + " " + user.getPrenom());
//            policeDao.update(police);
//            if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                for (OrclassRisque r : listeRisque) {
//                    if (r == null || r.getId() == null) {
//                        continue;
//                    }
//                    this.setRisque(r);
//                    risque.setDateModification(new Date());
//                    risque.setModifier_par(user.getNom() + " " + user.getPrenom());
//
//                    risqueDao.update(risque);
//
//                }
//
//                for (OrclassRisqueFamille rf : listeRisqueFamille) {
//                    if (rf.getId() == null && (rf.getIdRisque() == null || rf.getIdRisque().getId() == null)) {
//                        continue;
//                    }
//                    risqueFamilleDao.update(rf);
//                }
//
//            } else if ((elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getId() != null) && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.famille)) {
//                risque.setDateModification(new Date());
//                risque.setModifier_par(user.getNom() + " " + user.getPrenom());
//                risqueDao.update(risque);
//                for (OrclassRisqueFamille rf : listeRisqueFamille) {
//                    if (rf.getId() == null && (rf.getIdRisque() == null || rf.getIdRisque().getId() == null)) {
//                        continue;
//                    }
//
//                    risqueFamilleDao.update(rf);
//                }
//            } else {
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
//            for (OrclassDetailPolicePlafond dp : listeDetailPolicePlafond) {
//                if (dp.getId() == null && (dp.getIdPrestation() == null || dp.getIdPrestation().getId() == null)) {
//                    continue;
//                }
//                if (dp.getId() == null && (dp.getIdPrestation() != null && dp.getIdPrestation().getId() != null)) {
//                    detailPolicePlafond = new OrclassDetailPolicePlafond();
//                    detailPolicePlafond.setIdEntreprise(entreprise);
//                    detailPolicePlafond.setBarem(dp.getBarem());
//                    detailPolicePlafond.setCode_ss(dp.getCode_ss());
//                    detailPolicePlafond.setForfait(dp.getForfait());
//                    detailPolicePlafond.setIdPolicePlafond(policePlafondDao.chargePolicePlafonMaladie(police, entreprise));
//                    detailPolicePlafond.setIdPrestation(dp.getIdPrestation());
//                    detailPolicePlafond.setIdRubrique(dp.getIdRubrique());
//                    detailPolicePlafond.setModeCalculDetailMaladie(dp.getModeCalculDetailMaladie());
//                    detailPolicePlafond.setPlafond(dp.getPlafond());
//                    detailPolicePlafond.setTaux(dp.getTaux());
//                    detailPolicePlafond.setTypeDetailMaladie(dp.getTypeDetailMaladie());
//                    detailPolicePlafondDao.create(detailPolicePlafond);
//                    continue;
//                }
//                detailPolicePlafondDao.update(dp);
//            }
//            OrclassPoliceCaracteristique newPoliceCaracteristique = null;
//            for (OrclassPoliceCaracteristique pc : listePoliceCaracteristique) {
//                if (pc.getId() == null && (pc.getIdCaracteristiques() == null || pc.getIdCaracteristiques().getId() == null)) {
//                    continue;
//                }
//
//                if (pc.getId() == null && (pc.getIdCaracteristiques() != null && pc.getIdCaracteristiques().getId() != null)) {
//
//                    newPoliceCaracteristique = new OrclassPoliceCaracteristique();
//
//                    newPoliceCaracteristique.setIdCaracteristiques(pc.getIdCaracteristiques());
//                    newPoliceCaracteristique.setIdPolice(police);
//                    newPoliceCaracteristique.setIdEntreprise(entreprise);
//                    if (pc.getDateValeur() != null) {
//                        newPoliceCaracteristique.setDateValeur(pc.getDateValeur());
//                    }
//                    if (pc.getValeurBoolean() != null) {
//                        newPoliceCaracteristique.setValeurBoolean(pc.getValeurBoolean());
//                    }
//                    if (pc.getValeurCode() != null) {
//                        newPoliceCaracteristique.setValeurCode(pc.getValeurCode());
//                    }
//                    if (pc.getValeurCaracteristique() != null) {
//                        pc.setValeurCaracteristique(pc.getValeurCaracteristique());
//                    }
//                    if (pc.getValeurTexte() != null) {
//                        newPoliceCaracteristique.setValeurTexte(pc.getValeurTexte());
//                    }
//                    if (pc.getValeurNumerique() != null && pc.getValeurNumerique() != null && pc.getValeurNumerique().intValue() != 0) {
//                        newPoliceCaracteristique.setValeurNumerique(pc.getValeurNumerique());
//                    }
//                    newPoliceCaracteristique.setIdCategories(police.getIdCategories());
//                    newPoliceCaracteristique.setIdCaracteristiques(pc.getIdCaracteristiques());
//                    policeCaracteristiqueDao.create(newPoliceCaracteristique);
//
//                    continue;
//                }
//                policeCaracteristiqueDao.update(pc);
//            }
//
//            OrclassPoliceGarantie newPoliceGarantie = null;
//            for (OrclassPoliceGarantie pg : listePoliceGarantie) {
//                if (pg.getId() == null || pg.getIdGarantie().getId() == null) {
//                    continue;
//                }
//                if (pg.getId() == null && pg.getIdGarantie() != null && pg.getIdGarantie().getId() != null) {
//                    newPoliceGarantie = new OrclassPoliceGarantie();
//                    newPoliceGarantie.setCapital(pg.getCapital());
//                    newPoliceGarantie.setIdEntreprise(entreprise);
//                    newPoliceGarantie.setIdGarantie(pg.getIdGarantie());
//                    newPoliceGarantie.setIdPolice(police);
//                    newPoliceGarantie.setPourcentage(pg.getPourcentage());
//                    newPoliceGarantie.setPrime(pg.getPrime());
//                    newPoliceGarantie.setProrata(pg.getProrata());
//                    newPoliceGarantie.setTaux(pg.getTaux());
//                    policeGarantieDao.create(newPoliceGarantie);
//                    continue;
//                }
//                policeGarantieDao.update(pg);
//
//            }
//            if (police.getMontantaccessoir() == null) {
//                quitance.setMontant_Accessoire(BigDecimal.ZERO);
//            } else {
//                quitance.setMontant_Accessoire(police.getMontantaccessoir());
//            }
//
//            quitance.setDate_echeance(police.getDate_echeance());
//            if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
//                quitance.setPrimeNette(this.caculTotalPrimeByForQuittance());
//                quitance.setTaxePrime((this.caculTotalPrimeByForQuittance().multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//            } else {
//                quitance.setPrimeNette(this.totalProrata);
//                quitance.setTaxePrime((this.totalProrata.multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
//            }
//
//            quitance.setPrimeTaxe(quitance.getPrimeNette().add(quitance.getTaxePrime()));
//            quitance.setTaxeAccessoir((quitance.getMontant_Accessoire().multiply(tva)).divide((BigDecimal.TEN).multiply(BigDecimal.TEN)));
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
////            for (OrclassPoliceGarantie pg : listePoliceGarantie) {
////                policeGarantieDao.update(pg);
////
////            }
//            /**
//             * creation de Groupe Police
//             */
//            OrclasseRefGroupe rg = null;
//            OrclassGroupePolice rgp = null;
//            for (OrclasseRefGroupe g : listeRefGroup) {
//                if (g != null && g.getId() != null) {
////                    rg = g;
////                    rg.setDateModification(new Date());
////                    refGroupeDao.update(rg);
//                    continue;
//
//                } else {
//                    rg = new OrclasseRefGroupe();
//                    rg.setAdresse(g.getAdresse() == null ? new Adresse("", "", "", "", "", "", "") : g.getAdresse());
//                    rg.setVille(g.getVille());
//                    rg.setLibelle(g.getLibelle());
//                    rg.setObservation(g.getObservation());
//                    rg.setDateCreation(new Date());
//                    refGroupeDao.create(rg);
//                }
//                if (groupePoliceDao.finKey(entreprise, police, g) == null) {
//                    rgp = new OrclassGroupePolice();
//                    rgp.setIdEntreprise(entreprise);
//                    rgp.setIdPolice(police);
//                    rgp.setIdRefGroupe(rg);
//                    rgp.setIdVille(rg.getVille());
//                    rgp.setAdresseGroup(rg.getAdresseGroup());
//                    rgp.setObservation(rg.getObservation());
//                    rgp.setDateCreation(new Date());
//                    groupePoliceDao.create(rgp);
//                }
//
////                if (suspensionBrance) {
////                    
////                }
//            }
//            etatModifier = Boolean.TRUE;
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "police", Success.UPDATE_SUCCESS + "", new Object[]{numero_police});
//
//        }
//    }
//
//    public void addProjetSante() throws IOException {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        try {
//
//            //traitement de la photo
//            if ((police == null || police.getId() == null)) {
//                police.setNumero_police(numero_police);
//                police.setIdCategories(categories);
//                police.setIdIntermediaire(intermediaires);
//                this.chargePrimeApproteur();
//                elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
//                try {
//                    if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                        listeImageRisques = (List<OrclassImageRisque>) imageRisqueDao.findAllEntitiesHavingValue("numero_polic", numero_police);
//                        valider = SanteService.addProjetSanteByMaladieGroup(assure, police, listeImageRisques, plafondMaladie, listeDetailPolicePlafond, listePoliceCaracteristique, listePoliceGarantie, quitance, listePoliceCommissionApporteur, entreprise, user, print, validation);
//                    } else {
//                        valider = SanteService.addProjetSanteByMaladieFamille(assure, police, risque, listeRisqueFamille, plafondMaladie, listeDetailPolicePlafond, listePoliceCaracteristique, listePoliceGarantie, quitance, listePoliceCommissionApporteur, entreprise, user, print, validation);
//                    }
//                } catch (GlobalException g) {
//                    valider = Boolean.FALSE;
//                    GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", g.getMessage() + " " + g.getLocalizedMessage() + " " + g.getCause().getMessage(), new Object[]{numero_police});
//
//                }
//
//            } else {
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "" + numero_police, null);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "", new Object[]{numero_police});
//                return;
//            }
//
//            if (Objects.equals(valider, Boolean.TRUE)) {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "police", Success.INSERT_SUCCESS + "", new Object[]{numero_police});
//                if (tabShowGroup == Boolean.TRUE) {
//                    this.creatEtatPrintForGroupMaladie();
//                } else {
//                    this.creatEtatPrint();
//                }
//                this.reload();
//
//            } else {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "police", exception.Error.INSERT_ERROR + "", new Object[]{numero_police});
//
//            }
//
//            System.out.println("etat de validation-->" + valider);
//
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, numero_police.toString(), e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "police", exception.Error.FATAL_ERROR + "", new Object[]{"police"});
//            logger.error("Erreur Survenu", th);
//        }
////        if (Objects.equals(valider, Boolean.TRUE)) {
////            this.reload();
////        }
//
//    }
//
//    public void addProjetSanteSave() throws IOException {
//        // etudiant.setMatricule(GenerationController.genererMatricule());
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        Boolean valider = Boolean.FALSE;
//        try {
//            if (Objects.equals(this.verificationDureeAndDateEffet(), Boolean.FALSE)) {
//                PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "la date d' echeance depasse la durée  du contrat choisir..."));
//                return;
//            }
//            elt_Categorie_Compagnie = elt_Categorie_CompagnieDao.finkey(categories, entreprise);
//            //traitement de la photo
//            if ((police == null || police.getId() == null)) {
//                if (policeDao.finKey(intermediaires, numero_police, entreprise, categories) != null) {
//                    this.reload();
//                    return;
//                }
//                police.setNumero_police(numero_police);
//                police.setIdCategories(categories);
//                police.setIdIntermediaire(intermediaires);
//                this.chargePrimeApproteur();
//                if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                    listeImageRisques = (List<OrclassImageRisque>) imageRisqueDao.findAllEntitiesHavingValue("numero_polic", numero_police);
//                    valider = SanteService.addProjetSanteByMaladieGroup(assure, police, listeImageRisques, plafondMaladie, listeDetailPolicePlafond, listePoliceCaracteristique, listePoliceGarantie, quitance, listePoliceCommissionApporteur, entreprise, user, print, validation);
//                } else {
//                    valider = SanteService.addProjetSanteByMaladieFamille(assure, police, risque, listeRisqueFamille, plafondMaladie, listeDetailPolicePlafond, listePoliceCaracteristique, listePoliceGarantie, quitance, listePoliceCommissionApporteur, entreprise, user, print, validation);
//                }
//
//            } else {
////                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "" + numero_police, null);
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, "police", exception.Error.DUPLICATE_ERROR_INSERT + "", new Object[]{numero_police});
//                return;
//            }
//
//            if (Objects.equals(valider, Boolean.TRUE)) {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_INFO, "police", Success.INSERT_SUCCESS + "", new Object[]{numero_police});
//                this.reload();
//
//            } else {
//                GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "police", exception.Error.INSERT_ERROR + "", new Object[]{numero_police});
//
//            }
//
//            System.out.println("etat de validation-->" + valider);
//
//        } catch (GlobalException e) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_ERROR, numero_police.toString(), e.getCode(), e.getParam());
//            //throw e;
//        } catch (ConstraintViolationException ejb) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_WARN, ejb.getCause().getClass().getSimpleName(), exception.Error.DUPLICATE_ERROR_INSERT + "", null);
//            throw ejb;
//        } catch (Throwable th) {
//            GlobalFonctions.displayMessages(null, ctx, FacesMessage.SEVERITY_FATAL, "police", exception.Error.FATAL_ERROR + "", new Object[]{"police"});
//            logger.error("Erreur Survenu", th);
//        }
//
//    }
//
//    public void controlleBefortPrint() throws IOException, JRException {
//        if (police != null && police.getId() != null && (police.getPolice() == null || police.getPolice().intValue() == 0)) {
//            if (Objects.equals(etatModifier, Boolean.FALSE)) {
//                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("click on modify before printing"));
//                return;
//            } else {
//                if (Objects.equals(this.verificationDureeAndDateEffet(), Boolean.FALSE)) {
//                    PrimeFaces.current().executeScript("PF('accordP').select(0);");
//                    PrimeFaces.current().executeScript("PF('accordPSecond').select(1);");
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR", "la date d' echeance depasse la durée  du contrat choisir..."));
//                    return;
//                }
////                this.addProjetSante();
//                if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
////                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ETAT EN DE REALISATION ...", " veillez contacter l administrateur "));
//                    creatEtatPrintForGroupMaladie();
//                } else {
//                    this.creatEtatPrint();
//                }
//
//            }
//        } else if (police != null && police.getId() != null && (police.getPolice() != null && police.getPolice().intValue() != 0)) {
//            this.addProjetSante();
//            if (Objects.equals(tabShowGroup, Boolean.TRUE)) {
////                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("ETAT EN DE REALISATION ...", " veillez contacter l administrateur "));
//                creatEtatPrintForGroupMaladie();
//            } else {
//                this.creatEtatPrint();
//            }
//        } else {
//            this.addProjetSante();
//        }
//
//    }
//
//    public void creatEtatPrint() throws IOException, JRException {
//        Contrat_Sante contrat_Sante;
//        OrclassPolice police = null;
//        colContrat_Sante.clear();
//        ComparatorChain chain = new ComparatorChain();
//        if (this.police.getPolice() != null && this.police.getPolice().intValue() != 0) {
//            police = policeDao.finKeyPoliceValide(intermediaires, this.police.getPolice().toBigInteger(), entreprise, categories);
//        } else {
//            police = policeDao.finKey(intermediaires, numero_police, entreprise, categories);
//        }
//        OrclassAssure assure = police.getIdAssure();
//        OrclassQuitance quittance = quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, police);
//        OrclassPlafondMaladie plafondMaladie = policePlafondDao.chargePlafonMaladie(police, entreprise);
//
//        List<OrclassRisqueFamille> colFamillePrint = new ArrayList<>();
//        List<OrclassDetailPolicePlafond> colPrestationPrint = new ArrayList<>();
//        List<OrclassPoliceCaracteristique> colCaracteristiquePrint = new ArrayList<>();
//        List<OrclassPoliceGarantie> listePoliceGarentie = new ArrayList<>();
//
//        if (police != null && police.getId() != null && police.getIdIntermediaire() != null) {
//
//            contrat_Sante = new Contrat_Sante();
////            if (police.getIdCategories().getCode().equals(LibelleCategorie.autres)) {
////                contrat_Sante.setCategorie(police.getIdCategories().getLibelleAutre());
////            } else {
////                contrat_Sante.setCategorie(GlobalFonctions.valueObject(police.getIdCategories().getLibelle()));
////            }
//            if (police.getIdCategories().getLibelleAutre() == null) {
//                contrat_Sante.setCategorie(GlobalFonctions.valueObject(police.getIdCategories().getLibelle()));
//            } else {
//                contrat_Sante.setCategorie(police.getIdCategories().getLibelleAutre());
//            }
////            contrat_Sante.setCategorie(pathFormat);
//            //information sur la police et l agance
//            contrat_Sante.setLibelleAgence(police.getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//            if (police.getPolice() != null && police.getPolice().intValue() != 0) {
//                contrat_Sante.setPolice(BigDecimal.valueOf(police.getPolice().doubleValue()));
//            } else {
//                contrat_Sante.setPolice(BigDecimal.valueOf(police.getNumero_police().doubleValue()));
//            }
//
//            contrat_Sante.setTelAgence(police.getIdIntermediaire().getAdresse().getTel());
//            if (police.getRef_intermediaire() == null) {
//                contrat_Sante.setRefPolice("");
//            } else {
//                contrat_Sante.setRefPolice(police.getRef_intermediaire());
//            }
//            if (police.getIdIntermediaire().getAdresse() != null && police.getIdIntermediaire().getAdresse().getFax() != null) {
//                contrat_Sante.setFaxAgence(police.getIdIntermediaire().getAdresse().getFax());
//            } else {
//                contrat_Sante.setFaxAgence("");
//            }
//
//            contrat_Sante.setAdresseBpAgence("" + police.getIdIntermediaire().getAdresse().getBp());
//            contrat_Sante.setVilleAgence(police.getIdIntermediaire().getIdVille().getLibelle());
//            //information du souscripteur;
//            if (assure != null && assure.getId() != null) {
//                if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                    contrat_Sante.setNomComplet(assure.getRaison_social());
//                } else {
//                    contrat_Sante.setNomComplet(assure.getNom());
//                    if (assure.getPrenom() != null) {
//                        contrat_Sante.setNomComplet(assure.getNom() + " " + assure.getPrenom());
//
//                    }
//                }
//
//                contrat_Sante.setTel(assure.getAdresse().getIndicatifPays() + "" + assure.getAdresse().getTel());
//                contrat_Sante.setAdresseBp(assure.getAdresse().getBp());
//                contrat_Sante.setActivite(assure.getIdActivite().getLibelle());
//                contrat_Sante.setMail(assure.getAdresse().getEmail());
//                contrat_Sante.setFax(assure.getAdresse().getFax());
//                contrat_Sante.setCodeAssure(assure.getCode());
//                if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                    contrat_Sante.setDateNaissance("");
//                } else {
//                    contrat_Sante.setDateNaissance(GlobalFonctions.formatDate(assure.getDate_naissance()) + "- Sexe " + assure.getSexe().name());
//                }
//
//            }
//            //information periode de garantie
//            contrat_Sante.setDateDebutGarantie(GlobalFonctions.formatDate(police.getDate_effet()) + " 00:00 ");
//            contrat_Sante.setDateFinGarantie(GlobalFonctions.formatDate(police.getDate_echeance()) + " 23:59 ");
//            contrat_Sante.setDureGarantie("" + police.getValeurDuree() + " " + GlobalFonctions.valueObject(police.getIdMajorationDuree().getIdDuree().getUniteDuree()));
//            contrat_Sante.setContrat(GlobalFonctions.valueObject(police.getContrat()) + " " + GlobalFonctions.valueObject(police.getNatureContrat()));
//            //information sur la quittance
//            if (quittance != null && quittance.getId() != null) {
//                contrat_Sante.setAccessoire(GlobalFonctions.formatNumberGeneral(quittance.getMontant_Accessoire().longValue()));
//                contrat_Sante.setDevise(police.getIdDevise().getCode());
//                contrat_Sante.setTotalTaxe(GlobalFonctions.formatNumberGeneral(quittance.getTotalTaxes().longValue()));
//                contrat_Sante.setPrimeNetteQuittance(GlobalFonctions.formatNumberGeneral(quittance.getPrimeNette().longValue()));
//                contrat_Sante.setTotal_a_paye(GlobalFonctions.formatNumberGeneral(quittance.getTotal_a_paye().longValue()));
//
//            }
//            if (Objects.equals(police.getValidation(), Boolean.TRUE) && police.getValider_par() != null) {
//                contrat_Sante.setUser(police.getValider_par());
//            } else if (Objects.equals(police.getValidation(), Boolean.FALSE) && police.getSaisir_par() != null) {
//                contrat_Sante.setUser(police.getSaisir_par());
//            }
//
//            if (police.getIdApporteur() != null && police.getIdApporteur().getIdApporteur() != null) {
//                contrat_Sante.setCharger_client(police.getIdApporteur().getIdRefApporteur().getRaisonSociale());
//            } else {
//                contrat_Sante.setCharger_client("");
//            }
//
////enregistrement garantie
////            listePoliceGarentie = policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, police);
//            listePoliceGarentie = this.formatageByAffichage(policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, police));
//
//            colCaracteristiquePrint = policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprise, police);
//            colPrestationPrint = detailPolicePlafondDao.listeDetailPlafond(police);
//
//            chain.addComparator(OrclassDetailPolicePlafond.ByRubriqueASC);
//
//            Collections.sort((List<OrclassDetailPolicePlafond>) colPrestationPrint, chain);
////                contrat_Sante.setColPrestation(colPrestationPrint);
//            if (Objects.equals(tabShowFamille, Boolean.TRUE)) {
//                colFamillePrint = risqueFamilleDao.listeRisqueFamilleByPoliceNotHaveAvenant(entreprise, police);
//            }
////            for (OrclassPoliceGarantie pg : ) {
////
////                //information plafond Maladie
////                if (plafondMaladie != null && plafondMaladie.getId() != null) {
////                    contrat_Sante.setLibellePlafondMaladie(plafondMaladie.getLibelle());
////                    contrat_Sante.setMontant(GlobalFonctions.formatNumberGeneral(plafondMaladie.getMontant().longValue()));
////                    contrat_Sante.setModeControle(GlobalFonctions.valueObject(plafondMaladie.getModeControle()));
////                    contrat_Sante.setPeriodeCouverture("" + plafondMaladie.getPriode());
////                    contrat_Sante.setDomaineApplication(GlobalFonctions.valueObject(plafondMaladie.getDomaine_application()));
////                    contrat_Sante.setActe("" + plafondMaladie.getNombre_Acte());
////                }
////
////                ///recuperation des information des membres de la famille
////                if (colFamillePrint.isEmpty()) {
////                    FamillePrint fp = null;
////                    for (OrclassRisqueFamille rf : ) {
////                        fp = new FamillePrint();
////                        fp.setMembre(rf.getNom_membre());
////                        fp.setLien_parente(GlobalFonctions.valueObject(rf.getLienParente()));
////                        fp.setNaissance(GlobalFonctions.formatDate(rf.getDateNaissance()));
////                        fp.setEntre_le(GlobalFonctions.formatDate(rf.getDateEntree()));
////                        fp.setSortie_le(GlobalFonctions.formatDate(rf.getDateSortie()));
////                        colFamillePrint.add(fp);
////                    }
////                }
////
////                //recuperation des caracteristique
////                if (colCaracteristiquePrint.isEmpty()) {
////                    CaracteristiquePrint cp = null;
////                    for (OrclassPoliceCaracteristique pc : ) {
////                        cp = new CaracteristiquePrint();
////                        cp.setLibelle(pc.getIdCaracteristiques().getLibelle());
////                        if (pc.getValeurBoolean() != null) {
////                            if (Objects.equals(pc.getValeurBoolean(), Boolean.TRUE)) {
////                                cp.setValeur("Oui");
////                            } else if (Objects.equals(pc.getValeurBoolean(), Boolean.FALSE)) {
////                                cp.setValeur("Faux");
////                            }
////
////                        } else if (pc.getDateValeur() != null) {
////                            cp.setValeur(GlobalFonctions.formatDate(pc.getDateValeur()));
////                        } else if (pc.getValeurCode() != null) {
////                            cp.setValeur(pc.getValeurCode());
////                        } else if (pc.getValeurNumerique() != null && pc.getValeurNumerique().intValue() != 0) {
////                            cp.setValeur(GlobalFonctions.formatNumberGeneral(pc.getValeurNumerique().longValue()));
////                        }
////                        colCaracteristiquePrint.add(cp);
////
////                    }
////                }
////
////                // recuperationdes prestations
////                if (colPrestationPrint.isEmpty()) {
////                    PrestationPrints pp = null;
////                    for (OrclassRubrique rb : detailPolicePlafondDao.listeRubrique(police)) {
////                        for (OrclassDetailPolicePlafond dp : detailPolicePlafondDao.listeDetailPlafondByRubrique(police, rb)) {
////                            pp = new PrestationPrints();
////                            pp.setRubrique(rb.getLibelle());
////                            pp.setLibellePrestation(dp.getIdPrestation().getLibelle());
////                            if (dp.getTaux() == null) {
////                                pp.setTauxPrestation("");
////                            } else {
////                                pp.setTauxPrestation(GlobalFonctions.formatNumberGeneral(dp.getTaux().longValue()));
////                            }
////
////                            if (dp.getPlafond() == null) {
////                                pp.setPlafond("");
////                            } else {
////                                pp.setPlafond(GlobalFonctions.formatNumberGeneral(dp.getPlafond().longValue()));
////                            }
////                            if (dp.getBarem() == null) {
////                                pp.setBareme("");
////                            } else {
////                                pp.setBareme(GlobalFonctions.formatNumberGeneral(dp.getBarem().longValue()));
////                            }
////                            if (dp.getForfait() == null) {
////                                pp.setForfait("");
////                            } else {
////                                pp.setForfait(GlobalFonctions.formatNumberGeneral(dp.getForfait().longValue()));
////                            }
////
////                            colPrestationPrint.add(pp);
////
////                        }
////                    }
////
////                }
//
//            // information sur la garantie
////                contrat_Sante.setGarantie(pg.getIdGarantie().getIdRefGaranties().getLibelle());
////                if (pg.getCapital() == null) {
////                    contrat_Sante.setCapital("");
////                } else {
////                    contrat_Sante.setCapital(GlobalFonctions.formatNumberGeneral(pg.getCapital().longValue()));
////                }
////
////                if (pg.getTaux() == null) {
////                    contrat_Sante.setTaux("");
////                } else {
////                    contrat_Sante.setTaux(GlobalFonctions.formatNumberGeneral(pg.getTaux().longValue()));
////                }
////
////                if (pg.getPourcentage() == null) {
////                    contrat_Sante.setPourcentage("");
////                } else {
////                    contrat_Sante.setPourcentage(GlobalFonctions.formatNumberGeneral(pg.getPourcentage().longValue()));
////                }
////
////                if (pg.getPrime() == null) {
////                    contrat_Sante.setPrime("");
////                } else {
////                    contrat_Sante.setPrime(GlobalFonctions.formatNumberGeneral(pg.getPrime().longValue()));
////                }
////
////                if (pg.getProrata() == null) {
////                    contrat_Sante.setProrate("");
////                } else {
////                    contrat_Sante.setProrate(GlobalFonctions.formatNumberGeneral(pg.getProrata().longValue()));
////                }
////
////                if (police.getIdCategories().getLibelleAutre() == null) {
////                    contrat_Sante.setCategorie(GlobalFonctions.valueObject(police.getIdCategories().getLibelle()));
////                } else {
////                    contrat_Sante.setCategorie(police.getIdCategories().getLibelleAutre());
////                }
//            contrat_Sante.setColCaracteristiquePrint(colCaracteristiquePrint);
//            contrat_Sante.setColFamille(colFamillePrint);
//            contrat_Sante.setColPrestation(colPrestationPrint);
//
//            contrat_Sante.setColGarantiePrint(listePoliceGarentie);
//            colContrat_Sante.add(contrat_Sante);
//
//            this.printCcontratMaladieIndividuelOrMaladieFamill();
//            etatModifier = Boolean.FALSE;
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
//                if (plg.getTaux() == null || plg.getTaux() == BigDecimal.ZERO) {
//                    plg.setLibelleTaux("");
//                } else {
//                    plg.setLibelleTaux(GlobalFonctions.formatNumberGeneral(plg.getTaux().longValue()));
//                }
//                if (plg.getPourcentage() == null || plg.getPourcentage() == BigInteger.ZERO || plg.getPourcentage() == BigInteger.ONE) {
//                    plg.setLibellePourcentage("");
//                } else {
//                    plg.setLibellePourcentage(GlobalFonctions.formatNumberGeneral(plg.getPourcentage().longValue()));
//                }
//                listePg.add(plg);
//            }
//        }
//        return listePg;
//    }
////     public List<OrclassPoliceGarantie> formatageByAffichage(List<OrclassPoliceGarantie> pg) {
////        List<OrclassPoliceGarantie> listePg = new ArrayList<>();
////        if (pg != null && !pg.isEmpty()) {
////            for (OrclassPoliceGarantie plg : pg) {
////                if (Objects.equals(plg.getIdGarantie().getCapital_illimitter(), Boolean.TRUE)) {
////                    plg.setLibelleCapital("ILLIMITE");
////                } else if (plg.getCapital() == null || plg.getCapital() == BigDecimal.ZERO) {
////                    plg.setLibelleCapital("");
////                } else {
////                    plg.setLibelleCapital(GlobalFonctions.formatNumberGeneral(plg.getCapital().longValue()));
////                }
////
////                if (plg.getPrime() == null || plg.getPrime() == BigDecimal.ZERO) {
////                    plg.setLibellePrime("");
////                } else {
////                    plg.setLibellePrime(GlobalFonctions.formatNumberGeneral(plg.getPrime().longValue()));
////                }
////                listePg.add(plg);
////            }
////        }
////        return listePg;
////    }
////
//
//    public void creatEtatPrintForGroupMaladie() throws IOException, JRException {
//        Contrat_Sante contrat_Sante;
//        OrclassPolice police = null;
//        colContrat_Sante.clear();
//        if (this.police.getPolice() != null && this.police.getPolice().intValue() != 0) {
//            police = policeDao.finKeyPoliceValide(intermediaires, this.police.getPolice().toBigInteger(), entreprise, categories);
//        } else {
//            police = policeDao.finKey(intermediaires, numero_police, entreprise, categories);
//        }
//        OrclassAssure assure = police.getIdAssure();
//        OrclassQuitance quittance = quitanceDao.quittanceByPoliceNotHaveAvenant(entreprise, police);
//        OrclassPlafondMaladie plafondMaladie = null;
//
//        List<OrclassRisqueFamille> colFamillePrint = new ArrayList<>();
//        List<OrclassDetailPolicePlafond> colPrestationPrint = new ArrayList<>();
//        List<OrclassPoliceCaracteristique> colCaracteristiquePrint = new ArrayList<>();
//        List<OrclassPoliceCaracteristique> colCaracteristiquePrintLocal = new ArrayList<>();
//        List<OrclassPoliceGarantie> listePoliceGarentie = new ArrayList<>();
//        List<OrclassGroupePolice> listeGroupePolice = new ArrayList<>();
//        GarantiePrint garantiePrint = null;
//        List<OrclassPoliceGarantie> listeGarantiePrint = new ArrayList<>();
//        List<Contrat_Sante> listeContrat_sante = new ArrayList<>();
//        OrclassQuitance quitance = null;
//        List<OrclasseRefGroupe> colRefGroup = new ArrayList<>();
//        Contrat_Sante t[];
//        OrclassGroupePolice gpr = null;
//        int index, i = 0;
//
//        if (police != null && police.getId() != null && police.getIdIntermediaire() != null) {
//            //recuperation des group
//            listeGroupePolice = groupePoliceDao.ListeGroupByCompagnieForPolice(entreprise, police);
//            if (listeGroupePolice.isEmpty()) {
//                colRefGroup = risqueDao.listeGroupeForRisque(entreprise, police);
//                if (colRefGroup.isEmpty()) {
//                    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage("FATAL ERROR...", "GROUP IS NOT EXISTS OPERATION IS STOPING"));
//                    return;
//                }
//                for (OrclasseRefGroupe refg : colRefGroup) {
//                    gpr = new OrclassGroupePolice();
//                    gpr.setIdEntreprise(entreprise);
//                    gpr.setIdPolice(police);
//                    gpr.setIdRefGroupe(refg);
//                    gpr.setAdresseGroup(police.getIdAssure().getAdresse().getBp());
//                    gpr.setIdVille(police.getIdAssure().getIdVille());
//                    listeGroupePolice.add(gpr);
//                }
//
//            }
//            index = listeGroupePolice.size();
//            t = new Contrat_Sante[index];
//            int order_bygroup = 0;
//            for (OrclassGroupePolice gp : listeGroupePolice) {
//                order_bygroup++;
//
////                colFamillePrint.clear();
////                colPrestationPrint.clear();
////                colCaracteristiquePrint.clear();
////                listeGarantiePrint.clear();
//                contrat_Sante = new Contrat_Sante();
////                contrat_Sante.setPolice(police.getPolice());
////                listeContrat_sante.clear();
//                contrat_Sante.setLibelleAgence(police.getIdIntermediaire().getIdRefIntermediaire().getRaisonSociale());
//                contrat_Sante.setCodeAgence(police.getIdIntermediaire().getIdRefIntermediaire().getCode());
//
//                if (police.getPolice() != null && police.getPolice().intValue() != 0) {
//                    contrat_Sante.setPolice(BigDecimal.valueOf(police.getPolice().doubleValue()));
//                } else {
//                    contrat_Sante.setPolice(BigDecimal.valueOf(police.getNumero_police().doubleValue()));
//                }
//
//                contrat_Sante.setTelAgence(police.getIdIntermediaire().getAdresse().getTel());
//                if (police.getRef_intermediaire() == null) {
//                    contrat_Sante.setRefPolice("");
//                } else {
//                    contrat_Sante.setRefPolice(police.getRef_intermediaire());
//                }
//                if (police.getIdIntermediaire().getAdresse() != null && police.getIdIntermediaire().getAdresse().getFax() != null) {
//                    contrat_Sante.setFaxAgence(police.getIdIntermediaire().getAdresse().getFax());
//                } else {
//                    contrat_Sante.setFaxAgence("");
//                }
//                contrat_Sante.setAdresseBpAgence("" + police.getIdIntermediaire().getAdresse().getBp());
//                contrat_Sante.setVilleAgence(police.getIdIntermediaire().getIdVille().getLibelle());
//
//                //information du souscripteur;
//                if (assure != null && assure.getId() != null) {
//                    if (elt_Categorie_Compagnie != null && elt_Categorie_Compagnie.getRisque().equals(LibelleRisque.groupe)) {
//                        contrat_Sante.setNomComplet(assure.getRaison_social());
//                    } else {
//                        contrat_Sante.setNomComplet(assure.getNom());
//                        if (assure.getPrenom() != null) {
//                            contrat_Sante.setNomComplet(assure.getNom() + " " + assure.getPrenom());
//
//                        }
//                    }
//
//                }
//
//                contrat_Sante.setTel(assure.getAdresse().getIndicatifPays() + "" + assure.getAdresse().getTel());
//                contrat_Sante.setAdresseBp(assure.getAdresse().getBp());
//                contrat_Sante.setActivite(assure.getIdActivite().getLibelle());
//                contrat_Sante.setMail(assure.getAdresse().getEmail());
//                contrat_Sante.setFax(assure.getAdresse().getFax());
//                contrat_Sante.setCodeAssure(assure.getCode());
//                contrat_Sante.setDateNaissance("");
//
//                contrat_Sante.setDateDebutGarantie(GlobalFonctions.formatDate(police.getDate_effet()) + " 00:00 ");
//                contrat_Sante.setDateFinGarantie(GlobalFonctions.formatDate(police.getDate_echeance()) + " 23:59 ");
//                contrat_Sante.setDureGarantie("" + police.getValeurDuree() + " " + GlobalFonctions.valueObject(police.getIdMajorationDuree().getIdDuree().getUniteDuree()));
//                contrat_Sante.setContrat(GlobalFonctions.valueObject(police.getContrat()) + " " + GlobalFonctions.valueObject(police.getNatureContrat()));
//                if (police.getIdCategories().getLibelleAutre() == null) {
//                    contrat_Sante.setCategorie(GlobalFonctions.valueObject(police.getIdCategories().getLibelle()));
//                } else {
//                    contrat_Sante.setCategorie(police.getIdCategories().getLibelleAutre());
//                }
//                // recuperation des garantie
//                listeGarantiePrint = policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprise, police, gp.getIdRefGroupe());
//
////                for (OrclassPoliceGarantie pg : ) {
////                    garantiePrint = new GarantiePrint();
////                    System.out.println("groupe:" + gp.getIdRefGroupe().getLibelle() + " garantie :" + pg.getIdGarantie().getIdRefGaranties().getLibelle() + " prime" + pg.getPrime());
////                    // information sur la garantie
////                    garantiePrint.setGarantie(pg.getIdGarantie().getIdRefGaranties().getLibelle());
////                    if (pg.getCapital() == null) {
////                        garantiePrint.setCapital("");
////                    } else {
////                        garantiePrint.setCapital(GlobalFonctions.formatNumberGeneral(pg.getCapital().longValue()));
////                    }
////                    
////                    if (pg.getTaux() == null) {
////                        garantiePrint.setTaux("");
////                    } else {
////                        garantiePrint.setTaux(GlobalFonctions.formatNumberGeneral(pg.getTaux().longValue()));
////                    }
////                    
////                    if (pg.getPourcentage() == null) {
////                        garantiePrint.setPourcentage("");
////                    } else {
////                        garantiePrint.setPourcentage(GlobalFonctions.formatNumberGeneral(pg.getPourcentage().longValue()));
////                    }
////                    
////                    if (pg.getPrime() == null) {
////                        garantiePrint.setPrime("");
////                    } else {
////                        garantiePrint.setPrime(GlobalFonctions.formatNumberGeneral(pg.getPrime().longValue()));
////                    }
////                    
////                    if (pg.getProrata() == null) {
////                        garantiePrint.setProrate("");
////                    } else {
////                        garantiePrint.setProrate(GlobalFonctions.formatNumberGeneral(pg.getProrata().longValue()));
////                    }
////                    garantiePrint.setTotalPrime(pg.getPrime());
////                    garantiePrint.setLibelleGroup(gp.getIdRefGroupe().getLibelle().toUpperCase());
////                    
////                    listeGarantiePrint.add(garantiePrint);
////                    
////                }
//                contrat_Sante.setColGarantiePrint(this.formatageByAffichage(listeGarantiePrint));
//                /// recuperation des caracteristique 
//                CaracteristiquePrint cp = null;
//                colCaracteristiquePrint = policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprise, police, gp.getIdRefGroupe());
//
//                for (OrclassPoliceCaracteristique pc : colCaracteristiquePrintLocal) {
//
//                    System.out.println("groupe:" + gp.getIdRefGroupe().getLibelle() + " caracterisque :" + pc.getIdCaracteristiques().getLibelle());
////                    cp.setLibelle(pc.getIdCaracteristiques().getLibelle());
//
//                }
//                contrat_Sante.setColCaracteristiquePrint(colCaracteristiquePrint);
//                // recuperation des membres et les familles
//                //recuperation membre 
//                colFamillePrint = risqueFamilleDao.listeFamilleRisqueForGroup(entreprise, police, gp.getIdRefGroupe());
//
////                    System.out.println("groupe:" + gp.getIdRefGroupe().getLibelle() + " risque :" + r.getLibelle());
////                    for (OrclassRisqueFamille rf : ) {
////                        System.out.println("groupe:" + gp.getIdRefGroupe().getLibelle() + " risque :" + r.getLibelle() + " Famille risque " + rf.getNom_membre());
//////                        fp = new OrclassRisqueFamille();
//////                        fp.setMembre(rf.getNom_membre());
//////                        fp.setLien_parente(GlobalFonctions.valueObject(rf.getLienParente()));
//////                        fp.setNaissance(GlobalFonctions.formatDate(rf.getDateNaissance()));
//////                        fp.setEntre_le(GlobalFonctions.formatDate(rf.getDateEntree()));
//////                        fp.setSortie_le(GlobalFonctions.formatDate(rf.getDateSortie()));
//////                        fp.setAdherent(r.getLibelle());
//////                        if (rf.getSexe() != null && rf.getSexe().equals(Sexe.F)) {
//////                            fp.setSexe("Femme");
//////                            
//////                        } else {
//////                            fp.setSexe("Homme");
//////                        }
//////                        fp.setLibelleGroup(gp.getIdRefGroupe().getLibelle().toUpperCase());
////                        colFamillePrint.add(rf);
////                    }
//                contrat_Sante.setColFamille(colFamillePrint);
//                // recuperation prestation 
//                PrestationPrints pp = null;
//                OrclassPlafondMaladie pf = null;
////                    for (OrclassRubrique rb : detailPolicePlafondDao.listeRubrique(police)) {
//                colPrestationPrint = detailPolicePlafondDao.listeDetailPlafond(entreprise, police, gp.getIdRefGroupe());
////                for (OrclassDetailPolicePlafond dp : ) {
////                    pp = new PrestationPrints();
////                    pp.setRubrique(dp.getIdRubrique().getLibelle());
////                    pp.setLibellePrestation(dp.getIdPrestation().getLibelle());
////                    if (dp.getTaux() == null) {
////                        pp.setTauxPrestation("");
////                    } else {
////                        pp.setTauxPrestation(GlobalFonctions.formatNumberGeneral(dp.getTaux().longValue()));
////                    }
////
////                    if (dp.getPlafond() == null) {
////                        pp.setPlafond("");
////                    } else {
////                        pp.setPlafond(GlobalFonctions.formatNumberGeneral(dp.getPlafond().longValue()));
////                    }
////                    if (dp.getBarem() == null) {
////                        pp.setBareme("");
////                    } else {
////                        pp.setBareme(GlobalFonctions.formatNumberGeneral(dp.getBarem().longValue()));
////                    }
////                    if (dp.getForfait() == null) {
////                        pp.setForfait("");
////                    } else {
////                        pp.setForfait(GlobalFonctions.formatNumberGeneral(dp.getForfait().longValue()));
////                    }
////                    if (dp.getTypeDetailMaladie() != null) {
////                        pp.setType(GlobalFonctions.valueObject(dp.getTypeDetailMaladie()));
////                    } else {
////                        pp.setType("");
////                    }
////                    if (dp.getModeCalculDetailMaladie() != null) {
////                        pp.setModeCalcul(GlobalFonctions.valueObject(dp.getModeCalculDetailMaladie()));
////                    } else {
////                        pp.setModeCalcul("");
////                    }
////
////                    pf = dp.getIdPolicePlafond().getIdPlafondMaladie();
////                    pp.setDevise(police.getIdDevise().getCode());
////                    pp.setDomaine_application_plafond(GlobalFonctions.valueObject(pf.getDomaine_application()));
////                    pp.setMode_controle_plafond(GlobalFonctions.valueObject(pf.getModeControle()));
////                    pp.setMontant_plafond(GlobalFonctions.formatNumberGeneral(pf.getMontant().longValue()));
////                    if (pf.getPriode() != null && pf.getPriode().intValue() != 0) {
////                        pp.setUnit_plafond(GlobalFonctions.valueObject(pf.getUniteDuree()));
////                        pp.setPeriode_couverture_plafond("" + pf.getPriode().intValue());
////                    }
////                    String texte = "Prestation(s) au dessous remboursable(s) à concurence de " + pp.getMontant_plafond() + " " + police.getIdDevise().getCode().toUpperCase() + " pour une periode de " + pp.getPeriode_couverture_plafond() + " " + pp.getUnit_plafond() + " " + " consécutifs appliquées";
////                    if (pf.getDomaine_application().equals(DomaineApplication.adherent)) {
////                        texte += " à l' Adherent";
////                    } else if (pf.getDomaine_application().equals(DomaineApplication.famille)) {
////                        texte += " à la Famille";
////                    } else if (pf.getDomaine_application().equals(DomaineApplication.groupe)) {
////                        texte += " au Groupe";
////                    } else if (pf.getDomaine_application().equals(DomaineApplication.membre)) {
////                        texte += " au Membre";
////                    } else if (pf.getDomaine_application().equals(DomaineApplication.personne)) {
////                        texte += " au Personne";
////                    } else if (pf.getDomaine_application().equals(DomaineApplication.police)) {
////                        texte += " à la Police";
////                    }
////                    pp.setTexte(texte);
////                    pp.setLibelleGroup(gp.getIdRefGroupe().getLibelle().toUpperCase());
////                    colPrestationPrint.add(pp);
////
////                }
//                ComparatorChain chain = new ComparatorChain();
//                chain.addComparator(OrclassDetailPolicePlafond.ByRubriqueASC);
//
//                Collections.sort((List<OrclassDetailPolicePlafond>) colPrestationPrint, chain);
//                contrat_Sante.setColPrestation(colPrestationPrint);
//
//                //information plafond Maladie
//                plafondMaladie = policePlafondDao.chargePlafonMaladie(police, entreprise, gp.getIdRefGroupe());
//                if (plafondMaladie != null && plafondMaladie.getId() != null) {
//                    contrat_Sante.setLibellePlafondMaladie(plafondMaladie.getLibelle());
//                    contrat_Sante.setMontant(GlobalFonctions.formatNumberGeneral(plafondMaladie.getMontant().longValue()));
//                    contrat_Sante.setModeControle(GlobalFonctions.valueObject(plafondMaladie.getModeControle()));
//                    contrat_Sante.setPeriodeCouverture("" + plafondMaladie.getPriode());
//                    contrat_Sante.setDomaineApplication(GlobalFonctions.valueObject(plafondMaladie.getDomaine_application()));
//                    contrat_Sante.setActe("" + plafondMaladie.getNombre_Acte());
//                }
//                /**
//                 * recuperer le groupe Police
//                 *
//                 */
////                 OrclassGroupePolice gp=groupePoliceDao.ListeGroupByCompagnieForPolice(entreprise, police)
//                contrat_Sante.setLibelleGroup(gp.getIdRefGroupe().getLibelle().toUpperCase());
//                contrat_Sante.setAdresseGroup(gp.getAdresseGroup());
//                contrat_Sante.setVilleGroup(gp.getIdVille().getLibelle().toUpperCase());
//                contrat_Sante.setDevise(police.getIdDevise().getCode().toUpperCase());
//                contrat_Sante.setObservation(gp.getObservation());
//                contrat_Sante.setGroupePolice(gp);
//                contrat_Sante.setOrdre_groupe(order_bygroup);
//                listeContrat_sante.add(contrat_Sante);
//                t[i] = contrat_Sante;
//                i++;
//
//            }
//            colContrat_Sante.addAll(listeContrat_sante);
//            this.printCcontratMaladieGroup();
//            etatModifier = Boolean.FALSE;
//        }
//    }
//
//    public void showDetailImageRisque(OrclassImageRisque item) {
//        this.setImageRisque(item);
//    }
//
//    public List<SousCaracteristique> charegSousCaracteristiqueProduit() {
//        List<SousCaracteristique> listeScprd = new ArrayList<>();
//        SousCaracteristique sousCaracteristique = null;
//        if (categories != null && categories.getIdCategorie() != null) {
//
//            for (OrclassSousCaracteristiqueProduit scpdrs : scprdDao.listeSousCaracteristiqueProduitByProduit(entreprise, categories)) {
//                switch (scpdrs.getSousCaracteristiqueProduitEnum()) {
//                    case nombre_adherent:
//                        sousCaracteristique = new SousCaracteristique();
//                        sousCaracteristique.setLibelle(scpdrs.getIdCaracteristiques().getLibelle());
//                        sousCaracteristique.setValeur("" + nombre_assure);
//                        listeScprd.add(sousCaracteristique);
//                        break;
//                    case nombre_adherent_membre:
//                        sousCaracteristique = new SousCaracteristique();
//                        sousCaracteristique.setLibelle(scpdrs.getIdCaracteristiques().getLibelle());
//
//                        sousCaracteristique.setValeur("" + (nombre_assure + nombre_membre));
//                        listeScprd.add(sousCaracteristique);
//                        break;
//                    case nombre_enfant:
//                        sousCaracteristique = new SousCaracteristique();
//                        sousCaracteristique.setLibelle(scpdrs.getIdCaracteristiques().getLibelle());
//
//                        sousCaracteristique.setValeur("" + (nombre_enfant));
//                        listeScprd.add(sousCaracteristique);
//                        break;
//                    case nombre_personne:
//                        sousCaracteristique = new SousCaracteristique();
//                        sousCaracteristique.setLibelle(scpdrs.getIdCaracteristiques().getLibelle());
//
//                        sousCaracteristique.setValeur("" + total_personne);
//                        listeScprd.add(sousCaracteristique);
//                        break;
//                    case prime_base_adherent:
//                        double prime_assure = totalPrime.intValue() / nombre_assure;
//                        prime_assure = GlobalFonctions.getNumberNet(prime_assure);
//                        sousCaracteristique = new SousCaracteristique();
//                        sousCaracteristique.setLibelle(scpdrs.getIdCaracteristiques().getLibelle());
//
//                        sousCaracteristique.setValeur(GlobalFonctions.formatNumberGeneral(Long.parseLong("" + GlobalFonctions.getNumberNetForInt(prime_assure))));
//                        listeScprd.add(sousCaracteristique);
//                        break;
//
//                }
//            }
//        }
//        return listeScprd;
//    }
//
//    public void printCcontratMaladieGroup() throws IOException, JRException {
//        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> map_subreport = new HashMap<>();
////        Map<String, Object> map_sousCaracteristique = new HashMap<>();
////        ComparatorChain chain = new ComparatorChain();
//        ImageIcon logo = null;
//        ImageIcon img = null;
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        Locale myLoc = ctx.getViewRoot().getLocale();
//        List<SousCaracteristique> listeScprd = new ArrayList<>(this.charegSousCaracteristiqueProduit());
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
//        map.put("tva", quittance.getTotalTaxes());
//        map.put("total_net_payer", quittance.getTotal_a_paye());
//        map.put("primenet", quittance.getPrimeNette());
//
//        map_subreport.put("tva_v", this.tva);
//        map_subreport.put("tva_v_1", this.tva.divide(BigDecimal.valueOf(100.0)));
//        map_subreport.put("scpd", listeScprd);
//        map.put("map_subreport", map_subreport);
//        if (numero_police.signum() < 0) {
//            map.put("proposition", "PROPOSITION");
//        } else {
//            map.put("proposition", "");
//        }
//        GlobalFonctions.retourneListe(listeScprd);
//        GlobalFonctions.printPdfHaveSubReport(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "groupMaladie", colContrat_Sante, map, null);
////        momtantTT = BigDecimal.ZERO;
//    }
//
//    public void printCcontratMaladieIndividuelOrMaladieFamill() throws IOException, JRException {
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
//        map.put("tva", quittance.getTotalTaxes());
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
//        if (Objects.equals(tabShowIndividuel, Boolean.TRUE)) {
//            GlobalFonctions.printPdfHaveSubReport(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "individueMaladie", colContrat_Sante, map, null);
//
//        } else if (Objects.equals(tabShowFamille, Boolean.TRUE)) {
//
//            GlobalFonctions.printPdfHaveSubReport(GlobalFonctions.dossierPrincipal + File.separator + GlobalFonctions.dossierContratSante + File.separator + GlobalFonctions.dossierDefaut + File.separator + "FamilleMaladie_1", colContrat_Sante, map, null);
//
//        }
//        //        momtantTT = BigDecimal.ZERO;
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
//    public OrclassPoliceGarantie getPoliceGarantieAffiche() {
//        return policeGarantieAffiche;
//    }
//
//    public void setPoliceGarantieAffiche(OrclassPoliceGarantie policeGarantieAffiche) {
//        this.policeGarantieAffiche = policeGarantieAffiche;
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
//    public OrclasseRefGroupe getRefGroupeSelectForPrestation() {
//        return refGroupeSelectForPrestation;
//    }
//
//    public void setRefGroupeSelectForPrestation(OrclasseRefGroupe refGroupeSelectForPrestation) {
//        this.refGroupeSelectForPrestation = refGroupeSelectForPrestation;
//    }
//
//    public OrclasseRefGroupe getRefGroupeSelectForCaracteristiqueAndGarantie() {
//        return refGroupeSelectForCaracteristiqueAndGarantie;
//    }
//
//    public void setRefGroupeSelectForCaracteristiqueAndGarantie(OrclasseRefGroupe refGroupeSelectForCaracteristiqueAndGarantie) {
//        this.refGroupeSelectForCaracteristiqueAndGarantie = refGroupeSelectForCaracteristiqueAndGarantie;
//    }
//
//    public OrclasseRefGroupe getRefGroupeSelectForGarantie() {
//        return refGroupeSelectForGarantie;
//    }
//
//    public void setRefGroupeSelectForGarantie(OrclasseRefGroupe refGroupeSelectForGarantie) {
//        this.refGroupeSelectForGarantie = refGroupeSelectForGarantie;
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
//    public OrclassPoliceCaracteristique getPoliceCaracteristique() {
//        return policeCaracteristique;
//    }
//
//    public void setPoliceCaracteristique(OrclassPoliceCaracteristique policeCaracteristique) {
//        this.policeCaracteristique = policeCaracteristique;
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
//    public BigDecimal getTva() {
//
//        return tva;
//    }
//
//    public void setTva(BigDecimal tva) {
//        this.tva = tva;
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
//    public List<OrclassPrestation> getFilterPrestation() {
//        return filterPrestation;
//    }
//
//    public void setFilterPrestation(List<OrclassPrestation> filterPrestation) {
//        this.filterPrestation = filterPrestation;
//    }
//
//    public int getNombre_membre() {
//        return nombre_membre;
//    }
//
//    public void setNombre_membre(int nombre_membre) {
//        this.nombre_membre = nombre_membre;
//    }
//
//    public int getNombre_enfant() {
//        return nombre_enfant;
//    }
//
//    public void setNombre_enfant(int nombre_enfant) {
//        this.nombre_enfant = nombre_enfant;
//    }
//
//    public int getTotal_personne() {
//        return total_personne;
//    }
//
//    public void setTotal_personne(int total_personne) {
//        this.total_personne = total_personne;
//    }
//
//    public int getNombre_assure() {
//        return nombre_assure;
//    }
//
//    public void setNombre_assure(int nombre_assure) {
//        this.nombre_assure = nombre_assure;
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
//}
