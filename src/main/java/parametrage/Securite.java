package parametrage;

import static Entreprise.EntrepriseServices.logger;
import Licence.KeyGenCode;
import dao.AbstractJpaDAO;
import dao.ConnectionLoggerDao;

import dao.OrclassActionsDao;


import dao.OrclassEntreprisesDao;
import dao.OrclassEntreprisesModulesDao;
import dao.OrclassFonctionnalitesDao;
//import dao.OrclassMenusAccesDao;
import dao.OrclassMenusDao;
import dao.OrclassModulesDao;

import dao.OrclassProfilModuleDao;
import dao.OrclassProfilsAccesDao;
import dao.OrclassProfilsDao;

import dao.OrclassUtilisateursAccesDao;
import dao.OrclassUtilisateursDao;
import enums.TypeUtilisateur;
import exception.GlobalException;

import exception.LicenceException;
//import exception.LicenceException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import modele.ConnectionLogger;

import modele.OrclassActions;


import modele.OrclassEntreprises;

import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassModules;

import modele.OrclassProfilModule;
import modele.OrclassProfils;
import modele.OrclassProfilsAcces;

import modele.OrclassUtilisateurs;
import modele.OrclassUtilisateursAcces;
import modele.Societe;

import utils.Crypto;

/**
 *
 * @author fabrice
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class Securite implements ISecurite {
    // debut Declaration modele et Injetion des EJBs

    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
    @Resource
    UserTransaction tx;

    @EJB
    private OrclassMenusDao menuDao;

    @EJB
    OrclassUtilisateursDao userDao;
    @EJB
    OrclassFonctionnalitesDao fonctionnalitesDao;
    @EJB
    OrclassActionsDao actionsDao;

    @EJB
    private OrclassModulesDao ModulesecuriteDao;
    @EJB
    private OrclassProfilsDao profilsDao;
    @EJB
    OrclassProfilsAccesDao profilsAccesDao;
    @EJB
    OrclassEntreprisesDao entreprisesDao;
    @EJB
    ConnectionLoggerDao connectionLoggerDao;
    @EJB
    OrclassUtilisateursAccesDao utilisateursAccesDao;
    @EJB
    OrclassProfilModuleDao profilModuleDao;
//    @EJB
//    OrclassMenusAccesDao menusAccesDao;
    @EJB
    OrclassMenusDao orclassMenusDao;
    @EJB
    OrclassEntreprisesModulesDao entreprisesModulesDao;
   

    @Override

    /**
     * verification si l utilisateur qui est entrain de se connecter existe
     */
    public OrclassUtilisateurs getAuthentification(OrclassUtilisateurs u) {

        String pwd = Crypto.sha256(u.getPassword().toUpperCase());
        String jpql = "SELECT us FROM OrclassUtilisateurs us WHERE us.login = :login and us.password = :Pwd";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("login", u.getLogin().toUpperCase());
        param.put("Pwd", pwd);
        OrclassUtilisateurs user = userDao.findEntityByUsingQuery(jpql, param);
        return user;
    }

    @Override
    public void resetPassword(OrclassUtilisateurs u, String newPassword) {
        u.setPassword(newPassword);
        userDao.update(u);
    }

    //Retourne tous les parametres d un module
    @Override
    public OrclassModules getModule(OrclassModules modulesecurite) {
        String jpql = "SELECT m FROM OrclassModules m where m.id=:modId";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("modId", modulesecurite.getIdModule());
        OrclassModules Module = ModulesecuriteDao.findEntityByUsingQuery(jpql, param);
        return Module;
    }

    //recupereration des modules d'un user
    @Override
    public Collection<OrclassModules> getModuleByUser(OrclassUtilisateurs u, OrclassProfils pr) {
        String jpql = "SELECT distinct m FROM OrclassModules m join m.orclassUtilisateursAccesList u WHERE u.idUtilisateur.idUtilisateur = :userId and u.idProfil.idProfil= :idProfil and (u.autorisation_principal=true or u.autorisation_principal=1)";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", u.getIdUtilisateur());
        param.put("idProfil", pr.getIdProfil());
        Collection<OrclassModules> listUserModule = new ArrayList<OrclassModules>();

        listUserModule = ModulesecuriteDao.findAllEntitiesByUsingQuery(jpql, param);
        return listUserModule;
    }

    @Override
    public Collection<OrclassModules> getModuleByUserByProfile(OrclassUtilisateurs u, OrclassProfils p) {
        String jpql = "SELECT m FROM OrclassModules m join m.orclassUtilisateursAccesList u  WHERE u.idUtilisateur.idUtilisateur = :userId and u.idProfil.idProfil= :idProfil";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", u.getIdUtilisateur());
        param.put("idProfil", p.getIdProfil());
        Collection<OrclassModules> listUserModule = new ArrayList<OrclassModules>();

        listUserModule = ModulesecuriteDao.findAllEntitiesByUsingQuery(jpql, param);
        return listUserModule;
    }

    @Override
    public Collection<OrclassModules> getModuleByProfile(OrclassProfils p) {
        String jpql = "SELECT distinct  m FROM OrclassModules m join m.orclassProfilsAccesList p WHERE p.idProfil.idProfil = :idProfil";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("idProfil", p.getIdProfil());
        Collection<OrclassModules> listUserModule = new ArrayList<OrclassModules>();

        listUserModule = ModulesecuriteDao.findAllEntitiesByUsingQuery(jpql, param);
        return listUserModule;
    }

    //recuperation de tous les modules
    @Override
    public Collection<OrclassModules> getAllModules() {
        Collection<OrclassModules> listmodulesecurite = ModulesecuriteDao.findAll();
        return listmodulesecurite;
    }
    //recuperation des modules qui ne sont pas a un user

    @Override
    public Collection<OrclassModules> getModuleForNotUser(OrclassUtilisateurs u, OrclassProfils pr) {
        Collection<OrclassModules> listUserModule3 = new ArrayList<>();
//        String jpql = "select t from OrclassModules t where t.idModule NOT IN(SELECT m FROM OrclassModules m "
//                + "join m.orclassUtilisateursAccesList u WHERE u.idUtilisateur.idUtilisateur = :userId and u.idProfil.idProfil= :idProfil and (u.autorisation_principal=true or u.autorisation_principal=1) )";
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("userId", u.getIdUtilisateur());
//        param.put("idProfil", pr.getIdProfil());
//        
//        Collection<OrclassModules> listUserModule = ModulesecuriteDao.findAllEntitiesByUsingQuery(jpql, param);
//        Collection<OrclassModules> listUserModule2 = this.getAllModuleHaveProfilByUser(pr);
//        for (OrclassModules md : listUserModule) {
//            if (listUserModule2.contains(md) == true) {
//                listUserModule3.add(md);
//            }
//        }
        Query q;
        q = em.createQuery("SELECT distinct pm.idModule FROM OrclassProfilModule pm WHERE NOT EXISTS (select distinct u.idModule FROM OrclassUtilisateursAcces u where u.idModule.idModule=pm.idModule.idModule  and  u.idUtilisateur.idUtilisateur = :userId and u.idProfil.idProfil= :idProfil  ) and pm.idProfil= :idProfil")
                .setParameter("userId", u.getIdUtilisateur())
                .setParameter("idProfil", pr.getIdProfil());

        return q.getResultList();
    }

    public Collection<OrclassModules> getAllModuleHaveProfilByUser(OrclassProfils pr) {
        Query q = em.createQuery("SELECT distinct pa.idModule From OrclassProfilsAcces pa WHERE pa.idProfil.idProfil= :idProfil and (pa.autorisation=true or pa.autorisation=1)")
                .setParameter("idProfil", pr.getIdProfil());
        return q.getResultList();
    }

    @Override
    public Collection<OrclassModules> getModuleForNotProfile(OrclassProfils p) {
        String jpql = "select t from OrclassModules t where t.idModule NOT IN(SELECT m FROM OrclassModules m "
                + "join m.orclassProfilsAccesList p WHERE p.idProfil.idProfil = :idProfil)";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("idProfil", p.getIdProfil());

        Collection<OrclassModules> listUserModule = ModulesecuriteDao.findAllEntitiesByUsingQuery(jpql, param);
        return listUserModule;
    }
    //recuperation des menus d'un module

    @Override
    public Collection<OrclassMenus> getMenusByModule(OrclassModules mod) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("moduleid", mod.getIdModule());
        Collection<OrclassMenus> listModuleMenu = menuDao.findAllEntitiesByUsingQueryName(OrclassMenus.FIND_BY_MODULEID_MENU, param);
        return listModuleMenu;
    }

    @Override
    public Collection<OrclassFonctionnalites> getFonctionnaliterByModule(OrclassModules mod) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("moduleid", mod.getIdModule());
        Collection<OrclassFonctionnalites> listModuleMenu = fonctionnalitesDao.findAllEntitiesByUsingQueryName(OrclassFonctionnalites.FIND_BY_MODULEID, param);
        return listModuleMenu;
    }

    @Override
    public Collection<OrclassActions> getActionByFonctionnalite(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils p) {

        Query q;
        q = em.createQuery("SELECT distinct m FROM OrclassActions m join m.orclassUtilisateursAccesList u WHERE u.idFonctionnalite.idFonctionnalite= :idFonctionnalite and  "
                + " u.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur = :userId and (u.autorisation_action=true or u.autorisation_action=1) and u.idProfil.idProfil= :idProfil ")
                .setParameter("moduleid", m.getIdModule())
                .setParameter("userId", u.getIdUtilisateur())
                .setParameter("idProfil", p.getIdProfil())
                .setParameter("idFonctionnalite", f.getIdFonctionnalite());
        return q.getResultList();

    }

    /**
     * recuperer les fonctionnalite d un profil
     *
     * @param p
     * @return
     */
    @Override
    public Collection<OrclassFonctionnalites> getFonctionnaliteByProfil(OrclassProfils p, OrclassModules m) {
        Collection<OrclassFonctionnalites> listModuleMenu = fonctionnalitesDao.listeFonctionaliteByProfil(p, m);
        return listModuleMenu;
    }

    //recuperation des menus d'un user pour un module precis
    @Override
    public Collection<OrclassMenus> getMenusbyModuleForUser(OrclassUtilisateurs u, OrclassModules m) {
        // les menus qui sont attribuer directement au role
        Collection<OrclassMenus> listUserMenuRole = new ArrayList<OrclassMenus>();
        if (u != null && m != null) {
            String jpql = "SELECT m FROM OrclassMenus m join m.orclassUtilisateursAccesList u WHERE"
                    + " m.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur = :userid and (u.autorisation=true or u.autorisation=1)";
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("moduleid", m.getIdModule());
            param.put("userid", u.getIdUtilisateur());
            listUserMenuRole = menuDao.findAllEntitiesByUsingQuery(jpql, param);

        }
        return listUserMenuRole;
    }

    @Override
    public Collection<OrclassFonctionnalites> getFonctionnnalitebyModuleForProfile(OrclassModules m, OrclassProfils p) {
        Collection<OrclassFonctionnalites> listUserMenuRole = new ArrayList<OrclassFonctionnalites>();
        if (p != null && m != null) {
            String jpql = "SELECT f FROM OrclassFonctionnalites f join f.orclassProfilsAccesList p WHERE"
                    + " p.idModule.idModule = :moduleid and p.idProfil.idProfil = :idProfil and (p.autorisation=true or p.autorisation=1)";
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("moduleid", m.getIdModule());
            param.put("idProfil", p.getIdProfil());
            listUserMenuRole = fonctionnalitesDao.findAllEntitiesByUsingQuery(jpql, param);

        }
        return listUserMenuRole;
    }

    //recuperation des menus non attribue a un user pour un module precis
    @Override
    public Collection<OrclassMenus> getMenusForNotModuleUser(OrclassUtilisateurs u, OrclassModules m) {
        // les menus qui ne sont pas attribuer directement au role
        String jpql = "SELECT t FROM OrclassMenus t where t.idModule.idModule = :moduleid and t.idMenu NOT IN (SELECT m FROM OrclassMenus m join m.orclassUtilisateursAccesList u WHERE"
                + " m.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur = :userid)";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("moduleid", m.getIdModule());
        param.put("userid", u.getIdUtilisateur());
        Collection<OrclassMenus> listUserMenuRole = menuDao.findAllEntitiesByUsingQuery(jpql, param);
        // les menus qui sont dans la table user acces et attribuer a l utilisateur
        param.clear();
        jpql = "SELECT m FROM OrclassMenus m join m.orclassUtilisateursAccesList u WHERE"
                + " m.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur = :userId and (u.autorisation=true or u.autorisation=1)";
        param.put("moduleid", m.getIdModule());
        param.put("userId", u.getIdUtilisateur());
        // on aurait pu utiliser :  listUserMenuRole.removeAll(menuDao.findAllEntitiesByUsingQuery(jpql,param));       
        for (OrclassMenus mu : menuDao.findAllEntitiesByUsingQuery(jpql, param)) {
            if (listUserMenuRole.contains(mu) == true) {
                listUserMenuRole.remove(mu);
            }
        }
        // les menus qui sont dans la table user acces non attribuer a l utilisateur
        param.clear();
        jpql = "SELECT m FROM OrclassMenus m join m.orclassUtilisateursAccesList u WHERE"
                + " m.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur= :userId and (u.autorisation=false or u.autorisation=0)";
        param.put("moduleid", m.getIdModule());
        param.put("userId", u.getIdUtilisateur());
        // on aurait pu utiliser : listUserMenuRole.addAll(menuDao.findAllEntitiesByUsingQuery(jpql,param));
        for (OrclassMenus mu : menuDao.findAllEntitiesByUsingQuery(jpql, param)) {
            if (listUserMenuRole.contains(mu) == false) {
                listUserMenuRole.add(mu);
            }
        }
        return listUserMenuRole;
    }

    @Override
    public Collection<OrclassFonctionnalites> getFonctionnalitesesForNotModuleUserProfile(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p) {
        String jpql = "SELECT distinct t FROM OrclassFonctionnalites t where t.idModule.idModule = :moduleid and t.idFonctionnalite NOT IN (SELECT m FROM OrclassFonctionnalites m join m.orclassUtilisateursAccesList u WHERE"
                + " m.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur = :userid and u.idProfil.idProfil= :idProfil and (u.autorisation_fonctionnalite=true or u.autorisation_fonctionnalite=1))";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("moduleid", m.getIdModule());
        param.put("userid", u.getIdUtilisateur());
        param.put("idProfil", p.getIdProfil());
        Collection<OrclassFonctionnalites> listUserMenuRole = fonctionnalitesDao.findAllEntitiesByUsingQuery(jpql, param);
//        // les menus qui sont dans la table user acces et attribuer a l utilisateur
//        param.clear();
//        jpql = "SELECT m FROM OrclassFonctionnalites m join m.orclassUtilisateursAccesList u WHERE"
//                + " m.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur = :userId and (u.autorisation=true or u.autorisation=1) and u.idProfil.idProfil= :idProfil";
//        param.put("moduleid", m.getIdModule());
//        param.put("userId", u.getIdUtilisateur());
//        param.put("idProfil", p.getIdProfil());
//        // on aurait pu utiliser :  listUserMenuRole.removeAll(menuDao.findAllEntitiesByUsingQuery(jpql,param));       
//        for (OrclassFonctionnalites mu : fonctionnalitesDao.findAllEntitiesByUsingQuery(jpql, param)) {
//            if (listUserMenuRole.contains(mu) == true) {
//                listUserMenuRole.remove(mu);
//            }
//        }
//        // les menus qui sont dans la table user acces non attribuer a l utilisateur
//        param.clear();
//        jpql = "SELECT m FROM OrclassFonctionnalites m join m.orclassUtilisateursAccesList u WHERE"
//                + " m.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur= :userId and (u.autorisation=false or u.autorisation=0) and u.idProfil.idProfil= :idProfil";
//        param.put("moduleid", m.getIdModule());
//        param.put("userId", u.getIdUtilisateur());
//        param.put("idProfil", p.getIdProfil());
//        // on aurait pu utiliser : listUserMenuRole.addAll(menuDao.findAllEntitiesByUsingQuery(jpql,param));
//        for (OrclassFonctionnalites mu : fonctionnalitesDao.findAllEntitiesByUsingQuery(jpql, param)) {
//            if (listUserMenuRole.contains(mu) == false) {
//                listUserMenuRole.add(mu);
//            }
//        }
        return listUserMenuRole;
    }

    //Les menus attribuer a un role pour un module precis
//    @Override
//    public Collection<Menu> getMenusbyModuleForRole(Role r, Modulesecurite m) {
//        // les menus qui sont attribue directement au role pour un module precis
//        String jpql = "SELECT m FROM Menu m join m.rolemenuCollection r join r.role ro  WHERE"
//                + " m.moduleid.id = :moduleid and ro.id = :roleId";
//        Map<String, Object> param = new HashMap<>();
//        param.put("moduleid", m.getId());
//        param.put("roleId", r.getId());
//        Collection<Menu> listMenuRole = menuDao.findAllEntitiesByUsingQuery(jpql, param);
//        return listMenuRole;
//    }
//     //Les menus non attribuer a un role pour un module precis
//    @Override
//    public Collection<Menu> getMenusForNotModuleRole(Role r, Modulesecurite m) {
//         // les menus non attribue directement au role pour un module precis
//        String jpql = "SELECT t FROM Menu t WHERE t.moduleid.id = :moduleid AND t.id NOT IN "
//                + "(SELECT m.id FROM Menu m join m.rolemenuCollection r WHERE"
//                + " m.moduleid.id = :moduleid and r.rolemenuPK.roleId = :roleId)";
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("moduleid", m.getId());
//        param.put("roleId", r.getId());
//        Collection<Menu> listMenuRole = menuDao.findAllEntitiesByUsingQuery(jpql, param);
//        return listMenuRole;
//    }
    @Override
    public void addModuleToFonctionnaliteActionProfile(OrclassModules mod, OrclassFonctionnalites f, List<OrclassActions> a, OrclassProfils p) {
        try {
            tx.begin();
            OrclassProfils profile = null;
            OrclassProfilsAcces pa = null;
            if (p != null && p.getIdProfil() == null) {
                if (profilsDao.findEntityHavingValue("code", p.getCode()) == null) {
                    profile = new OrclassProfils();
                    profile.setCode(p.getCode());
                    profile.setLibelle(p.getLibelle());
                    profile.setActif(Boolean.TRUE);
                    profile.setDateCreation(new Date());
                    em.persist(profile);

                } else {
                    profile = em.merge(profilsDao.findEntityHavingValue("code", p.getCode()));
                }
            } else if (p.getIdProfil() != null) {
                profile = em.merge(p);
            }

            for (OrclassActions action : a) {
                if (profilsAccesDao.finkey(mod, f, action, profile) != null) {
                    continue;
                }
                pa = new OrclassProfilsAcces();
                pa.setAutorisation(Boolean.TRUE);
                pa.setIdAction(action);
                pa.setIdFonctionnalite(f);
                pa.setIdProfil(profile);
                pa.setIdModule(mod);
                em.persist(pa);

            }

            tx.commit();
        } catch (Exception e) {
            try {
                tx.rollback();
                throw e;
            } catch (Throwable t) {

            }
        }
    }

    @Override
    public void addModuleToUser(OrclassUtilisateurs u, OrclassModules m) {
//        usermodule = new OrclassModules();
//        usermodule.setAddDate(new Date());
//        usermodule.setUserAdd(u.getMatricule());
//        usermodule.setUsermodulePK(new UsermodulePK(m.getId(), u.getId()));
//        usermoduleDao.create(usermodule);
    }

    @Override
    public void addModulesToUser(OrclassUtilisateurs u, List<OrclassModules> mod, OrclassProfils p) {
        //je recupère les modules de l'user
        Collection<OrclassModules> moduleUser = this.getModuleByUser(u, p);
        //je teste si l'user a deja des modules
        if (moduleUser.isEmpty()) {
            //je parcours la liste et j'inserte
            for (OrclassModules m : mod) {
                /*
                    etant deja dans la table access je met autorisation a ture
                 */
                this.addModuleFortoUserAcces(u, m, p);
            }
        } else {
            for (OrclassModules m : mod) {
                if (moduleUser.contains(m) == false) {

                    try {
//                        tx.begin();
                        OrclassProfilModule pm;
                        if (profilModuleDao.finkey(p, m) != null) {
                            for (OrclassUtilisateursAcces ua : this.getAllModuleAccesForuserNotHaveAutorisationPrincipal(u, m, p)) {
                                ua.setAutorisation_principal(Boolean.TRUE);
                                utilisateursAccesDao.update(ua);
                            }
                            continue;
                        }
                        pm = new OrclassProfilModule();
                        pm.setIdModule(m);
                        pm.setIdProfil(p);
                        profilModuleDao.create(pm);
//                        em.persist(pm);
//                        tx.commit();
                    } catch (Exception e) {

                    }

                }
//                for (OrclassUtilisateursAcces ua : this.getAllModuleAccesForuserNotHaveAutorisationPrincipal(u, m, p)) {
//                    ua.setAutorisation_principal(Boolean.TRUE);
//                    utilisateursAccesDao.update(ua);
//                }
            }

        }
    }

    public OrclassModules finKeyByModuleForuser(OrclassUtilisateurs u, OrclassModules m) {
        Query q;
        q = em.createQuery("SELECT distinct m FROM OrclassModules m join m.orclassUtilisateursAccesList u WHERE u.idUtilisateur.idUtilisateur = :userId and u.idModule.idModule= :idModule")
                .setParameter("userId", u.getIdUtilisateur())
                .setParameter("idModule", m.getIdModule());
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (OrclassModules) q.getResultList().toArray()[0];
    }

    /*
    liste tout les moduls d un utilisateur
     */
    public Collection<OrclassUtilisateursAcces> getAllModuleAccesForuser(OrclassUtilisateurs u, OrclassModules m) {
        Query q;
        q = em.createQuery("SELECT  ua FROM OrclassUtilisateursAcces ua  WHERE ua.idUtilisateur.idUtilisateur = :userId and ua.idModule.idModule= :idModule")
                .setParameter("userId", u.getIdUtilisateur())
                .setParameter("idModule", m.getIdModule());
        return q.getResultList();
    }

    public Collection<OrclassUtilisateursAcces> getAllModuleAccesForuser(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p) {
        Query q;
        q = em.createQuery("SELECT  ua FROM OrclassUtilisateursAcces ua  WHERE ua.idUtilisateur.idUtilisateur = :userId and ua.idModule.idModule= :idModule AND ua.idProfil.idProfil= :idProfil")
                .setParameter("userId", u.getIdUtilisateur())
                .setParameter("idModule", m.getIdModule())
                .setParameter("idProfil", p.getIdProfil());
        return q.getResultList();
    }

    public Collection<OrclassUtilisateursAcces> getAllModuleAccesForuserNotHaveAutorisationPrincipal(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p) {
        Query q;
        q = em.createQuery("SELECT  ua FROM OrclassUtilisateursAcces ua  WHERE ua.idUtilisateur.idUtilisateur = :userId and ua.idModule.idModule= :idModule  and (ua.autorisation_principal=false or ua.autorisation_principal=0) and ua.idProfil.idProfil= :idProfil")
                .setParameter("userId", u.getIdUtilisateur())
                .setParameter("idModule", m.getIdModule())
                .setParameter("idProfil", p.getIdProfil());
        return q.getResultList();
    }

    public Collection<OrclassUtilisateursAcces> getAllModuleAccesForuserForAutorisation(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p) {
        Query q;
        q = em.createQuery("SELECT  ua FROM OrclassUtilisateursAcces ua  WHERE ua.idUtilisateur.idUtilisateur = :userId and ua.idModule.idModule= :idModule  and (ua.autorisation_principal=true or ua.autorisation_principal=1) and ua.idProfil.idProfil= :idProfil")
                .setParameter("userId", u.getIdUtilisateur())
                .setParameter("idModule", m.getIdModule())
                .setParameter("idProfil", p.getIdProfil());
        return q.getResultList();
    }

    public Collection<OrclassUtilisateursAcces> getAllFonctionnaliteAccesForUserForAutorisation(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils pr) {
        Query q;
        q = em.createQuery("SELECT  ua FROM OrclassUtilisateursAcces ua  WHERE ua.idUtilisateur.idUtilisateur = :userId and ua.idModule.idModule= :idModule  and (ua.autorisation_fonctionnalite=true or ua.autorisation_fonctionnalite=1) and ua.idFonctionnalite.idFonctionnalite= :idFonctionnalite and ua.idProfil.idProfil= :idProfil")
                .setParameter("userId", u.getIdUtilisateur())
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("idProfil", pr.getIdProfil())
                .setParameter("idModule", m.getIdModule());
        return q.getResultList();
    }

    public Collection<OrclassUtilisateursAcces> getAllFonctionnaliteAccesForUser(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils pr) {
        Query q;
        q = em.createQuery("SELECT  ua FROM OrclassUtilisateursAcces ua  WHERE ua.idUtilisateur.idUtilisateur = :userId and ua.idModule.idModule= :idModule  and  ua.idFonctionnalite.idFonctionnalite= :idFonctionnalite and ua.idProfil.idProfil= :idProfil")
                .setParameter("userId", u.getIdUtilisateur())
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("idProfil", pr.getIdProfil())
                .setParameter("idModule", m.getIdModule());
        return q.getResultList();
    }

    public Collection<OrclassModules> getModuleByUserForAccesNotAutorisation(OrclassUtilisateurs u) {
        String jpql = "SELECT distinct m FROM OrclassModules m join m.orclassUtilisateursAccesList u WHERE u.idUtilisateur.idUtilisateur = :userId and (u.autorisation_principal=false or u.autorisation_principal=0)";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", u.getIdUtilisateur());
        Collection<OrclassModules> listUserModule = new ArrayList<OrclassModules>();

        listUserModule = ModulesecuriteDao.findAllEntitiesByUsingQuery(jpql, param);
        return listUserModule;
    }

    @Override
    public void removeModulesToUser(OrclassUtilisateurs u, List<OrclassModules> mod, OrclassProfils p) {

        //verifie si les modules sont presents
        OrclassUtilisateursAcces uacces;
        if (mod.isEmpty() == false || mod != null) {
            for (OrclassModules m : mod) {
                //je le recherche dans la table pour pouvoir delete sinon impossible vu l etat 
                OrclassModules modSecu = this.finKeyByModuleForuser(u, m);

                if (modSecu != null && modSecu.getIdModule() != null) {
                    for (OrclassUtilisateursAcces ua : this.getAllModuleAccesForuserForAutorisation(u, modSecu, p)) {
                        uacces = ua;
                        uacces.setAutorisation_principal(Boolean.FALSE);
                        utilisateursAccesDao.update(uacces);
                    }

                }
            }
        }

    }

    public void addModuleFortoUserAcces(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p) {
        //verifie si les modules sont presents
        OrclassUtilisateursAcces uacces;
        if (m != null && m.getIdModule() != null) {
            for (OrclassUtilisateursAcces ua : this.getAllModuleAccesForuser(u, m, p)) {

                uacces = ua;
                uacces.setAutorisation_principal(Boolean.TRUE);
                utilisateursAccesDao.update(uacces);

            }
        }
    }

    @Override
    public void removeMenutoUser(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p, OrclassFonctionnalites f) {
        OrclassUtilisateursAcces uacces = null;
        for (OrclassUtilisateursAcces ua : this.getAllFonctionnaliteAccesForUserForAutorisation(u, m, f, p)) {
            uacces = ua;
            uacces.setAutorisation_fonctionnalite(Boolean.FALSE);
            utilisateursAccesDao.update(uacces);
        }

    }

//    @Override
//    public Collection<Menu> getMenusByRole(Role role) {
//        
//        String req = "select m from Menu m join m.rolemenuCollection rm "
//                + "where rm.rolemenuPK.roleId= :roleId";
//        Query q = em.createQuery(req).setParameter("roleId", role.getId());
//        return q.getResultList();
//    }
////    @Override
////    public void addMenuToRole(Role r, Menu m) {
////        Rolemenu roleMenu = new Rolemenu();
////        roleMenu.setRolemenuPK(new RolemenuPK(m.getId(), r.getId()));
////        rolemenuDao.create(roleMenu);
////    }
//    @Override
//    public void addMenusToRole(Role r, List<Menu> menus) {
//        //je recupère les Menus du Role
//        Collection<Menu> menuRole = this.getMenusByRole(r);
//        //je teste si le Role a deja des menus
//        if (menuRole.isEmpty()) {
//            //je parcours la liste et j'inserte
//            for (Menu m : menus) {
//                this.addMenuToRole(r, m);
//            }
//        } else {
//            for (Menu m : menus) {
//                if (menuRole.contains(m) == false) {
//                    this.addMenuToRole(r, m);
//                }
//            }
//        }
//    }
//    @Override
//    public void removeMenusToRole(Role r, Menu m) {
//        Rolemenu roleMenu=new Rolemenu();
//       roleMenu = rolemenuDao.find(new RolemenuPK(m.getId(), r.getId()));
//        if (roleMenu != null) {
//            rolemenuDao.delete(roleMenu);
//        }
//    }
//    @Override
    public Collection<OrclassMenus> getMenusForUser(OrclassUtilisateurs u) {
//        // les menus qui sont attribuer directement au role
//        Collection<Menu> listUserMenuRole = new ArrayList<Menu>();
//        if (u != null) {
//            String jpql = "SELECT m FROM Menu m join m.usermenuCollection u WHERE u.usermenuPK.userId = :userid";
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("userid", u.getId());
//            listUserMenuRole = menuDao.findAllEntitiesByUsingQuery(jpql, param);
//
//            // les menus qui sont dans la table user menu et attribuer a l utilisateur
//            param.clear();
//            jpql = "SELECT m FROM Menu m join m.usermenuCollection u WHERE"
//                    + " u.usermenuPK.userId = :userId and (u.autorisation=true or u.autorisation=1)";
//            param.put("userId", u.getId());
//            // on aurait pu utiliser :  listUserMenuRole.addAll(menuDao.findAllEntitiesByUsingQuery(jpql,param));       
//            for (Menu mu : menuDao.findAllEntitiesByUsingQuery(jpql, param)) {
//                if (listUserMenuRole.contains(mu) == false) {
//                    listUserMenuRole.add(mu);
//                }
//            }
//
//            // les menus qui sont dans la table user non attribuer a l utilisateur
//            param.clear();
//            jpql = "SELECT m FROM Menu m join m.usermenuCollection u WHERE"
//                    + " u.usermenuPK.userId = :userId and (u.autorisation=false or u.autorisation=0)";
//            param.put("userId", u.getId());
//            // on aurait pu utiliser : listUserMenuRole.removeAll(menuDao.findAllEntitiesByUsingQuery(jpql,param));
//            for (Menu mu : menuDao.findAllEntitiesByUsingQuery(jpql, param)) {
//                if (listUserMenuRole.contains(mu) == true) {
//                    listUserMenuRole.remove(mu);
//                }
//            }
//        }
//        return listUserMenuRole;
        return null;
    }
//    @Override
//    public Collection<Modulesecurite> getModulesByRole(Role role) {
//          Map<String, Object> param = new HashMap<>();
//        param.put("roleId",role.getId());
//        String jpql="select distinct rm.menu.moduleid from Rolemenu rm where rm.role.id = :roleId";
//        return ModulesecuriteDao.findAllEntitiesByUsingQuery(jpql, param);
//    }

    @Override
    public void insertUserInLogger(OrclassUtilisateurs u) {
//        Query q = em.createQuery("select c from ConnectionLogger c order By c.id DESC");
////        try {
//        List<ConnectionLogger> listCon = (List<ConnectionLogger>) q.getResultList();
//        if (listCon == null || listCon.isEmpty()) {
//
//            if (entreprisesDao.findAll().isEmpty() == Boolean.FALSE) {
//
//                ConnectionLogger connexionUser = new ConnectionLogger();
//                connexionUser.setIdUtilisateur(u);
//
//                connexionUser.setDateConnexion(new Date());
////                connexionUser.setOn_line(Boolean.TRUE);
//
////                em.persist(connexionUser);
//                connectionLoggerDao.create(connexionUser);
//            }
//
//        } else {
        ConnectionLogger connexionUser = new ConnectionLogger();
        connexionUser.setIdUtilisateur(u);
        connexionUser.setDateConnexion(new Date());
//            connexionUser.setOn_line(Boolean.TRUE);
//            em.persist(connexionUser);
        connectionLoggerDao.create(connexionUser);
//        }
//        } catch (Exception e) {
//            
//        }

    }

//    @Override
//    public void checkDateConnexionWithLastConnexion(Date date) throws LicenceException{
//        Query q=em.createQuery("select c from ConnectionLogger c order By c.id DESC");
//        ConnectionLogger con=null;
//        try{
//            List<ConnectionLogger> listCon=(List<ConnectionLogger>)q.getResultList();
//            if(listCon!=null && !listCon.isEmpty()){
//               con=(ConnectionLogger)q.getResultList().get(0);  
//            }
//        }catch(NoResultException e){
//            
//        }
//       //toute premiere connexion
//        if(con!=null){
//            System.out.println("controle de date");
//            Date lastConnectionDate=con.getDateConnexion();
//            
//            Calendar calLastConnexion=Calendar.getInstance();
//            Calendar calCurrent=Calendar.getInstance();
//            
//            calLastConnexion.setTime(lastConnectionDate);
//            calCurrent.setTime(date);
//            
//            //bandit t'as modifie la date systeme
//            if(calCurrent.before(calLastConnexion)){
//                System.out.println("PLEASE CHECK YOUR SYSTEM DATE.");
//                throw new LicenceException("PLEASE CHECK YOUR SYSTEM DATE.");
//            }
//            
//            
//        }
//        
//    }
    @Override
    public void checkDateConnexionWithLastConnexion(Date date) throws LicenceException {

        Query q = em.createQuery("select c from ConnectionLogger c order By c.id DESC");
        ConnectionLogger con = null;
        try {
            List<ConnectionLogger> listCon = (List<ConnectionLogger>) q.getResultList();
            if (listCon != null && !listCon.isEmpty()) {
                con = (ConnectionLogger) q.getResultList().get(0);
            }
        } catch (NoResultException e) {

        }
        //toute premiere connexion
        if (con != null) {
            System.out.println("controle de date");
            Date lastConnectionDate = con.getDateConnexion();

            Calendar calLastConnexion = Calendar.getInstance();
            Calendar calCurrent = Calendar.getInstance();
            if (lastConnectionDate == null) {
                System.out.println("PLEASE CHECK YOUR DATA BASE");
                throw new LicenceException("PLEASE CHECK YOUR DATA BASE ...LAST CONNECTION OF DATE IS NULL");
            }
            calLastConnexion.setTime(lastConnectionDate);
            calCurrent.setTime(date);

            //bandit t'as modifie la date systeme
            if (calCurrent.before(calLastConnexion)) {
                System.out.println("PLEASE CHECK YOUR SYSTEM DATE.");
                throw new LicenceException("PLEASE CHECK YOUR SYSTEM DATE.");
            }
//            if(date.before(lastConnectionDate)==false){
//                System.out.println("PLEASE CHECK YOUR SYSTEM DATE.");
//                throw new LicenceException("PLEASE CHECK YOUR SYSTEM DATE.");
//            }

        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteModuleSecurite(OrclassModules m) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//   
//        try {
//            List<Usermenu> listUsermenu = new ArrayList<>();
//            List<Usermodule> listUsermodule = new ArrayList<>();
//            List<Menu> listMenu = new ArrayList<>();
//            List<Modulesecurite> listModulesecurite = new ArrayList<>();
//            Query q;
////          List<Modulesecurite> listModuleMenu=new ArrayList<>();
//
//            tx.begin();
//            q = em.createQuery("SELECT u FROM Usermenu u WHERE u.menu.moduleid.id = :idm")
//                    .setParameter("idm", m.getId());
//            listUsermenu = q.getResultList();
//            if (!listUsermenu.isEmpty()) {
//
//                for (Usermenu um : listUsermenu) {
//                    em.remove(um);
//                }
//
//            }
//
//            q = em.createQuery("SELECT u FROM Usermodule u WHERE u.modulesecurite.id= :idm")
//                    .setParameter("idm", m.getId());
//            listUsermodule = q.getResultList();
//            if (!listUsermodule.isEmpty()) {
//
//                for (Usermodule um : listUsermodule) {
//                    em.remove(um);
//                }
//
//            }
//            q = em.createQuery("SELECT u FROM Menu u WHERE u.moduleid.id =:idm")
//                    .setParameter("idm", m.getId());
//            listMenu = q.getResultList();
//            if (!listUsermodule.isEmpty()) {
//
//                for (Menu um : listMenu) {
//                    em.remove(um);
//                }
//
//            }
//
//            //delette module
////          em.remove(m);
//            tx.commit();
//
//        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
//            try {
//                tx.rollback();
//                throw e;
//
//            } catch (Exception ex) {
//
//            }
//        }

    }

    @Override
    public Collection<OrclassFonctionnalites> getFonctionnaliteForNotModuleUserProfile(OrclassMenus u, OrclassModules m) {
        /*
        a developper
         */
        return null;

    }

    @Override
    public Collection<OrclassUtilisateursAcces> getUtilisateurAccesForUser(OrclassUtilisateurs u) {
        Query q;
        q = em.createQuery("SELECT ua FROM OrclassUtilisateursAcces ua WHERE ua.idUtilisateurAcces.idUtilisateurAcces= :idUtilisateurAcces and (ua.autorisation_principal=true or ua.autorisation_principal=1) ")
                .setParameter("idUtilisateurAcces", u.getIdUtilisateur());
        return q.getResultList();
    }

    @Override
    public Collection<OrclassMenus> getMenusByUser(OrclassUtilisateurs u) {
        Query q = null, q1;
        q1 = em.createQuery(" SELECT DISTINCT ua.idFonctionnalite FROM OrclassUtilisateursAcces ua WHERE ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_fonctionnalite=true  or ua.autorisation_fonctionnalite=1) and ua.idProfil.actif= :value")
                .setParameter("iduser", u.getIdUtilisateur())
                .setParameter("value", Boolean.TRUE);
        //je recupere les menus lier a la fonctionnalite
        Collection<OrclassFonctionnalites> colFonctionnalite = new ArrayList<>();
        Collection<OrclassMenus> colMenuByUser = new ArrayList<OrclassMenus>();
        colFonctionnalite = q1.getResultList();
        if (!colFonctionnalite.isEmpty()) {
            for (OrclassFonctionnalites f : colFonctionnalite) {
                for (OrclassMenus m : this.orclassMenusDao.getAllMenuByFonctionnalite(f)) {
                    if (colMenuByUser.contains(m) == Boolean.FALSE) {
                        colMenuByUser.add(m);
                    }
                }
            }
        }

//        q = em.createQuery("SELECT DISTINCT m.idMenu FROM OrclassMenusAcces m JOIN m.idFonctionnalite.orclassUtilisateursAccesList u where u.idUtilisateur.idUtilisateur= :iduser    and u.autorisation_fonctionnalite= :value")
//                .setParameter("iduser", u.getIdUtilisateur())
//                .setParameter("value", Boolean.TRUE);
        return colMenuByUser;

    }

    @Override

    public void addUtilisateur(OrclassUtilisateurs u, List<OrclassProfils> profil, Societe e) {

        try {
            tx.begin();
            OrclassUtilisateurs user = null;
            OrclassUtilisateursAcces ua = null;
            if (userDao.findEntityHavingValue("login", u.getLogin().toUpperCase()) == null) {
                user = new OrclassUtilisateurs();
                user.setActif(u.getActif());
                user.setAdresse(u.getAdresse());
                user.setDateCreation(new Date());
                user.setNom(u.getNom());
                user.setLogin(u.getLogin().toUpperCase());
                user.setPrenom(u.getPrenom());
//                user.setPassword(Crypto.sha256(u.getPassword().toUpperCase()));
                user.setTypeUtilisateur(TypeUtilisateur.UTILISATEUR_ENTITE);
            
                user.setIdSociete(e);
                user.setLangue(u.getLangue());
                user.setNumeroCNI(u.getNumeroCNI());
                user.setSituationMatrimoniale(u.getSituationMatrimoniale());
                user.setSexe(u.getSexe());
                user.setAllAccessForIntermediaire(u.getAllAccessForIntermediaire());
//                user.setCle_securite(Crypto.sha256(createCleSecuriteForUser(e).toUpperCase()));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe
                em.persist(user);
//                user = em.merge(user);
            } else {
                user = em.merge(userDao.findEntityHavingValue("login", u.getLogin().toUpperCase()));
            }

            for (OrclassProfils p : profil) {
                for (OrclassProfilsAcces pa : profilsAccesDao.getAllAccesByProfil(p)) {
                    ua = utilisateursAccesDao.finKey(pa, user);
                    if (ua != null) {
                        continue;
                    }
                    ua = new OrclassUtilisateursAcces();
                    ua.setAutorisation_principal(Boolean.TRUE);
                    ua.setAutorisation_fonctionnalite(Boolean.TRUE);
                    ua.setAutorisation_action(Boolean.TRUE);
                    ua.setIdAction(pa.getIdAction());
                    ua.setIdFonctionnalite(pa.getIdFonctionnalite());
                    ua.setIdModule(pa.getIdModule());
                    ua.setIdProfil(pa.getIdProfil());
                    ua.setIdUtilisateur(user);
                    em.persist(ua);
                }

            }

            tx.commit();

        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            try {
                tx.rollback();
                throw ex;
            } catch (Throwable t) {

            }
        } catch (Throwable th) {

            System.out.println("parametrage.Securite.addUtilisateur(): " + th.getMessage());
            throw th;
        }
    }

    @Override
    public Collection<OrclassFonctionnalites> getFonctionnaliteByModuleByUserByProfil(OrclassModules m, OrclassUtilisateurs u, OrclassProfils p) {
        String jpql = "SELECT distinct m FROM OrclassFonctionnalites m join m.orclassUtilisateursAccesList u WHERE"
                + " m.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur = :userId and (u.autorisation_fonctionnalite=true or u.autorisation_fonctionnalite=1) and u.idProfil.idProfil= :idProfil";
        Map<String, Object> param = new HashMap<String, Object>();

        // les menus qui sont dans la table user acces et attribuer a l utilisateur
        param.clear();

        param.put("moduleid", m.getIdModule());
        param.put("userId", u.getIdUtilisateur());
        param.put("idProfil", p.getIdProfil());
        return fonctionnalitesDao.findAllEntitiesByUsingQuery(jpql, param);

    }

    @Override
    public Collection<OrclassActions> getActionNotForUserForFonctionnalite(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils p) {
        Query q;
        q = em.createQuery("SELECT distinct t FROM OrclassActions t where t.idFonctionnalite.idFonctionnalite = :idFonctionnalite and t.idAction NOT IN (SELECT m FROM OrclassActions m join m.orclassUtilisateursAccesList u WHERE u.idFonctionnalite.idFonctionnalite= :idFonctionnalite and "
                + " u.idModule.idModule = :moduleid and u.idUtilisateur.idUtilisateur = :userid and u.idProfil.idProfil= :idProfil and (u.autorisation_action=true or u.autorisation_action=1))")
                .setParameter("userid", u.getIdUtilisateur())
                .setParameter("moduleid", m.getIdModule())
                .setParameter("idProfil", p.getIdProfil())
                .setParameter("idFonctionnalite", f.getIdFonctionnalite());
        return q.getResultList();

    }

    @Override
    public void addMenutoUser(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils pr) {
        OrclassUtilisateursAcces uacces = new OrclassUtilisateursAcces();
        Collection<OrclassUtilisateursAcces> colUacces = new ArrayList<>();
        colUacces = this.getAllFonctionnaliteAccesForUser(u, m, f, pr);
        if (!colUacces.isEmpty()) {
            for (OrclassUtilisateursAcces ua : colUacces) {
                uacces = ua;
                uacces.setAutorisation_principal(Boolean.TRUE);
                uacces.setAutorisation_fonctionnalite(Boolean.TRUE);
                utilisateursAccesDao.update(uacces);
            }
        } else {
            OrclassProfilsAcces pacces = null;
            for (OrclassActions act : actionsDao.getAllactionByFonctionnalite(f, m)) {
                pacces = profilsAccesDao.finkey(m, f, act, pr);
                if (pacces == null) {
                    pacces = new OrclassProfilsAcces();
                    pacces.setAutorisation(Boolean.TRUE);
                    pacces.setIdAction(act);
                    pacces.setIdFonctionnalite(f);
                    pacces.setIdModule(m);
                    pacces.setIdProfil(pr);
                    profilsAccesDao.create(pacces);

                    uacces = utilisateursAccesDao.finKey(pacces, u);
                    if (uacces == null) {
                        uacces = new OrclassUtilisateursAcces();
                        uacces.setAutorisation_fonctionnalite(Boolean.TRUE);
                        uacces.setAutorisation_action(Boolean.TRUE);
                        uacces.setAutorisation_principal(Boolean.TRUE);
                        uacces.setIdAction(act);
                        uacces.setIdFonctionnalite(f);
                        uacces.setIdModule(m);
                        uacces.setIdProfil(pr);
                        uacces.setIdUtilisateur(u);
                        utilisateursAccesDao.create(uacces);
                    }
                } else {
                    uacces = utilisateursAccesDao.finKey(pacces, u);
                    if (uacces == null) {
                        uacces = new OrclassUtilisateursAcces();
                        uacces.setAutorisation_fonctionnalite(Boolean.TRUE);
                        uacces.setAutorisation_action(Boolean.TRUE);
                        uacces.setAutorisation_principal(Boolean.TRUE);
                        uacces.setIdAction(act);
                        uacces.setIdFonctionnalite(f);
                        uacces.setIdModule(m);
                        uacces.setIdProfil(pr);
                        uacces.setIdUtilisateur(u);
                        utilisateursAccesDao.create(uacces);
                    } else {
                        uacces.setAutorisation_principal(Boolean.TRUE);
                        uacces.setAutorisation_fonctionnalite(Boolean.TRUE);
                        utilisateursAccesDao.update(uacces);
                    }

                }
            }
        }
    }

    @Override
    public void removeActiontoUser(OrclassUtilisateurs u, OrclassModules m, OrclassProfils p, OrclassFonctionnalites f, OrclassActions a) {
        OrclassUtilisateursAcces uacces = null;
        OrclassProfilsAcces pa = profilsAccesDao.finkey(m, f, a, p);
        if (pa != null) {
            uacces = utilisateursAccesDao.finKey(pa, u);
            if (uacces != null) {
                uacces.setAutorisation_action(Boolean.FALSE);
                utilisateursAccesDao.update(uacces);
            }
        }

    }

    @Override
    public void addActiontoUser(OrclassUtilisateurs u, OrclassModules m, OrclassFonctionnalites f, OrclassProfils pr, OrclassActions act) {
        OrclassUtilisateursAcces uacces = new OrclassUtilisateursAcces();
        Collection<OrclassUtilisateursAcces> colUacces = new ArrayList<>();
        OrclassProfilsAcces pacces;

        pacces = profilsAccesDao.finkey(m, f, act, pr);
        if (pacces == null) {
            pacces = new OrclassProfilsAcces();
            pacces.setAutorisation(Boolean.TRUE);
            pacces.setIdAction(act);
            pacces.setIdFonctionnalite(f);
            pacces.setIdModule(m);
            pacces.setIdProfil(pr);
            profilsAccesDao.create(pacces);

            uacces = utilisateursAccesDao.finKey(pacces, u);
            if (uacces == null) {
                uacces = new OrclassUtilisateursAcces();
                uacces.setAutorisation_fonctionnalite(Boolean.TRUE);
                uacces.setAutorisation_action(Boolean.TRUE);
                uacces.setAutorisation_principal(Boolean.TRUE);
                uacces.setIdAction(act);
                uacces.setIdFonctionnalite(f);
                uacces.setIdModule(m);
                uacces.setIdProfil(pr);
                uacces.setIdUtilisateur(u);
                utilisateursAccesDao.create(uacces);
            }
        } else {
            uacces = utilisateursAccesDao.finKey(pacces, u);
            if (uacces == null) {
                uacces = new OrclassUtilisateursAcces();
                uacces.setAutorisation_fonctionnalite(Boolean.TRUE);
                uacces.setAutorisation_action(Boolean.TRUE);
                uacces.setAutorisation_principal(Boolean.TRUE);
                uacces.setIdAction(act);
                uacces.setIdFonctionnalite(f);
                uacces.setIdModule(m);
                uacces.setIdProfil(pr);
                uacces.setIdUtilisateur(u);
                utilisateursAccesDao.create(uacces);
            } else {
                uacces.setAutorisation_action(Boolean.TRUE);
                utilisateursAccesDao.update(uacces);
            }

        }

    }

    @Override
    public Collection<OrclassModules> getAllModulesByEntreprise(Societe s) {
        Query q;
        q = em.createQuery("SELECT em.orclassModules FROM OrclassEntreprisesModules em WHERE em.societe = :s")
                .setParameter("s", s);

        return q.getResultList();
    }

    @Override
    public String addProfilForAccess(Collection<OrclassModules> modules, OrclassFonctionnalites[][] f, OrclassProfils pr) {
        String messageError = null;
        try {
            tx.begin();

            OrclassProfils p = profilsDao.findEntityHavingValue("code", pr.getCode());
            OrclassFonctionnalites fonc = null;
            OrclassProfilsAcces pa;
            int controle = 0;// la valeur 0 signifit que aucune fonctionnatite n a ete enregistre dans ce cas on declencher une excetion 
            if (p == null) {
                p = new OrclassProfils();
                p.setActif(Boolean.TRUE);
                p.setCode(pr.getCode());
                p.setDateCreation(new Date());
                p.setLibelle(pr.getLibelle());
                p.setIdSociete(pr.getIdSociete());
                em.persist(p);
            } else if (p != null && p.getIdProfil() != null) {
                messageError = "FATAL ERROR PLEASE CHANGE CODE... PROFIL EXIST";
                throw new GlobalException(messageError);
            }

            for (OrclassModules m : modules) {
                if (m.getIndiceTableau() < 0) {

                    continue;
                }
                int indice = m.getIndiceTableau();
                for (int j = 0; j < f[indice].length; j++) {
                    fonc = f[indice][j];
                    if (fonc == null) {
                        continue;
                    }
                    for (OrclassActions act : actionsDao.getAllactionByFonctionnalite(fonc, m)) {
                        pa = profilsAccesDao.finkey(m, fonc, act, p);
                        if (pa != null && pa.getIdProfilAcces() != null) {
                            messageError = "FATAL ERROR PLEASE PERMISSION EXISTE... PROFIL ACCESS IS ALREDY HERE";
                            throw new GlobalException(messageError);
                        }
                        pa = new OrclassProfilsAcces();
                        pa.setAutorisation(Boolean.TRUE);
                        pa.setIdAction(act);
                        pa.setIdModule(m);
                        pa.setIdProfil(p);
                        pa.setIdFonctionnalite(fonc);
                        em.persist(pa);
                        controle++;
                    }
                }
            }
            if (controle == 0) {
                messageError = "FATAL ERROR PLEASE SELECT A FUNCTION... TRY AGAINST";
                throw new GlobalException(messageError);
            }
            tx.commit();
        } catch (Exception e) {
            try {
                tx.rollback();
                logger.error("Erreur Survenu", e.getMessage());
                throw new GlobalException("GlobalException survenu " + e.getMessage() + " " + messageError);

            } catch (IllegalStateException | SecurityException | SystemException ex) {

                logger.error("erreur survenu", ex.getMessage() + " " + messageError);

            }
        }

        return exception.Success.OPERATION_SUCCESS.toString();

    }

    @Override
    public Collection<OrclassFonctionnalites> getFonctionaliteHaveAction(OrclassModules m) {
        Query q;
        q = em.createQuery("SELECT DISTINCT a.idFonctionnalite FROM OrclassActions a WHERE a.idFonctionnalite.idModule.idModule= :idModule")
                .setParameter("idModule", m.getIdModule());
        return q.getResultList();
    }

    @Override
    public Collection<OrclassActions> getAllActionByFonctionnaliteAndProfile(OrclassModules modules, OrclassFonctionnalites f, OrclassProfils pr) {
        Query q;
        q = em.createQuery("SELECT pa.idAction FROM OrclassProfilsAcces pa WHERE pa.idFonctionnalite.idFonctionnalite= :idFonctionnalite  AND pa.idModule.idModule= :idModule and pa.idProfil.idProfil= :idProfil  ")
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("idModule", modules.getIdModule())
                .setParameter("idProfil", pr.getIdProfil());
        return q.getResultList();

    }

    @Override
    public Collection<OrclassActions> getAllActionByFonctionnaliteNotHaveProfile(OrclassFonctionnalites f, OrclassProfils p) {
        Query q;
        q = em.createQuery("select a FROM OrclassActions a WHERE NOT EXISTS ( SELECT pa.idAction FROM OrclassProfilsAcces pa WHERE pa.idProfil.idProfil= :idProfil ) and a.idFonctionnalite.idFonctionnalite= :idFonctionnalite")
                .setParameter("idFonctionnalite", f.getIdFonctionnalite())
                .setParameter("idProfil", p.getIdProfil());
        return q.getResultList();
    }

    @Override
    public Boolean checkEtatProfilExisteForUserAccess(OrclassProfils pr) {
        Query q;
        q = em.createQuery("SELECT  DISTINCT ua FROM OrclassUtilisateursAcces ua WHERE ua.idProfil.idProfil= :idProfil")
                .setParameter("idProfil", pr.getIdProfil());
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public void deleteActionForProfilAccess(OrclassProfilsAcces pa) {
//       em.createQuery("DELETE  FROM OrclassProfilsAcces pa  WHERE pa.idProfilAcces= :idProfilAcces")
//               .setParameter("idProfilAcces", pa.getIdProfilAcces());

        try {
            tx.begin();
            em.detach(pa);
            tx.commit();
        } catch (Exception e) {

        }
    }

    @Override
    public Boolean testeFirstConnection(OrclassUtilisateurs user) {
        Query q = em.createQuery("select c from ConnectionLogger c WHERE c.idUtilisateur.idUtilisateur= :idUtilisateur")
                .setParameter("idUtilisateur", user.getIdUtilisateur());
        if (q.getResultList().isEmpty()) {
            /*
             l utilisateur user ne c est jamais connecter d ou il se connect comme sa pour la premier fois
             */
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

   
    // creation de la cles de securite
    public String createCleSecuriteForUser(Societe e) {
//        user.setCle_securite(Crypto.sha256("141087"));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe

        String cle = KeyGenCode.gen();
        if (cle != null) {
            //verification si cette cle a ete deja generer  
            while (Objects.equals(userDao.cleSecuriteExiste(cle, e), Boolean.TRUE)) {
                cle = KeyGenCode.gen();
            }
        }
        return cle;
    }

    public String updateCleSecuriteForUser(Societe e, OrclassUtilisateurs u) {
//        user.setCle_securite(Crypto.sha256("141087"));// la cle de securite c est 6chiffre important lors de la mise a jour d un mot de passe

        String cle = KeyGenCode.gen();
        if (cle != null && u != null && u.getIdSociete()!= null) {
            //verification si cette cle a ete deja generer  
            while (Objects.equals(userDao.cleSecuriteExiste(cle, e), Boolean.TRUE) && u.getCle_securite().equals(Crypto.sha256(cle)) == Boolean.FALSE) {
                cle = KeyGenCode.gen();
            }
        }
        return cle;
    }

    @Override
    public void resetCleSecurite(OrclassUtilisateurs u, Societe e) {
        if (u != null && u.getIdUtilisateur() != null && e != null && e.getCodesoci() != null) {
            u.setCle_securite(Crypto.sha256(updateCleSecuriteForUser(e, u)));
            userDao.update(u);
        }

    }

}
