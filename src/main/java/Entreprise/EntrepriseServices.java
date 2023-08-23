package Entreprise;

import dao.AbstractJpaDAO;
import dao.AnneeDao;

import dao.ORCLASS_PROFILS_UTILISATEURSDao;
import dao.OrclassEntreprisesModulesDao;
import dao.OrclassModulesDao;
import dao.OrclassProfilsAccesDao;
import dao.OrclassProfilsDao;
import dao.OrclassUtilisateursAccesDao;
import dao.OrclassUtilisateursDao;
import dao.SocieteDao;
import enums.TypeUtilisateur;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import modele.ORCLASS_PROFILS_UTILISATEURS;
import modele.OrclassEntreprises;
import modele.OrclassEntreprisesModules;
import modele.OrclassEntreprisesModulesPK;
import modele.OrclassModules;
import modele.OrclassProfils;
import modele.OrclassProfilsAcces;
import modele.OrclassUtilisateurs;
import modele.OrclassUtilisateursAcces;
import modele.Societe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Crypto;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class EntrepriseServices implements IEntreprise {
    
    public static final Logger logger = LoggerFactory.getLogger(EntrepriseServices.class);
    
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    
    @Resource
    UserTransaction tx;
    
    @EJB
    private AnneeDao anneeDao;
    
    @EJB
    private SocieteDao societeDao;
    
    @EJB
    private OrclassModulesDao orclassModulesDao;
    
    @EJB
    OrclassProfilsDao profilsDao;
    
    @EJB
    OrclassProfilsAccesDao profilsAccesDao;
    
    @EJB
    OrclassUtilisateursAccesDao utilisateursAccesDao;
    
    @EJB
    OrclassUtilisateursDao utilisateursDao;
    
    @EJB
    OrclassEntreprisesModulesDao entreprisesModulesDao;
    
    @EJB
    ORCLASS_PROFILS_UTILISATEURSDao pROFILS_UTILISATEURSDao;
    
    public Societe addEntreprise(List<OrclassModules> collModules, Societe societe, Date debut, Date fin, OrclassUtilisateurs utilisateur) {
        Societe s = null;
        ORCLASS_PROFILS_UTILISATEURS pu = null;
        try {
            this.tx.begin();
            OrclassProfils profil = null;
            s = this.societeDao.getSocieteByCode(societe.getCodesoci());
            if (s == null) {
                s = new Societe();
                s.setChemin_logo(societe.getChemin_logo());
                s.setCodesoci(societe.getCodesoci());
                s.setDateCreation(new Date());
                s.setLogosoci(societe.getLogosoci());
                s.setRegicomm(societe.getRegicomm());
                s.setNpSoci(societe.getNpSoci());
                s.setRaissoci(societe.getRaissoci().toUpperCase());
                s.setAdresoci(societe.getAdresoci());
                s.setDgcontact(societe.getDgcontact());
                s.setAbresoci(societe.getAbresoci());
                s.setAlgoId(societe.getAlgoId());
                s.setCapisoci(societe.getCapisoci());
                s.setCodeenti(societe.getCodeenti());
                s.setCodlanme(societe.getCodlanme());
                s.setCommsoci(societe.getCommsoci());
                s.setComsocb0(societe.getComsocb0());
                s.setComsocba(societe.getComsocba());
                s.setCosopotv(societe.getCosopotv());
                s.setDecimonn(societe.getDecimonn());
                s.setFaxSoci(societe.getFaxSoci());
                s.setFlagmodu(societe.getFlagmodu());
                s.setFlagsecu(societe.getFlagsecu());
                s.setFlagtrac(societe.getFlagtrac());
                s.setIfSoci(societe.getIfSoci());
                s.setLencomco(societe.getLencomco());
                s.setMasqassu(societe.getMasqassu());
                s.setMasqdate(societe.getMasqdate());
                s.setModcodas(societe.getModcodas());
                s.setModedepl(societe.getModedepl());
                s.setMonnsoci(societe.getMonnsoci());
                s.setNumeLot(societe.getNumeLot());
                s.setOldnumlo(societe.getOldnumlo());
                s.setOrasadmi(societe.getOrasadmi());
                s.setPertrare(societe.getPertrare());
                s.setPlanumas(societe.getPlanumas());
                s.setSsSoci(societe.getSsSoci());
                s.setStajurso(societe.getStajurso());
                s.setTelesoci(societe.getTelesoci());
                s.setTelxsoci(societe.getTelxsoci());
                s.setTvaSoci(societe.getTvaSoci());
                s.setTypecomp(societe.getTypecomp());
                s.setUnimonca(societe.getUnimonca());
                s.setVillsoci(societe.getVillsoci());
             
                this.em.persist(s);
            }
            s = (Societe) this.em.merge(s);
            for (OrclassModules m : collModules) {
                OrclassEntreprisesModules me = null;
                me = (OrclassEntreprisesModules) this.entreprisesModulesDao.find(new OrclassEntreprisesModulesPK(m.getIdModule(), s.getCodesoci()));
                if (me == null) {
                    OrclassEntreprisesModules mes = new OrclassEntreprisesModules(new OrclassEntreprisesModulesPK(m.getIdModule(), s.getCodesoci()));
                    mes.setOrclassModules(m);
                    mes.setSociete(s);
                    mes.setDateDebut(debut);
                    mes.setDateFin(fin);
                    this.em.persist(mes);
                }
            }
            profil = (OrclassProfils) this.profilsDao.findEntityHavingValue("code", "admin");
            OrclassUtilisateursAcces ua = null;
            OrclassUtilisateurs user = null;
            if (profil != null && profil.getIdProfil() != null) {
                user = (OrclassUtilisateurs) this.utilisateursDao.findEntityHavingValue("login", utilisateur.getLogin().toUpperCase());
                if (user == null) {
                    user = new OrclassUtilisateurs();
                    user.setLogin(utilisateur.getLogin().toUpperCase());
                    user.setNom(utilisateur.getNom());
                    user.setPrenom(utilisateur.getPrenom());
                    user.setActif(Boolean.TRUE);
                    user.setLangue(utilisateur.getLangue());
                    user.setIdSociete(societe);
                    user.setAdresse(utilisateur.getAdresse());
//          user.setActif(Boolean.TRUE);
//          user.setPassword(Crypto.sha256(utilisateur.getPassword().toUpperCase()));
                    user.setTypeUtilisateur(TypeUtilisateur.UTILISATEUR_ENTITE);
                    this.em.persist(user);
                }
                user = (OrclassUtilisateurs) this.em.merge(user);
                for (OrclassProfilsAcces pa : this.profilsAccesDao.getAllAccesByProfil(profil)) {
                    if (this.utilisateursAccesDao.finKey(pa, user) == null) {
                        ua = new OrclassUtilisateursAcces();
                        ua.setAutorisation_principal(Boolean.TRUE);
                        ua.setAutorisation_fonctionnalite(Boolean.TRUE);
                        ua.setAutorisation_action(Boolean.TRUE);
                        ua.setIdAction(pa.getIdAction());
                        ua.setIdFonctionnalite(pa.getIdFonctionnalite());
                        ua.setIdModule(pa.getIdModule());
                        ua.setIdProfil(profil);
                        ua.setIdUtilisateur(user);
                        this.em.persist(ua);
                    }
                }
                if (this.pROFILS_UTILISATEURSDao.finkey(user, profil) == null) {
                    pu = new ORCLASS_PROFILS_UTILISATEURS();
                    pu.setIdProfil(profil);
                    pu.setIdUtilisateur(user);
                    this.em.persist(pu);
                }
            }
            this.tx.commit();
        } catch (IllegalStateException | SecurityException | javax.transaction.HeuristicMixedException | javax.transaction.HeuristicRollbackException | javax.transaction.NotSupportedException | javax.transaction.RollbackException | javax.transaction.SystemException ex) {
            System.err.println("message error : " + ex.getMessage() + " localisation " + ex.getLocalizedMessage() + " " + ex.toString());
        }
        return s;
    }
}
